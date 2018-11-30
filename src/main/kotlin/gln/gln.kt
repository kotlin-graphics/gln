@file:Suppress("NOTHING_TO_INLINE")

package gln

import glm_.vec2.Vec2
import glm_.vec2.Vec2i
import glm_.vec3.Vec3
import glm_.vec4.Vec4
import glm_.vec4.Vec4bool
import glm_.vec4.Vec4i
import kool.adr
import kool.stak
import org.lwjgl.opengl.*
import org.lwjgl.system.MemoryUtil
import java.awt.Color
import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.IntBuffer
import java.nio.ShortBuffer

@Deprecated("use kool instead")
val buf: ByteBuffer = MemoryUtil.memAlloc(256)
@Deprecated("use kool instead")
val bufAd = buf.adr


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


inline fun glGetVec2(pname: Int): Vec2 = stak.vec2Address{ GL30C.nglGetFloatv(pname, it) }

inline fun glGetVec4(pname: Int): Vec4 = stak.vec4Address{GL30C.nglGetFloatv(pname, it) }

inline fun glGetVec2i(pname: Int): Vec2i = stak.vec2iAddress{GL30C.nglGetIntegerv(pname, it) }

inline fun glGetVec4i(pname: Int): Vec4i = stak.vec4iAddress{GL30C.nglGetIntegerv(pname, it) }

inline fun glGetVec4bool(pname: Int): Vec4bool = stak.vec4boolAddress{GL30C.nglGetIntegerv(pname, it) }


@JvmOverloads
fun checkError(location: String = "", throws: Boolean = true): Boolean {

    val error = GL11.glGetError()
    return when (error) {
        GL11.GL_NO_ERROR -> true
        else -> {
            val message = when (error) {
                GL11.GL_INVALID_ENUM -> "GL_INVALID_ENUM"
                GL11.GL_INVALID_VALUE -> "GL_INVALID_VALUE"
                GL11.GL_INVALID_OPERATION -> "GL_INVALID_OPERATION"
                GL30.GL_INVALID_FRAMEBUFFER_OPERATION -> "GL_INVALID_FRAMEBUFFER_OPERATION"
                GL11.GL_OUT_OF_MEMORY -> "GL_OUT_OF_MEMORY"
                GL11.GL_STACK_UNDERFLOW -> "GL_STACK_UNDERFLOW"
                GL11.GL_STACK_OVERFLOW -> "GL_STACK_OVERFLOW"
                else -> throw IllegalStateException()
            }
            if (throws)
                throw Exception("OpenGL Error ($message) at $location")
            else
                System.err.println(message)
            false
        }
    }
}

val VERSION = "0.4.6"

fun main(args: Array<String>) {


}

object gl11 : gl11i

object gl15 : gl11i, gl12i, gl13i, gl14i, gl15i

object gl20 :
        glEnum(),
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
        else -> throw Exception("unsupported")
    }