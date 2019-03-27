package gln

import gli_.gl
import glm_.vec2.Vec2i
import glm_.vec3.Vec3i
import gln.objects.GlTexture
import gln.transformFeedback.GlTransformFeedback
import kool.Ptr
import org.lwjgl.opengl.GL11C
import org.lwjgl.opengl.GL12C
import org.lwjgl.opengl.GL42C

/**
 * The OpenGL functionality up to version 4.2. Includes only Core Profile symbols.
 *
 * <p>OpenGL 4.2 implementations support revision 4.20 of the OpenGL Shading Language.</p>
 *
 * <p>Extensions promoted to core in this release:</p>
 *
 * <ul>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_texture_compression_bptc.txt">ARB_texture_compression_bptc</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_compressed_texture_pixel_storage.txt">ARB_compressed_texture_pixel_storage</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_shader_atomic_counters.txt">ARB_shader_atomic_counters</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_texture_storage.txt">ARB_texture_storage</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_transform_feedback_instanced.txt">ARB_transform_feedback_instanced</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_base_instance.txt">ARB_base_instance</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_shader_image_load_store.txt">ARB_shader_image_load_store</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_conservative_depth.txt">ARB_conservative_depth</a> and <a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_shading_language_420pack.txt">ARB_shading_language_420pack</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_internalformat_query.txt">ARB_internalformat_query</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_map_buffer_alignment.txt">ARB_map_buffer_alignment</a></li>
 * </ul>
 */
interface gl42i {

    // --- [ glGetActiveAtomicCounterBufferiv ] ---
    // inline reified

    // --- [ glTexStorage1D ] ---

    /**
     * Simultaneously specifies storage for all levels of a one-dimensional texture.
     *
     * @param levels         the number of texture levels
     * @param internalFormat the sized internal format to be used to store texture image data
     * @param width          the width of the texture, in texels
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTexStorage1D">Reference Page</a>
     */
    fun texStorage1D(levels: Int, internalFormat: gl.InternalFormat, width: Int) = GL42C.glTexStorage1D(GL11C.GL_TEXTURE_1D, levels, internalFormat.i, width)

    // --- [ glTexStorage2D ] ---
    /**
     * Simultaneously specifies storage for all levels of a two-dimensional or one-dimensional array texture.
     *
     * @param target         the target of the operation. One of:<br><table><tr><td>{@link GL11#GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link GL11#GL_PROXY_TEXTURE_2D PROXY_TEXTURE_2D}</td><td>{@link GL30#GL_PROXY_TEXTURE_1D_ARRAY PROXY_TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_PROXY_TEXTURE_RECTANGLE PROXY_TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_PROXY_TEXTURE_CUBE_MAP PROXY_TEXTURE_CUBE_MAP}</td></tr></table>
     * @param levels         the number of texture levels
     * @param internalFormat the sized internal format to be used to store texture image data
     * @param size           the size of the texture, in texels
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTexStorage2D">Reference Page</a>
     */
    fun texStorage2D(levels: Int, internalFormat: gl.InternalFormat, size: Vec2i) = GL42C.glTexStorage2D(GL11C.GL_TEXTURE_2D, levels, internalFormat.i, size.x, size.y)

    // --- [ glTexStorage3D ] --- TODO also non defaul target?

    /**
     * Simultaneously specifies storage for all levels of a three-dimensional, two-dimensional array or cube-map array texture.
     *
     * @param target         the target of the operation. One of:<br><table><tr><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL40#GL_TEXTURE_CUBE_MAP_ARRAY TEXTURE_CUBE_MAP_ARRAY}</td><td>{@link GL12#GL_PROXY_TEXTURE_3D PROXY_TEXTURE_3D}</td></tr><tr><td>{@link GL30#GL_PROXY_TEXTURE_2D_ARRAY PROXY_TEXTURE_2D_ARRAY}</td><td>{@link GL40#GL_PROXY_TEXTURE_CUBE_MAP_ARRAY PROXY_TEXTURE_CUBE_MAP_ARRAY}</td></tr></table>
     * @param levels         the number of texture levels
     * @param internalFormat the sized internal format to be used to store texture image data
     * @param size           the size of the texture, in texels
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTexStorage3D">Reference Page</a>
     */
    fun texStorage3D(levels: Int, internalFormat: gl.InternalFormat, size: Vec3i) = GL42C.glTexStorage3D(GL12C.GL_TEXTURE_3D, levels, internalFormat.i, size.x, size.y, size.z)

