package gln.objects

import glm_.bool
import gln.*
import gln.program.ProgramBase
import kool.adr
import kool.stak
import org.lwjgl.opengl.*
import org.lwjgl.system.MemoryStack.stackGet
import java.lang.Exception

inline class GlProgram(val i: Int) {

    // --- [ glDeleteProgram ] ---

    fun delete() = GL20C.glDeleteProgram(i)

    // --- [ glIsProgram ] ---

    val valid: Boolean
        get() = GL20C.glIsProgram(i)

    // --- [ glAttachShader ] ---

    infix fun attach(shader: GlShader) = GL20C.glAttachShader(i, shader.i)

    operator fun plusAssign(shader: GlShader) = GL20C.glAttachShader(i, shader.i)

    // --- [ glDetachShader ] ---

    infix fun detach(shader: GlShader) = GL20C.glDetachShader(i, shader.i)

    operator fun minusAssign(shader: GlShader) = GL20C.glDetachShader(i, shader.i)

    // --- [ glShaderSource ] ---

    // --- [ glLinkProgram ] ---

    fun link() = GL20C.glLinkProgram(i)

    // --- [ glUseProgram ] ---

    fun use() = GL20C.glUseProgram(i)

    inline fun withProgram(program: Int, block: ProgramBase.() -> Unit) {
        ProgramBase.name = program
        ProgramBase.block()
    }
    fun <R> use(block: ProgramBase.() -> R): R {
        ProgramBase.name = i
        return ProgramBase.block().also { ProgramBase.name = 0 }
    }

    // --- [ glValidateProgram ] ---

    fun validate() = GL20C.glValidateProgram(i)

    // --- [ glGetProgramiv ] ---

    val deleteStatus: Boolean
        get() = gl20.getProgram(this, GetProgram(GL20C.GL_DELETE_STATUS)).bool

    val linkStatus: Boolean
        get() = gl20.getProgram(this, GetProgram(GL20C.GL_LINK_STATUS)).bool

    val validateStatus: Boolean
        get() = gl20.getProgram(this, GetProgram(GL20C.GL_VALIDATE_STATUS)).bool

    val infoLogLength: Int
        get() = gl20.getProgram(this, GetProgram(GL20C.GL_INFO_LOG_LENGTH))

    val attachedShadersCount: Int
        get() = gl20.getProgram(this, GetProgram(GL20C.GL_ATTACHED_SHADERS))

    val activeAtomicCounterBuffers: Int
        get() = gl20.getProgram(this, GetProgram(GL42.GL_ACTIVE_ATOMIC_COUNTER_BUFFERS))

    val activeAttributes: Int
        get() = gl20.getProgram(this, GetProgram(GL20C.GL_ACTIVE_ATTRIBUTES))

    val activeAttributeMaxLength: Int
        get() = gl20.getProgram(this, GetProgram(GL20C.GL_ACTIVE_ATTRIBUTE_MAX_LENGTH))

    val activeUniforms: Int
        get() = gl20.getProgram(this, GetProgram(GL20C.GL_ACTIVE_UNIFORMS))

    val activeUniformMaxLength: Int
        get() = gl20.getProgram(this, GetProgram(GL20C.GL_ACTIVE_UNIFORM_MAX_LENGTH))

    val binaryLength: Int
        get() = gl20.getProgram(this, GetProgram(GL41.GL_PROGRAM_BINARY_LENGTH))

//    val computeWorkGroupSize: Vec3i
//        get() = stak.vec3iAddress { GL20C.nglGetProgramiv(i, GL43.GL_COMPUTE_WORK_GROUP_SIZE, it) }

    val transformFeedbackBufferMode: Int
        get() = gl20.getProgram(this, GetProgram(GL41.GL_TRANSFORM_FEEDBACK_BUFFER_MODE))

    val transformFeedbackVaryings: Int
        get() = gl20.getProgram(this, GetProgram(GL41.GL_TRANSFORM_FEEDBACK_VARYINGS))

    val transformFeedbackVaryingMaxLength: Int
        get() = gl20.getProgram(this, GetProgram(GL41.GL_TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH))

    val geometryVerticesOut: Int
        get() = gl20.getProgram(this, GetProgram(GL41.GL_GEOMETRY_VERTICES_OUT))

    val geometryInputType: Int
        get() = gl20.getProgram(this, GetProgram(GL41.GL_GEOMETRY_INPUT_TYPE))

    val geometryOutputType: Int
        get() = gl20.getProgram(this, GetProgram(GL41.GL_GEOMETRY_OUTPUT_TYPE))

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

    infix fun getUniformF(location: Int): Float = gl20.getUniformFloat(this, location)

    // --- [ glGetUniformiv ] ---

    infix fun getUniformI(location: Int): Int = gl20.getUniformInt(this, location)

    // --- [ glBindAttribLocation ] ---

    fun bindAttribLocation(index: Int, name: String) = stak { GL20C.nglBindAttribLocation(i, index, it.ASCII(name).adr) }

    // --- [ glBindFragDataLocation ] ---

    fun bindFragDataLocation(colorNumber: Int, name: String) = stak { GL20C.nglBindAttribLocation(i, colorNumber, it.ASCII(name).adr) }

    // --- [ glGetActiveAttrib ] ---

    fun getActiveAttrib(index: Int): Triple<String, Int, AttributeType> = gl20.getActiveAttrib(this, index)

    companion object {
        // --- [ glCreateProgram ] ---
        fun create() = GlProgram(GL20C.glCreateProgram())

        inline fun init(block: ProgramBase.() -> Unit): Int {
            ProgramBase.name = GL20.glCreateProgram()
            ProgramBase.block()
            return ProgramBase.name
        }

        fun createFromSource(vertSrc: String, fragSrc: String): GlProgram {

            val program = GlProgram.create()

            val v = GlShader.createFromSource(vertSrc, ShaderType(GL20.GL_VERTEX_SHADER))
            val f = GlShader.createFromSource(fragSrc, ShaderType(GL20.GL_FRAGMENT_SHADER))

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

            val v = GlShader.createFromSource(vertSrc, ShaderType(GL20.GL_VERTEX_SHADER))
            val g = GlShader.createFromSource(geomSrc, ShaderType(GL32.GL_GEOMETRY_SHADER))
            val f = GlShader.createFromSource(fragSrc, ShaderType(GL20.GL_FRAGMENT_SHADER))

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