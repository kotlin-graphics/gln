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