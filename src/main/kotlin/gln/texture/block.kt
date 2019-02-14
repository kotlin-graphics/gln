@file:Suppress("NOTHING_TO_INLINE")

package gln.texture

import gli_.gl
import gli_.gli
import glm_.BYTES
import glm_.vec2.Vec2i
import glm_.vec3.Vec3i
import gln.buf
import gln.bufAd
import kool.*
import org.lwjgl.opengl.*
import org.lwjgl.system.MemoryUtil.NULL
import java.nio.ByteBuffer
import java.nio.IntBuffer


inline fun withTexture1d(texture: Enum<*>, block: Texture1d.() -> Unit) = withTexture1d(textureName[texture], block)
inline fun withTexture1d(texture: IntBuffer, block: Texture1d.() -> Unit) = withTexture1d(texture[0], block)
inline fun withTexture1d(texture: Int, block: Texture1d.() -> Unit) {
    GL11C.glBindTexture(GL11C.GL_TEXTURE_1D, texture)
    Texture1d.block()
    GL11C.glBindTexture(GL11C.GL_TEXTURE_1D, 0)
}

inline fun withTexture2d(texture: Enum<*>, block: Texture2d.() -> Unit) = withTexture2d(textureName[texture], block)
inline fun withTexture2d(texture: IntBuffer, block: Texture2d.() -> Unit) = withTexture2d(texture[0], block)
inline fun withTexture2d(texture: Int, block: Texture2d.() -> Unit) {
    GL11C.glBindTexture(GL11C.GL_TEXTURE_2D, texture)
    Texture2d.block()
    GL11C.glBindTexture(GL11C.GL_TEXTURE_2D, 0)
}

inline fun withTexture(target: Int, texture: Enum<*>, block: Texture.() -> Unit) = withTexture(target, textureName[texture], block)
inline fun withTexture(target: Int, texture: IntBuffer, block: Texture.() -> Unit) = withTexture(target, texture[0], block)
inline fun withTexture(target: Int, texture: Int, block: Texture.() -> Unit) {
    Texture.target = target
    GL11C.glBindTexture(target, texture)
    Texture.block()
    GL11C.glBindTexture(target, 0)
}

inline fun withTexture1d(unit: Int, texture: Enum<*>, sampler: IntBuffer, block: Texture1d.() -> Unit) = withTexture1d(unit, textureName[texture], sampler, block)
inline fun withTexture1d(unit: Int, texture: IntBuffer, sampler: IntBuffer, block: Texture1d.() -> Unit) = withTexture1d(unit, texture[0], sampler, block)
inline fun withTexture1d(unit: Int, texture: Int, sampler: IntBuffer, block: Texture1d.() -> Unit) {
    GL13.glActiveTexture(GL13.GL_TEXTURE0 + unit)
    Texture1d.name = texture  // bind
    GL33.glBindSampler(unit, sampler[0])
    Texture1d.block()
    GL11C.glBindTexture(GL11C.GL_TEXTURE_1D, 0)
    GL33.glBindSampler(0, sampler[0])
}

// TODO sampler enum

inline fun withTexture2d(unit: Int, texture: Int, sampler: IntBuffer, block: Texture2d.() -> Unit) = withTexture2d(unit, texture, sampler[0], block)
inline fun withTexture2d(unit: Int, texture: Int, sampler: Int, block: Texture2d.() -> Unit) {
    GL13.glActiveTexture(GL13.GL_TEXTURE0 + unit)
    Texture2d.name = texture  // bind
    GL33.glBindSampler(unit, sampler)
    Texture2d.block()
    GL11C.glBindTexture(GL11C.GL_TEXTURE_2D, 0)
    GL33.glBindSampler(0, sampler)
}

inline fun withTexture2d(unit: Int, texture: Enum<*>, block: Texture2d.() -> Unit) = withTexture2d(unit, textureName[texture], block)
inline fun withTexture2d(unit: Int, texture: IntBuffer, block: Texture2d.() -> Unit) = withTexture2d(unit, texture[0], block)
inline fun withTexture2d(unit: Int, texture: Int, block: Texture2d.() -> Unit) {
    GL13.glActiveTexture(GL13.GL_TEXTURE0 + unit)
    Texture2d.name = texture  // bind
    Texture2d.block()
    GL11C.glBindTexture(GL11C.GL_TEXTURE_2D, 0)
}

