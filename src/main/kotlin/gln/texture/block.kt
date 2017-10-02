package gln.texture

import gli_.gl
import glm_.BYTES
import glm_.vec2.Vec2i
import gln.buf
import gln.bufAd
import gln.get
import org.lwjgl.opengl.*
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.system.MemoryUtil.memAddress0
import java.nio.ByteBuffer
import java.nio.IntBuffer


inline fun withTexture1d(texture: Enum<*>, block: Texture1d.() -> Unit) = withTexture1d(textureName[texture], block)
inline fun withTexture1d(texture: IntBuffer, block: Texture1d.() -> Unit) = withTexture1d(texture[0], block)
inline fun withTexture1d(texture: Int, block: Texture1d.() -> Unit) {
    GL11.glBindTexture(GL11.GL_TEXTURE_1D, texture)
    Texture1d.block()
    GL11.glBindTexture(GL11.GL_TEXTURE_1D, 0)
}

inline fun withTexture2d(texture: Enum<*>, block: Texture2d.() -> Unit) = withTexture2d(textureName[texture], block)
inline fun withTexture2d(texture: IntBuffer, block: Texture2d.() -> Unit) = withTexture2d(texture[0], block)
inline fun withTexture2d(texture: Int, block: Texture2d.() -> Unit) {
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture)
    Texture2d.block()
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0)
}

inline fun withTexture(target: Int, texture: Enum<*>, block: Texture.() -> Unit) = withTexture(target, textureName[texture], block)
inline fun withTexture(target: Int, texture: IntBuffer, block: Texture.() -> Unit) = withTexture(target, texture[0], block)
inline fun withTexture(target: Int, texture: Int, block: Texture.() -> Unit) {
    Texture.target = target
    GL11.glBindTexture(target, texture)
    Texture.block()
    GL11.glBindTexture(target, 0)
}

inline fun withTexture1d(unit: Int, texture: Enum<*>, sampler: IntBuffer, block: Texture1d.() -> Unit) = withTexture1d(unit, textureName[texture], sampler, block)
inline fun withTexture1d(unit: Int, texture: IntBuffer, sampler: IntBuffer, block: Texture1d.() -> Unit) = withTexture1d(unit, texture[0], sampler, block)
inline fun withTexture1d(unit: Int, texture: Int, sampler: IntBuffer, block: Texture1d.() -> Unit) {
    GL13.glActiveTexture(GL13.GL_TEXTURE0 + unit)
    Texture1d.name = texture  // bind
    GL33.glBindSampler(unit, sampler[0])
    Texture1d.block()
    GL11.glBindTexture(GL11.GL_TEXTURE_1D, 0)
    GL33.glBindSampler(0, sampler[0])
}

// TODO sampler enum

inline fun withTexture2d(unit: Int, texture: Int, sampler: IntBuffer, block: Texture2d.() -> Unit) = withTexture2d(unit, texture, sampler[0], block)
inline fun withTexture2d(unit: Int, texture: Int, sampler: Int, block: Texture2d.() -> Unit) {
    GL13.glActiveTexture(GL13.GL_TEXTURE0 + unit)
    Texture2d.name = texture  // bind
    GL33.glBindSampler(unit, sampler)
    Texture2d.block()
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0)
    GL33.glBindSampler(0, sampler)
}

inline fun withTexture2d(unit: Int, texture: Enum<*>, block: Texture2d.() -> Unit) = withTexture2d(unit, textureName[texture], block)
inline fun withTexture2d(unit: Int, texture: IntBuffer, block: Texture2d.() -> Unit) = withTexture2d(unit, texture[0], block)
inline fun withTexture2d(unit: Int, texture: Int, block: Texture2d.() -> Unit) {
    GL13.glActiveTexture(GL13.GL_TEXTURE0 + unit)
    Texture2d.name = texture  // bind
    Texture2d.block()
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0)
}

inline fun withTexture(unit: Int, target: Int, texture: Enum<*>, sampler: Int, block: Texture.() -> Unit) = withTexture(unit, target, textureName[texture], sampler, block)
inline fun withTexture(unit: Int, target: Int, texture: Int, sampler: Int, block: Texture.() -> Unit) {
    Texture.target = target
    GL13.glActiveTexture(GL13.GL_TEXTURE0 + unit)
    Texture.name = texture  // bind
    GL33.glBindSampler(unit, sampler)
    Texture.block()
    GL11.glBindTexture(target, 0)
    GL33.glBindSampler(0, sampler)
}

