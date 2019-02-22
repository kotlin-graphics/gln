package gln.objects


import glm_.L
import glm_.bool
import gln.*
import kool.*
import org.lwjgl.opengl.*
import org.lwjgl.system.APIUtil
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.system.MemoryUtil.memByteBuffer
import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.IntBuffer

inline class GlBuffer(val i: Int) {

    // --- [ glIsBuffer ] ---

    val isValid: Boolean
        get() = GL20C.glIsBuffer(i)

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

    // glGetBufferPointer

    fun pointer(target: BufferTarget): Ptr = gl21.getBufferPointer(target)

    fun data(target: BufferTarget, size: Int, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, size.L, NULL, usage.i)

    fun data(target: BufferTarget, data: Buffer, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, data.remSize.L, data.adr, usage.i)

    fun subData(target: BufferTarget, offset: Int, data: Buffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remSize.L, data.adr)
    fun subData(target: BufferTarget, data: Buffer) = GL15C.nglBufferSubData(target.i, 0, data.remSize.L, data.adr)

    fun map(target: BufferTarget, access: BufferAccess): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        return if(ptr != NULL) memByteBuffer(ptr, GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)) else null
    }

    infix fun unmap(target: BufferTarget): Boolean = GL15C.glUnmapBuffer(target.i)

    fun mapped(target: BufferTarget, access: BufferAccess, block: (ByteBuffer?) -> Boolean): Boolean? = block(map(target, access)).also { unmap(target) }
}

inline class GlArrayBuffer(val i: Int) {

    val target get() = BufferTarget.ARRAY_BUFFER

    // --- [ glIsBuffer ] ---

    val valid: Boolean
    get() = GL20C.glIsBuffer(i)

    // glGetBufferParameter*

    val access: BufferAccess
    get() = BufferAccess(GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_ACCESS))

    val accessFlag: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL30.GL_BUFFER_ACCESS_FLAGS)

    val immutableStorage: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_IMMUTABLE_STORAGE).bool

    val mapped: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_MAPPED).bool

    val mapLength: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_LENGTH)

    val mapOffset: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_OFFSET)

    val size: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)

    val storageFlags: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_STORAGE_FLAGS)

    val usage: Usage
    get() = Usage(GL15C.glGetBufferParameteri(target.i, GL15.GL_BUFFER_USAGE))

    // glGetBufferPointer

    val pointer: Ptr
    get() = gl21.getBufferPointer(target)


    fun data(size: Int, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, size.L, NULL, usage.i)

    fun data(data: Buffer, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, data.remSize.L, data.adr, usage.i)

    fun subData(offset: Int, data: Buffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remSize.L, data.adr)
    infix fun subData(data: Buffer) = GL15C.nglBufferSubData(target.i, 0, data.remSize.L, data.adr)

    infix fun map(access: BufferAccess): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        return memByteBuffer(ptr, GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)).takeIf { ptr != NULL }
    }

    fun unmap(): Boolean = GL15C.glUnmapBuffer(target.i)

    fun mapped(access: BufferAccess, block: (ByteBuffer?) -> Boolean): Boolean? = block(map(access)).also { unmap() }
}

inline class GlAtomicCounterBuffer(val i: Int) {

    val target get() = BufferTarget.ATOMIC_COUNTER_BUFFER

    // --- [ glIsBuffer ] ---

    val valid: Boolean
    get() = GL20C.glIsBuffer(i)

    // glGetBufferParameter*

    val access: BufferAccess
    get() = BufferAccess(GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_ACCESS))

    val accessFlag: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL30.GL_BUFFER_ACCESS_FLAGS)

    val immutableStorage: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_IMMUTABLE_STORAGE).bool

    val mapped: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_MAPPED).bool

    val mapLength: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_LENGTH)

    val mapOffset: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_OFFSET)

    val size: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)

    val storageFlags: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_STORAGE_FLAGS)

    val usage: Usage
    get() = Usage(GL15C.glGetBufferParameteri(target.i, GL15.GL_BUFFER_USAGE))

    // glGetBufferPointer

    val pointer: Ptr
    get() = gl21.getBufferPointer(target)


    fun data(size: Int, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, size.L, NULL, usage.i)

    fun data(data: Buffer, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, data.remSize.L, data.adr, usage.i)

    fun subData(offset: Int, data: Buffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remSize.L, data.adr)
    infix fun subData(data: Buffer) = GL15C.nglBufferSubData(target.i, 0, data.remSize.L, data.adr)

    infix fun map(access: BufferAccess): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        return memByteBuffer(ptr, GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)).takeIf { ptr != NULL }
    }

    fun unmap(): Boolean = GL15C.glUnmapBuffer(target.i)

    fun mapped(access: BufferAccess, block: (ByteBuffer?) -> Boolean): Boolean? = block(map(access)).also { unmap() }
}

