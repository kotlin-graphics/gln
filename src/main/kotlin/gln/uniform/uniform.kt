package gln.uniform


import glm_.f
import glm_.i
import glm_.mat2x2.Mat2
import glm_.mat2x3.Mat2x3
import glm_.mat2x4.Mat2x4
import glm_.mat3x2.Mat3x2
import glm_.mat3x3.Mat3
import glm_.mat3x4.Mat3x4
import glm_.mat4x2.Mat4x2
import glm_.mat4x3.Mat4x3
import glm_.mat4x4.Mat4
import glm_.vec1.Vec1
import glm_.vec1.Vec1bool
import glm_.vec1.Vec1i
import glm_.vec1.Vec1t
import glm_.vec2.Vec2
import glm_.vec2.Vec2bool
import glm_.vec2.Vec2i
import glm_.vec2.Vec2t
import glm_.vec3.Vec3
import glm_.vec3.Vec3bool
import glm_.vec3.Vec3i
import glm_.vec3.Vec3t
import glm_.vec4.Vec4
import glm_.vec4.Vec4bool
import glm_.vec4.Vec4i
import glm_.vec4.Vec4t
import gln.buf
import gln.bufAd
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL40
import unsigned.Uint
import java.awt.Color


// ----------------------------------------- vec -----------------------------------------------------------------------
// inferred length and type

fun glUniform(location: Int, float: Float) = GL20.glUniform1f(location, float)
fun glUniform(location: Int, double: Double) = GL40.glUniform1d(location, double)
fun glUniform(location: Int, int: Int) = GL20.glUniform1i(location, int)
fun glUniform(location: Int, uint: Uint) = GL30.glUniform1ui(location, uint.v)
fun glUniform(location: Int, boolean: Boolean) = GL20.glUniform1i(location, boolean.i)

fun glUniform(location: Int, vec1: Vec1) = GL20.glUniform1f(location, vec1.x)
//fun glUniform(location: Int, vec1d: Vec1d) = GL40.glUniform1d(location, vec1d.x) TODO
fun glUniform(location: Int, vec1i: Vec1i) = GL20.glUniform1i(location, vec1i.x)

fun glUniform(location: Int, vec1bool: Vec1bool) = GL20.glUniform1i(location, vec1bool.x.i)

fun glUniform(location: Int, x: Float, y: Float) = GL20.glUniform2f(location, x, y)
fun glUniform(location: Int, x: Double, y: Double) = GL40.glUniform2d(location, x, y)
fun glUniform(location: Int, vec2: Vec2) = GL20.glUniform2f(location, vec2.x, vec2.y)
fun glUniform(location: Int, x: Int, y: Int) = GL20.glUniform2i(location, x, y)
fun glUniform(location: Int, vec2i: Vec2i) = GL20.glUniform2i(location, vec2i.x, vec2i.y)
fun glUniform(location: Int, x: Boolean, y: Boolean) = GL20.glUniform2i(location, x.i, y.i)
fun glUniform(location: Int, vec2bool: Vec2bool) = GL20.glUniform2i(location, vec2bool.x.i, vec2bool.y.i)

fun glUniform(location: Int, x: Float, y: Float, z: Float) = GL20.glUniform3f(location, x, y, z)
fun glUniform(location: Int, vec3: Vec3) = GL20.glUniform3f(location, vec3.x, vec3.y, vec3.z)
fun glUniform(location: Int, x: Int, y: Int, z: Int) = GL20.glUniform3i(location, x, y, z)
fun glUniform(location: Int, vec3i: Vec3i) = GL20.glUniform3i(location, vec3i.x, vec3i.y, vec3i.z)
fun glUniform(location: Int, x: Boolean, y: Boolean, z: Boolean) = GL20.glUniform3i(location, x.i, y.i, z.i)
fun glUniform(location: Int, vec3bool: Vec3bool) = GL20.glUniform3i(location, vec3bool.x.i, vec3bool.y.i, vec3bool.z.i)

fun glUniform(location: Int, x: Float, y: Float, z: Float, w: Float) = GL20.glUniform4f(location, x, y, z, w)
fun glUniform(location: Int, vec4: Vec4) = GL20.glUniform4f(location, vec4.x, vec4.y, vec4.z, vec4.w)
fun glUniform(location: Int, x: Int, y: Int, z: Int, w: Int) = GL20.glUniform4i(location, x, y, z, w)
fun glUniform(location: Int, vec4i: Vec4i) = GL20.glUniform4i(location, vec4i.x, vec4i.y, vec4i.z, vec4i.w)
fun glUniform(location: Int, x: Boolean, y: Boolean, z: Boolean, w: Boolean) = GL20.glUniform4i(location, x.i, y.i, z.i, w.i)
fun glUniform(location: Int, vec4bool: Vec4bool) = GL20.glUniform4i(location, vec4bool.x.i, vec4bool.y.i, vec4bool.z.i, vec4bool.w.i)


