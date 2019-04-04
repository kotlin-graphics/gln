package gln

import gli_.gl
import glm_.bool
import glm_.vec2.Vec2i
import glm_.vec3.Vec3i
import gln.identifiers.GlTexture
import kool.Ptr
import kool.adr
import kool.rem
import kool.Stack
import org.lwjgl.PointerBuffer
import org.lwjgl.opengl.GL32C
import org.lwjgl.system.MemoryUtil.NULL
import java.nio.IntBuffer

/**
 * The OpenGL functionality up to version 3.2. Includes only Core Profile symbols.
 *
 * <p>OpenGL 3.2 implementations support revision 1.50 of the OpenGL Shading Language.</p>
 *
 * <p>Extensions promoted to core in this release:</p>
 *
 * <ul>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_vertex_array_bgra.txt">ARB_vertex_array_bgra</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_draw_elements_base_vertex.txt">ARB_draw_elements_base_vertex</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_fragment_coord_conventions.txt">ARB_fragment_coord_conventions</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_provoking_vertex.txt">ARB_provoking_vertex</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_seamless_cube_map.txt">ARB_seamless_cube_map</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_texture_multisample.txt">ARB_texture_multisample</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_depth_clamp.txt">ARB_depth_clamp</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_geometry_shader4.txt">ARB_geometry_shader4</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_sync.txt">ARB_sync</a></li>
 * </ul>
 */

interface gl32i {

    // --- [ glGetBufferParameteri64v ] ---
    // inline reified

    // --- [ glDrawElementsBaseVertex ] ---

    /**
     * Renders primitives from array data with a per-element offset.
     *
     * @param mode       the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link #GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link #GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link #GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link #GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
     * @param count      the number of elements to be rendered
     * @param type       the type of the values in {@code indices}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param indices    a pointer to the location where the indices are stored
     * @param baseVertex a constant that should be added to each element of {@code indices} when choosing elements from the enabled vertex arrays
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsBaseVertex">Reference Page</a>
     */
    fun drawElementsBaseVertex(mode: DrawMode, count: Int, type: IndexType, indices: Ptr, baseVertex: Int) =
            GL32C.nglDrawElementsBaseVertex(mode.i, count, type.i, indices, baseVertex)

    /**
     * Renders primitives from array data with a per-element offset.
     *
     * @param mode       the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link #GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link #GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link #GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link #GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
     * @param count      the number of elements to be rendered
     * @param type       the type of the values in {@code indices}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param indices    a pointer to the location where the indices are stored
     * @param baseVertex a constant that should be added to each element of {@code indices} when choosing elements from the enabled vertex arrays
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsBaseVertex">Reference Page</a>
     */
    fun drawElementsBaseVertex(count: Int, type: IndexType, indices: Ptr, baseVertex: Int) =
            GL32C.nglDrawElementsBaseVertex(DrawMode.TRIANGLES.i, count, type.i, indices, baseVertex)

//    /** TODO?
//     * Renders primitives from array data with a per-element offset.
//     *
//     * @param mode       the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link #GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link #GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link #GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link #GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
//     * @param type       the type of the values in {@code indices}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
//     * @param indices    a pointer to the location where the indices are stored
//     * @param basevertex a constant that should be added to each element of {@code indices} when choosing elements from the enabled vertex arrays
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsBaseVertex">Reference Page</a>
//     */
//    public static void glDrawElementsBaseVertex(@NativeType("GLenum") int mode, @NativeType("GLenum") int type, @NativeType("void const *") ByteBuffer indices, @NativeType("GLint") int basevertex) {
//        nglDrawElementsBaseVertex(mode, indices.remaining() >> GLChecks.typeToByteShift(type), type, memAddress(indices), basevertex);
//    }
//
//    /**
//     * Renders primitives from array data with a per-element offset.
//     *
//     * @param mode       the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link #GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link #GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link #GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link #GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
//     * @param indices    a pointer to the location where the indices are stored
//     * @param basevertex a constant that should be added to each element of {@code indices} when choosing elements from the enabled vertex arrays
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsBaseVertex">Reference Page</a>
//     */
//    public static void glDrawElementsBaseVertex(@NativeType("GLenum") int mode, @NativeType("void const *") ByteBuffer indices, @NativeType("GLint") int basevertex) {
//        nglDrawElementsBaseVertex(mode, indices.remaining(), GL11.GL_UNSIGNED_BYTE, memAddress(indices), basevertex);
//    }
//
//    /**
//     * Renders primitives from array data with a per-element offset.
//     *
//     * @param mode       the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link #GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link #GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link #GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link #GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
//     * @param indices    a pointer to the location where the indices are stored
//     * @param basevertex a constant that should be added to each element of {@code indices} when choosing elements from the enabled vertex arrays
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsBaseVertex">Reference Page</a>
//     */
//    public static void glDrawElementsBaseVertex(@NativeType("GLenum") int mode, @NativeType("void const *") ShortBuffer indices, @NativeType("GLint") int basevertex) {
//        nglDrawElementsBaseVertex(mode, indices.remaining(), GL11.GL_UNSIGNED_SHORT, memAddress(indices), basevertex);
//    }
//
//    /**
//     * Renders primitives from array data with a per-element offset.
//     *
//     * @param mode       the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link #GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link #GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link #GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link #GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
//     * @param indices    a pointer to the location where the indices are stored
//     * @param basevertex a constant that should be added to each element of {@code indices} when choosing elements from the enabled vertex arrays
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsBaseVertex">Reference Page</a>
//     */
//    public static void glDrawElementsBaseVertex(@NativeType("GLenum") int mode, @NativeType("void const *") IntBuffer indices, @NativeType("GLint") int basevertex) {
//        nglDrawElementsBaseVertex(mode, indices.remaining(), GL11.GL_UNSIGNED_INT, memAddress(indices), basevertex);
//    }

