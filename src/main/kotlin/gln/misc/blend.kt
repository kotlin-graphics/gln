package gln.misc

import gln.BlendEquationMode
import gln.BlendFactor
import org.lwjgl.opengl.*

object BlendDsl {

    fun getEnabled(index: Int): Boolean = GL30C.glIsEnabledi(GL30C.GL_BLEND, index)
    fun setEnabled(index: Int, enabled: Boolean) = when {
        enabled -> GL30C.glEnablei(GL30C.GL_BLEND, index)
        else -> GL30C.glDisablei(GL30C.GL_BLEND, index)
    }

    fun equation(mode: BlendEquationMode) = GL14C.glBlendEquation(mode.i)
    fun equation(index: Int, mode: BlendEquationMode) = GL40C.glBlendEquationi(index, mode.i)

    fun equationSeparate(rgbMode: BlendEquationMode, alphaMode: BlendEquationMode) = GL20C.glBlendEquationSeparate(rgbMode.i, alphaMode.i)
    fun equationSeparate(index: Int, rgbMode: BlendEquationMode, alphaMode: BlendEquationMode) = GL40C.glBlendEquationSeparatei(index, rgbMode.i, alphaMode.i)

    fun func(srcFactor: BlendFactor, destFactor: BlendFactor) = GL11C.glBlendFunc(srcFactor.i, destFactor.i)
    fun func(index: Int, srcFactor: BlendFactor, destFactor: BlendFactor) = GL40C.glBlendFunci(index, srcFactor.i, destFactor.i)

    fun funcSeparate(srcRgbFactor: BlendFactor, srcAlphaFactor: BlendFactor, destRgbFactor: BlendFactor, destAlphaFactor: BlendFactor)
            = GL14.glBlendFuncSeparate(srcRgbFactor.i, destRgbFactor.i, srcAlphaFactor.i, destAlphaFactor.i)
    fun funcSeparate(index: Int, srcRgbFactor: BlendFactor, srcAlphaFactor: BlendFactor, destRgbFactor: BlendFactor, destAlphaFactor: BlendFactor)
            = GL40C.glBlendFuncSeparatei(index, srcRgbFactor.i, destRgbFactor.i, srcAlphaFactor.i, destAlphaFactor.i)
}