package gln.identifiers


import glm_.vec2.Vec2i
import glm_.vec3.Vec3i
import glm_.vec4.Vec4
import glm_.vec4.Vec4i
import glm_.vec4.Vec4ui
import gln.*
import gln.texture.GlTextureDsl
import gln.texture.GlTexturesDsl
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

    /**
     * DSA version of {@link GL13C#glCompressedTexSubImage1D CompressedTexSubImage1D}.
     *
     * @param level   the level-of-detail number. Level 0 is the base image level. Level n is the nth mipmap reduction image.
     * @param offset a texel offset in the x direction within the texture array
     * @param width   the width of the texture subimage
     * @param format  the format of the compressed image data stored at address {@code data}. One of:<br><table><tr><td>{@link GL30#GL_COMPRESSED_RED_RGTC1 COMPRESSED_RED_RGTC1}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RED_RGTC1 COMPRESSED_SIGNED_RED_RGTC1}</td></tr><tr><td>{@link GL30#GL_COMPRESSED_RG_RGTC2 COMPRESSED_RG_RGTC2}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RG_RGTC2 COMPRESSED_SIGNED_RG_RGTC2}</td></tr><tr><td>{@link GL42#GL_COMPRESSED_RGBA_BPTC_UNORM COMPRESSED_RGBA_BPTC_UNORM}</td><td>{@link GL42#GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM COMPRESSED_SRGB_ALPHA_BPTC_UNORM}</td></tr><tr><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT COMPRESSED_RGB_BPTC_SIGNED_FLOAT}</td><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGB8_ETC2 COMPRESSED_RGB8_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ETC2 COMPRESSED_SRGB8_ETC2}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGBA8_ETC2_EAC COMPRESSED_RGBA8_ETC2_EAC}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC COMPRESSED_SRGB8_ALPHA8_ETC2_EAC}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_R11_EAC COMPRESSED_R11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_R11_EAC COMPRESSED_SIGNED_R11_EAC}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RG11_EAC COMPRESSED_RG11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_RG11_EAC COMPRESSED_SIGNED_RG11_EAC}</td></tr><tr><td>see {@link EXTTextureCompressionS3TC}</td><td>see {@link EXTTextureCompressionLATC}</td></tr><tr><td>see {@link ATITextureCompression3DC}</td><td>see {@link KHRTextureCompressionASTCLDR}</td></tr></table>
     * @param data    a pointer to the compressed image data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCompressedTextureSubImage1D">Reference Page</a>
     */
    fun compressedSubImage1D(level: Int, offset: Int, width: Int, format: Int, data: ByteBuffer) = gl.compressedTexSubImage1D(this, level, offset, width, format, data)

    // --- [ glCompressedTextureSubImage2D ] ---

    /**
     * DSA version of {@link GL13C#glCompressedTexSubImage2D CompressedTexSubImage2D}.
     *
     * @param level   the level-of-detail number. Level 0 is the base image level. Level n is the nth mipmap reduction image.
     * @param offset  a texel offset in the x, y direction within the texture array
     * @param size    the size of the texture subimage
     * @param height  the height of the texture subimage
     * @param format  the format of the compressed image data stored at address {@code data}. One of:<br><table><tr><td>{@link GL30#GL_COMPRESSED_RED_RGTC1 COMPRESSED_RED_RGTC1}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RED_RGTC1 COMPRESSED_SIGNED_RED_RGTC1}</td></tr><tr><td>{@link GL30#GL_COMPRESSED_RG_RGTC2 COMPRESSED_RG_RGTC2}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RG_RGTC2 COMPRESSED_SIGNED_RG_RGTC2}</td></tr><tr><td>{@link GL42#GL_COMPRESSED_RGBA_BPTC_UNORM COMPRESSED_RGBA_BPTC_UNORM}</td><td>{@link GL42#GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM COMPRESSED_SRGB_ALPHA_BPTC_UNORM}</td></tr><tr><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT COMPRESSED_RGB_BPTC_SIGNED_FLOAT}</td><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGB8_ETC2 COMPRESSED_RGB8_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ETC2 COMPRESSED_SRGB8_ETC2}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGBA8_ETC2_EAC COMPRESSED_RGBA8_ETC2_EAC}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC COMPRESSED_SRGB8_ALPHA8_ETC2_EAC}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_R11_EAC COMPRESSED_R11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_R11_EAC COMPRESSED_SIGNED_R11_EAC}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RG11_EAC COMPRESSED_RG11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_RG11_EAC COMPRESSED_SIGNED_RG11_EAC}</td></tr><tr><td>see {@link EXTTextureCompressionS3TC}</td><td>see {@link EXTTextureCompressionLATC}</td></tr><tr><td>see {@link ATITextureCompression3DC}</td><td>see {@link KHRTextureCompressionASTCLDR}</td></tr></table>
     * @param data    a pointer to the compressed image data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCompressedTextureSubImage2D">Reference Page</a>
     */
    fun compressedSubImage2D(level: Int, offset: Vec2i, size: Vec2i, format: Int, data: ByteBuffer) = gl.compressedTexSubImage2D(this, level, offset, size, format, data)

    // --- [ glCompressedTextureSubImage3D ] ---

    /**
     * DSA version of {@link GL13C#glCompressedTexSubImage3D CompressedTexSubImage3D}.
     *
     * @param level   the level-of-detail number. Level 0 is the base image level. Level n is the nth mipmap reduction image.
     * @param offset  a texel offset in the x, y, z direction within the texture array
     * @param size    the size of the texture subimage
     * @param format  the format of the compressed image data stored at address {@code data}. One of:<br><table><tr><td>{@link GL30#GL_COMPRESSED_RED_RGTC1 COMPRESSED_RED_RGTC1}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RED_RGTC1 COMPRESSED_SIGNED_RED_RGTC1}</td></tr><tr><td>{@link GL30#GL_COMPRESSED_RG_RGTC2 COMPRESSED_RG_RGTC2}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RG_RGTC2 COMPRESSED_SIGNED_RG_RGTC2}</td></tr><tr><td>{@link GL42#GL_COMPRESSED_RGBA_BPTC_UNORM COMPRESSED_RGBA_BPTC_UNORM}</td><td>{@link GL42#GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM COMPRESSED_SRGB_ALPHA_BPTC_UNORM}</td></tr><tr><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT COMPRESSED_RGB_BPTC_SIGNED_FLOAT}</td><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGB8_ETC2 COMPRESSED_RGB8_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ETC2 COMPRESSED_SRGB8_ETC2}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGBA8_ETC2_EAC COMPRESSED_RGBA8_ETC2_EAC}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC COMPRESSED_SRGB8_ALPHA8_ETC2_EAC}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_R11_EAC COMPRESSED_R11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_R11_EAC COMPRESSED_SIGNED_R11_EAC}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RG11_EAC COMPRESSED_RG11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_RG11_EAC COMPRESSED_SIGNED_RG11_EAC}</td></tr><tr><td>see {@link EXTTextureCompressionS3TC}</td><td>see {@link EXTTextureCompressionLATC}</td></tr><tr><td>see {@link ATITextureCompression3DC}</td><td>see {@link KHRTextureCompressionASTCLDR}</td></tr></table>
     * @param data    a pointer to the compressed image data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCompressedTextureSubImage3D">Reference Page</a>
     */
    fun compressedSubImage3D(level: Int, offset: Vec3i, size: Vec3i, format: Int, data: ByteBuffer) = gl.compressedTexSubImage3D(this, level, offset, size, format, data)

    // --- [ glCopyTextureSubImage1D ] ---

    /**
     * DSA version of {@link GL11C#glCopyTexSubImage1D CopyTexSubImage1D}.
     *
     * @param level             the level-of-detail number
     * @param xoffset           the left texel coordinate of the texture subregion to update
     * @param leftLowerPixel    the leftLower framebuffer pixel coordinate
     * @param width             the texture subregion width
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCopyTextureSubImage1D">Reference Page</a>
     */
    fun copySubImage1D(level: Int, offset: Int, leftLowerPixel: Vec2i, width: Int) = gl.copyTexSubImage1D(this, level, offset, leftLowerPixel, width)

    // --- [ glCopyTextureSubImage2D ] ---

    /**
     * DSA version of {@link GL11C#glCopyTexSubImage2D CopyTexSubImage2D}.
     *
     * @param level             the level-of-detail number
     * @param offset            the left-lower texel coordinate of the texture subregion to update
     * @param leftLowerPixel    the left-lower framebuffer pixel coordinate
     * @param size              the texture subregion size
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCopyTextureSubImage2D">Reference Page</a>
     */
    fun copySubImage2D(level: Int, offset: Vec2i, leftLowerPixel: Vec2i, size: Vec2i) = gl.copyTexSubImage2D(this, level, offset, leftLowerPixel, size)

    // --- [ glCopyTextureSubImage3D ] ---

    /**
     * DSA version of {@link GL12C#glCopyTexSubImage3D CopyTexSubImage3D}.
     *
     * @param level             the level-of-detail number
     * @param offset            the x, y, z coordinate of the texture subregion to update
     * @param leftLowerPixel    the left framebuffer pixel coordinate
     * @param size              the texture subregion size
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCopyTextureSubImage3D">Reference Page</a>
     */
    fun copySubImage3D(level: Int, offset: Vec3i, leftLowerPixel: Vec2i, size: Vec2i) = gl.copyTexSubImage3D(this, level, offset, leftLowerPixel, size)

    // --- [ glIsTexture ] ---

    val isValid: Boolean
        get() = GL20C.glIsTexture(name)

    // --- [ glGenerateTextureMipmap ] ---

    /**
     * DSA version of {@link GL30C#glGenerateMipmap GenerateMipmap}.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenerateTextureMipmap">Reference Page</a>
     */
    fun generateMipmap() = gl.generateMipmap(this)

    // --- [ glGetCompressedTextureImage ] ---

    fun getCompressedImage(level: Int, pixels: ByteBuffer) = gl.getCompressedTexImage(this, level, pixels)

    // --- [ glGetTextureLevelParameterfv / glGetTextureLevelParameteriv ] ---

    inline fun <reified T> getLevelParameter(level: Int, name: TexLevelParameter): T = gl.getTexLevelParameter(this, level, name)

    // --- [ glGetTextureParameterfv / glGetTextureParameteriv / glGetTextureParameterIiv / glGetTextureParameterIuiv ] ---

    inline fun <reified T> getParameter(name: TexParameter): T = gl.getTexParameter(this, name)

    // glGetTexLevelParameter

    fun getWidth(target: TextureTarget, level: Int = 0): Int = gl.getTexLevelParameter(target, level, TexLevelParameter(GL11.GL_TEXTURE_WIDTH))
    fun getHeight(target: TextureTarget, level: Int = 0): Int = gl.getTexLevelParameter(target, level, TexLevelParameter(GL11.GL_TEXTURE_HEIGHT))
    fun getDepth(target: TextureTarget, level: Int = 0): Int = gl.getTexLevelParameter(target, level, TexLevelParameter(GL12.GL_TEXTURE_DEPTH))
    /** JVM custom */
    fun getSize(target: TextureTarget, level: Int = 0): Vec3i = Vec3i(getWidth(target, level), getHeight(target, level), getDepth(target, level))

    fun getInternalFormat(target: TextureTarget, level: Int = 0): gli_.gl.InternalFormat = gli_.gl.InternalFormat of gl.getTexLevelParameter(target, level, TexLevelParameter(GL11.GL_TEXTURE_INTERNAL_FORMAT))
    fun getRedSize(target: TextureTarget, level: Int = 0): Int = gl.getTexLevelParameter(target, level, TexLevelParameter(GL11.GL_TEXTURE_RED_SIZE))
    fun getGreenSize(target: TextureTarget, level: Int = 0): Int = gl.getTexLevelParameter(target, level, TexLevelParameter(GL11.GL_TEXTURE_GREEN_SIZE))
    fun getBlueSize(target: TextureTarget, level: Int = 0): Int = gl.getTexLevelParameter(target, level, TexLevelParameter(GL11.GL_TEXTURE_BLUE_SIZE))
    fun getAlphaSize(target: TextureTarget, level: Int = 0): Int = gl.getTexLevelParameter(target, level, TexLevelParameter(GL11.GL_TEXTURE_ALPHA_SIZE))
    /** JVM custom */
    fun getRgbSize(target: TextureTarget, level: Int = 0): Vec3i = Vec3i(getRedSize(target, level), getGreenSize(target, level), getBlueSize(target, level))

    /** JVM custom */
    fun getRgbaSize(target: TextureTarget, level: Int = 0): Vec4i = Vec4i(getRedSize(target, level), getGreenSize(target, level), getBlueSize(target, level), getAlphaSize(target, level))

    fun getDepthSize(target: TextureTarget, level: Int = 0): Int = gl.getTexLevelParameter(target, level, TexLevelParameter(GL30.GL_TEXTURE_DEPTH_SIZE))
    fun getRedType(target: TextureTarget, level: Int = 0): Int = gl.getTexLevelParameter(target, level, TexLevelParameter(GL30.GL_TEXTURE_RED_TYPE))
    fun getGreenType(target: TextureTarget, level: Int = 0): Int = gl.getTexLevelParameter(target, level, TexLevelParameter(GL30.GL_TEXTURE_GREEN_TYPE))
    fun getBlueType(target: TextureTarget, level: Int = 0): Int = gl.getTexLevelParameter(target, level, TexLevelParameter(GL30.GL_TEXTURE_BLUE_TYPE))
    fun getAlphaType(target: TextureTarget, level: Int = 0): Int = gl.getTexLevelParameter(target, level, TexLevelParameter(GL30.GL_TEXTURE_ALPHA_TYPE))
    /** JVM custom */
    fun getRgbType(target: TextureTarget, level: Int = 0): Vec3i = Vec3i(getRedType(target, level), getGreenType(target, level), getBlueType(target, level))

    /** JVM custom */
    fun getRgbaType(target: TextureTarget, level: Int = 0): Vec4i = Vec4i(getRedType(target, level), getGreenType(target, level), getBlueType(target, level), getAlphaType(target, level))

    fun getDepthType(target: TextureTarget, level: Int = 0): Int = gl.getTexLevelParameter(target, level, TexLevelParameter(GL30.GL_TEXTURE_DEPTH_TYPE))
    fun getCompressed(target: TextureTarget, level: Int = 0): Boolean = gl.getTexLevelParameter(target, level, TexLevelParameter(GL30.GL_TEXTURE_COMPRESSED))
    fun getCompressedImageSize(target: TextureTarget, level: Int = 0): Int = gl.getTexLevelParameter(target, level, TexLevelParameter(GL13.GL_TEXTURE_COMPRESSED_IMAGE_SIZE))
    fun getBufferOffset(target: TextureTarget, level: Int = 0): Int = gl.getTexLevelParameter(target, level, TexLevelParameter(GL43.GL_TEXTURE_BUFFER_OFFSET))
    fun getBufferSize(target: TextureTarget, level: Int = 0): Int = gl.getTexLevelParameter(target, level, TexLevelParameter(GL43.GL_TEXTURE_BUFFER_SIZE))

    // glGetTexParameter / glTexParameter (setter)

    fun getMagFilter(target: TextureTarget): MagFilter = MagFilter(gl.getTexParameter(target, TexParameter(GL11.GL_TEXTURE_MAG_FILTER)))
    fun setMagFilter(target: TextureTarget, filter: MagFilter) = gl.texParameter(target, TexParameter(GL11.GL_TEXTURE_MAG_FILTER), filter.i)

    fun getMinFilter(target: TextureTarget): MinFilter = MinFilter(gl.getTexParameter(target, TexParameter(GL11.GL_TEXTURE_MIN_FILTER)))
    fun setMinFilter(target: TextureTarget, filter: MinFilter) = gl.texParameter(target, TexParameter(GL11.GL_TEXTURE_MIN_FILTER), filter.i)
    /** JVM custom */
    fun getFilters(target: TextureTarget): Pair<MinFilter, MagFilter> = getMinFilter(target) to getMagFilter(target)

    fun setFilters(target: TextureTarget, min: MinFilter, mag: MagFilter) {
        setMinFilter(target, min)
        setMagFilter(target, mag)
    }

    fun getMinLod(target: TextureTarget): Float = gl.getTexParameter(target, TexParameter(GL12.GL_TEXTURE_MIN_LOD))
    fun setMinLod(target: TextureTarget, lod: Float) = gl.texParameter(target, TexParameter(GL12.GL_TEXTURE_MIN_LOD), lod)

    fun getMaxLod(target: TextureTarget): Float = gl.getTexParameter(target, TexParameter(GL12.GL_TEXTURE_MAX_LOD))
    fun setMaxLod(target: TextureTarget, lod: Float) = gl.texParameter(target, TexParameter(GL12.GL_TEXTURE_MAX_LOD), lod)
    /** JVM custom */
    fun setMinMaxLod(target: TextureTarget, min: Float, max: Float) {
        setMinLod(target, min)
        setMaxLod(target, max)
    }

    fun getBaseLevel(target: TextureTarget): Int = gl.getTexParameter(target, TexParameter(GL12.GL_TEXTURE_BASE_LEVEL))
    fun setBaseLevel(target: TextureTarget, level: Int) = gl.texParameter(target, TexParameter(GL12.GL_TEXTURE_BASE_LEVEL), level)
    fun getMaxLevel(target: TextureTarget): Int = gl.getTexParameter(target, TexParameter(GL12.GL_TEXTURE_MAX_LEVEL))
    fun setMaxLevel(target: TextureTarget, level: Int) = gl.texParameter(target, TexParameter(GL12.GL_TEXTURE_MAX_LEVEL), level)
    /** JVM custom */
    fun setBaseMaxLevel(target: TextureTarget, base: Int, max: Int) {
        setBaseLevel(target, base)
        setMaxLevel(target, max)
    }

    fun getBaseMaxLevel(target: TextureTarget) = getBaseLevel(target) to getMaxLevel(target)

    fun getSwizzleR(target: TextureTarget): TextureSwizzle = TextureSwizzle(gl.getTexParameter(target, TexParameter(GL33.GL_TEXTURE_SWIZZLE_R)))
    fun setSwizzleR(target: TextureTarget, r: TextureSwizzle) = gl.texParameter(target, TexParameter(GL33.GL_TEXTURE_SWIZZLE_R), r.i)
    fun getSwizzleG(target: TextureTarget): TextureSwizzle = TextureSwizzle(gl.getTexParameter(target, TexParameter(GL33.GL_TEXTURE_SWIZZLE_G)))
    fun setSwizzleG(target: TextureTarget, g: TextureSwizzle) = gl.texParameter(target, TexParameter(GL33.GL_TEXTURE_SWIZZLE_G), g.i)
    fun getSwizzleB(target: TextureTarget): TextureSwizzle = TextureSwizzle(gl.getTexParameter(target, TexParameter(GL33.GL_TEXTURE_SWIZZLE_B)))
    fun setSwizzleB(target: TextureTarget, b: TextureSwizzle) = gl.texParameter(target, TexParameter(GL33.GL_TEXTURE_SWIZZLE_B), b.i)
    fun getSwizzleA(target: TextureTarget): TextureSwizzle = TextureSwizzle(gl.getTexParameter(target, TexParameter(GL33.GL_TEXTURE_SWIZZLE_A)))
    fun setSwizzleA(target: TextureTarget, a: TextureSwizzle) = gl.texParameter(target, TexParameter(GL33.GL_TEXTURE_SWIZZLE_A), a.i)
    fun getSwizzleRGBA(target: TextureTarget): Vec4i = Vec4i(getSwizzleR(target).i, getSwizzleG(target).i, getSwizzleB(target).i, getSwizzleA(target).i)
    fun setSwizzleRGBA(target: TextureTarget, swizzle: Vec4i) {
        setSwizzleR(target, TextureSwizzle(swizzle.r))
        setSwizzleG(target, TextureSwizzle(swizzle.g))
        setSwizzleB(target, TextureSwizzle(swizzle.b))
        setSwizzleA(target, TextureSwizzle(swizzle.a))
    }

    fun getWrapS(target: TextureTarget): TextureWrapMode = TextureWrapMode(gl.getTexParameter(target, TexParameter(GL11.GL_TEXTURE_WRAP_S)))
    fun setWrapS(target: TextureTarget, wrap: TextureWrapMode) = gl.texParameter(target, TexParameter(GL11.GL_TEXTURE_WRAP_S), wrap.i)
    fun getWrapT(target: TextureTarget): TextureWrapMode = TextureWrapMode(gl.getTexParameter(target, TexParameter(GL11.GL_TEXTURE_WRAP_T)))
    fun setWrapT(target: TextureTarget, wrap: TextureWrapMode) = gl.texParameter(target, TexParameter(GL11.GL_TEXTURE_WRAP_T), wrap.i)
    fun getWrapR(target: TextureTarget): TextureWrapMode = TextureWrapMode(gl.getTexParameter(target, TexParameter(GL12.GL_TEXTURE_WRAP_R)))
    fun setWrapR(target: TextureTarget, wrap: TextureWrapMode) = gl.texParameter(target, TexParameter(GL12.GL_TEXTURE_WRAP_R), wrap.i)
    /** JVM custom */
    fun getWrapST(target: TextureTarget): Vec2i = Vec2i(getWrapS(target).i, getWrapT(target).i)

    fun setWrapST(target: TextureTarget, wrap: TextureWrapMode) {
        setWrapS(target, wrap)
        setWrapT(target, wrap)
    }

    /** JVM custom */
    fun getWrapSTR(target: TextureTarget): Vec3i = Vec3i(getWrapS(target).i, getWrapT(target).i, getWrapR(target).i)
    fun setWrapSTR(target: TextureTarget, wrap: TextureWrapMode) {
            setWrapS(target, wrap)
            setWrapT(target, wrap)
            setWrapR(target, wrap)
        }

    fun getBorderColor(target: TextureTarget): Vec4 = Stack.vec4Address { GL11C.nglGetTexParameterfv(target.i, GL11.GL_TEXTURE_BORDER_COLOR, it) }
    fun setBorderColor(target: TextureTarget, color: Vec4) = Stack.vec4Address(color) { GL11C.nglTexParameterfv(target.i, GL11.GL_TEXTURE_BORDER_COLOR, it) }

    fun getCompareMode(target: TextureTarget): TextureCompareMode = TextureCompareMode(gl.getTexParameter(target, TexParameter(GL14.GL_TEXTURE_COMPARE_MODE)))
    fun setCompareMode(target: TextureTarget, mode: TextureCompareMode) = gl.texParameter(target, TexParameter(GL14.GL_TEXTURE_COMPARE_MODE), mode.i)
    fun getCompareFunc(target: TextureTarget): CompareFunction = CompareFunction(gl.getTexParameter(target, TexParameter(GL14.GL_TEXTURE_COMPARE_FUNC)))
    fun setCompareFunc(target: TextureTarget, mode: CompareFunction) = gl.texParameter(target, TexParameter(GL14.GL_TEXTURE_COMPARE_FUNC), mode.i)
    fun getCompare(target: TextureTarget): Pair<CompareFunction, TextureCompareMode> = getCompareFunc(target) to getCompareMode(target)
    fun setCompare(target: TextureTarget, mode: TextureCompareMode, func: CompareFunction) {
        setCompareMode(target, mode)
        setCompareFunc(target, func)
    }
    fun getViewMinLevel(target: TextureTarget): Int = gl.getTexParameter(target, TexParameter(GL43.GL_TEXTURE_VIEW_MIN_LEVEL))
    fun setViewMinLevel(target: TextureTarget, level: Int) = gl.texParameter(target, TexParameter(GL43.GL_TEXTURE_VIEW_MIN_LEVEL), level)
    fun getViewNumLevels(target: TextureTarget): Int = gl.getTexParameter(target, TexParameter(GL43.GL_TEXTURE_VIEW_NUM_LEVELS))
    fun setViewNumLevels(target: TextureTarget, levels: Int) = gl.texParameter(target, TexParameter(GL43.GL_TEXTURE_VIEW_NUM_LEVELS), levels)
    fun getViewMinLayer(target: TextureTarget): Int = gl.getTexParameter(target, TexParameter(GL43.GL_TEXTURE_VIEW_MIN_LAYER))
    fun setViewMinLayer(target: TextureTarget, layer: Int) = gl.texParameter(target, TexParameter(GL43.GL_TEXTURE_VIEW_MIN_LAYER), layer)
    fun getViewNumLayers(target: TextureTarget): Int = gl.getTexParameter(target, TexParameter(GL43.GL_TEXTURE_VIEW_NUM_LAYERS))
    fun setViewNumLayers(target: TextureTarget, layers: Int) = gl.texParameter(target, TexParameter(GL43.GL_TEXTURE_VIEW_NUM_LAYERS), layers)

    fun getImageFormatCompatibilityType(target: TextureTarget): ImageFormatCompatibilityType = ImageFormatCompatibilityType(gl.getTexParameter(target, TexParameter(GL42.GL_IMAGE_FORMAT_COMPATIBILITY_TYPE)))

    fun getTextureImmutableFormat(target: TextureTarget): Boolean = gl.getTexParameter(target, TexParameter(GL42.GL_TEXTURE_IMMUTABLE_FORMAT))

    /** Tricky, just use with GL >= 4.5, https://stackoverflow.com/a/38308602/1047713 */
    val textureTarget: TextureTarget
        get() = TODO()

    fun bind(target: TextureTarget) = GL11C.glBindTexture(target.i, name)
    fun bind(target: TextureTarget, unit: Int) {
        GL13C.glActiveTexture(GL13C.GL_TEXTURE0 + unit)
        GL11C.glBindTexture(target.i, name)
    }
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

    fun delete() = gl.deleteTextures(this)

    // --- [ glGetTextureImage ] ---

    fun getImage(level: Int, format: TextureFormat2, type: TextureType2, pixels: ByteBuffer) = gl.getTexImage(this, level, format, type, pixels)

    // --- [ glGetnTexImage ] ---

    fun getnImage(level: Int, format: TextureFormat2, type: TextureType2, img: ByteBuffer) = gl.getnTexImage(this, level, format, type, img)

    // --- [ glGetTextureSubImage ] ---

    fun getTextureSubImage(level: Int, offset: Vec3i, size: Vec3i, format: TextureFormat3, type: TextureType2, pixels: ByteBuffer) = gl.getTextureSubImage(this, level, offset, size, format, type, pixels)

    // --- [ glGetCompressedTextureSubImage ] ---

    fun getCompressedTextureSubImage(level: Int, offset: Vec3i, size: Vec3i, pixels: ByteBuffer) = gl.getCompressedTextureSubImage(this, level, offset, size, pixels)


    // --- [ glInvalidateTexSubImage ] ---

    /**
     * Invalidates a region of a texture image.
     *
     * @param level   the level of detail of the texture object within which the region resides
     * @param offset  the offset of the region to be invalidated
     * @param size    the size of the region to be invalidated
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glInvalidateTexSubImage">Reference Page</a>
     */
    fun invalidateSubImage(level: Int, offset: Vec3i, size: Vec3i) = gl.invalidateTexSubImage(this, level, offset, size)

    // --- [ glInvalidateTexImage ] ---

    /**
     * Invalidates the entirety of a texture image.
     *
     * @param level   the level of detail of the texture object to invalidate
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glInvalidateTexImage">Reference Page</a>
     */
    infix fun invalidateImage(level: Int) = gl.invalidateTexImage(this, level)

    // --- [ glTextureBuffer ] ---

    /**
     * DSA version of {@link GL31C#glTexBuffer TexBuffer}.
     *
     * @param internalFormat the sized internal format of the data in the store belonging to {@code buffer}
     * @param buffer         the name of the buffer object whose storage to attach to the active buffer texture
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureBuffer">Reference Page</a>
     */
    fun buffer(internalFormat: InternalFormat, buffer: GlBuffer) = gl.texBuffer(this, internalFormat, buffer)

    // --- [ glTextureBufferRange ] ---

    /**
     * DSA version of {@link GL43C#glTexBufferRange TexBufferRange}.
     *
     * @param internalFormat the internal format of the data in the store belonging to {@code buffer}
     * @param buffer         the name of the buffer object whose storage to attach to the active buffer texture
     * @param offset         the offset of the start of the range of the buffer's data store to attach
     * @param size           the size of the range of the buffer's data store to attach
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureBufferRange">Reference Page</a>
     */
    fun bufferRange(internalFormat: InternalFormat, buffer: GlBuffer, offset: Int, size: Int) = gl.texBufferRange(this, internalFormat, buffer, offset, size)

    // --- [ glTextureParameterf ] ---

    /**
     * DSA version of {@link GL11C#glTexParameterf TexParameterf}.
     *
     * @param name   the parameter to set
     * @param param   the parameter value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureParameterf">Reference Page</a>
     */
    fun parameter(name: TexParameter, param: Float) = gl.texParameter(this, name, param)

    // --- [ glTextureParameteri ] ---

    /**
     * DSA version of {@link GL11C#glTexParameteri TexParameteri}.
     *
     * @param name   the parameter to set. One of:<br><table><tr><td>{@link GL12#GL_TEXTURE_BASE_LEVEL TEXTURE_BASE_LEVEL}</td><td>{@link GL11#GL_TEXTURE_BORDER_COLOR TEXTURE_BORDER_COLOR}</td><td>{@link GL14#GL_TEXTURE_COMPARE_MODE TEXTURE_COMPARE_MODE}</td><td>{@link GL14#GL_TEXTURE_COMPARE_FUNC TEXTURE_COMPARE_FUNC}</td></tr><tr><td>{@link GL14#GL_TEXTURE_LOD_BIAS TEXTURE_LOD_BIAS}</td><td>{@link GL11#GL_TEXTURE_MAG_FILTER TEXTURE_MAG_FILTER}</td><td>{@link GL12#GL_TEXTURE_MAX_LEVEL TEXTURE_MAX_LEVEL}</td><td>{@link GL12#GL_TEXTURE_MAX_LOD TEXTURE_MAX_LOD}</td></tr><tr><td>{@link GL11#GL_TEXTURE_MIN_FILTER TEXTURE_MIN_FILTER}</td><td>{@link GL12#GL_TEXTURE_MIN_LOD TEXTURE_MIN_LOD}</td><td>{@link GL33#GL_TEXTURE_SWIZZLE_R TEXTURE_SWIZZLE_R}</td><td>{@link GL33#GL_TEXTURE_SWIZZLE_G TEXTURE_SWIZZLE_G}</td></tr><tr><td>{@link GL33#GL_TEXTURE_SWIZZLE_B TEXTURE_SWIZZLE_B}</td><td>{@link GL33#GL_TEXTURE_SWIZZLE_A TEXTURE_SWIZZLE_A}</td><td>{@link GL33#GL_TEXTURE_SWIZZLE_RGBA TEXTURE_SWIZZLE_RGBA}</td><td>{@link GL11#GL_TEXTURE_WRAP_S TEXTURE_WRAP_S}</td></tr><tr><td>{@link GL11#GL_TEXTURE_WRAP_T TEXTURE_WRAP_T}</td><td>{@link GL12#GL_TEXTURE_WRAP_R TEXTURE_WRAP_R}</td><td>{@link GL14#GL_DEPTH_TEXTURE_MODE DEPTH_TEXTURE_MODE}</td><td>{@link GL14#GL_GENERATE_MIPMAP GENERATE_MIPMAP}</td></tr></table>
     * @param param   the parameter value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureParameteri">Reference Page</a>
     */
    fun parameter(name: TexParameter, param: Int) = gl.texParameter(this, name, param)

    // --- [ glTextureParameterIiv ] ---

    /**
     * DSA version of {@link GL30C#glTexParameterIiv TexParameterIiv}.
     *
     * @param name   the symbolic name of a single-valued texture parameter
     * @param param  the value of {@code name}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureParameter">Reference Page</a>
     */
    fun parameterI(name: TexParameter, param: Vec4i) = gl.texParameterI(this, name, param)

    // --- [ glTextureParameterIuiv ] ---

    /**
     * DSA version of {@link GL30C#glTexParameterIuiv TexParameterIuiv}.
     *
     * @param name   the symbolic name of a single-valued texture parameter
     * @param param  the value of {@code name}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureParameter">Reference Page</a>
     */
    fun parameterI(name: TexParameter, param: Vec4ui) = gl.texParameterI(this, name, param)

    // --- [ glTextureStorage1D ] ---

    /**
     * DSA version of {@link GL42C#glTexStorage1D TexStorage1D}.
     *
     * @param levels         the number of texture levels
     * @param internalFormat the sized internal format to be used to store texture image data
     * @param width          the width of the texture, in texels
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureStorage1D">Reference Page</a>
     */
    fun storage1D(levels: Int, internalFormat: gli_.gl.InternalFormat, width: Int) = gl.texStorage1D(this, levels, internalFormat, width)

    // --- [ glTextureStorage2D ] ---

    /**
     * DSA version of {@link GL42C#glTexStorage2D TexStorage2D}.
     *
     * @param levels         the number of texture levels
     * @param internalFormat the sized internal format to be used to store texture image data
     * @param size           the size of the texture, in texels
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureStorage2D">Reference Page</a>
     */
    fun storage2D(levels: Int, internalFormat: gli_.gl.InternalFormat, size: Vec2i) = gl.texStorage2D(this, levels, internalFormat, size)

    // --- [ glTextureStorage3D ] ---

    /**
     * DSA version of {@link GL42C#glTexStorage3D TexStorage3D}.
     *
     * @param levels         the number of texture levels
     * @param internalFormat the sized internal format to be used to store texture image data
     * @param size           the size of the texture, in texels
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureStorage3D">Reference Page</a>
     */
    fun storage3D(levels: Int, internalFormat: gli_.gl.InternalFormat, size: Vec3i) = gl.texStorage3D(this, levels, internalFormat, size)

    // --- [ glTextureStorage2DMultisample ] ---

    /**
     * DSA version of {@link GL43C#glTexStorage2DMultisample TexStorage2DMultisample}.
     *
     * @param samples              the number of samples in the texture
     * @param internalFormat       the sized internal format to be used to store texture image data
     * @param width                the width of the texture, in texels
     * @param height               the height of the texture, in texels
     * @param fixedSampleLocations whether the image will use identical sample locations and the same number of samples for all texels in the image, and the sample locations will not
     *                             depend on the internal format or size of the image
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureStorage2DMultisample">Reference Page</a>
     */
    fun storage2dMS(samples: Int, internalFormat: gli_.gl.InternalFormat, size: Vec2i, fixedSampleLocations: Boolean) = gl.texStorage2dMS(this, samples, internalFormat, size, fixedSampleLocations)

    // --- [ glTextureStorage3DMultisample ] ---

    /**
     * DSA version of {@link GL43C#glTexStorage3DMultisample TexStorage3DMultisample}.
     *
     * @param samples              the number of samples in the texture
     * @param internalFormat       the sized internal format to be used to store texture image data
     * @param width                the width of the texture, in texels
     * @param height               the height of the texture, in texels
     * @param depth                the depth of the texture, in texels
     * @param fixedsamplelocations whether the image will use identical sample locations and the same number of samples for all texels in the image, and the sample locations will not
     *                             depend on the internal format or size of the image
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureStorage3DMultisample">Reference Page</a>
     */
    fun storage3dMS(samples: Int, internalFormat: gli_.gl.InternalFormat, size: Vec3i, fixedSampleLocations: Boolean) = gl.texStorage3dMS(this, samples, internalFormat, size, fixedSampleLocations)

    // --- [ glTextureSubImage1D ] ---

    /**
     * DSA version of {@link GL11C#glTexSubImage1D TexSubImage1D}.
     *
     * @param level   the level-of-detail-number
     * @param offset  the left coordinate of the texel subregion
     * @param width   the subregion width
     * @param format  the pixel data format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
     * @param type    the pixel data type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
     * @param pixels  the pixel data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureSubImage1D">Reference Page</a>
     */
    fun subImage1D(level: Int, offset: Int, width: Int, format: Int, type: Int, pixels: Buffer) = gl.texSubImage1D(this, level, offset, width, format, type, pixels)

    // --- [ glTextureSubImage2D ] ---

    /**
     * DSA version of {@link GL11C#glTexSubImage2D TexSubImage2D}.
     *
     * @param level   the level-of-detail-number
     * @param offset  the coordinate of the texel subregion [left, bottom]
     * @param size    the subregion size
     * @param format  the pixel data format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
     * @param type    the pixel data type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
     * @param pixels  the pixel data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureSubImage2D">Reference Page</a>
     */
    fun subImage2D(level: Int, offset: Vec2i, size: Vec2i, format: Int, type: Int, pixels: Buffer) = gl.texSubImage2D(this, level, offset, size, format, type, pixels)

    // --- [ glTextureSubImage3D ] ---

    /**
     * DSA version of {@link GL12C#glTexSubImage3D TexSubImage3D}.
     *
     * @param level   the level-of-detail-number
     * @param offset  the coordinate of the texel subregion
     * @param size    the subregion size
     * @param format  the pixel data format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
     * @param type    the pixel data type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
     * @param pixels  the pixel data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureSubImage3D">Reference Page</a>
     */
    fun subImage3D(level: Int, offset: Vec3i, size: Vec3i, format: Int, type: Int, pixels: Buffer) = gl.texSubImage3D(this, level, offset, size, format, type, pixels)

    companion object {

        // --- [ glCreateTextures ] ---

        infix fun create(target: TextureTarget): GlTexture = gl.createTextures(target)

        fun gen(): GlTexture = gl.genTexture()
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

    companion object {

        // --- [ glGenTextures ] ---

        fun gen(size: Int): GlTextures = gl.genTextures(size)
    }
}