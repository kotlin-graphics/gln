@file:Suppress("NOTHING_TO_INLINE")

package gln.texture

import gli_.gl
import gli_.gl.ExternalFormat
import gli_.gl.TypeFormat
import glm_.bool
import glm_.vec2.Vec2i
import glm_.vec3.Vec3i
import glm_.vec4.Vec4
import gln.*
import gln.identifiers.GlTextures
import kool.Stack
import kool.adr
import kool.pos
import kool.rem
import org.lwjgl.opengl.*
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.system.MemoryUtil.memAddress
import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.IntBuffer


object GlTextureDsl {

    var target: TextureTarget = TextureTarget._1D
    var name = 0
    var level = 0

    // TODO remove *D since dimensionality is implicit in the parameter given?

    inline fun image1D(internalFormat: gl.InternalFormat, width: Int, format: ExternalFormat, type: TypeFormat, pixels: ByteBuffer) =
            image1D(0, internalFormat, width, format, type, pixels)

    inline fun image1D(level: Int, internalFormat: gl.InternalFormat, width: Int, format: ExternalFormat, type: TypeFormat, pixels: ByteBuffer) =
            GL11C.nglTexImage1D(target.i, level, internalFormat.i, width, 0, format.i, type.i, memAddress(pixels))

    inline fun image2D(internalFormat: gl.InternalFormat, size: Vec2i, format: ExternalFormat, type: TypeFormat, pixels: ByteBuffer) =
            image2D(0, internalFormat, size, format, type, pixels)

    inline fun image2D(level: Int, internalFormat: gl.InternalFormat, size: Vec2i, format: ExternalFormat, type: TypeFormat, pixels: ByteBuffer) =
            GL11C.nglTexImage2D(target.i, level, internalFormat.i, size.x, size.y, 0, format.i, type.i, memAddress(pixels))

    inline fun image3D(internalFormat: gl.InternalFormat, size: Vec3i, format: ExternalFormat, type: TypeFormat, pixels: ByteBuffer) =
            image3D(0, internalFormat, size, format, type, pixels)

    inline fun image3D(level: Int, internalFormat: gl.InternalFormat, size: Vec3i, format: ExternalFormat, type: TypeFormat, pixels: ByteBuffer) =
            GL12C.nglTexImage3D(target.i, level, internalFormat.i, size.x, size.y, size.z, 0, format.i, type.i, memAddress(pixels))

    // null

    inline fun image1D(internalFormat: gl.InternalFormat, width: Int, format: ExternalFormat, type: TypeFormat) =
            image1D(0, internalFormat, width, format, type)

    inline fun image1D(level: Int, internalFormat: gl.InternalFormat, width: Int, format: ExternalFormat, type: TypeFormat) =
            GL11C.nglTexImage1D(target.i, level, internalFormat.i, width, 0, format.i, type.i, NULL)

    inline fun image2D(internalFormat: gl.InternalFormat, size: Vec2i, format: ExternalFormat, type: TypeFormat) =
            image2D(0, internalFormat, size, format, type)

    inline fun image2D(level: Int, internalFormat: gl.InternalFormat, size: Vec2i, format: ExternalFormat, type: TypeFormat) =
            GL11C.nglTexImage2D(target.i, level, internalFormat.i, size.x, size.y, 0, format.i, type.i, NULL)

    inline fun image3D(internalFormat: gl.InternalFormat, size: Vec3i, format: ExternalFormat, type: TypeFormat) =
            image3D(0, internalFormat, size, format, type)

    inline fun image3D(level: Int, internalFormat: gl.InternalFormat, size: Vec3i, format: ExternalFormat, type: TypeFormat) =
            GL12C.nglTexImage3D(target.i, level, internalFormat.i, size.x, size.y, size.z, 0, format.i, type.i, NULL)


    // --- [ glGetTexParameterfv glGetTexParameteriv glGetTexParameterIiv glGetTexParameterIuiv ] ---

