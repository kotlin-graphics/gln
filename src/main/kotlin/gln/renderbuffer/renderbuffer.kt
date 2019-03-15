@file:Suppress("NOTHING_TO_INLINE")

package gln.renderbuffer

import glm_.vec2.Vec2i
import kool.IntBuffer
import kool.adr
import kool.get
import kool.rem
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL30C
import java.nio.IntBuffer
import kotlin.properties.Delegates


var renderbufferName: IntBuffer by Delegates.notNull()


inline fun glRenderbufferStorageMultisample(samples: Int, internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorageMultisample(GL30.GL_RENDERBUFFER, samples, internalFormat, size.x, size.y)

inline fun glBindRenderbuffer() = GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, 0)
inline fun glBindRenderbuffer(renderbuffer: IntArray) = GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, renderbuffer[0])
inline fun glBindRenderbuffer(renderbuffer: IntBuffer) = GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, renderbuffer[0])

inline fun glRenderbufferStorage(internalFormat: Int, width: Int, height: Int) = GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, internalFormat, width, height)
inline fun glRenderbufferStorage(internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, internalFormat, size.x, size.y)
inline fun glRenderbufferStorage(target: Int, internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorage(target, internalFormat, size.x, size.y)


inline class GlRenderbuffers(val names: IntBuffer) {
    val rem get() = names.rem
    val adr get() = names.adr
}

fun GlRenderbuffers(size: Int) = GlRenderbuffers(IntBuffer(size))

inline class GlRenderbuffer(val name: Int = -1) {

    inline fun bind(block: RenderBuffer.() -> Unit): GlRenderbuffer {
        GL30C.glBindRenderbuffer(GL30C.GL_RENDERBUFFER, name)
        RenderBuffer.block()
        GL30C.glBindRenderbuffer(GL30C.GL_RENDERBUFFER, 0)
        return this
    }

    fun delete() = GL30C.glDeleteRenderbuffers(name)

    companion object {
        fun gen(): GlRenderbuffer = GlRenderbuffer(GL30C.glGenRenderbuffers())
    }
}