inline fun initTexture1d(texture: Enum<*>, block: Texture1d.() -> Unit) = textureName.put(texture.ordinal, initTexture1d(block))
inline fun initTexture1d(texture: IntBuffer, block: Texture1d.() -> Unit) = texture.put(0, initTexture1d(block))

inline fun initTexture1d(block: Texture1d.() -> Unit): Int {
    GL11.nglGenTextures(1, bufAd)
    val name = buf.getInt(0)
    Texture1d.name = name   // bind
    Texture1d.block()
    return name
}

inline fun initTexture2d(texture: Enum<*>, block: Texture2d.() -> Unit) = textureName.put(texture.ordinal, initTexture2d(block))
inline fun initTexture2d(texture: IntBuffer, block: Texture2d.() -> Unit) = texture.put(0, initTexture2d(block))

inline fun initTexture2d(block: Texture2d.() -> Unit): Int {
    GL11.nglGenTextures(1, bufAd)
    val name = buf.getInt(0)
    Texture2d.name = name   // bind
    Texture2d.block()
    return name
}

inline fun initTexture(target: Int, texture: Enum<*>, block: Texture.() -> Unit) = textureName.put(texture.ordinal, initTexture(target, block))
inline fun initTexture(target: Int, texture: IntBuffer, block: Texture.() -> Unit) = texture.put(0, initTexture(target, block))

inline fun initTexture(target: Int, block: Texture.() -> Unit): Int {
    GL11.nglGenTextures(1, bufAd)
    val name = buf.getInt(0)
    Texture.target = target
    Texture.name = name   // bind
    Texture.block()
    return name
}


inline fun initTextures2d(block: Textures2d.() -> Unit) = initTextures2d(textureName, block)
inline fun initTextures2d(textures: IntBuffer, block: Textures2d.() -> Unit) {
    GL11.nglGenTextures(textures.capacity(), memAddress0(textures) + textures.capacity() shl 2)
    Textures2d.names = textures
    Textures2d.block()
}

inline fun initTextures(target: Int, block: Textures.() -> Unit) = initTextures(target, textureName, block)
inline fun initTextures(target: Int, textures: IntBuffer, block: Textures.() -> Unit) {
    GL11.nglGenTextures(textures.capacity(), memAddress0(textures) + textures.capacity() shl 2)
    Textures.target = target
    Textures.names = textures
    Textures.block()
}

object Texture {

    var target = 0
    var name = 0
        set(value) {
            GL11.glBindTexture(target, value)
            field = name
        }

    inline fun image1d(internalFormat: Int, width: Int, format: Int, type: Int, pixels: ByteBuffer) = image1d(0, internalFormat, width, format, type, pixels)
    inline fun image1d(level: Int, internalFormat: Int, width: Int, format: Int, type: Int, pixels: ByteBuffer) =
            GL11.nglTexImage1D(GL11.GL_TEXTURE_1D, level, internalFormat, width, 0, format, type, memAddress0(pixels) + pixels.position())

    var baseLevel = 0
        set(value) {
            GL11.glTexParameteri(target, GL12.GL_TEXTURE_BASE_LEVEL, value)
            field = value
        }
    var maxLevel = 1_000
        set(value) {
            GL11.glTexParameteri(target, GL12.GL_TEXTURE_MAX_LEVEL, value)
            field = value
        }
    var levels = 0..1_000
        set(value) {
            GL11.glTexParameteri(target, GL12.GL_TEXTURE_BASE_LEVEL, value.first)
            GL11.glTexParameteri(target, GL12.GL_TEXTURE_MAX_LEVEL, value.endInclusive)
            field = value
        }
}

object Texture1d {

    var name = 0
        set(value) {
            GL11.glBindTexture(GL11.GL_TEXTURE_1D, value)
            field = name
        }

    fun image(internalFormat: Int, width: Int, format: Int, type: Int, pixels: ByteBuffer) = image(0, internalFormat, width, format, type, pixels)
    fun image(level: Int, internalFormat: Int, width: Int, format: Int, type: Int, pixels: ByteBuffer) =
            GL11.nglTexImage1D(GL11.GL_TEXTURE_1D, level, internalFormat, width, 0, format, type, memAddress0(pixels) + pixels.position())

