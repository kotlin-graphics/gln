@file:Suppress("NOTHING_TO_INLINE")

package gln.draw

import glm_.BYTES
import glm_.size
import kool.rem
import org.lwjgl.opengl.*
import java.nio.ByteBuffer
import java.nio.IntBuffer


inline fun glDrawArrays(count: Int) = GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, count)
inline fun glDrawArrays(mode: Int, count: Int) = GL11.glDrawArrays(mode, 0, count)

inline fun glMultiDrawArrays(first: IntArray, count: IntArray) = GL14.glMultiDrawArrays(GL11.GL_TRIANGLES, first, count)
inline fun glMultiDrawArrays(first: IntBuffer, count: IntBuffer) = GL14.glMultiDrawArrays(GL11.GL_TRIANGLES, first, count)

inline fun glDrawArraysInstanced(count: Int, primCount: Int) = glDrawArraysInstanced(GL11.GL_TRIANGLES, count, primCount)
inline fun glDrawArraysInstanced(mode: Int, count: Int, primCount: Int) = GL31.glDrawArraysInstanced(mode, 0, count, primCount)

inline fun glDrawArraysIndirect(indirect: Long) = GL40.glDrawArraysIndirect(GL11.GL_TRIANGLES, indirect)
inline fun glDrawArraysIndirect(indirect: IntArray) = GL40.glDrawArraysIndirect(GL11.GL_TRIANGLES, indirect)
inline fun glDrawArraysIndirect(indirect: IntBuffer) = GL40.glDrawArraysIndirect(GL11.GL_TRIANGLES, indirect)
inline fun glDrawArraysIndirect(indirect: ByteBuffer) = GL40.glDrawArraysIndirect(GL11.GL_TRIANGLES, indirect)

inline fun glDrawArraysInstancedBaseInstance(count: Int, primCount: Int, baseInstance: Int) = glDrawArraysInstancedBaseInstance(GL11.GL_TRIANGLES, count, primCount, baseInstance)
inline fun glDrawArraysInstancedBaseInstance(mode: Int, count: Int, primCount: Int, baseInstance: Int) = GL42.glDrawArraysInstancedBaseInstance(mode, 0, count, primCount, baseInstance)
// TODO check primcount, also stride: Int = 0
inline fun glMultiDrawArraysIndirect(indirect: ByteBuffer) = GL43.glMultiDrawArraysIndirect(GL11.GL_TRIANGLES, indirect, indirect.size / (4 * Int.BYTES), 0)

inline fun glMultiDrawArraysIndirect(mode: Int, indirect: ByteBuffer) = GL43.glMultiDrawArraysIndirect(mode, indirect, indirect.size / (4 * Int.BYTES), 0)
inline fun glMultiDrawArraysIndirect(mode: Int, indirect: ByteBuffer, primCount: Int) = GL43.glMultiDrawArraysIndirect(mode, indirect, primCount, 0)
inline fun glMultiDrawArraysIndirect(indirect: IntBuffer) = GL43.glMultiDrawArraysIndirect(GL11.GL_TRIANGLES, indirect, indirect.rem / DrawArraysIndirectCommand_LENGTH, 0)
inline fun glMultiDrawArraysIndirect(mode: Int, indirect: IntBuffer) = GL43.glMultiDrawArraysIndirect(mode, indirect, indirect.rem / DrawArraysIndirectCommand_LENGTH, 0)
inline fun glMultiDrawArraysIndirect(mode: Int, indirect: IntBuffer, primCount: Int) = GL43.glMultiDrawArraysIndirect(mode, indirect, primCount, 0)
inline fun glMultiDrawArraysIndirect(indirect: IntArray) = GL43.glMultiDrawArraysIndirect(GL11.GL_TRIANGLES, indirect, indirect.size / DrawArraysIndirectCommand_LENGTH, 0)
inline fun glMultiDrawArraysIndirect(mode: Int, indirect: IntArray) = GL43.glMultiDrawArraysIndirect(mode, indirect, indirect.size / DrawArraysIndirectCommand_LENGTH, 0)
inline fun glMultiDrawArraysIndirect(mode: Int, indirect: IntArray, primCount: Int) = GL43.glMultiDrawArraysIndirect(mode, indirect, primCount, 0)

inline fun glMultiDrawArraysIndirectBindlessNV(indirect: ByteBuffer, drawCount: Int, vertexBufferCount: Int) = glMultiDrawArraysIndirectBindlessNV(GL11.GL_TRIANGLES, indirect, drawCount, vertexBufferCount)
inline fun glMultiDrawArraysIndirectBindlessNV(mode: Int, indirect: ByteBuffer, drawCount: Int, vertexBufferCount: Int) = NVBindlessMultiDrawIndirect.glMultiDrawArraysIndirectBindlessNV(mode, indirect, drawCount, 0, vertexBufferCount)

inline fun glMultiDrawArraysIndirectBindlessCountNV(indirect: ByteBuffer, drawCount: Long, maxDrawCount: Int, vertexBufferCount: Int) = glMultiDrawArraysIndirectBindlessCountNV(GL11.GL_TRIANGLES, indirect, drawCount, maxDrawCount, vertexBufferCount)
inline fun glMultiDrawArraysIndirectBindlessCountNV(mode: Int, indirect: ByteBuffer, drawCount: Long, maxDrawCount: Int, vertexBufferCount: Int) = NVBindlessMultiDrawIndirectCount.glMultiDrawArraysIndirectBindlessCountNV(mode, indirect, drawCount, maxDrawCount, 0, vertexBufferCount)

inline fun glDrawElements(count: Int) = GL11.glDrawElements(GL11.GL_TRIANGLES, count, GL11.GL_UNSIGNED_INT, 0)
inline fun glDrawElements(count: Int, type: Int) = GL11.glDrawElements(GL11.GL_TRIANGLES, count, type, 0)
inline fun glDrawElements(mode: Int, count: Int, type: Int) = GL11.glDrawElements(mode, count, type, 0)

inline fun glDrawElementsBaseVertex(count: Int, type: Int, indices_buffer_offset: Long, basevertex: Int) = GL32.glDrawElementsBaseVertex(GL11.GL_TRIANGLES, count, type, indices_buffer_offset, basevertex)
// TODO finish
inline fun glDrawElementsInstancedBaseVertex(count: Int, type: Int, primcount: Int, basevertex: Int) = GL32.glDrawElementsInstancedBaseVertex(GL11.GL_TRIANGLES, count, type, 0, primcount, basevertex)


/**
 *     typedef  struct {
 *          uint  count;
 *          uint  instanceCount;
 *          uint  first;
 *          uint  baseInstance;
 *     } DrawArraysIndirectCommand;
 */
val DrawArraysIndirectCommand_LENGTH = 4 // TODO property of inline classe