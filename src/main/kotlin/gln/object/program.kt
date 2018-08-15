package gln.`object`

import ab.appBuffer
import glm_.bool
import glm_.buffer.adr
import glm_.vec3.Vec3i
import gln.*
import org.lwjgl.opengl.GL20C
import org.lwjgl.opengl.GL43

inline class GLprogram(val i: Int) {

    // --- [ glDeleteProgram ] ---

    fun delete() = GL20C.glDeleteProgram(i)

    // --- [ glIsProgram ] ---

    val valid: Boolean
        get() = GL20C.glIsProgram(i)

    // --- [ glAttachShader ] ---

    infix fun attach(shader: GLshader) = GL20C.glAttachShader(i, shader.i)

    operator fun plusAssign(shader: GLshader) = GL20C.glAttachShader(i, shader.i)

    // --- [ glDetachShader ] ---

    infix fun detach(shader: GLshader) = GL20C.glDetachShader(i, shader.i)

    operator fun minusAssign(shader: GLshader) = GL20C.glDetachShader(i, shader.i)

    // --- [ glShaderSource ] ---

    // --- [ glLinkProgram ] ---

    fun link() = GL20C.glLinkProgram(i)

    // --- [ glUseProgram ] ---

    fun use() = GL20C.glUseProgram(i)
    fun <R> use(block: (GLprogram) -> R): R {
        GL20C.glUseProgram(i)
        return block(this).also { GL20C.glUseProgram(0) }
    }

    // --- [ glValidateProgram ] ---

    fun validate() = GL20C.glValidateProgram(i)

    // --- [ glGetProgramiv ] ---

    val deleteStatus: Boolean
        get() = gl20.getProgram(this, DELETE_STATUS_).bool

    val linkStatus: Boolean
        get() = gl20.getProgram(this, LINK_STATUS).bool

    val validateStatus: Boolean
        get() = gl20.getProgram(this, VALIDATE_STATUS).bool

    val infoLogLength: Int
        get() = gl20.getProgram(this, INFO_LOG_LENGTH_)

    val attachedShadersCount: Int
        get() = gl20.getProgram(this, ATTACHED_SHADERS)

    val activeAtomicCounterBuffers: Int
        get() = gl20.getProgram(this, ACTIVE_ATOMIC_COUNTER_BUFFERS)

    val activeAttributes: Int
        get() = gl20.getProgram(this, ACTIVE_ATTRIBUTES)

    val activeAttributeMaxLength: Int
        get() = gl20.getProgram(this, ACTIVE_ATTRIBUTE_MAX_LENGTH)

    val activeUniforms: Int
        get() = gl20.getProgram(this, ACTIVE_UNIFORMS)

    val activeUniformMaxLength: Int
        get() = gl20.getProgram(this, ACTIVE_UNIFORM_MAX_LENGTH)

    val binaryLength: Int
        get() = gl20.getProgram(this, PROGRAM_BINARY_LENGTH)

    val computeWorkGroupSize: Vec3i
        get() {
            val ints = appBuffer.intArray(Vec3i.length)
            GL20C.nglGetProgramiv(i, GL43.GL_COMPUTE_WORK_GROUP_SIZE, ints)
            return Vec3i(ints)
        }

    val transformFeedbackBufferMode: Int
        get() = gl20.getProgram(this, TRANSFORM_FEEDBACK_BUFFER_MODE)

    val transformFeedbackVaryings: Int
        get() = gl20.getProgram(this, TRANSFORM_FEEDBACK_VARYINGS)

    val transformFeedbackVaryingMaxLength: Int
        get() = gl20.getProgram(this, TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH)

    val geometryVerticesOut: Int
        get() = gl20.getProgram(this, GEOMETRY_VERTICES_OUT)

    val geometryInputType: Int
        get() = gl20.getProgram(this, GEOMETRY_INPUT_TYPE)

    val geometryOutputType: Int
        get() = gl20.getProgram(this, GEOMETRY_OUTPUT_TYPE)

    // --- [ glGetProgramInfoLog ] ---

    val infoLog: String
        get() = gl20.getProgramInfoLog(this)

    // --- [ glGetAttachedShaders ] ---

    val attachedShaders: GLshaders
        get() = gl20.getAttachedShaders(this)

    // --- [ glGetUniformLocation ] ---

    infix fun getUniformLocation(name: String): Int = gl20.getUniformLocation(this, name)
    operator fun get(name: String): Int = gl20.getUniformLocation(this, name)

    // --- [ glGetActiveUniform ] ---

    infix fun getActiveUniform(index: Int): Triple<String, Int, UniformType> = gl20.getActiveUniform(this, index)

    // --- [ glGetUniformfv ] ---

    infix fun getUniformF(location: Int): Float = gl20.getUniformF(this, location)

    // --- [ glGetUniformiv ] ---

    infix fun getUniformI(location: Int): Int = gl20.getUniformI(this, location)

    // --- [ glBindAttribLocation ] ---

    fun bindAttribLocation(index: Int, name: String) = GL20C.nglBindAttribLocation(i, index, appBuffer.bufferOfAscii(name).adr)

    // --- [ glGetActiveAttrib ] ---

    fun getActiveAttrib(index: Int): Triple<String, Int, AttributeType> = gl20.getActiveAttrib(this, index)

    companion object {
        // --- [ glCreateProgram ] ---
        fun create() = GLprogram(GL20C.glCreateProgram())
    }
}