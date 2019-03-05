package gln.blend

import gln.*
import org.lwjgl.opengl.*

inline fun blend(block: ObjectBlend.() -> Unit) = ObjectBlend.block()

object ObjectBlend {

	var enabled: Boolean
		get() = GL11C.glIsEnabled(GL11C.GL_BLEND)
		set(value) = when {
			value -> GL11C.glEnable(GL11C.GL_BLEND)
			else  -> GL11C.glDisable(GL11C.GL_BLEND)
		}

	fun getEnabled(index: Int) = GL30.glIsEnabledi(GL30C.GL_BLEND, index)
	fun setEnabled(index: Int) = GL30.glEnablei(GL30C.GL_BLEND, index)

	fun equation(mode: BlendEquationMode) = GL14.glBlendEquation(mode.i)
	fun equation(index: Int, mode: BlendEquationMode) = GL40C.glBlendEquationi(index, mode.i)

	fun equationSeparate(rgbMode: BlendEquationMode, alphaMode: BlendEquationMode) = GL20.glBlendEquationSeparate(rgbMode.i, alphaMode.i)
	fun equationSeparate(index: Int, rgbMode: BlendEquationMode, alphaMode: BlendEquationMode) = GL40C.glBlendEquationSeparatei(index, rgbMode.i, alphaMode.i)

	fun func(srcFactor: BlendingFactor, destFactor: BlendingFactor) = GL11.glBlendFunc(srcFactor.i, destFactor.i)
	fun func(index: Int, srcFactor: BlendingFactor, destFactor: BlendingFactor) = GL40C.glBlendFunci(index, srcFactor.i, destFactor.i)

	fun funcSeparate(srcRgbFactor: BlendingFactor, srcAlphaFactor: BlendingFactor, destRgbFactor: BlendingFactor, destAlphaFactor: BlendingFactor)
			= GL14.glBlendFuncSeparate(srcRgbFactor.i, srcAlphaFactor.i, destRgbFactor.i, destAlphaFactor.i)
	fun funcSeparate(index: Int, srcRgbFactor: BlendingFactor, srcAlphaFactor: BlendingFactor, destRgbFactor: BlendingFactor, destAlphaFactor: BlendingFactor)
			= GL40C.glBlendFuncSeparatei(index, srcRgbFactor.i, srcAlphaFactor.i, destRgbFactor.i, destAlphaFactor.i)
}