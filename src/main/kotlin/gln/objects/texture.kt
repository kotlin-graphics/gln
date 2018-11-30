package gln.objects


import gli_.gl.InternalFormat
import glm_.bool
import glm_.vec2.Vec2i
import glm_.vec3.Vec3i
import glm_.vec4.Vec4
import glm_.vec4.Vec4i
import gln.*
import kool.adr
import kool.stak
import org.lwjgl.opengl.*


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

interface GlTexture {

    val i: Int
    val target: TextureTarget

    // --- [ glIsTexture ] ---

    val valid: Boolean
        get() = GL20C.glIsTexture(i)

    // glGetTexLevelParameter

    fun width(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, GetTexLevelParameter(GL11.GL_TEXTURE_WIDTH))
    fun height(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, GetTexLevelParameter(GL11.GL_TEXTURE_HEIGHT))
    fun depth(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, GetTexLevelParameter(GL12.GL_TEXTURE_DEPTH))
    /** JVM custom */
    fun size(level: Int = 0): Vec3i = Vec3i(width(level), height(level), depth(level))

    fun internalFormat(level: Int = 0): InternalFormat = InternalFormat of gl11.getTexLevelParameterI(target, level, GetTexLevelParameter(GL11.GL_TEXTURE_INTERNAL_FORMAT))
    fun redSize(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, GetTexLevelParameter(GL11.GL_TEXTURE_RED_SIZE))
    fun greenSize(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, GetTexLevelParameter(GL11.GL_TEXTURE_GREEN_SIZE))
    fun blueSize(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, GetTexLevelParameter(GL11.GL_TEXTURE_BLUE_SIZE))
    fun alphaSize(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, GetTexLevelParameter(GL11.GL_TEXTURE_ALPHA_SIZE))
    /** JVM custom */
    fun rgbSize(level: Int = 0): Vec3i = Vec3i(redSize(level), greenSize(level), blueSize(level))

    /** JVM custom */
    fun rgbaSize(level: Int = 0): Vec4i = Vec4i(redSize(level), greenSize(level), blueSize(level), alphaSize(level))

    fun depthSize(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, GetTexLevelParameter(GL30.GL_TEXTURE_DEPTH_SIZE))
    fun redType(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, GetTexLevelParameter(GL30.GL_TEXTURE_RED_TYPE))
    fun greenType(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, GetTexLevelParameter(GL30.GL_TEXTURE_GREEN_TYPE))
    fun blueType(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, GetTexLevelParameter(GL30.GL_TEXTURE_BLUE_TYPE))
    fun alphaType(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, GetTexLevelParameter(GL30.GL_TEXTURE_ALPHA_TYPE))
    /** JVM custom */
    fun rgbType(level: Int = 0): Vec3i = Vec3i(redType(level), greenType(level), blueType(level))

    /** JVM custom */
    fun rgbaType(level: Int = 0): Vec4i = Vec4i(redType(level), greenType(level), blueType(level), alphaType(level))

    fun depthType(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, GetTexLevelParameter(GL30.GL_TEXTURE_DEPTH_TYPE))
    fun compressed(level: Int = 0): Boolean = gl11.getTexLevelParameterI(target, level, GetTexLevelParameter(GL30.GL_TEXTURE_COMPRESSED)).bool
    fun compressedImageSize(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, GetTexLevelParameter(GL13.GL_TEXTURE_COMPRESSED_IMAGE_SIZE))
    fun bufferOffset(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, GetTexLevelParameter(GL43.GL_TEXTURE_BUFFER_OFFSET))
    fun bufferSize(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, GetTexLevelParameter(GL43.GL_TEXTURE_BUFFER_SIZE))

    // glGetTexParameter / glTexParameter (setter)

    var magFilter: TextureMagFilter
        get() = TextureMagFilter(gl11.getTexParameterI(target, TexParameter(GL11.GL_TEXTURE_MAG_FILTER)))
        set(value) = gl11.texParameterI(target, TexParameter(GL11.GL_TEXTURE_MAG_FILTER), value.i)
    var minFilter: TextureMinFilter
        get() = TextureMinFilter(gl11.getTexParameterI(target, TexParameter(GL11.GL_TEXTURE_MIN_FILTER)))
        set(value) = gl11.texParameterI(target, TexParameter(GL11.GL_TEXTURE_MIN_FILTER), value.i)
    /** JVM custom */
    var filters: Pair<TextureMinFilter, TextureMagFilter>
        get() = minFilter to magFilter
        set(value) {
            minFilter = value.first
            magFilter = value.second
        }

