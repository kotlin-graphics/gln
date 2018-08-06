/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package gln

import ab.appBuffer
import glm_.L
import glm_.buffer.adr
import glm_.buffer.intBufferBig
import glm_.buffer.rem
import org.lwjgl.opengl.GL15C
import org.lwjgl.system.APIUtil
import org.lwjgl.system.MemoryUtil
import org.lwjgl.system.MemoryUtil.*
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
    fun bindBuffer(target: BufferTarget, buffer: GLbuffer) = GL15C.glBindBuffer(target.i, buffer.i)

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
    infix fun deleteBuffer(buffer: GLbuffer) {
        val buff = appBuffer.int
        MemoryUtil.memPutInt(buff, buffer.i)
        GL15C.nglDeleteBuffers(1, buff)
    }

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
    infix fun genBuffers(count: Int): GLbuffers = GLbuffers(intBufferBig(count).apply { GL15C.nglGenBuffers(rem, adr) })

    /**
     * Generates buffer object names.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenBuffers">Reference Page</a>
     */
    fun genBuffer(): Int {
        val buff = appBuffer.int
        GL15C.nglGenBuffers(1, buff)
        return MemoryUtil.memGetInt(buff)
    }

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
    fun bufferData(target: BufferTarget, data: Buffer, usage: Usage) = GL15C.nglBufferData(target.i, data.remSize, data.adr, usage.i)

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
    fun bufferSubData(target: BufferTarget, offset: Int, data: Buffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remSize, data.adr)

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
        val ptr = Ptr(GL15C.nglMapBuffer(target.i, access.i))
        return memByteBuffer(ptr.L, GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)).takeIf { ptr.valid }
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
        val ptr = Ptr(GL15C.nglMapBuffer(target.i, access.i))
        val length = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)
        return APIUtil.apiGetMappedBuffer(oldBuffer, ptr.L, length)
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
        val ptr = Ptr(GL15C.nglMapBuffer(target.i, access.i))
        return APIUtil.apiGetMappedBuffer(oldBuffer, ptr.L, length)
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
    @NativeType("GLboolean")
    public static native boolean glUnmapBuffer(@NativeType("GLenum") int target);

    // --- [ glGetBufferParameteriv ] ---

    /** Unsafe version of: {@link #glGetBufferParameteriv GetBufferParameteriv} */
    public static native void nglGetBufferParameteriv(int target, int pname, long params);

    /**
     * Returns the value of a buffer object parameter.
     *
     * @param target the target buffer object. One of:<br><table><tr><td>{@link #GL_ARRAY_BUFFER ARRAY_BUFFER}</td><td>{@link #GL_ELEMENT_ARRAY_BUFFER ELEMENT_ARRAY_BUFFER}</td><td>{@link GL21#GL_PIXEL_PACK_BUFFER PIXEL_PACK_BUFFER}</td><td>{@link GL21#GL_PIXEL_UNPACK_BUFFER PIXEL_UNPACK_BUFFER}</td></tr><tr><td>{@link GL30#GL_TRANSFORM_FEEDBACK_BUFFER TRANSFORM_FEEDBACK_BUFFER}</td><td>{@link GL31#GL_UNIFORM_BUFFER UNIFORM_BUFFER}</td><td>{@link GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link GL31#GL_COPY_READ_BUFFER COPY_READ_BUFFER}</td></tr><tr><td>{@link GL31#GL_COPY_WRITE_BUFFER COPY_WRITE_BUFFER}</td><td>{@link GL40#GL_DRAW_INDIRECT_BUFFER DRAW_INDIRECT_BUFFER}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td><td>{@link GL43#GL_DISPATCH_INDIRECT_BUFFER DISPATCH_INDIRECT_BUFFER}</td></tr><tr><td>{@link GL43#GL_SHADER_STORAGE_BUFFER SHADER_STORAGE_BUFFER}</td><td>{@link ARBIndirectParameters#GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB}</td></tr></table>
     * @param pname  the symbolic name of a buffer object parameter. One of:<br><table><tr><td>{@link GL15#GL_BUFFER_SIZE BUFFER_SIZE}</td><td>{@link #GL_BUFFER_USAGE BUFFER_USAGE}</td><td>{@link #GL_BUFFER_ACCESS BUFFER_ACCESS}</td><td>{@link #GL_BUFFER_MAPPED BUFFER_MAPPED}</td></tr><tr><td>{@link GL30#GL_BUFFER_ACCESS_FLAGS BUFFER_ACCESS_FLAGS}</td><td>{@link GL30#GL_BUFFER_MAP_LENGTH BUFFER_MAP_LENGTH}</td><td>{@link GL30#GL_BUFFER_MAP_OFFSET BUFFER_MAP_OFFSET}</td><td>{@link GL44#GL_BUFFER_IMMUTABLE_STORAGE BUFFER_IMMUTABLE_STORAGE}</td></tr><tr><td>{@link GL44#GL_BUFFER_STORAGE_FLAGS BUFFER_STORAGE_FLAGS}</td></tr></table>
     * @param params the requested parameter
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetBufferParameter">Reference Page</a>
     */
    public static void glGetBufferParameteriv(@NativeType("GLenum") int target, @NativeType("GLenum") int pname, @NativeType("GLint *") IntBuffer params)
    {
        if (CHECKS) {
            check(params, 1);
        }
        nglGetBufferParameteriv(target, pname, memAddress(params));
    }

    /**
     * Returns the value of a buffer object parameter.
     *
     * @param target the target buffer object. One of:<br><table><tr><td>{@link #GL_ARRAY_BUFFER ARRAY_BUFFER}</td><td>{@link #GL_ELEMENT_ARRAY_BUFFER ELEMENT_ARRAY_BUFFER}</td><td>{@link GL21#GL_PIXEL_PACK_BUFFER PIXEL_PACK_BUFFER}</td><td>{@link GL21#GL_PIXEL_UNPACK_BUFFER PIXEL_UNPACK_BUFFER}</td></tr><tr><td>{@link GL30#GL_TRANSFORM_FEEDBACK_BUFFER TRANSFORM_FEEDBACK_BUFFER}</td><td>{@link GL31#GL_UNIFORM_BUFFER UNIFORM_BUFFER}</td><td>{@link GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link GL31#GL_COPY_READ_BUFFER COPY_READ_BUFFER}</td></tr><tr><td>{@link GL31#GL_COPY_WRITE_BUFFER COPY_WRITE_BUFFER}</td><td>{@link GL40#GL_DRAW_INDIRECT_BUFFER DRAW_INDIRECT_BUFFER}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td><td>{@link GL43#GL_DISPATCH_INDIRECT_BUFFER DISPATCH_INDIRECT_BUFFER}</td></tr><tr><td>{@link GL43#GL_SHADER_STORAGE_BUFFER SHADER_STORAGE_BUFFER}</td><td>{@link ARBIndirectParameters#GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB}</td></tr></table>
     * @param pname  the symbolic name of a buffer object parameter. One of:<br><table><tr><td>{@link GL15#GL_BUFFER_SIZE BUFFER_SIZE}</td><td>{@link #GL_BUFFER_USAGE BUFFER_USAGE}</td><td>{@link #GL_BUFFER_ACCESS BUFFER_ACCESS}</td><td>{@link #GL_BUFFER_MAPPED BUFFER_MAPPED}</td></tr><tr><td>{@link GL30#GL_BUFFER_ACCESS_FLAGS BUFFER_ACCESS_FLAGS}</td><td>{@link GL30#GL_BUFFER_MAP_LENGTH BUFFER_MAP_LENGTH}</td><td>{@link GL30#GL_BUFFER_MAP_OFFSET BUFFER_MAP_OFFSET}</td><td>{@link GL44#GL_BUFFER_IMMUTABLE_STORAGE BUFFER_IMMUTABLE_STORAGE}</td></tr><tr><td>{@link GL44#GL_BUFFER_STORAGE_FLAGS BUFFER_STORAGE_FLAGS}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetBufferParameter">Reference Page</a>
     */
    @NativeType("void")
    public static int glGetBufferParameteri(@NativeType("GLenum") int target, @NativeType("GLenum") int pname)
    {
        MemoryStack stack = stackGet (); int stackPointer = stack . getPointer ();
        try {
            IntBuffer params = stack . callocInt (1);
            nglGetBufferParameteriv(target, pname, memAddress(params));
            return params.get(0);
        } finally {
            stack.setPointer(stackPointer);
        }
    }

    // --- [ glGetBufferPointerv ] ---

    /** Unsafe version of: {@link #glGetBufferPointerv GetBufferPointerv} */
    public static native void nglGetBufferPointerv(int target, int pname, long params);

    /**
     * Returns the pointer to a mapped buffer object's data store.
     *
     * @param target the target buffer object. One of:<br><table><tr><td>{@link #GL_ARRAY_BUFFER ARRAY_BUFFER}</td><td>{@link #GL_ELEMENT_ARRAY_BUFFER ELEMENT_ARRAY_BUFFER}</td><td>{@link GL21#GL_PIXEL_PACK_BUFFER PIXEL_PACK_BUFFER}</td><td>{@link GL21#GL_PIXEL_UNPACK_BUFFER PIXEL_UNPACK_BUFFER}</td></tr><tr><td>{@link GL30#GL_TRANSFORM_FEEDBACK_BUFFER TRANSFORM_FEEDBACK_BUFFER}</td><td>{@link GL31#GL_UNIFORM_BUFFER UNIFORM_BUFFER}</td><td>{@link GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link GL31#GL_COPY_READ_BUFFER COPY_READ_BUFFER}</td></tr><tr><td>{@link GL31#GL_COPY_WRITE_BUFFER COPY_WRITE_BUFFER}</td><td>{@link GL40#GL_DRAW_INDIRECT_BUFFER DRAW_INDIRECT_BUFFER}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td><td>{@link GL43#GL_DISPATCH_INDIRECT_BUFFER DISPATCH_INDIRECT_BUFFER}</td></tr><tr><td>{@link GL43#GL_SHADER_STORAGE_BUFFER SHADER_STORAGE_BUFFER}</td><td>{@link ARBIndirectParameters#GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB}</td></tr></table>
     * @param pname  the pointer to be returned. Must be:<br><table><tr><td>{@link #GL_BUFFER_MAP_POINTER BUFFER_MAP_POINTER}</td></tr></table>
     * @param params the pointer value specified by {@code pname}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetBufferPointerv">Reference Page</a>
     */
    public static void glGetBufferPointerv(@NativeType("GLenum") int target, @NativeType("GLenum") int pname, @NativeType("void **") PointerBuffer params)
    {
        if (CHECKS) {
            check(params, 1);
        }
        nglGetBufferPointerv(target, pname, memAddress(params));
    }

    /**
     * Returns the pointer to a mapped buffer object's data store.
     *
     * @param target the target buffer object. One of:<br><table><tr><td>{@link #GL_ARRAY_BUFFER ARRAY_BUFFER}</td><td>{@link #GL_ELEMENT_ARRAY_BUFFER ELEMENT_ARRAY_BUFFER}</td><td>{@link GL21#GL_PIXEL_PACK_BUFFER PIXEL_PACK_BUFFER}</td><td>{@link GL21#GL_PIXEL_UNPACK_BUFFER PIXEL_UNPACK_BUFFER}</td></tr><tr><td>{@link GL30#GL_TRANSFORM_FEEDBACK_BUFFER TRANSFORM_FEEDBACK_BUFFER}</td><td>{@link GL31#GL_UNIFORM_BUFFER UNIFORM_BUFFER}</td><td>{@link GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link GL31#GL_COPY_READ_BUFFER COPY_READ_BUFFER}</td></tr><tr><td>{@link GL31#GL_COPY_WRITE_BUFFER COPY_WRITE_BUFFER}</td><td>{@link GL40#GL_DRAW_INDIRECT_BUFFER DRAW_INDIRECT_BUFFER}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td><td>{@link GL43#GL_DISPATCH_INDIRECT_BUFFER DISPATCH_INDIRECT_BUFFER}</td></tr><tr><td>{@link GL43#GL_SHADER_STORAGE_BUFFER SHADER_STORAGE_BUFFER}</td><td>{@link ARBIndirectParameters#GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB}</td></tr></table>
     * @param pname  the pointer to be returned. Must be:<br><table><tr><td>{@link #GL_BUFFER_MAP_POINTER BUFFER_MAP_POINTER}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetBufferPointerv">Reference Page</a>
     */
    @NativeType("void")
    public static long glGetBufferPointer(@NativeType("GLenum") int target, @NativeType("GLenum") int pname)
    {
        MemoryStack stack = stackGet (); int stackPointer = stack . getPointer ();
        try {
            PointerBuffer params = stack . callocPointer (1);
            nglGetBufferPointerv(target, pname, memAddress(params));
            return params.get(0);
        } finally {
            stack.setPointer(stackPointer);
        }
    }

    // --- [ glGenQueries ] ---

    /**
     * Unsafe version of: {@link #glGenQueries GenQueries}
     *
     * @param n the number of query object names to be generated
     */
    public static native void nglGenQueries(int n, long ids);

    /**
     * Generates query object names.
     *
     * @param ids a buffer in which the generated query object names are stored
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenQueries">Reference Page</a>
     */
    public static void glGenQueries(@NativeType("GLuint *") IntBuffer ids)
    {
        nglGenQueries(ids.remaining(), memAddress(ids));
    }

    /**
     * Generates query object names.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenQueries">Reference Page</a>
     */
    @NativeType("void")
    public static int glGenQueries()
    {
        MemoryStack stack = stackGet (); int stackPointer = stack . getPointer ();
        try {
            IntBuffer ids = stack . callocInt (1);
            nglGenQueries(1, memAddress(ids));
            return ids.get(0);
        } finally {
            stack.setPointer(stackPointer);
        }
    }

    // --- [ glDeleteQueries ] ---

    /**
     * Unsafe version of: {@link #glDeleteQueries DeleteQueries}
     *
     * @param n the number of query objects to be deleted
     */
    public static native void nglDeleteQueries(int n, long ids);

    /**
     * Deletes named query objects.
     *
     * @param ids an array of query objects to be deleted
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteQueries">Reference Page</a>
     */
    public static void glDeleteQueries(@NativeType("GLuint const *") IntBuffer ids)
    {
        nglDeleteQueries(ids.remaining(), memAddress(ids));
    }

    /**
     * Deletes named query objects.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteQueries">Reference Page</a>
     */
    public static void glDeleteQueries(@NativeType("GLuint const *") int id)
    {
        MemoryStack stack = stackGet (); int stackPointer = stack . getPointer ();
        try {
            IntBuffer ids = stack . ints (id);
            nglDeleteQueries(1, memAddress(ids));
        } finally {
            stack.setPointer(stackPointer);
        }
    }

    // --- [ glIsQuery ] ---

    /**
     * Determine if a name corresponds to a query object.
     *
     * @param id a value that may be the name of a query object
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glIsQuery">Reference Page</a>
     */
    @NativeType("GLboolean")
    public static native boolean glIsQuery(@NativeType("GLuint") int id);

    // --- [ glBeginQuery ] ---

    /**
     * Creates a query object and makes it active.
     *
     * @param target the target type of query object established. One of:<br><table><tr><td>{@link #GL_SAMPLES_PASSED SAMPLES_PASSED}</td><td>{@link GL30#GL_PRIMITIVES_GENERATED PRIMITIVES_GENERATED}</td><td>{@link GL30#GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN}</td><td>{@link GL33#GL_TIME_ELAPSED TIME_ELAPSED}</td></tr><tr><td>{@link GL33#GL_TIMESTAMP TIMESTAMP}</td><td>{@link GL33#GL_ANY_SAMPLES_PASSED ANY_SAMPLES_PASSED}</td><td>{@link GL43#GL_ANY_SAMPLES_PASSED_CONSERVATIVE ANY_SAMPLES_PASSED_CONSERVATIVE}</td></tr></table>
     * @param id     the name of a query object
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBeginQuery">Reference Page</a>
     */
    public static native void glBeginQuery(@NativeType("GLenum") int target, @NativeType("GLuint") int id);

    // --- [ glEndQuery ] ---

    /**
     * Marks the end of the sequence of commands to be tracked for the active query specified by {@code target}.
     *
     * @param target the query object target. One of:<br><table><tr><td>{@link #GL_SAMPLES_PASSED SAMPLES_PASSED}</td><td>{@link GL30#GL_PRIMITIVES_GENERATED PRIMITIVES_GENERATED}</td><td>{@link GL30#GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN}</td><td>{@link GL33#GL_TIME_ELAPSED TIME_ELAPSED}</td></tr><tr><td>{@link GL33#GL_TIMESTAMP TIMESTAMP}</td><td>{@link GL33#GL_ANY_SAMPLES_PASSED ANY_SAMPLES_PASSED}</td><td>{@link GL43#GL_ANY_SAMPLES_PASSED_CONSERVATIVE ANY_SAMPLES_PASSED_CONSERVATIVE}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glEndQuery">Reference Page</a>
     */
    public static native void glEndQuery(@NativeType("GLenum") int target);

    // --- [ glGetQueryiv ] ---

    /** Unsafe version of: {@link #glGetQueryiv GetQueryiv} */
    public static native void nglGetQueryiv(int target, int pname, long params);

    /**
     * Returns parameters of a query object target.
     *
     * @param target the query object target. One of:<br><table><tr><td>{@link #GL_SAMPLES_PASSED SAMPLES_PASSED}</td><td>{@link GL30#GL_PRIMITIVES_GENERATED PRIMITIVES_GENERATED}</td><td>{@link GL30#GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN}</td><td>{@link GL33#GL_TIME_ELAPSED TIME_ELAPSED}</td></tr><tr><td>{@link GL33#GL_TIMESTAMP TIMESTAMP}</td><td>{@link GL33#GL_ANY_SAMPLES_PASSED ANY_SAMPLES_PASSED}</td><td>{@link GL43#GL_ANY_SAMPLES_PASSED_CONSERVATIVE ANY_SAMPLES_PASSED_CONSERVATIVE}</td></tr></table>
     * @param pname  the symbolic name of a query object target parameter. One of:<br><table><tr><td>{@link #GL_QUERY_COUNTER_BITS QUERY_COUNTER_BITS}</td><td>{@link #GL_CURRENT_QUERY CURRENT_QUERY}</td></tr></table>
     * @param params the requested data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetQuery">Reference Page</a>
     */
    public static void glGetQueryiv(@NativeType("GLenum") int target, @NativeType("GLenum") int pname, @NativeType("GLint *") IntBuffer params)
    {
        if (CHECKS) {
            check(params, 1);
        }
        nglGetQueryiv(target, pname, memAddress(params));
    }

    /**
     * Returns parameters of a query object target.
     *
     * @param target the query object target. One of:<br><table><tr><td>{@link #GL_SAMPLES_PASSED SAMPLES_PASSED}</td><td>{@link GL30#GL_PRIMITIVES_GENERATED PRIMITIVES_GENERATED}</td><td>{@link GL30#GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN}</td><td>{@link GL33#GL_TIME_ELAPSED TIME_ELAPSED}</td></tr><tr><td>{@link GL33#GL_TIMESTAMP TIMESTAMP}</td><td>{@link GL33#GL_ANY_SAMPLES_PASSED ANY_SAMPLES_PASSED}</td><td>{@link GL43#GL_ANY_SAMPLES_PASSED_CONSERVATIVE ANY_SAMPLES_PASSED_CONSERVATIVE}</td></tr></table>
     * @param pname  the symbolic name of a query object target parameter. One of:<br><table><tr><td>{@link #GL_QUERY_COUNTER_BITS QUERY_COUNTER_BITS}</td><td>{@link #GL_CURRENT_QUERY CURRENT_QUERY}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetQuery">Reference Page</a>
     */
    @NativeType("void")
    public static int glGetQueryi(@NativeType("GLenum") int target, @NativeType("GLenum") int pname)
    {
        MemoryStack stack = stackGet (); int stackPointer = stack . getPointer ();
        try {
            IntBuffer params = stack . callocInt (1);
            nglGetQueryiv(target, pname, memAddress(params));
            return params.get(0);
        } finally {
            stack.setPointer(stackPointer);
        }
    }

    // --- [ glGetQueryObjectiv ] ---

    /** Unsafe version of: {@link #glGetQueryObjectiv GetQueryObjectiv} */
    public static native void nglGetQueryObjectiv(int id, int pname, long params);

    /**
     * Returns the integer value of a query object parameter.
     *
     * @param id     the name of a query object
     * @param pname  the symbolic name of a query object parameter. One of:<br><table><tr><td>{@link #GL_QUERY_RESULT QUERY_RESULT}</td><td>{@link #GL_QUERY_RESULT_AVAILABLE QUERY_RESULT_AVAILABLE}</td></tr></table>
     * @param params the requested data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetQueryObject">Reference Page</a>
     */
    public static void glGetQueryObjectiv(@NativeType("GLuint") int id, @NativeType("GLenum") int pname, @NativeType("GLint *") IntBuffer params)
    {
        if (CHECKS) {
            check(params, 1);
        }
        nglGetQueryObjectiv(id, pname, memAddress(params));
    }

    /**
     * Returns the integer value of a query object parameter.
     *
     * @param id    the name of a query object
     * @param pname the symbolic name of a query object parameter. One of:<br><table><tr><td>{@link #GL_QUERY_RESULT QUERY_RESULT}</td><td>{@link #GL_QUERY_RESULT_AVAILABLE QUERY_RESULT_AVAILABLE}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetQueryObject">Reference Page</a>
     */
    @NativeType("void")
    public static int glGetQueryObjecti(@NativeType("GLuint") int id, @NativeType("GLenum") int pname)
    {
        MemoryStack stack = stackGet (); int stackPointer = stack . getPointer ();
        try {
            IntBuffer params = stack . callocInt (1);
            nglGetQueryObjectiv(id, pname, memAddress(params));
            return params.get(0);
        } finally {
            stack.setPointer(stackPointer);
        }
    }

    // --- [ glGetQueryObjectuiv ] ---

    /** Unsafe version of: {@link #glGetQueryObjectuiv GetQueryObjectuiv} */
    public static native void nglGetQueryObjectuiv(int id, int pname, long params);

    /**
     * Unsigned version of {@link #glGetQueryObjectiv GetQueryObjectiv}.
     *
     * @param id     the name of a query object
     * @param pname  the symbolic name of a query object parameter. One of:<br><table><tr><td>{@link #GL_QUERY_RESULT QUERY_RESULT}</td><td>{@link #GL_QUERY_RESULT_AVAILABLE QUERY_RESULT_AVAILABLE}</td></tr></table>
     * @param params the requested data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetQueryObject">Reference Page</a>
     */
    public static void glGetQueryObjectuiv(@NativeType("GLuint") int id, @NativeType("GLenum") int pname, @NativeType("GLuint *") IntBuffer params)
    {
        if (CHECKS) {
            check(params, 1);
        }
        nglGetQueryObjectuiv(id, pname, memAddress(params));
    }

    /**
     * Unsigned version of {@link #glGetQueryObjectiv GetQueryObjectiv}.
     *
     * @param id    the name of a query object
     * @param pname the symbolic name of a query object parameter. One of:<br><table><tr><td>{@link #GL_QUERY_RESULT QUERY_RESULT}</td><td>{@link #GL_QUERY_RESULT_AVAILABLE QUERY_RESULT_AVAILABLE}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetQueryObject">Reference Page</a>
     */
    @NativeType("void")
    public static int glGetQueryObjectui(@NativeType("GLuint") int id, @NativeType("GLenum") int pname)
    {
        MemoryStack stack = stackGet (); int stackPointer = stack . getPointer ();
        try {
            IntBuffer params = stack . callocInt (1);
            nglGetQueryObjectuiv(id, pname, memAddress(params));
            return params.get(0);
        } finally {
            stack.setPointer(stackPointer);
        }
    }

    /**
     * Array version of: {@link #glDeleteBuffers DeleteBuffers}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteBuffers">Reference Page</a>
     */
    public static void glDeleteBuffers(@NativeType("GLuint const *") int[] buffers)
    {
        long __functionAddress = GL . getICD ().glDeleteBuffers;
        if (CHECKS) {
            check(__functionAddress);
        }
        callPV(__functionAddress, buffers.length, buffers);
    }

    /**
     * Array version of: {@link #glGenBuffers GenBuffers}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenBuffers">Reference Page</a>
     */
    public static void glGenBuffers(@NativeType("GLuint *") int[] buffers)
    {
        long __functionAddress = GL . getICD ().glGenBuffers;
        if (CHECKS) {
            check(__functionAddress);
        }
        callPV(__functionAddress, buffers.length, buffers);
    }

    /**
     * Array version of: {@link #glBufferData BufferData}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferData">Reference Page</a>
     */
    public static void glBufferData(@NativeType("GLenum") int target, @NativeType("void const *") short[] data , @NativeType("GLenum") int usage)
    {
        long __functionAddress = GL . getICD ().glBufferData;
        if (CHECKS) {
            check(__functionAddress);
        }
        callPPV(__functionAddress, target, Integer.toUnsignedLong(data.length) < < 1, data, usage);
    }

    /**
     * Array version of: {@link #glBufferData BufferData}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferData">Reference Page</a>
     */
    public static void glBufferData(@NativeType("GLenum") int target, @NativeType("void const *") int[] data , @NativeType("GLenum") int usage)
    {
        long __functionAddress = GL . getICD ().glBufferData;
        if (CHECKS) {
            check(__functionAddress);
        }
        callPPV(__functionAddress, target, Integer.toUnsignedLong(data.length) < < 2, data, usage);
    }

    /**
     * Array version of: {@link #glBufferData BufferData}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferData">Reference Page</a>
     */
    public static void glBufferData(@NativeType("GLenum") int target, @NativeType("void const *") long[] data , @NativeType("GLenum") int usage)
    {
        long __functionAddress = GL . getICD ().glBufferData;
        if (CHECKS) {
            check(__functionAddress);
        }
        callPPV(__functionAddress, target, Integer.toUnsignedLong(data.length) < < 3, data, usage);
    }

    /**
     * Array version of: {@link #glBufferData BufferData}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferData">Reference Page</a>
     */
    public static void glBufferData(@NativeType("GLenum") int target, @NativeType("void const *") float[] data , @NativeType("GLenum") int usage)
    {
        long __functionAddress = GL . getICD ().glBufferData;
        if (CHECKS) {
            check(__functionAddress);
        }
        callPPV(__functionAddress, target, Integer.toUnsignedLong(data.length) < < 2, data, usage);
    }

    /**
     * Array version of: {@link #glBufferData BufferData}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferData">Reference Page</a>
     */
    public static void glBufferData(@NativeType("GLenum") int target, @NativeType("void const *") double[] data , @NativeType("GLenum") int usage)
    {
        long __functionAddress = GL . getICD ().glBufferData;
        if (CHECKS) {
            check(__functionAddress);
        }
        callPPV(__functionAddress, target, Integer.toUnsignedLong(data.length) < < 3, data, usage);
    }

    /**
     * Array version of: {@link #glBufferSubData BufferSubData}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferSubData">Reference Page</a>
     */
    public static void glBufferSubData(@NativeType("GLenum") int target, @NativeType("GLintptr") long offset, @NativeType("void const *") short[] data )
    {
        long __functionAddress = GL . getICD ().glBufferSubData;
        if (CHECKS) {
            check(__functionAddress);
        }
        callPPPV(__functionAddress, target, offset, Integer.toUnsignedLong(data.length) < < 1, data);
    }

    /**
     * Array version of: {@link #glBufferSubData BufferSubData}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferSubData">Reference Page</a>
     */
    public static void glBufferSubData(@NativeType("GLenum") int target, @NativeType("GLintptr") long offset, @NativeType("void const *") int[] data )
    {
        long __functionAddress = GL . getICD ().glBufferSubData;
        if (CHECKS) {
            check(__functionAddress);
        }
        callPPPV(__functionAddress, target, offset, Integer.toUnsignedLong(data.length) < < 2, data);
    }

    /**
     * Array version of: {@link #glBufferSubData BufferSubData}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferSubData">Reference Page</a>
     */
    public static void glBufferSubData(@NativeType("GLenum") int target, @NativeType("GLintptr") long offset, @NativeType("void const *") long[] data )
    {
        long __functionAddress = GL . getICD ().glBufferSubData;
        if (CHECKS) {
            check(__functionAddress);
        }
        callPPPV(__functionAddress, target, offset, Integer.toUnsignedLong(data.length) < < 3, data);
    }

    /**
     * Array version of: {@link #glBufferSubData BufferSubData}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferSubData">Reference Page</a>
     */
    public static void glBufferSubData(@NativeType("GLenum") int target, @NativeType("GLintptr") long offset, @NativeType("void const *") float[] data )
    {
        long __functionAddress = GL . getICD ().glBufferSubData;
        if (CHECKS) {
            check(__functionAddress);
        }
        callPPPV(__functionAddress, target, offset, Integer.toUnsignedLong(data.length) < < 2, data);
    }

    /**
     * Array version of: {@link #glBufferSubData BufferSubData}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferSubData">Reference Page</a>
     */
    public static void glBufferSubData(@NativeType("GLenum") int target, @NativeType("GLintptr") long offset, @NativeType("void const *") double[] data )
    {
        long __functionAddress = GL . getICD ().glBufferSubData;
        if (CHECKS) {
            check(__functionAddress);
        }
        callPPPV(__functionAddress, target, offset, Integer.toUnsignedLong(data.length) < < 3, data);
    }

    /**
     * Array version of: {@link #glGetBufferSubData GetBufferSubData}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetBufferSubData">Reference Page</a>
     */
    public static void glGetBufferSubData(@NativeType("GLenum") int target, @NativeType("GLintptr") long offset, @NativeType("void *") short[] data )
    {
        long __functionAddress = GL . getICD ().glGetBufferSubData;
        if (CHECKS) {
            check(__functionAddress);
        }
        callPPPV(__functionAddress, target, offset, Integer.toUnsignedLong(data.length) < < 1, data);
    }

    /**
     * Array version of: {@link #glGetBufferSubData GetBufferSubData}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetBufferSubData">Reference Page</a>
     */
    public static void glGetBufferSubData(@NativeType("GLenum") int target, @NativeType("GLintptr") long offset, @NativeType("void *") int[] data )
    {
        long __functionAddress = GL . getICD ().glGetBufferSubData;
        if (CHECKS) {
            check(__functionAddress);
        }
        callPPPV(__functionAddress, target, offset, Integer.toUnsignedLong(data.length) < < 2, data);
    }

    /**
     * Array version of: {@link #glGetBufferSubData GetBufferSubData}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetBufferSubData">Reference Page</a>
     */
    public static void glGetBufferSubData(@NativeType("GLenum") int target, @NativeType("GLintptr") long offset, @NativeType("void *") long[] data )
    {
        long __functionAddress = GL . getICD ().glGetBufferSubData;
        if (CHECKS) {
            check(__functionAddress);
        }
        callPPPV(__functionAddress, target, offset, Integer.toUnsignedLong(data.length) < < 3, data);
    }

    /**
     * Array version of: {@link #glGetBufferSubData GetBufferSubData}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetBufferSubData">Reference Page</a>
     */
    public static void glGetBufferSubData(@NativeType("GLenum") int target, @NativeType("GLintptr") long offset, @NativeType("void *") float[] data )
    {
        long __functionAddress = GL . getICD ().glGetBufferSubData;
        if (CHECKS) {
            check(__functionAddress);
        }
        callPPPV(__functionAddress, target, offset, Integer.toUnsignedLong(data.length) < < 2, data);
    }

    /**
     * Array version of: {@link #glGetBufferSubData GetBufferSubData}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetBufferSubData">Reference Page</a>
     */
    public static void glGetBufferSubData(@NativeType("GLenum") int target, @NativeType("GLintptr") long offset, @NativeType("void *") double[] data )
    {
        long __functionAddress = GL . getICD ().glGetBufferSubData;
        if (CHECKS) {
            check(__functionAddress);
        }
        callPPPV(__functionAddress, target, offset, Integer.toUnsignedLong(data.length) < < 3, data);
    }

    /**
     * Array version of: {@link #glGetBufferParameteriv GetBufferParameteriv}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetBufferParameter">Reference Page</a>
     */
    public static void glGetBufferParameteriv(@NativeType("GLenum") int target, @NativeType("GLenum") int pname, @NativeType("GLint *") int[] params)
    {
        long __functionAddress = GL . getICD ().glGetBufferParameteriv;
        if (CHECKS) {
            check(__functionAddress);
            check(params, 1);
        }
        callPV(__functionAddress, target, pname, params);
    }

    /**
     * Array version of: {@link #glGenQueries GenQueries}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenQueries">Reference Page</a>
     */
    public static void glGenQueries(@NativeType("GLuint *") int[] ids)
    {
        long __functionAddress = GL . getICD ().glGenQueries;
        if (CHECKS) {
            check(__functionAddress);
        }
        callPV(__functionAddress, ids.length, ids);
    }

    /**
     * Array version of: {@link #glDeleteQueries DeleteQueries}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteQueries">Reference Page</a>
     */
    public static void glDeleteQueries(@NativeType("GLuint const *") int[] ids)
    {
        long __functionAddress = GL . getICD ().glDeleteQueries;
        if (CHECKS) {
            check(__functionAddress);
        }
        callPV(__functionAddress, ids.length, ids);
    }

    /**
     * Array version of: {@link #glGetQueryiv GetQueryiv}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetQuery">Reference Page</a>
     */
    public static void glGetQueryiv(@NativeType("GLenum") int target, @NativeType("GLenum") int pname, @NativeType("GLint *") int[] params)
    {
        long __functionAddress = GL . getICD ().glGetQueryiv;
        if (CHECKS) {
            check(__functionAddress);
            check(params, 1);
        }
        callPV(__functionAddress, target, pname, params);
    }

    /**
     * Array version of: {@link #glGetQueryObjectiv GetQueryObjectiv}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetQueryObject">Reference Page</a>
     */
    public static void glGetQueryObjectiv(@NativeType("GLuint") int id, @NativeType("GLenum") int pname, @NativeType("GLint *") int[] params)
    {
        long __functionAddress = GL . getICD ().glGetQueryObjectiv;
        if (CHECKS) {
            check(__functionAddress);
            check(params, 1);
        }
        callPV(__functionAddress, id, pname, params);
    }

    /**
     * Array version of: {@link #glGetQueryObjectuiv GetQueryObjectuiv}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetQueryObject">Reference Page</a>
     */
    public static void glGetQueryObjectuiv(@NativeType("GLuint") int id, @NativeType("GLenum") int pname, @NativeType("GLuint *") int[] params)
    {
        long __functionAddress = GL . getICD ().glGetQueryObjectuiv;
        if (CHECKS) {
            check(__functionAddress);
            check(params, 1);
        }
        callPV(__functionAddress, id, pname, params);
    }

}