inline fun withTexture(unit: Int, target: Int, texture: Enum<*>, sampler: Int, block: Texture.() -> Unit) = withTexture(unit, target, textureName[texture], sampler, block)
inline fun withTexture(unit: Int, target: Int, texture: Int, sampler: Int, block: Texture.() -> Unit) {
    Texture.target = target
    GL13.glActiveTexture(GL13.GL_TEXTURE0 + unit)
    Texture.name = texture  // bind
    GL33.glBindSampler(unit, sampler)
    Texture.block()
    GL11C.glBindTexture(target, 0)
    GL33.glBindSampler(0, sampler)
}

inline fun initTexture1d(texture: Enum<*>, block: Texture1d.() -> Unit) = textureName.put(texture.ordinal, initTexture1d(block))
inline fun initTexture1d(texture: IntBuffer, block: Texture1d.() -> Unit) = texture.put(0, initTexture1d(block))

inline fun initTexture1d(block: Texture1d.() -> Unit): Int {
    GL11C.nglGenTextures(1, bufAd)
    val name = buf.getInt(0)
    Texture1d.name = name   // bind
    Texture1d.block()
    return name
}

inline fun initTexture2d(texture: Enum<*>, block: Texture2d.() -> Unit) = textureName.put(texture.ordinal, initTexture2d(block))
inline fun initTexture2d(texture: IntBuffer, block: Texture2d.() -> Unit) = texture.put(0, initTexture2d(block))

inline fun initTexture2d(block: Texture2d.() -> Unit): Int {
    GL11C.nglGenTextures(1, bufAd)
    val name = buf.getInt(0)
    Texture2d.name = name   // bind
    Texture2d.block()
    return name
}

inline fun initTexture(target: Int, texture: Enum<*>, block: Texture.() -> Unit) = textureName.put(texture.ordinal, initTexture(target, block))
inline fun initTexture(target: Int, texture: IntBuffer, block: Texture.() -> Unit) = texture.put(0, initTexture(target, block))

inline fun initTexture(target: Int, block: Texture.() -> Unit): Int {
    GL11C.nglGenTextures(1, bufAd)
    val name = buf.getInt(0)
    Texture.target = target
    Texture.name = name   // bind
    Texture.block()
    return name
}


inline fun initTextures2d(block: Textures2d.() -> Unit) = initTextures2d(textureName, block)
inline fun initTextures2d(textures: IntBuffer, block: Textures2d.() -> Unit) {
    GL11C.nglGenTextures(textures.rem, textures.adr + textures.pos * Int.BYTES)
    Textures2d.names = textures
    Textures2d.block()
}

inline fun initTextures(target: Int, block: Textures.() -> Unit) = initTextures(target, textureName, block)
inline fun initTextures(target: Int, textures: IntBuffer, block: Textures.() -> Unit) {
    GL11C.nglGenTextures(textures.rem, textures.adr + textures.pos * Int.BYTES)
    Textures.target = target
    Textures.names = textures
    Textures.block()
}

object Texture {

    var target = 0
    var name = 0
        set(value) {
            GL11C.glBindTexture(target, value)
            field = name
        }

    inline fun image1d(internalFormat: Int, width: Int, format: Int, type: Int, pixels: ByteBuffer) = image1d(0, internalFormat, width, format, type, pixels)
    inline fun image1d(level: Int, internalFormat: Int, width: Int, format: Int, type: Int, pixels: ByteBuffer) =
            GL11C.nglTexImage1D(GL11C.GL_TEXTURE_1D, level, internalFormat, width, 0, format, type, pixels.adr + pixels.pos)

    var baseLevel = 0
        set(value) {
            GL11C.glTexParameteri(target, GL12.GL_TEXTURE_BASE_LEVEL, value)
            field = value
        }
    var maxLevel = 1_000
        set(value) {
            GL11C.glTexParameteri(target, GL12.GL_TEXTURE_MAX_LEVEL, value)
            field = value
        }
    var levels = 0..1_000
        set(value) {
            GL11C.glTexParameteri(target, GL12.GL_TEXTURE_BASE_LEVEL, value.first)
            GL11C.glTexParameteri(target, GL12.GL_TEXTURE_MAX_LEVEL, value.endInclusive)
            field = value
        }
}

