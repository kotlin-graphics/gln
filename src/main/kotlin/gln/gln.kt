package gln

import org.lwjgl.system.MemoryUtil
import java.nio.IntBuffer

val buf = MemoryUtil.memAlloc(256)
val bufAd = MemoryUtil.memAddress(buf)

operator fun IntBuffer.get(e: Enum<*>) = get(e.ordinal)
operator fun IntArray.get(e: Enum<*>) = get(e.ordinal)
operator fun IntArray.set(e: Enum<*>, int: Int) = set(e.ordinal, int)
inline fun <reified T : Enum<T>> intArrayBig() = IntArray(enumValues<T>().size)