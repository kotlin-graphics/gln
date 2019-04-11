/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package gln

import glm_.BYTES
import glm_.L
import glm_.bool
import glm_.mat2x2.Mat2
import glm_.mat3x3.Mat3
import glm_.mat4x4.Mat4
import glm_.vec1.Vec1
import glm_.vec1.Vec1d
import glm_.vec1.Vec1i
import glm_.vec1.Vec1s
import glm_.vec2.Vec2
import glm_.vec2.Vec2d
import glm_.vec2.Vec2i
import glm_.vec2.Vec2s
import glm_.vec3.Vec3
import glm_.vec3.Vec3d
import glm_.vec3.Vec3i
import glm_.vec3.Vec3s
import glm_.vec4.Vec4
import glm_.vec4.Vec4d
import glm_.vec4.Vec4s
import gln.glf.VertexAttribute
import gln.glf.VertexLayout
import gln.identifiers.GLshaders
import gln.identifiers.GlProgram
import gln.identifiers.GlShader
import kool.*
import org.lwjgl.opengl.*
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.system.MemoryUtil.memGetInt
import unsigned.Ubyte
import java.nio.DoubleBuffer
import java.nio.FloatBuffer
import java.nio.IntBuffer

/**
 * The OpenGL functionality of a forward compatible context, up to version 2.0.
 *
 * <p>Extensions promoted to core in this release:</p>
 *
 * <ul>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_shader_objects.txt">ARB_shader_objects</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_vertex_shader.txt">ARB_vertex_shader</a> and <a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_fragment_shader.txt">ARB_fragment_shader</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_shading_language_100.txt">ARB_shading_language_100</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_draw_buffers.txt">ARB_draw_buffers</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_texture_non_power_of_two.txt">ARB_texture_non_power_of_two</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_point_sprite.txt">ARB_point_sprite</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ATI/ATI_separate_stencil.txt">ATI_separate_stencil</a> and <a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_stencil_two_side.txt">EXT_stencil_two_side</a></li>
 * </ul>
 */
interface gl20i {

    // --- [ glCreateProgram ] ---

    /**
     * Creates a program object.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateProgram">Reference Page</a>
     */
    fun createProgram(): GlProgram = GlProgram(GL20C.glCreateProgram())

    // --- [ glDeleteProgram ] ---

    /**
     * Deletes a program object.
     *
     * @param program the program object to be deleted
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteProgram">Reference Page</a>
     */
    fun deleteProgram(program: GlProgram) = GL20C.glDeleteProgram(program.name)

    // --- [ glIsProgram ] ---

    /**
     * Returns {@link GL11#GL_TRUE TRUE} if {@code program} is the name of a program object. If {@code program} is zero, or a non-zero value that is not the name of a program
     * object, IsProgram returns {@link GL11#GL_FALSE FALSE}. No error is generated if program is not a valid program object name.
     *
     * @param program the program object name to query
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glIsProgram">Reference Page</a>
     */
    fun isProgram(program: GlProgram) = GL20C.glIsProgram(program.name)

    // --- [ glCreateShader ] ---

    /**
     * Creates a shader object.
     *
     * @param type the type of shader to be created. One of:<br><table><tr><td>{@link #GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link #GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td><td>{@link GL40#GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td></tr><tr><td>{@link GL40#GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateShader">Reference Page</a>
     */
    fun createShader(type: ShaderType): GlShader = GlShader(GL20C.glCreateShader(type.i))

    // --- [ glDeleteShader ] ---

    /**
     * Deletes a shader object.
     *
     * @param shader the shader object to be deleted
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteShader">Reference Page</a>
     */
    fun deleteShader(shader: GlShader) = GL20C.glDeleteShader(shader.name)

    // --- [ glIsShader ] ---

    /**
     * Returns {@link GL11#GL_TRUE TRUE} if {@code shader} is the name of a shader object. If {@code shader} is zero, or a nonzero value that is not the name of a shader
     * object, IsShader returns {@link GL11#GL_FALSE FALSE}. No error is generated if shader is not a valid shader object name.
     *
     * @param shader the shader object name to query
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glIsShader">Reference Page</a>
     */
    fun isShader(shader: GlShader) = GL20C.glIsShader(shader.name)

    // --- [ glAttachShader ] ---

    /**
     * Attaches a shader object to a program object.
     *
     * <p>In order to create a complete shader program, there must be a way to specify the list of things that will be linked together. Program objects provide
     * this mechanism. Shaders that are to be linked together in a program object must first be attached to that program object. glAttachShader attaches the
     * shader object specified by shader to the program object specified by program. This indicates that shader will be included in link operations that will
     * be performed on program.</p>
     *
     * <p>All operations that can be performed on a shader object are valid whether or not the shader object is attached to a program object. It is permissible to
     * attach a shader object to a program object before source code has been loaded into the shader object or before the shader object has been compiled. It
     * is permissible to attach multiple shader objects of the same type because each may contain a portion of the complete shader. It is also permissible to
     * attach a shader object to more than one program object. If a shader object is deleted while it is attached to a program object, it will be flagged for
     * deletion, and deletion will not occur until glDetachShader is called to detach it from all program objects to which it is attached.</p>
     *
     * @param program the program object to which a shader object will be attached
     * @param shader  the shader object that is to be attached
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glAttachShader">Reference Page</a>
     */
    fun attachShader(program: GlProgram, shader: GlShader) = GL20C.glAttachShader(program.name, shader.name)

    // --- [ glDetachShader ] ---

    /**
     * Detaches a shader object from a program object to which it is attached.
     *
     * @param program the program object from which to detach the shader object
     * @param shader  the shader object to be detached
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDetachShader">Reference Page</a>
     */
    fun detachShader(program: GlProgram, shader: GlShader) = GL20C.glDetachShader(program.name, shader.name)

    // --- [ glShaderSource ] ---

    /**
     * Sets the source code in {@code shader} to the source code in the array of strings specified by {@code strings}. Any source code previously stored in the
     * shader object is completely replaced. The number of strings in the array is specified by {@code count}. If {@code length} is {@code NULL}, each string is
     * assumed to be null terminated. If {@code length} is a value other than {@code NULL}, it points to an array containing a string length for each of the
     * corresponding elements of {@code strings}. Each element in the length array may contain the length of the corresponding string (the null character is not
     * counted as part of the string length) or a value less than 0 to indicate that the string is null terminated. The source code strings are not scanned or
     * parsed at this time; they are simply copied into the specified shader object.
     *
     * @param shader  the shader object whose source code is to be replaced
     * @param strings an array of pointers to strings containing the source code to be loaded into the shader
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glShaderSource">Reference Page</a>
     */
    fun shaderSource(shader: GlShader, vararg strings: CharSequence) = GL20C.glShaderSource(shader.name, *strings)

