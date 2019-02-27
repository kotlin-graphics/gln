@file:Suppress("NOTHING_TO_INLINE")

package gln.texture

import gli_.gl
import glm_.vec2.Vec2i
import glm_.vec3.Vec3i
import gln.CompareFunction
import gln.DSA
import gln.TextureCompareMode
import gln.TextureTarget
import kool.adr
import kool.pos
import kool.rem
import org.lwjgl.opengl.*
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.system.MemoryUtil.memAddress
import java.nio.ByteBuffer
import java.nio.IntBuffer


object GlTextureDsl {

    var target: TextureTarget = TextureTarget._1D
    var name = 0

    inline fun image(internalFormat: gl.InternalFormat, width: Int, format: gl.ExternalFormat, type: gl.TypeFormat, pixels: ByteBuffer) =
            image(0, internalFormat, width, format, type, pixels)

    inline fun image(level: Int, internalFormat: gl.InternalFormat, width: Int, format: gl.ExternalFormat, type: gl.TypeFormat, pixels: ByteBuffer) =
            GL11C.nglTexImage1D(target.i, level, internalFormat.i, width, 0, format.i, type.i, memAddress(pixels))

    inline fun image(internalFormat: gl.InternalFormat, size: Vec2i, format: gl.ExternalFormat, type: gli_.gl.TypeFormat, pixels: ByteBuffer) =
            image(0, internalFormat, size, format, type, pixels)

    inline fun image(level: Int, internalFormat: gl.InternalFormat, size: Vec2i, format: gl.ExternalFormat, type: gl.TypeFormat, pixels: ByteBuffer) =
            GL11C.nglTexImage2D(target.i, level, internalFormat.i, size.x, size.y, 0, format.i, type.i, memAddress(pixels))

    inline fun image(internalFormat: gl.InternalFormat, size: Vec3i, format: gl.ExternalFormat, type: gli_.gl.TypeFormat, pixels: ByteBuffer) =
            image(0, internalFormat, size, format, type, pixels)

    inline fun image(level: Int, internalFormat: gl.InternalFormat, size: Vec3i, format: gl.ExternalFormat, type: gl.TypeFormat, pixels: ByteBuffer) =
            GL12C.nglTexImage3D(target.i, level, internalFormat.i, size.x, size.y, size.z, 0, format.i, type.i, memAddress(pixels))

    // null

    inline fun image(internalFormat: gl.InternalFormat, width: Int, format: gl.ExternalFormat, type: gl.TypeFormat) =
            image(0, internalFormat, width, format, type)

    inline fun image(level: Int, internalFormat: gl.InternalFormat, width: Int, format: gl.ExternalFormat, type: gl.TypeFormat) =
            GL11C.nglTexImage1D(target.i, level, internalFormat.i, width, 0, format.i, type.i, NULL)

    inline fun image(internalFormat: gl.InternalFormat, size: Vec2i, format: gl.ExternalFormat, type: gli_.gl.TypeFormat) =
            image(0, internalFormat, size, format, type)

    inline fun image(level: Int, internalFormat: gl.InternalFormat, size: Vec2i, format: gl.ExternalFormat, type: gl.TypeFormat) =
            GL11C.nglTexImage2D(target.i, level, internalFormat.i, size.x, size.y, 0, format.i, type.i, NULL)

    inline fun image(internalFormat: gl.InternalFormat, size: Vec3i, format: gl.ExternalFormat, type: gli_.gl.TypeFormat) =
            image(0, internalFormat, size, format, type)

    inline fun image(level: Int, internalFormat: gl.InternalFormat, size: Vec3i, format: gl.ExternalFormat, type: gl.TypeFormat) =
            GL12C.nglTexImage3D(target.i, level, internalFormat.i, size.x, size.y, size.z, 0, format.i, type.i, NULL)

    var baseLevel: Int
        get() = GL11C.glGetTexParameteri(target.i, GL12.GL_TEXTURE_BASE_LEVEL)
        set(value) = GL11C.glTexParameteri(target.i, GL12.GL_TEXTURE_BASE_LEVEL, value)
    var maxLevel: Int
        get() = GL11C.glGetTexParameteri(target.i, GL12.GL_TEXTURE_MAX_LEVEL)
        set(value) = GL11C.glTexParameteri(target.i, GL12.GL_TEXTURE_MAX_LEVEL, value)

    var levels: IntRange
        get() = GL11C.glGetTexParameteri(target.i, GL12.GL_TEXTURE_BASE_LEVEL)..GL11C.glGetTexParameteri(target.i, GL12.GL_TEXTURE_MAX_LEVEL)
        set(value) = when {
            DSA -> {
                GL45C.glTextureParameteri(name, GL12.GL_TEXTURE_BASE_LEVEL, value.first)
                GL45C.glTextureParameteri(name, GL12.GL_TEXTURE_MAX_LEVEL, value.endInclusive)
            }
            else -> {
                GL11C.glTexParameteri(target.i, GL12.GL_TEXTURE_BASE_LEVEL, value.first)
                GL11C.glTexParameteri(target.i, GL12.GL_TEXTURE_MAX_LEVEL, value.endInclusive)
            }
        }

//    inline fun gli_.Texture.image() { TODO
//        val format = gli.gl.translate(format, swizzles)
//        for (i in 0 until levels())
//            image(i, format.internal, extent(i), format.external, format.type, data(0, 0, i))
//    }

