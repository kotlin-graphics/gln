package gln.dsa

import glm_.L
import glm_.vec3.Vec3i
import gln.*
import gln.identifiers.*
import gln.program.GlPipeline
import gln.program.GlPipelines
import gln.sampler.GlSampler
import gln.sampler.GlSamplers
import gln.vertexArray.GlVertexArray
import gln.vertexArray.GlVertexArrays
import kool.adr
import kool.rem
import kool.Stack
import org.lwjgl.opengl.ARBDirectStateAccess
import org.lwjgl.opengl.GL15C
import org.lwjgl.system.MemoryUtil
import java.nio.ByteBuffer
import java.nio.IntBuffer
import java.nio.LongBuffer

object dsaARB: dsaInterface {
    /**
     * Returns {@code n} previously unused vertex array object names in {@code arrays}.
     *
     * @param arrays the buffer in which to return the created vertex array object names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateVertexArrays">Reference Page</a>
     */
    override infix fun createVertexArrays(arrays: GlVertexArrays) =
            ARBDirectStateAccess.nglCreateVertexArrays(arrays.rem, arrays.adr)

    /**
     * Returns {@code n} previously unused vertex array object names in {@code arrays}.
     *
     * @param size the size of the created vertex array object names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateVertexArrays">Reference Page</a>
     */
    override infix fun createVertexArrays(size: Int): GlVertexArrays = GlVertexArrays(size).apply(::createVertexArrays)

    /**
     * Returns {@code n} previously unused vertex array object names in {@code arrays}.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateVertexArrays">Reference Page</a>
     */
    override fun createVertexArrays(): GlVertexArray =
            GlVertexArray(Stack.intAddress { ARBDirectStateAccess.nglCreateVertexArrays(1, it) })

    // TODO vertexArray* -> vertex*?

    // --- [ glDisableVertexArrayAttrib ] ---

    /**
     * DSA version of {@link GL20C#glDisableVertexAttribArray DisableVertexAttribArray}.
     *
     * @param vaobj the vertex array object name
     * @param index the index of the generic vertex attribute to be disabled
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDisableVertexArrayAttrib">Reference Page</a>
     */
    override fun disableVertexArrayAttrib(vaobj: GlVertexArray, index: VertexAttrIndex) =
            ARBDirectStateAccess.glDisableVertexArrayAttrib(vaobj.name, index)

    // --- [ glEnableVertexArrayAttrib ] ---

    /**
     * DSA version of {@link GL20C#glEnableVertexAttribArray EnableVertexAttribArray}.
     *
     * @param vaobj the vertex array object name
     * @param index the index of the generic vertex attribute to be enabled
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glEnableVertexArrayAttrib">Reference Page</a>
     */
    override fun enableVertexArrayAttrib(vaobj: GlVertexArray, index: VertexAttrIndex) =
            ARBDirectStateAccess.glEnableVertexArrayAttrib(vaobj.name, index)

    // --- [ glVertexArrayElementBuffer ] ---

    /**
     * Binds a buffer object to the element array buffer bind point of a vertex array object.
     *
     * @param vaobj  the vertex array object name
     * @param buffer the buffer object name. If {@code buffer} is zero, any existing element array buffer binding to {@code vaobj} is removed.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexArrayElementBuffer">Reference Page</a>
     */
    override fun vertexArrayElementBuffer(vaobj: GlVertexArray, buffer: GlBuffer) =
            ARBDirectStateAccess.glVertexArrayElementBuffer(vaobj.name, buffer.name)

    // --- [ glVertexArrayVertexBuffer ] ---

    /**
     * DSA version of {@link GL43C#glBindVertexBuffer BindVertexBuffer}.
     *
     * @param vaobj        the vertex array object name
     * @param bindingIndex the index of the vertex buffer binding point to which to bind the buffer
     * @param buffer       the name of an existing buffer to bind to the vertex buffer binding point
     * @param offset       the offset of the first element of the buffer
     * @param stride       the distance between elements within the buffer
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexArrayVertexBuffer">Reference Page</a>
     */
    override fun vertexArrayVertexBuffer(vaobj: GlVertexArray, bindingIndex: Int, buffer: GlBuffer, offset: Int, stride: Int) =
            ARBDirectStateAccess.glVertexArrayVertexBuffer(vaobj.name, bindingIndex, buffer.name, offset.L, stride)

