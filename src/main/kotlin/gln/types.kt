package gln

import kool.adr
import kool.rem
import org.lwjgl.system.MemoryUtil.NULL
import java.nio.IntBuffer


inline class GLframebuffer(val i: Int)
inline class GLprogramPipeline(val i: Int)
inline class GLrenderbuffer(val i: Int)
inline class GLsampler(val i: Int)
inline class GLvertexArray(val i: Int)

inline class GLtextures(val i: IntBuffer) {

    inline val rem: Int
        get() = i.rem

    inline val adr: Long
        get() = i.adr

    companion object {
        fun gen(count: Int): GLtextures = gl20.genTextures(count)
    }

    fun delete() = gl20.deleteTextures(this)
}



inline class Ptr(val L: Long) {
    val valid: Boolean
        get() = L != NULL

    fun notNull(): Boolean = L != NULL
}


typealias GLbitfield = Int

