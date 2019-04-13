package gln.identifiers


import glm_.bool
import glm_.vec2.Vec2i
import glm_.vec3.Vec3i
import glm_.vec4.Vec4
import glm_.vec4.Vec4i
import glm_.vec4.Vec4ui
import gln.*
import gln.texture.*
import kool.IntBuffer
import kool.adr
import kool.rem
import kool.Stack
import org.lwjgl.opengl.*
import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.IntBuffer


//inline class GlTexture1d(override val i: Int) : GlTexture {
//    override val target: TextureTarget
//        get() = `1D`
//}
//
//inline class GlTexture1dArray(override val i: Int) : GlTexture {
//    override val target: TextureTarget
//        get() = `1D_ARRAY`
//}
//
//inline class GlTexture2d(override val i: Int) : GlTexture {
//    override val target: TextureTarget
//        get() = `2D`
//}
//
//inline class GlTexture2dArray(override val i: Int) : GlTexture {
//    override val target: TextureTarget
//        get() = `2D_ARRAY`
//}
//
//inline class GlTexture3d(override val i: Int) : GlTexture {
//    override val target: TextureTarget
//        get() = `3D`
//}
//
////inline class GlTextureCube(override val i: Int) : GlTexture {
////    override val target: TextureTarget
////        get() = CUBE_MAP
////}
//
////inline class GlTextureCubeArray(override val i: Int) : GlTexture {
////    override val target: TextureTarget
////        get() = `1D`
////}
//
//inline class GlTextureRectangle(override val i: Int) : GlTexture {
//    override val target: TextureTarget
//        get() = RECTANGLE
//}

