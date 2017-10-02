package gln.renderbuffer

import glm_.vec2.Vec2i
import gln.get
import org.lwjgl.opengl.GL30
import java.nio.IntBuffer
import kotlin.properties.Delegates


var renderbufferName: IntBuffer by Delegates.notNull()


inline fun glRenderbufferStorageMultisample(target: Int, samples: Int, internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorageMultisample(target, samples, internalFormat, size.x, size.y)

inline fun glBindRenderbuffer(target: Int) = GL30.glBindRenderbuffer(target, 0)
inline fun glBindRenderbuffer(target: Int, renderbuffer: IntBuffer) = GL30.glBindRenderbuffer(target, renderbuffer[0])
inline fun glBindRenderbuffer(target: Int, renderbuffer: Enum<*>) = GL30.glBindRenderbuffer(target, renderbufferName[renderbuffer])


inline fun glRenderbufferStorage(internalFormat: Int, width: Int, height: Int) = GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, internalFormat, width, height)
inline fun glRenderbufferStorage(internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, internalFormat, size.x, size.y)
inline fun glRenderbufferStorage(target: Int, internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorage(target, internalFormat, size.x, size.y)