    /**
     * Sets the source code in {@code shader} to the source code in the array of strings specified by {@code strings}. Any source code previously stored in the
     * shader object is completely replaced. The number of strings in the array is specified by {@code count}. If {@code length} is {@code NULL}, each string is
     * assumed to be null terminated. If {@code length} is a value other than {@code NULL}, it points to an array containing a string length for each of the
     * corresponding elements of {@code strings}. Each element in the length array may contain the length of the corresponding string (the null character is not
     * counted as part of the string length) or a value less than 0 to indicate that the string is null terminated. The source code strings are not scanned or
     * parsed at this time; they are simply copied into the specified shader object.
     *
     * @param shader the shader object whose source code is to be replaced
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glShaderSource">Reference Page</a>
     */
    fun shaderSource(shader: GlShader, string: CharSequence) = GL20C.glShaderSource(shader.name, string)

    // --- [ glCompileShader ] ---

    /**
     * Compiles a shader object.
     *
     * @param shader the shader object to be compiled
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCompileShader">Reference Page</a>
     */
    fun compileShader(shader: GlShader) = GL20C.glCompileShader(shader.name)

    // --- [ glLinkProgram ] ---

    /**
     * Links a program object.
     *
     * @param program the program object to be linked
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glLinkProgram">Reference Page</a>
     */
    fun linkProgram(program: GlProgram) = GL20C.glLinkProgram(program.name)

    // --- [ glUseProgram ] ---

    /**
     * Installs a program object as part of current rendering state.
     *
     * @param program the program object whose executables are to be used as part of current rendering state
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUseProgram">Reference Page</a>
     */
    fun useProgram(program: GlProgram = GlProgram.NULL) = GL20C.glUseProgram(program.name)

    // --- [ glValidateProgram ] ---

    /**
     * Validates a program object.
     *
     * @param program the program object to be validated
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glValidateProgram">Reference Page</a>
     */
    fun validateProgram(program: GlProgram) = GL20C.glValidateProgram(program.name)

    // --- [ glUniform1f ] ---

    /**
     * Specifies the value of a float uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param v0       the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: UniformLocation, v0: Float) = GL20C.glUniform1f(location, v0)

    /**
     * Specifies the value of a float uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: UniformLocation, v: Vec1) = GL20C.glUniform1f(location, v.x)

    // --- [ glUniform2f ] ---

    /**
     * Specifies the value of a vec2 uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param v0       the uniform x value
     * @param v1       the uniform y value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: UniformLocation, v0: Float, v1: Float) = GL20C.glUniform2f(location, v0, v1)

    /**
     * Specifies the value of a vec2 uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: UniformLocation, v: Vec2) = GL20C.glUniform2f(location, v.x, v.y)

    // --- [ glUniform3f ] ---

    /**
     * Specifies the value of a vec3 uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param v0       the uniform x value
     * @param v1       the uniform y value
     * @param v2       the uniform z value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: UniformLocation, v0: Float, v1: Float, v2: Float) = GL20C.glUniform3f(location, v0, v1, v2)

    /**
     * Specifies the value of a vec3 uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: UniformLocation, v: Vec3) = GL20C.glUniform3f(location, v.x, v.y, v.z)

    // --- [ glUniform4f ] ---

    /**
     * Specifies the value of a vec4 uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param v0       the uniform x value
     * @param v1       the uniform y value
     * @param v2       the uniform z value
     * @param v3       the uniform w value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: UniformLocation, v0: Float, v1: Float, v2: Float, v3: Float) = GL20C.glUniform4f(location, v0, v1, v2, v3)

    /**
     * Specifies the value of a vec4 uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: UniformLocation, v: Vec4) = GL20C.glUniform4f(location, v.x, v.y, v.z, v.w)

    // --- [ glUniform1i ] ---

    /**
     * Specifies the value of an int uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param v0       the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: UniformLocation, v0: Int) = GL20C.glUniform1i(location, v0)

    /**
     * Specifies the value of an int uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: UniformLocation, v: Vec1i) = GL20C.glUniform1i(location, v.x)

    // --- [ glUniform2i ] ---

    /**
     * Specifies the value of an ivec2 uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param v0       the uniform x value
     * @param v1       the uniform y value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: UniformLocation, v0: Int, v1: Int) = GL20C.glUniform2i(location, v0, v1)

    /**
     * Specifies the value of an ivec2 uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: UniformLocation, v: Vec2i) = GL20C.glUniform2i(location, v.x, v.y)

    // --- [ glUniform3i ] ---

    /**
     * Specifies the value of an ivec3 uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param v0       the uniform x value
     * @param v1       the uniform y value
     * @param v2       the uniform z value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: UniformLocation, v0: Int, v1: Int, v2: Int) = GL20C.glUniform3i(location, v0, v1, v2)

    /**
     * Specifies the value of an ivec3 uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: UniformLocation, v: Vec3i) = GL20C.glUniform3i(location, v.x, v.y, v.z)

    // --- [ glUniform4i ] ---

    /**
     * Specifies the value of an ivec4 uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param v0       the uniform x value
     * @param v1       the uniform y value
     * @param v2       the uniform z value
     * @param v3       the uniform w value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: UniformLocation, v0: Int, v1: Int, v2: Int, v3: Int) = GL20C.glUniform4i(location, v0, v1, v2, v3)

    // --- [ glUniformMatrix2fv ] ---

    /**
     * Specifies the value of a single mat2 uniform variable or a mat2 uniform variable array for the current program object.
     *
     * @param location  the location of the uniform variable to be modified
     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
     * @param value     a pointer to an array of {@code count} values that will be used to update the specified uniform variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: UniformLocation, value: Mat2) = Stack { GL20C.nglUniformMatrix2fv(location, 1, false, value.toFloatBuffer(it).adr) }

    // --- [ glUniformMatrix3fv ] ---

    /**
     * Unsafe version of: {@link #glUniformMatrix3fv UniformMatrix3fv}
     *
     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
     */
    fun uniform(location: UniformLocation, value: Mat3) = Stack { GL20C.nglUniformMatrix3fv(location, 1, false, value.toFloatBuffer(it).adr) }

