package gln.dsa

import glm_.vec3.Vec3i
import gln.*
import gln.identifiers.*
import gln.program.GlPipeline
import gln.program.GlPipelines
import gln.sampler.GlSampler
import gln.sampler.GlSamplers
import gln.vertexArray.GlVertexArray
import gln.vertexArray.GlVertexArrays
import java.nio.ByteBuffer
import java.nio.IntBuffer
import java.nio.LongBuffer

private val impl: dsaInterface
    get() = gl.state.dsaClass

interface dsai: dsaInterface {
    override infix fun createVertexArrays(arrays: GlVertexArrays) = impl.createVertexArrays(arrays)

    override infix fun createVertexArrays(size: Int): GlVertexArrays = impl.createVertexArrays(size)

    override fun createVertexArrays(): GlVertexArray = impl.createVertexArrays()

    override fun disableVertexArrayAttrib(vaobj: GlVertexArray, index: VertexAttrIndex) = impl.disableVertexArrayAttrib(vaobj, index)

    override fun enableVertexArrayAttrib(vaobj: GlVertexArray, index: VertexAttrIndex) = impl.enableVertexArrayAttrib(vaobj, index)

    override fun vertexArrayElementBuffer(vaobj: GlVertexArray, buffer: GlBuffer) = impl.vertexArrayElementBuffer(vaobj, buffer)

    override fun vertexArrayVertexBuffer(vaobj: GlVertexArray, bindingIndex: Int, buffer: GlBuffer, offset: Int, stride: Int) = impl.vertexArrayVertexBuffer(vaobj, bindingIndex, buffer, offset, stride)

    override fun vertexArrayVertexBuffers(vaobj: GlVertexArray, first: Int, buffers: GlBuffers?, offsets: LongBuffer?, strides: IntBuffer?) = impl.vertexArrayVertexBuffers(vaobj, first, buffers, offsets, strides)

    override fun vertexArrayAttribFormat(vaobj: GlVertexArray, attribIndex: VertexAttrIndex, size: Int, type: VertexAttrType, normalized: Boolean, relativeOffset: Int) = impl.vertexArrayAttribFormat(vaobj, attribIndex, size, type, normalized, relativeOffset)

    override fun vertexArrayAttribIFormat(vaobj: GlVertexArray, attribIndex: VertexAttrIndex, size: VertexAttrSize, type: VertexAttrType, relativeOffset: Int) = impl.vertexArrayAttribIFormat(vaobj, attribIndex, size, type, relativeOffset)

    override fun vertexArrayAttribLFormat(vaobj: GlVertexArray, attribIndex: VertexAttrIndex, size: VertexAttrSize, type: VertexAttrType, relativeOffset: Int) = impl.vertexArrayAttribLFormat(vaobj, attribIndex, size, type, relativeOffset)

    override fun vertexArrayAttribBinding(vaobj: GlVertexArray, attribIndex: VertexAttrIndex, bindingIndex: Int) = impl.vertexArrayAttribBinding(vaobj, attribIndex, bindingIndex)

    override fun vertexArrayBindingDivisor(vaobj: GlVertexArray, bindingIndex: Int, divisor: Int) = impl.vertexArrayBindingDivisor(vaobj, bindingIndex, divisor)

    override fun getVertexArrayElementBuffer(vaobj: GlVertexArray): GlBuffer = impl.getVertexArrayElementBuffer(vaobj)

    override fun createSamplers(samplers: GlSamplers) = impl.createSamplers(samplers)

    override fun createSamplers(size: Int): GlSamplers = impl.createSamplers(size)

    override fun createSamplers(): GlSampler = impl.createSamplers()

    override fun createProgramPipelines(pipelines: GlPipelines) = impl.createProgramPipelines(pipelines)

    override fun createProgramPipelines(size: Int): GlPipelines = impl.createProgramPipelines(size)

    override fun createProgramPipelines(): GlPipeline = impl.createProgramPipelines()

    override fun createQueries(target: QueryTarget, ids: GlQueries) = impl.createQueries(target, ids)

