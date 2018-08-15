@file:Suppress("NOTHING_TO_INLINE")

package gln

import ab.appBuffer
import glm_.BYTES
import glm_.buffer.adr
import glm_.vec2.Vec2
import glm_.vec2.Vec2i
import glm_.vec3.Vec3
import glm_.vec4.Vec4
import glm_.vec4.Vec4bool
import glm_.vec4.Vec4i
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11C
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL41
import org.lwjgl.system.MemoryUtil
import java.awt.Color
import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.IntBuffer
import java.nio.ShortBuffer

@Deprecated("use appBuffer instead")
val buf: ByteBuffer = MemoryUtil.memAlloc(256)
@Deprecated("use appBuffer instead")
val bufAd = buf.adr

operator fun IntBuffer.get(e: Enum<*>) = get(e.ordinal)
operator fun IntBuffer.set(e: Enum<*>, value: Int): IntBuffer = put(e.ordinal, value)
operator fun IntArray.get(e: Enum<*>) = get(e.ordinal)
operator fun IntArray.set(e: Enum<*>, int: Int) = set(e.ordinal, int)
inline fun <reified T : Enum<T>> intArrayBig() = IntArray(enumValues<T>().size)


inline fun glViewport(size: Vec2i) = GL11.glViewport(0, 0, size.x, size.y)
inline fun glViewport(width: Int, height: Int) = GL11.glViewport(0, 0, width, height)
inline fun glViewport(viewport: Vec4i) = GL11.glViewport(viewport.x, viewport.y, viewport.z, viewport.w)

inline fun glScissor(size: Vec2i) = GL11.glScissor(0, 0, size.x, size.y)
inline fun glScissor(scissor: Vec4i) = GL11.glScissor(scissor.x, scissor.y, scissor.z, scissor.w)

inline fun glBlitFramebuffer(size: Vec2i) = GL30.glBlitFramebuffer(
        0, 0, size.x, size.y,
        0, 0, size.x, size.y,
        GL11.GL_COLOR_BUFFER_BIT, GL11.GL_LINEAR)


inline fun glClearColor() = GL11.glClearColor(0f, 0f, 0f, 1f)
inline fun glClearColor(float: Float) = GL11.glClearColor(float, float, float, float)
inline fun glClearColor(color: Color) = GL11.glClearColor(color.red / 255f, color.green / 255f, color.blue / 255f, color.alpha / 255f)
inline fun glClearColor(vec: Vec3) = GL11.glClearColor(vec.x, vec.y, vec.z, 1f)
inline fun glClearColor(vec: Vec3, alpha: Float) = GL11.glClearColor(vec.x, vec.y, vec.z, alpha)
inline fun glClearColor(vec: Vec4) = GL11.glClearColor(vec.x, vec.y, vec.z, vec.w)
inline fun glClearDepthf() = GL41.glClearDepthf(1f)


inline fun glGetVec2(pname: Int): Vec2 {
    val x = appBuffer.float
    val y = appBuffer.float
    return Vec2(MemoryUtil.memGetFloat(x), MemoryUtil.memGetFloat(y))
}

inline fun glGetVec4(pname: Int): Vec4 {
    val x = appBuffer.float
    val y = appBuffer.float
    val z = appBuffer.float
    val w = appBuffer.float
    return Vec4(MemoryUtil.memGetFloat(x), MemoryUtil.memGetFloat(y), MemoryUtil.memGetFloat(z), MemoryUtil.memGetFloat(w))
}

inline fun glGetVec2i(pname: Int): Vec2i {
    val x = appBuffer.int
    val y = appBuffer.int
    return Vec2i(MemoryUtil.memGetInt(x), MemoryUtil.memGetInt(y))
}

inline fun glGetVec4i(pname: Int): Vec4i {
    val x = appBuffer.int
    val y = appBuffer.int
    val z = appBuffer.int
    val w = appBuffer.int
    return Vec4i(MemoryUtil.memGetInt(x), MemoryUtil.memGetInt(y), MemoryUtil.memGetInt(z), MemoryUtil.memGetInt(w))
}

// utils

fun vec4i(pName: Int): Vec4i {
    val ints = appBuffer.intArray(4)
    GL11C.nglGetIntegerv(pName, ints)
    return Vec4i(MemoryUtil.memGetInt(ints), MemoryUtil.memGetInt(ints + Int.BYTES),
            MemoryUtil.memGetInt(ints + Int.BYTES * 2), MemoryUtil.memGetInt(ints + Int.BYTES * 3))
}

fun vec4(pName: Int): Vec4 {
    val floats = appBuffer.floatBuffer(Vec4.length)
    GL11C.nglGetFloatv(pName, floats.adr)
    return Vec4(floats)
}

fun vec4bool(pName: Int): Vec4bool {
    val bools = appBuffer.intBuffer(4)
    GL11C.glGetIntegerv(pName, bools)
    return Vec4bool(bools)
}

fun checkError(location: String, throwError: Boolean = true): Boolean {

    val error = GL11.glGetError()
    if (error != GL11.GL_NO_ERROR) {
        val errorString = when (error) {
            GL11.GL_INVALID_ENUM -> "GL_INVALID_ENUM"
            GL11.GL_INVALID_VALUE -> "GL_INVALID_VALUE"
            GL11.GL_INVALID_OPERATION -> "GL_INVALID_OPERATION"
            GL30.GL_INVALID_FRAMEBUFFER_OPERATION -> "GL_INVALID_FRAMEBUFFER_OPERATION"
            GL11.GL_OUT_OF_MEMORY -> "GL_OUT_OF_MEMORY"
            GL11.GL_STACK_UNDERFLOW -> "GL_STACK_UNDERFLOW"
            GL11.GL_STACK_OVERFLOW -> "GL_STACK_OVERFLOW"
            else -> throw IllegalStateException()
        }
        return if (throwError) throw Error("OpenGL Error ($errorString) at $location") else false
    }
    return true
}

val VERSION = "0.4.4"

fun main(args: Array<String>) {

}

object gl11 : gl11i

object gl15 : gl11i, gl12i, gl13i, gl14i, gl15i

object gl20 :
        gl11i,
        gl12i,
        gl13i,
        gl14i,
        gl15i,
        gl20i

val Buffer.glType: Int
    get() = when(this) {
        is ByteBuffer -> GL11C.GL_UNSIGNED_BYTE
        is ShortBuffer -> GL11C.GL_UNSIGNED_SHORT
        is IntBuffer -> GL11C.GL_UNSIGNED_INT
        else -> throw Error("unsupported")
    }