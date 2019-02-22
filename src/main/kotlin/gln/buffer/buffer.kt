@file:Suppress("NOTHING_TO_INLINE")

package gln.buffer

import glm_.BYTES
import glm_.L
import glm_.mat4x4.Mat4
import gln.BufferTarget
import gln.Usage
import gln.buf
import gln.bufAd
import kool.adr
import kool.free
import kool.rem
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL20
import org.lwjgl.system.MemoryUtil

/**
 * Created by elect on 18/04/17.
 */



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
    GL15.nglBufferData(target.i, buffer.rem.L, buffer.adr, usage.i)
    buffer.free()
}



inline fun glDrawBuffers(vararg buffers: Int) = GL20.glDrawBuffers(buffers)