inline class GlCopyReadBuffer(val i: Int) {

    val target get() = BufferTarget.COPY_READ_BUFFER

    // --- [ glIsBuffer ] ---

    val valid: Boolean
    get() = GL20C.glIsBuffer(i)

    // glGetBufferParameter*

    val access: BufferAccess
    get() = BufferAccess(GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_ACCESS))

    val accessFlag: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL30.GL_BUFFER_ACCESS_FLAGS)

    val immutableStorage: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_IMMUTABLE_STORAGE).bool

    val mapped: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_MAPPED).bool

    val mapLength: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_LENGTH)

    val mapOffset: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_OFFSET)

    val size: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)

    val storageFlags: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_STORAGE_FLAGS)

    val usage: Usage
    get() = Usage(GL15C.glGetBufferParameteri(target.i, GL15.GL_BUFFER_USAGE))

    // glGetBufferPointer

    val pointer: Ptr
    get() = gl21.getBufferPointer(target)


    fun data(size: Int, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, size.L, NULL, usage.i)

    fun data(data: Buffer, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, data.remSize.L, data.adr, usage.i)

    fun subData(offset: Int, data: Buffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remSize.L, data.adr)
    infix fun subData(data: Buffer) = GL15C.nglBufferSubData(target.i, 0, data.remSize.L, data.adr)

    infix fun map(access: BufferAccess): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        return memByteBuffer(ptr, GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)).takeIf { ptr != NULL }
    }

    fun unmap(): Boolean = GL15C.glUnmapBuffer(target.i)

    fun mapped(access: BufferAccess, block: (ByteBuffer?) -> Boolean): Boolean? = block(map(access)).also { unmap() }
}

inline class GlCopyWriteBuffer(val i: Int) {

    val target get() = BufferTarget.COPY_WRITE_BUFFER

    // --- [ glIsBuffer ] ---

    val valid: Boolean
    get() = GL20C.glIsBuffer(i)

    // glGetBufferParameter*

    val access: BufferAccess
    get() = BufferAccess(GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_ACCESS))

    val accessFlag: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL30.GL_BUFFER_ACCESS_FLAGS)

    val immutableStorage: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_IMMUTABLE_STORAGE).bool

    val mapped: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_MAPPED).bool

    val mapLength: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_LENGTH)

    val mapOffset: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_OFFSET)

    val size: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)

    val storageFlags: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_STORAGE_FLAGS)

    val usage: Usage
    get() = Usage(GL15C.glGetBufferParameteri(target.i, GL15.GL_BUFFER_USAGE))

    // glGetBufferPointer

    val pointer: Ptr
    get() = gl21.getBufferPointer(target)


    fun data(size: Int, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, size.L, NULL, usage.i)

    fun data(data: Buffer, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, data.remSize.L, data.adr, usage.i)

    fun subData(offset: Int, data: Buffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remSize.L, data.adr)
    infix fun subData(data: Buffer) = GL15C.nglBufferSubData(target.i, 0, data.remSize.L, data.adr)

    infix fun map(access: BufferAccess): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        return memByteBuffer(ptr, GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)).takeIf { ptr != NULL }
    }

    fun unmap(): Boolean = GL15C.glUnmapBuffer(target.i)

    fun mapped(access: BufferAccess, block: (ByteBuffer?) -> Boolean): Boolean? = block(map(access)).also { unmap() }
}

