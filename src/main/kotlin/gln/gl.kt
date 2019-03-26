package gln

import gli_.gl
import glm_.BYTES
import glm_.bool
import glm_.vec2.Vec2
import glm_.vec2.Vec2d
import glm_.vec2.Vec2i
import glm_.vec2.Vec2ui
import glm_.vec3.Vec3
import glm_.vec3.Vec3d
import glm_.vec3.Vec3i
import glm_.vec3.Vec3ui
import glm_.vec4.Vec4
import glm_.vec4.Vec4d
import glm_.vec4.Vec4i
import glm_.vec4.Vec4ui
import gln.misc.GlDebugSource
import gln.objects.GlBuffer
import gln.objects.GlProgram
import gln.objects.GlQuery
import gln.objects.GlShader
import gln.transformFeedback.GlTransformFeedback
import kool.IntBuffer
import kool.adr
import kool.lib.toIntArray
import kool.stak
import org.lwjgl.opengl.*
import org.lwjgl.system.MemoryUtil.memGetInt
import unsigned.Uint


object gl :
        glGetSet,
        gl11i, gl12i, gl13i, gl14i, gl15i,
        gl20i, gl21i,
        gl30i, gl31i, gl32i, gl33i,
        gl40i, gl41i, gl42i, gl43i, gl44i, gl45i {

    // --- [ glGet* ] ---

    /**
     * Returns the current integer value of the specified state variable.
     *
     * <p><b>LWJGL note</b>: The state that corresponds to the state variable may be a single value or an array of values. In the case of an array of values,
     * LWJGL will <b>not</b> validate if {@code params} has enough space to store that array. Doing so would introduce significant overhead, as the
     * OpenGL state variables are too many. It is the user's responsibility to avoid JVM crashes by ensuring enough space for the returned values.</p>
     *
     * @param name the state variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetIntegerv">Reference Page</a>
     */
    inline fun <reified T : Number> get(name: Int): T =
            stak { s ->
                when (T::class) {
                    Int::class -> s.intAddress { GL11C.nglGetIntegerv(name, it) } as T
                    Long::class -> s.longAddress { GL32C.nglGetInteger64v(name, it) } as T
                    Float::class -> s.floatAddress { GL11C.nglGetFloatv(name, it) } as T
                    else -> throw Exception("Invalid")
                }
            }

    // --- [ glGetTexLevelParameter ] ---

    /**
     * Places integer information about texture image parameter {@code name} for level-of-detail {@code level} of the specified {@code target} into {@code params}.
     *
     * @param target the texture image target. One of:<br><table><tr><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link #GL_PROXY_TEXTURE_2D PROXY_TEXTURE_2D}</td><td>{@link GL30#GL_PROXY_TEXTURE_1D_ARRAY PROXY_TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_PROXY_TEXTURE_RECTANGLE PROXY_TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_PROXY_TEXTURE_CUBE_MAP PROXY_TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL40#GL_TEXTURE_CUBE_MAP_ARRAY TEXTURE_CUBE_MAP_ARRAY}</td></tr><tr><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE TEXTURE_2D_MULTISAMPLE}</td><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE_ARRAY TEXTURE_2D_MULTISAMPLE_ARRAY}</td><td>{@link #GL_PROXY_TEXTURE_1D PROXY_TEXTURE_1D}</td><td>{@link GL12#GL_PROXY_TEXTURE_3D PROXY_TEXTURE_3D}</td></tr><tr><td>{@link GL30#GL_PROXY_TEXTURE_2D_ARRAY PROXY_TEXTURE_2D_ARRAY}</td><td>{@link GL40#GL_PROXY_TEXTURE_CUBE_MAP_ARRAY PROXY_TEXTURE_CUBE_MAP_ARRAY}</td><td>{@link GL32#GL_PROXY_TEXTURE_2D_MULTISAMPLE PROXY_TEXTURE_2D_MULTISAMPLE}</td><td>{@link GL32#GL_PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY}</td></tr></table>
     * @param level  the level-of-detail number
     * @param name  the parameter to query. One of:<br><table><tr><td>{@link #GL_TEXTURE_WIDTH TEXTURE_WIDTH}</td><td>{@link #GL_TEXTURE_HEIGHT TEXTURE_HEIGHT}</td><td>{@link GL12#GL_TEXTURE_DEPTH TEXTURE_DEPTH}</td><td>{@link GL32#GL_TEXTURE_SAMPLES TEXTURE_SAMPLES}</td></tr><tr><td>{@link GL32#GL_TEXTURE_FIXED_SAMPLE_LOCATIONS TEXTURE_FIXED_SAMPLE_LOCATIONS}</td><td>{@link #GL_TEXTURE_INTERNAL_FORMAT TEXTURE_INTERNAL_FORMAT}</td><td>{@link #GL_TEXTURE_RED_SIZE TEXTURE_RED_SIZE}</td><td>{@link #GL_TEXTURE_GREEN_SIZE TEXTURE_GREEN_SIZE}</td></tr><tr><td>{@link #GL_TEXTURE_BLUE_SIZE TEXTURE_BLUE_SIZE}</td><td>{@link #GL_TEXTURE_ALPHA_SIZE TEXTURE_ALPHA_SIZE}</td><td>{@link GL14#GL_TEXTURE_DEPTH_SIZE TEXTURE_DEPTH_SIZE}</td><td>{@link GL30#GL_TEXTURE_STENCIL_SIZE TEXTURE_STENCIL_SIZE}</td></tr><tr><td>{@link GL30#GL_TEXTURE_SHARED_SIZE TEXTURE_SHARED_SIZE}</td><td>{@link GL30#GL_TEXTURE_ALPHA_TYPE TEXTURE_ALPHA_TYPE}</td><td>{@link GL30#GL_TEXTURE_DEPTH_TYPE TEXTURE_DEPTH_TYPE}</td><td>{@link GL13#GL_TEXTURE_COMPRESSED TEXTURE_COMPRESSED}</td></tr><tr><td>{@link GL13#GL_TEXTURE_COMPRESSED_IMAGE_SIZE TEXTURE_COMPRESSED_IMAGE_SIZE}</td><td>{@link GL31#GL_TEXTURE_BUFFER_DATA_STORE_BINDING TEXTURE_BUFFER_DATA_STORE_BINDING}</td><td>{@link GL43#GL_TEXTURE_BUFFER_OFFSET TEXTURE_BUFFER_OFFSET}</td><td>{@link GL43#GL_TEXTURE_BUFFER_SIZE TEXTURE_BUFFER_SIZE}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexLevelParameter">Reference Page</a>
     */
    inline fun <reified T> getTexParameter(target: TextureTarget, level: Int, name: GetTexLevelParameter): T =
            stak { s ->
                when (T::class) {
                    Int::class -> s.intAddress { GL11C.nglGetTexLevelParameteriv(target.i, level, name.i, it) } as T
                    Float::class -> s.floatAddress { GL11C.nglGetTexLevelParameterfv(target.i, level, name.i, it) } as T
                    Boolean::class -> s.intAddress { GL11C.nglGetTexLevelParameteriv(target.i, level, name.i, it) }.bool as T
                    else -> throw Exception("Invalid")
                }
            }

    // --- [ glGetTexParameter ] ---

    /**
     * Places integer information about texture image parameter {@code name} for level-of-detail {@code level} of the specified {@code target} into {@code params}.
     *
     * @param target the texture image target. One of:<br><table><tr><td>{@link #GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link #GL_PROXY_TEXTURE_2D PROXY_TEXTURE_2D}</td><td>{@link GL30#GL_PROXY_TEXTURE_1D_ARRAY PROXY_TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_PROXY_TEXTURE_RECTANGLE PROXY_TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_PROXY_TEXTURE_CUBE_MAP PROXY_TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link #GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL40#GL_TEXTURE_CUBE_MAP_ARRAY TEXTURE_CUBE_MAP_ARRAY}</td></tr><tr><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE TEXTURE_2D_MULTISAMPLE}</td><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE_ARRAY TEXTURE_2D_MULTISAMPLE_ARRAY}</td><td>{@link #GL_PROXY_TEXTURE_1D PROXY_TEXTURE_1D}</td><td>{@link GL12#GL_PROXY_TEXTURE_3D PROXY_TEXTURE_3D}</td></tr><tr><td>{@link GL30#GL_PROXY_TEXTURE_2D_ARRAY PROXY_TEXTURE_2D_ARRAY}</td><td>{@link GL40#GL_PROXY_TEXTURE_CUBE_MAP_ARRAY PROXY_TEXTURE_CUBE_MAP_ARRAY}</td><td>{@link GL32#GL_PROXY_TEXTURE_2D_MULTISAMPLE PROXY_TEXTURE_2D_MULTISAMPLE}</td><td>{@link GL32#GL_PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY}</td></tr></table>
     * @param name  the parameter to query. One of:<br><table><tr><td>{@link #GL_TEXTURE_WIDTH TEXTURE_WIDTH}</td><td>{@link #GL_TEXTURE_HEIGHT TEXTURE_HEIGHT}</td><td>{@link GL12#GL_TEXTURE_DEPTH TEXTURE_DEPTH}</td><td>{@link GL32#GL_TEXTURE_SAMPLES TEXTURE_SAMPLES}</td></tr><tr><td>{@link GL32#GL_TEXTURE_FIXED_SAMPLE_LOCATIONS TEXTURE_FIXED_SAMPLE_LOCATIONS}</td><td>{@link #GL_TEXTURE_INTERNAL_FORMAT TEXTURE_INTERNAL_FORMAT}</td><td>{@link #GL_TEXTURE_RED_SIZE TEXTURE_RED_SIZE}</td><td>{@link #GL_TEXTURE_GREEN_SIZE TEXTURE_GREEN_SIZE}</td></tr><tr><td>{@link #GL_TEXTURE_BLUE_SIZE TEXTURE_BLUE_SIZE}</td><td>{@link #GL_TEXTURE_ALPHA_SIZE TEXTURE_ALPHA_SIZE}</td><td>{@link GL14#GL_TEXTURE_DEPTH_SIZE TEXTURE_DEPTH_SIZE}</td><td>{@link GL30#GL_TEXTURE_STENCIL_SIZE TEXTURE_STENCIL_SIZE}</td></tr><tr><td>{@link GL30#GL_TEXTURE_SHARED_SIZE TEXTURE_SHARED_SIZE}</td><td>{@link GL30#GL_TEXTURE_ALPHA_TYPE TEXTURE_ALPHA_TYPE}</td><td>{@link GL30#GL_TEXTURE_DEPTH_TYPE TEXTURE_DEPTH_TYPE}</td><td>{@link GL13#GL_TEXTURE_COMPRESSED TEXTURE_COMPRESSED}</td></tr><tr><td>{@link GL13#GL_TEXTURE_COMPRESSED_IMAGE_SIZE TEXTURE_COMPRESSED_IMAGE_SIZE}</td><td>{@link GL31#GL_TEXTURE_BUFFER_DATA_STORE_BINDING TEXTURE_BUFFER_DATA_STORE_BINDING}</td><td>{@link GL43#GL_TEXTURE_BUFFER_OFFSET TEXTURE_BUFFER_OFFSET}</td><td>{@link GL43#GL_TEXTURE_BUFFER_SIZE TEXTURE_BUFFER_SIZE}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexLevelParameter">Reference Page</a>
     */
    inline fun <reified T> getTexParameter(target: TextureTarget, name: TexParameter): T =
            stak { s ->
                when (T::class) {
                    Int::class -> s.intAddress { GL11C.nglGetTexParameteriv(target.i, name.i, it) } as T
                    Float::class -> s.floatAddress { GL11C.nglGetTexParameterfv(target.i, name.i, it) } as T
                    Boolean::class -> s.intAddress { GL11C.nglGetTexParameteriv(target.i, name.i, it) }.bool as T
                    else -> throw Exception("Invalid")
                }
            }

    /**
     * Conditional rendering.
     *
     * @param id   the name of an occlusion query object whose results are used to determine if the rendering commands are discarded
     * @param mode how {@code glBeginConditionalRender} interprets the results of the occlusion query. One of:<br><table><tr><td>{@link #GL_QUERY_WAIT QUERY_WAIT}</td><td>{@link #GL_QUERY_NO_WAIT QUERY_NO_WAIT}</td><td>{@link #GL_QUERY_BY_REGION_WAIT QUERY_BY_REGION_WAIT}</td></tr><tr><td>{@link #GL_QUERY_BY_REGION_NO_WAIT QUERY_BY_REGION_NO_WAIT}</td><td>{@link GL45#GL_QUERY_WAIT_INVERTED QUERY_WAIT_INVERTED}</td><td>{@link GL45#GL_QUERY_NO_WAIT_INVERTED QUERY_NO_WAIT_INVERTED}</td></tr><tr><td>{@link GL45#GL_QUERY_BY_REGION_WAIT_INVERTED QUERY_BY_REGION_WAIT_INVERTED}</td><td>{@link GL45#GL_QUERY_BY_REGION_NO_WAIT_INVERTED QUERY_BY_REGION_NO_WAIT_INVERTED}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBeginConditionalRender">Reference Page</a>
     */
    inline fun conditionalRender(id: GlQuery, mode: ConditionalMode, block: () -> Unit) {
        beginConditionalRender(id, mode)
        block()
        endConditionalRender()
    }

    // --- [ glGetUniform* ] ---

    /**
     * Returns the value(s) of a uniform variable.
     *
     * @param program  the program object to be queried
     * @param location the location of the uniform variable to be queried
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetUniform">Reference Page</a>
     */
    inline fun <reified T> getUniform(program: GlProgram, location: UniformLocation): T =
            stak { s ->
                when (T::class) {
                    Float::class -> s.floatAddress { GL20C.nglGetUniformfv(program.name, location, it) } as T
                    Vec2::class -> s.vec2Address { GL20C.nglGetUniformfv(program.name, location, it) } as T
                    Vec3::class -> s.vec3Address { GL20C.nglGetUniformfv(program.name, location, it) } as T
                    Vec4::class -> s.vec4Address { GL20C.nglGetUniformfv(program.name, location, it) } as T
                    Int::class -> s.intAddress { GL20C.nglGetUniformiv(program.name, location, it) } as T
                    Vec2i::class -> s.vec2iAddress { GL20C.nglGetUniformfv(program.name, location, it) } as T
                    Vec3i::class -> s.vec3iAddress { GL20C.nglGetUniformfv(program.name, location, it) } as T
                    Vec4i::class -> s.vec4iAddress { GL20C.nglGetUniformfv(program.name, location, it) } as T
                    Uint::class -> Uint(s.intAddress { GL20C.nglGetUniformiv(program.name, location, it) }) as T
                    Vec2ui::class -> s.vec2uiAddress { GL20C.nglGetUniformfv(program.name, location, it) } as T
                    Vec3ui::class -> s.vec3uiAddress { GL20C.nglGetUniformfv(program.name, location, it) } as T
                    Vec4ui::class -> s.vec4uiAddress { GL20C.nglGetUniformfv(program.name, location, it) } as T
                    Double::class -> s.doubleAddress { GL20C.nglGetUniformfv(program.name, location, it) } as T
                    Vec2d::class -> s.vec2dAddress { GL20C.nglGetUniformfv(program.name, location, it) } as T
                    Vec3d::class -> s.vec3dAddress { GL20C.nglGetUniformfv(program.name, location, it) } as T
                    Vec4d::class -> s.vec4dAddress { GL20C.nglGetUniformfv(program.name, location, it) } as T
                    else -> throw Exception("[gln.gl.getUniform] invalid T")
                }
            }

    // --- [ glGetShaderiv ] ---

    /**
     * Returns a parameter from a shader object.
     *
     * @param shader the shader object to be queried
     * @param name  the object parameter. One of:<br><table><tr><td>{@link #GL_SHADER_TYPE SHADER_TYPE}</td><td>{@link #GL_DELETE_STATUS DELETE_STATUS}</td><td>{@link #GL_COMPILE_STATUS COMPILE_STATUS}</td><td>{@link #GL_INFO_LOG_LENGTH INFO_LOG_LENGTH}</td><td>{@link #GL_SHADER_SOURCE_LENGTH SHADER_SOURCE_LENGTH}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetShader">Reference Page</a>
     */
    inline fun <reified T> getShader(shader: GlShader, name: GetShader): T =
            stak { s ->
                when (T::class) {
                    Int::class -> s.intAddress { GL20C.nglGetShaderiv(shader.name, name.i, it) } as T
                    Boolean::class -> s.intAddress { GL20C.nglGetShaderiv(shader.name, name.i, it) }.bool as T
                    ShaderType::class -> ShaderType(s.intAddress { GL20C.nglGetShaderiv(shader.name, name.i, it) }) as T
                    else -> throw Exception("[gln.gl.getShader] invalid T")
                }
            }

    // --- [ glGetProgramiv ] ---

    /**
     * Returns a parameter from a program object.
     *
     * @param program the program object to be queried
     * @param name   the object parameter. One of:<br><table><tr><td>{@link #GL_DELETE_STATUS DELETE_STATUS}</td><td>{@link #GL_LINK_STATUS LINK_STATUS}</td><td>{@link #GL_VALIDATE_STATUS VALIDATE_STATUS}</td></tr><tr><td>{@link #GL_INFO_LOG_LENGTH INFO_LOG_LENGTH}</td><td>{@link #GL_ATTACHED_SHADERS ATTACHED_SHADERS}</td><td>{@link #GL_ACTIVE_ATTRIBUTES ACTIVE_ATTRIBUTES}</td></tr><tr><td>{@link #GL_ACTIVE_ATTRIBUTE_MAX_LENGTH ACTIVE_ATTRIBUTE_MAX_LENGTH}</td><td>{@link #GL_ACTIVE_UNIFORMS ACTIVE_UNIFORMS}</td><td>{@link #GL_ACTIVE_UNIFORM_MAX_LENGTH ACTIVE_UNIFORM_MAX_LENGTH}</td></tr><tr><td>{@link GL30#GL_TRANSFORM_FEEDBACK_BUFFER_MODE TRANSFORM_FEEDBACK_BUFFER_MODE}</td><td>{@link GL30#GL_TRANSFORM_FEEDBACK_VARYINGS TRANSFORM_FEEDBACK_VARYINGS}</td><td>{@link GL30#GL_TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH}</td></tr><tr><td>{@link GL31#GL_ACTIVE_UNIFORM_BLOCKS ACTIVE_UNIFORM_BLOCKS}</td><td>{@link GL31#GL_ACTIVE_UNIFORM_BLOCK_MAX_NAME_LENGTH ACTIVE_UNIFORM_BLOCK_MAX_NAME_LENGTH}</td><td>{@link GL32#GL_GEOMETRY_VERTICES_OUT GEOMETRY_VERTICES_OUT}</td></tr><tr><td>{@link GL32#GL_GEOMETRY_INPUT_TYPE GEOMETRY_INPUT_TYPE}</td><td>{@link GL32#GL_GEOMETRY_OUTPUT_TYPE GEOMETRY_OUTPUT_TYPE}</td><td>{@link GL41#GL_PROGRAM_BINARY_LENGTH PROGRAM_BINARY_LENGTH}</td></tr><tr><td>{@link GL42#GL_ACTIVE_ATOMIC_COUNTER_BUFFERS ACTIVE_ATOMIC_COUNTER_BUFFERS}</td><td>{@link GL43#GL_COMPUTE_WORK_GROUP_SIZE COMPUTE_WORK_GROUP_SIZE}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetProgram">Reference Page</a>
     *
     * Everything except  GL_COMPUTE_WORK_GROUP_SIZE
     */
    inline fun <reified T> getProgram(program: GlProgram, name: GetProgram): T =
            stak { s ->
                when (T::class) {
                    Int::class -> s.intAddress { GL20C.nglGetProgramiv(program.name, name.i, it) } as T
                    Boolean::class -> s.intAddress { GL20C.nglGetProgramiv(program.name, name.i, it) }.bool as T
                    Vec3i::class -> s.vec3iAddress { GL20C.nglGetProgramiv(program.name, name.i, it) } as T // GL_COMPUTE_WORK_GROUP_SIZE
                    else -> throw Exception("[gln.gl.getShader] invalid T")
                }
            }

    /**
     * Queries the T value of an indexed state variable.
     *
     * @param target the indexed state to query
     * @param index  the index of the element being queried
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetBooleani_v">Reference Page</a>
     */
//    inline fun <reified T> get(target: Get, @NativeType("GLuint") int index) { TODO?
//        MemoryStack stack = stackGet(); int stackPointer = stack.getPointer();
//        try {
//            ByteBuffer data = stack.calloc(1);
//            nglGetBooleani_v(target, index, memAddress(data));
//            return data.get(0) != 0;
//        } finally {
//            stack.setPointer(stackPointer);
//        }
//    }

    // --- [ glBeginTransformFeedback ] ---
    // --- [ glEndTransformFeedback ] ---

    /**
     * transform feedback operation.
     *
     * @param primitiveMode the output type of the primitives that will be recorded into the buffer objects that are bound for transform feedback. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBeginTransformFeedback">Reference Page</a>
     */
    inline fun transformFeedback(primitiveMode: PrimitiveMode, block: () -> Unit) {
        GL30C.glBeginTransformFeedback(primitiveMode.i)
        block()
        GL30C.glEndTransformFeedback()
    }

    // --- [ glGetBufferParameteriv / glGetBufferParameteri64v ] ---

    /**
     * Returns the value of a buffer object parameter.
     *
     * @param target the target buffer object. One of:<br><table><tr><td>{@link GL15#GL_ARRAY_BUFFER ARRAY_BUFFER}</td><td>{@link GL15#GL_ELEMENT_ARRAY_BUFFER ELEMENT_ARRAY_BUFFER}</td><td>{@link GL21#GL_PIXEL_PACK_BUFFER PIXEL_PACK_BUFFER}</td><td>{@link GL21#GL_PIXEL_UNPACK_BUFFER PIXEL_UNPACK_BUFFER}</td></tr><tr><td>{@link GL30#GL_TRANSFORM_FEEDBACK_BUFFER TRANSFORM_FEEDBACK_BUFFER}</td><td>{@link GL31#GL_UNIFORM_BUFFER UNIFORM_BUFFER}</td><td>{@link GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link GL31#GL_COPY_READ_BUFFER COPY_READ_BUFFER}</td></tr><tr><td>{@link GL31#GL_COPY_WRITE_BUFFER COPY_WRITE_BUFFER}</td><td>{@link GL40#GL_DRAW_INDIRECT_BUFFER DRAW_INDIRECT_BUFFER}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td><td>{@link GL43#GL_DISPATCH_INDIRECT_BUFFER DISPATCH_INDIRECT_BUFFER}</td></tr><tr><td>{@link GL43#GL_SHADER_STORAGE_BUFFER SHADER_STORAGE_BUFFER}</td><td>{@link ARBIndirectParameters#GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB}</td></tr></table>
     * @param param  the symbolic param of a buffer object parameter. One of:<br><table><tr><td>{@link GL15#GL_BUFFER_SIZE BUFFER_SIZE}</td><td>{@link GL15#GL_BUFFER_USAGE BUFFER_USAGE}</td><td>{@link GL15#GL_BUFFER_ACCESS BUFFER_ACCESS}</td><td>{@link GL15#GL_BUFFER_MAPPED BUFFER_MAPPED}</td></tr><tr><td>{@link GL30#GL_BUFFER_ACCESS_FLAGS BUFFER_ACCESS_FLAGS}</td><td>{@link GL30#GL_BUFFER_MAP_LENGTH BUFFER_MAP_LENGTH}</td><td>{@link GL30#GL_BUFFER_MAP_OFFSET BUFFER_MAP_OFFSET}</td><td>{@link GL44#GL_BUFFER_IMMUTABLE_STORAGE BUFFER_IMMUTABLE_STORAGE}</td></tr><tr><td>{@link GL44#GL_BUFFER_STORAGE_FLAGS BUFFER_STORAGE_FLAGS}</td></tr></table>
     * @param params the requested parameter
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetBufferParameter">Reference Page</a>
     */
    inline fun <reified T> getBufferParameter(target: BufferTarget, param: BufferParameter): T =
            stak { s ->
                when (T::class) {
                    Int::class -> s.intAddress { GL15C.nglGetBufferParameteriv(target.i, param.i, it) } as T
                    Long::class -> s.longAddress { GL32C.nglGetBufferParameteri64v(target.i, param.i, it) } as T
                    Boolean::class -> s.intAddress { GL15C.nglGetBufferParameteriv(target.i, param.i, it) }.bool as T
                    else -> throw Exception("[gln.gl.getBufferParameter(target, ..)] invalid T")
                }
            }

    // ========================= GL31C

    // --- [ glGetActiveUniformBlockiv ] ---

    /**
     * Queries information about an active uniform block.
     *
     * @param program           the name of a program containing the uniform block
     * @param uniformBlockIndex the index of the uniform block within {@code program}
     * @param name             the name of the parameter to query. One of:<br><table><tr><td>{@link #GL_UNIFORM_BLOCK_BINDING UNIFORM_BLOCK_BINDING}</td><td>{@link #GL_UNIFORM_BLOCK_DATA_SIZE UNIFORM_BLOCK_DATA_SIZE}</td></tr><tr><td>{@link #GL_UNIFORM_BLOCK_NAME_LENGTH UNIFORM_BLOCK_NAME_LENGTH}</td><td>{@link #GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS UNIFORM_BLOCK_ACTIVE_UNIFORMS}</td></tr><tr><td>{@link #GL_UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES}</td><td>{@link #GL_UNIFORM_BLOCK_REFERENCED_BY_VERTEX_SHADER UNIFORM_BLOCK_REFERENCED_BY_VERTEX_SHADER}</td></tr><tr><td>{@link #GL_UNIFORM_BLOCK_REFERENCED_BY_GEOMETRY_SHADER UNIFORM_BLOCK_REFERENCED_BY_GEOMETRY_SHADER}</td><td>{@link #GL_UNIFORM_BLOCK_REFERENCED_BY_FRAGMENT_SHADER UNIFORM_BLOCK_REFERENCED_BY_FRAGMENT_SHADER}</td></tr></table>
     * @param params            the address of a variable to receive the result of the query
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetActiveUniformBlock">Reference Page</a>
     */
    inline fun <reified T> getActiveUniformBlockiv(program: GlProgram, uniformBlockIndex: UniformBlockIndex, name: GetActiveUniformBlock): T =
            stak { s ->
                when (T::class) {
                    Int::class -> s.intAddress { GL31C.nglGetActiveUniformBlockiv(program.name, uniformBlockIndex, name.i, it) } as T
                    Boolean::class -> s.intAddress { GL31C.nglGetActiveUniformBlockiv(program.name, uniformBlockIndex, name.i, it) }.bool as T
                    IntArray::class -> {
                        val size = s.intAddress { GL31C.nglGetActiveUniformBlockiv(program.name, uniformBlockIndex, GL31C.GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS, it) }
                        val ints = s.IntBuffer(size)
                        GL31C.nglGetActiveUniformBlockiv(program.name, uniformBlockIndex, GL31C.GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS, ints.adr)
                        ints.toIntArray() as T
                    }
                    else -> throw Exception("[gln.gl.getActiveUniformBlockiv] invalid T")
                }
            }

    // --- [ glGetSamplerParameteriv ] ---

    /**
     * Return the value(s) of a sampler parameter.
     *
     * @param sampler the name of the sampler object from which to retrieve parameters
     * @param pname   the symbolic name of a sampler parameter. One of:<br><table><tr><td>{@link GL11#GL_TEXTURE_WRAP_S TEXTURE_WRAP_S}</td><td>{@link GL11#GL_TEXTURE_WRAP_T TEXTURE_WRAP_T}</td><td>{@link GL12#GL_TEXTURE_WRAP_R TEXTURE_WRAP_R}</td><td>{@link GL11#GL_TEXTURE_MIN_FILTER TEXTURE_MIN_FILTER}</td><td>{@link GL11#GL_TEXTURE_MAG_FILTER TEXTURE_MAG_FILTER}</td></tr><tr><td>{@link GL12#GL_TEXTURE_MIN_LOD TEXTURE_MIN_LOD}</td><td>{@link GL12#GL_TEXTURE_MAX_LOD TEXTURE_MAX_LOD}</td><td>{@link GL14#GL_TEXTURE_LOD_BIAS TEXTURE_LOD_BIAS}</td><td>{@link GL14#GL_TEXTURE_COMPARE_MODE TEXTURE_COMPARE_MODE}</td><td>{@link GL14#GL_TEXTURE_COMPARE_FUNC TEXTURE_COMPARE_FUNC}</td></tr><tr><td>,</td><td>{@link GL11#GL_TEXTURE_BORDER_COLOR TEXTURE_BORDER_COLOR}</td></tr></table>
     * @param params  the sampler parameters
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetSamplerParameter">Reference Page</a>
     */
    inline fun <reified T> getSamplerParameter(target: GlBuffer, param: SamplerParameter): T =
            stak { s ->
                when (T::class) {
                    Int::class -> s.intAddress { GL33C.nglGetSamplerParameteriv(target.name, param.i, it) } as T
                    Float::class -> s.floatAddress { GL33C.nglGetSamplerParameterfv(target.name, param.i, it) } as T
                    Vec4i::class -> s.vec4iAddress { GL33C.nglGetSamplerParameterIiv(target.name, param.i, it) } as T
                    Vec4ui::class -> s.vec4uiAddress { GL33C.nglGetSamplerParameterIiv(target.name, param.i, it) } as T
                    Long::class -> s.longAddress { GL32C.nglGetBufferParameteri64v(target.name, param.i, it) } as T
                    Boolean::class -> s.intAddress { GL15C.nglGetBufferParameteriv(target.name, param.i, it) }.bool as T
                    else -> throw Exception("[gln.gl.getBufferParam] invalid T")
                }
            }

    // --- [ glBeginQueryIndexed / glEndQueryIndexed ] ---

    /**
     * Incapsulate code into Begins/Ends of query object on an indexed target
     *
     * @param target the target type of query object established between {@code glBeginQueryIndexed} and the subsequent {@link #glEndQueryIndexed EndQueryIndexed}. One of:<br><table><tr><td>{@link GL15#GL_SAMPLES_PASSED SAMPLES_PASSED}</td><td>{@link GL30#GL_PRIMITIVES_GENERATED PRIMITIVES_GENERATED}</td><td>{@link GL30#GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN}</td><td>{@link GL33#GL_TIME_ELAPSED TIME_ELAPSED}</td></tr><tr><td>{@link GL33#GL_TIMESTAMP TIMESTAMP}</td><td>{@link GL33#GL_ANY_SAMPLES_PASSED ANY_SAMPLES_PASSED}</td><td>{@link GL43#GL_ANY_SAMPLES_PASSED_CONSERVATIVE ANY_SAMPLES_PASSED_CONSERVATIVE}</td></tr></table>
     * @param index  the index of the query target upon which to begin the query
     * @param id     the name of a query object
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBeginQueryIndexed">Reference Page</a>
     */
    inline fun queryIndexed(target: QueryIndexedTarget, index: Int, id: GlQuery, block: () -> Unit) {
        GL40C.glBeginQueryIndexed(target.i, index, id.name)
        block()
        GL40C.glEndQueryIndexed(target.i, index)
    }

    // --- [ glGetQueryIndexediv ] ---

    /**
     * Returns parameters of an indexed query object target.
     *
     * @param target a query object target. One of:<br><table><tr><td>{@link GL15#GL_SAMPLES_PASSED SAMPLES_PASSED}</td><td>{@link GL30#GL_PRIMITIVES_GENERATED PRIMITIVES_GENERATED}</td><td>{@link GL30#GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN}</td><td>{@link GL33#GL_TIME_ELAPSED TIME_ELAPSED}</td></tr><tr><td>{@link GL33#GL_TIMESTAMP TIMESTAMP}</td><td>{@link GL33#GL_ANY_SAMPLES_PASSED ANY_SAMPLES_PASSED}</td><td>{@link GL43#GL_ANY_SAMPLES_PASSED_CONSERVATIVE ANY_SAMPLES_PASSED_CONSERVATIVE}</td></tr></table>
     * @param index  the index of the query object target
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetQueryIndexed">Reference Page</a>
     */
    inline fun <reified T> getQueryIndexed(target: QueryIndexedTarget, index: Int): T =
            stak { s ->
                when (T::class) {
                    GlQuery::class -> GlQuery(s.intAddress { GL40C.nglGetQueryIndexediv(target.i, index, GL15C.GL_CURRENT_QUERY, it) }) as T
                    Int::class -> s.intAddress { GL40C.nglGetQueryIndexediv(target.i, index, GL15C.GL_QUERY_COUNTER_BITS, it) } as T
                    else -> throw Exception("[gln.gl.glGetQueryIndexediv] invalid T")
                }
            }

    // --- [ glGet*i ] ---

    /**
     * Queries the T value of an indexed state variable.
     *
     * @param target the indexed state to query
     * @param index  the index of the element being queried
     * @param data   a scalar or buffer in which to place the returned data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetFloati_v">Reference Page</a>
     */
    inline fun <reified T> get(target: Int, index: Int): T =
            stak { s ->
                when (T::class) {
                    // --- [ glGetFloati_v ] ---
                    Float::class -> s.floatAddress { GL41C.nglGetFloati_v(target, index, it) } as T
                    // --- [ glGetDoublei_v ] ---
                    Double::class -> s.doubleAddress { GL41C.nglGetDoublei_v(target, index, it) } as T
                    else -> throw Exception("[gln.gl.get] invalid T")
                }
            }

    // ===================== GL42

    // --- [ glGetActiveAtomicCounterBufferiv ] ---

    /**
     * Obtains information about the set of active atomic counter buffers for a program.
     *
     * @param program     the name of a program object for which the command {@link GL20C#glLinkProgram LinkProgram} has been issued in the past
     * @param bufferIndex the index of an active atomic counter buffer
     * @param pname       the parameter to query. One of:<br><table><tr><td>{@link #GL_ATOMIC_COUNTER_BUFFER_DATA_SIZE ATOMIC_COUNTER_BUFFER_DATA_SIZE}</td></tr><tr><td>{@link #GL_ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTERS ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTERS}</td></tr><tr><td>{@link #GL_ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTER_INDICES ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTER_INDICES}</td></tr><tr><td>{@link #GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_VERTEX_SHADER ATOMIC_COUNTER_BUFFER_REFERENCED_BY_VERTEX_SHADER}</td></tr><tr><td>{@link #GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_TESS_CONTROL_SHADER ATOMIC_COUNTER_BUFFER_REFERENCED_BY_TESS_CONTROL_SHADER}</td></tr><tr><td>{@link #GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_TESS_EVALUATION_SHADER ATOMIC_COUNTER_BUFFER_REFERENCED_BY_TESS_EVALUATION_SHADER}</td></tr><tr><td>{@link #GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_GEOMETRY_SHADER ATOMIC_COUNTER_BUFFER_REFERENCED_BY_GEOMETRY_SHADER}</td></tr><tr><td>{@link #GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_FRAGMENT_SHADER ATOMIC_COUNTER_BUFFER_REFERENCED_BY_FRAGMENT_SHADER}</td></tr></table>
     * @param params      a buffer in which to place the returned value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetActiveAtomicCounterBuffer">Reference Page</a>
     */
    inline fun <reified T> getActiveAtomicCounterBufferiv(program: GlProgram, bufferIndex: Int, name: GetActiveAtomicCounterBuffer): T =
            stak { s ->
                when (T::class) {
                    Int::class -> s.intAddress { GL42C.nglGetActiveAtomicCounterBufferiv(program.name, bufferIndex, name.i, it) } as T
                    Boolean::class -> s.intAddress { GL42C.nglGetActiveAtomicCounterBufferiv(program.name, bufferIndex, name.i, it) }.bool as T
                    IntArray::class -> {
                        val size = s.intAddress { GL42C.nglGetActiveAtomicCounterBufferiv(program.name, bufferIndex, GL42C.GL_ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTERS, it) }
                        val ints = s.IntBuffer(size)
                        GL42C.nglGetActiveAtomicCounterBufferiv(program.name, bufferIndex, GL42C.GL_ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTER_INDICES, ints.adr)
                        ints.toIntArray() as T
                    }
                    else -> throw Exception("[gln.gl.getActiveAtomicCounterBufferiv] invalid T")
                }
            }

    // --- [ glGetInternalformativ ] ---

    /**
     * Retrieves information about implementation-dependent support for internal formats.
     *
     * @param target         the usage of the internal format. One of:<br><table><tr><td>{@link GL11#GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link GL11#GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL40#GL_TEXTURE_CUBE_MAP_ARRAY TEXTURE_CUBE_MAP_ARRAY}</td><td>{@link GL30#GL_RENDERBUFFER RENDERBUFFER}</td><td>{@link GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td></tr><tr><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE TEXTURE_2D_MULTISAMPLE}</td><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE_ARRAY TEXTURE_2D_MULTISAMPLE_ARRAY}</td></tr></table>
     * @param internalformat the internal format about which to retrieve information
     * @param name          the type of information to query
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetInternalformat">Reference Page</a>
     */
    inline fun <reified T> getInternalformat(target: TextureTarget, internalformat: gl.InternalFormat, name: GetInternalFormat, bufSize: Int = 1): T =
            stak { s ->
                when (T::class) {
                    Int::class -> s.intAddress { GL42C.nglGetInternalformativ(target.i, internalformat.i, name.i, 1, it) } as T
                    Long::class -> s.longAddress { GL43C.nglGetInternalformati64v(target.i, internalformat.i, name.i, 1, it) } as T
                    IntArray::class -> {
                        val ints = s.nmalloc(4, bufSize * Int.BYTES)
                        GL42C.nglGetInternalformativ(target.i, internalformat.i, name.i, bufSize, ints)
                        IntArray(bufSize) { memGetInt(ints + it * Int.BYTES) } as T
                    }
                    Boolean::class -> s.intAddress { GL42C.nglGetInternalformativ(target.i, internalformat.i, name.i, 1, it) }.bool as T
                    else -> throw Exception("[gln.gl.getInternalformat] invalid T")
                }
            }

    // ================== GL43

    // --- [ glPushDebugGroup / glPopDebugGroup ] ---

    /**
     * Incapsulate into a debug group described by the string {@code message} into the command stream. The value of {@code id} specifies the ID of messages generated.
     * The parameter {@code length} contains the number of characters in {@code message}. If {@code length} is negative, it is implied that {@code message}
     * contains a null terminated string. The message has the specified {@code source} and {@code id}, {@code type} {@link #GL_DEBUG_TYPE_PUSH_GROUP DEBUG_TYPE_PUSH_GROUP}, and
     * {@code severity} {@link #GL_DEBUG_SEVERITY_NOTIFICATION DEBUG_SEVERITY_NOTIFICATION}. The GL will put a new debug group on top of the debug group stack which inherits the control of the
     * volume of debug output of the debug group previously residing on the top of the debug group stack. Because debug groups are strictly hierarchical, any
     * additional control of the debug output volume will only apply within the active debug group and the debug groups pushed on top of the active debug group.
     *
     * <p>An {@link GL11#GL_INVALID_ENUM INVALID_ENUM} error is generated if the value of {@code source} is neither {@link #GL_DEBUG_SOURCE_APPLICATION DEBUG_SOURCE_APPLICATION} nor {@link #GL_DEBUG_SOURCE_THIRD_PARTY DEBUG_SOURCE_THIRD_PARTY}. An
     * {@link GL11#GL_INVALID_VALUE INVALID_VALUE} error is generated if {@code length} is negative and the number of characters in {@code message}, excluding the null-terminator, is
     * not less than the value of {@link #GL_MAX_DEBUG_MESSAGE_LENGTH MAX_DEBUG_MESSAGE_LENGTH}.</p>
     *
     * @param source  the source of the debug message. One of:<br><table><tr><td>{@link #GL_DEBUG_SOURCE_APPLICATION DEBUG_SOURCE_APPLICATION}</td><td>{@link #GL_DEBUG_SOURCE_THIRD_PARTY DEBUG_SOURCE_THIRD_PARTY}</td></tr></table>
     * @param id      the identifier of the message
     * @param message a string containing the message to be sent to the debug output stream
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glPushDebugGroup">Reference Page</a>
     */
    inline fun debugGroup(source: GlDebugSource, id: Int, message: CharSequence, block: () -> Unit) {
        pushDebugGroup(source, id, message)
        block()
        popDebugGroup()
    }

    // --- [ glGetFramebufferParameteriv ] ---

    /**
     * Retrieves a named parameter from a framebuffer.
     *
     * @param target target of the operation. One of:<br><table><tr><td>{@link GL30#GL_READ_FRAMEBUFFER READ_FRAMEBUFFER}</td><td>{@link GL30#GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER}</td><td>{@link GL30#GL_FRAMEBUFFER FRAMEBUFFER}</td></tr></table>
     * @param name  a token indicating the parameter to be retrieved. One of:<br><table><tr><td>{@link #GL_FRAMEBUFFER_DEFAULT_WIDTH FRAMEBUFFER_DEFAULT_WIDTH}</td><td>{@link #GL_FRAMEBUFFER_DEFAULT_HEIGHT FRAMEBUFFER_DEFAULT_HEIGHT}</td></tr><tr><td>{@link #GL_FRAMEBUFFER_DEFAULT_LAYERS FRAMEBUFFER_DEFAULT_LAYERS}</td><td>{@link #GL_FRAMEBUFFER_DEFAULT_SAMPLES FRAMEBUFFER_DEFAULT_SAMPLES}</td></tr><tr><td>{@link #GL_FRAMEBUFFER_DEFAULT_FIXED_SAMPLE_LOCATIONS FRAMEBUFFER_DEFAULT_FIXED_SAMPLE_LOCATIONS}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetFramebufferParameter">Reference Page</a>
     */
    inline fun <reified T> getFramebufferParameter(target: FramebufferTarget, name: FramebufferParameter): T =
            stak { s ->
                when (T::class) {
                    Int::class -> s.intAddress { GL43C.nglGetFramebufferParameteriv(target.i, name.i, it) } as T
                    Boolean::class -> s.intAddress { GL43C.nglGetFramebufferParameteriv(target.i, name.i, it) }.bool as T
                    else -> throw Exception("[gln.gl.getFramebufferParameter] invalid T")
                }
            }

    // ==================== GL45

    // --- [ glGetTransformFeedbackiv ] ---

    /**
     * Returns information about a transform feedback object.
     *
     * @param xfb   zero or the name of an existing transform feedback object
     * @param name the parameter to query. One of:<br><table><tr><td>{@link GL42#GL_TRANSFORM_FEEDBACK_PAUSED TRANSFORM_FEEDBACK_PAUSED}</td><td>{@link GL42#GL_TRANSFORM_FEEDBACK_ACTIVE TRANSFORM_FEEDBACK_ACTIVE}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTransformFeedback">Reference Page</a>
     */
    inline fun <reified T> getTransformFeedback(xfb: GlTransformFeedback, name: GetTransformFeedback): T =
            stak { s ->
                when (T::class) {
                    Int::class -> s.intAddress { GL45C.nglGetTransformFeedbackiv(xfb.name, name.i, it) } as T
                    Boolean::class -> s.intAddress { GL45C.nglGetTransformFeedbackiv(xfb.name, name.i, it) }.bool as T
                    else -> throw Exception("[gln.gl.getTransformFeedback] invalid T")
                }
            }

    // --- [ glGetTransformFeedbacki_v ] ---

    /**
     * Returns information about a transform feedback object.
     *
     * @param xfb   zero or the name of an existing transform feedback object
     * @param name the parameter to query. Must be:<br><table><tr><td>{@link GL30#GL_TRANSFORM_FEEDBACK_BUFFER_BINDING TRANSFORM_FEEDBACK_BUFFER_BINDING}</td></tr></table>
     * @param index the transform feedback stream index
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTransformFeedbacki_v">Reference Page</a>
     */
    inline fun <reified T> getTransformFeedback(xfb: GlTransformFeedback, name: GetTransformFeedbackIndexed, index: Int): T =
            stak { s ->
                when (T::class) {
                    Int::class -> s.intAddress { GL45C.nglGetTransformFeedbacki_v(xfb.name, name.i, index, it) } as T
                    Long::class -> s.longAddress { GL45C.nglGetTransformFeedbacki64_v(xfb.name, name.i, index, it) } as T
                    else -> throw Exception("[gln.gl.getTransformFeedback] invalid T")
                }
            }

    // --- [ glGetNamedBufferParameteriv / glGetNamedBufferParameteri64v ] ---

    /**
     * DSA version of {@link GL15C#glGetBufferParameteriv GetBufferParameteriv}.
     *
     * @param buffer the buffer object name
     * @param pname  the symbolic name of a buffer object parameter. One of:<br><table><tr><td>{@link GL15#GL_BUFFER_SIZE BUFFER_SIZE}</td><td>{@link GL15#GL_BUFFER_USAGE BUFFER_USAGE}</td><td>{@link GL15#GL_BUFFER_ACCESS BUFFER_ACCESS}</td><td>{@link GL15#GL_BUFFER_MAPPED BUFFER_MAPPED}</td></tr><tr><td>{@link GL30#GL_BUFFER_ACCESS_FLAGS BUFFER_ACCESS_FLAGS}</td><td>{@link GL30#GL_BUFFER_MAP_LENGTH BUFFER_MAP_LENGTH}</td><td>{@link GL30#GL_BUFFER_MAP_OFFSET BUFFER_MAP_OFFSET}</td><td>{@link GL44#GL_BUFFER_IMMUTABLE_STORAGE BUFFER_IMMUTABLE_STORAGE}</td></tr><tr><td>{@link GL44#GL_BUFFER_STORAGE_FLAGS BUFFER_STORAGE_FLAGS}</td></tr></table>
     * @param params the requested parameter
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetBufferParameter">Reference Page</a>
     */
    inline fun <reified T> getBufferParameter(buffer: GlBuffer, param: BufferParameter): T =
            stak { s ->
                when (T::class) {
                    Int::class -> s.intAddress { GL45C.nglGetNamedBufferParameteriv(buffer.name, param.i, it) } as T
                    Long::class -> s.longAddress { GL45C.nglGetNamedBufferParameteri64v(buffer.name, param.i, it) } as T
                    Boolean::class -> s.intAddress { GL45C.nglGetNamedBufferParameteriv(buffer.name, param.i, it) }.bool as T
                    else -> throw Exception("[gln.gl.getBufferParameter(buffer, ..)] invalid T")
                }
            }
}