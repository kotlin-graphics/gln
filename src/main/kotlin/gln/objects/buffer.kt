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

inline class GLbuffers(val i: IntBuffer) {

    inline val rem: Int
        get() = i.rem
    inline val adr: Adr
        get() = i.adr

    companion object {
        fun big(count: Int) = GLbuffers(IntBuffer(count))
    }
}