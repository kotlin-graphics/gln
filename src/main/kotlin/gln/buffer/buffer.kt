package gln.buffer

import gli_.buffer.destroy
import glm_.BYTES
import glm_.L
import glm_.mat4x4.Mat4
import glm_.size
import gln.buf
import gln.bufAd
import gln.get
import org.lwjgl.opengl.*
import org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER
import org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER
import org.lwjgl.opengl.GL31.GL_UNIFORM_BUFFER
import org.lwjgl.system.JNI
import org.lwjgl.system.MemoryUtil
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.system.MemoryUtil.memAddress
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



inline fun glArrayBufferData(size: Int, usage: Int) = GL15.nglBufferData(GL_ARRAY_BUFFER, size.L, NULL, usage)
inline fun glArrayBufferData(floats: FloatArray, usage: Int) = GL15.glBufferData(GL_ARRAY_BUFFER, floats, usage)

inline fun glArrayBufferSubData(offset: Int, elements: Int, floats: FloatArray) = JNI.callPPPV(GL.getCapabilities().glBufferSubData, GL_ARRAY_BUFFER, offset.L, (elements shl 2).L, floats)
inline fun glArrayBufferSubData(floats: FloatArray) = GL15.glBufferSubData(GL_ARRAY_BUFFER, 0L, floats)

inline fun glElementBufferData(size: Int, usage: Int) = GL15.nglBufferData(GL_ELEMENT_ARRAY_BUFFER, size.L, NULL, usage)
inline fun glElementBufferData(ints: IntArray, usage: Int) = GL15.glBufferData(GL_ELEMENT_ARRAY_BUFFER, ints, usage)
inline fun glElementBufferData(shorts: ShortArray, usage: Int) = GL15.glBufferData(GL_ELEMENT_ARRAY_BUFFER, shorts, usage)

inline fun glElementBufferSubData(offset: Int, elements: Int, ints: IntArray) = JNI.callPPPV(GL.getCapabilities().glBufferSubData, GL_ARRAY_BUFFER, offset.L, (elements shl 2).L, ints)
inline fun glElementBufferSubData(ints: IntArray) = GL15.glBufferSubData(GL_ARRAY_BUFFER, 0L, ints)
inline fun glElementBufferSubData(offset: Int, elements: Int, shorts: ShortArray) = JNI.callPPPV(GL.getCapabilities().glBufferSubData, GL_ARRAY_BUFFER, offset.L, (elements shl 2).L, shorts)
inline fun glElementBufferSubData(shorts: ShortArray) = GL15.glBufferSubData(GL_ARRAY_BUFFER, 0L, shorts)

inline fun glUniformBufferData(size: Int, usage: Int) = GL15.nglBufferData(GL_UNIFORM_BUFFER, size.L, NULL, usage)

inline fun glBufferData(target: Int, size: Int, usage: Int) = GL15.nglBufferData(target, size.L, NULL, usage)


// ----- Mat4 ----- TODO others

inline fun glBufferData(target: Int, mat: Mat4, usage: Int) {
    mat to buf
    GL15.nglBufferData(target, Mat4.size.L, bufAd, usage)
}

inline fun glBufferSubData(target: Int, offset: Int, mat: Mat4) {
    mat to buf
    GL15.nglBufferSubData(target, offset.L, Mat4.size.L, bufAd)
}

inline fun glBufferSubData(target: Int, mat: Mat4) {
    mat to buf
    GL15.nglBufferSubData(target, 0L, Mat4.size.L, bufAd)
}


inline fun glBufferData(target: Int, data: FloatArray, usage: Int) {
    val buffer = MemoryUtil.memAlloc(data.size * Float.BYTES)
    for(i in data.indices) buffer.putFloat(i * Float.BYTES, data[i])
    GL15.nglBufferData(target, buffer.size.L, memAddress(buffer, 0), usage)
    buffer.destroy()
}


