package gln

import glm_.mat2x2.Mat2d
import glm_.mat3x3.Mat3d
import glm_.mat4x4.Mat4d
import gln.identifiers.GlProgram
import gln.identifiers.GlQuery
import gln.transformFeedback.GlTransformFeedback
import gln.transformFeedback.GlTransformFeedbacks
import kool.*
import org.lwjgl.opengl.GL40C
import org.lwjgl.system.MemoryUtil
import org.lwjgl.system.MemoryUtil.memGetInt
import java.nio.ByteBuffer
import java.nio.FloatBuffer
import java.nio.IntBuffer
import kotlin.reflect.KMutableProperty0

/**
 * The OpenGL functionality up to version 4.0. Includes only Core Profile symbols.
 *
 * <p>OpenGL 4.0 implementations support revision 4.00 of the OpenGL Shading Language.</p>
 *
 * <p>Extensions promoted to core in this release:</p>
 *
 * <ul>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_texture_query_lod.txt">ARB_texture_query_lod</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_draw_buffers_blend.txt">ARB_draw_buffers_blend</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_draw_indirect.txt">ARB_draw_indirect</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_gpu_shader5.txt">ARB_gpu_shader5</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_gpu_shader_fp64.txt">ARB_gpu_shader_fp64</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_sample_shading.txt">ARB_sample_shading</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_shader_subroutine.txt">ARB_shader_subroutine</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_tessellation_shader.txt">ARB_tessellation_shader</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_texture_buffer_object_rgb32.txt">ARB_texture_buffer_object_rgb32</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_texture_cube_map_array.txt">ARB_texture_cube_map_array</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_texture_gather.txt">ARB_texture_gather</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_transform_feedback2.txt">ARB_transform_feedback2</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_transform_feedback3.txt">ARB_transform_feedback3</a></li>
 * </ul>
 */
interface gl40i {

    // --- [ glBlendEquationi ] ---

    /**
     * Specifies the equation used for both the RGB blend equation and the Alpha blend equation for the specified draw buffer.
     *
     * @param buf  the index of the draw buffer for which to set the blend equation
     * @param mode how source and destination colors are combined. One of:<br><table><tr><td>{@link GL14#GL_FUNC_ADD FUNC_ADD}</td><td>{@link GL14#GL_FUNC_SUBTRACT FUNC_SUBTRACT}</td><td>{@link GL14#GL_FUNC_REVERSE_SUBTRACT FUNC_REVERSE_SUBTRACT}</td><td>{@link GL14#GL_MIN MIN}</td><td>{@link GL14#GL_MAX MAX}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBlendEquationi">Reference Page</a>
     */
    fun blendEquation(buf: Int, mode: BlendEquationMode) = GL40C.glBlendEquationi(buf, mode.i)

    // --- [ glBlendEquationSeparatei ] ---

    /**
     * Sets the RGB blend equation and the alpha blend equation separately for the specified draw buffer.
     *
     * @param buf       the index of the draw buffer for which to set the blend equations
     * @param modeRGB   the RGB blend equation, how the red, green, and blue components of the source and destination colors are combined. One of:<br><table><tr><td>{@link GL14#GL_FUNC_ADD FUNC_ADD}</td><td>{@link GL14#GL_FUNC_SUBTRACT FUNC_SUBTRACT}</td><td>{@link GL14#GL_FUNC_REVERSE_SUBTRACT FUNC_REVERSE_SUBTRACT}</td><td>{@link GL14#GL_MIN MIN}</td><td>{@link GL14#GL_MAX MAX}</td></tr></table>
     * @param modeAlpha the alpha blend equation, how the alpha component of the source and destination colors are combined. One of:<br><table><tr><td>{@link GL14#GL_FUNC_ADD FUNC_ADD}</td><td>{@link GL14#GL_FUNC_SUBTRACT FUNC_SUBTRACT}</td><td>{@link GL14#GL_FUNC_REVERSE_SUBTRACT FUNC_REVERSE_SUBTRACT}</td><td>{@link GL14#GL_MIN MIN}</td><td>{@link GL14#GL_MAX MAX}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBlendEquationSeparatei">Reference Page</a>
     */
    fun blendEquationSeparate(buf: Int, modeRGB: BlendEquationMode, modeAlpha: BlendEquationMode) =
        GL40C.glBlendEquationSeparatei(buf, modeRGB.i, modeAlpha.i)

    // --- [ glBlendFunci ] ---

    /**
     * Specifies pixel arithmetic for the specified draw buffer.
     *
     * @param buf     the index of the draw buffer for which to set the blend function
     * @param sFactor how the red, green, blue, and alpha source blending factors are computed
     * @param dFactor how the red, green, blue, and alpha destination blending factors are computed
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBlendFunci">Reference Page</a>
     */
    fun blendFunc(buf: Int, sFactor: BlendFactor, dFactor: BlendFactor) = GL40C.glBlendFunci(buf, sFactor.i, dFactor.i)

    // --- [ glBlendFuncSeparatei ] ---

