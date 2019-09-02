/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package gln

import glm_.BYTES
import glm_.L
import gln.identifiers.GlBuffer
import gln.identifiers.GlProgram
import kool.*
import org.lwjgl.opengl.GL31C
import org.lwjgl.system.MemoryUtil.memASCII
import org.lwjgl.system.MemoryUtil.memGetInt
import java.nio.ByteBuffer
import java.nio.IntBuffer


/**
 * The OpenGL functionality of a forward compatible context, up to version 3.1.
 *
 * <p>OpenGL 3.1 implementations support revision 1.40 of the OpenGL Shading Language.</p>
 *
 * <p>Extensions promoted to core in this release:</p>
 *
 * <ul>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_draw_instanced.txt">ARB_draw_instanced</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_copy_buffer.txt">ARB_copy_buffer</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/NV/NV_primitive_restart.txt">NV_primitive_restart</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_texture_buffer_object.txt">ARB_texture_buffer_object</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_texture_rectangle.txt">ARB_texture_rectangle</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_uniform_buffer_object.txt">ARB_uniform_buffer_object</a></li>
 * </ul>
 */
interface gl31i {

    // --- [ glDrawArraysInstanced ] ---

    /**
     * Draw multiple instances of a range of elements.
     *
     * @param mode      the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
     * @param first     the index of the first vertex to be rendered
     * @param count     the number of vertices to be rendered
     * @param primCount the number of instances of the specified range of vertices to be rendered
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawArraysInstanced">Reference Page</a>
     */
    fun drawArraysInstanced(mode: DrawMode, first: Int, count: Int, primCount: Int) = GL31C.glDrawArraysInstanced(mode.i, first, count, primCount)

    /**
     * Draw multiple instances of a range of elements. Default triangles
     *
     * @param first     the index of the first vertex to be rendered
     * @param count     the number of vertices to be rendered
     * @param primCount the number of instances of the specified range of vertices to be rendered
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawArraysInstanced">Reference Page</a>
     */
    fun drawArraysInstanced(first: Int, count: Int, primCount: Int) = GL31C.glDrawArraysInstanced(PrimitiveMode.TRIANGLES.i, first, count, primCount)

    // --- [ glDrawElementsInstanced ] ---

    /**
     * Draws multiple instances of a set of elements.
     *
     * @param mode      the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
     * @param type      the type of the values in {@code indices}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param indices   the ByteBuffer containing the indices to be rendered
     * @param primCount the number of instances of the specified range of indices to be rendered
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsInstanced">Reference Page</a>
     */
    fun drawElementsInstanced(mode: PrimitiveMode, type: IndexType, indices: ByteBuffer, primCount: Int) =
            GL31C.nglDrawElementsInstanced(mode.i, indices.rem(type), type.i, indices.adr, primCount)

//    /** TODO?
//     * Draws multiple instances of a set of elements.
//     *
//     * @param mode      the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
//     * @param indices   the ByteBuffer containing the indices to be rendered
//     * @param primcount the number of instances of the specified range of indices to be rendered
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsInstanced">Reference Page</a>
//     */
//    public static void glDrawElementsInstanced(@NativeType("GLenum") int mode, @NativeType("void const *") ByteBuffer indices, @NativeType("GLsizei") int primcount) {
//        nglDrawElementsInstanced(mode, indices.remaining(), GL11.GL_UNSIGNED_BYTE, memAddress(indices), primcount);
//    }
//
//    /**
//     * Draws multiple instances of a set of elements.
//     *
//     * @param mode      the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
//     * @param indices   the ByteBuffer containing the indices to be rendered
//     * @param primcount the number of instances of the specified range of indices to be rendered
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsInstanced">Reference Page</a>
//     */
//    public static void glDrawElementsInstanced(@NativeType("GLenum") int mode, @NativeType("void const *") ShortBuffer indices, @NativeType("GLsizei") int primcount) {
//        nglDrawElementsInstanced(mode, indices.remaining(), GL11.GL_UNSIGNED_SHORT, memAddress(indices), primcount);
//    }
//
//    /**
//     * Draws multiple instances of a set of elements.
//     *
//     * @param mode      the kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
//     * @param indices   the ByteBuffer containing the indices to be rendered
//     * @param primcount the number of instances of the specified range of indices to be rendered
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawElementsInstanced">Reference Page</a>
//     */
//    public static void glDrawElementsInstanced(@NativeType("GLenum") int mode, @NativeType("void const *") IntBuffer indices, @NativeType("GLsizei") int primcount) {
//        nglDrawElementsInstanced(mode, indices.remaining(), GL11.GL_UNSIGNED_INT, memAddress(indices), primcount);
//    }