    var minLod: Float
        get() = gl11.getTexParameterF(target, TexParameter(GL12.GL_TEXTURE_MIN_LOD))
        set(value) = gl11.texParameterF(target, TexParameter(GL12.GL_TEXTURE_MIN_LOD), value)

    var maxLod: Float
        get() = gl11.getTexParameterF(target, TexParameter(GL12.GL_TEXTURE_MAX_LOD))
        set(value) = gl11.texParameterF(target, TexParameter(GL12.GL_TEXTURE_MAX_LOD), value)
    /** JVM custom */
    var lods: Pair<Float, Float>
        get() = minLod to maxLod
        set(value) {
            minLod = value.first
            maxLod = value.second
        }

    var baseLevel: Int
        get() = gl11.getTexParameterI(target, TexParameter(GL12.GL_TEXTURE_BASE_LEVEL))
        set(value) = gl11.texParameterI(target, TexParameter(GL12.GL_TEXTURE_BASE_LEVEL), value)
    var maxLevel: Int
        get() = gl11.getTexParameterI(target, TexParameter(GL12.GL_TEXTURE_MAX_LEVEL))
        set(value) = gl11.texParameterI(target, TexParameter(GL12.GL_TEXTURE_MAX_LEVEL), value)
    /** JVM custom */
    var levels: IntRange
        get() = baseLevel..maxLevel
        set(value) {
            baseLevel = value.first
            maxLevel = value.endInclusive
        }

    var swizzleR: TextureSwizzle
        get() = TextureSwizzle(gl11.getTexParameterI(target, TexParameter(GL33.GL_TEXTURE_SWIZZLE_R)))
        set(value) = gl11.texParameterI(target, TexParameter(GL33.GL_TEXTURE_SWIZZLE_R), value.i)
    var swizzleG: TextureSwizzle
        get() = TextureSwizzle(gl11.getTexParameterI(target, TexParameter(GL33.GL_TEXTURE_SWIZZLE_G)))
        set(value) = gl11.texParameterI(target, TexParameter(GL33.GL_TEXTURE_SWIZZLE_G), value.i)
    var swizzleB: TextureSwizzle
        get() = TextureSwizzle(gl11.getTexParameterI(target, TexParameter(GL33.GL_TEXTURE_SWIZZLE_B)))
        set(value) = gl11.texParameterI(target, TexParameter(GL33.GL_TEXTURE_SWIZZLE_B), value.i)
    var swizzleA: TextureSwizzle
        get() = TextureSwizzle(gl11.getTexParameterI(target, TexParameter(GL33.GL_TEXTURE_SWIZZLE_A)))
        set(value) = gl11.texParameterI(target, TexParameter(GL33.GL_TEXTURE_SWIZZLE_A), value.i)
    var swizzleRGBA: Vec4i
        get () = Vec4i(swizzleR.i, swizzleG.i, swizzleB.i, swizzleA.i)
        set(value) {
            swizzleR = TextureSwizzle(value.r)
            swizzleG = TextureSwizzle(value.r)
            swizzleB = TextureSwizzle(value.r)
            swizzleA = TextureSwizzle(value.r)
        }

    var wrapS: TextureWrapMode
        get() = TextureWrapMode(gl11.getTexParameterI(target, TexParameter(GL11.GL_TEXTURE_WRAP_S)))
        set(value) = gl11.texParameterI(target, TexParameter(GL11.GL_TEXTURE_WRAP_S), value.i)
    var wrapT: TextureWrapMode
        get() = TextureWrapMode(gl11.getTexParameterI(target, TexParameter(GL11.GL_TEXTURE_WRAP_T)))
        set(value) = gl11.texParameterI(target, TexParameter(GL11.GL_TEXTURE_WRAP_T), value.i)
    var wrapR: TextureWrapMode
        get() = TextureWrapMode(gl11.getTexParameterI(target, TexParameter(GL12.GL_TEXTURE_WRAP_R)))
        set(value) = gl11.texParameterI(target, TexParameter(GL12.GL_TEXTURE_WRAP_R), value.i)
    /** JVM custom */
    var wrapST: Vec2i
        get() = Vec2i(wrapS.i, wrapT.i)
        set(value) {
            wrapS = TextureWrapMode((value.s))
            wrapT = TextureWrapMode((value.t))
        }

