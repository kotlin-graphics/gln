@file:Suppress("NOTHING_TO_INLINE")

package gln.framebuffer

import gln.FramebufferTarget
import gln.identifiers.GlTexture
import gln.renderbuffer.GlRenderbuffer
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL30C
import org.lwjgl.opengl.GL32
import java.nio.IntBuffer

//
//inline fun initFramebuffer(framebuffer: IntBuffer, block: GlFramebufferDsl.() -> Unit) = framebuffer.put(0, initFramebuffer(block))
//inline fun initFramebuffer(block: GlFramebufferDsl.() -> Unit): Int {
//    GL30.nglGenFramebuffers(1, bufAd)
//    val res = buf.getInt(0)
//    GlFramebufferDsl.name = res   // bind
//    GlFramebufferDsl.block()
//    glBindFramebuffer()
//    return res
//}
//
//inline fun initFramebuffers(block: GlFramebuffersDsl.() -> Unit) = initFramebuffers(framebufferName, block)
//inline fun initFramebuffers(framebuffer: IntBuffer, block: GlFramebuffersDsl.() -> Unit) {
//    GL30.nglGenFramebuffers(framebuffer.rem, framebuffer.adr + framebuffer.pos * Int.BYTES)
//    GlFramebuffersDsl.names = framebuffer
//    GlFramebuffersDsl.block()
//}
//
//inline fun <R> withFramebuffer(framebuffer: IntBuffer, block: GlFramebufferDsl.() -> R) = withFramebuffer(framebuffer[0], block)
//inline fun <R> withFramebuffer(framebuffer: Enum<*>, block: GlFramebufferDsl.() -> R) = withFramebuffer(framebufferName[framebuffer], block)
//inline fun <R> withFramebuffer(framebuffer: IntArray, block: GlFramebufferDsl.() -> R) = withFramebuffer(framebuffer[0], block)
//inline fun <R> withFramebuffer(block: GlFramebufferDsl.() -> R) = withFramebuffer(0, block)
//inline fun <R> withFramebuffer(framebuffer: Int, block: GlFramebufferDsl.() -> R): R {
//    GlFramebufferDsl.name = framebuffer
//    return GlFramebufferDsl.block()
//}

// TODO check if leave, backup current fbo?
//inline fun withDefaultFramebuffer(block: Framebuffer.() -> Unit) {
//    Framebuffer.name = 0
//    Framebuffer.block()
//}

object GlFramebufferDsl {

    var name = 0


    inline fun texture(target: FramebufferTarget, attachment: Int, texture: GlTexture, level: Int = 0) = GL32.glFramebufferTexture(target.i, attachment, texture.name, level)
    inline fun texture(attachment: Int, texture: GlTexture, level: Int = 0) = GL32.glFramebufferTexture(GL30.GL_FRAMEBUFFER, attachment, texture.name, level)

    inline fun texture2D(target: Int, attachment: Int, texture: GlTexture, level: Int = 0) = GL30.glFramebufferTexture2D(target, attachment, GL11.GL_TEXTURE_2D, texture.name, level)

    fun renderbuffer(target: FramebufferTarget, attachment: Int, renderbuffer: GlRenderbuffer) = GL30C.glFramebufferRenderbuffer(target.i, attachment, GL30C.GL_RENDERBUFFER, renderbuffer.name)
    fun renderbuffer(attachment: Int, renderbuffer: GlRenderbuffer) = GL30C.glFramebufferRenderbuffer(GL30C.GL_FRAMEBUFFER, attachment, GL30C.GL_RENDERBUFFER, renderbuffer.name)

    val complete: Boolean
        get() = when (val status = GL30.glCheckFramebufferStatus(GL30.GL_FRAMEBUFFER)) {
            GL30.GL_FRAMEBUFFER_COMPLETE -> true
            else -> {
                val statusString = when (status) {
                    GL30.GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT -> "GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT"
                    GL30.GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT -> "GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT"
                    GL30.GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER -> "GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER"
                    GL30.GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER -> "GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER"
                    GL30.GL_FRAMEBUFFER_UNSUPPORTED -> "GL_FRAMEBUFFER_UNSUPPORTED"
                    GL30.GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE -> "GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE"
                    GL32.GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS -> "GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS"
                    GL30.GL_FRAMEBUFFER_UNDEFINED -> "GL_FRAMEBUFFER_UNDEFINED"
                    else -> throw IllegalStateException()
                }
                println("framebuffer incomplete, status: $statusString")
                false
            }
        }

    fun getParameter(attachment: Int, pName: Int) = GL30.glGetFramebufferAttachmentParameteri(GL30.GL_FRAMEBUFFER, attachment, pName)

    fun getDepthParameter(pName: Int) = GL30.glGetFramebufferAttachmentParameteri(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT, pName)
    fun getStencilParameter(pName: Int) = GL30.glGetFramebufferAttachmentParameteri(GL30.GL_FRAMEBUFFER, GL30.GL_STENCIL_ATTACHMENT, pName)
    fun getDepthStencilParameter(pName: Int) = GL30.glGetFramebufferAttachmentParameteri(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_STENCIL_ATTACHMENT, pName)

    fun getColorEncoding(index: Int = 0) = GL30.glGetFramebufferAttachmentParameteri(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0 + index, GL30.GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING)
}

object GlFramebuffersDsl {

    lateinit var names: IntBuffer

    inline fun <E : Enum<E>> E.bind(block: GlFramebufferDsl.() -> Unit) {
        val name = names[ordinal]
        GL30C.glBindFramebuffer(GL30.GL_FRAMEBUFFER, name)
        GlFramebufferDsl.name = name
        GlFramebufferDsl.block()
    }
}