    // --- [ glCopyBufferSubData ] ---

    /**
     * Copies all or part of one buffer object's data store to the data store of another buffer object.
     *
     * <p>An {@link GL11#GL_INVALID_VALUE INVALID_VALUE} error is generated if any of readoffset, writeoffset, or size are negative, if readoffset+size exceeds the size of the buffer object
     * bound to readtarget, or if writeoffset+size exceeds the size of the buffer object bound to writetarget.</p>
     *
     * <p>An {@link GL11#GL_INVALID_VALUE INVALID_VALUE} error is generated if the same buffer object is bound to both readtarget and writetarget, and the ranges [readoffset, readoffset+size)
     * and [writeoffset, writeoffset+size) overlap.</p>
     *
     * <p>An {@link GL11#GL_INVALID_OPERATION INVALID_OPERATION} error is generated if zero is bound to readtarget or writetarget.</p>
     *
     * <p>An {@link GL11#GL_INVALID_OPERATION INVALID_OPERATION} error is generated if the buffer objects bound to either readtarget or writetarget are mapped.</p>
     *
     * @param readTarget  the source buffer object target. One of:<br><table><tr><td>{@link GL15#GL_ARRAY_BUFFER ARRAY_BUFFER}</td><td>{@link #GL_COPY_READ_BUFFER COPY_READ_BUFFER}</td><td>{@link #GL_COPY_WRITE_BUFFER COPY_WRITE_BUFFER}</td><td>{@link GL15#GL_ELEMENT_ARRAY_BUFFER ELEMENT_ARRAY_BUFFER}</td></tr><tr><td>{@link GL21#GL_PIXEL_PACK_BUFFER PIXEL_PACK_BUFFER}</td><td>{@link GL21#GL_PIXEL_UNPACK_BUFFER PIXEL_UNPACK_BUFFER}</td><td>{@link GL30#GL_TRANSFORM_FEEDBACK_BUFFER TRANSFORM_FEEDBACK_BUFFER}</td><td>{@link #GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td></tr><tr><td>{@link #GL_UNIFORM_BUFFER UNIFORM_BUFFER}</td></tr></table>
     * @param writeTarget the destination buffer object target
     * @param readOffset  the source buffer object offset, in bytes
     * @param writeOffset the destination buffer object offset, in bytes
     * @param size        the number of bytes to copy
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCopyBufferSubData">Reference Page</a>
     */
    fun copyBufferSubData(readTarget: BufferTarget, writeTarget: BufferTarget, readOffset: Ptr, writeOffset: Ptr, size: Int) =
            GL31C.glCopyBufferSubData(readTarget.i, writeTarget.i, readOffset, writeOffset, size.L)

    // --- [ glPrimitiveRestartIndex ] ---

    /**
     * Specifies the primitive restart index.
     *
     * @param index the value to be interpreted as the primitive restart index
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glPrimitiveRestartIndex">Reference Page</a>
     */
    fun primitiveRestartIndex(index: Int) = GL31C.glPrimitiveRestartIndex(index)

    // --- [ glTexBuffer ] ---