    /** TODO simply blendFunc?
     * Specifies pixel arithmetic for RGB and alpha components separately for the specified draw buffer.
     *
     * @param buf      the index of the draw buffer for which to set the blend functions
     * @param srcRGB   how the red, green, and blue blending factors are computed
     * @param dstRGB   how the red, green, and blue destination blending factors are computed
     * @param srcAlpha how the alpha source blending factor is computed
     * @param dstAlpha how the alpha destination blending factor is computed
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBlendFuncSeparatei">Reference Page</a>
     */
    fun blendFuncSeparate(buf: Int, srcRGB: BlendFactor, dstRGB: BlendFactor, srcAlpha: BlendFactor, dstAlpha: BlendFactor) =
        GL40C.glBlendFuncSeparatei(buf, srcRGB.i, dstRGB.i, srcAlpha.i, dstAlpha.i)

    // --- [ glDrawArraysIndirect ] ---

    /**
     * Renders primitives from array data, taking parameters from memory.
     *
     * <p>{@code glDrawArraysIndirect} behaves similarly to {@link GL42C#glDrawArraysInstancedBaseInstance DrawArraysInstancedBaseInstance}, except that the parameters to
     * glDrawArraysInstancedBaseInstance are stored in memory at the address given by {@code indirect}.</p>
     *
     * <p>The parameters addressed by {@code indirect} are packed into a structure that takes the form (in C):</p>
     *
     * <pre><code>
     * typedef struct {
     *     uint count;
     *     uint primCount;
     *     uint first;
     *     uint baseInstance; // must be 0 unless OpenGL 4.2 is supported
     * } DrawArraysIndirectCommand;
     *
     * const DrawArraysIndirectCommand *cmd = (const DrawArraysIndirectCommand *)indirect;
     * glDrawArraysInstancedBaseInstance(mode, cmd-&gt;first, cmd-&gt;count, cmd-&gt;primCount, cmd-&gt;baseInstance);</code></pre>
     *
     * @param mode     what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link #GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
     * @param indirect a structure containing the draw parameters
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawArraysIndirect">Reference Page</a>
     */
    fun drawArraysIndirect(mode: DrawMode, indirect: ByteBuffer) = GL40C.nglDrawArraysIndirect(mode.i, indirect.adr.L)

    /**
     * Renders primitives from array data, taking parameters from memory.
     *
     * <p>{@code glDrawArraysIndirect} behaves similarly to {@link GL42C#glDrawArraysInstancedBaseInstance DrawArraysInstancedBaseInstance}, except that the parameters to
     * glDrawArraysInstancedBaseInstance are stored in memory at the address given by {@code indirect}.</p>
     *
     * <p>The parameters addressed by {@code indirect} are packed into a structure that takes the form (in C):</p>
     *
     * <pre><code>
     * typedef struct {
     *     uint count;
     *     uint primCount;
     *     uint first;
     *     uint baseInstance; // must be 0 unless OpenGL 4.2 is supported
     * } DrawArraysIndirectCommand;
     *
     * const DrawArraysIndirectCommand *cmd = (const DrawArraysIndirectCommand *)indirect;
     * glDrawArraysInstancedBaseInstance(mode, cmd-&gt;first, cmd-&gt;count, cmd-&gt;primCount, cmd-&gt;baseInstance);</code></pre>
     *
     * @param mode     what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link #GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
     * @param indirect a structure containing the draw parameters
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawArraysIndirect">Reference Page</a>
     */
    fun drawArraysIndirect(mode: DrawMode, indirect: IntBuffer) = GL40C.nglDrawArraysIndirect(mode.i, indirect.adr.L)

    // --- [ glDrawElementsIndirect ] ---

    /**
     * Renders indexed primitives from array data, taking parameters from memory.
     *
     * <p>{@code glDrawElementsIndirect} behaves similarly to {@link GL42C#glDrawElementsInstancedBaseVertexBaseInstance DrawElementsInstancedBaseVertexBaseInstance}, execpt that the parameters to
     * glDrawElementsInstancedBaseVertexBaseInstance are stored in memory at the address given by {@code indirect}.</p>
     *
     * <p>The parameters addressed by {@code indirect} are packed into a structure that takes the form (in C):</p>
     *
     * <pre><code>
     * typedef struct {
     *     uint count;
     *     uint primCount;
     *     uint firstIndex;
     *     uint baseVertex;
     *     uint baseInstance;
     * } DrawElementsIndirectCommand;</code></pre>
     *
     * <p>{@code glDrawElementsIndirect} is equivalent to:</p>
     *
     * <pre><code>
     * void glDrawElementsIndirect(GLenum mode, GLenum type, const void *indirect) {
     *     const DrawElementsIndirectCommand *cmd  = (const DrawElementsIndirectCommand *)indirect;
     *     glDrawElementsInstancedBaseVertexBaseInstance(
     *         mode,
     *         cmd-&gt;count,
     *         type,
     *         cmd-&gt;firstIndex + size-of-type,
     *         cmd-&gt;primCount,
     *         cmd-&gt;baseVertex,
     *         cmd-&gt;baseInstance
     *     );
     * }</code></pre>
     *
     * @param mode     what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link #GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
     * @param type     the type of data in the buffer bound to the {@link GL15#GL_ELEMENT_ARRAY_BUFFER ELEMENT_ARRAY_BUFFER} binding. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param indirect the address of a structure containing the draw parameters
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsIndirect">Reference Page</a>
     */
    fun drawElementsIndirect(mode: DrawMode, type: IndexType, indirect: ByteBuffer) = GL40C.nglDrawElementsIndirect(mode.i, type.i, indirect.adr.L)

