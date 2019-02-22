@file:Suppress("NOTHING_TO_INLINE")

package gln.buffer

import glm_.BYTES
import glm_.L
import glm_.mat4x4.Mat4
import glm_.vec3.Vec3
import glm_.vec4.Vec4
import gln.*
import gln.Usage.Companion.STATIC_DRAW
import kool.*
import org.lwjgl.opengl.*
import org.lwjgl.system.MemoryUtil.NULL
import java.nio.*


object GlBufferDSL {

    var target = BufferTarget.ARRAY
    var name = 0
        set(value) {
            GL15.glBindBuffer(target.i, value)
            field = value
        }

    inline fun data(data: ShortArray, usage: Usage = STATIC_DRAW) = GL15.glBufferData(target.i, data, usage.i)
    inline fun data(data: IntArray, usage: Usage = STATIC_DRAW) = GL15.glBufferData(target.i, data, usage.i)
    inline fun data(data: LongArray, usage: Usage = STATIC_DRAW) = GL15.glBufferData(target.i, data, usage.i)
    inline fun data(data: FloatArray, usage: Usage = STATIC_DRAW) = GL15.glBufferData(target.i, data, usage.i)
    inline fun data(data: DoubleArray, usage: Usage = STATIC_DRAW) = GL15.glBufferData(target.i, data, usage.i)

    inline fun data(data: ByteBuffer, usage: Usage = STATIC_DRAW) = GL15.nglBufferData(target.i, data.remaining().L, data.adr + data.pos, usage.i)
    inline fun data(data: ShortBuffer, usage: Usage = STATIC_DRAW) = GL15.nglBufferData(target.i, data.remaining().L * Short.BYTES, data.adr + data.pos * Short.BYTES, usage.i)
    inline fun data(data: IntBuffer, usage: Usage = STATIC_DRAW) = GL15.nglBufferData(target.i, data.remaining().L * Int.BYTES, data.adr + data.pos * Int.BYTES, usage.i)
    inline fun data(data: LongBuffer, usage: Usage = STATIC_DRAW) = GL15.nglBufferData(target.i, data.remaining().L * Long.BYTES, data.adr + data.pos * Long.BYTES, usage.i)
    inline fun data(data: FloatBuffer, usage: Usage = STATIC_DRAW) = GL15.nglBufferData(target.i, data.remaining().L * Float.BYTES, data.adr + data.pos * Float.BYTES, usage.i)
    inline fun data(data: DoubleBuffer, usage: Usage = STATIC_DRAW) = GL15.nglBufferData(target.i, data.remaining().L * Double.BYTES, data.adr + data.pos * Double.BYTES, usage.i)

    inline fun data(size: Int, data: Vec4, usage: Usage = STATIC_DRAW) {
        buf.putFloat(0, data.x).putFloat(Float.BYTES, data.y).putFloat(Float.BYTES * 2, data.z).putFloat(Float.BYTES * 3, data.w)
        GL15.nglBufferData(target.i, size.L, bufAd, usage.i)
    }

    inline fun data(size: Int, usage: Usage = STATIC_DRAW) = GL15.nglBufferData(target.i, size.L, NULL, usage.i)

    inline fun subData(offset: Int, data: ByteBuffer) = GL15.nglBufferSubData(target.i, offset.L, data.remaining().L, data.adr + data.pos)
    inline fun subData(offset: Int, data: ShortBuffer) = GL15.nglBufferSubData(target.i, offset.L, data.remaining().L * Short.BYTES, data.adr + data.pos * Short.BYTES)
    inline fun subData(offset: Int, data: IntBuffer) = GL15.nglBufferSubData(target.i, offset.L, data.remaining().L * Int.BYTES, data.adr + data.pos * Int.BYTES)
    inline fun subData(offset: Int, data: LongBuffer) = GL15.nglBufferSubData(target.i, offset.L, data.remaining().L * Long.BYTES, data.adr + data.pos * Long.BYTES)
    inline fun subData(offset: Int, data: FloatBuffer) = GL15.nglBufferSubData(target.i, offset.L, data.remaining().L * Float.BYTES, data.adr + data.pos * Float.BYTES)
    inline fun subData(offset: Int, data: DoubleBuffer) = GL15.nglBufferSubData(target.i, offset.L, data.remaining().L * Double.BYTES, data.adr + data.pos * Double.BYTES)