    // --- [ glVertexArrayVertexBuffers ] ---

    /**
     * DSA version of {@link GL44C#glBindVertexBuffers BindVertexBuffers}.
     *
     * @param vaobj   the vertex array object name
     * @param first   the first vertex buffer binding point
     * @param buffers an array of zeros or names of existing buffers objects
     * @param offsets an array of offses
     * @param strides an array of stride values
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexArrayVertexBuffers">Reference Page</a>
     */
    override fun vertexArrayVertexBuffers(vaobj: GlVertexArray, first: Int, buffers: GlBuffers?, offsets: LongBuffer?, strides: IntBuffer?) =
            ARBDirectStateAccess.nglVertexArrayVertexBuffers(vaobj.name, first, buffers?.rem ?: 0, buffers?.adr ?: MemoryUtil.NULL, offsets?.adr
                    ?: MemoryUtil.NULL, strides?.adr ?: MemoryUtil.NULL)

    // --- [ glVertexArrayAttribFormat ] ---

    /**
     * DSA version of {@link GL43C#glVertexAttribFormat VertexAttribFormat}.
     *
     * @param vaobj          the vertex array object name
     * @param attribIndex    the generic vertex attribute array being described
     * @param size           the number of values per vertex that are stored in the array. One of:<br><table><tr><td>1</td><td>2</td><td>3</td><td>4</td><td>{@link GL12#GL_BGRA BGRA}</td></tr></table>
     * @param type           the type of the data stored in the array
     * @param normalized     if true then integer data is normalized to the range [-1, 1] or [0, 1] if it is signed or unsigned, respectively. If false then integer data is
     *                       directly converted to floating point.
     * @param relativeOffset the offset, measured in basic machine units of the first element relative to the start of the vertex buffer binding this attribute fetches from
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexArrayAttribFormat">Reference Page</a>
     */
    override fun vertexArrayAttribFormat(vaobj: GlVertexArray, attribIndex: VertexAttrIndex, size: Int, type: VertexAttrType, normalized: Boolean, relativeOffset: Int) =
            ARBDirectStateAccess.glVertexArrayAttribFormat(vaobj.name, attribIndex, size, type.i, normalized, relativeOffset)

    // --- [ glVertexArrayAttribIFormat ] ---

    /**
     * DSA version of {@link GL43C#glVertexAttribIFormat VertexAttribIFormat}.
     *
     * @param vaobj          the vertex array object name
     * @param attribIndex    the generic vertex attribute array being described
     * @param size           the number of values per vertex that are stored in the array. One of:<br><table><tr><td>1</td><td>2</td><td>3</td><td>4</td><td>{@link GL12#GL_BGRA BGRA}</td></tr></table>
     * @param type           the type of the data stored in the array
     * @param relativeOffset the offset, measured in basic machine units of the first element relative to the start of the vertex buffer binding this attribute fetches from
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexArrayAttribIFormat">Reference Page</a>
     */
    override fun vertexArrayAttribIFormat(vaobj: GlVertexArray, attribIndex: VertexAttrIndex, size: VertexAttrSize, type: VertexAttrType, relativeOffset: Int) =
            ARBDirectStateAccess.glVertexArrayAttribIFormat(vaobj.name, attribIndex, size, type.i, relativeOffset)

    // --- [ glVertexArrayAttribLFormat ] ---

