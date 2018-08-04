/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */

package gln

import ab.appBuffer
import glm_.buffer.adr
import glm_.buffer.rem
import glm_.vec2.Vec2d
import glm_.vec4.Vec4
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11C
import org.lwjgl.system.*
import org.lwjgl.system.Checks.*
import org.lwjgl.system.MemoryUtil.memAddress
import java.awt.Color
import java.nio.*


object gl11 : gl11i

fun main(args: Array<String>) {
    gl11.flush()
}

/**
 * The OpenGL functionality of a forward compatible context, up to version 1.1.
 *
 * <p>Extensions promoted to core in this release:</p>
 *
 * <ul>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_vertex_array.txt">EXT_vertex_array</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_polygon_offset.txt">EXT_polygon_offset</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_blend_logic_op.txt">EXT_blend_logic_op</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_texture.txt">EXT_texture</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_copy_texture.txt">EXT_copy_texture</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_subtexture.txt">EXT_subtexture</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_texture_object.txt">EXT_texture_object</a></li>
 * </ul>
 */

interface gl11i {

    /** vertex_array */
//    public static final int GL_VERTEX_ARRAY = 0x8074;

    // --- [ glEnable ] ---

    /**
     * Enables the specified OpenGL state.
     *
     * @param target the OpenGL state to enable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glEnable">Reference Page</a>
     */
    fun enable(target: EnableCap) = GL11C.glEnable(target.i)

    // --- [ glDisable ] ---

    /**
     * Disables the specified OpenGL state.
     *
     * @param target the OpenGL state to disable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDisable">Reference Page</a>
     */
    fun disable(target: EnableCap) = GL11C.glDisable(target.i)

    // --- [ glBindTexture ] ---

    /**
     * Binds the a texture to a texture target.
     *
     * <p>While a texture object is bound, GL operations on the target to which it is bound affect the bound object, and queries of the target to which it is
     * bound return state from the bound object. If texture mapping of the dimensionality of the target to which a texture object is bound is enabled, the
     * state of the bound texture object directs the texturing operation.</p>
     *
     * @param target  the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL40#GL_TEXTURE_CUBE_MAP_ARRAY TEXTURE_CUBE_MAP_ARRAY}</td><td>{@link GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE TEXTURE_2D_MULTISAMPLE}</td></tr><tr><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE_ARRAY TEXTURE_2D_MULTISAMPLE_ARRAY}</td></tr></table>
     * @param texture the texture object to bind
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBindTexture">Reference Page</a>
     */
    fun bindTexture(target: TextureTarget, texture: GLtexture) = GL11C.glBindTexture(target.i, texture.i)

    // --- [ glBlendFunc ] ---

    /**
     * Specifies the weighting factors used by the blend equation, for both RGB and alpha functions and for all draw buffers.
     *
     * @param sFactor the source weighting factor. One of:<br><table><tr><td>{@link #GL_ZERO ZERO}</td><td>{@link #GL_ONE ONE}</td><td>{@link #GL_SRC_COLOR SRC_COLOR}</td><td>{@link #GL_ONE_MINUS_SRC_COLOR ONE_MINUS_SRC_COLOR}</td><td>{@link #GL_DST_COLOR DST_COLOR}</td></tr><tr><td>{@link #GL_ONE_MINUS_DST_COLOR ONE_MINUS_DST_COLOR}</td><td>{@link #GL_SRC_ALPHA SRC_ALPHA}</td><td>{@link #GL_ONE_MINUS_SRC_ALPHA ONE_MINUS_SRC_ALPHA}</td><td>{@link #GL_DST_ALPHA DST_ALPHA}</td><td>{@link #GL_ONE_MINUS_DST_ALPHA ONE_MINUS_DST_ALPHA}</td></tr><tr><td>{@link GL14#GL_CONSTANT_COLOR CONSTANT_COLOR}</td><td>{@link GL14#GL_ONE_MINUS_CONSTANT_COLOR ONE_MINUS_CONSTANT_COLOR}</td><td>{@link GL14#GL_CONSTANT_ALPHA CONSTANT_ALPHA}</td><td>{@link GL14#GL_ONE_MINUS_CONSTANT_ALPHA ONE_MINUS_CONSTANT_ALPHA}</td><td>{@link #GL_SRC_ALPHA_SATURATE SRC_ALPHA_SATURATE}</td></tr><tr><td>{@link GL33#GL_SRC1_COLOR SRC1_COLOR}</td><td>{@link GL33#GL_ONE_MINUS_SRC1_COLOR ONE_MINUS_SRC1_COLOR}</td><td>{@link GL15#GL_SRC1_ALPHA SRC1_ALPHA}</td><td>{@link GL33#GL_ONE_MINUS_SRC1_ALPHA ONE_MINUS_SRC1_ALPHA}</td></tr></table>
     * @param dFactor the destination weighting factor
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBlendFunc">Reference Page</a>
     */
    fun blendFunc(sFactor: BlendingFactorSrc, dFactor: BlendingFactorDest) = GL11C.glBlendFunc(sFactor.i, dFactor.i)

    // --- [ glClear ] ---

    /**
     * Sets portions of every pixel in a particular buffer to the same value. The value to which each buffer is cleared depends on the setting of the clear
     * value for that buffer.
     *
     * @param mask Zero or the bitwise OR of one or more values indicating which buffers are to be cleared. One or more of:<br><table><tr><td>{@link #GL_COLOR_BUFFER_BIT COLOR_BUFFER_BIT}</td><td>{@link #GL_DEPTH_BUFFER_BIT DEPTH_BUFFER_BIT}</td><td>{@link #GL_STENCIL_BUFFER_BIT STENCIL_BUFFER_BIT}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClear">Reference Page</a>
     */
    fun clear(mask: ClearBufferMask) = GL11C.glClear(mask.i)

    // --- [ glClearColor ] ---

    /**
     * Sets the clear value for fixed-point and floating-point color buffers in RGBA mode. The specified components are stored as floating-point values.
     *
     * @param red   the value to which to clear the R channel of the color buffer
     * @param green the value to which to clear the G channel of the color buffer
     * @param blue  the value to which to clear the B channel of the color buffer
     * @param alpha the value to which to clear the A channel of the color buffer
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClearColor">Reference Page</a>
     */
    fun clearColor(red: Float, green: Float, blue: Float, alpha: Float) = GL11C.glClearColor(red, green, blue, alpha)

    /**
     * Sets the clear value for fixed-point and floating-point color buffers in RGBA mode. The specified components are stored as floating-point values.
     *
     * @param red   the value to which to clear the R channel of the color buffer
     * @param green the value to which to clear the G channel of the color buffer
     * @param blue  the value to which to clear the B channel of the color buffer
     * @param alpha the value to which to clear the A channel of the color buffer
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClearColor">Reference Page</a>
     */
    fun clearColor(float: Float) = GL11C.glClearColor(float, float, float, float)

    /**
     * Sets the clear value for fixed-point and floating-point color buffers in RGBA mode. The specified components are stored as floating-point values.
     *
     * @param red   the value to which to clear the R channel of the color buffer
     * @param green the value to which to clear the G channel of the color buffer
     * @param blue  the value to which to clear the B channel of the color buffer
     * @param alpha the value to which to clear the A channel of the color buffer
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClearColor">Reference Page</a>
     */
    fun clearColor(color: Color) = GL11C.glClearColor(color.red / 255f, color.green / 255f, color.blue / 255f, color.alpha / 255f)

    /**
     * Sets the clear value for fixed-point and floating-point color buffers in RGBA mode. The specified components are stored as floating-point values.
     *
     * @param red   the value to which to clear the R channel of the color buffer
     * @param green the value to which to clear the G channel of the color buffer
     * @param blue  the value to which to clear the B channel of the color buffer
     * @param alpha the value to which to clear the A channel of the color buffer
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClearColor">Reference Page</a>
     */
    fun clearColor(color: Vec4) = GL11C.glClearColor(color.r, color.g, color.b, color.a)

    // --- [ glClearDepth ] ---

    /**
     * Sets the depth value used when clearing the depth buffer. When clearing a fixedpoint depth buffer, {@code depth} is clamped to the range [0,1] and
     * converted to fixed-point. No conversion is applied when clearing a floating-point depth buffer.
     *
     * @param depth the value to which to clear the depth buffer
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClearDepth">Reference Page</a>
     */
    fun clearDepth(depth: Double) = GL11C.glClearDepth(depth)

    // --- [ glClearStencil ] ---

    /**
     * Sets the value to which to clear the stencil buffer. {@code s} is masked to the number of bitplanes in the stencil buffer.
     *
     * @param s the value to which to clear the stencil buffer
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClearStencil">Reference Page</a>
     */
    fun clearStencil(s: Int) = GL11C.glClearStencil(s)

    // --- [ glColorMask ] ---

    /**
     * Masks the writing of R, G, B and A values to all draw buffers. In the initial state, all color values are enabled for writing for all draw buffers.
     *
     * @param red   whether R values are written or not
     * @param green whether G values are written or not
     * @param blue  whether B values are written or not
     * @param alpha whether A values are written or not
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glColorMask">Reference Page</a>
     */
    fun colorMask(red: Boolean, green: Boolean, blue: Boolean, alpha: Boolean) = GL11C.glColorMask(red, green, blue, alpha)

    // --- [ glCullFace ] ---

    /**
     * Specifies which polygon faces are culled if {@link #GL_CULL_FACE CULL_FACE} is enabled. Front-facing polygons are rasterized if either culling is disabled or the
     * CullFace mode is {@link #GL_BACK BACK} while back-facing polygons are rasterized only if either culling is disabled or the CullFace mode is
     * {@link #GL_FRONT FRONT}. The initial setting of the CullFace mode is {@link #GL_BACK BACK}. Initially, culling is disabled.
     *
     * @param mode the CullFace mode. One of:<br><table><tr><td>{@link #GL_FRONT FRONT}</td><td>{@link #GL_BACK BACK}</td><td>{@link #GL_FRONT_AND_BACK FRONT_AND_BACK}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCullFace">Reference Page</a>
     */
    fun cullFace(mode: CullFaceMode) = GL11C.glCullFace(mode.i)

    // --- [ glDepthFunc ] ---

    /**
     * Specifies the comparison that takes place during the depth buffer test (when {@link #GL_DEPTH_TEST DEPTH_TEST} is enabled).
     *
     * @param func the depth test comparison. One of:<br><table><tr><td>{@link #GL_NEVER NEVER}</td><td>{@link #GL_ALWAYS ALWAYS}</td><td>{@link #GL_LESS LESS}</td><td>{@link #GL_LEQUAL LEQUAL}</td><td>{@link #GL_EQUAL EQUAL}</td><td>{@link #GL_GREATER GREATER}</td><td>{@link #GL_GEQUAL GEQUAL}</td><td>{@link #GL_NOTEQUAL NOTEQUAL}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDepthFunc">Reference Page</a>
     */
    fun depthFunc(func: CompareFunction) = GL11C.glDepthFunc(func.i)

    // --- [ glDepthMask ] ---

    /**
     * Masks the writing of depth values to the depth buffer. In the initial state, the depth buffer is enabled for writing.
     *
     * @param flag whether depth values are written or not.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDepthMask">Reference Page</a>
     */
    fun depthMask(flag: Boolean) = GL11C.glDepthMask(flag)

    // --- [ glDepthRange ] ---

    /**
     * Sets the depth range for all viewports to the same values.
     *
     * @param zNear the near depth range
     * @param zFar  the far depth range
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDepthRange">Reference Page</a>
     */
    fun depthRange(zNear: Double, zFar: Double) = GL11C.glDepthRange(zNear, zFar)

    /**
     * Sets the depth range for all viewports to the same values.
     *
     * @param zNear the near depth range
     * @param zFar  the far depth range
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDepthRange">Reference Page</a>
     */
    fun depthRange(z: Vec2d) = GL11C.glDepthRange(z.x, z.y)

    // --- [ glDrawArrays ] ---

    /**
     * Constructs a sequence of geometric primitives by successively transferring elements for {@code count} vertices. Elements {@code first} through
     * <code>first + count &ndash; 1</code> of each enabled non-instanced array are transferred to the GL.
     *
     * <p>If an array corresponding to an attribute required by a vertex shader is not enabled, then the corresponding element is taken from the current attribute
     * state. If an array is enabled, the corresponding current vertex attribute value is unaffected by the execution of this function.</p>
     *
     * @param mode  the kind of primitives being constructed
     * @param first the first vertex to transfer to the GL
     * @param count the number of vertices after {@code first} to transfer to the GL
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawArrays">Reference Page</a>
     */
    fun drawArrays(mode: BeginMode, first: Int, count: Int) = GL11C.glDrawArrays(mode.i, first, count)

    /**
     * Constructs a sequence of geometric primitives by successively transferring elements for {@code count} vertices. Elements {@code first} through
     * <code>first + count &ndash; 1</code> of each enabled non-instanced array are transferred to the GL.
     *
     * <p>If an array corresponding to an attribute required by a vertex shader is not enabled, then the corresponding element is taken from the current attribute
     * state. If an array is enabled, the corresponding current vertex attribute value is unaffected by the execution of this function.</p>
     *
     * @param mode  the kind of primitives being constructed
     * @param count the number of vertices after {@code first} to transfer to the GL
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawArrays">Reference Page</a>
     */
    fun drawArrays(mode: BeginMode, count: Int) = GL11C.glDrawArrays(mode.i, 0, count)

    // --- [ glDrawBuffer ] ---

    /**
     * Defines the color buffer to which fragment color zero is written.
     *
     * <p>Acceptable values for {@code buf} depend on whether the GL is using the default framebuffer (i.e., {@link GL30#GL_DRAW_FRAMEBUFFER_BINDING DRAW_FRAMEBUFFER_BINDING} is zero), or
     * a framebuffer object (i.e., {@link GL30#GL_DRAW_FRAMEBUFFER_BINDING DRAW_FRAMEBUFFER_BINDING} is non-zero). In the initial state, the GL is bound to the default framebuffer.</p>
     *
     * @param buf the color buffer to draw to. One of:<br><table><tr><td>{@link #GL_NONE NONE}</td><td>{@link #GL_FRONT_LEFT FRONT_LEFT}</td><td>{@link #GL_FRONT_RIGHT FRONT_RIGHT}</td><td>{@link #GL_BACK_LEFT BACK_LEFT}</td><td>{@link #GL_BACK_RIGHT BACK_RIGHT}</td><td>{@link #GL_FRONT FRONT}</td><td>{@link #GL_BACK BACK}</td><td>{@link #GL_LEFT LEFT}</td></tr><tr><td>{@link #GL_RIGHT RIGHT}</td><td>{@link #GL_FRONT_AND_BACK FRONT_AND_BACK}</td><td>{@link GL30#GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td><td>GL30.GL_COLOR_ATTACHMENT[1-15]</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawBuffer">Reference Page</a>
     */
    fun drawBuffer(buf: BufferMode) = GL11C.glDrawBuffer(buf.i)

    // --- [ glDrawElements ] ---

    /**
     * Constructs a sequence of geometric primitives by successively transferring elements for {@code count} vertices to the GL.
     * The i<sup>th</sup> element transferred by {@code DrawElements} will be taken from element {@code indices[i]} (if no element array buffer is bound), or
     * from the element whose index is stored in the currently bound element array buffer at offset {@code indices + i}.
     *
     * @param mode    the kind of primitives being constructed. One of:<br><table><tr><td>{@link #GL_POINTS POINTS}</td><td>{@link #GL_LINE_STRIP LINE_STRIP}</td><td>{@link #GL_LINE_LOOP LINE_LOOP}</td><td>{@link #GL_LINES LINES}</td><td>{@link #GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link #GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link #GL_TRIANGLES TRIANGLES}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
     * @param count   the number of vertices to transfer to the GL
     * @param type    indicates the type of index values in {@code indices}. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param indices the index values
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElements">Reference Page</a>
     */
    fun drawElements(mode: BeginMode, count: Int, type: DataType = UNSIGNED_INT, indices: Long = 0) = GL11C.nglDrawElements(mode.i, count, type.i, indices)

    /**
     * Constructs a sequence of geometric primitives by successively transferring elements for {@code count} vertices to the GL.
     * The i<sup>th</sup> element transferred by {@code DrawElements} will be taken from element {@code indices[i]} (if no element array buffer is bound), or
     * from the element whose index is stored in the currently bound element array buffer at offset {@code indices + i}.
     *
     * @param mode    the kind of primitives being constructed. One of:<br><table><tr><td>{@link #GL_POINTS POINTS}</td><td>{@link #GL_LINE_STRIP LINE_STRIP}</td><td>{@link #GL_LINE_LOOP LINE_LOOP}</td><td>{@link #GL_LINES LINES}</td><td>{@link #GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link #GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link #GL_TRIANGLES TRIANGLES}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
     * @param count   the number of vertices to transfer to the GL
     * @param type    indicates the type of index values in {@code indices}. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param indices the index values
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElements">Reference Page</a>
     */
    fun drawElements(count: Int, type: DataType = UNSIGNED_INT, indices: Long = 0) = GL11C.nglDrawElements(TRIANGLES.i, count, type.i, indices)

    /**
     * Constructs a sequence of geometric primitives by successively transferring elements for {@code count} vertices to the GL.
     * The i<sup>th</sup> element transferred by {@code DrawElements} will be taken from element {@code indices[i]} (if no element array buffer is bound), or
     * from the element whose index is stored in the currently bound element array buffer at offset {@code indices + i}.
     *
     * @param mode    the kind of primitives being constructed. One of:<br><table><tr><td>{@link #GL_POINTS POINTS}</td><td>{@link #GL_LINE_STRIP LINE_STRIP}</td><td>{@link #GL_LINE_LOOP LINE_LOOP}</td><td>{@link #GL_LINES LINES}</td><td>{@link #GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link #GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link #GL_TRIANGLES TRIANGLES}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
     * @param type    indicates the type of index values in {@code indices}. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param indices the index values
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElements">Reference Page</a>
     */
    fun drawElements(mode: BeginMode, type: DataType, indices: ByteBuffer) = GL11C.nglDrawElements(mode.i, indices.rem * type.size, type.i, indices.adr)

    /**
     * Constructs a sequence of geometric primitives by successively transferring elements for {@code count} vertices to the GL.
     * The i<sup>th</sup> element transferred by {@code DrawElements} will be taken from element {@code indices[i]} (if no element array buffer is bound), or
     * from the element whose index is stored in the currently bound element array buffer at offset {@code indices + i}.
     *
     * @param mode    the kind of primitives being constructed. One of:<br><table><tr><td>{@link #GL_POINTS POINTS}</td><td>{@link #GL_LINE_STRIP LINE_STRIP}</td><td>{@link #GL_LINE_LOOP LINE_LOOP}</td><td>{@link #GL_LINES LINES}</td><td>{@link #GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link #GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link #GL_TRIANGLES TRIANGLES}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
     * @param indices the index values
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElements">Reference Page</a>
     */
    fun drawElements(mode: BeginMode, indices: ByteBuffer) = GL11C.nglDrawElements(mode.i, indices.rem, GL11C.GL_UNSIGNED_BYTE, indices.adr)

    /**
     * Constructs a sequence of geometric primitives by successively transferring elements for {@code count} vertices to the GL.
     * The i<sup>th</sup> element transferred by {@code DrawElements} will be taken from element {@code indices[i]} (if no element array buffer is bound), or
     * from the element whose index is stored in the currently bound element array buffer at offset {@code indices + i}.
     *
     * @param mode    the kind of primitives being constructed. One of:<br><table><tr><td>{@link #GL_POINTS POINTS}</td><td>{@link #GL_LINE_STRIP LINE_STRIP}</td><td>{@link #GL_LINE_LOOP LINE_LOOP}</td><td>{@link #GL_LINES LINES}</td><td>{@link #GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link #GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link #GL_TRIANGLES TRIANGLES}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
     * @param indices the index values
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElements">Reference Page</a>
     */
    fun drawElements(mode: BeginMode, indices: ShortBuffer) = GL11C.nglDrawElements(mode.i, indices.rem, GL11C.GL_UNSIGNED_SHORT, indices.adr)

