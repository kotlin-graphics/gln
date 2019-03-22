package gln

import glm_.bool
import gln.texture.GlTexturesDsl
import kool.IntBuffer
import kool.adr
import kool.rem
import kool.stak
import org.lwjgl.opengl.GL32C
import org.lwjgl.opengl.GL40C
import org.lwjgl.opengl.GL45C
import org.lwjgl.system.MemoryUtil.NULL
import java.nio.ByteBuffer
import java.nio.IntBuffer


inline class GLframebuffer(val i: Int)
inline class GLprogramPipeline(val i: Int)

inline class GlTextures(val names: IntBuffer) {

    inline val rem: Int
        get() = names.rem

    inline val adr: Long
        get() = names.adr

    companion object {
        fun gen(count: Int): GlTextures = gl.genTextures(count)
    }

    fun delete() = gl.deleteTextures(this)

    //    inline operator fun invoke(block: GlTexturesDsl.() -> Unit) {
//        GlTexturesDsl.names = i
//        GlTexturesDsl.block()
//    }
//
    fun create() = GL45C.glCreateBuffers(names)

    inline fun create(block: GlTexturesDsl.() -> Unit) {
        create()
        GlTexturesDsl.names = names
        GlTexturesDsl.block()
    }
}

fun GlTextures(size: Int) = GlTextures(IntBuffer(size))

inline class GlSync(val L: Long) {

    val isValid: Boolean
        get() = GL32C.glIsSync(L)
    val isInvalid: Boolean
        get() = !GL32C.glIsSync(L)

    fun delete() = GL32C.nglDeleteSync(L)

    fun clientWait(flushFirst: Boolean, timeout: NanoSecond): SyncStatus =
            SyncStatus(GL32C.nglClientWaitSync(L, if (flushFirst) GL32C.GL_SYNC_FLUSH_COMMANDS_BIT else 0, timeout.L))

    val isSignaled: Boolean
        get() = stak.intAddress {
            GL32C.nglGetSynciv(L, GL32C.GL_SYNC_STATUS, 1, NULL, it)
        }.bool

    companion object {
        fun new() = GlSync(GL32C.glFenceSync(GL32C.GL_SYNC_GPU_COMMANDS_COMPLETE, 0))
    }
}

inline class NanoSecond(val L: Long)

inline class Subroutine(val p: Pair<Int, UniformLocation>) {
    val index: Int
        get() = p.first
    val uniformLocation: UniformLocation
        get() = p.second
}

typealias UniformLocation = Int
typealias AttribLocation = Int

/** One or more of:
 *  - GL_MAP_READ_BIT
 *  - GL_MAP_WRITE_BIT
 *  - GL_MAP_INVALIDATE_RANGE_BIT
 *  - GL_MAP_INVALIDATE_BUFFER_BIT
 *  - GL_MAP_FLUSH_EXPLICIT_BIT MAP_FLUSH_EXPLICIT_BIT
 *  - GL_MAP_UNSYNCHRONIZED_BIT MAP_UNSYNCHRONIZED_BIT  */
typealias BufferMapFlags = Int

inline class ShaderPrecisionFormat(val p: Pair<IntRange, Int>) {
    val range: IntRange
        get() = p.first
    val precision: Int
        get() = p.second
}

typealias BinaryFormat = Int

class ProgramBinary(val data: ByteBuffer, val format: BinaryFormat)