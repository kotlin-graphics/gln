package gln.transformFeedback

import gln.DrawMode
import gln.gl
import kool.IntBuffer
import kool.adr
import kool.rem
import org.lwjgl.opengl.GL40C
import java.nio.IntBuffer

fun GlTransformFeedbacks(size: Int): GlTransformFeedbacks = GlTransformFeedbacks(IntBuffer(size))

inline class GlTransformFeedbacks(val names: IntBuffer) {
    inline val rem: Int
        get() = names.rem

    inline val adr: Long
        get() = names.adr

    // --- [ glDeleteTransformFeedbacks ] ---

    /**
     * Deletes transform feedback objects.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteTransformFeedbacks">Reference Page</a>
     */
    fun delete() = GL40C.nglDeleteTransformFeedbacks(rem, adr)

    // --- [ glGenTransformFeedbacks ] ---

    /**
     * Reserves transform feedback object names.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenTransformFeedbacks">Reference Page</a>
     */
    fun gen() = GL40C.nglGenTransformFeedbacks(rem, adr)
}

inline class GlTransformFeedback(val name: Int = 0) {

    // --- [ glBindTransformFeedback ] ---

    /**
     * Binds a transform feedback object.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBindTransformFeedback">Reference Page</a>
     */
    fun bind() = GL40C.glBindTransformFeedback(GL40C.GL_TRANSFORM_FEEDBACK, name) // TODO bind {}

    fun unbind() = GL40C.glBindTransformFeedback(GL40C.GL_TRANSFORM_FEEDBACK, 0)

    // --- [ glDeleteTransformFeedbacks ] ---

    /**
     * Deletes transform feedback objects.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteTransformFeedbacks">Reference Page</a>
     */
    fun delete() = gl.deleteTransformFeedbacks(this)

    // --- [ glDrawTransformFeedback ] ---

    /**
     * Render primitives using a count derived from a transform feedback object.
     *
     * @param mode what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link #GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawTransformFeedback">Reference Page</a>
     */
    fun draw(mode: DrawMode) = GL40C.glDrawTransformFeedback(mode.i, name)

    // --- [ glDrawTransformFeedbackStream ] ---

    /**
     * Renders primitives using a count derived from a specifed stream of a transform feedback object.
     *
     * @param mode   what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr><tr><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td><td>{@link #GL_PATCHES PATCHES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_QUADS QUADS}</td></tr><tr><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td></tr></table>
     * @param stream the index of the transform feedback stream from which to retrieve a primitive count
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDrawTransformFeedbackStream">Reference Page</a>
     */
    fun drawStream(mode: DrawMode, stream: Int) = GL40C.glDrawTransformFeedbackStream(mode.i, name, stream)


    // --- [ glIsTransformFeedback ] ---

    /**
     * Determines if a name corresponds to a transform feedback object.
     *
     * @param id a value that may be the name of a transform feedback object
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glIsTransformFeedback">Reference Page</a>
     */
    val isValid: Boolean
        get() = GL40C.glIsTransformFeedback(name)
    val isInvalid: Boolean
        get() = !GL40C.glIsTransformFeedback(name)

    companion object {
        val NULL = GlTransformFeedback(0)
    }
}