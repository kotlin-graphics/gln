@file:Suppress("NOTHING_TO_INLINE")

package gln.vertexArray


import glm_.L
import glm_.set
import gln.buf
import gln.bufAd
import gln.buffer.bufferName
import gln.glf.VertexLayout
import kool.get
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30
import java.nio.IntBuffer


inline fun initVertexArrays(block: VertexArrays.() -> Unit) = initVertexArrays(vertexArrayName, block)
inline fun initVertexArrays(vertexArrays: IntBuffer, block: VertexArrays.() -> Unit) {
    GL30.nglGenVertexArrays(1, bufAd)
    vertexArrays[0] = buf.getInt(0)
    VertexArrays.names = vertexArrays
    VertexArrays.block()
    glBindVertexArray()
}

inline fun initVertexArray(vertexArray: Enum<*>, block: VertexArray.() -> Unit) = vertexArrayName.put(vertexArray.ordinal, initVertexArray(block))
inline fun initVertexArray(vertexArray: IntBuffer, block: VertexArray.() -> Unit) = vertexArray.put(0, initVertexArray(block))
inline fun initVertexArray(block: VertexArray.() -> Unit): Int {
    GL30.nglGenVertexArrays(1, bufAd)
    val res = buf.getInt(0)
    VertexArray.name = res   // bind
    VertexArray.block()
    glBindVertexArray()
    return res
}

inline fun withVertexArray(vertexArray: Enum<*>, block: VertexArray.() -> Unit) = withVertexArray(vertexArrayName[vertexArray], block)
inline fun withVertexArray(vertexArray: IntBuffer, block: VertexArray.() -> Unit) = withVertexArray(vertexArray[0], block)
inline fun withVertexArray(vertexArray: Int, block: VertexArray.() -> Unit) {
    VertexArray.name = vertexArray   // bind
    VertexArray.block()
    glBindVertexArray()
}

object VertexArray {

    var name = 0
        set(value) = GL30.glBindVertexArray(value)

    inline fun array(array: Enum<*>, format: VertexLayout) = array(bufferName[array], format)
    inline fun array(array: IntBuffer, format: VertexLayout) = array(array[0], format)
    inline fun array(array: Int, format: VertexLayout) {
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, array)
        for (attr in format.attributes) {
            GL20.glEnableVertexAttribArray(attr.index)
            GL20.glVertexAttribPointer(attr.index, attr.size, attr.type.i, attr.normalized, attr.interleavedStride, attr.pointer)
        }
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)
    }

    inline fun array(array: Enum<*>, format: VertexLayout, vararg offset: Int) = array(bufferName[array], format, *offset) // TODO check, slow operator
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

    inline fun element(element: Enum<*>) = GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, bufferName[element])
    inline fun element(element: IntBuffer) = GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, element[0])
    inline fun element(element: Int) = GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, element)
}

object VertexArrays {

    lateinit var names: IntBuffer

    inline fun at(index: Enum<*>, block: VertexArray.() -> Unit) = at(index.ordinal, block)
    inline fun at(index: Int, block: VertexArray.() -> Unit) {
        VertexArray.name = names[index]   // bind
        VertexArray.block()
    }
}


inline fun withVertexLayout(array: Enum<*>, element: Enum<*>, format: VertexLayout, block: () -> Unit) = withVertexLayout(bufferName[array], bufferName[element], format, block)
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

inline fun withVertexLayout(array: Enum<*>, format: VertexLayout, block: () -> Unit) = withVertexLayout(bufferName[array], format, block)
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