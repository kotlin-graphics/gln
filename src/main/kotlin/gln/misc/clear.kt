@file:Suppress("NOTHING_TO_INLINE")

package gln.misc

import glm_.f
import glm_.vec1.Vec1
import glm_.vec4.Vec4
import gln.L
import gln.at
import gln.offHeapPtr
import kool.set
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL30C

/**
 * Created by GBarbieri on 21.04.2017.
 */

inline fun clear(block: Clear.() -> Unit) = Clear.block()

object Clear {

    inline fun color() = color(0f, 0f, 0f, 1f)
    inline fun color(f: Float) = color(f, f, f, f)
    inline fun color(n: Number) = color(n, n, n, n)
    inline fun color(red: Number, green: Number, blue: Number, alpha: Number) = color(red.f, green.f, blue.f, alpha.f)
    inline fun color(red: Float, green: Float, blue: Float, alpha: Float) = glClearBuffer(GL11.GL_COLOR, 0, red, green, blue, alpha)

    inline fun depth() = depth(1f)
    inline fun depth(depth: Number) = depth(depth.f)
    inline fun depth(depth: Float) = glClearBuffer(GL11.GL_DEPTH, 0, depth)

    // TODO stencil
}

//inline fun glClearBuffer(buffer: Int, value: Float) = glClearBuffer(buffer, 0, value)
//inline fun glClearBuffer(buffer: Int, drawbuffer: Int, value: Float) = GL30.glClearBufferfv(buffer, drawbuffer, fBuf.put(0, value))

inline fun glClearColorBuffer(drawbuffer: Int, color: Vec4) = glClearBuffer(GL11.GL_COLOR, drawbuffer, color)
inline fun glClearColorBuffer(color: Vec4) = glClearBuffer(GL11.GL_COLOR, 0, color.x, color.y, color.z, color.w)

inline fun glClearColorBuffer(drawbuffer: Int, red: Float = 0f, green: Float = 0f, blue: Float = 0f, alpha: Float = 1f) = glClearBuffer(GL11.GL_COLOR, drawbuffer, red, green, blue, alpha)
inline fun glClearColorBuffer(red: Float = 0f, green: Float = 0f, blue: Float = 0f, alpha: Float = 1f) = glClearBuffer(GL11.GL_COLOR, 0, red, green, blue, alpha)

inline fun glClearDepthBuffer(depth: Float = 1f) = glClearBuffer(GL11.GL_DEPTH, 0, depth)
inline fun glClearStencilBuffer(stencil: Int = 0) = GL30C.nglClearBufferiv(GL11.GL_STENCIL, 0, stencil at offHeapPtr)
inline fun glClearDepthStencilBuffer(depth: Float, stencil: Int = 0) = GL30C.glClearBufferfi(GL30C.GL_DEPTH_STENCIL, 0, depth, stencil)


inline fun glClearBuffer(buffer: Int, value: Vec4) = GL30.nglClearBufferfv(buffer, 0, value at offHeapPtr)

inline fun glClearBuffer(buffer: Int, drawbuffer: Int, value: Vec1) = GL30.nglClearBufferfv(buffer, drawbuffer, value at offHeapPtr)

inline fun glClearBuffer(buffer: Int, drawbuffer: Int, color: Vec4) = glClearBuffer(buffer, drawbuffer, color.x, color.y, color.z, color.w)

inline fun glClearBuffer(buffer: Int, drawbuffer: Int, float: Float) = GL30.nglClearBufferfv(buffer, drawbuffer, float at offHeapPtr)

inline fun glClearBuffer(buffer: Int, drawbuffer: Int, red: Float, green: Float, blue: Float, alpha: Float) {
    val p = offHeapPtr.toPtr<Float>()
    p[0] = red; p[1] = green; p[2] = blue; p[3] = alpha
    GL30.nglClearBufferfv(buffer, drawbuffer, p.adr.L)
}