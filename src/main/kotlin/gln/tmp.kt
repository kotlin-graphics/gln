package gln

import glm_.BYTES
import glm_.bool
import glm_.vec2.Vec2
import glm_.vec2.Vec2i
import glm_.vec3.Vec3
import glm_.vec3.Vec3i
import glm_.vec4.Vec4
import glm_.vec4.Vec4bool
import glm_.vec4.Vec4i
import kool.Adr
import kool.Ptr
import kool.adr
import kool.stak
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil.memGetFloat
import org.lwjgl.system.MemoryUtil.memGetInt
import java.nio.FloatBuffer
import java.nio.IntBuffer

interface GlBufferEnum

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

inline fun stak.vec3Address(block: (Ptr) -> Unit): Vec3 =
        stak {
            val buf = it.malloc(Vec3.size)
            block(buf.adr)
            Vec3(buf)
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

// TODO :Unit ?
inline fun stak.vec4Address(vec: Vec4, block: (Ptr) -> Unit): Vec4 =
        stak {
            val buf = vec.toBuffer(it)
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

inline fun stak.vec4iAddress(vec4i: Vec4i, block: (Ptr) -> Unit) = stak { block(vec4i.toIntBuffer(it).adr) }


typealias UniformLocation = Int
/** One or more of:
 *  - GL_MAP_READ_BIT
 *  - GL_MAP_WRITE_BIT
 *  - GL_MAP_INVALIDATE_RANGE_BIT
 *  - GL_MAP_INVALIDATE_BUFFER_BIT
 *  - GL_MAP_FLUSH_EXPLICIT_BIT MAP_FLUSH_EXPLICIT_BIT
 *  - GL_MAP_UNSYNCHRONIZED_BIT MAP_UNSYNCHRONIZED_BIT  */
typealias BufferMapFlags = Int

inline fun MemoryStack.intAddress(block: (Adr) -> Unit): Int {
    val adr = nmalloc(4, Int.BYTES)
    block(adr)
    return memGetInt(adr)
}

inline fun MemoryStack.intBuffer(block: (IntBuffer) -> Unit): Int {
    val buf = mallocInt(1)
    block(buf)
    return buf[0]
}

inline fun MemoryStack.floatAddress(block: (Adr) -> Unit): Float {
    val adr = nmalloc(4, Float.BYTES)
    block(adr)
    return memGetFloat(adr)
}

inline fun MemoryStack.floatBuffer(block: (FloatBuffer) -> Unit): Float {
    val buf = mallocFloat(1)
    block(buf)
    return buf[0]
}

inline fun MemoryStack.vec2Address(block: (Ptr) -> Unit): Vec2 {
    val buf = malloc(Vec2.size)
    block(buf.adr)
    return Vec2(buf)
}

inline fun MemoryStack.vec2iAddress(block: (Ptr) -> Unit): Vec2i {
    val buf = malloc(Vec2i.size)
    block(buf.adr)
    return Vec2i(buf)
}

inline fun MemoryStack.vec3Address(block: (Ptr) -> Unit): Vec3 {
    val buf = malloc(Vec3.size)
    block(buf.adr)
    return Vec3(buf)
}

inline fun MemoryStack.vec3iAddress(block: (Ptr) -> Unit): Vec3i {
    val buf = malloc(Vec3i.size)
    block(buf.adr)
    return Vec3i(buf)
}

inline fun MemoryStack.vec4Address(block: (Ptr) -> Unit): Vec4 {
    val buf = malloc(Vec4.size)
    block(buf.adr)
    return Vec4(buf)
}

//// TODO :Unit ?
//inline fun MemoryStack.vec4Address(vec: Vec4, block: (Ptr) -> Unit): Vec4  {
//            val buf = vec.toBuffer(it)
//            block(buf.adr)
//            Vec4(buf)
//        }

inline fun MemoryStack.vec4Buffer(block: (FloatBuffer) -> Unit): Vec4 {
    val buf = mallocFloat(Vec4.length)
    block(buf)
    return Vec4(buf)
}


inline fun MemoryStack.vec4iAddress(block: (Ptr) -> Unit): Vec4i {
    val buf = malloc(Vec4i.size)
    block(buf.adr)
    return Vec4i(buf)
}