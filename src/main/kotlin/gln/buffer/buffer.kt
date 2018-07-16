package gln.buffer

import glm_.BYTES
import glm_.L
import glm_.buffer.adr
import glm_.buffer.free
import glm_.mat4x4.Mat4
import glm_.size
import gln.buf
import gln.bufAd
import gln.buffer.BufferTarget.BufferTarget2
import gln.get
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL31.*
import org.lwjgl.opengl.GL40.GL_DRAW_INDIRECT_BUFFER
import org.lwjgl.opengl.GL42.GL_ATOMIC_COUNTER_BUFFER
import org.lwjgl.opengl.GL43.GL_DISPATCH_INDIRECT_BUFFER
import org.lwjgl.opengl.GL43.GL_SHADER_STORAGE_BUFFER
import org.lwjgl.opengl.GL44.GL_QUERY_BUFFER
import org.lwjgl.system.JNI
import org.lwjgl.system.MemoryUtil
import org.lwjgl.system.MemoryUtil.NULL
import java.nio.*
import kotlin.properties.Delegates
import kotlin.reflect.KMutableProperty0

/**
 * Created by elect on 18/04/17.
 */

var bufferName: IntBuffer by Delegates.notNull()

inline fun glGenBuffer(buffer: KMutableProperty0<Int>) = buffer.set(GL15.glGenBuffers())
inline fun glGenBuffer() = GL15.glGenBuffers()
inline fun glDeleteBuffer(buffer: Int) = GL15.glDeleteBuffers(buffer)

enum class Usage(val i: Int) {
    StreamDraw(GL_STREAM_DRAW),
    StreamRead(GL_STREAM_READ),
    StreamCopy(GL_STREAM_COPY),
    StaticDraw(GL_STATIC_DRAW),
    StaticRead(GL_STATIC_READ),
    StaticCopy(GL_STATIC_COPY),
    DynamicDraw(GL_DYNAMIC_DRAW),
    DynamicRead(GL_DYNAMIC_READ),
    DynamicCopy(GL_DYNAMIC_COPY)
}


sealed class BufferTarget(val i: Int) {

    /** Vertex attributes */
    object Array : BufferTarget(GL_ARRAY_BUFFER)

    /** Buffer copy source */
    object CopyRead : BufferTarget(GL_COPY_READ_BUFFER)

    /** Buffer copy destination */
    object CopyWrite : BufferTarget(GL_COPY_WRITE_BUFFER)

    /** Indirect compute dispatch commands */
    object DispatchIndirect : BufferTarget(GL_DISPATCH_INDIRECT_BUFFER)

    /** Indirect command arguments */
    object DrawIndirect : BufferTarget(GL_DRAW_INDIRECT_BUFFER)

    /** Vertex array indices */
    object ElementArray : BufferTarget(GL_ELEMENT_ARRAY_BUFFER)

    /** Pixel read target */
    object PixelPack : BufferTarget(GL_PIXEL_PACK_BUFFER)

    /** Texture data source */
    object PixelUnpack : BufferTarget(GL_PIXEL_UNPACK_BUFFER)

    /** Query result buffer */
    object Query : BufferTarget(GL_QUERY_BUFFER)

    /** glBindBufferbase and glBindBufferRange */
    sealed class BufferTarget2(i: Int) : BufferTarget(i) {

        /** Atomic counter storage */
        object AtomicCounter : BufferTarget2(GL_ATOMIC_COUNTER_BUFFER)

        /** Read-write storage for shaders */
        object ShaderStorage : BufferTarget2(GL_SHADER_STORAGE_BUFFER)

        /** Texture data buffer */
        object Texture : BufferTarget2(GL_TEXTURE_BUFFER)

        /** Transform feedback buffer */
        object Transform : BufferTarget2(GL_TRANSFORM_FEEDBACK_BUFFER)

        /** Uniform block storage */
        object Uniform : BufferTarget2(GL_UNIFORM_BUFFER)
    }

