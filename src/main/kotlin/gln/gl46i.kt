package gln

import gln.identifiers.GlShader
import kool.Ptr
import kool.adr
import kool.rem
import kool.stak
import org.lwjgl.opengl.GL46C
import java.nio.ByteBuffer
import java.nio.IntBuffer

/**
 * The OpenGL functionality up to version 4.6. Includes only Core Profile symbols.
 *
 * <p>OpenGL 4.6 implementations support revision 4.60 of the OpenGL Shading Language.</p>
 *
 * <p>Extensions promoted to core in this release:</p>
 *
 * <ul>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_indirect_parameters.txt">ARB_indirect_parameters</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_pipeline_statistics_query.txt">ARB_pipeline_statistics_query</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_polygon_offset_clamp.txt">ARB_polygon_offset_clamp</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/KHR/KHR_no_error.txt">KHR_no_error</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_shader_atomic_counter_ops.txt">ARB_shader_atomic_counter_ops</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_shader_draw_parameters.txt">ARB_shader_draw_parameters</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_shader_group_vote.txt">ARB_shader_group_vote</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_gl_spirv.txt">ARB_gl_spirv</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_spirv_extensions.txt">ARB_spirv_extensions</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_texture_filter_anisotropic.txt">ARB_texture_filter_anisotropic</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_transform_feedback_overflow_query.txt">ARB_transform_feedback_overflow_query</a></li>
 * </ul>
 */
interface gl46i {

    // --- [ glMultiDrawArraysIndirectCount ] ---

    /**
     * Behaves similarly to {@link GL43C#glMultiDrawArraysIndirect MultiDrawArraysIndirect}, except that {@code drawCount} defines an offset (in bytes) into the buffer object bound to the
     * {@link #GL_PARAMETER_BUFFER PARAMETER_BUFFER} binding point at which a single {@code sizei} typed value is stored, which contains the draw count. {@code maxDrawCount} specifies
     * the maximum number of draws that are expected to be stored in the buffer. If the value stored at {@code drawCount} into the buffer is greater than
     * {@code maxDrawCount}, the implementation stops processing draws after {@code maxDrawCount} parameter sets. {@code drawCount} must be a multiple of
     * four.
     *
     * @param mode         what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
     * @param indirect     an array of structures containing the draw parameters
     * @param drawCount    the offset into the parameter buffer object
     * @param maxDrawCount the maximum number of draws
     * @param stride       the distance in basic machine units between elements of the draw parameter array
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glMultiDrawArraysIndirectCount">Reference Page</a>
     */
    fun multiDrawArraysIndirectCount(mode: DrawMode, indirect: ByteBuffer, drawCount: Ptr, maxDrawCount: Int, stride: Int) =
            GL46C.nglMultiDrawArraysIndirectCount(mode.i, indirect.adr, drawCount, maxDrawCount, stride)

    /**
     * Behaves similarly to {@link GL43C#glMultiDrawArraysIndirect MultiDrawArraysIndirect}, except that {@code drawCount} defines an offset (in bytes) into the buffer object bound to the
     * {@link #GL_PARAMETER_BUFFER PARAMETER_BUFFER} binding point at which a single {@code sizei} typed value is stored, which contains the draw count. {@code maxDrawCount} specifies
     * the maximum number of draws that are expected to be stored in the buffer. If the value stored at {@code drawCount} into the buffer is greater than
     * {@code maxDrawCount}, the implementation stops processing draws after {@code maxDrawCount} parameter sets. {@code drawCount} must be a multiple of
     * four.
     *
     * @param mode         what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
     * @param indirect     an array of structures containing the draw parameters
     * @param drawCount    the offset into the parameter buffer object
     * @param maxDrawCount the maximum number of draws
     * @param stride       the distance in basic machine units between elements of the draw parameter array
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glMultiDrawArraysIndirectCount">Reference Page</a>
     */
    fun multiDrawArraysIndirectCount(mode: DrawMode, indirect: IntBuffer, drawCount: Ptr, maxDrawCount: Int, stride: Int) =
            GL46C.nglMultiDrawArraysIndirectCount(mode.i, indirect.adr, drawCount, maxDrawCount, stride)

    // --- [ glMultiDrawElementsIndirectCount ] ---

    /**
     * Behaves similarly to {@link GL43C#glMultiDrawElementsIndirect MultiDrawElementsIndirect}, except that {@code drawCount} defines an offset (in bytes) into the buffer object bound to the
     * {@link #GL_PARAMETER_BUFFER PARAMETER_BUFFER} binding point at which a single {@code sizei} typed value is stored, which contains the draw count. {@code maxDrawCount} specifies
     * the maximum number of draws that are expected to be stored in the buffer. If the value stored at {@code drawCount} into the buffer is greater than
     * {@code maxDrawCount}, the implementation stops processing draws after {@code maxDrawCount} parameter sets. {@code drawCount} must be a multiple of
     * four.
     *
     * @param mode         what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
     * @param type         the type of data in the buffer bound to the GL_ELEMENT_ARRAY_BUFFER binding. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param indirect     a structure containing an array of draw parameters
     * @param drawCount    the offset into the parameter buffer object
     * @param maxDrawCount the maximum number of draws
     * @param stride       the distance in basic machine units between elements of the draw parameter array
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glMultiDrawElementsIndirectCount">Reference Page</a>
     */
    fun multiDrawElementsIndirectCount(mode: DrawMode, type: IndexType, indirect: ByteBuffer, drawCount: Ptr, maxDrawCount: Int, stride: Int) =
            GL46C.nglMultiDrawElementsIndirectCount(mode.i, type.i, indirect.adr, drawCount, maxDrawCount, stride)