    /**
     * Renders indexed primitives from array data, taking parameters from memory.
     *
     * <p>{@code glDrawElementsIndirect} behaves similarly to {@link GL42C#glDrawElementsInstancedBaseVertexBaseInstance DrawElementsInstancedBaseVertexBaseInstance}, execpt that the parameters to
     * glDrawElementsInstancedBaseVertexBaseInstance are stored in memory at the address given by {@code indirect}.</p>
     *
     * <p>The parameters addressed by {@code indirect} are packed into a structure that takes the form (in C):</p>
     *
     * <pre><code>
     * typedef struct {
     *     uint count;
     *     uint primCount;
     *     uint firstIndex;
     *     uint baseVertex;
     *     uint baseInstance;
     * } DrawElementsIndirectCommand;</code></pre>
     *
     * <p>{@code glDrawElementsIndirect} is equivalent to:</p>
     *
     * <pre><code>
     * void glDrawElementsIndirect(GLenum mode, GLenum type, const void *indirect) {
     *     const DrawElementsIndirectCommand *cmd  = (const DrawElementsIndirectCommand *)indirect;
     *     glDrawElementsInstancedBaseVertexBaseInstance(
     *         mode,
     *         cmd-&gt;count,
     *         type,
     *         cmd-&gt;firstIndex + size-of-type,
     *         cmd-&gt;primCount,
     *         cmd-&gt;baseVertex,
     *         cmd-&gt;baseInstance
     *     );
     * }</code></pre>
     *
     * @param mode     what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link #GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
     * @param type     the type of data in the buffer bound to the {@link GL15#GL_ELEMENT_ARRAY_BUFFER ELEMENT_ARRAY_BUFFER} binding. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param indirect the address of a structure containing the draw parameters
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsIndirect">Reference Page</a>
     */
    fun drawElementsIndirect(mode: DrawMode, type: IndexType, indirect: IntBuffer) = GL40C.nglDrawElementsIndirect(mode.i, type.i, indirect.adr.L)

    // --- [ glUniform1d ] ---

    /**
     * Specifies the value of a double uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param x        the uniform x value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: Int, x: Double) = GL40C.glUniform1d(location, x)

    // --- [ glUniform2d ] ---

    /**
     * Specifies the value of a dvec2 uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param x        the uniform x value
     * @param y        the uniform y value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: Int, x: Double, y: Double) = GL40C.glUniform2d(location, x, y)

    // --- [ glUniform3d ] ---

    /**
     * Specifies the value of a dvec3 uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param x        the uniform x value
     * @param y        the uniform y value
     * @param z        the uniform z value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: Int, x: Double, y: Double, z: Double) = GL40C.glUniform3d(location, x, y, z)

    // --- [ glUniform4d ] ---

    /**
     * Specifies the value of a dvec4 uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param x        the uniform x value
     * @param y        the uniform y value
     * @param z        the uniform z value
     * @param w        the uniform w value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: Int, x: Double, y: Double, z: Double, w: Double) = GL40C.glUniform4d(location, x, y, z, w)

//    // --- [ glUniform1dv ] --- TODO?
//
//    /**
//     * Unsafe version of: {@link #glUniform1dv Uniform1dv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglUniform1dv(int location, int count, long value);
//
//    /**
//     * Specifies the value of a single double uniform variable or a double uniform variable array for the current program object.
//     *
//     * @param location the location of the uniform variable to be modified
//     * @param value    a pointer to an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
//     */
//    public static void glUniform1dv(@NativeType("GLint") int location, @NativeType("GLdouble const *") DoubleBuffer value) {
//        nglUniform1dv(location, value.remaining(), memAddress(value));
//    }
//
//    // --- [ glUniform2dv ] ---
//
//    /**
//     * Unsafe version of: {@link #glUniform2dv Uniform2dv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglUniform2dv(int location, int count, long value);
//
//    /**
//     * Specifies the value of a single dvec2 uniform variable or a dvec2 uniform variable array for the current program object.
//     *
//     * @param location the location of the uniform variable to be modified
//     * @param value    a pointer to an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
//     */
//    public static void glUniform2dv(@NativeType("GLint") int location, @NativeType("GLdouble const *") DoubleBuffer value) {
//        nglUniform2dv(location, value.remaining() >> 1, memAddress(value));
//    }
//
//    // --- [ glUniform3dv ] ---
//
//    /**
//     * Unsafe version of: {@link #glUniform3dv Uniform3dv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglUniform3dv(int location, int count, long value);
//
//    /**
//     * Specifies the value of a single dvec3 uniform variable or a dvec3 uniform variable array for the current program object.
//     *
//     * @param location the location of the uniform variable to be modified
//     * @param value    a pointer to an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
//     */
//    public static void glUniform3dv(@NativeType("GLint") int location, @NativeType("GLdouble const *") DoubleBuffer value) {
//        nglUniform3dv(location, value.remaining() / 3, memAddress(value));
//    }
//
//    // --- [ glUniform4dv ] ---
//
//    /**
//     * Unsafe version of: {@link #glUniform4dv Uniform4dv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglUniform4dv(int location, int count, long value);
//
//    /**
//     * Specifies the value of a single dvec4 uniform variable or a dvec4 uniform variable array for the current program object.
//     *
//     * @param location the location of the uniform variable to be modified
//     * @param value    a pointer to an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
//     */
//    public static void glUniform4dv(@NativeType("GLint") int location, @NativeType("GLdouble const *") DoubleBuffer value) {
//        nglUniform4dv(location, value.remaining() >> 2, memAddress(value));
//    }
//
    // --- [ glUniformMatrix2dv ] ---

