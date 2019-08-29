package gln.identifiers


import glm_.L
import glm_.bool
import glm_.i
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

    // --- [ glIsBuffer ] ---

    val isValid: Boolean
        get() = GL15C.glIsBuffer(name)

    val isInvalid: Boolean
        get() = !GL15C.glIsBuffer(name)

    // --- [ glClearNamedBufferData ] --- TODO format and type

    fun clearData(buffer: GlBuffer, internalFormat: InternalFormat, format: Int, type: Int, data: Buffer? = null) = gl.clearBufferData(this, internalFormat, format, type, data)

    // --- [ glCopyNamedBufferSubData ] --- TODO -> copySubDataTo?

    fun copySubData(writeBuffer: GlBuffer, readOffset: Int, writeOffset: Int, size: Int) = gl.copyBufferSubData(this, writeBuffer, readOffset, writeOffset, size)

    // --- [ glClearNamedBufferSubData ] --- TODO format and type

    fun clearSubData(internalformat: Int, offset: Int, size: Int, format: Int, type: Int, data: Buffer? = null) = gl.clearBufferSubData(this, internalformat, offset, size, format, type, data)

    // --- [ glFlushMappedNamedBufferRange ] --- TODO rename to flushMappedRange ?

    fun flushMappedBufferRange(buffer: GlBuffer, offset: Int, length: Int) = gl.flushMappedBufferRange(this, offset, length)

    // --- [ glGetNamedBufferSubData ] ---

    fun getSubData(offset: Int, data: Buffer) = gl.getBufferSubData(this, offset, data)

    // --- [ glMapNamedBuffer ] ---

    fun map(access: BufferAccess): ByteBuffer? {
        val ptr = GL45C.nglMapNamedBuffer(name, access.i)
        return if (ptr != NULL) memByteBufferSafe(ptr, getParameter(BufferParameter.SIZE)) else null
    }

    inline fun mapped(access: BufferAccess, block: (data: ByteBuffer?) -> Unit) {
        block(map(access))
        unmap()
    }

    // --- [ glMapNamedBufferRange ] ---

    fun mapRange(offset: Int, length: Int, access: BufferAccess): ByteBuffer? = gl.mapBufferRange(this, offset, length, access)

    inline fun mappedRange(offset: Int, length: Int, access: BufferAccess, block: (ByteBuffer?) -> Unit) {
        block(mapRange(offset, length, access))
        unmap()
    }

    // --- [ glNamedBufferData ] ---

    fun data(size: Int, usage: Usage = Usage.STATIC_DRAW) = gl.bufferData(this, size, usage)

    fun data(data: Buffer, usage: Usage = Usage.STATIC_DRAW) = gl.bufferData(this, data, usage)

    // --- [ glNamedBufferSubData ] ---

    fun subData(offset: Int, data: Buffer) = gl.bufferSubData(this, offset, data)

    // --- [ glBindBuffer ] ---

    infix fun bind(target: BufferTarget) = GL15C.glBindBuffer(target.i, name)
    infix fun unbind(target: BufferTarget) = GL15C.glBindBuffer(target.i, 0)

    inline fun <R> bind(target: BufferTarget, block: GlBufferDsl.() -> R): R {
        bind(target)
        GlBufferDsl.target = target
        GlBufferDsl.name = name
        return GlBufferDsl.block()
    }

    inline fun <R> bound(target: BufferTarget, block: GlBufferDsl.() -> R): R =
            bind(target, block).also { GL15C.glBindBuffer(target.i, 0) }

    // --- [ glDeleteBuffers ] ---

    fun delete() = GL15C.glDeleteBuffers(name)


    fun storage(data: ByteBuffer, flags: Int = 0) = GL45C.glNamedBufferStorage(name, data, flags)
    fun storage(data: ShortBuffer, flags: Int = 0) = GL45C.glNamedBufferStorage(name, data, flags)


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

    fun invalidateSubData(offset: Ptr, length: Ptr) = gl.invalidateBufferSubData(this, offset, length)

    // --- [ glInvalidateBufferData ] ---

    fun invalidateData() = gl.invalidateBufferData(this)

    // --- [ glUnmapNamedBuffer ] ---

    fun unmap(): Boolean = gl.unmapBuffer(this)

    inline operator fun invoke(block: GlBufferDsl.() -> Unit) {
        GlBufferDsl.name = name
        GlBufferDsl.block()
    }

    // --- [ glGetNamedBufferParameteriv / glGetNamedBufferParameteri64v ] ---

    inline infix fun <reified T> getParameter(param: BufferParameter): T = gl.getBufferParameter(this, param)

    val access: BufferAccess
        get() = BufferAccess(GL45C.glGetNamedBufferParameteri(name, GL15C.GL_BUFFER_ACCESS))

    val accessFlag: MapBufferFlags
        get() = GL45C.glGetNamedBufferParameteri(name, GL30C.GL_BUFFER_ACCESS_FLAGS)

    val immutableStorage: Boolean
        get() = GL45C.glGetNamedBufferParameteri(name, GL44C.GL_BUFFER_IMMUTABLE_STORAGE).bool

    val mapped: Boolean
        get() = GL45C.glGetNamedBufferParameteri(name, GL15C.GL_BUFFER_MAPPED).bool

    val mapLength: Int
        get() = GL32C.glGetBufferParameteri64(name, GL30C.GL_BUFFER_MAP_LENGTH).i

    val mapOffset: Int
        get() = GL32C.glGetBufferParameteri64(name, GL30C.GL_BUFFER_MAP_OFFSET).i

    val size: Int
        get() = GL45C.glGetNamedBufferParameteri(name, GL15C.GL_BUFFER_SIZE)

    val storageFlags: BufferStorageFlags
        get() = GL45C.glGetNamedBufferParameteri(name, GL44C.GL_BUFFER_STORAGE_FLAGS)

    val usage: Usage
        get() = Usage(GL45C.glGetNamedBufferParameteri(name, GL15C.GL_BUFFER_USAGE))

    // --- [ glGetNamedBufferPointerv ] ---

    val pointer: Ptr
        get() = GL15C.glGetBufferPointer(name, GL15C.GL_BUFFER_MAP_POINTER)


    companion object {
        // --- [ glCreateBuffers ] ---

        fun create(): GlBuffer = gl.createBuffers()

        // --- [ glGenBuffers ] ---

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

    fun create() = gl.createBuffers(this)

    // --- [ glGenBuffers ] ---

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

    // --- [ glDeleteBuffers ] ---

    fun delete() = GL15C.nglDeleteBuffers(names.rem, names.adr)

    operator fun get(index: Int): GlBuffer = GlBuffer(names[index])
    operator fun set(index: Int, buffer: GlBuffer) {
        names.put(index, buffer.name)
    }

    operator fun get(enum: Enum<*>): GlBuffer = GlBuffer(names[enum.ordinal])
    operator fun set(enum: Enum<*>, buffer: GlBuffer) {
        names.put(enum.ordinal, buffer.name)
    }
}

fun GlBuffers(size: Int): GlBuffers = GlBuffers(IntBuffer(size))
inline fun <reified E : Enum<E>> GlBuffers(): GlBuffers = GlBuffers(IntBuffer<E>())