    inline var depthStencilMode: DepthStencilTextureMode
        get() = DepthStencilTextureMode(GL11C.glGetTexParameteri(target.i, GL43C.GL_DEPTH_STENCIL_TEXTURE_MODE))
        set(value) = GL11C.glTexParameteri(target.i, GL43C.GL_DEPTH_STENCIL_TEXTURE_MODE, value.i)

    inline var magFilter: TexMagFilter
        get() = TexMagFilter(GL11C.glGetTexParameteri(target.i, GL11C.GL_TEXTURE_MAG_FILTER))
        set(value) = GL11C.glTexParameteri(target.i, GL11C.GL_TEXTURE_MAG_FILTER, value.i)

    inline var minFilter: TexMinFilter
        get() = TexMinFilter(GL11C.glGetTexParameteri(target.i, GL11C.GL_TEXTURE_MIN_FILTER))
        set(value) = GL11C.glTexParameteri(target.i, GL11C.GL_TEXTURE_MIN_FILTER, value.i)

    inline fun filters(min: TexMinFilter, mag: TexMagFilter) {
        minFilter = min
        magFilter = mag
    }

    inline var minLod: Float
        get() = GL11C.glGetTexParameterf(target.i, GL12C.GL_TEXTURE_MIN_LOD)
        set(value) = GL11C.glTexParameterf(target.i, GL12C.GL_TEXTURE_MIN_LOD, value)

    inline var maxLod: Float
        get() = GL11C.glGetTexParameterf(target.i, GL12C.GL_TEXTURE_MAX_LOD)
        set(value) = GL11C.glTexParameterf(target.i, GL12C.GL_TEXTURE_MAX_LOD, value)

    inline var lodBias: Float
        get() = GL11C.glGetTexParameterf(target.i, GL14C.GL_MAX_TEXTURE_LOD_BIAS)
        set(value) = GL11C.glTexParameterf(target.i, GL14C.GL_TEXTURE_LOD_BIAS, value)

    inline var baseLevel: Int
        get() = GL11C.glGetTexParameteri(target.i, GL12.GL_TEXTURE_BASE_LEVEL)
        set(value) = GL11C.glTexParameteri(target.i, GL12.GL_TEXTURE_BASE_LEVEL, value)
    inline var maxLevel: Int
        get() = GL11C.glGetTexParameteri(target.i, GL12.GL_TEXTURE_MAX_LEVEL)
        set(value) = GL11C.glTexParameteri(target.i, GL12.GL_TEXTURE_MAX_LEVEL, value)