// inferred type float

fun glUniform1(location: Int, x: Float) = GL20.glUniform1f(location, x)
fun glUniform1(location: Int, v: Vec2) = GL20.glUniform1f(location, v.x)
fun glUniform1(location: Int, v: Vec3) = GL20.glUniform1f(location, v.x)
fun glUniform1(location: Int, v: Vec4) = GL20.glUniform1f(location, v.x)

fun glUniform2(location: Int, x: Float) = GL20.glUniform2f(location, x, x)
fun glUniform2(location: Int, v: Vec2) = GL20.glUniform2f(location, v.x, v.y)
fun glUniform2(location: Int, v: Vec3) = GL20.glUniform2f(location, v.x, v.y)
fun glUniform2(location: Int, v: Vec4) = GL20.glUniform2f(location, v.x, v.y)

fun glUniform3(location: Int, x: Float) = GL20.glUniform3f(location, x, x, x)
fun glUniform3(location: Int, v: Vec2) = GL20.glUniform3f(location, v.x, v.y, 0f)
fun glUniform3(location: Int, v: Vec3) = GL20.glUniform3f(location, v.x, v.y, v.z)
fun glUniform3(location: Int, v: Vec4) = GL20.glUniform3f(location, v.x, v.y, v.z)

fun glUniform4(location: Int, x: Float) = GL20.glUniform4f(location, x, x, x, x)
fun glUniform4(location: Int, v: Vec2) = GL20.glUniform4f(location, v.x, v.y, 0f, 1f)
fun glUniform4(location: Int, v: Vec3) = GL20.glUniform4f(location, v.x, v.y, v.z, 1f)
fun glUniform4(location: Int, v: Vec4) = GL20.glUniform4f(location, v.x, v.y, v.z, v.w)


// inferred type int

fun glUniform1(location: Int, x: Int) = GL20.glUniform1i(location, x)
fun glUniform1(location: Int, v: Vec2i) = GL20.glUniform1i(location, v.x)
fun glUniform1(location: Int, v: Vec3i) = GL20.glUniform1i(location, v.x)
fun glUniform1(location: Int, v: Vec4i) = GL20.glUniform1i(location, v.x)

fun glUniform2(location: Int, x: Int) = GL20.glUniform2i(location, x, x)
fun glUniform2(location: Int, v: Vec2i) = GL20.glUniform2i(location, v.x, v.y)
fun glUniform2(location: Int, v: Vec3i) = GL20.glUniform2i(location, v.x, v.y)
fun glUniform2(location: Int, v: Vec4i) = GL20.glUniform2i(location, v.x, v.y)

fun glUniform3(location: Int, x: Int) = GL20.glUniform3i(location, x, x, x)
fun glUniform3(location: Int, v: Vec2i) = GL20.glUniform3i(location, v.x, v.y, 0)
fun glUniform3(location: Int, v: Vec3i) = GL20.glUniform3i(location, v.x, v.y, v.z)
fun glUniform3(location: Int, v: Vec4i) = GL20.glUniform3i(location, v.x, v.y, v.z)

fun glUniform4(location: Int, x: Int) = GL20.glUniform4i(location, x, x, x, x)
fun glUniform4(location: Int, v: Vec2i) = GL20.glUniform4i(location, v.x, v.y, 0, 1)
fun glUniform4(location: Int, v: Vec3i) = GL20.glUniform4i(location, v.x, v.y, v.z, 1)
fun glUniform4(location: Int, v: Vec4i) = GL20.glUniform4i(location, v.x, v.y, v.z, v.w)


// conversions, 1f

fun glUniform1f(location: Int) = GL20.glUniform1f(location, 0f)

fun glUniform1f(location: Int, x: Number) = GL20.glUniform1f(location, x.f)
fun glUniform1f(location: Int, vec1: Vec1t<*>) = GL20.glUniform1f(location, vec1.x.f)
fun glUniform1f(location: Int, vec2: Vec2t<*>) = GL20.glUniform1f(location, vec2.x.f)
fun glUniform1f(location: Int, vec3: Vec3t<*>) = GL20.glUniform1f(location, vec3.x.f)
fun glUniform1f(location: Int, vec4: Vec4t<*>) = GL20.glUniform1f(location, vec4.x.f)

fun glUniform1f(location: Int, vec1: Vec1) = GL20.glUniform1f(location, vec1.x)
fun glUniform1f(location: Int, vec2: Vec2) = GL20.glUniform1f(location, vec2.x)
fun glUniform1f(location: Int, vec3: Vec3) = GL20.glUniform1f(location, vec3.x)
fun glUniform1f(location: Int, vec4: Vec4) = GL20.glUniform1f(location, vec4.x)

