@file:Suppress("NOTHING_TO_INLINE")

package gln.texture

import gli_.gl
import glm_.BYTES
import glm_.buffer.adr
import glm_.buffer.pos
import glm_.vec2.Vec2i
import glm_.vec3.Vec3i
import glm_.vec4.Vec4
import glm_.vec4.Vec4i
import gln.buf
import gln.bufAd
import gln.get
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11.GL_TEXTURE_1D
import org.lwjgl.opengl.GL11.GL_TEXTURE_2D
import org.lwjgl.opengl.GL12.GL_TEXTURE_3D
import org.lwjgl.opengl.GL13
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL32.GL_TEXTURE_2D_MULTISAMPLE
import org.lwjgl.opengl.GL32.glTexImage2DMultisample
import org.lwjgl.opengl.GL42
import org.lwjgl.system.MemoryUtil
import org.lwjgl.system.MemoryUtil.NULL
import java.nio.ByteBuffer
import java.nio.IntBuffer
import kotlin.properties.Delegates
import kotlin.reflect.KMutableProperty0


var textureName: IntBuffer by Delegates.notNull()

operator fun Int.plus(enum: Enum<*>) = plus(enum.ordinal)

inline fun glGenTexture(texture: KMutableProperty0<Int>) = texture.set(GL11.glGenTextures())
inline fun glGenTexture() = GL11.glGenTextures()
inline fun glDeleteTexture(texture: Int) = GL11.glDeleteTextures(texture)

inline fun glBindTexture(target: Int, texture: Enum<*>) = GL11.glBindTexture(target, textureName[texture])
inline fun glBindTexture(target: Int, texture: IntBuffer) = GL11.glBindTexture(target, texture[0])

inline fun glBindTexture(target: gli_.gl.Target, texture: Int) = GL11.glBindTexture(target.i, texture)
inline fun glBindTexture(target: gli_.gl.Target, texture: Enum<*>) = GL11.glBindTexture(target.i, textureName[texture])
inline fun glBindTexture(target: gli_.gl.Target, texture: IntBuffer) = GL11.glBindTexture(target.i, texture[0])

inline fun glBindTexture(target: Int) = GL11.glBindTexture(target, 0)

inline fun glBindTexture2d(texture: Int = 0) = GL11.glBindTexture(GL_TEXTURE_2D, texture)
inline fun glBindTexture2d(texture: IntArray) = GL11.glBindTexture(GL_TEXTURE_2D, texture[0])
inline fun glBindTexture2d(texture: IntBuffer) = GL11.glBindTexture(GL_TEXTURE_2D, texture[0])
inline fun glBindTexture2dMs(texture: Int = 0) = GL11.glBindTexture(GL_TEXTURE_2D_MULTISAMPLE, texture)
inline fun glBindTexture2dMs(texture: IntArray) = GL11.glBindTexture(GL_TEXTURE_2D_MULTISAMPLE, texture[0])
inline fun glBindTexture2dMs(texture: IntBuffer) = GL11.glBindTexture(GL_TEXTURE_2D_MULTISAMPLE, texture[0])

inline fun glTex2dParameter(name: Int, param: Int) = GL11.glTexParameteri(GL_TEXTURE_2D, name, param)
inline fun glTex2dParameter(name: Int, param: Vec4i) = GL11.glTexParameteriv(GL_TEXTURE_2D, name, param.toIntArray())
inline fun glTex2dParameter(name: Int, param: IntArray) = GL11.glTexParameteriv(GL_TEXTURE_2D, name, param)

inline fun glTex2dParameter(name: Int, param: Float) = GL11.glTexParameterf(GL_TEXTURE_2D, name, param)
inline fun glTex2dParameter(name: Int, param: Vec4) = GL11.glTexParameterfv(GL_TEXTURE_2D, name, param.toFloatArray())
inline fun glTex2dParameter(name: Int, param: FloatArray) = GL11.glTexParameterfv(GL_TEXTURE_2D, name, param)

