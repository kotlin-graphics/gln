package gln.buffer

import glm_.L
import glm_.mat4x4.Mat4
import gln.buf
import gln.bufAd
import gln.get
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL31
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.system.MemoryUtil.memAddress
import java.nio.ByteBuffer
import java.nio.IntBuffer
import kotlin.properties.Delegates

/**
 * Created by elect on 18/04/17.
 */

var bufferName: IntBuffer by Delegates.notNull()

//
//inline fun glArrayBufferData(size: Int, usage: Int) = GL15.nglBufferData(GL15.GL_ARRAY_BUFFER, size.L, NULL, usage)
//inline fun glElementBufferData(size: Int, usage: Int) = GL15.nglBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, size.L, NULL, usage)
//inline fun glUniformBufferData(size: Int, usage: Int) = GL15.nglBufferData(GL31.GL_UNIFORM_BUFFER, size.L, NULL, usage)
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

inline fun glBufferSubData(target: Int, offset: Int, size: Int, buffer: ByteBuffer) = GL15.nglBufferSubData(target, 0L, size.L, memAddress(buffer))


inline fun glBindBuffer(target: Int) = GL15.glBindBuffer(target, 0)
inline fun glBindBuffer(target: Int, buffer: IntBuffer) = GL15.glBindBuffer(target, buffer[0])
inline fun glBindBuffer(target: Int, buffer: Enum<*>) = GL15.glBindBuffer(target, bufferName[buffer])

inline fun glBindArrayBuffer(buffer: Enum<*>) = GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferName[buffer])
inline fun glBindArrayBuffer(buffer: IntArray) = GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, buffer[0])
inline fun glBindArrayBuffer(buffer: Int) = GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, buffer)

inline fun glBindElementBuffer(buffer: Enum<*>) = GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, bufferName[buffer])
inline fun glBindElementBuffer(buffer: IntArray) = GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer[0])
inline fun glBindElementBuffer(buffer: Int) = GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer)


inline fun glBindUniformBufferRange(index: Int, buffer: Enum<*>, size: Int) = glBindBufferRange(GL31.GL_UNIFORM_BUFFER, index, buffer, 0, size)
inline fun glBindUniformBufferRange(index: Int, buffer: Enum<*>, offset: Int, size: Int) = glBindBufferRange(GL31.GL_UNIFORM_BUFFER, index, buffer, offset, size)
inline fun glBindUniformBufferRange(index: Int, buffer: IntBuffer, offset: Int, size: Int) = glBindBufferRange(GL31.GL_UNIFORM_BUFFER, index, buffer, offset, size)

inline fun glBindBufferRange(target: Int, index: Int, buffer: IntBuffer, offset: Int, size: Int) = GL30.glBindBufferRange(target, index, buffer[0], offset.L, size.L)
inline fun glBindBufferRange(target: Int, index: Int, buffer: Enum<*>, offset: Int, size: Int) = GL30.glBindBufferRange(target, index, bufferName[buffer], offset.L, size.L)


inline fun glBindUniformBufferBase(index: Int, buffer: Enum<*>) = glBindBufferBase(GL31.GL_UNIFORM_BUFFER, index, buffer)

inline fun glBindBufferBase(target: Int, index: Int, buffer: Enum<*>) = GL30.glBindBufferBase(target, index, bufferName[buffer])
inline fun glBindBufferBase(target: Int, index: Int, buffer: IntBuffer) = GL30.glBindBufferBase(target, index, buffer[0])
inline fun glBindBufferBase(target: Int, index: Int) = GL30.glBindBufferBase(target, index, 0)