fun glUniform1f(location: Int, x: Boolean) = GL20.glUniform1f(location, x.f)
fun glUniform1f(location: Int, vec1: Vec1bool) = GL20.glUniform1f(location, vec1.x.f)
fun glUniform1f(location: Int, vec2: Vec2bool) = GL20.glUniform1f(location, vec2.x.f)
fun glUniform1f(location: Int, vec3: Vec3bool) = GL20.glUniform1f(location, vec3.x.f)
fun glUniform1f(location: Int, vec4: Vec4bool) = GL20.glUniform1f(location, vec4.x.f)


// conversions, 1i

fun glUniform1i(location: Int) = GL20.glUniform1i(location, 0)

fun glUniform1i(location: Int, x: Number) = GL20.glUniform1i(location, x.i)
fun glUniform1i(location: Int, vec1: Vec1t<*>) = GL20.glUniform1i(location, vec1.x.i)
fun glUniform1i(location: Int, vec2: Vec2t<*>) = GL20.glUniform1i(location, vec2.x.i)
fun glUniform1i(location: Int, vec3: Vec3t<*>) = GL20.glUniform1i(location, vec3.x.i)
fun glUniform1i(location: Int, vec4: Vec4t<*>) = GL20.glUniform1i(location, vec4.x.i)

fun glUniform1i(location: Int, vec1: Vec1i) = GL20.glUniform1i(location, vec1.x)
fun glUniform1i(location: Int, vec2: Vec2i) = GL20.glUniform1i(location, vec2.x)
fun glUniform1i(location: Int, vec3: Vec3i) = GL20.glUniform1i(location, vec3.x)
fun glUniform1i(location: Int, vec4: Vec4i) = GL20.glUniform1i(location, vec4.x)

fun glUniform1i(location: Int, x: Boolean) = GL20.glUniform1i(location, x.i)
fun glUniform1i(location: Int, vec1: Vec1bool) = GL20.glUniform1i(location, vec1.x.i)
fun glUniform1i(location: Int, vec2: Vec2bool) = GL20.glUniform1i(location, vec2.x.i)
fun glUniform1i(location: Int, vec3: Vec3bool) = GL20.glUniform1i(location, vec3.x.i)
fun glUniform1i(location: Int, vec4: Vec4bool) = GL20.glUniform1i(location, vec4.x.i)

// conversions, 2f

fun glUniform2f(location: Int) = GL20.glUniform2f(location, 0f, 0f)

fun glUniform2f(location: Int, x: Number) = GL20.glUniform2f(location, x.f, x.f)
fun glUniform2f(location: Int, x: Number, y: Number) = GL20.glUniform2f(location, x.f, y.f)
fun glUniform2f(location: Int, vec1: Vec1t<*>) = GL20.glUniform2f(location, vec1.x.f, 0f)
fun glUniform2f(location: Int, vec1: Vec1t<*>, y: Number) = GL20.glUniform2f(location, vec1.x.f, y.f)
fun glUniform2f(location: Int, vec2: Vec2t<*>) = GL20.glUniform2f(location, vec2.x.f, vec2.y.f)
fun glUniform2f(location: Int, vec3: Vec3t<*>) = GL20.glUniform2f(location, vec3.x.f, vec3.y.f)
fun glUniform2f(location: Int, vec4: Vec4t<*>) = GL20.glUniform2f(location, vec4.x.f, vec4.y.f)

fun glUniform2f(location: Int, x: Float) = GL20.glUniform2f(location, x, x)
fun glUniform2f(location: Int, vec1: Vec1) = GL20.glUniform2f(location, vec1.x, 0f)
fun glUniform2f(location: Int, vec1: Vec1, y: Float) = GL20.glUniform2f(location, vec1.x, y)
fun glUniform2f(location: Int, vec2: Vec2) = GL20.glUniform2f(location, vec2.x, vec2.y)
fun glUniform2f(location: Int, vec3: Vec3) = GL20.glUniform2f(location, vec3.x, vec3.y)
fun glUniform2f(location: Int, vec4: Vec4) = GL20.glUniform2f(location, vec4.x, vec4.y)

