package gln.buffer

import glm_.L
import glm_.bool
import glm_.i
import glm_.mat4x4.Mat4
import glm_.vec3.Vec3
import glm_.vec4.Vec4
import gln.*
import gln.Usage.Companion.STATIC_DRAW
import gln.identifiers.GlBuffer
import gln.identifiers.GlBuffers
import kool.*
import org.lwjgl.opengl.*
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.system.MemoryUtil.memByteBuffer
import java.nio.*


object GlBufferDsl {

    var target: BufferTarget = BufferTarget.ARRAY
    var name = 0

    // --- [ glBufferStorage ] ---

    fun storage(data: Buffer, flags: BufferStorageFlags) = gl.bufferStorage(target, data, flags)

    fun data(data: ShortArray, usage: Usage = STATIC_DRAW) = GL15C.glBufferData(target.i, data, usage.i)
    fun data(data: IntArray, usage: Usage = STATIC_DRAW) = GL15C.glBufferData(target.i, data, usage.i)
    fun data(data: LongArray, usage: Usage = STATIC_DRAW) = GL15C.glBufferData(target.i, data, usage.i)
    fun data(data: FloatArray, usage: Usage = STATIC_DRAW) = GL15C.glBufferData(target.i, data, usage.i)
    fun data(data: DoubleArray, usage: Usage = STATIC_DRAW) = GL15C.glBufferData(target.i, data, usage.i)

    fun data(data: ByteBuffer, usage: Usage = STATIC_DRAW) = GL15C.nglBufferData(target.i, data.remaining().L, data.adr + data.pos, usage.i)
    fun data(data: ShortBuffer, usage: Usage = STATIC_DRAW) = GL15C.nglBufferData(target.i, data.remaining().L * Short.BYTES, data.adr + data.pos * Short.BYTES, usage.i)
    fun data(data: IntBuffer, usage: Usage = STATIC_DRAW) = GL15C.nglBufferData(target.i, data.remaining().L * Int.BYTES, data.adr + data.pos * Int.BYTES, usage.i)
    fun data(data: LongBuffer, usage: Usage = STATIC_DRAW) = GL15C.nglBufferData(target.i, data.remaining().L * Long.BYTES, data.adr + data.pos * Long.BYTES, usage.i)
    fun data(data: FloatBuffer, usage: Usage = STATIC_DRAW) = GL15C.nglBufferData(target.i, data.remaining().L * Float.BYTES, data.adr + data.pos * Float.BYTES, usage.i)
    fun data(data: DoubleBuffer, usage: Usage = STATIC_DRAW) = GL15C.nglBufferData(target.i, data.remaining().L * Double.BYTES, data.adr + data.pos * Double.BYTES, usage.i)

    fun data(size: Int, data: Vec4, usage: Usage = STATIC_DRAW) = GL15C.nglBufferData(target.i, size.L, data at offHeapPtr, usage.i)

    fun subData(offset: Int, data: ByteBuffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remaining().L, data.adr + data.pos)
    fun subData(offset: Int, data: ShortBuffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remaining().L * Short.BYTES, data.adr + data.pos * Short.BYTES)
    fun subData(offset: Int, data: IntBuffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remaining().L * Int.BYTES, data.adr + data.pos * Int.BYTES)
    fun subData(offset: Int, data: LongBuffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remaining().L * Long.BYTES, data.adr + data.pos * Long.BYTES)
    fun subData(offset: Int, data: FloatBuffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remaining().L * Float.BYTES, data.adr + data.pos * Float.BYTES)
    fun subData(offset: Int, data: DoubleBuffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remaining().L * Double.BYTES, data.adr + data.pos * Double.BYTES)

    fun subData(data: ByteBuffer) = GL15C.nglBufferSubData(target.i, 0L, data.remaining().L, data.adr + data.pos)
    fun subData(data: ShortBuffer) = GL15C.nglBufferSubData(target.i, 0L, data.remaining().L * Short.BYTES, data.adr + data.pos * Short.BYTES)
    fun subData(data: IntBuffer) = GL15C.nglBufferSubData(target.i, 0L, data.remaining().L * Int.BYTES, data.adr + data.pos * Int.BYTES)
    fun subData(data: LongBuffer) = GL15C.nglBufferSubData(target.i, 0L, data.remaining().L * Long.BYTES, data.adr + data.pos * Long.BYTES)
    fun subData(data: FloatBuffer) = GL15C.nglBufferSubData(target.i, 0L, data.remaining().L * Float.BYTES, data.adr + data.pos * Float.BYTES)
    fun subData(data: DoubleBuffer) = GL15C.nglBufferSubData(target.i, 0L, data.remaining().L * Double.BYTES, data.adr + data.pos * Double.BYTES)

