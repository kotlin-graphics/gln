@file:Suppress("NOTHING_TO_INLINE")

package gln

import glm_.vec2.Vec2
import glm_.vec2.Vec2i
import glm_.vec3.Vec3
import glm_.vec4.Vec4
import glm_.vec4.Vec4bool
import glm_.vec4.Vec4i
import kool.adr
import kool.stak
import org.lwjgl.opengl.*
import org.lwjgl.system.MemoryUtil
import java.awt.Color
import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.IntBuffer
import java.nio.ShortBuffer

@Deprecated("use kool instead")
val buf: ByteBuffer = MemoryUtil.memAlloc(256)
@Deprecated("use kool instead")
val bufAd = buf.adr


inline fun glViewport(size: Vec2i) = GL11.glViewport(0, 0, size.x, size.y)
inline fun glViewport(width: Int, height: Int) = GL11.glViewport(0, 0, width, height)
inline fun glViewport(viewport: Vec4i) = GL11.glViewport(viewport.x, viewport.y, viewport.z, viewport.w)

inline fun glScissor(size: Vec2i) = GL11.glScissor(0, 0, size.x, size.y)
inline fun glScissor(scissor: Vec4i) = GL11.glScissor(scissor.x, scissor.y, scissor.z, scissor.w)

inline fun glBlitFramebuffer(size: Vec2i) = GL30.glBlitFramebuffer(
        0, 0, size.x, size.y,
        0, 0, size.x, size.y,
        GL11.GL_COLOR_BUFFER_BIT, GL11.GL_LINEAR)


inline fun glClearColor() = GL11.glClearColor(0f, 0f, 0f, 1f)
inline fun glClearColor(float: Float) = GL11.glClearColor(float, float, float, float)
inline fun glClearColor(color: Color) = GL11.glClearColor(color.red / 255f, color.green / 255f, color.blue / 255f, color.alpha / 255f)
inline fun glClearColor(vec: Vec3) = GL11.glClearColor(vec.x, vec.y, vec.z, 1f)
inline fun glClearColor(vec: Vec3, alpha: Float) = GL11.glClearColor(vec.x, vec.y, vec.z, alpha)
inline fun glClearColor(vec: Vec4) = GL11.glClearColor(vec.x, vec.y, vec.z, vec.w)
inline fun glClearDepthf() = GL41.glClearDepthf(1f)


inline fun glGetVec2(pname: Int): Vec2 = stak.vec2Address { GL30C.nglGetFloatv(pname, it) }

inline fun glGetVec4(pname: Int): Vec4 = stak.vec4Address { GL30C.nglGetFloatv(pname, it) }

inline fun glGetVec2i(pname: Int): Vec2i = stak.vec2iAddress { GL30C.nglGetIntegerv(pname, it) }

inline fun glGetVec4i(pname: Int): Vec4i = stak.vec4iAddress { GL30C.nglGetIntegerv(pname, it) }

inline fun glGetVec4bool(pname: Int): Vec4bool = stak.vec4boolAddress { GL30C.nglGetIntegerv(pname, it) }


@JvmOverloads
fun checkError(location: String = "", throws: Boolean = true): Boolean {

    val error = GL11.glGetError()
    return when (error) {
        GL11.GL_NO_ERROR -> true
        else -> {
            val message = when (error) {
                GL11.GL_INVALID_ENUM -> "GL_INVALID_ENUM"
                GL11.GL_INVALID_VALUE -> "GL_INVALID_VALUE"
                GL11.GL_INVALID_OPERATION -> "GL_INVALID_OPERATION"
                GL30.GL_INVALID_FRAMEBUFFER_OPERATION -> "GL_INVALID_FRAMEBUFFER_OPERATION"
                GL11.GL_OUT_OF_MEMORY -> "GL_OUT_OF_MEMORY"
                GL11.GL_STACK_UNDERFLOW -> "GL_STACK_UNDERFLOW"
                GL11.GL_STACK_OVERFLOW -> "GL_STACK_OVERFLOW"
                else -> throw IllegalStateException()
            }
            if (throws)
                throw Exception("OpenGL Error ($message) at $location")
            else
                System.err.println(message)
            false
        }
    }
}

val VERSION = "0.4.7"

object gl11 : gl11i {

    // --- [ glGetTexLevelParameter ] ---