fun glUniform2f(location: Int, x: Boolean) = GL20.glUniform2f(location, x.f, x.f)
fun glUniform2f(location: Int, vec1: Vec1bool) = GL20.glUniform2f(location, vec1.x.f, 0f)
fun glUniform2f(location: Int, vec1: Vec1bool, y: Number) = GL20.glUniform2f(location, vec1.x.f, y.f)
fun glUniform2f(location: Int, vec1: Vec1bool, y: Boolean) = GL20.glUniform2f(location, vec1.x.f, y.f)
fun glUniform2f(location: Int, vec2: Vec2bool) = GL20.glUniform2f(location, vec2.x.f, vec2.y.f)
fun glUniform2f(location: Int, vec3: Vec3bool) = GL20.glUniform2f(location, vec3.x.f, vec3.y.f)
fun glUniform2f(location: Int, vec4: Vec4bool) = GL20.glUniform2f(location, vec4.x.f, vec4.y.f)


// conversions, 2i

fun glUniform2i(location: Int) = GL20.glUniform2i(location, 0, 0)

fun glUniform2i(location: Int, x: Number) = GL20.glUniform2i(location, x.i, x.i)
fun glUniform2i(location: Int, vec1: Vec1t<*>) = GL20.glUniform2i(location, vec1.x.i, 0)
fun glUniform2i(location: Int, vec1: Vec1t<*>, y: Number) = GL20.glUniform2i(location, vec1.x.i, y.i)
fun glUniform2i(location: Int, vec2: Vec2t<*>) = GL20.glUniform2i(location, vec2.x.i, vec2.y.i)
fun glUniform2i(location: Int, vec3: Vec3t<*>) = GL20.glUniform2i(location, vec3.x.i, vec3.y.i)
fun glUniform2i(location: Int, vec4: Vec4t<*>) = GL20.glUniform2i(location, vec4.x.i, vec4.y.i)

fun glUniform2i(location: Int, x: Int) = GL20.glUniform2i(location, x, x)
fun glUniform2i(location: Int, vec1: Vec1i) = GL20.glUniform2i(location, vec1.x, 0)
fun glUniform2i(location: Int, vec1: Vec1i, y: Int) = GL20.glUniform2i(location, vec1.x, y)
fun glUniform2i(location: Int, vec2: Vec2i) = GL20.glUniform2i(location, vec2.x, vec2.y)
fun glUniform2i(location: Int, vec3: Vec3i) = GL20.glUniform2i(location, vec3.x, vec3.y)
fun glUniform2i(location: Int, vec4: Vec4i) = GL20.glUniform2i(location, vec4.x, vec4.y)

fun glUniform2i(location: Int, x: Boolean) = GL20.glUniform2i(location, x.i, x.i)
fun glUniform2i(location: Int, vec1: Vec1bool) = GL20.glUniform2i(location, vec1.x.i, 0)
fun glUniform2i(location: Int, vec1: Vec1bool, y: Number) = GL20.glUniform2i(location, vec1.x.i, y.i)
fun glUniform2i(location: Int, vec1: Vec1bool, y: Boolean) = GL20.glUniform2i(location, vec1.x.i, y.i)
fun glUniform2i(location: Int, vec2: Vec2bool) = GL20.glUniform2i(location, vec2.x.i, vec2.y.i)
fun glUniform2i(location: Int, vec3: Vec3bool) = GL20.glUniform2i(location, vec3.x.i, vec3.y.i)
fun glUniform2i(location: Int, vec4: Vec4bool) = GL20.glUniform2i(location, vec4.x.i, vec4.y.i)


// conversions, 3f

fun glUniform3f(location: Int) = GL20.glUniform3f(location, 0f, 0f, 0f)

fun glUniform3f(location: Int, x: Number) = GL20.glUniform3f(location, x.f, x.f, x.f)
fun glUniform3f(location: Int, x: Number, y: Number, z: Number) = GL20.glUniform3f(location, x.f, y.f, z.f)
fun glUniform3f(location: Int, vec1: Vec1t<*>) = GL20.glUniform3f(location, vec1.x.f, 0f, 0f)
fun glUniform3f(location: Int, vec1: Vec1t<*>, y: Number, z: Number) = GL20.glUniform3f(location, vec1.x.f, y.f, z.f)
fun glUniform3f(location: Int, vec2: Vec2t<*>) = GL20.glUniform3f(location, vec2.x.f, vec2.y.f, 0f)
fun glUniform3f(location: Int, vec2: Vec2t<*>, z: Number) = GL20.glUniform3f(location, vec2.x.f, vec2.y.f, z.f)
fun glUniform3f(location: Int, vec3: Vec3t<*>) = GL20.glUniform3f(location, vec3.x.f, vec3.y.f, vec3.y.f)
fun glUniform3f(location: Int, vec4: Vec4t<*>) = GL20.glUniform3f(location, vec4.x.f, vec4.y.f, vec4.y.f)