inline class GlDrawIndirectBuffer(val i: Int) {

    val target get() = BufferTarget.DRAW_INDIRECT_BUFFER

    // --- [ glIsBuffer ] ---

    val valid: Boolean
    get() = GL20C.glIsBuffer(i)

    // glGetBufferParameter*

    val access: BufferAccess
    get() = BufferAccess(GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_ACCESS))

    val accessFlag: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL30.GL_BUFFER_ACCESS_FLAGS)

    val immutableStorage: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_IMMUTABLE_STORAGE).bool

    val mapped: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_MAPPED).bool

    val mapLength: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_LENGTH)

    val mapOffset: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_OFFSET)

    val size: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)

    val storageFlags: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_STORAGE_FLAGS)

    val usage: Usage
    get() = Usage(GL15C.glGetBufferParameteri(target.i, GL15.GL_BUFFER_USAGE))

    // glGetBufferPointer

    val pointer: Ptr
    get() = gl21.getBufferPointer(target)


    fun data(size: Int, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, size.L, NULL, usage.i)

    fun data(data: Buffer, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, data.remSize.L, data.adr, usage.i)

    fun subData(offset: Int, data: Buffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remSize.L, data.adr)
    infix fun subData(data: Buffer) = GL15C.nglBufferSubData(target.i, 0, data.remSize.L, data.adr)

    infix fun map(access: BufferAccess): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        return memByteBuffer(ptr, GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)).takeIf { ptr != NULL }
    }

    fun unmap(): Boolean = GL15C.glUnmapBuffer(target.i)

    fun mapped(access: BufferAccess, block: (ByteBuffer?) -> Boolean): Boolean? = block(map(access)).also { unmap() }
}

inline class GlDispatchIndirectBuffer(val i: Int) {

    val target get() = BufferTarget.DISPATCH_INDIRECT_BUFFER

    // --- [ glIsBuffer ] ---

    val valid: Boolean
    get() = GL20C.glIsBuffer(i)

    // glGetBufferParameter*

    val access: BufferAccess
    get() = BufferAccess(GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_ACCESS))

    val accessFlag: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL30.GL_BUFFER_ACCESS_FLAGS)

    val immutableStorage: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_IMMUTABLE_STORAGE).bool

    val mapped: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_MAPPED).bool

    val mapLength: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_LENGTH)

    val mapOffset: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_OFFSET)

    val size: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)

    val storageFlags: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_STORAGE_FLAGS)

    val usage: Usage
    get() = Usage(GL15C.glGetBufferParameteri(target.i, GL15.GL_BUFFER_USAGE))

    // glGetBufferPointer

    val pointer: Ptr
    get() = gl21.getBufferPointer(target)


    fun data(size: Int, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, size.L, NULL, usage.i)

    fun data(data: Buffer, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, data.remSize.L, data.adr, usage.i)

    fun subData(offset: Int, data: Buffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remSize.L, data.adr)
    infix fun subData(data: Buffer) = GL15C.nglBufferSubData(target.i, 0, data.remSize.L, data.adr)

    infix fun map(access: BufferAccess): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        return memByteBuffer(ptr, GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)).takeIf { ptr != NULL }
    }

    fun unmap(): Boolean = GL15C.glUnmapBuffer(target.i)

    fun mapped(access: BufferAccess, block: (ByteBuffer?) -> Boolean): Boolean? = block(map(access)).also { unmap() }
}

inline class GlElementBuffer(val i: Int) {

    val target get() = BufferTarget.ELEMENT_ARRAY_BUFFER

    // --- [ glIsBuffer ] ---

    val valid: Boolean
    get() = GL20C.glIsBuffer(i)

    // glGetBufferParameter*

    val access: BufferAccess
    get() = BufferAccess(GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_ACCESS))

    val accessFlag: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL30.GL_BUFFER_ACCESS_FLAGS)

    val immutableStorage: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_IMMUTABLE_STORAGE).bool

    val mapped: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_MAPPED).bool

    val mapLength: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_LENGTH)

    val mapOffset: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_OFFSET)

    val size: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)

    val storageFlags: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_STORAGE_FLAGS)

    val usage: Usage
    get() = Usage(GL15C.glGetBufferParameteri(target.i, GL15.GL_BUFFER_USAGE))

    // glGetBufferPointer

    val pointer: Ptr
    get() = gl21.getBufferPointer(target)


    fun data(size: Int, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, size.L, NULL, usage.i)

    fun data(data: Buffer, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, data.remSize.L, data.adr, usage.i)

    fun subData(offset: Int, data: Buffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remSize.L, data.adr)
    infix fun subData(data: Buffer) = GL15C.nglBufferSubData(target.i, 0, data.remSize.L, data.adr)

    infix fun map(access: BufferAccess): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        return memByteBuffer(ptr, GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)).takeIf { ptr != NULL }
    }

    fun unmap(): Boolean = GL15C.glUnmapBuffer(target.i)

    fun mapped(access: BufferAccess, block: (ByteBuffer?) -> Boolean): Boolean? = block(map(access)).also { unmap() }
}

