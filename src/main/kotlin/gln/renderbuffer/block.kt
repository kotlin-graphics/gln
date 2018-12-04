@file:Suppress("NOTHING_TO_INLINE")

package gln.renderbuffer

import glm_.BYTES
import glm_.vec2.Vec2i
import gln.buf
import gln.bufAd
import kool.adr
import kool.get
import kool.pos
import kool.rem
import org.lwjgl.opengl.GL30
import java.nio.IntBuffer


inline fun initRenderbuffers(block: RenderBuffers.() -> Unit) = initRenderbuffers(renderbufferName, block)
inline fun initRenderbuffers(renderbuffers: IntBuffer, block: RenderBuffers.() -> Unit) {
    GL30.nglGenRenderbuffers(renderbuffers.rem, renderbuffers.adr + renderbuffers.pos * Int.BYTES)
    RenderBuffers.names = renderbuffers
    RenderBuffers.block()
}

inline fun initRenderbuffer(renderbuffer: Enum<*>, block: RenderBuffer.() -> Unit) = renderbufferName.put(renderbuffer.ordinal, initRenderbuffer(block))
inline fun initRenderbuffer(renderbuffer: IntBuffer, block: RenderBuffer.() -> Unit) = renderbuffer.put(0, initRenderbuffer(block))
inline fun initRenderbuffer(block: RenderBuffer.() -> Unit): Int {
    GL30.nglGenRenderbuffers(1, bufAd)
    val res = buf.getInt(0)
    RenderBuffer.name = res
    RenderBuffer.block()
    return res
}

inline fun withRenderbuffer(renderbuffer: Enum<*>, block: RenderBuffer.() -> Unit) = withRenderbuffer(renderbufferName[renderbuffer], block)
inline fun withRenderbuffer(renderbuffer: IntBuffer, block: RenderBuffer.() -> Unit) = withRenderbuffer(renderbuffer[0], block)
inline fun withRenderbuffer(renderbuffer: Int, block: RenderBuffer.() -> Unit) {
    RenderBuffer.name = renderbuffer
    RenderBuffer.block()
}

object RenderBuffers {

    var target = GL30.GL_RENDERBUFFER
    lateinit var names: IntBuffer

    inline fun at(index: Enum<*>, block: RenderBuffer.() -> Unit) = at(index.ordinal, block)
    inline fun at(index: Int, block: RenderBuffer.() -> Unit) {
        RenderBuffer.name = names[index] // bind
        RenderBuffer.block()
        RenderBuffer.name = 0   // unbind
    }
}

object RenderBuffer {

    var target = GL30.GL_RENDERBUFFER
    var name = 0
        set(value) {
            GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, value)
            field = name
        }

    inline fun storage(internalFormat: Int, width: Int, height: Int) = GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, internalFormat, width, height)
    inline fun storage(internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, internalFormat, size.x, size.y)
    inline fun storageMultisample(samples: Int, internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorageMultisample(target, samples, internalFormat, size.x, size.y)

    val size get() = Vec2i(GL30.glGetRenderbufferParameteri(GL30.GL_RENDERBUFFER, GL30.GL_RENDERBUFFER_WIDTH), GL30.glGetRenderbufferParameteri(GL30.GL_RENDERBUFFER, GL30.GL_RENDERBUFFER_HEIGHT))
    val samples get() = GL30.glGetRenderbufferParameteri(GL30.GL_RENDERBUFFER, GL30.GL_RENDERBUFFER_SAMPLES)
    val format get() = GL30.glGetRenderbufferParameteri(GL30.GL_RENDERBUFFER, GL30.GL_RENDERBUFFER_INTERNAL_FORMAT)
}