package gln.renderbuffer

import gli_.gl
import glm_.vec2.Vec2i
import org.lwjgl.opengl.GL30C
import java.nio.IntBuffer

object GlRenderbufferDsl {

    var name = 0

    fun storage(internalFormat: gl.InternalFormat, size: Vec2i) = GL30C.glRenderbufferStorage(GL30C.GL_RENDERBUFFER, internalFormat.i, size.x, size.y)
}

object GlRenderbuffersDsl {

    lateinit var names: IntBuffer

    fun <E : Enum<E>> E.bind() = GL30C.glBindRenderbuffer(GL30C.GL_RENDERBUFFER, names[ordinal])
    inline fun <E : Enum<E>> E.bind(block: GlRenderbufferDsl.() -> Unit) {
        val name = names[ordinal]
        GL30C.glBindRenderbuffer(GL30C.GL_RENDERBUFFER, name)
        GlRenderbufferDsl.name = name
        GlRenderbufferDsl.block()
    }

    inline fun <E : Enum<E>> E.bound(block: GlRenderbufferDsl.() -> Unit) {
        bind(block)
        GL30C.glBindRenderbuffer(GL30C.GL_RENDERBUFFER, 0)
    }
}

