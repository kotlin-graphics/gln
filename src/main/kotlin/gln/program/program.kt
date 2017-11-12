package gln.program

import gln.get
import org.lwjgl.opengl.*
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.util.stream.Collectors
import kotlin.properties.Delegates


var programName: IntArray by Delegates.notNull()


inline fun glCreatePrograms(programs: IntArray) = repeat(programs.size) { programs[it] = GL20.glCreateProgram() }

inline fun glUseProgram(program: Enum<*>) = GL20.glUseProgram(programName[program])
inline fun glUseProgram(program: IntArray) = GL20.glUseProgram(program[0])
inline fun glUseProgram(program: Program) = GL20.glUseProgram(program.name)
inline fun glUseProgram() = GL20.glUseProgram(0)

inline fun glDeletePrograms(programs: Enum<*>) = GL20.glDeleteProgram(programName[programs])
inline fun glDeletePrograms(programs: IntArray) = programs.forEach { GL20.glDeleteProgram(it) }
inline fun glDeleteProgram(program: Program) = GL20.glDeleteProgram(program.name)
inline fun glDeletePrograms(vararg programs: Program) = programs.forEach { GL20.glDeleteProgram(it.name) }


open class Program {

    @JvmField val name: Int
    val uniforms = HashMap<String, Int>()

    // for Learn OpenGL

    /** (root, vertex, fragment) or (vertex, fragment)  */
    constructor(context: Class<*>, vararg strings: String) {

        name = GL20.glCreateProgram()

        val root =
                if (strings[0].isShaderPath())
                    ""
                else {
                    var r = strings[0]
                    if (r[0] != '/')
                        r = "/$r"
                    if (!r.endsWith('/'))
                        r = "$r/"
                    r
                }

        val (shaders, uniforms) = strings.drop(if (root.isEmpty()) 0 else 1).partition { it.isShaderPath() }

        val shaderNames = shaders.map { createShader(context, root + it) }.onEach { GL20.glAttachShader(name, it) }

        GL20.glLinkProgram(name)

        if (GL20.glGetProgrami(name, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {

            val strInfoLog = GL20.glGetProgramInfoLog(name)

            System.err.println("Linker failure: $strInfoLog")
        }

        shaderNames.forEach {
            GL20.glDetachShader(name, it)
            GL20.glDeleteShader(it)
        }

        uniforms.forEach {
            val i = GL20.glGetUniformLocation(name, it)
            if (i != -1)
                this.uniforms[it] = i
            else
                println("unable to find '$it' uniform location!")
        }
    }

    constructor(vararg strings: String) {

        name = GL20.glCreateProgram()

//        var root = ""
//
//        if (!strings[0].isShaderPath() && !strings[0].contains("#version")) { // TODO passing directly srcs
//            root = strings[0]
//            if (!root.endsWith('/'))
//                root = "$root/"
//        }

        val root =
                if (strings[0].isShaderPath())
                    ""
                else {
                    var r = strings[0]
                    if(!r.endsWith('/'))
                        r = "$r/"
                    r
                }

        val (shaders, uniforms) = strings.drop(if (root.isEmpty()) 0 else 1).partition { it.isShaderPath() }

        val shaderNames = shaders.map { createShader(root + it) }.onEach { GL20.glAttachShader(name, it) }

        GL20.glLinkProgram(name)

        if (GL20.glGetProgrami(name, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {

            val strInfoLog = GL20.glGetProgramInfoLog(name)

            System.err.println("Linker failure: $strInfoLog")
        }

        shaderNames.forEach {
            GL20.glDetachShader(name, it)
            GL20.glDeleteShader(it)
        }

        uniforms.forEach {
            val i = GL20.glGetUniformLocation(name, it)
            if (i != -1)
                this.uniforms[it] = i
            else
                println("unable to find '$it' uniform location!")
        }
    }

    operator fun get(s: String): Int = uniforms[s]!!

    internal fun String.isShaderPath() = endsWith(".vert") || endsWith(".tesc") || endsWith(".tese") || endsWith(".geom") || endsWith(".frag") || endsWith(".comp")


    fun createShader(context: Class<*>, path: String): Int {

        val shader = GL20.glCreateShader(path.type)

        val url = context::class.java.getResource(path)
        val lines = File(url.toURI()).readLines()

        var source = ""
        lines.forEach {
            if (it.startsWith("#include "))
                source += parseInclude(context, path.substringBeforeLast('/'), it.substring("#include ".length).trim())
            else
                source += it
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

    fun parseInclude(context: Class<*>, root: String, shader: String): String {
        if (shader.startsWith('"') && shader.endsWith('"'))
            shader.substring(1, shader.length - 1)
        val url = context::class.java.getResource("$root/$shader")
        return File(url.toURI()).readText() + "\n"
    }

    fun createShader(path: String): Int {

        val shader = GL20.glCreateShader(path.type)

        val lines = ClassLoader.getSystemResourceAsStream(path).use {
            BufferedReader(InputStreamReader(it)).lines().collect(Collectors.toList())
        }

        var source = ""
        lines.forEach {
            if (it.startsWith("#include "))
                source += parseInclude(path.substringBeforeLast('/'), it.substring("#include ".length).trim())
            else
                source += it
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

    fun parseInclude(root: String, shader: String): String {
        if (shader.startsWith('"') && shader.endsWith('"'))
            shader.substring(1, shader.length - 1)
        val url = ClassLoader.getSystemResource("$root/$shader")
        return File(url.toURI()).readText() + "\n"
    }

    fun createProgram(shaderList: List<Int>): Int {

        val program = GL20.glCreateProgram()

        shaderList.forEach { GL20.glAttachShader(program, it) }

        GL20.glLinkProgram(program)

        val status = GL20.glGetProgrami(program, GL20.GL_LINK_STATUS)
        if (status == GL11.GL_FALSE) {

            val strInfoLog = GL20.glGetProgramInfoLog(program)

            System.err.println("Linker failure: $strInfoLog")
        }

        shaderList.forEach {
            GL20.glDetachShader(program, it)
            GL20.glDeleteShader(it)
        }

        return program
    }

    private val String.type
        get() = when (substringAfterLast('.')) {
            "vert" -> GL20.GL_VERTEX_SHADER
            "tesc" -> GL40.GL_TESS_CONTROL_SHADER
            "tese" -> GL40.GL_TESS_EVALUATION_SHADER
            "geom" -> GL32.GL_GEOMETRY_SHADER
            "frag" -> GL20.GL_FRAGMENT_SHADER
            "comp" -> GL43.GL_COMPUTE_SHADER
            else -> throw Error("invalid shader extension")
        }
}