    // --- [ glUniformMatrix4fv ] ---

    /**
     * Unsafe version of: {@link #glUniformMatrix4fv UniformMatrix4fv}
     *
     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
     */
    fun uniform(location: UniformLocation, value: Mat4) = Stack { GL20C.nglUniformMatrix4fv(location, 1, false, value.toFloatBuffer(it).adr) }

    // --- [ glGetShaderiv ] ---

    // inline reified

    // --- [ glGetProgramiv ] ---

    // inline reified

    // --- [ glGetShaderInfoLog ] ---

    /**
     * Returns the information log for a shader object.
     *
     * @param shader    the shader object whose information log is to be queried
     * @param maxLength the size of the character buffer for storing the returned information log
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetShaderInfoLog">Reference Page</a>
     */
    infix fun getShaderInfoLog(shader: GlShader): String =
            Stack {
                val maxLength = shader.infoLogLength
                val infoLog = it.calloc(maxLength)
                GL20C.nglGetShaderInfoLog(shader.name, maxLength, NULL, infoLog.adr)
                MemoryTextDecoding.decodeUTF8(infoLog.adr, maxLength, 0)
            }

    // --- [ glGetProgramInfoLog ] ---

    /**
     * Returns the information log for a program object.
     *
     * @param program   the program object whose information log is to be queried
     * @param maxLength the size of the character buffer for storing the returned information log
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetProgramInfoLog">Reference Page</a>
     */
    infix fun getProgramInfoLog(program: GlProgram): String =
            Stack {
                val maxLength = program.infoLogLength
                val infoLog = it.calloc(maxLength)
                GL20C.nglGetProgramInfoLog(program.name, maxLength, NULL, infoLog.adr)
                MemoryTextDecoding.decodeUTF8(infoLog.adr, maxLength, 0)
            }

    // --- [ glGetAttachedShaders ] ---

    /**
     * Returns the shader objects attached to a program object.
     *
     * @param program the program object to be queried
     * @param count   the number of names actually returned in {@code shaders}
     * @param shaders an array that is used to return the names of attached shader objects
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetAttachedShaders">Reference Page</a>
     */
    infix fun getAttachedShaders(program: GlProgram): GLshaders =
            Stack {
                val maxCount = program.attachedShadersCount
                val shaders = IntBuffer(maxCount)
                GL20C.nglGetAttachedShaders(program.name, maxCount, NULL, shaders.adr)
                GLshaders(shaders)
            }

    // --- [ glGetUniformLocation ] ---

    /**
     * Returns the location of a uniform variable.
     *
     * @param program the program object to be queried
     * @param name    a null terminated string containing the name of the uniform variable whose location is to be queried
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetUniformLocation">Reference Page</a>
     */
    fun getUniformLocation(program: GlProgram, name: String): UniformLocation =
            Stack {
                val nameEncoded = it.ASCII(name)
                GL20C.nglGetUniformLocation(program.name, nameEncoded.adr)
            }

    // --- [ glGetActiveUniform ] ---

    /**
     * Returns information about an active uniform variable for the specified program object.
     *
     * @param program   the program object to be queried
     * @param index     the index of the uniform variable to be queried
     *
     * @return (name: String, size: Int, type: UniformType) of the uniform variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetActiveUniform">Reference Page</a>
     */
    fun getActiveUniform(program: GlProgram, index: Int): Triple<String, Int, UniformType> =
            Stack {
                val maxLength = program.activeUniformMaxLength
                val name = it.calloc(maxLength).adr
                val length = it.malloc(3 * Int.BYTES).adr
                val size = length + Int.BYTES
                val type = size + Int.BYTES
                GL20C.nglGetActiveUniform(program.name, index, maxLength, length, size, type, name)
                Triple(MemoryTextDecoding.decodeASCII(name, memGetInt(length), 0), memGetInt(size), UniformType(memGetInt(type)))
            }

    // --- [ glGetShaderSource ] ---

    /**
     * Returns the source code string from a shader object.
     *
     * @param shader    the shader object to be queried
     * @param maxLength the size of the character buffer for storing the returned source code string
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetShaderSource">Reference Page</a>
     */
    infix fun getShaderSource(shader: GlShader): String =
            Stack {
                val maxLength = shader.sourceLength
                val source = it.malloc(maxLength).adr
                GL20C.nglGetShaderSource(shader.name, maxLength, NULL, source)
                return MemoryTextDecoding.decodeUTF8(source, maxLength, 0)
            }

    // --- [ glVertexAttrib1f ] ---

