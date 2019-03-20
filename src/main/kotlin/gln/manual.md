glVertexAttribI1i to vertexAttrib

vertexAttrib also offered for the glm types: vertexAttrib(Vec1i)

void glClampColor(GLenum target, GLenum clamp); 

is simply 

void glClampColor(bool clamp);

glBindRenderbuffer has no target since it's fixed
glRenderbufferStorage has no target since it's fixed
renderbufferStorageMultisample has no target since it's fixed

framebufferTexture*D is offered with a default framebuffer target

glFramebufferRenderbuffer has no renderbuffer target since it's fixed

glTexParameterIiv is glTexParameter with int

glColorMaski is glColorMask with index: Int

colorMask has also rgba: Boolean and Vec4bool overload

glEnablei/glDisablei/glIsEnabledi are found as enable/disable/isEnabled with cap

glGetTransformFeedbackVarying returns size, type and name as a triple

glTexImage2DMultisample becomes texImage2dMS, default target to 2D_MULTISAMPLE

glTexImage3DMultisample becomes texImage3dMS, default target to 2D_MULTISAMPLE_ARRAY

camelHumps variables name

glGetMultisamplefv becomes glGetMultisample, default name GL_SAMPLE_POSITION

glSampleMaski becomes sampleMask with index

TODO FramebufferTarget and FramebufferBindTarget

glFenceSync has a mandatory condition and flags 0, that's why it becomes simply fenceSync()

glClientWaitSync flags has been substituted by a boolean `flushFirst` since you can pass either GL_SYNC_FLUSH_COMMANDS_BIT or 0

glWaitSync, given flags and timeout are currently not used and must be set to zero and the special value GL_TIMEOUT_IGNORED, respectively, they are removed

since glGetSynciv has the only real benefit of being used to query GL_SYNC_STATUS, which can be either GL_SIGNALED or GL_UNSIGNALED, it becomes
isSyncSignaled 