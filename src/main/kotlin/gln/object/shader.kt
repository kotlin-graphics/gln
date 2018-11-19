package gln

import glm_.bool
import org.lwjgl.opengl.GL20C
import java.nio.IntBuffer

@Deprecated("This class was renamed to GlShader", ReplaceWith("GlShader", "gln.`object`"))
typealias GLshader = GlShader // TODO remove


inline class GlShader(val i: Int) {

    // --- [ glDeleteShader ] ---

    fun delete() = GL20C.glDeleteShader(i)

    // --- [ glIsShader ] ---

    val valid: Boolean
        get() = GL20C.glIsShader(i)

    // --- [ glCompileShader ] ---

    fun compile() = GL20C.glCompileShader(i)

    // --- [ glShaderSource ] ---

    infix fun source(source: CharSequence) = gl20.shaderSource(this, source)

    fun source(vararg sources: CharSequence) = gl20.shaderSource(this, *sources)

    // --- [ glGetShaderiv ] ---

    val type: ShaderType
        get() = ShaderType(GL20C.glGetShaderi(i, GL20C.GL_SHADER_TYPE))

    val deleteStatus: Boolean
        get() = GL20C.glGetShaderi(i, GL20C.GL_DELETE_STATUS).bool

    val compileStatus: Boolean
        get() = GL20C.glGetShaderi(i, GL20C.GL_COMPILE_STATUS).bool

    val infoLogLength: Int
        get() = GL20C.glGetShaderi(i, GL20C.GL_INFO_LOG_LENGTH)

    val sourceLength: Int
        get() = GL20C.glGetShaderi(i, GL20C.GL_SHADER_SOURCE_LENGTH)

    // --- [ glGetShaderInfoLog ] ---

    val infoLog: String
        get() = gl20.getShaderInfoLog(this)

    // --- [ glGetShaderSource ] ---

    val shaderSource: String
        get() = gl20.getShaderSource(this)



    companion object {
        // --- [ glCreateShader ] ---
        fun create(type: ShaderType) = GlShader(GL20C.glCreateShader(type.i))
    }
}

inline class GLshaders(val i: IntBuffer)