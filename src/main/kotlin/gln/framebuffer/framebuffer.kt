@file:Suppress("NOTHING_TO_INLINE")

package gln.framebuffer


import glm_.vec2.Vec2i
import glm_.vec4.Vec4i
import gln.*
import gln.identifiers.GlTexture
import gln.renderbuffer.GlRenderbuffer
import kool.IntBuffer
import kool.adr
import kool.get
import kool.rem
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL30.*
import org.lwjgl.opengl.GL30C
import org.lwjgl.opengl.GL32
import java.nio.FloatBuffer
import java.nio.IntBuffer
import kotlin.properties.Delegates
import kotlin.reflect.KMutableProperty0

/**
 * Created by elect on 18/04/17.
 */

var framebufferName: IntBuffer by Delegates.notNull()

val defaultFramebuffer = 0

inline fun glFramebufferRenderbuffer(attachment: Int, renderbuffer: IntArray) = GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, attachment, GL_RENDERBUFFER, renderbuffer[0])
inline fun glFramebufferRenderbuffer(attachment: Int, renderbuffer: IntBuffer) = GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, attachment, GL_RENDERBUFFER, renderbuffer[0])


inline fun glBindFramebuffer(target: Int, framebuffer: Enum<*>){
    if(target == GL30.GL_FRAMEBUFFER) {
        gl.state._framebuffer = GlFramebuffer(framebufferName[framebuffer])
    }
    GL30.glBindFramebuffer(target, framebufferName[framebuffer])
}
inline fun glBindFramebuffer(target: Int, framebuffer: IntBuffer){
    if(target == GL30.GL_FRAMEBUFFER) {
        gl.state._framebuffer = GlFramebuffer(framebuffer[0])
    }
    GL30.glBindFramebuffer(target, framebuffer[0])
}
inline fun glBindFramebuffer(framebuffer: Enum<*>){
    gl.state._framebuffer = GlFramebuffer(framebufferName[framebuffer])
    GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, framebufferName[framebuffer])
}
inline fun glBindFramebuffer(framebuffer: IntBuffer){
    gl.state._framebuffer = GlFramebuffer(framebuffer[0])
    GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, framebuffer[0])
}
inline fun glBindFramebuffer(framebuffer: Int) {
    gl.state._framebuffer = GlFramebuffer(framebuffer)
    GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, framebuffer)
}
inline fun glBindFramebuffer(){
    gl.state._framebuffer = GlFramebuffer.DEFAULT
    GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0)
}


//inline fun glFramebufferTexture2D(target: Int, attachment: Int, textarget: Int, texture: Int) = GL30.glFramebufferTexture2D(target, attachment, textarget, texture, 0) TODO renable without target
inline fun glFramebufferTexture(attachment: Int, texture: Int, level: Int = 0) = GL32.glFramebufferTexture(GL_FRAMEBUFFER, attachment, texture, level)

inline fun glCheckFramebufferStatus() = GL30.glCheckFramebufferStatus(GL_FRAMEBUFFER)


inline class GlFramebuffers(val names: IntBuffer) {
    val rem get() = names.rem
    val adr get() = names.adr

    // --- [ glCreateFramebuffers ] ---

    /**
     * Returns {@code n} previously unused framebuffer names in {@code framebuffers}, each representing a new framebuffer object.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateFramebuffers">Reference Page</a>
     */
    fun create() = gl.createFramebuffers(this)

    companion object {
        // --- [ glCreateFramebuffers ] ---

        infix fun createFramebuffers(size: Int): GlFramebuffers = gl.createFramebuffers(size)
    }
}

fun GlFramebuffers(size: Int) = GlFramebuffers(IntBuffer(size))

