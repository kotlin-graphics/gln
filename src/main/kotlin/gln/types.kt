package gln

import ab.appBuffer
import glm_.BYTES
import glm_.bool
import glm_.buffer.adr
import glm_.buffer.rem
import glm_.vec2.Vec2i
import glm_.vec3.Vec3i
import glm_.vec4.Vec4
import glm_.vec4.Vec4i
import gli_.gl.InternalFormat
import org.lwjgl.opengl.GL11C
import org.lwjgl.system.MemoryUtil
import org.lwjgl.system.MemoryUtil.NULL
import java.nio.IntBuffer
import gln.TextureTarget.*
import org.lwjgl.opengl.GL15C

inline class GLtexture1d(override val i: Int) : GLtexture {
    override val target: TextureTarget
        get() = `1D`
}

inline class GLtexture1dArray(override val i: Int) : GLtexture {
    override val target: TextureTarget
        get() = `1D_ARRAY`
}

inline class GLtexture2d(override val i: Int) : GLtexture {
    override val target: TextureTarget
        get() = `2D`
}

inline class GLtexture2dArray(override val i: Int) : GLtexture {
    override val target: TextureTarget
        get() = `2D_ARRAY`
}

inline class GLtexture3d(override val i: Int) : GLtexture {
    override val target: TextureTarget
        get() = `3D`
}

//inline class GLtextureCube(override val i: Int) : GLtexture {
//    override val target: TextureTarget
//        get() = CUBE_MAP
//}

//inline class GLtextureCubeArray(override val i: Int) : GLtexture {
//    override val target: TextureTarget
//        get() = `1D`
//}

inline class GLtextureRectangle(override val i: Int) : GLtexture {
    override val target: TextureTarget
        get() = RECTANGLE
}

interface GLtexture {

    val i: Int
    val target: TextureTarget

    // glGetTexLevelParameter

    fun width(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, WIDTH)
    fun height(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, HEIGHT)
    fun depth(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, DEPTH)
    /** JVM custom */
    fun size(level: Int = 0): Vec3i = Vec3i(width(level), height(level), depth(level))

    fun internalFormat(level: Int = 0): InternalFormat = InternalFormat of gl11.getTexLevelParameterI(target, level, INTERNAL_FORMAT)
    fun redSize(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, RED_SIZE)
    fun greenSize(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, GREEN_SIZE)
    fun blueSize(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, BLUE_SIZE)
    fun alphaSize(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, ALPHA_SIZE)
    /** JVM custom */
    fun rgbSize(level: Int = 0): Vec3i = Vec3i(redSize(level), greenSize(level), blueSize(level))

    /** JVM custom */
    fun rgbaSize(level: Int = 0): Vec4i = Vec4i(redSize(level), greenSize(level), blueSize(level), alphaSize(level))

    fun depthSize(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, DEPTH_SIZE)
    fun redType(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, RED_TYPE)
    fun greenType(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, GREEN_TYPE)
    fun blueType(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, BLUE_TYPE)
    fun alphaType(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, ALPHA_TYPE)
    /** JVM custom */
    fun rgbType(level: Int = 0): Vec3i = Vec3i(redType(level), greenType(level), blueType(level))

    /** JVM custom */
    fun rgbaType(level: Int = 0): Vec4i = Vec4i(redType(level), greenType(level), blueType(level), alphaType(level))

    fun depthType(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, DEPTH_TYPE)
    fun compressed(level: Int = 0): Boolean = gl11.getTexLevelParameterI(target, level, COMPRESSED).bool
    fun compressedImageSize(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, COMPRESSED_IMAGE_SIZE)
    fun bufferOffset(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, BUFFER_OFFSET)
    fun bufferSize(level: Int = 0): Int = gl11.getTexLevelParameterI(target, level, BUFFER_SIZE)

    // glGetTexParameter / glTexParameter (setter)

    var magFilter: TextureMagFilter
        get() = TextureMagFilter(gl11.getTexParameterI(target, MAG_FILTER))
        set(value) = gl11.texParameterI(target, MAG_FILTER, value.i)
    var minFilter: TextureMinFilter
        get() = TextureMinFilter(gl11.getTexParameterI(target, MIN_FILTER))
        set(value) = gl11.texParameterI(target, MIN_FILTER, value.i)
    /** JVM custom */
    var filters: Pair<TextureMinFilter, TextureMagFilter>
        get() = minFilter to magFilter
        set(value) {
            minFilter = value.first
            magFilter = value.second
        }

    var minLod: Float
        get() = gl11.getTexParameterF(target, MIN_LOD)
        set(value) = gl11.texParameterF(target, MIN_LOD, value)

    var maxLod: Float
        get() = gl11.getTexParameterF(target, MAX_LOD)
        set(value) = gl11.texParameterF(target, MAX_LOD, value)
    /** JVM custom */
    var lods: Pair<Float, Float>
        get() = minLod to maxLod
        set(value) {
            minLod = value.first
            maxLod = value.second
        }

