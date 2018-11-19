package gln.`object`


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

@Deprecated("This class was renamed to GlBuffer", ReplaceWith("GlBuffer", "gln.`object`"))
typealias GLbuffer = GlBuffer // TODO remove

object GlBuffer {

    var i = 0
    var target = BufferTarget(GL15.GL_ARRAY_BUFFER)


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
        get() = gl15.getBufferPointer(target)


    fun data(size: Int, usage: Usage) = GL15C.nglBufferData(target.i, size.L, NULL, usage.i)

    fun data(data: Buffer, usage: Usage) = GL15C.nglBufferData(target.i, data.remSize.L, data.adr, usage.i)

    fun subData(offset: Int, data: Buffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remSize.L, data.adr)
    infix fun subData(data: Buffer) = GL15C.nglBufferSubData(target.i, 0, data.remSize.L, data.adr)

    infix fun map(access: BufferAccess): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        return memByteBuffer(ptr, GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)).takeIf { ptr != NULL }
    }

    fun map(access: BufferAccess, oldBuffer: ByteBuffer?): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        val length = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)
        return APIUtil.apiGetMappedBuffer(oldBuffer, ptr, length)
    }

    fun map(access: BufferAccess, length: Int, oldBuffer: ByteBuffer?): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        return APIUtil.apiGetMappedBuffer(oldBuffer, ptr, length)
    }

    fun unmap(): Boolean = GL15C.glUnmapBuffer(target.i)

    fun mapped(access: BufferAccess, block: (ByteBuffer?) -> Boolean): Boolean? = block(map(access)).also { unmap() }
    fun mapped(access: BufferAccess, oldBuffer: ByteBuffer?, block: (ByteBuffer?) -> Boolean): Boolean? = block(map(access, oldBuffer)).also { unmap() }
    fun mapped(access: BufferAccess, length: Int, oldBuffer: ByteBuffer?, block: (ByteBuffer?) -> Boolean): Boolean? = block(map(access, length, oldBuffer)).also { unmap() }
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