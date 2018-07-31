package gln

import ab.appBuffer
import glm_.BYTES
import glm_.bool
import glm_.buffer.adr
import glm_.vec2.Vec2
import glm_.vec4.Vec4
import glm_.vec4.Vec4bool
import glm_.vec4.Vec4i
import org.lwjgl.opengl.*
import org.lwjgl.system.MemoryUtil

object glGet {

    inline fun int(pName: Int) = GL11C.glGetInteger(pName)
    inline fun int(pName: Int, index: Int) = GL30C.glGetIntegeri(pName, index)
    inline fun long(pName: Int) = GL32C.glGetInteger64(pName)
    inline fun long(pName: Int, index: Int) = GL32C.glGetInteger64i(pName, index)
    inline fun pointer(pName: Int): Pointer {
        val pointer = appBuffer.pointerBuffer
        GL11C.nglGetPointerv(pName, MemoryUtil.memAddress(pointer))
        return Pointer(pointer[0])
    }

    inline fun float(pName: Int) = GL11C.glGetFloat(pName)
    inline fun bool(pName: Int) = GL11C.glGetInteger(pName).bool
    inline fun vec4bool(pName: Int): Vec4bool {
        val bools = appBuffer.intBuffer(4)
        GL11C.glGetIntegerv(pName, bools)
        return Vec4bool(bools)
    }

    inline fun vec2(pName: Int): Vec2 {
        val floats = appBuffer.floatArray(Vec2.length)
        GL11C.nglGetFloatv(pName, floats)
        return Vec2(MemoryUtil.memGetFloat(floats), MemoryUtil.memGetFloat(floats + Float.BYTES))
    }

    inline fun vec4(pName: Int): Vec4 {
        val floats = appBuffer.floatBuffer(Vec4.length)
        GL11C.nglGetFloatv(pName, floats.adr)
        return Vec4(floats)
    }

    inline fun vec4i(pName: Int): Vec4i {
        val ints = appBuffer.intArray(4)
        GL30.nglGetIntegerv(pName, ints)
        return Vec4i(MemoryUtil.memGetInt(ints), MemoryUtil.memGetInt(ints + Int.BYTES),
                MemoryUtil.memGetInt(ints + Int.BYTES * 2), MemoryUtil.memGetInt(ints + Int.BYTES * 3))
    }

    val activeTexture: Int
        get() = int(GL13.GL_ACTIVE_TEXTURE)

    val arrayBufferBinding: Int
        get() = int(GL15.GL_ARRAY_BUFFER_BINDING)

    val blend: Boolean
        get() = bool(GL11C.GL_BLEND)

    val blendColor: Vec4
        get() = vec4(ARBImaging.GL_BLEND_COLOR)

    val blendDstAlpha: BlendingFactorDest
        get() = BlendingFactorDest(int(GL14.GL_BLEND_DST_ALPHA))

    val blendDstRgb: BlendingFactorDest
        get() = BlendingFactorDest(int(GL14.GL_BLEND_DST_RGB))

    val blendEquationRgb: BlendEquationMode
        get() = BlendEquationMode(int(GL20.GL_BLEND_EQUATION_RGB))

    val blendEquationAlpha: BlendEquationMode
        get() = BlendEquationMode(int(GL20.GL_BLEND_EQUATION_ALPHA))

    val blendSrcAlpha: BlendingFactorSrc
        get() = BlendingFactorSrc(int(GL14.GL_BLEND_SRC_ALPHA))

    val blendSrcRgb: BlendingFactorSrc
        get() = BlendingFactorSrc(int(GL14.GL_BLEND_SRC_RGB))

    val colorClearValue: Vec4
        get() = vec4(GL11.GL_COLOR_CLEAR_VALUE)

    val colorLogicOp: LogicOp
        get() = LogicOp(int(GL11.GL_COLOR_LOGIC_OP))

    val colorWritemask: Vec4bool
        get() = vec4bool(GL11.GL_COLOR_WRITEMASK)

    val dispatchIndirectBufferBinding: Int
        get() = int(GL43.GL_DISPATCH_INDIRECT_BUFFER_BINDING)

    val debugGroupStackDepth: Int
        get() = int(GL43.GL_DEBUG_GROUP_STACK_DEPTH)

    val cullFace: CullFaceMode
        get() = CullFaceMode(int(GL11.GL_CULL_FACE))

    val currentProgram: GLprogram
        get() = GLprogram(GL20.GL_CURRENT_PROGRAM)

    val depthClearValue: Float
        get() = float(GL20.GL_DEPTH_CLEAR_VALUE)

    val depthFunc: CompareFunction
        get() = CompareFunction(int(GL20.GL_DEPTH_FUNC))

