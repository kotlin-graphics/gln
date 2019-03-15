@file:Suppress("NOTHING_TO_INLINE")

package gln.framebuffer


import kool.IntBuffer
import kool.adr
import kool.get
import kool.rem
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL30.GL_FRAMEBUFFER
import org.lwjgl.opengl.GL30.GL_RENDERBUFFER
import org.lwjgl.opengl.GL30C
import org.lwjgl.opengl.GL32
import java.nio.IntBuffer
import kotlin.properties.Delegates

/**
 * Created by elect on 18/04/17.
 */

var framebufferName: IntBuffer by Delegates.notNull()

val defaultFramebuffer = 0

inline fun glFramebufferRenderbuffer(attachment: Int, renderbuffer: IntArray) = GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, attachment, GL_RENDERBUFFER, renderbuffer[0])
inline fun glFramebufferRenderbuffer(attachment: Int, renderbuffer: IntBuffer) = GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, attachment, GL_RENDERBUFFER, renderbuffer[0])


inline fun glBindFramebuffer(target: Int, framebuffer: Enum<*>) = GL30.glBindFramebuffer(target, framebufferName[framebuffer])
inline fun glBindFramebuffer(target: Int, framebuffer: IntBuffer) = GL30.glBindFramebuffer(target, framebuffer[0])
inline fun glBindFramebuffer(framebuffer: Enum<*>) = GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, framebufferName[framebuffer])
inline fun glBindFramebuffer(framebuffer: IntBuffer) = GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, framebuffer[0])
inline fun glBindFramebuffer(framebuffer: Int) = GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, framebuffer)
inline fun glBindFramebuffer() = GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0)


//inline fun glFramebufferTexture2D(target: Int, attachment: Int, textarget: Int, texture: Int) = GL30.glFramebufferTexture2D(target, attachment, textarget, texture, 0) TODO renable without target
inline fun glFramebufferTexture(attachment: Int, texture: Int, level: Int = 0) = GL32.glFramebufferTexture(GL_FRAMEBUFFER, attachment, texture, level)

inline fun glCheckFramebufferStatus() = GL30.glCheckFramebufferStatus(GL_FRAMEBUFFER)


inline class GlFramebuffers(val names: IntBuffer) {
    val rem get() = names.rem
    val adr get() = names.adr
}

fun GlFramebuffers(size: Int) = GlFramebuffers(IntBuffer(size))

inline class GlFramebuffer(val name: Int = -1) {

    fun bind() = GL30C.glBindFramebuffer(GL30C.GL_FRAMEBUFFER, name)

    fun bindRead() = GL30C.glBindFramebuffer(GL30C.GL_READ_FRAMEBUFFER, name)
    fun bindDraw() = GL30C.glBindFramebuffer(GL30C.GL_DRAW_FRAMEBUFFER, name)

    inline fun bound(block: GlFramebufferDsl.() -> Unit): GlFramebuffer {
        GL30C.glBindFramebuffer(GL30C.GL_FRAMEBUFFER, name)
        GlFramebufferDsl.name = name
        GlFramebufferDsl.block()
        GL30C.glBindFramebuffer(GL30C.GL_FRAMEBUFFER, 0)
        return this
    }

    fun delete() = GL30C.glDeleteFramebuffers(name)

    companion object {
        fun gen(): GlFramebuffer = GlFramebuffer(GL30C.glGenFramebuffers())
    }
}