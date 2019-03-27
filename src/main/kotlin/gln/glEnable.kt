package gln

import glm_.vec4.Vec4i
import org.lwjgl.opengl.*

interface glEnable {

    fun clipDistance(index: Int): Boolean = GL30C.glIsEnabled(GL30C.GL_CLIP_DISTANCE0 + index)
    fun clipDistance(index: Int, enabled: Boolean) = when {
        enabled -> GL30C.glEnable(GL30C.GL_CLIP_DISTANCE0 + index)
        else -> GL30C.glDisable(GL30C.GL_CLIP_DISTANCE0 + index)
    }

    var depthClamp: Boolean
        get() = GL11C.glIsEnabled(GL32.GL_DEPTH_CLAMP)
        set(value) = when {
            value -> GL11C.glEnable(GL32.GL_DEPTH_CLAMP)
            else -> GL11C.glDisable(GL32.GL_DEPTH_CLAMP)
        }

    fun useDepthClamp(block: () -> Unit) {
        GL11C.glEnable(GL32.GL_DEPTH_CLAMP)
        block()
        GL11C.glDisable(GL32.GL_DEPTH_CLAMP)
    }


    var debugOutput: Boolean
        get() = GL11C.glIsEnabled(GL43.GL_DEBUG_OUTPUT)
        set(value) = when {
            value -> GL11C.glEnable(GL43.GL_DEBUG_OUTPUT)
            else -> GL11C.glDisable(GL43.GL_DEBUG_OUTPUT)
        }

    fun useDebugOutput(block: () -> Unit) {
        GL11C.glEnable(GL43.GL_DEBUG_OUTPUT)
        block()
        GL11C.glDisable(GL43.GL_DEBUG_OUTPUT)
    }


    var debugOutputSynchronous: Boolean
        get() = GL11C.glIsEnabled(GL43.GL_DEBUG_OUTPUT_SYNCHRONOUS)
        set(value) = when {
            value -> GL11C.glEnable(GL43.GL_DEBUG_OUTPUT_SYNCHRONOUS)
            else -> GL11C.glDisable(GL43.GL_DEBUG_OUTPUT_SYNCHRONOUS)
        }

    fun useDebugOutputSynchronous(block: () -> Unit) {
        GL11C.glEnable(GL43.GL_DEBUG_OUTPUT_SYNCHRONOUS)
        block()
        GL11C.glDisable(GL43.GL_DEBUG_OUTPUT_SYNCHRONOUS)
    }


    var depthTest: Boolean
        get() = GL11C.glIsEnabled(GL11.GL_DEPTH_TEST)
        set(value) = when {
            value -> GL11C.glEnable(GL11.GL_DEPTH_TEST)
            else -> GL11C.glDisable(GL11.GL_DEPTH_TEST)
        }

    fun depthTest(block: () -> Unit) {
        GL11C.glEnable(GL11.GL_DEPTH_TEST)
        block()
        GL11C.glDisable(GL11.GL_DEPTH_TEST)
    }

    fun depthFunc(func: CompareFunction, block: () -> Unit) {
        GL11C.glEnable(GL11.GL_DEPTH_TEST)
        GL11C.glDepthFunc(func.i)
        block()
        GL11C.glDisable(GL11.GL_DEPTH_TEST)
    }

    fun depthFunc(func: CompareFunction, near: Float, far: Float, block: () -> Unit) {
        GL11C.glEnable(GL11.GL_DEPTH_TEST)
        GL11C.glDepthFunc(func.i)
        GL41C.glDepthRangef(near, far)
        block()
        GL11C.glDisable(GL11.GL_DEPTH_TEST)
    }


    var dither: Boolean
        get() = GL11C.glIsEnabled(GL11.GL_DITHER)
        set(value) = when {
            value -> GL11C.glEnable(GL11.GL_DITHER)
            else -> GL11C.glDisable(GL11.GL_DITHER)
        }

    fun dither(block: () -> Unit) {
        GL11C.glEnable(GL11.GL_DITHER)
        block()
        GL11C.glDisable(GL11.GL_DITHER)
    }


    var framebufferSrgb: Boolean
        get() = GL11C.glIsEnabled(GL30.GL_FRAMEBUFFER_SRGB)
        set(value) = when {
            value -> GL11C.glEnable(GL30.GL_FRAMEBUFFER_SRGB)
            else -> GL11C.glDisable(GL30.GL_FRAMEBUFFER_SRGB)
        }

    fun framebufferSrgb(block: () -> Unit) {
        GL11C.glEnable(GL30.GL_FRAMEBUFFER_SRGB)
        block()
        GL11C.glDisable(GL30.GL_FRAMEBUFFER_SRGB)
    }