    val depthRange: Vec2
        get() = vec2(GL11.GL_DEPTH_RANGE)

    val depthTest: Boolean
        get() = bool(GL11.GL_DEPTH_TEST)

    val depthWritemask: Boolean
        get() = bool(GL11.GL_DEPTH_WRITEMASK)

    val dither: Boolean
        get() = bool(GL11.GL_DITHER)

    val doublebuffer: Boolean
        get() = bool(GL11.GL_DOUBLEBUFFER)

    val drawBuffer: BufferMode
        get() = BufferMode(int(GL11.GL_DRAW_BUFFER))

    fun drawBuffer(i: Int): BufferMode = BufferMode(int(GL20.GL_DRAW_BUFFER0 + i))

    val drawFramebufferBinding: GLframebuffer
        get() = GLframebuffer(int(GL30.GL_DRAW_FRAMEBUFFER_BINDING))

    val readFramebufferBinding: GLframebuffer
        get() = GLframebuffer(int(GL30.GL_READ_FRAMEBUFFER_BINDING))

    val elementArrayBufferBinding: GLbuffer
        get() = GLbuffer(int(GL30.GL_ELEMENT_ARRAY_BUFFER_BINDING))

    val fragmentShaderDerivativeHint: HintMode
        get() = HintMode(int(GL30.GL_FRAGMENT_SHADER_DERIVATIVE_HINT))

    val lineSmooth: Boolean
        get() = bool(GL11.GL_LINE_SMOOTH)

    val lineSmoothHint: HintMode
        get() = HintMode(int(GL11.GL_LINE_SMOOTH_HINT))

    val lineWidth: Float
        get() = float(GL11.GL_LINE_WIDTH)

    val logicOpMode: LogicOp
        get() = LogicOp(int(GL11.GL_LOGIC_OP_MODE))

    val packAlignment: Int
        get() = int(GL11.GL_PACK_ALIGNMENT)

    val packImageHeight: Int
        get() = int(GL12.GL_PACK_IMAGE_HEIGHT)

    val packLsbFirst: Boolean
        get() = bool(GL11.GL_PACK_LSB_FIRST)

    val packRowLength: Int
        get() = int(GL11.GL_PACK_ROW_LENGTH)

    val packSkipImages: Int
        get() = int(GL12.GL_PACK_SKIP_IMAGES)

    val packSkipPixels: Int
        get() = int(GL11.GL_PACK_SKIP_PIXELS)

    val packSkipRows: Int
        get() = int(GL11.GL_PACK_SKIP_ROWS)

    val packSwapBytes: Boolean
        get() = bool(GL11.GL_PACK_SWAP_BYTES)

    val pixelPackBufferBinding: GLbuffer
        get() = GLbuffer(int(GL21.GL_PIXEL_PACK_BUFFER_BINDING))

    val pixelUnpackBufferBinding: GLbuffer
        get() = GLbuffer(int(GL21.GL_PIXEL_UNPACK_BUFFER_BINDING))

    val pointFadeThresholdSize: Float
        get() = float(GL14.GL_POINT_FADE_THRESHOLD_SIZE)

    val primitiveRestartIndex: Int
        get() = int(GL31.GL_PRIMITIVE_RESTART_INDEX)

    val programPipelineBinding: Int
        get() = int(GL41.GL_PROGRAM_PIPELINE_BINDING)

    val programPointSize: Int
        get() = int(GL32.GL_PROGRAM_POINT_SIZE)

    val provokingVertex: ProvokeMode
        get() = ProvokeMode(int(GL32.GL_PROVOKING_VERTEX))

    val pointSize: Float
        get() = float(GL11.GL_POINT_SIZE)

    val polygonOffsetFactor: Float
        get() = float(GL11.GL_POLYGON_OFFSET_FACTOR)

    val polygonOffsetUnits: Float
        get() = float(GL11.GL_POLYGON_OFFSET_UNITS)

    val polygonOffsetFill: Boolean
        get() = bool(GL11.GL_POLYGON_OFFSET_FILL)

    val polygonOffsetLine: Boolean
        get() = bool(GL11.GL_POLYGON_OFFSET_LINE)

    val polygonOffsetPoint: Boolean
        get() = bool(GL11.GL_POLYGON_OFFSET_POINT)

    val polygonSmooth: Boolean
        get() = bool(GL11.GL_POLYGON_SMOOTH)

    val polygonSmoothHint: HintMode
        get() = HintMode(int(GL11.GL_POLYGON_SMOOTH))

    val readBuffer: BufferMode
        get() = BufferMode(int(GL11.GL_READ_BUFFER))

    val renderbufferBinding: GLrenderbuffer
        get() = GLrenderbuffer(int(GL30.GL_RENDERBUFFER_BINDING))

