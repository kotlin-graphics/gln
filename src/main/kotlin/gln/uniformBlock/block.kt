package gln.uniformBlock

import glm_.BYTES
import glm_.bool
import glm_.buffer.cap
import glm_.glm
import gln.buf
import gln.bufAd
import org.lwjgl.opengl.GL31
import org.lwjgl.opengl.GL40
import org.lwjgl.opengl.GL43


object UniformBlock {

    var programName = 0
    var blockIndex = 0

    inline val name: String
        get() {
            GL31.nglGetActiveUniformBlockName(programName, blockIndex, buf.cap, bufAd, bufAd + Int.BYTES)
            val bytes = ByteArray(buf.getInt(0), { buf[Int.BYTES + it] })
            return String(bytes)
        }
    inline val binding: Int
        get() {
            GL31.nglGetActiveUniformBlockiv(programName, blockIndex, GL31.GL_UNIFORM_BLOCK_BINDING, bufAd)
            return buf.getInt(0)
        }
    inline val dataSize: Int
        get() {
            GL31.nglGetActiveUniformBlockiv(programName, blockIndex, GL31.GL_UNIFORM_BLOCK_DATA_SIZE, bufAd)
            return buf.getInt(0)
        }
    inline val nameLength: Int
        get() {
            GL31.nglGetActiveUniformBlockiv(programName, blockIndex, GL31.GL_UNIFORM_BLOCK_NAME_LENGTH, bufAd)
            return buf.getInt(0)
        }
    inline val activeUniforms: Int
        get() {
            GL31.nglGetActiveUniformBlockiv(programName, blockIndex, GL31.GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS, bufAd)
            return buf.getInt(0)
        }
    inline val activeUniformsIndices: IntArray
        get() {
            GL31.nglGetActiveUniformBlockiv(programName, blockIndex, GL31.GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS, bufAd)
            return IntArray(glm.min(activeUniforms, 32), { buf.getInt(it * Int.BYTES) })
        }
    inline val byVertexShader: Boolean
        get() {
            GL31.nglGetActiveUniformBlockiv(programName, blockIndex, GL31.GL_UNIFORM_BLOCK_REFERENCED_BY_VERTEX_SHADER, bufAd)
            return buf.get(0).bool
        }
    inline val byTessControlShader: Boolean
        get() {
            GL31.nglGetActiveUniformBlockiv(programName, blockIndex, GL40.GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_CONTROL_SHADER, bufAd)
            return buf.get(0).bool
        }
    inline val byTessEvaluationShader: Boolean
        get() {
            GL31.nglGetActiveUniformBlockiv(programName, blockIndex, GL40.GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_EVALUATION_SHADER, bufAd)
            return buf.get(0).bool
        }
    inline val byGeometryShader: Boolean
        get() {
            GL31.nglGetActiveUniformBlockiv(programName, blockIndex, GL31.GL_UNIFORM_BLOCK_REFERENCED_BY_GEOMETRY_SHADER, bufAd)
            return buf.get(0).bool
        }
    inline val byFragmentShader: Boolean
        get() {
            GL31.nglGetActiveUniformBlockiv(programName, blockIndex, GL31.GL_UNIFORM_BLOCK_REFERENCED_BY_FRAGMENT_SHADER, bufAd)
            return buf.get(0).bool
        }
    inline val byComputeShader: Boolean
        get() {
            GL31.nglGetActiveUniformBlockiv(programName, blockIndex, GL43.GL_UNIFORM_BLOCK_REFERENCED_BY_COMPUTE_SHADER, bufAd)
            return buf.get(0).bool
        }
}