    // --- [ glDrawRangeElementsBaseVertex ] ---

    /**
     * Renders primitives from array data with a per-element offset.
     *
     * @param mode       the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link #GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link #GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link #GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link #GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
     * @param start      the minimum array index contained in {@code indices}
     * @param end        the maximum array index contained in {@code indices}
     * @param count      the number of elements to be rendered
     * @param type       the type of the values in {@code indices}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param indices    a pointer to the location where the indices are stored
     * @param baseVertex a constant that should be added to each element of {@code indices} when choosing elements from the enabled vertex arrays
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawRangeElementsBaseVertex">Reference Page</a>
     */
    fun drawRangeElementsBaseVertex(mode: DrawMode, start: Int, end: Int, count: Int, type: IndexType, indices: Ptr, baseVertex: Int) =
            GL32C.nglDrawRangeElementsBaseVertex(mode.i, start, end, count, type.i, indices, baseVertex)

    /**
     * Renders primitives from array data with a per-element offset.
     *
     * @param start      the minimum array index contained in {@code indices}
     * @param end        the maximum array index contained in {@code indices}
     * @param count      the number of elements to be rendered
     * @param type       the type of the values in {@code indices}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param indices    a pointer to the location where the indices are stored
     * @param baseVertex a constant that should be added to each element of {@code indices} when choosing elements from the enabled vertex arrays
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawRangeElementsBaseVertex">Reference Page</a>
     */
    fun drawRangeElementsBaseVertex(start: Int, end: Int, count: Int, type: IndexType, indices: Ptr, baseVertex: Int) =
            GL32C.nglDrawRangeElementsBaseVertex(DrawMode.TRIANGLES.i, start, end, count, type.i, indices, baseVertex)