inline class GlFramebuffer(val name: Int = -1) {

    fun bind() = glBindFramebuffer(name)

    inline fun bind(block: GlFramebufferDsl.() -> Unit) {
        bind()
        GlFramebufferDsl.name = name
        GlFramebufferDsl.block()
    }

    fun bindRead() = GL30C.glBindFramebuffer(GL30C.GL_READ_FRAMEBUFFER, name)
    fun bindDraw() = GL30C.glBindFramebuffer(GL30C.GL_DRAW_FRAMEBUFFER, name)

    inline fun bound(block: GlFramebufferDsl.() -> Unit): GlFramebuffer {
        bind()
        GlFramebufferDsl.name = name
        GlFramebufferDsl.block()
        glBindFramebuffer()
        return this
    }

    // --- [ glBlitNamedFramebuffer ] --- TODO -> blitTo?

    /**
     * DSA version of {@link GL30C#glBlitFramebuffer BlitFramebuffer}.
     *
     * @param drawFramebuffer the destination framebuffer name
     * @param src             the coordinate of the source rectangle within the read buffer [lower-left, upper-left, lower-right, upper-right]
     * @param dst             the coordinate of the destination rectangle within the write buffer [lower-left, upper-left, lower-right, upper-right]
     * @param mask            the bitwise OR of the flags indicating which buffers are to be copied. One of:<br><table><tr><td>{@link GL11#GL_COLOR_BUFFER_BIT COLOR_BUFFER_BIT}</td><td>{@link GL11#GL_DEPTH_BUFFER_BIT DEPTH_BUFFER_BIT}</td><td>{@link GL11#GL_STENCIL_BUFFER_BIT STENCIL_BUFFER_BIT}</td></tr></table>
     * @param filter          the interpolation to be applied if the image is stretched. One of:<br><table><tr><td>{@link GL11#GL_NEAREST NEAREST}</td><td>{@link GL11#GL_LINEAR LINEAR}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBlitFramebuffer">Reference Page</a>
     */
    fun blit(drawFramebuffer: GlFramebuffer, src: Vec4i, dst: Vec4i, mask: AttribMask, filter: MagFilter) = gl.blitFramebuffer(this, drawFramebuffer, src, dst, mask, filter)

    // --- [ glClearNamedFramebufferiv ] ---

    /**
     * DSA version of {@link GL30C#glClearBufferiv ClearBufferiv}.
     *
     * @param buffer      the buffer to clear. One of:<br><table><tr><td>{@link GL11#GL_COLOR COLOR}</td><td>{@link GL11#GL_STENCIL STENCIL}</td></tr></table>
     * @param drawBuffer  the draw buffer to clear
     * @param value       for color buffers, a pointer to a four-element vector specifying R, G, B and A values to clear the buffer to. For stencil buffers, a pointer to a
     *                    single stencil value to clear the buffer to.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClearFramebuffer">Reference Page</a>
     */
    fun clear(buffer: BufferType, drawBuffer: Int, value: IntBuffer) = gl.clearFramebuffer(this, buffer, drawBuffer, value)

    // --- [ glClearNamedFramebufferuiv ] ---

    /**
     * DSA version of {@link GL30C#glClearBufferuiv ClearBufferuiv}.
     *
     * @param buffer      the buffer to clear. Must be:<br><table><tr><td>{@link GL11#GL_COLOR COLOR}</td></tr></table>
     * @param drawBuffer  the draw buffer to clear
     * @param value       a pointer to a four-element vector specifying R, G, B and A values to clear the buffer to
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClearFramebuffer">Reference Page</a>
     */
    fun clear(buffer: BufferType, drawBuffer: Int, value: UintBuffer) = gl.clearFramebuffer(this, buffer, drawBuffer, value)

    // --- [ glClearNamedFramebufferfv ] ---

    /**
     * DSA version of {@link GL30C#glClearBufferfv ClearBufferfv}.
     *
     * @param buffer      the buffer to clear. One of:<br><table><tr><td>{@link GL11#GL_COLOR COLOR}</td><td>{@link GL11#GL_DEPTH DEPTH}</td></tr></table>
     * @param drawbuffer  the draw buffer to clear
     * @param value       for color buffers, a pointer to a four-element vector specifying R, G, B and A values to clear the buffer to. For depth buffers, a pointer to a
     *                    single depth value to clear the buffer to.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClearFramebuffer">Reference Page</a>
     */
    fun clear(buffer: BufferType, drawbuffer: Int, value: FloatBuffer) = gl.clearFramebuffer(this, buffer, drawbuffer, value)

    // --- [ glClearNamedFramebufferfi ] ---

    /**
     * DSA version of {@link GL30C#glClearBufferfi ClearBufferfi}.
     *
     * @param depth       the depth value to clear the buffer to
     * @param stencil     the stencil value to clear the buffer to
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClearFramebufferfi">Reference Page</a>
     */
    fun clearDepthStencil(depth: Float, stencil: Int) = gl.clearBufferDepthStencil(depth, stencil)

    fun delete() = GL30C.glDeleteFramebuffers(name)

    // --- [ glGetNamedFramebufferAttachmentParameteriv ] ---

    /**
     * DSA version of {@link GL30C#glGetFramebufferAttachmentParameteriv GetFramebufferAttachmentParameteriv}.
     *
     * @param attachment  the attachment within {@code target}. One of:<br><table><tr><td>{@link GL30#GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td><td>{@link GL30#GL_COLOR_ATTACHMENT1 COLOR_ATTACHMENT1}</td><td>{@link GL30#GL_COLOR_ATTACHMENT2 COLOR_ATTACHMENT2}</td><td>{@link GL30#GL_COLOR_ATTACHMENT3 COLOR_ATTACHMENT3}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT4 COLOR_ATTACHMENT4}</td><td>{@link GL30#GL_COLOR_ATTACHMENT5 COLOR_ATTACHMENT5}</td><td>{@link GL30#GL_COLOR_ATTACHMENT6 COLOR_ATTACHMENT6}</td><td>{@link GL30#GL_COLOR_ATTACHMENT7 COLOR_ATTACHMENT7}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT8 COLOR_ATTACHMENT8}</td><td>{@link GL30#GL_COLOR_ATTACHMENT9 COLOR_ATTACHMENT9}</td><td>{@link GL30#GL_COLOR_ATTACHMENT10 COLOR_ATTACHMENT10}</td><td>{@link GL30#GL_COLOR_ATTACHMENT11 COLOR_ATTACHMENT11}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT12 COLOR_ATTACHMENT12}</td><td>{@link GL30#GL_COLOR_ATTACHMENT13 COLOR_ATTACHMENT13}</td><td>{@link GL30#GL_COLOR_ATTACHMENT14 COLOR_ATTACHMENT14}</td><td>{@link GL30#GL_COLOR_ATTACHMENT15 COLOR_ATTACHMENT15}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT16 COLOR_ATTACHMENT16}</td><td>{@link GL30#GL_COLOR_ATTACHMENT17 COLOR_ATTACHMENT17}</td><td>{@link GL30#GL_COLOR_ATTACHMENT18 COLOR_ATTACHMENT18}</td><td>{@link GL30#GL_COLOR_ATTACHMENT19 COLOR_ATTACHMENT19}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT20 COLOR_ATTACHMENT20}</td><td>{@link GL30#GL_COLOR_ATTACHMENT21 COLOR_ATTACHMENT21}</td><td>{@link GL30#GL_COLOR_ATTACHMENT22 COLOR_ATTACHMENT22}</td><td>{@link GL30#GL_COLOR_ATTACHMENT23 COLOR_ATTACHMENT23}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT24 COLOR_ATTACHMENT24}</td><td>{@link GL30#GL_COLOR_ATTACHMENT25 COLOR_ATTACHMENT25}</td><td>{@link GL30#GL_COLOR_ATTACHMENT26 COLOR_ATTACHMENT26}</td><td>{@link GL30#GL_COLOR_ATTACHMENT27 COLOR_ATTACHMENT27}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT28 COLOR_ATTACHMENT28}</td><td>{@link GL30#GL_COLOR_ATTACHMENT29 COLOR_ATTACHMENT29}</td><td>{@link GL30#GL_COLOR_ATTACHMENT30 COLOR_ATTACHMENT30}</td><td>{@link GL30#GL_COLOR_ATTACHMENT31 COLOR_ATTACHMENT31}</td></tr><tr><td>{@link GL30#GL_DEPTH_ATTACHMENT DEPTH_ATTACHMENT}</td><td>{@link GL30#GL_STENCIL_ATTACHMENT STENCIL_ATTACHMENT}</td><td>{@link GL30#GL_DEPTH_STENCIL_ATTACHMENT DEPTH_STENCIL_ATTACHMENT}</td></tr></table>
     * @param name       the parameter of {@code attachment} to query. One of:<br><table><tr><td>{@link GL30#GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE}</td><td>{@link GL30#GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME FRAMEBUFFER_ATTACHMENT_OBJECT_NAME}</td></tr><tr><td>{@link GL30#GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL}</td><td>{@link GL30#GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE}</td></tr><tr><td>{@link GL30#GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER}</td><td>{@link GL30#GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING}</td></tr><tr><td>{@link GL30#GL_FRAMEBUFFER_ATTACHMENT_COMPONENT_TYPE FRAMEBUFFER_ATTACHMENT_COMPONENT_TYPE}</td><td>{@link GL30#GL_FRAMEBUFFER_ATTACHMENT_RED_SIZE FRAMEBUFFER_ATTACHMENT_RED_SIZE}</td></tr><tr><td>{@link GL30#GL_FRAMEBUFFER_ATTACHMENT_GREEN_SIZE FRAMEBUFFER_ATTACHMENT_GREEN_SIZE}</td><td>{@link GL30#GL_FRAMEBUFFER_ATTACHMENT_BLUE_SIZE FRAMEBUFFER_ATTACHMENT_BLUE_SIZE}</td></tr><tr><td>{@link GL30#GL_FRAMEBUFFER_ATTACHMENT_ALPHA_SIZE FRAMEBUFFER_ATTACHMENT_ALPHA_SIZE}</td><td>{@link GL30#GL_FRAMEBUFFER_ATTACHMENT_DEPTH_SIZE FRAMEBUFFER_ATTACHMENT_DEPTH_SIZE}</td></tr><tr><td>{@link GL30#GL_FRAMEBUFFER_ATTACHMENT_STENCIL_SIZE FRAMEBUFFER_ATTACHMENT_STENCIL_SIZE}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetFramebufferAttachmentParameter">Reference Page</a>
     */
    inline fun <reified T> getAttachmentParameter(attachment: Int, name: GetFramebufferAttachment): T = gl.getFramebufferAttachmentParameter(this, attachment, name)

    // --- [ glGetNamedFramebufferParameteriv ] ---

    /**
     * DSA version of {@link GL43C#glGetFramebufferParameteriv GetFramebufferParameteriv}.
     *
     * @param name       a token indicating the parameter to be retrieved. One of:<br><table><tr><td>{@link GL43#GL_FRAMEBUFFER_DEFAULT_WIDTH FRAMEBUFFER_DEFAULT_WIDTH}</td><td>{@link GL43#GL_FRAMEBUFFER_DEFAULT_HEIGHT FRAMEBUFFER_DEFAULT_HEIGHT}</td></tr><tr><td>{@link GL43#GL_FRAMEBUFFER_DEFAULT_LAYERS FRAMEBUFFER_DEFAULT_LAYERS}</td><td>{@link GL43#GL_FRAMEBUFFER_DEFAULT_SAMPLES FRAMEBUFFER_DEFAULT_SAMPLES}</td></tr><tr><td>{@link GL43#GL_FRAMEBUFFER_DEFAULT_FIXED_SAMPLE_LOCATIONS FRAMEBUFFER_DEFAULT_FIXED_SAMPLE_LOCATIONS}</td></tr></table>
     * @param params      a variable to receive the value of the parameter named {@code name}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetFramebufferParameter">Reference Page</a>
     */
    infix inline fun <reified T> getParameter(name: FramebufferParameter): T = gl.getFramebufferParameter(this, name)

    // --- [ glInvalidateFramebuffer ] ---

    /**
     * Invalidate the content some or all of a framebuffer object's attachments.
     *
     * @param target      the target to which the framebuffer is attached. One of:<br><table><tr><td>{@link GL30#GL_FRAMEBUFFER FRAMEBUFFER}</td><td>{@link GL30#GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER}</td><td>{@link GL30#GL_READ_FRAMEBUFFER READ_FRAMEBUFFER}</td></tr></table>
     * @param attachments the address of an array identifying the attachments to be invalidated
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glInvalidateFramebuffer">Reference Page</a>
     */
    fun invalidate(target: FramebufferTarget, vararg attachments: Int) = gl.invalidateFramebuffer(target, *attachments)

    /**
     * Invalidate the content some or all of a framebuffer object's attachments.
     *
     * @param target the target to which the framebuffer is attached. One of:<br><table><tr><td>{@link GL30#GL_FRAMEBUFFER FRAMEBUFFER}</td><td>{@link GL30#GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER}</td><td>{@link GL30#GL_READ_FRAMEBUFFER READ_FRAMEBUFFER}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glInvalidateFramebuffer">Reference Page</a>
     */
    fun invalidate(target: FramebufferTarget, attachment: Int) = gl.invalidateFramebuffer(target, attachment)

    // --- [ glInvalidateNamedFramebufferData ] ---

    /**
     * DSA version of {@link GL43C#glInvalidateFramebuffer InvalidateFramebuffer}.
     *
     * @param attachments the address of an array identifying the attachments to be invalidated
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glInvalidateFramebufferData">Reference Page</a>
     */
    fun invalidateData(attachments: IntBuffer) = gl.invalidateFramebufferData(this, attachments)

    /**
     * DSA version of {@link GL43C#glInvalidateFramebuffer InvalidateFramebuffer}.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glInvalidateFramebufferData">Reference Page</a>
     */
    infix fun invalidateData(attachment: Int) = gl.invalidateFramebufferData(this, attachment)

    // --- [ glInvalidateNamedFramebufferSubData ] ---

    /**
     * DSA version of {@link GL43C#glInvalidateSubFramebuffer InvalidateSubFramebuffer}.
     *
     * @param attachments an array identifying the attachments to be invalidated
     * @param offset      the offset of the region to be invalidated
     * @param size        the size of the region to be invalidated
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glInvalidateFramebufferSubData">Reference Page</a>
     */
    fun invalidateSubData(attachments: IntBuffer, offset: Vec2i, size: Vec2i) = gl.invalidateFramebufferSubData(this, attachments, offset, size)

    /**
     * DSA version of {@link GL43C#glInvalidateSubFramebuffer InvalidateSubFramebuffer}.
     *
     * @param offset      the offset of the region to be invalidated
     * @param size        the size of the region to be invalidated
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glInvalidateFramebufferSubData">Reference Page</a>
     */
    fun invalidateSubData(attachment: Int, offset: Vec2i, size: Vec2i) = gl.invalidateFramebufferSubData(this, attachment, offset, size)

    // --- [ glInvalidateSubFramebuffer ] ---

    /**
     * Invalidates the content of a region of some or all of a framebuffer object's attachments.
     *
     * @param target      the target to which the framebuffer is attached. One of:<br><table><tr><td>{@link GL30#GL_FRAMEBUFFER FRAMEBUFFER}</td><td>{@link GL30#GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER}</td><td>{@link GL30#GL_READ_FRAMEBUFFER READ_FRAMEBUFFER}</td></tr></table>
     * @param attachments an array identifying the attachments to be invalidated
     * @param offset      the offset of the region to be invalidated
     * @param size        the size of the region to be invalidated
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glInvalidateSubFramebuffer">Reference Page</a>
     */
    fun invalidateSubFramebuffer(target: FramebufferTarget, offset: Vec2i, size: Vec2i, vararg attachments: Int) = gl.invalidateSubFramebuffer(target, offset, size, *attachments)

    /**
     * Invalidates the content of a region of some or all of a framebuffer object's attachments.
     *
     * @param target the target to which the framebuffer is attached. One of:<br><table><tr><td>{@link GL30#GL_FRAMEBUFFER FRAMEBUFFER}</td><td>{@link GL30#GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER}</td><td>{@link GL30#GL_READ_FRAMEBUFFER READ_FRAMEBUFFER}</td></tr></table>
     * @param offset the offset of the region to be invalidated
     * @param size   the size of the region to be invalidated
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glInvalidateSubFramebuffer">Reference Page</a>
     */
    fun invalidateSubFramebuffer(target: FramebufferTarget, offset: Vec2i, size: Vec2i, attachment: Int) = gl.invalidateSubFramebuffer(target, offset, size, attachment)

    // --- [ glNamedFramebufferDrawBuffer ] ---

    /**
     * DSA version of {@link GL11C#glDrawBuffer DrawBuffer}.
     *
     * @param buf         the color buffer to draw to. One of:<br><table><tr><td>{@link GL11#GL_NONE NONE}</td><td>{@link GL11#GL_FRONT_LEFT FRONT_LEFT}</td><td>{@link GL11#GL_FRONT_RIGHT FRONT_RIGHT}</td><td>{@link GL11#GL_BACK_LEFT BACK_LEFT}</td><td>{@link GL11#GL_BACK_RIGHT BACK_RIGHT}</td><td>{@link GL11#GL_FRONT FRONT}</td><td>{@link GL11#GL_BACK BACK}</td><td>{@link GL11#GL_LEFT LEFT}</td></tr><tr><td>{@link GL11#GL_RIGHT RIGHT}</td><td>{@link GL11#GL_FRONT_AND_BACK FRONT_AND_BACK}</td><td>{@link GL30#GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td><td>GL30.GL_COLOR_ATTACHMENT[1-15]</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferDrawBuffer">Reference Page</a>
     */
    infix fun drawBuffer(buf: Int) = gl.framebufferDrawBuffer(this, buf)

    // --- [ glNamedFramebufferDrawBuffers ] ---

    /**
     * DSA version of {@link GL20C#glDrawBuffers DrawBuffers}.
     *
     * @param bufs        an array of symbolic constants specifying the buffers into which fragment colors or data values will be written. One of:<br><table><tr><td>{@link GL11#GL_NONE NONE}</td><td>{@link GL11#GL_FRONT_LEFT FRONT_LEFT}</td><td>{@link GL11#GL_FRONT_RIGHT FRONT_RIGHT}</td><td>{@link GL11#GL_BACK_LEFT BACK_LEFT}</td><td>{@link GL11#GL_BACK_RIGHT BACK_RIGHT}</td><td>{@link GL30#GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td></tr><tr><td>GL30.GL_COLOR_ATTACHMENT[1-15]</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferDrawBuffers">Reference Page</a>
     */
    infix fun drawBuffers(bufs: IntBuffer) = gl.framebufferDrawBuffers(this, bufs)

    /**
     * DSA version of {@link GL20C#glDrawBuffers DrawBuffers}. TODO rename to framebufferDrawBuffer?
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferDrawBuffers">Reference Page</a>
     */
    infix fun drawBuffers(buf: Int) = gl.framebufferDrawBuffers(this, buf)

    // --- [ glNamedFramebufferParameteri ] ---

    /**
     * DSA version of {@link GL43C#glFramebufferParameteri FramebufferParameteri}.
     *
     * @param name       a token indicating the parameter to be modified. One of:<br><table><tr><td>{@link GL43#GL_FRAMEBUFFER_DEFAULT_WIDTH FRAMEBUFFER_DEFAULT_WIDTH}</td><td>{@link GL43#GL_FRAMEBUFFER_DEFAULT_HEIGHT FRAMEBUFFER_DEFAULT_HEIGHT}</td></tr><tr><td>{@link GL43#GL_FRAMEBUFFER_DEFAULT_LAYERS FRAMEBUFFER_DEFAULT_LAYERS}</td><td>{@link GL43#GL_FRAMEBUFFER_DEFAULT_SAMPLES FRAMEBUFFER_DEFAULT_SAMPLES}</td></tr><tr><td>{@link GL43#GL_FRAMEBUFFER_DEFAULT_FIXED_SAMPLE_LOCATIONS FRAMEBUFFER_DEFAULT_FIXED_SAMPLE_LOCATIONS}</td></tr></table>
     * @param param       the new value for the parameter named {@code name}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferParameteri">Reference Page</a>
     */
    fun parameter(name: FramebufferParameter, param: Int) = gl.framebufferParameter(this, name, param)

    // --- [ glNamedFramebufferReadBuffer ] ---

    /**
     * DSA version of {@link GL11C#glReadBuffer ReadBuffer}.
     *
     * @param src         the color buffer to read from. One of:<br><table><tr><td>{@link GL11#GL_NONE NONE}</td><td>{@link GL11#GL_FRONT_LEFT FRONT_LEFT}</td><td>{@link GL11#GL_FRONT_RIGHT FRONT_RIGHT}</td><td>{@link GL11#GL_BACK_LEFT BACK_LEFT}</td><td>{@link GL11#GL_BACK_RIGHT BACK_RIGHT}</td><td>{@link GL11#GL_FRONT FRONT}</td><td>{@link GL11#GL_BACK BACK}</td><td>{@link GL11#GL_LEFT LEFT}</td></tr><tr><td>{@link GL11#GL_RIGHT RIGHT}</td><td>{@link GL11#GL_FRONT_AND_BACK FRONT_AND_BACK}</td><td>{@link GL30#GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td><td>GL30.GL_COLOR_ATTACHMENT[1-15]</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferReadBuffer">Reference Page</a>
     */
    infix fun readBuffer(src: Int) = gl.framebufferReadBuffer(this, src)

    // --- [ glNamedFramebufferRenderbuffer ] ---

    /**
     * DSA version of {@link GL30C#glFramebufferRenderbuffer FramebufferRenderbuffer}.
     *
     * @param attachment         the attachment point of the framebuffer. One of:<br><table><tr><td>{@link GL30#GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td><td>{@link GL30#GL_COLOR_ATTACHMENT1 COLOR_ATTACHMENT1}</td><td>{@link GL30#GL_COLOR_ATTACHMENT2 COLOR_ATTACHMENT2}</td><td>{@link GL30#GL_COLOR_ATTACHMENT3 COLOR_ATTACHMENT3}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT4 COLOR_ATTACHMENT4}</td><td>{@link GL30#GL_COLOR_ATTACHMENT5 COLOR_ATTACHMENT5}</td><td>{@link GL30#GL_COLOR_ATTACHMENT6 COLOR_ATTACHMENT6}</td><td>{@link GL30#GL_COLOR_ATTACHMENT7 COLOR_ATTACHMENT7}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT8 COLOR_ATTACHMENT8}</td><td>{@link GL30#GL_COLOR_ATTACHMENT9 COLOR_ATTACHMENT9}</td><td>{@link GL30#GL_COLOR_ATTACHMENT10 COLOR_ATTACHMENT10}</td><td>{@link GL30#GL_COLOR_ATTACHMENT11 COLOR_ATTACHMENT11}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT12 COLOR_ATTACHMENT12}</td><td>{@link GL30#GL_COLOR_ATTACHMENT13 COLOR_ATTACHMENT13}</td><td>{@link GL30#GL_COLOR_ATTACHMENT14 COLOR_ATTACHMENT14}</td><td>{@link GL30#GL_COLOR_ATTACHMENT15 COLOR_ATTACHMENT15}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT16 COLOR_ATTACHMENT16}</td><td>{@link GL30#GL_COLOR_ATTACHMENT17 COLOR_ATTACHMENT17}</td><td>{@link GL30#GL_COLOR_ATTACHMENT18 COLOR_ATTACHMENT18}</td><td>{@link GL30#GL_COLOR_ATTACHMENT19 COLOR_ATTACHMENT19}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT20 COLOR_ATTACHMENT20}</td><td>{@link GL30#GL_COLOR_ATTACHMENT21 COLOR_ATTACHMENT21}</td><td>{@link GL30#GL_COLOR_ATTACHMENT22 COLOR_ATTACHMENT22}</td><td>{@link GL30#GL_COLOR_ATTACHMENT23 COLOR_ATTACHMENT23}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT24 COLOR_ATTACHMENT24}</td><td>{@link GL30#GL_COLOR_ATTACHMENT25 COLOR_ATTACHMENT25}</td><td>{@link GL30#GL_COLOR_ATTACHMENT26 COLOR_ATTACHMENT26}</td><td>{@link GL30#GL_COLOR_ATTACHMENT27 COLOR_ATTACHMENT27}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT28 COLOR_ATTACHMENT28}</td><td>{@link GL30#GL_COLOR_ATTACHMENT29 COLOR_ATTACHMENT29}</td><td>{@link GL30#GL_COLOR_ATTACHMENT30 COLOR_ATTACHMENT30}</td><td>{@link GL30#GL_COLOR_ATTACHMENT31 COLOR_ATTACHMENT31}</td></tr><tr><td>{@link GL30#GL_DEPTH_ATTACHMENT DEPTH_ATTACHMENT}</td><td>{@link GL30#GL_STENCIL_ATTACHMENT STENCIL_ATTACHMENT}</td><td>{@link GL30#GL_DEPTH_STENCIL_ATTACHMENT DEPTH_STENCIL_ATTACHMENT}</td></tr></table>
     * @param renderbuffertarget the renderbuffer target. Must be:<br><table><tr><td>{@link GL30#GL_RENDERBUFFER RENDERBUFFER}</td></tr></table>
     * @param renderbuffer       the name of an existing renderbuffer object of type {@code renderbuffertarget} to attach
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferRenderbuffer">Reference Page</a>
     */
    fun renderbuffer(attachment: Int, renderbuffer: GlRenderbuffer) = gl.framebufferRenderbuffer(this, attachment, renderbuffer)

    // --- [ glCheckNamedFramebufferStatus ] ---

    /**
     * DSA version of {@link GL30C#glCheckFramebufferStatus CheckFramebufferStatus}.
     *
     * @param target      the target of the framebuffer completeness check. One of:<br><table><tr><td>{@link GL30#GL_FRAMEBUFFER FRAMEBUFFER}</td><td>{@link GL30#GL_READ_FRAMEBUFFER READ_FRAMEBUFFER}</td><td>{@link GL30#GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCheckFramebufferStatus">Reference Page</a>
     */
    infix fun checkStatus(target: FramebufferTarget): FramebufferStatus = gl.checkFramebufferStatus(this, target)

    val isComplete: Boolean
            get() = checkStatus(FramebufferTarget.DRAW).isComplete

    // --- [ glNamedFramebufferTexture ] ---

    /**
     * DSA version of {@link GL32C#glFramebufferTexture FramebufferTexture}.
     *
     * @param attachment  the attachment point of the framebuffer
     * @param texture     the texture object to attach to the framebuffer attachment point named by {@code attachment}
     * @param level       the mipmap level of {@code texture} to attach
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferTexture">Reference Page</a>
     */
    fun texture(attachment: Int, texture: GlTexture, level: Int) = gl.framebufferTexture(this, attachment, texture, level)

    // --- [ glNamedFramebufferTextureLayer ] ---

    /**
     * DSA version of {@link GL30C#glFramebufferTextureLayer FramebufferTextureLayer}.
     *
     * @param attachment  the attachment point of the framebuffer. One of:<br><table><tr><td>{@link GL30#GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td><td>{@link GL30#GL_COLOR_ATTACHMENT1 COLOR_ATTACHMENT1}</td><td>{@link GL30#GL_COLOR_ATTACHMENT2 COLOR_ATTACHMENT2}</td><td>{@link GL30#GL_COLOR_ATTACHMENT3 COLOR_ATTACHMENT3}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT4 COLOR_ATTACHMENT4}</td><td>{@link GL30#GL_COLOR_ATTACHMENT5 COLOR_ATTACHMENT5}</td><td>{@link GL30#GL_COLOR_ATTACHMENT6 COLOR_ATTACHMENT6}</td><td>{@link GL30#GL_COLOR_ATTACHMENT7 COLOR_ATTACHMENT7}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT8 COLOR_ATTACHMENT8}</td><td>{@link GL30#GL_COLOR_ATTACHMENT9 COLOR_ATTACHMENT9}</td><td>{@link GL30#GL_COLOR_ATTACHMENT10 COLOR_ATTACHMENT10}</td><td>{@link GL30#GL_COLOR_ATTACHMENT11 COLOR_ATTACHMENT11}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT12 COLOR_ATTACHMENT12}</td><td>{@link GL30#GL_COLOR_ATTACHMENT13 COLOR_ATTACHMENT13}</td><td>{@link GL30#GL_COLOR_ATTACHMENT14 COLOR_ATTACHMENT14}</td><td>{@link GL30#GL_COLOR_ATTACHMENT15 COLOR_ATTACHMENT15}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT16 COLOR_ATTACHMENT16}</td><td>{@link GL30#GL_COLOR_ATTACHMENT17 COLOR_ATTACHMENT17}</td><td>{@link GL30#GL_COLOR_ATTACHMENT18 COLOR_ATTACHMENT18}</td><td>{@link GL30#GL_COLOR_ATTACHMENT19 COLOR_ATTACHMENT19}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT20 COLOR_ATTACHMENT20}</td><td>{@link GL30#GL_COLOR_ATTACHMENT21 COLOR_ATTACHMENT21}</td><td>{@link GL30#GL_COLOR_ATTACHMENT22 COLOR_ATTACHMENT22}</td><td>{@link GL30#GL_COLOR_ATTACHMENT23 COLOR_ATTACHMENT23}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT24 COLOR_ATTACHMENT24}</td><td>{@link GL30#GL_COLOR_ATTACHMENT25 COLOR_ATTACHMENT25}</td><td>{@link GL30#GL_COLOR_ATTACHMENT26 COLOR_ATTACHMENT26}</td><td>{@link GL30#GL_COLOR_ATTACHMENT27 COLOR_ATTACHMENT27}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT28 COLOR_ATTACHMENT28}</td><td>{@link GL30#GL_COLOR_ATTACHMENT29 COLOR_ATTACHMENT29}</td><td>{@link GL30#GL_COLOR_ATTACHMENT30 COLOR_ATTACHMENT30}</td><td>{@link GL30#GL_COLOR_ATTACHMENT31 COLOR_ATTACHMENT31}</td></tr><tr><td>{@link GL30#GL_DEPTH_ATTACHMENT DEPTH_ATTACHMENT}</td><td>{@link GL30#GL_STENCIL_ATTACHMENT STENCIL_ATTACHMENT}</td><td>{@link GL30#GL_DEPTH_STENCIL_ATTACHMENT DEPTH_STENCIL_ATTACHMENT}</td></tr></table>
     * @param texture     the texture object to attach to the framebuffer attachment point named by {@code attachment}
     * @param level       the mipmap level of {@code texture} to attach
     * @param layer       the layer of {@code texture} to attach.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferTextureLayer">Reference Page</a>
     */
    fun textureLayer(attachment: Int, texture: GlTexture, level: Int, layer: Int) = gl.framebufferTextureLayer(this, attachment, texture, level, layer)

    companion object {

        val NULL = GlFramebuffer(-1)
        val DEFAULT = GlFramebuffer(0)

        // --- [ glCreateFramebuffers ] ---

        fun create(): GlFramebuffer = gl.createFramebuffers()

        fun gen(): GlFramebuffer = GlFramebuffer(GL30C.glGenFramebuffers())

        fun gen(pFramebuffer: KMutableProperty0<GlFramebuffer>): GlFramebuffer {
            var framebuffer by pFramebuffer
            framebuffer = gen()
            return framebuffer
        }
    }
}