    /**
     * DSA version of {@link GL43C#glVertexAttribLFormat VertexAttribLFormat}.
     *
     * @param vaobj          the vertex array object name
     * @param attribIndex    the generic vertex attribute array being described
     * @param size           the number of values per vertex that are stored in the array. One of:<br><table><tr><td>1</td><td>2</td><td>3</td><td>4</td><td>{@link GL12#GL_BGRA BGRA}</td></tr></table>
     * @param type           the type of the data stored in the array
     * @param relativeOffset the offset, measured in basic machine units of the first element relative to the start of the vertex buffer binding this attribute fetches from
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexArrayAttribLFormat">Reference Page</a>
     */
    override fun vertexArrayAttribLFormat(vaobj: GlVertexArray, attribIndex: VertexAttrIndex, size: VertexAttrSize, type: VertexAttrType, relativeOffset: Int) =
            ARBDirectStateAccess.glVertexArrayAttribLFormat(vaobj.name, attribIndex, size, type.i, relativeOffset)

    // --- [ glVertexArrayAttribBinding ] ---

    /**
     * DSA version of {@link GL43C#glVertexAttribBinding VertexAttribBinding}.
     *
     * @param vaobj        the vertex array object name
     * @param attribIndex  the index of the attribute to associate with a vertex buffer binding
     * @param bindingIndex the index of the vertex buffer binding with which to associate the generic vertex attribute
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexArrayAttribBinding">Reference Page</a>
     */
    override fun vertexArrayAttribBinding(vaobj: GlVertexArray, attribIndex: VertexAttrIndex, bindingIndex: Int) =
            ARBDirectStateAccess.glVertexArrayAttribBinding(vaobj.name, attribIndex, bindingIndex)

    // --- [ glVertexArrayBindingDivisor ] ---

    /**
     * DSA version of {@link GL43C#glVertexBindingDivisor VertexBindingDivisor}.
     *
     * @param vaobj        the vertex array object name
     * @param bindingIndex the index of the generic vertex attribute
     * @param divisor      the number of instances that will pass between updates of the generic attribute at slot {@code index}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexArrayBindingDivisor">Reference Page</a>
     */
    override fun vertexArrayBindingDivisor(vaobj: GlVertexArray, bindingIndex: Int, divisor: Int) =
            ARBDirectStateAccess.glVertexArrayBindingDivisor(vaobj.name, bindingIndex, divisor)

    // --- [ glGetVertexArrayiv ] ---

    /**
     * Queries parameters of a vertex array object.
     *
     * @param vaobj the vertex array object name
     * @param pname the parameter to query. Must be:<br><table><tr><td>{@link GL15#GL_ELEMENT_ARRAY_BUFFER_BINDING ELEMENT_ARRAY_BUFFER_BINDING}</td></tr></table>
     * @param param the buffer in which to return the parameter values
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetVertexArray">Reference Page</a>
     */
    override fun getVertexArrayElementBuffer(vaobj: GlVertexArray): GlBuffer =
            GlBuffer(Stack.intAddress { ARBDirectStateAccess.nglGetVertexArrayiv(vaobj.name, GL15C.GL_ELEMENT_ARRAY_BUFFER_BINDING, it) })

    // --- [ glGetVertexArrayIndexediv ] ---
    // inline reified

    // --- [ glGetVertexArrayIndexed64iv ] ---
    // inline reified

    // --- [ glCreateSamplers ] ---

    /**
     * Returns {@code n} previously unused sampler names in {@code samplers}, each representing a new sampler object.
     *
     * @param samplers the buffer in which to return the created sampler object names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateSamplers">Reference Page</a>
     */
    override fun createSamplers(samplers: GlSamplers) = ARBDirectStateAccess.nglCreateSamplers(samplers.rem, samplers.adr)

    /**
     * Returns {@code n} previously unused sampler names in {@code samplers}, each representing a new sampler object.
     *
     * @param size the size of the created sampler object names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateSamplers">Reference Page</a>
     */
    override fun createSamplers(size: Int) = GlSamplers(size).apply(::createSamplers)

    /**
     * Returns {@code n} previously unused sampler names in {@code samplers}, each representing a new sampler object.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateSamplers">Reference Page</a>
     */
    override fun createSamplers(): GlSampler =
            GlSampler(Stack.intAddress { ARBDirectStateAccess.nglCreateSamplers(1, it) })

    // --- [ glCreateProgramPipelines ] ---

