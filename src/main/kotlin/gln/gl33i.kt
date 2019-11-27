package gln

import glm_.vec4.Vec4
import glm_.vec4.Vec4i
import glm_.vec4.Vec4ui
import gln.identifiers.GlProgram
import gln.sampler.GlSampler
import gln.sampler.GlSamplers
import kool.Stack
import org.lwjgl.opengl.GL11C
import org.lwjgl.opengl.GL33C
import kotlin.reflect.KMutableProperty0

/**
 * The OpenGL functionality up to version 3.3. Includes only Core Profile symbols.
 *
 * <p>OpenGL 3.3 implementations support revision 3.30 of the OpenGL Shading Language.</p>
 *
 * <p>Extensions promoted to core in this release:</p>
 *
 * <ul>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_shader_bit_encoding.txt">ARB_shader_bit_encoding</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_blend_func_extended.txt">ARB_blend_func_extended</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_explicit_attrib_location.txt">ARB_explicit_attrib_location</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_occlusion_query2.txt">ARB_occlusion_query2</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_sampler_objects.txt">ARB_sampler_objects</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_texture_rgb10_a2ui.txt">ARB_texture_rgb10_a2ui</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_texture_swizzle.txt">ARB_texture_swizzle</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_timer_query.txt">ARB_timer_query</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_instanced_arrays.txt">ARB_instanced_arrays</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_vertex_type_2_10_10_10_rev.txt">ARB_vertex_type_2_10_10_10_rev</a></li>
 * </ul>
 */
interface gl33i {

    // --- [ glBindFragDataLocationIndexed ] ---

    /**
     * Binds a user-defined varying out variable to a fragment shader color number and index.
     *
     * @param program     the name of the program containing varying out variable whose binding to modify
     * @param colorNumber the color number to bind the user-defined varying out variable to
     * @param index       the index of the color input to bind the user-defined varying out variable to
     * @param name        the name of the user-defined varying out variable whose binding to modify
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBindFragDataLocationIndexed">Reference Page</a>
     */
    fun bindFragDataLocationIndexed(program: GlProgram, colorNumber: Int, index: Int, name: CharSequence) =
            Stack.asciiAddress(name) { GL33C.nglBindFragDataLocationIndexed(program.name, colorNumber, index, it) }

    // --- [ glGetFragDataIndex ] ---

    /**
     * Queries the bindings of color indices to user-defined varying out variables.
     *
     * @param program the name of the program containing varying out variable whose binding to query
     * @param name    the name of the user-defined varying out variable whose index to query
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetFragDataIndex">Reference Page</a>
     */
    fun getFragDataIndex(program: GlProgram, name: CharSequence) =
            Stack.asciiAddress(name) { GL33C.nglGetFragDataIndex(program.name, it) }

    // --- [ glGenSamplers ] ---

    /**
     * Generates sampler object names.
     *
     * @param samplers a buffer in which the generated sampler object names are stored
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenSamplers">Reference Page</a>
     */
    fun genSamplers(samplers: GlSamplers) = GL33C.nglGenSamplers(samplers.rem, samplers.adr)

    /**
     * Generates sampler object names.
     *
     * @param samplers a buffer in which the generated sampler object names are stored
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenSamplers">Reference Page</a>
     */
    fun genSamplers(size: Int): GlSamplers = GlSamplers(size).also(::genSamplers)

    /**
     * Generates sampler object names.
     *
     * @param samplers a buffer in which the generated sampler object names are stored
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenSamplers">Reference Page</a>
     */
    fun genSamplers(sampler: KMutableProperty0<GlSampler>): GlSampler {
        sampler.set(genSamplers())
        return sampler()
    }

    /**
     * Generates sampler object names.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenSamplers">Reference Page</a>
     */
    fun genSamplers(): GlSampler = GlSampler(Stack.intAddress { GL33C.nglGenSamplers(1, it) })

    // --- [ glDeleteSamplers ] ---

    /**
     * Deletes named sampler objects.
     *
     * @param samplers an array of sampler objects to be deleted
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteSamplers">Reference Page</a>
     */
    fun deleteSamplers(samplers: GlSamplers) = GL33C.nglDeleteSamplers(samplers.rem, samplers.adr)

    /**
     * Deletes named sampler objects.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteSamplers">Reference Page</a>
     */
    fun deleteSamplers(sampler: GlSampler) = Stack.intAddress(sampler.name) { GL33C.nglDeleteSamplers(1, it) }

    // --- [ glIsSampler ] ---

