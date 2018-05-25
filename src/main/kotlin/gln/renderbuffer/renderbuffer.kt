package gln.renderbuffer

import glm_.vec2.Vec2i
import gln.get
import org.lwjgl.opengl.GL30
import java.nio.IntBuffer
import kotlin.properties.Delegates


var renderbufferName: IntBuffer by Delegates.notNull()


inline fun glRenderbufferStorageMultisample(samples: Int, internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorageMultisample(GL30.GL_RENDERBUFFER, samples, internalFormat, size.x, size.y)

inline fun glBindRenderbuffer() = GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, 0)
inline fun glBindRenderbuffer(renderbuffer: IntArray) = GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, renderbuffer[0])
inline fun glBindRenderbuffer(renderbuffer: IntBuffer) = GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, renderbuffer[0])
inline fun glBindRenderbuffer(renderbuffer: Enum<*>) = GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, renderbufferName[renderbuffer])


inline fun glRenderbufferStorage(internalFormat: Int, width: Int, height: Int) = GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, internalFormat, width, height)
inline fun glRenderbufferStorage(internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, internalFormat, size.x, size.y)
inline fun glRenderbufferStorage(target: Int, internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorage(target, internalFormat, size.x, size.y)