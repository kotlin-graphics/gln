@file:Suppress("NOTHING_TO_INLINE")

package gln.program

import glm_.bool
import gln.GLshader
import gln.ShaderType
import gln.get
import gln.gl20
import org.lwjgl.opengl.*
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.lang.NullPointerException
import java.util.stream.Collectors
import kotlin.properties.Delegates


var programName: IntArray by Delegates.notNull()


inline fun glCreatePrograms(programs: IntArray) = repeat(programs.size) { programs[it] = GL20.glCreateProgram() }

inline fun glUseProgram(program: Enum<*>) = GL20.glUseProgram(programName[program])
inline fun glUseProgram(program: IntArray) = GL20.glUseProgram(program[0])
inline fun glUseProgram(program: GlslProgram) = GL20.glUseProgram(program.name)
inline fun glUseProgram() = GL20.glUseProgram(0)

inline fun glDeletePrograms(programs: Enum<*>) = GL20.glDeleteProgram(programName[programs])
inline fun glDeletePrograms(programs: IntArray) = programs.forEach { GL20.glDeleteProgram(it) }
inline fun glDeleteProgram(program: GlslProgram) = GL20.glDeleteProgram(program.name)
inline fun glDeletePrograms(vararg programs: GlslProgram) = programs.forEach { GL20.glDeleteProgram(it.name) }

typealias ShaderSource = String