inline fun glTexParameter(target: gli_.gl.Target, name: Int, param: Int) = GL11.glTexParameteri(target.i, name, param)
inline fun glTexParameter(target: gli_.gl.Target, name: Int, param: Float) = GL11.glTexParameterf(target.i, name, param)
inline fun glTexParameter(target: gli_.gl.Target, name: Int, param: gli_.gl.Swizzles) {
    buf.putInt(0, param[0].i).putInt(Int.BYTES, param[1].i).putInt(Int.BYTES * 2, param[2].i).putInt(Int.BYTES * 3, param[3].i)
    GL11.nglTexParameteriv(target.i, name, bufAd)
}

inline fun glTexImage2D(level: Int, internalFormat: Int, width: Int, height: Int, format: Int, type: Int, pixels: ByteBuffer) {
    GL11.nglTexImage2D(GL11.GL_TEXTURE_2D, level, internalFormat, width, height, 0, format, type, pixels.adr + pixels.pos)
}
inline fun glTexImage2D(internalFormat: Int, width: Int, height: Int, format: Int, type: Int, pixels: ByteBuffer) = glTexImage2D(0, internalFormat, width, height, format, type, pixels)

inline fun glTexImage2D(level: Int, internalFormat: gl.InternalFormat, width: Int, height: Int, format: gl.ExternalFormat, type: gl.TypeFormat, pixels: ByteBuffer) {
    GL11.nglTexImage2D(GL11.GL_TEXTURE_2D, level, internalFormat.i, width, height, 0, format.i, type.i, pixels.adr + pixels.pos)
}
inline fun glTexImage2D(internalFormat: gl.InternalFormat, width: Int, height: Int, format: gl.ExternalFormat, type: gl.TypeFormat, pixels: ByteBuffer) = glTexImage2D(0, internalFormat, width, height, format, type, pixels)

// bufferless
inline fun glTexImage2D(level: Int, internalFormat: Int, width: Int, height: Int, format: Int, type: Int) = GL11.nglTexImage2D(GL11.GL_TEXTURE_2D, level, internalFormat, width, height, 0, format, type, NULL)

inline fun glTexImage2D(internalFormat: Int, width: Int, height: Int, format: Int, type: Int) = glTexImage2D(0, internalFormat, width, height, format, type)

inline fun glTexImage2D(level: Int, internalFormat: gl.InternalFormat, width: Int, height: Int, format: gl.ExternalFormat, type: gl.TypeFormat) = GL11.nglTexImage2D(GL11.GL_TEXTURE_2D, level, internalFormat.i, width, height, 0, format.i, type.i, NULL)
inline fun glTexImage2D(internalFormat: gl.InternalFormat, width: Int, height: Int, format: gl.ExternalFormat, type: gl.TypeFormat) = glTexImage2D(0, internalFormat, width, height, format, type)


// glm size
inline fun glTexImage2D(level: Int, internalFormat: Int, size: Vec2i, format: Int, type: Int, pixels: ByteBuffer) {
    GL11.nglTexImage2D(GL11.GL_TEXTURE_2D, level, internalFormat, size.x, size.y, 0, format, type, pixels.adr + pixels.pos)
}

inline fun glTexImage2D(internalFormat: Int, size: Vec2i, format: Int, type: Int, pixels: ByteBuffer) = glTexImage2D(0, internalFormat, size, format, type, pixels)

inline fun glTexImage2D(level: Int, internalFormat: gl.InternalFormat, size: Vec2i, format: gl.ExternalFormat, type: gl.TypeFormat, pixels: ByteBuffer) {
    GL11.nglTexImage2D(GL11.GL_TEXTURE_2D, level, internalFormat.i, size.x, size.y, 0, format.i, type.i, pixels.adr + pixels.pos)
}
inline fun glTexImage2D(internalFormat: gl.InternalFormat, size: Vec2i, format: gl.ExternalFormat, type: gl.TypeFormat, pixels: ByteBuffer) = glTexImage2D(0, internalFormat, size, format, type, pixels)