    var lineSmooth: Boolean
        get() = GL11C.glIsEnabled(GL11.GL_LINE_SMOOTH)
        set(value) = when {
            value -> GL11C.glEnable(GL11.GL_LINE_SMOOTH)
            else -> GL11C.glDisable(GL11.GL_LINE_SMOOTH)
        }

    fun lineSmooth(block: () -> Unit) {
        GL11C.glEnable(GL11.GL_LINE_SMOOTH)
        block()
        GL11C.glDisable(GL30.GL_FRAMEBUFFER_SRGB)
    }

    fun lineSmooth(width: Float, block: () -> Unit) {
        GL11C.glEnable(GL11.GL_LINE_SMOOTH)
        GL11.glLineWidth(width)
        block()
        GL11C.glDisable(GL30.GL_FRAMEBUFFER_SRGB)
    }


    var multisample: Boolean
        get() = GL11C.glIsEnabled(GL13.GL_MULTISAMPLE)
        set(value) = when {
            value -> GL11C.glEnable(GL13.GL_MULTISAMPLE)
            else -> GL11C.glDisable(GL13.GL_MULTISAMPLE)
        }

    fun multisample(block: () -> Unit) {
        GL11C.glEnable(GL13.GL_MULTISAMPLE)
        block()
        GL11C.glDisable(GL13.GL_MULTISAMPLE)
    }

    fun multisample(sampleCoverage: Float, invert: Boolean, block: () -> Unit) {
        GL11C.glEnable(GL13.GL_MULTISAMPLE)
        GL13.glSampleCoverage(sampleCoverage, invert)
        block()
        GL11C.glDisable(GL13.GL_MULTISAMPLE)
    }


    var polygonSmooth: Boolean
        get() = GL11C.glIsEnabled(GL13.GL_POLYGON_SMOOTH)
        set(value) = when {
            value -> GL11C.glEnable(GL13.GL_POLYGON_SMOOTH)
            else -> GL11C.glDisable(GL13.GL_POLYGON_SMOOTH)
        }

    fun polygonSmooth(block: () -> Unit) {
        GL11C.glEnable(GL13.GL_POLYGON_SMOOTH)
        block()
        GL11C.glDisable(GL13.GL_POLYGON_SMOOTH)
    }

