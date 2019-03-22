package gln

import glm_.BYTES
import glm_.i
import gln.objects.GlProgram
import gln.program.GlPipeline
import gln.program.GlPipelines
import kool.*
import org.lwjgl.opengl.GL41C
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.system.MemoryUtil.memGetInt
import java.nio.ByteBuffer
import java.nio.IntBuffer

/**
 * The OpenGL functionality up to version 4.1. Includes only Core Profile symbols.
 *
 * <p>OpenGL 4.1 implementations support revision 4.10 of the OpenGL Shading Language.</p>
 *
 * <p>Extensions promoted to core in this release:</p>
 *
 * <ul>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_ES2_compatibility.txt">ARB_ES2_compatibility</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_get_program_binary.txt">ARB_get_program_binary</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_separate_shader_objects.txt">ARB_separate_shader_objects</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_shader_precision.txt">ARB_shader_precision</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_vertex_attrib_64bit.txt">ARB_vertex_attrib_64bit</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_viewport_array.txt">ARB_viewport_array</a></li>
 * </ul>
 */
interface gl41i {

    // --- [ glReleaseShaderCompiler ] ---

    /**
     * Releases resources allocated by the shader compiler. This is a hint from the application, and does not prevent later use of the shader compiler.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glReleaseShaderCompiler">Reference Page</a>
     */
    fun releaseShaderCompiler() = GL41C.glReleaseShaderCompiler()

    // --- [ glShaderBinary ] ---

    /**
     * Loads pre-compiled shader binaries.
     *
     * @param shaders      an array of shader handles into which to load pre-compiled shader binaries
     * @param binaryFormat the format of the shader binaries contained in {@code binary}
     * @param binary       an array of bytes containing pre-compiled binary shader code
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glShaderBinary">Reference Page</a>
     */
    fun shaderBinary(shaders: IntBuffer, binaryFormat: BinaryFormat, binary: ByteBuffer) =
            GL41C.nglShaderBinary(shaders.rem, shaders.adr, binaryFormat, binary.adr, binary.rem)

    // --- [ glGetShaderPrecisionFormat ] ---

    /**
     * Retrieves the range and precision for numeric formats supported by the shader compiler.
     *
     * @param shaderType    the type of shader whose precision to query. One of:<br><table><tr><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td></tr></table>
     * @param precisionType the numeric format whose precision and range to query
     * @param range         the address of array of two integers into which encodings of the implementation's numeric range are returned
     * @param precision     the address of an integer into which the numeric precision of the implementation is written
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetShaderPrecisionFormat">Reference Page</a>
     */
    fun getShaderPrecisionFormat(shaderType: ShaderType, precisionType: PrecisionType): ShaderPrecisionFormat =
            stak {
                val adr = it.nmalloc(4, Int.BYTES * 3)
                GL41C.nglGetShaderPrecisionFormat(shaderType.i, precisionType.i, adr, adr + Int.BYTES * 2)
                ShaderPrecisionFormat(memGetInt(adr)..memGetInt(adr + Int.BYTES) to memGetInt(adr + Int.BYTES * 2))
            }

    // --- [ glDepthRangef ] ---

    /**
     * Specifies mapping of depth values from normalized device coordinates to window coordinates
     *
     * @param zNear the mapping of the near clipping plane to window coordinates. The initial value is 0.0f.
     * @param zFar  the mapping of the far clipping plane to window coordinates. The initial value is 1.0f.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDepthRangef">Reference Page</a>
     */
    fun depthRange(zNear: Float, zFar: Float) = GL41C.glDepthRangef(zNear, zFar)

    // --- [ glClearDepthf ] ---

