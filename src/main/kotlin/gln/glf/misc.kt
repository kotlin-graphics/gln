//package gln.glf
//
//import glm_.vec2.Vec2
//import glm_.vec3.Vec3
//import glm_.vec4.Vec4
//import kool.FloatBuffer
//import java.nio.FloatBuffer
//
//
//inline class Vec2Buffer(val data: FloatBuffer)
//
//fun Vec2Buffer(size: Int) = Vec2Buffer(FloatBuffer(size * Vec2.length))
//
//inline fun Vec2Buffer(size: Int, init: (Int) -> Vec2): Vec2Buffer {
//    val buffer = Vec2Buffer(size)
//    for (i in 0 until size)
//        init(i).to(buffer.data, i * Vec2.length)
//    return buffer
//}
//
//fun vec2BufferOf(vararg vecs: Vec2) = Vec2Buffer(vecs.size) { vecs[it] }
//
//
//class Vertex_v2v2(val a: Vec2, val b: Vec2) {
//
//    fun to(floats: FloatBuffer, index: Int) {
//        a.to(floats, index)
//        b.to(floats, index + Vec2.length)
//    }
//
//    companion object {
//        val size = Vec2.size * 2
//        val length = Vec2.length * 2
//    }
//}
//
//inline class Vertex_v2v2_Buffer(val data: FloatBuffer) {
//
//
//}
//
//fun Vertex_v2v2_Buffer(size: Int) = Vertex_v2v2_Buffer(FloatBuffer(size * Vertex_v2v2.length))
//
//inline fun Vertex_v2v2_Buffer(size: Int, init: (Int) -> Vertex_v2v2): Vertex_v2v2_Buffer {
//    val buffer = Vertex_v2v2_Buffer(size)
//    for (i in 0 until size)
//        init(i).to(buffer.data, i * Vertex_v2v2.length)
//    return buffer
//}
//
//fun vertex_v2v2_buffer_of(vararg vertices: Vertex_v2v2) = Vertex_v2v2_Buffer(vertices.size) { vertices[it] }
//fun vertex_v2v2_buffer_of(vararg vecs: Vec2) = Vertex_v2v2_Buffer(vecs.size / 2).apply {
//    var i = 0
//    while (i < vecs.size / 2) {
//        vecs[i * 2].to(data, i * Vertex_v2v2.length)
//        vecs[i * 2 + 1].to(data, i++ * Vertex_v2v2.length + Vec2.length)
//    }
//}
//
//
//class Vertex_v3n3c4(val position: Vec3, val normal: Vec3, val color: Vec4) {
//
//    fun to(floats: FloatBuffer, index: Int) {
//        position.to(floats, index)
//        normal.to(floats, index + Vec3.length)
//        color.to(floats, index + Vec3.length * 2)
//    }
//
//    companion object {
//        val size = Vec3.size * 2 + Vec4.size
//        val length = Vec3.length * 2 + Vec4.size
//    }
//}
//
//inline class Vertex_v3n3c4_Buffer(val data: FloatBuffer) {
//
//
//}
//
//fun Vertex_v3n3c4_Buffer(size: Int) = Vertex_v3n3c4_Buffer(FloatBuffer(size * Vertex_v3n3c4.length))
//
//inline fun Vertex_v3n3c4_Buffer(size: Int, init: (Int) -> Vertex_v3n3c4): Vertex_v3n3c4_Buffer {
//    val buffer = Vertex_v3n3c4_Buffer(size)
//    for (i in 0 until size)
//        init(i).to(buffer.data, i * Vertex_v3n3c4.length)
//    return buffer
//}
//
//fun vertex_v3n3c4_buffer_of(vararg vertices: Vertex_v3n3c4): Vertex_v3n3c4_Buffer = Vertex_v3n3c4_Buffer(vertices.size) { vertices[it] }
//fun vertex_v3n3c4_buffer_of(vararg vecs: Any): Vertex_v3n3c4_Buffer {
//    val buf = Vertex_v3n3c4_Buffer(FloatBuffer(vecs.size / 3 * Vertex_v3n3c4.length))
//    for(i in vecs.indices) {
//        val p = vecs[i * 3] as Vec3
//        val n = vecs[i * 3 + 1] as Vec3
//        val c = vecs[i * 3 + 2] as Vec4
//        p.to(buf.data, i * Vertex_v3n3c4.length)
//        n.to(buf.data, i * Vertex_v3n3c4.length + Vec3.length)
//        c.to(buf.data, i * Vertex_v3n3c4.length + Vec3.length * 2)
//    }
//    return buf
//}