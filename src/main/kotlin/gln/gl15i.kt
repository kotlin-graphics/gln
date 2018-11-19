/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package gln

import glm_.L
import kool.adr
import kool.intBufferBig
import kool.rem
import kool.remSize
import gln.`object`.GlBuffer
import gln.`object`.GLbuffers
import gln.`object`.GlQueries
import gln.`object`.GlQuery
import kool.stak
import org.lwjgl.opengl.GL15C
import org.lwjgl.system.APIUtil
import org.lwjgl.system.MemoryUtil.*
import unsigned.Uint
import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.IntBuffer

/**
 * The OpenGL functionality of a forward compatible context, up to version 1.5.
 *
 * <p>Extensions promoted to core in this release:</p>
 *
 * <ul>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_vertex_buffer_object.txt">ARB_vertex_buffer_object</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_occlusion_query.txt">ARB_occlusion_query</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_shadow_funcs.txt">EXT_shadow_funcs</a></li>
 * </ul>
 */
interface gl15i {

    // --- [ glBindBuffer ] ---

    /**
     * Binds a named buffer object.
     *
     * @param target the target to which the buffer object is bound. One of:<br><table><tr><td>{@link #GL_ARRAY_BUFFER ARRAY_BUFFER}</td><td>{@link #GL_ELEMENT_ARRAY_BUFFER ELEMENT_ARRAY_BUFFER}</td><td>{@link GL21#GL_PIXEL_PACK_BUFFER PIXEL_PACK_BUFFER}</td><td>{@link GL21#GL_PIXEL_UNPACK_BUFFER PIXEL_UNPACK_BUFFER}</td></tr><tr><td>{@link GL30#GL_TRANSFORM_FEEDBACK_BUFFER TRANSFORM_FEEDBACK_BUFFER}</td><td>{@link GL31#GL_UNIFORM_BUFFER UNIFORM_BUFFER}</td><td>{@link GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link GL31#GL_COPY_READ_BUFFER COPY_READ_BUFFER}</td></tr><tr><td>{@link GL31#GL_COPY_WRITE_BUFFER COPY_WRITE_BUFFER}</td><td>{@link GL40#GL_DRAW_INDIRECT_BUFFER DRAW_INDIRECT_BUFFER}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td><td>{@link GL43#GL_DISPATCH_INDIRECT_BUFFER DISPATCH_INDIRECT_BUFFER}</td></tr><tr><td>{@link GL43#GL_SHADER_STORAGE_BUFFER SHADER_STORAGE_BUFFER}</td><td>{@link ARBIndirectParameters#GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB}</td></tr></table>
     * @param buffer the name of a buffer object
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBindBuffer">Reference Page</a>
     */
    fun bindBuffer(target: BufferTarget, buffer: GlBuffer) = GL15C.glBindBuffer(target.i, buffer.i)

    // --- [ glDeleteBuffers ] ---

    /**
     * Deletes named buffer objects.
     *
     * @param buffers an array of buffer objects to be deleted
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteBuffers">Reference Page</a>
     */
    infix fun deleteBuffers(buffers: IntBuffer) = GL15C.nglDeleteBuffers(buffers.rem, buffers.adr)

    /**
     * Deletes named buffer objects.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteBuffers">Reference Page</a>
     */
    infix fun deleteBuffer(buffer: GlBuffer) = stak.intAddress(buffer.i) { GL15C.nglDeleteBuffers(1, it) }

    // --- [ glGenBuffers ] ---

    /**
     * Generates buffer object names.
     *
     * @param buffers a buffer in which the generated buffer object names are stored
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenBuffers">Reference Page</a>
     */
    infix fun genBuffers(buffers: IntBuffer) = GL15C.nglGenBuffers(buffers.rem, buffers.adr)

    /**
     * Generates buffer object names.
     *
     * @param count a scalar representing the wished buffer names to be allocated
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenBuffers">Reference Page</a>
     */
    infix fun genBuffers(count: Int): GLbuffers = GLbuffers.big(count).apply { GL15C.nglGenBuffers(rem, adr) }

    /**
     * Generates buffer object names.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenBuffers">Reference Page</a>
     */
    fun genBuffer() = stak.intAddress { GL15C.nglGenBuffers(1, it) }

    // --- [ glIsBuffer ] ---

    /**
     * Determines if a name corresponds to a buffer object.
     *
     * @param buffer a value that may be the name of a buffer object
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glIsBuffer">Reference Page</a>
     */
    fun isBuffer(buffer: Int) = GL15C.glIsBuffer(buffer)

    // --- [ glBufferData ] ---

