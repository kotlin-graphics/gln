package gln.uniformBlock

import org.lwjgl.opengl.GL31


inline fun glUniformBlockBinding(program: IntArray, uniformBlockIndex: Int, uniformBlockBinding: Int) = GL31.glUniformBlockBinding(program[0], uniformBlockIndex, uniformBlockBinding)