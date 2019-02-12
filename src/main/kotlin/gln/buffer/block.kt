@file:Suppress("NOTHING_TO_INLINE")

package gln.buffer

import glm_.BYTES
import glm_.L
import glm_.mat4x4.Mat4
import glm_.vec4.Vec4
import gln.GL_STATIC_DRAW
import gln.Usage
import gln.buf
import gln.bufAd
import gln.buffer.BufferTarget.BufferTarget2
import kool.*
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL15C
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL31
import org.lwjgl.system.MemoryUtil.NULL
import java.nio.*


inline fun initArrayBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) {
    buffer[0] = initBuffer(BufferTarget.Array, block)
}

inline fun initArrayBuffer(buffer: Enum<*>, block: Buffer.() -> Unit) {
    bufferName[buffer.ordinal] = initBuffer(BufferTarget.Array, block)
}

inline fun initElementBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) {
    buffer[0] = initBuffer(BufferTarget.ElementArray, block)
}

inline fun initElementBuffer(buffer: Enum<*>, block: Buffer.() -> Unit) {
    bufferName[buffer.ordinal] = initBuffer(BufferTarget.ElementArray, block)
}

inline fun initUniformBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) {
    buffer[0] = initBuffer(BufferTarget2.Uniform, block)
}

inline fun initUniformBuffer(buffer: Enum<*>, block: Buffer.() -> Unit) {
    bufferName[buffer.ordinal] = initBuffer(BufferTarget2.Uniform, block)
}

inline fun initBuffer(target: BufferTarget, block: Buffer.() -> Unit): Int {
    Buffer.target = target
    GL15.nglGenBuffers(1, bufAd)
    val name = buf.getInt(0)
    Buffer.name = name // bind
    Buffer.block()
    GL15.glBindBuffer(target.i, 0)
    return name
}

inline fun initBuffers(buffers: IntBuffer, block: Buffers.() -> Unit) {
    GL15.nglGenBuffers(buffers.rem, buffers.adr + buffers.pos * Int.BYTES)
    Buffers.buffers = buffers
    Buffers.block()
}

inline fun initUniformBuffers(buffers: IntBuffer, block: Buffers.() -> Unit) {
    Buffers.target = GL31.GL_UNIFORM_BUFFER
    GL15.nglGenBuffers(buffers.rem, buffers.adr + buffers.pos * Int.BYTES)
    Buffers.buffers = buffers
    Buffers.block()
    GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, 0)
}

inline fun withBuffer(target: BufferTarget, buffer: IntBuffer, block: Buffer.() -> Unit) = withBuffer(target, buffer[0], block)
inline fun withBuffer(target: BufferTarget, buffer: Enum<*>, block: Buffer.() -> Unit) = withBuffer(target, bufferName[buffer], block)
inline fun withBuffer(target: BufferTarget, buffer: Int, block: Buffer.() -> Unit) {
    Buffer.target = target
    Buffer.name = buffer   // bind
    Buffer.block()
    GL15.glBindBuffer(target.i, 0)
}

inline fun withArrayBuffer(buffer: Enum<*>, block: Buffer.() -> Unit) = withBuffer(BufferTarget.Array, bufferName[buffer], block)
inline fun withArrayBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) = withBuffer(BufferTarget.Array, buffer[0], block)
inline fun withArrayBuffer(buffer: Int, block: Buffer.() -> Unit) = withBuffer(BufferTarget.Array, buffer, block)
inline fun withElementBuffer(buffer: Enum<*>, block: Buffer.() -> Unit) = withBuffer(BufferTarget.ElementArray, bufferName[buffer], block)
inline fun withElementBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) = withBuffer(BufferTarget.ElementArray, buffer[0], block)
inline fun withElementBuffer(buffer: Int, block: Buffer.() -> Unit) = withBuffer(BufferTarget.ElementArray, buffer, block)
inline fun withUniformBuffer(buffer: Enum<*>, block: Buffer.() -> Unit) = withBuffer(BufferTarget2.Uniform, bufferName[buffer], block)
inline fun withUniformBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) = withBuffer(BufferTarget2.Uniform, buffer[0], block)
inline fun withUniformBuffer(buffer: Int, block: Buffer.() -> Unit) = withBuffer(BufferTarget2.Uniform, buffer, block)

object Buffer {

    lateinit var target: BufferTarget
    var name = 0
        set(value) {
            GL15.glBindBuffer(target.i, value)
            field = value
        }

    inline fun data(data: ShortArray, usage: Usage = GL_STATIC_DRAW) = GL15.glBufferData(target.i, data, usage.i)
    inline fun data(data: IntArray, usage: Usage = GL_STATIC_DRAW) = GL15.glBufferData(target.i, data, usage.i)
    inline fun data(data: LongArray, usage: Usage = GL_STATIC_DRAW) = GL15.glBufferData(target.i, data, usage.i)
    inline fun data(data: FloatArray, usage: Usage = GL_STATIC_DRAW) = GL15.glBufferData(target.i, data, usage.i)
    inline fun data(data: DoubleArray, usage: Usage = GL_STATIC_DRAW) = GL15.glBufferData(target.i, data, usage.i)