    /**
     * Creates and initializes a buffer object's data store.
     *
     * <p>{@code usage} is a hint to the GL implementation as to how a buffer object's data store will be accessed. This enables the GL implementation to make
     * more intelligent decisions that may significantly impact buffer object performance. It does not, however, constrain the actual usage of the data store.
     * {@code usage} can be broken down into two parts: first, the frequency of access (modification and usage), and second, the nature of that access. The
     * frequency of access may be one of these:</p>
     *
     * <ul>
     * <li><em>STREAM</em> - The data store contents will be modified once and used at most a few times.</li>
     * <li><em>STATIC</em> - The data store contents will be modified once and used many times.</li>
     * <li><em>DYNAMIC</em> - The data store contents will be modified repeatedly and used many times.</li>
     * </ul>
     *
     * <p>The nature of access may be one of these:</p>
     *
     * <ul>
     * <li><em>DRAW</em> - The data store contents are modified by the application, and used as the source for GL drawing and image specification commands.</li>
     * <li><em>READ</em> - The data store contents are modified by reading data from the GL, and used to return that data when queried by the application.</li>
     * <li><em>COPY</em> - The data store contents are modified by reading data from the GL, and used as the source for GL drawing and image specification commands.</li>
     * </ul>
     *
     * @param target the target buffer object. One of:<br><table><tr><td>{@link #GL_ARRAY_BUFFER ARRAY_BUFFER}</td><td>{@link #GL_ELEMENT_ARRAY_BUFFER ELEMENT_ARRAY_BUFFER}</td><td>{@link GL21#GL_PIXEL_PACK_BUFFER PIXEL_PACK_BUFFER}</td><td>{@link GL21#GL_PIXEL_UNPACK_BUFFER PIXEL_UNPACK_BUFFER}</td></tr><tr><td>{@link GL30#GL_TRANSFORM_FEEDBACK_BUFFER TRANSFORM_FEEDBACK_BUFFER}</td><td>{@link GL31#GL_UNIFORM_BUFFER UNIFORM_BUFFER}</td><td>{@link GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link GL31#GL_COPY_READ_BUFFER COPY_READ_BUFFER}</td></tr><tr><td>{@link GL31#GL_COPY_WRITE_BUFFER COPY_WRITE_BUFFER}</td><td>{@link GL40#GL_DRAW_INDIRECT_BUFFER DRAW_INDIRECT_BUFFER}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td><td>{@link GL43#GL_DISPATCH_INDIRECT_BUFFER DISPATCH_INDIRECT_BUFFER}</td></tr><tr><td>{@link GL43#GL_SHADER_STORAGE_BUFFER SHADER_STORAGE_BUFFER}</td><td>{@link ARBIndirectParameters#GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB}</td></tr></table>
     * @param size   the size in bytes of the buffer object's new data store
     * @param usage  the expected usage pattern of the data store. One of:<br><table><tr><td>{@link #GL_STREAM_DRAW STREAM_DRAW}</td><td>{@link #GL_STREAM_READ STREAM_READ}</td><td>{@link #GL_STREAM_COPY STREAM_COPY}</td><td>{@link #GL_STATIC_DRAW STATIC_DRAW}</td><td>{@link #GL_STATIC_READ STATIC_READ}</td><td>{@link #GL_STATIC_COPY STATIC_COPY}</td><td>{@link #GL_DYNAMIC_DRAW DYNAMIC_DRAW}</td></tr><tr><td>{@link #GL_DYNAMIC_READ DYNAMIC_READ}</td><td>{@link #GL_DYNAMIC_COPY DYNAMIC_COPY}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferData">Reference Page</a>
     */
    fun bufferData(target: BufferTarget, size: Int, usage: Usage) = GL15C.nglBufferData(target.i, size.L, NULL, usage.i)

