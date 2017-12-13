package gln.vertexArray

import glm_.L
import gln.get
import gln.glf.VertexAttribute
import gln.glf.VertexLayout
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30
import java.nio.IntBuffer
import kotlin.properties.Delegates


var vertexArrayName: IntBuffer by Delegates.notNull()


inline fun glBindVertexArray(vertexArray: Enum<*>) = GL30.glBindVertexArray(vertexArrayName[vertexArray])
inline fun glBindVertexArray(vertexArray: IntBuffer) = GL30.glBindVertexArray(vertexArray[0])

inline fun glBindVertexArray() = GL30.glBindVertexArray(0)


inline fun glVertexAttribPointer(index: Int, size: Int, type: Int, normalized: Boolean, stride: Int, pointer: Int)
        = GL20.glVertexAttribPointer(index, size, type, normalized, stride, pointer.L)

inline fun glVertexAttribPointer(layout: VertexLayout) = glVertexAttribPointer(layout[0])
inline fun glEnableVertexAttribArray(layout: VertexLayout) = glEnableVertexAttribArray(layout[0])
inline fun glDisableVertexAttribArray(layout: VertexLayout) = glDisableVertexAttribArray(layout[0])
inline fun glVertexAttribPointer(attribute: VertexAttribute) = with(attribute) { GL20.glVertexAttribPointer(index, size, type, normalized, interleavedStride, pointer.L) }
inline fun glEnableVertexAttribArray(attribute: VertexAttribute) = GL20.glEnableVertexAttribArray(attribute.index)
inline fun glDisableVertexAttribArray(attribute: VertexAttribute) = GL20.glDisableVertexAttribArray(attribute.index)