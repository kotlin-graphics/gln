package gln.objects

import glm_.bool
import gln.*
import gln.program.ProgramBase
import gln.program.ProgramUse
import kool.adr
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

    fun source(source: String) = GL20C.glShaderSource(i, source)

    // --- [ glLinkProgram ] ---

    fun link() = GL20C.glLinkProgram(i)

    // --- [ glUseProgram ] ---

    fun use() = GL20C.glUseProgram(i)

    // JVM custom

    fun unuse() = GL20C.glUseProgram(0)

    inline fun use(block: ProgramUse.() -> Unit) {
        ProgramUse.program = this
        use()
        ProgramUse.block()
        unuse()
    }

    // --- [ glValidateProgram ] ---

    fun validate() = GL20C.glValidateProgram(i)

    // --- [ glGetProgramiv ] ---

    val deleteStatus: Boolean
        get() = gl21.getProgram(this, GetProgram(GL20C.GL_DELETE_STATUS)).bool

    val linkStatus: Boolean
        get() = gl21.getProgram(this, GetProgram(GL20C.GL_LINK_STATUS)).bool

    val validateStatus: Boolean
        get() = gl21.getProgram(this, GetProgram(GL20C.GL_VALIDATE_STATUS)).bool

    val infoLogLength: Int
        get() = gl21.getProgram(this, GetProgram(GL20C.GL_INFO_LOG_LENGTH))

    val attachedShadersCount: Int
        get() = gl21.getProgram(this, GetProgram(GL20C.GL_ATTACHED_SHADERS))

    val activeAtomicCounterBuffers: Int
        get() = gl21.getProgram(this, GetProgram(GL42.GL_ACTIVE_ATOMIC_COUNTER_BUFFERS))

    val activeAttributes: Int
        get() = gl21.getProgram(this, GetProgram(GL20C.GL_ACTIVE_ATTRIBUTES))

    val activeAttributeMaxLength: Int
        get() = gl21.getProgram(this, GetProgram(GL20C.GL_ACTIVE_ATTRIBUTE_MAX_LENGTH))

    val activeUniforms: Int
        get() = gl21.getProgram(this, GetProgram(GL20C.GL_ACTIVE_UNIFORMS))

    val activeUniformMaxLength: Int
        get() = gl21.getProgram(this, GetProgram(GL20C.GL_ACTIVE_UNIFORM_MAX_LENGTH))

    val binaryLength: Int
        get() = gl21.getProgram(this, GetProgram(GL41.GL_PROGRAM_BINARY_LENGTH))

//    val computeWorkGroupSize: Vec3i
//        get() = stak.vec3iAddress { GL20C.nglGetProgramiv(i, GL43.GL_COMPUTE_WORK_GROUP_SIZE, it) }

    val transformFeedbackBufferMode: Int
        get() = gl21.getProgram(this, GetProgram(GL41.GL_TRANSFORM_FEEDBACK_BUFFER_MODE))

    val transformFeedbackVaryings: Int
        get() = gl21.getProgram(this, GetProgram(GL41.GL_TRANSFORM_FEEDBACK_VARYINGS))

    val transformFeedbackVaryingMaxLength: Int
        get() = gl21.getProgram(this, GetProgram(GL41.GL_TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH))

    val geometryVerticesOut: Int
        get() = gl21.getProgram(this, GetProgram(GL41.GL_GEOMETRY_VERTICES_OUT))

    val geometryInputType: Int
        get() = gl21.getProgram(this, GetProgram(GL41.GL_GEOMETRY_INPUT_TYPE))

    val geometryOutputType: Int
        get() = gl21.getProgram(this, GetProgram(GL41.GL_GEOMETRY_OUTPUT_TYPE))

    // --- [ glGetProgramInfoLog ] ---

    val infoLog: String
        get() = gl21.getProgramInfoLog(this)

    // --- [ glGetAttachedShaders ] ---

    val attachedShaders: GLshaders
        get() = gl21.getAttachedShaders(this)

    // --- [ glGetUniformLocation ] ---

    infix fun getUniformLocation(name: String): Int = gl21.getUniformLocation(this, name)
    operator fun get(name: String): Int = gl21.getUniformLocation(this, name)

    // --- [ glGetActiveUniform ] ---

    infix fun getActiveUniform(index: Int): Triple<String, Int, UniformType> = gl21.getActiveUniform(this, index)

    // --- [ glGetUniformfv ] ---

    infix fun getUniformF(location: Int): Float = gl21.getUniformFloat(this, location)

    // --- [ glGetUniformiv ] ---

    infix fun getUniformI(location: Int): Int = gl21.getUniformInt(this, location)

    // --- [ glGetAttribLocation ] ---
    infix fun getAttribLocation(name: String): Int = GL20.glGetAttribLocation(i, name)

    // --- [ glBindAttribLocation ] ---

    fun bindAttribLocation(index: Int, name: String) {
        val stack = stackGet()
        val ptr = stack.pointer
        GL20C.nglBindAttribLocation(i, index, stack.ASCII(name).adr)
        stack.pointer = ptr
    }

    // --- [ glGetUniformBlockIndex ] ---
    fun getUniformBlockIndex(uniformBlockName: CharSequence) = GL31C.glGetUniformBlockIndex(i, uniformBlockName)

    // --- [ glUniformBlockBinding ] ---
    fun uniformBlockBinding(uniformBlockIndex: Int, uniformBlockBinding: Int) = GL31C.glUniformBlockBinding(i, uniformBlockIndex, uniformBlockBinding)

    // --- [ glBindFragDataLocation ] ---

    fun bindFragDataLocation(index: Int, name: String) {
        val stack = stackGet()
        val ptr = stack.pointer
        GL20C.nglBindAttribLocation(i, index, stack.ASCII(name).adr)
        stack.pointer = ptr
    }

    // --- [ glGetActiveAttrib ] ---

    fun getActiveAttrib(index: Int): Triple<String, Int, AttributeType> = gl21.getActiveAttrib(this, index)

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