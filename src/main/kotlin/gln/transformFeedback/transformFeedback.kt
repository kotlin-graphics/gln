package gln.transformFeedback

import gln.DrawMode
import gln.GetTransformFeedback
import gln.gl
import gln.identifiers.GlBuffer
import kool.IntBuffer
import kool.adr
import kool.rem
import org.lwjgl.opengl.GL40C
import java.nio.IntBuffer

fun GlTransformFeedbacks(size: Int): GlTransformFeedbacks = GlTransformFeedbacks(IntBuffer(size))

@JvmInline
value class GlTransformFeedbacks(val names: IntBuffer) {
    inline val rem: Int
        get() = names.rem

    inline val adr: Long
        get() = names.adr

    // --- [ glCreateTransformFeedbacks ] ---

    /**
     * Returns {@code n} previously unused transform feedback object names in {@code ids}, each representing a new state vector.
     *
     * @param ids the buffer in which to return the names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateTransformFeedbacks">Reference Page</a>
     */
    fun create() = gl.createTransformFeedbacks(this)

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

@JvmInline
value class GlTransformFeedback(val name: Int = 0) {

    // --- [ glBindTransformFeedback ] ---

    /**
     * Binds a transform feedback object.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBindTransformFeedback">Reference Page</a>
     */
    fun bind() = GL40C.glBindTransformFeedback(GL40C.GL_TRANSFORM_FEEDBACK, name) // TODO bind {}

    fun unbind() = GL40C.glBindTransformFeedback(GL40C.GL_TRANSFORM_FEEDBACK, 0)

    /**
     * Returns {@code n} previously unused transform feedback object names in {@code ids}, each representing a new state vector.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateTransformFeedbacks">Reference Page</a>
     */
    fun create(): GlTransformFeedback = gl.createTransformFeedbacks()

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

    // --- [ glGetTransformFeedbackiv ] ---

    /**
     * Returns information about a transform feedback object.
     *
     * @param name the parameter to query. One of:<br><table><tr><td>{@link GL42#GL_TRANSFORM_FEEDBACK_PAUSED TRANSFORM_FEEDBACK_PAUSED}</td><td>{@link GL42#GL_TRANSFORM_FEEDBACK_ACTIVE TRANSFORM_FEEDBACK_ACTIVE}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTransformFeedback">Reference Page</a>
     */
    inline infix fun <reified T> get(name: GetTransformFeedback): T = gl.getTransformFeedback(this, name)

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

    // --- [ glTransformFeedbackBufferBase ] ---

    /**
     * Binds a buffer object to a transform feedback object.
     *
     * @param index  the transform feedback stream index
     * @param buffer the name of an existing buffer object
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTransformFeedbackBufferBase">Reference Page</a>
     */
    fun bufferBase(index: Int, buffer: GlBuffer) = gl.transformFeedbackBufferBase(this, index, buffer)

    // --- [ glTransformFeedbackBufferRange ] ---

    /**
     * Binds a region of a buffer object to a transform feedback object.
     *
     * @param index  the transform feedback stream index
     * @param buffer the name of an existing buffer object
     * @param offset the starting offset in basic machine units into the buffer object
     * @param size   the amount of data in machine units
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTransformFeedbackBufferRange">Reference Page</a>
     */
    fun bufferRange(index: Int, buffer: GlBuffer, offset: Int, size: Int) = gl.transformFeedbackBufferRange(this, index, buffer, offset, size)

    companion object {
        val NULL = GlTransformFeedback(0)
    }
}