@file:Suppress("NOTHING_TO_INLINE")

package gln.vertexArray

import glm_.L
import gln.VertexAttrIndex
import gln.VertexAttrSize
import gln.VertexAttrType
import gln.gl
import gln.glf.VertexAttribute
import gln.glf.VertexLayout
import gln.objects.GlBuffer
import gln.objects.GlBuffers
import kool.IntBuffer
import kool.adr
import kool.get
import kool.rem
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL30C
import java.nio.IntBuffer
import java.nio.LongBuffer
import kotlin.properties.Delegates
import kotlin.reflect.KMutableProperty0


var vertexArrayName: IntBuffer by Delegates.notNull()

inline fun glGenVertexArray(array: KMutableProperty0<Int>) = array.set(GL30.glGenVertexArrays())
inline fun glGenVertexArray() = GL30.glGenVertexArrays()
inline fun glDeleteVertexArray(array: Int) = GL30.glDeleteVertexArrays(array)

inline fun glBindVertexArray(vertexArray: Enum<*>) = GL30.glBindVertexArray(vertexArrayName[vertexArray])
inline fun glBindVertexArray(vertexArray: IntBuffer) = GL30.glBindVertexArray(vertexArray[0])

inline fun glBindVertexArray() = GL30.glBindVertexArray(0)


inline fun glVertexAttribPointer(index: Int, size: Int, type: Int, normalized: Boolean, stride: Int, pointer: Int) = GL20.glVertexAttribPointer(index, size, type, normalized, stride, pointer.L)

inline fun glVertexAttribPointer(layout: VertexLayout) = layout.attributes.forEach(::glVertexAttribPointer)
inline fun glEnableVertexAttribArray(layout: VertexLayout) = layout.attributes.forEach(::glEnableVertexAttribArray)
inline fun glDisableVertexAttribArray(layout: VertexLayout) = layout.attributes.forEach(::glDisableVertexAttribArray)
inline fun glVertexAttribPointer(attribute: VertexAttribute) = with(attribute) { GL20.glVertexAttribPointer(index, size, type.i, normalized, interleavedStride, pointer.L) }
inline fun glEnableVertexAttribArray(attribute: VertexAttribute) = GL20.glEnableVertexAttribArray(attribute.index)
inline fun glDisableVertexAttribArray(attribute: VertexAttribute) = GL20.glDisableVertexAttribArray(attribute.index)


fun GlVertexArrays(size: Int) = GlVertexArrays(IntBuffer(size))

inline class GlVertexArrays(val names: IntBuffer) {
    val rem get() = names.rem
    val adr get() = names.adr

    // --- [ glCreateVertexArrays ] ---

    fun create() = gl.createVertexArrays(this)

    companion object {

        // --- [ glCreateVertexArrays ] ---
        fun create(size: Int) = gl.createVertexArrays(size)
    }
}


inline class GlVertexArray(val name: Int = -1) {

    fun bind() = GL30C.glBindVertexArray(name)
    inline fun bound(block: () -> Unit): GlVertexArray {
        GL30C.glBindVertexArray(name)
        block()
        GL30C.glBindVertexArray(0)
        return this
    }

    fun delete() = GL30C.glDeleteVertexArrays(name)

    // --- [ glDisableVertexArrayAttrib ] ---

    infix fun disableAttrib(index: VertexAttrIndex) = gl.disableVertexArrayAttrib(this, index)

    // --- [ glEnableVertexArrayAttrib ] ---

    infix fun enableAttrib(index: VertexAttrIndex) = gl.enableVertexArrayAttrib(this, index)

    // --- [ glVertexArrayElementBuffer ] ---

    infix fun elementBuffer(buffer: GlBuffer) = gl.vertexArrayElementBuffer(this, buffer)

    // --- [ glVertexArrayVertexBuffer ] ---

    fun vertexBuffer(bindingIndex: Int, buffer: GlBuffer, offset: Int, stride: Int) = gl.vertexArrayVertexBuffer(this, bindingIndex, buffer, offset, stride)

    // --- [ glVertexArrayVertexBuffers ] ---

    fun vertexBuffers(first: Int, buffers: GlBuffers? = null, offsets: LongBuffer? = null, strides: IntBuffer? = null) = gl.vertexArrayVertexBuffers(this, first, buffers, offsets, strides)

    // --- [ glVertexArrayAttribBinding ] ---

    fun attribBinding(attribIndex: VertexAttrIndex, bindingIndex: Int) = gl.vertexArrayAttribBinding(this, attribIndex, bindingIndex)

    // --- [ glVertexArrayAttribFormat ] ---

    fun attribFormat(attribIndex: VertexAttrIndex, size: Int, type: VertexAttrType, normalized: Boolean, relativeOffset: Int) = gl.vertexArrayAttribFormat(this, attribIndex, size, type, normalized, relativeOffset)

    // --- [ glVertexArrayAttribIFormat ] ---

    fun attribIFormat(attribIndex: VertexAttrIndex, size: VertexAttrSize, type: VertexAttrType, relativeOffset: Int) = gl.vertexArrayAttribIFormat(this, attribIndex, size, type, relativeOffset)

    // --- [ glVertexArrayAttribLFormat ] ---

    fun attribLFormat(attribIndex: VertexAttrIndex, size: VertexAttrSize, type: VertexAttrType, relativeOffset: Int) = gl.vertexArrayAttribLFormat(this, attribIndex, size, type, relativeOffset)

    // --- [ glGetVertexArrayiv ] ---

    val elementBuffer: GlBuffer
            get() = gl.getVertexArrayElementBuffer(this)


                    companion object {

        // --- [ glCreateVertexArrays ] ---
        fun create(): GlVertexArray = gl.createVertexArrays()

        fun gen(): GlVertexArray = GlVertexArray(GL30C.glGenVertexArrays())
    }
}