    /**
     * Determines if a name corresponds to a sampler object.
     *
     * @param sampler a value that may be the name of a sampler object
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glIsSampler">Reference Page</a>
     */
    fun isSampler(sampler: GlSampler): Boolean = GL33C.glIsSampler(sampler.name)

    // --- [ glBindSampler ] ---

    /**
     * Binds a named sampler to a texturing target.
     *
     * @param unit    the index of the texture unit to which the sampler is bound
     * @param sampler the name of a sampler
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBindSampler">Reference Page</a>
     */
    fun bindSampler(unit: Int, sampler: GlSampler) = GL33C.glBindSampler(unit, sampler.name)

    // --- [ glSamplerParameteri ] ---

    /**
     * Set the integer value of a sampler parameter.
     *
     * @param sampler the sampler object whose parameter to modify
     * @param name   the symbolic name of a single-valued sampler parameter. One of:<br><table><tr><td>{@link GL11#GL_TEXTURE_WRAP_S TEXTURE_WRAP_S}</td><td>{@link GL11#GL_TEXTURE_WRAP_T TEXTURE_WRAP_T}</td><td>{@link GL12#GL_TEXTURE_WRAP_R TEXTURE_WRAP_R}</td><td>{@link GL11#GL_TEXTURE_MIN_FILTER TEXTURE_MIN_FILTER}</td><td>{@link GL11#GL_TEXTURE_MAG_FILTER TEXTURE_MAG_FILTER}</td></tr><tr><td>{@link GL12#GL_TEXTURE_MIN_LOD TEXTURE_MIN_LOD}</td><td>{@link GL12#GL_TEXTURE_MAX_LOD TEXTURE_MAX_LOD}</td><td>{@link GL14#GL_TEXTURE_LOD_BIAS TEXTURE_LOD_BIAS}</td><td>{@link GL14#GL_TEXTURE_COMPARE_MODE TEXTURE_COMPARE_MODE}</td><td>{@link GL14#GL_TEXTURE_COMPARE_FUNC TEXTURE_COMPARE_FUNC}</td></tr></table>
     * @param param   the value of {@code name}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glSamplerParameteri">Reference Page</a>
     */
    fun samplerParameter(sampler: GlSampler, name: SamplerParameter, param: Int) =
            GL33C.glSamplerParameteri(sampler.name, name.i, param)

    // --- [ glSamplerParameterf ] ---

    /**
     * Float version of {@link #glSamplerParameteri SamplerParameteri}.
     *
     * @param sampler the sampler object whose parameter to modify
     * @param name   the symbolic name of a single-valued sampler parameter
     * @param param   the value of {@code name}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glSamplerParameterf">Reference Page</a>
     */
    fun samplerParameter(sampler: GlSampler, name: SamplerParameter, param: Float) =
            GL33C.glSamplerParameterf(sampler.name, name.i, param)

//    // --- [ glSamplerParameteriv ] --- TODO?
//
//    /**
//     * Pointer version of {@link #glSamplerParameteri SamplerParameteri}.
//     *
//     * @param sampler the sampler object whose parameter to modify
//     * @param pname   the symbolic name of a sampler parameter. One of:<br><table><tr><td>{@link GL11#GL_TEXTURE_BORDER_COLOR TEXTURE_BORDER_COLOR}</td><td>{@link GL11#GL_TEXTURE_WRAP_S TEXTURE_WRAP_S}</td><td>{@link GL11#GL_TEXTURE_WRAP_T TEXTURE_WRAP_T}</td><td>{@link GL12#GL_TEXTURE_WRAP_R TEXTURE_WRAP_R}</td></tr><tr><td>{@link GL11#GL_TEXTURE_MIN_FILTER TEXTURE_MIN_FILTER}</td><td>{@link GL11#GL_TEXTURE_MAG_FILTER TEXTURE_MAG_FILTER}</td><td>{@link GL12#GL_TEXTURE_MIN_LOD TEXTURE_MIN_LOD}</td><td>{@link GL12#GL_TEXTURE_MAX_LOD TEXTURE_MAX_LOD}</td></tr><tr><td>{@link GL14#GL_TEXTURE_LOD_BIAS TEXTURE_LOD_BIAS}</td><td>{@link GL14#GL_TEXTURE_COMPARE_MODE TEXTURE_COMPARE_MODE}</td><td>{@link GL14#GL_TEXTURE_COMPARE_FUNC TEXTURE_COMPARE_FUNC}</td></tr></table>
//     * @param params  an array where the value or values of {@code pname} are stored
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glSamplerParameter">Reference Page</a>
//     */
//    fun samplerBorderColor(sampler: GlSampler, color: Vec4) =
//            Stack.vec4Address(color)    {GL33C.nglSamplerParameteriv(sampler, pname, memAddress(params));
//    }