inline class GlPixelPackBuffer(val i: Int) {

    val target get() = BufferTarget.PIXEL_PACK_BUFFER

    // --- [ glIsBuffer ] ---

    val valid: Boolean
    get() = GL20C.glIsBuffer(i)

    // glGetBufferParameter*

    val access: BufferAccess
    get() = BufferAccess(GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_ACCESS))

    val accessFlag: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL30.GL_BUFFER_ACCESS_FLAGS)

    val immutableStorage: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_IMMUTABLE_STORAGE).bool

    val mapped: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_MAPPED).bool

    val mapLength: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_LENGTH)

    val mapOffset: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_OFFSET)

    val size: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)

    val storageFlags: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_STORAGE_FLAGS)

    val usage: Usage
    get() = Usage(GL15C.glGetBufferParameteri(target.i, GL15.GL_BUFFER_USAGE))

    // glGetBufferPointer

    val pointer: Ptr
    get() = gl21.getBufferPointer(target)


    fun data(size: Int, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, size.L, NULL, usage.i)

    fun data(data: Buffer, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, data.remSize.L, data.adr, usage.i)

    fun subData(offset: Int, data: Buffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remSize.L, data.adr)
    infix fun subData(data: Buffer) = GL15C.nglBufferSubData(target.i, 0, data.remSize.L, data.adr)

    infix fun map(access: BufferAccess): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        return memByteBuffer(ptr, GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)).takeIf { ptr != NULL }
    }

    fun unmap(): Boolean = GL15C.glUnmapBuffer(target.i)

    fun mapped(access: BufferAccess, block: (ByteBuffer?) -> Boolean): Boolean? = block(map(access)).also { unmap() }
}

inline class GlPixelUnpackBuffer(val i: Int) {

    val target get() = BufferTarget.PIXEL_UNPACK_BUFFER

    // --- [ glIsBuffer ] ---

    val valid: Boolean
    get() = GL20C.glIsBuffer(i)

    // glGetBufferParameter*

    val access: BufferAccess
    get() = BufferAccess(GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_ACCESS))

    val accessFlag: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL30.GL_BUFFER_ACCESS_FLAGS)

    val immutableStorage: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_IMMUTABLE_STORAGE).bool

    val mapped: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_MAPPED).bool

    val mapLength: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_LENGTH)

    val mapOffset: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_OFFSET)

    val size: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)

    val storageFlags: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_STORAGE_FLAGS)

    val usage: Usage
    get() = Usage(GL15C.glGetBufferParameteri(target.i, GL15.GL_BUFFER_USAGE))

    // glGetBufferPointer

    val pointer: Ptr
    get() = gl21.getBufferPointer(target)


    fun data(size: Int, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, size.L, NULL, usage.i)

    fun data(data: Buffer, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, data.remSize.L, data.adr, usage.i)

    fun subData(offset: Int, data: Buffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remSize.L, data.adr)
    infix fun subData(data: Buffer) = GL15C.nglBufferSubData(target.i, 0, data.remSize.L, data.adr)

    infix fun map(access: BufferAccess): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        return memByteBuffer(ptr, GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)).takeIf { ptr != NULL }
    }

    fun unmap(): Boolean = GL15C.glUnmapBuffer(target.i)

    fun mapped(access: BufferAccess, block: (ByteBuffer?) -> Boolean): Boolean? = block(map(access)).also { unmap() }
}

