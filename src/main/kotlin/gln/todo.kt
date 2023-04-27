package gln

import glm_.mat4x4.Mat4
import glm_.vec1.Vec1
import glm_.vec3.Vec3
import glm_.vec4.Vec4
import kool.Ptr
import kool.set
import unsigned.Ulong

infix fun Int.at(ptr: Ptr<Byte>): Long {
    ptr.toPtr<Int>()[0] = this
    return ptr.adr.L
}

infix fun Float.at(ptr: Ptr<Byte>): Long {
    ptr.toPtr<Float>()[0] = this
    return ptr.adr.L
}

infix fun Vec1.at(ptr: Ptr<Byte>): Long {
    this to ptr.toPtr<Float>()
    return ptr.adr.L
}

infix fun Vec3.at(ptr: Ptr<Byte>): Long {
    this to ptr.toPtr<Float>()
    return ptr.adr.L
}

infix fun Vec4.at(ptr: Ptr<Byte>): Long {
    this to ptr.toPtr()
    return ptr.adr.L
}

infix fun Mat4.at(ptr: Ptr<Byte>): Long {
    this.to(ptr.toPtr())
    return ptr.adr.L
}

infix operator fun ULong.plus(int: Int): Long = toLong() + int
val ULong.L: Long
    get() = toLong()

inline operator fun Ptr<Int>.minus(offset: Int): Ptr<Int> = plus(offset.toULong())
inline operator fun Ptr<*>.minus(offset: Int): Ptr<*> = plus(offset.toULong())