    // --- [ glSamplerParameterfv ] ---

    /**
     * glSamplerParameterfv for GL_TEXTURE_BORDER_COLOR
     *
     * @param sampler the sampler object whose parameter to modify
     * @param color   the value of the color
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glSamplerParameter">Reference Page</a>
     */
    fun samplerBorderColor(sampler: GlSampler, color: Vec4) =
            Stack.vec4Address(color) { GL33C.nglSamplerParameterfv(sampler.name, GL11C.GL_TEXTURE_BORDER_COLOR, it) }

    // --- [ glSamplerParameterIiv ] ---

    /**
     * glSamplerParameterIiv for GL_TEXTURE_BORDER_COLOR
     *
     * @param sampler the sampler object whose parameter to modify
     * @param color   the value of the color
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glSamplerParameter">Reference Page</a>
     */
    fun samplerBorderColor(sampler: GlSampler, color: Vec4i) =
            Stack.vec4iAddress(color) { GL33C.nglSamplerParameterIiv(sampler.name, GL11C.GL_TEXTURE_BORDER_COLOR, it) }

    // --- [ glSamplerParameterIuiv ] ---

    /**
     * glSamplerParameterIuv for GL_TEXTURE_BORDER_COLOR
     *
     * @param sampler the sampler object whose parameter to modify
     * @param color   the value of the color
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glSamplerParameter">Reference Page</a>
     */
    fun samplerBorderColor(sampler: GlSampler, color: Vec4ui) =
            Stack.vec4uiAddress(color) { GL33C.nglSamplerParameterfv(sampler.name, GL11C.GL_TEXTURE_BORDER_COLOR, it) }

    // --- [ glGetSamplerParameteriv ] ---
    // inline reified

    // --- [ glGetSamplerParameterfv ] ---
    // inline reified

    // --- [ glGetSamplerParameterIiv ] ---
    // inline reified

    // --- [ glGetSamplerParameterIuiv ] ---
    // inline reified

    // --- [ glQueryCounter ] ---

    /**
     * Records the GL time into a query object after all previous commands have reached the GL server but have not yet necessarily executed.
     *
     * @param id     the name of a query object into which to record the GL time
     * @param target the counter to query. Must be:<br><table><tr><td>{@link #GL_TIMESTAMP TIMESTAMP}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glQueryCounter">Reference Page</a>
     */
    fun queryCounter(id: Int) = GL33C.glQueryCounter(id, GL33C.GL_TIMESTAMP)

    // --- [ glGetQueryObjecti64v ] --- TODO
//
//    /** Unsafe version of: {@link #glGetQueryObjecti64v GetQueryObjecti64v} */
//    public static native void nglGetQueryObjecti64v(int id, int pname, long params);
//
//    /**
//     * Returns the 64bit integer value of query object parameter.
//     *
//     * @param id     the name of a query object
//     * @param pname  the symbolic name of a query object parameter. One of:<br><table><tr><td>{@link GL15#GL_QUERY_RESULT QUERY_RESULT}</td><td>{@link GL15#GL_QUERY_RESULT_AVAILABLE QUERY_RESULT_AVAILABLE}</td></tr></table>
//     * @param params the requested data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetQueryObject">Reference Page</a>
//     */
//    public static void glGetQueryObjecti64v(@NativeType("GLuint") int id, @NativeType("GLenum") int pname, @NativeType("GLint64 *") LongBuffer params)
//    {
//        if (CHECKS) {
//            check(params, 1);
//        }
//        nglGetQueryObjecti64v(id, pname, memAddress(params));
//    }
//
//    /**
//     * Returns the 64bit integer value of query object parameter.
//     *
//     * @param id    the name of a query object
//     * @param pname the symbolic name of a query object parameter. One of:<br><table><tr><td>{@link GL15#GL_QUERY_RESULT QUERY_RESULT}</td><td>{@link GL15#GL_QUERY_RESULT_AVAILABLE QUERY_RESULT_AVAILABLE}</td></tr></table>
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetQueryObject">Reference Page</a>
//     */
//    @NativeType("void")
//    public static long glGetQueryObjecti64(@NativeType("GLuint") int id, @NativeType("GLenum") int pname)
//    {
//        MemoryStack stack = stackGet (); int stackPointer = stack . getPointer ();
//        try {
//            LongBuffer params = stack . callocLong (1);
//            nglGetQueryObjecti64v(id, pname, memAddress(params));
//            return params.get(0);
//        } finally {
//            stack.setPointer(stackPointer);
//        }
//    }