object Texture1d {

    var name = 0
        set(value) {
            GL11C.glBindTexture(GL11C.GL_TEXTURE_1D, value)
            field = name
        }

    fun image(internalFormat: Int, width: Int, format: Int, type: Int, pixels: ByteBuffer) = image(0, internalFormat, width, format, type, pixels)
    fun image(level: Int, internalFormat: Int, width: Int, format: Int, type: Int, pixels: ByteBuffer) =
            GL11C.nglTexImage1D(GL11C.GL_TEXTURE_1D, level, internalFormat, width, 0, format, type, pixels.adr + pixels.pos)

    var baseLevel = 0
        set(value) {
            GL11C.glTexParameteri(GL11C.GL_TEXTURE_1D, GL12.GL_TEXTURE_BASE_LEVEL, value)
            field = value
        }
    var maxLevel = 1_000
        set(value) {
            GL11C.glTexParameteri(GL11C.GL_TEXTURE_1D, GL12.GL_TEXTURE_MAX_LEVEL, value)
            field = value
        }
    var levels = 0..1_000
        set(value) {
            GL11C.glTexParameteri(GL11C.GL_TEXTURE_1D, GL12.GL_TEXTURE_BASE_LEVEL, value.first)
            GL11C.glTexParameteri(GL11C.GL_TEXTURE_1D, GL12.GL_TEXTURE_MAX_LEVEL, value.endInclusive)
            field = value
        }
}

object Texture2d {

    var name = 0
        set(value) {
            GL11C.glBindTexture(GL11C.GL_TEXTURE_2D, value)
            field = name
        }

    inline fun gli_.Texture.image() {
        val format = gli.gl.translate(format, swizzles)
        for (i in 0 until levels())
            image(i, format.internal, extent(i), format.external, format.type, data(0, 0, i))
    }

    inline fun image(internalFormat: Int, width: Int, height: Int, format: Int, type: Int) = image(0, internalFormat, width, height, format, type)
    inline fun image(level: Int, internalFormat: Int, width: Int, height: Int, format: Int, type: Int) =
            GL11C.nglTexImage2D(GL11C.GL_TEXTURE_2D, level, internalFormat, width, height, 0, format, type, NULL)

    inline fun image(internalFormat: Int, width: Int, height: Int, format: Int, type: Int, pixels: ByteBuffer) = image(0, internalFormat, width, height, format, type, pixels)
    inline fun image(level: Int, internalFormat: Int, width: Int, height: Int, format: Int, type: Int, pixels: ByteBuffer) =
            GL11C.nglTexImage2D(GL11C.GL_TEXTURE_2D, level, internalFormat, width, height, 0, format, type, pixels.adr + pixels.pos)

    // TODO size for others

    inline fun image(internalFormat: Int, size: Vec2i, format: Int, type: Int) = image(0, internalFormat, size, format, type)
    inline fun image(level: Int, internalFormat: Int, size: Vec2i, format: Int, type: Int) = GL11C.nglTexImage2D(GL11C.GL_TEXTURE_2D, level, internalFormat, size.x, size.y, 0, format, type, NULL)

    inline fun image(internalFormat: Int, size: Vec2i, format: Int, type: Int, pixels: ByteBuffer) = image(0, internalFormat, size, format, type, pixels)
    inline fun image(level: Int, internalFormat: Int, size: Vec2i, format: Int, type: Int, pixels: ByteBuffer) =
            GL11C.nglTexImage2D(GL11C.GL_TEXTURE_2D, level, internalFormat, size.x, size.y, 0, format, type, pixels.adr + pixels.pos)

    inline fun image(internalFormat: gli_.gl.InternalFormat, size: Vec3i, format: gli_.gl.ExternalFormat, type: gli_.gl.TypeFormat, pixels: ByteBuffer) =
            image(0, internalFormat, size, format, type, pixels)

    inline fun image(level: Int, internalFormat: gli_.gl.InternalFormat, size: Vec3i, format: gli_.gl.ExternalFormat, type: gli_.gl.TypeFormat, pixels: ByteBuffer) =
            GL11C.nglTexImage2D(GL11C.GL_TEXTURE_2D, level, internalFormat.i, size.x, size.y, 0, format.i, type.i, pixels.adr + pixels.pos)