    // --- [ glDrawTransformFeedbackInstanced ] ---

    /**
     * Renders multiple instances of primitives using a count derived from a transform feedback object.
     *
     * @param mode      what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
     * @param id        the name of a transform feedback object from which to retrieve a primitive count
     * @param primCount the number of instances of the geometry to render
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawTransformFeedbackInstanced">Reference Page</a>
     */
    fun drawTransformFeedbackInstanced(mode: DrawMode, id: GlTransformFeedback, primCount: Int) =
            GL42C.glDrawTransformFeedbackInstanced(mode.i, id.name, primCount)

    // --- [ glDrawTransformFeedbackStreamInstanced ] ---

    /**
     * Renders multiple instances of primitives using a count derived from a specifed stream of a transform feedback object.
     *
     * @param mode      what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
     * @param id        the name of a transform feedback object from which to retrieve a primitive count
     * @param stream    the index of the transform feedback stream from which to retrieve a primitive count
     * @param primCount the number of instances of the geometry to render
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawTransformFeedbackStreamInstanced">Reference Page</a>
     */
    fun drawTransformFeedbackStreamInstanced(mode: DrawMode, id: GlTransformFeedback, stream: Int, primCount: Int) =
            GL42C.glDrawTransformFeedbackStreamInstanced(mode.i, id.name, stream, primCount)

    // --- [ glDrawArraysInstancedBaseInstance ] ---

    /**
     * Draws multiple instances of a range of elements with an offset applied to instanced attributes.
     *
     * @param mode         what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
     * @param first        the starting index in the enabled arrays
     * @param count        the number of indices to be rendered
     * @param primCount    the number of instances of the specified range of indices to be rendered
     * @param baseInstance the base instance for use in fetching instanced vertex attributes
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawArraysInstancedBaseInstance">Reference Page</a>
     */
    fun drawArraysInstancedBaseInstance(mode: DrawMode, first: Int, count: Int, primCount: Int, baseInstance: Int) =
            GL42C.glDrawArraysInstancedBaseInstance(mode.i, first, count, primCount, baseInstance)

    // --- [ glDrawElementsInstancedBaseInstance ] ---