    /**
     * Attaches the storage for the buffer object named {@code buffer} to the active buffer texture, and specifies an internal format for the texel array found
     * in the attached buffer object. If {@code buffer} is zero, any buffer object attached to the buffer texture is detached, and no new buffer object is
     * attached. If {@code buffer} is non-zero, but is not the name of an existing buffer object, the error {@link GL11#GL_INVALID_OPERATION INVALID_OPERATION} is generated.
     *
     * <p>When a buffer object is attached to a buffer texture, the buffer object's data store is taken as the texture's texel array. The number of texels in the
     * buffer texture's texel array is given by</p>
     *
     * <p>{@code floor(buffer_size / (components * sizeof(base_type))},</p>
     *
     * <p>where {@code buffer_size} is the size of the buffer object, in basic machine units and {@code components} and {@code base_type} are the element count
     * and base data type for elements. The number of texels in the texel array is then clamped to the implementation-dependent limit {@link #GL_MAX_TEXTURE_BUFFER_SIZE MAX_TEXTURE_BUFFER_SIZE}.
     * When a buffer texture is accessed in a shader, the results of a texel fetch are undefined if the specified texel number is greater than or equal to the
     * clamped number of texels in the texel array.</p>
     *
     * <p>When a buffer texture is accessed in a shader, an integer is provided to indicate the texel number being accessed. If no buffer object is bound to the
     * buffer texture, the results of the texel access are undefined. Otherwise, the attached buffer object's data store is interpreted as an array of elements
     * of the GL data type corresponding to {@code internalformat}. Each texel consists of one to four elements that are mapped to texture components
     * (R, G, B, A, L, and I). Element {@code m} of the texel numbered {@code n} is taken from element {@code n} * {@code components} + {@code m} of the
     * attached buffer object's data store. Elements and texels are both numbered starting with zero. For texture formats with normalized components, the
     * extracted values are converted to floating-point values. The components of the texture are then converted to an (R,G,B,A) vector, and returned to the
     * shader as a four-component result vector with components of the appropriate data type for the texture's internal format.</p>
     *
     * @param target         the target of the operation. Must be:<br><table><tr><td>{@link #GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td></tr></table>
     * @param internalformat the sized internal format of the data in the store belonging to {@code buffer}
     * @param buffer         the name of the buffer object whose storage to attach to the active buffer texture
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTexBuffer">Reference Page</a>
     */
    fun texBuffer(internalformat: InternalFormat, buffer: GlBuffer) = GL31C.glTexBuffer(GL31C.GL_TEXTURE_BUFFER, internalformat.i, buffer.name)

    // --- [ glGetUniformIndices ] ---

    /**
     * Retrieves the indices of a number of uniforms within a program object
     *
     * @param program        the name of a program containing uniforms whose indices to query
     * @param uniformNames   an array of pointers to buffers containing the names of the queried uniforms
     * @return               an array that will receive the indices of the uniforms
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetUniformIndices">Reference Page</a>
     */
    fun getUniformIndices(program: GlProgram, uniformNames: Array<CharSequence>): IntArray = Stack { s ->
        val pUniformNames = s.PointerBuffer(uniformNames.size)
        for (i in uniformNames.indices) pUniformNames[i] = memASCII(uniformNames[i])
        val pUniformIndices = s.nmalloc(4, Int.BYTES * uniformNames.size)
        GL31C.nglGetUniformIndices(program.name, uniformNames.size, pUniformNames.adr, pUniformIndices)
        IntArray(uniformNames.size) { memGetInt(pUniformIndices + Int.BYTES * it) }
    }

    // --- [ glGetActiveUniformsiv ] --- TODO rename?

    /**
     * Returns information about several active uniform variables for the specified program object.
     *
     * @param program        the program object to be queried
     * @param uniformIndices an array of {@code uniformCount} integers containing the indices of uniforms within {@code program}
     * @param name          the property of the each uniform in {@code uniformIndices} that should be written into the corresponding element of {@code params}
     * @param params         an array of {@code uniformCount} integers which are to receive the value of {@code name} for each uniform in {@code uniformIndices}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetActiveUniforms">Reference Page</a>
     */
    fun getActiveUniformsiv(program: GlProgram, uniformIndices: IntBuffer, name: GetActiveUniform, params: IntBuffer) =
            GL31C.nglGetActiveUniformsiv(program.name, uniformIndices.rem, uniformIndices.adr, name.i, params.adr)

