package gln

import glm_.bool
import glm_.vec2.Vec2
import glm_.vec4.Vec4
import gln.identifiers.GlFramebuffer
import gln.identifiers.GlProgram
import gln.identifiers.GlRenderbuffer
import gln.identifiers.GlVertexArray
import gln.sampler.GlSampler
import kool.Ptr
import kool.stack
import org.lwjgl.opengl.*

interface glGetSet {

    private fun int(pName: Int) = GL11C.glGetInteger(pName)
//    private fun int(pName: Int, index: Int) = GL30C.glGetIntegeri(pName, index)
    private fun bool(pName: Int) = GL30C.glGetInteger(pName).bool
    private fun long(pName: Int) = GL32C.glGetInteger64(pName)
    private fun long(pName: Int, index: Int) = GL32C.glGetInteger64i(pName, index)
    private fun pointer(pName: Int): Ptr<*> = readPointer { GL11C.nglGetPointerv(pName, it) }

    fun float(pName: Int) = GL11C.glGetFloat(pName)


    fun vec2(pName: Int): Vec2 = readVec2 { GL11C.nglGetFloatv(pName, it) }

    fun vec4(pName: Int): Vec4 = readVec4 { GL11C.nglGetFloatv(pName, it) }


    val arrayBufferBinding: Int
        get() = int(GL15.GL_ARRAY_BUFFER_BINDING)


    val blendDstAlpha: BlendFactor
        get() = BlendFactor(int(GL14.GL_BLEND_DST_ALPHA))

    val blendDstRgb: BlendFactor
        get() = BlendFactor(int(GL14.GL_BLEND_DST_RGB))

    val blendEquationRgb: BlendEquationMode
        get() = BlendEquationMode(int(GL20.GL_BLEND_EQUATION_RGB))

    val blendEquationAlpha: BlendEquationMode
        get() = BlendEquationMode(int(GL20.GL_BLEND_EQUATION_ALPHA))

    val blendSrcAlpha: BlendFactor
        get() = BlendFactor(int(GL14.GL_BLEND_SRC_ALPHA))

    val blendSrcRgb: BlendFactor
        get() = BlendFactor(int(GL14.GL_BLEND_SRC_RGB))


    val dispatchIndirectBufferBinding: Int
        get() = int(GL43.GL_DISPATCH_INDIRECT_BUFFER_BINDING)

    val debugGroupStackDepth: Int
        get() = int(GL43.GL_DEBUG_GROUP_STACK_DEPTH)

    val currentProgram: GlProgram
        get() = GlProgram(GL20.GL_CURRENT_PROGRAM)


    var depthFunc: CompareFunction
        get() = CompareFunction(int(GL20.GL_DEPTH_FUNC))
        set(value) = GL11C.glDepthFunc(value.i)

    fun drawBuffer(i: Int): BufferMode = int(GL20.GL_DRAW_BUFFER0 + i)

    val drawFramebufferBinding: GlFramebuffer
        get() = GlFramebuffer(int(GL30.GL_DRAW_FRAMEBUFFER_BINDING))

    val readFramebufferBinding: GlFramebuffer
        get() = GlFramebuffer(int(GL30.GL_READ_FRAMEBUFFER_BINDING))

//    val elementArrayBufferBinding: GlBuffer
//        get() = GlBuffer(int(GL30.GL_ELEMENT_ARRAY_BUFFER_BINDING))

    val fragmentShaderDerivativeHint: HintMode
        get() = HintMode(int(GL30.GL_FRAGMENT_SHADER_DERIVATIVE_HINT))

    val lineSmoothHint: HintMode
        get() = HintMode(int(GL11.GL_LINE_SMOOTH_HINT))

    val logicOpMode: LogicOp
        get() = LogicOp(int(GL11.GL_LOGIC_OP_MODE))

//    val pixelPackBufferBinding: GlBuffer
//        get() = GlBuffer(int(GL21.GL_PIXEL_PACK_BUFFER_BINDING))
//
//    val pixelUnpackBufferBinding: GlBuffer
//        get() = GlBuffer(int(GL21.GL_PIXEL_UNPACK_BUFFER_BINDING))