    /**
     * Places integer information about texture image parameter {@code name} for level-of-detail {@code level} of the specified {@code target} into {@code params}.
     *
     * @param target the texture image target. One of:<br><table><tr><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link #GL_PROXY_TEXTURE_2D PROXY_TEXTURE_2D}</td><td>{@link GL30#GL_PROXY_TEXTURE_1D_ARRAY PROXY_TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_PROXY_TEXTURE_RECTANGLE PROXY_TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_PROXY_TEXTURE_CUBE_MAP PROXY_TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL40#GL_TEXTURE_CUBE_MAP_ARRAY TEXTURE_CUBE_MAP_ARRAY}</td></tr><tr><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE TEXTURE_2D_MULTISAMPLE}</td><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE_ARRAY TEXTURE_2D_MULTISAMPLE_ARRAY}</td><td>{@link #GL_PROXY_TEXTURE_1D PROXY_TEXTURE_1D}</td><td>{@link GL12#GL_PROXY_TEXTURE_3D PROXY_TEXTURE_3D}</td></tr><tr><td>{@link GL30#GL_PROXY_TEXTURE_2D_ARRAY PROXY_TEXTURE_2D_ARRAY}</td><td>{@link GL40#GL_PROXY_TEXTURE_CUBE_MAP_ARRAY PROXY_TEXTURE_CUBE_MAP_ARRAY}</td><td>{@link GL32#GL_PROXY_TEXTURE_2D_MULTISAMPLE PROXY_TEXTURE_2D_MULTISAMPLE}</td><td>{@link GL32#GL_PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY}</td></tr></table>
     * @param level  the level-of-detail number
     * @param name  the parameter to query. One of:<br><table><tr><td>{@link #GL_TEXTURE_WIDTH TEXTURE_WIDTH}</td><td>{@link #GL_TEXTURE_HEIGHT TEXTURE_HEIGHT}</td><td>{@link GL12#GL_TEXTURE_DEPTH TEXTURE_DEPTH}</td><td>{@link GL32#GL_TEXTURE_SAMPLES TEXTURE_SAMPLES}</td></tr><tr><td>{@link GL32#GL_TEXTURE_FIXED_SAMPLE_LOCATIONS TEXTURE_FIXED_SAMPLE_LOCATIONS}</td><td>{@link #GL_TEXTURE_INTERNAL_FORMAT TEXTURE_INTERNAL_FORMAT}</td><td>{@link #GL_TEXTURE_RED_SIZE TEXTURE_RED_SIZE}</td><td>{@link #GL_TEXTURE_GREEN_SIZE TEXTURE_GREEN_SIZE}</td></tr><tr><td>{@link #GL_TEXTURE_BLUE_SIZE TEXTURE_BLUE_SIZE}</td><td>{@link #GL_TEXTURE_ALPHA_SIZE TEXTURE_ALPHA_SIZE}</td><td>{@link GL14#GL_TEXTURE_DEPTH_SIZE TEXTURE_DEPTH_SIZE}</td><td>{@link GL30#GL_TEXTURE_STENCIL_SIZE TEXTURE_STENCIL_SIZE}</td></tr><tr><td>{@link GL30#GL_TEXTURE_SHARED_SIZE TEXTURE_SHARED_SIZE}</td><td>{@link GL30#GL_TEXTURE_ALPHA_TYPE TEXTURE_ALPHA_TYPE}</td><td>{@link GL30#GL_TEXTURE_DEPTH_TYPE TEXTURE_DEPTH_TYPE}</td><td>{@link GL13#GL_TEXTURE_COMPRESSED TEXTURE_COMPRESSED}</td></tr><tr><td>{@link GL13#GL_TEXTURE_COMPRESSED_IMAGE_SIZE TEXTURE_COMPRESSED_IMAGE_SIZE}</td><td>{@link GL31#GL_TEXTURE_BUFFER_DATA_STORE_BINDING TEXTURE_BUFFER_DATA_STORE_BINDING}</td><td>{@link GL43#GL_TEXTURE_BUFFER_OFFSET TEXTURE_BUFFER_OFFSET}</td><td>{@link GL43#GL_TEXTURE_BUFFER_SIZE TEXTURE_BUFFER_SIZE}</td></tr></table>
     * @param params a scalar or buffer in which to place the returned data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexLevelParameter">Reference Page</a>
     */
    inline fun <reified T : Number> getTexParameter(target: TextureTarget, level: Int, name: GetTexLevelParameter): T =
            when(T::class) {
                Int::class -> stak.intAddress { GL11C.nglGetTexLevelParameteriv(target.i, level, name.i, it) }  as T
                Float::class -> stak.floatAddress { GL11C.nglGetTexLevelParameterfv(target.i, level, name.i, it) }  as T
                else -> throw Exception("Invalid")
            }

    // --- [ glGetTexParameter ] ---

