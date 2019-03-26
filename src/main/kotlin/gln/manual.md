glVertexAttribI1i to vertexAttrib

vertexAttrib also offered for the glm types: vertexAttrib(Vec1i)

void glClampColor(GLenum target, GLenum clamp); 

is simply 

void glClampColor(bool clamp);

-------------- GL30

since in glClearBufferfi buffer must be GL_DEPTH_STENCIL and drawbuffer 0, these are inferred and the function name becomes clearBufferDepthStencil

glBindRenderbuffer has no target since it's fixed
glRenderbufferStorage has no target since it's fixed
renderbufferStorageMultisample has no target since it's fixed

framebufferTexture*D is offered with a default framebuffer target

glFramebufferRenderbuffer has no renderbuffer target since it's fixed

glTexParameterIiv is glTexParameter with int

glColorMaski is glColorMask with index: Int

colorMask has also rgba: Boolean and Vec4bool overload

glEnablei/glDisablei/glIsEnabledi are found as enable/disable/isEnabled with cap

glTexBuffer has a mandatory target, so it's inferred 

glGetTransformFeedbackVarying returns size, type and name as a triple

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

glSamplerParameteri and glSamplerParameterf becomes simple glSamplerParameter

given glSamplerParameterfv is useful only for setting GL_TEXTURE_BORDER_COLOR, it becomes samplerBorderColor

since glQueryCounter target must be GL_TIMESTAMP, this is inferred

glBlendEquationi become glBlendEquation

glBlendEquationSeparatei becomes glBlendEquationSeparate

glBlendFuncSeparate becomes glBlendFuncSeparatei

GL40C

uniform*d becomes uniform

glUniformMatrix*dv becomes uniform with count automatically set to 1 and transpose to false    

getSubroutine return a Subroutine, which is an inline class of (index, uniformLocation)

since glGetActiveSubroutineUniformiv has only one property which is not int, there is getActiveSubroutineUniform: Int and getActiveSubroutineUniformCompatibles: IntArray for GL_COMPATIBLE_SUBROUTINES.
Also GetActiveSubroutineUniform.COMPATIBLE_SUBROUTINES is marked as deprecated for higher safety

since glPatchParameteri is only meant to be used for GL_PATCH_VERTICES, it becomes   patchVertices(value: Int)

in glBindTransformFeedback target must be GL_TRANSFORM_FEEDBACK, so it's automatically inferred

given glGetQueryIndexediv can be used only for retrieving the current query or the number of counter bits, this is inline reified accordingly

------------- GL41C

glGetShaderPrecisionFormat return ShaderPrecisionFormat, which is a wrapper for range: IntRange and precision: Int

glDepthRangef is simply glDepthRange with `Float`s
glClearDepthf is simply glClearDepth with `Float`

glGetProgramBinary/ glProgramBinary uses a ProgramBinary class holding data and format 

since both the names of programParameter are boolean, programParameter expects a boolean 

glProgramUniform* becomes programUniform
glProgramUniformMatrix* becomes programUniform

glVertexAttribL* becomes vertexAttribL

----------------- GL42

glTexStorage*D has inferred target and glm size constructor

-------------------- GL43

pushDebugGroup incapsulate lamdas into glPushDebugGroup / glPopDebugGroup 


getProgramResource has a version where only a single integer is returned

 fun getProgramResource(program: GlProgram, programInterface: ProgramInterface, index: Int, props: Int): Int 
 
and another one contemplating full arrays

 fun getProgramResource(program: GlProgram, programInterface: ProgramInterface, index: Int, props: IntBuffer, length: IntBuffer?, params: IntBuffer)
 
 
 given that in glTexBufferRange the target must be GL_TEXTURE_BUFFER, this is inferred
 
 -------------- GL44
 
 glClearTexSubImage will default data to null
 
 bindBuffersBase, bindBuffersRange, bindTextures, bindSamplers will default identifier to null
 
 ---------------------- GL45
 
 glGetTransformFeedbacki_v and glGetTransformFeedbacki64_v get merged in one unique inlined reified getTransformFeedback
 
 glClearNamedBufferData last parameter data: Buffer? defaults to null
 
 glGetNamedBufferParameteriv and glGetNamedBufferParameteri64v get merged in one unique inlined reified getBufferParameteri
 
 glNamedFramebufferRenderbuffer becomes framebufferRenderbuffer with GlFramebuffer as first parameter and since renderbuffertarget must be GL_RENDERBUFFER, this is inferred
 
 since in glClearNamedFramebufferfi buffer must be GL_DEPTH_STENCIL and drawbuffer 0, these are inferred and the function name becomes clearBufferDepthStencil

