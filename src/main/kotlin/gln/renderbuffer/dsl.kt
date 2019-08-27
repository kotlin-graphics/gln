package gln.renderbuffer

import org.lwjgl.opengl.GL30C
import java.nio.IntBuffer

object GlRenderbufferDsl {
    var name = 0
}

object GlRenderbuffersDsl {

    lateinit var names: IntBuffer

    fun <E : Enum<E>> E.bind() = GL30C.glBindRenderbuffer(GL30C.GL_RENDERBUFFER, names[ordinal])
    inline fun <E : Enum<E>> E.bound(block: GlRenderbufferDsl.() -> Unit) {
        val name = names[ordinal]
        GL30C.glBindRenderbuffer(GL30C.GL_RENDERBUFFER, name)
        GlRenderbufferDsl.name = name
        GlRenderbufferDsl.block()
        GL30C.glBindRenderbuffer(GL30C.GL_RENDERBUFFER, 0)
    }
}

