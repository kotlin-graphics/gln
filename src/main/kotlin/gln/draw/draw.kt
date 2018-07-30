package gln.draw

import glm_.BYTES
import glm_.buffer.cap
import glm_.size
import org.lwjgl.opengl.*
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL32.*
import org.lwjgl.opengl.GL40.GL_PATCHES
import java.nio.ByteBuffer
import java.nio.IntBuffer


enum class DrawMode(val i: Int) {
    points(GL_POINTS),
    lineStrip(GL_LINE_STRIP),
    lineLoop(GL_LINE_LOOP),
    lines(GL_LINES),
    lineStripAdjacency(GL_LINE_STRIP_ADJACENCY),
    linesAdjacency(GL_LINES_ADJACENCY),
    triangleStrip(GL_TRIANGLE_STRIP),
    triangleFan(GL_TRIANGLE_FAN),
    triangles(GL_TRIANGLES),
    triangleStripAdjacency(GL_TRIANGLE_STRIP_ADJACENCY),
    trianglesAdjacency(GL_TRIANGLES_ADJACENCY),
    patches(GL_PATCHES)
}

inline fun glDrawArrays(count: Int) = GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, count)
inline fun glDrawArrays(mode: DrawMode, count: Int) = GL11.glDrawArrays(mode.i, 0, count)

inline fun glMultiDrawArrays(first: IntArray, count: IntArray) = GL14.glMultiDrawArrays(GL11.GL_TRIANGLES, first, count)
inline fun glMultiDrawArrays(first: IntBuffer, count: IntBuffer) = GL14.glMultiDrawArrays(GL11.GL_TRIANGLES, first, count)

inline fun glDrawArraysInstanced(count: Int, primCount: Int) = glDrawArraysInstanced(DrawMode.triangles, count, primCount)
inline fun glDrawArraysInstanced(mode: DrawMode, count: Int, primCount: Int) = GL31.glDrawArraysInstanced(mode.i, 0, count, primCount)

inline fun glDrawArraysIndirect(indirect: Long) = GL40.glDrawArraysIndirect(GL11.GL_TRIANGLES, indirect)
inline fun glDrawArraysIndirect(indirect: IntArray) = GL40.glDrawArraysIndirect(GL11.GL_TRIANGLES, indirect)
inline fun glDrawArraysIndirect(indirect: IntBuffer) = GL40.glDrawArraysIndirect(GL11.GL_TRIANGLES, indirect)
inline fun glDrawArraysIndirect(indirect: ByteBuffer) = GL40.glDrawArraysIndirect(GL11.GL_TRIANGLES, indirect)

inline fun glDrawArraysInstancedBaseInstance(count: Int, primCount: Int, baseInstance: Int) = glDrawArraysInstancedBaseInstance(GL11.GL_TRIANGLES, count, primCount, baseInstance)
inline fun glDrawArraysInstancedBaseInstance(mode: Int, count: Int, primCount: Int, baseInstance: Int) = GL42.glDrawArraysInstancedBaseInstance(mode, 0, count, primCount, baseInstance)
// TODO check primcount, also stride: Int = 0
inline fun glMultiDrawArraysIndirect(indirect: ByteBuffer) = GL43.glMultiDrawArraysIndirect(GL11.GL_TRIANGLES, indirect, indirect.size / (4 * Int.BYTES), 0)
inline fun glMultiDrawArraysIndirect(mode: DrawMode, indirect: ByteBuffer) = GL43.glMultiDrawArraysIndirect(mode.i, indirect, indirect.size / (4 * Int.BYTES), 0)
inline fun glMultiDrawArraysIndirect(mode: DrawMode, indirect: ByteBuffer, primCount: Int) = GL43.glMultiDrawArraysIndirect(mode.i, indirect, primCount, 0)
inline fun glMultiDrawArraysIndirect(indirect: IntBuffer) = GL43.glMultiDrawArraysIndirect(GL11.GL_TRIANGLES, indirect, indirect.cap / 4, 0)
inline fun glMultiDrawArraysIndirect(mode: DrawMode, indirect: IntBuffer) = GL43.glMultiDrawArraysIndirect(GL11.GL_TRIANGLES, indirect, indirect.cap / 4, 0)
inline fun glMultiDrawArraysIndirect(mode: DrawMode, indirect: IntBuffer, primCount: Int) = GL43.glMultiDrawArraysIndirect(mode.i, indirect, primCount, 0)
inline fun glMultiDrawArraysIndirect(indirect: IntArray) = GL43.glMultiDrawArraysIndirect(GL11.GL_TRIANGLES, indirect, indirect.size / 4, 0)
inline fun glMultiDrawArraysIndirect(mode: DrawMode, indirect: IntArray) = GL43.glMultiDrawArraysIndirect(GL11.GL_TRIANGLES, indirect, indirect.size / 4, 0)
inline fun glMultiDrawArraysIndirect(mode: DrawMode, indirect: IntArray, primCount: Int) = GL43.glMultiDrawArraysIndirect(mode.i, indirect, primCount, 0)

inline fun glMultiDrawArraysIndirectBindlessNV(indirect: ByteBuffer, drawCount: Int, vertexBufferCount: Int) = glMultiDrawArraysIndirectBindlessNV(DrawMode.triangles, indirect, drawCount, vertexBufferCount)
inline fun glMultiDrawArraysIndirectBindlessNV(mode: DrawMode, indirect: ByteBuffer, drawCount: Int, vertexBufferCount: Int) = NVBindlessMultiDrawIndirect.glMultiDrawArraysIndirectBindlessNV(mode.i, indirect, drawCount, 0, vertexBufferCount)

inline fun glMultiDrawArraysIndirectBindlessCountNV(indirect: ByteBuffer, drawCount: Long, maxDrawCount: Int, vertexBufferCount: Int) = glMultiDrawArraysIndirectBindlessCountNV(DrawMode.triangles, indirect, drawCount, maxDrawCount, vertexBufferCount)
inline fun glMultiDrawArraysIndirectBindlessCountNV(mode: DrawMode, indirect: ByteBuffer, drawCount: Long, maxDrawCount: Int, vertexBufferCount: Int) = NVBindlessMultiDrawIndirectCount.glMultiDrawArraysIndirectBindlessCountNV(mode.i, indirect, drawCount, maxDrawCount, 0, vertexBufferCount)

inline fun glDrawElements(count: Int) = GL11.glDrawElements(GL11.GL_TRIANGLES, count, GL11.GL_UNSIGNED_INT, 0)
inline fun glDrawElements(count: Int, type: Int) = GL11.glDrawElements(GL11.GL_TRIANGLES, count, type, 0)
inline fun glDrawElements(mode: DrawMode, count: Int, type: Int) = GL11.glDrawElements(mode.i, count, type, 0)

inline fun glDrawElementsBaseVertex(count: Int, type: Int, indices_buffer_offset: Long, basevertex: Int) = GL32.glDrawElementsBaseVertex(GL11.GL_TRIANGLES, count, type, indices_buffer_offset, basevertex)
// TODO finish
inline fun glDrawElementsInstancedBaseVertex(count: Int, type: Int, primcount: Int, basevertex: Int) = GL32.glDrawElementsInstancedBaseVertex(GL11.GL_TRIANGLES, count, type, 0, primcount, basevertex)