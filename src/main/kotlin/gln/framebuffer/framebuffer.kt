@file:Suppress("NOTHING_TO_INLINE")

package gln.framebuffer


import glm_.vec2.Vec2i
import gln.FramebufferTarget
import gln.gl
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

    // --- [ glInvalidateFramebuffer ] ---

    /**
     * Invalidate the content some or all of a framebuffer object's attachments.
     *
     * @param target      the target to which the framebuffer is attached. One of:<br><table><tr><td>{@link GL30#GL_FRAMEBUFFER FRAMEBUFFER}</td><td>{@link GL30#GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER}</td><td>{@link GL30#GL_READ_FRAMEBUFFER READ_FRAMEBUFFER}</td></tr></table>
     * @param attachments the address of an array identifying the attachments to be invalidated
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glInvalidateFramebuffer">Reference Page</a>
     */
    fun invalidate(target: FramebufferTarget, vararg attachments: Int) = gl.invalidateFramebuffer(target, *attachments)

    /**
     * Invalidate the content some or all of a framebuffer object's attachments.
     *
     * @param target the target to which the framebuffer is attached. One of:<br><table><tr><td>{@link GL30#GL_FRAMEBUFFER FRAMEBUFFER}</td><td>{@link GL30#GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER}</td><td>{@link GL30#GL_READ_FRAMEBUFFER READ_FRAMEBUFFER}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glInvalidateFramebuffer">Reference Page</a>
     */
    fun invalidate(target: FramebufferTarget, attachment: Int) = gl.invalidateFramebuffer(target, attachment)

    // --- [ glInvalidateSubFramebuffer ] ---

    /**
     * Invalidates the content of a region of some or all of a framebuffer object's attachments.
     *
     * @param target      the target to which the framebuffer is attached. One of:<br><table><tr><td>{@link GL30#GL_FRAMEBUFFER FRAMEBUFFER}</td><td>{@link GL30#GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER}</td><td>{@link GL30#GL_READ_FRAMEBUFFER READ_FRAMEBUFFER}</td></tr></table>
     * @param attachments an array identifying the attachments to be invalidated
     * @param offset      the offset of the region to be invalidated
     * @param size        the size of the region to be invalidated
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glInvalidateSubFramebuffer">Reference Page</a>
     */
    fun invalidateSubFramebuffer(target: FramebufferTarget, offset: Vec2i, size: Vec2i, vararg attachments: Int) = gl.invalidateSubFramebuffer(target, offset, size, *attachments)

    /**
     * Invalidates the content of a region of some or all of a framebuffer object's attachments.
     *
     * @param target the target to which the framebuffer is attached. One of:<br><table><tr><td>{@link GL30#GL_FRAMEBUFFER FRAMEBUFFER}</td><td>{@link GL30#GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER}</td><td>{@link GL30#GL_READ_FRAMEBUFFER READ_FRAMEBUFFER}</td></tr></table>
     * @param offset the offset of the region to be invalidated
     * @param size   the size of the region to be invalidated
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glInvalidateSubFramebuffer">Reference Page</a>
     */
    fun invalidateSubFramebuffer(target: FramebufferTarget, offset: Vec2i, size: Vec2i, attachment: Int) = gl.invalidateSubFramebuffer(target, offset, size, attachment)

    companion object {
        fun gen(): GlFramebuffer = GlFramebuffer(GL30C.glGenFramebuffers())
    }
}