    /**
     * Renders primitives from array data with a per-element offset.
     *
     * @param mode       the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link #GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link #GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link #GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link #GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
     * @param start      the minimum array index contained in {@code indices}
     * @param end        the maximum array index contained in {@code indices}
     * @param count      the number of elements to be rendered
     * @param type       the type of the values in {@code indices}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param indices    a pointer to the location where the indices are stored
     * @param baseVertex a constant that should be added to each element of {@code indices} when choosing elements from the enabled vertex arrays
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawRangeElementsBaseVertex">Reference Page</a>
     */
//    fun drawRangeElementsBaseVertex(start: Int, end: Int, count: Int, type: IndexType, indices: Ptr, baseVertex: Int) =
//        GL32C.nglDrawRangeElementsBaseVertex(mode.i, start, end, count, type.i, indices, baseVertex)

//    /**
//     * Renders primitives from array data with a per-element offset. TODO?
//     *
//     * @param mode       the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link #GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link #GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link #GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link #GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
//     * @param start      the minimum array index contained in {@code indices}
//     * @param end        the maximum array index contained in {@code indices}
//     * @param type       the type of the values in {@code indices}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
//     * @param indices    a pointer to the location where the indices are stored
//     * @param basevertex a constant that should be added to each element of {@code indices} when choosing elements from the enabled vertex arrays
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawRangeElementsBaseVertex">Reference Page</a>
//     */
//    public static void glDrawRangeElementsBaseVertex(@NativeType("GLenum") int mode, @NativeType("GLuint") int start, @NativeType("GLuint") int end, @NativeType("GLenum") int type, @NativeType("void const *") ByteBuffer indices, @NativeType("GLint") int basevertex) {
//        nglDrawRangeElementsBaseVertex(mode, start, end, indices.remaining() >> GLChecks.typeToByteShift(type), type, memAddress(indices), basevertex);
//    }
//
//    /**
//     * Renders primitives from array data with a per-element offset.
//     *
//     * @param mode       the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link #GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link #GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link #GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link #GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
//     * @param start      the minimum array index contained in {@code indices}
//     * @param end        the maximum array index contained in {@code indices}
//     * @param indices    a pointer to the location where the indices are stored
//     * @param basevertex a constant that should be added to each element of {@code indices} when choosing elements from the enabled vertex arrays
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawRangeElementsBaseVertex">Reference Page</a>
//     */
//    public static void glDrawRangeElementsBaseVertex(@NativeType("GLenum") int mode, @NativeType("GLuint") int start, @NativeType("GLuint") int end, @NativeType("void const *") ByteBuffer indices, @NativeType("GLint") int basevertex) {
//        nglDrawRangeElementsBaseVertex(mode, start, end, indices.remaining(), GL11.GL_UNSIGNED_BYTE, memAddress(indices), basevertex);
//    }
//
//    /**
//     * Renders primitives from array data with a per-element offset.
//     *
//     * @param mode       the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link #GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link #GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link #GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link #GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
//     * @param start      the minimum array index contained in {@code indices}
//     * @param end        the maximum array index contained in {@code indices}
//     * @param indices    a pointer to the location where the indices are stored
//     * @param basevertex a constant that should be added to each element of {@code indices} when choosing elements from the enabled vertex arrays
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawRangeElementsBaseVertex">Reference Page</a>
//     */
//    public static void glDrawRangeElementsBaseVertex(@NativeType("GLenum") int mode, @NativeType("GLuint") int start, @NativeType("GLuint") int end, @NativeType("void const *") ShortBuffer indices, @NativeType("GLint") int basevertex) {
//        nglDrawRangeElementsBaseVertex(mode, start, end, indices.remaining(), GL11.GL_UNSIGNED_SHORT, memAddress(indices), basevertex);
//    }
//
//    /**
//     * Renders primitives from array data with a per-element offset.
//     *
//     * @param mode       the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link #GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link #GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link #GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link #GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
//     * @param start      the minimum array index contained in {@code indices}
//     * @param end        the maximum array index contained in {@code indices}
//     * @param indices    a pointer to the location where the indices are stored
//     * @param basevertex a constant that should be added to each element of {@code indices} when choosing elements from the enabled vertex arrays
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawRangeElementsBaseVertex">Reference Page</a>
//     */
//    public static void glDrawRangeElementsBaseVertex(@NativeType("GLenum") int mode, @NativeType("GLuint") int start, @NativeType("GLuint") int end, @NativeType("void const *") IntBuffer indices, @NativeType("GLint") int basevertex) {
//        nglDrawRangeElementsBaseVertex(mode, start, end, indices.remaining(), GL11.GL_UNSIGNED_INT, memAddress(indices), basevertex);
//    }

    // --- [ glDrawElementsInstancedBaseVertex ] ---

