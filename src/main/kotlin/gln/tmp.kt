package gln

import glm_.BYTES
import glm_.bool
import glm_.mat2x2.Mat2
import glm_.mat3x3.Mat3
import glm_.mat4x4.Mat4
import glm_.quat.Quat
import glm_.vec2.Vec2
import glm_.vec2.Vec2d
import glm_.vec2.Vec2i
import glm_.vec2.Vec2ui
import glm_.vec3.Vec3
import glm_.vec3.Vec3d
import glm_.vec3.Vec3i
import glm_.vec3.Vec3ui
import glm_.vec4.*
import gln.identifiers.GlBuffer
import kool.*
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL41
import org.lwjgl.opengl.NVGPUShader5
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil.*
import java.nio.ByteBuffer
import java.nio.FloatBuffer
import java.nio.IntBuffer

fun main() {
    Quat().x
}

inline fun Stack.vec2Address(block: (Ptr) -> Unit): Vec2 = Stack { Vec2.fromPointer(it.nmalloc(4, Vec2.size).also(block)) }

inline fun Stack.vec2Address(vec: Vec2, block: (Ptr) -> Unit) = Stack { block(vec.toBuffer(it).adr) }

inline fun Stack.vec2iAddress(block: (Ptr) -> Unit): Vec2i = Stack { Vec2i.fromPointer(it.nmalloc(4, Vec2i.size).also(block)) }

inline fun Stack.vec2dAddress(block: (Ptr) -> Unit): Vec2d = Stack { Vec2d.fromPointer(it.nmalloc(8, Vec2d.size).also(block)) }

inline fun Stack.vec3Address(block: (Ptr) -> Unit): Vec3 = Stack { Vec3.fromPointer(it.nmalloc(4, Vec3.size).also(block)) }

inline fun Stack.vec3Address(vec: Vec3, block: (Ptr) -> Unit) = Stack { block(vec.toBuffer(it).adr) }

inline fun Stack.vec3iAddress(block: (Ptr) -> Unit): Vec3i = Stack { Vec3i.fromPointer(it.nmalloc(4, Vec3i.size).also(block)) }

inline fun Stack.vec4Address(block: (Ptr) -> Unit): Vec4 = Stack { Vec4.fromPointer(it.nmalloc(4, Vec4.size).also(block)) }

inline fun Stack.vec4Address(vec: Vec4, block: (Ptr) -> Unit) = Stack { block(vec.toBuffer(it).adr) }

inline fun Stack.vec4Buffer(block: (FloatBuffer) -> Unit): Vec4 = Stack { Vec4(it.mallocFloat(Vec4.length).also(block)) }

inline fun Stack.vec4iAddress(block: (Ptr) -> Unit): Vec4i = Stack { Vec4i.fromPointer(it.nmalloc(4, Vec4i.size).also(block)) }

inline fun Stack.vec4uiAddress(block: (Ptr) -> Unit): Vec4ui = Stack { Vec4ui.fromPointer(it.nmalloc(4, Vec4ui.size).also(block)) }

inline fun Stack.vec4boolAddress(block: (Ptr) -> Unit): Vec4bool =
        Stack {
            val adr = it.malloc(4 * Int.BYTES).adr // TODO
            block(adr)
            Vec4bool(memGetInt(adr).bool, memGetInt(adr + Int.BYTES).bool, memGetInt(adr + Int.BYTES * 2).bool, memGetInt(adr + Int.BYTES * 3).bool)
        }

inline fun Stack.vec4iAddress(vec4i: Vec4i, block: (Ptr) -> Unit) = Stack { block(vec4i.toIntBuffer(it).adr) }

inline fun Stack.vec4uiAddress(vec4ui: Vec4ui, block: (Ptr) -> Unit) = Stack { block(vec4ui.toIntBuffer(it).adr) }

inline fun Stack.mat2Address(mat: Mat2, block: (Ptr) -> Unit) = Stack { block(mat.toBuffer(it).adr) }
inline fun Stack.mat3Address(mat: Mat3, block: (Ptr) -> Unit) = Stack { block(mat.toBuffer(it).adr) }
inline fun Stack.mat4Address(mat: Mat4, block: (Ptr) -> Unit) = Stack { block(mat.toBuffer(it).adr) }

inline fun MemoryStack.intAdr(block: (Adr) -> Unit): Int = memGetInt(nmalloc(4, Int.BYTES).also(block))

inline fun MemoryStack.longAddress(block: (Adr) -> Unit): Long = memGetLong(nmalloc(8, Long.BYTES).also(block))

inline fun MemoryStack.intBuffer(block: (IntBuffer) -> Unit): Int = mallocInt(1).also(block)[0]

inline fun MemoryStack.floatAddress(block: (Adr) -> Unit): Float = memGetFloat(nmalloc(4, Float.BYTES).also(block))
inline fun MemoryStack.doubleAddress(block: (Adr) -> Unit): Double = memGetDouble(nmalloc(8, Double.BYTES).also(block))