    /**
     * Specifies the value of a single dmat2 uniform variable or a dmat2 uniform variable array for the current program object.
     *
     * @param location  the location of the uniform variable to be modified
     * @param value     a pointer to an array of {@code count} values that will be used to update the specified uniform matrix variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: Int, value: Mat2d) = GL40C.nglUniformMatrix2dv(location, 1, false, value.toOffHeap())

    // --- [ glUniformMatrix3dv ] ---

    /**
     * Specifies the value of a single dmat3 uniform variable or a dmat3 uniform variable array for the current program object.
     *
     * @param location  the location of the uniform variable to be modified
     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
     * @param value     a pointer to an array of {@code count} values that will be used to update the specified uniform matrix variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: Int, value: Mat3d) = GL40C.nglUniformMatrix3dv(location, 1, false, value.toOffHeap())

    // --- [ glUniformMatrix4dv ] ---

    /**
     * Specifies the value of a single dmat4 uniform variable or a dmat4 uniform variable array for the current program object.
     *
     * @param location  the location of the uniform variable to be modified
     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
     * @param value     a pointer to an array of {@code count} values that will be used to update the specified uniform matrix variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: Int, value: Mat4d) = GL40C.nglUniformMatrix4dv(location, 1, false, value.toOffHeap())

//    // --- [ glUniformMatrix2x3dv ] --- TODO
//
//    /**
//     * Unsafe version of: {@link #glUniformMatrix2x3dv UniformMatrix2x3dv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglUniformMatrix2x3dv(int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single dmat2x3 uniform variable or a dmat2x3 uniform variable array for the current program object.
//     *
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     a pointer to an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
//     */
//    public static void glUniformMatrix2x3dv(@NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLdouble const *") DoubleBuffer value) {
//        nglUniformMatrix2x3dv(location, value.remaining() / 6, transpose, memAddress(value));
//    }
//
//    // --- [ glUniformMatrix2x4dv ] ---
//
//    /**
//     * Unsafe version of: {@link #glUniformMatrix2x4dv UniformMatrix2x4dv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglUniformMatrix2x4dv(int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single dmat2x4 uniform variable or a dmat2x4 uniform variable array for the current program object.
//     *
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     a pointer to an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
//     */
//    public static void glUniformMatrix2x4dv(@NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLdouble const *") DoubleBuffer value) {
//        nglUniformMatrix2x4dv(location, value.remaining() >> 3, transpose, memAddress(value));
//    }
//
//    // --- [ glUniformMatrix3x2dv ] ---
//
//    /**
//     * Unsafe version of: {@link #glUniformMatrix3x2dv UniformMatrix3x2dv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglUniformMatrix3x2dv(int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single dmat3x2 uniform variable or a dmat3x2 uniform variable array for the current program object.
//     *
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     a pointer to an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
//     */
//    public static void glUniformMatrix3x2dv(@NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLdouble const *") DoubleBuffer value) {
//        nglUniformMatrix3x2dv(location, value.remaining() / 6, transpose, memAddress(value));
//    }
//
//    // --- [ glUniformMatrix3x4dv ] ---
//
//    /**
//     * Unsafe version of: {@link #glUniformMatrix3x4dv UniformMatrix3x4dv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglUniformMatrix3x4dv(int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single dmat3x4 uniform variable or a dmat3x4 uniform variable array for the current program object.
//     *
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     a pointer to an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
//     */
//    public static void glUniformMatrix3x4dv(@NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLdouble const *") DoubleBuffer value) {
//        nglUniformMatrix3x4dv(location, value.remaining() / 12, transpose, memAddress(value));
//    }
//
//    // --- [ glUniformMatrix4x2dv ] ---
//
//    /**
//     * Unsafe version of: {@link #glUniformMatrix4x2dv UniformMatrix4x2dv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglUniformMatrix4x2dv(int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single dmat4x2 uniform variable or a dmat4x2 uniform variable array for the current program object.
//     *
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     a pointer to an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
//     */
//    public static void glUniformMatrix4x2dv(@NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLdouble const *") DoubleBuffer value) {
//        nglUniformMatrix4x2dv(location, value.remaining() >> 3, transpose, memAddress(value));
//    }
//
//    // --- [ glUniformMatrix4x3dv ] ---
//
//    /**
//     * Unsafe version of: {@link #glUniformMatrix4x3dv UniformMatrix4x3dv}
//     *
//     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
//     */
//    public static native void nglUniformMatrix4x3dv(int location, int count, boolean transpose, long value);
//
//    /**
//     * Specifies the value of a single dmat4x3 uniform variable or a dmat4x3 uniform variable array for the current program object.
//     *
//     * @param location  the location of the uniform variable to be modified
//     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
//     * @param value     a pointer to an array of {@code count} values that will be used to update the specified uniform matrix variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
//     */
//    public static void glUniformMatrix4x3dv(@NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLdouble const *") DoubleBuffer value) {
//        nglUniformMatrix4x3dv(location, value.remaining() / 12, transpose, memAddress(value));
//    }
//
    // --- [ glGetUniformdv ] ---
    // inline reified