fun glUniform3f(location: Int, x: Float) = GL20.glUniform3f(location, x, x, x)
fun glUniform3f(location: Int, vec1: Vec1) = GL20.glUniform3f(location, vec1.x, 0f, 0f)
fun glUniform3f(location: Int, vec1: Vec1, y: Float, z: Float) = GL20.glUniform3f(location, vec1.x, y, z)
fun glUniform3f(location: Int, vec2: Vec2) = GL20.glUniform3f(location, vec2.x, vec2.y, 0f)
fun glUniform3f(location: Int, vec2: Vec2, z: Float) = GL20.glUniform3f(location, vec2.x, vec2.y, z)
fun glUniform3f(location: Int, vec3: Vec3) = GL20.glUniform3f(location, vec3.x, vec3.y, vec3.z)
fun glUniform3f(location: Int, vec4: Vec4) = GL20.glUniform3f(location, vec4.x, vec4.y, vec4.z)

fun glUniform3f(location: Int, x: Boolean) = GL20.glUniform3f(location, x.f, x.f, x.f)
fun glUniform3f(location: Int, vec1: Vec1bool) = GL20.glUniform3f(location, vec1.x.f, 0f, 0f)
fun glUniform3f(location: Int, vec1: Vec1bool, y: Number, z: Number) = GL20.glUniform3f(location, vec1.x.f, y.f, z.f)
fun glUniform3f(location: Int, vec1: Vec1bool, y: Boolean, z: Boolean) = GL20.glUniform3f(location, vec1.x.f, y.f, z.f)
fun glUniform3f(location: Int, vec2: Vec2bool) = GL20.glUniform3f(location, vec2.x.f, vec2.y.f, 0f)
fun glUniform3f(location: Int, vec2: Vec2bool, z: Number) = GL20.glUniform3f(location, vec2.x.f, vec2.y.f, z.f)
fun glUniform3f(location: Int, vec2: Vec2bool, z: Boolean) = GL20.glUniform3f(location, vec2.x.f, vec2.y.f, z.f)
fun glUniform3f(location: Int, vec3: Vec3bool) = GL20.glUniform3f(location, vec3.x.f, vec3.y.f, vec3.z.f)
fun glUniform3f(location: Int, vec4: Vec4bool) = GL20.glUniform3f(location, vec4.x.f, vec4.y.f, vec4.z.f)


// conversions, 3i

fun glUniform3i(location: Int) = GL20.glUniform3i(location, 0, 0, 0)

fun glUniform3i(location: Int, x: Number) = GL20.glUniform3i(location, x.i, x.i, x.i)
fun glUniform3i(location: Int, vec1: Vec1t<*>) = GL20.glUniform3i(location, vec1.x.i, 0, 0)
fun glUniform3i(location: Int, vec1: Vec1t<*>, y: Number, z: Number) = GL20.glUniform3i(location, vec1.x.i, y.i, z.i)
fun glUniform3i(location: Int, vec2: Vec2t<*>, z: Number) = GL20.glUniform3i(location, vec2.x.i, vec2.y.i, z.i)
fun glUniform3i(location: Int, vec3: Vec3t<*>) = GL20.glUniform3i(location, vec3.x.i, vec3.y.i, vec3.z.i)
fun glUniform3i(location: Int, vec4: Vec4t<*>) = GL20.glUniform3i(location, vec4.x.i, vec4.y.i, vec4.z.i)

fun glUniform3i(location: Int, x: Int) = GL20.glUniform3i(location, x, x, x)
fun glUniform3i(location: Int, vec1: Vec1i) = GL20.glUniform3i(location, vec1.x, 0, 0)
fun glUniform3i(location: Int, vec1: Vec1i, y: Int, z: Int) = GL20.glUniform3i(location, vec1.x, y, z)
fun glUniform3i(location: Int, vec2: Vec2i) = GL20.glUniform3i(location, vec2.x, vec2.y, 0)
fun glUniform3i(location: Int, vec2: Vec2i, z: Int) = GL20.glUniform3i(location, vec2.x, vec2.y, z)
fun glUniform3i(location: Int, vec3: Vec3i) = GL20.glUniform3i(location, vec3.x, vec3.y, vec3.z)
fun glUniform3i(location: Int, vec4: Vec4i) = GL20.glUniform3i(location, vec4.x, vec4.y, vec4.z)