    /**
     * Renders multiple instances of a set of primitives from array data with a per-element offset.
     *
     * @param mode       the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link #GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link #GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link #GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link #GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
     * @param count      the number of elements to be rendered
     * @param type       the type of the values in {@code indices}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param indices    a pointer to the location where the indices are stored
     * @param primCount  the number of instances of the indexed geometry that should be drawn
     * @param baseVertex a constant that should be added to each element of indices when chosing elements from the enabled vertex arrays
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsInstancedBaseVertex">Reference Page</a>
     */
    fun drawElementsInstancedBaseVertex(mode: DrawMode, count: Int, type: IndexType, indices: Ptr, primCount: Int, baseVertex: Int) =
            GL32C.nglDrawElementsInstancedBaseVertex(mode.i, count, type.i, indices, primCount, baseVertex)

    /**
     * Renders multiple instances of a set of primitives from array data with a per-element offset.
     *
     * @param count      the number of elements to be rendered
     * @param type       the type of the values in {@code indices}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param indices    a pointer to the location where the indices are stored
     * @param primCount  the number of instances of the indexed geometry that should be drawn
     * @param baseVertex a constant that should be added to each element of indices when chosing elements from the enabled vertex arrays
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsInstancedBaseVertex">Reference Page</a>
     */
    fun drawElementsInstancedBaseVertex(count: Int, type: IndexType, indices: Ptr, primCount: Int, baseVertex: Int) =
            GL32C.nglDrawElementsInstancedBaseVertex(DrawMode.TRIANGLES.i, count, type.i, indices, primCount, baseVertex)

//    /** TODO?
//     * Renders multiple instances of a set of primitives from array data with a per-element offset.
//     *
//     * @param mode       the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link #GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link #GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link #GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link #GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
//     * @param type       the type of the values in {@code indices}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
//     * @param indices    a pointer to the location where the indices are stored
//     * @param primcount  the number of instances of the indexed geometry that should be drawn
//     * @param basevertex a constant that should be added to each element of indices when chosing elements from the enabled vertex arrays
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsInstancedBaseVertex">Reference Page</a>
//     */
//    public static void glDrawElementsInstancedBaseVertex(@NativeType("GLenum") int mode, @NativeType("GLenum") int type, @NativeType("void const *") ByteBuffer indices, @NativeType("GLsizei") int primcount, @NativeType("GLint") int basevertex) {
//        nglDrawElementsInstancedBaseVertex(mode, indices.remaining() >> GLChecks.typeToByteShift(type), type, memAddress(indices), primcount, basevertex);
//    }
//
//    /**
//     * Renders multiple instances of a set of primitives from array data with a per-element offset.
//     *
//     * @param mode       the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link #GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link #GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link #GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link #GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
//     * @param indices    a pointer to the location where the indices are stored
//     * @param primcount  the number of instances of the indexed geometry that should be drawn
//     * @param basevertex a constant that should be added to each element of indices when chosing elements from the enabled vertex arrays
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsInstancedBaseVertex">Reference Page</a>
//     */
//    public static void glDrawElementsInstancedBaseVertex(@NativeType("GLenum") int mode, @NativeType("void const *") ByteBuffer indices, @NativeType("GLsizei") int primcount, @NativeType("GLint") int basevertex) {
//        nglDrawElementsInstancedBaseVertex(mode, indices.remaining(), GL11.GL_UNSIGNED_BYTE, memAddress(indices), primcount, basevertex);
//    }
//
//    /**
//     * Renders multiple instances of a set of primitives from array data with a per-element offset.
//     *
//     * @param mode       the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link #GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link #GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link #GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link #GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
//     * @param indices    a pointer to the location where the indices are stored
//     * @param primcount  the number of instances of the indexed geometry that should be drawn
//     * @param basevertex a constant that should be added to each element of indices when chosing elements from the enabled vertex arrays
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsInstancedBaseVertex">Reference Page</a>
//     */
//    public static void glDrawElementsInstancedBaseVertex(@NativeType("GLenum") int mode, @NativeType("void const *") ShortBuffer indices, @NativeType("GLsizei") int primcount, @NativeType("GLint") int basevertex) {
//        nglDrawElementsInstancedBaseVertex(mode, indices.remaining(), GL11.GL_UNSIGNED_SHORT, memAddress(indices), primcount, basevertex);
//    }
//
//    /**
//     * Renders multiple instances of a set of primitives from array data with a per-element offset.
//     *
//     * @param mode       the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link #GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link #GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link #GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link #GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
//     * @param indices    a pointer to the location where the indices are stored
//     * @param primcount  the number of instances of the indexed geometry that should be drawn
//     * @param basevertex a constant that should be added to each element of indices when chosing elements from the enabled vertex arrays
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsInstancedBaseVertex">Reference Page</a>
//     */
//    public static void glDrawElementsInstancedBaseVertex(@NativeType("GLenum") int mode, @NativeType("void const *") IntBuffer indices, @NativeType("GLsizei") int primcount, @NativeType("GLint") int basevertex) {
//        nglDrawElementsInstancedBaseVertex(mode, indices.remaining(), GL11.GL_UNSIGNED_INT, memAddress(indices), primcount, basevertex);
//    }