    // --- [ glMinSampleShading ] ---

    /**
     * Specifies the minimum rate at which sample shading takes place.
     *
     * @param value the rate at which samples are shaded within each covered pixel
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glMinSampleShading">Reference Page</a>
     */
    fun minSampleShading(value: Float) = GL40C.glMinSampleShading(value)

    // --- [ glGetSubroutineUniformLocation ] ---

    /**
     * Retrieves the location of a subroutine uniform of a given shader stage within a program.
     *
     * @param program    the name of the program containing shader stage
     * @param shaderType the shader stage from which to query for subroutine uniform index. One of:<br><table><tr><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td><td>{@link #GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td></tr><tr><td>{@link #GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
     * @param name       the name of the subroutine uniform whose index to query.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetSubroutineUniformLocation">Reference Page</a>
     */
    fun getSubroutineUniformLocation(program: GlProgram, shaderType: ShaderType, name: CharSequence) =
        stack.writeAsciiToAdr(name) { GL40C.nglGetSubroutineUniformLocation(program.name, shaderType.i, it.L) }

    // --- [ glGetSubroutineIndex ] ---

    /**
     * Retrieves the index of a subroutine function of a given shader stage within a program.
     *
     * @param program    the name of the program containing shader stage
     * @param shaderType the shader stage from which to query for subroutine function index. One of:<br><table><tr><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td><td>{@link #GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td></tr><tr><td>{@link #GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
     * @param name       the name of the subroutine function whose index to query
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetSubroutineIndex">Reference Page</a>
     */
    fun getSubroutineIndex(program: GlProgram, shaderType: ShaderType, name: CharSequence) =
        stack.writeAsciiToAdr(name) { GL40C.nglGetSubroutineIndex(program.name, shaderType.i, it.L) }

    // --- [ glGetSubroutineIndex / glGetSubroutineUniformLocation ] ---

    /**
     * Retrieves the index and the uniform location of a subroutine uniform of a given shader stage within a program.
     *
     * @param shaderType the shader stage from which to query for subroutine uniform index. One of:<br><table><tr><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td><td>{@link #GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td></tr><tr><td>{@link #GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
     * @param name       the name of the subroutine uniform whose index to query.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetSubroutineUniformLocation">Reference Page</a>
     */
    fun getSubroutine(program: GlProgram, shaderType: ShaderType, name: CharSequence): Subroutine =
        Subroutine(getSubroutineIndex(program, shaderType, name) to getSubroutineUniformLocation(program, shaderType, name))

    // --- [ glGetActiveSubroutineUniformiv ] ---

    /**
     * Queries a property of an active shader subroutine uniform.
     *
     * @param program    the name of the program containing the subroutine
     * @param shaderType the shader stage from which to query for the subroutine parameter. One of:<br><table><tr><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td><td>{@link #GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td></tr><tr><td>{@link #GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
     * @param index      the index of the shader subroutine uniform
     * @param name       the parameter of the shader subroutine uniform to query. One of:<br><table><tr><td>{@link #GL_NUM_COMPATIBLE_SUBROUTINES NUM_COMPATIBLE_SUBROUTINES}</td><td>{@link #GL_COMPATIBLE_SUBROUTINES COMPATIBLE_SUBROUTINES}</td><td>{@link GL31#GL_UNIFORM_SIZE UNIFORM_SIZE}</td><td>{@link GL31#GL_UNIFORM_NAME_LENGTH UNIFORM_NAME_LENGTH}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetActiveSubroutineUniform">Reference Page</a>
     */
    fun getActiveSubroutineUniform(program: GlProgram, shaderType: ShaderType, index: Int, name: GetActiveSubroutineUniform): Int =
        readInt { GL40C.nglGetActiveSubroutineUniformiv(program.name, shaderType.i, index, name.i, it) }

    /**
     * @return GL_COMPATIBLE_SUBROUTINES of an active shader subroutine uniform.
     *
     * @param program    the name of the program containing the subroutine
     * @param shaderType the shader stage from which to query for the subroutine parameter. One of:<br><table><tr><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td><td>{@link #GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td></tr><tr><td>{@link #GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
     * @param index      the index of the shader subroutine uniform
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetActiveSubroutineUniform">Reference Page</a>
     */
    fun getActiveSubroutineUniformCompatibles(program: GlProgram, shaderType: ShaderType, index: Int): IntArray =
        stack { s ->
            val size = s.readIntFromAdr { GL40C.nglGetActiveSubroutineUniformiv(program.name, shaderType.i, index, GL40C.GL_NUM_COMPATIBLE_SUBROUTINES, it.L) }
            val ints = s.nmalloc(4, size * Int.BYTES)
            GL40C.nglGetActiveSubroutineUniformiv(program.name, shaderType.i, index, GL40C.GL_COMPATIBLE_SUBROUTINES, ints)
            IntArray(size) { memGetInt(ints + Int.BYTES * it) }
        }

    // --- [ glGetActiveSubroutineUniformName ] ---