    // --- [ glGetQueryObjectui64v ] --- TODO

//    /** Unsafe version of: {@link #glGetQueryObjectui64v GetQueryObjectui64v} */
//    public static native void nglGetQueryObjectui64v(int id, int pname, long params);
//
//    /**
//     * Unsigned version of {@link #glGetQueryObjecti64v GetQueryObjecti64v}.
//     *
//     * @param id     the name of a query object
//     * @param pname  the symbolic name of a query object parameter
//     * @param params the requested data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetQueryObject">Reference Page</a>
//     */
//    public static void glGetQueryObjectui64v(@NativeType("GLuint") int id, @NativeType("GLenum") int pname, @NativeType("GLuint64 *") LongBuffer params)
//    {
//        if (CHECKS) {
//            check(params, 1);
//        }
//        nglGetQueryObjectui64v(id, pname, memAddress(params));
//    }
//
//    /**
//     * Unsigned version of {@link #glGetQueryObjecti64v GetQueryObjecti64v}.
//     *
//     * @param id    the name of a query object
//     * @param pname the symbolic name of a query object parameter
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetQueryObject">Reference Page</a>
//     */
//    @NativeType("void")
//    public static long glGetQueryObjectui64(@NativeType("GLuint") int id, @NativeType("GLenum") int pname)
//    {
//        MemoryStack stack = stackGet (); int stackPointer = stack . getPointer ();
//        try {
//            LongBuffer params = stack . callocLong (1);
//            nglGetQueryObjectui64v(id, pname, memAddress(params));
//            return params.get(0);
//        } finally {
//            stack.setPointer(stackPointer);
//        }
//    }

    // --- [ glVertexAttribDivisor ] ---

    /**
     * Modifies the rate at which generic vertex attributes advance during instanced rendering.
     *
     * @param index   the index of the generic vertex attribute
     * @param divisor the number of instances that will pass between updates of the generic attribute at slot {@code index}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttribDivisor">Reference Page</a>
     */
    fun vertexAttribDivisor(index: Int, divisor: Int) = GL33C.glVertexAttribDivisor(index, divisor)

