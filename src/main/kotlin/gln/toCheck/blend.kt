package gln.toCheck

import org.lwjgl.opengl.*

inline fun blend(block: ObjectBlend.() -> Unit) = ObjectBlend.block()

object ObjectBlend {

    val add = Equation.Add
    val subtract = Equation.Subtract
    val reverseSubtract = Equation.ReverseSubtract
    val min = Equation.Min
    val max = Equation.Max

    val zero = Factor.Zero
    val one = Factor.One
    val srcColor = Factor.SrcColor
    val oneMinusSrcColor = Factor.OneMinusSrcColor
    val dstColor = Factor.DstColor
    val oneMinusDstColor = Factor.OneMinusDstColor
    val srcAlpha = Factor.SrcAlpha
    val oneMinusSrcAlpha = Factor.OneMinusSrcAlpha
    val dstAlpha = Factor.DstAlpha
    val oneMinusDstAlpha = Factor.OneMinusDstAlpha
    val constantColor = Factor.ConstantColor
    val oneMinusConstantColor = Factor.OneMinusConstantColor
    val constantAlpha = Factor.ConstantAlpha
    val oneMinusConstantAlpha = Factor.OneMinusConstantAlpha
    val srcAlphaSaturate = Factor.SrcAlphaSaturate
    val src1Color = Factor.Src1Color
    val oneMinusSrc1Color = Factor.OneMinusSrc1Color
    val src1Alpha = Factor.Src1Alpha
    val oneMinusSrc1Alpha = Factor.OneMinusSrc1Alpha

    var enabled: Boolean
        get() = GL11C.glIsEnabled(GL11C.GL_BLEND)
        set(value) = when {
            value -> GL11C.glEnable(GL11C.GL_BLEND)
            else  -> GL11C.glDisable(GL11C.GL_BLEND)
        }

    fun getEnabled(index: Int) = GL30.glIsEnabledi(GL30C.GL_BLEND, index)
    fun setEnabled(index: Int) = GL30.glEnablei(GL30C.GL_BLEND, index)

    fun equation(mode: Equation) = GL14.glBlendEquation(mode.i)
    fun equation(index: Int, mode: Equation) = GL40C.glBlendEquationi(index, mode.i)

    fun equationSeparate(rgbMode: Equation, alphaMode: Equation) = GL20.glBlendEquationSeparate(rgbMode.i, alphaMode.i)
    fun equationSeparate(index: Int, rgbMode: Equation, alphaMode: Equation) = GL40C.glBlendEquationSeparatei(index, rgbMode.i, alphaMode.i)

    fun func(srcFactor: Factor, destFactor: Factor) = GL11.glBlendFunc(srcFactor.i, destFactor.i)
    fun func(index: Int, srcFactor: Factor, destFactor: Factor) = GL40C.glBlendFunci(index, srcFactor.i, destFactor.i)

    fun funcSeparate(srcRgbFactor: Factor, srcAlphaFactor: Factor, destRgbFactor: Factor, destAlphaFactor: Factor)
            = GL14.glBlendFuncSeparate(srcRgbFactor.i, srcAlphaFactor.i, destRgbFactor.i, destAlphaFactor.i)
    fun funcSeparate(index: Int, srcRgbFactor: Factor, srcAlphaFactor: Factor, destRgbFactor: Factor, destAlphaFactor: Factor)
            = GL40C.glBlendFuncSeparatei(index, srcRgbFactor.i, srcAlphaFactor.i, destRgbFactor.i, destAlphaFactor.i)


    enum class Equation(val i: Int) {
        Add(GL14.GL_FUNC_ADD),
        Subtract(GL14.GL_FUNC_SUBTRACT),
        ReverseSubtract(GL14.GL_FUNC_REVERSE_SUBTRACT),
        Min(GL14.GL_MIN),
        Max(GL14.GL_MAX);

        companion object {
            fun fromValue(value: Int): Equation = values().find { it.i == value } ?: throw IllegalArgumentException()
        }
    }

    enum class Factor(val i: Int) {
        Zero(GL11.GL_ZERO),
        One(GL11.GL_ONE),
        SrcColor(GL11.GL_SRC_COLOR),
        OneMinusSrcColor(GL11.GL_ONE_MINUS_SRC_COLOR),
        DstColor(GL11.GL_DST_COLOR),
        OneMinusDstColor(GL11.GL_ONE_MINUS_DST_COLOR),
        SrcAlpha(GL11.GL_SRC_ALPHA),
        OneMinusSrcAlpha(GL11.GL_ONE_MINUS_SRC_ALPHA),
        DstAlpha(GL11.GL_DST_ALPHA),
        OneMinusDstAlpha(GL11.GL_ONE_MINUS_DST_ALPHA),
        ConstantColor(GL14.GL_CONSTANT_COLOR),
        OneMinusConstantColor(GL14.GL_ONE_MINUS_CONSTANT_COLOR),
        ConstantAlpha(GL14.GL_CONSTANT_ALPHA),
        OneMinusConstantAlpha(GL14.GL_ONE_MINUS_CONSTANT_ALPHA),
        SrcAlphaSaturate(GL14.GL_SRC_ALPHA_SATURATE),
        Src1Color(GL33.GL_SRC1_COLOR),
        OneMinusSrc1Color(GL33.GL_ONE_MINUS_SRC1_COLOR),
        Src1Alpha(GL15.GL_SRC1_ALPHA),
        OneMinusSrc1Alpha(GL33.GL_ONE_MINUS_SRC1_ALPHA);

        companion object {
            fun fromValue(value: Int): Factor = values().find { it.i == value } ?: throw IllegalArgumentException()
        }
    }
}