    /**
     * Creates and initializes a buffer object's data store.
     *
     * <p>{@code usage} is a hint to the GL implementation as to how a buffer object's data store will be accessed. This enables the GL implementation to make
     * more intelligent decisions that may significantly impact buffer object performance. It does not, however, constrain the actual usage of the data store.
     * {@code usage} can be broken down into two parts: first, the frequency of access (modification and usage), and second, the nature of that access. The
     * frequency of access may be one of these:</p>
     *
     * <ul>
     * <li><em>STREAM</em> - The data store contents will be modified once and used at most a few times.</li>
     * <li><em>STATIC</em> - The data store contents will be modified once and used many times.</li>
     * <li><em>DYNAMIC</em> - The data store contents will be modified repeatedly and used many times.</li>
     * </ul>
     *
     * <p>The nature of access may be one of these:</p>
     *
     * <ul>
     * <li><em>DRAW</em> - The data store contents are modified by the application, and used as the source for GL drawing and image specification commands.</li>
     * <li><em>READ</em> - The data store contents are modified by reading data from the GL, and used to return that data when queried by the application.</li>
     * <li><em>COPY</em> - The data store contents are modified by reading data from the GL, and used as the source for GL drawing and image specification commands.</li>
     * </ul>
     *
     * @param target the target buffer object. One of:<br><table><tr><td>{@link #GL_ARRAY_BUFFER ARRAY_BUFFER}</td><td>{@link #GL_ELEMENT_ARRAY_BUFFER ELEMENT_ARRAY_BUFFER}</td><td>{@link GL21#GL_PIXEL_PACK_BUFFER PIXEL_PACK_BUFFER}</td><td>{@link GL21#GL_PIXEL_UNPACK_BUFFER PIXEL_UNPACK_BUFFER}</td></tr><tr><td>{@link GL30#GL_TRANSFORM_FEEDBACK_BUFFER TRANSFORM_FEEDBACK_BUFFER}</td><td>{@link GL31#GL_UNIFORM_BUFFER UNIFORM_BUFFER}</td><td>{@link GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link GL31#GL_COPY_READ_BUFFER COPY_READ_BUFFER}</td></tr><tr><td>{@link GL31#GL_COPY_WRITE_BUFFER COPY_WRITE_BUFFER}</td><td>{@link GL40#GL_DRAW_INDIRECT_BUFFER DRAW_INDIRECT_BUFFER}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td><td>{@link GL43#GL_DISPATCH_INDIRECT_BUFFER DISPATCH_INDIRECT_BUFFER}</td></tr><tr><td>{@link GL43#GL_SHADER_STORAGE_BUFFER SHADER_STORAGE_BUFFER}</td><td>{@link ARBIndirectParameters#GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB}</td></tr></table>
     * @param data   a pointer to data that will be copied into the data store for initialization, or {@code NULL} if no data is to be copied
     * @param usage  the expected usage pattern of the data store. One of:<br><table><tr><td>{@link #GL_STREAM_DRAW STREAM_DRAW}</td><td>{@link #GL_STREAM_READ STREAM_READ}</td><td>{@link #GL_STREAM_COPY STREAM_COPY}</td><td>{@link #GL_STATIC_DRAW STATIC_DRAW}</td><td>{@link #GL_STATIC_READ STATIC_READ}</td><td>{@link #GL_STATIC_COPY STATIC_COPY}</td><td>{@link #GL_DYNAMIC_DRAW DYNAMIC_DRAW}</td></tr><tr><td>{@link #GL_DYNAMIC_READ DYNAMIC_READ}</td><td>{@link #GL_DYNAMIC_COPY DYNAMIC_COPY}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferData">Reference Page</a>
     */
    fun bufferData(target: BufferTarget, data: Buffer, usage: Usage) = GL15C.nglBufferData(target.i, data.remSize.L, data.adr, usage.i)

    // --- [ glBufferSubData ] ---

    /**
     * Updates a subset of a buffer object's data store.
     *
     * @param target the target buffer object. One of:<br><table><tr><td>{@link #GL_ARRAY_BUFFER ARRAY_BUFFER}</td><td>{@link #GL_ELEMENT_ARRAY_BUFFER ELEMENT_ARRAY_BUFFER}</td><td>{@link GL21#GL_PIXEL_PACK_BUFFER PIXEL_PACK_BUFFER}</td><td>{@link GL21#GL_PIXEL_UNPACK_BUFFER PIXEL_UNPACK_BUFFER}</td></tr><tr><td>{@link GL30#GL_TRANSFORM_FEEDBACK_BUFFER TRANSFORM_FEEDBACK_BUFFER}</td><td>{@link GL31#GL_UNIFORM_BUFFER UNIFORM_BUFFER}</td><td>{@link GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link GL31#GL_COPY_READ_BUFFER COPY_READ_BUFFER}</td></tr><tr><td>{@link GL31#GL_COPY_WRITE_BUFFER COPY_WRITE_BUFFER}</td><td>{@link GL40#GL_DRAW_INDIRECT_BUFFER DRAW_INDIRECT_BUFFER}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td><td>{@link GL43#GL_DISPATCH_INDIRECT_BUFFER DISPATCH_INDIRECT_BUFFER}</td></tr><tr><td>{@link GL43#GL_SHADER_STORAGE_BUFFER SHADER_STORAGE_BUFFER}</td><td>{@link ARBIndirectParameters#GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB}</td></tr></table>
     * @param offset the offset into the buffer object's data store where data replacement will begin, measured in bytes
     * @param data   a pointer to the new data that will be copied into the data store
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferSubData">Reference Page</a>
     */
    fun bufferSubData(target: BufferTarget, offset: Int, data: Buffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remSize.L, data.adr)

    // --- [ glGetBufferSubData ] ---

