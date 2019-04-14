#### glEnable

As vanilla:
 
`gl.enable(BLEND)` 

or

`gl.blend = true`

The only exception is `GL_CLIP_DISTANCEi`, which shall be written as

`gl.enable(CLIP_DISTANCE0 + i)`

Same logic applies to `gl.disable(*)` and `gl.isEnabled(*)`, that is

`gl.blend = false`

`if (gl.blend) { .. }`

#### glClear*


To set the clearColor, other than the classic 

`fun clearColor(red: Float, green: Float, blue: Float, alpha: Float)`

there are also the following overloads:

`infix fun clearColor(rgba: Float)`

`infix fun clearColor(color: Color)`

`var clearColor: Vec4`

Depth and Stencil are also variables

`gl.clearDepth = 0f`

`gl.clearStencil = 1`

#### ColorMask

colorMask has the following overload

`fun colorMask(rgba: Boolean)`

#### cullFace, logicOp

For both this parameters, OpenGL differentiates between enable/disable and the mode.

gln unifies both, so instead of 

```
glEnable(GL_CULL_FACE)
glCullFace(GL_BACK)
..
glDisable(GL_CULL_FACE)
```

you can directly

```
gl.cullFace = FRONT
..
gl.cullFace = DISABLED
```

or 

```
gl.cullFaced {
   ..
}
```

#### Draw calls

All the draw calls also has an overload with the mode parameter set to `GL_TRIANGLES`

#### drawBuffer(s)

To set the `drawBuffer` use the following variable

`var drawBuffer: BufferMode /*Int*/` 

`drawBuffers` accepts either and `IntArray`:

`fun drawBuffers(bufs: IntBuffer)`

that an `vararg`:

`fun drawBuffers(vararg bufs: Int)`

#### glGetError

`gl.error: ErrorCode`

#### glGetBooleanv, glGetFloatv, ..

`gl.get(): T`

`T` will be inferred by the result

For example:

`val activeTexture: Int = gl.get(ACTIVE_TEXTURE)`

#### glGet*

[`glGet`](http://docs.gl/gl4/glGet) mixes a lot of different types of queries.

`gln` order them in different categories and subcategories:

- caps (ported from g-truc)
 
   - version (MINOR_VERSION, ..)
   
   - extensions (ARB_multitexture, ..)
   
   - debug (CONTEXT_FLAGS, ..)
   
   - limits (MAX_COMPUTE_SHADER_STORAGE_BLOCKS, ..)
   
   - values (SUBPIXEL_BITS, ..)
   
   - formats (COMPRESSED_RGB_S3TC_DXT1_EXT, ..)

- state values (GL_ACTIVE_TEXTURE, ..)


Note that state values are also offered for convenience directly under the `gl` object, ie:

`val activeTexture = gl.activeTexture`

#### glViewport, glScissor

they are offered either via `var`s that via a method accepting `size: Vec2i` (`x` and `y` are always 0 in that case).

```
gl {
   scissorBox: Vec4i
   scissorBox(Vec2i)
   viewport: Vec4i
   viewport(Vec2i)
}
```

### glTexImage1D

Given the target can be only `GL_TEXTURE_1D` or `GL_PROXY_TEXTURE_1D`, it's currently always inferred as the first one.

Same applies to `glCopyTexImage1D` as well.

#### glGetTexLevelParameteriv, glGetTexLevelParameterfv, glGetTexParameteriv, glGetTexParameterfv

They are inline reified, T is inferred. `Int`, `Float` and `Boolean` accepted.   

#### glPointParameter

since there are only a `GL_POINT_FADE_THRESHOLD_SIZE: Float` and a `GL_POINT_SPRITE_COORD_ORIGIN: GlEnum` possible, 
we then have 

`var pointFadeThresholdSize: Float`

`var pointSpriteCoordOrigin: PointSpriteCoordOrigin`

------- 

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

glEnablei/glDisablei/glIsEnabledi are found as enable/disable/isEnabled with index

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
 
 glBindImageTextures becomes bindImages on GlTextures
 
 ---------------------- GL45
 
 glGetTransformFeedbacki_v and glGetTransformFeedbacki64_v get merged in one unique inlined reified getTransformFeedback
 
 glClearNamedBufferData last parameter data: Buffer? defaults to null
 
 glGetNamedBufferParameteriv and glGetNamedBufferParameteri64v get merged in one unique inlined reified getBufferParameteri
 
 glNamedFramebufferRenderbuffer becomes framebufferRenderbuffer with GlFramebuffer as first parameter and since renderbuffertarget must be GL_RENDERBUFFER, this is inferred
 
 since in glClearNamedFramebufferfi buffer must be GL_DEPTH_STENCIL and drawbuffer 0, these are inferred and the function name becomes clearBufferDepthStencil


vertexArrayVertexBuffers, buffers, offset and strides default to null

since glGetVertexArrayiv can query only GL_ELEMENT_ARRAY_BUFFER_BINDING, this is inferred and the name becomes getVertexArrayElementBuffer




TODOs:
- infix what needed
TOCHECK:
- create*s to create* for single objects?
- given the strictly relation between texture name and target, consider switching GlTexture to a class for holding the target?
- we have `var clearDepth: Double`, `var clearDepthF: Float`?