inline fun glBufferSubData(target: Int, offset: Int, size: Int, buffer: ByteBuffer) = GL15.nglBufferSubData(target, 0L, size.L, memAddress(buffer))
inline fun glBufferSubData(target: Int, offset: Int, size: Int, buffer: ShortBuffer) = GL15.nglBufferSubData(target, 0L, size.L, memAddress(buffer))
inline fun glBufferSubData(target: Int, offset: Int, size: Int, buffer: IntBuffer) = GL15.nglBufferSubData(target, 0L, size.L, memAddress(buffer))
inline fun glBufferSubData(target: Int, offset: Int, size: Int, buffer: LongBuffer) = GL15.nglBufferSubData(target, 0L, size.L, memAddress(buffer))
inline fun glBufferSubData(target: Int, offset: Int, size: Int, buffer: CharBuffer) = GL15.nglBufferSubData(target, 0L, size.L, memAddress(buffer))
inline fun glBufferSubData(target: Int, offset: Int, size: Int, buffer: FloatBuffer) = GL15.nglBufferSubData(target, 0L, size.L, memAddress(buffer))
inline fun glBufferSubData(target: Int, offset: Int, size: Int, buffer: DoubleBuffer) = GL15.nglBufferSubData(target, 0L, size.L, memAddress(buffer))


inline fun glBindBuffer(target: Int) = GL15.glBindBuffer(target, 0)
inline fun glBindBuffer(target: Int, buffer: IntBuffer) = GL15.glBindBuffer(target, buffer[0])
inline fun glBindBuffer(target: Int, buffer: Enum<*>) = GL15.glBindBuffer(target, bufferName[buffer])

inline fun glBindArrayBuffer(buffer: Enum<*>) = GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferName[buffer])
inline fun glBindArrayBuffer(buffer: IntArray) = GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, buffer[0])
inline fun glBindArrayBuffer(buffer: IntBuffer) = GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, buffer[0])
inline fun glBindArrayBuffer(buffer: Int = 0) = GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, buffer)

inline fun glBindElementBuffer(buffer: Enum<*>) = GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, bufferName[buffer])
inline fun glBindElementBuffer(buffer: IntArray) = GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer[0])
inline fun glBindElementBuffer(buffer: IntBuffer) = GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer[0])
inline fun glBindElementBuffer(buffer: Int = 0) = GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer)


inline fun glBindUniformBufferRange(index: Int, buffer: Enum<*>, size: Int) = glBindBufferRange(GL31.GL_UNIFORM_BUFFER, index, buffer, 0, size)
inline fun glBindUniformBufferRange(index: Int, buffer: Enum<*>, offset: Int, size: Int) = glBindBufferRange(GL31.GL_UNIFORM_BUFFER, index, buffer, offset, size)
inline fun glBindUniformBufferRange(index: Int, buffer: IntBuffer, offset: Int, size: Int) = glBindBufferRange(GL31.GL_UNIFORM_BUFFER, index, buffer, offset, size)

inline fun glBindBufferRange(target: Int, index: Int, buffer: IntBuffer, offset: Int, size: Int) = GL30.glBindBufferRange(target, index, buffer[0], offset.L, size.L)
inline fun glBindBufferRange(target: Int, index: Int, buffer: Enum<*>, offset: Int, size: Int) = GL30.glBindBufferRange(target, index, bufferName[buffer], offset.L, size.L)


inline fun glBindUniformBufferBase(index: Int, buffer: Enum<*>) = glBindBufferBase(GL31.GL_UNIFORM_BUFFER, index, buffer)

inline fun glBindBufferBase(target: Int, index: Int, buffer: Enum<*>) = GL30.glBindBufferBase(target, index, bufferName[buffer])
inline fun glBindBufferBase(target: Int, index: Int, buffer: IntBuffer) = GL30.glBindBufferBase(target, index, buffer[0])
inline fun glBindBufferBase(target: Int, index: Int) = GL30.glBindBufferBase(target, index, 0)

inline fun glDrawBuffers(vararg buffers: Int) = GL20.glDrawBuffers(buffers)