    /**
     * Behaves similarly to {@link GL43C#glMultiDrawElementsIndirect MultiDrawElementsIndirect}, except that {@code drawCount} defines an offset (in bytes) into the buffer object bound to the
     * {@link #GL_PARAMETER_BUFFER PARAMETER_BUFFER} binding point at which a single {@code sizei} typed value is stored, which contains the draw count. {@code maxDrawCount} specifies
     * the maximum number of draws that are expected to be stored in the buffer. If the value stored at {@code drawCount} into the buffer is greater than
     * {@code maxDrawCount}, the implementation stops processing draws after {@code maxDrawCount} parameter sets. {@code drawCount} must be a multiple of
     * four.
     *
     * @param mode         what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
     * @param type         the type of data in the buffer bound to the GL_ELEMENT_ARRAY_BUFFER binding. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param indirect     a structure containing an array of draw parameters
     * @param drawCount    the offset into the parameter buffer object
     * @param maxDrawCount the maximum number of draws
     * @param stride       the distance in basic machine units between elements of the draw parameter array
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glMultiDrawElementsIndirectCount">Reference Page</a>
     */
    fun multiDrawElementsIndirectCount(mode: DrawMode, type: IndexType, indirect: IntBuffer, drawCount: Ptr, maxDrawCount: Int, stride: Int) =
            GL46C.nglMultiDrawElementsIndirectCount(mode.i, type.i, indirect.adr, drawCount, maxDrawCount, stride)

    // --- [ glPolygonOffsetClamp ] ---

    /**
     * The depth values of all fragments generated by the rasterization of a polygon may be offset by a single value that is computed for that polygon. This
     * function determines this value.
     *
     * <p>{@code factor} scales the maximum depth slope of the polygon, and {@code units} scales an implementation-dependent constant that relates to the usable
     * resolution of the depth buffer. The resulting values are summed to produce the polygon offset value, which may then be clamped to a minimum or maximum
     * value specified by {@code clamp}.</p>
     *
     * <p>The values {@code factor}, {@code units}, and {@code clamp} may each be positive, negative, or zero. Calling the command {@link GL11C#glPolygonOffset PolygonOffset} is equivalent
     * to calling the command {@code PolygonOffsetClamp} with clamp equal to zero.</p>
     *
     * @param factor scales the maximum depth slope of the polygon
     * @param units  scales an implementation-dependent constant that relates to the usable resolution of the depth buffer
     * @param clamp  the minimum or maximum polygon offset value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glPolygonOffsetClamp">Reference Page</a>
     */
    fun polygonOffsetClamp(factor: Float, units: Float, clamp: Float) = GL46C.glPolygonOffsetClamp(factor, units, clamp)

    // --- [ glSpecializeShader ] ---

    /**
     * Specializes a shader created from a SPIR-V module.
     *
     * <p>Shaders associated with SPIR-V modules must be specialized before they can be linked into a program object. It is not necessary to specialize the
     * shader before it is attached to a program object. Once specialized, a shader may not be specialized again without first re-associating the original
     * SPIR-V module with it, through {@link GL41C#glShaderBinary ShaderBinary}.</p>
     *
     * <p>Specialization does two things:</p>
     *
     * <ul>
     * <li>Selects the name of the entry point, for that shader’s stage, from the SPIR-V module.</li>
     * <li>Sets the values of all, or a subset of, the specialization constants in the SPIRV module.</li>
     * </ul>
     *
     * <p>On successful shader specialization, the compile status for shader is set to {@link GL11#GL_TRUE TRUE}. On failure, the compile status for shader is set to {@link GL11#GL_FALSE FALSE} and
     * additional information about the cause of the failure may be available in the shader compilation log.</p>
     *
     * @param shader         the name of a shader object containing unspecialized SPIR-V as created from a successful call to {@link GL41C#glShaderBinary ShaderBinary} to which a SPIR-V module was
     *                       passed
     * @param entryPoint    a pointer to a null-terminated UTF-8 string specifying the name of the entry point in the SPIR-V module to use for this shader
     * @param pConstantIndex is a pointer to an array of {@code numSpecializationConstants} unsigned integers, each holding the index of a specialization constant in the SPIR-V
     *                       module whose value to set.
     *
     *                       <p>Specialization constants not referenced by {@code pConstantIndex} retain their default values as specified in the SPIR-V module.</p>
     * @param pConstantValue an entry in {@code pConstantValue} is used to set the value of the specialization constant indexed by the corresponding entry in
     *                       {@code pConstantIndex}.
     *
     *                       <p>Although this array is of unsigned integer, each entry is bitcast to the appropriate type for the module, and therefore, floating-point constants
     *                       may be set by including their IEEE-754 bit representation in the {@code pConstantValue} array.</p>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glSpecializeShader">Reference Page</a>
     */
    fun specializeShader(shader: GlShader, entryPoint: CharSequence, pConstantIndex: IntBuffer, pConstantValue: IntBuffer) =
            stak.utf8Address(entryPoint) { pEntryPoint ->
                GL46C.nglSpecializeShader(shader.name, pEntryPoint, pConstantIndex.rem, pConstantIndex.adr, pConstantValue.adr)
            }
}