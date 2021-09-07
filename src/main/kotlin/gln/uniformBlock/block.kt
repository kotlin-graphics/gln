package gln.uniformBlock

import glm_.bool
import gln.identifiers.GlProgram
import kool.Stack
import kool.mByte
import kool.mInt
import org.lwjgl.opengl.GL31C
import org.lwjgl.opengl.GL40C
import org.lwjgl.opengl.GL43C
import org.lwjgl.system.MemoryUtil.memASCII


object UniformBlock {

    var programName = 0
    val program: GlProgram
        get() = GlProgram(programName)
    var blockIndex = 0

    inline val name: String
        get() = Stack {
            val size = nameLength
            val pLength = it.mInt()
            val pUniformBlockName = it.mByte(size)
            GL31C.nglGetActiveUniformBlockName(program.name, blockIndex, size, pLength.adr, pUniformBlockName.adr)
            memASCII(pUniformBlockName.adr, pLength[0])
        }
    inline val binding: Int
        get() = Stack.intAdr { GL31C.nglGetActiveUniformBlockiv(programName, blockIndex, GL31C.GL_UNIFORM_BLOCK_BINDING, it) }
    inline val dataSize: Int
        get() = Stack.intAdr { GL31C.nglGetActiveUniformBlockiv(programName, blockIndex, GL31C.GL_UNIFORM_BLOCK_DATA_SIZE, it) }
    inline val nameLength: Int
        get() = Stack.intAdr { GL31C.nglGetActiveUniformBlockiv(programName, blockIndex, GL31C.GL_UNIFORM_BLOCK_NAME_LENGTH, it) }
    inline val activeUniforms: Int
        get() = Stack.intAdr { GL31C.nglGetActiveUniformBlockiv(programName, blockIndex, GL31C.GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS, it) }
    inline val activeUniformsIndices: IntArray
        get() = Stack {
            val size = activeUniforms
            val pIndices = it.mInt(size)
            GL31C.nglGetActiveUniformBlockiv(programName, blockIndex, GL31C.GL_UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES, pIndices.adr)
            IntArray(size) { i -> pIndices[i] }
        }
    inline val byVertexShader: Boolean
        get() = Stack.byteAdr { GL31C.nglGetActiveUniformBlockiv(programName, blockIndex, GL31C.GL_UNIFORM_BLOCK_REFERENCED_BY_VERTEX_SHADER, it) }.bool
    inline val byTessControlShader: Boolean
        get() = Stack.byteAdr { GL31C.nglGetActiveUniformBlockiv(programName, blockIndex, GL40C.GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_CONTROL_SHADER, it) }.bool
    inline val byTessEvaluationShader: Boolean
        get() = Stack.byteAdr { GL31C.nglGetActiveUniformBlockiv(programName, blockIndex, GL40C.GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_EVALUATION_SHADER, it) }.bool
    inline val byGeometryShader: Boolean
        get() = Stack.byteAdr { GL31C.nglGetActiveUniformBlockiv(programName, blockIndex, GL31C.GL_UNIFORM_BLOCK_REFERENCED_BY_GEOMETRY_SHADER, it) }.bool
    inline val byFragmentShader: Boolean
        get() = Stack.byteAdr { GL31C.nglGetActiveUniformBlockiv(programName, blockIndex, GL31C.GL_UNIFORM_BLOCK_REFERENCED_BY_FRAGMENT_SHADER, it) }.bool
    inline val byComputeShader: Boolean
        get() = Stack.byteAdr { GL31C.nglGetActiveUniformBlockiv(programName, blockIndex, GL43C.GL_UNIFORM_BLOCK_REFERENCED_BY_COMPUTE_SHADER, it) }.bool
}