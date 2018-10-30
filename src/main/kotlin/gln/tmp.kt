package gln

import glm_.vec4.Vec4
import kool.adr
import kool.stak
import java.nio.FloatBuffer

inline fun stak.vec4Address(block: (kool.Ptr) -> Unit): Vec4 =
        stak {
            val buf = it.malloc(Vec4.size)
            block(buf.adr)
            Vec4(buf)
        }

inline fun stak.vec4Buffer(block: (FloatBuffer) -> Unit): Vec4 =
        stak {
            val buf = it.mallocFloat(Vec4.length)
            block(buf)
            Vec4(buf)
        }

inline class UniformLocation(val i: Int)