fun glUniform3i(location: Int, x: Boolean) = GL20.glUniform3i(location, x.i, x.i, x.i)
fun glUniform3i(location: Int, vec1: Vec1bool) = GL20.glUniform3i(location, vec1.x.i, 0, 0)
fun glUniform3i(location: Int, vec1: Vec1bool, y: Number, z: Number) = GL20.glUniform3i(location, vec1.x.i, y.i, z.i)
fun glUniform3i(location: Int, vec1: Vec1bool, y: Boolean, z: Boolean) = GL20.glUniform3i(location, vec1.x.i, y.i, z.i)
fun glUniform3i(location: Int, vec2: Vec2bool) = GL20.glUniform3i(location, vec2.x.i, vec2.y.i, 0)
fun glUniform3i(location: Int, vec2: Vec2bool, z: Number) = GL20.glUniform3i(location, vec2.x.i, vec2.y.i, z.i)
fun glUniform3i(location: Int, vec2: Vec2bool, z: Boolean) = GL20.glUniform3i(location, vec2.x.i, vec2.y.i, z.i)
fun glUniform3i(location: Int, vec3: Vec3bool) = GL20.glUniform3i(location, vec3.x.i, vec3.y.i, vec3.z.i)
fun glUniform3i(location: Int, vec4: Vec4bool) = GL20.glUniform3i(location, vec4.x.i, vec4.y.i, vec4.z.i)


// conversions, 4f

fun glUniform4f(location: Int) = GL20.glUniform4f(location, 0f, 0f, 0f, 1f)

fun glUniform4f(location: Int, x: Number) = GL20.glUniform4f(location, x.f, x.f, x.f, x.f)
fun glUniform4f(location: Int, x: Number, y: Number, z: Number, w: Number) = GL20.glUniform4f(location, x.f, y.f, z.f, w.f)
fun glUniform4f(location: Int, vec1: Vec1t<*>) = GL20.glUniform4f(location, vec1.x.f, 0f, 0f, 1f)
fun glUniform4f(location: Int, vec1: Vec1t<*>, y: Number, z: Number, w: Number) = GL20.glUniform4f(location, vec1.x.f, y.f, z.f, w.f)
fun glUniform4f(location: Int, vec2: Vec2t<*>) = GL20.glUniform4f(location, vec2.x.f, vec2.y.f, 0f, 1f)
fun glUniform4f(location: Int, vec2: Vec2t<*>, z: Number, w: Number) = GL20.glUniform4f(location, vec2.x.f, vec2.y.f, z.f, w.f)
fun glUniform4f(location: Int, a: Vec2t<*>, b: Vec2t<*>) = GL20.glUniform4f(location, a.x.f, a.y.f, b.x.f, b.y.f)
fun glUniform4f(location: Int, vec3: Vec3t<*>) = GL20.glUniform4f(location, vec3.x.f, vec3.y.f, vec3.y.f, 1f)
fun glUniform4f(location: Int, vec3: Vec3t<*>, w: Number) = GL20.glUniform4f(location, vec3.x.f, vec3.y.f, vec3.y.f, w.f)
fun glUniform4f(location: Int, vec4: Vec4t<*>) = GL20.glUniform4f(location, vec4.x.f, vec4.y.f, vec4.y.f, vec4.w.f)

fun glUniform4f(location: Int, x: Float) = GL20.glUniform4f(location, x, x, x, x)
fun glUniform4f(location: Int, vec1: Vec1) = GL20.glUniform4f(location, vec1.x, 0f, 0f, 1f)
fun glUniform4f(location: Int, vec1: Vec1, y: Float, z: Float, w: Float) = GL20.glUniform4f(location, vec1.x, y, z, w)
fun glUniform4f(location: Int, vec2: Vec2) = GL20.glUniform4f(location, vec2.x, vec2.y, 0f, 1f)
fun glUniform4f(location: Int, vec2: Vec2, z: Float, w: Float) = GL20.glUniform4f(location, vec2.x, vec2.y, z, w)
fun glUniform4f(location: Int, a: Vec2, b: Vec2) = GL20.glUniform4f(location, a.x, a.y, a.x, a.y)
fun glUniform4f(location: Int, vec3: Vec3) = GL20.glUniform4f(location, vec3.x, vec3.y, vec3.z, 1f)
fun glUniform4f(location: Int, vec3: Vec3, w: Float) = GL20.glUniform4f(location, vec3.x, vec3.y, vec3.z, w)
fun glUniform4f(location: Int, vec4: Vec4) = GL20.glUniform4f(location, vec4.x, vec4.y, vec4.z, vec4.w)