    /**
     * Places integer information about texture image parameter {@code name} for level-of-detail {@code level} of the specified {@code target} into {@code params}.
     *
     * @param target the texture image target. One of:<br><table><tr><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link #GL_PROXY_TEXTURE_2D PROXY_TEXTURE_2D}</td><td>{@link GL30#GL_PROXY_TEXTURE_1D_ARRAY PROXY_TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_PROXY_TEXTURE_RECTANGLE PROXY_TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_PROXY_TEXTURE_CUBE_MAP PROXY_TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL40#GL_TEXTURE_CUBE_MAP_ARRAY TEXTURE_CUBE_MAP_ARRAY}</td></tr><tr><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE TEXTURE_2D_MULTISAMPLE}</td><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE_ARRAY TEXTURE_2D_MULTISAMPLE_ARRAY}</td><td>{@link #GL_PROXY_TEXTURE_1D PROXY_TEXTURE_1D}</td><td>{@link GL12#GL_PROXY_TEXTURE_3D PROXY_TEXTURE_3D}</td></tr><tr><td>{@link GL30#GL_PROXY_TEXTURE_2D_ARRAY PROXY_TEXTURE_2D_ARRAY}</td><td>{@link GL40#GL_PROXY_TEXTURE_CUBE_MAP_ARRAY PROXY_TEXTURE_CUBE_MAP_ARRAY}</td><td>{@link GL32#GL_PROXY_TEXTURE_2D_MULTISAMPLE PROXY_TEXTURE_2D_MULTISAMPLE}</td><td>{@link GL32#GL_PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY}</td></tr></table>
     * @param level  the level-of-detail number
     * @param name  the parameter to query. One of:<br><table><tr><td>{@link #GL_TEXTURE_WIDTH TEXTURE_WIDTH}</td><td>{@link #GL_TEXTURE_HEIGHT TEXTURE_HEIGHT}</td><td>{@link GL12#GL_TEXTURE_DEPTH TEXTURE_DEPTH}</td><td>{@link GL32#GL_TEXTURE_SAMPLES TEXTURE_SAMPLES}</td></tr><tr><td>{@link GL32#GL_TEXTURE_FIXED_SAMPLE_LOCATIONS TEXTURE_FIXED_SAMPLE_LOCATIONS}</td><td>{@link #GL_TEXTURE_INTERNAL_FORMAT TEXTURE_INTERNAL_FORMAT}</td><td>{@link #GL_TEXTURE_RED_SIZE TEXTURE_RED_SIZE}</td><td>{@link #GL_TEXTURE_GREEN_SIZE TEXTURE_GREEN_SIZE}</td></tr><tr><td>{@link #GL_TEXTURE_BLUE_SIZE TEXTURE_BLUE_SIZE}</td><td>{@link #GL_TEXTURE_ALPHA_SIZE TEXTURE_ALPHA_SIZE}</td><td>{@link GL14#GL_TEXTURE_DEPTH_SIZE TEXTURE_DEPTH_SIZE}</td><td>{@link GL30#GL_TEXTURE_STENCIL_SIZE TEXTURE_STENCIL_SIZE}</td></tr><tr><td>{@link GL30#GL_TEXTURE_SHARED_SIZE TEXTURE_SHARED_SIZE}</td><td>{@link GL30#GL_TEXTURE_ALPHA_TYPE TEXTURE_ALPHA_TYPE}</td><td>{@link GL30#GL_TEXTURE_DEPTH_TYPE TEXTURE_DEPTH_TYPE}</td><td>{@link GL13#GL_TEXTURE_COMPRESSED TEXTURE_COMPRESSED}</td></tr><tr><td>{@link GL13#GL_TEXTURE_COMPRESSED_IMAGE_SIZE TEXTURE_COMPRESSED_IMAGE_SIZE}</td><td>{@link GL31#GL_TEXTURE_BUFFER_DATA_STORE_BINDING TEXTURE_BUFFER_DATA_STORE_BINDING}</td><td>{@link GL43#GL_TEXTURE_BUFFER_OFFSET TEXTURE_BUFFER_OFFSET}</td><td>{@link GL43#GL_TEXTURE_BUFFER_SIZE TEXTURE_BUFFER_SIZE}</td></tr></table>
     * @param params a scalar or buffer in which to place the returned data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexLevelParameter">Reference Page</a>
     */
    inline fun <reified T : Number> getTexParameter(target: TextureTarget, name: TexParameter): T =
            when(T::class) {
                Int::class -> stak.intAddress { GL11C.nglGetTexParameteriv(target.i, name.i, it) }  as T
                Float::class -> stak.floatAddress { GL11C.nglGetTexParameterfv(target.i, name.i, it) }  as T
                else -> throw Exception("Invalid")
            }
}

object gl15 : gl11i, gl12i, gl13i, gl14i, gl15i

object gl20 :
        glEnum(),
        gl11i,
        gl12i,
        gl13i,
        gl14i,
        gl15i,
        gl20i

val Buffer.glType: Int
    get() = when (this) {
        is ByteBuffer -> GL11C.GL_UNSIGNED_BYTE
        is ShortBuffer -> GL11C.GL_UNSIGNED_SHORT
        is IntBuffer -> GL11C.GL_UNSIGNED_INT
        else -> throw Exception("unsupported")
    }