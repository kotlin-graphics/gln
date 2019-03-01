package gln.program

import gln.framebuffer.GlFramebufferDsl
import gln.objects.GlProgram
import org.lwjgl.opengl.GL30C
import org.lwjgl.opengl.GL41C

inline class GlPipeline(val name: Int = -1) {

    fun bind() = GL30C.glBindFramebuffer(GL30C.GL_FRAMEBUFFER, name)

    fun bindRead() = GL30C.glBindFramebuffer(GL30C.GL_READ_FRAMEBUFFER, name)
    fun bindDraw() = GL30C.glBindFramebuffer(GL30C.GL_DRAW_FRAMEBUFFER, name)

    inline fun bind(block: GlFramebufferDsl.() -> Unit) {
        GL30C.glBindFramebuffer(GL30C.GL_FRAMEBUFFER, name)
        GlFramebufferDsl.name = name
        GlFramebufferDsl.block()
        GL30C.glBindFramebuffer(GL30C.GL_FRAMEBUFFER, 0)
    }

    fun useStages(stages: Int, program: GlProgram): GlPipeline {
        GL41C.glUseProgramStages(name, stages, program.i)
        return this
    }

    fun delete() = GL30C.glDeleteFramebuffers(name)

    companion object {
        fun gen(): GlPipeline = GlPipeline(GL41C.glGenProgramPipelines())
    }
}