    companion object {
        infix fun of(i: Int): BufferTarget = when (i) {
            GL_ARRAY_BUFFER -> BufferTarget.Array
            GL_COPY_READ_BUFFER -> BufferTarget.CopyRead
            GL_COPY_WRITE_BUFFER -> BufferTarget.CopyWrite
            GL_DISPATCH_INDIRECT_BUFFER -> BufferTarget.DispatchIndirect
            GL_DRAW_INDIRECT_BUFFER -> BufferTarget.DrawIndirect
            GL_ELEMENT_ARRAY_BUFFER -> BufferTarget.ElementArray
            GL_PIXEL_PACK_BUFFER -> BufferTarget.PixelPack
            GL_PIXEL_UNPACK_BUFFER -> BufferTarget.PixelUnpack
            GL_ATOMIC_COUNTER_BUFFER -> BufferTarget2.AtomicCounter
            GL_SHADER_STORAGE_BUFFER -> BufferTarget2.ShaderStorage
            GL_TEXTURE_BUFFER -> BufferTarget2.Texture
            GL_TRANSFORM_FEEDBACK_BUFFER -> BufferTarget2.Transform
            GL_UNIFORM_BUFFER -> BufferTarget2.Uniform
            else -> throw Error()
        }
    }
}

inline fun glArrayBufferData(size: Int, usage: Usage) = GL15.nglBufferData(GL_ARRAY_BUFFER, size.L, NULL, usage.i)
inline fun glArrayBufferData(floats: FloatArray, usage: Usage) = GL15.glBufferData(GL_ARRAY_BUFFER, floats, usage.i)
inline fun glArrayBufferData(floats: FloatBuffer, usage: Usage) = GL15.glBufferData(GL_ARRAY_BUFFER, floats, usage.i)

inline fun glArrayBufferSubData(offset: Int, elements: Int, floats: FloatArray) = JNI.callPPPV(GL.getCapabilities().glBufferSubData, GL_ARRAY_BUFFER, offset.L, (elements * Float.BYTES).L, floats)
inline fun glArrayBufferSubData(floats: FloatArray) = GL15.glBufferSubData(GL_ARRAY_BUFFER, 0L, floats)

inline fun glElementBufferData(size: Int, usage: Usage) = GL15.nglBufferData(GL_ELEMENT_ARRAY_BUFFER, size.L, NULL, usage.i)
inline fun glElementBufferData(ints: IntArray, usage: Usage) = GL15.glBufferData(GL_ELEMENT_ARRAY_BUFFER, ints, usage.i)
inline fun glElementBufferData(ints: IntBuffer, usage: Usage) = GL15.glBufferData(GL_ELEMENT_ARRAY_BUFFER, ints, usage.i)
inline fun glElementBufferData(shorts: ShortArray, usage: Usage) = GL15.glBufferData(GL_ELEMENT_ARRAY_BUFFER, shorts, usage.i)
inline fun glElementBufferData(shorts: ShortBuffer, usage: Usage) = GL15.glBufferData(GL_ELEMENT_ARRAY_BUFFER, shorts, usage.i)

inline fun glElementBufferSubData(offset: Int, elements: Int, ints: IntArray) = JNI.callPPPV(GL.getCapabilities().glBufferSubData, GL_ARRAY_BUFFER, offset.L, (elements * Int.BYTES).L, ints)
inline fun glElementBufferSubData(ints: IntArray) = GL15.glBufferSubData(GL_ARRAY_BUFFER, 0L, ints)
inline fun glElementBufferSubData(offset: Int, elements: Int, shorts: ShortArray) = JNI.callPPPV(GL.getCapabilities().glBufferSubData, GL_ARRAY_BUFFER, offset.L, (elements * Short.BYTES).L, shorts)
inline fun glElementBufferSubData(shorts: ShortArray) = GL15.glBufferSubData(GL_ARRAY_BUFFER, 0L, shorts)

inline fun glUniformBufferData(size: Int, usage: Usage) = GL15.nglBufferData(GL_UNIFORM_BUFFER, size.L, NULL, usage.i)

inline fun glBufferData(target: BufferTarget, size: Int, usage: Usage) = GL15.nglBufferData(target.i, size.L, NULL, usage.i)


// ----- Mat4 ----- TODO others

inline fun glBufferData(target: BufferTarget, mat: Mat4, usage: Usage) {
    mat to buf
    GL15.nglBufferData(target.i, Mat4.size.L, bufAd, usage.i)
}

inline fun glBufferSubData(target: BufferTarget, offset: Int, mat: Mat4) {
    mat to buf
    GL15.nglBufferSubData(target.i, offset.L, Mat4.size.L, bufAd)
}

inline fun glBufferSubData(target: BufferTarget, mat: Mat4) {
    mat to buf
    GL15.nglBufferSubData(target.i, 0L, Mat4.size.L, bufAd)
}


