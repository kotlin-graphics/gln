@file:Suppress("NOTHING_TO_INLINE")

package gln.framebuffer

import gln.Attachment
import gln.FramebufferTarget
import gln.gl
import gln.identifiers.GlFramebuffer
import kool.get
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL30.GL_FRAMEBUFFER
import org.lwjgl.opengl.GL30.GL_RENDERBUFFER
import org.lwjgl.opengl.GL32
import java.nio.IntBuffer
import kotlin.properties.Delegates

/**
 * Created by elect on 18/04/17.
 */

var framebufferName: IntBuffer by Delegates.notNull()

val defaultFramebuffer = 0

inline fun glFramebufferRenderbuffer(attachment: Attachment, renderbuffer: IntArray) = GL30.glFramebufferRenderbuffer(GL_FRAMEBUFFER, attachment.i, GL_RENDERBUFFER, renderbuffer[0])
inline fun glFramebufferRenderbuffer(attachment: Attachment, renderbuffer: IntBuffer) = GL30.glFramebufferRenderbuffer(GL_FRAMEBUFFER, attachment.i, GL_RENDERBUFFER, renderbuffer[0])


inline fun glBindFramebuffer(target: Int, framebuffer: Enum<*>){
    if(target == GL_FRAMEBUFFER)
        gl.state._framebuffer = GlFramebuffer(framebufferName[framebuffer])
    GL30.glBindFramebuffer(target, framebufferName[framebuffer])
}
inline fun glBindFramebuffer(target: Int, framebuffer: IntBuffer){
    if(target == GL_FRAMEBUFFER)
        gl.state._framebuffer = GlFramebuffer(framebuffer[0])
    GL30.glBindFramebuffer(target, framebuffer[0])
}
inline fun glBindFramebuffer(framebuffer: Enum<*>){
    gl.state._framebuffer = GlFramebuffer(framebufferName[framebuffer])
    GL30.glBindFramebuffer(GL_FRAMEBUFFER, framebufferName[framebuffer])
}
inline fun glBindFramebuffer(framebuffer: IntBuffer){
    gl.state._framebuffer = GlFramebuffer(framebuffer[0])
    GL30.glBindFramebuffer(GL_FRAMEBUFFER, framebuffer[0])
}
inline fun glBindFramebuffer(framebuffer: Int) {
    gl.state._framebuffer = GlFramebuffer(framebuffer)
    GL30.glBindFramebuffer(GL_FRAMEBUFFER, framebuffer)
}
inline fun glBindFramebuffer(){
    gl.state._framebuffer = GlFramebuffer.DEFAULT
    GL30.glBindFramebuffer(GL_FRAMEBUFFER, 0)
}


inline fun glFramebufferTexture2D(target: FramebufferTarget, attachment: Attachment, textarget: Int, texture: Int) = GL30.glFramebufferTexture2D(target.i, attachment.i, textarget, texture, 0)
inline fun glFramebufferTexture(attachment: Attachment, texture: Int, level: Int = 0) = GL32.glFramebufferTexture(GL_FRAMEBUFFER, attachment.i, texture, level)

inline fun glCheckFramebufferStatus() = GL30.glCheckFramebufferStatus(GL_FRAMEBUFFER)