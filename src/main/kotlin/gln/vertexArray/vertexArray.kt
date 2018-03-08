package gln.vertexArray

import glm_.L
import gln.get
import gln.glf.VertexAttribute
import gln.glf.VertexLayout
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30
import java.nio.IntBuffer
import kotlin.properties.Delegates
import kotlin.reflect.KMutableProperty0


var vertexArrayName: IntBuffer by Delegates.notNull()

inline fun glGenVertexArray(array: KMutableProperty0<Int>) = array.set(GL30.glGenVertexArrays())
inline fun glGenVertexArray() = GL30.glGenVertexArrays()

inline fun glBindVertexArray(vertexArray: Enum<*>) = GL30.glBindVertexArray(vertexArrayName[vertexArray])
inline fun glBindVertexArray(vertexArray: IntBuffer) = GL30.glBindVertexArray(vertexArray[0])

inline fun glBindVertexArray() = GL30.glBindVertexArray(0)


inline fun glVertexAttribPointer(index: Int, size: Int, type: Int, normalized: Boolean, stride: Int, pointer: Int)
        = GL20.glVertexAttribPointer(index, size, type, normalized, stride, pointer.L)

inline fun glVertexAttribPointer(layout: VertexLayout) = layout.attributes.forEach(::glVertexAttribPointer)
inline fun glEnableVertexAttribArray(layout: VertexLayout) = layout.attributes.forEach(::glEnableVertexAttribArray)
inline fun glDisableVertexAttribArray(layout: VertexLayout) = layout.attributes.forEach(::glDisableVertexAttribArray)
inline fun glVertexAttribPointer(attribute: VertexAttribute) = with(attribute) { GL20.glVertexAttribPointer(index, size, type, normalized, interleavedStride, pointer.L) }
inline fun glEnableVertexAttribArray(attribute: VertexAttribute) = GL20.glEnableVertexAttribArray(attribute.index)
inline fun glDisableVertexAttribArray(attribute: VertexAttribute) = GL20.glDisableVertexAttribArray(attribute.index)