    inline var levels: IntRange
        get() = baseLevel..maxLevel
        set(value) {
            baseLevel = value.first
            maxLevel = value.last
        }

//    var swizzleR: gl.Swizzle
//        get() = gl.Swizzle.values().first { it.i == GL11C.glGetTexParameteri(target.i, GL33C.GL_TEXTURE_SWIZZLE_R) }
//        set(value) = GL11C.glTexParameteri(target.i, GL33.GL_TEXTURE_SWIZZLE_R, value.r.i)
//
//    var swizzleG: gl.Swizzle
//        get() = gl.Swizzle.values().first { it.i == GL11C.glGetTexParameteri(target.i, GL33C.GL_TEXTURE_SWIZZLE_G) }
//        set(value) = GL11C.glTexParameteri(target.i, GL33.GL_TEXTURE_SWIZZLE_G, value.r.i)
//
//    var swizzleB: gl.Swizzle
//        get() = gl.Swizzle.values().first { it.i == GL11C.glGetTexParameteri(target.i, GL33C.GL_TEXTURE_SWIZZLE_B) }
//        set(value) = GL11C.glTexParameteri(target.i, GL33.GL_TEXTURE_SWIZZLE_B, value.r.i)
//
//    var swizzleA: gl.Swizzle
//        get() = gl.Swizzle.values().first { it.i == GL11C.glGetTexParameteri(target.i, GL33C.GL_TEXTURE_SWIZZLE_A) }
//        set(value) = GL11C.glTexParameteri(target.i, GL33.GL_TEXTURE_SWIZZLE_A, value.r.i)
//
//    var swizzles: gl.Swizzles
//        get() = Stack {
//            val ints = it.mallocInt(4)
//            GL11C.glGetTexParameteriv(target.i, GL33C.GL_TEXTURE_SWIZZLE_RGBA, ints)
//            gl.Swizzle()
//        }
//        set(value) {
//            GL11C.glTexParameteri(target.i, GL33.GL_TEXTURE_SWIZZLE_R, value.r.i)
//            GL11C.glTexParameteri(target.i, GL33.GL_TEXTURE_SWIZZLE_G, value.g.i)
//            GL11C.glTexParameteri(target.i, GL33.GL_TEXTURE_SWIZZLE_B, value.b.i)
//            GL11C.glTexParameteri(target.i, GL33.GL_TEXTURE_SWIZZLE_A, value.a.i)
//        }
//
//    inline fun swizzles(r: Swizzle, g: Swizzle, b: Swizzle, a: Swizzle) {
//        GL11C.glTexParameteri(target.i, GL33.GL_TEXTURE_SWIZZLE_R, r.i)
//        GL11C.glTexParameteri(target.i, GL33.GL_TEXTURE_SWIZZLE_G, g.i)
//        GL11C.glTexParameteri(target.i, GL33.GL_TEXTURE_SWIZZLE_B, b.i)
//        GL11C.glTexParameteri(target.i, GL33.GL_TEXTURE_SWIZZLE_A, a.i)
//    }

    inline var wrapS: TexWrap
        get() = TexWrap(GL11C.glGetTexParameteri(target.i, GL11C.GL_TEXTURE_WRAP_S))
        set(value) = GL11C.glTexParameteri(target.i, GL11C.GL_TEXTURE_WRAP_S, value.i)
    inline var wrapT: TexWrap
        get() = TexWrap(GL11C.glGetTexParameteri(target.i, GL11C.GL_TEXTURE_WRAP_T))
        set(value) = GL11C.glTexParameteri(target.i, GL11C.GL_TEXTURE_WRAP_T, value.i)
    inline var wrapR: TexWrap
        get() = TexWrap(GL11C.glGetTexParameteri(target.i, GL12C.GL_TEXTURE_WRAP_R))
        set(value) = GL11C.glTexParameteri(target.i, GL12C.GL_TEXTURE_WRAP_R, value.i)

    inline var wrapST: TexWrap
        get() = throw Exception("Invalid")
        set(value) {
            wrapS = value
            wrapT = value
        }

    inline var wrapSTR: TexWrap
        get() = throw Exception("Invalid")
        set(value) {
            wrapS = value
            wrapT = value
            wrapR = value
        }

    inline fun wrap(s: TexWrap, t: TexWrap) {
        wrapS = s
        wrapT = t
    }

    inline fun wrap(s: TexWrap, t: TexWrap, r: TexWrap) {
        wrapS = s
        wrapT = t
        wrapR = r
    }

    inline var borderColor: Vec4
        get() = Stack.vec4Address { GL11C.nglGetTexParameteriv(target.i, GL11C.GL_TEXTURE_BORDER_COLOR, it) }
        set(value) = Stack.vec4Address(value) { GL11C.nglTexParameteriv(target.i, GL11C.GL_TEXTURE_BORDER_COLOR, it) }

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

    // view* calls are set by glTextureView call

    inline val viewMinLevel: Int
        get() = GL11C.glGetTexParameteri(target.i, GL43C.GL_TEXTURE_VIEW_MIN_LEVEL)

    inline val viewNumLevels: Int
        get() = GL11C.glGetTexParameteri(target.i, GL43C.GL_TEXTURE_VIEW_NUM_LEVELS)

    inline val viewMinLayer: Int
        get() = GL11C.glGetTexParameteri(target.i, GL43C.GL_TEXTURE_VIEW_MIN_LAYER)