    fun polygonSmooth(mode: PolygonMode, block: () -> Unit) {
        GL11C.glEnable(GL13.GL_POLYGON_SMOOTH)
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, mode.i)
        block()
        GL11C.glDisable(GL13.GL_POLYGON_SMOOTH)
    }


    var polygonOffsetFill: Boolean
        get() = GL11C.glIsEnabled(GL13.GL_POLYGON_OFFSET_FILL)
        set(value) = when {
            value -> GL11C.glEnable(GL13.GL_POLYGON_OFFSET_FILL)
            else -> GL11C.glDisable(GL13.GL_POLYGON_OFFSET_FILL)
        }

    fun polygonOffsetFill(block: () -> Unit) {
        GL11C.glEnable(GL13.GL_POLYGON_OFFSET_FILL)
        block()
        GL11C.glDisable(GL13.GL_POLYGON_OFFSET_FILL)
    }

    fun polygonOffsetFill(factor: Float, units: Float, block: () -> Unit) {
        GL11C.glEnable(GL13.GL_POLYGON_OFFSET_FILL)
        GL11.glPolygonOffset(factor, units)
        block()
        GL11C.glDisable(GL13.GL_POLYGON_OFFSET_FILL)
    }


    var polygonOffsetLine: Boolean
        get() = GL11C.glIsEnabled(GL13.GL_POLYGON_OFFSET_LINE)
        set(value) = when {
            value -> GL11C.glEnable(GL13.GL_POLYGON_OFFSET_LINE)
            else -> GL11C.glDisable(GL13.GL_POLYGON_OFFSET_LINE)
        }

    fun polygonOffsetLine(block: () -> Unit) {
        GL11C.glEnable(GL13.GL_POLYGON_OFFSET_LINE)
        block()
        GL11C.glDisable(GL13.GL_POLYGON_OFFSET_LINE)
    }

    fun polygonOffsetLine(factor: Float, units: Float, block: () -> Unit) {
        GL11C.glEnable(GL13.GL_POLYGON_OFFSET_LINE)
        GL11.glPolygonOffset(factor, units)
        block()
        GL11C.glDisable(GL13.GL_POLYGON_OFFSET_LINE)
    }


    var polygonOffsetPoint: Boolean
        get() = GL11C.glIsEnabled(GL13.GL_POLYGON_OFFSET_POINT)
        set(value) = when {
            value -> GL11C.glEnable(GL13.GL_POLYGON_OFFSET_POINT)
            else -> GL11C.glDisable(GL13.GL_POLYGON_OFFSET_POINT)
        }

    fun polygonOffsetPoint(block: () -> Unit) {
        GL11C.glEnable(GL13.GL_POLYGON_OFFSET_POINT)
        block()
        GL11C.glDisable(GL13.GL_POLYGON_OFFSET_POINT)
    }

    fun polygonOffsetPoint(factor: Float, units: Float, block: () -> Unit) {
        GL11C.glEnable(GL13.GL_POLYGON_OFFSET_POINT)
        GL11.glPolygonOffset(factor, units)
        block()
        GL11C.glDisable(GL13.GL_POLYGON_OFFSET_POINT)
    }


    var programPointSize: Boolean
        get() = GL11C.glIsEnabled(GL32.GL_PROGRAM_POINT_SIZE)
        set(value) = when {
            value -> GL11C.glEnable(GL32.GL_PROGRAM_POINT_SIZE)
            else -> GL11C.glDisable(GL32.GL_PROGRAM_POINT_SIZE)
        }

    fun programPointSize(block: () -> Unit) {
        GL11C.glEnable(GL32.GL_PROGRAM_POINT_SIZE)
        block()
        GL11C.glDisable(GL32.GL_PROGRAM_POINT_SIZE)
    }


    var primitiveRestart: Boolean
        get() = GL11C.glIsEnabled(GL32.GL_PRIMITIVE_RESTART)
        set(value) = when {
            value -> GL11C.glEnable(GL32.GL_PRIMITIVE_RESTART)
            else -> GL11C.glDisable(GL32.GL_PRIMITIVE_RESTART)
        }

    fun primitiveRestart(block: () -> Unit) {
        GL11C.glEnable(GL32.GL_PRIMITIVE_RESTART)
        block()
        GL11C.glDisable(GL32.GL_PRIMITIVE_RESTART)
    }

    fun primitiveRestart(index: Int, block: () -> Unit) {
        GL11C.glEnable(GL32.GL_PRIMITIVE_RESTART)
        GL31.glPrimitiveRestartIndex(index)
        block()
        GL11C.glDisable(GL32.GL_PRIMITIVE_RESTART)
    }


    var sampleAlphaToCoverage: Boolean
        get() = GL11C.glIsEnabled(GL32.GL_SAMPLE_ALPHA_TO_COVERAGE)
        set(value) = when {
            value -> GL11C.glEnable(GL32.GL_SAMPLE_ALPHA_TO_COVERAGE)
            else -> GL11C.glDisable(GL32.GL_SAMPLE_ALPHA_TO_COVERAGE)
        }

    fun sampleAlphaToCoverage(block: () -> Unit) {
        GL11C.glEnable(GL32.GL_SAMPLE_ALPHA_TO_COVERAGE)
        block()
        GL11C.glDisable(GL32.GL_SAMPLE_ALPHA_TO_COVERAGE)
    }

    fun sampleAlphaToCoverage(value: Float, invert: Boolean, block: () -> Unit) {
        GL11C.glEnable(GL32.GL_SAMPLE_ALPHA_TO_COVERAGE)
        GL13.glSampleCoverage(value, invert)
        block()
        GL11C.glDisable(GL32.GL_SAMPLE_ALPHA_TO_COVERAGE)
    }


    var sampleAlphaToOne: Boolean
        get() = GL11C.glIsEnabled(GL32.GL_SAMPLE_ALPHA_TO_ONE)
        set(value) = when {
            value -> GL11C.glEnable(GL32.GL_SAMPLE_ALPHA_TO_ONE)
            else -> GL11C.glDisable(GL32.GL_SAMPLE_ALPHA_TO_ONE)
        }

    fun sampleAlphaToOne(block: () -> Unit) {
        GL11C.glEnable(GL32.GL_SAMPLE_ALPHA_TO_ONE)
        block()
        GL11C.glDisable(GL32.GL_SAMPLE_ALPHA_TO_ONE)
    }

    fun sampleAlphaToOne(value: Float, invert: Boolean, block: () -> Unit) {
        GL11C.glEnable(GL32.GL_SAMPLE_ALPHA_TO_ONE)
        GL13C.glSampleCoverage(value, invert)
        block()
        GL11C.glDisable(GL32.GL_SAMPLE_ALPHA_TO_ONE)
    }


    var sampleCoverage: Boolean
        get() = GL11C.glIsEnabled(GL32.GL_SAMPLE_COVERAGE)
        set(value) = when {
            value -> GL11C.glEnable(GL32.GL_SAMPLE_COVERAGE)
            else -> GL11C.glDisable(GL32.GL_SAMPLE_COVERAGE)
        }

    fun sampleCoverage(block: () -> Unit) {
        GL11C.glEnable(GL32.GL_SAMPLE_COVERAGE)
        block()
        GL11C.glDisable(GL32.GL_SAMPLE_COVERAGE)
    }

    fun sampleCoverage(value: Float, invert: Boolean, block: () -> Unit) {
        GL11C.glEnable(GL32.GL_SAMPLE_COVERAGE)
        GL13.glSampleCoverage(value, invert)
        block()
        GL11C.glDisable(GL32.GL_SAMPLE_COVERAGE)
    }


    var sampleMask: Boolean
        get() = GL11C.glIsEnabled(GL32.GL_SAMPLE_MASK)
        set(value) = when {
            value -> GL11C.glEnable(GL32.GL_SAMPLE_MASK)
            else -> GL11C.glDisable(GL32.GL_SAMPLE_MASK)
        }

    fun sampleMask(block: () -> Unit) {
        GL11C.glEnable(GL32.GL_SAMPLE_MASK)
        block()
        GL11C.glDisable(GL32.GL_SAMPLE_MASK)
    }


    var scissor: Boolean
        get() = GL11C.glIsEnabled(GL32.GL_SCISSOR_TEST)
        set(value) = when {
            value -> GL11C.glEnable(GL32.GL_SCISSOR_TEST)
            else -> GL11C.glDisable(GL32.GL_SCISSOR_TEST)
        }

    fun scissor(block: () -> Unit) {
        GL11C.glEnable(GL32.GL_SCISSOR_TEST)
        block()
        GL11C.glDisable(GL32.GL_SCISSOR_TEST)
    }

    fun scissor(x: Int, y: Int, width: Int, height: Int, block: () -> Unit) {
        GL11C.glEnable(GL32.GL_SCISSOR_TEST)
        GL11.glScissor(x, y, width, height)
        block()
        GL11C.glDisable(GL32.GL_SCISSOR_TEST)
    }

    fun scissor(scissor: Vec4i, block: () -> Unit) {
        GL11C.glEnable(GL32.GL_SCISSOR_TEST)
        GL11.glScissor(scissor.x, scissor.y, scissor.z, scissor.w)
        block()
        GL11C.glDisable(GL32.GL_SCISSOR_TEST)
    }


    var stencil: Boolean
        get() = GL11C.glIsEnabled(GL32.GL_STENCIL)
        set(value) = when {
            value -> GL11C.glEnable(GL32.GL_STENCIL)
            else -> GL11C.glDisable(GL32.GL_STENCIL)
        }

    fun stencil(block: () -> Unit) {
        GL11C.glEnable(GL32.GL_STENCIL)
        block()
        GL11C.glDisable(GL32.GL_STENCIL)
    }

    fun stencil(func: CompareFunction, ref: Int, mask: Int, sFail: StencilOp, dpFail: StencilOp, dpPass: StencilOp, block: () -> Unit) {
        GL11C.glEnable(GL32.GL_STENCIL)
        GL11C.glStencilFunc(func.i, ref, mask)
        GL11C.glStencilOp(sFail.i, dpFail.i, dpPass.i)
        block()
        GL11C.glDisable(GL32.GL_STENCIL)
    }


    var textureCubeMapSeamless: Boolean
        get() = GL11C.glIsEnabled(GL32.GL_TEXTURE_CUBE_MAP_SEAMLESS)
        set(value) = when {
            value -> GL11C.glEnable(GL32.GL_TEXTURE_CUBE_MAP_SEAMLESS)
            else -> GL11C.glDisable(GL32.GL_TEXTURE_CUBE_MAP_SEAMLESS)
        }

    fun textureCubeMapSeamless(block: () -> Unit) {
        GL11C.glEnable(GL32.GL_TEXTURE_CUBE_MAP_SEAMLESS)
        block()
        GL11C.glDisable(GL32.GL_TEXTURE_CUBE_MAP_SEAMLESS)
    }


    var depthMask: Boolean
        get() = GL11C.glIsEnabled(GL32.GL_DEPTH_WRITEMASK)
        set(value) = when {
            value -> GL11C.glEnable(GL32.GL_DEPTH_WRITEMASK)
            else -> GL11C.glDisable(GL32.GL_DEPTH_WRITEMASK)
        }

    fun depthMask(block: () -> Unit) {
        GL11C.glEnable(GL32.GL_DEPTH_WRITEMASK)
        block()
        GL11C.glDisable(GL32.GL_DEPTH_WRITEMASK)
    }
}