open class GlslProgram(
        @JvmField
        var name: Int = GL20.glCreateProgram()) {

    val uniforms = HashMap<String, Int>()

    constructor(vertSrc: String, fragSrc: String) : this() {

        val v = createShaderFromSource(vertSrc, GL20.GL_VERTEX_SHADER)
        val f = createShaderFromSource(fragSrc, GL20.GL_FRAGMENT_SHADER)

        plusAssign(v)
        plusAssign(f)

        link()

        if (!linkStatus) System.err.println("Linker failure: $infoLog")

        minusAssign(v)
        minusAssign(f)
        v.delete()
        f.delete()
    }

    constructor(vertSrc: String, geomSrc: String, fragSrc: String) : this() {

        val v = createShaderFromSource(vertSrc, GL20.GL_VERTEX_SHADER)
        val g = createShaderFromSource(geomSrc, GL32.GL_GEOMETRY_SHADER)
        val f = createShaderFromSource(fragSrc, GL20.GL_FRAGMENT_SHADER)

        plusAssign(v)
        plusAssign(g)
        plusAssign(f)

        link()

        if (!linkStatus) System.err.println("Linker failure: $infoLog")

        minusAssign(v)
        minusAssign(g)
        minusAssign(f)
        v.delete()
        g.delete()
        f.delete()
    }

    // for Learn OpenGL

    /** (root, vertex, fragment) or (vertex, fragment)  */
    /* constructor(context: Class<*>, vararg strings: String) {

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

         val shaderNames = shaders.map { createShaderFromPath(context, root + it) }.onEach { GL20.glAttachShader(name, it) }

         GL20.glLinkProgram(name)

         if (GL20.glGetProgrami(name, GL20.GL_LINK_STATUS) == GL11.GL_FALSE)
             System.err.println("Linker failure: ${GL20.glGetProgramInfoLog(name)}")

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
 */

    operator fun get(s: String): Int = uniforms.getOrPut(s) { GL20.glGetUniformLocation(name, s) }


    fun createShaderFromPath(context: Class<*>, path: String): Int {

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

    fun parseInclude(context: Class<*>, root: String, shader: String): String {
        if (shader.startsWith('"') && shader.endsWith('"'))
            shader.substring(1, shader.length - 1)
        val url = context::class.java.getResource("$root/$shader")
        return File(url.toURI()).readText() + "\n"
    }

    fun createProgram(shaderList: List<Int>): Int {

        val program = GL20.glCreateProgram()

        shaderList.forEach { GL20.glAttachShader(program, it) }

        GL20.glLinkProgram(program)

        if (GL20.glGetProgrami(program, GL20.GL_LINK_STATUS) == GL11.GL_FALSE)
            System.err.println("Linker failure: ${GL20.glGetProgramInfoLog(program)}")

        shaderList.forEach {
            GL20.glDetachShader(program, it)
            GL20.glDeleteShader(it)
        }

        return program
    }

    operator fun plusAssign(shader: Int) = GL20.glAttachShader(name, shader)
    operator fun minusAssign(shader: Int) = GL20.glDetachShader(name, shader)

    operator fun plusAssign(shader: GLshader) = GL20.glAttachShader(name, shader.i)
    operator fun minusAssign(shader: GLshader) = GL20.glDetachShader(name, shader.i)

    fun link() = GL20.glLinkProgram(name)

    val linkStatus: Boolean
        get() = GL20.glGetProgrami(name, GL20.GL_LINK_STATUS).bool

    val infoLog: String
        get() = GL20.glGetProgramInfoLog(name)

    companion object {

        @JvmStatic
        fun from(vararg strings: String): GlslProgram {

            fun String.hasSeparator(): Boolean = '/' in this || '\\' in this

            /*
                a) path/to/shaders
                b) path/to/shaders, shader(.*)
                c) path/to/shaders, vertShader(.vert), fragShader(.frag)
                d) path/to/vert(.vert), path/to/frag(.frag)

                ~ vert(.vert), frag(.frag) ~
             */
            return when {
                strings.size == 1 -> {
                    val s = strings[0]
                    var index = s.lastIndexOf('/')
                    if (index < 0)
                        index = s.lastIndexOf('\\')
                    fromRoot(s.substring(0, index), s.substring(index, s.length))
                }
                strings[0].hasSeparator() -> when {
                    !strings[1].hasSeparator() -> when (strings.size) {
                        2 -> fromRoot(strings[0], strings[1])
                        3 -> fromRoot(strings[0], strings[1], strings[2])
                        4 -> fromRoot(strings[0], strings[1], strings[2], strings[3])
                        else -> TODO()
                    }
                    else -> when (strings.size) {
                        2 -> fromRoot("", strings[0], strings[1])
                        3 -> fromRoot("", strings[0], strings[1], strings[2])
                        else -> TODO()
                    }
                }
                else -> TODO()
            }
        }

        @JvmStatic
        fun fromRoot(root: String, shader: String): GlslProgram {

            val shaderNames = arrayListOf<GLshader>()

            val program = GlslProgram()

            val fullShader = when {
                root.endsWith('/') || root.endsWith('\\') -> "$root$shader"
                else -> "$root/$shader"
            }

            for (ext in shaderExtensions)
                try {
                    val shaderName = createShaderFromPath("$fullShader$ext")
                    program += shaderName
                    shaderNames += shaderName
                } catch (exc: Exception) {
//                    println("$fullShader$ext doesnt exist "+ exc.message)
                }
            program.link()

            if (!program.linkStatus)
                System.err.println("Linker failure: ${program.infoLog}")

            for (s in shaderNames) {
                program -= s
                s.delete()
            }

            return program
        }

        @JvmStatic
        fun fromRoot(root: String, vert: String, frag: String): GlslProgram {

            val program = GlslProgram()

            fun fullShader(s: String) = when {
                root.endsWith('/') || root.endsWith('\\') -> "$root$s"
                else -> "$root/$s"
            }

            var fs = fullShader(vert)
            val v = createShaderFromPath(if (fs.endsWith(".vert")) fs else "$fs.vert")
            fs = fullShader(frag)
            val f = createShaderFromPath(if (fs.endsWith(".frag")) fs else "$fs.frag")

            program += v
            program += f

            program.link()

            if (!program.linkStatus) System.err.println("Linker failure: ${program.infoLog}")

            program -= v
            program -= f
            v.delete()
            f.delete()

            return program
        }

        @JvmStatic
        fun fromRoot(root: String,
                     vert: String, vertTransform: ((String) -> String)? = null,
                     frag: String, fragTransform: ((String) -> String)? = null): GlslProgram {

            val program = GlslProgram()

            fun fullShader(s: String) = when {
                root.endsWith('/') || root.endsWith('\\') -> "$root$s"
                else -> "$root/$s"
            }

            var fs = fullShader(vert)
            val v = createShaderFromPath(if (fs.endsWith(".vert")) fs else "$fs.vert", vertTransform)
            fs = fullShader(frag)
            val f = createShaderFromPath(if (fs.endsWith(".frag")) fs else "$fs.frag", fragTransform)

            program += v
            program += f

            program.link()

            if (!program.linkStatus) System.err.println("Linker failure: ${program.infoLog}")

            program -= v
            program -= f
            v.delete()
            f.delete()

            return program
        }

        @JvmStatic
        fun fromRoot(root: String, vert: String, geom: String, frag: String): GlslProgram {

            val program = GlslProgram()

            fun fullShader(s: String) = when {
                root.endsWith('/') || root.endsWith('\\') -> "$root$s"
                else -> "$root/$s"
            }

            var fs = fullShader(vert)
            val v = createShaderFromPath(if (fs.endsWith(".vert")) fs else "$fs.vert")
            fs = fullShader(geom)
            val g = createShaderFromPath(if (fs.endsWith(".geom")) fs else "$fs.geom")
            fs = fullShader(frag)
            val f = createShaderFromPath(if (fs.endsWith(".frag")) fs else "$fs.frag")

            program += v
            program += g
            program += f

            program.link()

            if (!program.linkStatus) System.err.println("Linker failure: ${program.infoLog}")

            program -= v
            program -= g
            program -= f
            v.delete()
            g.delete()
            f.delete()

            return program
        }

        @JvmStatic
        fun fromRoot(root: String,
                     vert: String, vertTransform: ((String) -> String)? = null,
                     geom: String, geomTransform: ((String) -> String)? = null,
                     frag: String, fragTransform: ((String) -> String)? = null): GlslProgram {

            val program = GlslProgram()

            fun fullShader(s: String) = when {
                root.endsWith('/') || root.endsWith('\\') -> "$root$s"
                else -> "$root/$s"
            }

            var fs = fullShader(vert)
            val v = createShaderFromPath(if (fs.endsWith(".vert")) fs else "$fs.vert", vertTransform)
            fs = fullShader(geom)
            val g = createShaderFromPath(if (fs.endsWith(".geom")) fs else "$fs.geom", geomTransform)
            fs = fullShader(frag)
            val f = createShaderFromPath(if (fs.endsWith(".frag")) fs else "$fs.frag", fragTransform)

            program += v
            program += g
            program += f

            program.link()

            if (!program.linkStatus) System.err.println("Linker failure: ${program.infoLog}")

            program -= v
            program -= g
            program -= f
            v.delete()
            g.delete()
            f.delete()

            return program
        }

        @Throws(Error::class)
        fun createShaderFromSource(source: String, type: Int): GLshader {

            val shader = GLshader.create(ShaderType(type)).apply {
                source(source)
                compile()
            }
            if (!shader.compileStatus)
                throw Error(shader.infoLog)

            return shader
        }

//        fun fromSources(vertSrc: Array<String>, fragSrc: Array<String>, geomSrc: Array<String>? = null): GlslProgram {
//
//            val program = GlslProgram()
//
//            val shaders = arrayListOf(createShaderFromSource(vertSrc, GL20.GL_VERTEX_SHADER), createShaderFromSource(fragSrc, GL20.GL_FRAGMENT_SHADER))
//            geomSrc?.let { shaders += createShaderFromSource(it, GL32.GL_GEOMETRY_SHADER) }
//
//            shaders.forEach { GL20.glAttachShader(program.name, it) }
//
//            program.link()
//
//            if (GL20.glGetProgrami(program.name, GL20.GL_LINK_STATUS) == GL11.GL_FALSE)
//                System.err.println("Linker failure: ${GL20.glGetProgramInfoLog(program.name)}")
//
//            shaders.forEach {
//                GL20.glDetachShader(program.name, it)
//                GL20.glDeleteShader(it)
//            }
//
//            return program
//        }

        @Throws(Error::class)
        fun createShaderFromSource(source: Array<String>, type: Int): GLshader {

            val shader = GLshader.create(ShaderType(type)).apply {
                source(*source)
                compile()
            }
            if (!shader.compileStatus)
                throw Error(shader.infoLog)

            return shader
        }

        @Throws(Exception::class)
        fun createShaderFromPath(path: String, transform: ((String) -> String)? = null): GLshader {

            val lines = ClassLoader.getSystemResourceAsStream(path).use {
                InputStreamReader(it).readLines()
            }

            var source = ""
            lines.forEach {
                source +=
                        if (it.startsWith("#include "))
                            parseInclude(path.substringBeforeLast('/'), it.substring("#include ".length).trim())
                        else it
                source += '\n'
            }

            try {
                return createShaderFromSource(transform?.invoke(source) ?: source, path.type)
            } catch (err: Exception) {
                throw Error("Compiler failure in ${path.substringAfterLast('/')} shader: ${err.message}")
            }
        }

        private fun parseInclude(root: String, shader: String): String {
            if (shader.startsWith('"') && shader.endsWith('"'))
                shader.substring(1, shader.length - 1)
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
                else -> throw Error("invalid shader extension")
            }

        private fun String.isShaderPath() = when (substringAfterLast('.')) {
            "vert", "tesc", "tese", "geom", "frag", "comp" -> true
            else -> false
        }

        private val shaderExtensions = arrayOf(".vert", ".tesc", ".tese", ".geom", ".frag", ".comp")
    }
}