fun glUniform4f(location: Int, x: Boolean) = GL20.glUniform4f(location, x.f, 0f, 0f, 1f)
fun glUniform4f(location: Int, vec1: Vec1bool) = GL20.glUniform4f(location, vec1.x.f, 0f, 0f, 1f)
fun glUniform4f(location: Int, vec1: Vec1bool, y: Number, z: Number, w: Number) = GL20.glUniform4f(location, vec1.x.f, y.f, z.f, w.f)
fun glUniform4f(location: Int, vec1: Vec1bool, y: Boolean, z: Boolean, w: Boolean) = GL20.glUniform4f(location, vec1.x.f, y.f, z.f, w.f)
fun glUniform4f(location: Int, vec2: Vec2bool) = GL20.glUniform4f(location, vec2.x.f, vec2.y.f, 0f, 1f)
fun glUniform4f(location: Int, vec2: Vec2bool, z: Number, w: Number) = GL20.glUniform4f(location, vec2.x.f, vec2.y.f, z.f, w.f)
fun glUniform4f(location: Int, vec2: Vec2bool, z: Boolean, w: Boolean) = GL20.glUniform4f(location, vec2.x.f, vec2.y.f, z.f, w.f)
fun glUniform4f(location: Int, vec3: Vec3bool) = GL20.glUniform4f(location, vec3.x.f, vec3.y.f, vec3.z.f, 1f)
fun glUniform4f(location: Int, vec3: Vec3bool, w: Number) = GL20.glUniform4f(location, vec3.x.f, vec3.y.f, vec3.z.f, w.f)
fun glUniform4f(location: Int, vec3: Vec3bool, w: Boolean) = GL20.glUniform4f(location, vec3.x.f, vec3.y.f, vec3.z.f, w.f)
fun glUniform4f(location: Int, vec4: Vec4bool) = GL20.glUniform4f(location, vec4.x.f, vec4.y.f, vec4.z.f, vec4.w.f)


// conversions, 4i

fun glUniform4i(location: Int) = GL20.glUniform4i(location, 0, 0, 0, 1)

fun glUniform4i(location: Int, x: Number) = GL20.glUniform4i(location, x.i, x.i, x.i, x.i)
fun glUniform4i(location: Int, vec1: Vec1t<*>) = GL20.glUniform4i(location, vec1.x.i, 0, 0, 1)
fun glUniform4i(location: Int, vec1: Vec1t<*>, y: Number, z: Number, w: Number) = GL20.glUniform4i(location, vec1.x.i, y.i, z.i, w.i)
fun glUniform4i(location: Int, vec2: Vec2t<*>, z: Number, w: Number) = GL20.glUniform4i(location, vec2.x.i, vec2.y.i, z.i, w.i)
fun glUniform4i(location: Int, a: Vec2t<*>, b: Vec2t<*>) = GL20.glUniform4i(location, a.x.i, a.y.i, b.x.i, b.y.i)
fun glUniform4i(location: Int, vec3: Vec3t<*>) = GL20.glUniform4i(location, vec3.x.i, vec3.y.i, vec3.z.i, 1)
fun glUniform4i(location: Int, vec3: Vec3t<*>, w: Number) = GL20.glUniform4i(location, vec3.x.i, vec3.y.i, vec3.z.i, w.i)
fun glUniform4i(location: Int, vec4: Vec4t<*>) = GL20.glUniform4i(location, vec4.x.i, vec4.y.i, vec4.z.i, vec4.w.i)

fun glUniform4i(location: Int, x: Int) = GL20.glUniform4i(location, x, x, x, x)
fun glUniform4i(location: Int, vec1: Vec1i) = GL20.glUniform4i(location, vec1.x, 0, 0, 1)
fun glUniform4i(location: Int, vec1: Vec1i, y: Int, z: Int, w: Int) = GL20.glUniform4i(location, vec1.x, y, z, w)
fun glUniform4i(location: Int, vec2: Vec2i) = GL20.glUniform4i(location, vec2.x, vec2.y, 0, 1)
fun glUniform4i(location: Int, vec2: Vec2i, z: Int, w: Int) = GL20.glUniform4i(location, vec2.x, vec2.y, z, w)
fun glUniform4i(location: Int, vec3: Vec3i) = GL20.glUniform4i(location, vec3.x, vec3.y, vec3.z, 1)
fun glUniform4i(location: Int, vec3: Vec3i, w: Int) = GL20.glUniform4i(location, vec3.x, vec3.y, vec3.z, w)
fun glUniform4i(location: Int, vec4: Vec4i) = GL20.glUniform4i(location, vec4.x, vec4.y, vec4.z, vec4.w)