inline fun MemoryStack.floatBuffer(block: (FloatBuffer) -> Unit): Float = mallocFloat(1).also(block)[0]

inline fun MemoryStack.vec2Address(block: (Ptr) -> Unit): Vec2 = Vec2.fromPointer(nmalloc(4, Vec2.size).also(block))

inline fun MemoryStack.vec2iAddress(block: (Ptr) -> Unit): Vec2i = Vec2i.fromPointer(nmalloc(4, Vec2i.size).also(block))
inline fun MemoryStack.vec2dAddress(block: (Ptr) -> Unit): Vec2d = Vec2d.fromPointer(nmalloc(8, Vec2d.size).also(block))

inline fun MemoryStack.vec3Address(block: (Ptr) -> Unit): Vec3 = Vec3.fromPointer(nmalloc(4, Vec3.size).also(block))

inline fun MemoryStack.vec3iAddress(block: (Ptr) -> Unit): Vec3i = Vec3i.fromPointer(nmalloc(4, Vec3i.size).also(block))
inline fun MemoryStack.vec3dAddress(block: (Ptr) -> Unit): Vec3d = Vec3d.fromPointer(nmalloc(8, Vec3d.size).also(block))

inline fun MemoryStack.vec4Address(block: (Ptr) -> Unit): Vec4 = Vec4.fromPointer(nmalloc(4, Vec4.size).also(block))

inline fun MemoryStack.vec4Address(vec: Vec4, block: (Ptr) -> Unit) = block(vec.toBuffer(this).adr)

inline fun MemoryStack.vec4Buffer(block: (FloatBuffer) -> Unit): Vec4 = Vec4(mallocFloat(Vec4.length).also(block))

inline fun MemoryStack.vec4iAddress(block: (Ptr) -> Unit): Vec4i = Vec4i.fromPointer(nmalloc(4, Vec4i.size).also(block))
inline fun MemoryStack.vec4dAddress(block: (Ptr) -> Unit): Vec4d = Vec4d.fromPointer(nmalloc(8, Vec4d.size).also(block))

inline fun MemoryStack.vec2uiAddress(block: (Ptr) -> Unit): Vec2ui = Vec2ui.fromPointer(nmalloc(4, Vec2ui.size).also(block))
inline fun MemoryStack.vec3uiAddress(block: (Ptr) -> Unit): Vec3ui = Vec3ui.fromPointer(nmalloc(4, Vec3ui.size).also(block))
inline fun MemoryStack.vec4uiAddress(block: (Ptr) -> Unit): Vec4ui = Vec4ui.fromPointer(nmalloc(4, Vec4ui.size).also(block))

inline fun Stack.asciiAddress(givenSize: Int, block: (pLength: Ptr, address: Ptr) -> Unit): String =
        Stack {
            val pLength = it.nmalloc(4, Int.BYTES)
            val pString = it.nmalloc(1, givenSize)
            block(pLength, pString)
            val length = memGetInt(pLength)
            memASCII(memByteBuffer(pString, length), length)
        }

inline fun Stack.utf8Address(givenSize: Int, block: (pLength: Ptr, address: Ptr) -> Unit): String =
        Stack {
            val pLength = it.nmalloc(4, Int.BYTES)
            val pString = it.nmalloc(1, givenSize)
            block(pLength, pString)
            memUTF8(pString, memGetInt(pLength))
        }

inline fun Stack.utf8Address(chars: CharSequence, block: (length: Int, address: Ptr) -> Unit) =
        Stack {
            val length = it.nUTF8(chars, false)
            val pChars = it.pointerAddress
            block(length, pChars)
        }

inline fun <R> Stack.utf8Address(chars: CharSequence, block: (address: Ptr) -> R): R =
        Stack {
            it.nUTF8(chars, true)
            val pChars = it.pointerAddress
            block(pChars)
        }

fun ByteBuffer.rem(type: IndexType): Int = rem shr when (type.i) {
    GL11.GL_BYTE, GL11.GL_UNSIGNED_BYTE -> 1
    GL11.GL_SHORT, GL11.GL_UNSIGNED_SHORT, GL11.GL_2_BYTES, GL30.GL_HALF_FLOAT -> 2
    GL11.GL_3_BYTES -> 3
    GL11.GL_INT, GL11.GL_UNSIGNED_INT, GL11.GL_FLOAT, GL11.GL_4_BYTES, GL41.GL_FIXED -> 4
    GL11.GL_DOUBLE, NVGPUShader5.GL_INT64_NV, NVGPUShader5.GL_UNSIGNED_INT64_NV -> 8
    else -> throw Exception("Unsupported OpenGL type: $type")
}

inline fun MemoryStack.intAdr(int: Int): Adr = nmalloc(4, Int.BYTES).also { memPutInt(it, int) }

operator fun <E : Enum<E>, T> Array<T>.get(index: E): T = get(index.ordinal)
operator fun <E : Enum<E>, T> Array<T>.set(index: E, value: T) = set(index.ordinal, value)