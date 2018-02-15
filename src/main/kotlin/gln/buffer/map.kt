package gln.buffer

import glm_.L
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL45
import java.nio.ByteBuffer


fun glMapBufferRange(target: Int, length: Long, access: Int) = GL30.glMapBufferRange(target, 0, length.L, access)
fun glMapBufferRange(target: Int, length: Int, access: Int) = GL30.glMapBufferRange(target, 0, length.L, access)
fun glMapBufferRange(target: Int, offset: Int, length: Int, access: Int) = GL30.glMapBufferRange(target, offset.L, length.L, access)

fun ByteBuffer.glMapBufferRange(target: Int, length: Long, access: Int) = GL30.glMapBufferRange(target, 0, length.L, access, this)
fun ByteBuffer.glMapBufferRange(target: Int, length: Int, access: Int) = GL30.glMapBufferRange(target, 0, length.L, access, this)
fun ByteBuffer.glMapBufferRange(target: Int, offset: Int, length: Int, access: Int) = GL30.glMapBufferRange(target, offset.L, length.L, access, this)

fun ByteBuffer.map(target: Int, access: Int) = GL15.glMapBuffer(target, access, this)
fun ByteBuffer.map(target: Int, access: Int, length: Int) = GL15.glMapBuffer(target, access, length.L, this)
fun ByteBuffer.map(target: Int, access: Int, length: Long) = GL15.glMapBuffer(target, access, length, this)

fun ByteBuffer.mapNamed(buffer: Int, access: Int) = GL45.glMapNamedBuffer(buffer, access, this)
fun ByteBuffer.mapNamed(buffer: Int, access: Int, length: Int) = GL45.glMapNamedBuffer(buffer, access, length.L, this)
fun ByteBuffer.mapNamed(buffer: Int, access: Int, length: Long) = GL45.glMapNamedBuffer(buffer, access, length, this)

fun glMapNamedBufferRange(buffer: Int, length: Long, access: Int) = GL45.glMapNamedBufferRange(buffer, 0, length, access)
fun glMapNamedBufferRange(buffer: Int, length: Int, access: Int) = GL45.glMapNamedBufferRange(buffer, 0, length.L, access)
fun glMapNamedBufferRange(buffer: Int, offset: Int, length: Int, access: Int) = GL45.glMapNamedBufferRange(buffer, offset.L, length.L, access)
//fun ByteBuffer.mapNamedRange(buffer: Int, offset: Int, length: Long, access: Int) = GL45.glMapNamedBufferRange(buffer, offset.L, length, this)