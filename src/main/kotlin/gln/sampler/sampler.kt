package gln.sampler

import glm_.vec4.Vec4
import gln.*
import gln.texture.TexWrap
import kool.stak
import org.lwjgl.opengl.GL12C
import org.lwjgl.opengl.GL14C
import org.lwjgl.opengl.GL33C
import org.lwjgl.opengl.GL45C

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

    fun delete() = GL33C.glDeleteSamplers(name)

    companion object {
        fun gen(): GlSampler = GlSampler(GL33C.glGenSamplers())
        fun create(): GlSampler = GlSampler(GL45C.glCreateSamplers())
        inline fun create(block: GlSampler.() -> Unit): GlSampler = create().also(block)
    }
}