    /**
     * Specifies the value of a generic vertex attribute. The y and z components are implicitly set to 0.0f and w to 1.0f.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v0    the vertex attribute x component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v0: Float) = GL20C.glVertexAttrib1f(index, v0)

    /**
     * Specifies the value of a generic vertex attribute. The y and z components are implicitly set to 0.0f and w to 1.0f.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v     the vertex attribute
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v: Vec1) = GL20C.glVertexAttrib1f(index, v.x)

    // --- [ glVertexAttrib1s ] ---

    /**
     * Short version of {@link #glVertexAttrib1f VertexAttrib1f}.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v0    the vertex attribute x component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v0: Short) = GL20C.glVertexAttrib1s(index, v0)

    /**
     * Short version of {@link #glVertexAttrib1f VertexAttrib1f}.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v     the vertex attribute
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v: Vec1s) = GL20C.glVertexAttrib1s(index, v.x)

    // --- [ glVertexAttrib1d ] ---

    /**
     * Double version of {@link #glVertexAttrib1f VertexAttrib1f}.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v0    the vertex attribute x component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v0: Double) = GL20C.glVertexAttrib1d(index, v0)

    /**
     * Double version of {@link #glVertexAttrib1f VertexAttrib1f}.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v     the vertex attribute
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v: Vec1d) = GL20C.glVertexAttrib1d(index, v.x)

    // --- [ glVertexAttrib2f ] ---

    /**
     * Specifies the value of a generic vertex attribute. The y component is implicitly set to 0.0f and w to 1.0f.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v0    the vertex attribute x component
     * @param v1    the vertex attribute y component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib2f(index: Int, v0: Float, v1: Float) = GL20C.glVertexAttrib2f(index, v0, v1)

    /**
     * Specifies the value of a generic vertex attribute. The y component is implicitly set to 0.0f and w to 1.0f.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v     the vertex attribute
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib2f(index: Int, v: Vec2) = GL20C.glVertexAttrib2f(index, v.x, v.y)

    // --- [ glVertexAttrib2s ] ---

    /**
     * Short version of {@link #glVertexAttrib2f VertexAttrib2f}.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v0    the vertex attribute x component
     * @param v1    the vertex attribute y component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v0: Short, v1: Short) = GL20C.glVertexAttrib2s(index, v0, v1)

    /**
     * Short version of {@link #glVertexAttrib2f VertexAttrib2f}.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v     the vertex attribute
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v: Vec2s) = GL20C.glVertexAttrib2s(index, v.x, v.y)

    // --- [ glVertexAttrib2d ] ---

    /**
     * Double version of {@link #glVertexAttrib2f VertexAttrib2f}.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v0    the vertex attribute x component
     * @param v1    the vertex attribute y component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v0: Double, v1: Double) = GL20C.glVertexAttrib2d(index, v0, v1)

    /**
     * Double version of {@link #glVertexAttrib2f VertexAttrib2f}.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v     the vertex attribute
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v: Vec2d) = GL20C.glVertexAttrib2d(index, v.x, v.y)

    // --- [ glVertexAttrib3f ] ---

    /**
     * Specifies the value of a generic vertex attribute. The w is implicitly set to 1.0f.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v0    the vertex attribute x component
     * @param v1    the vertex attribute y component
     * @param v2    the vertex attribute z component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v0: Float, v1: Float, v2: Float) = GL20C.glVertexAttrib3f(index, v0, v1, v2)

    /**
     * Specifies the value of a generic vertex attribute. The w is implicitly set to 1.0f.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v     the vertex attribute
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v: Vec3) = GL20C.glVertexAttrib3f(index, v.x, v.y, v.z)

    // --- [ glVertexAttrib3s ] ---

    /**
     * Short version of {@link #glVertexAttrib3f VertexAttrib3f}.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v0    the vertex attribute x component
     * @param v1    the vertex attribute y component
     * @param v2    the vertex attribute z component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v0: Short, v1: Short, v2: Short) = GL20C.glVertexAttrib3s(index, v0, v1, v2)

    /**
     * Short version of {@link #glVertexAttrib3f VertexAttrib3f}.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v     the vertex attribute
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v: Vec3s) = GL20C.glVertexAttrib3s(index, v.x, v.y, v.z)

    // --- [ glVertexAttrib3d ] ---

    /**
     * Double version of {@link #glVertexAttrib3f VertexAttrib3f}.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v0    the vertex attribute x component
     * @param v1    the vertex attribute y component
     * @param v2    the vertex attribute z component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v0: Double, v1: Double, v2: Double) = GL20C.glVertexAttrib3d(index, v0, v1, v2)

    /**
     * Double version of {@link #glVertexAttrib3f VertexAttrib3f}.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v     the vertex attribute
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v: Vec3d) = GL20C.glVertexAttrib3d(index, v.x, v.y, v.z)

    // --- [ glVertexAttrib4f ] ---

    /**
     * Specifies the value of a generic vertex attribute.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v0    the vertex attribute x component
     * @param v1    the vertex attribute y component
     * @param v2    the vertex attribute z component
     * @param v3    the vertex attribute w component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v0: Float, v1: Float, v2: Float, v3: Float) = GL20C.glVertexAttrib4f(index, v0, v1, v2, v3)

    /**
     * Specifies the value of a generic vertex attribute.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v     the vertex attribute
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v: Vec4) = GL20C.glVertexAttrib4f(index, v.x, v.y, v.z, v.w)

    // --- [ glVertexAttrib4s ] ---

    /**
     * Short version of {@link #glVertexAttrib4f VertexAttrib4f}.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v0    the vertex attribute x component
     * @param v1    the vertex attribute y component
     * @param v2    the vertex attribute z component
     * @param v3    the vertex attribute w component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v0: Short, v1: Short, v2: Short, v3: Short) = GL20C.glVertexAttrib4s(index, v0, v1, v2, v3)

    /**
     * Short version of {@link #glVertexAttrib4f VertexAttrib4f}.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v     the vertex attribute
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v: Vec4s) = GL20C.glVertexAttrib4s(index, v.x, v.y, v.z, v.w)

    // --- [ glVertexAttrib4d ] ---

    /**
     * Double version of {@link #glVertexAttrib4f VertexAttrib4f}.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v0    the vertex attribute x component
     * @param v1    the vertex attribute y component
     * @param v2    the vertex attribute z component
     * @param v3    the vertex attribute w component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v0: Double, v1: Double, v2: Double, v3: Double) = GL20C.glVertexAttrib4d(index, v0, v1, v2, v3)

    /**
     * Double version of {@link #glVertexAttrib4f VertexAttrib4f}.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param v     the vertex attribute
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v: Vec4d) = GL20C.glVertexAttrib4d(index, v.x, v.y, v.z, v.w)

    // --- [ glVertexAttrib4Nub ] ---

    /**
     * Normalized unsigned byte version of {@link #glVertexAttrib4f VertexAttrib4f}.
     *
     * @param index the index of the generic vertex attribute to be modified
     * @param x     the vertex attribute x component
     * @param y     the vertex attribute y component
     * @param z     the vertex attribute z component
     * @param w     the vertex attribute w component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib4N(index: Int, x: Ubyte, y: Ubyte, z: Ubyte, w: Ubyte) = GL20C.glVertexAttrib4Nub(index, x.v, y.v, z.v, w.v)
//
//    // --- [ glVertexAttrib1fv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttrib1fv VertexAttrib1fv} */
//    public static native void nglVertexAttrib1fv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttrib1f VertexAttrib1f}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttrib1fv(@NativeType("GLuint") int index, @NativeType("GLfloat const *") FloatBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 1);
//        }
//        nglVertexAttrib1fv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttrib1sv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttrib1sv VertexAttrib1sv} */
//    public static native void nglVertexAttrib1sv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttrib1s VertexAttrib1s}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttrib1sv(@NativeType("GLuint") int index, @NativeType("GLshort const *") ShortBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 1);
//        }
//        nglVertexAttrib1sv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttrib1dv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttrib1dv VertexAttrib1dv} */
//    public static native void nglVertexAttrib1dv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttrib1d VertexAttrib1d}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttrib1dv(@NativeType("GLuint") int index, @NativeType("GLdouble const *") DoubleBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 1);
//        }
//        nglVertexAttrib1dv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttrib2fv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttrib2fv VertexAttrib2fv} */
//    public static native void nglVertexAttrib2fv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttrib2f VertexAttrib2f}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttrib2fv(@NativeType("GLuint") int index, @NativeType("GLfloat const *") FloatBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 2);
//        }
//        nglVertexAttrib2fv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttrib2sv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttrib2sv VertexAttrib2sv} */
//    public static native void nglVertexAttrib2sv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttrib2s VertexAttrib2s}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttrib2sv(@NativeType("GLuint") int index, @NativeType("GLshort const *") ShortBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 2);
//        }
//        nglVertexAttrib2sv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttrib2dv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttrib2dv VertexAttrib2dv} */
//    public static native void nglVertexAttrib2dv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttrib2d VertexAttrib2d}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttrib2dv(@NativeType("GLuint") int index, @NativeType("GLdouble const *") DoubleBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 2);
//        }
//        nglVertexAttrib2dv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttrib3fv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttrib3fv VertexAttrib3fv} */
//    public static native void nglVertexAttrib3fv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttrib3f VertexAttrib3f}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttrib3fv(@NativeType("GLuint") int index, @NativeType("GLfloat const *") FloatBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 3);
//        }
//        nglVertexAttrib3fv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttrib3sv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttrib3sv VertexAttrib3sv} */
//    public static native void nglVertexAttrib3sv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttrib3s VertexAttrib3s}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttrib3sv(@NativeType("GLuint") int index, @NativeType("GLshort const *") ShortBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 3);
//        }
//        nglVertexAttrib3sv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttrib3dv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttrib3dv VertexAttrib3dv} */
//    public static native void nglVertexAttrib3dv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttrib3d VertexAttrib3d}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttrib3dv(@NativeType("GLuint") int index, @NativeType("GLdouble const *") DoubleBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 3);
//        }
//        nglVertexAttrib3dv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttrib4fv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttrib4fv VertexAttrib4fv} */
//    public static native void nglVertexAttrib4fv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttrib4f VertexAttrib4f}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttrib4fv(@NativeType("GLuint") int index, @NativeType("GLfloat const *") FloatBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 4);
//        }
//        nglVertexAttrib4fv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttrib4sv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttrib4sv VertexAttrib4sv} */
//    public static native void nglVertexAttrib4sv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttrib4s VertexAttrib4s}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttrib4sv(@NativeType("GLuint") int index, @NativeType("GLshort const *") ShortBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 4);
//        }
//        nglVertexAttrib4sv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttrib4dv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttrib4dv VertexAttrib4dv} */
//    public static native void nglVertexAttrib4dv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttrib4d VertexAttrib4d}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttrib4dv(@NativeType("GLuint") int index, @NativeType("GLdouble const *") DoubleBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 4);
//        }
//        nglVertexAttrib4dv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttrib4iv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttrib4iv VertexAttrib4iv} */
//    public static native void nglVertexAttrib4iv(int index, long v);
//
//    /**
//     * Integer pointer version of {@link #glVertexAttrib4f VertexAttrib4f}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttrib4iv(@NativeType("GLuint") int index, @NativeType("GLint const *") IntBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 4);
//        }
//        nglVertexAttrib4iv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttrib4bv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttrib4bv VertexAttrib4bv} */
//    public static native void nglVertexAttrib4bv(int index, long v);
//
//    /**
//     * Byte pointer version of {@link #glVertexAttrib4f VertexAttrib4f}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttrib4bv(@NativeType("GLuint") int index, @NativeType("GLbyte const *") ByteBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 4);
//        }
//        nglVertexAttrib4bv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttrib4ubv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttrib4ubv VertexAttrib4ubv} */
//    public static native void nglVertexAttrib4ubv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttrib4Nub VertexAttrib4Nub}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttrib4ubv(@NativeType("GLuint") int index, @NativeType("GLubyte const *") ByteBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 4);
//        }
//        nglVertexAttrib4ubv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttrib4usv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttrib4usv VertexAttrib4usv} */
//    public static native void nglVertexAttrib4usv(int index, long v);
//
//    /**
//     * Unsigned short pointer version of {@link #glVertexAttrib4f VertexAttrib4f}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttrib4usv(@NativeType("GLuint") int index, @NativeType("GLushort const *") ShortBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 4);
//        }
//        nglVertexAttrib4usv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttrib4uiv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttrib4uiv VertexAttrib4uiv} */
//    public static native void nglVertexAttrib4uiv(int index, long v);
//
//    /**
//     * Unsigned int pointer version of {@link #glVertexAttrib4f VertexAttrib4f}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttrib4uiv(@NativeType("GLuint") int index, @NativeType("GLuint const *") IntBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 4);
//        }
//        nglVertexAttrib4uiv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttrib4Nbv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttrib4Nbv VertexAttrib4Nbv} */
//    public static native void nglVertexAttrib4Nbv(int index, long v);
//
//    /**
//     * Normalized byte pointer version of {@link #glVertexAttrib4f VertexAttrib4f}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttrib4Nbv(@NativeType("GLuint") int index, @NativeType("GLbyte const *") ByteBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 4);
//        }
//        nglVertexAttrib4Nbv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttrib4Nsv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttrib4Nsv VertexAttrib4Nsv} */
//    public static native void nglVertexAttrib4Nsv(int index, long v);
//
//    /**
//     * Normalized short pointer version of {@link #glVertexAttrib4f VertexAttrib4f}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttrib4Nsv(@NativeType("GLuint") int index, @NativeType("GLshort const *") ShortBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 4);
//        }
//        nglVertexAttrib4Nsv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttrib4Niv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttrib4Niv VertexAttrib4Niv} */
//    public static native void nglVertexAttrib4Niv(int index, long v);
//
//    /**
//     * Normalized int pointer version of {@link #glVertexAttrib4f VertexAttrib4f}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttrib4Niv(@NativeType("GLuint") int index, @NativeType("GLint const *") IntBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 4);
//        }
//        nglVertexAttrib4Niv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttrib4Nubv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttrib4Nubv VertexAttrib4Nubv} */
//    public static native void nglVertexAttrib4Nubv(int index, long v);
//
//    /**
//     * Normalized unsigned byte pointer version of {@link #glVertexAttrib4f VertexAttrib4f}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttrib4Nubv(@NativeType("GLuint") int index, @NativeType("GLubyte const *") ByteBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 4);
//        }
//        nglVertexAttrib4Nubv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttrib4Nusv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttrib4Nusv VertexAttrib4Nusv} */
//    public static native void nglVertexAttrib4Nusv(int index, long v);
//
//    /**
//     * Normalized unsigned short pointer version of {@link #glVertexAttrib4f VertexAttrib4f}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttrib4Nusv(@NativeType("GLuint") int index, @NativeType("GLushort const *") ShortBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 4);
//        }
//        nglVertexAttrib4Nusv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttrib4Nuiv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttrib4Nuiv VertexAttrib4Nuiv} */
//    public static native void nglVertexAttrib4Nuiv(int index, long v);
//
//    /**
//     * Normalized unsigned int pointer version of {@link #glVertexAttrib4f VertexAttrib4f}.
//     *
//     * @param index the index of the generic vertex attribute to be modified
//     * @param v     the vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttrib4Nuiv(@NativeType("GLuint") int index, @NativeType("GLuint const *") IntBuffer v)
//    {
//        if (CHECKS) {
//            check(v, 4);
//        }
//        nglVertexAttrib4Nuiv(index, memAddress(v));
//    }
//
    // --- [ glVertexAttribPointer ] ---

