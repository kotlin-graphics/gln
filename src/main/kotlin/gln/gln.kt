package gln

import glm_.BYTES
import org.lwjgl.system.MemoryUtil
import java.nio.IntBuffer

val buf = MemoryUtil.memAlloc(256)
val buf2 = MemoryUtil.memAlloc(Int.BYTES)
val bufAd = MemoryUtil.memAddress(buf)
val buf2Ad = MemoryUtil.memAddress(buf2)

operator fun IntBuffer.get(e: Enum<*>) = get(e.ordinal)
operator fun IntArray.get(e: Enum<*>) = get(e.ordinal)
operator fun IntArray.set(e: Enum<*>, int: Int) = set(e.ordinal, int)
inline fun <reified T : Enum<T>> intArrayBig() = IntArray(enumValues<T>().size)