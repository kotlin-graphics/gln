@file:Suppress("NOTHING_TO_INLINE")

package gln.misc

import glm_.BYTES
import glm_.f
import glm_.vec1.Vec1
import glm_.vec4.Vec4
import gln.buf
import gln.bufAd
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL30

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

inline fun glClearDepthBuffer(depth: Float = 1f) = glClearBuffer(GL11.GL_DEPTH, 0, depth)

inline fun glClearColorBuffer(drawbuffer: Int, color: Vec4) = glClearBuffer(GL11.GL_COLOR, drawbuffer, color)
inline fun glClearColorBuffer(color: Vec4) = glClearBuffer(GL11.GL_COLOR, 0, color.x, color.y, color.z, color.w)

inline fun glClearColorBuffer(drawbuffer: Int, red: Float = 0f, green: Float = 0f, blue: Float = 0f, alpha: Float = 1f) = glClearBuffer(GL11.GL_COLOR, drawbuffer, red, green, blue, alpha)
inline fun glClearColorBuffer(red: Float = 0f, green: Float = 0f, blue: Float = 0f, alpha: Float = 1f) = glClearBuffer(GL11.GL_COLOR, 0, red, green, blue, alpha)


inline fun glClearBuffer(buffer: Int, value: Vec4) {
    value to buf
    GL30.nglClearBufferfv(buffer, 0, bufAd)
}

inline fun glClearBuffer(buffer: Int, drawbuffer: Int, value: Vec1) {
    buf.putFloat(0, value.x)
    GL30.nglClearBufferfv(buffer, drawbuffer, bufAd)
}

inline fun glClearBuffer(buffer: Int, drawbuffer: Int, color: Vec4) = glClearBuffer(buffer, drawbuffer, color.x, color.y, color.z, color.w)

inline fun glClearBuffer(buffer: Int, drawbuffer: Int, float: Float) {
    buf.putFloat(0, float)
    GL30.nglClearBufferfv(buffer, drawbuffer, bufAd)
}

inline fun glClearBuffer(buffer: Int, drawbuffer: Int, red: Float, green: Float, blue: Float, alpha: Float) {
    buf.putFloat(0, red).putFloat(Float.BYTES, green).putFloat(Float.BYTES * 2, blue).putFloat(Float.BYTES * 3, alpha)
    GL30.nglClearBufferfv(buffer, drawbuffer, bufAd)
}