inline class GlTexture(val name: Int = -1) {

    // --- [ glBindTextureUnit ] --- TODO bindToUnit?
    infix fun bindUnit(unit: TexUnit) = gl.bindTextureUnit(unit, this)

    // --- [ glClearTexImage ] ---
    fun clearImage(level: Int, format: TextureFormat, type: TextureType, data: Buffer? = null) = gl.clearTexImage(this, level, format, type, data)

    // --- [ glClearTexSubImage ] ---
    fun clearSubImage(level: Int, offset: Vec3i, size: Vec3i, format: TextureFormat, type: TextureType, data: Buffer? = null) = gl.clearTexSubImage(this, level, offset, size, format, type, data)

    // --- [ glCompressedTextureSubImage1D ] ---
    fun compressedSubImage1D(level: Int, offset: Int, width: Int, format: Int, data: ByteBuffer) = gl.compressedTexSubImage1D(this, level, offset, width, format, data)

    // --- [ glCompressedTextureSubImage2D ] ---
    fun compressedSubImage2D(level: Int, offset: Vec2i, size: Vec2i, format: Int, data: ByteBuffer) = gl.compressedTexSubImage2D(this, level, offset, size, format, data)

    // --- [ glCompressedTextureSubImage3D ] ---
    fun compressedSubImage3D(level: Int, offset: Vec3i, size: Vec3i, format: Int, data: ByteBuffer) = gl.compressedTexSubImage3D(this, level, offset, size, format, data)

    // --- [ glCopyTextureSubImage1D ] ---
    fun copySubImage1D(level: Int, offset: Int, leftLowerPixel: Vec2i, width: Int) = gl.copyTexSubImage1D(this, level, offset, leftLowerPixel, width)

    // --- [ glCopyTextureSubImage2D ] ---
    fun copySubImage2D(level: Int, offset: Vec2i, leftLowerPixel: Vec2i, size: Vec2i) = gl.copyTexSubImage2D(this, level, offset, leftLowerPixel, size)

    // --- [ glCopyTextureSubImage3D ] ---
    fun copySubImage3D(level: Int, offset: Vec3i, leftLowerPixel: Vec2i, size: Vec2i) = gl.copyTexSubImage3D(this, level, offset, leftLowerPixel, size)

    // --- [ glIsTexture ] ---
    inline val isValid: Boolean
        get() = GL20C.glIsTexture(name)
    inline val isInvalid: Boolean
        get() = !GL20C.glIsTexture(name)

    // --- [ glGenerateTextureMipmap ] ---
    fun generateMipmap() = gl.generateMipmap(this)

    // --- [ glGetCompressedTextureImage ] ---
    fun getCompressedImage(level: Int, pixels: ByteBuffer) = gl.getCompressedTexImage(this, level, pixels)

    // --- [ glGetTextureLevelParameterfv / glGetTextureLevelParameteriv ] ---
    inline fun <reified T> getLevelParameter(level: Int, name: TexLevelParameter): T = gl.getTexLevelParameter(this, level, name)

    // --- [ glGetTextureParameterfv / glGetTextureParameteriv / glGetTextureParameterIiv / glGetTextureParameterIuiv ] ---

    inline fun <reified T> getParameter(name: TexParameter): T = gl.getTexParameter(this, name)

    // --- [ glGetTexLevelParameter ] --- TODO

    // --- [ glBindTexture ] ---

    fun bind(target: TextureTarget) = GL11C.glBindTexture(target.i, name)

    fun bind(target: TextureTarget, unit: Int) {
        GL13C.glActiveTexture(GL13C.GL_TEXTURE0 + unit)
        GL11C.glBindTexture(target.i, name)
    }

    // TODO keep it?
    fun unbind(target: TextureTarget) = GL11C.glBindTexture(target.i, 0)

    fun bound(target: TextureTarget, block: GlTextureDsl.() -> Unit): GlTexture {
        bind(target)
        GlTextureDsl.name = name
        GlTextureDsl.target = target
        GlTextureDsl.block()
        unbind(target)
        return this
    }

    fun bound(target: TextureTarget, unit: Int, block: GlTextureDsl.() -> Unit): GlTexture {
        bind(target, unit)
        GlTextureDsl.name = name
        GlTextureDsl.target = target
        GlTextureDsl.block()
        unbind(target)
        return this
    }

    // --- [ glDeleteTextures ] ---

    fun delete() = gl.deleteTexture(this)

    // --- [ glGetTextureImage ] ---

    fun getImage(level: Int, format: TextureFormat2, type: TextureType2, pixels: ByteBuffer) = gl.getTexImage(this, level, format, type, pixels)

    // --- [ glGetnTexImage ] ---

    fun getnImage(level: Int, format: TextureFormat2, type: TextureType2, img: ByteBuffer) = gl.getnTexImage(this, level, format, type, img)

    // --- [ glGetTextureSubImage ] ---

    fun getTextureSubImage(level: Int, offset: Vec3i, size: Vec3i, format: TextureFormat3, type: TextureType2, pixels: ByteBuffer) = gl.getTextureSubImage(this, level, offset, size, format, type, pixels)

    // --- [ glGetCompressedTextureSubImage ] ---

    fun getCompressedTextureSubImage(level: Int, offset: Vec3i, size: Vec3i, pixels: ByteBuffer) = gl.getCompressedTextureSubImage(this, level, offset, size, pixels)


    // --- [ glInvalidateTexSubImage ] ---

    fun invalidateSubImage(level: Int, offset: Vec3i, size: Vec3i) = gl.invalidateTexSubImage(this, level, offset, size)

    // --- [ glInvalidateTexImage ] ---

    infix fun invalidateImage(level: Int) = gl.invalidateTexImage(this, level)

    // --- [ glTextureBuffer ] ---

    fun buffer(internalFormat: InternalFormat, buffer: GlBuffer) = gl.texBuffer(this, internalFormat, buffer)

    // --- [ glTextureBufferRange ] ---

    fun bufferRange(internalFormat: InternalFormat, buffer: GlBuffer, offset: Int, size: Int) = gl.texBufferRange(this, internalFormat, buffer, offset, size)

    // --- [ glTextureParameterf ] ---

    fun parameter(name: TexParameter, param: Float) = gl.texParameter(this, name, param)

    // --- [ glTextureParameteri ] ---

    fun parameter(name: TexParameter, param: Int) = gl.texParameter(this, name, param)

    // --- [ glTextureParameterIiv ] ---

    fun parameterI(name: TexParameter, param: Vec4i) = gl.texParameterI(this, name, param)

    // --- [ glTextureParameterIuiv ] ---

    fun parameterI(name: TexParameter, param: Vec4ui) = gl.texParameterI(this, name, param)

    // --- [ glTextureStorage1D ] ---

    fun storage1D(levels: Int, internalFormat: gli_.gl.InternalFormat, width: Int) = gl.texStorage1D(this, levels, internalFormat, width)

    // --- [ glTextureStorage2D ] ---

    fun storage2D(levels: Int, internalFormat: gli_.gl.InternalFormat, size: Vec2i) = gl.texStorage2D(this, levels, internalFormat, size)

    // --- [ glTextureStorage3D ] ---

    fun storage3D(levels: Int, internalFormat: gli_.gl.InternalFormat, size: Vec3i) = gl.texStorage3D(this, levels, internalFormat, size)

    // --- [ glTextureStorage2DMultisample ] ---

    fun storage2dMS(samples: Int, internalFormat: gli_.gl.InternalFormat, size: Vec2i, fixedSampleLocations: Boolean) = gl.texStorage2dMS(this, samples, internalFormat, size, fixedSampleLocations)

    // --- [ glTextureStorage3DMultisample ] ---

    fun storage3dMS(samples: Int, internalFormat: gli_.gl.InternalFormat, size: Vec3i, fixedSampleLocations: Boolean) = gl.texStorage3dMS(this, samples, internalFormat, size, fixedSampleLocations)

    // --- [ glTextureSubImage1D ] ---

    fun subImage1D(level: Int, offset: Int, width: Int, format: Int, type: Int, pixels: Buffer) = gl.texSubImage1D(this, level, offset, width, format, type, pixels)

    // --- [ glTextureSubImage2D ] ---

    fun subImage2D(level: Int, offset: Vec2i, size: Vec2i, format: Int, type: Int, pixels: Buffer) = gl.texSubImage2D(this, level, offset, size, format, type, pixels)

    // --- [ glTextureSubImage3D ] ---

    fun subImage3D(level: Int, offset: Vec3i, size: Vec3i, format: Int, type: Int, pixels: Buffer) = gl.texSubImage3D(this, level, offset, size, format, type, pixels)

    // --- [ glGetTexParameterfv glGetTexParameteriv glGetTexParameterIiv glGetTexParameterIuiv ] ---

    inline var depthStencilMode: DepthStencilTextureMode
        get() = DepthStencilTextureMode(GL45C.glGetTextureParameteri(name, GL43C.GL_DEPTH_STENCIL_TEXTURE_MODE))
        set(value) = GL45C.glTextureParameteri(name, GL43C.GL_DEPTH_STENCIL_TEXTURE_MODE, value.i)

    inline var magFilter: TexMagFilter
        get() = TexMagFilter(GL45C.glGetTextureParameteri(name, GL11C.GL_TEXTURE_MAG_FILTER))
        set(value) = GL45C.glTextureParameteri(name, GL11C.GL_TEXTURE_MAG_FILTER, value.i)

    inline var minFilter: TexMinFilter
        get() = TexMinFilter(GL45C.glGetTextureParameteri(name, GL11C.GL_TEXTURE_MIN_FILTER))
        set(value) = GL45C.glTextureParameteri(name, GL11C.GL_TEXTURE_MIN_FILTER, value.i)

    inline fun filters(min: TexMinFilter, mag: TexMagFilter) {
        minFilter = min
        magFilter = mag
    }

    inline var minLod: Float
        get() = GL45C.glGetTextureParameterf(name, GL12C.GL_TEXTURE_MIN_LOD)
        set(value) = GL45C.glTextureParameterf(name, GL12C.GL_TEXTURE_MIN_LOD, value)

    inline var maxLod: Float
        get() = GL45C.glGetTextureParameterf(name, GL12C.GL_TEXTURE_MAX_LOD)
        set(value) = GL45C.glTextureParameterf(name, GL12C.GL_TEXTURE_MAX_LOD, value)

    inline var lodBias: Float
        get() = GL45C.glGetTextureParameterf(name, GL14C.GL_MAX_TEXTURE_LOD_BIAS)
        set(value) = GL45C.glTextureParameterf(name, GL14C.GL_TEXTURE_LOD_BIAS, value)

    inline var baseLevel: Int
        get() = GL45C.glGetTextureParameteri(name, GL12.GL_TEXTURE_BASE_LEVEL)
        set(value) = GL45C.glTextureParameteri(name, GL12.GL_TEXTURE_BASE_LEVEL, value)
    inline var maxLevel: Int
        get() = GL45C.glGetTextureParameteri(name, GL12.GL_TEXTURE_MAX_LEVEL)
        set(value) = GL45C.glTextureParameteri(name, GL12.GL_TEXTURE_MAX_LEVEL, value)

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
        get() = TexWrap(GL45C.glGetTextureParameteri(name, GL11C.GL_TEXTURE_WRAP_S))
        set(value) = GL45C.glTextureParameteri(name, GL11C.GL_TEXTURE_WRAP_S, value.i)
    inline var wrapT: TexWrap
        get() = TexWrap(GL45C.glGetTextureParameteri(name, GL11C.GL_TEXTURE_WRAP_T))
        set(value) = GL45C.glTextureParameteri(name, GL11C.GL_TEXTURE_WRAP_T, value.i)
    inline var wrapR: TexWrap
        get() = TexWrap(GL45C.glGetTextureParameteri(name, GL12C.GL_TEXTURE_WRAP_R))
        set(value) = GL45C.glTextureParameteri(name, GL12C.GL_TEXTURE_WRAP_R, value.i)

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
        get() = Stack.vec4Address { GL11C.nglGetTexParameteriv(GlTextureDsl.target.i, GL11C.GL_TEXTURE_BORDER_COLOR, it) }
        set(value) = Stack.vec4Address(value) { GL11C.nglTexParameteriv(GlTextureDsl.target.i, GL11C.GL_TEXTURE_BORDER_COLOR, it) }

    var compareFunc: CompareFunction
        get() = CompareFunction(GL45C.glGetTextureParameteri(name, GL14.GL_TEXTURE_COMPARE_FUNC))
        set(value) = GL45C.glTextureParameteri(name, GL14.GL_TEXTURE_COMPARE_FUNC, value.i)
    var compareMode: TextureCompareMode
        get() = TextureCompareMode(GL45C.glGetTextureParameteri(name, GL14.GL_TEXTURE_COMPARE_MODE))
        set(value) = GL45C.glTextureParameteri(name, GL14.GL_TEXTURE_COMPARE_MODE, value.i)

    inline fun compare(func: CompareFunction, mode: TextureCompareMode) {
        compareFunc = func
        compareMode = mode
    }

    // view* calls are set by glTextureView call

    inline val viewMinLevel: Int
        get() = GL45C.glGetTextureParameteri(name, GL43C.GL_TEXTURE_VIEW_MIN_LEVEL)

    inline val viewNumLevels: Int
        get() = GL45C.glGetTextureParameteri(name, GL43C.GL_TEXTURE_VIEW_NUM_LEVELS)

    inline val viewMinLayer: Int
        get() = GL45C.glGetTextureParameteri(name, GL43C.GL_TEXTURE_VIEW_MIN_LAYER)

    inline val viewNumLayers: Int
        get() = GL45C.glGetTextureParameteri(name, GL43C.GL_TEXTURE_VIEW_NUM_LAYERS)

    /*
        Internally when glTexStorage* is used, OpenGL marks the texture object as being immutable by setting
        GL_TEXTURE_IMMUTABLE_FORMAT to GL_TRUE and GL_TEXTURE_IMMUTABLE_LEVELS to the number of levels passed
        to glTexstorage*
     */
    inline val immutableLevels: Int
        get() = GL45C.glGetTextureParameteri(name, GL43C.GL_TEXTURE_IMMUTABLE_LEVELS)

    // the next three are read-only parameters

    inline val imageFormatCompatibilityType: ImageFormatCompatibilityType
        get() = ImageFormatCompatibilityType(GL45C.glGetTextureParameteri(name, GL43C.GL_IMAGE_FORMAT_COMPATIBILITY_TYPE))

    inline val immutableFormat: Boolean
        get() = GL45C.glGetTextureParameteri(name, GL43C.GL_TEXTURE_IMMUTABLE_FORMAT).bool

    inline val target: TextureTarget
        get() = TextureTarget(GL45C.glGetTextureParameteri(name, GL45C.GL_TEXTURE_TARGET))

    // --- [ glGetTexLevelParameteriv, glGetTexLevelParameterfv ] ---

    inline fun width(level: Int = 0): Int = GL45C.glGetTextureLevelParameteri(name, level, GL11C.GL_TEXTURE_WIDTH)
    inline fun height(level: Int = 0): Int = GL45C.glGetTextureLevelParameteri(name, level, GL11C.GL_TEXTURE_HEIGHT)
    inline fun depth(level: Int = 0): Int = GL45C.glGetTextureLevelParameteri(name, level, GL12C.GL_TEXTURE_DEPTH)
    inline fun size(level: Int = 0): Vec3i = Vec3i(width(level), height(level), depth(level))

    inline fun internalFormat(level: Int = 0): InternalFormat = InternalFormat(GL45C.glGetTextureLevelParameteri(name, level, GL11C.GL_TEXTURE_INTERNAL_FORMAT))

    inline fun redType(level: Int = 0): TextureComponentType = TextureComponentType(GL45C.glGetTextureLevelParameteri(name, level, GL30C.GL_TEXTURE_RED_TYPE))
    inline fun greenType(level: Int = 0): TextureComponentType = TextureComponentType(GL45C.glGetTextureLevelParameteri(name, level, GL30C.GL_TEXTURE_GREEN_TYPE))
    inline fun blueType(level: Int = 0): TextureComponentType = TextureComponentType(GL45C.glGetTextureLevelParameteri(name, level, GL30C.GL_TEXTURE_BLUE_TYPE))
    inline fun alphaType(level: Int = 0): TextureComponentType = TextureComponentType(GL45C.glGetTextureLevelParameteri(name, level, GL30C.GL_TEXTURE_ALPHA_TYPE))
    inline fun depthType(level: Int = 0): TextureComponentType = TextureComponentType(GL45C.glGetTextureLevelParameteri(name, level, GL30C.GL_TEXTURE_DEPTH_TYPE))
    // TODO rgb(a)?


    inline fun redSize(level: Int = 0): Int = GL45C.glGetTextureLevelParameteri(name, level, GL30C.GL_TEXTURE_RED_SIZE)
    inline fun greenSize(level: Int = 0): Int = GL45C.glGetTextureLevelParameteri(name, level, GL30C.GL_TEXTURE_GREEN_SIZE)
    inline fun blueSize(level: Int = 0): Int = GL45C.glGetTextureLevelParameteri(name, level, GL30C.GL_TEXTURE_BLUE_SIZE)
    inline fun alphaSize(level: Int = 0): Int = GL45C.glGetTextureLevelParameteri(name, level, GL30C.GL_TEXTURE_ALPHA_SIZE)
    inline fun depthSize(level: Int = 0): Int = GL45C.glGetTextureLevelParameteri(name, level, GL30C.GL_TEXTURE_DEPTH_SIZE)
    // TODO rgb(a)?

    inline fun compressed(level: Int = 0): Boolean = GL45C.glGetTextureLevelParameteri(name, level, GL13C.GL_TEXTURE_COMPRESSED).bool

    inline fun compressedImageSize(level: Int = 0): Int = GL45C.glGetTextureLevelParameteri(name, level, GL13C.GL_TEXTURE_COMPRESSED_IMAGE_SIZE)

    inline fun bufferOffset(level: Int = 0): Int = GL45C.glGetTextureLevelParameteri(name, level, GL43C.GL_TEXTURE_BUFFER_OFFSET)
    inline fun bufferSize(level: Int = 0): Int = GL45C.glGetTextureLevelParameteri(name, level, GL43C.GL_TEXTURE_BUFFER_SIZE)


    companion object {

        // --- [ glCreateTextures ] ---

        infix fun create(target: TextureTarget): GlTexture = gl.createTextures(target)

        // --- [ glGenTextures ] ---

        fun gen(): GlTexture = gl.genTextures()
    }
}

