package gln

import glm_.BYTES
import glm_.buffer.adr
import glm_.vec2.Vec2
import glm_.vec2.Vec2i
import glm_.vec3.Vec3
import glm_.vec4.Vec4
import glm_.vec4.Vec4i
import org.lwjgl.opengl.*
import org.lwjgl.system.MemoryUtil
import java.awt.Color
import java.nio.ByteBuffer
import java.nio.IntBuffer

val buf: ByteBuffer = MemoryUtil.memAlloc(256)
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
    GL11.nglGetFloatv(pname, bufAd)
    return Vec2(buf)
}

inline fun glGetVec2i(pname: Int): Vec2i {
    GL11.nglGetIntegerv(pname, bufAd)
    return Vec2i(buf)
}

inline fun glGetVec4(pname: Int): Vec4 {
    GL11.nglGetFloatv(pname, bufAd)
    return Vec4(buf)
}

inline fun glGetVec4i(pname: Int): Vec4i {
    GL11.nglGetIntegerv(pname, bufAd)
    return Vec4i(buf)
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