    // --- [ glMultiDrawElementsBaseVertex ] ---

    /**
     * Renders multiple sets of primitives by specifying indices of array data elements and an offset to apply to each index.
     *
     * <p><b>LWJGL note</b>: Use {@link org.lwjgl.system.MemoryUtil#memAddress} to retrieve pointers to the index buffers.</p>
     *
     * @param mode       the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link #GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link #GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link #GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link #GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
     * @param count      an array of the elements counts
     * @param type       the type of the values in {@code indices}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param indices    a pointer to the location where the indices are stored
     * @param baseVertex a pointer to the location where the base vertices are stored
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glMultiDrawElementsBaseVertex">Reference Page</a>
     */
    fun multiDrawElementsBaseVertex(mode: DrawMode, count: IntBuffer, type: IndexType, indices: PointerBuffer, baseVertex: IntBuffer) =
            GL32C.nglMultiDrawElementsBaseVertex(mode.i, count.adr, type.i, indices.adr, count.rem, baseVertex.adr)

    /**
     * Renders multiple sets of primitives by specifying indices of array data elements and an offset to apply to each index.
     *
     * <p><b>LWJGL note</b>: Use {@link org.lwjgl.system.MemoryUtil#memAddress} to retrieve pointers to the index buffers.</p>
     *
     * @param count      an array of the elements counts
     * @param type       the type of the values in {@code indices}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param indices    a pointer to the location where the indices are stored
     * @param baseVertex a pointer to the location where the base vertices are stored
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glMultiDrawElementsBaseVertex">Reference Page</a>
     */
    fun multiDrawElementsBaseVertex(count: IntBuffer, type: IndexType, indices: PointerBuffer, baseVertex: IntBuffer) =
            GL32C.nglMultiDrawElementsBaseVertex(DrawMode.TRIANGLES.i, count.adr, type.i, indices.adr, count.rem, baseVertex.adr)

    // --- [ glProvokingVertex ] ---
    // glGetSet

    // --- [ glTexImage2DMultisample ] ---

    /**
     * Establishes the data storage, format, dimensions, and number of samples of a 2D multisample texture's image.
     *
     * @param samples              the number of samples in the multisample texture's image
     * @param internalformat       the internal format to be used to store the multisample texture's image. {@code internalformat} must specify a color-renderable, depth-renderable,
     *                             or stencil-renderable format.
     * @param size                 the size of the multisample texture's image, in texels
     * @param fixedSampleLocations whether the image will use identical sample locations and the same number of samples for all texels in the image, and the sample locations will not
     *                             depend on the internal format or size of the image
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTexImage2DMultisample">Reference Page</a>
     */
    fun texImage2dMS(samples: Int, internalformat: gl.InternalFormat, size: Vec2i, fixedSampleLocations: Boolean) =
            GL32C.glTexImage2DMultisample(TextureTarget._2D_MULTISAMPLE.i, samples, internalformat.i, size.x, size.y, fixedSampleLocations)

    // --- [ glTexImage3DMultisample ] ---