inline fun glBufferData(target: BufferTarget, data: FloatArray, usage: Usage) {
    val buffer = MemoryUtil.memAlloc(data.size * Float.BYTES)
    for (i in data.indices) buffer.putFloat(i * Float.BYTES, data[i])
    GL15.nglBufferData(target.i, buffer.size.L, buffer.adr, usage.i)
    buffer.free()
}


inline fun glBufferSubData(target: BufferTarget, offset: Int, size: Int, buffer: ByteBuffer) = GL15.nglBufferSubData(target.i, offset.L, size.L, buffer.adr)
inline fun glBufferSubData(target: BufferTarget, offset: Int, size: Int, buffer: ShortBuffer) = GL15.nglBufferSubData(target.i, offset.L, size.L, buffer.adr)
inline fun glBufferSubData(target: BufferTarget, offset: Int, size: Int, buffer: IntBuffer) = GL15.nglBufferSubData(target.i, offset.L, size.L, buffer.adr)
inline fun glBufferSubData(target: BufferTarget, offset: Int, size: Int, buffer: LongBuffer) = GL15.nglBufferSubData(target.i, offset.L, size.L, buffer.adr)
inline fun glBufferSubData(target: BufferTarget, offset: Int, size: Int, buffer: CharBuffer) = GL15.nglBufferSubData(target.i, offset.L, size.L, buffer.adr)
inline fun glBufferSubData(target: BufferTarget, offset: Int, size: Int, buffer: FloatBuffer) = GL15.nglBufferSubData(target.i, offset.L, size.L, buffer.adr)
inline fun glBufferSubData(target: BufferTarget, offset: Int, size: Int, buffer: DoubleBuffer) = GL15.nglBufferSubData(target.i, offset.L, size.L, buffer.adr)


inline fun glBindBuffer(target: BufferTarget) = GL15.glBindBuffer(target.i, 0)
inline fun glBindBuffer(target: BufferTarget, buffer: IntBuffer) = GL15.glBindBuffer(target.i, buffer[0])
inline fun glBindBuffer(target: BufferTarget, buffer: Enum<*>) = GL15.glBindBuffer(target.i, bufferName[buffer])

inline fun glBindArrayBuffer(buffer: Enum<*>) = GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferName[buffer])
inline fun glBindArrayBuffer(buffer: IntArray) = GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, buffer[0])
inline fun glBindArrayBuffer(buffer: IntBuffer) = GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, buffer[0])
inline fun glBindArrayBuffer(buffer: Int = 0) = GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, buffer)

inline fun glBindElementBuffer(buffer: Enum<*>) = GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, bufferName[buffer])
inline fun glBindElementBuffer(buffer: IntArray) = GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer[0])
inline fun glBindElementBuffer(buffer: IntBuffer) = GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer[0])
inline fun glBindElementBuffer(buffer: Int = 0) = GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer)


inline fun glBindUniformBufferRange(index: Int, buffer: Enum<*>, size: Int) = glBindBufferRange(BufferTarget2.Uniform, index, buffer, 0, size)
inline fun glBindUniformBufferRange(index: Int, buffer: Enum<*>, offset: Int, size: Int) = glBindBufferRange(BufferTarget2.Uniform, index, buffer, offset, size)
inline fun glBindUniformBufferRange(index: Int, buffer: IntBuffer, offset: Int, size: Int) = glBindBufferRange(BufferTarget2.Uniform, index, buffer, offset, size)

inline fun glBindBufferRange(target: BufferTarget2, index: Int, buffer: IntBuffer, offset: Int, size: Int) = GL30.glBindBufferRange(target.i, index, buffer[0], offset.L, size.L)
inline fun glBindBufferRange(target: BufferTarget2, index: Int, buffer: Enum<*>, offset: Int, size: Int) = GL30.glBindBufferRange(target.i, index, bufferName[buffer], offset.L, size.L)


inline fun glBindUniformBufferBase(index: Int, buffer: Enum<*>) = glBindBufferBase(BufferTarget2.Uniform, index, buffer)

inline fun glBindBufferBase(target: BufferTarget2, index: Int, buffer: Enum<*>) = GL30.glBindBufferBase(target.i, index, bufferName[buffer])
inline fun glBindBufferBase(target: BufferTarget2, index: Int, buffer: IntBuffer) = GL30.glBindBufferBase(target.i, index, buffer[0])
inline fun glBindBufferBase(target: BufferTarget2, index: Int) = GL30.glBindBufferBase(target.i, index, 0)

inline fun glDrawBuffers(vararg buffers: Int) = GL20.glDrawBuffers(buffers)