    /**
     * Specifies the location and organization of a vertex attribute array.
     *
     * @param index      the index of the generic vertex attribute to be modified
     * @param size       the number of values per vertex that are stored in the array. The initial value is 4. One of:<br><table><tr><td>1</td><td>2</td><td>3</td><td>4</td><td>{@link GL12#GL_BGRA BGRA}</td></tr></table>
     * @param type       the data type of each component in the array. The initial value is GL_FLOAT. One of:<br><table><tr><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_SHORT SHORT}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL11#GL_DOUBLE DOUBLE}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td><td>{@link GL33#GL_INT_2_10_10_10_REV INT_2_10_10_10_REV}</td><td>{@link GL41#GL_FIXED FIXED}</td></tr></table>
     * @param normalized whether fixed-point data values should be normalized or converted directly as fixed-point values when they are accessed
     * @param stride     the byte offset between consecutive generic vertex attributes. If stride is 0, the generic vertex attributes are understood to be tightly packed in
     *                   the array. The initial value is 0.
     * @param pointer    the vertex attribute data or the offset of the first component of the first generic vertex attribute in the array in the data store of the buffer
     *                   currently bound to the {@link GL15#GL_ARRAY_BUFFER ARRAY_BUFFER} target. The initial value is 0.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttribPointer">Reference Page</a>
     */
    fun vertexAttribPointer(index: Int, size: Int, type: VertexAttrType, normalized: Boolean, stride: Int, pointer: Int) {
        GL20C.nglVertexAttribPointer(index, size, type.i, normalized, stride, pointer.L)
    }