    var baseLevel = 0
        set(value) {
            GL11.glTexParameteri(GL11.GL_TEXTURE_1D, GL12.GL_TEXTURE_BASE_LEVEL, value)
            field = value
        }
    var maxLevel = 1_000
        set(value) {
            GL11.glTexParameteri(GL11.GL_TEXTURE_1D, GL12.GL_TEXTURE_MAX_LEVEL, value)
            field = value
        }
    var levels = 0..1_000
        set(value) {
            GL11.glTexParameteri(GL11.GL_TEXTURE_1D, GL12.GL_TEXTURE_BASE_LEVEL, value.first)
            GL11.glTexParameteri(GL11.GL_TEXTURE_1D, GL12.GL_TEXTURE_MAX_LEVEL, value.endInclusive)
            field = value
        }
}

object Texture2d {

    var name = 0
        set(value) {
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, value)
            field = name
        }

    inline fun image(internalFormat: Int, width: Int, height: Int, format: Int, type: Int) = image(0, internalFormat, width, height, format, type)
    inline fun image(level: Int, internalFormat: Int, width: Int, height: Int, format: Int, type: Int) =
            GL11.nglTexImage2D(GL11.GL_TEXTURE_2D, level, internalFormat, width, height, 0, format, type, NULL)

    inline fun image(internalFormat: Int, width: Int, height: Int, format: Int, type: Int, pixels: ByteBuffer) = image(0, internalFormat, width, height, format, type, pixels)
    inline fun image(level: Int, internalFormat: Int, width: Int, height: Int, format: Int, type: Int, pixels: ByteBuffer) =
            GL11.nglTexImage2D(GL11.GL_TEXTURE_2D, level, internalFormat, width, height, 0, format, type, memAddress0(pixels) + pixels.position())

    // TODO size for others

    inline fun image(internalFormat: Int, size: Vec2i, format: Int, type: Int) = image(0, internalFormat, size, format, type)
    inline fun image(level: Int, internalFormat: Int, size: Vec2i, format: Int, type: Int) = GL11.nglTexImage2D(GL11.GL_TEXTURE_2D, level, internalFormat, size.x, size.y, 0, format, type, NULL)

    inline fun image(internalFormat: Int, size: Vec2i, format: Int, type: Int, pixels: ByteBuffer) = image(0, internalFormat, size, format, type, pixels)
    inline fun image(level: Int, internalFormat: Int, size: Vec2i, format: Int, type: Int, pixels: ByteBuffer) =
            GL11.nglTexImage2D(GL11.GL_TEXTURE_2D, level, internalFormat, size.x, size.y, 0, format, type, memAddress0(pixels) + pixels.position())

    inline fun storage(internalFormat: Int, size: Vec2i) = storage(1, internalFormat, size)
    inline fun storage(levels: Int, internalFormat: Int, size: Vec2i) = GL42.glTexStorage2D(GL11.GL_TEXTURE_2D, levels, internalFormat, size.x, size.y)
    inline fun storage(levels: Int, internalFormat: gl.InternalFormat, size: Vec2i) = GL42.glTexStorage2D(GL11.GL_TEXTURE_2D, levels, internalFormat.i, size.x, size.y)

    var baseLevel = 0
        set(value) {
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL12.GL_TEXTURE_BASE_LEVEL, value)
            field = value
        }
    var maxLevel = 1_000
        set(value) {
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL12.GL_TEXTURE_MAX_LEVEL, value)
            field = value
        }
    var levels = 0..1_000
        set(value) {
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL12.GL_TEXTURE_BASE_LEVEL, value.first)
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL12.GL_TEXTURE_MAX_LEVEL, value.endInclusive)
            field = value
        }
    var swizzles = gl.Swizzles()
        set(value) {
            buf.putInt(0, value.r.i).putInt(Int.BYTES, value.g.i).putInt(Int.BYTES * 2, value.b.i).putInt(Int.BYTES * 3, value.a.i)
            GL11.nglTexParameteriv(GL11.GL_TEXTURE_2D, GL33.GL_TEXTURE_SWIZZLE_RGBA, bufAd)
        }

    inline fun levels(base: Int = 0, max: Int = 1_000) {
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL12.GL_TEXTURE_BASE_LEVEL, base)
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL12.GL_TEXTURE_MAX_LEVEL, max)
    }

    val linear = Filter.linear
    val nearest = Filter.nearest

    val nearest_mmNearest = Filter.nearest_mmNearest
    val linear_mmNearest = Filter.linear_mmNearest
    val nearest_mmLinear = Filter.nearest_mmLinear
    val linear_mmLinear = Filter.linear_mmLinear

    val clampToEdge = Wrap.clampToEdge
    val mirroredRepeat = Wrap.mirroredRepeat
    val repeat = Wrap.repeat

    var magFilter = linear
        set(value) {
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, value.i)
            field = value
        }
    var minFilter = nearest_mmLinear
        set(value) {
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, value.i)
            field = value
        }

    inline fun filter(min: Filter = nearest_mmLinear, mag: Filter = linear) {
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, min.i)
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, mag.i)
    }

    enum class Filter(val i: Int) { nearest(GL11.GL_NEAREST), linear(GL11.GL_LINEAR), nearest_mmNearest(GL11.GL_NEAREST_MIPMAP_NEAREST),
        linear_mmNearest(GL11.GL_LINEAR_MIPMAP_NEAREST), nearest_mmLinear(GL11.GL_NEAREST_MIPMAP_LINEAR), linear_mmLinear(GL11.GL_LINEAR_MIPMAP_LINEAR)
    }

    //    var maxAnisotropy = 1.0f
