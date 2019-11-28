package gln

import glm_.BYTES
import glm_.i
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
import gln.identifiers.GlProgram
import gln.program.GlPipeline
import gln.program.GlPipelines
import kool.*
import org.lwjgl.opengl.GL41C
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.system.MemoryUtil.memGetInt
import unsigned.Uint
import java.nio.ByteBuffer
import java.nio.DoubleBuffer
import java.nio.FloatBuffer
import java.nio.IntBuffer
import kotlin.reflect.KMutableProperty0

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
            Stack {
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
        val format = Stack.intAdr { GL41C.nglGetProgramBinary(program.name, data.rem, NULL, it, data.adr) }
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
    fun createShaderProgram(type: ShaderType, vararg strings: CharSequence): GlProgram = Stack {
        val pStrings = it.PointerBuffer(strings.size)
        for (i in strings.indices) pStrings[i] = it.UTF8(strings[i])
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
    fun deleteProgramPipelines(pipeline: GlPipeline) = Stack.intAdr(pipeline.name) { GL41C.nglDeleteProgramPipelines(1, it) }

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
    fun genProgramPipelines(pipeline: KMutableProperty0<GlPipeline>): GlPipeline{
        pipeline.set(genProgramPipelines())
        return pipeline()
    }

    /**
     * Reserves program pipeline object names.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenProgramPipelines">Reference Page</a>
     */
    fun genProgramPipelines(): GlPipeline = GlPipeline(Stack.intAdr { GL41C.nglGenProgramPipelines(1, it) })

    // --- [ glIsProgramPipeline ] ---

    /**
     * Determines if a name corresponds to a program pipeline object.
     *
     * @param pipeline a value that may be the name of a program pipeline object
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glIsProgramPipeline">Reference Page</a>
     */
    fun isProgramPipeline(pipeline: GlPipeline) = GL41C.glIsProgramPipeline(pipeline.name)

    // --- [ glGetProgramPipelineiv ] ---

    /**
     * Retrieves properties of a program pipeline object.
     *
     * @param pipeline the name of a program pipeline object whose parameter retrieve
     * @param name    the name of the parameter to retrieve. One of:<br><table><tr><td>{@link #GL_ACTIVE_PROGRAM ACTIVE_PROGRAM}</td><td>{@link GL20#GL_INFO_LOG_LENGTH INFO_LOG_LENGTH}</td><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td></tr><tr><td>{@link GL40#GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td><td>{@link GL40#GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetProgramPipeline">Reference Page</a>
     */
    fun getProgramPipeline(pipeline: GlPipeline, name: GetProgramPipeline) =
            Stack.intAdr { GL41C.nglGetProgramPipelineiv(pipeline.name, name.i, it) }

    // --- [ glProgramUniform1i ] ---

    /**
     * Specifies the value of an int uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param x        the uniform x value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, x: Int) = GL41C.glProgramUniform1i(program.name, location, x)

    /**
     * Specifies the value of an int uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, v: Vec1i) = GL41C.glProgramUniform1i(program.name, location, v.x)

    // --- [ glProgramUniform2i ] ---

    /**
     * Specifies the value of an ivec2 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param x        the uniform x value
     * @param y        the uniform y value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, x: Int, y: Int) = GL41C.glProgramUniform2i(program.name, location, x, y)

    /**
     * Specifies the value of an ivec2 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, v: Vec2i) = GL41C.glProgramUniform2i(program.name, location, v.x, v.y)

    // --- [ glProgramUniform3i ] ---

    /**
     * Specifies the value of an ivec3 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param x        the uniform x value
     * @param y        the uniform y value
     * @param z        the uniform z value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, x: Int, y: Int, z: Int) = GL41C.glProgramUniform3i(program.name, location, x, y, z)

    /**
     * Specifies the value of an ivec3 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, v: Vec3i) = GL41C.glProgramUniform3i(program.name, location, v.x, v.y, v.z)

    // --- [ glProgramUniform4i ] ---

    /**
     * Specifies the value of an ivec4 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param x        the uniform x value
     * @param y        the uniform y value
     * @param z        the uniform z value
     * @param w        the uniform w value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, x: Int, y: Int, z: Int, w: Int) = GL41C.glProgramUniform4i(program.name, location, x, y, z, w)

    /**
     * Specifies the value of an ivec4 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, v: Vec4i) = GL41C.glProgramUniform4i(program.name, location, v.x, v.y, v.z, v.w)

    // --- [ glProgramUniform1ui ] ---

    /**
     * Specifies the value of a uint uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param x        the uniform x value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, x: Uint) = GL41C.glProgramUniform1ui(program.name, location, x.v)

    /**
     * Specifies the value of a uint uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, v: Vec1ui) = GL41C.glProgramUniform1ui(program.name, location, v.x.v)

    // --- [ glProgramUniform2ui ] ---

    /**
     * Specifies the value of a uvec2 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param x        the uniform x value
     * @param y        the uniform y value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, x: Uint, y: Uint) = GL41C.glProgramUniform2ui(program.name, location, x.v, y.v)

    /**
     * Specifies the value of a uvec2 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, v: Vec2ui) = GL41C.glProgramUniform2ui(program.name, location, v.x.v, v.y.v)

    // --- [ glProgramUniform3ui ] ---

    /**
     * Specifies the value of a uvec3 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param x        the uniform x value
     * @param y        the uniform y value
     * @param z        the uniform z value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, x: Uint, y: Uint, z: Uint) = GL41C.glProgramUniform3ui(program.name, location, x.v, y.v, z.v)

    /**
     * Specifies the value of a uvec3 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param x        the uniform x value
     * @param y        the uniform y value
     * @param z        the uniform z value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, v: Vec3ui) = GL41C.glProgramUniform3ui(program.name, location, v.x.v, v.y.v, v.z.v)

    // --- [ glProgramUniform4ui ] ---

    /**
     * Specifies the value of a uvec4 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param x        the uniform x value
     * @param y        the uniform y value
     * @param z        the uniform z value
     * @param w        the uniform w value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, x: Uint, y: Uint, z: Uint, w: Uint) = GL41C.glProgramUniform4ui(program.name, location, x.v, y.v, z.v, w.v)

    /**
     * Specifies the value of a uvec4 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, v: Vec4ui) = GL41C.glProgramUniform4ui(program.name, location, v.x.v, v.y.v, v.z.v, v.w.v)

    // --- [ glProgramUniform1f ] ---

    /**
     * Specifies the value of a float uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param x        the uniform x value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, x: Float) = GL41C.glProgramUniform1f(program.name, location, x)

    /**
     * Specifies the value of a float uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, v: Vec1) = GL41C.glProgramUniform1f(program.name, location, v.x)

    // --- [ glProgramUniform2f ] ---

    /**
     * Specifies the value of a vec2 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param x        the uniform x value
     * @param y        the uniform y value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, x: Float, y: Float) = GL41C.glProgramUniform2f(program.name, location, x, y)

    /**
     * Specifies the value of a vec2 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, v: Vec2) = GL41C.glProgramUniform2f(program.name, location, v.x, v.y)

    // --- [ glProgramUniform3f ] ---

    /**
     * Specifies the value of a vec3 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param x        the uniform x value
     * @param y        the uniform y value
     * @param z        the uniform z value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, x: Float, y: Float, z: Float) = GL41C.glProgramUniform3f(program.name, location, x, y, z)

    /**
     * Specifies the value of a vec3 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, v: Vec3) = GL41C.glProgramUniform3f(program.name, location, v.x, v.y, v.z)

    // --- [ glProgramUniform4f ] ---

    /**
     * Specifies the value of a vec4 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param x        the uniform x value
     * @param y        the uniform y value
     * @param z        the uniform z value
     * @param w        the uniform w value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, x: Float, y: Float, z: Float, w: Float) = GL41C.glProgramUniform4f(program.name, location, x, y, z, w)

    /**
     * Specifies the value of a vec4 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, v: Vec4) = GL41C.glProgramUniform4f(program.name, location, v.x, v.y, v.z, v.w)

    // --- [ glProgramUniform1d ] ---

    /**
     * Specifies the value of a double uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param x        the uniform x value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, x: Double) = GL41C.glProgramUniform1d(program.name, location, x)

    /**
     * Specifies the value of a double uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, v: Vec1d) = GL41C.glProgramUniform1d(program.name, location, v.x)

    // --- [ glProgramUniform2d ] ---

    /**
     * Specifies the value of a dvec2 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param x        the uniform x value
     * @param y        the uniform y value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, x: Double, y: Double) = GL41C.glProgramUniform2d(program.name, location, x, y)

    /**
     * Specifies the value of a dvec2 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, v: Vec2d) = GL41C.glProgramUniform2d(program.name, location, v.x, v.y)

    // --- [ glProgramUniform3d ] ---

    /**
     * Specifies the value of a dvec3 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param x        the uniform x value
     * @param y        the uniform y value
     * @param z        the uniform z value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, x: Double, y: Double, z: Double) = GL41C.glProgramUniform3d(program.name, location, x, y, z)

    /**
     * Specifies the value of a dvec3 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, v: Vec3d) = GL41C.glProgramUniform3d(program.name, location, v.x, v.y, v.z)

    // --- [ glProgramUniform4d ] ---

    /**
     * Specifies the value of a dvec4 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param x        the uniform x value
     * @param y        the uniform y value
     * @param z        the uniform z value
     * @param w        the uniform w value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, x: Double, y: Double, z: Double, w: Double) = GL41C.glProgramUniform4d(program.name, location, x, y, z, w)

    /**
     * Specifies the value of a dvec4 uniform variable for a specified program object.
     *
     * @param program  the handle of the program containing the uniform variable to be modified
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, v: Vec4d) = GL41C.glProgramUniform4d(program.name, location, v.x, v.y, v.z, v.w)

//    // --- [ glProgramUniform1iv ] --- TODO?
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
    // --- [ glProgramUniformMatrix2fv ] ---

    /**
     * Specifies the value of a single mat2 uniform variable or a mat2 uniform variable array for the current program object.
     *
     * @param program   the handle of the program containing the uniform variable to be modified
     * @param location  the location of the uniform variable to be modified
     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, value: Mat2) =
            Stack { GL41C.nglProgramUniformMatrix2fv(program.name, location, 1, false, value.toBuffer(it).adr) }

    // --- [ glProgramUniformMatrix3fv ] ---

    /**
     * Specifies the value of a single mat3 uniform variable or a mat3 uniform variable array for the current program object.
     *
     * @param program   the handle of the program containing the uniform variable to be modified
     * @param location  the location of the uniform variable to be modified
     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, value: Mat3) =
            Stack { GL41C.nglProgramUniformMatrix3fv(program.name, location, 1, false, value.toBuffer(it).adr) }

    // --- [ glProgramUniformMatrix4fv ] ---

    /**
     * Specifies the value of a single mat4 uniform variable or a mat4 uniform variable array for the current program object.
     *
     * @param program   the handle of the program containing the uniform variable to be modified
     * @param location  the location of the uniform variable to be modified
     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, value: Mat4) =
            Stack { GL41C.nglProgramUniformMatrix4fv(program.name, location, 1, false, value.toBuffer(it).adr) }

    // --- [ glProgramUniformMatrix2dv ] ---

    /**
     * Specifies the value of a single dmat2 uniform variable or a dmat2 uniform variable array for the current program object.
     *
     * @param program   the handle of the program containing the uniform variable to be modified
     * @param location  the location of the uniform variable to be modified
     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, value: Mat2d) =
            Stack { GL41C.nglProgramUniformMatrix2dv(program.name, location, 1, false, value.toBuffer(it).adr) }

    // --- [ glProgramUniformMatrix3dv ] ---

    /**
     * Specifies the value of a single dmat3 uniform variable or a dmat3 uniform variable array for the current program object.
     *
     * @param program   the handle of the program containing the uniform variable to be modified
     * @param location  the location of the uniform variable to be modified
     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, value: Mat3d) =
            Stack { GL41C.nglProgramUniformMatrix3dv(program.name, location, 1, false, value.toBuffer(it).adr) }

    // --- [ glProgramUniformMatrix4dv ] ---

    /**
     * Specifies the value of a single dmat4 uniform variable or a dmat4 uniform variable array for the current program object.
     *
     * @param program   the handle of the program containing the uniform variable to be modified
     * @param location  the location of the uniform variable to be modified
     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
     * @param value     an array of {@code count} values that will be used to update the specified uniform matrix variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glProgramUniform">Reference Page</a>
     */
    fun programUniform(program: GlProgram, location: UniformLocation, value: Mat4d) =
            Stack { GL41C.nglProgramUniformMatrix4dv(program.name, location, 1, false, value.toBuffer(it).adr) }

//    // --- [ glProgramUniformMatrix2x3fv ] --- TODO
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
//    fun programUniform(program: GlProgram, location: UniformLocation, value: Mat2x3): Nothing = TODO()
////    {
////        nglProgramUniformMatrix2x3fv(program, location, value.remaining() / 6, transpose, memAddress(value));
////    }
//
//    // --- [ glProgramUniformMatrix3x2fv ] ---
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
//    fun programUniform(program: GlProgram, location: UniformLocation, value: Mat3x2): Nothing = TODO()
////    {
////        nglProgramUniformMatrix3x2fv(program, location, value.remaining() / 6, transpose, memAddress(value));
////    }
//
//    // --- [ glProgramUniformMatrix2x4fv ] ---
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

    // --- [ glValidateProgramPipeline ] ---

    /**
     * Validates a program pipeline object against current GL state.
     *
     * @param pipeline the name of a program pipeline object to validate
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glValidateProgramPipeline">Reference Page</a>
     */
    fun validateProgramPipeline(pipeline: GlPipeline) = GL41C.glValidateProgramPipeline(pipeline.name)

    // --- [ glGetProgramPipelineInfoLog ] ---

    /**
     * Retrieves the info log string from a program pipeline object.
     *
     * @param pipeline the name of a program pipeline object from which to retrieve the info log
     * @param bufSize  the maximum number of characters, including the null terminator, that may be written into {@code infoLog}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetProgramPipelineInfoLog">Reference Page</a>
     */
    fun getProgramPipelineInfoLog(pipeline: GlPipeline,
                                  bufSize: Int = getProgramPipeline(pipeline, GetProgramPipeline.INFO_LOG_LENGTH)): String =
            Stack.asciiAddress(bufSize) { pLength, pString ->
                GL41C.nglGetProgramPipelineInfoLog(pipeline.name, bufSize, pLength, pString)
            }

    // --- [ glVertexAttribL1d ] ---

    /**
     * Specifies the value of a generic vertex attribute. The y and z components are implicitly set to 0.0 and w to 1.0.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param x     the vertex attribute x component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttribL(index: Int, x: Double) = GL41C.glVertexAttribL1d(index, x)

    /**
     * Specifies the value of a generic vertex attribute. The y and z components are implicitly set to 0.0 and w to 1.0.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v     the vertex attribute v component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttribL(index: Int, v: Vec1d) = GL41C.glVertexAttribL1d(index, v.x)

    // --- [ glVertexAttribL2d ] ---

    /**
     * Specifies the value of a generic vertex attribute. The y component is implicitly set to 0.0 and w to 1.0.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param x     the vertex attribute x component
     * @param y     the vertex attribute y component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttribL(index: Int, x: Double, y: Double) = GL41C.glVertexAttribL2d(index, x, y)

    /**
     * Specifies the value of a generic vertex attribute. The y component is implicitly set to 0.0 and w to 1.0.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v     the vertex attribute
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttribL(index: Int, v: Vec2d) = GL41C.glVertexAttribL2d(index, v.x, v.y)

    // --- [ glVertexAttribL3d ] ---

    /**
     * Specifies the value of a generic vertex attribute. The w is implicitly set to 1.0.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param x     the vertex attribute x component
     * @param y     the vertex attribute y component
     * @param z     the vertex attribute z component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttribL(index: Int, x: Double, y: Double, z: Double) = GL41C.glVertexAttribL3d(index, x, y, z)

    /**
     * Specifies the value of a generic vertex attribute. The w is implicitly set to 1.0.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v     the vertex attribute
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttribL(index: Int, v: Vec3d) = GL41C.glVertexAttribL3d(index, v.x, v.y, v.z)

    // --- [ glVertexAttribL4d ] ---

    /**
     * Specifies the value of a generic vertex attribute.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param x     the vertex attribute x component
     * @param y     the vertex attribute y component
     * @param z     the vertex attribute z component
     * @param w     the vertex attribute w component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttribL(index: Int, x: Double, y: Double, z: Double, w: Double) = GL41C.glVertexAttribL4d(index, x, y, z, w)

    /**
     * Specifies the value of a generic vertex attribute.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v     the vertex attribute
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttribL(index: Int, v: Vec4d) = GL41C.glVertexAttribL4d(index, v.x, v.y, v.z, v.w)

//    // --- [ glVertexAttribL1dv ] --- TODO?
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
    // --- [ glViewportArrayv ] ---

    /**
     * Sets multiple viewports.
     *
     * @param first the first viewport to set
     * @param v     an array containing the viewport parameters
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glViewportArrayv">Reference Page</a>
     */
    fun viewportArray(first: Int, v: FloatBuffer) = GL41C.nglViewportArrayv(first, v.rem shr 2, v.adr)

    // --- [ glViewportIndexedf ] ---

    /**
     * Sets a specified viewport.
     *
     * @param index the viewport to set
     * @param x     the left viewport coordinate
     * @param y     the bottom viewport coordinate
     * @param w     the viewport width
     * @param h     the viewport height
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glViewportIndexedf">Reference Page</a>
     */
    fun viewportIndexed(index: Int, x: Float, y: Float, z: Float, w: Float) = GL41C.glViewportIndexedf(index, x, y, z, w)

    /**
     * Sets a specified viewport.
     *
     * @param index     the viewport to set
     * @param viewport  the viewport
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glViewportIndexedf">Reference Page</a>
     */
    fun viewportIndexed(index: Int, viewport: Vec4) = GL41C.glViewportIndexedf(index, viewport.x, viewport.y, viewport.z, viewport.w)

    // --- [ glViewportIndexedfv ] ---

    /**
     * Pointer version of {@link #glViewportIndexedf ViewportIndexedf}.
     *
     * @param index the viewport to set
     * @param v     the viewport parameters
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glViewportIndexed">Reference Page</a>
     */
    fun viewportIndexed(index: Int, v: FloatBuffer) = GL41C.nglViewportIndexedfv(index, v.adr)

    // --- [ glScissorArrayv ] ---

    /**
     * Defines the scissor box for multiple viewports.
     *
     * @param first the index of the first viewport whose scissor box to modify
     * @param v     an array containing the left, bottom, width and height of each scissor box, in that order
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glScissorArrayv">Reference Page</a>
     */
    fun scissorArray(first: Int, v: IntBuffer) = GL41C.nglScissorArrayv(first, v.rem shr 2, v.adr)

    // --- [ glScissorIndexed ] ---

    /**
     * Defines the scissor box for a specific viewport.
     *
     * @param index  the index of the viewport whose scissor box to modify
     * @param left   the left scissor box coordinate
     * @param bottom the bottom scissor box coordinate
     * @param width  the scissor box width
     * @param height the scissor box height
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glScissorIndexed">Reference Page</a>
     */
    fun scissorIndexed(index: Int, left: Int, bottom: Int, width: Int, height: Int) = GL41C.glScissorIndexed(index, left, bottom, width, height)

    /**
     * Defines the scissor box for a specific viewport.
     *
     * @param index     the index of the viewport whose scissor box to modify
     * @param scissor   the scissor box
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glScissorIndexed">Reference Page</a>
     */
    fun scissorIndexed(index: Int, scissor: Vec4i) = GL41C.glScissorIndexed(index, scissor.x, scissor.y, scissor.z, scissor.w)

    // --- [ glScissorIndexedv ] ---

    /**
     * Pointer version of {@link #glScissorIndexed ScissorIndexed}.
     *
     * @param index the index of the viewport whose scissor box to modify
     * @param v     an array containing the left, bottom, width and height of each scissor box, in that order
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glScissorIndexed">Reference Page</a>
     */
    fun scissorIndexed(index: Int, v: IntBuffer) = GL41C.nglScissorIndexedv(index, v.adr)

    // --- [ glDepthRangeArrayv ] ---

    /**
     * Specifies mapping of depth values from normalized device coordinates to window coordinates for a specified set of viewports.
     *
     * @param first the index of the first viewport whose depth range to update
     * @param v     n array containing the near and far values for the depth range of each modified viewport
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDepthRangeArrayv">Reference Page</a>
     */
    fun depthRangeArray(first: Int, v: DoubleBuffer) = GL41C.nglDepthRangeArrayv(first, v.rem shr 1, v.adr)

    // --- [ glDepthRangeIndexed ] ---

    /**
     * Specifies mapping of depth values from normalized device coordinates to window coordinates for a specified viewport.
     *
     * @param index the index of the viewport whose depth range to update
     * @param zNear the mapping of the near clipping plane to window coordinates. The initial value is 0.
     * @param zFar  the mapping of the far clipping plane to window coordinates. The initial value is 1.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDepthRangeIndexed">Reference Page</a>
     */
    fun depthRangeIndexed(index: Int, zNear: Double, zFar: Double) = GL41C.glDepthRangeIndexed(index, zNear, zFar)

    // --- [ glGetFloati_v ] ---
    // inline reified

    // --- [ glGetDoublei_v ] ---
    // inline reified
}