    /**
     * Queries the name of an active shader subroutine uniform.
     *
     * @param program    the name of the program containing the subroutine
     * @param shaderType the shader stage from which to query for the subroutine parameter. One of:<br><table><tr><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td><td>{@link #GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td></tr><tr><td>{@link #GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
     * @param index      the index of the shader subroutine uniform
     * @param bufSize    the size of the buffer whose address is given in {@code name}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetActiveSubroutineUniformName">Reference Page</a>
     */
    fun getActiveSubroutineUniformName(program: GlProgram, shaderType: ShaderType, index: Int,
                                       bufSize: Int = getActiveSubroutineUniform(program, shaderType, index, GetActiveSubroutineUniform.UNIFORM_NAME_LENGTH)): String =
        stack {
            val pString = it.nmalloc(1, bufSize)
            val pLength = it.nmalloc(Int.BYTES, Int.BYTES)
            GL40C.nglGetActiveSubroutineUniformName(program.name, shaderType.i, index, bufSize, pLength, pString)
            MemoryUtil.memASCII(pString, memGetInt(pLength))
        }

    // --- [ glGetActiveSubroutineName ] ---

    /**
     * Queries the name of an active shader subroutine.
     *
     * @param program    the name of the program containing the subroutine
     * @param shaderType the shader stage from which to query the subroutine name. One of:<br><table><tr><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td><td>{@link #GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td></tr><tr><td>{@link #GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
     * @param index      the index of the shader subroutine uniform
     * @param bufSize    the size of the buffer whose address is given in {@code name}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetActiveSubroutineName">Reference Page</a>
     */
    fun getActiveSubroutineName(program: GlProgram, shaderType: ShaderType, index: Int,
                                bufSize: Int = GL40C.glGetProgramStagei(program.name, shaderType.i, GL40C.GL_ACTIVE_SUBROUTINE_MAX_LENGTH)): String =
        stack {
            val pString = it.nmalloc(1, bufSize)
            val pLength = it.nmalloc(Int.BYTES, Int.BYTES)
            GL40C.nglGetActiveSubroutineName(program.name, shaderType.i, index, bufSize, pLength, pString)
            MemoryUtil.memASCII(pString, memGetInt(pLength))
        }

    // --- [ glUniformSubroutinesuiv ] ---

    /**
     * Loads active subroutine uniforms. TODO rename to uniformSubroutines?
     *
     * @param shaderType the shader stage to update. One of:<br><table><tr><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td><td>{@link #GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td></tr><tr><td>{@link #GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
     * @param indices    an array holding the indices to load into the shader subroutine variables
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniformSubroutines">Reference Page</a>
     */
    fun uniformSubroutinesuiv(shaderType: ShaderType, indices: IntBuffer) = GL40C.nglUniformSubroutinesuiv(shaderType.i, indices.rem, indices.adr.L)

    /**
     * Loads active subroutine uniforms.
     *
     * @param shaderType the shader stage to update. One of:<br><table><tr><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td><td>{@link #GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td></tr><tr><td>{@link #GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniformSubroutines">Reference Page</a>
     */
    fun uniformSubroutinesui(shaderType: ShaderType, index: Int) = GL40C.nglUniformSubroutinesuiv(shaderType.i, 1, index.toOffHeap())

    // --- [ glGetUniformSubroutineuiv ] ---

    /**
     * Retrieves the value of a subroutine uniform of a given shader stage of the current program.
     *
     * @param shaderType the shader stage from which to query for subroutine uniform index. One of:<br><table><tr><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td><td>{@link #GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td></tr><tr><td>{@link #GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
     * @param location   the location of the subroutine uniform
     * @param params     a variable to receive the value or values of the subroutine uniform
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetUniformSubroutine">Reference Page</a>
     */
    fun getUniformSubroutineuiv(shaderType: ShaderType, location: UniformLocation, params: IntBuffer) =
        GL40C.nglGetUniformSubroutineuiv(shaderType.i, location, params.adr.L)

    /**
     * Retrieves the value of a subroutine uniform of a given shader stage of the current program.
     *
     * @param shaderType the shader stage from which to query for subroutine uniform index. One of:<br><table><tr><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td><td>{@link #GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td></tr><tr><td>{@link #GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
     * @param location   the location of the subroutine uniform
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetUniformSubroutine">Reference Page</a>
     */
    fun getUniformSubroutineui(shaderType: ShaderType, location: UniformLocation): Int = readInt { GL40C.nglGetUniformSubroutineuiv(shaderType.i, location, it) }

    // --- [ glGetProgramStageiv ] ---