    /**
     * Specifies the clear value for the depth buffer
     *
     * @param depth the depth value used when the depth buffer is cleared. The initial value is 1.0f.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClearDepthf">Reference Page</a>
     */
    fun clearDepth(depth: Float) = GL41C.glClearDepthf(depth)

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
    fun getProgramBinary(program: GlProgram): ProgramBinary {
        val data = ByteBuffer(program.binaryLength)
        val format = stak.intAddress { GL41C.nglGetProgramBinary(program.name, data.rem, NULL, it, data.adr) }
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
    fun programBinary(program: GlProgram, binary: ProgramBinary) =
            GL41C.nglProgramBinary(program.name, binary.format, binary.data.adr, binary.data.rem)

    // --- [ glProgramParameteri ] ---

    /**
     * Specifies the integer value of a program object parameter.
     *
     * @param program the name of a program object whose parameter to modify
     * @param name   the name of the parameter to modify. One of:<br><table><tr><td>{@link #GL_PROGRAM_BINARY_RETRIEVABLE_HINT PROGRAM_BINARY_RETRIEVABLE_HINT}</td><td>{@link #GL_PROGRAM_SEPARABLE PROGRAM_SEPARABLE}</td></tr></table>
     * @param value   the new value of the parameter specified by {@code name} for {@code program}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramParameteri">Reference Page</a>
     */
    fun programParameter(program: GlProgram, name: ProgramParameter, value: Boolean) =
            GL41C.glProgramParameteri(program.name, name.i, value.i)

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
    fun useProgramStages(pipeline: GlPipeline, stages: Int, program: GlProgram) =
            GL41C.glUseProgramStages(pipeline.name, stages, program.name)

    // --- [ glActiveShaderProgram ] ---

    /**
     * Sets the active program object for a program pipeline object.
     *
     * @param pipeline the program pipeline object to set the active program object for
     * @param program  the program object to set as the active program pipeline object {@code pipeline}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glActiveShaderProgram">Reference Page</a>
     */
    fun activeShaderProgram(pipeline: GlPipeline, program: GlProgram) = GL41C.glActiveShaderProgram(pipeline.name, program.name)

    // --- [ glCreateShaderProgramv ] ---

    /**
     * Creates a stand-alone program from an array of null-terminated source code strings.
     *
     * <p>{@code glCreateShaderProgram} is equivalent (assuming no errors are generated) to:</p>
     *
     * <pre><code>
     * const GLuint shader = glCreateShader(type);
     * if (shader) {
     *     glShaderSource(shader, count, strings, NULL);
     *     glCompileShader(shader);
     *     const GLuint program = glCreateProgram();
     *     if (program) {
     *         GLint compiled = GL_FALSE;
     *         glGetShaderiv(shader, GL_COMPILE_STATUS, &amp;compiled);
     *         glProgramParameteri(program, GL_PROGRAM_SEPARABLE, GL_TRUE);
     *         if (compiled) {
     *             glAttachShader(program, shader);
     *             glLinkProgram(program);
     *             glDetachShader(program, shader);
     *         }
     *         // append-shader-info-log-to-program-info-log
     *     }
     *     glDeleteShader(shader);
     *     return program;
     * } else {
     *     return 0;
     * }</code></pre>
     *
     * <p>The program object created by glCreateShaderProgram has its GL_PROGRAM_SEPARABLE status set to GL_TRUE.</p>
     *
     * @param type    the type of shader to create
     * @param strings an array of pointers to source code strings from which to create the program object
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateShaderProgramv">Reference Page</a>
     */
    fun createShaderProgram(type: ShaderType, vararg strings: CharSequence): GlProgram = stak {
        val pStrings = it.PointerBuffer(strings.size)
        for(i in strings.indices) pStrings[i] = it.UTF8(strings[i])
        GlProgram(GL41C.nglCreateShaderProgramv(type.i, 1, pStrings.adr))
    }

    // --- [ glBindProgramPipeline ] ---

    /**
     * Binds a program pipeline to the current context.
     *
     * @param pipeline the name of the pipeline object to bind to the context
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBindProgramPipeline">Reference Page</a>
     */
    fun bindProgramPipeline(pipeline: GlPipeline) = GL41C.glBindProgramPipeline(pipeline.name)

    // --- [ glDeleteProgramPipelines ] ---

    /**
     * Deletes program pipeline objects.
     *
     * @param pipelines an array of names of program pipeline objects to delete
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteProgramPipelines">Reference Page</a>
     */
    fun deleteProgramPipelines(pipelines: GlPipelines) = GL41C.nglDeleteProgramPipelines(pipelines.rem, pipelines.adr)

    /**
     * Deletes program pipeline objects.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteProgramPipelines">Reference Page</a>
     */
    fun deleteProgramPipelines(pipeline: GlPipeline) = stak.intAddress(pipeline.name) { GL41C.nglDeleteProgramPipelines(1, it) }

    // --- [ glGenProgramPipelines ] ---

    /**
     * Reserves program pipeline object names.
     *
     * @param pipelines an array of into which the reserved names will be written
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenProgramPipelines">Reference Page</a>
     */
    fun genProgramPipelines(pipelines: GlPipelines) = GL41C.nglGenProgramPipelines(pipelines.rem, pipelines.adr)

    /**
     * Reserves program pipeline object names.
     *
     * @param pipelines an array of into which the reserved names will be written
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenProgramPipelines">Reference Page</a>
     */
    fun genProgramPipelines(size: Int): GlPipelines = GlPipelines(size).also(::genProgramPipelines)

    /**
     * Reserves program pipeline object names.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenProgramPipelines">Reference Page</a>
     */
    fun genProgramPipelines(): GlPipeline = GlPipeline(stak.intAddress { GL41C.nglGenProgramPipelines(1, it) })

    // --- [ glIsProgramPipeline ] ---

    /**
     * Determines if a name corresponds to a program pipeline object.
     *
     * @param pipeline a value that may be the name of a program pipeline object
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glIsProgramPipeline">Reference Page</a>
     */
//    @NativeType("GLboolean")
//    public static native boolean glIsProgramPipeline(@NativeType("GLuint") int pipeline);

//    // --- [ glGetProgramPipelineiv ] ---
//
//    /** Unsafe version of: {@link #glGetProgramPipelineiv GetProgramPipelineiv} */
//    public static native void nglGetProgramPipelineiv(int pipeline, int pname, long params);
//
//    /**
//     * Retrieves properties of a program pipeline object.
//     *
//     * @param pipeline the name of a program pipeline object whose parameter retrieve
//     * @param pname    the name of the parameter to retrieve. One of:<br><table><tr><td>{@link #GL_ACTIVE_PROGRAM ACTIVE_PROGRAM}</td><td>{@link GL20#GL_INFO_LOG_LENGTH INFO_LOG_LENGTH}</td><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td></tr><tr><td>{@link GL40#GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td><td>{@link GL40#GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
//     * @param params   a variable into which will be written the value or values of {@code pname} for {@code pipeline}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetProgramPipeline">Reference Page</a>
//     */
//    public static void glGetProgramPipelineiv(@NativeType("GLuint") int pipeline, @NativeType("GLenum") int pname, @NativeType("GLint *") IntBuffer params)
//    {
//        if (CHECKS) {
//            check(params, 1);
//        }
//        nglGetProgramPipelineiv(pipeline, pname, memAddress(params));
//    }
//
//    /**
//     * Retrieves properties of a program pipeline object.
//     *
//     * @param pipeline the name of a program pipeline object whose parameter retrieve
//     * @param pname    the name of the parameter to retrieve. One of:<br><table><tr><td>{@link #GL_ACTIVE_PROGRAM ACTIVE_PROGRAM}</td><td>{@link GL20#GL_INFO_LOG_LENGTH INFO_LOG_LENGTH}</td><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td></tr><tr><td>{@link GL40#GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td><td>{@link GL40#GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetProgramPipeline">Reference Page</a>
//     */
//    @NativeType("void")
//    public static int glGetProgramPipelinei(@NativeType("GLuint") int pipeline, @NativeType("GLenum") int pname)
//    {
//        MemoryStack stack = stackGet (); int stackPointer = stack . getPointer ();
//        try {
//            IntBuffer params = stack . callocInt (1);
//            nglGetProgramPipelineiv(pipeline, pname, memAddress(params));
//            return params.get(0);
//        } finally {
//            stack.setPointer(stackPointer);
//        }
//    }
//
//    // --- [ glProgramUniform1i ] ---
//
//    /**
//     * Specifies the value of an int uniform variable for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param x        the uniform x value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static native void glProgramUniform1i(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLint") int x);
//
//    // --- [ glProgramUniform2i ] ---
//
//    /**
//     * Specifies the value of an ivec2 uniform variable for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param x        the uniform x value
//     * @param y        the uniform y value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static native void glProgramUniform2i(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLint") int x, @NativeType("GLint") int y);
//
//    // --- [ glProgramUniform3i ] ---
//
//    /**
//     * Specifies the value of an ivec3 uniform variable for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param x        the uniform x value
//     * @param y        the uniform y value
//     * @param z        the uniform z value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static native void glProgramUniform3i(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLint") int x, @NativeType("GLint") int y, @NativeType("GLint") int z);
//
//    // --- [ glProgramUniform4i ] ---
//
//    /**
//     * Specifies the value of an ivec4 uniform variable for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param x        the uniform x value
//     * @param y        the uniform y value
//     * @param z        the uniform z value
//     * @param w        the uniform w value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static native void glProgramUniform4i(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLint") int x, @NativeType("GLint") int y, @NativeType("GLint") int z, @NativeType("GLint") int w);
//
//    // --- [ glProgramUniform1ui ] ---
//
//    /**
//     * Specifies the value of a uint uniform variable for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param x        the uniform x value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static native void glProgramUniform1ui(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLuint") int x);
//
//    // --- [ glProgramUniform2ui ] ---
//
//    /**
//     * Specifies the value of a uvec2 uniform variable for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param x        the uniform x value
//     * @param y        the uniform y value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static native void glProgramUniform2ui(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLuint") int x, @NativeType("GLuint") int y);
//
//    // --- [ glProgramUniform3ui ] ---
//
//    /**
//     * Specifies the value of a uvec3 uniform variable for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param x        the uniform x value
//     * @param y        the uniform y value
//     * @param z        the uniform z value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static native void glProgramUniform3ui(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLuint") int x, @NativeType("GLuint") int y, @NativeType("GLuint") int z);
//
//    // --- [ glProgramUniform4ui ] ---
//
//    /**
//     * Specifies the value of a uvec4 uniform variable for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param x        the uniform x value
//     * @param y        the uniform y value
//     * @param z        the uniform z value
//     * @param w        the uniform w value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static native void glProgramUniform4ui(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLuint") int x, @NativeType("GLuint") int y, @NativeType("GLuint") int z, @NativeType("GLuint") int w);
//
//    // --- [ glProgramUniform1f ] ---
//
//    /**
//     * Specifies the value of a float uniform variable for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param x        the uniform x value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static native void glProgramUniform1f(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLfloat") float x);
//
//    // --- [ glProgramUniform2f ] ---
//
//    /**
//     * Specifies the value of a vec2 uniform variable for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param x        the uniform x value
//     * @param y        the uniform y value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static native void glProgramUniform2f(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLfloat") float x, @NativeType("GLfloat") float y);
//
//    // --- [ glProgramUniform3f ] ---
//
//    /**
//     * Specifies the value of a vec3 uniform variable for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param x        the uniform x value
//     * @param y        the uniform y value
//     * @param z        the uniform z value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static native void glProgramUniform3f(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLfloat") float x, @NativeType("GLfloat") float y, @NativeType("GLfloat") float z);
//
//    // --- [ glProgramUniform4f ] ---
//
//    /**
//     * Specifies the value of a vec4 uniform variable for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param x        the uniform x value
//     * @param y        the uniform y value
//     * @param z        the uniform z value
//     * @param w        the uniform w value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static native void glProgramUniform4f(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLfloat") float x, @NativeType("GLfloat") float y, @NativeType("GLfloat") float z, @NativeType("GLfloat") float w);
//
//    // --- [ glProgramUniform1d ] ---
//
//    /**
//     * Specifies the value of a double uniform variable for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param x        the uniform x value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static native void glProgramUniform1d(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLdouble") double x);
//
//    // --- [ glProgramUniform2d ] ---
//
//    /**
//     * Specifies the value of a dvec2 uniform variable for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param x        the uniform x value
//     * @param y        the uniform y value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static native void glProgramUniform2d(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLdouble") double x, @NativeType("GLdouble") double y);
//
//    // --- [ glProgramUniform3d ] ---
//
//    /**
//     * Specifies the value of a dvec3 uniform variable for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param x        the uniform x value
//     * @param y        the uniform y value
//     * @param z        the uniform z value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static native void glProgramUniform3d(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLdouble") double x, @NativeType("GLdouble") double y, @NativeType("GLdouble") double z);
//
//    // --- [ glProgramUniform4d ] ---
//
//    /**
//     * Specifies the value of a dvec4 uniform variable for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param x        the uniform x value
//     * @param y        the uniform y value
//     * @param z        the uniform z value
//     * @param w        the uniform w value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static native void glProgramUniform4d(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLdouble") double x, @NativeType("GLdouble") double y, @NativeType("GLdouble") double z, @NativeType("GLdouble") double w);
//
//    // --- [ glProgramUniform1iv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniform1iv ProgramUniform1iv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglProgramUniform1iv(int program, int location, int count, long value);
//
//    /**
//     * Specifies the value of a single float uniform variable or a float uniform variable array for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param value    an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniform1iv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLint const *") IntBuffer value)
//    {
//        nglProgramUniform1iv(program, location, value.remaining(), memAddress(value));
//    }
//
//    // --- [ glProgramUniform2iv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniform2iv ProgramUniform2iv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglProgramUniform2iv(int program, int location, int count, long value);
//
//    /**
//     * Specifies the value of a single ivec2 uniform variable or an ivec2 uniform variable array for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param value    an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniform2iv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLint const *") IntBuffer value)
//    {
//        nglProgramUniform2iv(program, location, value.remaining() > > 1, memAddress(value));
//    }
//
//    // --- [ glProgramUniform3iv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniform3iv ProgramUniform3iv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglProgramUniform3iv(int program, int location, int count, long value);
//
//    /**
//     * Specifies the value of a single ivec3 uniform variable or an ivec3 uniform variable array for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param value    an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniform3iv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLint const *") IntBuffer value)
//    {
//        nglProgramUniform3iv(program, location, value.remaining() / 3, memAddress(value));
//    }
//
//    // --- [ glProgramUniform4iv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniform4iv ProgramUniform4iv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglProgramUniform4iv(int program, int location, int count, long value);
//
//    /**
//     * Specifies the value of a single ivec4 uniform variable or an ivec4 uniform variable array for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param value    an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniform4iv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLint const *") IntBuffer value)
//    {
//        nglProgramUniform4iv(program, location, value.remaining() > > 2, memAddress(value));
//    }
//
//    // --- [ glProgramUniform1uiv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniform1uiv ProgramUniform1uiv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglProgramUniform1uiv(int program, int location, int count, long value);
//
//    /**
//     * Specifies the value of a single uint uniform variable or a uint uniform variable array for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param value    an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniform1uiv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLuint const *") IntBuffer value)
//    {
//        nglProgramUniform1uiv(program, location, value.remaining(), memAddress(value));
//    }
//
//    // --- [ glProgramUniform2uiv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniform2uiv ProgramUniform2uiv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglProgramUniform2uiv(int program, int location, int count, long value);
//
//    /**
//     * Specifies the value of a single uvec2 uniform variable or a uvec2 uniform variable array for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param value    an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniform2uiv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLuint const *") IntBuffer value)
//    {
//        nglProgramUniform2uiv(program, location, value.remaining() > > 1, memAddress(value));
//    }
//
//    // --- [ glProgramUniform3uiv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniform3uiv ProgramUniform3uiv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglProgramUniform3uiv(int program, int location, int count, long value);
//
//    /**
//     * Specifies the value of a single uvec3 uniform variable or a uvec3 uniform variable array for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param value    an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniform3uiv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLuint const *") IntBuffer value)
//    {
//        nglProgramUniform3uiv(program, location, value.remaining() / 3, memAddress(value));
//    }
//
//    // --- [ glProgramUniform4uiv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniform4uiv ProgramUniform4uiv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglProgramUniform4uiv(int program, int location, int count, long value);
//
//    /**
//     * Specifies the value of a single uvec4 uniform variable or a uvec4 uniform variable array for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param value    an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniform4uiv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLuint const *") IntBuffer value)
//    {
//        nglProgramUniform4uiv(program, location, value.remaining() > > 2, memAddress(value));
//    }
//
//    // --- [ glProgramUniform1fv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniform1fv ProgramUniform1fv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglProgramUniform1fv(int program, int location, int count, long value);
//
//    /**
//     * Specifies the value of a single float uniform variable or a float uniform variable array for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param value    an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniform1fv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLfloat const *") FloatBuffer value)
//    {
//        nglProgramUniform1fv(program, location, value.remaining(), memAddress(value));
//    }
//
//    // --- [ glProgramUniform2fv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniform2fv ProgramUniform2fv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglProgramUniform2fv(int program, int location, int count, long value);
//
//    /**
//     * Specifies the value of a single vec2 uniform variable or a vec2 uniform variable array for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param value    an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniform2fv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLfloat const *") FloatBuffer value)
//    {
//        nglProgramUniform2fv(program, location, value.remaining() > > 1, memAddress(value));
//    }
//
//    // --- [ glProgramUniform3fv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniform3fv ProgramUniform3fv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglProgramUniform3fv(int program, int location, int count, long value);
//
//    /**
//     * Specifies the value of a single vec3 uniform variable or a vec3 uniform variable array for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param value    an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniform3fv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLfloat const *") FloatBuffer value)
//    {
//        nglProgramUniform3fv(program, location, value.remaining() / 3, memAddress(value));
//    }
//
//    // --- [ glProgramUniform4fv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniform4fv ProgramUniform4fv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglProgramUniform4fv(int program, int location, int count, long value);
//
//    /**
//     * Specifies the value of a single vec4 uniform variable or a vec4 uniform variable array for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param value    an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniform4fv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLfloat const *") FloatBuffer value)
//    {
//        nglProgramUniform4fv(program, location, value.remaining() > > 2, memAddress(value));
//    }
//
//    // --- [ glProgramUniform1dv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniform1dv ProgramUniform1dv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglProgramUniform1dv(int program, int location, int count, long value);
//
//    /**
//     * Specifies the value of a single double uniform variable or a double uniform variable array for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param value    an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniform1dv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLdouble const *") DoubleBuffer value)
//    {
//        nglProgramUniform1dv(program, location, value.remaining(), memAddress(value));
//    }
//
//    // --- [ glProgramUniform2dv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniform2dv ProgramUniform2dv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglProgramUniform2dv(int program, int location, int count, long value);
//
//    /**
//     * Specifies the value of a single dvec2 uniform variable or a dvec2 uniform variable array for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param value    an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniform2dv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLdouble const *") DoubleBuffer value)
//    {
//        nglProgramUniform2dv(program, location, value.remaining() > > 1, memAddress(value));
//    }
//
//    // --- [ glProgramUniform3dv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniform3dv ProgramUniform3dv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglProgramUniform3dv(int program, int location, int count, long value);
//
//    /**
//     * Specifies the value of a single dvec3 uniform variable or a dvec3 uniform variable array for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param value    an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniform3dv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLdouble const *") DoubleBuffer value)
//    {
//        nglProgramUniform3dv(program, location, value.remaining() / 3, memAddress(value));
//    }
//
//    // --- [ glProgramUniform4dv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniform4dv ProgramUniform4dv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglProgramUniform4dv(int program, int location, int count, long value);
//
//    /**
//     * Specifies the value of a single dvec4 uniform variable or a dvec4 uniform variable array for a specified program object.
//     *
//     * @param program  the handle of the program containing the uniform variable to be modified
//     * @param location the location of the uniform variable to be modified
//     * @param value    an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniform4dv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLdouble const *") DoubleBuffer value)
//    {
//        nglProgramUniform4dv(program, location, value.remaining() > > 2, memAddress(value));
//    }
//
//    // --- [ glProgramUniformMatrix2fv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniformMatrix2fv ProgramUniformMatrix2fv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglProgramUniformMatrix2fv(int program, int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single mat2 uniform variable or a mat2 uniform variable array for the current program object.
//     *
//     * @param program   the handle of the program containing the uniform variable to be modified
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniformMatrix2fv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLfloat const *") FloatBuffer value)
//    {
//        nglProgramUniformMatrix2fv(program, location, value.remaining() > > 2, transpose, memAddress(value));
//    }
//
//    // --- [ glProgramUniformMatrix3fv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniformMatrix3fv ProgramUniformMatrix3fv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglProgramUniformMatrix3fv(int program, int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single mat3 uniform variable or a mat3 uniform variable array for the current program object.
//     *
//     * @param program   the handle of the program containing the uniform variable to be modified
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniformMatrix3fv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLfloat const *") FloatBuffer value)
//    {
//        nglProgramUniformMatrix3fv(program, location, value.remaining() / 9, transpose, memAddress(value));
//    }
//
//    // --- [ glProgramUniformMatrix4fv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniformMatrix4fv ProgramUniformMatrix4fv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglProgramUniformMatrix4fv(int program, int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single mat4 uniform variable or a mat4 uniform variable array for the current program object.
//     *
//     * @param program   the handle of the program containing the uniform variable to be modified
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniformMatrix4fv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLfloat const *") FloatBuffer value)
//    {
//        nglProgramUniformMatrix4fv(program, location, value.remaining() > > 4, transpose, memAddress(value));
//    }
//
//    // --- [ glProgramUniformMatrix2dv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniformMatrix2dv ProgramUniformMatrix2dv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglProgramUniformMatrix2dv(int program, int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single dmat2 uniform variable or a dmat2 uniform variable array for the current program object.
//     *
//     * @param program   the handle of the program containing the uniform variable to be modified
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniformMatrix2dv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLdouble const *") DoubleBuffer value)
//    {
//        nglProgramUniformMatrix2dv(program, location, value.remaining() > > 2, transpose, memAddress(value));
//    }
//
//    // --- [ glProgramUniformMatrix3dv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniformMatrix3dv ProgramUniformMatrix3dv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglProgramUniformMatrix3dv(int program, int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single dmat3 uniform variable or a dmat3 uniform variable array for the current program object.
//     *
//     * @param program   the handle of the program containing the uniform variable to be modified
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniformMatrix3dv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLdouble const *") DoubleBuffer value)
//    {
//        nglProgramUniformMatrix3dv(program, location, value.remaining() / 9, transpose, memAddress(value));
//    }
//
//    // --- [ glProgramUniformMatrix4dv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniformMatrix4dv ProgramUniformMatrix4dv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglProgramUniformMatrix4dv(int program, int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single dmat4 uniform variable or a dmat4 uniform variable array for the current program object.
//     *
//     * @param program   the handle of the program containing the uniform variable to be modified
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniformMatrix4dv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLdouble const *") DoubleBuffer value)
//    {
//        nglProgramUniformMatrix4dv(program, location, value.remaining() > > 4, transpose, memAddress(value));
//    }
//
//    // --- [ glProgramUniformMatrix2x3fv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniformMatrix2x3fv ProgramUniformMatrix2x3fv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglProgramUniformMatrix2x3fv(int program, int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single mat2x3 uniform variable or a mat2x3 uniform variable array for the current program object.
//     *
//     * @param program   the handle of the program containing the uniform variable to be modified
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniformMatrix2x3fv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLfloat const *") FloatBuffer value)
//    {
//        nglProgramUniformMatrix2x3fv(program, location, value.remaining() / 6, transpose, memAddress(value));
//    }
//
//    // --- [ glProgramUniformMatrix3x2fv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniformMatrix3x2fv ProgramUniformMatrix3x2fv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglProgramUniformMatrix3x2fv(int program, int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single mat3x2 uniform variable or a mat3x2 uniform variable array for the current program object.
//     *
//     * @param program   the handle of the program containing the uniform variable to be modified
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniformMatrix3x2fv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLfloat const *") FloatBuffer value)
//    {
//        nglProgramUniformMatrix3x2fv(program, location, value.remaining() / 6, transpose, memAddress(value));
//    }
//
//    // --- [ glProgramUniformMatrix2x4fv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniformMatrix2x4fv ProgramUniformMatrix2x4fv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglProgramUniformMatrix2x4fv(int program, int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single mat2x4 uniform variable or a mat2x4 uniform variable array for the current program object.
//     *
//     * @param program   the handle of the program containing the uniform variable to be modified
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniformMatrix2x4fv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLfloat const *") FloatBuffer value)
//    {
//        nglProgramUniformMatrix2x4fv(program, location, value.remaining() > > 3, transpose, memAddress(value));
//    }
//
//    // --- [ glProgramUniformMatrix4x2fv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniformMatrix4x2fv ProgramUniformMatrix4x2fv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglProgramUniformMatrix4x2fv(int program, int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single mat4x2 uniform variable or a mat4x2 uniform variable array for the current program object.
//     *
//     * @param program   the handle of the program containing the uniform variable to be modified
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniformMatrix4x2fv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLfloat const *") FloatBuffer value)
//    {
//        nglProgramUniformMatrix4x2fv(program, location, value.remaining() > > 3, transpose, memAddress(value));
//    }
//
//    // --- [ glProgramUniformMatrix3x4fv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniformMatrix3x4fv ProgramUniformMatrix3x4fv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglProgramUniformMatrix3x4fv(int program, int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single mat3x4 uniform variable or a mat3x4 uniform variable array for the current program object.
//     *
//     * @param program   the handle of the program containing the uniform variable to be modified
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniformMatrix3x4fv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLfloat const *") FloatBuffer value)
//    {
//        nglProgramUniformMatrix3x4fv(program, location, value.remaining() / 12, transpose, memAddress(value));
//    }
//
//    // --- [ glProgramUniformMatrix4x3fv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniformMatrix4x3fv ProgramUniformMatrix4x3fv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglProgramUniformMatrix4x3fv(int program, int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single mat4x3 uniform variable or a mat4x3 uniform variable array for the current program object.
//     *
//     * @param program   the handle of the program containing the uniform variable to be modified
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniformMatrix4x3fv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLfloat const *") FloatBuffer value)
//    {
//        nglProgramUniformMatrix4x3fv(program, location, value.remaining() / 12, transpose, memAddress(value));
//    }
//
//    // --- [ glProgramUniformMatrix2x3dv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniformMatrix2x3dv ProgramUniformMatrix2x3dv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglProgramUniformMatrix2x3dv(int program, int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single dmat2x3 uniform variable or a dmat2x3 uniform variable array for the current program object.
//     *
//     * @param program   the handle of the program containing the uniform variable to be modified
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniformMatrix2x3dv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLdouble const *") DoubleBuffer value)
//    {
//        nglProgramUniformMatrix2x3dv(program, location, value.remaining() / 6, transpose, memAddress(value));
//    }
//
//    // --- [ glProgramUniformMatrix3x2dv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniformMatrix3x2dv ProgramUniformMatrix3x2dv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglProgramUniformMatrix3x2dv(int program, int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single dmat3x2 uniform variable or a dmat3x2 uniform variable array for the current program object.
//     *
//     * @param program   the handle of the program containing the uniform variable to be modified
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniformMatrix3x2dv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLdouble const *") DoubleBuffer value)
//    {
//        nglProgramUniformMatrix3x2dv(program, location, value.remaining() / 6, transpose, memAddress(value));
//    }
//
//    // --- [ glProgramUniformMatrix2x4dv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniformMatrix2x4dv ProgramUniformMatrix2x4dv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglProgramUniformMatrix2x4dv(int program, int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single dmat2x4 uniform variable or a dmat2x4 uniform variable array for the current program object.
//     *
//     * @param program   the handle of the program containing the uniform variable to be modified
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniformMatrix2x4dv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLdouble const *") DoubleBuffer value)
//    {
//        nglProgramUniformMatrix2x4dv(program, location, value.remaining() > > 3, transpose, memAddress(value));
//    }
//
//    // --- [ glProgramUniformMatrix4x2dv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniformMatrix4x2dv ProgramUniformMatrix4x2dv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglProgramUniformMatrix4x2dv(int program, int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single dmat4x2 uniform variable or a dmat4x2 uniform variable array for the current program object.
//     *
//     * @param program   the handle of the program containing the uniform variable to be modified
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniformMatrix4x2dv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLdouble const *") DoubleBuffer value)
//    {
//        nglProgramUniformMatrix4x2dv(program, location, value.remaining() > > 3, transpose, memAddress(value));
//    }
//
//    // --- [ glProgramUniformMatrix3x4dv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniformMatrix3x4dv ProgramUniformMatrix3x4dv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglProgramUniformMatrix3x4dv(int program, int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single dmat3x4 uniform variable or a dmat3x4 uniform variable array for the current program object.
//     *
//     * @param program   the handle of the program containing the uniform variable to be modified
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniformMatrix3x4dv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLdouble const *") DoubleBuffer value)
//    {
//        nglProgramUniformMatrix3x4dv(program, location, value.remaining() / 12, transpose, memAddress(value));
//    }
//
//    // --- [ glProgramUniformMatrix4x3dv ] ---
//
//    /**
//     * Unsafe version of: {@link #glProgramUniformMatrix4x3dv ProgramUniformMatrix4x3dv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglProgramUniformMatrix4x3dv(int program, int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single dmat4x3 uniform variable or a dmat4x3 uniform variable array for the current program object.
//     *
//     * @param program   the handle of the program containing the uniform variable to be modified
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
//     */
//    public static void glProgramUniformMatrix4x3dv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLdouble const *") DoubleBuffer value)
//    {
//        nglProgramUniformMatrix4x3dv(program, location, value.remaining() / 12, transpose, memAddress(value));
//    }
//
//    // --- [ glValidateProgramPipeline ] ---
//
//    /**
//     * Validates a program pipeline object against current GL state.
//     *
//     * @param pipeline the name of a program pipeline object to validate
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glValidateProgramPipeline">Reference Page</a>
//     */
//    public static native void glValidateProgramPipeline(@NativeType("GLuint") int pipeline);
//
//    // --- [ glGetProgramPipelineInfoLog ] ---
//
//    /**
//     * Unsafe version of: {@link #glGetProgramPipelineInfoLog GetProgramPipelineInfoLog}
//     *
//     * @param bufSize the maximum number of characters, including the null terminator, that may be written into {@code infoLog}
//     */
//    public static native void nglGetProgramPipelineInfoLog(int pipeline, int bufSize, long length, long infoLog);
//
//    /**
//     * Retrieves the info log string from a program pipeline object.
//     *
//     * @param pipeline the name of a program pipeline object from which to retrieve the info log
//     * @param length   a variable into which will be written the number of characters written into {@code infoLog}
//     * @param infoLog  an array of characters into which will be written the info log for {@code pipeline}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetProgramPipelineInfoLog">Reference Page</a>
//     */
//    public static void glGetProgramPipelineInfoLog(@NativeType("GLuint") int pipeline, @Nullable @NativeType("GLsizei *") IntBuffer length, @NativeType("GLchar *") ByteBuffer infoLog)
//    {
//        if (CHECKS) {
//            checkSafe(length, 1);
//        }
//        nglGetProgramPipelineInfoLog(pipeline, infoLog.remaining(), memAddressSafe(length), memAddress(infoLog));
//    }
//
//    /**
//     * Retrieves the info log string from a program pipeline object.
//     *
//     * @param pipeline the name of a program pipeline object from which to retrieve the info log
//     * @param bufSize  the maximum number of characters, including the null terminator, that may be written into {@code infoLog}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetProgramPipelineInfoLog">Reference Page</a>
//     */
//    @NativeType("void")
//    public static String glGetProgramPipelineInfoLog(@NativeType("GLuint") int pipeline, @NativeType("GLsizei") int bufSize)
//    {
//        MemoryStack stack = stackGet (); int stackPointer = stack . getPointer ();
//        ByteBuffer infoLog = memAlloc (bufSize);
//        try {
//            IntBuffer length = stack . ints (0);
//            nglGetProgramPipelineInfoLog(pipeline, bufSize, memAddress(length), memAddress(infoLog));
//            return memUTF8(infoLog, length.get(0));
//        } finally {
//            memFree(infoLog);
//            stack.setPointer(stackPointer);
//        }
//    }
//
//    /**
//     * Retrieves the info log string from a program pipeline object.
//     *
//     * @param pipeline the name of a program pipeline object from which to retrieve the info log
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetProgramPipelineInfoLog">Reference Page</a>
//     */
//    @NativeType("void")
//    public static String glGetProgramPipelineInfoLog(@NativeType("GLuint") int pipeline)
//    {
//        return glGetProgramPipelineInfoLog(pipeline, glGetProgramPipelinei(pipeline, GL20.GL_INFO_LOG_LENGTH));
//    }
//
//    // --- [ glVertexAttribL1d ] ---
//
//    /**
//     * Specifies the value of a generic vertex attribute. The y and z components are implicitly set to 0.0 and w to 1.0.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param x     the vertex attribute x component
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static native void glVertexAttribL1d(@NativeType("GLuint") int index, @NativeType("GLdouble") double x);
//
//    // --- [ glVertexAttribL2d ] ---
//
//    /**
//     * Specifies the value of a generic vertex attribute. The y component is implicitly set to 0.0 and w to 1.0.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param x     the vertex attribute x component
//     * @param y     the vertex attribute y component
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static native void glVertexAttribL2d(@NativeType("GLuint") int index, @NativeType("GLdouble") double x, @NativeType("GLdouble") double y);
//
//    // --- [ glVertexAttribL3d ] ---
//
//    /**
//     * Specifies the value of a generic vertex attribute. The w is implicitly set to 1.0.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param x     the vertex attribute x component
//     * @param y     the vertex attribute y component
//     * @param z     the vertex attribute z component
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static native void glVertexAttribL3d(@NativeType("GLuint") int index, @NativeType("GLdouble") double x, @NativeType("GLdouble") double y, @NativeType("GLdouble") double z);
//
//    // --- [ glVertexAttribL4d ] ---
//
//    /**
//     * Specifies the value of a generic vertex attribute.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param x     the vertex attribute x component
//     * @param y     the vertex attribute y component
//     * @param z     the vertex attribute z component
//     * @param w     the vertex attribute w component
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static native void glVertexAttribL4d(@NativeType("GLuint") int index, @NativeType("GLdouble") double x, @NativeType("GLdouble") double y, @NativeType("GLdouble") double z, @NativeType("GLdouble") double w);
//
//    // --- [ glVertexAttribL1dv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttribL1dv VertexAttribL1dv} */
//    public static native void nglVertexAttribL1dv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttribL1d VertexAttribL1d}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttribL1dv(@NativeType("GLuint") int index, @NativeType("GLdouble const *") DoubleBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 1);
//        }
//        nglVertexAttribL1dv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttribL2dv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttribL2dv VertexAttribL2dv} */
//    public static native void nglVertexAttribL2dv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttribL2d VertexAttribL2d}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttribL2dv(@NativeType("GLuint") int index, @NativeType("GLdouble const *") DoubleBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 2);
//        }
//        nglVertexAttribL2dv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttribL3dv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttribL3dv VertexAttribL3dv} */
//    public static native void nglVertexAttribL3dv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttribL3d VertexAttribL3d}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttribL3dv(@NativeType("GLuint") int index, @NativeType("GLdouble const *") DoubleBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 3);
//        }
//        nglVertexAttribL3dv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttribL4dv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttribL4dv VertexAttribL4dv} */
//    public static native void nglVertexAttribL4dv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttribL4d VertexAttribL4d}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttribL4dv(@NativeType("GLuint") int index, @NativeType("GLdouble const *") DoubleBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 4);
//        }
//        nglVertexAttribL4dv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttribLPointer ] ---
//
//    /**
//     * Unsafe version of: {@link #glVertexAttribLPointer VertexAttribLPointer}
//     *
//     * @param type the data type of each component in the array. Must be:<br><table><tr><td>{@link GL11#GL_DOUBLE DOUBLE}</td></tr></table>
//     */
//    public static native void nglVertexAttribLPointer(int index, int size, int type, int stride, long pointer);
//
//    /**
//     * Specifies the location and organization of a 64-bit vertex attribute array.
//     *
//     * @param index   the index of the generic vertex attribute to be modified
//     * @param size    the number of values per vertex that are stored in the array. The initial value is 4. One of:<br><table><tr><td>1</td><td>2</td><td>3</td><td>4</td><td>{@link GL12#GL_BGRA BGRA}</td></tr></table>
//     * @param type    the data type of each component in the array. Must be:<br><table><tr><td>{@link GL11#GL_DOUBLE DOUBLE}</td></tr></table>
//     * @param stride  the byte offset between consecutive generic vertex attributes. If stride is 0, the generic vertex attributes are understood to be tightly packed in
//     *                the array. The initial value is 0.
//     * @param pointer the vertex attribute data or the offset of the first component of the first generic vertex attribute in the array in the data store of the buffer
//     *                currently bound to the {@link GL15#GL_ARRAY_BUFFER ARRAY_BUFFER} target. The initial value is 0.
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttribLPointer">Reference Page</a>
//     */
//    public static void glVertexAttribLPointer(@NativeType("GLuint") int index, @NativeType("GLint") int size, @NativeType("GLenum") int type, @NativeType("GLsizei") int stride, @NativeType("void const *") ByteBuffer pointer)
//    {
//        nglVertexAttribLPointer(index, size, type, stride, memAddress(pointer));
//    }
//
//    /**
//     * Specifies the location and organization of a 64-bit vertex attribute array.
//     *
//     * @param index   the index of the generic vertex attribute to be modified
//     * @param size    the number of values per vertex that are stored in the array. The initial value is 4. One of:<br><table><tr><td>1</td><td>2</td><td>3</td><td>4</td><td>{@link GL12#GL_BGRA BGRA}</td></tr></table>
//     * @param type    the data type of each component in the array. Must be:<br><table><tr><td>{@link GL11#GL_DOUBLE DOUBLE}</td></tr></table>
//     * @param stride  the byte offset between consecutive generic vertex attributes. If stride is 0, the generic vertex attributes are understood to be tightly packed in
//     *                the array. The initial value is 0.
//     * @param pointer the vertex attribute data or the offset of the first component of the first generic vertex attribute in the array in the data store of the buffer
//     *                currently bound to the {@link GL15#GL_ARRAY_BUFFER ARRAY_BUFFER} target. The initial value is 0.
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttribLPointer">Reference Page</a>
//     */
//    public static void glVertexAttribLPointer(@NativeType("GLuint") int index, @NativeType("GLint") int size, @NativeType("GLenum") int type, @NativeType("GLsizei") int stride, @NativeType("void const *") long pointer)
//    {
//        nglVertexAttribLPointer(index, size, type, stride, pointer);
//    }
//
//    /**
//     * Specifies the location and organization of a 64-bit vertex attribute array.
//     *
//     * @param index   the index of the generic vertex attribute to be modified
//     * @param size    the number of values per vertex that are stored in the array. The initial value is 4. One of:<br><table><tr><td>1</td><td>2</td><td>3</td><td>4</td><td>{@link GL12#GL_BGRA BGRA}</td></tr></table>
//     * @param stride  the byte offset between consecutive generic vertex attributes. If stride is 0, the generic vertex attributes are understood to be tightly packed in
//     *                the array. The initial value is 0.
//     * @param pointer the vertex attribute data or the offset of the first component of the first generic vertex attribute in the array in the data store of the buffer
//     *                currently bound to the {@link GL15#GL_ARRAY_BUFFER ARRAY_BUFFER} target. The initial value is 0.
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttribLPointer">Reference Page</a>
//     */
//    public static void glVertexAttribLPointer(@NativeType("GLuint") int index, @NativeType("GLint") int size, @NativeType("GLsizei") int stride, @NativeType("void const *") DoubleBuffer pointer)
//    {
//        nglVertexAttribLPointer(index, size, GL11.GL_DOUBLE, stride, memAddress(pointer));
//    }
//
//    // --- [ glGetVertexAttribLdv ] ---
//
//    /** Unsafe version of: {@link #glGetVertexAttribLdv GetVertexAttribLdv} */
//    public static native void nglGetVertexAttribLdv(int index, int pname, long params);
//
//    /**
//     * Double version of {@link GL20C#glGetVertexAttribiv GetVertexAttribiv}.
//     *
//     * @param index  the generic vertex attribute parameter to be queried
//     * @param pname  the symbolic name of the vertex attribute parameter to be queried
//     * @param params the requested data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetVertexAttrib">Reference Page</a>
//     */
//    public static void glGetVertexAttribLdv(@NativeType("GLuint") int index, @NativeType("GLenum") int pname, @NativeType("GLdouble *") DoubleBuffer params)
//    {
//        if (CHECKS) {
//            check(params, 1);
//        }
//        nglGetVertexAttribLdv(index, pname, memAddress(params));
//    }
//
//    // --- [ glViewportArrayv ] ---
//
//    /**
//     * Unsafe version of: {@link #glViewportArrayv ViewportArrayv}
//     *
//     * @param count the number of viewports to set
//     */
//    public static native void nglViewportArrayv(int first, int count, long v);
//
//    /**
//     * Sets multiple viewports.
//     *
//     * @param first the first viewport to set
//     * @param v     an array containing the viewport parameters
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glViewportArrayv">Reference Page</a>
//     */
//    public static void glViewportArrayv(@NativeType("GLuint") int first, @NativeType("GLfloat const *") FloatBuffer v)
//    {
//        nglViewportArrayv(first, v.remaining() > > 2, memAddress(v));
//    }
//
//    // --- [ glViewportIndexedf ] ---
//
//    /**
//     * Sets a specified viewport.
//     *
//     * @param index the viewport to set
//     * @param x     the left viewport coordinate
//     * @param y     the bottom viewport coordinate
//     * @param w     the viewport width
//     * @param h     the viewport height
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glViewportIndexedf">Reference Page</a>
//     */
//    public static native void glViewportIndexedf(@NativeType("GLuint") int index, @NativeType("GLfloat") float x, @NativeType("GLfloat") float y, @NativeType("GLfloat") float w, @NativeType("GLfloat") float h);
//
//    // --- [ glViewportIndexedfv ] ---
//
//    /** Unsafe version of: {@link #glViewportIndexedfv ViewportIndexedfv} */
//    public static native void nglViewportIndexedfv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glViewportIndexedf ViewportIndexedf}.
//     *
//     * @param index the viewport to set
//     * @param v     the viewport parameters
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glViewportIndexed">Reference Page</a>
//     */
//    public static void glViewportIndexedfv(@NativeType("GLuint") int index, @NativeType("GLfloat const *") FloatBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 4);
//        }
//        nglViewportIndexedfv(index, memAddress(v));
//    }
//
//    // --- [ glScissorArrayv ] ---
//
//    /**
//     * Unsafe version of: {@link #glScissorArrayv ScissorArrayv}
//     *
//     * @param count the number of scissor boxes to modify
//     */
//    public static native void nglScissorArrayv(int first, int count, long v);
//
//    /**
//     * Defines the scissor box for multiple viewports.
//     *
//     * @param first the index of the first viewport whose scissor box to modify
//     * @param v     an array containing the left, bottom, width and height of each scissor box, in that order
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glScissorArrayv">Reference Page</a>
//     */
//    public static void glScissorArrayv(@NativeType("GLuint") int first, @NativeType("GLint const *") IntBuffer v)
//    {
//        nglScissorArrayv(first, v.remaining() > > 2, memAddress(v));
//    }
//
//    // --- [ glScissorIndexed ] ---
//
//    /**
//     * Defines the scissor box for a specific viewport.
//     *
//     * @param index  the index of the viewport whose scissor box to modify
//     * @param left   the left scissor box coordinate
//     * @param bottom the bottom scissor box coordinate
//     * @param width  the scissor box width
//     * @param height the scissor box height
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glScissorIndexed">Reference Page</a>
//     */
//    public static native void glScissorIndexed(@NativeType("GLuint") int index, @NativeType("GLint") int left, @NativeType("GLint") int bottom, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height);
//
//    // --- [ glScissorIndexedv ] ---
//
//    /** Unsafe version of: {@link #glScissorIndexedv ScissorIndexedv} */
//    public static native void nglScissorIndexedv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glScissorIndexed ScissorIndexed}.
//     *
//     * @param index the index of the viewport whose scissor box to modify
//     * @param v     an array containing the left, bottom, width and height of each scissor box, in that order
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glScissorIndexed">Reference Page</a>
//     */
//    public static void glScissorIndexedv(@NativeType("GLuint") int index, @NativeType("GLint const *") IntBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 4);
//        }
//        nglScissorIndexedv(index, memAddress(v));
//    }
//
//    // --- [ glDepthRangeArrayv ] ---
//
//    /**
//     * Unsafe version of: {@link #glDepthRangeArrayv DepthRangeArrayv}
//     *
//     * @param count the number of viewports whose depth range to update
//     */
//    public static native void nglDepthRangeArrayv(int first, int count, long v);
//
//    /**
//     * Specifies mapping of depth values from normalized device coordinates to window coordinates for a specified set of viewports.
//     *
//     * @param first the index of the first viewport whose depth range to update
//     * @param v     n array containing the near and far values for the depth range of each modified viewport
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDepthRangeArrayv">Reference Page</a>
//     */
//    public static void glDepthRangeArrayv(@NativeType("GLuint") int first, @NativeType("GLdouble const *") DoubleBuffer v)
//    {
//        nglDepthRangeArrayv(first, v.remaining() > > 1, memAddress(v));
//    }
//
//    // --- [ glDepthRangeIndexed ] ---
//
//    /**
//     * Specifies mapping of depth values from normalized device coordinates to window coordinates for a specified viewport.
//     *
//     * @param index the index of the viewport whose depth range to update
//     * @param zNear the mapping of the near clipping plane to window coordinates. The initial value is 0.
//     * @param zFar  the mapping of the far clipping plane to window coordinates. The initial value is 1.
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDepthRangeIndexed">Reference Page</a>
//     */
//    public static native void glDepthRangeIndexed(@NativeType("GLuint") int index, @NativeType("GLdouble") double zNear, @NativeType("GLdouble") double zFar);
//
//    // --- [ glGetFloati_v ] ---
//
//    /** Unsafe version of: {@link #glGetFloati_v GetFloati_v} */
//    public static native void nglGetFloati_v(int target, int index, long data );
//
//    /**
//     * Queries the float value of an indexed state variable.
//     *
//     * @param target the indexed state to query
//     * @param index  the index of the element being queried
//     * @param data   a scalar or buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetFloati_v">Reference Page</a>
//     */
//    public static void glGetFloati_v(@NativeType("GLenum") int target, @NativeType("GLuint") int index, @NativeType("GLfloat *") FloatBuffer data )
//    {
//        if (CHECKS) {
//            check(data, 1);
//        }
//        nglGetFloati_v(target, index, memAddress(data));
//    }
//
//    /**
//     * Queries the float value of an indexed state variable.
//     *
//     * @param target the indexed state to query
//     * @param index  the index of the element being queried
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetFloati_v">Reference Page</a>
//     */
//    @NativeType("void")
//    public static float glGetFloati(@NativeType("GLenum") int target, @NativeType("GLuint") int index)
//    {
//        MemoryStack stack = stackGet (); int stackPointer = stack . getPointer ();
//        try {
//            FloatBuffer data = stack . callocFloat (1);
//            nglGetFloati_v(target, index, memAddress(data));
//            return data.get(0);
//        } finally {
//            stack.setPointer(stackPointer);
//        }
//    }
//
//    // --- [ glGetDoublei_v ] ---
//
//    /** Unsafe version of: {@link #glGetDoublei_v GetDoublei_v} */
//    public static native void nglGetDoublei_v(int target, int index, long data );
//
//    /**
//     * Queries the double value of an indexed state variable.
//     *
//     * @param target the indexed state to query
//     * @param index  the index of the element being queried
//     * @param data   a scalar or buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetDoublei_v">Reference Page</a>
//     */
//    public static void glGetDoublei_v(@NativeType("GLenum") int target, @NativeType("GLuint") int index, @NativeType("GLdouble *") DoubleBuffer data )
//    {
//        if (CHECKS) {
//            check(data, 1);
//        }
//        nglGetDoublei_v(target, index, memAddress(data));
//    }
//
//    /**
//     * Queries the double value of an indexed state variable.
//     *
//     * @param target the indexed state to query
//     * @param index  the index of the element being queried
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetDoublei_v">Reference Page</a>
//     */
//    @NativeType("void")
//    public static double glGetDoublei(@NativeType("GLenum") int target, @NativeType("GLuint") int index)
//    {
//        MemoryStack stack = stackGet (); int stackPointer = stack . getPointer ();
//        try {
//            DoubleBuffer data = stack . callocDouble (1);
//            nglGetDoublei_v(target, index, memAddress(data));
//            return data.get(0);
//        } finally {
//            stack.setPointer(stackPointer);
//        }
//    }
}