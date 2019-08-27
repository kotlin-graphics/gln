package gln.identifiers

import glm_.mat2x2.Mat2
import glm_.mat2x2.Mat2d
import glm_.mat3x3.Mat3
import glm_.mat3x3.Mat3d
import glm_.mat4x4.Mat4
import glm_.mat4x4.Mat4d
import glm_.vec1.Vec1
import glm_.vec1.Vec1d
import glm_.vec1.Vec1i
import glm_.vec1.Vec1ui
import glm_.vec2.Vec2
import glm_.vec2.Vec2d
import glm_.vec2.Vec2i
import glm_.vec2.Vec2ui
import glm_.vec3.Vec3
import glm_.vec3.Vec3d
import glm_.vec3.Vec3i
import glm_.vec3.Vec3ui
import glm_.vec4.Vec4
import glm_.vec4.Vec4d
import glm_.vec4.Vec4i
import glm_.vec4.Vec4ui
import gln.*
import gln.program.ProgramBase
import gln.program.ProgramUse
import kool.ByteBuffer
import kool.adr
import kool.rem
import kool.Stack
import org.lwjgl.opengl.*
import org.lwjgl.opengl.GL20C
import org.lwjgl.system.MemoryUtil
import unsigned.Uint
import java.lang.Exception
import java.nio.IntBuffer

