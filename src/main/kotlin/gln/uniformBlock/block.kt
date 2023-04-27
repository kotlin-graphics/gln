package gln.uniformBlock

import glm_.bool
import gln.L
import gln.identifiers.GlProgram
import gln.readBoolean
import gln.readInt
import kool.*
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
        get() = stack {
            val size = nameLength
            val pLength = it.mallocInt(1)
            val pUniformBlockName = it.malloc(size)
            GL31C.nglGetActiveUniformBlockName(program.name, blockIndex, size, pLength.adr.L, pUniformBlockName.adr.L)
            memASCII(pUniformBlockName.adr.L, pLength[0])
        }
    inline val binding: Int
        get() = readInt { GL31C.nglGetActiveUniformBlockiv(programName, blockIndex, GL31C.GL_UNIFORM_BLOCK_BINDING, it) }
    inline val dataSize: Int
        get() = readInt { GL31C.nglGetActiveUniformBlockiv(programName, blockIndex, GL31C.GL_UNIFORM_BLOCK_DATA_SIZE, it) }
    inline val nameLength: Int
        get() = readInt { GL31C.nglGetActiveUniformBlockiv(programName, blockIndex, GL31C.GL_UNIFORM_BLOCK_NAME_LENGTH, it) }
    inline val activeUniforms: Int
        get() = readInt { GL31C.nglGetActiveUniformBlockiv(programName, blockIndex, GL31C.GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS, it) }
    inline val activeUniformsIndices: IntArray
        get() = stack {
            val size = activeUniforms
            val pIndices = it.mallocInt(size)
            GL31C.nglGetActiveUniformBlockiv(programName, blockIndex, GL31C.GL_UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES, pIndices.adr.L)
            pIndices.toIntArray()
        }
    inline val byVertexShader: Boolean
        get() = readBoolean { GL31C.nglGetActiveUniformBlockiv(programName, blockIndex, GL31C.GL_UNIFORM_BLOCK_REFERENCED_BY_VERTEX_SHADER, it) }
    inline val byTessControlShader: Boolean
        get() = readBoolean { GL31C.nglGetActiveUniformBlockiv(programName, blockIndex, GL40C.GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_CONTROL_SHADER, it) }
    inline val byTessEvaluationShader: Boolean
        get() = readBoolean { GL31C.nglGetActiveUniformBlockiv(programName, blockIndex, GL40C.GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_EVALUATION_SHADER, it) }
    inline val byGeometryShader: Boolean
        get() = readBoolean { GL31C.nglGetActiveUniformBlockiv(programName, blockIndex, GL31C.GL_UNIFORM_BLOCK_REFERENCED_BY_GEOMETRY_SHADER, it) }
    inline val byFragmentShader: Boolean
        get() = readBoolean { GL31C.nglGetActiveUniformBlockiv(programName, blockIndex, GL31C.GL_UNIFORM_BLOCK_REFERENCED_BY_FRAGMENT_SHADER, it) }
    inline val byComputeShader: Boolean
        get() = readBoolean { GL31C.nglGetActiveUniformBlockiv(programName, blockIndex, GL43C.GL_UNIFORM_BLOCK_REFERENCED_BY_COMPUTE_SHADER, it) }
}