    val primitiveRestartIndex: Int
        get() = int(GL31.GL_PRIMITIVE_RESTART_INDEX)

    val programPipelineBinding: Int
        get() = int(GL41.GL_PROGRAM_PIPELINE_BINDING)


    var provokingVertex: ProvokeMode
        get() = ProvokeMode(int(GL32.GL_PROVOKING_VERTEX))
    // --- [ glProvokingVertex ] ---
        /**
         * Specifies the vertex to be used as the source of data for flat shaded varyings.
         *
         * @param mode the provoking vertex mode. One of:<br><table><tr><td>{@link #GL_FIRST_VERTEX_CONVENTION FIRST_VERTEX_CONVENTION}</td><td>{@link #GL_LAST_VERTEX_CONVENTION LAST_VERTEX_CONVENTION}</td></tr></table>
         *
         * @see <a target="_blank" href="http://docs.gl/gl4/glProvokingVertex">Reference Page</a>
         */
        set(value) = GL32C.glProvokingVertex(value.i)

    val polygonOffsetFactor: Float
        get() = float(GL11.GL_POLYGON_OFFSET_FACTOR)

    val polygonOffsetUnits: Float
        get() = float(GL11.GL_POLYGON_OFFSET_UNITS)

    val polygonSmoothHint: HintMode
        get() = HintMode(int(GL11.GL_POLYGON_SMOOTH))

    val doublebuffer: Boolean
        get() = bool(GL11.GL_DOUBLEBUFFER)

    val renderbufferBinding: GlRenderbuffer
        get() = GlRenderbuffer(int(GL30.GL_RENDERBUFFER_BINDING))

    val sampleBuffers: Int
        get() = int(GL13.GL_SAMPLE_BUFFERS)

    val sampleCoverageValue: Float
        get() = float(GL13.GL_SAMPLE_COVERAGE_VALUE)

    val sampleCoverageInvert: Boolean
        get() = bool(GL13.GL_SAMPLE_COVERAGE_INVERT)

    fun sampleMaskValue(index: Int): Int = readInt { GL30.nglGetIntegeri_v(GL32.GL_SAMPLE_MASK_VALUE, index, it) }

    val samplerBinding: GlSampler
        get() = GlSampler(int(GL33.GL_SAMPLER_BINDING))

    val samples: Int
        get() = int(GL13.GL_SAMPLES)


    val shaderCompiler: Boolean
        get() = bool(GL41.GL_SHADER_COMPILER)


//    val shaderStorageBufferBinding: GlBuffer
//        get() = GlBuffer(int(GL43.GL_SHADER_STORAGE_BUFFER_BINDING))

//    fun shaderStorageBufferBinding(index: Int): GlBuffer = GlBuffer(int(GL43.GL_SHADER_STORAGE_BUFFER_BINDING, index))

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
//
//    val stencilClearValue: Int
//        get() = int(GL11.GL_STENCIL_CLEAR_VALUE)

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

    val stencilValueMask: Int
        get() = int(GL11.GL_STENCIL_VALUE_MASK)

    val stereo: Boolean
        get() = bool(GL11.GL_STEREO)

//    val textureBinding1d: GLtexture1d
//        get() = GLtexture1d(int(GL11.GL_TEXTURE_BINDING_1D))
//
//    val textureBinding1dArray: GLtexture1dArray
//        get() = GLtexture1dArray(int(GL30.GL_TEXTURE_BINDING_1D_ARRAY))
//
//    val textureBinding2d: GLtexture2d
//        get() = GLtexture2d(int(GL11.GL_TEXTURE_BINDING_2D))
//
//    val textureBinding2dArray: GLtexture2dArray
//        get() = GLtexture2dArray(int(GL30.GL_TEXTURE_BINDING_2D_ARRAY))
//
//    val textureBinding2dMultisample: GLtexture2d // TODO multisample type?
//        get() = GLtexture2d(int(GL32.GL_TEXTURE_BINDING_2D_MULTISAMPLE))
//
//    val textureBinding2dMultisampleArray: GLtexture2dArray
//        get() = GLtexture2dArray(int(GL32.GL_TEXTURE_BINDING_2D_MULTISAMPLE_ARRAY))
//
//    val textureBinding3d: GLtexture3d
//        get() = GLtexture3d(int(GL12.GL_TEXTURE_BINDING_3D))
//
//    val textureBindingBuffer: GlTexture
//        get() = GLtexture2d(int(GL31.GL_TEXTURE_BINDING_BUFFER))

//    val textureBindingCubeMap: GLtextureCube TODO
//        get() = GLtextureCube(int(GL31.GL_TEXTURE_BINDING_CUBE_MAP))

//    val textureBindingRectangle: GLtextureRectangle
//        get() = GLtextureRectangle(int(GL31.GL_TEXTURE_BINDING_RECTANGLE))