    /**
     * Constructs a sequence of geometric primitives by successively transferring elements for {@code count} vertices to the GL.
     * The i<sup>th</sup> element transferred by {@code DrawElements} will be taken from element {@code indices[i]} (if no element array buffer is bound), or
     * from the element whose index is stored in the currently bound element array buffer at offset {@code indices + i}.
     *
     * @param mode    the kind of primitives being constructed. One of:<br><table><tr><td>{@link #GL_POINTS POINTS}</td><td>{@link #GL_LINE_STRIP LINE_STRIP}</td><td>{@link #GL_LINE_LOOP LINE_LOOP}</td><td>{@link #GL_LINES LINES}</td><td>{@link #GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link #GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link #GL_TRIANGLES TRIANGLES}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
     * @param indices the index values
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElements">Reference Page</a>
     */
    fun drawElements(mode: BeginMode, indices: IntBuffer) = GL11C.nglDrawElements(mode.i, indices.rem, GL11C.GL_UNSIGNED_INT, indices.adr)

    // --- [ glFinish ] ---

    /**
     * Forces all previously issued GL commands to complete. {@code Finish} does not return until all effects from such commands on GL client and server
     * state and the framebuffer are fully realized.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFinish">Reference Page</a>
     */
    fun finish() = GL11C.glFinish()

    // --- [ glFlush ] ---

    /**
     * Causes all previously issued GL commands to complete in finite time (although such commands may still be executing when {@code Flush} returns).
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFlush">Reference Page</a>
     */
    fun flush() = GL11C.glFlush()

    // --- [ glFrontFace ] ---

    /**
     * The first step of polygon rasterization is to determine if the polygon is back-facing or front-facing. This determination is made based on the sign of
     * the (clipped or unclipped) polygon's area computed in window coordinates. The interpretation of the sign of this value is controlled with this function.
     * In the initial state, the front face direction is set to {@link #GL_CCW CCW}.
     *
     * @param dir the front face direction. One of:<br><table><tr><td>{@link #GL_CCW CCW}</td><td>{@link #GL_CW CW}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFrontFace">Reference Page</a>
     */
    fun frontFace(dir: FrontFaceDirection) = GL11C.glFrontFace(dir.i)

    // --- [ glGenTextures ] ---

    /**
     * Returns n previously unused texture names in textures. These names are marked as used, for the purposes of GenTextures only, but they acquire texture
     * state and a dimensionality only when they are first bound, just as if they were unused.
     *
     * @param textures a scalar or buffer in which to place the returned texture names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenTextures">Reference Page</a>
     */
    fun genTextures(textures: GLtextures) = GL11C.nglGenTextures(textures.rem, textures.adr)

    /**
     * Returns n previously unused texture names in textures. These names are marked as used, for the purposes of GenTextures only, but they acquire texture
     * state and a dimensionality only when they are first bound, just as if they were unused.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenTextures">Reference Page</a>
     */
    fun genTexture() = appBuffer.withIntPtr { GL11C.nglGenTextures(1, it) }

    // --- [ glDeleteTextures ] ---

    /**
     * Deletes texture objects. After a texture object is deleted, it has no contents or dimensionality, and its name is again unused. If a texture that is
     * currently bound to any of the target bindings of {@link #glBindTexture BindTexture} is deleted, it is as though {@link #glBindTexture BindTexture} had been executed with the
     * same target and texture zero. Additionally, special care must be taken when deleting a texture if any of the images of the texture are attached to a
     * framebuffer object.
     *
     * <p>Unused names in textures that have been marked as used for the purposes of {@link #glGenTextures GenTextures} are marked as unused again. Unused names in textures are
     * silently ignored, as is the name zero.</p>
     *
     * @param textures contains {@code n} names of texture objects to be deleted
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteTextures">Reference Page</a>
     */
    fun deleteTextures(textures: GLtextures) = GL11C.nglDeleteTextures(textures.rem, textures.adr)

    /**
     * Deletes texture objects. After a texture object is deleted, it has no contents or dimensionality, and its name is again unused. If a texture that is
     * currently bound to any of the target bindings of {@link #glBindTexture BindTexture} is deleted, it is as though {@link #glBindTexture BindTexture} had been executed with the
     * same target and texture zero. Additionally, special care must be taken when deleting a texture if any of the images of the texture are attached to a
     * framebuffer object.
     *
     * <p>Unused names in textures that have been marked as used for the purposes of {@link #glGenTextures GenTextures} are marked as unused again. Unused names in textures are
     * silently ignored, as is the name zero.</p>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteTextures">Reference Page</a>
     */
    fun deleteTexture(texture: GLtexture) = appBuffer.withIntPtr(texture.i) { GL11C.nglDeleteTextures(1, it) }

    // --- [ glGetError ] ---

    /**
     * Returns error information. Each detectable error is assigned a numeric code. When an error is detected, a flag is set and the code is recorded. Further
     * errors, if they occur, do not affect this recorded code. When {@code GetError} is called, the code is returned and the flag is cleared, so that a
     * further error will again record its code. If a call to {@code GetError} returns {@link #GL_NO_ERROR NO_ERROR}, then there has been no detectable error since
     * the last call to {@code GetError} (or since the GL was initialized).
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetError">Reference Page</a>
     */
    val error: ErrorCode
        get() = ErrorCode(GL11C.glGetError())

    // --- [ glGetTexImage ] ---

    /**
     * Obtains texture images.
     *
     * @param tex    the texture (or texture face) to be obtained. One of:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td></tr><tr><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_X TEXTURE_CUBE_MAP_POSITIVE_X}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_X TEXTURE_CUBE_MAP_NEGATIVE_X}</td></tr><tr><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Y TEXTURE_CUBE_MAP_POSITIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Y TEXTURE_CUBE_MAP_NEGATIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Z TEXTURE_CUBE_MAP_POSITIVE_Z}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Z TEXTURE_CUBE_MAP_NEGATIVE_Z}</td></tr></table>
     * @param level  the level-of-detail number
     * @param format the pixel format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
     * @param type   the pixel type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
     * @param pixels the buffer in which to place the returned data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexImage">Reference Page</a>
     */
    fun getTexImage(tex: TextureTarget, level: Int, format: GliExternalFormat, type: GliTypeFormat, pixels: ByteBuffer) = GL11C.nglGetTexImage(tex.i, level, format, type, pixels.adr)

    // TODO getTexImage: ByteBuffer?
    /**
     * Obtains texture images.
     *
     * @param tex    the texture (or texture face) to be obtained. One of:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td></tr><tr><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_X TEXTURE_CUBE_MAP_POSITIVE_X}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_X TEXTURE_CUBE_MAP_NEGATIVE_X}</td></tr><tr><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Y TEXTURE_CUBE_MAP_POSITIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Y TEXTURE_CUBE_MAP_NEGATIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Z TEXTURE_CUBE_MAP_POSITIVE_Z}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Z TEXTURE_CUBE_MAP_NEGATIVE_Z}</td></tr></table>
     * @param level  the level-of-detail number
     * @param format the pixel format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
     * @param type   the pixel type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
     * @param pixels the buffer in which to place the returned data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexImage">Reference Page</a>
     */
    fun getTexImage(tex: TextureTarget, level: Int, format: GliExternalFormat, type: GliTypeFormat, pixels: Pointer) = GL11C.nglGetTexImage(tex.i, level, format, type, pixels.L)

    /**
     * Obtains texture images.
     *
     * @param tex    the texture (or texture face) to be obtained. One of:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td></tr><tr><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_X TEXTURE_CUBE_MAP_POSITIVE_X}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_X TEXTURE_CUBE_MAP_NEGATIVE_X}</td></tr><tr><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Y TEXTURE_CUBE_MAP_POSITIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Y TEXTURE_CUBE_MAP_NEGATIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Z TEXTURE_CUBE_MAP_POSITIVE_Z}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Z TEXTURE_CUBE_MAP_NEGATIVE_Z}</td></tr></table>
     * @param level  the level-of-detail number
     * @param format the pixel format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
     * @param type   the pixel type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
     * @param pixels the buffer in which to place the returned data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexImage">Reference Page</a>
     */
    fun getTexImage(tex: TextureTarget, level: Int, format: GliExternalFormat, type: GliTypeFormat, pixels: ShortBuffer) = GL11C.nglGetTexImage(tex.i, level, format, type, pixels.adr)

    /**
     * Obtains texture images.
     *
     * @param tex    the texture (or texture face) to be obtained. One of:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td></tr><tr><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_X TEXTURE_CUBE_MAP_POSITIVE_X}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_X TEXTURE_CUBE_MAP_NEGATIVE_X}</td></tr><tr><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Y TEXTURE_CUBE_MAP_POSITIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Y TEXTURE_CUBE_MAP_NEGATIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Z TEXTURE_CUBE_MAP_POSITIVE_Z}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Z TEXTURE_CUBE_MAP_NEGATIVE_Z}</td></tr></table>
     * @param level  the level-of-detail number
     * @param format the pixel format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
     * @param type   the pixel type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
     * @param pixels the buffer in which to place the returned data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexImage">Reference Page</a>
     */
    fun getTexImage(tex: TextureTarget, level: Int, format: GliExternalFormat, type: GliTypeFormat, pixels: IntBuffer) = GL11C.nglGetTexImage(tex.i, level, format, type, pixels.adr)

    /**
     * Obtains texture images.
     *
     * @param tex    the texture (or texture face) to be obtained. One of:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td></tr><tr><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_X TEXTURE_CUBE_MAP_POSITIVE_X}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_X TEXTURE_CUBE_MAP_NEGATIVE_X}</td></tr><tr><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Y TEXTURE_CUBE_MAP_POSITIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Y TEXTURE_CUBE_MAP_NEGATIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Z TEXTURE_CUBE_MAP_POSITIVE_Z}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Z TEXTURE_CUBE_MAP_NEGATIVE_Z}</td></tr></table>
     * @param level  the level-of-detail number
     * @param format the pixel format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
     * @param type   the pixel type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
     * @param pixels the buffer in which to place the returned data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexImage">Reference Page</a>
     */
    fun getTexImage(tex: TextureTarget, level: Int, format: GliExternalFormat, type: GliTypeFormat, pixels: LongBuffer) = GL11C.nglGetTexImage(tex.i, level, format, type, pixels.adr)

    /**
     * Obtains texture images.
     *
     * @param tex    the texture (or texture face) to be obtained. One of:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td></tr><tr><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_X TEXTURE_CUBE_MAP_POSITIVE_X}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_X TEXTURE_CUBE_MAP_NEGATIVE_X}</td></tr><tr><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Y TEXTURE_CUBE_MAP_POSITIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Y TEXTURE_CUBE_MAP_NEGATIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Z TEXTURE_CUBE_MAP_POSITIVE_Z}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Z TEXTURE_CUBE_MAP_NEGATIVE_Z}</td></tr></table>
     * @param level  the level-of-detail number
     * @param format the pixel format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
     * @param type   the pixel type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
     * @param pixels the buffer in which to place the returned data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexImage">Reference Page</a>
     */
    fun getTexImage(tex: TextureTarget, level: Int, format: GliExternalFormat, type: GliTypeFormat, pixels: FloatBuffer) = GL11C.nglGetTexImage(tex.i, level, format, type, pixels.adr)

    /**
     * Obtains texture images.
     *
     * @param tex    the texture (or texture face) to be obtained. One of:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td></tr><tr><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_X TEXTURE_CUBE_MAP_POSITIVE_X}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_X TEXTURE_CUBE_MAP_NEGATIVE_X}</td></tr><tr><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Y TEXTURE_CUBE_MAP_POSITIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Y TEXTURE_CUBE_MAP_NEGATIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Z TEXTURE_CUBE_MAP_POSITIVE_Z}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Z TEXTURE_CUBE_MAP_NEGATIVE_Z}</td></tr></table>
     * @param level  the level-of-detail number
     * @param format the pixel format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
     * @param type   the pixel type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
     * @param pixels the buffer in which to place the returned data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexImage">Reference Page</a>
     */
    fun getTexImage(tex: TextureTarget, level: Int, format: GliExternalFormat, type: GliTypeFormat, pixels: DoubleBuffer) = GL11C.nglGetTexImage(tex.i, level, format, type, pixels.adr)

    // --- [ glGetTexLevelParameteriv ] ---

    /**
     * Places integer information about texture image parameter {@code pname} for level-of-detail {@code level} of the specified {@code target} into {@code params}.
     *
     * @param target the texture image target. One of:<br><table><tr><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link #GL_PROXY_TEXTURE_2D PROXY_TEXTURE_2D}</td><td>{@link GL30#GL_PROXY_TEXTURE_1D_ARRAY PROXY_TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_PROXY_TEXTURE_RECTANGLE PROXY_TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_PROXY_TEXTURE_CUBE_MAP PROXY_TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL40#GL_TEXTURE_CUBE_MAP_ARRAY TEXTURE_CUBE_MAP_ARRAY}</td></tr><tr><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE TEXTURE_2D_MULTISAMPLE}</td><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE_ARRAY TEXTURE_2D_MULTISAMPLE_ARRAY}</td><td>{@link #GL_PROXY_TEXTURE_1D PROXY_TEXTURE_1D}</td><td>{@link GL12#GL_PROXY_TEXTURE_3D PROXY_TEXTURE_3D}</td></tr><tr><td>{@link GL30#GL_PROXY_TEXTURE_2D_ARRAY PROXY_TEXTURE_2D_ARRAY}</td><td>{@link GL40#GL_PROXY_TEXTURE_CUBE_MAP_ARRAY PROXY_TEXTURE_CUBE_MAP_ARRAY}</td><td>{@link GL32#GL_PROXY_TEXTURE_2D_MULTISAMPLE PROXY_TEXTURE_2D_MULTISAMPLE}</td><td>{@link GL32#GL_PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY}</td></tr></table>
     * @param level  the level-of-detail number
     * @param pname  the parameter to query. One of:<br><table><tr><td>{@link #GL_TEXTURE_WIDTH TEXTURE_WIDTH}</td><td>{@link #GL_TEXTURE_HEIGHT TEXTURE_HEIGHT}</td><td>{@link GL12#GL_TEXTURE_DEPTH TEXTURE_DEPTH}</td><td>{@link GL32#GL_TEXTURE_SAMPLES TEXTURE_SAMPLES}</td></tr><tr><td>{@link GL32#GL_TEXTURE_FIXED_SAMPLE_LOCATIONS TEXTURE_FIXED_SAMPLE_LOCATIONS}</td><td>{@link #GL_TEXTURE_INTERNAL_FORMAT TEXTURE_INTERNAL_FORMAT}</td><td>{@link #GL_TEXTURE_RED_SIZE TEXTURE_RED_SIZE}</td><td>{@link #GL_TEXTURE_GREEN_SIZE TEXTURE_GREEN_SIZE}</td></tr><tr><td>{@link #GL_TEXTURE_BLUE_SIZE TEXTURE_BLUE_SIZE}</td><td>{@link #GL_TEXTURE_ALPHA_SIZE TEXTURE_ALPHA_SIZE}</td><td>{@link GL14#GL_TEXTURE_DEPTH_SIZE TEXTURE_DEPTH_SIZE}</td><td>{@link GL30#GL_TEXTURE_STENCIL_SIZE TEXTURE_STENCIL_SIZE}</td></tr><tr><td>{@link GL30#GL_TEXTURE_SHARED_SIZE TEXTURE_SHARED_SIZE}</td><td>{@link GL30#GL_TEXTURE_ALPHA_TYPE TEXTURE_ALPHA_TYPE}</td><td>{@link GL30#GL_TEXTURE_DEPTH_TYPE TEXTURE_DEPTH_TYPE}</td><td>{@link GL13#GL_TEXTURE_COMPRESSED TEXTURE_COMPRESSED}</td></tr><tr><td>{@link GL13#GL_TEXTURE_COMPRESSED_IMAGE_SIZE TEXTURE_COMPRESSED_IMAGE_SIZE}</td><td>{@link GL31#GL_TEXTURE_BUFFER_DATA_STORE_BINDING TEXTURE_BUFFER_DATA_STORE_BINDING}</td><td>{@link GL43#GL_TEXTURE_BUFFER_OFFSET TEXTURE_BUFFER_OFFSET}</td><td>{@link GL43#GL_TEXTURE_BUFFER_SIZE TEXTURE_BUFFER_SIZE}</td></tr></table>
     * @param params a scalar or buffer in which to place the returned data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexLevelParameter">Reference Page</a>
     */
    fun getTexLevelParameteriv(target: TextureTarget, level: Int, @NativeType("GLenum") pname: Int, @NativeType("GLint *") params: IntBuffer) {
        if (CHECKS) {
            check(params, 1);
        }
        GL11.nglGetTexLevelParameteriv(target.i, level, pname, memAddress(params));
    }