inline class GlProgram(val name: Int) {

    // --- [ glAttachShader ] ---

    infix fun attach(shader: GlShader) = GL20C.glAttachShader(name, shader.name)

    operator fun plusAssign(shader: GlShader) = GL20C.glAttachShader(name, shader.name)

    var binary: ProgramBinary
    // --- [ glGetProgramBinary ] ---
        /**
         * Returns a binary representation of a program object's compiled and linked executable source.
         *
         * @param program      the name of a program object whose binary representation to retrieve
         * @param length       the address of a variable to receive the number of bytes written into {@code binary}
         * @param binaryFormat a variable to receive a token indicating the format of the binary data returned by the GL
         * @param binary       an array into which the GL will return {@code program}'s binary representation
         *
         * @see <a target="_blank" href="http://docs.gl/gl4/glGetProgramBinary">Reference Page</a>
         */
        get() {
            val data = ByteBuffer(binaryLength)
            val format = Stack.intAddress { GL41C.nglGetProgramBinary(name, data.rem, MemoryUtil.NULL, it, data.adr) }
            return ProgramBinary(data, format)
        }
    // --- [ glProgramBinary ] ---
        /**
         * Loads a program object with a program binary.
         *
         * @param program      the name of a program object into which to load a program binary
         * @param binaryFormat the format of the binary data in binary
         * @param binary       an array containing the binary to be loaded into {@code program}
         *
         * @see <a target="_blank" href="http://docs.gl/gl4/glProgramBinary">Reference Page</a>
         */
        set(value) = GL41C.nglProgramBinary(name, value.format, value.data.adr, value.data.rem)

    // --- [ glBindAttribLocation ] ---

    fun bindAttribLocation(index: Int, name: String) = gl.bindAttribLocation(this, index, name)

    // --- [ glBindFragDataLocation ] ---

    fun bindFragDataLocation(index: Int, name: String) = gl.bindFragDataLocation(this, index, name)

    // --- [ glBindFragDataLocationIndexed ] ---

    /**
     * Binds a user-defined varying out variable to a fragment shader color number and index.
     *
     * @param colorNumber the color number to bind the user-defined varying out variable to
     * @param index       the index of the color input to bind the user-defined varying out variable to
     * @param name        the name of the user-defined varying out variable whose binding to modify
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBindFragDataLocationIndexed">Reference Page</a>
     */
    fun bindFragDataLocationIndexed(colorNumber: Int, index: Int, name: CharSequence) = gl.bindFragDataLocationIndexed(this, colorNumber, index, name)

    // --- [ glDeleteProgram ] ---

    fun delete() = GL20C.glDeleteProgram(name)

    // --- [ glDetachShader ] ---

    infix fun detach(shader: GlShader) = GL20C.glDetachShader(name, shader.name)

    operator fun minusAssign(shader: GlShader) = GL20C.glDetachShader(name, shader.name)

    // --- [ glGetActiveAttrib ] ---

    fun getActiveAttrib(index: Int): Triple<String, Int, AttributeType> = gl.getActiveAttrib(this, index)

    // --- [ glGetActiveSubroutineName ] ---

    /**
     * Queries the name of an active shader subroutine.
     *
     * @param shaderType the shader stage from which to query the subroutine name. One of:<br><table><tr><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td><td>{@link #GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td></tr><tr><td>{@link #GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
     * @param index      the index of the shader subroutine uniform
     * @param bufSize    the size of the buffer whose address is given in {@code name}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetActiveSubroutineName">Reference Page</a>
     */
    fun getActiveSubroutineName(shaderType: ShaderType, index: Int, bufSize: Int = getStage(shaderType, GetProgramStage.ACTIVE_SUBROUTINE_MAX_LENGTH)): String = gl.getActiveSubroutineName(this, shaderType, index, bufSize)

    // --- [ glGetActiveSubroutineUniformiv ] ---

    /**
     * Queries a property of an active shader subroutine uniform.
     *
     * @param shaderType the shader stage from which to query for the subroutine parameter. One of:<br><table><tr><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td><td>{@link #GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td></tr><tr><td>{@link #GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
     * @param index      the index of the shader subroutine uniform
     * @param name       the parameter of the shader subroutine uniform to query. One of:<br><table><tr><td>{@link #GL_NUM_COMPATIBLE_SUBROUTINES NUM_COMPATIBLE_SUBROUTINES}</td><td>{@link #GL_COMPATIBLE_SUBROUTINES COMPATIBLE_SUBROUTINES}</td><td>{@link GL31#GL_UNIFORM_SIZE UNIFORM_SIZE}</td><td>{@link GL31#GL_UNIFORM_NAME_LENGTH UNIFORM_NAME_LENGTH}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetActiveSubroutineUniform">Reference Page</a>
     */
    fun getActiveSubroutineUniform(shaderType: ShaderType, index: Int, name: GetActiveSubroutineUniform): Int = gl.getActiveSubroutineUniform(this, shaderType, index, name)

    /**
     * @return GL_COMPATIBLE_SUBROUTINES of an active shader subroutine uniform.
     *
     * @param shaderType the shader stage from which to query for the subroutine parameter. One of:<br><table><tr><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td><td>{@link #GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td></tr><tr><td>{@link #GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
     * @param index      the index of the shader subroutine uniform
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetActiveSubroutineUniform">Reference Page</a>
     */
    fun getActiveSubroutineUniformCompatibles(shaderType: ShaderType, index: Int): IntArray = gl.getActiveSubroutineUniformCompatibles(this, shaderType, index)

    // --- [ glGetActiveSubroutineUniformName ] ---

    /**
     * Queries the name of an active shader subroutine uniform.
     *
     * @param shaderType the shader stage from which to query for the subroutine parameter. One of:<br><table><tr><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td><td>{@link #GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td></tr><tr><td>{@link #GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
     * @param index      the index of the shader subroutine uniform
     * @param bufSize    the size of the buffer whose address is given in {@code name}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetActiveSubroutineUniformName">Reference Page</a>
     */
    fun getActiveSubroutineUniformName(shaderType: ShaderType, index: Int, bufSize: Int = getActiveSubroutineUniform(shaderType, index, GetActiveSubroutineUniform.UNIFORM_NAME_LENGTH)): String = gl.getActiveSubroutineUniformName(this, shaderType, index, bufSize)

    // --- [ glGetActiveUniform ] ---

    infix fun getActiveUniform(index: Int): Triple<String, Int, UniformType> = gl.getActiveUniform(this, index)

    // --- [ glGetAttachedShaders ] ---

    val attachedShaders: GLshaders
        get() = gl.getAttachedShaders(this)

    // --- [ glGetAttribLocation ] ---
    infix fun getAttribLocation(name: String): AttribLocation = gl.getAttribLocation(this, name)

    // --- [ glGetFragDataIndex ] ---

    /**
     * Queries the bindings of color indices to user-defined varying out variables.
     *
     * @param program the name of the program containing varying out variable whose binding to query
     * @param name    the name of the user-defined varying out variable whose index to query
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetFragDataIndex">Reference Page</a>
     */
    infix fun getFragDataIndex(name: CharSequence): Int = gl.getFragDataIndex(this, name)

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

    // --- [ glGetProgramResourceiv ] ---

    /**
     * Retrieves values for multiple properties of a single active resource within a program object.
     *
     * @param programInterface a token identifying the interface within {@code program} containing the resource named {@code name}. One of:<br><table><tr><td>{@link #GL_UNIFORM UNIFORM}</td><td>{@link #GL_UNIFORM_BLOCK UNIFORM_BLOCK}</td><td>{@link #GL_PROGRAM_INPUT PROGRAM_INPUT}</td></tr><tr><td>{@link #GL_PROGRAM_OUTPUT PROGRAM_OUTPUT}</td><td>{@link #GL_BUFFER_VARIABLE BUFFER_VARIABLE}</td><td>{@link #GL_SHADER_STORAGE_BLOCK SHADER_STORAGE_BLOCK}</td></tr><tr><td>{@link #GL_VERTEX_SUBROUTINE VERTEX_SUBROUTINE}</td><td>{@link #GL_TESS_CONTROL_SUBROUTINE TESS_CONTROL_SUBROUTINE}</td><td>{@link #GL_TESS_EVALUATION_SUBROUTINE TESS_EVALUATION_SUBROUTINE}</td></tr><tr><td>{@link #GL_GEOMETRY_SUBROUTINE GEOMETRY_SUBROUTINE}</td><td>{@link #GL_FRAGMENT_SUBROUTINE FRAGMENT_SUBROUTINE}</td><td>{@link #GL_COMPUTE_SUBROUTINE COMPUTE_SUBROUTINE}</td></tr><tr><td>{@link #GL_VERTEX_SUBROUTINE_UNIFORM VERTEX_SUBROUTINE_UNIFORM}</td><td>{@link #GL_TESS_CONTROL_SUBROUTINE_UNIFORM TESS_CONTROL_SUBROUTINE_UNIFORM}</td><td>{@link #GL_TESS_EVALUATION_SUBROUTINE_UNIFORM TESS_EVALUATION_SUBROUTINE_UNIFORM}</td></tr><tr><td>{@link #GL_GEOMETRY_SUBROUTINE_UNIFORM GEOMETRY_SUBROUTINE_UNIFORM}</td><td>{@link #GL_FRAGMENT_SUBROUTINE_UNIFORM FRAGMENT_SUBROUTINE_UNIFORM}</td><td>{@link #GL_COMPUTE_SUBROUTINE_UNIFORM COMPUTE_SUBROUTINE_UNIFORM}</td></tr><tr><td>{@link #GL_TRANSFORM_FEEDBACK_VARYING TRANSFORM_FEEDBACK_VARYING}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td></tr></table>
     * @param index            the active resource index
     * @param props            an array that will receive the active resource properties
     * @param length           a variable which will receive the number of values returned
     * @param params           an array that will receive the property values
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetProgramResource">Reference Page</a>
     */
    fun getResource(programInterface: ProgramInterface, index: Int, props: Int): Int = gl.getProgramResource(this, programInterface, index, props)

    /**
     * Retrieves values for multiple properties of a single active resource within a program object.
     *
     * @param programInterface a token identifying the interface within {@code program} containing the resource named {@code name}. One of:<br><table><tr><td>{@link #GL_UNIFORM UNIFORM}</td><td>{@link #GL_UNIFORM_BLOCK UNIFORM_BLOCK}</td><td>{@link #GL_PROGRAM_INPUT PROGRAM_INPUT}</td></tr><tr><td>{@link #GL_PROGRAM_OUTPUT PROGRAM_OUTPUT}</td><td>{@link #GL_BUFFER_VARIABLE BUFFER_VARIABLE}</td><td>{@link #GL_SHADER_STORAGE_BLOCK SHADER_STORAGE_BLOCK}</td></tr><tr><td>{@link #GL_VERTEX_SUBROUTINE VERTEX_SUBROUTINE}</td><td>{@link #GL_TESS_CONTROL_SUBROUTINE TESS_CONTROL_SUBROUTINE}</td><td>{@link #GL_TESS_EVALUATION_SUBROUTINE TESS_EVALUATION_SUBROUTINE}</td></tr><tr><td>{@link #GL_GEOMETRY_SUBROUTINE GEOMETRY_SUBROUTINE}</td><td>{@link #GL_FRAGMENT_SUBROUTINE FRAGMENT_SUBROUTINE}</td><td>{@link #GL_COMPUTE_SUBROUTINE COMPUTE_SUBROUTINE}</td></tr><tr><td>{@link #GL_VERTEX_SUBROUTINE_UNIFORM VERTEX_SUBROUTINE_UNIFORM}</td><td>{@link #GL_TESS_CONTROL_SUBROUTINE_UNIFORM TESS_CONTROL_SUBROUTINE_UNIFORM}</td><td>{@link #GL_TESS_EVALUATION_SUBROUTINE_UNIFORM TESS_EVALUATION_SUBROUTINE_UNIFORM}</td></tr><tr><td>{@link #GL_GEOMETRY_SUBROUTINE_UNIFORM GEOMETRY_SUBROUTINE_UNIFORM}</td><td>{@link #GL_FRAGMENT_SUBROUTINE_UNIFORM FRAGMENT_SUBROUTINE_UNIFORM}</td><td>{@link #GL_COMPUTE_SUBROUTINE_UNIFORM COMPUTE_SUBROUTINE_UNIFORM}</td></tr><tr><td>{@link #GL_TRANSFORM_FEEDBACK_VARYING TRANSFORM_FEEDBACK_VARYING}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td></tr></table>
     * @param index            the active resource index
     * @param props            an array that will receive the active resource properties
     * @param length           a variable which will receive the number of values returned
     * @param params           an array that will receive the property values
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetProgramResource">Reference Page</a>
     */
    fun getResource(programInterface: ProgramInterface, index: Int, props: IntBuffer, length: IntBuffer?, params: IntBuffer) = gl.getProgramResource(this, programInterface, index, props, length, params)

    // --- [ glGetProgramResourceIndex ] ---

    /**
     * Queries the index of a named resource within a program.
     *
     * @param programInterface a token identifying the interface within {@code program} containing the resource named {Wcode name}. One of:<br><table><tr><td>{@link #GL_UNIFORM UNIFORM}</td><td>{@link #GL_UNIFORM_BLOCK UNIFORM_BLOCK}</td><td>{@link #GL_PROGRAM_INPUT PROGRAM_INPUT}</td></tr><tr><td>{@link #GL_PROGRAM_OUTPUT PROGRAM_OUTPUT}</td><td>{@link #GL_BUFFER_VARIABLE BUFFER_VARIABLE}</td><td>{@link #GL_SHADER_STORAGE_BLOCK SHADER_STORAGE_BLOCK}</td></tr><tr><td>{@link #GL_VERTEX_SUBROUTINE VERTEX_SUBROUTINE}</td><td>{@link #GL_TESS_CONTROL_SUBROUTINE TESS_CONTROL_SUBROUTINE}</td><td>{@link #GL_TESS_EVALUATION_SUBROUTINE TESS_EVALUATION_SUBROUTINE}</td></tr><tr><td>{@link #GL_GEOMETRY_SUBROUTINE GEOMETRY_SUBROUTINE}</td><td>{@link #GL_FRAGMENT_SUBROUTINE FRAGMENT_SUBROUTINE}</td><td>{@link #GL_COMPUTE_SUBROUTINE COMPUTE_SUBROUTINE}</td></tr><tr><td>{@link #GL_VERTEX_SUBROUTINE_UNIFORM VERTEX_SUBROUTINE_UNIFORM}</td><td>{@link #GL_TESS_CONTROL_SUBROUTINE_UNIFORM TESS_CONTROL_SUBROUTINE_UNIFORM}</td><td>{@link #GL_TESS_EVALUATION_SUBROUTINE_UNIFORM TESS_EVALUATION_SUBROUTINE_UNIFORM}</td></tr><tr><td>{@link #GL_GEOMETRY_SUBROUTINE_UNIFORM GEOMETRY_SUBROUTINE_UNIFORM}</td><td>{@link #GL_FRAGMENT_SUBROUTINE_UNIFORM FRAGMENT_SUBROUTINE_UNIFORM}</td><td>{@link #GL_COMPUTE_SUBROUTINE_UNIFORM COMPUTE_SUBROUTINE_UNIFORM}</td></tr><tr><td>{@link #GL_TRANSFORM_FEEDBACK_VARYING TRANSFORM_FEEDBACK_VARYING}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td></tr></table>
     * @param name             the name of the resource to query the index of
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetProgramResourceIndex">Reference Page</a>
     */
    fun getResourceIndex(programInterface: ProgramInterface, name: CharSequence): Int = gl.getProgramResourceIndex(this, programInterface, name)

    // --- [ glGetProgramResourceLocation ] ---

    /**
     * Queries the location of a named resource within a program.
     *
     * @param program          the name of a program object whose resources to query
     * @param programInterface a token identifying the interface within {@code program} containing the resource named {@code name}
     * @param name             the name of the resource to query the location of
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetProgramResourceLocation">Reference Page</a>
     */
    fun getResourceLocation(programInterface: ProgramInterface, name: CharSequence): Int = gl.getProgramResourceLocation(this, programInterface, name)

    // --- [ glGetProgramResourceLocationIndex ] ---

    /**
     * Queries the fragment color index of a named variable within a program.
     *
     * @param programInterface a token identifying the interface within {@code program} containing the resource named {@code name}. Must be:<br><table><tr><td>{@link #GL_PROGRAM_OUTPUT PROGRAM_OUTPUT}</td></tr></table>
     * @param name             the name of the resource to query the location of
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetProgramResourceLocationIndex">Reference Page</a>
     */
    fun getResourceLocationIndex(programInterface: ProgramInterface, name: CharSequence) = gl.getProgramResourceLocationIndex(this, programInterface, name)

    // --- [ glGetProgramResourceName ] ---

    /**
     * Queries the name of an indexed resource within a program.
     *
     * @param programInterface a token identifying the interface within {@code program} containing the indexed resource. One of:<br><table><tr><td>{@link #GL_UNIFORM UNIFORM}</td><td>{@link #GL_UNIFORM_BLOCK UNIFORM_BLOCK}</td><td>{@link #GL_PROGRAM_INPUT PROGRAM_INPUT}</td></tr><tr><td>{@link #GL_PROGRAM_OUTPUT PROGRAM_OUTPUT}</td><td>{@link #GL_BUFFER_VARIABLE BUFFER_VARIABLE}</td><td>{@link #GL_SHADER_STORAGE_BLOCK SHADER_STORAGE_BLOCK}</td></tr><tr><td>{@link #GL_VERTEX_SUBROUTINE VERTEX_SUBROUTINE}</td><td>{@link #GL_TESS_CONTROL_SUBROUTINE TESS_CONTROL_SUBROUTINE}</td><td>{@link #GL_TESS_EVALUATION_SUBROUTINE TESS_EVALUATION_SUBROUTINE}</td></tr><tr><td>{@link #GL_GEOMETRY_SUBROUTINE GEOMETRY_SUBROUTINE}</td><td>{@link #GL_FRAGMENT_SUBROUTINE FRAGMENT_SUBROUTINE}</td><td>{@link #GL_COMPUTE_SUBROUTINE COMPUTE_SUBROUTINE}</td></tr><tr><td>{@link #GL_VERTEX_SUBROUTINE_UNIFORM VERTEX_SUBROUTINE_UNIFORM}</td><td>{@link #GL_TESS_CONTROL_SUBROUTINE_UNIFORM TESS_CONTROL_SUBROUTINE_UNIFORM}</td><td>{@link #GL_TESS_EVALUATION_SUBROUTINE_UNIFORM TESS_EVALUATION_SUBROUTINE_UNIFORM}</td></tr><tr><td>{@link #GL_GEOMETRY_SUBROUTINE_UNIFORM GEOMETRY_SUBROUTINE_UNIFORM}</td><td>{@link #GL_FRAGMENT_SUBROUTINE_UNIFORM FRAGMENT_SUBROUTINE_UNIFORM}</td><td>{@link #GL_COMPUTE_SUBROUTINE_UNIFORM COMPUTE_SUBROUTINE_UNIFORM}</td></tr><tr><td>{@link #GL_TRANSFORM_FEEDBACK_VARYING TRANSFORM_FEEDBACK_VARYING}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td></tr></table>
     * @param index            the index of the resource within {@code programInterface} of {@code program}
     * @param bufSize          the size of the character array whose address is given by {@code name}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetProgramResourceName">Reference Page</a>
     */
    fun getResourceName(programInterface: ProgramInterface, index: Int, bufSize: Int = getProgramInterface(programInterface, GetProgramInterface.MAX_NAME_LENGTH)): String = gl.getProgramResourceName(this, programInterface, index, bufSize)

    // --- [ glGetProgramStageiv ] ---

    /**
     * Retrieves properties of a program object corresponding to a specified shader stage.
     *
     * @param shaderType the shader stage from which to query for the subroutine parameter. One of:<br><table><tr><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td><td>{@link #GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td></tr><tr><td>{@link #GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
     * @param name      the parameter of the shader to query. One of:<br><table><tr><td>{@link #GL_ACTIVE_SUBROUTINES ACTIVE_SUBROUTINES}</td><td>{@link #GL_ACTIVE_SUBROUTINE_UNIFORMS ACTIVE_SUBROUTINE_UNIFORMS}</td></tr><tr><td>{@link #GL_ACTIVE_SUBROUTINE_UNIFORM_LOCATIONS ACTIVE_SUBROUTINE_UNIFORM_LOCATIONS}</td><td>{@link #GL_ACTIVE_SUBROUTINE_MAX_LENGTH ACTIVE_SUBROUTINE_MAX_LENGTH}</td></tr><tr><td>{@link #GL_ACTIVE_SUBROUTINE_UNIFORM_MAX_LENGTH ACTIVE_SUBROUTINE_UNIFORM_MAX_LENGTH}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetProgramStage">Reference Page</a>
     */
    fun getStage(shaderType: ShaderType, name: GetProgramStage): Int = gl.getProgramStage(this, shaderType, name)

    // --- [ glGetSubroutineIndex ] ---

    /**
     * Retrieves the index of a subroutine function of a given shader stage within a program.
     *
     * @param shaderType the shader stage from which to query for subroutine function index. One of:<br><table><tr><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td><td>{@link #GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td></tr><tr><td>{@link #GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
     * @param name       the name of the subroutine function whose index to query
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetSubroutineIndex">Reference Page</a>
     */
    fun getSubroutineIndex(shaderType: ShaderType, name: CharSequence): Int = gl.getSubroutineIndex(this, shaderType, name)

    // --- [ glGetSubroutineUniformLocation ] ---

    /**
     * Retrieves the location of a subroutine uniform of a given shader stage within a program.
     *
     * @param shaderType the shader stage from which to query for subroutine uniform index. One of:<br><table><tr><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td><td>{@link #GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td></tr><tr><td>{@link #GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
     * @param name       the name of the subroutine uniform whose index to query.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetSubroutineUniformLocation">Reference Page</a>
     */
    fun getSubroutineUniformLocation(shaderType: ShaderType, name: CharSequence): Int = gl.getSubroutineUniformLocation(this, shaderType, name)

    // --- [ glGetSubroutineIndex / glGetSubroutineUniformLocation ] ---

    /**
     * Retrieves the index and the uniform location of a subroutine uniform of a given shader stage within a program.
     *
     * @param shaderType the shader stage from which to query for subroutine uniform index. One of:<br><table><tr><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td><td>{@link #GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td></tr><tr><td>{@link #GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
     * @param name       the name of the subroutine uniform whose index to query.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetSubroutineUniformLocation">Reference Page</a>
     */
    fun getSubroutine(shaderType: ShaderType, name: CharSequence): Subroutine = gl.getSubroutine(this, shaderType, name)

    // --- [ glGetUniform* ] ---

    inline infix fun <reified T> getUniform(location: UniformLocation): T = gl.getUniform(this, location)

    // --- [ glProgramParameteri ] ---

    var binaryRetrievableHint: Boolean
        get() = gl.getProgram(this, GetProgram.PROGRAM_BINARY_RETRIEVABLE_HINT)
        set(value) = gl.programParameter(this, ProgramParameter.PROGRAM_BINARY_RETRIEVABLE_HINT, value)

    var separable: Boolean
        get() = gl.getProgram(this, GetProgram.PROGRAM_SEPARABLE)
        set(value) = gl.programParameter(this, ProgramParameter.PROGRAM_SEPARABLE, value)

    // --- [ glGetUniformLocation ] ---

    infix fun getUniformLocation(name: String): UniformLocation = gl.getUniformLocation(this, name)
    operator fun get(name: String): UniformLocation = gl.getUniformLocation(this, name)

    // --- [ glIsProgram ] ---

    val isValid: Boolean
        get() = GL20C.glIsProgram(name)

    val isInvalid: Boolean
        get() = !GL20C.glIsProgram(name)

    // --- [ glLinkProgram ] ---

    fun link() = GL20C.glLinkProgram(name)

    // --- [ glProgramUniform* ] ---

    fun programUniform(location: UniformLocation, x: Int) = GL41C.glProgramUniform1i(name, location, x)
    fun programUniform(location: UniformLocation, v: Vec1i) = GL41C.glProgramUniform1i(name, location, v.x)

    fun programUniform(location: UniformLocation, x: Int, y: Int) = GL41C.glProgramUniform2i(name, location, x, y)
    fun programUniform(location: UniformLocation, v: Vec2i) = GL41C.glProgramUniform2i(name, location, v.x, v.y)

    fun programUniform(location: UniformLocation, x: Int, y: Int, z: Int) = GL41C.glProgramUniform3i(name, location, x, y, z)
    fun programUniform(location: UniformLocation, v: Vec3i) = GL41C.glProgramUniform3i(name, location, v.x, v.y, v.z)

    fun programUniform(location: UniformLocation, x: Int, y: Int, z: Int, w: Int) = GL41C.glProgramUniform4i(name, location, x, y, z, w)
    fun programUniform(location: UniformLocation, v: Vec4i) = GL41C.glProgramUniform4i(name, location, v.x, v.y, v.z, v.w)

    fun programUniform(location: UniformLocation, x: Uint) = GL41C.glProgramUniform1ui(name, location, x.v)
    fun programUniform(location: UniformLocation, v: Vec1ui) = GL41C.glProgramUniform1ui(name, location, v.x.v)

    fun programUniform(location: UniformLocation, x: Uint, y: Uint) = GL41C.glProgramUniform2ui(name, location, x.v, y.v)
    fun programUniform(location: UniformLocation, v: Vec2ui) = GL41C.glProgramUniform2ui(name, location, v.x.v, v.y.v)

    fun programUniform(location: UniformLocation, x: Uint, y: Uint, z: Uint) = GL41C.glProgramUniform3ui(name, location, x.v, y.v, z.v)
    fun programUniform(location: UniformLocation, v: Vec3ui) = GL41C.glProgramUniform3ui(name, location, v.x.v, v.y.v, v.z.v)

    fun programUniform(location: UniformLocation, x: Uint, y: Uint, z: Uint, w: Uint) = GL41C.glProgramUniform4ui(name, location, x.v, y.v, z.v, w.v)
    fun programUniform(location: UniformLocation, v: Vec4ui) = GL41C.glProgramUniform4ui(name, location, v.x.v, v.y.v, v.z.v, v.w.v)

    fun programUniform(location: UniformLocation, x: Float) = GL41C.glProgramUniform1f(name, location, x)
    fun programUniform(location: UniformLocation, v: Vec1) = GL41C.glProgramUniform1f(name, location, v.x)

    fun programUniform(location: UniformLocation, x: Float, y: Float) = GL41C.glProgramUniform2f(name, location, x, y)
    fun programUniform(location: UniformLocation, v: Vec2) = GL41C.glProgramUniform2f(name, location, v.x, v.y)

    fun programUniform(location: UniformLocation, x: Float, y: Float, z: Float) = GL41C.glProgramUniform3f(name, location, x, y, z)
    fun programUniform(location: UniformLocation, v: Vec3) = GL41C.glProgramUniform3f(name, location, v.x, v.y, v.z)

    fun programUniform(location: UniformLocation, x: Float, y: Float, z: Float, w: Float) = GL41C.glProgramUniform4f(name, location, x, y, z, w)
    fun programUniform(location: UniformLocation, v: Vec4) = GL41C.glProgramUniform4f(name, location, v.x, v.y, v.z, v.w)

    fun programUniform(location: UniformLocation, x: Double) = GL41C.glProgramUniform1d(name, location, x)
    fun programUniform(location: UniformLocation, v: Vec1d) = GL41C.glProgramUniform1d(name, location, v.x)

    fun programUniform(location: UniformLocation, x: Double, y: Double) = GL41C.glProgramUniform2d(name, location, x, y)
    fun programUniform(location: UniformLocation, v: Vec2d) = GL41C.glProgramUniform2d(name, location, v.x, v.y)

    fun programUniform(location: UniformLocation, x: Double, y: Double, z: Double) = GL41C.glProgramUniform3d(name, location, x, y, z)
    fun programUniform(location: UniformLocation, v: Vec3d) = GL41C.glProgramUniform3d(name, location, v.x, v.y, v.z)

    fun programUniform(location: UniformLocation, x: Double, y: Double, z: Double, w: Double) = GL41C.glProgramUniform4d(name, location, x, y, z, w)
    fun programUniform(location: UniformLocation, v: Vec4d) = GL41C.glProgramUniform4d(name, location, v.x, v.y, v.z, v.w)

    // --- [ glProgramUniformMatrix* ] ---

    fun programUniform(location: UniformLocation, value: Mat2) = gl.programUniform(this, location, value)
    fun programUniform(location: UniformLocation, value: Mat3) = gl.programUniform(this, location, value)
    fun programUniform(location: UniformLocation, value: Mat4) = gl.programUniform(this, location, value)
    fun programUniform(location: UniformLocation, value: Mat2d) = gl.programUniform(this, location, value)
    fun programUniform(location: UniformLocation, value: Mat3d) = gl.programUniform(this, location, value)
    fun programUniform(location: UniformLocation, value: Mat4d) = gl.programUniform(this, location, value)

    // --- [ glShaderSource ] ---

    fun source(source: String) = GL20C.glShaderSource(name, source)

    // --- [ glUseProgram ] ---

    fun use() = gl.useProgram(this)

    // JVM custom
    fun unuse() = gl.useProgram()

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

    // --- [ glValidateProgramPipeline ] ---

    /**
     * Validates a program pipeline object against current GL state.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glValidateProgramPipeline">Reference Page</a>
     */
    fun validate() = GL41C.glValidateProgramPipeline(name)

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
     * @param uniformBlockName an array of characters to containing the name of the uniform block whose index to retrieve
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetUniformBlockIndex">Reference Page</a>
     */
    fun getUniformBlockIndex(uniformBlockName: CharSequence): UniformBlockIndex = gl.getUniformBlockIndex(this, uniformBlockName)

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
                                  bufSize: Int = getActiveUniformBlockiv(uniformBlockIndex, GetActiveUniformBlock.NAME_LENGTH)): String =
            gl.getActiveUniformBlockName(this, uniformBlockIndex, bufSize)

    // --- [ glGetProgramInterfaceiv ] ---

    /**
     * Queries a property of an interface in a program.
     *
     * @param programInterface a token identifying the interface within {@code program} to query. One of:<br><table><tr><td>{@link #GL_UNIFORM UNIFORM}</td><td>{@link #GL_UNIFORM_BLOCK UNIFORM_BLOCK}</td><td>{@link #GL_PROGRAM_INPUT PROGRAM_INPUT}</td></tr><tr><td>{@link #GL_PROGRAM_OUTPUT PROGRAM_OUTPUT}</td><td>{@link #GL_BUFFER_VARIABLE BUFFER_VARIABLE}</td><td>{@link #GL_SHADER_STORAGE_BLOCK SHADER_STORAGE_BLOCK}</td></tr><tr><td>{@link #GL_VERTEX_SUBROUTINE VERTEX_SUBROUTINE}</td><td>{@link #GL_TESS_CONTROL_SUBROUTINE TESS_CONTROL_SUBROUTINE}</td><td>{@link #GL_TESS_EVALUATION_SUBROUTINE TESS_EVALUATION_SUBROUTINE}</td></tr><tr><td>{@link #GL_GEOMETRY_SUBROUTINE GEOMETRY_SUBROUTINE}</td><td>{@link #GL_FRAGMENT_SUBROUTINE FRAGMENT_SUBROUTINE}</td><td>{@link #GL_COMPUTE_SUBROUTINE COMPUTE_SUBROUTINE}</td></tr><tr><td>{@link #GL_VERTEX_SUBROUTINE_UNIFORM VERTEX_SUBROUTINE_UNIFORM}</td><td>{@link #GL_TESS_CONTROL_SUBROUTINE_UNIFORM TESS_CONTROL_SUBROUTINE_UNIFORM}</td><td>{@link #GL_TESS_EVALUATION_SUBROUTINE_UNIFORM TESS_EVALUATION_SUBROUTINE_UNIFORM}</td></tr><tr><td>{@link #GL_GEOMETRY_SUBROUTINE_UNIFORM GEOMETRY_SUBROUTINE_UNIFORM}</td><td>{@link #GL_FRAGMENT_SUBROUTINE_UNIFORM FRAGMENT_SUBROUTINE_UNIFORM}</td><td>{@link #GL_COMPUTE_SUBROUTINE_UNIFORM COMPUTE_SUBROUTINE_UNIFORM}</td></tr><tr><td>{@link #GL_TRANSFORM_FEEDBACK_VARYING TRANSFORM_FEEDBACK_VARYING}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td></tr></table>
     * @param name            the name of the parameter within {@code programInterface} to query. One of:<br><table><tr><td>{@link #GL_ACTIVE_RESOURCES ACTIVE_RESOURCES}</td><td>{@link #GL_MAX_NAME_LENGTH MAX_NAME_LENGTH}</td><td>{@link #GL_MAX_NUM_ACTIVE_VARIABLES MAX_NUM_ACTIVE_VARIABLES}</td></tr><tr><td>{@link #GL_MAX_NUM_COMPATIBLE_SUBROUTINES MAX_NUM_COMPATIBLE_SUBROUTINES}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetProgramInterface">Reference Page</a>
     */
    fun getProgramInterface(programInterface: ProgramInterface, name: GetProgramInterface): Int = gl.getProgramInterface(this, programInterface, name)

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

    // --- [ glGetActiveAtomicCounterBufferiv ] ---

    /**
     * Obtains information about the set of active atomic counter buffers for a program.
     *
     * @param bufferIndex the index of an active atomic counter buffer
     * @param pname       the parameter to query. One of:<br><table><tr><td>{@link #GL_ATOMIC_COUNTER_BUFFER_DATA_SIZE ATOMIC_COUNTER_BUFFER_DATA_SIZE}</td></tr><tr><td>{@link #GL_ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTERS ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTERS}</td></tr><tr><td>{@link #GL_ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTER_INDICES ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTER_INDICES}</td></tr><tr><td>{@link #GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_VERTEX_SHADER ATOMIC_COUNTER_BUFFER_REFERENCED_BY_VERTEX_SHADER}</td></tr><tr><td>{@link #GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_TESS_CONTROL_SHADER ATOMIC_COUNTER_BUFFER_REFERENCED_BY_TESS_CONTROL_SHADER}</td></tr><tr><td>{@link #GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_TESS_EVALUATION_SHADER ATOMIC_COUNTER_BUFFER_REFERENCED_BY_TESS_EVALUATION_SHADER}</td></tr><tr><td>{@link #GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_GEOMETRY_SHADER ATOMIC_COUNTER_BUFFER_REFERENCED_BY_GEOMETRY_SHADER}</td></tr><tr><td>{@link #GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_FRAGMENT_SHADER ATOMIC_COUNTER_BUFFER_REFERENCED_BY_FRAGMENT_SHADER}</td></tr></table>
     * @param params      a buffer in which to place the returned value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetActiveAtomicCounterBuffer">Reference Page</a>
     */
    inline fun <reified T> getActiveAtomicCounterBufferiv(bufferIndex: Int, name: GetActiveAtomicCounterBuffer): T = gl.getActiveAtomicCounterBufferiv(this, bufferIndex, name)

    companion object {

        val NULL = GlProgram(0)

        // --- [ glCreateProgram ] ---
        fun create() = GlProgram(GL20C.glCreateProgram())

        inline fun init(block: ProgramBase.() -> Unit): GlProgram {
            ProgramBase.program = create()
            ProgramBase.block()
            return ProgramBase.program
        }

        /** for ogl-samples */
        inline fun initFromPath(vertAndFrag: String, block: ProgramBase.() -> Unit): GlProgram =
                init(GlShader.createFromPath("$vertAndFrag.vert"), GlShader.createFromPath("$vertAndFrag.frag"), block)

        inline fun initFromPath(vert: String, frag: String, block: ProgramBase.() -> Unit): GlProgram =
                init(GlShader.createFromPath(vert), GlShader.createFromPath(frag), block)

        inline fun init(vert: GlShader, frag: GlShader, block: ProgramBase.() -> Unit): GlProgram {
            ProgramBase.apply {
                program = create().apply {
                    attach(vert)
                    attach(frag)
                }

                block()

                program.apply {
                    link()

                    if (!linkStatus)
                        throw Exception("Linker failure: $infoLog")

                    detach(vert)
                    detach(frag)
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