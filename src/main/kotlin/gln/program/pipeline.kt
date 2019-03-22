package gln.program

import gln.gl
import gln.objects.GlProgram
import kool.Adr
import kool.IntBuffer
import kool.adr
import kool.rem
import org.lwjgl.opengl.GL30C
import org.lwjgl.opengl.GL41C
import java.nio.IntBuffer

fun GlPipelines(size: Int) = GlPipelines(IntBuffer(size))

inline class GlPipelines(val names: IntBuffer) {
    inline val rem: Int
        get() = names.rem
    inline val adr: Adr
        get() = names.adr

    // --- [ glDeleteProgramPipelines ] ---

    /**
     * Deletes program pipeline objects.
     *
     * @param pipelines an array of names of program pipeline objects to delete
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteProgramPipelines">Reference Page</a>
     */
    fun delete() = GL41C.nglDeleteProgramPipelines(rem, adr)
}

inline class GlPipeline(val name: Int = 0) {

    // --- [ glActiveShaderProgram ] ---

    /**
     * Sets the active program object for a program pipeline object.
     *
     * @param pipeline the program pipeline object to set the active program object for
     * @param program  the program object to set as the active program pipeline object {@code pipeline}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glActiveShaderProgram">Reference Page</a>
     */
    infix fun activeShaderProgram(program: GlProgram) = gl.activeShaderProgram(this, program)

    // --- [ glBindProgramPipeline ] ---

    /**
     * Binds a program pipeline to the current context.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBindProgramPipeline">Reference Page</a>
     */
    fun bind() = GL41C.glBindProgramPipeline(name)

    // --- [ glUseProgramStages ] ---

    /**
     * Binds stages of a program object to a program pipeline.
     *
     * @param pipeline the program pipeline object to which to bind stages from {@code program}
     * @param stages   a set of program stages to bind to the program pipeline object
     * @param program  the program object containing the shader executables to use in {@code pipeline}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUseProgramStages">Reference Page</a>
     */
    fun useProgramStages(stages: Int, program: GlProgram) = gl.useProgramStages(this, stages, program)

    fun delete() = GL30C.glDeleteFramebuffers(name)

    companion object {
        fun gen(): GlPipeline = GlPipeline(GL41C.glGenProgramPipelines())
    }
}