@file:Suppress("NOTHING_TO_INLINE")

package gln.buffer

import glm_.L
import glm_.mat4x4.Mat4
import gln.BufferTarget
import gln.Usage
import gln.buf
import gln.bufAd
import gln.identifiers.GlBuffers
import kool.IntBuffer
import org.lwjgl.opengl.GL15C
import org.lwjgl.opengl.GL20C

/**
 * Created by elect on 18/04/17.
 */


fun glGenBuffers(size: Int) = GlBuffers(size).also { GL15C.glGenBuffers(it.names) }


// ----- Mat4 ----- TODO others

inline fun glBufferData(target: BufferTarget, mat: Mat4, usage: Usage = Usage.STATIC_DRAW) {
    mat to buf
    GL15C.nglBufferData(target.i, Mat4.size.L, bufAd, usage.i)
}

inline fun glBufferSubData(target: BufferTarget, offset: Int, mat: Mat4) {
    mat to buf
    GL15C.nglBufferSubData(target.i, offset.L, Mat4.size.L, bufAd)
}

inline fun glBufferSubData(target: BufferTarget, mat: Mat4) {
    mat to buf
    GL15C.nglBufferSubData(target.i, 0L, Mat4.size.L, bufAd)
}


inline fun glDrawBuffers(vararg buffers: Int) = GL20C.glDrawBuffers(buffers)