    /**
     * Draws multiple instances of a set of elements with an offset applied to instanced attributes
     *
     * @param mode         what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
     * @param count        the number of elements to be rendered
     * @param type         the type of the values in {@code indices}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param indices      a pointer to the location where the indices are stored
     * @param primCount    the number of instances of the specified range of indices to be rendered
     * @param baseInstance the base instance for use in fetching instanced vertex attributes
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsInstancedBaseInstance">Reference Page</a>
     */
    fun drawElementsInstancedBaseInstance(mode: DrawMode, count: Int, type: IndexType, indices: Ptr, primCount: Int, baseInstance: Int) =
        GL42C.nglDrawElementsInstancedBaseInstance(mode.i, count, type.i, indices, primCount, baseInstance)

//    /** TODO?
//     * Draws multiple instances of a set of elements with an offset applied to instanced attributes
//     *
//     * @param mode         what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
//     * @param type         the type of the values in {@code indices}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
//     * @param indices      a pointer to the location where the indices are stored
//     * @param primcount    the number of instances of the specified range of indices to be rendered
//     * @param baseinstance the base instance for use in fetching instanced vertex attributes
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsInstancedBaseInstance">Reference Page</a>
//     */
//    fun drawElementsInstancedBaseInstance(mode: DrawMode, type: IndexType, @NativeType("void const *") ByteBuffer indices, @NativeType("GLsizei") int primcount, @NativeType("GLuint") int baseinstance) {
//        nglDrawElementsInstancedBaseInstance(mode, indices.remaining() >> GLChecks.typeToByteShift(type), type, memAddress(indices), primcount, baseinstance);
//    }
//
//    /**
//     * Draws multiple instances of a set of elements with an offset applied to instanced attributes
//     *
//     * @param mode         what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
//     * @param indices      a pointer to the location where the indices are stored
//     * @param primcount    the number of instances of the specified range of indices to be rendered
//     * @param baseinstance the base instance for use in fetching instanced vertex attributes
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsInstancedBaseInstance">Reference Page</a>
//     */
//    public static void glDrawElementsInstancedBaseInstance(@NativeType("GLenum") int mode, @NativeType("void const *") ByteBuffer indices, @NativeType("GLsizei") int primcount, @NativeType("GLuint") int baseinstance) {
//        nglDrawElementsInstancedBaseInstance(mode, indices.remaining(), GL11.GL_UNSIGNED_BYTE, memAddress(indices), primcount, baseinstance);
//    }
//
//    /**
//     * Draws multiple instances of a set of elements with an offset applied to instanced attributes
//     *
//     * @param mode         what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
//     * @param indices      a pointer to the location where the indices are stored
//     * @param primcount    the number of instances of the specified range of indices to be rendered
//     * @param baseinstance the base instance for use in fetching instanced vertex attributes
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsInstancedBaseInstance">Reference Page</a>
//     */
//    public static void glDrawElementsInstancedBaseInstance(@NativeType("GLenum") int mode, @NativeType("void const *") ShortBuffer indices, @NativeType("GLsizei") int primcount, @NativeType("GLuint") int baseinstance) {
//        nglDrawElementsInstancedBaseInstance(mode, indices.remaining(), GL11.GL_UNSIGNED_SHORT, memAddress(indices), primcount, baseinstance);
//    }
//
//    /**
//     * Draws multiple instances of a set of elements with an offset applied to instanced attributes
//     *
//     * @param mode         what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
//     * @param indices      a pointer to the location where the indices are stored
//     * @param primcount    the number of instances of the specified range of indices to be rendered
//     * @param baseinstance the base instance for use in fetching instanced vertex attributes
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsInstancedBaseInstance">Reference Page</a>
//     */
//    public static void glDrawElementsInstancedBaseInstance(@NativeType("GLenum") int mode, @NativeType("void const *") IntBuffer indices, @NativeType("GLsizei") int primcount, @NativeType("GLuint") int baseinstance) {
//        nglDrawElementsInstancedBaseInstance(mode, indices.remaining(), GL11.GL_UNSIGNED_INT, memAddress(indices), primcount, baseinstance);
//    }

    // --- [ glDrawElementsInstancedBaseVertexBaseInstance ] ---

