package gln.program

import gln.GetProgramPipeline
import gln.gl
import gln.identifiers.GlProgram
import kool.Adr
import kool.IntBuffer
import kool.adr
import kool.rem
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

    // --- [ glCreateProgramPipelines ] ---

    fun create() = gl.createProgramPipelines(this)

    companion object {
        // --- [ glCreateProgramPipelines ] ---

        infix fun create(size: Int): GlPipelines = gl.createProgramPipelines(size)
    }
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

    // --- [ glGetProgramPipelineiv ] ---

    /**
     * Retrieves properties of a program pipeline object.
     *
     * @param name    the name of the parameter to retrieve. One of:<br><table><tr><td>{@link #GL_ACTIVE_PROGRAM ACTIVE_PROGRAM}</td><td>{@link GL20#GL_INFO_LOG_LENGTH INFO_LOG_LENGTH}</td><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td></tr><tr><td>{@link GL40#GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td><td>{@link GL40#GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetProgramPipeline">Reference Page</a>
     */
    fun getProgramPipeline(name: GetProgramPipeline) = gl.getProgramPipeline(this, name)

    // --- [ glGetProgramPipelineInfoLog ] ---

    /**
     * Retrieves the info log string from a program pipeline object.
     *
     * @param pipeline the name of a program pipeline object from which to retrieve the info log
     * @param bufSize  the maximum number of characters, including the null terminator, that may be written into {@code infoLog}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetProgramPipelineInfoLog">Reference Page</a>
     */
    fun getInfoLog(bufSize: Int = getProgramPipeline(GetProgramPipeline.INFO_LOG_LENGTH)): String = gl.getProgramPipelineInfoLog(this, bufSize)

    // --- [ glIsProgramPipeline ] ---

    /**
     * Determines if a name corresponds to a program pipeline object.
     *
     * @param pipeline a value that may be the name of a program pipeline object
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glIsProgramPipeline">Reference Page</a>
     */
    val isValid: Boolean
        get() = GL41C.glIsProgramPipeline(name)

    val isInvalid: Boolean
        get() = !GL41C.glIsProgramPipeline(name)

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


    companion object {

        // --- [ glCreateProgramPipelines ] ---

        fun create(): GlPipeline = gl.createProgramPipelines()

        fun gen(): GlPipeline = GlPipeline(GL41C.glGenProgramPipelines())
    }
}