fun GlTextures(size: Int) = GlTextures(IntBuffer(size))

inline class GlTextures(val names: IntBuffer) {

    inline val rem: Int
        get() = names.rem

    inline val adr: Long
        get() = names.adr

    // --- [ glBindImageTextures ] ---

    fun bindImages(first: Int = 0) = gl.bindImageTextures(first, this)

    // --- [ glBindTextures ] ---

    fun bind(first: Int = 0) = gl.bindTextures(first, this)

    fun delete() = gl.deleteTextures(this)

    //    inline operator fun invoke(block: GlTexturesDsl.() -> Unit) {
//        GlTexturesDsl.names = i
//        GlTexturesDsl.block()
//    }

    // --- [ glGenTextures ] ---

    fun gen() = gl.genTextures(this)

    // --- [ glCreateTextures ] ---

    infix fun create(target: TextureTarget) = gl.createTextures(target, this)

    inline fun create(target: TextureTarget, block: GlTexturesDsl.() -> Unit) {
        create(target)
        GlTexturesDsl.names = names
        GlTexturesDsl.block()
    }

    // --- [ glDeleteTextures ] ---

    infix fun deleteTextures(textures: GlTextures) = GL11C.nglDeleteTextures(textures.rem, textures.adr)

    companion object {

        // --- [ glGenTextures ] ---

        fun gen(size: Int): GlTextures = gl.genTextures(size)
    }
}