    /**
     * Renders multiple instances of a set of primitives from array data with a per-element offset.
     *
     * @param mode         what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
     * @param count        the number of elements to be rendered
     * @param type         the type of the values in {@code indices}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param indices      a pointer to the location where the indices are stored
     * @param primCount    the number of instances of the indexed geometry that should be drawn
     * @param baseVertex   a constant that should be added to each element of {@code indices} when choosing elements from the enabled vertex arrays
     * @param baseInstance the base instance for use in fetching instanced vertex attributes
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsInstancedBaseVertexBaseInstance">Reference Page</a>
     */
    fun drawElementsInstancedBaseVertexBaseInstance(mode: DrawMode, count: Int, type: IndexType, indices: Ptr, primCount: Int, baseVertex: Int, baseInstance: Int) =
            GL42C.nglDrawElementsInstancedBaseVertexBaseInstance(mode.i, count, type.i, indices, primCount, baseVertex, baseInstance)

//    /**
//     * Renders multiple instances of a set of primitives from array data with a per-element offset.
//     *
//     * @param mode         what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
//     * @param type         the type of the values in {@code indices}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
//     * @param indices      a pointer to the location where the indices are stored
//     * @param primcount    the number of instances of the indexed geometry that should be drawn
//     * @param basevertex   a constant that should be added to each element of {@code indices} when choosing elements from the enabled vertex arrays
//     * @param baseinstance the base instance for use in fetching instanced vertex attributes
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsInstancedBaseVertexBaseInstance">Reference Page</a>
//     */
//    public static void glDrawElementsInstancedBaseVertexBaseInstance(@NativeType("GLenum") int mode, @NativeType("GLenum") int type, @NativeType("void const *") ByteBuffer indices, @NativeType("GLsizei") int primcount, @NativeType("GLint") int basevertex, @NativeType("GLuint") int baseinstance) {
//        nglDrawElementsInstancedBaseVertexBaseInstance(mode, indices.remaining() >> GLChecks.typeToByteShift(type), type, memAddress(indices), primcount, basevertex, baseinstance);
//    }
//
//    /**
//     * Renders multiple instances of a set of primitives from array data with a per-element offset.
//     *
//     * @param mode         what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
//     * @param indices      a pointer to the location where the indices are stored
//     * @param primcount    the number of instances of the indexed geometry that should be drawn
//     * @param basevertex   a constant that should be added to each element of {@code indices} when choosing elements from the enabled vertex arrays
//     * @param baseinstance the base instance for use in fetching instanced vertex attributes
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsInstancedBaseVertexBaseInstance">Reference Page</a>
//     */
//    public static void glDrawElementsInstancedBaseVertexBaseInstance(@NativeType("GLenum") int mode, @NativeType("void const *") ByteBuffer indices, @NativeType("GLsizei") int primcount, @NativeType("GLint") int basevertex, @NativeType("GLuint") int baseinstance) {
//        nglDrawElementsInstancedBaseVertexBaseInstance(mode, indices.remaining(), GL11.GL_UNSIGNED_BYTE, memAddress(indices), primcount, basevertex, baseinstance);
//    }
//
//    /**
//     * Renders multiple instances of a set of primitives from array data with a per-element offset.
//     *
//     * @param mode         what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
//     * @param indices      a pointer to the location where the indices are stored
//     * @param primcount    the number of instances of the indexed geometry that should be drawn
//     * @param basevertex   a constant that should be added to each element of {@code indices} when choosing elements from the enabled vertex arrays
//     * @param baseinstance the base instance for use in fetching instanced vertex attributes
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsInstancedBaseVertexBaseInstance">Reference Page</a>
//     */
//    public static void glDrawElementsInstancedBaseVertexBaseInstance(@NativeType("GLenum") int mode, @NativeType("void const *") ShortBuffer indices, @NativeType("GLsizei") int primcount, @NativeType("GLint") int basevertex, @NativeType("GLuint") int baseinstance) {
//        nglDrawElementsInstancedBaseVertexBaseInstance(mode, indices.remaining(), GL11.GL_UNSIGNED_SHORT, memAddress(indices), primcount, basevertex, baseinstance);
//    }
//
//    /**
//     * Renders multiple instances of a set of primitives from array data with a per-element offset.
//     *
//     * @param mode         what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
//     * @param indices      a pointer to the location where the indices are stored
//     * @param primcount    the number of instances of the indexed geometry that should be drawn
//     * @param basevertex   a constant that should be added to each element of {@code indices} when choosing elements from the enabled vertex arrays
//     * @param baseinstance the base instance for use in fetching instanced vertex attributes
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsInstancedBaseVertexBaseInstance">Reference Page</a>
//     */
//    public static void glDrawElementsInstancedBaseVertexBaseInstance(@NativeType("GLenum") int mode, @NativeType("void const *") IntBuffer indices, @NativeType("GLsizei") int primcount, @NativeType("GLint") int basevertex, @NativeType("GLuint") int baseinstance) {
//        nglDrawElementsInstancedBaseVertexBaseInstance(mode, indices.remaining(), GL11.GL_UNSIGNED_INT, memAddress(indices), primcount, basevertex, baseinstance);
//    }