inline class GlQueryBuffer(val i: Int) {

    val target get() = BufferTarget.QUERY_BUFFER

    // --- [ glIsBuffer ] ---

    val valid: Boolean
    get() = GL20C.glIsBuffer(i)

    // glGetBufferParameter*

    val access: BufferAccess
    get() = BufferAccess(GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_ACCESS))

    val accessFlag: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL30.GL_BUFFER_ACCESS_FLAGS)

    val immutableStorage: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_IMMUTABLE_STORAGE).bool

    val mapped: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_MAPPED).bool

    val mapLength: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_LENGTH)

    val mapOffset: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_OFFSET)

    val size: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)

    val storageFlags: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_STORAGE_FLAGS)

    val usage: Usage
    get() = Usage(GL15C.glGetBufferParameteri(target.i, GL15.GL_BUFFER_USAGE))

    // glGetBufferPointer

    val pointer: Ptr
    get() = gl21.getBufferPointer(target)


    fun data(size: Int, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, size.L, NULL, usage.i)

    fun data(data: Buffer, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, data.remSize.L, data.adr, usage.i)

    fun subData(offset: Int, data: Buffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remSize.L, data.adr)
    infix fun subData(data: Buffer) = GL15C.nglBufferSubData(target.i, 0, data.remSize.L, data.adr)

    infix fun map(access: BufferAccess): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        return memByteBuffer(ptr, GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)).takeIf { ptr != NULL }
    }

    fun unmap(): Boolean = GL15C.glUnmapBuffer(target.i)

    fun mapped(access: BufferAccess, block: (ByteBuffer?) -> Boolean): Boolean? = block(map(access)).also { unmap() }
}

inline class GlShaderStorageBuffer(val i: Int) {

    val target get() = BufferTarget.SHADER_STORAGE_BUFFER

    // --- [ glIsBuffer ] ---

    val valid: Boolean
    get() = GL20C.glIsBuffer(i)

    // glGetBufferParameter*

    val access: BufferAccess
    get() = BufferAccess(GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_ACCESS))

    val accessFlag: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL30.GL_BUFFER_ACCESS_FLAGS)

    val immutableStorage: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_IMMUTABLE_STORAGE).bool

    val mapped: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_MAPPED).bool

    val mapLength: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_LENGTH)

    val mapOffset: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_OFFSET)

    val size: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)

    val storageFlags: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_STORAGE_FLAGS)

    val usage: Usage
    get() = Usage(GL15C.glGetBufferParameteri(target.i, GL15.GL_BUFFER_USAGE))

    // glGetBufferPointer

    val pointer: Ptr
    get() = gl21.getBufferPointer(target)


    fun data(size: Int, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, size.L, NULL, usage.i)

    fun data(data: Buffer, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, data.remSize.L, data.adr, usage.i)

    fun subData(offset: Int, data: Buffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remSize.L, data.adr)
    infix fun subData(data: Buffer) = GL15C.nglBufferSubData(target.i, 0, data.remSize.L, data.adr)

    infix fun map(access: BufferAccess): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        return memByteBuffer(ptr, GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)).takeIf { ptr != NULL }
    }

    fun unmap(): Boolean = GL15C.glUnmapBuffer(target.i)

    fun mapped(access: BufferAccess, block: (ByteBuffer?) -> Boolean): Boolean? = block(map(access)).also { unmap() }
}

inline class GlTextureBuffer(val i: Int) {

    val target get() = BufferTarget.TEXTURE_BUFFER

    // --- [ glIsBuffer ] ---

    val valid: Boolean
    get() = GL20C.glIsBuffer(i)

    // glGetBufferParameter*

    val access: BufferAccess
    get() = BufferAccess(GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_ACCESS))

    val accessFlag: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL30.GL_BUFFER_ACCESS_FLAGS)

    val immutableStorage: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_IMMUTABLE_STORAGE).bool

    val mapped: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_MAPPED).bool

    val mapLength: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_LENGTH)

    val mapOffset: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_OFFSET)

    val size: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)

    val storageFlags: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_STORAGE_FLAGS)

    val usage: Usage
    get() = Usage(GL15C.glGetBufferParameteri(target.i, GL15.GL_BUFFER_USAGE))

    // glGetBufferPointer

    val pointer: Ptr
    get() = gl21.getBufferPointer(target)


    fun data(size: Int, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, size.L, NULL, usage.i)

    fun data(data: Buffer, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, data.remSize.L, data.adr, usage.i)

    fun subData(offset: Int, data: Buffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remSize.L, data.adr)
    infix fun subData(data: Buffer) = GL15C.nglBufferSubData(target.i, 0, data.remSize.L, data.adr)

    infix fun map(access: BufferAccess): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        return memByteBuffer(ptr, GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)).takeIf { ptr != NULL }
    }

    fun unmap(): Boolean = GL15C.glUnmapBuffer(target.i)

    fun mapped(access: BufferAccess, block: (ByteBuffer?) -> Boolean): Boolean? = block(map(access)).also { unmap() }
}

