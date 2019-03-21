package gln.objects

import glm_.vec3.Vec3i
import gln.*
import gln.program.ProgramBase
import gln.program.ProgramUse
import kool.adr
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL20C
import org.lwjgl.opengl.GL31C
import org.lwjgl.system.MemoryStack.stackGet
import java.nio.IntBuffer

inline class GlProgram(val name: Int) {

    // --- [ glDeleteProgram ] ---

    fun delete() = GL20C.glDeleteProgram(name)

    // --- [ glIsProgram ] ---

    val isValid: Boolean
        get() = GL20C.glIsProgram(name)

    // --- [ glAttachShader ] ---

    infix fun attach(shader: GlShader) = GL20C.glAttachShader(name, shader.name)

    operator fun plusAssign(shader: GlShader) = GL20C.glAttachShader(name, shader.name)

    // --- [ glDetachShader ] ---

    infix fun detach(shader: GlShader) = GL20C.glDetachShader(name, shader.name)

    operator fun minusAssign(shader: GlShader) = GL20C.glDetachShader(name, shader.name)

    // --- [ glShaderSource ] ---

    fun source(source: String) = GL20C.glShaderSource(name, source)

    // --- [ glLinkProgram ] ---

    fun link() = GL20C.glLinkProgram(name)

    // --- [ glUseProgram ] ---

    fun use() = GL20C.glUseProgram(name)

    // JVM custom

    fun unuse() = GL20C.glUseProgram(0)

    inline fun used(block: ProgramUse.() -> Unit) {
        ProgramUse.program = this
        GL20C.glUseProgram(name)
        ProgramUse.block()
        GL20C.glUseProgram(0)
    }

    inline fun use(block: ProgramUse.() -> Unit) {
        ProgramUse.program = this
        GL20C.glUseProgram(name)
        ProgramUse.block()
    }

    // --- [ glValidateProgram ] ---

    fun validate() = GL20C.glValidateProgram(name)

    // --- [ glGetProgramiv ] ---

    val deleteStatus: Boolean
        get() = gl.getProgram(this, GetProgram.DELETE_STATUS)

    val linkStatus: Boolean
        get() = gl.getProgram(this, GetProgram.LINK_STATUS)

    val validateStatus: Boolean
        get() = gl.getProgram(this, GetProgram.VALIDATE_STATUS)

    val infoLogLength: Int
        get() = gl.getProgram(this, GetProgram.INFO_LOG_LENGTH)

    val attachedShadersCount: Int
        get() = gl.getProgram(this, GetProgram.ATTACHED_SHADERS)

    val activeAtomicCounterBuffers: Int
        get() = gl.getProgram(this, GetProgram.ACTIVE_ATOMIC_COUNTER_BUFFERS)

    val activeAttributes: Int
        get() = gl.getProgram(this, GetProgram.ACTIVE_ATTRIBUTES)

    val activeAttributeMaxLength: Int
        get() = gl.getProgram(this, GetProgram.ACTIVE_ATTRIBUTE_MAX_LENGTH)

    val activeUniforms: Int
        get() = gl.getProgram(this, GetProgram.ACTIVE_UNIFORMS)

    val activeUniformMaxLength: Int
        get() = gl.getProgram(this, GetProgram.ACTIVE_UNIFORM_MAX_LENGTH)

    val binaryLength: Int
        get() = gl.getProgram(this, GetProgram.PROGRAM_BINARY_LENGTH)

    val computeWorkGroupSize: Vec3i
        get() = gl.getProgram(this, GetProgram.COMPUTE_WORK_GROUP_SIZE)

    val transformFeedbackBufferMode: Int
        get() = gl.getProgram(this, GetProgram.TRANSFORM_FEEDBACK_BUFFER_MODE)

    val transformFeedbackVaryings: Int
        get() = gl.getProgram(this, GetProgram.TRANSFORM_FEEDBACK_VARYINGS)

    val transformFeedbackVaryingMaxLength: Int
        get() = gl.getProgram(this, GetProgram.TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH)

    val geometryVerticesOut: Int
        get() = gl.getProgram(this, GetProgram.GEOMETRY_VERTICES_OUT)

    val geometryInputType: GeometryInputType
        get() = gl.getProgram(this, GetProgram.GEOMETRY_INPUT_TYPE)

    val geometryOutputType: GeometryOutputType
        get() = gl.getProgram(this, GetProgram.GEOMETRY_OUTPUT_TYPE)

    // --- [ glGetProgramInfoLog ] ---

    val infoLog: String
        get() = gl.getProgramInfoLog(this)

    // --- [ glGetAttachedShaders ] ---

    val attachedShaders: GLshaders
        get() = gl.getAttachedShaders(this)

    // --- [ glGetUniformLocation ] ---

    infix fun getUniformLocation(name: String): Int = gl.getUniformLocation(this, name)
    operator fun get(name: String): Int = gl.getUniformLocation(this, name)

    // --- [ glGetActiveUniform ] ---

    infix fun getActiveUniform(index: Int): Triple<String, Int, UniformType> = gl.getActiveUniform(this, index)

    // --- [ glGetUniform* ] ---

    inline infix fun <reified T> getUniform(location: Int): T = gl.getUniform(this, location)

    // --- [ glGetAttribLocation ] ---
    infix fun getAttribLocation(name: String): Int = GL20.glGetAttribLocation(this.name, name)

    // --- [ glBindAttribLocation ] ---

    fun bindAttribLocation(index: Int, name: String) {
        val stack = stackGet()
        val ptr = stack.pointer
        GL20C.nglBindAttribLocation(this.name, index, stack.ASCII(name).adr)
        stack.pointer = ptr
    }

    // --- [ glGetUniformBlockIndex ] ---
    fun getUniformBlockIndex(uniformBlockName: CharSequence) = GL31C.glGetUniformBlockIndex(name, uniformBlockName)

    // --- [ glBindFragDataLocation ] ---

    fun bindFragDataLocation(index: Int, name: String) {
        val stack = stackGet()
        val ptr = stack.pointer
        GL20C.nglBindAttribLocation(this.name, index, stack.ASCII(name).adr)
        stack.pointer = ptr
    }

    // --- [ glGetActiveAttrib ] ---

    fun getActiveAttrib(index: Int): Triple<String, Int, AttributeType> = gl.getActiveAttrib(this, index)

    // --- [ glGetUniformIndices ] ---

    /**
     * Retrieves the indices of a number of uniforms within a program object
     *
     * @param uniformNames   an array of pointers to buffers containing the names of the queried uniforms
     * @return               an array that will receive the indices of the uniforms
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetUniformIndices">Reference Page</a>
     */
    fun getUniformIndices(uniformNames: Array<CharSequence>): IntArray = gl.getUniformIndices(this, uniformNames)

    // --- [ glGetActiveUniformsiv ] --- TODO rename?

    /**
     * Returns information about several active uniform variables for the specified program object.
     *
     * @param uniformIndices an array of {@code uniformCount} integers containing the indices of uniforms within {@code program}
     * @param name          the property of the each uniform in {@code uniformIndices} that should be written into the corresponding element of {@code params}
     * @param params         an array of {@code uniformCount} integers which are to receive the value of {@code name} for each uniform in {@code uniformIndices}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetActiveUniforms">Reference Page</a>
     */
    fun getActiveUniformsiv(uniformIndices: IntBuffer, name: GetActiveUniform, params: IntBuffer) = gl.getActiveUniformsiv(this, uniformIndices, name, params)

    /**
     * Returns information about several active uniform variables for the specified program object.
     *
     * @param name   the property of the each uniform in {@code uniformIndices} that should be written into the corresponding element of {@code params}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetActiveUniforms">Reference Page</a>
     */
    fun getActiveUniformsi(uniformIndex: Int, name: GetActiveUniform): Int = gl.getActiveUniformsi(this, uniformIndex, name)

    // --- [ glGetActiveUniformName ] ---

    /**
     * Queries the name of an active uniform.
     *
     * @param uniformIndex the index of the active uniform whose name to query
     * @param bufSize      the size of the buffer, in units of {@code GLchar}, of the buffer whose address is specified in {@code uniformName}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetActiveUniformName">Reference Page</a>
     */
    fun getActiveUniformName(uniformIndex: Int, bufSize: Int = getActiveUniformsi(uniformIndex, GetActiveUniform.UNIFORM_NAME_LENGTH)): String =
            gl.getActiveUniformName(this, uniformIndex, bufSize)

    // --- [ glGetUniformBlockIndex ] ---

    /**
     * Retrieves the index of a named uniform block.
     *
     * @param program          the name of a program containing the uniform block
     * @param uniformBlockName an array of characters to containing the name of the uniform block whose index to retrieve
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetUniformBlockIndex">Reference Page</a>
     */
    fun getUniformBlockIndex(program: GlProgram, uniformBlockName: CharSequence): UniformBlockIndex = gl.getUniformBlockIndex(this, uniformBlockName)

    // --- [ glGetActiveUniformBlockiv ] ---

    /**
     * Queries information about an active uniform block.
     *
     * @param uniformBlockIndex the index of the uniform block within {@code program}
     * @param name             the name of the parameter to query. One of:<br><table><tr><td>{@link #GL_UNIFORM_BLOCK_BINDING UNIFORM_BLOCK_BINDING}</td><td>{@link #GL_UNIFORM_BLOCK_DATA_SIZE UNIFORM_BLOCK_DATA_SIZE}</td></tr><tr><td>{@link #GL_UNIFORM_BLOCK_NAME_LENGTH UNIFORM_BLOCK_NAME_LENGTH}</td><td>{@link #GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS UNIFORM_BLOCK_ACTIVE_UNIFORMS}</td></tr><tr><td>{@link #GL_UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES}</td><td>{@link #GL_UNIFORM_BLOCK_REFERENCED_BY_VERTEX_SHADER UNIFORM_BLOCK_REFERENCED_BY_VERTEX_SHADER}</td></tr><tr><td>{@link #GL_UNIFORM_BLOCK_REFERENCED_BY_GEOMETRY_SHADER UNIFORM_BLOCK_REFERENCED_BY_GEOMETRY_SHADER}</td><td>{@link #GL_UNIFORM_BLOCK_REFERENCED_BY_FRAGMENT_SHADER UNIFORM_BLOCK_REFERENCED_BY_FRAGMENT_SHADER}</td></tr></table>
     * @param params            the address of a variable to receive the result of the query
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetActiveUniformBlock">Reference Page</a>
     */
    inline fun <reified T> getActiveUniformBlockiv(uniformBlockIndex: UniformBlockIndex, name: GetActiveUniformBlock): T = gl.getActiveUniformBlockiv(this, uniformBlockIndex, name)

    // --- [ glGetActiveUniformBlockName ] ---

    /**
     * Retrieves the name of an active uniform block.
     *
     * @param uniformBlockIndex the index of the uniform block within {@code program}
     * @param bufSize           the size of the buffer addressed by {@code uniformBlockName}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetActiveUniformBlockName">Reference Page</a>
     */
    fun getActiveUniformBlockName(uniformBlockIndex: UniformBlockIndex,
                                  bufSize: Int = getActiveUniformBlockiv(uniformBlockIndex, GetActiveUniformBlock.GL_UNIFORM_BLOCK_NAME_LENGTH)): String =
            gl.getActiveUniformBlockName(this, uniformBlockIndex, bufSize)

    // --- [ glUniformBlockBinding ] ---

    /**
     * Assigns a binding point to an active uniform block.
     *
     * @param uniformBlockIndex   the index of the active uniform block within {@code program} whose binding to assign
     * @param uniformBlockBinding the binding point to which to bind the uniform block with index {@code uniformBlockIndex} within {@code program}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniformBlockBinding">Reference Page</a>
     */
    fun uniformBlockBinding(uniformBlockIndex: UniformBlockIndex, uniformBlockBinding: Int) = gl.uniformBlockBinding(this, uniformBlockIndex, uniformBlockBinding)

    fun uniformBlockBinding(uniformBlockIndex: Int, uniformBlockBinding: Enum<*>) = gl.uniformBlockBinding(this, uniformBlockIndex, uniformBlockBinding.ordinal)

    companion object {

        val NULL = GlProgram(-1)

        // --- [ glCreateProgram ] ---
        fun create() = GlProgram(GL20C.glCreateProgram())

        inline fun init(block: ProgramBase.() -> Unit): GlProgram {
            ProgramBase.program = create()
            ProgramBase.block()
            return ProgramBase.program
        }

        /** for ogl-samples */
        inline fun initFromPath(vert: String, frag: String, block: ProgramBase.() -> Unit): GlProgram =
                init(GlShader.createFromPath(vert), GlShader.createFromPath(frag), block)

        inline fun init(vert: GlShader, frag: GlShader, block: ProgramBase.() -> Unit): GlProgram {
            ProgramBase.apply {
                program = create().apply {
                    plusAssign(vert)
                    plusAssign(frag)
                }

                block()

                program.apply {
                    link()

                    if (!linkStatus)
                        throw Exception("Linker failure: $infoLog")

                    minusAssign(vert)
                    minusAssign(frag)
                    vert.delete()
                    frag.delete()
                }
            }
            return ProgramBase.program
        }

        fun createFromSource(vertSrc: String, fragSrc: String): GlProgram {

            val program = GlProgram.create()

            val v = GlShader.createFromSource(ShaderType.VERTEX_SHADER, vertSrc)
            val f = GlShader.createFromSource(ShaderType.FRAGMENT_SHADER, fragSrc)

            program += v
            program += f

            program.link()


            program -= v
            program -= f
            v.delete()
            f.delete()

            if (!program.linkStatus) throw Exception("Linker failure: ${program.infoLog}")

            return program
        }

        fun createFromSource(vertSrc: String, geomSrc: String, fragSrc: String): GlProgram {

            val program = GlProgram.create()

            val v = GlShader.createFromSource(ShaderType.VERTEX_SHADER, vertSrc)
            val g = GlShader.createFromSource(ShaderType.GEOMETRY_SHADER, geomSrc)
            val f = GlShader.createFromSource(ShaderType.FRAGMENT_SHADER, fragSrc)

            program += v
            program += g
            program += f

            program.link()


            program -= v
            program -= g
            program -= f
            v.delete()
            g.delete()
            f.delete()

            if (!program.linkStatus) throw Exception("Linker failure: ${program.infoLog}")

            return program
        }

        // TODO createFromPath
    }
}