    val textureCompressionHint: HintMode
        get() = HintMode(int(GL13.GL_TEXTURE_COMPRESSION_HINT))

    val timestamp: Long
        get() = long(GL33.GL_TIMESTAMP)

//    val transformFeedbackBufferBinding: GlBuffer
//        get() = GlBuffer(int(GL30.GL_TRANSFORM_FEEDBACK_BUFFER_BINDING))

//    fun transformFeedbackBufferBinding(index: Int): GlBuffer = GlBuffer(int(GL30.GL_TRANSFORM_FEEDBACK_BUFFER_START, index))

    val transformFeedbackBufferStart: Long
        get() = long(GL30.GL_TRANSFORM_FEEDBACK_BUFFER_START)

    val transformFeedbackBufferSize: Long
        get() = long(GL30.GL_TRANSFORM_FEEDBACK_BUFFER_SIZE)

//    val uniformBufferBinding: GlBuffer
//        get() = GlBuffer(int(GL31.GL_UNIFORM_BUFFER_BINDING))

    val uniformBufferSize: Long
        get() = long(GL31.GL_UNIFORM_BUFFER_SIZE)

    val uniformBufferStart: Long
        get() = long(GL31.GL_UNIFORM_BUFFER_START)


    val vertexArrayBinding: GlVertexArray
        get() = GlVertexArray(int(GL30.GL_VERTEX_ARRAY_BINDING))

    val vertexBindingDivisor: Int
        get() = int(GL43.GL_VERTEX_BINDING_DIVISOR)

    val vertexBindingOffset: Int
        get() = int(GL43.GL_VERTEX_BINDING_OFFSET)

    val vertexBindingStride: Int
        get() = int(GL43.GL_VERTEX_BINDING_STRIDE)


    // glGetPointerv

    val colorArrayPointer: Ptr<*>
        get() = pointer(GL11.GL_COLOR_ARRAY_POINTER)

    val edgeFlagArrayPointer: Ptr<*>
        get() = pointer(GL11.GL_EDGE_FLAG_ARRAY_POINTER)

    val feedbackBufferPointer: Ptr<*>
        get() = pointer(GL11.GL_FEEDBACK_BUFFER_POINTER)

    val indexArrayPointer: Ptr<*>
        get() = pointer(GL11.GL_INDEX_ARRAY_POINTER)

    val normalArrayPointer: Ptr<*>
        get() = pointer(GL11.GL_NORMAL_ARRAY_POINTER)

    val textureCoordArrayPointer: Ptr<*>
        get() = pointer(GL11.GL_TEXTURE_COORD_ARRAY_POINTER)

    val selectionBufferPointer: Ptr<*>
        get() = pointer(GL11.GL_SELECTION_BUFFER_POINTER)

    val vertexArrayPointer: Ptr<*>
        get() = pointer(GL11.GL_VERTEX_ARRAY_POINTER)

    val debugCallbackFunction: Ptr<*>
        get() = pointer(GL43.GL_DEBUG_CALLBACK_FUNCTION)

    val debugCallbackUserParam: Ptr<*>
        get() = pointer(GL43.GL_DEBUG_CALLBACK_USER_PARAM)
}