    var baseLevel: Int
        get() = gl11.getTexParameterI(target, BASE_LEVEL)
        set(value) = gl11.texParameterI(target, BASE_LEVEL, value)
    var maxLevel: Int
        get() = gl11.getTexParameterI(target, MAX_LEVEL)
        set(value) = gl11.texParameterI(target, MAX_LEVEL, value)
    /** JVM custom */
    var levels: IntRange
        get() = baseLevel..maxLevel
        set(value) {
            baseLevel = value.first
            maxLevel = value.endInclusive
        }

    var swizzleR: TextureSwizzle
        get() = TextureSwizzle(gl11.getTexParameterI(target, SWIZZLE_R_))
        set(value) = gl11.texParameterI(target, SWIZZLE_R_, value.i)
    var swizzleG: TextureSwizzle
        get() = TextureSwizzle(gl11.getTexParameterI(target, SWIZZLE_G_))
        set(value) = gl11.texParameterI(target, SWIZZLE_G_, value.i)
    var swizzleB: TextureSwizzle
        get() = TextureSwizzle(gl11.getTexParameterI(target, SWIZZLE_B_))
        set(value) = gl11.texParameterI(target, SWIZZLE_B_, value.i)
    var swizzleA: TextureSwizzle
        get() = TextureSwizzle(gl11.getTexParameterI(target, SWIZZLE_A_))
        set(value) = gl11.texParameterI(target, SWIZZLE_A_, value.i)
    var swizzleRGBA: Vec4i
        get () = Vec4i(swizzleR.i, swizzleG.i, swizzleB.i, swizzleA.i)
        set(value) {
            swizzleR = TextureSwizzle(value.r)
            swizzleG = TextureSwizzle(value.r)
            swizzleB = TextureSwizzle(value.r)
            swizzleA = TextureSwizzle(value.r)
        }

    var wrapS: TextureWrapMode
        get() = TextureWrapMode(gl11.getTexParameterI(target, WRAP_S))
        set(value) = gl11.texParameterI(target, WRAP_S, value.i)
    var wrapT: TextureWrapMode
        get() = TextureWrapMode(gl11.getTexParameterI(target, WRAP_T))
        set(value) = gl11.texParameterI(target, WRAP_T, value.i)
    var wrapR: TextureWrapMode
        get() = TextureWrapMode(gl11.getTexParameterI(target, WRAP_R))
        set(value) = gl11.texParameterI(target, WRAP_R, value.i)
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
        get() {
            val color = appBuffer.floatArray(Vec4.length)
            GL11C.nglGetTexParameterfv(target.i, BORDER_COLOR.i, color)
            return Vec4 { MemoryUtil.memGetFloat(color + Float.BYTES * it) }
        }
        set(value) = GL11C.nglTexParameterfv(target.i, BORDER_COLOR.i, value.toByteBuffer().adr)

    var compareMode: TextureCompareMode
        get() = TextureCompareMode(gl11.getTexParameterI(target, COMPARE_MODE))
        set(value) = gl11.texParameterI(target, COMPARE_MODE, value.i)
    var compareFunc: CompareFunction
        get() = CompareFunction(gl11.getTexParameterI(target, COMPARE_FUNC))
        set(value) = gl11.texParameterI(target, COMPARE_FUNC, value.i)
    var viewMinLevel: Int
        get() = gl11.getTexParameterI(target, VIEW_MIN_LEVEL)
        set(value) = gl11.texParameterI(target, VIEW_MIN_LEVEL, value)
    var viewNumLevels: Int
        get() = gl11.getTexParameterI(target, VIEW_NUM_LEVELS)
        set(value) = gl11.texParameterI(target, VIEW_NUM_LEVELS, value)
    var viewMinLayer: Int
        get() = gl11.getTexParameterI(target, VIEW_MIN_LAYER)
        set(value) = gl11.texParameterI(target, VIEW_MIN_LAYER, value)
    var viewNumLayers: Int
        get() = gl11.getTexParameterI(target, VIEW_NUM_LAYERS)
        set(value) = gl11.texParameterI(target, VIEW_NUM_LAYERS, value)

    val imageFormatCompatibilityType: ImageFormatCompatibilityType
        get() = ImageFormatCompatibilityType(gl11.getTexParameterI(target, IMAGE_FORMAT_COMPATIBILITY_TYPE))

    val textureImmutableFormat: Boolean
        get() = gl11.getTexParameterI(target, IMMUTABLE_FORMAT).bool

    /** Tricky, just use with GL >= 4.5, https://stackoverflow.com/a/38308602/1047713 */
    val textureTarget: TextureTarget
        get() = TODO()
}

inline class GLprogram(val i: Int)
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
}

interface GLbuffer {

    val i: Int
    val target: TextureTarget

    val size: Int
        get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)
}

inline class GLbuffers(val i: IntBuffer) {

    inline val rem: Int
        get() = i.rem
    inline val adr: Long
        get() = i.adr
}

inline class Ptr(val L: Long) {
    val valid: Boolean
        get() = L != NULL
}


typealias GLbitfield = Int