fun glUniform4i(location: Int, x: Boolean) = GL20.glUniform4i(location, x.i, x.i, x.i, x.i)
fun glUniform4i(location: Int, vec1: Vec1bool) = GL20.glUniform4i(location, vec1.x.i, 0, 0, 1)
fun glUniform4i(location: Int, vec1: Vec1bool, y: Number, z: Number, w: Number) = GL20.glUniform4i(location, vec1.x.i, y.i, z.i, w.i)
fun glUniform4i(location: Int, vec1: Vec1bool, y: Boolean, z: Boolean, w: Boolean) = GL20.glUniform4i(location, vec1.x.i, y.i, z.i, w.i)
fun glUniform4i(location: Int, vec2: Vec2bool) = GL20.glUniform4i(location, vec2.x.i, vec2.y.i, 0, 1)
fun glUniform4i(location: Int, vec2: Vec2bool, z: Number, w: Number) = GL20.glUniform4i(location, vec2.x.i, vec2.y.i, z.i, w.i)
fun glUniform4i(location: Int, vec2: Vec2bool, z: Boolean, w: Boolean) = GL20.glUniform4i(location, vec2.x.i, vec2.y.i, z.i, w.i)
fun glUniform4i(location: Int, vec3: Vec3bool) = GL20.glUniform4i(location, vec3.x.i, vec3.y.i, vec3.z.i, 1)
fun glUniform4i(location: Int, vec3: Vec3bool, w: Number) = GL20.glUniform4i(location, vec3.x.i, vec3.y.i, vec3.z.i, w.i)
fun glUniform4i(location: Int, vec3: Vec3bool, w: Boolean) = GL20.glUniform4i(location, vec3.x.i, vec3.y.i, vec3.z.i, w.i)
fun glUniform4i(location: Int, vec4: Vec4bool) = GL20.glUniform4i(location, vec4.x.i, vec4.y.i, vec4.z.i, vec4.w.i)


// ----------------------------------------- mat -----------------------------------------------------------------------

// inferred length and type

fun glUniform(location: Int, mat2: Mat2) {
    mat2 to buf
    GL20.nglUniformMatrix2fv(location, 1, false, bufAd)
}

fun glUniform(location: Int, mat2x3: Mat2x3) {
    TODO()
//    mat2x3 to
//    GL21.glUniformMatrix2x3fv(location, false, mat2x3 to m23Buf)
}

fun glUniform(location: Int, mat2x4: Mat2x4) {
    TODO()
//    GL21.glUniformMatrix2x4fv(location, false, mat2x4 to m24Buf)
}

fun glUniform(location: Int, mat3x2: Mat3x2) {
    TODO()
//    GL21.glUniformMatrix3x2fv(location, false, mat3x2 to m32Buf)
}

fun glUniform(location: Int, mat3: Mat3) {
    mat3 to buf
    GL20.nglUniformMatrix3fv(location, 1, false, bufAd)
}

fun glUniform(location: Int, mat3x4: Mat3x4) {
    TODO()
//    GL21.glUniformMatrix3x4fv(location, false, mat3x4 to m34Buf)
}

fun glUniform(location: Int, mat4x2: Mat4x2) {
    TODO()
//    GL21.glUniformMatrix4x2fv(location, false, mat4x2 to m42Buf)
}

fun glUniform(location: Int, mat4x3: Mat4x3) {
    TODO()
//    GL21.glUniformMatrix4x3fv(location, false, mat4x3 to m43Buf)
}

fun glUniform(location: Int, mat4: Mat4) {
    mat4 to buf
    GL20.nglUniformMatrix4fv(location, 1, false, bufAd)
}

// TODO double mat and vectors
//fun glUniform(location: Int, mat2: Mat2) = GL40.glUniformMatrix2dv(location, false, mat2 to m2Buf)
//fun glUniform(location: Int, mat2x3: Mat2x3) = GL40.glUniformMatrix2x3dv(location, false, mat2x3 to m23Buf)
//fun glUniform(location: Int, mat2x4: Mat2x4) = GL40.glUniformMatrix2x4dv(location, false, mat2x4 to m24Buf)
//fun glUniform(location: Int, mat3x2: Mat3x2) = GL40.glUniformMatrix3x2dv(location, false, mat3x2 to m32Buf)
//fun glUniform(location: Int, mat3: Mat3) = GL40.glUniformMatrix3dv(location, false, mat3 to m3Buf)
//fun glUniform(location: Int, mat3x4: Mat3x4) = GL40.glUniformMatrix3x4dv(location, false, mat3x4 to m34Buf)
//fun glUniform(location: Int, mat4x2: Mat4x2) = GL40.glUniformMatrix4x2dv(location, false, mat4x2 to m42Buf)
//fun glUniform(location: Int, mat4x3: Mat4x3) = GL40.glUniformMatrix4x3dv(location, false, mat4x3 to m43Buf)
//fun glUniform(location: Int, mat4: Mat4) = GL40.glUniformMatrix4dv(location, false, mat4 to m4Buf)

// special for textures
fun glUniform1i(location: Int, v0: Enum<*>) = GL20.glUniform1i(location, v0.ordinal)

// special for colors
fun glUniform(location: Int, color: Color) = with(color) { GL20.glUniform4f(location, red / 255f, green / 255f, blue / 255f, alpha / 255f) }