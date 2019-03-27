package gln.objects


import glm_.vec2.Vec2i
import glm_.vec3.Vec3i
import glm_.vec4.Vec4
import glm_.vec4.Vec4i
import gln.*
import gln.texture.GlTextureDsl
import kool.stak
import org.lwjgl.opengl.*
import java.nio.Buffer
import java.nio.ByteBuffer


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

    // --- [ glIsTexture ] ---

    val isValid: Boolean
        get() = GL20C.glIsTexture(name)

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

    fun getBorderColor(target: TextureTarget): Vec4 = stak.vec4Address { GL11C.nglGetTexParameterfv(target.i, GL11.GL_TEXTURE_BORDER_COLOR, it) }
    fun setBorderColor(target: TextureTarget, color: Vec4) = stak.vec4Address(color) { GL11C.nglTexParameterfv(target.i, GL11.GL_TEXTURE_BORDER_COLOR, it) }

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

    companion object {

        // --- [ glCreateTextures ] ---

        /**
         * Returns {@code n} previously unused texture names in {@code textures}, each representing a new texture object.
         *
         * @param target the texture target. One of:<br><table><tr><td>{@link GL11#GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link GL11#GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL40#GL_TEXTURE_CUBE_MAP_ARRAY TEXTURE_CUBE_MAP_ARRAY}</td><td>{@link GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE TEXTURE_2D_MULTISAMPLE}</td></tr><tr><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE_ARRAY TEXTURE_2D_MULTISAMPLE_ARRAY}</td></tr></table>
         *
         * @see <a target="_blank" href="http://docs.gl/gl4/glCreateTextures">Reference Page</a>
         */
        infix fun createTextures(target: TextureTarget): GlTexture = gl.createTextures(target)

        fun gen(): GlTexture = gl.genTexture()
    }
}