    /**
     * Establishes the data storage, format, dimensions, and number of samples of a 3D multisample texture's image.
     *
     * @param samples              the number of samples in the multisample texture's image
     * @param internalformat       the internal format to be used to store the multisample texture's image. {@code internalformat} must specify a color-renderable, depth-renderable,
     *                             or stencil-renderable format.
     * @param size                 the size of the multisample texture's image, in texels
     * @param fixedSampleLocations whether the image will use identical sample locations and the same number of samples for all texels in the image, and the sample locations will not
     *                             depend on the internal format or size of the image
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTexImage3DMultisample">Reference Page</a>
     */
    fun texImage3dMS(samples: Int, internalformat: gl.InternalFormat, size: Vec3i, fixedSampleLocations: Boolean) =
            GL32C.glTexImage3DMultisample(TextureTarget._2D_MULTISAMPLE_ARRAY.i, samples, internalformat.i, size.x, size.y, size.z, fixedSampleLocations)

    // --- [ glGetMultisamplefv ] ---

    /**
     * Retrieves the location of a sample.
     *
     * @param index the index of the sample whose position to query
     * @return the position of the sample
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetMultisample">Reference Page</a>
     */
    fun getMultisample(index: Int): Float = Stack.floatAddress { GL32C.nglGetMultisamplefv(GL32C.GL_SAMPLE_POSITION, index, it) }

    // --- [ glSampleMaski ] ---

    /**
     * Sets the value of a sub-word of the sample mask.
     *
     * @param index which 32-bit sub-word of the sample mask to update
     * @param mask  the new value of the mask sub-word
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glSampleMaski">Reference Page</a>
     */
    fun sampleMask(index: Int, mask: Int) = GL32C.glSampleMaski(index, mask)

    // --- [ glFramebufferTexture ] ---

    /**
     * Attaches a level of a texture object as a logical buffer to the currently bound framebuffer object.
     *
     * @param target     the framebuffer target. One of:<br><table><tr><td>{@link GL30#GL_FRAMEBUFFER FRAMEBUFFER}</td><td>{@link GL30#GL_READ_FRAMEBUFFER READ_FRAMEBUFFER}</td><td>{@link GL30#GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER}</td></tr></table>
     * @param attachment the attachment point of the framebuffer
     * @param texture    the texture object to attach to the framebuffer attachment point named by {@code attachment}
     * @param level      the mipmap level of {@code texture} to attach
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferTexture">Reference Page</a>
     */
    fun framebufferTexture(target: FramebufferTarget, attachment: Int, texture: GlTexture, level: Int) =
            GL32C.glFramebufferTexture(target.i, attachment, texture.name, level)

    // --- [ glFenceSync ] ---

    /**
     * Creates a new sync object and inserts it into the GL command stream.
     *
     * @param condition the condition that must be met to set the sync object's state to signaled. Must be:<br><table><tr><td>{@link #GL_SYNC_GPU_COMMANDS_COMPLETE SYNC_GPU_COMMANDS_COMPLETE}</td></tr></table>
     * @param flags     a bitwise combination of flags controlling the behavior of the sync object. No flags are presently defined for this operation and {@code flags} must
     *                  be zero.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFenceSync">Reference Page</a>
     */
    fun fenceSync() = GlSync(GL32C.glFenceSync(GL32C.GL_SYNC_GPU_COMMANDS_COMPLETE, 0))

    // --- [ glIsSync ] ---

    /**
     * Determines if a name corresponds to a sync object.
     *
     * @param sync a value that may be the name of a sync object
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glIsSync">Reference Page</a>
     */
    infix fun isSync(sync: GlSync) = GL32C.nglIsSync(sync.L)

    // --- [ glDeleteSync ] ---

    /**
     * Deletes a sync object.
     *
     * @param sync the sync object to be deleted
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteSync">Reference Page</a>
     */
    infix fun deleteSync(sync: GlSync) = GL32C.nglDeleteSync(sync.L)

    // --- [ glClientWaitSync ] ---