    /**
     * Specifies the location and organization of a vertex attribute array.
     *
     * @param attribute     the vertex attribute
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttribPointer">Reference Page</a>
     */
    fun vertexAttribPointer(attribute: VertexAttribute) = with(attribute) {
        GL20C.nglVertexAttribPointer(index, size, type.i, normalized, interleavedStride, pointer)
    }

    /**
     * Specifies the location and organization of a vertex attribute array.
     *
     * @param vertexLayout     the vertex layout containing the attribute(s) to apply
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttribPointer">Reference Page</a>
     */
    fun vertexAttribPointer(vertexLayout: VertexLayout) =
            vertexLayout.attributes.forEach {
                GL20C.nglVertexAttribPointer(it.index, it.size, it.type.i, it.normalized, it.interleavedStride, it.pointer)
            }

    // --- [ glEnableVertexAttribArray ] ---

    /**
     * Enables a generic vertex attribute array.
     *
     * @param index the index of the generic vertex attribute to be enabled
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glEnableVertexAttribArray">Reference Page</a>
     */
    fun enableVertexAttribArray(index: Int) = GL20C.glEnableVertexAttribArray(index)

    /**
     * Enables a generic vertex attribute array.
     *
     * @param attribute the attribute of the generic vertex attribute to be enabled
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glEnableVertexAttribArray">Reference Page</a>
     */
    fun enableVertexAttribArray(attribute: VertexAttribute) = GL20C.glEnableVertexAttribArray(attribute.index)

    /**
     * Enables a generic vertex attribute array.
     *
     * @param vertexLayout the vertex layout containing the attribute(s) to enable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glEnableVertexAttribArray">Reference Page</a>
     */
    fun enableVertexAttribArray(vertexLayout: VertexLayout) =
            vertexLayout.attributes.forEach { GL20C.glEnableVertexAttribArray(it.index) }

    // --- [ glDisableVertexAttribArray ] ---

    /**
     * Disables a generic vertex attribute array.
     *
     * @param index the index of the generic vertex attribute to be disabled
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDisableVertexAttribArray">Reference Page</a>
     */
    fun disableVertexAttribArray(index: Int) = GL20C.glDisableVertexAttribArray(index)

    /**
     * Disables a generic vertex attribute array.
     *
     * @param attribute    generic vertex attribute, where its index has to be disabled
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDisableVertexAttribArray">Reference Page</a>
     */
    fun disableVertexAttribArray(attribute: VertexAttribute) = GL20C.glDisableVertexAttribArray(attribute.index)

    // --- [ glBindAttribLocation ] ---

    /**
     * Associates a generic vertex attribute index with a named attribute variable.
     *
     * @param program the program object in which the association is to be made
     * @param index   the index of the generic vertex attribute to be bound
     * @param name    a null terminated string containing the name of the vertex shader attribute variable to which {@code index} is to be bound
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBindAttribLocation">Reference Page</a>
     */
    fun bindAttribLocation(program: GlProgram, index: Int, name: CharSequence) =
            Stack.asciiAddress(name) { GL20C.nglBindAttribLocation(program.name, index, it) }

    // --- [ glGetActiveAttrib ] ---

    /**
     * Returns information about an active attribute variable for the specified program object.
     *
     * @param program   the program object to be queried
     * @param index     the index of the attribute variable to be queried
     *
     * @return <name, size, type> of the attribute variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetActiveAttrib">Reference Page</a>
     */
    fun getActiveAttrib(program: GlProgram, index: Int): Triple<String, Int, AttributeType> =
            Stack {
                val maxLength = program.activeAttributeMaxLength
                val length = it.malloc(3 * Int.BYTES).adr
                val size = length + Int.BYTES
                val type = size + Int.BYTES
                val name = it.calloc(maxLength).adr
                GL20C.nglGetActiveAttrib(program.name, index, maxLength, length, size, type, name)
                Triple(MemoryTextDecoding.decodeASCII(name, memGetInt(length), 0), memGetInt(size), AttributeType(memGetInt(type)))
            }

