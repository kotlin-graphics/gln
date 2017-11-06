package gln.vertexArray

import glm_.L
import glm_.i
import gln.get
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