    /**
     * Places integer information about texture image parameter {@code pname} for level-of-detail {@code level} of the specified {@code target} into {@code params}.
     *
     * @param target the texture image target. One of:<br><table><tr><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link #GL_PROXY_TEXTURE_2D PROXY_TEXTURE_2D}</td><td>{@link GL30#GL_PROXY_TEXTURE_1D_ARRAY PROXY_TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_PROXY_TEXTURE_RECTANGLE PROXY_TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_PROXY_TEXTURE_CUBE_MAP PROXY_TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL40#GL_TEXTURE_CUBE_MAP_ARRAY TEXTURE_CUBE_MAP_ARRAY}</td></tr><tr><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE TEXTURE_2D_MULTISAMPLE}</td><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE_ARRAY TEXTURE_2D_MULTISAMPLE_ARRAY}</td><td>{@link #GL_PROXY_TEXTURE_1D PROXY_TEXTURE_1D}</td><td>{@link GL12#GL_PROXY_TEXTURE_3D PROXY_TEXTURE_3D}</td></tr><tr><td>{@link GL30#GL_PROXY_TEXTURE_2D_ARRAY PROXY_TEXTURE_2D_ARRAY}</td><td>{@link GL40#GL_PROXY_TEXTURE_CUBE_MAP_ARRAY PROXY_TEXTURE_CUBE_MAP_ARRAY}</td><td>{@link GL32#GL_PROXY_TEXTURE_2D_MULTISAMPLE PROXY_TEXTURE_2D_MULTISAMPLE}</td><td>{@link GL32#GL_PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY}</td></tr></table>
     * @param level  the level-of-detail number
     * @param pname  the parameter to query. One of:<br><table><tr><td>{@link #GL_TEXTURE_WIDTH TEXTURE_WIDTH}</td><td>{@link #GL_TEXTURE_HEIGHT TEXTURE_HEIGHT}</td><td>{@link GL12#GL_TEXTURE_DEPTH TEXTURE_DEPTH}</td><td>{@link GL32#GL_TEXTURE_SAMPLES TEXTURE_SAMPLES}</td></tr><tr><td>{@link GL32#GL_TEXTURE_FIXED_SAMPLE_LOCATIONS TEXTURE_FIXED_SAMPLE_LOCATIONS}</td><td>{@link #GL_TEXTURE_INTERNAL_FORMAT TEXTURE_INTERNAL_FORMAT}</td><td>{@link #GL_TEXTURE_RED_SIZE TEXTURE_RED_SIZE}</td><td>{@link #GL_TEXTURE_GREEN_SIZE TEXTURE_GREEN_SIZE}</td></tr><tr><td>{@link #GL_TEXTURE_BLUE_SIZE TEXTURE_BLUE_SIZE}</td><td>{@link #GL_TEXTURE_ALPHA_SIZE TEXTURE_ALPHA_SIZE}</td><td>{@link GL14#GL_TEXTURE_DEPTH_SIZE TEXTURE_DEPTH_SIZE}</td><td>{@link GL30#GL_TEXTURE_STENCIL_SIZE TEXTURE_STENCIL_SIZE}</td></tr><tr><td>{@link GL30#GL_TEXTURE_SHARED_SIZE TEXTURE_SHARED_SIZE}</td><td>{@link GL30#GL_TEXTURE_ALPHA_TYPE TEXTURE_ALPHA_TYPE}</td><td>{@link GL30#GL_TEXTURE_DEPTH_TYPE TEXTURE_DEPTH_TYPE}</td><td>{@link GL13#GL_TEXTURE_COMPRESSED TEXTURE_COMPRESSED}</td></tr><tr><td>{@link GL13#GL_TEXTURE_COMPRESSED_IMAGE_SIZE TEXTURE_COMPRESSED_IMAGE_SIZE}</td><td>{@link GL31#GL_TEXTURE_BUFFER_DATA_STORE_BINDING TEXTURE_BUFFER_DATA_STORE_BINDING}</td><td>{@link GL43#GL_TEXTURE_BUFFER_OFFSET TEXTURE_BUFFER_OFFSET}</td><td>{@link GL43#GL_TEXTURE_BUFFER_SIZE TEXTURE_BUFFER_SIZE}</td></tr></table>
     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexLevelParameter">Reference Page</a>
//     */
//    @NativeType("void")
//    public static int glGetTexLevelParameteri(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLenum") int pname) {
//        MemoryStack stack = stackGet(); int stackPointer = stack.getPointer();
//        try {
//            IntBuffer params = stack.callocInt(1);
//            nglGetTexLevelParameteriv(target, level, pname, memAddress(params));
//            return params.get(0);
//        } finally {
//            stack.setPointer(stackPointer);
//        }
//    }
//
//    // --- [ glGetTexLevelParameterfv ] ---
//
//    /** Unsafe version of: {@link #glGetTexLevelParameterfv GetTexLevelParameterfv} */
//    public static native void nglGetTexLevelParameterfv(int target, int level, int pname, long params);
//
//    /**
//     * Float version of {@link #glGetTexLevelParameteriv GetTexLevelParameteriv}.
//     *
//     * @param target the texture image target
//     * @param level  the level-of-detail number
//     * @param pname  the parameter to query
//     * @param params a scalar or buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexLevelParameter">Reference Page</a>
//     */
//    public static void glGetTexLevelParameterfv(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLenum") int pname, @NativeType("GLfloat *") FloatBuffer params) {
//        if (CHECKS) {
//            check(params, 1);
//        }
//        nglGetTexLevelParameterfv(target, level, pname, memAddress(params));
//    }
//
//    /**
//     * Float version of {@link #glGetTexLevelParameteriv GetTexLevelParameteriv}.
//     *
//     * @param target the texture image target
//     * @param level  the level-of-detail number
//     * @param pname  the parameter to query
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexLevelParameter">Reference Page</a>
//     */
//    @NativeType("void")
//    public static float glGetTexLevelParameterf(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLenum") int pname) {
//        MemoryStack stack = stackGet(); int stackPointer = stack.getPointer();
//        try {
//            FloatBuffer params = stack.callocFloat(1);
//            nglGetTexLevelParameterfv(target, level, pname, memAddress(params));
//            return params.get(0);
//        } finally {
//            stack.setPointer(stackPointer);
//        }
//    }
//
//    // --- [ glGetTexParameteriv ] ---
//
//    /** Unsafe version of: {@link #glGetTexParameteriv GetTexParameteriv} */
//    public static native void nglGetTexParameteriv(int target, int pname, long params);
//
//    /**
//     * Place integer information about texture parameter {@code pname} for the specified {@code target} into {@code params}.
//     *
//     * @param target the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td></tr><tr><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td><td>{@link GL40#GL_TEXTURE_CUBE_MAP_ARRAY TEXTURE_CUBE_MAP_ARRAY}</td></tr><tr><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE TEXTURE_2D_MULTISAMPLE}</td><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE_ARRAY TEXTURE_2D_MULTISAMPLE_ARRAY}</td></tr></table>
//     * @param pname  the parameter to query. One of:<br><table><tr><td>{@link GL12#GL_TEXTURE_BASE_LEVEL TEXTURE_BASE_LEVEL}</td><td>{@link #GL_TEXTURE_BORDER_COLOR TEXTURE_BORDER_COLOR}</td><td>{@link GL14#GL_TEXTURE_COMPARE_MODE TEXTURE_COMPARE_MODE}</td><td>{@link GL14#GL_TEXTURE_COMPARE_FUNC TEXTURE_COMPARE_FUNC}</td></tr><tr><td>{@link GL14#GL_TEXTURE_LOD_BIAS TEXTURE_LOD_BIAS}</td><td>{@link #GL_TEXTURE_MAG_FILTER TEXTURE_MAG_FILTER}</td><td>{@link GL12#GL_TEXTURE_MAX_LEVEL TEXTURE_MAX_LEVEL}</td><td>{@link GL12#GL_TEXTURE_MAX_LOD TEXTURE_MAX_LOD}</td></tr><tr><td>{@link #GL_TEXTURE_MIN_FILTER TEXTURE_MIN_FILTER}</td><td>{@link GL12#GL_TEXTURE_MIN_LOD TEXTURE_MIN_LOD}</td><td>{@link GL33#GL_TEXTURE_SWIZZLE_R TEXTURE_SWIZZLE_R}</td><td>{@link GL33#GL_TEXTURE_SWIZZLE_G TEXTURE_SWIZZLE_G}</td></tr><tr><td>{@link GL33#GL_TEXTURE_SWIZZLE_B TEXTURE_SWIZZLE_B}</td><td>{@link GL33#GL_TEXTURE_SWIZZLE_A TEXTURE_SWIZZLE_A}</td><td>{@link GL33#GL_TEXTURE_SWIZZLE_RGBA TEXTURE_SWIZZLE_RGBA}</td><td>{@link #GL_TEXTURE_WRAP_S TEXTURE_WRAP_S}</td></tr><tr><td>{@link #GL_TEXTURE_WRAP_T TEXTURE_WRAP_T}</td><td>{@link GL12#GL_TEXTURE_WRAP_R TEXTURE_WRAP_R}</td><td>{@link GL14#GL_DEPTH_TEXTURE_MODE DEPTH_TEXTURE_MODE}</td><td>{@link GL14#GL_GENERATE_MIPMAP GENERATE_MIPMAP}</td></tr><tr><td>{@link GL42#GL_IMAGE_FORMAT_COMPATIBILITY_TYPE IMAGE_FORMAT_COMPATIBILITY_TYPE}</td><td>{@link GL42#GL_TEXTURE_IMMUTABLE_FORMAT TEXTURE_IMMUTABLE_FORMAT}</td><td>{@link GL43#GL_TEXTURE_IMMUTABLE_LEVELS TEXTURE_IMMUTABLE_LEVELS}</td><td>{@link GL43#GL_TEXTURE_VIEW_MIN_LEVEL TEXTURE_VIEW_MIN_LEVEL}</td></tr><tr><td>{@link GL43#GL_TEXTURE_VIEW_NUM_LEVELS TEXTURE_VIEW_NUM_LEVELS}</td><td>{@link GL43#GL_TEXTURE_VIEW_MIN_LAYER TEXTURE_VIEW_MIN_LAYER}</td><td>{@link GL43#GL_TEXTURE_VIEW_NUM_LAYERS TEXTURE_VIEW_NUM_LAYERS}</td></tr></table>
//     * @param params a scalar or buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexParameter">Reference Page</a>
//     */
//    public static void glGetTexParameteriv(@NativeType("GLenum") int target, @NativeType("GLenum") int pname, @NativeType("GLint *") IntBuffer params) {
//        if (CHECKS) {
//            check(params, 1);
//        }
//        nglGetTexParameteriv(target, pname, memAddress(params));
//    }
//
//    /**
//     * Place integer information about texture parameter {@code pname} for the specified {@code target} into {@code params}.
//     *
//     * @param target the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td></tr><tr><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td><td>{@link GL40#GL_TEXTURE_CUBE_MAP_ARRAY TEXTURE_CUBE_MAP_ARRAY}</td></tr><tr><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE TEXTURE_2D_MULTISAMPLE}</td><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE_ARRAY TEXTURE_2D_MULTISAMPLE_ARRAY}</td></tr></table>
//     * @param pname  the parameter to query. One of:<br><table><tr><td>{@link GL12#GL_TEXTURE_BASE_LEVEL TEXTURE_BASE_LEVEL}</td><td>{@link #GL_TEXTURE_BORDER_COLOR TEXTURE_BORDER_COLOR}</td><td>{@link GL14#GL_TEXTURE_COMPARE_MODE TEXTURE_COMPARE_MODE}</td><td>{@link GL14#GL_TEXTURE_COMPARE_FUNC TEXTURE_COMPARE_FUNC}</td></tr><tr><td>{@link GL14#GL_TEXTURE_LOD_BIAS TEXTURE_LOD_BIAS}</td><td>{@link #GL_TEXTURE_MAG_FILTER TEXTURE_MAG_FILTER}</td><td>{@link GL12#GL_TEXTURE_MAX_LEVEL TEXTURE_MAX_LEVEL}</td><td>{@link GL12#GL_TEXTURE_MAX_LOD TEXTURE_MAX_LOD}</td></tr><tr><td>{@link #GL_TEXTURE_MIN_FILTER TEXTURE_MIN_FILTER}</td><td>{@link GL12#GL_TEXTURE_MIN_LOD TEXTURE_MIN_LOD}</td><td>{@link GL33#GL_TEXTURE_SWIZZLE_R TEXTURE_SWIZZLE_R}</td><td>{@link GL33#GL_TEXTURE_SWIZZLE_G TEXTURE_SWIZZLE_G}</td></tr><tr><td>{@link GL33#GL_TEXTURE_SWIZZLE_B TEXTURE_SWIZZLE_B}</td><td>{@link GL33#GL_TEXTURE_SWIZZLE_A TEXTURE_SWIZZLE_A}</td><td>{@link GL33#GL_TEXTURE_SWIZZLE_RGBA TEXTURE_SWIZZLE_RGBA}</td><td>{@link #GL_TEXTURE_WRAP_S TEXTURE_WRAP_S}</td></tr><tr><td>{@link #GL_TEXTURE_WRAP_T TEXTURE_WRAP_T}</td><td>{@link GL12#GL_TEXTURE_WRAP_R TEXTURE_WRAP_R}</td><td>{@link GL14#GL_DEPTH_TEXTURE_MODE DEPTH_TEXTURE_MODE}</td><td>{@link GL14#GL_GENERATE_MIPMAP GENERATE_MIPMAP}</td></tr><tr><td>{@link GL42#GL_IMAGE_FORMAT_COMPATIBILITY_TYPE IMAGE_FORMAT_COMPATIBILITY_TYPE}</td><td>{@link GL42#GL_TEXTURE_IMMUTABLE_FORMAT TEXTURE_IMMUTABLE_FORMAT}</td><td>{@link GL43#GL_TEXTURE_IMMUTABLE_LEVELS TEXTURE_IMMUTABLE_LEVELS}</td><td>{@link GL43#GL_TEXTURE_VIEW_MIN_LEVEL TEXTURE_VIEW_MIN_LEVEL}</td></tr><tr><td>{@link GL43#GL_TEXTURE_VIEW_NUM_LEVELS TEXTURE_VIEW_NUM_LEVELS}</td><td>{@link GL43#GL_TEXTURE_VIEW_MIN_LAYER TEXTURE_VIEW_MIN_LAYER}</td><td>{@link GL43#GL_TEXTURE_VIEW_NUM_LAYERS TEXTURE_VIEW_NUM_LAYERS}</td></tr></table>
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexParameter">Reference Page</a>
//     */
//    @NativeType("void")
//    public static int glGetTexParameteri(@NativeType("GLenum") int target, @NativeType("GLenum") int pname) {
//        MemoryStack stack = stackGet(); int stackPointer = stack.getPointer();
//        try {
//            IntBuffer params = stack.callocInt(1);
//            nglGetTexParameteriv(target, pname, memAddress(params));
//            return params.get(0);
//        } finally {
//            stack.setPointer(stackPointer);
//        }
//    }
//
//    // --- [ glGetTexParameterfv ] ---
//
//    /** Unsafe version of: {@link #glGetTexParameterfv GetTexParameterfv} */
//    public static native void nglGetTexParameterfv(int target, int pname, long params);
//
//    /**
//     * Float version of {@link #glGetTexParameteriv GetTexParameteriv}.
//     *
//     * @param target the texture target
//     * @param pname  the parameter to query
//     * @param params a scalar or buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexParameter">Reference Page</a>
//     */
//    public static void glGetTexParameterfv(@NativeType("GLenum") int target, @NativeType("GLenum") int pname, @NativeType("GLfloat *") FloatBuffer params) {
//        if (CHECKS) {
//            check(params, 1);
//        }
//        nglGetTexParameterfv(target, pname, memAddress(params));
//    }
//
//    /**
//     * Float version of {@link #glGetTexParameteriv GetTexParameteriv}.
//     *
//     * @param target the texture target
//     * @param pname  the parameter to query
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexParameter">Reference Page</a>
//     */
//    @NativeType("void")
//    public static float glGetTexParameterf(@NativeType("GLenum") int target, @NativeType("GLenum") int pname) {
//        MemoryStack stack = stackGet(); int stackPointer = stack.getPointer();
//        try {
//            FloatBuffer params = stack.callocFloat(1);
//            nglGetTexParameterfv(target, pname, memAddress(params));
//            return params.get(0);
//        } finally {
//            stack.setPointer(stackPointer);
//        }
//    }
//
//    // --- [ glHint ] ---
//
//    /**
//     * Certain aspects of GL behavior, when there is room for variation, may be controlled with this function. The initial value for all hints is
//     * {@link #GL_DONT_CARE DONT_CARE}.
//     *
//     * @param target the behavior to control. One of:<br><table><tr><td>{@link #GL_LINE_SMOOTH_HINT LINE_SMOOTH_HINT}</td><td>{@link #GL_POLYGON_SMOOTH_HINT POLYGON_SMOOTH_HINT}</td><td>{@link GL13#GL_TEXTURE_COMPRESSION_HINT TEXTURE_COMPRESSION_HINT}</td></tr><tr><td>{@link GL20#GL_FRAGMENT_SHADER_DERIVATIVE_HINT FRAGMENT_SHADER_DERIVATIVE_HINT}</td></tr></table>
//     * @param hint   the behavior hint. One of:<br><table><tr><td>{@link #GL_FASTEST FASTEST}</td><td>{@link #GL_NICEST NICEST}</td><td>{@link #GL_DONT_CARE DONT_CARE}</td></tr></table>
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glHint">Reference Page</a>
//     */
//    public static native void glHint(@NativeType("GLenum") int target, @NativeType("GLenum") int hint);
//
//    // --- [ glIsEnabled ] ---
//
//    /**
//     * Determines if {@code cap} is currently enabled (as with {@link #glEnable Enable}) or disabled.
//     *
//     * @param cap the enable state to query
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glIsEnabled">Reference Page</a>
//     */
//    @NativeType("GLboolean")
//    public static native boolean glIsEnabled(@NativeType("GLenum") int cap);
//
//    // --- [ glIsTexture ] ---
//
//    /**
//     * Returns true if {@code texture} is the name of a texture object.
//     *
//     * @param texture the texture name to query
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glIsTexture">Reference Page</a>
//     */
//    @NativeType("GLboolean")
//    public static native boolean glIsTexture(@NativeType("GLuint") int texture);
//
//    // --- [ glLineWidth ] ---
//
//    /**
//     * Sets the width of rasterized line segments. The default width is 1.0.
//     *
//     * @param width the line width
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glLineWidth">Reference Page</a>
//     */
//    public static native void glLineWidth(@NativeType("GLfloat") float width);
//
//    // --- [ glLogicOp ] ---
//
//    /**
//     * Sets the logical framebuffer operation.
//     *
//     * @param op the operation to set. One of:<br><table><tr><td>{@link #GL_CLEAR CLEAR}</td><td>{@link #GL_AND AND}</td><td>{@link #GL_AND_REVERSE AND_REVERSE}</td><td>{@link #GL_COPY COPY}</td><td>{@link #GL_AND_INVERTED AND_INVERTED}</td><td>{@link #GL_NOOP NOOP}</td><td>{@link #GL_XOR XOR}</td><td>{@link #GL_OR OR}</td><td>{@link #GL_NOR NOR}</td><td>{@link #GL_EQUIV EQUIV}</td><td>{@link #GL_INVERT INVERT}</td><td>{@link #GL_OR_REVERSE OR_REVERSE}</td><td>{@link #GL_COPY_INVERTED COPY_INVERTED}</td></tr><tr><td>{@link #GL_OR_INVERTED OR_INVERTED}</td><td>{@link #GL_NAND NAND}</td><td>{@link #GL_SET SET}</td></tr></table>
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glLogicOp">Reference Page</a>
//     */
//    public static native void glLogicOp(@NativeType("GLenum") int op);
//
//    // --- [ glPixelStorei ] ---
//
//    /**
//     * Sets the integer value of a pixel store parameter.
//     *
//     * @param pname the pixel store parameter to set. One of:<br><table><tr><td>{@link #GL_UNPACK_SWAP_BYTES UNPACK_SWAP_BYTES}</td><td>{@link #GL_UNPACK_LSB_FIRST UNPACK_LSB_FIRST}</td><td>{@link #GL_UNPACK_ROW_LENGTH UNPACK_ROW_LENGTH}</td></tr><tr><td>{@link #GL_UNPACK_SKIP_ROWS UNPACK_SKIP_ROWS}</td><td>{@link #GL_UNPACK_SKIP_PIXELS UNPACK_SKIP_PIXELS}</td><td>{@link #GL_UNPACK_ALIGNMENT UNPACK_ALIGNMENT}</td></tr><tr><td>{@link GL12#GL_UNPACK_IMAGE_HEIGHT UNPACK_IMAGE_HEIGHT}</td><td>{@link GL12#GL_UNPACK_SKIP_IMAGES UNPACK_SKIP_IMAGES}</td><td>{@link GL42#GL_UNPACK_COMPRESSED_BLOCK_WIDTH UNPACK_COMPRESSED_BLOCK_WIDTH}</td></tr><tr><td>{@link GL42#GL_UNPACK_COMPRESSED_BLOCK_HEIGHT UNPACK_COMPRESSED_BLOCK_HEIGHT}</td><td>{@link GL42#GL_UNPACK_COMPRESSED_BLOCK_DEPTH UNPACK_COMPRESSED_BLOCK_DEPTH}</td><td>{@link GL42#GL_UNPACK_COMPRESSED_BLOCK_SIZE UNPACK_COMPRESSED_BLOCK_SIZE}</td></tr></table>
//     * @param param the parameter value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glPixelStorei">Reference Page</a>
//     */
//    public static native void glPixelStorei(@NativeType("GLenum") int pname, @NativeType("GLint") int param);
//
//    // --- [ glPixelStoref ] ---
//
//    /**
//     * Float version of {@link #glPixelStorei PixelStorei}.
//     *
//     * @param pname the pixel store parameter to set
//     * @param param the parameter value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glPixelStoref">Reference Page</a>
//     */
//    public static native void glPixelStoref(@NativeType("GLenum") int pname, @NativeType("GLfloat") float param);
//
//    // --- [ glPointSize ] ---
//
//    /**
//     * Controls the rasterization of points if no vertex, tessellation control, tessellation evaluation, or geometry shader is active. The default point size is 1.0.
//     *
//     * @param size the request size of a point
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glPointSize">Reference Page</a>
//     */
//    public static native void glPointSize(@NativeType("GLfloat") float size);
//
//    // --- [ glPolygonMode ] ---
//
//    /**
//     * Controls the interpretation of polygons for rasterization.
//     *
//     * <p>{@link #GL_FILL FILL} is the default mode of polygon rasterization. Note that these modes affect only the final rasterization of polygons: in particular, a
//     * polygon's vertices are lit, and the polygon is clipped and possibly culled before these modes are applied. Polygon antialiasing applies only to the
//     * {@link #GL_FILL FILL} state of PolygonMode. For {@link #GL_POINT POINT} or {@link #GL_LINE LINE}, point antialiasing or line segment antialiasing, respectively, apply.</p>
//     *
//     * @param face the face for which to set the rasterizing method. One of:<br><table><tr><td>{@link #GL_FRONT FRONT}</td><td>{@link #GL_BACK BACK}</td><td>{@link #GL_FRONT_AND_BACK FRONT_AND_BACK}</td></tr></table>
//     * @param mode the rasterization mode. One of:<br><table><tr><td>{@link #GL_POINT POINT}</td><td>{@link #GL_LINE LINE}</td><td>{@link #GL_FILL FILL}</td></tr></table>
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glPolygonMode">Reference Page</a>
//     */
//    public static native void glPolygonMode(@NativeType("GLenum") int face, @NativeType("GLenum") int mode);
//
//    // --- [ glPolygonOffset ] ---
//
//    /**
//     * The depth values of all fragments generated by the rasterization of a polygon may be offset by a single value that is computed for that polygon. This
//     * function determines that value.
//     *
//     * <p>{@code factor} scales the maximum depth slope of the polygon, and {@code units} scales an implementation-dependent constant that relates to the usable
//     * resolution of the depth buffer. The resulting values are summed to produce the polygon offset value.</p>
//     *
//     * @param factor the maximum depth slope factor
//     * @param units  the constant scale
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glPolygonOffset">Reference Page</a>
//     */
//    public static native void glPolygonOffset(@NativeType("GLfloat") float factor, @NativeType("GLfloat") float units);
//
//    // --- [ glReadBuffer ] ---
//
//    /**
//     * Defines the color buffer from which values are obtained.
//     *
//     * <p>Acceptable values for {@code src} depend on whether the GL is using the default framebuffer (i.e., {@link GL30#GL_DRAW_FRAMEBUFFER_BINDING DRAW_FRAMEBUFFER_BINDING} is zero), or
//     * a framebuffer object (i.e., {@link GL30#GL_DRAW_FRAMEBUFFER_BINDING DRAW_FRAMEBUFFER_BINDING} is non-zero). In the initial state, the GL is bound to the default framebuffer.</p>
//     *
//     * @param src the color buffer to read from. One of:<br><table><tr><td>{@link #GL_NONE NONE}</td><td>{@link #GL_FRONT_LEFT FRONT_LEFT}</td><td>{@link #GL_FRONT_RIGHT FRONT_RIGHT}</td><td>{@link #GL_BACK_LEFT BACK_LEFT}</td><td>{@link #GL_BACK_RIGHT BACK_RIGHT}</td><td>{@link #GL_FRONT FRONT}</td><td>{@link #GL_BACK BACK}</td><td>{@link #GL_LEFT LEFT}</td></tr><tr><td>{@link #GL_RIGHT RIGHT}</td><td>{@link #GL_FRONT_AND_BACK FRONT_AND_BACK}</td><td>{@link GL30#GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td><td>GL30.GL_COLOR_ATTACHMENT[1-15]</td></tr></table>
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glReadBuffer">Reference Page</a>
//     */
//    public static native void glReadBuffer(@NativeType("GLenum") int src);
//
//    // --- [ glReadPixels ] ---
//
//    /** Unsafe version of: {@link #glReadPixels ReadPixels} */
//    public static native void nglReadPixels(int x, int y, int width, int height, int format, int type, long pixels);
//
//    /**
//     * ReadPixels obtains values from the selected read buffer from each pixel with lower left hand corner at {@code (x + i, y + j)} for {@code 0 <= i < width}
//     * and {@code 0 <= j < height}; this pixel is said to be the i<sup>th</sup> pixel in the j<sup>th</sup> row. If any of these pixels lies outside of the
//     * window allocated to the current GL context, or outside of the image attached to the currently bound read framebuffer object, then the values obtained
//     * for those pixels are undefined. When {@link GL30#GL_READ_FRAMEBUFFER_BINDING READ_FRAMEBUFFER_BINDING} is zero, values are also undefined for individual pixels that are not owned by
//     * the current context. Otherwise, {@code ReadPixels} obtains values from the selected buffer, regardless of how those values were placed there.
//     *
//     * @param x      the left pixel coordinate
//     * @param y      the lower pixel coordinate
//     * @param width  the number of pixels to read in the x-dimension
//     * @param height the number of pixels to read in the y-dimension
//     * @param format the pixel format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type   the pixel type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels a buffer in which to place the returned pixel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glReadPixels">Reference Page</a>
//     */
//    public static void glReadPixels(@NativeType("GLint") int x, @NativeType("GLint") int y, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") ByteBuffer pixels) {
//        nglReadPixels(x, y, width, height, format, type, memAddress(pixels));
//    }
//
//    /**
//     * ReadPixels obtains values from the selected read buffer from each pixel with lower left hand corner at {@code (x + i, y + j)} for {@code 0 <= i < width}
//     * and {@code 0 <= j < height}; this pixel is said to be the i<sup>th</sup> pixel in the j<sup>th</sup> row. If any of these pixels lies outside of the
//     * window allocated to the current GL context, or outside of the image attached to the currently bound read framebuffer object, then the values obtained
//     * for those pixels are undefined. When {@link GL30#GL_READ_FRAMEBUFFER_BINDING READ_FRAMEBUFFER_BINDING} is zero, values are also undefined for individual pixels that are not owned by
//     * the current context. Otherwise, {@code ReadPixels} obtains values from the selected buffer, regardless of how those values were placed there.
//     *
//     * @param x      the left pixel coordinate
//     * @param y      the lower pixel coordinate
//     * @param width  the number of pixels to read in the x-dimension
//     * @param height the number of pixels to read in the y-dimension
//     * @param format the pixel format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type   the pixel type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels a buffer in which to place the returned pixel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glReadPixels">Reference Page</a>
//     */
//    public static void glReadPixels(@NativeType("GLint") int x, @NativeType("GLint") int y, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") long pixels) {
//        nglReadPixels(x, y, width, height, format, type, pixels);
//    }
//
//    /**
//     * ReadPixels obtains values from the selected read buffer from each pixel with lower left hand corner at {@code (x + i, y + j)} for {@code 0 <= i < width}
//     * and {@code 0 <= j < height}; this pixel is said to be the i<sup>th</sup> pixel in the j<sup>th</sup> row. If any of these pixels lies outside of the
//     * window allocated to the current GL context, or outside of the image attached to the currently bound read framebuffer object, then the values obtained
//     * for those pixels are undefined. When {@link GL30#GL_READ_FRAMEBUFFER_BINDING READ_FRAMEBUFFER_BINDING} is zero, values are also undefined for individual pixels that are not owned by
//     * the current context. Otherwise, {@code ReadPixels} obtains values from the selected buffer, regardless of how those values were placed there.
//     *
//     * @param x      the left pixel coordinate
//     * @param y      the lower pixel coordinate
//     * @param width  the number of pixels to read in the x-dimension
//     * @param height the number of pixels to read in the y-dimension
//     * @param format the pixel format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type   the pixel type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels a buffer in which to place the returned pixel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glReadPixels">Reference Page</a>
//     */
//    public static void glReadPixels(@NativeType("GLint") int x, @NativeType("GLint") int y, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") ShortBuffer pixels) {
//        nglReadPixels(x, y, width, height, format, type, memAddress(pixels));
//    }
//
//    /**
//     * ReadPixels obtains values from the selected read buffer from each pixel with lower left hand corner at {@code (x + i, y + j)} for {@code 0 <= i < width}
//     * and {@code 0 <= j < height}; this pixel is said to be the i<sup>th</sup> pixel in the j<sup>th</sup> row. If any of these pixels lies outside of the
//     * window allocated to the current GL context, or outside of the image attached to the currently bound read framebuffer object, then the values obtained
//     * for those pixels are undefined. When {@link GL30#GL_READ_FRAMEBUFFER_BINDING READ_FRAMEBUFFER_BINDING} is zero, values are also undefined for individual pixels that are not owned by
//     * the current context. Otherwise, {@code ReadPixels} obtains values from the selected buffer, regardless of how those values were placed there.
//     *
//     * @param x      the left pixel coordinate
//     * @param y      the lower pixel coordinate
//     * @param width  the number of pixels to read in the x-dimension
//     * @param height the number of pixels to read in the y-dimension
//     * @param format the pixel format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type   the pixel type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels a buffer in which to place the returned pixel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glReadPixels">Reference Page</a>
//     */
//    public static void glReadPixels(@NativeType("GLint") int x, @NativeType("GLint") int y, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") IntBuffer pixels) {
//        nglReadPixels(x, y, width, height, format, type, memAddress(pixels));
//    }
//
//    /**
//     * ReadPixels obtains values from the selected read buffer from each pixel with lower left hand corner at {@code (x + i, y + j)} for {@code 0 <= i < width}
//     * and {@code 0 <= j < height}; this pixel is said to be the i<sup>th</sup> pixel in the j<sup>th</sup> row. If any of these pixels lies outside of the
//     * window allocated to the current GL context, or outside of the image attached to the currently bound read framebuffer object, then the values obtained
//     * for those pixels are undefined. When {@link GL30#GL_READ_FRAMEBUFFER_BINDING READ_FRAMEBUFFER_BINDING} is zero, values are also undefined for individual pixels that are not owned by
//     * the current context. Otherwise, {@code ReadPixels} obtains values from the selected buffer, regardless of how those values were placed there.
//     *
//     * @param x      the left pixel coordinate
//     * @param y      the lower pixel coordinate
//     * @param width  the number of pixels to read in the x-dimension
//     * @param height the number of pixels to read in the y-dimension
//     * @param format the pixel format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type   the pixel type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels a buffer in which to place the returned pixel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glReadPixels">Reference Page</a>
//     */
//    public static void glReadPixels(@NativeType("GLint") int x, @NativeType("GLint") int y, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") FloatBuffer pixels) {
//        nglReadPixels(x, y, width, height, format, type, memAddress(pixels));
//    }
//
//    // --- [ glScissor ] ---
//
//    /**
//     * Defines the scissor rectangle for all viewports. The scissor test is enabled or disabled for all viewports using {@link #glEnable Enable} or {@link #glDisable Disable}
//     * with the symbolic constant {@link #GL_SCISSOR_TEST SCISSOR_TEST}. When disabled, it is as if the scissor test always passes. When enabled, if
//     * <code>left &le; x<sub>w</sub> &lt; left + width</code> and <code>bottom &le; y<sub>w</sub> &lt; bottom + height</code> for the scissor rectangle, then the scissor
//     * test passes. Otherwise, the test fails and the fragment is discarded.
//     *
//     * @param x      the left scissor rectangle coordinate
//     * @param y      the bottom scissor rectangle coordinate
//     * @param width  the scissor rectangle width
//     * @param height the scissor rectangle height
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glScissor">Reference Page</a>
//     */
//    public static native void glScissor(@NativeType("GLint") int x, @NativeType("GLint") int y, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height);
//
//    // --- [ glStencilFunc ] ---
//
//    /**
//     * Controls the stencil test.
//     *
//     * <p>{@code ref} is an integer reference value that is used in the unsigned stencil comparison. Stencil comparison operations and queries of {@code ref}
//     * clamp its value to the range [0, 2<sup>s</sup> &ndash; 1], where s is the number of bits in the stencil buffer attached to the draw framebuffer. The s
//     * least significant bits of {@code mask} are bitwise ANDed with both the reference and the stored stencil value, and the resulting masked values are those that
//     * participate in the comparison controlled by {@code func}.</p>
//     *
//     * @param func the stencil comparison function. One of:<br><table><tr><td>{@link #GL_NEVER NEVER}</td><td>{@link #GL_ALWAYS ALWAYS}</td><td>{@link #GL_LESS LESS}</td><td>{@link #GL_LEQUAL LEQUAL}</td><td>{@link #GL_EQUAL EQUAL}</td><td>{@link #GL_GEQUAL GEQUAL}</td><td>{@link #GL_GREATER GREATER}</td><td>{@link #GL_NOTEQUAL NOTEQUAL}</td></tr></table>
//     * @param ref  the reference value
//     * @param mask the stencil comparison mask
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glStencilFunc">Reference Page</a>
//     */
//    public static native void glStencilFunc(@NativeType("GLenum") int func, @NativeType("GLint") int ref, @NativeType("GLuint") int mask);
//
//    // --- [ glStencilMask ] ---
//
//    /**
//     * Masks the writing of particular bits into the stencil plans.
//     *
//     * <p>The least significant s bits of {@code mask}, where s is the number of bits in the stencil buffer, specify an integer mask. Where a 1 appears in this
//     * mask, the corresponding bit in the stencil buffer is written; where a 0 appears, the bit is not written.</p>
//     *
//     * @param mask the stencil mask
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glStencilMask">Reference Page</a>
//     */
//    public static native void glStencilMask(@NativeType("GLuint") int mask);
//
//    // --- [ glStencilOp ] ---
//
//    /**
//     * Indicates what happens to the stored stencil value if this or certain subsequent tests fail or pass.
//     *
//     * <p>The supported actions are {@link #GL_KEEP KEEP}, {@link #GL_ZERO ZERO}, {@link #GL_REPLACE REPLACE}, {@link #GL_INCR INCR}, {@link #GL_DECR DECR}, {@link #GL_INVERT INVERT},
//     * {@link GL14#GL_INCR_WRAP INCR_WRAP} and {@link GL14#GL_DECR_WRAP DECR_WRAP}. These correspond to keeping the current value, setting to zero, replacing with the reference value,
//     * incrementing with saturation, decrementing with saturation, bitwise inverting it, incrementing without saturation, and decrementing without saturation.</p>
//     *
//     * <p>For purposes of increment and decrement, the stencil bits are considered as an unsigned integer. Incrementing or decrementing with saturation clamps
//     * the stencil value at 0 and the maximum representable value. Incrementing or decrementing without saturation will wrap such that incrementing the maximum
//     * representable value results in 0, and decrementing 0 results in the maximum representable value.</p>
//     *
//     * @param sfail  the action to take if the stencil test fails
//     * @param dpfail the action to take if the depth buffer test fails
//     * @param dppass the action to take if the depth buffer test passes
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glStencilOp">Reference Page</a>
//     */
//    public static native void glStencilOp(@NativeType("GLenum") int sfail, @NativeType("GLenum") int dpfail, @NativeType("GLenum") int dppass);
//
//    // --- [ glTexImage1D ] ---
//
//    /** Unsafe version of: {@link #glTexImage1D TexImage1D} */
//    public static native void nglTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, long pixels);
//
//    /**
//     * One-dimensional version of {@link #glTexImage2D TexImage2D}}.
//     *
//     * @param target         the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link #GL_PROXY_TEXTURE_1D PROXY_TEXTURE_1D}</td></tr></table>
//     * @param level          the level-of-detail number
//     * @param internalformat the texture internal format
//     * @param width          the texture width
//     * @param border         the texture border width
//     * @param format         the texel data format
//     * @param type           the texel data type
//     * @param pixels         the texel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexImage1D">Reference Page</a>
//     */
//    public static void glTexImage1D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int internalformat, @NativeType("GLsizei") int width, @NativeType("GLint") int border, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @Nullable @NativeType("void const *") ByteBuffer pixels) {
//        nglTexImage1D(target, level, internalformat, width, border, format, type, memAddressSafe(pixels));
//    }
//
//    /**
//     * One-dimensional version of {@link #glTexImage2D TexImage2D}}.
//     *
//     * @param target         the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link #GL_PROXY_TEXTURE_1D PROXY_TEXTURE_1D}</td></tr></table>
//     * @param level          the level-of-detail number
//     * @param internalformat the texture internal format
//     * @param width          the texture width
//     * @param border         the texture border width
//     * @param format         the texel data format
//     * @param type           the texel data type
//     * @param pixels         the texel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexImage1D">Reference Page</a>
//     */
//    public static void glTexImage1D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int internalformat, @NativeType("GLsizei") int width, @NativeType("GLint") int border, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @Nullable @NativeType("void const *") long pixels) {
//        nglTexImage1D(target, level, internalformat, width, border, format, type, pixels);
//    }
//
//    /**
//     * One-dimensional version of {@link #glTexImage2D TexImage2D}}.
//     *
//     * @param target         the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link #GL_PROXY_TEXTURE_1D PROXY_TEXTURE_1D}</td></tr></table>
//     * @param level          the level-of-detail number
//     * @param internalformat the texture internal format
//     * @param width          the texture width
//     * @param border         the texture border width
//     * @param format         the texel data format
//     * @param type           the texel data type
//     * @param pixels         the texel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexImage1D">Reference Page</a>
//     */
//    public static void glTexImage1D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int internalformat, @NativeType("GLsizei") int width, @NativeType("GLint") int border, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @Nullable @NativeType("void const *") ShortBuffer pixels) {
//        nglTexImage1D(target, level, internalformat, width, border, format, type, memAddressSafe(pixels));
//    }
//
//    /**
//     * One-dimensional version of {@link #glTexImage2D TexImage2D}}.
//     *
//     * @param target         the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link #GL_PROXY_TEXTURE_1D PROXY_TEXTURE_1D}</td></tr></table>
//     * @param level          the level-of-detail number
//     * @param internalformat the texture internal format
//     * @param width          the texture width
//     * @param border         the texture border width
//     * @param format         the texel data format
//     * @param type           the texel data type
//     * @param pixels         the texel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexImage1D">Reference Page</a>
//     */
//    public static void glTexImage1D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int internalformat, @NativeType("GLsizei") int width, @NativeType("GLint") int border, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @Nullable @NativeType("void const *") IntBuffer pixels) {
//        nglTexImage1D(target, level, internalformat, width, border, format, type, memAddressSafe(pixels));
//    }
//
//    /**
//     * One-dimensional version of {@link #glTexImage2D TexImage2D}}.
//     *
//     * @param target         the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link #GL_PROXY_TEXTURE_1D PROXY_TEXTURE_1D}</td></tr></table>
//     * @param level          the level-of-detail number
//     * @param internalformat the texture internal format
//     * @param width          the texture width
//     * @param border         the texture border width
//     * @param format         the texel data format
//     * @param type           the texel data type
//     * @param pixels         the texel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexImage1D">Reference Page</a>
//     */
//    public static void glTexImage1D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int internalformat, @NativeType("GLsizei") int width, @NativeType("GLint") int border, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @Nullable @NativeType("void const *") FloatBuffer pixels) {
//        nglTexImage1D(target, level, internalformat, width, border, format, type, memAddressSafe(pixels));
//    }
//
//    /**
//     * One-dimensional version of {@link #glTexImage2D TexImage2D}}.
//     *
//     * @param target         the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link #GL_PROXY_TEXTURE_1D PROXY_TEXTURE_1D}</td></tr></table>
//     * @param level          the level-of-detail number
//     * @param internalformat the texture internal format
//     * @param width          the texture width
//     * @param border         the texture border width
//     * @param format         the texel data format
//     * @param type           the texel data type
//     * @param pixels         the texel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexImage1D">Reference Page</a>
//     */
//    public static void glTexImage1D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int internalformat, @NativeType("GLsizei") int width, @NativeType("GLint") int border, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @Nullable @NativeType("void const *") DoubleBuffer pixels) {
//        nglTexImage1D(target, level, internalformat, width, border, format, type, memAddressSafe(pixels));
//    }
//
//    // --- [ glTexImage2D ] ---
//
//    /** Unsafe version of: {@link #glTexImage2D TexImage2D} */
//    public static native void nglTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, long pixels);
//
//    /**
//     * Specifies a two-dimensional texture image.
//     *
//     * @param target         the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link #GL_PROXY_TEXTURE_2D PROXY_TEXTURE_2D}</td><td>{@link GL30#GL_PROXY_TEXTURE_1D_ARRAY PROXY_TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_PROXY_TEXTURE_RECTANGLE PROXY_TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_PROXY_TEXTURE_CUBE_MAP PROXY_TEXTURE_CUBE_MAP}</td></tr></table>
//     * @param level          the level-of-detail number
//     * @param internalformat the texture internal format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr><tr><td>{@link GL30#GL_R8 R8}</td><td>{@link GL31#GL_R8_SNORM R8_SNORM}</td><td>{@link GL30#GL_R16 R16}</td><td>{@link GL31#GL_R16_SNORM R16_SNORM}</td><td>{@link GL30#GL_RG8 RG8}</td><td>{@link GL31#GL_RG8_SNORM RG8_SNORM}</td></tr><tr><td>{@link GL30#GL_RG16 RG16}</td><td>{@link GL31#GL_RG16_SNORM RG16_SNORM}</td><td>{@link #GL_R3_G3_B2 R3_G3_B2}</td><td>{@link #GL_RGB4 RGB4}</td><td>{@link #GL_RGB5 RGB5}</td><td>{@link GL41#GL_RGB565 RGB565}</td></tr><tr><td>{@link #GL_RGB8 RGB8}</td><td>{@link GL31#GL_RGB8_SNORM RGB8_SNORM}</td><td>{@link #GL_RGB10 RGB10}</td><td>{@link #GL_RGB12 RGB12}</td><td>{@link #GL_RGB16 RGB16}</td><td>{@link GL31#GL_RGB16_SNORM RGB16_SNORM}</td></tr><tr><td>{@link #GL_RGBA2 RGBA2}</td><td>{@link #GL_RGBA4 RGBA4}</td><td>{@link #GL_RGB5_A1 RGB5_A1}</td><td>{@link #GL_RGBA8 RGBA8}</td><td>{@link GL31#GL_RGBA8_SNORM RGBA8_SNORM}</td><td>{@link #GL_RGB10_A2 RGB10_A2}</td></tr><tr><td>{@link GL33#GL_RGB10_A2UI RGB10_A2UI}</td><td>{@link #GL_RGBA12 RGBA12}</td><td>{@link #GL_RGBA16 RGBA16}</td><td>{@link GL31#GL_RGBA16_SNORM RGBA16_SNORM}</td><td>{@link GL21#GL_SRGB8 SRGB8}</td><td>{@link GL21#GL_SRGB8_ALPHA8 SRGB8_ALPHA8}</td></tr><tr><td>{@link GL30#GL_R16F R16F}</td><td>{@link GL30#GL_RG16F RG16F}</td><td>{@link GL30#GL_RGB16F RGB16F}</td><td>{@link GL30#GL_RGBA16F RGBA16F}</td><td>{@link GL30#GL_R32F R32F}</td><td>{@link GL30#GL_RG32F RG32F}</td></tr><tr><td>{@link GL30#GL_RGB32F RGB32F}</td><td>{@link GL30#GL_RGBA32F RGBA32F}</td><td>{@link GL30#GL_R11F_G11F_B10F R11F_G11F_B10F}</td><td>{@link GL30#GL_RGB9_E5 RGB9_E5}</td><td>{@link GL30#GL_R8I R8I}</td><td>{@link GL30#GL_R8UI R8UI}</td></tr><tr><td>{@link GL30#GL_R16I R16I}</td><td>{@link GL30#GL_R16UI R16UI}</td><td>{@link GL30#GL_R32I R32I}</td><td>{@link GL30#GL_R32UI R32UI}</td><td>{@link GL30#GL_RG8I RG8I}</td><td>{@link GL30#GL_RG8UI RG8UI}</td></tr><tr><td>{@link GL30#GL_RG16I RG16I}</td><td>{@link GL30#GL_RG16UI RG16UI}</td><td>{@link GL30#GL_RG32I RG32I}</td><td>{@link GL30#GL_RG32UI RG32UI}</td><td>{@link GL30#GL_RGB8I RGB8I}</td><td>{@link GL30#GL_RGB8UI RGB8UI}</td></tr><tr><td>{@link GL30#GL_RGB16I RGB16I}</td><td>{@link GL30#GL_RGB16UI RGB16UI}</td><td>{@link GL30#GL_RGB32I RGB32I}</td><td>{@link GL30#GL_RGB32UI RGB32UI}</td><td>{@link GL30#GL_RGBA8I RGBA8I}</td><td>{@link GL30#GL_RGBA8UI RGBA8UI}</td></tr><tr><td>{@link GL30#GL_RGBA16I RGBA16I}</td><td>{@link GL30#GL_RGBA16UI RGBA16UI}</td><td>{@link GL30#GL_RGBA32I RGBA32I}</td><td>{@link GL30#GL_RGBA32UI RGBA32UI}</td><td>{@link GL14#GL_DEPTH_COMPONENT16 DEPTH_COMPONENT16}</td><td>{@link GL14#GL_DEPTH_COMPONENT24 DEPTH_COMPONENT24}</td></tr><tr><td>{@link GL14#GL_DEPTH_COMPONENT32 DEPTH_COMPONENT32}</td><td>{@link GL30#GL_DEPTH24_STENCIL8 DEPTH24_STENCIL8}</td><td>{@link GL30#GL_DEPTH_COMPONENT32F DEPTH_COMPONENT32F}</td><td>{@link GL30#GL_DEPTH32F_STENCIL8 DEPTH32F_STENCIL8}</td><td>{@link GL30#GL_COMPRESSED_RED COMPRESSED_RED}</td><td>{@link GL30#GL_COMPRESSED_RG COMPRESSED_RG}</td></tr><tr><td>{@link GL13#GL_COMPRESSED_RGB COMPRESSED_RGB}</td><td>{@link GL13#GL_COMPRESSED_RGBA COMPRESSED_RGBA}</td><td>{@link GL21#GL_COMPRESSED_SRGB COMPRESSED_SRGB}</td><td>{@link GL21#GL_COMPRESSED_SRGB_ALPHA COMPRESSED_SRGB_ALPHA}</td><td>{@link GL30#GL_COMPRESSED_RED_RGTC1 COMPRESSED_RED_RGTC1}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RED_RGTC1 COMPRESSED_SIGNED_RED_RGTC1}</td></tr><tr><td>{@link GL30#GL_COMPRESSED_RG_RGTC2 COMPRESSED_RG_RGTC2}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RG_RGTC2 COMPRESSED_SIGNED_RG_RGTC2}</td><td>{@link GL42#GL_COMPRESSED_RGBA_BPTC_UNORM COMPRESSED_RGBA_BPTC_UNORM}</td><td>{@link GL42#GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM COMPRESSED_SRGB_ALPHA_BPTC_UNORM}</td><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT COMPRESSED_RGB_BPTC_SIGNED_FLOAT}</td><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGB8_ETC2 COMPRESSED_RGB8_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ETC2 COMPRESSED_SRGB8_ETC2}</td><td>{@link GL43#GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td><td>{@link GL43#GL_COMPRESSED_RGBA8_ETC2_EAC COMPRESSED_RGBA8_ETC2_EAC}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC COMPRESSED_SRGB8_ALPHA8_ETC2_EAC}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_R11_EAC COMPRESSED_R11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_R11_EAC COMPRESSED_SIGNED_R11_EAC}</td><td>{@link GL43#GL_COMPRESSED_RG11_EAC COMPRESSED_RG11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_RG11_EAC COMPRESSED_SIGNED_RG11_EAC}</td><td>see {@link EXTTextureCompressionS3TC}</td><td>see {@link EXTTextureCompressionLATC}</td></tr><tr><td>see {@link ATITextureCompression3DC}</td></tr></table>
//     * @param width          the texture width
//     * @param height         the texture height
//     * @param border         the texture border width
//     * @param format         the texel data format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type           the texel data type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels         the texel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexImage2D">Reference Page</a>
//     */
//    public static void glTexImage2D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int internalformat, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLint") int border, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @Nullable @NativeType("void const *") ByteBuffer pixels) {
//        nglTexImage2D(target, level, internalformat, width, height, border, format, type, memAddressSafe(pixels));
//    }
//
//    /**
//     * Specifies a two-dimensional texture image.
//     *
//     * @param target         the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link #GL_PROXY_TEXTURE_2D PROXY_TEXTURE_2D}</td><td>{@link GL30#GL_PROXY_TEXTURE_1D_ARRAY PROXY_TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_PROXY_TEXTURE_RECTANGLE PROXY_TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_PROXY_TEXTURE_CUBE_MAP PROXY_TEXTURE_CUBE_MAP}</td></tr></table>
//     * @param level          the level-of-detail number
//     * @param internalformat the texture internal format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr><tr><td>{@link GL30#GL_R8 R8}</td><td>{@link GL31#GL_R8_SNORM R8_SNORM}</td><td>{@link GL30#GL_R16 R16}</td><td>{@link GL31#GL_R16_SNORM R16_SNORM}</td><td>{@link GL30#GL_RG8 RG8}</td><td>{@link GL31#GL_RG8_SNORM RG8_SNORM}</td></tr><tr><td>{@link GL30#GL_RG16 RG16}</td><td>{@link GL31#GL_RG16_SNORM RG16_SNORM}</td><td>{@link #GL_R3_G3_B2 R3_G3_B2}</td><td>{@link #GL_RGB4 RGB4}</td><td>{@link #GL_RGB5 RGB5}</td><td>{@link GL41#GL_RGB565 RGB565}</td></tr><tr><td>{@link #GL_RGB8 RGB8}</td><td>{@link GL31#GL_RGB8_SNORM RGB8_SNORM}</td><td>{@link #GL_RGB10 RGB10}</td><td>{@link #GL_RGB12 RGB12}</td><td>{@link #GL_RGB16 RGB16}</td><td>{@link GL31#GL_RGB16_SNORM RGB16_SNORM}</td></tr><tr><td>{@link #GL_RGBA2 RGBA2}</td><td>{@link #GL_RGBA4 RGBA4}</td><td>{@link #GL_RGB5_A1 RGB5_A1}</td><td>{@link #GL_RGBA8 RGBA8}</td><td>{@link GL31#GL_RGBA8_SNORM RGBA8_SNORM}</td><td>{@link #GL_RGB10_A2 RGB10_A2}</td></tr><tr><td>{@link GL33#GL_RGB10_A2UI RGB10_A2UI}</td><td>{@link #GL_RGBA12 RGBA12}</td><td>{@link #GL_RGBA16 RGBA16}</td><td>{@link GL31#GL_RGBA16_SNORM RGBA16_SNORM}</td><td>{@link GL21#GL_SRGB8 SRGB8}</td><td>{@link GL21#GL_SRGB8_ALPHA8 SRGB8_ALPHA8}</td></tr><tr><td>{@link GL30#GL_R16F R16F}</td><td>{@link GL30#GL_RG16F RG16F}</td><td>{@link GL30#GL_RGB16F RGB16F}</td><td>{@link GL30#GL_RGBA16F RGBA16F}</td><td>{@link GL30#GL_R32F R32F}</td><td>{@link GL30#GL_RG32F RG32F}</td></tr><tr><td>{@link GL30#GL_RGB32F RGB32F}</td><td>{@link GL30#GL_RGBA32F RGBA32F}</td><td>{@link GL30#GL_R11F_G11F_B10F R11F_G11F_B10F}</td><td>{@link GL30#GL_RGB9_E5 RGB9_E5}</td><td>{@link GL30#GL_R8I R8I}</td><td>{@link GL30#GL_R8UI R8UI}</td></tr><tr><td>{@link GL30#GL_R16I R16I}</td><td>{@link GL30#GL_R16UI R16UI}</td><td>{@link GL30#GL_R32I R32I}</td><td>{@link GL30#GL_R32UI R32UI}</td><td>{@link GL30#GL_RG8I RG8I}</td><td>{@link GL30#GL_RG8UI RG8UI}</td></tr><tr><td>{@link GL30#GL_RG16I RG16I}</td><td>{@link GL30#GL_RG16UI RG16UI}</td><td>{@link GL30#GL_RG32I RG32I}</td><td>{@link GL30#GL_RG32UI RG32UI}</td><td>{@link GL30#GL_RGB8I RGB8I}</td><td>{@link GL30#GL_RGB8UI RGB8UI}</td></tr><tr><td>{@link GL30#GL_RGB16I RGB16I}</td><td>{@link GL30#GL_RGB16UI RGB16UI}</td><td>{@link GL30#GL_RGB32I RGB32I}</td><td>{@link GL30#GL_RGB32UI RGB32UI}</td><td>{@link GL30#GL_RGBA8I RGBA8I}</td><td>{@link GL30#GL_RGBA8UI RGBA8UI}</td></tr><tr><td>{@link GL30#GL_RGBA16I RGBA16I}</td><td>{@link GL30#GL_RGBA16UI RGBA16UI}</td><td>{@link GL30#GL_RGBA32I RGBA32I}</td><td>{@link GL30#GL_RGBA32UI RGBA32UI}</td><td>{@link GL14#GL_DEPTH_COMPONENT16 DEPTH_COMPONENT16}</td><td>{@link GL14#GL_DEPTH_COMPONENT24 DEPTH_COMPONENT24}</td></tr><tr><td>{@link GL14#GL_DEPTH_COMPONENT32 DEPTH_COMPONENT32}</td><td>{@link GL30#GL_DEPTH24_STENCIL8 DEPTH24_STENCIL8}</td><td>{@link GL30#GL_DEPTH_COMPONENT32F DEPTH_COMPONENT32F}</td><td>{@link GL30#GL_DEPTH32F_STENCIL8 DEPTH32F_STENCIL8}</td><td>{@link GL30#GL_COMPRESSED_RED COMPRESSED_RED}</td><td>{@link GL30#GL_COMPRESSED_RG COMPRESSED_RG}</td></tr><tr><td>{@link GL13#GL_COMPRESSED_RGB COMPRESSED_RGB}</td><td>{@link GL13#GL_COMPRESSED_RGBA COMPRESSED_RGBA}</td><td>{@link GL21#GL_COMPRESSED_SRGB COMPRESSED_SRGB}</td><td>{@link GL21#GL_COMPRESSED_SRGB_ALPHA COMPRESSED_SRGB_ALPHA}</td><td>{@link GL30#GL_COMPRESSED_RED_RGTC1 COMPRESSED_RED_RGTC1}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RED_RGTC1 COMPRESSED_SIGNED_RED_RGTC1}</td></tr><tr><td>{@link GL30#GL_COMPRESSED_RG_RGTC2 COMPRESSED_RG_RGTC2}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RG_RGTC2 COMPRESSED_SIGNED_RG_RGTC2}</td><td>{@link GL42#GL_COMPRESSED_RGBA_BPTC_UNORM COMPRESSED_RGBA_BPTC_UNORM}</td><td>{@link GL42#GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM COMPRESSED_SRGB_ALPHA_BPTC_UNORM}</td><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT COMPRESSED_RGB_BPTC_SIGNED_FLOAT}</td><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGB8_ETC2 COMPRESSED_RGB8_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ETC2 COMPRESSED_SRGB8_ETC2}</td><td>{@link GL43#GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td><td>{@link GL43#GL_COMPRESSED_RGBA8_ETC2_EAC COMPRESSED_RGBA8_ETC2_EAC}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC COMPRESSED_SRGB8_ALPHA8_ETC2_EAC}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_R11_EAC COMPRESSED_R11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_R11_EAC COMPRESSED_SIGNED_R11_EAC}</td><td>{@link GL43#GL_COMPRESSED_RG11_EAC COMPRESSED_RG11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_RG11_EAC COMPRESSED_SIGNED_RG11_EAC}</td><td>see {@link EXTTextureCompressionS3TC}</td><td>see {@link EXTTextureCompressionLATC}</td></tr><tr><td>see {@link ATITextureCompression3DC}</td></tr></table>
//     * @param width          the texture width
//     * @param height         the texture height
//     * @param border         the texture border width
//     * @param format         the texel data format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type           the texel data type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels         the texel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexImage2D">Reference Page</a>
//     */
//    public static void glTexImage2D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int internalformat, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLint") int border, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @Nullable @NativeType("void const *") long pixels) {
//        nglTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
//    }
//
//    /**
//     * Specifies a two-dimensional texture image.
//     *
//     * @param target         the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link #GL_PROXY_TEXTURE_2D PROXY_TEXTURE_2D}</td><td>{@link GL30#GL_PROXY_TEXTURE_1D_ARRAY PROXY_TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_PROXY_TEXTURE_RECTANGLE PROXY_TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_PROXY_TEXTURE_CUBE_MAP PROXY_TEXTURE_CUBE_MAP}</td></tr></table>
//     * @param level          the level-of-detail number
//     * @param internalformat the texture internal format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr><tr><td>{@link GL30#GL_R8 R8}</td><td>{@link GL31#GL_R8_SNORM R8_SNORM}</td><td>{@link GL30#GL_R16 R16}</td><td>{@link GL31#GL_R16_SNORM R16_SNORM}</td><td>{@link GL30#GL_RG8 RG8}</td><td>{@link GL31#GL_RG8_SNORM RG8_SNORM}</td></tr><tr><td>{@link GL30#GL_RG16 RG16}</td><td>{@link GL31#GL_RG16_SNORM RG16_SNORM}</td><td>{@link #GL_R3_G3_B2 R3_G3_B2}</td><td>{@link #GL_RGB4 RGB4}</td><td>{@link #GL_RGB5 RGB5}</td><td>{@link GL41#GL_RGB565 RGB565}</td></tr><tr><td>{@link #GL_RGB8 RGB8}</td><td>{@link GL31#GL_RGB8_SNORM RGB8_SNORM}</td><td>{@link #GL_RGB10 RGB10}</td><td>{@link #GL_RGB12 RGB12}</td><td>{@link #GL_RGB16 RGB16}</td><td>{@link GL31#GL_RGB16_SNORM RGB16_SNORM}</td></tr><tr><td>{@link #GL_RGBA2 RGBA2}</td><td>{@link #GL_RGBA4 RGBA4}</td><td>{@link #GL_RGB5_A1 RGB5_A1}</td><td>{@link #GL_RGBA8 RGBA8}</td><td>{@link GL31#GL_RGBA8_SNORM RGBA8_SNORM}</td><td>{@link #GL_RGB10_A2 RGB10_A2}</td></tr><tr><td>{@link GL33#GL_RGB10_A2UI RGB10_A2UI}</td><td>{@link #GL_RGBA12 RGBA12}</td><td>{@link #GL_RGBA16 RGBA16}</td><td>{@link GL31#GL_RGBA16_SNORM RGBA16_SNORM}</td><td>{@link GL21#GL_SRGB8 SRGB8}</td><td>{@link GL21#GL_SRGB8_ALPHA8 SRGB8_ALPHA8}</td></tr><tr><td>{@link GL30#GL_R16F R16F}</td><td>{@link GL30#GL_RG16F RG16F}</td><td>{@link GL30#GL_RGB16F RGB16F}</td><td>{@link GL30#GL_RGBA16F RGBA16F}</td><td>{@link GL30#GL_R32F R32F}</td><td>{@link GL30#GL_RG32F RG32F}</td></tr><tr><td>{@link GL30#GL_RGB32F RGB32F}</td><td>{@link GL30#GL_RGBA32F RGBA32F}</td><td>{@link GL30#GL_R11F_G11F_B10F R11F_G11F_B10F}</td><td>{@link GL30#GL_RGB9_E5 RGB9_E5}</td><td>{@link GL30#GL_R8I R8I}</td><td>{@link GL30#GL_R8UI R8UI}</td></tr><tr><td>{@link GL30#GL_R16I R16I}</td><td>{@link GL30#GL_R16UI R16UI}</td><td>{@link GL30#GL_R32I R32I}</td><td>{@link GL30#GL_R32UI R32UI}</td><td>{@link GL30#GL_RG8I RG8I}</td><td>{@link GL30#GL_RG8UI RG8UI}</td></tr><tr><td>{@link GL30#GL_RG16I RG16I}</td><td>{@link GL30#GL_RG16UI RG16UI}</td><td>{@link GL30#GL_RG32I RG32I}</td><td>{@link GL30#GL_RG32UI RG32UI}</td><td>{@link GL30#GL_RGB8I RGB8I}</td><td>{@link GL30#GL_RGB8UI RGB8UI}</td></tr><tr><td>{@link GL30#GL_RGB16I RGB16I}</td><td>{@link GL30#GL_RGB16UI RGB16UI}</td><td>{@link GL30#GL_RGB32I RGB32I}</td><td>{@link GL30#GL_RGB32UI RGB32UI}</td><td>{@link GL30#GL_RGBA8I RGBA8I}</td><td>{@link GL30#GL_RGBA8UI RGBA8UI}</td></tr><tr><td>{@link GL30#GL_RGBA16I RGBA16I}</td><td>{@link GL30#GL_RGBA16UI RGBA16UI}</td><td>{@link GL30#GL_RGBA32I RGBA32I}</td><td>{@link GL30#GL_RGBA32UI RGBA32UI}</td><td>{@link GL14#GL_DEPTH_COMPONENT16 DEPTH_COMPONENT16}</td><td>{@link GL14#GL_DEPTH_COMPONENT24 DEPTH_COMPONENT24}</td></tr><tr><td>{@link GL14#GL_DEPTH_COMPONENT32 DEPTH_COMPONENT32}</td><td>{@link GL30#GL_DEPTH24_STENCIL8 DEPTH24_STENCIL8}</td><td>{@link GL30#GL_DEPTH_COMPONENT32F DEPTH_COMPONENT32F}</td><td>{@link GL30#GL_DEPTH32F_STENCIL8 DEPTH32F_STENCIL8}</td><td>{@link GL30#GL_COMPRESSED_RED COMPRESSED_RED}</td><td>{@link GL30#GL_COMPRESSED_RG COMPRESSED_RG}</td></tr><tr><td>{@link GL13#GL_COMPRESSED_RGB COMPRESSED_RGB}</td><td>{@link GL13#GL_COMPRESSED_RGBA COMPRESSED_RGBA}</td><td>{@link GL21#GL_COMPRESSED_SRGB COMPRESSED_SRGB}</td><td>{@link GL21#GL_COMPRESSED_SRGB_ALPHA COMPRESSED_SRGB_ALPHA}</td><td>{@link GL30#GL_COMPRESSED_RED_RGTC1 COMPRESSED_RED_RGTC1}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RED_RGTC1 COMPRESSED_SIGNED_RED_RGTC1}</td></tr><tr><td>{@link GL30#GL_COMPRESSED_RG_RGTC2 COMPRESSED_RG_RGTC2}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RG_RGTC2 COMPRESSED_SIGNED_RG_RGTC2}</td><td>{@link GL42#GL_COMPRESSED_RGBA_BPTC_UNORM COMPRESSED_RGBA_BPTC_UNORM}</td><td>{@link GL42#GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM COMPRESSED_SRGB_ALPHA_BPTC_UNORM}</td><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT COMPRESSED_RGB_BPTC_SIGNED_FLOAT}</td><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGB8_ETC2 COMPRESSED_RGB8_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ETC2 COMPRESSED_SRGB8_ETC2}</td><td>{@link GL43#GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td><td>{@link GL43#GL_COMPRESSED_RGBA8_ETC2_EAC COMPRESSED_RGBA8_ETC2_EAC}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC COMPRESSED_SRGB8_ALPHA8_ETC2_EAC}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_R11_EAC COMPRESSED_R11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_R11_EAC COMPRESSED_SIGNED_R11_EAC}</td><td>{@link GL43#GL_COMPRESSED_RG11_EAC COMPRESSED_RG11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_RG11_EAC COMPRESSED_SIGNED_RG11_EAC}</td><td>see {@link EXTTextureCompressionS3TC}</td><td>see {@link EXTTextureCompressionLATC}</td></tr><tr><td>see {@link ATITextureCompression3DC}</td></tr></table>
//     * @param width          the texture width
//     * @param height         the texture height
//     * @param border         the texture border width
//     * @param format         the texel data format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type           the texel data type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels         the texel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexImage2D">Reference Page</a>
//     */
//    public static void glTexImage2D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int internalformat, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLint") int border, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @Nullable @NativeType("void const *") ShortBuffer pixels) {
//        nglTexImage2D(target, level, internalformat, width, height, border, format, type, memAddressSafe(pixels));
//    }
//
//    /**
//     * Specifies a two-dimensional texture image.
//     *
//     * @param target         the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link #GL_PROXY_TEXTURE_2D PROXY_TEXTURE_2D}</td><td>{@link GL30#GL_PROXY_TEXTURE_1D_ARRAY PROXY_TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_PROXY_TEXTURE_RECTANGLE PROXY_TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_PROXY_TEXTURE_CUBE_MAP PROXY_TEXTURE_CUBE_MAP}</td></tr></table>
//     * @param level          the level-of-detail number
//     * @param internalformat the texture internal format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr><tr><td>{@link GL30#GL_R8 R8}</td><td>{@link GL31#GL_R8_SNORM R8_SNORM}</td><td>{@link GL30#GL_R16 R16}</td><td>{@link GL31#GL_R16_SNORM R16_SNORM}</td><td>{@link GL30#GL_RG8 RG8}</td><td>{@link GL31#GL_RG8_SNORM RG8_SNORM}</td></tr><tr><td>{@link GL30#GL_RG16 RG16}</td><td>{@link GL31#GL_RG16_SNORM RG16_SNORM}</td><td>{@link #GL_R3_G3_B2 R3_G3_B2}</td><td>{@link #GL_RGB4 RGB4}</td><td>{@link #GL_RGB5 RGB5}</td><td>{@link GL41#GL_RGB565 RGB565}</td></tr><tr><td>{@link #GL_RGB8 RGB8}</td><td>{@link GL31#GL_RGB8_SNORM RGB8_SNORM}</td><td>{@link #GL_RGB10 RGB10}</td><td>{@link #GL_RGB12 RGB12}</td><td>{@link #GL_RGB16 RGB16}</td><td>{@link GL31#GL_RGB16_SNORM RGB16_SNORM}</td></tr><tr><td>{@link #GL_RGBA2 RGBA2}</td><td>{@link #GL_RGBA4 RGBA4}</td><td>{@link #GL_RGB5_A1 RGB5_A1}</td><td>{@link #GL_RGBA8 RGBA8}</td><td>{@link GL31#GL_RGBA8_SNORM RGBA8_SNORM}</td><td>{@link #GL_RGB10_A2 RGB10_A2}</td></tr><tr><td>{@link GL33#GL_RGB10_A2UI RGB10_A2UI}</td><td>{@link #GL_RGBA12 RGBA12}</td><td>{@link #GL_RGBA16 RGBA16}</td><td>{@link GL31#GL_RGBA16_SNORM RGBA16_SNORM}</td><td>{@link GL21#GL_SRGB8 SRGB8}</td><td>{@link GL21#GL_SRGB8_ALPHA8 SRGB8_ALPHA8}</td></tr><tr><td>{@link GL30#GL_R16F R16F}</td><td>{@link GL30#GL_RG16F RG16F}</td><td>{@link GL30#GL_RGB16F RGB16F}</td><td>{@link GL30#GL_RGBA16F RGBA16F}</td><td>{@link GL30#GL_R32F R32F}</td><td>{@link GL30#GL_RG32F RG32F}</td></tr><tr><td>{@link GL30#GL_RGB32F RGB32F}</td><td>{@link GL30#GL_RGBA32F RGBA32F}</td><td>{@link GL30#GL_R11F_G11F_B10F R11F_G11F_B10F}</td><td>{@link GL30#GL_RGB9_E5 RGB9_E5}</td><td>{@link GL30#GL_R8I R8I}</td><td>{@link GL30#GL_R8UI R8UI}</td></tr><tr><td>{@link GL30#GL_R16I R16I}</td><td>{@link GL30#GL_R16UI R16UI}</td><td>{@link GL30#GL_R32I R32I}</td><td>{@link GL30#GL_R32UI R32UI}</td><td>{@link GL30#GL_RG8I RG8I}</td><td>{@link GL30#GL_RG8UI RG8UI}</td></tr><tr><td>{@link GL30#GL_RG16I RG16I}</td><td>{@link GL30#GL_RG16UI RG16UI}</td><td>{@link GL30#GL_RG32I RG32I}</td><td>{@link GL30#GL_RG32UI RG32UI}</td><td>{@link GL30#GL_RGB8I RGB8I}</td><td>{@link GL30#GL_RGB8UI RGB8UI}</td></tr><tr><td>{@link GL30#GL_RGB16I RGB16I}</td><td>{@link GL30#GL_RGB16UI RGB16UI}</td><td>{@link GL30#GL_RGB32I RGB32I}</td><td>{@link GL30#GL_RGB32UI RGB32UI}</td><td>{@link GL30#GL_RGBA8I RGBA8I}</td><td>{@link GL30#GL_RGBA8UI RGBA8UI}</td></tr><tr><td>{@link GL30#GL_RGBA16I RGBA16I}</td><td>{@link GL30#GL_RGBA16UI RGBA16UI}</td><td>{@link GL30#GL_RGBA32I RGBA32I}</td><td>{@link GL30#GL_RGBA32UI RGBA32UI}</td><td>{@link GL14#GL_DEPTH_COMPONENT16 DEPTH_COMPONENT16}</td><td>{@link GL14#GL_DEPTH_COMPONENT24 DEPTH_COMPONENT24}</td></tr><tr><td>{@link GL14#GL_DEPTH_COMPONENT32 DEPTH_COMPONENT32}</td><td>{@link GL30#GL_DEPTH24_STENCIL8 DEPTH24_STENCIL8}</td><td>{@link GL30#GL_DEPTH_COMPONENT32F DEPTH_COMPONENT32F}</td><td>{@link GL30#GL_DEPTH32F_STENCIL8 DEPTH32F_STENCIL8}</td><td>{@link GL30#GL_COMPRESSED_RED COMPRESSED_RED}</td><td>{@link GL30#GL_COMPRESSED_RG COMPRESSED_RG}</td></tr><tr><td>{@link GL13#GL_COMPRESSED_RGB COMPRESSED_RGB}</td><td>{@link GL13#GL_COMPRESSED_RGBA COMPRESSED_RGBA}</td><td>{@link GL21#GL_COMPRESSED_SRGB COMPRESSED_SRGB}</td><td>{@link GL21#GL_COMPRESSED_SRGB_ALPHA COMPRESSED_SRGB_ALPHA}</td><td>{@link GL30#GL_COMPRESSED_RED_RGTC1 COMPRESSED_RED_RGTC1}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RED_RGTC1 COMPRESSED_SIGNED_RED_RGTC1}</td></tr><tr><td>{@link GL30#GL_COMPRESSED_RG_RGTC2 COMPRESSED_RG_RGTC2}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RG_RGTC2 COMPRESSED_SIGNED_RG_RGTC2}</td><td>{@link GL42#GL_COMPRESSED_RGBA_BPTC_UNORM COMPRESSED_RGBA_BPTC_UNORM}</td><td>{@link GL42#GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM COMPRESSED_SRGB_ALPHA_BPTC_UNORM}</td><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT COMPRESSED_RGB_BPTC_SIGNED_FLOAT}</td><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGB8_ETC2 COMPRESSED_RGB8_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ETC2 COMPRESSED_SRGB8_ETC2}</td><td>{@link GL43#GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td><td>{@link GL43#GL_COMPRESSED_RGBA8_ETC2_EAC COMPRESSED_RGBA8_ETC2_EAC}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC COMPRESSED_SRGB8_ALPHA8_ETC2_EAC}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_R11_EAC COMPRESSED_R11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_R11_EAC COMPRESSED_SIGNED_R11_EAC}</td><td>{@link GL43#GL_COMPRESSED_RG11_EAC COMPRESSED_RG11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_RG11_EAC COMPRESSED_SIGNED_RG11_EAC}</td><td>see {@link EXTTextureCompressionS3TC}</td><td>see {@link EXTTextureCompressionLATC}</td></tr><tr><td>see {@link ATITextureCompression3DC}</td></tr></table>
//     * @param width          the texture width
//     * @param height         the texture height
//     * @param border         the texture border width
//     * @param format         the texel data format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type           the texel data type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels         the texel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexImage2D">Reference Page</a>
//     */
//    public static void glTexImage2D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int internalformat, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLint") int border, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @Nullable @NativeType("void const *") IntBuffer pixels) {
//        nglTexImage2D(target, level, internalformat, width, height, border, format, type, memAddressSafe(pixels));
//    }
//
//    /**
//     * Specifies a two-dimensional texture image.
//     *
//     * @param target         the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link #GL_PROXY_TEXTURE_2D PROXY_TEXTURE_2D}</td><td>{@link GL30#GL_PROXY_TEXTURE_1D_ARRAY PROXY_TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_PROXY_TEXTURE_RECTANGLE PROXY_TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_PROXY_TEXTURE_CUBE_MAP PROXY_TEXTURE_CUBE_MAP}</td></tr></table>
//     * @param level          the level-of-detail number
//     * @param internalformat the texture internal format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr><tr><td>{@link GL30#GL_R8 R8}</td><td>{@link GL31#GL_R8_SNORM R8_SNORM}</td><td>{@link GL30#GL_R16 R16}</td><td>{@link GL31#GL_R16_SNORM R16_SNORM}</td><td>{@link GL30#GL_RG8 RG8}</td><td>{@link GL31#GL_RG8_SNORM RG8_SNORM}</td></tr><tr><td>{@link GL30#GL_RG16 RG16}</td><td>{@link GL31#GL_RG16_SNORM RG16_SNORM}</td><td>{@link #GL_R3_G3_B2 R3_G3_B2}</td><td>{@link #GL_RGB4 RGB4}</td><td>{@link #GL_RGB5 RGB5}</td><td>{@link GL41#GL_RGB565 RGB565}</td></tr><tr><td>{@link #GL_RGB8 RGB8}</td><td>{@link GL31#GL_RGB8_SNORM RGB8_SNORM}</td><td>{@link #GL_RGB10 RGB10}</td><td>{@link #GL_RGB12 RGB12}</td><td>{@link #GL_RGB16 RGB16}</td><td>{@link GL31#GL_RGB16_SNORM RGB16_SNORM}</td></tr><tr><td>{@link #GL_RGBA2 RGBA2}</td><td>{@link #GL_RGBA4 RGBA4}</td><td>{@link #GL_RGB5_A1 RGB5_A1}</td><td>{@link #GL_RGBA8 RGBA8}</td><td>{@link GL31#GL_RGBA8_SNORM RGBA8_SNORM}</td><td>{@link #GL_RGB10_A2 RGB10_A2}</td></tr><tr><td>{@link GL33#GL_RGB10_A2UI RGB10_A2UI}</td><td>{@link #GL_RGBA12 RGBA12}</td><td>{@link #GL_RGBA16 RGBA16}</td><td>{@link GL31#GL_RGBA16_SNORM RGBA16_SNORM}</td><td>{@link GL21#GL_SRGB8 SRGB8}</td><td>{@link GL21#GL_SRGB8_ALPHA8 SRGB8_ALPHA8}</td></tr><tr><td>{@link GL30#GL_R16F R16F}</td><td>{@link GL30#GL_RG16F RG16F}</td><td>{@link GL30#GL_RGB16F RGB16F}</td><td>{@link GL30#GL_RGBA16F RGBA16F}</td><td>{@link GL30#GL_R32F R32F}</td><td>{@link GL30#GL_RG32F RG32F}</td></tr><tr><td>{@link GL30#GL_RGB32F RGB32F}</td><td>{@link GL30#GL_RGBA32F RGBA32F}</td><td>{@link GL30#GL_R11F_G11F_B10F R11F_G11F_B10F}</td><td>{@link GL30#GL_RGB9_E5 RGB9_E5}</td><td>{@link GL30#GL_R8I R8I}</td><td>{@link GL30#GL_R8UI R8UI}</td></tr><tr><td>{@link GL30#GL_R16I R16I}</td><td>{@link GL30#GL_R16UI R16UI}</td><td>{@link GL30#GL_R32I R32I}</td><td>{@link GL30#GL_R32UI R32UI}</td><td>{@link GL30#GL_RG8I RG8I}</td><td>{@link GL30#GL_RG8UI RG8UI}</td></tr><tr><td>{@link GL30#GL_RG16I RG16I}</td><td>{@link GL30#GL_RG16UI RG16UI}</td><td>{@link GL30#GL_RG32I RG32I}</td><td>{@link GL30#GL_RG32UI RG32UI}</td><td>{@link GL30#GL_RGB8I RGB8I}</td><td>{@link GL30#GL_RGB8UI RGB8UI}</td></tr><tr><td>{@link GL30#GL_RGB16I RGB16I}</td><td>{@link GL30#GL_RGB16UI RGB16UI}</td><td>{@link GL30#GL_RGB32I RGB32I}</td><td>{@link GL30#GL_RGB32UI RGB32UI}</td><td>{@link GL30#GL_RGBA8I RGBA8I}</td><td>{@link GL30#GL_RGBA8UI RGBA8UI}</td></tr><tr><td>{@link GL30#GL_RGBA16I RGBA16I}</td><td>{@link GL30#GL_RGBA16UI RGBA16UI}</td><td>{@link GL30#GL_RGBA32I RGBA32I}</td><td>{@link GL30#GL_RGBA32UI RGBA32UI}</td><td>{@link GL14#GL_DEPTH_COMPONENT16 DEPTH_COMPONENT16}</td><td>{@link GL14#GL_DEPTH_COMPONENT24 DEPTH_COMPONENT24}</td></tr><tr><td>{@link GL14#GL_DEPTH_COMPONENT32 DEPTH_COMPONENT32}</td><td>{@link GL30#GL_DEPTH24_STENCIL8 DEPTH24_STENCIL8}</td><td>{@link GL30#GL_DEPTH_COMPONENT32F DEPTH_COMPONENT32F}</td><td>{@link GL30#GL_DEPTH32F_STENCIL8 DEPTH32F_STENCIL8}</td><td>{@link GL30#GL_COMPRESSED_RED COMPRESSED_RED}</td><td>{@link GL30#GL_COMPRESSED_RG COMPRESSED_RG}</td></tr><tr><td>{@link GL13#GL_COMPRESSED_RGB COMPRESSED_RGB}</td><td>{@link GL13#GL_COMPRESSED_RGBA COMPRESSED_RGBA}</td><td>{@link GL21#GL_COMPRESSED_SRGB COMPRESSED_SRGB}</td><td>{@link GL21#GL_COMPRESSED_SRGB_ALPHA COMPRESSED_SRGB_ALPHA}</td><td>{@link GL30#GL_COMPRESSED_RED_RGTC1 COMPRESSED_RED_RGTC1}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RED_RGTC1 COMPRESSED_SIGNED_RED_RGTC1}</td></tr><tr><td>{@link GL30#GL_COMPRESSED_RG_RGTC2 COMPRESSED_RG_RGTC2}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RG_RGTC2 COMPRESSED_SIGNED_RG_RGTC2}</td><td>{@link GL42#GL_COMPRESSED_RGBA_BPTC_UNORM COMPRESSED_RGBA_BPTC_UNORM}</td><td>{@link GL42#GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM COMPRESSED_SRGB_ALPHA_BPTC_UNORM}</td><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT COMPRESSED_RGB_BPTC_SIGNED_FLOAT}</td><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGB8_ETC2 COMPRESSED_RGB8_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ETC2 COMPRESSED_SRGB8_ETC2}</td><td>{@link GL43#GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td><td>{@link GL43#GL_COMPRESSED_RGBA8_ETC2_EAC COMPRESSED_RGBA8_ETC2_EAC}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC COMPRESSED_SRGB8_ALPHA8_ETC2_EAC}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_R11_EAC COMPRESSED_R11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_R11_EAC COMPRESSED_SIGNED_R11_EAC}</td><td>{@link GL43#GL_COMPRESSED_RG11_EAC COMPRESSED_RG11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_RG11_EAC COMPRESSED_SIGNED_RG11_EAC}</td><td>see {@link EXTTextureCompressionS3TC}</td><td>see {@link EXTTextureCompressionLATC}</td></tr><tr><td>see {@link ATITextureCompression3DC}</td></tr></table>
//     * @param width          the texture width
//     * @param height         the texture height
//     * @param border         the texture border width
//     * @param format         the texel data format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type           the texel data type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels         the texel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexImage2D">Reference Page</a>
//     */
//    public static void glTexImage2D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int internalformat, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLint") int border, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @Nullable @NativeType("void const *") FloatBuffer pixels) {
//        nglTexImage2D(target, level, internalformat, width, height, border, format, type, memAddressSafe(pixels));
//    }
//
//    /**
//     * Specifies a two-dimensional texture image.
//     *
//     * @param target         the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link #GL_PROXY_TEXTURE_2D PROXY_TEXTURE_2D}</td><td>{@link GL30#GL_PROXY_TEXTURE_1D_ARRAY PROXY_TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_PROXY_TEXTURE_RECTANGLE PROXY_TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_PROXY_TEXTURE_CUBE_MAP PROXY_TEXTURE_CUBE_MAP}</td></tr></table>
//     * @param level          the level-of-detail number
//     * @param internalformat the texture internal format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr><tr><td>{@link GL30#GL_R8 R8}</td><td>{@link GL31#GL_R8_SNORM R8_SNORM}</td><td>{@link GL30#GL_R16 R16}</td><td>{@link GL31#GL_R16_SNORM R16_SNORM}</td><td>{@link GL30#GL_RG8 RG8}</td><td>{@link GL31#GL_RG8_SNORM RG8_SNORM}</td></tr><tr><td>{@link GL30#GL_RG16 RG16}</td><td>{@link GL31#GL_RG16_SNORM RG16_SNORM}</td><td>{@link #GL_R3_G3_B2 R3_G3_B2}</td><td>{@link #GL_RGB4 RGB4}</td><td>{@link #GL_RGB5 RGB5}</td><td>{@link GL41#GL_RGB565 RGB565}</td></tr><tr><td>{@link #GL_RGB8 RGB8}</td><td>{@link GL31#GL_RGB8_SNORM RGB8_SNORM}</td><td>{@link #GL_RGB10 RGB10}</td><td>{@link #GL_RGB12 RGB12}</td><td>{@link #GL_RGB16 RGB16}</td><td>{@link GL31#GL_RGB16_SNORM RGB16_SNORM}</td></tr><tr><td>{@link #GL_RGBA2 RGBA2}</td><td>{@link #GL_RGBA4 RGBA4}</td><td>{@link #GL_RGB5_A1 RGB5_A1}</td><td>{@link #GL_RGBA8 RGBA8}</td><td>{@link GL31#GL_RGBA8_SNORM RGBA8_SNORM}</td><td>{@link #GL_RGB10_A2 RGB10_A2}</td></tr><tr><td>{@link GL33#GL_RGB10_A2UI RGB10_A2UI}</td><td>{@link #GL_RGBA12 RGBA12}</td><td>{@link #GL_RGBA16 RGBA16}</td><td>{@link GL31#GL_RGBA16_SNORM RGBA16_SNORM}</td><td>{@link GL21#GL_SRGB8 SRGB8}</td><td>{@link GL21#GL_SRGB8_ALPHA8 SRGB8_ALPHA8}</td></tr><tr><td>{@link GL30#GL_R16F R16F}</td><td>{@link GL30#GL_RG16F RG16F}</td><td>{@link GL30#GL_RGB16F RGB16F}</td><td>{@link GL30#GL_RGBA16F RGBA16F}</td><td>{@link GL30#GL_R32F R32F}</td><td>{@link GL30#GL_RG32F RG32F}</td></tr><tr><td>{@link GL30#GL_RGB32F RGB32F}</td><td>{@link GL30#GL_RGBA32F RGBA32F}</td><td>{@link GL30#GL_R11F_G11F_B10F R11F_G11F_B10F}</td><td>{@link GL30#GL_RGB9_E5 RGB9_E5}</td><td>{@link GL30#GL_R8I R8I}</td><td>{@link GL30#GL_R8UI R8UI}</td></tr><tr><td>{@link GL30#GL_R16I R16I}</td><td>{@link GL30#GL_R16UI R16UI}</td><td>{@link GL30#GL_R32I R32I}</td><td>{@link GL30#GL_R32UI R32UI}</td><td>{@link GL30#GL_RG8I RG8I}</td><td>{@link GL30#GL_RG8UI RG8UI}</td></tr><tr><td>{@link GL30#GL_RG16I RG16I}</td><td>{@link GL30#GL_RG16UI RG16UI}</td><td>{@link GL30#GL_RG32I RG32I}</td><td>{@link GL30#GL_RG32UI RG32UI}</td><td>{@link GL30#GL_RGB8I RGB8I}</td><td>{@link GL30#GL_RGB8UI RGB8UI}</td></tr><tr><td>{@link GL30#GL_RGB16I RGB16I}</td><td>{@link GL30#GL_RGB16UI RGB16UI}</td><td>{@link GL30#GL_RGB32I RGB32I}</td><td>{@link GL30#GL_RGB32UI RGB32UI}</td><td>{@link GL30#GL_RGBA8I RGBA8I}</td><td>{@link GL30#GL_RGBA8UI RGBA8UI}</td></tr><tr><td>{@link GL30#GL_RGBA16I RGBA16I}</td><td>{@link GL30#GL_RGBA16UI RGBA16UI}</td><td>{@link GL30#GL_RGBA32I RGBA32I}</td><td>{@link GL30#GL_RGBA32UI RGBA32UI}</td><td>{@link GL14#GL_DEPTH_COMPONENT16 DEPTH_COMPONENT16}</td><td>{@link GL14#GL_DEPTH_COMPONENT24 DEPTH_COMPONENT24}</td></tr><tr><td>{@link GL14#GL_DEPTH_COMPONENT32 DEPTH_COMPONENT32}</td><td>{@link GL30#GL_DEPTH24_STENCIL8 DEPTH24_STENCIL8}</td><td>{@link GL30#GL_DEPTH_COMPONENT32F DEPTH_COMPONENT32F}</td><td>{@link GL30#GL_DEPTH32F_STENCIL8 DEPTH32F_STENCIL8}</td><td>{@link GL30#GL_COMPRESSED_RED COMPRESSED_RED}</td><td>{@link GL30#GL_COMPRESSED_RG COMPRESSED_RG}</td></tr><tr><td>{@link GL13#GL_COMPRESSED_RGB COMPRESSED_RGB}</td><td>{@link GL13#GL_COMPRESSED_RGBA COMPRESSED_RGBA}</td><td>{@link GL21#GL_COMPRESSED_SRGB COMPRESSED_SRGB}</td><td>{@link GL21#GL_COMPRESSED_SRGB_ALPHA COMPRESSED_SRGB_ALPHA}</td><td>{@link GL30#GL_COMPRESSED_RED_RGTC1 COMPRESSED_RED_RGTC1}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RED_RGTC1 COMPRESSED_SIGNED_RED_RGTC1}</td></tr><tr><td>{@link GL30#GL_COMPRESSED_RG_RGTC2 COMPRESSED_RG_RGTC2}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RG_RGTC2 COMPRESSED_SIGNED_RG_RGTC2}</td><td>{@link GL42#GL_COMPRESSED_RGBA_BPTC_UNORM COMPRESSED_RGBA_BPTC_UNORM}</td><td>{@link GL42#GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM COMPRESSED_SRGB_ALPHA_BPTC_UNORM}</td><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT COMPRESSED_RGB_BPTC_SIGNED_FLOAT}</td><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGB8_ETC2 COMPRESSED_RGB8_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ETC2 COMPRESSED_SRGB8_ETC2}</td><td>{@link GL43#GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td><td>{@link GL43#GL_COMPRESSED_RGBA8_ETC2_EAC COMPRESSED_RGBA8_ETC2_EAC}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC COMPRESSED_SRGB8_ALPHA8_ETC2_EAC}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_R11_EAC COMPRESSED_R11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_R11_EAC COMPRESSED_SIGNED_R11_EAC}</td><td>{@link GL43#GL_COMPRESSED_RG11_EAC COMPRESSED_RG11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_RG11_EAC COMPRESSED_SIGNED_RG11_EAC}</td><td>see {@link EXTTextureCompressionS3TC}</td><td>see {@link EXTTextureCompressionLATC}</td></tr><tr><td>see {@link ATITextureCompression3DC}</td></tr></table>
//     * @param width          the texture width
//     * @param height         the texture height
//     * @param border         the texture border width
//     * @param format         the texel data format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type           the texel data type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels         the texel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexImage2D">Reference Page</a>
//     */
//    public static void glTexImage2D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int internalformat, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLint") int border, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @Nullable @NativeType("void const *") DoubleBuffer pixels) {
//        nglTexImage2D(target, level, internalformat, width, height, border, format, type, memAddressSafe(pixels));
//    }
//
//    // --- [ glCopyTexImage1D ] ---
//
//    /**
//     * Defines a one-dimensional texel array in exactly the manner of {@link #glTexImage1D TexImage1D}, except that the image data are taken from the framebuffer rather
//     * than from client memory. For the purposes of decoding the texture image, {@code CopyTexImage1D} is equivalent to calling {@link #glCopyTexImage2D CopyTexImage2D}
//     * with corresponding arguments and height of 1, except that the height of the image is always 1, regardless of the value of border. level, internalformat,
//     * and border are specified using the same values, with the same meanings, as the corresponding arguments of {@link #glTexImage1D TexImage1D}. The constraints on
//     * width and border are exactly those of the corresponding arguments of {@link #glTexImage1D TexImage1D}.
//     *
//     * @param target         the texture target. Must be:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td></tr></table>
//     * @param level          the level-of-detail number
//     * @param internalFormat the texture internal format. See {@link #glTexImage2D TexImage2D} for a list of supported formats.
//     * @param x              the left framebuffer pixel coordinate
//     * @param y              the lower framebuffer pixel coordinate
//     * @param width          the texture width
//     * @param border         the texture border width
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glCopyTexImage1D">Reference Page</a>
//     */
//    public static native void glCopyTexImage1D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLenum") int internalFormat, @NativeType("GLint") int x, @NativeType("GLint") int y, @NativeType("GLsizei") int width, @NativeType("GLint") int border);
//
//    // --- [ glCopyTexImage2D ] ---
//
//    /**
//     * Defines a two-dimensional texel array in exactly the manner of {@link #glTexImage2D TexImage2D}, except that the image data are taken from the framebuffer rather
//     * than from client memory.
//     *
//     * <p>{@code x}, {@code y}, {@code width}, and {@code height} correspond precisely to the corresponding arguments to {@link #glReadPixels ReadPixels}; they specify the
//     * image's width and height, and the lower left (x, y) coordinates of the framebuffer region to be copied.</p>
//     *
//     * <p>The image is taken from the framebuffer exactly as if these arguments were passed to {@link GL11#glCopyPixels CopyPixels} with argument type set to {@link #GL_COLOR COLOR},
//     * {@link #GL_DEPTH DEPTH}, or {@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}, depending on {@code internalformat}. RGBA data is taken from the current color buffer, while depth
//     * component and stencil index data are taken from the depth and stencil buffers, respectively.</p>
//     *
//     * <p>Subsequent processing is identical to that described for {@link #glTexImage2D TexImage2D}, beginning with clamping of the R, G, B, A, or depth values, and masking
//     * of the stencil index values from the resulting pixel groups. Parameters {@code level}, {@code internalformat}, and {@code border} are specified using
//     * the same values, with the same meanings, as the corresponding arguments of {@link #glTexImage2D TexImage2D}.</p>
//     *
//     * <p>The constraints on width, height, and border are exactly those for the corresponding arguments of {@link #glTexImage2D TexImage2D}.</p>
//     *
//     * @param target         the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr></table>
//     * @param level          the level-of-detail number
//     * @param internalFormat the texture internal format. See {@link #glTexImage2D TexImage2D} for a list of supported formats.
//     * @param x              the left framebuffer pixel coordinate
//     * @param y              the lower framebuffer pixel coordinate
//     * @param width          the texture width
//     * @param height         the texture height
//     * @param border         the texture border width
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glCopyTexImage2D">Reference Page</a>
//     */
//    public static native void glCopyTexImage2D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLenum") int internalFormat, @NativeType("GLint") int x, @NativeType("GLint") int y, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLint") int border);
//
//    // --- [ glCopyTexSubImage1D ] ---
//
//    /**
//     * Respecifies a rectangular subregion of an existing texel array. No change is made to the {@code internalformat}, {@code width} or {@code border}
//     * parameters of the specified texel array, nor is any change made to texel values outside the specified subregion. See {@link #glCopyTexImage1D CopyTexImage1D} for more
//     * details.
//     *
//     * @param target  the texture target. Must be:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td></tr></table>
//     * @param level   the level-of-detail number
//     * @param xoffset the left texel coordinate of the texture subregion to update
//     * @param x       the left framebuffer pixel coordinate
//     * @param y       the lower framebuffer pixel coordinate
//     * @param width   the texture subregion width
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glCopyTexSubImage1D">Reference Page</a>
//     */
//    public static native void glCopyTexSubImage1D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLint") int x, @NativeType("GLint") int y, @NativeType("GLsizei") int width);
//
//    // --- [ glCopyTexSubImage2D ] ---
//
//    /**
//     * Respecifies a rectangular subregion of an existing texel array. No change is made to the {@code internalformat}, {@code width}, {@code height},
//     * or {@code border} parameters of the specified texel array, nor is any change made to texel values outside the specified subregion. See
//     * {@link #glCopyTexImage2D CopyTexImage2D} for more details.
//     *
//     * @param target  the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr></table>
//     * @param level   the level-of-detail number
//     * @param xoffset the left texel coordinate of the texture subregion to update
//     * @param yoffset the lower texel coordinate of the texture subregion to update
//     * @param x       the left framebuffer pixel coordinate
//     * @param y       the lower framebuffer pixel coordinate
//     * @param width   the texture subregion width
//     * @param height  the texture subregion height
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glCopyTexSubImage2D">Reference Page</a>
//     */
//    public static native void glCopyTexSubImage2D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLint") int yoffset, @NativeType("GLint") int x, @NativeType("GLint") int y, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height);
//
//    // --- [ glTexParameteri ] ---
//
//    /**
//     * Sets the integer value of a texture parameter, which controls how the texel array is treated when specified or changed, and when applied to a fragment.
//     *
//     * @param target the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td></tr><tr><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td><td>{@link GL40#GL_TEXTURE_CUBE_MAP_ARRAY TEXTURE_CUBE_MAP_ARRAY}</td></tr><tr><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE TEXTURE_2D_MULTISAMPLE}</td><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE_ARRAY TEXTURE_2D_MULTISAMPLE_ARRAY}</td></tr></table>
//     * @param pname  the parameter to set. One of:<br><table><tr><td>{@link GL12#GL_TEXTURE_BASE_LEVEL TEXTURE_BASE_LEVEL}</td><td>{@link #GL_TEXTURE_BORDER_COLOR TEXTURE_BORDER_COLOR}</td><td>{@link GL14#GL_TEXTURE_COMPARE_MODE TEXTURE_COMPARE_MODE}</td><td>{@link GL14#GL_TEXTURE_COMPARE_FUNC TEXTURE_COMPARE_FUNC}</td></tr><tr><td>{@link GL14#GL_TEXTURE_LOD_BIAS TEXTURE_LOD_BIAS}</td><td>{@link #GL_TEXTURE_MAG_FILTER TEXTURE_MAG_FILTER}</td><td>{@link GL12#GL_TEXTURE_MAX_LEVEL TEXTURE_MAX_LEVEL}</td><td>{@link GL12#GL_TEXTURE_MAX_LOD TEXTURE_MAX_LOD}</td></tr><tr><td>{@link #GL_TEXTURE_MIN_FILTER TEXTURE_MIN_FILTER}</td><td>{@link GL12#GL_TEXTURE_MIN_LOD TEXTURE_MIN_LOD}</td><td>{@link GL33#GL_TEXTURE_SWIZZLE_R TEXTURE_SWIZZLE_R}</td><td>{@link GL33#GL_TEXTURE_SWIZZLE_G TEXTURE_SWIZZLE_G}</td></tr><tr><td>{@link GL33#GL_TEXTURE_SWIZZLE_B TEXTURE_SWIZZLE_B}</td><td>{@link GL33#GL_TEXTURE_SWIZZLE_A TEXTURE_SWIZZLE_A}</td><td>{@link GL33#GL_TEXTURE_SWIZZLE_RGBA TEXTURE_SWIZZLE_RGBA}</td><td>{@link #GL_TEXTURE_WRAP_S TEXTURE_WRAP_S}</td></tr><tr><td>{@link #GL_TEXTURE_WRAP_T TEXTURE_WRAP_T}</td><td>{@link GL12#GL_TEXTURE_WRAP_R TEXTURE_WRAP_R}</td><td>{@link GL14#GL_DEPTH_TEXTURE_MODE DEPTH_TEXTURE_MODE}</td><td>{@link GL14#GL_GENERATE_MIPMAP GENERATE_MIPMAP}</td></tr></table>
//     * @param param  the parameter value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexParameteri">Reference Page</a>
//     */
//    public static native void glTexParameteri(@NativeType("GLenum") int target, @NativeType("GLenum") int pname, @NativeType("GLint") int param);
//
//    // --- [ glTexParameteriv ] ---
//
//    /** Unsafe version of: {@link #glTexParameteriv TexParameteriv} */
//    public static native void nglTexParameteriv(int target, int pname, long params);
//
//    /**
//     * Pointer version of {@link #glTexParameteri TexParameteri}.
//     *
//     * @param target the texture target
//     * @param pname  the parameter to set
//     * @param params the parameter value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexParameter">Reference Page</a>
//     */
//    public static void glTexParameteriv(@NativeType("GLenum") int target, @NativeType("GLenum") int pname, @NativeType("GLint const *") IntBuffer params) {
//        if (CHECKS) {
//            check(params, 4);
//        }
//        nglTexParameteriv(target, pname, memAddress(params));
//    }
//
//    // --- [ glTexParameterf ] ---
//
//    /**
//     * Float version of {@link #glTexParameteri TexParameteri}.
//     *
//     * @param target the texture target
//     * @param pname  the parameter to set
//     * @param param  the parameter value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexParameterf">Reference Page</a>
//     */
//    public static native void glTexParameterf(@NativeType("GLenum") int target, @NativeType("GLenum") int pname, @NativeType("GLfloat") float param);
//
//    // --- [ glTexParameterfv ] ---
//
//    /** Unsafe version of: {@link #glTexParameterfv TexParameterfv} */
//    public static native void nglTexParameterfv(int target, int pname, long params);
//
//    /**
//     * Pointer version of {@link #glTexParameterf TexParameterf}.
//     *
//     * @param target the texture target
//     * @param pname  the parameter to set
//     * @param params the parameter value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexParameter">Reference Page</a>
//     */
//    public static void glTexParameterfv(@NativeType("GLenum") int target, @NativeType("GLenum") int pname, @NativeType("GLfloat const *") FloatBuffer params) {
//        if (CHECKS) {
//            check(params, 4);
//        }
//        nglTexParameterfv(target, pname, memAddress(params));
//    }
//
//    // --- [ glTexSubImage1D ] ---
//
//    /** Unsafe version of: {@link #glTexSubImage1D TexSubImage1D} */
//    public static native void nglTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, long pixels);
//
//    /**
//     * One-dimensional version of {@link #glTexSubImage2D TexSubImage2D}.
//     *
//     * @param target  the texture target. Must be:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td></tr></table>
//     * @param level   the level-of-detail-number
//     * @param xoffset the left coordinate of the texel subregion
//     * @param width   the subregion width
//     * @param format  the pixel data format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type    the pixel data type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels  the pixel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexSubImage1D">Reference Page</a>
//     */
//    public static void glTexSubImage1D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLsizei") int width, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void const *") ByteBuffer pixels) {
//        nglTexSubImage1D(target, level, xoffset, width, format, type, memAddress(pixels));
//    }
//
//    /**
//     * One-dimensional version of {@link #glTexSubImage2D TexSubImage2D}.
//     *
//     * @param target  the texture target. Must be:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td></tr></table>
//     * @param level   the level-of-detail-number
//     * @param xoffset the left coordinate of the texel subregion
//     * @param width   the subregion width
//     * @param format  the pixel data format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type    the pixel data type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels  the pixel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexSubImage1D">Reference Page</a>
//     */
//    public static void glTexSubImage1D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLsizei") int width, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void const *") long pixels) {
//        nglTexSubImage1D(target, level, xoffset, width, format, type, pixels);
//    }
//
//    /**
//     * One-dimensional version of {@link #glTexSubImage2D TexSubImage2D}.
//     *
//     * @param target  the texture target. Must be:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td></tr></table>
//     * @param level   the level-of-detail-number
//     * @param xoffset the left coordinate of the texel subregion
//     * @param width   the subregion width
//     * @param format  the pixel data format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type    the pixel data type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels  the pixel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexSubImage1D">Reference Page</a>
//     */
//    public static void glTexSubImage1D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLsizei") int width, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void const *") ShortBuffer pixels) {
//        nglTexSubImage1D(target, level, xoffset, width, format, type, memAddress(pixels));
//    }
//
//    /**
//     * One-dimensional version of {@link #glTexSubImage2D TexSubImage2D}.
//     *
//     * @param target  the texture target. Must be:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td></tr></table>
//     * @param level   the level-of-detail-number
//     * @param xoffset the left coordinate of the texel subregion
//     * @param width   the subregion width
//     * @param format  the pixel data format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type    the pixel data type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels  the pixel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexSubImage1D">Reference Page</a>
//     */
//    public static void glTexSubImage1D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLsizei") int width, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void const *") IntBuffer pixels) {
//        nglTexSubImage1D(target, level, xoffset, width, format, type, memAddress(pixels));
//    }
//
//    /**
//     * One-dimensional version of {@link #glTexSubImage2D TexSubImage2D}.
//     *
//     * @param target  the texture target. Must be:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td></tr></table>
//     * @param level   the level-of-detail-number
//     * @param xoffset the left coordinate of the texel subregion
//     * @param width   the subregion width
//     * @param format  the pixel data format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type    the pixel data type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels  the pixel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexSubImage1D">Reference Page</a>
//     */
//    public static void glTexSubImage1D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLsizei") int width, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void const *") FloatBuffer pixels) {
//        nglTexSubImage1D(target, level, xoffset, width, format, type, memAddress(pixels));
//    }
//
//    /**
//     * One-dimensional version of {@link #glTexSubImage2D TexSubImage2D}.
//     *
//     * @param target  the texture target. Must be:<br><table><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td></tr></table>
//     * @param level   the level-of-detail-number
//     * @param xoffset the left coordinate of the texel subregion
//     * @param width   the subregion width
//     * @param format  the pixel data format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type    the pixel data type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels  the pixel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexSubImage1D">Reference Page</a>
//     */
//    public static void glTexSubImage1D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLsizei") int width, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void const *") DoubleBuffer pixels) {
//        nglTexSubImage1D(target, level, xoffset, width, format, type, memAddress(pixels));
//    }
//
//    // --- [ glTexSubImage2D ] ---
//
//    /** Unsafe version of: {@link #glTexSubImage2D TexSubImage2D} */
//    public static native void nglTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, long pixels);
//
//    /**
//     * Respecifies a rectangular subregion of an existing texel array. No change is made to the internalformat, width, height, depth, or border parameters of
//     * the specified texel array, nor is any change made to texel values outside the specified subregion.
//     *
//     * @param target  the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr></table>
//     * @param level   the level-of-detail-number
//     * @param xoffset the left coordinate of the texel subregion
//     * @param yoffset the bottom coordinate of the texel subregion
//     * @param width   the subregion width
//     * @param height  the subregion height
//     * @param format  the pixel data format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type    the pixel data type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels  the pixel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexSubImage2D">Reference Page</a>
//     */
//    public static void glTexSubImage2D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLint") int yoffset, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void const *") ByteBuffer pixels) {
//        nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, memAddress(pixels));
//    }
//
//    /**
//     * Respecifies a rectangular subregion of an existing texel array. No change is made to the internalformat, width, height, depth, or border parameters of
//     * the specified texel array, nor is any change made to texel values outside the specified subregion.
//     *
//     * @param target  the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr></table>
//     * @param level   the level-of-detail-number
//     * @param xoffset the left coordinate of the texel subregion
//     * @param yoffset the bottom coordinate of the texel subregion
//     * @param width   the subregion width
//     * @param height  the subregion height
//     * @param format  the pixel data format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type    the pixel data type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels  the pixel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexSubImage2D">Reference Page</a>
//     */
//    public static void glTexSubImage2D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLint") int yoffset, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void const *") long pixels) {
//        nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
//    }
//
//    /**
//     * Respecifies a rectangular subregion of an existing texel array. No change is made to the internalformat, width, height, depth, or border parameters of
//     * the specified texel array, nor is any change made to texel values outside the specified subregion.
//     *
//     * @param target  the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr></table>
//     * @param level   the level-of-detail-number
//     * @param xoffset the left coordinate of the texel subregion
//     * @param yoffset the bottom coordinate of the texel subregion
//     * @param width   the subregion width
//     * @param height  the subregion height
//     * @param format  the pixel data format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type    the pixel data type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels  the pixel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexSubImage2D">Reference Page</a>
//     */
//    public static void glTexSubImage2D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLint") int yoffset, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void const *") ShortBuffer pixels) {
//        nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, memAddress(pixels));
//    }
//
//    /**
//     * Respecifies a rectangular subregion of an existing texel array. No change is made to the internalformat, width, height, depth, or border parameters of
//     * the specified texel array, nor is any change made to texel values outside the specified subregion.
//     *
//     * @param target  the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr></table>
//     * @param level   the level-of-detail-number
//     * @param xoffset the left coordinate of the texel subregion
//     * @param yoffset the bottom coordinate of the texel subregion
//     * @param width   the subregion width
//     * @param height  the subregion height
//     * @param format  the pixel data format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type    the pixel data type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels  the pixel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexSubImage2D">Reference Page</a>
//     */
//    public static void glTexSubImage2D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLint") int yoffset, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void const *") IntBuffer pixels) {
//        nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, memAddress(pixels));
//    }
//
//    /**
//     * Respecifies a rectangular subregion of an existing texel array. No change is made to the internalformat, width, height, depth, or border parameters of
//     * the specified texel array, nor is any change made to texel values outside the specified subregion.
//     *
//     * @param target  the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr></table>
//     * @param level   the level-of-detail-number
//     * @param xoffset the left coordinate of the texel subregion
//     * @param yoffset the bottom coordinate of the texel subregion
//     * @param width   the subregion width
//     * @param height  the subregion height
//     * @param format  the pixel data format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type    the pixel data type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels  the pixel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexSubImage2D">Reference Page</a>
//     */
//    public static void glTexSubImage2D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLint") int yoffset, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void const *") FloatBuffer pixels) {
//        nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, memAddress(pixels));
//    }
//
//    /**
//     * Respecifies a rectangular subregion of an existing texel array. No change is made to the internalformat, width, height, depth, or border parameters of
//     * the specified texel array, nor is any change made to texel values outside the specified subregion.
//     *
//     * @param target  the texture target. One of:<br><table><tr><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr></table>
//     * @param level   the level-of-detail-number
//     * @param xoffset the left coordinate of the texel subregion
//     * @param yoffset the bottom coordinate of the texel subregion
//     * @param width   the subregion width
//     * @param height  the subregion height
//     * @param format  the pixel data format. One of:<br><table><tr><td>{@link #GL_RED RED}</td><td>{@link #GL_GREEN GREEN}</td><td>{@link #GL_BLUE BLUE}</td><td>{@link #GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link #GL_RGB RGB}</td><td>{@link #GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link #GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link #GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type    the pixel data type. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_BYTE BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_SHORT SHORT}</td></tr><tr><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link #GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link #GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels  the pixel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexSubImage2D">Reference Page</a>
//     */
//    public static void glTexSubImage2D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLint") int yoffset, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void const *") DoubleBuffer pixels) {
//        nglTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, memAddress(pixels));
//    }
//
//    // --- [ glViewport ] ---
//
//    /**
//     * Specifies the viewport transformation parameters for all viewports.
//     *
//     * <p>The location of the viewport's bottom-left corner, given by {@code (x, y)}, are clamped to be within the implementation-dependent viewport bounds range.
//     * The viewport bounds range {@code [min, max]} tuple may be determined by calling {@link #glGetFloatv GetFloatv} with the symbolic
//     * constant {@link GL41#GL_VIEWPORT_BOUNDS_RANGE VIEWPORT_BOUNDS_RANGE}. Viewport width and height are clamped to implementation-dependent maximums when specified. The maximum
//     * width and height may be found by calling {@link #glGetFloatv GetFloatv} with the symbolic constant {@link #GL_MAX_VIEWPORT_DIMS MAX_VIEWPORT_DIMS}. The
//     * maximum viewport dimensions must be greater than or equal to the larger of the visible dimensions of the display being rendered to (if a display
//     * exists), and the largest renderbuffer image which can be successfully created and attached to a framebuffer object.</p>
//     *
//     * <p>In the initial state, {@code w} and {@code h} for each viewport are set to the width and height, respectively, of the window into which the GL is to do
//     * its rendering. If the default framebuffer is bound but no default framebuffer is associated with the GL context, then {@code w} and {@code h} are
//     * initially set to zero.</p>
//     *
//     * @param x the left viewport coordinate
//     * @param y the bottom viewport coordinate
//     * @param w the viewport width
//     * @param h the viewport height
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glViewport">Reference Page</a>
//     */
//    public static native void glViewport(@NativeType("GLint") int x, @NativeType("GLint") int y, @NativeType("GLsizei") int w, @NativeType("GLsizei") int h);
//
//    /**
//     * Array version of: {@link #glGenTextures GenTextures}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGenTextures">Reference Page</a>
//     */
//    public static void glGenTextures(@NativeType("GLuint *") int[] textures) {
//        long __functionAddress = GL.getICD().glGenTextures;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, textures.length, textures);
//    }
//
//    /**
//     * Array version of: {@link #glDeleteTextures DeleteTextures}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteTextures">Reference Page</a>
//     */
//    public static void glDeleteTextures(@NativeType("GLuint const *") int[] textures) {
//        long __functionAddress = GL.getICD().glDeleteTextures;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, textures.length, textures);
//    }
//
//    /**
//     * Array version of: {@link #glGetFloatv GetFloatv}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetFloatv">Reference Page</a>
//     */
//    public static void glGetFloatv(@NativeType("GLenum") int pname, @NativeType("GLfloat *") float[] params) {
//        long __functionAddress = GL.getICD().glGetFloatv;
//        if (CHECKS) {
//            check(__functionAddress);
//            check(params, 1);
//        }
//        callPV(__functionAddress, pname, params);
//    }
//
//    /**
//     * Array version of: {@link #glGetIntegerv GetIntegerv}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetIntegerv">Reference Page</a>
//     */
//    public static void glGetIntegerv(@NativeType("GLenum") int pname, @NativeType("GLint *") int[] params) {
//        long __functionAddress = GL.getICD().glGetIntegerv;
//        if (CHECKS) {
//            check(__functionAddress);
//            check(params, 1);
//        }
//        callPV(__functionAddress, pname, params);
//    }
//
//    /**
//     * Array version of: {@link #glGetDoublev GetDoublev}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetDoublev">Reference Page</a>
//     */
//    public static void glGetDoublev(@NativeType("GLenum") int pname, @NativeType("GLdouble *") double[] params) {
//        long __functionAddress = GL.getICD().glGetDoublev;
//        if (CHECKS) {
//            check(__functionAddress);
//            check(params, 1);
//        }
//        callPV(__functionAddress, pname, params);
//    }
//
//    /**
//     * Array version of: {@link #glGetTexImage GetTexImage}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexImage">Reference Page</a>
//     */
//    public static void glGetTexImage(@NativeType("GLenum") int tex, @NativeType("GLint") int level, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") short[] pixels) {
//        long __functionAddress = GL.getICD().glGetTexImage;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, tex, level, format, type, pixels);
//    }
//
//    /**
//     * Array version of: {@link #glGetTexImage GetTexImage}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexImage">Reference Page</a>
//     */
//    public static void glGetTexImage(@NativeType("GLenum") int tex, @NativeType("GLint") int level, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") int[] pixels) {
//        long __functionAddress = GL.getICD().glGetTexImage;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, tex, level, format, type, pixels);
//    }
//
//    /**
//     * Array version of: {@link #glGetTexImage GetTexImage}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexImage">Reference Page</a>
//     */
//    public static void glGetTexImage(@NativeType("GLenum") int tex, @NativeType("GLint") int level, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") float[] pixels) {
//        long __functionAddress = GL.getICD().glGetTexImage;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, tex, level, format, type, pixels);
//    }
//
//    /**
//     * Array version of: {@link #glGetTexImage GetTexImage}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexImage">Reference Page</a>
//     */
//    public static void glGetTexImage(@NativeType("GLenum") int tex, @NativeType("GLint") int level, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") double[] pixels) {
//        long __functionAddress = GL.getICD().glGetTexImage;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, tex, level, format, type, pixels);
//    }
//
//    /**
//     * Array version of: {@link #glGetTexLevelParameteriv GetTexLevelParameteriv}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexLevelParameter">Reference Page</a>
//     */
//    public static void glGetTexLevelParameteriv(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLenum") int pname, @NativeType("GLint *") int[] params) {
//        long __functionAddress = GL.getICD().glGetTexLevelParameteriv;
//        if (CHECKS) {
//            check(__functionAddress);
//            check(params, 1);
//        }
//        callPV(__functionAddress, target, level, pname, params);
//    }
//
//    /**
//     * Array version of: {@link #glGetTexLevelParameterfv GetTexLevelParameterfv}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexLevelParameter">Reference Page</a>
//     */
//    public static void glGetTexLevelParameterfv(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLenum") int pname, @NativeType("GLfloat *") float[] params) {
//        long __functionAddress = GL.getICD().glGetTexLevelParameterfv;
//        if (CHECKS) {
//            check(__functionAddress);
//            check(params, 1);
//        }
//        callPV(__functionAddress, target, level, pname, params);
//    }
//
//    /**
//     * Array version of: {@link #glGetTexParameteriv GetTexParameteriv}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexParameter">Reference Page</a>
//     */
//    public static void glGetTexParameteriv(@NativeType("GLenum") int target, @NativeType("GLenum") int pname, @NativeType("GLint *") int[] params) {
//        long __functionAddress = GL.getICD().glGetTexParameteriv;
//        if (CHECKS) {
//            check(__functionAddress);
//            check(params, 1);
//        }
//        callPV(__functionAddress, target, pname, params);
//    }
//
//    /**
//     * Array version of: {@link #glGetTexParameterfv GetTexParameterfv}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexParameter">Reference Page</a>
//     */
//    public static void glGetTexParameterfv(@NativeType("GLenum") int target, @NativeType("GLenum") int pname, @NativeType("GLfloat *") float[] params) {
//        long __functionAddress = GL.getICD().glGetTexParameterfv;
//        if (CHECKS) {
//            check(__functionAddress);
//            check(params, 1);
//        }
//        callPV(__functionAddress, target, pname, params);
//    }
//
//    /**
//     * Array version of: {@link #glReadPixels ReadPixels}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glReadPixels">Reference Page</a>
//     */
//    public static void glReadPixels(@NativeType("GLint") int x, @NativeType("GLint") int y, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") short[] pixels) {
//        long __functionAddress = GL.getICD().glReadPixels;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, x, y, width, height, format, type, pixels);
//    }
//
//    /**
//     * Array version of: {@link #glReadPixels ReadPixels}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glReadPixels">Reference Page</a>
//     */
//    public static void glReadPixels(@NativeType("GLint") int x, @NativeType("GLint") int y, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") int[] pixels) {
//        long __functionAddress = GL.getICD().glReadPixels;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, x, y, width, height, format, type, pixels);
//    }
//
//    /**
//     * Array version of: {@link #glReadPixels ReadPixels}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glReadPixels">Reference Page</a>
//     */
//    public static void glReadPixels(@NativeType("GLint") int x, @NativeType("GLint") int y, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") float[] pixels) {
//        long __functionAddress = GL.getICD().glReadPixels;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, x, y, width, height, format, type, pixels);
//    }
//
//    /**
//     * Array version of: {@link #glTexImage1D TexImage1D}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexImage1D">Reference Page</a>
//     */
//    public static void glTexImage1D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int internalformat, @NativeType("GLsizei") int width, @NativeType("GLint") int border, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @Nullable @NativeType("void const *") short[] pixels) {
//        long __functionAddress = GL.getICD().glTexImage1D;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, target, level, internalformat, width, border, format, type, pixels);
//    }
//
//    /**
//     * Array version of: {@link #glTexImage1D TexImage1D}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexImage1D">Reference Page</a>
//     */
//    public static void glTexImage1D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int internalformat, @NativeType("GLsizei") int width, @NativeType("GLint") int border, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @Nullable @NativeType("void const *") int[] pixels) {
//        long __functionAddress = GL.getICD().glTexImage1D;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, target, level, internalformat, width, border, format, type, pixels);
//    }
//
//    /**
//     * Array version of: {@link #glTexImage1D TexImage1D}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexImage1D">Reference Page</a>
//     */
//    public static void glTexImage1D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int internalformat, @NativeType("GLsizei") int width, @NativeType("GLint") int border, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @Nullable @NativeType("void const *") float[] pixels) {
//        long __functionAddress = GL.getICD().glTexImage1D;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, target, level, internalformat, width, border, format, type, pixels);
//    }
//
//    /**
//     * Array version of: {@link #glTexImage1D TexImage1D}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexImage1D">Reference Page</a>
//     */
//    public static void glTexImage1D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int internalformat, @NativeType("GLsizei") int width, @NativeType("GLint") int border, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @Nullable @NativeType("void const *") double[] pixels) {
//        long __functionAddress = GL.getICD().glTexImage1D;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, target, level, internalformat, width, border, format, type, pixels);
//    }
//
//    /**
//     * Array version of: {@link #glTexImage2D TexImage2D}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexImage2D">Reference Page</a>
//     */
//    public static void glTexImage2D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int internalformat, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLint") int border, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @Nullable @NativeType("void const *") short[] pixels) {
//        long __functionAddress = GL.getICD().glTexImage2D;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, target, level, internalformat, width, height, border, format, type, pixels);
//    }
//
//    /**
//     * Array version of: {@link #glTexImage2D TexImage2D}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexImage2D">Reference Page</a>
//     */
//    public static void glTexImage2D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int internalformat, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLint") int border, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @Nullable @NativeType("void const *") int[] pixels) {
//        long __functionAddress = GL.getICD().glTexImage2D;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, target, level, internalformat, width, height, border, format, type, pixels);
//    }
//
//    /**
//     * Array version of: {@link #glTexImage2D TexImage2D}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexImage2D">Reference Page</a>
//     */
//    public static void glTexImage2D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int internalformat, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLint") int border, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @Nullable @NativeType("void const *") float[] pixels) {
//        long __functionAddress = GL.getICD().glTexImage2D;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, target, level, internalformat, width, height, border, format, type, pixels);
//    }
//
//    /**
//     * Array version of: {@link #glTexImage2D TexImage2D}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexImage2D">Reference Page</a>
//     */
//    public static void glTexImage2D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int internalformat, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLint") int border, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @Nullable @NativeType("void const *") double[] pixels) {
//        long __functionAddress = GL.getICD().glTexImage2D;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, target, level, internalformat, width, height, border, format, type, pixels);
//    }
//
//    /**
//     * Array version of: {@link #glTexParameteriv TexParameteriv}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexParameter">Reference Page</a>
//     */
//    public static void glTexParameteriv(@NativeType("GLenum") int target, @NativeType("GLenum") int pname, @NativeType("GLint const *") int[] params) {
//        long __functionAddress = GL.getICD().glTexParameteriv;
//        if (CHECKS) {
//            check(__functionAddress);
//            check(params, 4);
//        }
//        callPV(__functionAddress, target, pname, params);
//    }
//
//    /**
//     * Array version of: {@link #glTexParameterfv TexParameterfv}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexParameter">Reference Page</a>
//     */
//    public static void glTexParameterfv(@NativeType("GLenum") int target, @NativeType("GLenum") int pname, @NativeType("GLfloat const *") float[] params) {
//        long __functionAddress = GL.getICD().glTexParameterfv;
//        if (CHECKS) {
//            check(__functionAddress);
//            check(params, 4);
//        }
//        callPV(__functionAddress, target, pname, params);
//    }
//
//    /**
//     * Array version of: {@link #glTexSubImage1D TexSubImage1D}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexSubImage1D">Reference Page</a>
//     */
//    public static void glTexSubImage1D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLsizei") int width, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void const *") short[] pixels) {
//        long __functionAddress = GL.getICD().glTexSubImage1D;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, target, level, xoffset, width, format, type, pixels);
//    }
//
//    /**
//     * Array version of: {@link #glTexSubImage1D TexSubImage1D}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexSubImage1D">Reference Page</a>
//     */
//    public static void glTexSubImage1D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLsizei") int width, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void const *") int[] pixels) {
//        long __functionAddress = GL.getICD().glTexSubImage1D;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, target, level, xoffset, width, format, type, pixels);
//    }
//
//    /**
//     * Array version of: {@link #glTexSubImage1D TexSubImage1D}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexSubImage1D">Reference Page</a>
//     */
//    public static void glTexSubImage1D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLsizei") int width, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void const *") float[] pixels) {
//        long __functionAddress = GL.getICD().glTexSubImage1D;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, target, level, xoffset, width, format, type, pixels);
//    }
//
//    /**
//     * Array version of: {@link #glTexSubImage1D TexSubImage1D}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexSubImage1D">Reference Page</a>
//     */
//    public static void glTexSubImage1D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLsizei") int width, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void const *") double[] pixels) {
//        long __functionAddress = GL.getICD().glTexSubImage1D;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, target, level, xoffset, width, format, type, pixels);
//    }
//
//    /**
//     * Array version of: {@link #glTexSubImage2D TexSubImage2D}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexSubImage2D">Reference Page</a>
//     */
//    public static void glTexSubImage2D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLint") int yoffset, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void const *") short[] pixels) {
//        long __functionAddress = GL.getICD().glTexSubImage2D;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, target, level, xoffset, yoffset, width, height, format, type, pixels);
//    }
//
//    /**
//     * Array version of: {@link #glTexSubImage2D TexSubImage2D}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexSubImage2D">Reference Page</a>
//     */
//    public static void glTexSubImage2D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLint") int yoffset, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void const *") int[] pixels) {
//        long __functionAddress = GL.getICD().glTexSubImage2D;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, target, level, xoffset, yoffset, width, height, format, type, pixels);
//    }
//
//    /**
//     * Array version of: {@link #glTexSubImage2D TexSubImage2D}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexSubImage2D">Reference Page</a>
//     */
//    public static void glTexSubImage2D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLint") int yoffset, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void const *") float[] pixels) {
//        long __functionAddress = GL.getICD().glTexSubImage2D;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, target, level, xoffset, yoffset, width, height, format, type, pixels);
//    }
//
//    /**
//     * Array version of: {@link #glTexSubImage2D TexSubImage2D}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexSubImage2D">Reference Page</a>
//     */
//    public static void glTexSubImage2D(@NativeType("GLenum") int target, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLint") int yoffset, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void const *") double[] pixels) {
//        long __functionAddress = GL.getICD().glTexSubImage2D;
//        if (CHECKS) {
//            check(__functionAddress);
//        }
//        callPV(__functionAddress, target, level, xoffset, yoffset, width, height, format, type, pixels);
//    }

}