    inline val viewNumLayers: Int
        get() = GL11C.glGetTexParameteri(target.i, GL43C.GL_TEXTURE_VIEW_NUM_LAYERS)

    /*
        Internally when glTexStorage* is used, OpenGL marks the texture object as being immutable by setting
        GL_TEXTURE_IMMUTABLE_FORMAT to GL_TRUE and GL_TEXTURE_IMMUTABLE_LEVELS to the number of levels passed
        to glTexstorage*
     */
    inline val immutableLevels: Int
        get() = GL11C.glGetTexParameteri(target.i, GL43C.GL_TEXTURE_IMMUTABLE_LEVELS)

    // the next three are read-only parameters

    inline val imageFormatCompatibilityType: ImageFormatCompatibilityType
        get() = ImageFormatCompatibilityType(GL11C.glGetTexParameteri(target.i, GL43C.GL_IMAGE_FORMAT_COMPATIBILITY_TYPE))

    inline val immutableFormat: Boolean
        get() = GL11C.glGetTexParameteri(target.i, GL43C.GL_TEXTURE_IMMUTABLE_FORMAT).bool

    // TOFIX it doesnt make really sense for non-dsa, since it'll return the target which has been passed, plus it clashed with `target`
//    inline val target: TextureTarget
//        get() = TextureTarget(GL11C.glGetTexParameteri(target.i, GL45C.GL_TEXTURE_TARGET))


    // --- [ glGetTexLevelParameteriv, glGetTexLevelParameterfv ] ---

    inline fun width(level: Int = this.level): Int = GL11C.glGetTexLevelParameteri(target.i, level, GL11C.GL_TEXTURE_WIDTH)
    inline fun height(level: Int = this.level): Int = GL11C.glGetTexLevelParameteri(target.i, level, GL11C.GL_TEXTURE_HEIGHT)
    inline fun depth(level: Int = this.level): Int = GL11C.glGetTexLevelParameteri(target.i, level, GL12C.GL_TEXTURE_DEPTH)
    inline fun size(level: Int = this.level): Vec3i = Vec3i(width(level), height(level), depth(level))

    inline fun internalFormat(level: Int = this.level): InternalFormat = InternalFormat(GL11C.glGetTexLevelParameteri(target.i, level, GL11C.GL_TEXTURE_INTERNAL_FORMAT))

    inline fun redType(level: Int = this.level): TextureComponentType = TextureComponentType(GL11C.glGetTexLevelParameteri(target.i, level, GL30C.GL_TEXTURE_RED_TYPE))
    inline fun greenType(level: Int = this.level): TextureComponentType = TextureComponentType(GL11C.glGetTexLevelParameteri(target.i, level, GL30C.GL_TEXTURE_GREEN_TYPE))
    inline fun blueType(level: Int = this.level): TextureComponentType = TextureComponentType(GL11C.glGetTexLevelParameteri(target.i, level, GL30C.GL_TEXTURE_BLUE_TYPE))
    inline fun alphaType(level: Int = this.level): TextureComponentType = TextureComponentType(GL11C.glGetTexLevelParameteri(target.i, level, GL30C.GL_TEXTURE_ALPHA_TYPE))
    inline fun depthType(level: Int = this.level): TextureComponentType = TextureComponentType(GL11C.glGetTexLevelParameteri(target.i, level, GL30C.GL_TEXTURE_DEPTH_TYPE))
    // TODO rgb(a)?


    inline fun redSize(level: Int = this.level): Int = GL11C.glGetTexLevelParameteri(target.i, level, GL30C.GL_TEXTURE_RED_SIZE)
    inline fun greenSize(level: Int = this.level): Int = GL11C.glGetTexLevelParameteri(target.i, level, GL30C.GL_TEXTURE_GREEN_SIZE)
    inline fun blueSize(level: Int = this.level): Int = GL11C.glGetTexLevelParameteri(target.i, level, GL30C.GL_TEXTURE_BLUE_SIZE)
    inline fun alphaSize(level: Int = this.level): Int = GL11C.glGetTexLevelParameteri(target.i, level, GL30C.GL_TEXTURE_ALPHA_SIZE)
    inline fun depthSize(level: Int = this.level): Int = GL11C.glGetTexLevelParameteri(target.i, level, GL30C.GL_TEXTURE_DEPTH_SIZE)
    // TODO rgb(a)?

