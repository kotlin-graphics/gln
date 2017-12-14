package gln.program

import glm_.mat2x2.Mat2
import glm_.mat3x3.Mat3
import glm_.mat4x4.Mat4
import glm_.vec2.Vec2
import glm_.vec3.Vec3
import glm_.vec4.Vec4
import gln.buf
import gln.bufAd
import gln.get
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL31


inline fun initProgram(program: Enum<*>, block: ProgramBase.() -> Unit) = programName.set(program.ordinal, initProgram(block))
inline fun initProgram(program: IntArray, block: ProgramBase.() -> Unit) = program.set(0, initProgram(block))
inline fun initProgram(block: ProgramBase.() -> Unit): Int {
    ProgramBase.name = GL20.glCreateProgram()
    ProgramBase.block()
    return ProgramBase.name
}

inline fun initPrograms(block: Programs.() -> Unit) = initPrograms(programName, block)
inline fun initPrograms(programs: IntArray, block: Programs.() -> Unit) {
    glCreatePrograms(programs)
    Programs.names = programs
    Programs.block()
}

inline fun usingProgram(program: Program, block: ProgramUse.() -> Unit) = usingProgram(program.name, block)
inline fun usingProgram(program: Enum<*>, block: ProgramUse.() -> Unit) = usingProgram(programName[program], block)
inline fun usingProgram(program: IntArray, block: ProgramUse.() -> Unit) = usingProgram(program[0], block)
inline fun usingProgram(program: Int, block: ProgramUse.() -> Unit) {
    ProgramUse.name = program //glUse
    ProgramUse.block()
    glUseProgram()
}

inline fun withProgram(program: Program, block: ProgramBase.() -> Unit) = withProgram(program.name, block)
inline fun withProgram(program: Enum<*>, block: ProgramBase.() -> Unit) = withProgram(programName[program], block)
inline fun withProgram(program: IntArray, block: ProgramBase.() -> Unit) = withProgram(program[0], block)
inline fun withProgram(program: Int, block: ProgramBase.() -> Unit) {
    ProgramBase.name = program
    ProgramBase.block()
}

object ProgramUse {

    var name = 0
        set(value) {
            GL20.glUseProgram(value)
            field = value
        }

    val String.uniform get() = GL20.glGetUniformLocation(name, this)

    var String.blockIndex
        get() = GL31.glGetUniformBlockIndex(name, this)
        set(value) = GL31.glUniformBlockBinding(name, blockIndex, value)

    var String.fragData
        get() = -1
        set(value) = GL30.glBindFragDataLocation(ProgramBase.name, value, this)

    var String.unit
        get() = uniform
        set(value) = GL20.glUniform1i(uniform, value)

    var String.unitE: Enum<*>
        get() = throw Error()
        set(value) = GL20.glUniform1i(uniform, value.ordinal)


    fun link() = GL20.glLinkProgram(name)

    infix fun Int.to(location: Int) = GL20.glUniform1i(location, this)
    infix fun Float.to(location: Int) = GL20.glUniform1f(location, this)

    infix fun Vec2.to(location: Int) {
        to(buf)
        GL20.nglUniform2fv(location, 1, bufAd)
    }

    infix fun Vec3.to(location: Int) {
        to(buf)
        GL20.nglUniform3fv(location, 1, bufAd)
    }

    infix fun Vec4.to(location: Int) {
        to(buf)
        GL20.nglUniform4fv(location, 1, bufAd)
    }

    infix fun Mat2.to(location: Int) {
        to(buf)
        GL20.nglUniformMatrix2fv(location, 1, false, bufAd)
    }

    infix fun Mat3.to(location: Int) {
        to(buf)
        GL20.nglUniformMatrix3fv(location, 1, false, bufAd)
    }

    infix fun Mat4.to(location: Int) {
        to(buf)
        GL20.nglUniformMatrix4fv(location, 1, false, bufAd)
    }

    infix fun Int.to(uniform: String) = GL20.glUniform1i(uniform.uniform, this)
    infix fun Float.to(uniform: String) = GL20.glUniform1f(uniform.uniform, this)

    infix fun Vec2.to(uniform: String) {
        to(buf)
        GL20.nglUniform2fv(uniform.uniform, 1, bufAd)
    }

    infix fun Vec3.to(uniform: String) {
        to(buf)
        GL20.nglUniform3fv(uniform.uniform, 1, bufAd)
    }

    infix fun Vec4.to(uniform: String) {
        to(buf)
        GL20.nglUniform4fv(uniform.uniform, 1, bufAd)
    }

    infix fun Mat2.to(uniform: String) {
        to(buf)
        GL20.nglUniformMatrix2fv(uniform.uniform, 1, false, bufAd)
    }

    infix fun Mat3.to(uniform: String) {
        to(buf)
        GL20.nglUniformMatrix3fv(uniform.uniform, 1, false, bufAd)
    }

    infix fun Mat4.to(uniform: String) {
        to(buf)
        GL20.nglUniformMatrix4fv(uniform.uniform, 1, false, bufAd)
    }
}

object ProgramBase {

    var name = 0

    val String.uniform get() = GL20.glGetUniformLocation(name, this)

    var String.attrib
        get() = GL20.glGetAttribLocation(name, this)
        set(value) = GL20.glBindAttribLocation(name, value, this)

    var String.blockIndex
        get() = GL31.glGetUniformBlockIndex(name, this)
        set(value) = GL31.glUniformBlockBinding(name, blockIndex, value)

    var String.fragData
        get() = -1
        set(value) = GL30.glBindFragDataLocation(name, value, this)

    // only get, no program use
    val String.unit get() = uniform

    inline fun use(block: ProgramUse.() -> Unit) {
        ProgramUse.name = name
        ProgramUse.block()
        GL20.glUseProgram(0)
    }

    inline fun link() = GL20.glLinkProgram(name)

    inline operator fun plusAssign(shader: Int) = GL20.glAttachShader(name, shader)
    inline infix fun attach(shader: Int) = GL20.glAttachShader(name, shader)
    inline fun attach(vararg shader: Int) = shader.forEach { GL20.glAttachShader(name, it) }
}

object Programs {

    lateinit var names: IntArray

    inline fun with(index: Enum<*>, block: ProgramBase.() -> Unit) = with(index.ordinal, block)
    inline fun with(index: Int, block: ProgramBase.() -> Unit) {
        ProgramBase.name = names[index]
        ProgramBase.block()
    }

    inline fun using(index: Enum<*>, block: ProgramUse.() -> Unit) = using(index.ordinal, block)
    inline fun using(index: Int, block: ProgramUse.() -> Unit) {
        ProgramUse.name = names[index]  // bind
        ProgramUse.block()
        GL20.glUseProgram(0)
    }
}