    // --- [ glBindImageTexture ] ---

    /**
     * Binds a level of a texture to an image unit.
     *
     * @param unit    the index of the image unit to which to bind the texture
     * @param texture the name of the texture to bind to the image unit
     * @param level   the level of the texture that is to be bound
     * @param layered whether a layered texture binding is to be established
     * @param layer   if {@code layered} is false, specifies the layer of texture to be bound to the image unit. Ignored otherwise.
     * @param access  a token indicating the type of access that will be performed on the image
     * @param format  the format that the elements of the image will be treated as for the purposes of formatted stores
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBindImageTexture">Reference Page</a>
     */
    fun bindImageTexture(unit: TexUnit, texture: GlTexture, level: Int, layered: Boolean, layer: Int, access: TextureAccess, format: InternalFormat) =
            GL42C.glBindImageTexture(unit, texture.name, level, layered, layer, access.i, format.i)

    // --- [ glMemoryBarrier ] --- TODO enum?

    /**
     * Defines a barrier ordering memory transactions.
     *
     * @param barriers the barriers to insert (bitwise combination). One or more of:<br><table><tr><td>{@link #GL_VERTEX_ATTRIB_ARRAY_BARRIER_BIT VERTEX_ATTRIB_ARRAY_BARRIER_BIT}</td><td>{@link #GL_ELEMENT_ARRAY_BARRIER_BIT ELEMENT_ARRAY_BARRIER_BIT}</td><td>{@link #GL_UNIFORM_BARRIER_BIT UNIFORM_BARRIER_BIT}</td></tr><tr><td>{@link #GL_TEXTURE_FETCH_BARRIER_BIT TEXTURE_FETCH_BARRIER_BIT}</td><td>{@link #GL_SHADER_IMAGE_ACCESS_BARRIER_BIT SHADER_IMAGE_ACCESS_BARRIER_BIT}</td><td>{@link #GL_COMMAND_BARRIER_BIT COMMAND_BARRIER_BIT}</td></tr><tr><td>{@link #GL_PIXEL_BUFFER_BARRIER_BIT PIXEL_BUFFER_BARRIER_BIT}</td><td>{@link #GL_TEXTURE_UPDATE_BARRIER_BIT TEXTURE_UPDATE_BARRIER_BIT}</td><td>{@link #GL_BUFFER_UPDATE_BARRIER_BIT BUFFER_UPDATE_BARRIER_BIT}</td></tr><tr><td>{@link #GL_FRAMEBUFFER_BARRIER_BIT FRAMEBUFFER_BARRIER_BIT}</td><td>{@link #GL_TRANSFORM_FEEDBACK_BARRIER_BIT TRANSFORM_FEEDBACK_BARRIER_BIT}</td><td>{@link #GL_ATOMIC_COUNTER_BARRIER_BIT ATOMIC_COUNTER_BARRIER_BIT}</td></tr><tr><td>{@link #GL_ALL_BARRIER_BITS ALL_BARRIER_BITS}</td><td>{@link GL43#GL_SHADER_STORAGE_BARRIER_BIT SHADER_STORAGE_BARRIER_BIT}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glMemoryBarrier">Reference Page</a>
     */
    fun memoryBarrier(barriers: Int) = GL42C.glMemoryBarrier(barriers)

    // --- [ glGetInternalformativ ] ---
    // inline reified
}