    inline fun compressed(level: Int = this.level): Boolean = GL11C.glGetTexLevelParameteri(target.i, level, GL13C.GL_TEXTURE_COMPRESSED).bool

    inline fun compressedImageSize(level: Int = this.level): Int = GL11C.glGetTexLevelParameteri(target.i, level, GL13C.GL_TEXTURE_COMPRESSED_IMAGE_SIZE)

    inline fun bufferOffset(level: Int = this.level): Int = GL11C.glGetTexLevelParameteri(target.i, level, GL43C.GL_TEXTURE_BUFFER_OFFSET)
    inline fun bufferSize(level: Int = this.level): Int = GL11C.glGetTexLevelParameteri(target.i, level, GL43C.GL_TEXTURE_BUFFER_SIZE)

    // -------------------------------------------

    // --- [ glTexImage1D ] --- default target, proxy is basically useless, https://www.opengl.org/discussion_boards/showthread.php/182903-Texture-proxy-vs-just-allocating-it-and-check-for-OOM-condition

    fun image1D(level: Int, internalFormat: InternalFormat, width: Int, format: ExternalFormat, type: TypeFormat, pixels: Buffer? = null) = gln.gl.texImage1D(level, internalFormat, width, format, type, pixels)

    // --- [ glTexImage2D ] ---

    fun image2D(level: Int, internalFormat: InternalFormat, size: Vec2i, format: ExternalFormat, type: TypeFormat, pixels: Buffer? = null) = gln.gl.texImage2D(level, internalFormat, size, format, type, pixels)

    // level 0
    fun image1D(internalFormat: InternalFormat, width: Int, format: ExternalFormat, type: TypeFormat, pixels: Buffer? = null) = gln.gl.texImage1D(0, internalFormat, width, format, type, pixels)
    fun image2D(internalFormat: InternalFormat, size: Vec2i, format: ExternalFormat, type: TypeFormat, pixels: Buffer? = null) = gln.gl.texImage2D(0, internalFormat, size, format, type, pixels)

    // --- [ glCopyTexImage1D ] --- TODO custom InternalFormat?

    fun copyImage1D(level: Int, internalFormat: InternalFormat, pos: Vec2i, width: Int) = gln.gl.copyTexImage1D(level, internalFormat, pos, width)

    // --- [ glCopyTexImage2D ] ---

    fun copyImage2D(level: Int, internalFormat: InternalFormat, pos: Vec2i, size: Vec2i) = gln.gl.copyTexImage2D(level, internalFormat, pos, size)

    // --- [ glCopyTexSubImage1D ] ---

    fun copySubImage1D(level: Int, xOffset: Int, pos: Vec2i, width: Int) = gln.gl.copyTexSubImage1D(level, xOffset, pos, width)

    // --- [ glCopyTexSubImage2D ] ---

    fun copySubImage2D(level: Int, offset: Vec2i, pos: Vec2i, size: Vec2i) = gln.gl.copyTexSubImage2D(level, offset, pos, size)

    // --- [ glTexParameteri ] ---

    fun parameter(name: TexParameter, param: Int) = gln.gl.texParameter(target, name, param)

    // --- [ glTexParameterf ] ---

    fun parameter(name: TexParameter, param: Float) = gln.gl.texParameter(target, name, param)

    // --- [ glTexSubImage1D ] ---

    fun subImage1D(level: Int, xOffset: Int, width: Int, format: ExternalFormat, type: TypeFormat, pixels: Buffer) = gln.gl.texSubImage1D(level, xOffset, width, format, type, pixels)