    val sampleBuffers: Int
        get() = int(GL13.GL_SAMPLE_BUFFERS)

    val sampleCoverageValue: Float
        get() = float(GL13.GL_SAMPLE_COVERAGE_VALUE)

    val sampleCoverageInvert: Boolean
        get() = bool(GL13.GL_SAMPLE_COVERAGE_INVERT)

    fun sampleMaskValue(index: Int): Int {
        val v = appBuffer.int
        GL30.nglGetIntegeri_v(GL32.GL_SAMPLE_MASK_VALUE, index, v)
        return MemoryUtil.memGetInt(v)
    }

    val samplerBinding: GLsampler
        get() = GLsampler(int(GL33.GL_SAMPLER_BINDING))

    val samples: Int
        get() = int(GL13.GL_SAMPLES)

    val scissorBox: Vec4i
        get() = vec4i(GL11.GL_SCISSOR_BOX)

    val scissorTest: Boolean
        get() = bool(GL11.GL_SCISSOR_TEST)

    val shaderCompiler: Boolean
        get() = bool(GL41.GL_SHADER_COMPILER)


    val shaderStorageBufferBinding: GLbuffer
        get() = GLbuffer(int(GL43.GL_SHADER_STORAGE_BUFFER_BINDING))

    fun shaderStorageBufferBinding(index: Int): GLbuffer = GLbuffer(int(GL43.GL_SHADER_STORAGE_BUFFER_BINDING, index))

    fun shaderStorageBufferStart(index: Int): Long = long(GL43.GL_SHADER_STORAGE_BUFFER_START, index)

    fun shaderStorageBufferSize(index: Int): Long = long(GL43.GL_SHADER_STORAGE_BUFFER_SIZE, index)

    val stencilBackFail: StencilOp
        get() = StencilOp(int(GL20.GL_STENCIL_BACK_FAIL))

    val stencilBackFunc: CompareFunction
        get() = CompareFunction(int(GL20.GL_STENCIL_BACK_FUNC))

    val stencilBackPassDepthFail: StencilOp
        get() = StencilOp(int(GL20.GL_STENCIL_BACK_PASS_DEPTH_FAIL))

    val stencilBackPassDepthPass: StencilOp
        get() = StencilOp(int(GL20.GL_STENCIL_BACK_PASS_DEPTH_PASS))

    val stencilBackRef: Int
        get() = int(GL20.GL_STENCIL_BACK_REF)

    val stencilBackValueMask: Int
        get() = int(GL20.GL_STENCIL_BACK_VALUE_MASK)

    val stencilBackWritemask: Int
        get() = int(GL20.GL_STENCIL_BACK_WRITEMASK)

    val stencilClearValue: Int
        get() = int(GL11.GL_STENCIL_CLEAR_VALUE)

    val stencilFail: StencilOp
        get() = StencilOp(int(GL11.GL_STENCIL_FAIL))

    val stencilFunc: CompareFunction
        get() = CompareFunction(int(GL11.GL_STENCIL_FUNC))

    val stencilPassDepthFail: StencilOp
        get() = StencilOp(int(GL11.GL_STENCIL_PASS_DEPTH_FAIL))

    val stencilPassDepthPass: StencilOp
        get() = StencilOp(int(GL11.GL_STENCIL_PASS_DEPTH_PASS))

    val stencilRef: Int
        get() = int(GL11.GL_STENCIL_REF)

    val stencilTest: Boolean
        get() = bool(GL11.GL_STENCIL_TEST)

    val stencilValueMask: Int
        get() = int(GL11.GL_STENCIL_VALUE_MASK)

    val stereo: Boolean
        get() = bool(GL11.GL_STEREO)

    val textureBinding1d: GLtexture
        get() = GLtexture(int(GL11.GL_TEXTURE_BINDING_1D))

    val textureBinding1dArray: GLtextureArray
        get() = GLtextureArray(int(GL30.GL_TEXTURE_BINDING_1D_ARRAY))

    val textureBinding2d: GLtexture
        get() = GLtexture(int(GL11.GL_TEXTURE_BINDING_2D))

    val textureBinding2dArray: GLtextureArray
        get() = GLtextureArray(int(GL30.GL_TEXTURE_BINDING_2D_ARRAY))

    val textureBinding2dMultisample: GLtexture
        get() = GLtexture(int(GL32.GL_TEXTURE_BINDING_2D_MULTISAMPLE))

    val textureBinding2dMultisampleArray: GLtextureArray
        get() = GLtextureArray(int(GL32.GL_TEXTURE_BINDING_2D_MULTISAMPLE_ARRAY))

    val textureBinding3d: GLtexture
        get() = GLtexture(int(GL12.GL_TEXTURE_BINDING_3D))

