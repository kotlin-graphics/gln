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

glTexBuffer has a mandatory target, so it's inferred 