    // --- [ glTexSubImage2D ] ---

    fun subImage2D(target: TextureTarget, level: Int, offset: Vec2i, size: Vec2i, format: ExternalFormat, type: TypeFormat, pixels: Buffer) = gln.gl.texSubImage2D(target, level, offset, size, format, type, pixels)

    // default target

    fun subImage2D(level: Int, offset: Vec2i, size: Vec2i, format: ExternalFormat, type: TypeFormat, pixels: Buffer) = gln.gl.texSubImage2D(level, offset, size, format, type, pixels)

    // ----- 1.2

    // --- [ glTexImage3D ] ---

    fun image3D(target: TextureTarget, level: Int, internalFormat: gl.InternalFormat, size: Vec3i, format: ExternalFormat, type: TypeFormat, pixels: Buffer?) = gln.gl.texImage3D(target, level, internalFormat, size, format, type, pixels)

    // default target
    fun image3D(level: Int, internalFormat: gl.InternalFormat, size: Vec3i, format: ExternalFormat, type: TypeFormat, pixels: Buffer?) = gln.gl.texImage3D(level, internalFormat, size, format, type, pixels)
    // default level
    fun image3D(internalFormat: gl.InternalFormat, size: Vec3i, format: ExternalFormat, type: TypeFormat, pixels: Buffer?) = gln.gl.texImage3D(0, internalFormat, size, format, type, pixels)

    // --- [ glTexSubImage3D ] ---

    fun subImage3D(target: TextureTarget, level: Int, offset: Vec3i, size: Vec3i, format: ExternalFormat, type: TypeFormat, pixels: Buffer) = gln.gl.texSubImage3D(target, level, offset, size, format, type, pixels)

    // default target

    fun subImage3D(level: Int, offset: Vec3i, size: Vec3i, format: ExternalFormat, type: TypeFormat, pixels: Buffer) = gln.gl.texSubImage3D(level, offset, size, format, type, pixels)

    // --- [ glCopyTexSubImage3D ] ---

    fun copySubImage3D(target: TextureTarget, level: Int, offset: Vec3i, pos: Vec2i, size: Vec2i) = gln.gl.copyTexSubImage3D(target, level, offset, pos, size)

    // default target

    fun copySubImage3D(level: Int, offset: Vec3i, pos: Vec2i, size: Vec2i) = gln.gl.copyTexSubImage3D(level, offset, pos, size)

    // ----- 1.3

    // --- [ glCompressedTexImage3D ] ---

    fun compressedImage3D(target: TextureTarget, level: Int, internalFormat: gl.InternalFormat, size: Vec3i, data: Buffer? = null) = gln.gl.compressedTexImage3D(target, level, internalFormat, size, data)

    // --- default target

    fun compressedImage3D(level: Int, internalFormat: gl.InternalFormat, size: Vec3i, data: Buffer? = null) = gln.gl.compressedTexImage3D(level, internalFormat, size, data)

    // --- [ glCompressedTexImage2D ] ---

    fun compressedImage2D(target: TextureTarget, level: Int, internalFormat: gl.InternalFormat, size: Vec2i, data: Buffer? = null) = gln.gl.compressedTexImage2D(target, level, internalFormat, size, data)

    // --- default target

    fun compressedImage2D(level: Int, internalFormat: gl.InternalFormat, size: Vec2i, data: Buffer? = null) = gln.gl.compressedTexImage2D(level, internalFormat, size, data)

    // --- [ glCompressedTexImage1D ] ---

    fun compressedImage1D(level: Int, internalFormat: gl.InternalFormat, width: Int, data: ByteBuffer? = null) = gln.gl.compressedTexImage1D(level, internalFormat, width, data)

    // --- [ glCompressedTexSubImage3D ] ---

    fun compressedSubImage3D(target: TextureTarget, level: Int, offset: Vec3i, size: Vec3i, format: gl.InternalFormat, data: ByteBuffer) = gln.gl.compressedTexSubImage3D(target, level, offset, size, format, data)