    /**
     * Causes the client to block and wait for a sync object to become signaled. If {@code sync} is signaled when {@code glClientWaitSync} is called,
     * {@code glClientWaitSync} returns immediately, otherwise it will block and wait for up to timeout nanoseconds for {@code sync} to become signaled.
     *
     * <p>The return value is one of four status values:</p>
     *
     * <ul>
     * <li>{@link #GL_ALREADY_SIGNALED ALREADY_SIGNALED} indicates that sync was signaled at the time that glClientWaitSync was called.</li>
     * <li>{@link #GL_TIMEOUT_EXPIRED TIMEOUT_EXPIRED} indicates that at least timeout nanoseconds passed and sync did not become signaled.</li>
     * <li>{@link #GL_CONDITION_SATISFIED CONDITION_SATISFIED} indicates that sync was signaled before the timeout expired.</li>
     * <li>{@link #GL_WAIT_FAILED WAIT_FAILED} indicates that an error occurred. Additionally, an OpenGL error will be generated.</li>
     * </ul>
     *
     * @param sync          the sync object whose status to wait on
     * @param flushFirst    then the equivalent of a glFlush will be issued before blocking on the sync object (GL_SYNC_FLUSH_COMMANDS_BIT)
     * @param timeout       the timeout, specified in nanoseconds, for which the implementation should wait for {@code sync} to become signaled
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClientWaitSync">Reference Page</a>
     */
    fun clientWaitSync(sync: GlSync, flushFirst: Boolean, timeout: NanoSecond): SyncStatus =
            SyncStatus(GL32C.nglClientWaitSync(sync.L, if (flushFirst) GL32C.GL_SYNC_FLUSH_COMMANDS_BIT else 0, timeout.L))

    // --- [ glWaitSync ] ---

    /**
     * Causes the GL server to block and wait for a sync object to become signaled.
     *
     * <p>{@code glWaitSync} will always wait no longer than an implementation-dependent timeout. The duration of this timeout in nanoseconds may be queried by
     * with {@link #GL_MAX_SERVER_WAIT_TIMEOUT MAX_SERVER_WAIT_TIMEOUT}. There is currently no way to determine whether glWaitSync unblocked because the timeout expired or because the
     * sync object being waited on was signaled.</p>
     *
     * <p>If an error occurs, {@code glWaitSync} does not cause the GL server to block.</p>
     *
     * @param sync    the sync object whose status to wait on
     * @param flags   a bitfield controlling the command flushing behavior. Must be:<br><table><tr><td>0</td></tr></table>
     * @param timeout the timeout that the server should wait before continuing. Must be:<br><table><tr><td>{@link #GL_TIMEOUT_IGNORED TIMEOUT_IGNORED}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glWaitSync">Reference Page</a>
     */
    infix fun waitSync(sync: GlSync) = GL32C.glWaitSync(sync.L, 0, GL32C.GL_TIMEOUT_IGNORED)

    // --- [ glGetInteger64v ] ---
    // inline reified

//    // --- [ glGetInteger64i_v ] --- TODO
//
//    /** Unsafe version of: {@link #glGetInteger64i_v GetInteger64i_v} */
//    public static native void nglGetInteger64i_v(int pname, int index, long params);
//
//    /**
//     * Queries the 64bit integer value of an indexed state variable.
//     *
//     * @param pname  the indexed state to query
//     * @param index  the index of the element being queried
//     * @param params the value or values of the specified parameter
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetInteger">Reference Page</a>
//     */
//    public static void glGetInteger64i_v(@NativeType("GLenum") int pname, @NativeType("GLuint") int index, @NativeType("GLint64 *") LongBuffer params)
//    {
//        if (CHECKS) {
//            check(params, 1);
//        }
//        nglGetInteger64i_v(pname, index, memAddress(params));
//    }
//
//    /**
//     * Queries the 64bit integer value of an indexed state variable.
//     *
//     * @param pname the indexed state to query
//     * @param index the index of the element being queried
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetInteger">Reference Page</a>
//     */
//    @NativeType("void")
//    public static long glGetInteger64i(@NativeType("GLenum") int pname, @NativeType("GLuint") int index)
//    {
//        MemoryStack stack = stackGet (); int stackPointer = stack . getPointer ();
//        try {
//            LongBuffer params = stack . callocLong (1);
//            nglGetInteger64i_v(pname, index, memAddress(params));
//            return params.get(0);
//        } finally {
//            stack.setPointer(stackPointer);
//        }
//    }

    // --- [ glGetSynciv ] ---

    /**
     * Queries the signaled status of a sync object.
     *
     * @param sync   the sync object whose properties to query
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetSync">Reference Page</a>
     */
    fun isSyncSignaled(sync: GlSync): Boolean =
            Stack.intAddress {
                GL32C.nglGetSynciv(sync.L, GL32C.GL_SYNC_STATUS, 1, NULL, it)
            }.bool
}