    val textureBindingBuffer: GLtexture
        get() = GLtexture(int(GL31.GL_TEXTURE_BINDING_BUFFER))

    val textureBindingCubeMap: GLtexture
        get() = GLtexture(int(GL31.GL_TEXTURE_BINDING_CUBE_MAP))

    val textureBindingRectangle: GLtexture
        get() = GLtexture(int(GL31.GL_TEXTURE_BINDING_RECTANGLE))

    val textureCompressionHint: HintMode
        get() = HintMode(int(GL13.GL_TEXTURE_COMPRESSION_HINT))

    val timestamp: Long
        get() = long(GL33.GL_TIMESTAMP)

    val transformFeedbackBufferBinding: GLbuffer
        get() = GLbuffer(int(GL30.GL_TRANSFORM_FEEDBACK_BUFFER_BINDING))

    fun transformFeedbackBufferBinding(index: Int): GLbuffer = GLbuffer(int(GL30.GL_TRANSFORM_FEEDBACK_BUFFER_START, index))

    val transformFeedbackBufferStart: Long
        get() = long(GL30.GL_TRANSFORM_FEEDBACK_BUFFER_START)

    val transformFeedbackBufferSize: Long
        get() = long(GL30.GL_TRANSFORM_FEEDBACK_BUFFER_SIZE)

    val uniformBufferBinding: GLbuffer
        get() = GLbuffer(int(GL31.GL_UNIFORM_BUFFER_BINDING))

    val uniformBufferSize: Long
        get() = long(GL31.GL_UNIFORM_BUFFER_SIZE)

    val uniformBufferStart: Long
        get() = long(GL31.GL_UNIFORM_BUFFER_START)

    val unpackAlignment: Int
        get() = int(GL11.GL_UNPACK_ALIGNMENT)

    val unpackImageHeight: Int
        get() = int(GL12.GL_UNPACK_IMAGE_HEIGHT)

    val unpackLsbFirst: Boolean
        get() = bool(GL11.GL_UNPACK_LSB_FIRST)

    val unpackRowLength: Int
        get() = int(GL11.GL_UNPACK_ROW_LENGTH)

    val unpackSkipImages: Int
        get() = int(GL12.GL_UNPACK_SKIP_IMAGES)

    val unpackSkipPixels: Int
        get() = int(GL11.GL_UNPACK_SKIP_PIXELS)

    val unpackSkipRows: Int
        get() = int(GL11.GL_UNPACK_SKIP_ROWS)

    val unpackSwapBytes: Boolean
        get() = bool(GL11.GL_UNPACK_SWAP_BYTES)

    val vertexArrayBinding: GLvertexArray
        get() = GLvertexArray(int(GL30.GL_VERTEX_ARRAY_BINDING))

    val vertexBindingDivisor: Int
        get() = int(GL43.GL_VERTEX_BINDING_DIVISOR)

    val vertexBindingOffset: Int
        get() = int(GL43.GL_VERTEX_BINDING_OFFSET)

    val vertexBindingStride: Int
        get() = int(GL43.GL_VERTEX_BINDING_STRIDE)

    val viewport: Vec4i
        get() = vec4i(GL11.GL_VIEWPORT)

    // glGetPointerv

    val colorArrayPointer: Pointer
        get() = pointer(GL11.GL_COLOR_ARRAY_POINTER)

    val edgeFlagArrayPointer: Pointer
        get() = pointer(GL11.GL_EDGE_FLAG_ARRAY_POINTER)

    val feedbackBufferPointer: Pointer
        get() = pointer(GL11.GL_FEEDBACK_BUFFER_POINTER)

    val indexArrayPointer: Pointer
        get() = pointer(GL11.GL_INDEX_ARRAY_POINTER)

    val normalArrayPointer: Pointer
        get() = pointer(GL11.GL_NORMAL_ARRAY_POINTER)

    val textureCoordArrayPointer: Pointer
        get() = pointer(GL11.GL_TEXTURE_COORD_ARRAY_POINTER)

    val selectionBufferPointer: Pointer
        get() = pointer(GL11.GL_SELECTION_BUFFER_POINTER)

    val vertexArrayPointer: Pointer
        get() = pointer(GL11.GL_VERTEX_ARRAY_POINTER)

    val debugCallbackFunction: Pointer
        get() = pointer(GL43.GL_DEBUG_CALLBACK_FUNCTION)

    val debugCallbackUserParam: Pointer
        get() = pointer(GL43.GL_DEBUG_CALLBACK_USER_PARAM)
}

// TODO remove
typealias GliInternalFormat = Int
typealias GliExternalFormat = Int

typealias GliTypeFormat = Int
