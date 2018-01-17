package gln.msaa

import glm_.vec2.Vec2i
import gln.framebuffer.glBindFramebuffer
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL13
import org.lwjgl.opengl.GL30

inline fun withMultiSample(block: () -> Unit) {
    GL11.glEnable(GL13.GL_MULTISAMPLE)
    block()
    GL11.glDisable(GL13.GL_MULTISAMPLE)
}

fun resolveMultiSample(readFbo: Int, readBuffer: Int, drawFbo: Int, drawBuffer: Int, size: Vec2i) {
    GL30.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, readFbo)
    GL11.glReadBuffer(readBuffer)
    GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, drawFbo)
    GL11.glDrawBuffer(drawBuffer)
    glBlitFramebuffer(size)
}

inline fun glBlitFramebuffer(size: Vec2i, mask: Int = GL11.GL_COLOR_BUFFER_BIT, filter: Int = GL11.GL_LINEAR) = GL30.glBlitFramebuffer(0, 0, size.x, size.y, 0, 0, size.x, size.y, mask, filter)