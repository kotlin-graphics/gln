package gln

import glm_.buffer.adr
import glm_.buffer.rem
import java.nio.IntBuffer

inline class GLtexture(val i: Int)
inline class GLtextureArray(val i: Int)
inline class GLprogram(val i: Int)
inline class GLframebuffer(val i: Int)
inline class GLbuffer(val i: Int)
inline class GLprogramPipeline(val i: Int)
inline class GLrenderbuffer(val i: Int)
inline class GLsampler(val i: Int)
inline class GLvertexArray(val i: Int)

typealias GLbitfield = Int

inline class GLtextures(val i: IntBuffer) {
    inline val rem: Int
        get() = i.rem
    inline val adr: Long
        get() = i.adr
}