    // --- [ glGetAttribLocation ] ---

    /**
     * Returns the location of an attribute variable.
     *
     * @param program the program object to be queried
     * @param name    a null terminated string containing the name of the attribute variable whose location is to be queried
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetAttribLocation">Reference Page</a>
     */
    fun getAttribLocation(program: GlProgram, name: String): Int =
            Stack {
                val nameEncoded = it.ASCII(name)
                GL20C.nglGetAttribLocation(program.name, nameEncoded.adr)
            }

    // --- [ glGetVertexAttribiv ] ---

    /**
     * Returns the integer value of a generic vertex attribute parameter.
     *
     * @param index the generic vertex attribute parameter to be queried
     * @param pName the symbolic name of the vertex attribute parameter to be queried. One of:<br><table><tr><td>{@link GL15#GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING VERTEX_ATTRIB_ARRAY_BUFFER_BINDING}</td><td>{@link #GL_VERTEX_ATTRIB_ARRAY_ENABLED VERTEX_ATTRIB_ARRAY_ENABLED}</td></tr><tr><td>{@link #GL_VERTEX_ATTRIB_ARRAY_SIZE VERTEX_ATTRIB_ARRAY_SIZE}</td><td>{@link #GL_VERTEX_ATTRIB_ARRAY_STRIDE VERTEX_ATTRIB_ARRAY_STRIDE}</td></tr><tr><td>{@link #GL_VERTEX_ATTRIB_ARRAY_TYPE VERTEX_ATTRIB_ARRAY_TYPE}</td><td>{@link #GL_VERTEX_ATTRIB_ARRAY_NORMALIZED VERTEX_ATTRIB_ARRAY_NORMALIZED}</td></tr><tr><td>{@link #GL_CURRENT_VERTEX_ATTRIB CURRENT_VERTEX_ATTRIB}</td><td>{@link GL30#GL_VERTEX_ATTRIB_ARRAY_INTEGER VERTEX_ATTRIB_ARRAY_INTEGER}</td></tr><tr><td>{@link GL33#GL_VERTEX_ATTRIB_ARRAY_DIVISOR VERTEX_ATTRIB_ARRAY_DIVISOR}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetVertexAttrib">Reference Page</a>
     */
    fun getVertexAttribI(index: Int, pName: VertexAttrib): Int = // TODO this is smelly, check I
            Stack.intAddress { GL20C.nglGetVertexAttribiv(index, pName.i, it) }

    /**
     * Returns a full Vertex Attribute class TODO move to gl?
     *
     * @param index the generic vertex attribute parameter to be queried
     */
    fun getVertexAttrib(index: Int): VertexAttribute {
        return VertexAttribute(
                index,
                size = getVertexAttribI(index, VertexAttrib(GL20.GL_VERTEX_ATTRIB_ARRAY_SIZE)),
                type = VertexAttrType(getVertexAttribI(index, VertexAttrib(GL20.GL_VERTEX_ATTRIB_ARRAY_TYPE))),
                normalized = getVertexAttribI(index, VertexAttrib(GL20.GL_VERTEX_ATTRIB_ARRAY_NORMALIZED)).bool,
                interleavedStride = getVertexAttribI(index, VertexAttrib(GL20.GL_VERTEX_ATTRIB_ARRAY_STRIDE)),
                pointer = getVertexAttribPointer(index).L).apply {

            bufferBinding = getVertexAttribI(index, VertexAttrib(GL15.GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING))
            enabled = getVertexAttribI(index, VertexAttrib(GL20.GL_VERTEX_ATTRIB_ARRAY_ENABLED)).bool
            integer = getVertexAttribI(index, VertexAttrib(GL30.GL_VERTEX_ATTRIB_ARRAY_INTEGER)).bool
            divisor = getVertexAttribI(index, VertexAttrib(GL33.GL_VERTEX_ATTRIB_ARRAY_DIVISOR))
            current = Stack.vec4Buffer { getCurrentVertexAttrib(index, it) }
        }
    }

    // --- [ glGetVertexAttribfv ] ---

    /**
     * Float version of {@link #glGetVertexAttribiv GetVertexAttribiv}.
     *
     * @param index  the generic vertex attribute parameter to be queried
     * @param params returns the requested data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetVertexAttrib">Reference Page</a>
     */
    fun getCurrentVertexAttrib(index: Int, params: FloatBuffer) = GL20C.nglGetVertexAttribfv(index, GL20C.GL_CURRENT_VERTEX_ATTRIB, params.adr)

    // --- [ glGetVertexAttribdv ] ---

    /**
     * Double version of {@link #glGetVertexAttribiv GetVertexAttribiv}.
     *
     * @param index  the generic vertex attribute parameter to be queried
     * @param pname  the symbolic name of the vertex attribute parameter to be queried
     * @param params returns the requested data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetVertexAttrib">Reference Page</a>
     */
    fun getCurrentVertexAttrib(index: Int, params: DoubleBuffer) = GL20C.nglGetVertexAttribdv(index, GL20C.GL_CURRENT_VERTEX_ATTRIB, params.adr)

    /**
     * Returns the address of the specified generic vertex attribute pointer.
     *
     * @param index the generic vertex attribute parameter to be queried
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetVertexAttribPointerv">Reference Page</a>
     */
    infix fun getVertexAttribPointer(index: Int): Ptr = Stack.pointerAddress { GL20C.nglGetVertexAttribPointerv(index, GL20C.GL_VERTEX_ATTRIB_ARRAY_POINTER, it) }

    // --- [ glDrawBuffers ] ---

    /**
     * Specifies a list of color buffers to be drawn into.
     *
     * @param bufs an array of symbolic constants specifying the buffers into which fragment colors or data values will be written. One of:<br><table><tr><td>{@link GL11#GL_NONE NONE}</td><td>{@link GL11#GL_FRONT_LEFT FRONT_LEFT}</td><td>{@link GL11#GL_FRONT_RIGHT FRONT_RIGHT}</td><td>{@link GL11#GL_BACK_LEFT BACK_LEFT}</td><td>{@link GL11#GL_BACK_RIGHT BACK_RIGHT}</td><td>{@link GL30#GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td></tr><tr><td>GL30.GL_COLOR_ATTACHMENT[1-15]</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawBuffers">Reference Page</a>
     */
    fun drawBuffers(bufs: IntBuffer) = GL20C.nglDrawBuffers(bufs.rem, bufs.adr)

