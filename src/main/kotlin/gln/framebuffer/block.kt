@file:Suppress("NOTHING_TO_INLINE")

package gln.framebuffer

import glm_.BYTES
import gln.buf
import gln.bufAd
import gln.objects.GlTexture
import gln.renderbuffer.GlRenderbuffer
import kool.get
import gln.texture.textureName
import kool.adr
import kool.pos
import kool.rem
import org.lwjgl.opengl.*
import java.nio.IntBuffer


inline fun initFramebuffer(framebuffer: IntBuffer, block: GlFramebufferDsl.() -> Unit) = framebuffer.put(0, initFramebuffer(block))
inline fun initFramebuffer(block: GlFramebufferDsl.() -> Unit): Int {
    GL30.nglGenFramebuffers(1, bufAd)
    val res = buf.getInt(0)
    GlFramebufferDsl.name = res   // bind
    GlFramebufferDsl.block()
    glBindFramebuffer()
    return res
}

inline fun initFramebuffers(block: Framebuffers.() -> Unit) = initFramebuffers(framebufferName, block)
inline fun initFramebuffers(framebuffer: IntBuffer, block: Framebuffers.() -> Unit) {
    GL30.nglGenFramebuffers(framebuffer.rem, framebuffer.adr + framebuffer.pos * Int.BYTES)
    Framebuffers.names = framebuffer
    Framebuffers.block()
}

inline fun <R> withFramebuffer(framebuffer: IntBuffer, block: GlFramebufferDsl.() -> R) = withFramebuffer(framebuffer[0], block)
inline fun <R> withFramebuffer(framebuffer: Enum<*>, block: GlFramebufferDsl.() -> R) = withFramebuffer(framebufferName[framebuffer], block)
inline fun <R> withFramebuffer(framebuffer: IntArray, block: GlFramebufferDsl.() -> R) = withFramebuffer(framebuffer[0], block)
inline fun <R> withFramebuffer(block: GlFramebufferDsl.() -> R) = withFramebuffer(0, block)
inline fun <R> withFramebuffer(framebuffer: Int, block: GlFramebufferDsl.() -> R): R {
    GlFramebufferDsl.name = framebuffer
    return GlFramebufferDsl.block()
}

// TODO check if leave, backup current fbo?
//inline fun withDefaultFramebuffer(block: Framebuffer.() -> Unit) {
//    Framebuffer.name = 0
//    Framebuffer.block()
//}

object GlFramebufferDsl {

