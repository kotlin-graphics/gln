package gln.buffer

import glm_.BYTES
import glm_.L
import glm_.mat4x4.Mat4
import glm_.set
import glm_.vec4.Vec4
import gln.buf
import gln.bufAd
import gln.get
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL31
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.system.MemoryUtil.memAddress0
import java.nio.*


inline fun initArrayBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) {
    buffer[0] = initBuffer(GL15.GL_ARRAY_BUFFER, block)
}

inline fun initArrayBuffer(buffer: Enum<*>, block: Buffer.() -> Unit) {
    bufferName[buffer.ordinal] = initBuffer(GL15.GL_ARRAY_BUFFER, block)
}

inline fun initElementBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) {
    buffer[0] = initBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, block)
}

inline fun initElementBuffer(buffer: Enum<*>, block: Buffer.() -> Unit) {
    bufferName[buffer.ordinal] = initBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, block)
}

inline fun initUniformBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) {
    buffer[0] = initBuffer(GL31.GL_UNIFORM_BUFFER, block)
}

inline fun initUniformBuffer(buffer: Enum<*>, block: Buffer.() -> Unit) {
    bufferName[buffer.ordinal] = initBuffer(GL31.GL_UNIFORM_BUFFER, block)
}

inline fun initBuffer(target: Int, block: Buffer.() -> Unit): Int {
    Buffer.target = target
    GL15.nglGenBuffers(1, bufAd)
    val name = buf.getInt(0)
    Buffer.name = name // bind
    Buffer.block()
    GL15.glBindBuffer(target, 0)
    return name
}

inline fun initBuffers(buffers: IntBuffer, block: Buffers.() -> Unit) {
    GL15.nglGenBuffers(buffers.capacity(), memAddress0(buffers) + (buffers.position() shl 2))
    Buffers.buffers = buffers
    Buffers.block()
}

inline fun initUniformBuffers(buffers: IntBuffer, block: Buffers.() -> Unit) {
    Buffers.target = GL31.GL_UNIFORM_BUFFER
    GL15.nglGenBuffers(buffers.capacity(), memAddress0(buffers) + (buffers.position() shl 2))
    Buffers.buffers = buffers
    Buffers.block()
    GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, 0)
}

inline fun withBuffer(target: Int, buffer: IntBuffer, block: Buffer.() -> Unit) = withBuffer(target, buffer[0], block)
inline fun withBuffer(target: Int, buffer: Enum<*>, block: Buffer.() -> Unit) = withBuffer(target, bufferName[buffer], block)
inline fun withBuffer(target: Int, buffer: Int, block: Buffer.() -> Unit) {
    Buffer.target = target
    Buffer.name = buffer   // bind
    Buffer.block()
    GL15.glBindBuffer(target, 0)
}

inline fun withArrayBuffer(buffer: Enum<*>, block: Buffer.() -> Unit) = withBuffer(GL15.GL_ARRAY_BUFFER, bufferName[buffer], block)
inline fun withArrayBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) = withBuffer(GL15.GL_ARRAY_BUFFER, buffer[0], block)
inline fun withArrayBuffer(buffer: Int, block: Buffer.() -> Unit) = withBuffer(GL15.GL_ARRAY_BUFFER, buffer, block)
inline fun withElementBuffer(buffer: Enum<*>, block: Buffer.() -> Unit) = withBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, bufferName[buffer], block)
inline fun withElementBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) = withBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer[0], block)
inline fun withElementBuffer(buffer: Int, block: Buffer.() -> Unit) = withBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, block)
inline fun withUniformBuffer(buffer: Enum<*>, block: Buffer.() -> Unit) = withBuffer(GL31.GL_UNIFORM_BUFFER, bufferName[buffer], block)
inline fun withUniformBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) = withBuffer(GL31.GL_UNIFORM_BUFFER, buffer[0], block)
inline fun withUniformBuffer(buffer: Int, block: Buffer.() -> Unit) = withBuffer(GL31.GL_UNIFORM_BUFFER, buffer, block)

object Buffer {

    var target = 0
    var name = 0
        set(value) {
            GL15.glBindBuffer(target, value)
            field = value
        }