    /**
     * Returns a subset of a buffer object's data store.
     *
     * @param target the target buffer object. One of:<br><table><tr><td>{@link #GL_ARRAY_BUFFER ARRAY_BUFFER}</td><td>{@link #GL_ELEMENT_ARRAY_BUFFER ELEMENT_ARRAY_BUFFER}</td><td>{@link GL21#GL_PIXEL_PACK_BUFFER PIXEL_PACK_BUFFER}</td><td>{@link GL21#GL_PIXEL_UNPACK_BUFFER PIXEL_UNPACK_BUFFER}</td></tr><tr><td>{@link GL30#GL_TRANSFORM_FEEDBACK_BUFFER TRANSFORM_FEEDBACK_BUFFER}</td><td>{@link GL31#GL_UNIFORM_BUFFER UNIFORM_BUFFER}</td><td>{@link GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link GL31#GL_COPY_READ_BUFFER COPY_READ_BUFFER}</td></tr><tr><td>{@link GL31#GL_COPY_WRITE_BUFFER COPY_WRITE_BUFFER}</td><td>{@link GL40#GL_DRAW_INDIRECT_BUFFER DRAW_INDIRECT_BUFFER}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td><td>{@link GL43#GL_DISPATCH_INDIRECT_BUFFER DISPATCH_INDIRECT_BUFFER}</td></tr><tr><td>{@link GL43#GL_SHADER_STORAGE_BUFFER SHADER_STORAGE_BUFFER}</td><td>{@link ARBIndirectParameters#GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB}</td></tr></table>
     * @param offset the offset into the buffer object's data store from which data will be returned, measured in bytes
     * @param data   a pointer to the location where buffer object data is returned
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetBufferSubData">Reference Page</a>
     */
    fun getBufferSubData(target: BufferTarget, offset: Int, data: Buffer) = GL15C.nglGetBufferSubData(target.i, offset.L, data.remSize.L, data.adr)

    // --- [ glMapBuffer ] ---

