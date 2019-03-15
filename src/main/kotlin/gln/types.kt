package gln

import gln.texture.GlTexturesDsl
import kool.IntBuffer
import kool.adr
import kool.rem
import org.lwjgl.opengl.GL45C
import java.nio.IntBuffer


inline class GLframebuffer(val i: Int)
inline class GLprogramPipeline(val i: Int)
inline class GLsampler(val i: Int)

inline class GlTextures(val names: IntBuffer) {

    inline val rem: Int
        get() = names.rem

    inline val adr: Long
        get() = names.adr

    companion object {
        fun gen(count: Int): GlTextures = gl.genTextures(count)
    }

    fun delete() = gl.deleteTextures(this)

//    inline operator fun invoke(block: GlTexturesDsl.() -> Unit) {
//        GlTexturesDsl.names = i
//        GlTexturesDsl.block()
//    }
//
    fun create() = GL45C.glCreateBuffers(names)
    inline fun create(block: GlTexturesDsl.() -> Unit) {
        create()
        GlTexturesDsl.names = names
        GlTexturesDsl.block()
    }
}

fun GlTextures(size: Int) = GlTextures(IntBuffer(size))