    fun data(vec: Vec3, usage: Usage = STATIC_DRAW) = GL15C.nglBufferData(target.i, Vec3.size.L, vec at offHeapPtr, usage.i)

    // ----- Mat4 -----
    fun data(mat: Mat4, usage: Usage) = GL15C.nglBufferData(target.i, Mat4.size.L, mat at offHeapPtr, usage.i)

    fun subData(offset: Int, mat: Mat4) = GL15C.nglBufferSubData(target.i, offset.L, Mat4.size.L, mat at offHeapPtr)

    fun subData(mat: Mat4) = subData(0, mat)

    // --- [ glGetBufferParameteriv / glGetBufferParameteri64v ] ---

    val access: BufferAccess
        get() = BufferAccess(GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_ACCESS))

    val accessFlag: MapBufferFlags
        get() = GL15C.glGetBufferParameteri(target.i, GL30C.GL_BUFFER_ACCESS_FLAGS)

    val immutableStorage: Boolean
        get() = GL15C.glGetBufferParameteri(target.i, GL44C.GL_BUFFER_IMMUTABLE_STORAGE).bool

    val mapped: Boolean
        get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_MAPPED).bool

    val mapLength: Int
        get() = GL32C.glGetBufferParameteri64(target.i, GL30C.GL_BUFFER_MAP_LENGTH).i

    val mapOffset: Int
        get() = GL32C.glGetBufferParameteri64(target.i, GL30C.GL_BUFFER_MAP_OFFSET).i

    val size: Int
        get() = GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE)

    val storageFlags: BufferStorageFlags
        get() = GL15C.glGetBufferParameteri(target.i, GL44C.GL_BUFFER_STORAGE_FLAGS)

    val usage: Usage
        get() = Usage(GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_USAGE))

    // --- [ glGetBufferPointerv ] ---

    val pointer: Ptr<Byte>
        get() = GL15C.glGetBufferPointer(target.i, GL15C.GL_BUFFER_MAP_POINTER).toPtr()


    fun bindRange(index: Int, offset: Int, size: Int) = GL30C.glBindBufferRange(target.i, index, name, offset.L, size.L)

    fun bindBase(index: Int) = GL30.glBindBufferBase(target.i, index, name)

    fun mapRange(length: Int, access: Int) = mapRange(0, length, access)
    fun mapRange(offset: Int, length: Int, access: Int) = GL30.glMapBufferRange(target.i, offset.L, length.L, access)

    inline fun mappedRange(size: Int, flags: Int, block: (ByteBuffer?) -> Unit) = mappedRange(0, size, flags, block)
    inline fun mappedRange(offset: Int, size: Int, flags: Int, block: (ByteBuffer?) -> Unit) {
        block(GL30C.glMapBufferRange(target.i, offset.L, size.L, flags))
        GL30C.glUnmapBuffer(target.i)
    }

    fun flushRange(length: Int) = flushRange(0, length)
    fun flushRange(offset: Int, length: Int) = GL30.glFlushMappedBufferRange(target.i, offset.L, length.L)

    // --- [ glBufferData ] ---

    fun data(size: Int, usage: Usage = STATIC_DRAW) = GL15C.nglBufferData(target.i, size.L, NULL, usage.i)

    fun data(data: Buffer, usage: Usage = STATIC_DRAW) = GL15C.nglBufferData(target.i, data.remByte.L, data.adr.L, usage.i)

    // --- [ glBufferSubData ] ---

    fun subData(offset: Int, data: Buffer) = GL15C.nglBufferSubData(target.i, offset.L, data.remByte.L, data.adr.L)

    // --- [ glGetBufferSubData ] ---

    fun getSubData(offset: Int, data: Buffer) = GL15C.nglGetBufferSubData(target.i, offset.L, data.remByte.L, data.adr.L)

    // --- [ glMapBuffer ] ---

    fun map(access: BufferAccess): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        return when (ptr) {
            NULL -> null
            else -> memByteBuffer(ptr, GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE))
        }
    }

    inline fun map(access: BufferAccess, block: (Ptr<Byte>) -> Unit): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        block(ptr.toPtr())
        return when (ptr) {
            NULL -> null
            else -> memByteBuffer(ptr, GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE))
        }
    }

    inline fun mapped(access: BufferAccess, block: (Ptr<Byte>) -> Unit): ByteBuffer? {
        val ptr = GL15C.nglMapBuffer(target.i, access.i)
        block(ptr.toPtr())
        return when (ptr) {
            NULL -> null
            else -> memByteBuffer(ptr, GL15C.glGetBufferParameteri(target.i, GL15C.GL_BUFFER_SIZE))
        }.also { unmap() }
    }

    // --- [ glUnmapBuffer ] ---

    fun unmap(): Boolean = GL15C.glUnmapBuffer(target.i)
}