    inline fun storage(internalFormat: gl.InternalFormat, size: Int) = storage(1, internalFormat, size)
    inline fun storage(levels: Int, internalFormat: gl.InternalFormat, size: Int) = GL42.glTexStorage1D(target.i, levels, internalFormat.i, size)

    inline fun storage(internalFormat: gl.InternalFormat, size: Vec2i) = storage(1, internalFormat, size)
    inline fun storage(levels: Int, internalFormat: gl.InternalFormat, size: Vec2i) = GL42.glTexStorage2D(target.i, levels, internalFormat.i, size.x, size.y)

    inline fun storage(internalFormat: gl.InternalFormat, size: Vec3i) = storage(1, internalFormat, size)
    inline fun storage(levels: Int, internalFormat: gl.InternalFormat, size: Vec3i) = GL42.glTexStorage3D(target.i, levels, internalFormat.i, size.x, size.y, size.z)

    inline fun compressedSubImage(level: Int, size: Vec3i, format: gl.InternalFormat, data: ByteBuffer) =
            compressedSubImage(level, 0, 0, size.x, size.y, format.i, data)

    inline fun compressedSubImage(level: Int, xOffset: Int, yOffset: Int, width: Int, height: Int, format: Int, data: ByteBuffer) =
            GL13.nglCompressedTexSubImage2D(target.i, level, xOffset, yOffset, width, height, format, data.rem, data.adr + data.pos)

    inline fun levels(base: Int = 0, max: Int = 1_000) {
        GL11C.glTexParameteri(target.i, GL12.GL_TEXTURE_BASE_LEVEL, base)
        GL11C.glTexParameteri(target.i, GL12.GL_TEXTURE_MAX_LEVEL, max)
    }

    var magFilter: TexFilter
        get() = TexFilter(GL11C.glGetTexParameteri(target.i, GL11C.GL_TEXTURE_MAG_FILTER))
        set(value) = GL11C.glTexParameteri(target.i, GL11C.GL_TEXTURE_MAG_FILTER, value.i)

    var minFilter: TexFilter
        get() = TexFilter(GL11C.glGetTexParameteri(target.i, GL11C.GL_TEXTURE_MIN_FILTER))
        set(value) = GL11C.glTexParameteri(target.i, GL11C.GL_TEXTURE_MIN_FILTER, value.i)

    var minMagFilter: TexFilter
        get() = throw Exception("Invalid")
        set(value) {
            minFilter = value
            magFilter = value
        }

    inline fun filter(min: TexFilter, mag: TexFilter) {
        minFilter = min
        magFilter = mag
    }

    var maxAnisotropy: Float
        get() = GL11C.glGetTexParameterf(target.i, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT)
        set(value) = GL11C.glTexParameterf(target.i, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, value)

    var wrapS: TexWrap
        get() = TexWrap(GL11C.glGetTexParameteri(target.i, GL11C.GL_TEXTURE_WRAP_S))
        set(value) = GL11C.glTexParameteri(target.i, GL11C.GL_TEXTURE_WRAP_S, value.i)
    var wrapT: TexWrap
        get() = TexWrap(GL11C.glGetTexParameteri(target.i, GL11C.GL_TEXTURE_WRAP_T))
        set(value) = GL11C.glTexParameteri(target.i, GL11C.GL_TEXTURE_WRAP_T, value.i)
    var wrapR: TexWrap
        get() = TexWrap(GL11C.glGetTexParameteri(target.i, GL12C.GL_TEXTURE_WRAP_R))
        set(value) = GL11C.glTexParameteri(target.i, GL12C.GL_TEXTURE_WRAP_R, value.i)

    inline fun wrapST(s: TexWrap, t: TexWrap) {
        wrapS = s
        wrapT = t
    }

    inline fun wrapSTR(s: TexWrap, t: TexWrap, r: TexWrap) {
        wrapS = s
        wrapT = t
        wrapR = r
    }

    var compareFunc: CompareFunction
        get() = CompareFunction(GL11C.glGetTexParameteri(target.i, GL14.GL_TEXTURE_COMPARE_FUNC))
        set(value) = GL11C.glTexParameteri(target.i, GL14.GL_TEXTURE_COMPARE_FUNC, value.i)
    var compareMode: TextureCompareMode
        get() = TextureCompareMode(GL11C.glGetTexParameteri(target.i, GL14.GL_TEXTURE_COMPARE_MODE))
        set(value) = GL11C.glTexParameteri(target.i, GL14.GL_TEXTURE_COMPARE_MODE, value.i)