    /**
     * Returns {@code n} previously unused program pipeline names in {@code pipelines}, each representing a new program pipeline object.
     *
     * @param pipelines the buffer in which to return the created program pipeline names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateProgramPipelines">Reference Page</a>
     */
    override fun createProgramPipelines(pipelines: GlPipelines) =
            ARBDirectStateAccess.nglCreateProgramPipelines(pipelines.rem, pipelines.adr)

    /**
     * Returns {@code n} previously unused program pipeline names in {@code pipelines}, each representing a new program pipeline object.
     *
     * @param size the size of the created program pipeline names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateProgramPipelines">Reference Page</a>
     */
    override fun createProgramPipelines(size: Int) = GlPipelines(size).apply(::createProgramPipelines)

    /**
     * Returns {@code n} previously unused program pipeline names in {@code pipelines}, each representing a new program pipeline object.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateProgramPipelines">Reference Page</a>
     */
    override fun createProgramPipelines(): GlPipeline =
            GlPipeline(Stack.intAddress { ARBDirectStateAccess.nglCreateProgramPipelines(1, it) })

    // --- [ glCreateQueries ] ---

    /**
     * Returns {@code n} previously unused query object names in {@code ids}, each representing a new query object with the specified {@code target}.
     *
     * @param target the query target. One of:<br><table><tr><td>{@link GL15#GL_SAMPLES_PASSED SAMPLES_PASSED}</td><td>{@link GL30#GL_PRIMITIVES_GENERATED PRIMITIVES_GENERATED}</td><td>{@link GL30#GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN}</td><td>{@link GL33#GL_TIME_ELAPSED TIME_ELAPSED}</td></tr><tr><td>{@link GL33#GL_TIMESTAMP TIMESTAMP}</td><td>{@link GL33#GL_ANY_SAMPLES_PASSED ANY_SAMPLES_PASSED}</td><td>{@link GL43#GL_ANY_SAMPLES_PASSED_CONSERVATIVE ANY_SAMPLES_PASSED_CONSERVATIVE}</td></tr></table>
     * @param ids    the buffer in which to return the created query object names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateQueries">Reference Page</a>
     */
    override fun createQueries(target: QueryTarget, ids: GlQueries) =
            ARBDirectStateAccess.nglCreateQueries(target.i, ids.rem, ids.adr)

    /**
     * Returns {@code n} previously unused query object names in {@code ids}, each representing a new query object with the specified {@code target}.
     *
     * @param target the query target. One of:<br><table><tr><td>{@link GL15#GL_SAMPLES_PASSED SAMPLES_PASSED}</td><td>{@link GL30#GL_PRIMITIVES_GENERATED PRIMITIVES_GENERATED}</td><td>{@link GL30#GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN}</td><td>{@link GL33#GL_TIME_ELAPSED TIME_ELAPSED}</td></tr><tr><td>{@link GL33#GL_TIMESTAMP TIMESTAMP}</td><td>{@link GL33#GL_ANY_SAMPLES_PASSED ANY_SAMPLES_PASSED}</td><td>{@link GL43#GL_ANY_SAMPLES_PASSED_CONSERVATIVE ANY_SAMPLES_PASSED_CONSERVATIVE}</td></tr></table>
     * @param size   the size of the created query object names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateQueries">Reference Page</a>
     */
    override fun createQueries(target: QueryTarget, size: Int) = GlQueries(size).also { createQueries(target, it) }

    /**
     * Returns {@code n} previously unused query object names in {@code ids}, each representing a new query object with the specified {@code target}.
     *
     * @param target the query target. One of:<br><table><tr><td>{@link GL15#GL_SAMPLES_PASSED SAMPLES_PASSED}</td><td>{@link GL30#GL_PRIMITIVES_GENERATED PRIMITIVES_GENERATED}</td><td>{@link GL30#GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN}</td><td>{@link GL33#GL_TIME_ELAPSED TIME_ELAPSED}</td></tr><tr><td>{@link GL33#GL_TIMESTAMP TIMESTAMP}</td><td>{@link GL33#GL_ANY_SAMPLES_PASSED ANY_SAMPLES_PASSED}</td><td>{@link GL43#GL_ANY_SAMPLES_PASSED_CONSERVATIVE ANY_SAMPLES_PASSED_CONSERVATIVE}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateQueries">Reference Page</a>
     */
    override infix fun createQueries(target: QueryTarget): GlQuery =
            GlQuery(Stack.intAddress { ARBDirectStateAccess.nglCreateQueries(target.i, 1, it) })

