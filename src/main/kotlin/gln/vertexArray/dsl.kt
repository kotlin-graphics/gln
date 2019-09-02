@file:Suppress("NOTHING_TO_INLINE")

package gln.vertexArray


import glm_.L
import gln.buf
import gln.bufAd
import gln.glf.VertexLayout
import kool.get
import kool.set
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL30C
import java.nio.IntBuffer


object GlVertexArrayDsl {

    var name = 0

    inline fun array(array: IntBuffer, format: VertexLayout) = array(array[0], format)
    inline fun array(array: Int, format: VertexLayout) {
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, array)
        for (attr in format.attributes) {
            GL20.glEnableVertexAttribArray(attr.index)
            GL20.glVertexAttribPointer(attr.index, attr.size, attr.type.i, attr.normalized, attr.interleavedStride, attr.pointer)
        }
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)
    }

    inline fun array(array: IntBuffer, format: VertexLayout, vararg offset: Int) = array(array[0], format, *offset) // TODO check, slow operator
    inline fun array(array: Int, format: VertexLayout, vararg offset: Int) {
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, array)
        for (i in 0 until format.attributes.size) {
            val attr = format.attributes[i]
            GL20.glEnableVertexAttribArray(attr.index)
            GL20.glVertexAttribPointer(attr.index, attr.size, attr.type.i, attr.normalized, 0, offset[i].L)
        }
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)
    }

    inline fun element(element: IntBuffer) = GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, element[0])
    inline fun element(element: Int) = GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, element)
}

object GlVertexArraysDsl {

    lateinit var names: IntBuffer

    inline fun Int.bind(block: GlVertexArrayDsl.() -> Unit) {
        val name = names[this]
        GlVertexArrayDsl.name = name
        GL30C.glBindVertexArray(name)
        GlVertexArrayDsl.block()
    }

    inline fun <E : Enum<E>> E.bind(block: GlVertexArrayDsl.() -> Unit) {
        val name = names[this]
        GlVertexArrayDsl.name = name
        GL30C.glBindVertexArray(name)
        GlVertexArrayDsl.block()
    }

    inline fun Int.bound(block: GlVertexArrayDsl.() -> Unit) {
        bind(block)
        GL30C.glBindVertexArray(0)
    }

    inline fun <E : Enum<E>> E.bound(block: GlVertexArrayDsl.() -> Unit) {
        bind(block)
        GL30C.glBindVertexArray(0)
    }
}


inline fun withVertexLayout(array: Int, element: Int, format: VertexLayout, block: () -> Unit) {
    GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, array)
    for (attr in format.attributes) {
        GL20.glEnableVertexAttribArray(attr.index)
        GL20.glVertexAttribPointer(attr.index, attr.size, attr.type.i, attr.normalized, attr.interleavedStride, attr.pointer)
    }
    GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)
    GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, element)
    block()
    for (attr in format.attributes)
        GL20.glDisableVertexAttribArray(attr.index)
}

inline fun withVertexLayout(array: IntBuffer, format: VertexLayout, block: () -> Unit) = withVertexLayout(array[0], format, block)
inline fun withVertexLayout(array: Int, format: VertexLayout, block: () -> Unit) {
    GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, array)
    for (attr in format.attributes) {
        GL20.glEnableVertexAttribArray(attr.index)
        GL20.glVertexAttribPointer(attr.index, attr.size, attr.type.i, attr.normalized, attr.interleavedStride, attr.pointer)
    }
    GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)
    block()
    for (attr in format.attributes) GL20.glDisableVertexAttribArray(attr.index)
}


/** For un-interleaved, that is not-interleaved */
inline fun withVertexLayout(buffer: IntBuffer, format: VertexLayout, vararg offset: Int, block: () -> Unit) {
    GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, buffer[0])
    for (i in 0 until format.attributes.size) {
        val attr = format.attributes[i]
        GL20.glEnableVertexAttribArray(attr.index)
        GL20.glVertexAttribPointer(attr.index, attr.size, attr.type.i, attr.normalized, 0, offset[i].L)
    }
    GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)
    block()
    for (attr in format.attributes)
        GL20.glDisableVertexAttribArray(attr.index)
}