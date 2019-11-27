@file:Suppress("NOTHING_TO_INLINE")

package gln.renderbuffer

import glm_.vec2.Vec2i
import org.lwjgl.opengl.GL30
import java.nio.IntBuffer



object GlRenderBuffersDsl {

    var target = GL30.GL_RENDERBUFFER
    lateinit var names: IntBuffer

    inline fun at(index: Enum<*>, block: GlRenderBufferDsl.() -> Unit) = at(index.ordinal, block)
    inline fun at(index: Int, block: GlRenderBufferDsl.() -> Unit) {
        val name = names[index]
        GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, name)
        GlRenderBufferDsl.name = name
        GlRenderBufferDsl.block()
        GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, 0)
    }
}

object GlRenderBufferDsl {

    var target = GL30.GL_RENDERBUFFER
    var name = 0

    inline fun storage(internalFormat: Int, width: Int, height: Int) = GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, internalFormat, width, height)
    inline fun storage(internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, internalFormat, size.x, size.y)
    inline fun storageMultisample(samples: Int, internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorageMultisample(target, samples, internalFormat, size.x, size.y)

    val size get() = Vec2i(GL30.glGetRenderbufferParameteri(GL30.GL_RENDERBUFFER, GL30.GL_RENDERBUFFER_WIDTH), GL30.glGetRenderbufferParameteri(GL30.GL_RENDERBUFFER, GL30.GL_RENDERBUFFER_HEIGHT))
    val samples get() = GL30.glGetRenderbufferParameteri(GL30.GL_RENDERBUFFER, GL30.GL_RENDERBUFFER_SAMPLES)
    val format get() = GL30.glGetRenderbufferParameteri(GL30.GL_RENDERBUFFER, GL30.GL_RENDERBUFFER_INTERNAL_FORMAT)
}