    /**
     * Obtains sub-regions of a texture image from a texture object.
     *
     * @param texture the source texture object name
     * @param level   the level-of-detail number
     * @param offset  the position of the subregion
     * @param size    the subregion size
     * @param format  the pixel format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td><td>{@link GL11#GL_LUMINANCE LUMINANCE}</td><td>{@link GL11#GL_LUMINANCE_ALPHA LUMINANCE_ALPHA}</td></tr></table>
     * @param type    the pixel type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr><tr><td>{@link GL11#GL_BITMAP BITMAP}</td></tr></table>
     * @param pixels  the buffer in which to place the returned data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTextureSubImage">Reference Page</a>
     */
    override fun getTextureSubImage(texture: GlTexture, level: Int, offset: Vec3i, size: Vec3i, format: TextureFormat3, type: TextureType2, pixels: ByteBuffer) =
            ARBDirectStateAccess.nglTextureSubImage3D(texture.name, level, offset.x, offset.y, offset.z, size.x, size.y, size.z, format.i, type.i, pixels.adr)

    /**
     * DSA version of {@link GL13C#glGetCompressedTexImage GetCompressedTexImage}.
     *
     * @param texture the texture name
     * @param level   the level-of-detail number. Level 0 is the base image level. Level n is the nth mipmap reduction image.
     * @param pixels  a buffer in which to return the compressed texture image
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetCompressedTextureImage">Reference Page</a>
     */
    override fun getCompressedTexImage(texture: GlTexture, level: Int, pixels: ByteBuffer) =
            ARBDirectStateAccess.nglGetCompressedTextureImage(texture.name, level, pixels.rem, pixels.adr)

    /**
     * Obtains a sub-region of a compressed texture image.
     *
     * @param texture the source texture object name
     * @param level   the level-of-detail number
     * @param offset  the position of the subregion
     * @param size    the subregion size
     * @param pixels  the buffer in which to place the returned data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetCompressedTextureSubImage">Reference Page</a>
     */
    override fun getCompressedTextureSubImage(texture: GlTexture, level: Int, offset: Vec3i, size: Vec3i, pixels: ByteBuffer): Nothing = TODO()
    //ARBDirectStateAccess.nglCompressedTextureSubImage3D(texture.name, level, offset.x, offset.y, offset.z, size.x, size.y, size.z, texture.getImageFormatCompatibilityType(TextureTarget._3D).i, pixels.rem, pixels.adr)

    /**
     * Robust version of {@link GL11C#glGetTexImage GetTexImage}
     *
     * @param tex    the texture (or texture face) to be obtained. One of:<br><table><tr><td>{@link GL11#GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link GL11#GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td></tr><tr><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_X TEXTURE_CUBE_MAP_POSITIVE_X}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_X TEXTURE_CUBE_MAP_NEGATIVE_X}</td></tr><tr><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Y TEXTURE_CUBE_MAP_POSITIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Y TEXTURE_CUBE_MAP_NEGATIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Z TEXTURE_CUBE_MAP_POSITIVE_Z}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Z TEXTURE_CUBE_MAP_NEGATIVE_Z}</td></tr></table>
     * @param level  the level-of-detail number
     * @param format the pixel format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
     * @param type   the pixel type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
     * @param img    a buffer in which to place the returned data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetnTexImage">Reference Page</a>
     */
    override fun getnTexImage(tex: GlTexture, level: Int, format: TextureFormat2, type: TextureType2, img: ByteBuffer) =
            ARBDirectStateAccess.nglGetTextureImage(tex.name, level, format.i, type.i, img.rem, img.adr)
}