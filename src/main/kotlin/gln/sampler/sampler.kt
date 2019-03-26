package gln.sampler

import glm_.vec4.Vec4
import gln.*
import gln.texture.TexWrap
import kool.*
import org.lwjgl.opengl.GL12C
import org.lwjgl.opengl.GL14C
import org.lwjgl.opengl.GL33C
import org.lwjgl.opengl.GL45C
import java.nio.IntBuffer

fun GlSamplers(size: Int): GlSamplers = GlSamplers(IntBuffer(size))

inline class GlSamplers(val names: IntBuffer) {
    inline val rem: Int
        get() = names.rem
    inline val adr: Adr
        get() = names.adr

    // --- [ glBindSamplers ] ---

    fun bind(first: Int = 0) = gl.bindSamplers(first, this)

    // --- [ glDeleteSamplers ] ---

    /**
     * Deletes named sampler objects.
     *
     * @param samplers an array of sampler objects to be deleted
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteSamplers">Reference Page</a>
     */
    fun delete() = GL33C.nglDeleteSamplers(rem, adr)
}

inline class GlSampler(val name: Int = -1) {

//    fun bind() = GL30C.glBindFramebuffer(GL30C.GL_FRAMEBUFFER, name)
//
//    fun bindRead() = GL30C.glBindFramebuffer(GL30C.GL_READ_FRAMEBUFFER, name)
//    fun bindDraw() = GL30C.glBindFramebuffer(GL30C.GL_DRAW_FRAMEBUFFER, name)
//
//    inline fun bind(block: Framebuffer.() -> Unit) {
//        GL30C.glBindFramebuffer(GL30C.GL_FRAMEBUFFER, name)
//        Framebuffer.name = name
//        Framebuffer.block()
//        GL30C.glBindFramebuffer(GL30C.GL_FRAMEBUFFER, 0)
//    }

    var minFiler: MinFilter
        get() = MinFilter(GL33C.glGetSamplerParameteri(name, GL12C.GL_TEXTURE_MIN_FILTER))
        set(value) = GL33C.glSamplerParameteri(name, GL12C.GL_TEXTURE_MIN_FILTER, value.i)
    var magFiler: MagFilter
        get() = MagFilter(GL33C.glGetSamplerParameteri(name, GL12C.GL_TEXTURE_MIN_FILTER))
        set(value) = GL33C.glSamplerParameteri(name, GL12C.GL_TEXTURE_MIN_FILTER, value.i)
    var wrapS: TexWrap
        get() = TexWrap(GL33C.glGetSamplerParameteri(name, GL12C.GL_TEXTURE_WRAP_S))
        set(value) = GL33C.glSamplerParameteri(name, GL12C.GL_TEXTURE_WRAP_S, value.i)
    var wrapT: TexWrap
        get() = TexWrap(GL33C.glGetSamplerParameteri(name, GL12C.GL_TEXTURE_WRAP_T))
        set(value) = GL33C.glSamplerParameteri(name, GL12C.GL_TEXTURE_WRAP_T, value.i)
    var wrapR: TexWrap
        get() = TexWrap(GL33C.glGetSamplerParameteri(name, GL12C.GL_TEXTURE_WRAP_R))
        set(value) = GL33C.glSamplerParameteri(name, GL12C.GL_TEXTURE_WRAP_R, value.i)

    fun setWrapSTR(wrap: TexWrap) {
        wrapS = wrap
        wrapT = wrap
        wrapR = wrap
    }

    var borderColor: Vec4
        get() = stak.vec4Address { GL33C.nglSamplerParameterfv(name, GL12C.GL_TEXTURE_BORDER_COLOR, it) }
        set(value) {
            stak.vec4Address(value) { GL33C.nglSamplerParameterfv(name, GL12C.GL_TEXTURE_BORDER_COLOR, it) }
        }
    var minLod: Float
        get() = GL33C.glGetSamplerParameterf(name, GL12C.GL_TEXTURE_MIN_LOD)
        set(value) = GL33C.glSamplerParameterf(name, GL12C.GL_TEXTURE_MIN_LOD, value)
    var maxLod: Float
        get() = GL33C.glGetSamplerParameterf(name, GL12C.GL_TEXTURE_MAX_LOD)
        set(value) = GL33C.glSamplerParameterf(name, GL12C.GL_TEXTURE_MAX_LOD, value)
    var lodBias: Float
        get() = GL33C.glGetSamplerParameterf(name, GL14C.GL_TEXTURE_LOD_BIAS)
        set(value) = GL33C.glSamplerParameterf(name, GL14C.GL_TEXTURE_LOD_BIAS, value)
    var compareMode: TextureCompareMode
        get() = TextureCompareMode(GL33C.glGetSamplerParameteri(name, GL14C.GL_TEXTURE_COMPARE_MODE))
        set(value) = GL33C.glSamplerParameteri(name, GL14C.GL_TEXTURE_COMPARE_MODE, value.i)
    var compareFunc: CompareFunction
        get() = CompareFunction(GL33C.glGetSamplerParameteri(name, GL14C.GL_TEXTURE_COMPARE_MODE))
        set(value) = GL33C.glSamplerParameteri(name, GL14C.GL_TEXTURE_COMPARE_MODE, value.i)

    // --- [ glBindSampler ] ---

    /**
     * Binds a named sampler to a texturing target.
     *
     * @param unit    the index of the texture unit to which the sampler is bound
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBindSampler">Reference Page</a>
     */
    fun bind(unit: Int) = GL33C.glBindSampler(unit, name)

    /**
     * Binds a named sampler to a texturing target.
     *
     * @param unit    the index of the texture unit to which the sampler is bound
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBindSampler">Reference Page</a>
     */
    inline fun bind(unit: Int, block: GlSamplerDSL.() -> Unit) {
        GL33C.glBindSampler(unit, name)
        GlSamplerDSL.name = name
        GlSamplerDSL.block()
    }

    /**
     * Binds a named sampler to a texturing target.
     *
     * @param unit    the index of the texture unit to which the sampler is bound
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBindSampler">Reference Page</a>
     */
    inline fun bound(unit: Int, block: GlSamplerDSL.() -> Unit) {
        GL33C.glBindSampler(unit, name)
        GlSamplerDSL.name = name
        GlSamplerDSL.block()
        GL33C.glBindSampler(unit, 0)
    }

    // --- [ glDeleteSamplers ] ---

    /**
     * Deletes named sampler objects.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteSamplers">Reference Page</a>
     */
    fun delete() = stak.intAddress(name) { GL33C.nglDeleteSamplers(1, it) }

    // --- [ glIsSampler ] ---

    /**
     * Determines if a name corresponds to a sampler object.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glIsSampler">Reference Page</a>
     */
    val isValid: Boolean
        get() = GL33C.glIsSampler(name)

    /**
     * Determines if a name doesn not correspond to a sampler object.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glIsSampler">Reference Page</a>
     */
    val isInvalid: Boolean
        get() = GL33C.glIsSampler(name)


    companion object {
        // --- [ glGenSamplers ] ---

        /**
         * Generates sampler object names.
         *
         * @param samplers a buffer in which the generated sampler object names are stored
         *
         * @see <a target="_blank" href="http://docs.gl/gl4/glGenSamplers">Reference Page</a>
         */
        fun gen(samplers: GlSamplers) = GL33C.nglGenSamplers(samplers.rem, samplers.adr)

        /**
         * Generates sampler object names.
         *
         * @param samplers a buffer in which the generated sampler object names are stored
         *
         * @see <a target="_blank" href="http://docs.gl/gl4/glGenSamplers">Reference Page</a>
         */
        fun gen(size: Int): GlSamplers = GlSamplers(size).also(::gen)

        /**
         * Generates sampler object names.
         *
         * @see <a target="_blank" href="http://docs.gl/gl4/glGenSamplers">Reference Page</a>
         */
        fun gen() = GlSamplers(stak.intAddress { GL33C.nglGenSamplers(1, it) })

        fun create(): GlSampler = GlSampler(GL45C.glCreateSamplers())
        inline fun create(block: GlSampler.() -> Unit): GlSampler = create().also(block)
    }
}