//        set(value) {
//            glTexParameteri(name, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, value)
//            field = value
//        }
    var wrapS = repeat
        set(value) {
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, value.i)
            field = value
        }
    var wrapT = repeat
        set(value) {
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, value.i)
            field = value
        }

    inline fun wrap(s: Wrap = repeat, t: Wrap = repeat) {
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, s.i)
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, t.i)
    }

    enum class Wrap(val i: Int) { clampToEdge(GL12.GL_CLAMP_TO_EDGE), mirroredRepeat(GL14.GL_MIRRORED_REPEAT), repeat(GL11.GL_REPEAT) }


    val rToTexture = CompareMode.rToTexture
    val none = CompareMode.none
    val lessEqual = CompareFunc.lessEqual
    val greaterEqual = CompareFunc.greaterEqual
    val less = CompareFunc.less
    val greater = CompareFunc.greater
    val equal = CompareFunc.equal
    val notEqual = CompareFunc.notEqual
    val always = CompareFunc.always
    val never = CompareFunc.never

    var compareFunc = rToTexture
        set(value) = GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_COMPARE_FUNC, value.i)
    var compareMode = lessEqual
        set(value) = GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_COMPARE_MODE, value.i)

    inline fun compare(func: CompareFunc, mode: CompareMode) {
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_COMPARE_FUNC, func.i)
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_COMPARE_MODE, mode.i)
    }

    enum class CompareMode(val i: Int) { rToTexture(GL14.GL_COMPARE_R_TO_TEXTURE), none(GL11.GL_NONE) }
    enum class CompareFunc(val i: Int) { lessEqual(GL11.GL_LEQUAL), greaterEqual(GL11.GL_GEQUAL), less(GL11.GL_LESS),
        greater(GL11.GL_GREATER), equal(GL11.GL_EQUAL), notEqual(GL11.GL_NOTEQUAL), always(GL11.GL_ALWAYS), never(GL11.GL_NEVER)
    }

    val red = Swizzle.r
    val green = Swizzle.g
    val blue = Swizzle.b
    val alpha = Swizzle.a

    inline fun swizzle(r: Swizzle, g: Swizzle, b: Swizzle, a: Swizzle) {
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL33.GL_TEXTURE_SWIZZLE_R, r.i)
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL33.GL_TEXTURE_SWIZZLE_G, g.i)
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL33.GL_TEXTURE_SWIZZLE_B, b.i)
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL33.GL_TEXTURE_SWIZZLE_A, a.i)
    }

    enum class Swizzle(val i: Int) { r(GL11.GL_RED), g(GL11.GL_GREEN), b(GL11.GL_BLUE), a(GL11.GL_ALPHA) }
}

object Textures {

    var target = 0
    lateinit var names: IntBuffer

    inline fun image1d(level: Int, internalFormat: Int, width: Int, format: Int, type: Int, pixels: ByteBuffer) =
            GL11.nglTexImage1D(target, level, internalFormat, width, 0, format, type, memAddress0(pixels) + pixels.position())
    inline fun image2d(level: Int, internalFormat: Int, width: Int, height: Int, format: Int, type: Int, pixels: ByteBuffer) =
            GL11.nglTexImage2D(target, level, internalFormat, width, height, 0, format, type, memAddress0(pixels) + pixels.position())

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
            GL11.nglTexImage2D(GL11.GL_TEXTURE_2D, level, internalFormat, width, height, 0, format, type, memAddress0(pixels) + pixels.position())

    inline fun at(index: Enum<*>, block: Texture2d.() -> Unit) = at(index.ordinal, block)
    inline fun at(index: Int, block: Texture2d.() -> Unit) {
        Texture2d.name = names[index] // bind
        Texture2d.block()
    }
}