    override fun createQueries(target: QueryTarget, size: Int): GlQueries = impl.createQueries(target, size)

    override fun createQueries(target: QueryTarget): GlQuery = impl.createQueries(target)

    override fun getTextureSubImage(texture: GlTexture, level: Int, offset: Vec3i, size: Vec3i, format: TextureFormat3, type: TextureType2, pixels: ByteBuffer) = impl.getTextureSubImage(texture, level, offset, size, format, type, pixels)

    override fun getCompressedTexImage(texture: GlTexture, level: Int, pixels: ByteBuffer) = impl.getCompressedTexImage(texture, level, pixels)

    override fun getCompressedTextureSubImage(texture: GlTexture, level: Int, offset: Vec3i, size: Vec3i, pixels: ByteBuffer) = impl.getCompressedTextureSubImage(texture, level, offset, size, pixels)

    override fun getnTexImage(tex: GlTexture, level: Int, format: TextureFormat2, type: TextureType2, img: ByteBuffer) = impl.getnTexImage(tex, level, format, type, img)
}



interface dsaInterface {
    infix fun createVertexArrays(arrays: GlVertexArrays)
    infix fun createVertexArrays(size: Int): GlVertexArrays
    fun createVertexArrays(): GlVertexArray
    fun disableVertexArrayAttrib(vaobj: GlVertexArray, index: VertexAttrIndex)
    fun enableVertexArrayAttrib(vaobj: GlVertexArray, index: VertexAttrIndex)
    fun vertexArrayElementBuffer(vaobj: GlVertexArray, buffer: GlBuffer)
    fun vertexArrayVertexBuffer(vaobj: GlVertexArray, bindingIndex: Int, buffer: GlBuffer, offset: Int, stride: Int)
    fun vertexArrayVertexBuffers(vaobj: GlVertexArray, first: Int, buffers: GlBuffers? = null, offsets: LongBuffer? = null, strides: IntBuffer? = null)
    fun vertexArrayAttribIFormat(vaobj: GlVertexArray, attribIndex: VertexAttrIndex, size: VertexAttrSize, type: VertexAttrType, relativeOffset: Int)
    fun vertexArrayAttribFormat(vaobj: GlVertexArray, attribIndex: VertexAttrIndex, size: Int, type: VertexAttrType, normalized: Boolean, relativeOffset: Int)
    fun vertexArrayAttribLFormat(vaobj: GlVertexArray, attribIndex: VertexAttrIndex, size: VertexAttrSize, type: VertexAttrType, relativeOffset: Int)
    fun vertexArrayAttribBinding(vaobj: GlVertexArray, attribIndex: VertexAttrIndex, bindingIndex: Int)
    fun vertexArrayBindingDivisor(vaobj: GlVertexArray, bindingIndex: Int, divisor: Int)
    fun getVertexArrayElementBuffer(vaobj: GlVertexArray): GlBuffer
    fun createSamplers(samplers: GlSamplers)
    fun createSamplers(size: Int): GlSamplers
    fun createSamplers(): GlSampler
    fun createProgramPipelines(pipelines: GlPipelines)
    fun createProgramPipelines(size: Int): GlPipelines
    fun createProgramPipelines(): GlPipeline
    fun createQueries(target: QueryTarget, ids: GlQueries)
    fun createQueries(target: QueryTarget, size: Int): GlQueries
    infix fun createQueries(target: QueryTarget): GlQuery
    fun getTextureSubImage(texture: GlTexture, level: Int, offset: Vec3i, size: Vec3i, format: TextureFormat3, type: TextureType2, pixels: ByteBuffer)
    fun getCompressedTexImage(texture: GlTexture, level: Int, pixels: ByteBuffer)
    fun getCompressedTextureSubImage(texture: GlTexture, level: Int, offset: Vec3i, size: Vec3i, pixels: ByteBuffer)
    fun getnTexImage(tex: GlTexture, level: Int, format: TextureFormat2, type: TextureType2, img: ByteBuffer)
}