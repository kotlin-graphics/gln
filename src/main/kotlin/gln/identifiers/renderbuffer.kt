package gln.identifiers

import glm_.vec2.Vec2i
import gln.GetRenderbuffer
import gln.gl
import gln.renderbuffer.GlRenderBufferDsl
import gln.renderbuffer.GlRenderbuffersDsl
import kool.*
import org.lwjgl.opengl.GL30C
import java.nio.IntBuffer


@JvmInline
value class GlRenderbuffers(val names: IntBuffer) {

    val rem: Int
        get() = names.rem

    val adr: Adr
        get() = names.adr

    operator fun get(index: Int): GlRenderbuffer = GlRenderbuffer(names[index])
    operator fun <E : Enum<E>> get(e: E): GlRenderbuffer = GlRenderbuffer(names[e.ordinal])

    // --- [ glCreateRenderbuffers ] ---

    /**
     * Returns {@code n} previously unused renderbuffer names in {@code renderbuffers}, each representing a new renderbuffer object.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateRenderbuffers">Reference Page</a>
     */
    fun create() = gl.createRenderbuffers(this)

    // --- [ glGenRenderbuffers ] ---
    fun gen() = gl.genRenderbuffers(this)

    inline fun gen(block: GlRenderbuffersDsl.() -> Unit) {
        GL30C.glGenRenderbuffers(names)
        GlRenderbuffersDsl.names = names
        GlRenderbuffersDsl.block()
    }
}

fun GlRenderbuffers(size: Int) = GlRenderbuffers(IntBuffer(size))
inline fun <reified E : Enum<E>> GlRenderbuffers(): GlRenderbuffers = GlRenderbuffers(IntBuffer<E>())

@JvmInline
value class GlRenderbuffer(val name: Int = -1) {

    inline fun bind(block: GlRenderBufferDsl.() -> Unit): GlRenderbuffer {
        GL30C.glBindRenderbuffer(GL30C.GL_RENDERBUFFER, name)
        GlRenderBufferDsl.block()
        GL30C.glBindRenderbuffer(GL30C.GL_RENDERBUFFER, 0)
        return this
    }

    fun delete() = GL30C.glDeleteRenderbuffers(name)


    // --- [ glGetNamedRenderbufferParameteriv ] ---

    /**
     * DSA version of {@link GL30C#glGetRenderbufferParameteriv GetRenderbufferParameteriv}.
     *
     * @param name the parameter whose value to retrieve from the renderbuffer bound to {@code target}. One of:<br><table><tr><td>{@link GL30#GL_RENDERBUFFER_WIDTH RENDERBUFFER_WIDTH}</td><td>{@link GL30#GL_RENDERBUFFER_HEIGHT RENDERBUFFER_HEIGHT}</td><td>{@link GL30#GL_RENDERBUFFER_INTERNAL_FORMAT RENDERBUFFER_INTERNAL_FORMAT}</td></tr><tr><td>{@link GL30#GL_RENDERBUFFER_RED_SIZE RENDERBUFFER_RED_SIZE}</td><td>{@link GL30#GL_RENDERBUFFER_GREEN_SIZE RENDERBUFFER_GREEN_SIZE}</td><td>{@link GL30#GL_RENDERBUFFER_BLUE_SIZE RENDERBUFFER_BLUE_SIZE}</td></tr><tr><td>{@link GL30#GL_RENDERBUFFER_ALPHA_SIZE RENDERBUFFER_ALPHA_SIZE}</td><td>{@link GL30#GL_RENDERBUFFER_DEPTH_SIZE RENDERBUFFER_DEPTH_SIZE}</td><td>{@link GL30#GL_RENDERBUFFER_STENCIL_SIZE RENDERBUFFER_STENCIL_SIZE}</td></tr><tr><td>{@link GL30#GL_RENDERBUFFER_SAMPLES RENDERBUFFER_SAMPLES}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetRenderbufferParameter">Reference Page</a>
     */
    infix fun getParameter(name: GetRenderbuffer): Int = gl.getRenderbufferParameter(this, name)

    // --- [ glNamedRenderbufferStorage ] ---

    /**
     * DSA version of {@link GL30C#glRenderbufferStorage RenderbufferStorage}.
     *
     * @param internalFormat the internal format to use for the renderbuffer object's image. Must be a color-renderable, depth-renderable, or stencil-renderable format.
     * @param size           the size of the renderbuffer, in pixels
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glRenderbufferStorage">Reference Page</a>
     */
    fun storage(internalFormat: Int, size: Vec2i) = gl.renderbufferStorage(this, internalFormat, size)

    // --- [ glNamedRenderbufferStorageMultisample ] ---

    /**
     * DSA version of {@link GL30C#glRenderbufferStorageMultisample RenderbufferStorageMultisample}.
     *
     * @param samples        the number of samples to be used for the renderbuffer object's storage
     * @param internalFormat the internal format to use for the renderbuffer object's image. Must be a color-renderable, depth-renderable, or stencil-renderable format.
     * @param width          the width of the renderbuffer, in pixels
     * @param height         the height of the renderbuffer, in pixels
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glRenderbufferStorageMultisample">Reference Page</a>
     */
    fun storageMS(samples: Int, internalFormat: Int, size: Vec2i) = gl.renderbufferStorageMS(this, samples, internalFormat, size)

    companion object {

        // --- [ glCreateRenderbuffers ] ---

        /**
         * Returns {@code n} previously unused renderbuffer names in {@code renderbuffers}, each representing a new renderbuffer object.
         *
         * @see <a target="_blank" href="http://docs.gl/gl4/glCreateRenderbuffers">Reference Page</a>
         */
        fun create(): GlRenderbuffer = gl.createRenderbuffers()

        fun gen(): GlRenderbuffer = GlRenderbuffer(GL30C.glGenRenderbuffers())
    }
}