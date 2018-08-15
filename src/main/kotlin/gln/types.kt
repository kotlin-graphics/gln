package gln

import ab.appBuffer
import gli_.gl.InternalFormat
import glm_.BYTES
import glm_.L
import glm_.bool
import glm_.buffer.adr
import glm_.buffer.intBufferBig
import glm_.buffer.rem
import glm_.buffer.remSize
import glm_.vec2.Vec2i
import glm_.vec3.Vec3i
import glm_.vec4.Vec4
import glm_.vec4.Vec4i
import gln.TextureTarget.*
import org.lwjgl.opengl.*
import org.lwjgl.system.APIUtil
import org.lwjgl.system.MemoryUtil
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.system.MemoryUtil.memByteBuffer
import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.IntBuffer


inline class GLframebuffer(val i: Int)
inline class GLprogramPipeline(val i: Int)
inline class GLrenderbuffer(val i: Int)
inline class GLsampler(val i: Int)
inline class GLvertexArray(val i: Int)

inline class GLtextures(val i: IntBuffer) {

    inline val rem: Int
        get() = i.rem

    inline val adr: Long
        get() = i.adr

    companion object {
        fun gen(count: Int): GLtextures = gl20.genTextures(count)
    }

    fun delete() = gl20.deleteTextures(this)
}



inline class Ptr(val L: Long) {
    val valid: Boolean
        get() = L != NULL

    fun notNull(): Boolean = L != NULL
}


typealias GLbitfield = Int

