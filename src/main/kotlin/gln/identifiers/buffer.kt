package gln.identifiers


import glm_.L
import glm_.bool
import gln.*
import gln.buffer.GlBufferDsl
import gln.buffer.GlBuffersDsl
import kool.*
import org.lwjgl.opengl.*
import org.lwjgl.system.MemoryUtil.*
import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.IntBuffer
import java.nio.ShortBuffer

inline class GlBuffer(val name: Int = -1) {

    // --- [ glClearNamedBufferData ] --- TODO format and type

    /**
     * DSA version of {@link GL43C#glClearBufferData ClearBufferData}.
     *
     * @param buffer         the buffer object name
     * @param internalFormat the internal format with which the data will be stored in the buffer object
     * @param format         the format of the data in memory addressed by {@code data}. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td><td>{@link GL11#GL_LUMINANCE LUMINANCE}</td><td>{@link GL11#GL_LUMINANCE_ALPHA LUMINANCE_ALPHA}</td></tr></table>
     * @param type           the type of the data in memory addressed by {@code data}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr><tr><td>{@link GL11#GL_BITMAP BITMAP}</td></tr></table>
     * @param data           the buffer containing the data to be used as the source of the constant fill value. The elements of data are converted by the GL into the format
     *                       specified by internalFormat, and then used to fill the specified range of the destination buffer. If data is {@code NULL}, then it is ignored and the
     *                       sub-range of the buffer is filled with zeros.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClearBufferData">Reference Page</a>
     */
    fun clearBufferData(buffer: GlBuffer, internalFormat: InternalFormat, format: Int, type: Int, data: Buffer? = null) = gl.clearBufferData(this, internalFormat, format, type, data)

    // --- [ glCopyNamedBufferSubData ] --- TODO -> copySubDataTo?

    /**
     * DSA version of {@link GL31C#glCopyBufferSubData CopyBufferSubData}.
     *
     * @param writeBuffer the destination buffer object name
     * @param readOffset  the source buffer object offset, in bytes
     * @param writeOffset the destination buffer object offset, in bytes
     * @param size        the number of bytes to copy
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCopyBufferSubData">Reference Page</a>
     */
    fun copySubData(writeBuffer: GlBuffer, readOffset: Int, writeOffset: Int, size: Int) = gl.copyBufferSubData(this, writeBuffer, readOffset, writeOffset, size)

    // --- [ glClearNamedBufferSubData ] --- TODO format and type

    /**
     * DSA version of {@link GL43C#glClearBufferSubData ClearBufferSubData}.
     *
     * @param internalformat the internal format with which the data will be stored in the buffer object
     * @param offset         the offset, in basic machine units into the buffer object's data store at which to start filling
     * @param size           the size, in basic machine units of the range of the data store to fill
     * @param format         the format of the data in memory addressed by {@code data}. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td><td>{@link GL11#GL_LUMINANCE LUMINANCE}</td><td>{@link GL11#GL_LUMINANCE_ALPHA LUMINANCE_ALPHA}</td></tr></table>
     * @param type           the type of the data in memory addressed by {@code data}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr><tr><td>{@link GL11#GL_BITMAP BITMAP}</td></tr></table>
     * @param data           the buffer containing the data to be used as the source of the constant fill value. The elements of data are converted by the GL into the format
     *                       specified by internalformat, and then used to fill the specified range of the destination buffer. If data is {@code NULL}, then it is ignored and the
     *                       sub-range of the buffer is filled with zeros.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClearBufferSubData">Reference Page</a>
     */
    fun clearSubData(internalformat: Int, offset: Int, size: Int, format: Int, type: Int, data: Buffer? = null) = gl.clearBufferSubData(this, internalformat, offset, size, format, type, data)

    // --- [ glFlushMappedNamedBufferRange ] --- TODO rename to flushMappedRange ?

    /**
     * DSA version of {@link GL30C#glFlushMappedBufferRange FlushMappedBufferRange}.
     *
     * @param buffer the buffer object name
     * @param offset the start of the buffer subrange, in basic machine units
     * @param length the length of the buffer subrange, in basic machine units
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFlushMappedBufferRange">Reference Page</a>
     */
    fun flushMappedBufferRange(buffer: GlBuffer, offset: Int, length: Int) = gl.flushMappedBufferRange(this, offset, length)

    // --- [ glGetNamedBufferParameteriv / glGetNamedBufferParameteri64v ] ---

    /**
     * DSA version of {@link GL15C#glGetBufferParameteriv GetBufferParameteriv}.
     *
     * @param pname  the symbolic name of a buffer object parameter. One of:<br><table><tr><td>{@link GL15#GL_BUFFER_SIZE BUFFER_SIZE}</td><td>{@link GL15#GL_BUFFER_USAGE BUFFER_USAGE}</td><td>{@link GL15#GL_BUFFER_ACCESS BUFFER_ACCESS}</td><td>{@link GL15#GL_BUFFER_MAPPED BUFFER_MAPPED}</td></tr><tr><td>{@link GL30#GL_BUFFER_ACCESS_FLAGS BUFFER_ACCESS_FLAGS}</td><td>{@link GL30#GL_BUFFER_MAP_LENGTH BUFFER_MAP_LENGTH}</td><td>{@link GL30#GL_BUFFER_MAP_OFFSET BUFFER_MAP_OFFSET}</td><td>{@link GL44#GL_BUFFER_IMMUTABLE_STORAGE BUFFER_IMMUTABLE_STORAGE}</td></tr><tr><td>{@link GL44#GL_BUFFER_STORAGE_FLAGS BUFFER_STORAGE_FLAGS}</td></tr></table>
     * @param params the requested parameter
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetBufferParameter">Reference Page</a>
     */
    inline infix fun <reified T> getParameter(param: BufferParameter): T = gl.getBufferParameter(this, param)

    // --- [ glGetNamedBufferPointerv ] --- TODO this could be merged into the above function if Ptr gets converted from typealias to inline class

    /**
     * DSA version of {@link GL15C#glGetBufferPointerv GetBufferPointerv}.
     *
     * @param buffer the buffer object name
     * @param name  the pointer to be returned. Must be:<br><table><tr><td>{@link GL15#GL_BUFFER_MAP_POINTER BUFFER_MAP_POINTER}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetBufferPointerv">Reference Page</a>
     */
    val pointer: Ptr
        get() = gl.getBufferPointer(this)

    // --- [ glGetNamedBufferSubData ] ---

    /**
     * DSA version of {@link GL15C#glGetBufferSubData GetBufferSubData}.
     *
     * @param offset the offset into the buffer object's data store from which data will be returned, measured in bytes
     * @param data   a pointer to the location where buffer object data is returned
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetBufferSubData">Reference Page</a>
     */
    fun getSubData(offset: Int, data: Buffer) = gl.getBufferSubData(this, offset, data)

    // --- [ glIsBuffer ] ---

    val isValid: Boolean
        get() = GL20C.glIsBuffer(name)

    // --- [ glMapNamedBuffer ] ---

    /**
     * DSA version of {@link GL15C#glMapBuffer MapBuffer}.
     *
     * @param access the access policy, indicating whether it will be possible to read from, write to, or both read from and write to the buffer object's mapped data store. One of:<br><table><tr><td>{@link GL15#GL_READ_ONLY READ_ONLY}</td><td>{@link GL15#GL_WRITE_ONLY WRITE_ONLY}</td><td>{@link GL15#GL_READ_WRITE READ_WRITE}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glMapBuffer">Reference Page</a>
     */
    fun map(access: BufferAccess): ByteBuffer? {
        val ptr = GL45C.nglMapNamedBuffer(name, access.i)
        return if (ptr != NULL) memByteBufferSafe(ptr, getParameter(BufferParameter.SIZE)) else null
    }

    inline fun mapped(access: BufferAccess, block: (data: ByteBuffer?) -> Unit) {
        block(map(access))
        unmap()
    }

    // --- [ glMapNamedBufferRange ] ---

    /**
     * DSA version of {@link GL30C#glMapBufferRange MapBufferRange}.
     *
     * @param offset the starting offset within the buffer of the range to be mapped
     * @param length the length of the range to be mapped
     * @param access a combination of access flags indicating the desired access to the range. One or more of:<br><table><tr><td>{@link GL30#GL_MAP_READ_BIT MAP_READ_BIT}</td><td>{@link GL30#GL_MAP_WRITE_BIT MAP_WRITE_BIT}</td><td>{@link GL30#GL_MAP_INVALIDATE_RANGE_BIT MAP_INVALIDATE_RANGE_BIT}</td><td>{@link GL30#GL_MAP_INVALIDATE_BUFFER_BIT MAP_INVALIDATE_BUFFER_BIT}</td></tr><tr><td>{@link GL30#GL_MAP_FLUSH_EXPLICIT_BIT MAP_FLUSH_EXPLICIT_BIT}</td><td>{@link GL30#GL_MAP_UNSYNCHRONIZED_BIT MAP_UNSYNCHRONIZED_BIT}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glMapBufferRange">Reference Page</a>
     */
    fun mapRange(offset: Int, length: Int, access: BufferAccess): ByteBuffer? = gl.mapBufferRange(this, offset, length, access)

    inline fun mappedRange(offset: Int, length: Int, access: BufferAccess, block: (ByteBuffer?) -> Unit) {
        block(mapRange(offset, length, access))
        unmap()
    }

    // --- [ glNamedBufferData ] ---

    /**
     * DSA version of {@link GL15C#glBufferData BufferData}.
     *
     * @param size  the size in bytes of the buffer object's new data store
     * @param usage the expected usage pattern of the data store. One of:<br><table><tr><td>{@link GL15#GL_STREAM_DRAW STREAM_DRAW}</td><td>{@link GL15#GL_STREAM_READ STREAM_READ}</td><td>{@link GL15#GL_STREAM_COPY STREAM_COPY}</td><td>{@link GL15#GL_STATIC_DRAW STATIC_DRAW}</td><td>{@link GL15#GL_STATIC_READ STATIC_READ}</td><td>{@link GL15#GL_STATIC_COPY STATIC_COPY}</td><td>{@link GL15#GL_DYNAMIC_DRAW DYNAMIC_DRAW}</td></tr><tr><td>{@link GL15#GL_DYNAMIC_READ DYNAMIC_READ}</td><td>{@link GL15#GL_DYNAMIC_COPY DYNAMIC_COPY}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferData">Reference Page</a>
     */
    fun bufferData(size: Int, usage: Usage) = gl.bufferData(this, size, usage)

    /**
     * DSA version of {@link GL15C#glBufferData BufferData}.
     *
     * @param data  a pointer to data that will be copied into the data store for initialization, or {@code NULL} if no data is to be copied
     * @param usage the expected usage pattern of the data store. One of:<br><table><tr><td>{@link GL15#GL_STREAM_DRAW STREAM_DRAW}</td><td>{@link GL15#GL_STREAM_READ STREAM_READ}</td><td>{@link GL15#GL_STREAM_COPY STREAM_COPY}</td><td>{@link GL15#GL_STATIC_DRAW STATIC_DRAW}</td><td>{@link GL15#GL_STATIC_READ STATIC_READ}</td><td>{@link GL15#GL_STATIC_COPY STATIC_COPY}</td><td>{@link GL15#GL_DYNAMIC_DRAW DYNAMIC_DRAW}</td></tr><tr><td>{@link GL15#GL_DYNAMIC_READ DYNAMIC_READ}</td><td>{@link GL15#GL_DYNAMIC_COPY DYNAMIC_COPY}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferData">Reference Page</a>
     */
    fun bufferData(data: Buffer, usage: Usage) = gl.bufferData(this, data, usage)

    // --- [ glNamedBufferSubData ] ---

    /**
     * DSA version of {@link GL15C#glBufferSubData BufferSubData}.
     *
     * @param offset the offset into the buffer object's data store where data replacement will begin, measured in bytes
     * @param data   a pointer to the new data that will be copied into the data store
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferSubData">Reference Page</a>
     */
    fun bufferSubData(offset: Int, data: Buffer) = gl.bufferSubData(this, offset, data)

    fun bind(target: BufferTarget) = GL15C.glBindBuffer(target.i, name)
    fun unbind(target: BufferTarget) = GL15C.glBindBuffer(target.i, 0)
    inline fun bind(target: BufferTarget, block: GlBufferDsl.() -> Unit) {
        bind(target)
        GlBufferDsl.target = target
        GlBufferDsl.buffer = this
        GlBufferDsl.block()
    }

    inline fun bound(target: BufferTarget, block: GlBufferDsl.() -> Unit): GlBuffer {
        bind(target, block)
        GL15C.glBindBuffer(target.i, 0)
        return this
    }

    fun storage(data: ByteBuffer, flags: Int = 0) = GL45C.glNamedBufferStorage(name, data, flags)
    fun storage(data: ShortBuffer, flags: Int = 0) = GL45C.glNamedBufferStorage(name, data, flags)

    fun delete() = GL15C.glDeleteBuffers(name)

    // glGetBufferParameter*

    fun getAccess(target: BufferTarget): BufferAccess = BufferAccess(GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_ACCESS))

    fun getAccessFlag(target: BufferTarget): Int = GL15C.glGetBufferParameteri(target.i, GL30.GL_BUFFER_ACCESS_FLAGS)

    fun getImmutableStorage(target: BufferTarget): Boolean = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_IMMUTABLE_STORAGE).bool

    fun isMapped(target: BufferTarget): Boolean = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_MAPPED).bool

    fun getMapLength(target: BufferTarget): Long = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_LENGTH)

    fun getMapOffset(target: BufferTarget): Long = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_OFFSET)

    fun getSize(target: BufferTarget): Int = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)

    fun getStorageFlags(target: BufferTarget): Int = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_STORAGE_FLAGS)

    fun getUsage(target: BufferTarget): Usage = Usage(GL15C.glGetBufferParameteri(target.i, GL15.GL_BUFFER_USAGE))

    fun getPointer(target: BufferTarget): Ptr = stak.pointerAddress { GL15C.nglGetBufferPointerv(target.i, GL15C.GL_BUFFER_MAP_POINTER, it) }


    fun data(target: BufferTarget, size: Int, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, size.L, NULL, usage.i)

    fun data(target: BufferTarget, data: Buffer, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, data.remSize.L, data.adr, usage.i)

    // --- [ glBufferStorage ] ---
    fun storage(target: BufferTarget, data: Buffer, flags: BufferStorageFlags) = gl.bufferStorage(target, data, flags)

    fun subData(target: BufferTarget, offset: Int, data: Buffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remSize.L, data.adr)
    fun subData(target: BufferTarget, data: Buffer) = GL15C.nglBufferSubData(target.i, 0, data.remSize.L, data.adr)

    fun map(target: BufferTarget, access: BufferAccess): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        return if (ptr != NULL) memByteBuffer(ptr, GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)) else null
    }

    infix fun unmap(target: BufferTarget): Boolean = GL15C.glUnmapBuffer(target.i)

    fun mapped(target: BufferTarget, access: BufferAccess, block: (ByteBuffer?) -> Boolean): Boolean? = block(map(target, access)).also { unmap(target) }

    // --- [ glInvalidateBufferSubData ] ---

    /**
     * Invalidates a region of a buffer object's data store.
     *
     * @param offset the offset within the buffer's data store of the start of the range to be invalidated
     * @param length the length of the range within the buffer's data store to be invalidated
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glInvalidateBufferSubData">Reference Page</a>
     */
    fun invalidateSubData(offset: Ptr, length: Ptr) = gl.invalidateBufferSubData(this, offset, length)

    // --- [ glInvalidateBufferData ] ---

    /**
     * Invalidates the content of a buffer object's data store.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glInvalidateBufferData">Reference Page</a>
     */
    fun invalidateData() = gl.invalidateBufferData(this)

    // --- [ glUnmapNamedBuffer ] ---

    /**
     * DSA version of {@link GL15C#glUnmapBuffer UnmapBuffer}.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUnmapBuffer">Reference Page</a>
     */
    fun unmap(): Boolean = gl.unmapBuffer(this)

    companion object {
        // --- [ glCreateBuffers ] ---

        /**
         * Returns {@code n} previously unused buffer names in {@code buffers}, each representing a new buffer object initialized as if it had been bound to an
         * unspecified target.
         *
         * @see <a target="_blank" href="http://docs.gl/gl4/glCreateBuffers">Reference Page</a>
         */
        fun create(): GlBuffer = gl.createBuffers()

        fun gen(): GlBuffer = GlBuffer(GL15C.glGenBuffers())
    }
}