inline fun glGenBuffers(bufferName: IntBuffer, block: GlBuffersDsl.() -> Unit) {
    GL15C.glGenBuffers(bufferName)
    GlBuffersDsl.names = bufferName
    GlBuffersDsl.block()
}

object GlBuffersDsl {

    lateinit var names: IntBuffer
    //    var target = BufferTarget.ARRAY

    // --- [ glBindBuffersBase ] ---

    fun bindBuffersBase(target: BufferTarget, first: Int = 0) = gl.bindBuffersBase(target, first, GlBuffers(names))

    // --- [ glBindBuffersRange ] ---

    fun bindBuffersRange(target: BufferTarget, first: Int, offsets: IntBuffer, sizes: IntBuffer) = gl.bindBuffersRange(target, first, GlBuffers(names), offsets, sizes)

    infix fun <E : Enum<E>> E.bind(target: BufferTarget) = GL15C.glBindBuffer(target.i, names[ordinal])
    fun unbind(target: BufferTarget) = GL15C.glBindBuffer(target.i, 0)
    inline fun <E : Enum<E>> E.bind(target: BufferTarget, block: GlBufferDsl.() -> Unit) {
        bind(target)
        GlBufferDsl.target = target
        GlBufferDsl.name = names[ordinal]
        GlBufferDsl.block()
    }

    inline fun <E : Enum<E>> E.bound(target: BufferTarget, block: GlBufferDsl.() -> Unit) {
        bind(target, block)
        GL15C.glBindBuffer(target.i, 0)
    }

    fun <E : Enum<E>> E.bindBase(target: BufferTarget, index: Int) = GL30C.glBindBufferBase(target.i, index, names[ordinal])

    fun <E : Enum<E>> E.storage(size: Int, flags: Int = 0) = GL45C.glNamedBufferStorage(names[ordinal], size.L, flags)

    fun <E : Enum<E>> E.storage(data: ByteBuffer, flags: Int = 0) = GL45C.glNamedBufferStorage(names[ordinal], data, flags)
    fun <E : Enum<E>> E.storage(data: ShortBuffer, flags: Int = 0) = GL45C.glNamedBufferStorage(names[ordinal], data, flags)
    fun <E : Enum<E>> E.storage(data: FloatBuffer, flags: Int = 0) = GL45C.glNamedBufferStorage(names[ordinal], data, flags)

    fun <E : Enum<E>> E.mapRange(offset: Int, size: Int, flags: Int = 0) = GL45C.glMapNamedBufferRange(names[ordinal], offset.L, size.L, flags)
    fun <E : Enum<E>> E.mapRange(size: Int, flags: Int = 0) = GL45C.glMapNamedBufferRange(names[ordinal], 0L, size.L, flags)

    infix fun Int.bind(target: BufferTarget) = GL15C.glBindBuffer(target.i, names[this])
    inline fun Int.bind(target: BufferTarget, block: GlBufferDsl.() -> Unit) {
        val name = names[this]
        GL15C.glBindBuffer(target.i, name)
        GlBufferDsl.target = target
        GlBufferDsl.name = name
        GlBufferDsl.block()
    }

    operator fun <E : Enum<E>> E.invoke() = GlBuffer(names[ordinal])
}