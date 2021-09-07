@file:Suppress("NOTHING_TO_INLINE")

package gln.buffer

import glm_.L
import glm_.mat4x4.Mat4
import gln.*
import gln.identifiers.GlBuffers
import kool.Stack
import kool.adr
import org.lwjgl.opengl.GL15C
import org.lwjgl.opengl.GL20C

/**
 * Created by elect on 18/04/17.
 */


fun glGenBuffers(size: Int) = GlBuffers(size).also { GL15C.glGenBuffers(it.names) }


// ----- Mat4 ----- TODO others

inline fun glBufferData(target: BufferTarget, mat: Mat4, usage: Usage = Usage.STATIC_DRAW) =
    Stack.mat4Address(mat) { GL15C.nglBufferData(target.i, Mat4.size.L, it, usage.i) }

inline fun glBufferSubData(target: BufferTarget, offset: Int, mat: Mat4) =
    Stack.mat4Address(mat) { GL15C.nglBufferSubData(target.i, offset.L, Mat4.size.L, it) }

inline fun glBufferSubData(target: BufferTarget, mat: Mat4) =
    Stack.mat4Address(mat) { GL15C.nglBufferSubData(target.i, 0L, Mat4.size.L, it) }


inline fun glDrawBuffers(vararg buffers: Int) = GL20C.glDrawBuffers(buffers)