    /**
     * Specifies a list of color buffers to be drawn into.
     *
     * @param bufs an array of symbolic constants specifying the buffers into which fragment colors or data values will be written. One of:<br><table><tr><td>{@link GL11#GL_NONE NONE}</td><td>{@link GL11#GL_FRONT_LEFT FRONT_LEFT}</td><td>{@link GL11#GL_FRONT_RIGHT FRONT_RIGHT}</td><td>{@link GL11#GL_BACK_LEFT BACK_LEFT}</td><td>{@link GL11#GL_BACK_RIGHT BACK_RIGHT}</td><td>{@link GL30#GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td></tr><tr><td>GL30.GL_COLOR_ATTACHMENT[1-15]</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawBuffers">Reference Page</a>
     */
    fun drawBuffers(vararg bufs: Int) = Stack {
        val pBufs = bufs.toBuffer(it)
        GL20C.nglDrawBuffers(bufs.size, pBufs.adr)
    }

    // --- [ glBlendEquationSeparate ] ---

    /**
     * Sets the RGB blend equation and the alpha blend equation separately.
     *
     * @param modeRGB   the RGB blend equation, how the red, green, and blue components of the source and destination colors are combined. One of:<br><table><tr><td>{@link GL14#GL_FUNC_ADD FUNC_ADD}</td><td>{@link GL14#GL_FUNC_SUBTRACT FUNC_SUBTRACT}</td><td>{@link GL14#GL_FUNC_REVERSE_SUBTRACT FUNC_REVERSE_SUBTRACT}</td><td>{@link GL14#GL_MIN MIN}</td><td>{@link GL14#GL_MAX MAX}</td></tr></table>
     * @param modeAlpha the alpha blend equation, how the alpha component of the source and destination colors are combined
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBlendEquationSeparate">Reference Page</a>
     */
    fun blendEquationSeparate(modeRGB: BlendEquationMode, modeAlpha: BlendEquationMode) = GL20C.glBlendEquationSeparate(modeRGB.i, modeAlpha.i)

    // --- [ glStencilOpSeparate ] ---

    /**
     * Sets front and/or back stencil test actions.
     *
     * @param face   whether front and/or back stencil state is updated. One of:<br><table><tr><td>{@link GL11#GL_FRONT FRONT}</td><td>{@link GL11#GL_BACK BACK}</td><td>{@link GL11#GL_FRONT_AND_BACK FRONT_AND_BACK}</td></tr></table>
     * @param sFail  the action to take when the stencil test fails. The initial value is GL_KEEP. One of:<br><table><tr><td>{@link GL11#GL_KEEP KEEP}</td><td>{@link GL11#GL_ZERO ZERO}</td><td>{@link GL11#GL_REPLACE REPLACE}</td><td>{@link GL11#GL_INCR INCR}</td><td>{@link GL14#GL_INCR_WRAP INCR_WRAP}</td><td>{@link GL11#GL_DECR DECR}</td><td>{@link GL14#GL_DECR_WRAP DECR_WRAP}</td><td>{@link GL11#GL_INVERT INVERT}</td></tr></table>
     * @param dpFail the stencil action when the stencil test passes, but the depth test fails. The initial value is GL_KEEP
     * @param dpPass the stencil action when both the stencil test and the depth test pass, or when the stencil test passes and either there is no depth buffer or depth
     *               testing is not enabled. The initial value is GL_KEEP
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glStencilOpSeparate">Reference Page</a>
     */
    fun stencilOpSeparate(face: FaceMode, sFail: StencilOp, dpFail: StencilOp, dpPass: StencilOp) = GL20C.glStencilOpSeparate(face.i, sFail.i, dpFail.i, dpPass.i)

    // --- [ glStencilFuncSeparate ] ---

    /**
     * Sets front and/or back function and reference value for stencil testing.
     *
     * @param face whether front and/or back stencil state is updated. One of:<br><table><tr><td>{@link GL11#GL_FRONT FRONT}</td><td>{@link GL11#GL_BACK BACK}</td><td>{@link GL11#GL_FRONT_AND_BACK FRONT_AND_BACK}</td></tr></table>
     * @param func the test function. The initial value is GL_ALWAYS. One of:<br><table><tr><td>{@link GL11#GL_NEVER NEVER}</td><td>{@link GL11#GL_LESS LESS}</td><td>{@link GL11#GL_LEQUAL LEQUAL}</td><td>{@link GL11#GL_GREATER GREATER}</td><td>{@link GL11#GL_GEQUAL GEQUAL}</td><td>{@link GL11#GL_EQUAL EQUAL}</td><td>{@link GL11#GL_NOTEQUAL NOTEQUAL}</td><td>{@link GL11#GL_ALWAYS ALWAYS}</td></tr></table>
     * @param ref  the reference value for the stencil test. {@code ref} is clamped to the range [0, 2n &ndash; 1], where {@code n} is the number of bitplanes in the stencil
     *             buffer. The initial value is 0.
     * @param mask a mask that is ANDed with both the reference value and the stored stencil value when the test is done. The initial value is all 1's.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glStencilFuncSeparate">Reference Page</a>
     */
    fun stencilFuncSeparate(face: FaceMode, func: CompareFunction, ref: Int, mask: Int) = GL20C.glStencilFuncSeparate(face.i, func.i, ref, mask)

    // --- [ glStencilMaskSeparate ] ---

    /**
     * Controls the front and/or back writing of individual bits in the stencil planes.
     *
     * @param face whether front and/or back stencil writemask is updated. One of:<br><table><tr><td>{@link GL11#GL_FRONT FRONT}</td><td>{@link GL11#GL_BACK BACK}</td><td>{@link GL11#GL_FRONT_AND_BACK FRONT_AND_BACK}</td></tr></table>
     * @param mask a bit mask to enable and disable writing of individual bits in the stencil planes. Initially, the mask is all 1's.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glStencilMaskSeparate">Reference Page</a>
     */
    fun stencilMaskSeparate(face: FaceMode, mask: Int) = GL20C.glStencilMaskSeparate(face.i, mask)
}