    /** JVM custom */
    var wrapSTR: Vec3i
        get() = Vec3i(wrapS.i, wrapT.i, wrapR.i)
        set(value) {
            wrapS = TextureWrapMode(value.s)
            wrapT = TextureWrapMode(value.t)
            wrapR = TextureWrapMode(value.r)
        }

    var borderColor: Vec4
        get() = stak.vec4Address {GL11C.nglGetTexParameterfv(target.i, GL11.GL_TEXTURE_BORDER_COLOR, it) }
        set(value) = GL11C.nglTexParameterfv(target.i, GL11.GL_TEXTURE_BORDER_COLOR, value.toBufferStack().adr)

    var compareMode: TextureCompareMode
        get() = TextureCompareMode(gl11.getTexParameterI(target, TexParameter(GL14.GL_TEXTURE_COMPARE_MODE)))
        set(value) = gl11.texParameterI(target, TexParameter(GL14.GL_TEXTURE_COMPARE_MODE), value.i)
    var compareFunc: CompareFunction
        get() = CompareFunction(gl11.getTexParameterI(target, TexParameter(GL14.GL_TEXTURE_COMPARE_FUNC)))
        set(value) = gl11.texParameterI(target, TexParameter(GL14.GL_TEXTURE_COMPARE_FUNC), value.i)
    var viewMinLevel: Int
        get() = gl11.getTexParameterI(target, TexParameter(GL43.GL_TEXTURE_VIEW_MIN_LAYER))
        set(value) = gl11.texParameterI(target, TexParameter(GL43.GL_TEXTURE_VIEW_MIN_LAYER), value)
    var viewNumLevels: Int
        get() = gl11.getTexParameterI(target, TexParameter(GL43.GL_TEXTURE_VIEW_NUM_LEVELS))
        set(value) = gl11.texParameterI(target, TexParameter(GL43.GL_TEXTURE_VIEW_NUM_LEVELS), value)
    var viewMinLayer: Int
        get() = gl11.getTexParameterI(target, TexParameter(GL43.GL_TEXTURE_VIEW_MIN_LAYER))
        set(value) = gl11.texParameterI(target, TexParameter(GL43.GL_TEXTURE_VIEW_MIN_LAYER), value)
    var viewNumLayers: Int
        get() = gl11.getTexParameterI(target, TexParameter(GL43.GL_TEXTURE_VIEW_NUM_LAYERS))
        set(value) = gl11.texParameterI(target, TexParameter(GL43.GL_TEXTURE_VIEW_NUM_LAYERS), value)

    val imageFormatCompatibilityType: ImageFormatCompatibilityType
        get() = ImageFormatCompatibilityType(gl11.getTexParameterI(target, TexParameter(GL42.GL_IMAGE_FORMAT_COMPATIBILITY_TYPE)))

    val textureImmutableFormat: Boolean
        get() = gl11.getTexParameterI(target, TexParameter(GL42.GL_TEXTURE_IMMUTABLE_FORMAT)).bool

    /** Tricky, just use with GL >= 4.5, https://stackoverflow.com/a/38308602/1047713 */
    val textureTarget: TextureTarget
        get() = TODO()

    fun bind() = GL11C.glBindTexture(target.i, i)
    fun unbind() = GL11C.glBindTexture(target.i, 0)
    fun <R> bound(block: (GlTexture) -> R): R {
        GL11C.glBindTexture(target.i, i)
        return block(this).also { GL11C.glBindTexture(target.i, 0) }
    }

    fun delete() = gl11.deleteTexture(this)

    companion object {
        fun gen() = gl11.genTexture()
    }
}