    inline fun data(data: ByteBuffer, usage: Int = 0) = GL15.nglBufferData(target, data.remaining().L, memAddress0(data) + data.position(), usage)
    inline fun data(data: ShortBuffer, usage: Int = 0) = GL15.nglBufferData(target, data.remaining().L shl 1, memAddress0(data) + data.position() shl 1, usage)
    inline fun data(data: IntBuffer, usage: Int = 0) = GL15.nglBufferData(target, data.remaining().L shl 2, memAddress0(data) + data.position() shl 2, usage)
    inline fun data(data: LongBuffer, usage: Int = 0) = GL15.nglBufferData(target, data.remaining().L shl 3, memAddress0(data) + data.position() shl 3, usage)
    inline fun data(data: FloatBuffer, usage: Int = 0) = GL15.nglBufferData(target, data.remaining().L shl 2, memAddress0(data) + data.position() shl 2, usage)
    inline fun data(data: DoubleBuffer, usage: Int = 0) = GL15.nglBufferData(target, data.remaining().L shl 3, memAddress0(data) + data.position() shl 3, usage)

    inline fun data(size: Int, data: Vec4, usage: Int = 0) {
        buf.putFloat(0, data.x).putFloat(Float.BYTES, data.y).putFloat(Float.BYTES * 2, data.z).putFloat(Float.BYTES * 3, data.w)
        GL15.nglBufferData(target, size.L, bufAd, usage)
    }

    inline fun data(size: Int, usage: Int = 0) = GL15.nglBufferData(target, size.L, NULL, usage)

    inline fun subData(offset: Int, data: ByteBuffer) = GL15.nglBufferSubData(target, offset.L, data.remaining().L, memAddress0(data) + data.position())
    inline fun subData(offset: Int, data: ShortBuffer) = GL15.nglBufferSubData(target, offset.L, data.remaining().L shl 1, memAddress0(data) + data.position() shl 1)
    inline fun subData(offset: Int, data: IntBuffer) = GL15.nglBufferSubData(target, offset.L, data.remaining().L shl 2, memAddress0(data) + data.position() shl 2)
    inline fun subData(offset: Int, data: LongBuffer) = GL15.nglBufferSubData(target, offset.L, data.remaining().L shl 3, memAddress0(data) + data.position() shl 3)
    inline fun subData(offset: Int, data: FloatBuffer) = GL15.nglBufferSubData(target, offset.L, data.remaining().L shl 2, memAddress0(data) + data.position() shl 2)
    inline fun subData(offset: Int, data: DoubleBuffer) = GL15.nglBufferSubData(target, offset.L, data.remaining().L shl 3, memAddress0(data) + data.position() shl 3)

    inline fun subData(data: ByteBuffer) = GL15.nglBufferSubData(target, 0L, data.remaining().L, memAddress0(data) + data.position())
    inline fun subData(data: ShortBuffer) = GL15.nglBufferSubData(target, 0L, data.remaining().L shl 1, memAddress0(data) + data.position() shl 1)
    inline fun subData(data: IntBuffer) = GL15.nglBufferSubData(target, 0L, data.remaining().L shl 2, memAddress0(data) + data.position() shl 2)
    inline fun subData(data: LongBuffer) = GL15.nglBufferSubData(target, 0L, data.remaining().L shl 3, memAddress0(data) + data.position() shl 3)
    inline fun subData(data: FloatBuffer) = GL15.nglBufferSubData(target, 0L, data.remaining().L shl 2, memAddress0(data) + data.position() shl 2)
    inline fun subData(data: DoubleBuffer) = GL15.nglBufferSubData(target, 0L, data.remaining().L shl 3, memAddress0(data) + data.position() shl 3)


    // ----- Mat4 -----
    inline fun data(mat: Mat4, usage: Int = 0) {
        mat to buf
        GL15.nglBufferData(target, Mat4.size.L, bufAd, usage)
    }

    inline fun subData(offset: Int, mat: Mat4) {
        mat to buf
        GL15.nglBufferSubData(target, offset.L, Mat4.size.L, bufAd)
    }

    inline fun subData(mat: Mat4) {
        mat to buf
        GL15.nglBufferSubData(target, 0L, Mat4.size.L, bufAd)
    }


    inline fun bindRange(index: Int, offset: Int, size: Int) = GL30.glBindBufferRange(target, index, name, offset.L, size.L)

    inline fun bindBase(index: Int) = GL30.glBindBufferBase(target, index, 0)

