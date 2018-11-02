/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package gln

import kool.adr
import kool.rem
import glm_.vec4.Vec4
import org.lwjgl.PointerBuffer
import org.lwjgl.opengl.ARBImaging
import org.lwjgl.opengl.GL11C
import org.lwjgl.opengl.GL14C
import org.lwjgl.opengl.GL20
import java.nio.IntBuffer


/**
 * The OpenGL functionality of a forward compatible context, up to version 1.4.
 *
 * <p>Extensions promoted to core in this release:</p>
 *
 * <ul>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/NV/NV_blend_square.txt">NV_blend_square</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_depth_texture.txt">ARB_depth_texture</a> and <a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_shadow.txt">ARB_shadow</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_fog_coord.txt">EXT_fog_coord</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_multi_draw_arrays.txt">EXT_multi_draw_arrays</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_point_parameters.txt">ARB_point_parameters</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_blend_func_separate.txt">EXT_blend_func_separate</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_stencil_wrap.txt">EXT_stencil_wrap</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_texture_env_crossbar.txt">ARB_texture_env_crossbar</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_texture_lod_bias.txt">EXT_texture_lod_bias</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_texture_mirrored_repeat.txt">ARB_texture_mirrored_repeat</a></li>
 * </ul>
 */
interface gl14i {

    // --- [ glBlendColor ] ---

    /**
     * Specifies the constant color C<sub>c</sub> to be used in blending.
     *
     * @param red   the red color component
     * @param green the green color component
     * @param blue  the blue color component
     * @param alpha the alpha color component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBlendColor">Reference Page</a>
     */
    fun blendColor(red: Float, green: Float, blue: Float, alpha: Float) = GL14C.glBlendColor(red, green, blue, alpha)

    /**
     * Specifies the constant color C<sub>c</sub> to be used in blending.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBlendColor">Reference Page</a>
     */
    var blendColor: Vec4
        get() = glGetVec4(ARBImaging.GL_BLEND_COLOR)
        set(value) = GL14C.glBlendColor(value.r, value.g, value.b, value.a)

    // --- [ glBlendEquation ] ---

    /**
     * Controls the blend equations used for per-fragment blending.
     *
     * @param mode the blend equation. One of:<br><table><tr><td>{@link #GL_FUNC_ADD FUNC_ADD}</td><td>{@link #GL_FUNC_SUBTRACT FUNC_SUBTRACT}</td><td>{@link #GL_FUNC_REVERSE_SUBTRACT FUNC_REVERSE_SUBTRACT}</td><td>{@link #GL_MIN MIN}</td><td>{@link #GL_MAX MAX}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBlendEquation">Reference Page</a>
     */
    fun blendEquation(mode: BlendEquationMode) = GL14C.glBlendEquation(mode.i)

    // --- [ glMultiDrawArrays ] ---

    /**
     * Renders multiple sets of primitives from array data.
     *
     * @param mode  the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
     * @param first an array of starting indices in the enabled arrays
     * @param count an array of the number of indices to be rendered
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glMultiDrawArrays">Reference Page</a>
     */
    fun multiDrawArrays(mode: DrawMode, first: IntBuffer, count: IntBuffer) {
        GL14C.nglMultiDrawArrays(mode.i, first.adr, count.adr, first.rem)
    }

    /**
     * Renders multiple sets of primitives from array data.
     *
     * @param first an array of starting indices in the enabled arrays
     * @param count an array of the number of indices to be rendered
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glMultiDrawArrays">Reference Page</a>
     */
    fun multiDrawArrays(first: IntBuffer, count: IntBuffer) {
        GL14C.nglMultiDrawArrays(GL11C.GL_TRIANGLES, first.adr, count.adr, first.rem)
    }

    // --- [ glMultiDrawElements ] ---

    /**
     * Renders multiple sets of primitives by specifying indices of array data elements.
     *
     * <p><b>LWJGL note</b>: Use {@link org.lwjgl.system.MemoryUtil#memAddress} to retrieve pointers to the index buffers.</p>
     *
     * @param mode    the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
     * @param count   an array of the elements counts
     * @param type    the type of the values in indices. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param indices a pointer to the location where the indices are stored
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glMultiDrawElements">Reference Page</a>
     */
    fun multiDrawElements(mode: DrawMode, count: IntBuffer, type: DataType, indices: PointerBuffer) {
        GL14C.nglMultiDrawElements(mode.i, count.adr, type.i, indices.adr, count.rem)
    }

