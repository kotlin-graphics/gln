# OpenGL 11

- All the `glEnable` and `glDisable` calls have been completely re-designed since it was mixing several different concepts all together.

Part of the queries are OpenGL capabilities, this is subdivided into extensions, debug settings, limits, values and formats. 

All of this is now under the [`Caps`]() class. Simple instantiate it and then all the capabilities will be read according to the given profile

Also if you want to enable blending you can simply type `blend = true` instead `GL11C.glEnable(GL11C.GL_BLEND)`

Also, comfortable constructs are offered, like:

```
blendFunc(SRC_ALPHA, ONE_MINUS_SRC_ALPHA) {
   // draw
}
```

is the equivalent of:

```
GL11C.glEnable(GL11C.GL_BLEND)
GL11C.glBlendFunc(GL11C.GL_SRC_ALPHA, GL11C.GL_ONE_MINUS_SRC_ALPHA)
// draw
GL11C.glDisable(GL11C.GL_BLEND)
```

For a complete panoramic, check out the [`interface glEnable`]()

- `clearColor()` takes also:
    - a single `Float`, when you want to clear all components to the same value.
    - `java.awt.Color`
    - there is also `var clearColor: Vec4`, that allows to get/set the clear color (same `clearDepth: Double` and `clearStencil: Int`)  

- `var colorMask: Vec4bool`, to set/get colorMask

- instead having to enable/disable and set the cullFace Mode, now `var cullFace: CullFaceMode` has four options: front, back, frontAndBack and 
the new one: off.  Same `var logicOp: LogicOp`, all the common options plus `OFF`

- `depthRange()` accepts two `Double`s or a `Vec2d`

- `drawArrays` now overloads both first (default to `0`) and mode (default to `GL_TRIANGLES`). Given that with draw calls most of parameters
are integers, the `drawMode` is a classic enumerator in order to avoid conflicts having the same jvm signature

- `var drawBuffer: BufferMode`

- `drawElements(count: Int, type: DataType = UNSIGNED_INT, indices: Ptr = Ptr(0L))`

- in order to reduce methods pollution, now methods have a generic `Buffer` parameter instead having a specific method for each type. 
Like `drawElements(indices: Buffer)`

- `var frontFace: FrontFaceDirection`

- you can generate GL resources by simply passing the size of the direct int buffer underneath, like `val textures = genTextures(3)`

- you can grab gl error by reading `val error: ErrorCode`

- `glGetTexLevelParameter` can be now substituted by methods on `GLtexture` element. For example: 

    `gl11.getTexLevelParameterI(target, level, WIDTH)` becomes `texture.width(level: Int = 0)`
    
    Similar `glGetTexParameter (getter) / glTexParameter (setter)`, For example the mag filter querying and setting:
    
    `gl11.getTexParameterI(target, MAG_FILTER)`, `gl11.texParameterI(target, MAG_FILTER, mag)`
    
    Now can be simply read/written as a variable:
    
    `var magFilter: TextureMagFilter` 
    
- calls to verify the gl object, such as `glIsTexture(int: Int): Boolean` are substituted by querying directly the object itself: `texture.valid: Boolean`

- all the texture related calls reduces the number of parameters by:
    - avoid useless parameters, such as border (which must be 0)
    - avoid very rare parameters, such as target on some calls (such as `glTexImage1D`), where the target must be one among `GL_TEXTURE_1D` 
    and `GL_PROXY_TEXTURE_1D` (default is always the non-proxy target). On the same way `2D` and `3D` calls (such as `texImage2D`), will be 
    also offered with a default texture target (`GL_TEXTURE_2D` and `GL_TEXTURE_3D`). 
    - merge all the size parameters into a glm vector, such as:
    
        `width: Int, height: Int` -> `size: Vec2i`  
        `width: Int, height: Int, ` -> `size: Vec2i`