inline class GlBuffers(val names: IntBuffer) {

    inline val rem: Int
        get() = names.rem
    inline val adr: Adr
        get() = names.adr

    // --- [ glBindBuffersBase ] ---

    fun bindBase(target: BufferTarget, first: Int = 0) = gl.bindBuffersBase(target, first, this)

    // --- [ glBindBuffersRange ] ---

    fun bindRange(target: BufferTarget, first: Int, offsets: IntBuffer, sizes: IntBuffer) = gl.bindBuffersRange(target, first, this, offsets, sizes)

    // --- [ glCreateBuffers ] ---

    /**
     * Returns {@code n} previously unused buffer names in {@code buffers}, each representing a new buffer object initialized as if it had been bound to an
     * unspecified target.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateBuffers">Reference Page</a>
     */
    fun create() = gl.createBuffers(this)

    fun gen() = GL15C.glGenBuffers(names)
    inline fun gen(block: GlBuffersDsl.() -> Unit) {
        GL15C.glGenBuffers(names)
        GlBuffersDsl.names = names
        GlBuffersDsl.block()
    }

    inline operator fun invoke(block: GlBuffersDsl.() -> Unit) {
        GlBuffersDsl.names = names
        GlBuffersDsl.block()
    }

    inline fun create(block: GlBuffersDsl.() -> Unit) {
        create()
        GlBuffersDsl.names = names
        GlBuffersDsl.block()
    }

    operator fun get(index: Int): GlBuffer = GlBuffer(names[index])
    operator fun get(enum: Enum<*>): GlBuffer = GlBuffer(names[enum.ordinal])
}

fun GlBuffers(size: Int): GlBuffers = GlBuffers(IntBuffer(size))
inline fun <reified E : Enum<E>> GlBuffers(): GlBuffers = GlBuffers(IntBuffer<E>())