    var name = 0
        set(value) {
            GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, value)
            field = value
        }

    inline fun texture(attachment: Int, texture: Enum<*>, level: Int = 0) = GL32.glFramebufferTexture(GL30.GL_FRAMEBUFFER, attachment, textureName[texture], level)
    inline fun texture(attachment: Int, texture: GlTexture, level: Int = 0) = GL32.glFramebufferTexture(GL30.GL_FRAMEBUFFER, attachment, texture.name, level)

    inline fun texture2D(target: Int, attachment: Int, texture: Enum<*>, level: Int = 0) = GL30.glFramebufferTexture2D(target, attachment, GL11.GL_TEXTURE_2D, textureName[texture], level)
    inline fun texture2D(target: Int, attachment: Int, texture: Int, level: Int = 0) = GL30.glFramebufferTexture2D(target, attachment, GL11.GL_TEXTURE_2D, texture, level)

    inline fun texture2D(attachment: Int, texture: Enum<*>, level: Int = 0) = GL30.glFramebufferTexture2D(GL30.GL_FRAMEBUFFER, attachment, GL11.GL_TEXTURE_2D, textureName[texture], level)
    inline fun texture2D(attachment: Int, texture: Int, level: Int = 0) = GL30.glFramebufferTexture2D(GL30.GL_FRAMEBUFFER, attachment, GL11.GL_TEXTURE_2D, texture, level)

    fun renderbuffer(attachment: Int, renderbuffer: GlRenderbuffer) = GL30C.glFramebufferRenderbuffer(GL30C.GL_FRAMEBUFFER, attachment, GL30C.GL_RENDERBUFFER, renderbuffer.name)
    inline fun renderbuffer(attachment: Int, renderbuffer: IntBuffer) = GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, attachment, GL30.GL_RENDERBUFFER, renderbuffer[0])
    inline fun renderbuffer(attachment: Int, renderbuffer: Int) = GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, attachment, GL30.GL_RENDERBUFFER, renderbuffer)

    val complete: Boolean
        get() {
            val status = GL30.glCheckFramebufferStatus(GL30.GL_FRAMEBUFFER)
            return if (status != GL30.GL_FRAMEBUFFER_COMPLETE) {
                println("framebuffer incomplete, status: ${when (status) {
                    GL30.GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT -> "GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT"
                    GL30.GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT -> "GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT"
                    GL30.GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER -> "GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER"
                    GL30.GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER -> "GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER"
                    GL30.GL_FRAMEBUFFER_UNSUPPORTED -> "GL_FRAMEBUFFER_UNSUPPORTED"
                    GL30.GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE -> "GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE"
                    GL32.GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS -> "GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS"
                    GL30.GL_FRAMEBUFFER_UNDEFINED -> "GL_FRAMEBUFFER_UNDEFINED"
                    else -> throw IllegalStateException()
                }}")
                false
            } else true
        }

    fun getParameter(attachment: Int, pName: Int) = GL30.glGetFramebufferAttachmentParameteri(GL30.GL_FRAMEBUFFER, attachment, pName)

    fun getDepthParameter(pName: Int) = GL30.glGetFramebufferAttachmentParameteri(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT, pName)
    fun getStencilParameter(pName: Int) = GL30.glGetFramebufferAttachmentParameteri(GL30.GL_FRAMEBUFFER, GL30.GL_STENCIL_ATTACHMENT, pName)
    fun getDepthStencilParameter(pName: Int) = GL30.glGetFramebufferAttachmentParameteri(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_STENCIL_ATTACHMENT, pName)

    fun getColorEncoding(index: Int = 0) = GL30.glGetFramebufferAttachmentParameteri(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0 + index, GL30.GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING)



    fun Framebuffer.texture(attachment: Int, texture: GlTexture, level: Int = 0) = GL30C.glFramebufferTexture2D(GL30C.GL_FRAMEBUFFER, attachment, GL11C.GL_TEXTURE_2D, texture.name, level)
}

object Framebuffers {

    lateinit var names: IntBuffer

    inline fun at(index: Enum<*>, block: GlFramebufferDsl.() -> Unit) = at(index.ordinal, block)
    inline fun at(index: Int, block: GlFramebufferDsl.() -> Unit) {
        GlFramebufferDsl.name = names[index] // bind
        GlFramebufferDsl.block()
    }
}

@JvmOverloads
fun checkFramebuffer(location: String = "", throws: Boolean = true): Boolean {
    val status = GL30.glCheckFramebufferStatus(GL30.GL_FRAMEBUFFER)
    return when (status) {
        GL30.GL_FRAMEBUFFER_COMPLETE -> true
        else -> {
            val message = "framebuffer incomplete, status: ${when (status) {
                GL30.GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT -> "GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT"
                GL30.GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT -> "GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT"
                GL30.GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER -> "GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER"
                GL30.GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER -> "GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER"
                GL30.GL_FRAMEBUFFER_UNSUPPORTED -> "GL_FRAMEBUFFER_UNSUPPORTED"
                GL30.GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE -> "GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE"
                GL32.GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS -> "GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS"
                GL30.GL_FRAMEBUFFER_UNDEFINED -> "GL_FRAMEBUFFER_UNDEFINED"
                else -> throw IllegalStateException()
            }}"
            if(throws)
                throw Exception(message)
            else
                System.err.println(message)
            false
        }
    }
}