    inline fun storage(internalFormat: Int, size: Vec2i) = storage(1, internalFormat, size)
    inline fun storage(levels: Int, internalFormat: Int, size: Vec2i) = GL42.glTexStorage2D(GL11C.GL_TEXTURE_2D, levels, internalFormat, size.x, size.y)
    inline fun storage(levels: Int, internalFormat: gl.InternalFormat, size: Vec3i) = GL42.glTexStorage2D(GL11C.GL_TEXTURE_2D, levels, internalFormat.i, size.x, size.y)

    inline fun compressedSubImage(level: Int, size: Vec3i, format: gl.InternalFormat, data: ByteBuffer) =
            compressedSubImage(level, 0, 0, size.x, size.y, format.i, data)

    inline fun compressedSubImage(level: Int, xOffset: Int, yOffset: Int, width: Int, height: Int, format: Int, data: ByteBuffer) =
            GL13.nglCompressedTexSubImage2D(GL11C.GL_TEXTURE_2D, level, xOffset, yOffset, width, height, format, data.rem, data.adr + data.pos)

    var baseLevel: Int
        get() = GL11C.glGetTexParameteri(GL11C.GL_TEXTURE_2D, GL12.GL_TEXTURE_BASE_LEVEL)
        set(value) = GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL12.GL_TEXTURE_BASE_LEVEL, value)
    var maxLevel: Int
        get() = GL11C.glGetTexParameteri(GL11C.GL_TEXTURE_2D, GL12.GL_TEXTURE_MAX_LEVEL)
        set(value) = GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL12.GL_TEXTURE_MAX_LEVEL, value)

    var levels: IntRange
        get() = GL11C.glGetTexParameteri(GL11C.GL_TEXTURE_2D, GL12.GL_TEXTURE_BASE_LEVEL)..GL11C.glGetTexParameteri(GL11C.GL_TEXTURE_2D, GL12.GL_TEXTURE_MAX_LEVEL)
        set(value) {
            GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL12.GL_TEXTURE_BASE_LEVEL, value.first)
            GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL12.GL_TEXTURE_MAX_LEVEL, value.endInclusive)
        }

    inline fun levels(base: Int = 0, max: Int = 1_000) {
        GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL12.GL_TEXTURE_BASE_LEVEL, base)
        GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL12.GL_TEXTURE_MAX_LEVEL, max)
    }

    var magFilter: TexFilter
        get() = TexFilter(GL11C.glGetTexParameteri(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_MAG_FILTER))
        set(value) = GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_MAG_FILTER, value.i)

    var minFilter: TexFilter
        get() = TexFilter(GL11C.glGetTexParameteri(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_MIN_FILTER))
        set(value) = GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_MIN_FILTER, value.i)

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
        get() = GL11C.glGetTexParameterf(GL11C.GL_TEXTURE_2D, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT)
        set(value) = GL11C.glTexParameterf(GL11C.GL_TEXTURE_2D, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, value)

    var wrapS: TexWrap
        get() = TexWrap(GL11C.glGetTexParameteri(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_WRAP_S))
        set(value) = GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_WRAP_S, value.i)
    var wrapT: TexWrap
        get() = TexWrap(GL11C.glGetTexParameteri(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_WRAP_T))
        set(value) = GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_WRAP_T, value.i)
    var wrapR: TexWrap
        get() = TexWrap(GL11C.glGetTexParameteri(GL11C.GL_TEXTURE_2D, GL12C.GL_TEXTURE_WRAP_R))
        set(value) = GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL12C.GL_TEXTURE_WRAP_R, value.i)

    inline fun wrapST(s: TexWrap, t: TexWrap) {
        wrapS = s
        wrapT = t
    }

    inline fun wrapSTR(s: TexWrap, t: TexWrap, r: TexWrap) {
        wrapS = s
        wrapT = t
        wrapR = r
    }

    var compareFunc: CompareFunc
        get() = CompareFunc(GL11C.glGetTexParameteri(GL11C.GL_TEXTURE_2D, GL14.GL_TEXTURE_COMPARE_FUNC))
        set(value) = GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL14.GL_TEXTURE_COMPARE_FUNC, value.i)
    var compareMode: CompareMode
        get() = CompareMode(GL11C.glGetTexParameteri(GL11C.GL_TEXTURE_2D, GL14.GL_TEXTURE_COMPARE_MODE))
        set(value) = GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL14.GL_TEXTURE_COMPARE_MODE, value.i)

    inline fun compare(func: CompareFunc, mode: CompareMode) {
        compareFunc = func
        compareMode = mode
    }

    //    var swizzles: gl.Swizzles TODO