    inline fun subData(data: ByteBuffer) = GL15.nglBufferSubData(target.i, 0L, data.remaining().L, data.adr + data.pos)
    inline fun subData(data: ShortBuffer) = GL15.nglBufferSubData(target.i, 0L, data.remaining().L * Short.BYTES, data.adr + data.pos * Short.BYTES)
    inline fun subData(data: IntBuffer) = GL15.nglBufferSubData(target.i, 0L, data.remaining().L * Int.BYTES, data.adr + data.pos * Int.BYTES)
    inline fun subData(data: LongBuffer) = GL15.nglBufferSubData(target.i, 0L, data.remaining().L * Long.BYTES, data.adr + data.pos * Long.BYTES)
    inline fun subData(data: FloatBuffer) = GL15.nglBufferSubData(target.i, 0L, data.remaining().L * Float.BYTES, data.adr + data.pos * Float.BYTES)
    inline fun subData(data: DoubleBuffer) = GL15.nglBufferSubData(target.i, 0L, data.remaining().L * Double.BYTES, data.adr + data.pos * Double.BYTES)

    fun data(data: Vec3, usage: Usage = STATIC_DRAW) = stak { GL15C.glBufferData(target.i, data.toBuffer(it), usage.i) }

    // ----- Mat4 -----
    inline fun data(mat: Mat4, usage: Usage) {
        mat to buf
        GL15.nglBufferData(target.i, Mat4.size.L, bufAd, usage.i)
    }

    inline fun subData(offset: Int, mat: Mat4) {
        mat to buf
        GL15.nglBufferSubData(target.i, offset.L, Mat4.size.L, bufAd)
    }

    inline fun subData(mat: Mat4) {
        mat to buf
        GL15.nglBufferSubData(target.i, 0L, Mat4.size.L, bufAd)
    }


    inline fun bindRange(index: Int, offset: Int, size: Int) = GL30.glBindBufferRange(target.i, index, name, offset.L, size.L)

    inline fun bindBase(index: Int) = GL30.glBindBufferBase(target.i, index, 0)

    inline fun mapRange(length: Int, access: Int) = mapRange(0, length, access)
    inline fun mapRange(offset: Int, length: Int, access: Int) = GL30.glMapBufferRange(target.i, offset.L, length.L, access)

    inline fun mappedRange(size: Int, flags: Int, block: MappedBuffer.(ByteBuffer?) -> Unit) = mappedRange(0, size, flags, block)
    inline fun mappedRange(offset: Int, size: Int, flags: Int, block: MappedBuffer.(ByteBuffer?) -> Unit) {
        GL30.glMapBufferRange(target.i, offset.L, size.L, flags)
        MappedBuffer.block(GL30C.glMapBufferRange(target.i, offset.L, size.L, flags))
        GL30C.glUnmapBuffer(target.i)
    }

    inline fun flushRange(length: Int) = flushRange(0, length)
    inline fun flushRange(offset: Int, length: Int) = GL30.glFlushMappedBufferRange(target.i, offset.L, length.L)

    inline fun unmap() = GL15.glUnmapBuffer(target.i)
}

inline fun glGenBuffers(bufferName: IntBuffer, block: GlBuffersDSL.() -> Unit) {
    GL15C.glGenBuffers(bufferName)
    GlBuffersDSL.buffers = bufferName
    GlBuffersDSL.block()
}

object GlBuffersDSL {

    lateinit var buffers: IntBuffer
    var target = 0

    inline fun bindArray(enum: Enum<*>, block: GlBufferDSL.() -> Unit) = bind(BufferTarget.ARRAY, enum, block)
    inline fun bindElement(enum: Enum<*>, block: GlBufferDSL.() -> Unit) = bind(BufferTarget.ELEMENT_ARRAY, enum, block)
    inline fun bindUniform(enum: Enum<*>, block: GlBufferDSL.() -> Unit) = bind(BufferTarget.UNIFORM, enum, block)

    inline fun bind(target: BufferTarget, enum: Enum<*>, block: GlBufferDSL.() -> Unit) {
        GlBufferDSL.target = target
        GlBufferDSL.name = buffers[enum.ordinal] // bind
        GlBufferDSL.block()
        GlBufferDSL.name = 0
    }
}

object MappedBuffer {

    var target = BufferTarget.ARRAY
    var name = 0
        set(value) {
            GL15.glBindBuffer(target.i, value)
            field = value
        }

    fun mapRange(length: Int, access: Int) {
        buffer = GL30.glMapBufferRange(target.i, 0L, length.L, access)!!
    }

    lateinit var buffer: ByteBuffer

//    var pointer: Any = NULL
//        set(value) {
//            if (value is Mat4) value to buffer
//        }
}