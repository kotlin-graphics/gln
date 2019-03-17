@file:Suppress("NOTHING_TO_INLINE")

package gln.vertexArray

import glm_.L
import gln.glf.VertexAttribute
import gln.glf.VertexLayout
import kool.IntBuffer
import kool.adr
import kool.get
import kool.rem
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL30C
import java.nio.IntBuffer
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

    companion object {
        fun gen(): GlVertexArray = GlVertexArray(GL30C.glGenVertexArrays())
    }
}