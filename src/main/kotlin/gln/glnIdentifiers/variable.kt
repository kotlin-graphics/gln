package gln.glnIdentifiers

import gln.L
import gln.identifiers.GlProgram
import gln.readAscii
import kool.BYTES
import kool.stack
import kool.with
import org.lwjgl.opengl.*
import org.lwjgl.system.MemoryUtil.memGetInt


// https://stackoverflow.com/questions/440144/in-opengl-is-there-a-way-to-get-a-list-of-all-uniforms-attribs-used-by-a-shade

sealed class Variable(val program: GlProgram,
                      val index: Int) {

    val size: Int
    val _type: Int
    abstract val maxLength: Int
    val name: String

    init {
        stack.with {
            val pSize = nmalloc(Int.BYTES, 8)
            val pType = pSize + Int.BYTES
            name = readAscii(maxLength) { ptr, length ->
                GL20C.nglGetActiveAttrib(program.name, index, maxLength, length.adr.L, pSize, pType, ptr.adr.L)
            }
            this@Variable.size = memGetInt(pSize)
            _type = memGetInt(pType)
        }
    }
    class ActiveAttribute(program: GlProgram, index: Int) : Variable(program, index) {

        val type: Type
            get() = Type(_type)
        override val maxLength = program.activeAttributeMaxLength
        @JvmInline
        value class Type(val i: Int) {
            companion object {
                val FLOAT = Type(GL11.GL_FLOAT)
                val FLOAT_VEC2 = Type(GL20.GL_FLOAT_VEC2)
                val FLOAT_VEC3 = Type(GL20.GL_FLOAT_VEC3)
                val FLOAT_VEC4 = Type(GL20.GL_FLOAT_VEC4)
                val FLOAT_MAT2 = Type(GL20.GL_FLOAT_MAT2)
                val FLOAT_MAT3 = Type(GL20.GL_FLOAT_MAT3)
                val FLOAT_MAT4 = Type(GL20.GL_FLOAT_MAT4)
                val FLOAT_MAT2x3 = Type(GL21.GL_FLOAT_MAT2x3)
                val FLOAT_MAT2x4 = Type(GL21.GL_FLOAT_MAT2x4)
                val FLOAT_MAT3x2 = Type(GL21.GL_FLOAT_MAT3x2)
                val FLOAT_MAT3x4 = Type(GL21.GL_FLOAT_MAT3x4)
                val FLOAT_MAT4x2 = Type(GL21.GL_FLOAT_MAT4x2)
                val FLOAT_MAT4x3 = Type(GL21.GL_FLOAT_MAT4x3)
                val INT_VEC2 = Type(GL20.GL_INT_VEC2)
                val INT_VEC3 = Type(GL20.GL_INT_VEC3)
                val INT_VEC4 = Type(GL20.GL_INT_VEC4)
                val UNSIGNED_INT = Type(GL11.GL_UNSIGNED_INT)
                val UNSIGNED_INT_VEC2 = Type(GL30.GL_UNSIGNED_INT_VEC2)
                val UNSIGNED_INT_VEC3 = Type(GL30.GL_UNSIGNED_INT_VEC3)
                val UNSIGNED_INT_VEC4 = Type(GL30.GL_UNSIGNED_INT_VEC4)
                val DOUBLE = Type(GL11.GL_DOUBLE)
                val DOUBLE_VEC2 = Type(GL40.GL_DOUBLE_VEC2)
                val DOUBLE_VEC3 = Type(GL40.GL_DOUBLE_VEC3)
                val DOUBLE_VEC4 = Type(GL40.GL_DOUBLE_VEC4)
                val DOUBLE_MAT2 = Type(GL40.GL_DOUBLE_MAT2)
                val DOUBLE_MAT3 = Type(GL40.GL_DOUBLE_MAT3)
                val DOUBLE_MAT4 = Type(GL40.GL_DOUBLE_MAT4)
                val DOUBLE_MAT2x3 = Type(GL40.GL_DOUBLE_MAT2x3)
                val DOUBLE_MAT2x4 = Type(GL40.GL_DOUBLE_MAT2x4)
                val DOUBLE_MAT3x2 = Type(GL40.GL_DOUBLE_MAT3x2)
                val DOUBLE_MAT3x4 = Type(GL40.GL_DOUBLE_MAT3x4)
                val DOUBLE_MAT4x2 = Type(GL40.GL_DOUBLE_MAT4x2)
                val DOUBLE_MAT4x3 = Type(GL40.GL_DOUBLE_MAT4x3)
            }
        }
    }

    class ActiveUniform(program: GlProgram, index: Int) : Variable(program, index) {

        val type: Type
            get() = Type(_type)
        override val maxLength = program.activeUniformMaxLength

        @JvmInline
        value class Type(val i: Int) {
            companion object {
                val FLOAT = Type(GL11.GL_FLOAT)
                val FLOAT_VEC2 = Type(GL20.GL_FLOAT_VEC2)
                val FLOAT_VEC3 = Type(GL20.GL_FLOAT_VEC3)
                val FLOAT_VEC4 = Type(GL20.GL_FLOAT_VEC4)
                val INT = Type(GL20.GL_INT)
                val INT_VEC2 = Type(GL20.GL_INT_VEC2)
                val INT_VEC3 = Type(GL20.GL_INT_VEC3)
                val INT_VEC4 = Type(GL20.GL_INT_VEC4)
                val UNSIGNED_INT = Type(GL11.GL_UNSIGNED_INT)
                val UNSIGNED_INT_VEC2 = Type(GL30.GL_UNSIGNED_INT_VEC2)
                val UNSIGNED_INT_VEC3 = Type(GL30.GL_UNSIGNED_INT_VEC3)
                val UNSIGNED_INT_VEC4 = Type(GL30.GL_UNSIGNED_INT_VEC4)
                val BOOL = Type(GL30.GL_BOOL)
                val BOOL_VEC2 = Type(GL30.GL_BOOL_VEC2)
                val BOOL_VEC3 = Type(GL30.GL_BOOL_VEC3)
                val BOOL_VEC4 = Type(GL30.GL_BOOL_VEC4)
                val FLOAT_MAT2 = Type(GL20.GL_FLOAT_MAT2)
                val FLOAT_MAT3 = Type(GL20.GL_FLOAT_MAT3)
                val FLOAT_MAT4 = Type(GL20.GL_FLOAT_MAT4)
                val FLOAT_MAT2x3 = Type(GL21.GL_FLOAT_MAT2x3)
                val FLOAT_MAT2x4 = Type(GL21.GL_FLOAT_MAT2x4)
                val FLOAT_MAT3x2 = Type(GL21.GL_FLOAT_MAT3x2)
                val FLOAT_MAT3x4 = Type(GL21.GL_FLOAT_MAT3x4)
                val FLOAT_MAT4x2 = Type(GL21.GL_FLOAT_MAT4x2)
                val FLOAT_MAT4x3 = Type(GL21.GL_FLOAT_MAT4x3)
                val SAMPLER_1D = Type(GL30.GL_SAMPLER_1D)
                val SAMPLER_2D = Type(GL30.GL_SAMPLER_2D)
                val SAMPLER_3D = Type(GL30.GL_SAMPLER_3D)
                val SAMPLER_CUBE = Type(GL30.GL_SAMPLER_CUBE)
                val SAMPLER_1D_SHADOW = Type(GL30.GL_SAMPLER_1D_SHADOW)
                val SAMPLER_2D_SHADOW = Type(GL30.GL_SAMPLER_2D_SHADOW)
                val SAMPLER_1D_ARRAY = Type(GL30.GL_SAMPLER_1D_ARRAY)
                val SAMPLER_2D_ARRAY = Type(GL30.GL_SAMPLER_2D_ARRAY)
                val SAMPLER_1D_ARRAY_SHADOW = Type(GL30.GL_SAMPLER_1D_ARRAY_SHADOW)
                val SAMPLER_2D_ARRAY_SHADOW = Type(GL30.GL_SAMPLER_2D_ARRAY_SHADOW)
                val SAMPLER_2D_MULTISAMPLE = Type(GL32.GL_SAMPLER_2D_MULTISAMPLE)
                val SAMPLER_2D_MULTISAMPLE_ARRAY = Type(GL32.GL_SAMPLER_2D_MULTISAMPLE_ARRAY)
                val SAMPLER_CUBE_SHADOW = Type(GL30.GL_SAMPLER_CUBE_SHADOW)
                val SAMPLER_BUFFER = Type(GL33.GL_SAMPLER_BUFFER)
                val SAMPLER_2D_RECT = Type(GL33.GL_SAMPLER_2D_RECT)
                val SAMPLER_2D_RECT_SHADOW = Type(GL33.GL_SAMPLER_2D_RECT_SHADOW)
                val INT_SAMPLER_1D = Type(GL30.GL_INT_SAMPLER_1D)
                val INT_SAMPLER_2D = Type(GL30.GL_INT_SAMPLER_2D)
                val INT_SAMPLER_3D = Type(GL30.GL_INT_SAMPLER_3D)
                val INT_SAMPLER_CUBE = Type(GL30.GL_INT_SAMPLER_CUBE)
                val INT_SAMPLER_1D_ARRAY = Type(GL30.GL_INT_SAMPLER_1D_ARRAY)
                val INT_SAMPLER_2D_ARRAY = Type(GL30.GL_INT_SAMPLER_2D_ARRAY)
                val INT_SAMPLER_2D_MULTISAMPLE = Type(GL32.GL_INT_SAMPLER_2D_MULTISAMPLE)
                val INT_SAMPLER_2D_MULTISAMPLE_ARRAY = Type(GL32.GL_INT_SAMPLER_2D_MULTISAMPLE_ARRAY)
                val INT_SAMPLER_BUFFER = Type(GL33.GL_INT_SAMPLER_BUFFER)
                val INT_SAMPLER_2D_RECT = Type(GL33.GL_INT_SAMPLER_2D_RECT)
                val UNSIGNED_INT_SAMPLER_1D = Type(GL30.GL_UNSIGNED_INT_SAMPLER_1D)
                val UNSIGNED_INT_SAMPLER_2D = Type(GL30.GL_UNSIGNED_INT_SAMPLER_2D)
                val UNSIGNED_INT_SAMPLER_3D = Type(GL30.GL_UNSIGNED_INT_SAMPLER_3D)
                val UNSIGNED_INT_SAMPLER_CUBE = Type(GL30.GL_UNSIGNED_INT_SAMPLER_CUBE)
                val UNSIGNED_INT_SAMPLER_1D_ARRAY = Type(GL30.GL_UNSIGNED_INT_SAMPLER_1D_ARRAY)
                val UNSIGNED_INT_SAMPLER_2D_ARRAY = Type(GL30.GL_UNSIGNED_INT_SAMPLER_2D_ARRAY)
                val UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE = Type(GL32.GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE)
                val UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE_ARRAY = Type(GL32.GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE_ARRAY)
                val UNSIGNED_INT_SAMPLER_BUFFER = Type(GL33.GL_UNSIGNED_INT_SAMPLER_BUFFER)
                val UNSIGNED_INT_SAMPLER_2D_RECT = Type(GL33.GL_UNSIGNED_INT_SAMPLER_2D_RECT)
            }
        }
    }

    class TransformFeedbackVarying(program: GlProgram, index: Int) : Variable(program, index) {

        val type: ActiveAttribute.Type
            get() = ActiveAttribute.Type(_type)

        override val maxLength = program.transformFeedbackVaryingMaxLength
    }
}