    /**
     * Renders multiple sets of primitives by specifying indices of array data elements.
     *
     * <p><b>LWJGL note</b>: Use {@link org.lwjgl.system.MemoryUtil#memAddress} to retrieve pointers to the index buffers.</p>
     *
     * @param count   an array of the elements counts
     * @param type    the type of the values in indices. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param indices a pointer to the location where the indices are stored
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glMultiDrawElements">Reference Page</a>
     */
    fun multiDrawElements(count: IntBuffer, type: DataType, indices: PointerBuffer) {
        GL14C.nglMultiDrawElements(GL11C.GL_TRIANGLES, count.adr, type.i, indices.adr, count.rem)
    }

    // --- [ glPointParameterf ] ---

    /**
     * Sets the float value of a pointer parameter.
     *
     * @param pname the parameter to set. Must be:<br><table><tr><td>{@link #GL_POINT_FADE_THRESHOLD_SIZE POINT_FADE_THRESHOLD_SIZE}</td></tr></table>
     * @param param the parameter value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glPointParameterf">Reference Page</a>
     */
    var pointFadeThresholdSize: Float
        get() = GL11C.glGetFloat(GL14C.GL_POINT_FADE_THRESHOLD_SIZE)
        set(value) = GL14C.glPointParameterf(GL14C.GL_POINT_FADE_THRESHOLD_SIZE, value)

    // --- [ glPointParameteri ] ---

    /**
     * Integer version of {@link #glPointParameterf PointParameterf}.
     *
     * @param pname the parameter to set. Must be:<br><table><tr><td>{@link #GL_POINT_FADE_THRESHOLD_SIZE POINT_FADE_THRESHOLD_SIZE}</td></tr></table>
     * @param param the parameter value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glPointParameteri">Reference Page</a>
     */
    var pointSpriteCoordOrigin: PointSpriteCoordOrigin
        get() = PointSpriteCoordOrigin(GL11C.glGetInteger(GL20.GL_POINT_SPRITE_COORD_ORIGIN))
        set(value) = GL14C.glPointParameteri(GL20.GL_POINT_SPRITE_COORD_ORIGIN, value.i)

    // --- [ glPointParameterfv ] ---

//    /** Unsafe version of: {@link #glPointParameterfv PointParameterfv} */
//    public static native void nglPointParameterfv(int pname, long params);
//
//    /**
//     * Pointer version of {@link #glPointParameterf PointParameterf}.
//     *
//     * @param pname  the parameter to set
//     * @param params the parameter value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glPointParameter">Reference Page</a>
//     */
//    public static void glPointParameterfv(@NativeType("GLenum") int pname, @NativeType("GLfloat const *") FloatBuffer params)
//    {
//        if (CHECKS) {
//            check(params, 3);
//        }
//        nglPointParameterfv(pname, memAddress(params));
//    }
//
//    // --- [ glPointParameteriv ] ---
//
//    /** Unsafe version of: {@link #glPointParameteriv PointParameteriv} */
//    public static native void nglPointParameteriv(int pname, long params);
//
//    /**
//     * Pointer version of {@link #glPointParameteri PointParameteri}.
//     *
//     * @param pname  the parameter to set
//     * @param params the parameter value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glPointParameter">Reference Page</a>
//     */
//    public static void glPointParameteriv(@NativeType("GLenum") int pname, @NativeType("GLint const *") IntBuffer params)
//    {
//        if (CHECKS) {
//            check(params, 3);
//        }
//        nglPointParameteriv(pname, memAddress(params));
//    }

    // --- [ glBlendFuncSeparate ] ---

    /**
     * Specifies pixel arithmetic for RGB and alpha components separately.
     *
     * @param sFactorRGB   how the red, green, and blue blending factors are computed. The initial value is GL_ONE.
     * @param dFactorRGB   how the red, green, and blue destination blending factors are computed. The initial value is GL_ZERO.
     * @param sFactorAlpha how the alpha source blending factor is computed. The initial value is GL_ONE.
     * @param dFactorAlpha how the alpha destination blending factor is computed. The initial value is GL_ZERO.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBlendFuncSeparate">Reference Page</a>
     */
    fun blendFuncSeparate(sFactorRGB: BlendEquationMode, dFactorRGB: BlendEquationMode, sFactorAlpha: BlendEquationMode, dFactorAlpha: BlendEquationMode) {
        GL14C.glBlendFuncSeparate(sFactorRGB.i, dFactorRGB.i, sFactorAlpha.i, dFactorAlpha.i)
    }
}