//        get() = stak {
//            val buf = it.callocInt(4)
//            GL11C.glGetTexParameteriv(GL11C.GL_TEXTURE_2D, GL33.GL_TEXTURE_SWIZZLE_RGBA, buf)
//            gl.Swizzles()
//        }
//        set(value) {
//            buf.putInt(0, value.r.i).putInt(Int.BYTES, value.g.i).putInt(Int.BYTES * 2, value.b.i).putInt(Int.BYTES * 3, value.a.i)
//            GL11C.nglTexParameteriv(GL11C.GL_TEXTURE_2D, GL33.GL_TEXTURE_SWIZZLE_RGBA, bufAd)
//        }

    inline fun swizzle(r: Swizzle, g: Swizzle, b: Swizzle, a: Swizzle) {
        GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL33.GL_TEXTURE_SWIZZLE_R, r.i)
        GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL33.GL_TEXTURE_SWIZZLE_G, g.i)
        GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL33.GL_TEXTURE_SWIZZLE_B, b.i)
        GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL33.GL_TEXTURE_SWIZZLE_A, a.i)
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

inline class CompareMode(val i: Int) {
    companion object {
        val COMPARE_REF_TO_TEXTURE = CompareMode(GL30C.GL_COMPARE_REF_TO_TEXTURE)
        val NONE = CompareMode(GL11C.GL_NONE)
    }
}

inline class CompareFunc(val i: Int) {
    companion object {
        val LEQUAL = CompareFunc(GL11C.GL_LEQUAL)
        val GEQUAL = CompareFunc(GL11C.GL_GEQUAL)
        val LESS = CompareFunc(GL11C.GL_LESS)
        val GREATER = CompareFunc(GL11C.GL_GREATER)
        val EQUAL = CompareFunc(GL11C.GL_EQUAL)
        val NOTEQUAL = CompareFunc(GL11C.GL_NOTEQUAL)
        val ALWAYS = CompareFunc(GL11C.GL_ALWAYS)
        val NEVER = CompareFunc(GL11C.GL_NEVER)
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

object Textures {

    var target = 0
    lateinit var names: IntBuffer

    inline fun image1d(level: Int, internalFormat: Int, width: Int, format: Int, type: Int, pixels: ByteBuffer) =
            GL11C.nglTexImage1D(target, level, internalFormat, width, 0, format, type, pixels.adr + pixels.pos)

    inline fun image2d(level: Int, internalFormat: Int, width: Int, height: Int, format: Int, type: Int, pixels: ByteBuffer) =
            GL11C.nglTexImage2D(target, level, internalFormat, width, height, 0, format, type, pixels.adr + pixels.pos)

    inline fun at1d(index: Enum<*>, block: Texture1d.() -> Unit) = at1d(index.ordinal, block)
    inline fun at1d(index: Int, block: Texture1d.() -> Unit) {
        Texture1d.name = names[index] // bind
        Texture1d.block()
    }

    inline fun at2d(index: Enum<*>, block: Texture2d.() -> Unit) = at2d(index.ordinal, block)
    inline fun at2d(index: Int, block: Texture2d.() -> Unit) {
        Texture2d.name = names[index] // bind
        Texture2d.block()
    }
}

object Textures2d {

    lateinit var names: IntBuffer

    inline fun image2d(level: Int, internalFormat: Int, width: Int, height: Int, format: Int, type: Int, pixels: ByteBuffer) =
            GL11C.nglTexImage2D(GL11C.GL_TEXTURE_2D, level, internalFormat, width, height, 0, format, type, pixels.adr + pixels.pos)

    inline fun at(index: Enum<*>, block: Texture2d.() -> Unit) = at(index.ordinal, block)
    inline fun at(index: Int, block: Texture2d.() -> Unit) {
        Texture2d.name = names[index] // bind
        Texture2d.block()
    }
}