package gln

import glm_.BYTES
import glm_.vec2.Vec2
import glm_.vec2.Vec2i
import glm_.vec3.Vec3
import glm_.vec4.Vec4
import glm_.vec4.Vec4i
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL41
import org.lwjgl.system.MemoryUtil
import java.nio.IntBuffer

val buf = MemoryUtil.memAlloc(256)
val buf2 = MemoryUtil.memAlloc(Int.BYTES)
val bufAd = MemoryUtil.memAddress(buf)
val buf2Ad = MemoryUtil.memAddress(buf2)

operator fun IntBuffer.get(e: Enum<*>) = get(e.ordinal)
operator fun IntArray.get(e: Enum<*>) = get(e.ordinal)
operator fun IntArray.set(e: Enum<*>, int: Int) = set(e.ordinal, int)
inline fun <reified T : Enum<T>> intArrayBig() = IntArray(enumValues<T>().size)


fun glViewport(size: Vec2i) = GL11.glViewport(0, 0, size.x, size.y)
fun glViewport(width: Int, height: Int) = GL11.glViewport(0, 0, width, height)
fun glViewport(viewport: Vec4i) = GL11.glViewport(viewport.x, viewport.y, viewport.z, viewport.w)

fun glScissor(size: Vec2i) = GL11.glScissor(0, 0, size.x, size.y)
fun glScissor(scissor: Vec4i) = GL11.glScissor(scissor.x, scissor.y, scissor.z, scissor.w)

fun glBlitFramebuffer(size: Vec2i) = GL30.glBlitFramebuffer(
        0, 0, size.x, size.y,
        0, 0, size.x, size.y,
        GL11.GL_COLOR_BUFFER_BIT, GL11.GL_LINEAR)


fun glClearColor() = GL11.glClearColor(0f, 0f, 0f, 1f)
fun glClearColor(color: Vec3) = GL11.glClearColor(color.x, color.y, color.z, 1f)
fun glClearColor(color: Vec4) = GL11.glClearColor(color.x, color.y, color.z, color.w)
fun glClearDepthf() = GL41.glClearDepthf(1f)


fun glGetVec2(pname: Int): Vec2 {
    GL11.nglGetFloatv(pname, bufAd)
    return Vec2(buf)
}

fun glGetVec2i(pname: Int): Vec2i {
    GL11.nglGetIntegerv(pname, bufAd)
    return Vec2i(buf)
}

fun glGetVec4(pname: Int): Vec4 {
    GL11.nglGetFloatv(pname, bufAd)
    return Vec4(buf)
}

fun glGetVec4i(pname: Int): Vec4i {
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