// bufferless
inline fun glTexImage2D(level: Int, internalFormat: Int, size: Vec2i, format: Int, type: Int) = GL11.nglTexImage2D(GL11.GL_TEXTURE_2D, level, internalFormat, size.x, size.y, 0, format, type, NULL)

inline fun glTexImage2D(internalFormat: Int, size: Vec2i, format: Int, type: Int) = glTexImage2D(0, internalFormat, size, format, type)

inline fun glTexImage2D(level: Int, internalFormat: gl.InternalFormat, size: Vec2i, format: gl.ExternalFormat, type: gl.TypeFormat) = GL11.nglTexImage2D(GL11.GL_TEXTURE_2D, level, internalFormat.i, size.x, size.y, 0, format.i, type.i, NULL)
inline fun glTexImage2D(internalFormat: gl.InternalFormat, size: Vec2i, format: gl.ExternalFormat, type: gl.TypeFormat) = glTexImage2D(0, internalFormat, size, format, type)


inline fun glTexImage2dMs(samples: Int, internalFormat: Int, width: Int, height: Int, fixedSampleLocations: Boolean = false) = glTexImage2DMultisample(GL_TEXTURE_2D_MULTISAMPLE, samples, internalFormat, width, height, fixedSampleLocations)
inline fun glTexImage2dMs(samples: Int, internalFormat: Int, size: Vec2i, fixedSampleLocations: Boolean = false) = glTexImage2DMultisample(GL_TEXTURE_2D_MULTISAMPLE, samples, internalFormat, size.x, size.y, fixedSampleLocations)


inline fun glTexStorage2D(target: Int, internalFormat: Int, size: Vec2i) = GL42.glTexStorage2D(target, 1, internalFormat, size.x, size.y)
//inline fun glTexStorage2D(target: gli_.gl.Target, internalFormat: Int, size: Vec2i) = GL42.glTexStorage2D(target.i, 1, internalFormat, size.x, size.y) TODO ?
inline fun glTexStorage2D(target: gli_.gl.Target, internalFormat: gli_.gl.InternalFormat, size: Vec3i) = glTexStorage2D(target, 1, internalFormat, size.x, size.y)

inline fun glTexStorage2D(target: gli_.gl.Target, levels: Int, internalFormat: gli_.gl.InternalFormat, size: Vec3i) = glTexStorage2D(target, levels, internalFormat, size.x, size.y)
inline fun glTexStorage2D(target: gli_.gl.Target, levels: Int, internalFormat: gli_.gl.InternalFormat, width: Int, height: Int) = GL42.glTexStorage2D(target.i, levels, internalFormat.i, width, height)

inline fun glCompressedTexSubImage2D(target: gli_.gl.Target, level: Int, extent: Vec3i, format: gli_.gl.InternalFormat, data: ByteBuffer) =
        glCompressedTexSubImage2D(target, level, 0, 0, extent.x, extent.y, format, data)

inline fun glCompressedTexSubImage2D(target: gli_.gl.Target, level: Int, xOffset: Int, yOffset: Int, extent: Vec3i, format: gli_.gl.InternalFormat, data: ByteBuffer) =
        glCompressedTexSubImage2D(target, level, xOffset, yOffset, extent.x, extent.y, format, data)

inline fun glCompressedTexSubImage2D(target: gli_.gl.Target, level: Int, xOffset: Int, yOffset: Int, width: Int, height: Int, format: gli_.gl.InternalFormat, data: ByteBuffer) =
        GL13.glCompressedTexSubImage2D(target.i, level, xOffset, yOffset, width, height, format.i, data)


inline fun glGenerateMipmap1D() = GL30.glGenerateMipmap(GL_TEXTURE_1D)
inline fun glGenerateMipmap2D() = GL30.glGenerateMipmap(GL_TEXTURE_2D)
inline fun glGenerateMipmap3D() = GL30.glGenerateMipmap(GL_TEXTURE_3D)