    /**
     * Maps a buffer object's data store.
     *
     * <p><b>LWJGL note</b>: This method comes in 3 flavors:</p>
     *
     * <ol>
     * <li>{@link #glMapBuffer(int, int)} - Calls {@link #glGetBufferParameteriv GetBufferParameteriv} to retrieve the buffer size and a new ByteBuffer instance is always returned.</li>
     * <li>{@link #glMapBuffer(int, int, ByteBuffer)} - Calls {@link #glGetBufferParameteriv GetBufferParameteriv} to retrieve the buffer size and the {@code old_buffer} parameter is reused if not null.</li>
     * <li>{@link #glMapBuffer(int, int, long, ByteBuffer)} - The buffer size is explicitly specified and the {@code old_buffer} parameter is reused if not null. This is the most efficient method.</li>
     * </ol>
     *
     * @param target the target buffer object being mapped. One of:<br><table><tr><td>{@link #GL_ARRAY_BUFFER ARRAY_BUFFER}</td><td>{@link #GL_ELEMENT_ARRAY_BUFFER ELEMENT_ARRAY_BUFFER}</td><td>{@link GL21#GL_PIXEL_PACK_BUFFER PIXEL_PACK_BUFFER}</td><td>{@link GL21#GL_PIXEL_UNPACK_BUFFER PIXEL_UNPACK_BUFFER}</td></tr><tr><td>{@link GL30#GL_TRANSFORM_FEEDBACK_BUFFER TRANSFORM_FEEDBACK_BUFFER}</td><td>{@link GL31#GL_UNIFORM_BUFFER UNIFORM_BUFFER}</td><td>{@link GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link GL31#GL_COPY_READ_BUFFER COPY_READ_BUFFER}</td></tr><tr><td>{@link GL31#GL_COPY_WRITE_BUFFER COPY_WRITE_BUFFER}</td><td>{@link GL40#GL_DRAW_INDIRECT_BUFFER DRAW_INDIRECT_BUFFER}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td><td>{@link GL43#GL_DISPATCH_INDIRECT_BUFFER DISPATCH_INDIRECT_BUFFER}</td></tr><tr><td>{@link GL43#GL_SHADER_STORAGE_BUFFER SHADER_STORAGE_BUFFER}</td><td>{@link ARBIndirectParameters#GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB}</td></tr></table>
     * @param access the access policy, indicating whether it will be possible to read from, write to, or both read from and write to the buffer object's mapped data store. One of:<br><table><tr><td>{@link #GL_READ_ONLY READ_ONLY}</td><td>{@link #GL_WRITE_ONLY WRITE_ONLY}</td><td>{@link #GL_READ_WRITE READ_WRITE}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glMapBuffer">Reference Page</a>
     */
    fun mapBuffer(target: BufferTarget, access: BufferAccess): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        return memByteBuffer(ptr, GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)).takeIf { ptr != NULL }
    }

    /**
     * Maps a buffer object's data store.
     *
     * <p><b>LWJGL note</b>: This method comes in 3 flavors:</p>
     *
     * <ol>
     * <li>{@link #glMapBuffer(int, int)} - Calls {@link #glGetBufferParameteriv GetBufferParameteriv} to retrieve the buffer size and a new ByteBuffer instance is always returned.</li>
     * <li>{@link #glMapBuffer(int, int, ByteBuffer)} - Calls {@link #glGetBufferParameteriv GetBufferParameteriv} to retrieve the buffer size and the {@code oldBuffer} parameter is reused if not null.</li>
     * <li>{@link #glMapBuffer(int, int, long, ByteBuffer)} - The buffer size is explicitly specified and the {@code oldBuffer} parameter is reused if not null. This is the most efficient method.</li>
     * </ol>
     *
     * @param target the target buffer object being mapped. One of:<br><table><tr><td>{@link #GL_ARRAY_BUFFER ARRAY_BUFFER}</td><td>{@link #GL_ELEMENT_ARRAY_BUFFER ELEMENT_ARRAY_BUFFER}</td><td>{@link GL21#GL_PIXEL_PACK_BUFFER PIXEL_PACK_BUFFER}</td><td>{@link GL21#GL_PIXEL_UNPACK_BUFFER PIXEL_UNPACK_BUFFER}</td></tr><tr><td>{@link GL30#GL_TRANSFORM_FEEDBACK_BUFFER TRANSFORM_FEEDBACK_BUFFER}</td><td>{@link GL31#GL_UNIFORM_BUFFER UNIFORM_BUFFER}</td><td>{@link GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link GL31#GL_COPY_READ_BUFFER COPY_READ_BUFFER}</td></tr><tr><td>{@link GL31#GL_COPY_WRITE_BUFFER COPY_WRITE_BUFFER}</td><td>{@link GL40#GL_DRAW_INDIRECT_BUFFER DRAW_INDIRECT_BUFFER}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td><td>{@link GL43#GL_DISPATCH_INDIRECT_BUFFER DISPATCH_INDIRECT_BUFFER}</td></tr><tr><td>{@link GL43#GL_SHADER_STORAGE_BUFFER SHADER_STORAGE_BUFFER}</td><td>{@link ARBIndirectParameters#GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB}</td></tr></table>
     * @param access the access policy, indicating whether it will be possible to read from, write to, or both read from and write to the buffer object's mapped data store. One of:<br><table><tr><td>{@link #GL_READ_ONLY READ_ONLY}</td><td>{@link #GL_WRITE_ONLY WRITE_ONLY}</td><td>{@link #GL_READ_WRITE READ_WRITE}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glMapBuffer">Reference Page</a>
     */
    fun mapBuffer(target: BufferTarget, access: BufferAccess, oldBuffer: ByteBuffer? = null): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        val length = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)
        return APIUtil.apiGetMappedBuffer(oldBuffer, ptr, length)
    }

    /**
     * Maps a buffer object's data store.
     *
     * <p><b>LWJGL note</b>: This method comes in 3 flavors:</p>
     *
     * <ol>
     * <li>{@link #glMapBuffer(int, int)} - Calls {@link #glGetBufferParameteriv GetBufferParameteriv} to retrieve the buffer size and a new ByteBuffer instance is always returned.</li>
     * <li>{@link #glMapBuffer(int, int, ByteBuffer)} - Calls {@link #glGetBufferParameteriv GetBufferParameteriv} to retrieve the buffer size and the {@code oldBuffer} parameter is reused if not null.</li>
     * <li>{@link #glMapBuffer(int, int, long, ByteBuffer)} - The buffer size is explicitly specified and the {@code oldBuffer} parameter is reused if not null. This is the most efficient method.</li>
     * </ol>
     *
     * @param target the target buffer object being mapped. One of:<br><table><tr><td>{@link #GL_ARRAY_BUFFER ARRAY_BUFFER}</td><td>{@link #GL_ELEMENT_ARRAY_BUFFER ELEMENT_ARRAY_BUFFER}</td><td>{@link GL21#GL_PIXEL_PACK_BUFFER PIXEL_PACK_BUFFER}</td><td>{@link GL21#GL_PIXEL_UNPACK_BUFFER PIXEL_UNPACK_BUFFER}</td></tr><tr><td>{@link GL30#GL_TRANSFORM_FEEDBACK_BUFFER TRANSFORM_FEEDBACK_BUFFER}</td><td>{@link GL31#GL_UNIFORM_BUFFER UNIFORM_BUFFER}</td><td>{@link GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link GL31#GL_COPY_READ_BUFFER COPY_READ_BUFFER}</td></tr><tr><td>{@link GL31#GL_COPY_WRITE_BUFFER COPY_WRITE_BUFFER}</td><td>{@link GL40#GL_DRAW_INDIRECT_BUFFER DRAW_INDIRECT_BUFFER}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td><td>{@link GL43#GL_DISPATCH_INDIRECT_BUFFER DISPATCH_INDIRECT_BUFFER}</td></tr><tr><td>{@link GL43#GL_SHADER_STORAGE_BUFFER SHADER_STORAGE_BUFFER}</td><td>{@link ARBIndirectParameters#GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB}</td></tr></table>
     * @param access the access policy, indicating whether it will be possible to read from, write to, or both read from and write to the buffer object's mapped data store. One of:<br><table><tr><td>{@link #GL_READ_ONLY READ_ONLY}</td><td>{@link #GL_WRITE_ONLY WRITE_ONLY}</td><td>{@link #GL_READ_WRITE READ_WRITE}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glMapBuffer">Reference Page</a>
     */
    fun mapBuffer(target: BufferTarget, access: BufferAccess, length: Int, oldBuffer: ByteBuffer?): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        return APIUtil.apiGetMappedBuffer(oldBuffer, ptr, length)
    }

    // --- [ glUnmapBuffer ] ---

    /**
     * Relinquishes the mapping of a buffer object and invalidates the pointer to its data store.
     *
     * <p>Returns TRUE unless data values in the buffer’s data store have become corrupted during the period that the buffer was mapped. Such corruption can be
     * the result of a screen resolution change or other window system-dependent event that causes system heaps such as those for high-performance graphics
     * memory to be discarded. GL implementations must guarantee that such corruption can occur only during the periods that a buffer’s data store is mapped.
     * If such corruption has occurred, UnmapBuffer returns FALSE, and the contents of the buffer’s data store become undefined.</p>
     *
     * @param target the target buffer object being unmapped. One of:<br><table><tr><td>{@link #GL_ARRAY_BUFFER ARRAY_BUFFER}</td><td>{@link #GL_ELEMENT_ARRAY_BUFFER ELEMENT_ARRAY_BUFFER}</td><td>{@link GL21#GL_PIXEL_PACK_BUFFER PIXEL_PACK_BUFFER}</td><td>{@link GL21#GL_PIXEL_UNPACK_BUFFER PIXEL_UNPACK_BUFFER}</td></tr><tr><td>{@link GL30#GL_TRANSFORM_FEEDBACK_BUFFER TRANSFORM_FEEDBACK_BUFFER}</td><td>{@link GL31#GL_UNIFORM_BUFFER UNIFORM_BUFFER}</td><td>{@link GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link GL31#GL_COPY_READ_BUFFER COPY_READ_BUFFER}</td></tr><tr><td>{@link GL31#GL_COPY_WRITE_BUFFER COPY_WRITE_BUFFER}</td><td>{@link GL40#GL_DRAW_INDIRECT_BUFFER DRAW_INDIRECT_BUFFER}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td><td>{@link GL43#GL_DISPATCH_INDIRECT_BUFFER DISPATCH_INDIRECT_BUFFER}</td></tr><tr><td>{@link GL43#GL_SHADER_STORAGE_BUFFER SHADER_STORAGE_BUFFER}</td><td>{@link ARBIndirectParameters#GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUnmapBuffer">Reference Page</a>
     */
    fun unmapBuffer(target: BufferTarget): Boolean = GL15C.glUnmapBuffer(target.i)

    // --- [ glGetBufferParameteriv ] ---

    /**
     * Returns the value of a buffer object parameter.
     *
     * @param target the target buffer object. One of:<br><table><tr><td>{@link #GL_ARRAY_BUFFER ARRAY_BUFFER}</td><td>{@link #GL_ELEMENT_ARRAY_BUFFER ELEMENT_ARRAY_BUFFER}</td><td>{@link GL21#GL_PIXEL_PACK_BUFFER PIXEL_PACK_BUFFER}</td><td>{@link GL21#GL_PIXEL_UNPACK_BUFFER PIXEL_UNPACK_BUFFER}</td></tr><tr><td>{@link GL30#GL_TRANSFORM_FEEDBACK_BUFFER TRANSFORM_FEEDBACK_BUFFER}</td><td>{@link GL31#GL_UNIFORM_BUFFER UNIFORM_BUFFER}</td><td>{@link GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link GL31#GL_COPY_READ_BUFFER COPY_READ_BUFFER}</td></tr><tr><td>{@link GL31#GL_COPY_WRITE_BUFFER COPY_WRITE_BUFFER}</td><td>{@link GL40#GL_DRAW_INDIRECT_BUFFER DRAW_INDIRECT_BUFFER}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td><td>{@link GL43#GL_DISPATCH_INDIRECT_BUFFER DISPATCH_INDIRECT_BUFFER}</td></tr><tr><td>{@link GL43#GL_SHADER_STORAGE_BUFFER SHADER_STORAGE_BUFFER}</td><td>{@link ARBIndirectParameters#GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB}</td></tr></table>
     * @param pname  the symbolic name of a buffer object parameter. One of:<br><table><tr><td>{@link GL15#GL_BUFFER_SIZE BUFFER_SIZE}</td><td>{@link #GL_BUFFER_USAGE BUFFER_USAGE}</td><td>{@link #GL_BUFFER_ACCESS BUFFER_ACCESS}</td><td>{@link #GL_BUFFER_MAPPED BUFFER_MAPPED}</td></tr><tr><td>{@link GL30#GL_BUFFER_ACCESS_FLAGS BUFFER_ACCESS_FLAGS}</td><td>{@link GL30#GL_BUFFER_MAP_LENGTH BUFFER_MAP_LENGTH}</td><td>{@link GL30#GL_BUFFER_MAP_OFFSET BUFFER_MAP_OFFSET}</td><td>{@link GL44#GL_BUFFER_IMMUTABLE_STORAGE BUFFER_IMMUTABLE_STORAGE}</td></tr><tr><td>{@link GL44#GL_BUFFER_STORAGE_FLAGS BUFFER_STORAGE_FLAGS}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetBufferParameter">Reference Page</a>
     */
    fun getBufferParameterI(target: BufferTarget, pName: BufferParameter) = stak.intAddress { GL15C.nglGetBufferParameteriv(target.i, pName.i, it) }

    // --- [ glGetBufferPointerv ] ---

    /**
     * Returns the pointer to a mapped buffer object's data store.
     *
     * @param target the target buffer object. One of:<br><table><tr><td>{@link #GL_ARRAY_BUFFER ARRAY_BUFFER}</td><td>{@link #GL_ELEMENT_ARRAY_BUFFER ELEMENT_ARRAY_BUFFER}</td><td>{@link GL21#GL_PIXEL_PACK_BUFFER PIXEL_PACK_BUFFER}</td><td>{@link GL21#GL_PIXEL_UNPACK_BUFFER PIXEL_UNPACK_BUFFER}</td></tr><tr><td>{@link GL30#GL_TRANSFORM_FEEDBACK_BUFFER TRANSFORM_FEEDBACK_BUFFER}</td><td>{@link GL31#GL_UNIFORM_BUFFER UNIFORM_BUFFER}</td><td>{@link GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link GL31#GL_COPY_READ_BUFFER COPY_READ_BUFFER}</td></tr><tr><td>{@link GL31#GL_COPY_WRITE_BUFFER COPY_WRITE_BUFFER}</td><td>{@link GL40#GL_DRAW_INDIRECT_BUFFER DRAW_INDIRECT_BUFFER}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td><td>{@link GL43#GL_DISPATCH_INDIRECT_BUFFER DISPATCH_INDIRECT_BUFFER}</td></tr><tr><td>{@link GL43#GL_SHADER_STORAGE_BUFFER SHADER_STORAGE_BUFFER}</td><td>{@link ARBIndirectParameters#GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB}</td></tr></table>
     * @param pname  the pointer to be returned. Must be:<br><table><tr><td>{@link #GL_BUFFER_MAP_POINTER BUFFER_MAP_POINTER}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetBufferPointerv">Reference Page</a>
     */
    infix fun getBufferPointer(target: BufferTarget) = stak.pointerAddress { GL15C.nglGetBufferPointerv(target.i, GL15C.GL_BUFFER_MAP_POINTER, it) }

    // --- [ glGenQueries ] ---

    /**
     * Generates query object names.
     *
     * @param ids a buffer in which the generated query object names are stored
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenQueries">Reference Page</a>
     */
    infix fun genQueries(ids: GlQueries) = GL15C.nglGenQueries(ids.rem, ids.adr)

    /**
     * Generates query object names.
     *
     * @param count an int representing the wished queries names to be allocated
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenQueries">Reference Page</a>
     */
    infix fun genQueries(count: Int): GlQueries = GlQueries(intBufferBig(count).apply { GL15C.nglGenQueries(rem, adr) })

    /**
     * Generates query object names.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenQueries">Reference Page</a>
     */
    fun genQuery(): GlQuery = GlQuery(stak.intAddress { GL15C.nglGenQueries(1, it) })

    // --- [ glDeleteQueries ] ---

    /**
     * Deletes named query objects.
     *
     * @param ids an array of query objects to be deleted
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteQueries">Reference Page</a>
     */
    fun deleteQueries(ids: GlQueries) = GL15C.nglDeleteQueries(ids.rem, ids.adr)

    /**
     * Deletes named query objects.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteQueries">Reference Page</a>
     */
    fun deleteQuery(id: GlQuery) = stak.intAddress(id.i) { GL15C.nglDeleteQueries(1, it) }

    // --- [ glIsQuery ] ---

    /**
     * Determine if a name corresponds to a query object.
     *
     * @param id a value that may be the name of a query object
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glIsQuery">Reference Page</a>
     */
    fun isQuery(id: GlQuery) = GL15C.glIsQuery(id.i)

    // --- [ glBeginQuery ] ---

    /**
     * Creates a query object and makes it active.
     *
     * @param target the target type of query object established. One of:<br><table><tr><td>{@link #GL_SAMPLES_PASSED SAMPLES_PASSED}</td><td>{@link GL30#GL_PRIMITIVES_GENERATED PRIMITIVES_GENERATED}</td><td>{@link GL30#GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN}</td><td>{@link GL33#GL_TIME_ELAPSED TIME_ELAPSED}</td></tr><tr><td>{@link GL33#GL_TIMESTAMP TIMESTAMP}</td><td>{@link GL33#GL_ANY_SAMPLES_PASSED ANY_SAMPLES_PASSED}</td><td>{@link GL43#GL_ANY_SAMPLES_PASSED_CONSERVATIVE ANY_SAMPLES_PASSED_CONSERVATIVE}</td></tr></table>
     * @param id     the name of a query object
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBeginQuery">Reference Page</a>
     */
    fun beginQuery(target: QueryTarget, id: GlQuery) = GL15C.glBeginQuery(target.i, id.i)

    // --- [ glEndQuery ] ---

    /**
     * Marks the end of the sequence of commands to be tracked for the active query specified by {@code target}.
     *
     * @param target the query object target. One of:<br><table><tr><td>{@link #GL_SAMPLES_PASSED SAMPLES_PASSED}</td><td>{@link GL30#GL_PRIMITIVES_GENERATED PRIMITIVES_GENERATED}</td><td>{@link GL30#GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN}</td><td>{@link GL33#GL_TIME_ELAPSED TIME_ELAPSED}</td></tr><tr><td>{@link GL33#GL_TIMESTAMP TIMESTAMP}</td><td>{@link GL33#GL_ANY_SAMPLES_PASSED ANY_SAMPLES_PASSED}</td><td>{@link GL43#GL_ANY_SAMPLES_PASSED_CONSERVATIVE ANY_SAMPLES_PASSED_CONSERVATIVE}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glEndQuery">Reference Page</a>
     */
    fun endQuery(target: QueryTarget) = GL15C.glEndQuery(target.i)

    // --- [ glGetQueryiv ] ---

    /**
     * Returns parameters of a query object target.
     *
     * @param target the query object target. One of:<br><table><tr><td>{@link #GL_SAMPLES_PASSED SAMPLES_PASSED}</td><td>{@link GL30#GL_PRIMITIVES_GENERATED PRIMITIVES_GENERATED}</td><td>{@link GL30#GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN}</td><td>{@link GL33#GL_TIME_ELAPSED TIME_ELAPSED}</td></tr><tr><td>{@link GL33#GL_TIMESTAMP TIMESTAMP}</td><td>{@link GL33#GL_ANY_SAMPLES_PASSED ANY_SAMPLES_PASSED}</td><td>{@link GL43#GL_ANY_SAMPLES_PASSED_CONSERVATIVE ANY_SAMPLES_PASSED_CONSERVATIVE}</td></tr></table>
     * @param pname  the symbolic name of a query object target parameter. One of:<br><table><tr><td>{@link #GL_QUERY_COUNTER_BITS QUERY_COUNTER_BITS}</td><td>{@link #GL_CURRENT_QUERY CURRENT_QUERY}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetQuery">Reference Page</a>
     */
    fun getQueryI(target: QueryTarget, pName: GetQuery): Int = stak.intAddress { GL15C.nglGetQueryiv(target.i, pName.i, it) }

    // --- [ glGetQueryObjectiv ] ---

    /**
     * Returns the integer value of a query object parameter.
     *
     * @param id    the name of a query object
     * @param pName the symbolic name of a query object parameter. One of:<br><table><tr><td>{@link #GL_QUERY_RESULT QUERY_RESULT}</td><td>{@link #GL_QUERY_RESULT_AVAILABLE QUERY_RESULT_AVAILABLE}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetQueryObject">Reference Page</a>
     */
    fun getQueryObjectI(id: GlQuery, pName: GetQueryObject): Int = stak.intAddress { GL15C.nglGetQueryObjectiv(id.i, pName.i, it) }

    // --- [ glGetQueryObjectuiv ] ---

    /**
     * Unsigned version of {@link #glGetQueryObjectiv GetQueryObjectiv}.
     *
     * @param id    the name of a query object
     * @param pname the symbolic name of a query object parameter. One of:<br><table><tr><td>{@link #GL_QUERY_RESULT QUERY_RESULT}</td><td>{@link #GL_QUERY_RESULT_AVAILABLE QUERY_RESULT_AVAILABLE}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetQueryObject">Reference Page</a>
     */
    fun getQueryObjectUI(id: GlQuery, pName: GetQueryObject): Uint = Uint(stak.intAddress { GL15C.nglGetQueryObjectiv(id.i, pName.i, it) })
}