    // --- default target

    fun compressedSubImage3D(level: Int, offset: Vec3i, size: Vec3i, format: gl.InternalFormat, data: ByteBuffer) = gln.gl.compressedTexSubImage3D(level, offset, size, format, data)

    // --- [ glCompressedTexSubImage2D ] ---

    fun compressedSubImage2D(target: TextureTarget, level: Int, offset: Vec2i, size: Vec2i, format: gl.InternalFormat, data: ByteBuffer) = gln.gl.compressedTexSubImage2D(target, level, offset, size, format, data)

    // --- default target

    fun compressedSubImage2D(level: Int, offset: Vec2i, size: Vec2i, format: gl.InternalFormat, data: ByteBuffer) = gln.gl.compressedTexSubImage2D(level, offset, size, format, data)

    // --- [ glCompressedTexSubImage1D ] ---

    fun compressedSubImage1D(level: Int, offset: Int, width: Int, format: gl.InternalFormat, data: ByteBuffer) = gln.gl.compressedTexSubImage1D(level, offset, width, format, data)

    // --- [ glGetCompressedTexImage ] ---

    fun getCompressedImage(target: TextureTarget, level: Int, pixels: ByteBuffer) = gln.gl.getCompressedTexImage(target, level, pixels)

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

    var maxAnisotropy: Float
        get() = GL11C.glGetTexParameterf(target.i, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT)
        set(value) = GL11C.glTexParameterf(target.i, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, value)


}

inline class TexMinFilter(val i: Int) {
    companion object {
        val NEAREST = TexMinFilter(GL11C.GL_NEAREST)
        val LINEAR = TexMinFilter(GL11C.GL_LINEAR)
        val NEAREST_MIPMAP_NEAREST = TexMinFilter(GL11C.GL_NEAREST_MIPMAP_NEAREST)
        val LINEAR_MIPMAP_NEAREST = TexMinFilter(GL11C.GL_LINEAR_MIPMAP_NEAREST)
        val NEAREST_MIPMAP_LINEAR = TexMinFilter(GL11C.GL_NEAREST_MIPMAP_LINEAR)
        val LINEAR_MIPMAP_LINEAR = TexMinFilter(GL11C.GL_LINEAR_MIPMAP_LINEAR)
    }
}

inline class TexMagFilter(val i: Int) {
    companion object {
        val NEAREST = TexMagFilter(GL11C.GL_NEAREST)
        val LINEAR = TexMagFilter(GL11C.GL_LINEAR)
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

    var activeTexture: Int = 0
        set(value) {
            GL13C.glActiveTexture(GL13C.GL_TEXTURE0 + value)
            field = value
        }

    // --- [ glBindImageTextures ] ---

    fun bindImages(first: Int = 0) = gln.gl.bindImageTextures(first, GlTextures(names))

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

    inline fun <E : Enum<E>> E.bind(target: TextureTarget, block: GlTextureDsl.() -> Unit) {
        val name = names[ordinal]
        GL11C.glBindTexture(target.i, name)
        GlTextureDsl.name = name
        GlTextureDsl.block()
    }

    inline fun <E : Enum<E>> E.bound(target: TextureTarget, block: GlTextureDsl.() -> Unit) {
        bind(target, block)
        GL11C.glBindTexture(target.i, 0)
    }

    inline fun <E : Enum<E>> E.bind(activeTexture: Int, target: TextureTarget, block: GlTextureDsl.() -> Unit) {
        GL13C.glActiveTexture(GL13C.GL_TEXTURE0 + activeTexture)
        bind(target, block)
    }

    inline fun <E : Enum<E>> E.bound(activeTexture: Int, target: TextureTarget, block: GlTextureDsl.() -> Unit) {
        GL13C.glActiveTexture(GL13C.GL_TEXTURE0 + activeTexture)
        bound(target, block)
    }
}
