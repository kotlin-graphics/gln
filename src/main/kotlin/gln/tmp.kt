package gln

import glm_.BYTES
import glm_.bool
import glm_.vec2.Vec2
import glm_.vec2.Vec2i
import glm_.vec3.Vec3i
import glm_.vec4.Vec4
import glm_.vec4.Vec4bool
import glm_.vec4.Vec4i
import kool.Ptr
import kool.adr
import kool.stak
import org.lwjgl.system.MemoryUtil
import org.lwjgl.system.MemoryUtil.memGetInt
import java.nio.FloatBuffer

inline fun stak.vec2Address(block: (Ptr) -> Unit): Vec2 =
        stak {
            val buf = it.malloc(Vec2.size)
            block(buf.adr)
            Vec2(buf)
        }

inline fun stak.vec2iAddress(block: (Ptr) -> Unit): Vec2i =
        stak {
            val buf = it.malloc(Vec2i.size)
            block(buf.adr)
            Vec2i(buf)
        }

inline fun stak.vec3iAddress(block: (Ptr) -> Unit): Vec3i =
        stak {
            val buf = it.malloc(Vec3i.size)
            block(buf.adr)
            Vec3i(buf)
        }

inline fun stak.vec4Address(block: (Ptr) -> Unit): Vec4 =
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


inline fun stak.vec4iAddress(block: (Ptr) -> Unit): Vec4i =
        stak {
            val buf = it.malloc(Vec4i.size)
            block(buf.adr)
            Vec4i(buf)
        }

inline fun stak.vec4boolAddress(block: (Ptr) -> Unit): Vec4bool =
        stak {
            val adr = it.malloc(4 * Int.BYTES).adr // TODO
            block(adr)
            Vec4bool(memGetInt(adr).bool, memGetInt(adr + Int.BYTES).bool, memGetInt(adr + Int.BYTES * 2).bool, memGetInt(adr + Int.BYTES * 3).bool)
        }

inline fun stak.vec4iAddress(vec4i: Vec4i, block: (Ptr) -> Unit) = stak {block(vec4i.toIntBuffer(it).adr) }


typealias UniformLocation = Int