    /**
     * Retrieves properties of a program object corresponding to a specified shader stage.
     *
     * @param program    the name of the program containing shader stage
     * @param shaderType the shader stage from which to query for the subroutine parameter. One of:<br><table><tr><td>{@link GL20#GL_VERTEX_SHADER VERTEX_SHADER}</td><td>{@link GL20#GL_FRAGMENT_SHADER FRAGMENT_SHADER}</td><td>{@link GL32#GL_GEOMETRY_SHADER GEOMETRY_SHADER}</td><td>{@link #GL_TESS_CONTROL_SHADER TESS_CONTROL_SHADER}</td></tr><tr><td>{@link #GL_TESS_EVALUATION_SHADER TESS_EVALUATION_SHADER}</td></tr></table>
     * @param name      the parameter of the shader to query. One of:<br><table><tr><td>{@link #GL_ACTIVE_SUBROUTINES ACTIVE_SUBROUTINES}</td><td>{@link #GL_ACTIVE_SUBROUTINE_UNIFORMS ACTIVE_SUBROUTINE_UNIFORMS}</td></tr><tr><td>{@link #GL_ACTIVE_SUBROUTINE_UNIFORM_LOCATIONS ACTIVE_SUBROUTINE_UNIFORM_LOCATIONS}</td><td>{@link #GL_ACTIVE_SUBROUTINE_MAX_LENGTH ACTIVE_SUBROUTINE_MAX_LENGTH}</td></tr><tr><td>{@link #GL_ACTIVE_SUBROUTINE_UNIFORM_MAX_LENGTH ACTIVE_SUBROUTINE_UNIFORM_MAX_LENGTH}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetProgramStage">Reference Page</a>
     */
    fun getProgramStage(program: GlProgram, shaderType: ShaderType, name: GetProgramStage): Int = readInt { GL40C.nglGetProgramStageiv(program.name, shaderType.i, name.i, it) }

    // --- [ glPatchParameteri ] ---

    /**
     * Specifies the value of the GL_PATCH_VERTICES for patch primitives.
     *
     * @param value the new value for the parameter given by {@code pname}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glPatchParameteri">Reference Page</a>
     */
    fun patchVertices(value: Int) = GL40C.glPatchParameteri(GL40C.GL_PATCH_VERTICES, value)

    // --- [ glPatchParameterfv ] ---

    /**
     * Specifies an array of float values for the specified parameter for patch primitives.
     *
     * @param name  the name of the parameter to set. One of:<br><table><tr><td>{@link #GL_PATCH_DEFAULT_OUTER_LEVEL PATCH_DEFAULT_OUTER_LEVEL}</td><td>{@link #GL_PATCH_DEFAULT_INNER_LEVEL PATCH_DEFAULT_INNER_LEVEL}</td></tr></table>
     * @param values an array containing the new values for the parameter given by {@code name}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glPatchParameter">Reference Page</a>
     */
    fun patchParameter(name: PatchParameter, values: FloatBuffer) = GL40C.nglPatchParameterfv(name.i, values.adr.L)

    // --- [ glBindTransformFeedback ] ---

    /**
     * Binds a transform feedback object.
     *
     * @param target the target to which to bind the transform feedback object {@code id}. Must be:<br><table><tr><td>{@link #GL_TRANSFORM_FEEDBACK TRANSFORM_FEEDBACK}</td></tr></table>
     * @param id     the name of a transform feedback object
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBindTransformFeedback">Reference Page</a>
     */
    fun bindTransformFeedback(id: GlTransformFeedback) = GL40C.glBindTransformFeedback(GL40C.GL_TRANSFORM_FEEDBACK, id.name)

    // --- [ glDeleteTransformFeedbacks ] ---

    /**
     * Deletes transform feedback objects.
     *
     * @param ids an array of names of transform feedback objects to delete
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteTransformFeedbacks">Reference Page</a>
     */
    fun deleteTransformFeedbacks(ids: GlTransformFeedbacks) = GL40C.nglDeleteTransformFeedbacks(ids.rem, ids.adr)

    /**
     * Deletes transform feedback objects.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteTransformFeedbacks">Reference Page</a>
     */
    fun deleteTransformFeedbacks(id: GlTransformFeedback) = GL40C.nglDeleteTransformFeedbacks(1, id.name.toOffHeap())

    // --- [ glGenTransformFeedbacks ] ---

    /**
     * Reserves transform feedback object names.
     *
     * @param ids an array of into which the reserved names will be written
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenTransformFeedbacks">Reference Page</a>
     */
    fun genTransformFeedbacks(ids: GlTransformFeedbacks) = GL40C.nglGenTransformFeedbacks(ids.rem, ids.adr)

    /**
     * Reserves transform feedback object names.
     *
     * @param ids an array of into which the reserved names will be written
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenTransformFeedbacks">Reference Page</a>
     */
    fun genTransformFeedbacks(size: Int): GlTransformFeedbacks = GlTransformFeedbacks(size).also(::genTransformFeedbacks)

    /**
     * Reserves transform feedback object names.
     *
     * @param ids an array of into which the reserved names will be written
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenTransformFeedbacks">Reference Page</a>
     */
    fun genTransformFeedbacks(feedback: KMutableProperty0<GlTransformFeedback>): GlTransformFeedback {
        feedback.set(genTransformFeedbacks())
        return feedback()
    }

    /**
     * Reserves transform feedback object names.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenTransformFeedbacks">Reference Page</a>
     */
    fun genTransformFeedbacks(): GlTransformFeedback = GlTransformFeedback(readInt { GL40C.nglGenTransformFeedbacks(1, it) })

    // --- [ glIsTransformFeedback ] ---

    /**
     * Determines if a name corresponds to a transform feedback object.
     *
     * @param id a value that may be the name of a transform feedback object
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glIsTransformFeedback">Reference Page</a>
     */
    fun isTransformFeedback(id: GlTransformFeedback): Boolean = GL40C.glIsTransformFeedback(id.name)

    // --- [ glPauseTransformFeedback ] --- TODO lambdas anyone?

