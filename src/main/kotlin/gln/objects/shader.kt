package gln.objects

import glm_.bool
import gln.ShaderType
import gln.gl20
import org.lwjgl.opengl.*
import java.io.*
import java.nio.IntBuffer

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

        fun createFromSource(sourceText: String, type: ShaderType) = create(type).apply {

            source(sourceText)
            compile()

            if(!compileStatus)
                throw Exception(infoLog)
        }

        fun createFromSource(sourceText: Array<String>, type: ShaderType) = create(type).apply {

            source(*sourceText)
            compile()

            if(!compileStatus)
                throw Exception(infoLog)
        }

        fun createFromPath(path: String, transform: ((String) -> String)? = null): GlShader {

            val lines = ClassLoader.getSystemResourceAsStream(path).use {
                InputStreamReader(it).readLines()
            }

            var source = ""
            lines.forEach {
                source += when {
                    it.startsWith("#include ") -> parseInclude(path.substringBeforeLast('/'), it.substring("#include ".length).trim())
                    else -> it
                } + '\n'
            }

            try {
                return createFromSource(transform?.invoke(source) ?: source, ShaderType(path.type))
            } catch (err: Exception) {
                throw Exception("Compiler failure in ${path.substringAfterLast('/')} shader: ${err.message}")
            }
        }

        fun createFromPath(context: Class<*>, path: String): Int {

            val shader = GL20.glCreateShader(path.type)

            val url = context::class.java.getResource(path)
            val lines = File(url.toURI()).readLines()

            var source = ""
            lines.forEach {
                source += when {
                    it.startsWith("#include ") -> parseInclude(context, path.substringBeforeLast('/'), it.substring("#include ".length).trim())
                    else -> it
                }
                source += '\n'
            }

            GL20.glShaderSource(shader, source)

            GL20.glCompileShader(shader)

            val status = GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS)
            if (status == GL11.GL_FALSE) {

                val strInfoLog = GL20.glGetShaderInfoLog(shader)

                System.err.println("Compiler failure in ${path.substringAfterLast('/')} shader: $strInfoLog")
            }

            return shader
        }

        private fun parseInclude(context: Class<*>, root: String, shader: String): String {
            if (shader.startsWith('"') && shader.endsWith('"'))
                shader.substring(1, shader.length - 1)
            val url = context::class.java.getResource("$root/$shader")
            return File(url.toURI()).readText() + "\n"
        }

        private fun parseInclude(root: String, shader_: String): String {
            val shader = when {
                shader_.startsWith('"') && shader_.endsWith('"') -> shader_.substring(1, shader_.length - 1)
                else -> shader_
            }
            val url = ClassLoader.getSystemResource("$root/$shader")
            return File(url.toURI()).readText() + "\n"
        }

        private val String.type: Int
            get() = when (substringAfterLast('.')) {
                "vert" -> GL20.GL_VERTEX_SHADER
                "tesc" -> GL40.GL_TESS_CONTROL_SHADER
                "tese" -> GL40.GL_TESS_EVALUATION_SHADER
                "geom" -> GL32.GL_GEOMETRY_SHADER
                "frag" -> GL20.GL_FRAGMENT_SHADER
                "comp" -> GL43.GL_COMPUTE_SHADER
                else -> throw Exception("invalid shader extension")
            }
    }
}

inline class GLshaders(val i: IntBuffer)