    inline fun data(data: ByteBuffer, usage: Usage = GL_STATIC_DRAW) = GL15.nglBufferData(target.i, data.remaining().L, data.adr + data.pos, usage.i)
    inline fun data(data: ShortBuffer, usage: Usage = GL_STATIC_DRAW) = GL15.nglBufferData(target.i, data.remaining().L * Short.BYTES, data.adr + data.pos * Short.BYTES, usage.i)
    inline fun data(data: IntBuffer, usage: Usage = GL_STATIC_DRAW) = GL15.nglBufferData(target.i, data.remaining().L * Int.BYTES, data.adr + data.pos * Int.BYTES, usage.i)
    inline fun data(data: LongBuffer, usage: Usage = GL_STATIC_DRAW) = GL15.nglBufferData(target.i, data.remaining().L * Long.BYTES, data.adr + data.pos * Long.BYTES, usage.i)
    inline fun data(data: FloatBuffer, usage: Usage = GL_STATIC_DRAW) = GL15.nglBufferData(target.i, data.remaining().L * Float.BYTES, data.adr + data.pos * Float.BYTES, usage.i)
    inline fun data(data: DoubleBuffer, usage: Usage = GL_STATIC_DRAW) = GL15.nglBufferData(target.i, data.remaining().L * Double.BYTES, data.adr + data.pos * Double.BYTES, usage.i)

    inline fun data(size: Int, data: Vec4, usage: Usage = GL_STATIC_DRAW) {
        buf.putFloat(0, data.x).putFloat(Float.BYTES, data.y).putFloat(Float.BYTES * 2, data.z).putFloat(Float.BYTES * 3, data.w)
        GL15.nglBufferData(target.i, size.L, bufAd, usage.i)
    }

    inline fun data(size: Int, usage: Usage = GL_STATIC_DRAW) = GL15.nglBufferData(target.i, size.L, NULL, usage.i)

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

    inline fun flushRange(length: Int) = flushRange(0, length)
    inline fun flushRange(offset: Int, length: Int) = GL30.glFlushMappedBufferRange(target.i, offset.L, length.L)
    inline fun unmap() = GL15.glUnmapBuffer(target.i)
}

inline fun glGenBuffers(bufferName: IntBuffer, block: Buffers.() -> Unit) {
    GL15C.glGenBuffers(bufferName)
    Buffers.buffers = bufferName
    Buffers.block()
}

object Buffers {

    lateinit var buffers: IntBuffer
    var target = 0

    inline fun bindArray(enum: Enum<*>, block: Buffer.() -> Unit) = bind(BufferTarget.Array, enum, block)
    inline fun bindElement(enum: Enum<*>, block: Buffer.() -> Unit) = bind(BufferTarget.ElementArray, enum, block)
    inline fun bindUniform(enum: Enum<*>, block: Buffer.() -> Unit) = bind(BufferTarget2.Uniform, enum, block)

    inline fun bind(target: BufferTarget, enum: Enum<*>, block: Buffer.() -> Unit) {
        Buffer.target = target
        Buffer.name = buffers[enum.ordinal] // bind
        Buffer.block()
        Buffer.name = 0
    }
}


inline fun mappingUniformBufferRange(buffer: Enum<*>, length: Int, access: Int, block: MappedBuffer.() -> Unit) = mappingUniformBufferRange(bufferName[buffer], length, access, block)
inline fun mappingUniformBufferRange(buffer: IntBuffer, length: Int, access: Int, block: MappedBuffer.() -> Unit) = mappingUniformBufferRange(buffer[0], length, access, block)
inline fun mappingUniformBufferRange(buffer: Int, length: Int, access: Int, block: MappedBuffer.() -> Unit) = mappingBufferRange(BufferTarget2.Uniform, buffer, length, access, block)
inline fun mappingBufferRange(target: BufferTarget, buffer: Int, length: Int, access: Int, block: MappedBuffer.() -> Unit) {
    MappedBuffer.target = target
    MappedBuffer.name = buffer    // bind
    MappedBuffer.mapRange(length, access)
    MappedBuffer.block()
    GL15.glUnmapBuffer(GL31.GL_UNIFORM_BUFFER)
    GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, 0)
}

//inline fun glFlushMappedArrayBufferRange(length: Int) = glFlushMappedBufferRange(GL15.GL_ARRAY_BUFFER, 0, length)
//inline fun glFlushMappedArrayBufferRange(offset: Int, length: Int) = glFlushMappedBufferRange(GL15.GL_ARRAY_BUFFER, offset, length)
//inline fun glFlushMappedElementBufferRange(length: Int) = glFlushMappedBufferRange(GL15.GL_ELEMENT_ARRAY_BUFFER, 0, length)
//inline fun glFlushMappedElementBufferRange(offset: Int, length: Int) = glFlushMappedBufferRange(GL15.GL_ELEMENT_ARRAY_BUFFER, offset, length)
//inline fun glFlushMappedUniformBufferRange(length: Int) = glFlushMappedBufferRange(GL31.GL_UNIFORM_BUFFER, 0, length)
//inline fun glFlushMappedUniformBufferRange(offset: Int, length: Int) = glFlushMappedBufferRange(GL31.GL_UNIFORM_BUFFER, offset, length)
//inline fun glFlushMappedBufferRange(target: Int, length: Int) = glFlushMappedBufferRange(target, 0, length)
inline fun glFlushMappedBufferRange(target: BufferTarget, offset: Int, length: Int) = GL30.glFlushMappedBufferRange(target.i, offset.L, length.L)

//inline fun glUnmapArrayBuffer() = GL15.glUnmapBuffer(GL15.GL_ARRAY_BUFFER)
//inline fun glUnmapElementBuffer() = GL15.glUnmapBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER)
//inline fun glUnmapUniformBuffer() = GL15.glUnmapBuffer(GL31.GL_UNIFORM_BUFFER)

object MappedBuffer {

    lateinit var target: BufferTarget
    var name = 0
        set(value) {
            GL15.glBindBuffer(target.i, value)
            field = value
        }

    fun mapRange(length: Int, access: Int) {
        buffer = GL30.glMapBufferRange(target.i, 0L, length.L, access)!!
    }

    lateinit var buffer: ByteBuffer

    var pointer: Any = NULL
        set(value) {
            if (value is Mat4) value to buffer
        }
}