    /**
     * Returns information about several active uniform variables for the specified program object.
     *
     * @param program the program object to be queried
     * @param name   the property of the each uniform in {@code uniformIndices} that should be written into the corresponding element of {@code params}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetActiveUniforms">Reference Page</a>
     */
    fun getActiveUniformsi(program: GlProgram, uniformIndex: Int, name: GetActiveUniform): Int = Stack { s ->
        s.intAddress { pParam ->
            GL31C.nglGetActiveUniformsiv(program.name, 1, s.intAddress(uniformIndex), name.i, pParam)
        }
    }

    // --- [ glGetActiveUniformName ] ---

    /**
     * Queries the name of an active uniform.
     *
     * @param program      the program containing the active uniform index {@code uniformIndex}
     * @param uniformIndex the index of the active uniform whose name to query
     * @param bufSize      the size of the buffer, in units of {@code GLchar}, of the buffer whose address is specified in {@code uniformName}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetActiveUniformName">Reference Page</a>
     */
    fun getActiveUniformName(program: GlProgram, uniformIndex: Int, bufSize: Int = getActiveUniformsi(program, uniformIndex, GetActiveUniform.UNIFORM_NAME_LENGTH)): String =
            Stack { s ->
                val pLength = s.nmalloc(4, Int.BYTES)
                val uniformName = s.Buffer(bufSize)
                GL31C.nglGetActiveUniformName(program.name, uniformIndex, bufSize, pLength, uniformName.adr)
                memASCII(uniformName, memGetInt(pLength))
            }

    // --- [ glGetUniformBlockIndex ] ---

    /**
     * Retrieves the index of a named uniform block.
     *
     * @param program          the name of a program containing the uniform block
     * @param uniformBlockName an array of characters to containing the name of the uniform block whose index to retrieve
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetUniformBlockIndex">Reference Page</a>
     */
    fun getUniformBlockIndex(program: GlProgram, uniformBlockName: CharSequence): UniformBlockIndex =
            Stack.asciiAddress(uniformBlockName) { GL31C.nglGetUniformBlockIndex(program.name, it) }

    // --- [ glGetActiveUniformBlockiv ] ---
    // inline reified

    // --- [ glGetActiveUniformBlockName ] ---

    /**
     * Retrieves the name of an active uniform block.
     *
     * @param program           the name of a program containing the uniform block
     * @param uniformBlockIndex the index of the uniform block within {@code program}
     * @param bufSize           the size of the buffer addressed by {@code uniformBlockName}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetActiveUniformBlockName">Reference Page</a>
     */
    fun getActiveUniformBlockName(program: GlProgram, uniformBlockIndex: UniformBlockIndex,
                                  bufSize: Int = gl.getActiveUniformBlockiv(program, uniformBlockIndex, GetActiveUniformBlock.NAME_LENGTH)): String =
            Stack {s ->
                val pLength = s.nmalloc(4, Int.BYTES)
                val uniformBlockName = s. Buffer(bufSize)
                GL31C.nglGetActiveUniformBlockName(program.name, uniformBlockIndex, bufSize, pLength, uniformBlockName.adr)
                memASCII(uniformBlockName, memGetInt(pLength))
            }

    // --- [ glUniformBlockBinding ] ---

    /**
     * Assigns a binding point to an active uniform block.
     *
     * @param program             the name of a program object containing the active uniform block whose binding to assign
     * @param uniformBlockIndex   the index of the active uniform block within {@code program} whose binding to assign
     * @param uniformBlockBinding the binding point to which to bind the uniform block with index {@code uniformBlockIndex} within {@code program}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniformBlockBinding">Reference Page</a>
     */
    fun uniformBlockBinding(program: GlProgram, uniformBlockIndex: UniformBlockIndex, uniformBlockBinding: Int) =
            GL31C.glUniformBlockBinding(program.name, uniformBlockIndex, uniformBlockBinding)
}