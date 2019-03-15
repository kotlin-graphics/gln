@file:Suppress("NOTHING_TO_INLINE")

package gln.program

import glm_.bool
import gln.ShaderType
import gln.objects.GlProgram
import gln.objects.GlShader
import kool.get
import org.lwjgl.opengl.GL20
import java.io.FileNotFoundException
import java.lang.RuntimeException
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

/** Mainly for Java */
open class GlslProgram(
        @JvmField
        var name: Int) {

    val uniforms = HashMap<String, Int>()

    constructor() : this(GL20.glCreateProgram())

    constructor(vert: GlShader, frag: GlShader, block: GlProgram) : this() {

        plusAssign(vert)
        plusAssign(frag)

        link()

        if (!linkStatus)
            throw Exception("Linker failure: $infoLog")

        minusAssign(vert)
        minusAssign(frag)
        vert.delete()
        frag.delete()
    }

    constructor(vertSrc: String, fragSrc: String) : this() {

        val v = GlShader.createFromSource(ShaderType.VERTEX_SHADER, vertSrc)
        val f = GlShader.createFromSource(ShaderType.FRAGMENT_SHADER, fragSrc)

        plusAssign(v)
        plusAssign(f)

        link()

        if (!linkStatus)
            throw Exception("Linker failure: $infoLog")

        minusAssign(v)
        minusAssign(f)
        v.delete()
        f.delete()
    }

    constructor(vertSrc: String, geomSrc: String, fragSrc: String) : this() {

        val v = GlShader.createFromSource(ShaderType.VERTEX_SHADER, vertSrc)
        val g = GlShader.createFromSource(ShaderType.GEOMETRY_SHADER, geomSrc)
        val f = GlShader.createFromSource(ShaderType.FRAGMENT_SHADER, fragSrc)

        plusAssign(v)
        plusAssign(g)
        plusAssign(f)

        link()

        if (!linkStatus)
            throw Exception("Linker failure: $infoLog")

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




    fun createProgram(shaderList: List<Int>): Int {

        val program = GlslProgram()

        shaderList.forEach { program += it }

        program.link()

        if (!program.linkStatus)
            throw Exception("Linker failure: ${program.infoLog}")

        shaderList.forEach {
            program -= it
            GL20.glDeleteShader(it)
        }

        return program.name
    }

    operator fun plusAssign(shader: Int) = GL20.glAttachShader(name, shader)
    operator fun minusAssign(shader: Int) = GL20.glDetachShader(name, shader)

    operator fun plusAssign(shader: GlShader) = GL20.glAttachShader(name, shader.name)
    operator fun minusAssign(shader: GlShader) = GL20.glDetachShader(name, shader.name)

    fun link() = GL20.glLinkProgram(name)

    val linkStatus: Boolean
        get() = GL20.glGetProgrami(name, GL20.GL_LINK_STATUS).bool

    val infoLog: String
        get() = GL20.glGetProgramInfoLog(name)


    fun use() = GL20.glUseProgram(name)
    fun unuse() = GL20.glUseProgram(0)

    fun <R> use(block: () -> R): R {
        use()
        return block().also { unuse() }
    }


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

            val shaderNames = arrayListOf<GlShader>()

            val program = GlslProgram()

            val fullShader = when {
                root.endsWith('/') || root.endsWith('\\') -> "$root$shader"
                else -> "$root/$shader"
            }

            for (ext in shaderExtensions)
                try {
                    val shaderName = GlShader.createFromPath("$fullShader$ext")
                    program += shaderName
                    shaderNames += shaderName
                } catch (exc: RuntimeException) {
                    throw exc
                } catch (exc: FileNotFoundException) {
                    // silent ignore
                }
            program.link()

            if (!program.linkStatus)
                System.err.println("Linker failure: ${program.infoLog}")     // TODO change to exception

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
            val v = GlShader.createFromPath(if (fs.endsWith(".vert")) fs else "$fs.vert")
            fs = fullShader(frag)
            val f = GlShader.createFromPath(if (fs.endsWith(".frag")) fs else "$fs.frag")

            program += v
            program += f

            program.link()

            if (!program.linkStatus) System.err.println("Linker failure: ${program.infoLog}")     // TODO change to exception

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
            val v = GlShader.createFromPath(if (fs.endsWith(".vert")) fs else "$fs.vert", vertTransform)
            fs = fullShader(frag)
            val f = GlShader.createFromPath(if (fs.endsWith(".frag")) fs else "$fs.frag", fragTransform)

            program += v
            program += f

            program.link()

            if (!program.linkStatus) System.err.println("Linker failure: ${program.infoLog}")     // TODO change to exception

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
            val v = GlShader.createFromPath(if (fs.endsWith(".vert")) fs else "$fs.vert")
            fs = fullShader(geom)
            val g = GlShader.createFromPath(if (fs.endsWith(".geom")) fs else "$fs.geom")
            fs = fullShader(frag)
            val f = GlShader.createFromPath(if (fs.endsWith(".frag")) fs else "$fs.frag")

            program += v
            program += g
            program += f

            program.link()

            if (!program.linkStatus) System.err.println("Linker failure: ${program.infoLog}")     // TODO change to exception

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
            val v = GlShader.createFromPath(if (fs.endsWith(".vert")) fs else "$fs.vert", vertTransform)
            fs = fullShader(geom)
            val g = GlShader.createFromPath(if (fs.endsWith(".geom")) fs else "$fs.geom", geomTransform)
            fs = fullShader(frag)
            val f = GlShader.createFromPath(if (fs.endsWith(".frag")) fs else "$fs.frag", fragTransform)

            program += v
            program += g
            program += f

            program.link()

            if (!program.linkStatus) System.err.println("Linker failure: ${program.infoLog}")     // TODO change to exception

            program -= v
            program -= g
            program -= f
            v.delete()
            g.delete()
            f.delete()

            return program
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
//                System.err.println("Linker failure: ${GL20.glGetProgramInfoLog(program.name)}")     // TODO change to exception
//
//            shaders.forEach {
//                GL20.glDetachShader(program.name, it)
//                GL20.glDeleteShader(it)
//            }
//
//            return program
//        }

        private val shaderExtensions = arrayOf(".vert", ".tesc", ".tese", ".geom", ".frag", ".comp")
    }
}