    inline fun compare(func: CompareFunction, mode: TextureCompareMode) {
        compareFunc = func
        compareMode = mode
    }

    //    var swizzles: gl.Swizzles TODO
//        get() = stak {
//            val buf = it.callocInt(4)
//            GL11C.glGetTexParameteriv(target.i, GL33.GL_TEXTURE_SWIZZLE_RGBA, buf)
//            gl.Swizzles()
//        }
//        set(value) {
//            buf.putInt(0, value.r.i).putInt(Int.BYTES, value.g.i).putInt(Int.BYTES * 2, value.b.i).putInt(Int.BYTES * 3, value.a.i)
//            GL11C.nglTexParameteriv(target.i, GL33.GL_TEXTURE_SWIZZLE_RGBA, bufAd)
//        }

    var swizzles: gl.Swizzles
        get() = throw Exception("Invalid")
        set(value) {
            GL11C.glTexParameteri(target.i, GL33.GL_TEXTURE_SWIZZLE_R, value.r.i)
            GL11C.glTexParameteri(target.i, GL33.GL_TEXTURE_SWIZZLE_G, value.g.i)
            GL11C.glTexParameteri(target.i, GL33.GL_TEXTURE_SWIZZLE_B, value.b.i)
            GL11C.glTexParameteri(target.i, GL33.GL_TEXTURE_SWIZZLE_A, value.a.i)
        }

    inline fun swizzles(r: Swizzle, g: Swizzle, b: Swizzle, a: Swizzle) {
        GL11C.glTexParameteri(target.i, GL33.GL_TEXTURE_SWIZZLE_R, r.i)
        GL11C.glTexParameteri(target.i, GL33.GL_TEXTURE_SWIZZLE_G, g.i)
        GL11C.glTexParameteri(target.i, GL33.GL_TEXTURE_SWIZZLE_B, b.i)
        GL11C.glTexParameteri(target.i, GL33.GL_TEXTURE_SWIZZLE_A, a.i)
    }
}

inline class TexFilter(val i: Int) {
    companion object {
        val NEAREST = TexFilter(GL11C.GL_NEAREST)
        val LINEAR = TexFilter(GL11C.GL_LINEAR)
        val NEAREST_MIPMAP_NEAREST = TexFilter(GL11C.GL_NEAREST_MIPMAP_NEAREST)
        val LINEAR_MIPMAP_NEAREST = TexFilter(GL11C.GL_LINEAR_MIPMAP_NEAREST)
        val NEAREST_MIPMAP_LINEAR = TexFilter(GL11C.GL_NEAREST_MIPMAP_LINEAR)
        val LINEAR_MIPMAP_LINEAR = TexFilter(GL11C.GL_LINEAR_MIPMAP_LINEAR)
    }
}

inline class TexWrap(val i: Int) {
    companion object {
        val CLAMP_TO_EDGE = TexWrap(GL12C.GL_CLAMP_TO_EDGE)
        val MIRRORED_REPEAT = TexWrap(GL14C.GL_MIRRORED_REPEAT)
        val REPEAT = TexWrap(GL11C.GL_REPEAT)
    }
}

inline class Swizzle(val i: Int) {
    companion object {
        val RED = Swizzle(GL11C.GL_RED)
        val GREEN = Swizzle(GL11C.GL_GREEN)
        val BLUE = Swizzle(GL11C.GL_BLUE)
        val ALPHA = Swizzle(GL11C.GL_ALPHA)
    }
}

object GlTexturesDsl {

    //    var target = TextureTarget._1D
    lateinit var names: IntBuffer

//    inline fun image1d(level: Int, internalFormat: Int, width: Int, format: Int, type: Int, pixels: ByteBuffer) =
//            GL11C.nglTexImage1D(target.i, level, internalFormat, width, 0, format, type, pixels.adr + pixels.pos)
//
//    inline fun image2d(level: Int, internalFormat: Int, width: Int, height: Int, format: Int, type: Int, pixels: ByteBuffer) =
//            GL11C.nglTexImage2D(target.i, level, internalFormat, width, height, 0, format, type, pixels.adr + pixels.pos)

    inline operator fun Enum<*>.invoke(block: GlTextureDsl.() -> Unit) {
//        GlTextureDsl.ta
        GlTextureDsl.block()
    }

//    inline fun at1d(index: Enum<*>, block: Texture1d.() -> Unit) = at1d(index.ordinal, block)
//    inline fun at1d(index: Int, block: Texture1d.() -> Unit) {
//        Texture1d.name = names[index] // bind
//        Texture1d.block()
//    }
//
//    inline fun at2d(index: Enum<*>, block: Texture2d.() -> Unit) = at2d(index.ordinal, block)
//    inline fun at2d(index: Int, block: Texture2d.() -> Unit) {
//        Texture2d.name = names[index] // bind
//        Texture2d.block()
//    }
}