    inline fun mapRange(length: Int, access: Int) = mapRange(0, length, access)
    inline fun mapRange(offset: Int, length: Int, access: Int): ByteBuffer = GL30.glMapBufferRange(target, offset.L, length.L, access)

    inline fun flushRange(length: Int) = flushRange(0, length)
    inline fun flushRange(offset: Int, length: Int) = GL30.glFlushMappedBufferRange(target, offset.L, length.L)
    inline fun unmap() = GL15.glUnmapBuffer(target)
}

object Buffers {

    lateinit var buffers: IntBuffer
    var target = 0

    inline fun at(bufferIndex: Enum<*>, block: Buffer.() -> Unit) = at(bufferIndex.ordinal, block)
    inline fun at(bufferIndex: Int, block: Buffer.() -> Unit) {
        Buffer.target = target
        Buffer.name = buffers[bufferIndex] // bind
        Buffer.block()
    }

    inline fun withArrayAt(bufferIndex: Enum<*>, block: Buffer.() -> Unit) = withArrayAt(bufferIndex.ordinal, block)
    inline fun withArrayAt(bufferIndex: Int, block: Buffer.() -> Unit) {
        Buffer.target = GL15.GL_ARRAY_BUFFER
        Buffer.name = buffers[bufferIndex] // bind
        Buffer.block()
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)
    }

    //    inline fun withElement(block: Buffer.() -> Unit) = withElementAt(0, block)
    inline fun withElementAt(bufferIndex: Enum<*>, block: Buffer.() -> Unit) = withElementAt(bufferIndex.ordinal, block)

    inline fun withElementAt(bufferIndex: Int, block: Buffer.() -> Unit) {
        Buffer.target = GL15.GL_ELEMENT_ARRAY_BUFFER
        Buffer.name = buffers[bufferIndex] // bind
        Buffer.block()
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0)
    }

    //    inline fun withUniform(block: Buffer.() -> Unit) = withUniformAt(0, block)
    inline fun withUniformAt(bufferIndex: Enum<*>, block: Buffer.() -> Unit) = withUniformAt(bufferIndex.ordinal, block)

    inline fun withUniformAt(bufferIndex: Int, block: Buffer.() -> Unit) {
        Buffer.target = GL31.GL_UNIFORM_BUFFER
        Buffer.name = buffers[bufferIndex] // bind
        Buffer.block()
        GL15.glBindBuffer(GL31.GL_UNIFORM_BUFFER, 0)
    }
}


inline fun mappingUniformBufferRange(buffer: Enum<*>, length: Int, access: Int, block: MappedBuffer.() -> Unit) = mappingUniformBufferRange(bufferName[buffer], length, access, block)
inline fun mappingUniformBufferRange(buffer: IntBuffer, length: Int, access: Int, block: MappedBuffer.() -> Unit) = mappingUniformBufferRange(buffer[0], length, access, block)
inline fun mappingUniformBufferRange(buffer: Int, length: Int, access: Int, block: MappedBuffer.() -> Unit) = mappingBufferRange(GL31.GL_UNIFORM_BUFFER, buffer, length, access, block)
inline fun mappingBufferRange(target: Int, buffer: Int, length: Int, access: Int, block: MappedBuffer.() -> Unit) {
    MappedBuffer.target = GL31.GL_UNIFORM_BUFFER
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
inline fun glFlushMappedBufferRange(target: Int, offset: Int, length: Int) = GL30.glFlushMappedBufferRange(target, offset.L, length.L)

//inline fun glUnmapArrayBuffer() = GL15.glUnmapBuffer(GL15.GL_ARRAY_BUFFER)
//inline fun glUnmapElementBuffer() = GL15.glUnmapBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER)
//inline fun glUnmapUniformBuffer() = GL15.glUnmapBuffer(GL31.GL_UNIFORM_BUFFER)

object MappedBuffer {

    var target = 0
    var name = 0
        set(value) {
            GL15.glBindBuffer(target, value)
            field = value
        }

    fun mapRange(length: Int, access: Int) {
        buffer = GL30.glMapBufferRange(target, 0L, length.L, access)
    }

    lateinit var buffer: ByteBuffer

    var pointer: Any = NULL
        set(value) {
            if (value is Mat4) value to buffer
        }
}