    // --- [ glVertexAttribP1ui ] ---

//    /** TODO?
//     * Packed component version of {@link GL20C#glVertexAttrib1f VertexAttrib1f}.
//     *
//     * @param index      the index of the generic vertex attribute to be modified
//     * @param type       type of packing used on the data. One of:<br><table><tr><td>{@link #GL_INT_2_10_10_10_REV INT_2_10_10_10_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr></table>
//     * @param normalized whether values should be normalized or cast directly to floating-point
//     * @param value      the packed value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static native void glVertexAttribP1ui(@NativeType("GLuint") int index, @NativeType("GLenum") int type, @NativeType("GLboolean") boolean normalized, @NativeType("GLuint") int value);
//
//    // --- [ glVertexAttribP2ui ] ---
//
//    /**
//     * Packed component version of {@link GL20C#glVertexAttrib2f VertexAttrib2f}.
//     *
//     * @param index      the index of the generic vertex attribute to be modified
//     * @param type       type of packing used on the data. One of:<br><table><tr><td>{@link #GL_INT_2_10_10_10_REV INT_2_10_10_10_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr></table>
//     * @param normalized whether values should be normalized or cast directly to floating-point
//     * @param value      the packed value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static native void glVertexAttribP2ui(@NativeType("GLuint") int index, @NativeType("GLenum") int type, @NativeType("GLboolean") boolean normalized, @NativeType("GLuint") int value);
//
//    // --- [ glVertexAttribP3ui ] ---
//
//    /**
//     * Packed component version of {@link GL20C#glVertexAttrib3f VertexAttrib3f}.
//     *
//     * @param index      the index of the generic vertex attribute to be modified
//     * @param type       type of packing used on the data. One of:<br><table><tr><td>{@link #GL_INT_2_10_10_10_REV INT_2_10_10_10_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr></table>
//     * @param normalized whether values should be normalized or cast directly to floating-point
//     * @param value      the packed value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static native void glVertexAttribP3ui(@NativeType("GLuint") int index, @NativeType("GLenum") int type, @NativeType("GLboolean") boolean normalized, @NativeType("GLuint") int value);
//
//    // --- [ glVertexAttribP4ui ] ---
//
//    /**
//     * Packed component version of {@link GL20C#glVertexAttrib4f VertexAttrib4f}.
//     *
//     * @param index      the index of the generic vertex attribute to be modified
//     * @param type       type of packing used on the data. One of:<br><table><tr><td>{@link #GL_INT_2_10_10_10_REV INT_2_10_10_10_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr></table>
//     * @param normalized whether values should be normalized or cast directly to floating-point
//     * @param value      the packed value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static native void glVertexAttribP4ui(@NativeType("GLuint") int index, @NativeType("GLenum") int type, @NativeType("GLboolean") boolean normalized, @NativeType("GLuint") int value);
//
//    // --- [ glVertexAttribP1uiv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttribP1uiv VertexAttribP1uiv} */
//    public static native void nglVertexAttribP1uiv(int index, int type, boolean normalized, long value);
//
//    /**
//     * Pointer version of {@link #glVertexAttribP1ui VertexAttribP1ui}.
//     *
//     * @param index      the index of the generic vertex attribute to be modified
//     * @param type       type of packing used on the data. One of:<br><table><tr><td>{@link #GL_INT_2_10_10_10_REV INT_2_10_10_10_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr></table>
//     * @param normalized whether values should be normalized or cast directly to floating-point
//     * @param value      the packed value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttribP1uiv(@NativeType("GLuint") int index, @NativeType("GLenum") int type, @NativeType("GLboolean") boolean normalized, @NativeType("GLuint const *") IntBuffer value)
//    {
//        if (CHECKS) {
//            check(value, 1);
//        }
//        nglVertexAttribP1uiv(index, type, normalized, memAddress(value));
//    }
//
//    // --- [ glVertexAttribP2uiv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttribP2uiv VertexAttribP2uiv} */
//    public static native void nglVertexAttribP2uiv(int index, int type, boolean normalized, long value);
//
//    /**
//     * Pointer version of {@link #glVertexAttribP2ui VertexAttribP2ui}.
//     *
//     * @param index      the index of the generic vertex attribute to be modified
//     * @param type       type of packing used on the data. One of:<br><table><tr><td>{@link #GL_INT_2_10_10_10_REV INT_2_10_10_10_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr></table>
//     * @param normalized whether values should be normalized or cast directly to floating-point
//     * @param value      the packed value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttribP2uiv(@NativeType("GLuint") int index, @NativeType("GLenum") int type, @NativeType("GLboolean") boolean normalized, @NativeType("GLuint const *") IntBuffer value)
//    {
//        if (CHECKS) {
//            check(value, 1);
//        }
//        nglVertexAttribP2uiv(index, type, normalized, memAddress(value));
//    }
//
//    // --- [ glVertexAttribP3uiv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttribP3uiv VertexAttribP3uiv} */
//    public static native void nglVertexAttribP3uiv(int index, int type, boolean normalized, long value);
//
//    /**
//     * Pointer version of {@link #glVertexAttribP3ui VertexAttribP3ui}.
//     *
//     * @param index      the index of the generic vertex attribute to be modified
//     * @param type       type of packing used on the data. One of:<br><table><tr><td>{@link #GL_INT_2_10_10_10_REV INT_2_10_10_10_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr></table>
//     * @param normalized whether values should be normalized or cast directly to floating-point
//     * @param value      the packed value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttribP3uiv(@NativeType("GLuint") int index, @NativeType("GLenum") int type, @NativeType("GLboolean") boolean normalized, @NativeType("GLuint const *") IntBuffer value)
//    {
//        if (CHECKS) {
//            check(value, 1);
//        }
//        nglVertexAttribP3uiv(index, type, normalized, memAddress(value));
//    }
//
//    // --- [ glVertexAttribP4uiv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttribP4uiv VertexAttribP4uiv} */
//    public static native void nglVertexAttribP4uiv(int index, int type, boolean normalized, long value);
//
//    /**
//     * Pointer version of {@link #glVertexAttribP4ui VertexAttribP4ui}.
//     *
//     * @param index      the index of the generic vertex attribute to be modified
//     * @param type       type of packing used on the data. One of:<br><table><tr><td>{@link #GL_INT_2_10_10_10_REV INT_2_10_10_10_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr></table>
//     * @param normalized whether values should be normalized or cast directly to floating-point
//     * @param value      the packed value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttribP4uiv(@NativeType("GLuint") int index, @NativeType("GLenum") int type, @NativeType("GLboolean") boolean normalized, @NativeType("GLuint const *") IntBuffer value)
//    {
//        if (CHECKS) {
//            check(value, 1);
//        }
//        nglVertexAttribP4uiv(index, type, normalized, memAddress(value));
//    }
}