    /**
     * Pauses transform feedback operations for the currently bound transform feedback object.
     *
     * <p>When transform feedback operations are paused, transform feedback is still considered active and changing most transform feedback state related to the
     * object results in an error. However, a new transform feedback object may be bound while transform feedback is paused. The error {@link GL11#GL_INVALID_OPERATION INVALID_OPERATION}
     * is generated by PauseTransformFeedback if the currently bound transform feedback is not active or is paused.</p>
     *
     * <p>When transform feedback is active and not paused, all geometric primitives generated must be compatible with the value of {@code primitiveMode} passed
     * to {@link GL30C#glBeginTransformFeedback BeginTransformFeedback}. The error {@link GL11#GL_INVALID_OPERATION INVALID_OPERATION} is generated by {@link GL11#glBegin Begin} or any operation that implicitly calls {@link GL11#glBegin Begin}
     * (such as {@link GL11C#glDrawElements DrawElements}) if {@code mode} is not one of the allowed modes. If a geometry shader is active, its output primitive type is used instead
     * of the {@code mode} parameter passed to {@link GL11#glBegin Begin} for the purposes of this error check. Any primitive type may be used while transform feedback is
     * paused.</p>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glPauseTransformFeedback">Reference Page</a>
     */
    fun pauseTransformFeedback() = GL40C.glPauseTransformFeedback()

    // --- [ glResumeTransformFeedback ] ---

    /**
     * Resumes transform feedback operations for the currently bound transform feedback object.
     *
     * <p>The error {@link GL11#GL_INVALID_OPERATION INVALID_OPERATION} is generated by {@link #glResumeTransformFeedback ResumeTransformFeedback} if the currently bound transform feedback is not active or is not paused.</p>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glResumeTransformFeedback">Reference Page</a>
     */
    fun resumeTransformFeedback() = GL40C.glResumeTransformFeedback()

    // --- [ glDrawTransformFeedback ] ---

    /**
     * Render primitives using a count derived from a transform feedback object.
     *
     * @param mode what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link #GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
     * @param id   the name of a transform feedback object from which to retrieve a primitive count
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawTransformFeedback">Reference Page</a>
     */
    fun drawTransformFeedback(mode: DrawMode, id: GlTransformFeedback) = GL40C.glDrawTransformFeedback(mode.i, id.name)

    // --- [ glDrawTransformFeedbackStream ] ---

    /**
     * Renders primitives using a count derived from a specifed stream of a transform feedback object.
     *
     * @param mode   what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link #GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
     * @param id     the name of a transform feedback object from which to retrieve a primitive count
     * @param stream the index of the transform feedback stream from which to retrieve a primitive count
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawTransformFeedbackStream">Reference Page</a>
     */
    fun drawTransformFeedbackStream(mode: DrawMode, id: GlTransformFeedback, stream: Int) = GL40C.glDrawTransformFeedbackStream(mode.i, id.name, stream)

    // --- [ glBeginQueryIndexed ] ---

    /**
     * Begins a query object on an indexed target
     *
     * @param target the target type of query object established between {@code glBeginQueryIndexed} and the subsequent {@link #glEndQueryIndexed EndQueryIndexed}. One of:<br><table><tr><td>{@link GL15#GL_SAMPLES_PASSED SAMPLES_PASSED}</td><td>{@link GL30#GL_PRIMITIVES_GENERATED PRIMITIVES_GENERATED}</td><td>{@link GL30#GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN}</td><td>{@link GL33#GL_TIME_ELAPSED TIME_ELAPSED}</td></tr><tr><td>{@link GL33#GL_TIMESTAMP TIMESTAMP}</td><td>{@link GL33#GL_ANY_SAMPLES_PASSED ANY_SAMPLES_PASSED}</td><td>{@link GL43#GL_ANY_SAMPLES_PASSED_CONSERVATIVE ANY_SAMPLES_PASSED_CONSERVATIVE}</td></tr></table>
     * @param index  the index of the query target upon which to begin the query
     * @param id     the name of a query object
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBeginQueryIndexed">Reference Page</a>
     */
    fun beginQueryIndexed(target: QueryIndexedTarget, index: Int, id: GlQuery) = GL40C.glBeginQueryIndexed(target.i, index, id.name)

    // --- [ glEndQueryIndexed ] ---

    /**
     * Ends a query object on an indexed target
     *
     * @param target the target type of query object to be concluded. One of:<br><table><tr><td>{@link GL15#GL_SAMPLES_PASSED SAMPLES_PASSED}</td><td>{@link GL30#GL_PRIMITIVES_GENERATED PRIMITIVES_GENERATED}</td><td>{@link GL30#GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN}</td><td>{@link GL33#GL_TIME_ELAPSED TIME_ELAPSED}</td></tr><tr><td>{@link GL33#GL_TIMESTAMP TIMESTAMP}</td><td>{@link GL33#GL_ANY_SAMPLES_PASSED ANY_SAMPLES_PASSED}</td><td>{@link GL43#GL_ANY_SAMPLES_PASSED_CONSERVATIVE ANY_SAMPLES_PASSED_CONSERVATIVE}</td></tr></table>
     * @param index  the index of the query target upon which to end the query
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glEndQueryIndexed">Reference Page</a>
     */
    fun endQueryIndexed(target: QueryIndexedTarget, index: Int) = GL40C.glEndQueryIndexed(target.i, index)

    // --- [ glGetQueryIndexediv ] ---
    // inline reified
}