inline class GlTransformFeedbackBuffer(val i: Int) {

    val target get() = BufferTarget.TRANSFORM_FEEDBACK_BUFFER

    // --- [ glIsBuffer ] ---

    val valid: Boolean
    get() = GL20C.glIsBuffer(i)

    // glGetBufferParameter*

    val access: BufferAccess
    get() = BufferAccess(GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_ACCESS))

    val accessFlag: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL30.GL_BUFFER_ACCESS_FLAGS)

    val immutableStorage: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_IMMUTABLE_STORAGE).bool

    val mapped: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_MAPPED).bool

    val mapLength: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_LENGTH)

    val mapOffset: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_OFFSET)

    val size: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)

    val storageFlags: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_STORAGE_FLAGS)

    val usage: Usage
    get() = Usage(GL15C.glGetBufferParameteri(target.i, GL15.GL_BUFFER_USAGE))

    // glGetBufferPointer

    val pointer: Ptr
    get() = gl21.getBufferPointer(target)


    fun data(size: Int, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, size.L, NULL, usage.i)

    fun data(data: Buffer, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, data.remSize.L, data.adr, usage.i)

    fun subData(offset: Int, data: Buffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remSize.L, data.adr)
    infix fun subData(data: Buffer) = GL15C.nglBufferSubData(target.i, 0, data.remSize.L, data.adr)

    infix fun map(access: BufferAccess): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        return memByteBuffer(ptr, GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)).takeIf { ptr != NULL }
    }

    fun unmap(): Boolean = GL15C.glUnmapBuffer(target.i)

    fun mapped(access: BufferAccess, block: (ByteBuffer?) -> Boolean): Boolean? = block(map(access)).also { unmap() }
}

inline class GlUniformBuffer(val i: Int) {

    val target get() = BufferTarget.UNIFORM_BUFFER

    // --- [ glIsBuffer ] ---

    val valid: Boolean
    get() = GL20C.glIsBuffer(i)

    // glGetBufferParameter*

    val access: BufferAccess
    get() = BufferAccess(GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_ACCESS))

    val accessFlag: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL30.GL_BUFFER_ACCESS_FLAGS)

    val immutableStorage: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_IMMUTABLE_STORAGE).bool

    val mapped: Boolean
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_MAPPED).bool

    val mapLength: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_LENGTH)

    val mapOffset: Long
    get() = GL32C.glGetBufferParameteri64(target.i, GL30.GL_BUFFER_MAP_OFFSET)

    val size: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)

    val storageFlags: Int
    get() = GL15C.glGetBufferParameteri(target.i, GL44.GL_BUFFER_STORAGE_FLAGS)

    val usage: Usage
    get() = Usage(GL15C.glGetBufferParameteri(target.i, GL15.GL_BUFFER_USAGE))

    // glGetBufferPointer

    val pointer: Ptr
    get() = gl21.getBufferPointer(target)


    fun data(size: Int, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, size.L, NULL, usage.i)

    fun data(data: Buffer, usage: Usage = Usage.STATIC_DRAW) = GL15C.nglBufferData(target.i, data.remSize.L, data.adr, usage.i)

    fun subData(offset: Int, data: Buffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remSize.L, data.adr)
    infix fun subData(data: Buffer) = GL15C.nglBufferSubData(target.i, 0, data.remSize.L, data.adr)

    infix fun map(access: BufferAccess): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        return memByteBuffer(ptr, GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)).takeIf { ptr != NULL }
    }

    fun unmap(): Boolean = GL15C.glUnmapBuffer(target.i)

    fun mapped(access: BufferAccess, block: (ByteBuffer?) -> Boolean): Boolean? = block(map(access)).also { unmap() }
}

inline class GLbuffers(val i: IntBuffer) {

    inline val rem: Int
        get() = i.rem
    inline val adr: Adr
        get() = i.adr

    companion object {
        fun big(count: Int) = GLbuffers(IntBuffer(count))
    }
}