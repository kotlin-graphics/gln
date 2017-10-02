package gln.texture

import gli_.gli
import glm_.vec2.Vec2i
import glm_.vec3.Vec3i
import gln.buf
import gln.bufAd
import gln.get
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL13
import org.lwjgl.opengl.GL42
import java.nio.ByteBuffer
import java.nio.IntBuffer
import kotlin.properties.Delegates


var textureName: IntBuffer by Delegates.notNull()


inline fun glBindTexture(target: Int, texture: Enum<*>) = GL11.glBindTexture(target, textureName[texture])
inline fun glBindTexture(target: Int, texture: IntBuffer) = GL11.glBindTexture(target, texture[0])
inline fun glBindTexture(target: gli_.gl.Target, texture: IntBuffer) = GL11.glBindTexture(target.i, texture[0])
inline fun glBindTexture(target: Int) = GL11.glBindTexture(target, 0)

inline fun glTexParameteri(target: gli_.gl.Target, pName: Int, param: Int) = GL11.glTexParameteri(target.i, pName, param)
inline fun glTexParameteriv(target: gli_.gl.Target, pName: Int, param: gli_.gl.Swizzles) {
    buf.putInt(0, param[0].i).putInt(1, param[1].i).putInt(2, param[2].i).putInt(3, param[3].i)
    GL11.nglTexParameteriv(target.i, pName, bufAd)
}

inline fun glTexStorage2D(target: Int, internalFormat: Int, size: Vec2i) = GL42.glTexStorage2D(target, 1, internalFormat, size.x, size.y)
inline fun glTexStorage2D(target: gli_.gl.Target, internalFormat: Int, size: Vec2i) = GL42.glTexStorage2D(target.i, 1, internalFormat, size.x, size.y)
inline fun glTexStorage2D(target: gli_.gl.Target, internalFormat: gli_.gl.InternalFormat, size: Vec3i) = glTexStorage2D(target, 1, internalFormat, size.x, size.y)
inline fun glTexStorage2D(target: gli_.gl.Target, levels: Int, internalFormat: gli_.gl.InternalFormat, size: Vec3i) = glTexStorage2D(target, levels, internalFormat, size.x, size.y)
inline fun glTexStorage2D(target: gli_.gl.Target, levels: Int, internalFormat: gli_.gl.InternalFormat, width: Int, height: Int) = GL42.glTexStorage2D(target.i, levels, internalFormat.i, width, height)

inline fun glCompressedTexSubImage2D(target: gli_.gl.Target, level: Int, xOffset: Int, yOffset: Int, extent: Vec3i, format: gli_.gl.InternalFormat, data: ByteBuffer) =
        glCompressedTexSubImage2D(target, level, xOffset, yOffset, extent.x, extent.y, format, data)
inline fun glCompressedTexSubImage2D(target: gli_.gl.Target, level: Int, xOffset: Int, yOffset: Int, width: Int, height: Int, format: gli_.gl.InternalFormat, data: ByteBuffer) =
        GL13.glCompressedTexSubImage2D(target.i, level, xOffset, yOffset, width, height, format.i, data)