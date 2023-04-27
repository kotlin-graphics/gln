/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package gln

import gli_.gl
import glm_.L
import glm_.vec1.Vec1i
import glm_.vec1.Vec1ui
import glm_.vec2.Vec2i
import glm_.vec2.Vec2ui
import glm_.vec3.Vec3i
import glm_.vec3.Vec3ui
import glm_.vec4.Vec4
import glm_.vec4.Vec4bool
import glm_.vec4.Vec4i
import glm_.vec4.Vec4ui
import gln.identifiers.*
import kool.BYTES
import kool.adr
import kool.stack
import org.lwjgl.opengl.GL30C
import org.lwjgl.system.MemoryUtil.*
import unsigned.Uint
import java.nio.ByteBuffer
import kotlin.reflect.KMutableProperty0

/**
 * The OpenGL functionality of a forward compatible context, up to version 3.0.
 *
 * <p>OpenGL 3.0 implementations are guaranteed to support at least versions 1.10, 1.20 and 1.30 of the shading language,
 * although versions 1.10 and 1.20 are deprecated in a forward-compatible context.</p>
 *
 * <p>Extensions promoted to core in this release:</p>
 *
 * <ul>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_gpu_shader4.txt">EXT_gpu_shader4</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/NV/NV_conditional_render.txt">NV_conditional_render</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/APPLE/APPLE_flush_buffer_range.txt">APPLE_flush_buffer_range</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_color_buffer_float.txt">ARB_color_buffer_float</a>, <a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/NV/NV_depth_buffer_float.txt">NV_depth_buffer_float</a>, <a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_texture_float.txt">ARB_texture_float</a>,
 * <a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_packed_float.txt">EXT_packed_float</a> and <a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_texture_shared_exponent.txt">EXT_texture_shared_exponent</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_framebuffer_object.txt">EXT_framebuffer_object</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/NV/NV_half_float.txt">NV_half_float</a> and <a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_half_FLOAT_pixel.txt">ARB_half_FLOAT_pixel</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_framebuffer_multisample.txt">EXT_framebuffer_multisample</a> and <a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_framebuffer_blit.txt">EXT_framebuffer_blit</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_texture_integer.txt">EXT_texture_integer</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_texture_array.txt">EXT_texture_array</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_packed_depth_stencil.txt">EXT_packed_depth_stencil</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_draw_buffers2.txt">EXT_draw_buffers2</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_texture_compression_rgtc.txt">EXT_texture_compression_rgtc</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_transform_feedback.txt">EXT_transform_feedback</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/APPLE/APPLE_vertex_array_object.txt">APPLE_vertex_array_object</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_framebuffer_sRGB.txt">EXT_framebuffer_sRGB</a></li>
 * </ul>
 */
interface gl30i {

    // --- [ glClearBufferiv ] ---

    /**
     * Clears an individual buffer of the currently bound framebuffer object to the {@link #GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER} binding.
     *
     * @param buffer     the buffer to clear. One of:<br><table><tr><td>{@link GL11#GL_COLOR COLOR}</td><td>{@link GL11#GL_STENCIL STENCIL}</td></tr></table>
     * @param drawbuffer the draw buffer to clear
     * @param value      for color buffers, a pointer to a four-element vector specifying R, G, B and A values to clear the buffer to. For stencil buffers, a pointer to a
     *                   single stencil value to clear the buffer to.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClearBuffer">Reference Page</a>
     */
    fun clearBuffer(buffer: BufferType, drawbuffer: Int, value: Vec4i) = GL30C.nglClearBufferiv(buffer.i, drawbuffer, value.toOffHeap())

    // --- [ glClearBufferuiv ] ---

    /**
     * Clears an individual buffer of the currently bound framebuffer object to the {@link #GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER} binding.
     *
     * @param buffer     the buffer to clear. Must be:<br><table><tr><td>{@link GL11#GL_COLOR COLOR}</td></tr></table>
     * @param drawbuffer the draw buffer to clear
     * @param value      a pointer to a four-element vector specifying R, G, B and A values to clear the buffer to
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClearBuffer">Reference Page</a>
     */
    fun clearBuffer(buffer: BufferType, drawbuffer: Int, value: Vec4ui) = GL30C.nglClearBufferuiv(buffer.i, drawbuffer, value.toOffHeap())

    // --- [ glClearBufferfv ] ---

    /**
     * Clears an individual buffer of the currently bound framebuffer object to the {@link #GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER} binding.
     *
     * @param buffer     the buffer to clear. One of:<br><table><tr><td>{@link GL11#GL_COLOR COLOR}</td><td>{@link GL11#GL_DEPTH DEPTH}</td></tr></table>
     * @param drawbuffer the draw buffer to clear
     * @param value      for color buffers, a pointer to a four-element vector specifying R, G, B and A values to clear the buffer to. For depth buffers, a pointer to a
     *                   single depth value to clear the buffer to.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClearBuffer">Reference Page</a>
     */
    fun clearBuffer(buffer: BufferType, drawbuffer: Int, value: Vec4) = GL30C.nglClearBufferfv(buffer.i, drawbuffer, value.toOffHeap())

    // --- [ glClearBufferfi ] ---

    /**
     * Clears an individual buffer of the currently bound framebuffer object to the {@link #GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER} binding.
     *
     * @param depth      the depth value to clear the buffer to
     * @param stencil    the stencil value to clear the buffer to
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClearBufferfi">Reference Page</a>
     */
    fun clearBufferDepthStencil(depth: Float, stencil: Int) = GL30C.glClearBufferfi(GL30C.GL_DEPTH_STENCIL, 0, depth, stencil)

    // --- [ glVertexAttribI1i ] ---

    /**
     * Specifies the value of a pure integer generic vertex attribute. The y and z components are implicitly set to 0 and w to 1.
     *
     * @param index the index of the pure integer generic vertex attribute to be modified
     * @param x     the vertex attribute x component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, x: Int) = GL30C.glVertexAttribI1i(index, x)

    /**
     * Specifies the value of a pure integer generic vertex attribute. The y and z components are implicitly set to 0 and w to 1.
     *
     * @param index the index of the pure integer generic vertex attribute to be modified
     * @param v     the vertex attribute component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v: Vec1i) = GL30C.glVertexAttribI1i(index, v.x)

    // --- [ glVertexAttribI2i ] ---

    /**
     * Specifies the value of a pure integer generic vertex attribute. The z component is implicitly set to 0 and w to 1.
     *
     * @param index the index of the pure integer generic vertex attribute to be modified
     * @param x     the vertex attribute x component
     * @param y     the vertex attribute y component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, x: Int, y: Int) = GL30C.glVertexAttribI2i(index, x, y)

    /**
     * Specifies the value of a pure integer generic vertex attribute. The z component is implicitly set to 0 and w to 1.
     *
     * @param index the index of the pure integer generic vertex attribute to be modified
     * @param v     the vertex attribute components
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v: Vec2i) = GL30C.glVertexAttribI2i(index, v.x, v.y)

    // --- [ glVertexAttribI3i ] ---

    /**
     * Specifies the value of a pure integer generic vertex attribute. The w component is implicitly set to 1.
     *
     * @param index the index of the pure integer generic vertex attribute to be modified
     * @param x     the vertex attribute x component
     * @param y     the vertex attribute y component
     * @param z     the vertex attribute z component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, x: Int, y: Int, z: Int) = GL30C.glVertexAttribI3i(index, x, y, z)

    /**
     * Specifies the value of a pure integer generic vertex attribute. The w component is implicitly set to 1.
     *
     * @param index the index of the pure integer generic vertex attribute to be modified
     * @param v     the vertex attribute components
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v: Vec3i) = GL30C.glVertexAttribI3i(index, v.x, v.y, v.z)

    // --- [ glVertexAttribI4i ] ---

    /**
     * Specifies the value of a pure integer generic vertex attribute.
     *
     * @param index the index of the pure integer generic vertex attribute to be modified
     * @param x     the vertex attribute x component
     * @param y     the vertex attribute y component
     * @param z     the vertex attribute z component
     * @param w     the vertex attribute w component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, x: Int, y: Int, z: Int, w: Int) = GL30C.glVertexAttribI4i(index, x, y, z, w)

    /**
     * Specifies the value of a pure integer generic vertex attribute.
     *
     * @param index the index of the pure integer generic vertex attribute to be modified
     * @param v     the vertex attribute components
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v: Vec4i) = GL30C.glVertexAttribI4i(index, v.x, v.y, v.z, v.w)

    // --- [ glVertexAttribI1ui ] ---

    /**
     * Specifies the value of an unsigned pure integer generic vertex attribute. The y and z components are implicitly set to 0 and w to 1.
     *
     * @param index the index of the pure integer generic vertex attribute to be modified
     * @param x     the vertex attribute x component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, x: Uint) = GL30C.glVertexAttribI1ui(index, x.v)

    /**
     * Specifies the value of an unsigned pure integer generic vertex attribute. The y and z components are implicitly set to 0 and w to 1.
     *
     * @param index the index of the pure integer generic vertex attribute to be modified
     * @param v     the vertex attribute component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v: Vec1ui) = GL30C.glVertexAttribI1ui(index, v.x.v)

    // --- [ glVertexAttribI2ui ] ---

    /**
     * Specifies the value of an unsigned pure integer generic vertex attribute. The z component is implicitly set to 0 and w to 1.
     *
     * @param index the index of the pure integer generic vertex attribute to be modified
     * @param x     the vertex attribute x component
     * @param y     the vertex attribute y component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, x: Uint, y: Uint) = GL30C.glVertexAttribI2ui(index, x.v, y.v)

    /**
     * Specifies the value of an unsigned pure integer generic vertex attribute. The z component is implicitly set to 0 and w to 1.
     *
     * @param index the index of the pure integer generic vertex attribute to be modified
     * @param v     the vertex attribute
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v: Vec2ui) = GL30C.glVertexAttribI2ui(index, v.x.v, v.y.v)

    // --- [ glVertexAttribI3ui ] ---

    /**
     * Specifies the value of an unsigned pure integer generic vertex attribute. The w component is implicitly set to 1.
     *
     * @param index the index of the pure integer generic vertex attribute to be modified
     * @param x     the vertex attribute x component
     * @param y     the vertex attribute y component
     * @param z     the vertex attribute z component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, x: Uint, y: Uint, z: Uint) = GL30C.glVertexAttribI3ui(index, x.v, y.v, z.v)

    /**
     * Specifies the value of an unsigned pure integer generic vertex attribute. The w component is implicitly set to 1.
     *
     * @param index the index of the pure integer generic vertex attribute to be modified
     * @param v     the vertex attribute
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v: Vec3ui) = GL30C.glVertexAttribI3ui(index, v.x.v, v.y.v, v.z.v)

    // --- [ glVertexAttribI4ui ] ---

    /**
     * Specifies the value of an unsigned pure integer generic vertex attribute.
     *
     * @param index the index of the pure integer generic vertex attribute to be modified
     * @param x     the vertex attribute x component
     * @param y     the vertex attribute y component
     * @param z     the vertex attribute z component
     * @param w     the vertex attribute w component
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, x: Uint, y: Uint, z: Uint, w: Uint) = GL30C.glVertexAttribI4ui(index, x.v, y.v, z.v, w.v)

    /**
     * Specifies the value of an unsigned pure integer generic vertex attribute.
     *
     * @param index the index of the pure integer generic vertex attribute to be modified
     * @param v     the vertex attribute
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
     */
    fun vertexAttrib(index: Int, v: Vec4ui) = GL30C.glVertexAttribI4ui(index, v.x.v, v.y.v, v.z.v, v.w.v)

    // --- [ glVertexAttribI1iv ] --- TODO?

//    /** Unsafe version of: {@link #glVertexAttribI1iv VertexAttribI1iv} */
//    public static native void nglVertexAttribI1iv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttribI1i VertexAttribI1i}.
//     *
//     * @param index the index of the pure integer generic vertex attribute to be modified
//     * @param v     the pure integer vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttribI1iv(@NativeType("GLuint") int index, @NativeType("GLint const *") IntBuffer v) {
//        if (CHECKS) {
//            check(v, 1);
//        }
//        nglVertexAttribI1iv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttribI2iv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttribI2iv VertexAttribI2iv} */
//    public static native void nglVertexAttribI2iv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttribI2i VertexAttribI2i}.
//     *
//     * @param index the index of the pure integer generic vertex attribute to be modified
//     * @param v     the pure integer vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttribI2iv(@NativeType("GLuint") int index, @NativeType("GLint const *") IntBuffer v) {
//        if (CHECKS) {
//            check(v, 2);
//        }
//        nglVertexAttribI2iv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttribI3iv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttribI3iv VertexAttribI3iv} */
//    public static native void nglVertexAttribI3iv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttribI3i VertexAttribI3i}.
//     *
//     * @param index the index of the pure integer generic vertex attribute to be modified
//     * @param v     the pure integer vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttribI3iv(@NativeType("GLuint") int index, @NativeType("GLint const *") IntBuffer v) {
//        if (CHECKS) {
//            check(v, 3);
//        }
//        nglVertexAttribI3iv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttribI4iv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttribI4iv VertexAttribI4iv} */
//    public static native void nglVertexAttribI4iv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttribI4i VertexAttribI4i}.
//     *
//     * @param index the index of the pure integer generic vertex attribute to be modified
//     * @param v     the pure integer vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttribI4iv(@NativeType("GLuint") int index, @NativeType("GLint const *") IntBuffer v) {
//        if (CHECKS) {
//            check(v, 4);
//        }
//        nglVertexAttribI4iv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttribI1uiv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttribI1uiv VertexAttribI1uiv} */
//    public static native void nglVertexAttribI1uiv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttribI1ui VertexAttribI1ui}.
//     *
//     * @param index the index of the pure integer generic vertex attribute to be modified
//     * @param v     the pure integer vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttribI1uiv(@NativeType("GLuint") int index, @NativeType("GLuint const *") IntBuffer v) {
//        if (CHECKS) {
//            check(v, 1);
//        }
//        nglVertexAttribI1uiv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttribI2uiv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttribI2uiv VertexAttribI2uiv} */
//    public static native void nglVertexAttribI2uiv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttribI2ui VertexAttribI2ui}.
//     *
//     * @param index the index of the pure integer generic vertex attribute to be modified
//     * @param v     the pure integer vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttribI2uiv(@NativeType("GLuint") int index, @NativeType("GLuint const *") IntBuffer v) {
//        if (CHECKS) {
//            check(v, 2);
//        }
//        nglVertexAttribI2uiv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttribI3uiv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttribI3uiv VertexAttribI3uiv} */
//    public static native void nglVertexAttribI3uiv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttribI3ui VertexAttribI3ui}.
//     *
//     * @param index the index of the pure integer generic vertex attribute to be modified
//     * @param v     the pure integer vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttribI3uiv(@NativeType("GLuint") int index, @NativeType("GLuint const *") IntBuffer v) {
//        if (CHECKS) {
//            check(v, 3);
//        }
//        nglVertexAttribI3uiv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttribI4uiv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttribI4uiv VertexAttribI4uiv} */
//    public static native void nglVertexAttribI4uiv(int index, long v);
//
//    /**
//     * Pointer version of {@link #glVertexAttribI4ui VertexAttribI4ui}.
//     *
//     * @param index the index of the pure integer generic vertex attribute to be modified
//     * @param v     the pure integer vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttribI4uiv(@NativeType("GLuint") int index, @NativeType("GLuint const *") IntBuffer v) {
//        if (CHECKS) {
//            check(v, 4);
//        }
//        nglVertexAttribI4uiv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttribI4bv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttribI4bv VertexAttribI4bv} */
//    public static native void nglVertexAttribI4bv(int index, long v);
//
//    /**
//     * Byte version of {@link #glVertexAttribI4iv VertexAttribI4iv}.
//     *
//     * @param index the index of the pure integer generic vertex attribute to be modified
//     * @param v     the pure integer vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttribI4bv(@NativeType("GLuint") int index, @NativeType("GLbyte const *") ByteBuffer v) {
//        if (CHECKS) {
//            check(v, 4);
//        }
//        nglVertexAttribI4bv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttribI4sv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttribI4sv VertexAttribI4sv} */
//    public static native void nglVertexAttribI4sv(int index, long v);
//
//    /**
//     * Short version of {@link #glVertexAttribI4iv VertexAttribI4iv}.
//     *
//     * @param index the index of the pure integer generic vertex attribute to be modified
//     * @param v     the pure integer vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttribI4sv(@NativeType("GLuint") int index, @NativeType("GLshort const *") ShortBuffer v) {
//        if (CHECKS) {
//            check(v, 4);
//        }
//        nglVertexAttribI4sv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttribI4ubv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttribI4ubv VertexAttribI4ubv} */
//    public static native void nglVertexAttribI4ubv(int index, long v);
//
//    /**
//     * Byte version of {@link #glVertexAttribI4uiv VertexAttribI4uiv}.
//     *
//     * @param index the index of the pure integer generic vertex attribute to be modified
//     * @param v     the pure integer vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttribI4ubv(@NativeType("GLuint") int index, @NativeType("GLbyte const *") ByteBuffer v) {
//        if (CHECKS) {
//            check(v, 4);
//        }
//        nglVertexAttribI4ubv(index, memAddress(v));
//    }
//
//    // --- [ glVertexAttribI4usv ] ---
//
//    /** Unsafe version of: {@link #glVertexAttribI4usv VertexAttribI4usv} */
//    public static native void nglVertexAttribI4usv(int index, long v);
//
//    /**
//     * Short version of {@link #glVertexAttribI4uiv VertexAttribI4uiv}.
//     *
//     * @param index the index of the pure integer generic vertex attribute to be modified
//     * @param v     the pure integer vertex attribute buffer
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttrib">Reference Page</a>
//     */
//    public static void glVertexAttribI4usv(@NativeType("GLuint") int index, @NativeType("GLshort const *") ShortBuffer v) {
//        if (CHECKS) {
//            check(v, 4);
//        }
//        nglVertexAttribI4usv(index, memAddress(v));
//    }
//
    // --- [ glVertexAttribIPointer ] ---

    /** TODO?
     * Specifies the location and organization of a pure integer vertex attribute array.
     *
     * @param index   the index of the pure integer generic vertex attribute to be modified
     * @param size    the number of values per vertex that are stored in the array. The initial value is 4. One of:<br><table><tr><td>1</td><td>2</td><td>3</td><td>4</td><td>{@link GL12#GL_BGRA BGRA}</td></tr></table>
     * @param type    the data type of each component in the array. One of:<br><table><tr><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_SHORT SHORT}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param stride  the byte offset between consecutive generic vertex attributes. If stride is 0, the generic vertex attributes are understood to be tightly packed in
     *                the array. The initial value is 0.
     * @param pointer the vertex attribute data or the offset of the first component of the first generic vertex attribute in the array in the data store of the buffer
     *                currently bound to the {@link GL15#GL_ARRAY_BUFFER ARRAY_BUFFER} target. The initial value is 0.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttribIPointer">Reference Page</a>
     */
//    fun vertexAttribIPointer(index: Int, size: Int, type: DataType, stride: Int, @NativeType("void const *") ByteBuffer pointer) =
//        nglVertexAttribIPointer(index, size, type, stride, memAddress(pointer))

    /**
     * Specifies the location and organization of a pure integer vertex attribute array.
     *
     * @param index   the index of the pure integer generic vertex attribute to be modified
     * @param size    the number of values per vertex that are stored in the array. The initial value is 4. One of:<br><table><tr><td>1</td><td>2</td><td>3</td><td>4</td><td>{@link GL12#GL_BGRA BGRA}</td></tr></table>
     * @param type    the data type of each component in the array. One of:<br><table><tr><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_SHORT SHORT}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param stride  the byte offset between consecutive generic vertex attributes. If stride is 0, the generic vertex attributes are understood to be tightly packed in
     *                the array. The initial value is 0.
     * @param pointer the vertex attribute data or the offset of the first component of the first generic vertex attribute in the array in the data store of the buffer
     *                currently bound to the {@link GL15#GL_ARRAY_BUFFER ARRAY_BUFFER} target. The initial value is 0.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttribIPointer">Reference Page</a>
     */
    fun vertexAttribIPointer(index: Int, size: Int, type: Int, stride: Int, pointer: Int) =
        GL30C.nglVertexAttribIPointer(index, size, type, stride, pointer.L)

    /**
     * Specifies the location and organization of a pure integer vertex attribute array.
     *
     * @param index   the index of the pure integer generic vertex attribute to be modified
     * @param size    the number of values per vertex that are stored in the array. The initial value is 4. One of:<br><table><tr><td>1</td><td>2</td><td>3</td><td>4</td><td>{@link GL12#GL_BGRA BGRA}</td></tr></table>
     * @param type    the data type of each component in the array. One of:<br><table><tr><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_SHORT SHORT}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param stride  the byte offset between consecutive generic vertex attributes. If stride is 0, the generic vertex attributes are understood to be tightly packed in
     *                the array. The initial value is 0.
     * @param pointer the vertex attribute data or the offset of the first component of the first generic vertex attribute in the array in the data store of the buffer
     *                currently bound to the {@link GL15#GL_ARRAY_BUFFER ARRAY_BUFFER} target. The initial value is 0.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttribIPointer">Reference Page</a>
     */
//    public static void glVertexAttribIPointer(@NativeType("GLuint") int index, @NativeType("GLint") int size, @NativeType("GLenum") int type, @NativeType("GLsizei") int stride, @NativeType("void const *") ShortBuffer pointer) {
//        nglVertexAttribIPointer(index, size, type, stride, memAddress(pointer));
//    }
//
//    /**
//     * Specifies the location and organization of a pure integer vertex attribute array.
//     *
//     * @param index   the index of the pure integer generic vertex attribute to be modified
//     * @param size    the number of values per vertex that are stored in the array. The initial value is 4. One of:<br><table><tr><td>1</td><td>2</td><td>3</td><td>4</td><td>{@link GL12#GL_BGRA BGRA}</td></tr></table>
//     * @param type    the data type of each component in the array. One of:<br><table><tr><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_SHORT SHORT}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
//     * @param stride  the byte offset between consecutive generic vertex attributes. If stride is 0, the generic vertex attributes are understood to be tightly packed in
//     *                the array. The initial value is 0.
//     * @param pointer the vertex attribute data or the offset of the first component of the first generic vertex attribute in the array in the data store of the buffer
//     *                currently bound to the {@link GL15#GL_ARRAY_BUFFER ARRAY_BUFFER} target. The initial value is 0.
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexAttribIPointer">Reference Page</a>
//     */
//    public static void glVertexAttribIPointer(@NativeType("GLuint") int index, @NativeType("GLint") int size, @NativeType("GLenum") int type, @NativeType("GLsizei") int stride, @NativeType("void const *") IntBuffer pointer) {
//        nglVertexAttribIPointer(index, size, type, stride, memAddress(pointer));
//    }
//
//    // --- [ glGetVertexAttribIiv ] ---
//
//    /** Unsafe version of: {@link #glGetVertexAttribIiv GetVertexAttribIiv} */
//    public static native void nglGetVertexAttribIiv(int index, int pname, long params);
//
//    /**
//     * Returns the value of a pure integer generic vertex attribute parameter.
//     *
//     * @param index  the index of the pure integer generic vertex attribute to be modified
//     * @param pname  the symbolic name of the vertex attribute parameter to be queried. Must be:<br><table><tr><td>{@link GL20#GL_CURRENT_VERTEX_ATTRIB CURRENT_VERTEX_ATTRIB}</td></tr></table>
//     * @param params returns the requested data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetVertexAttrib">Reference Page</a>
//     */
//    public static void glGetVertexAttribIiv(@NativeType("GLuint") int index, @NativeType("GLenum") int pname, @NativeType("GLint *") IntBuffer params) {
//        if (CHECKS) {
//            check(params, 4);
//        }
//        nglGetVertexAttribIiv(index, pname, memAddress(params));
//    }
//
    /**
     * Returns the value of a pure integer generic vertex attribute parameter.
     *
     * @param index the index of the pure integer generic vertex attribute to be modified
     * @param name the symbolic name of the vertex attribute parameter to be queried. Must be:<br><table><tr><td>{@link GL20#GL_CURRENT_VERTEX_ATTRIB CURRENT_VERTEX_ATTRIB}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetVertexAttrib">Reference Page</a>
     */
    fun getVertexAttribI(index: Int, name: Int): Int = readInt { GL30C.nglGetVertexAttribIiv(index, name, it) }

    // --- [ glGetVertexAttribIuiv ] ---

    /**
     * Unsigned version of {@link #glGetVertexAttribIiv GetVertexAttribIiv}.
     *
     * @param index the index of the pure integer generic vertex attribute to be modified
     * @param name the symbolic name of the vertex attribute parameter to be queried. Must be:<br><table><tr><td>{@link GL20#GL_CURRENT_VERTEX_ATTRIB CURRENT_VERTEX_ATTRIB}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetVertexAttrib">Reference Page</a>
     */
    fun getVertexAttribIu(index: Int, name: Int): UInt = readUInt { GL30C.nglGetVertexAttribIuiv(index, name, it) }

    // --- [ glUniform1ui ] ---

    /**
     * Specifies the value of a uint uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: Int, v: Uint) = GL30C.glUniform1ui(location, v.v)

    /**
     * Specifies the value of a uint uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param v0       the uniform value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: Int, v0: Vec1ui) = GL30C.glUniform1ui(location, v0.x.v)

    // --- [ glUniform2ui ] ---

    /**
     * Specifies the value of a uvec2 uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param v0       the uniform x value
     * @param v1       the uniform y value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: Int, v0: Uint, v1: Uint) = GL30C.glUniform2ui(location, v0.v, v1.v)

    /**
     * Specifies the value of a uvec2 uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: Int, v: Vec2ui) = GL30C.glUniform2ui(location, v.x.v, v.y.v)

    // --- [ glUniform3ui ] ---

    /**
     * Specifies the value of a uvec3 uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param v0       the uniform x value
     * @param v1       the uniform y value
     * @param v2       the uniform z value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: Int, v0: Uint, v1: Uint, v2: Uint) = GL30C.glUniform3ui(location, v0.v, v1.v, v2.v)

    /**
     * Specifies the value of a uvec3 uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: Int, v: Vec3ui) = GL30C.glUniform3ui(location, v.x.v, v.y.v, v.z.v)

    // --- [ glUniform4ui ] ---

    /**
     * Specifies the value of a uvec4 uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param v0       the uniform x value
     * @param v1       the uniform y value
     * @param v2       the uniform z value
     * @param v3       the uniform w value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: Int, v0: Uint, v1: Uint, v2: Uint, v3: Uint) = GL30C.glUniform4ui(location, v0.v, v1.v, v2.v, v3.v)

    /**
     * Specifies the value of a uvec4 uniform variable for the current program object.
     *
     * @param location the location of the uniform variable to be modified
     * @param v        the uniform
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: Int, v: Vec4ui) = GL30C.glUniform4ui(location, v.x.v, v.y.v, v.z.v, v.w.v)

//    // --- [ glUniform1uiv ] --- TODO?
//
//    /**
//     * Unsafe version of: {@link #glUniform1uiv Uniform1uiv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglUniform1uiv(int location, int count, long value);
//
//    /**
//     * Specifies the value of a single uint uniform variable or a uint uniform variable array for the current program object.
//     *
//     * @param location the location of the uniform variable to be modified
//     * @param value    a pointer to an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
//     */
//    public static void glUniform1uiv(@NativeType("GLint") int location, @NativeType("GLuint const *") IntBuffer value) {
//        nglUniform1uiv(location, value.remaining(), memAddress(value));
//    }
//
//    // --- [ glUniform2uiv ] ---
//
//    /**
//     * Unsafe version of: {@link #glUniform2uiv Uniform2uiv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglUniform2uiv(int location, int count, long value);
//
//    /**
//     * Specifies the value of a single uvec2 uniform variable or a uvec2 uniform variable array for the current program object.
//     *
//     * @param location the location of the uniform variable to be modified
//     * @param value    a pointer to an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
//     */
//    public static void glUniform2uiv(@NativeType("GLint") int location, @NativeType("GLuint const *") IntBuffer value) {
//        nglUniform2uiv(location, value.remaining() >> 1, memAddress(value));
//    }
//
//    // --- [ glUniform3uiv ] ---
//
//    /**
//     * Unsafe version of: {@link #glUniform3uiv Uniform3uiv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglUniform3uiv(int location, int count, long value);
//
//    /**
//     * Specifies the value of a single uvec3 uniform variable or a uvec3 uniform variable array for the current program object.
//     *
//     * @param location the location of the uniform variable to be modified
//     * @param value    a pointer to an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
//     */
//    public static void glUniform3uiv(@NativeType("GLint") int location, @NativeType("GLuint const *") IntBuffer value) {
//        nglUniform3uiv(location, value.remaining() / 3, memAddress(value));
//    }
//
//    // --- [ glUniform4uiv ] ---
//
//    /**
//     * Unsafe version of: {@link #glUniform4uiv Uniform4uiv}
//     *
//     * @param count the number of elements that are to be modified. This should be 1 if the targeted uniform variable is not an array, and 1 or more if it is an array.
//     */
//    public static native void nglUniform4uiv(int location, int count, long value);
//
//    /**
//     * Specifies the value of a single uvec4 uniform variable or a uvec4 uniform variable array for the current program object.
//     *
//     * @param location the location of the uniform variable to be modified
//     * @param value    a pointer to an array of {@code count} values that will be used to update the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
//     */
//    public static void glUniform4uiv(@NativeType("GLint") int location, @NativeType("GLuint const *") IntBuffer value) {
//        nglUniform4uiv(location, value.remaining() >> 2, memAddress(value));
//    }
//
//    // --- [ glGetUniformuiv ] ---
//
//    /** Unsafe version of: {@link #glGetUniformuiv GetUniformuiv} */
//    public static native void nglGetUniformuiv(int program, int location, long params);
//
//    /**
//     * Returns the uint value(s) of a uniform variable.
//     *
//     * @param program  the program object to be queried
//     * @param location the location of the uniform variable to be queried
//     * @param params   the value of the specified uniform variable
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetUniform">Reference Page</a>
//     */
//    public static void glGetUniformuiv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLuint *") IntBuffer params) {
//        if (CHECKS) {
//            check(params, 1);
//        }
//        nglGetUniformuiv(program, location, memAddress(params));
//    }
//
//    /**
//     * Returns the uint value(s) of a uniform variable.
//     *
//     * @param program  the program object to be queried
//     * @param location the location of the uniform variable to be queried
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetUniform">Reference Page</a>
//     */
//    @NativeType("void")
//    public static int glGetUniformui(@NativeType("GLuint") int program, @NativeType("GLint") int location) {
//        MemoryStack stack = stackGet(); int stackPointer = stack.getPointer();
//        try {
//            IntBuffer params = stack.callocInt(1);
//            nglGetUniformuiv(program, location, memAddress(params));
//            return params.get(0);
//        } finally {
//            stack.setPointer(stackPointer);
//        }
//    }

    // --- [ glBindFragDataLocation ] ---

    /**
     * Binds a user-defined varying out variable to a fragment shader color number.
     *
     * @param program     the name of the program containing varying out variable whose binding to modify
     * @param colorNumber the color number to bind the user-defined varying out variable to
     * @param name        the name of the user-defined varying out variable whose binding to modify
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBindFragDataLocation">Reference Page</a>
     */
    fun bindFragDataLocation(program: GlProgram, colorNumber: Int, name: CharSequence) =
        stack.writeAscii(name) { GL30C.nglBindFragDataLocation(program.name, colorNumber, it.adr.L) }

    // --- [ glGetFragDataLocation ] ---

    /**
     * Queries the bindings of color numbers to user-defined varying out variables.
     *
     * @param program the name of the program containing varying out variable whose binding to query
     * @param name    the name of the user-defined varying out variable whose binding to query
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetFragDataLocation">Reference Page</a>
     */
    fun getFragDataLocation(program: GlProgram, name: CharSequence): Int =
        stack.writeAscii(name) { GL30C.nglGetFragDataLocation(program.name, it.adr.L) }

    // --- [ glBeginConditionalRender ] ---

    /**
     * Starts conditional rendering.
     *
     * @param id   the name of an occlusion query object whose results are used to determine if the rendering commands are discarded
     * @param mode how {@code glBeginConditionalRender} interprets the results of the occlusion query. One of:<br><table><tr><td>{@link #GL_QUERY_WAIT QUERY_WAIT}</td><td>{@link #GL_QUERY_NO_WAIT QUERY_NO_WAIT}</td><td>{@link #GL_QUERY_BY_REGION_WAIT QUERY_BY_REGION_WAIT}</td></tr><tr><td>{@link #GL_QUERY_BY_REGION_NO_WAIT QUERY_BY_REGION_NO_WAIT}</td><td>{@link GL45#GL_QUERY_WAIT_INVERTED QUERY_WAIT_INVERTED}</td><td>{@link GL45#GL_QUERY_NO_WAIT_INVERTED QUERY_NO_WAIT_INVERTED}</td></tr><tr><td>{@link GL45#GL_QUERY_BY_REGION_WAIT_INVERTED QUERY_BY_REGION_WAIT_INVERTED}</td><td>{@link GL45#GL_QUERY_BY_REGION_NO_WAIT_INVERTED QUERY_BY_REGION_NO_WAIT_INVERTED}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBeginConditionalRender">Reference Page</a>
     */
    fun beginConditionalRender(id: GlQuery, mode: ConditionalMode) = GL30C.glBeginConditionalRender(id.name, mode.i)

    // --- [ glEndConditionalRender ] ---

    /**
     * Ends conditional rendering.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glEndConditionalRender">Reference Page</a>
     */
    fun endConditionalRender() = GL30C.glEndConditionalRender()

    // --- [ glMapBufferRange ] ---

    /**
     * Maps a section of a buffer object's data store.
     *
     * <p><b>LWJGL note</b>: This method comes in 2 flavors:</p>
     *
     * <ol>
     * <li>{@link #glMapBufferRange(int, long, long, int)} - Always returns a new ByteBuffer instance.</li>
     * <li>{@link #glMapBufferRange(int, long, long, int, ByteBuffer)} - The {@code old_buffer} parameter is reused if not null.</li>
     * </ol>
     *
     * @param target a binding to which the target buffer is bound. One of:<br><table><tr><td>{@link GL15#GL_ARRAY_BUFFER ARRAY_BUFFER}</td><td>{@link GL15#GL_ELEMENT_ARRAY_BUFFER ELEMENT_ARRAY_BUFFER}</td><td>{@link GL21#GL_PIXEL_PACK_BUFFER PIXEL_PACK_BUFFER}</td><td>{@link GL21#GL_PIXEL_UNPACK_BUFFER PIXEL_UNPACK_BUFFER}</td></tr><tr><td>{@link #GL_TRANSFORM_FEEDBACK_BUFFER TRANSFORM_FEEDBACK_BUFFER}</td><td>{@link GL31#GL_UNIFORM_BUFFER UNIFORM_BUFFER}</td><td>{@link GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link GL31#GL_COPY_READ_BUFFER COPY_READ_BUFFER}</td></tr><tr><td>{@link GL31#GL_COPY_WRITE_BUFFER COPY_WRITE_BUFFER}</td><td>{@link GL40#GL_DRAW_INDIRECT_BUFFER DRAW_INDIRECT_BUFFER}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td><td>{@link GL43#GL_DISPATCH_INDIRECT_BUFFER DISPATCH_INDIRECT_BUFFER}</td></tr><tr><td>{@link GL43#GL_SHADER_STORAGE_BUFFER SHADER_STORAGE_BUFFER}</td><td>{@link ARBIndirectParameters#GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB}</td></tr></table>
     * @param offset the starting offset within the buffer of the range to be mapped
     * @param length the length of the range to be mapped
     * @param access a combination of access flags indicating the desired access to the range. One or more of:<br><table><tr><td>{@link #GL_MAP_READ_BIT MAP_READ_BIT}</td><td>{@link #GL_MAP_WRITE_BIT MAP_WRITE_BIT}</td><td>{@link #GL_MAP_INVALIDATE_RANGE_BIT MAP_INVALIDATE_RANGE_BIT}</td><td>{@link #GL_MAP_INVALIDATE_BUFFER_BIT MAP_INVALIDATE_BUFFER_BIT}</td></tr><tr><td>{@link #GL_MAP_FLUSH_EXPLICIT_BIT MAP_FLUSH_EXPLICIT_BIT}</td><td>{@link #GL_MAP_UNSYNCHRONIZED_BIT MAP_UNSYNCHRONIZED_BIT}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glMapBufferRange">Reference Page</a>
     */
    fun mapBufferRange(target: BufferTarget, offset: Int, length: Int, access: BufferMapFlags): ByteBuffer? {
        val result = GL30C.nglMapBufferRange(target.i, offset.L, length.L, access)
        return memByteBufferSafe(result, length)
    }

    // --- [ glFlushMappedBufferRange ] ---

    /**
     * Indicates modifications to a range of a mapped buffer.
     *
     * @param target the target of the flush operation. One of:<br><table><tr><td>{@link GL15#GL_ARRAY_BUFFER ARRAY_BUFFER}</td><td>{@link GL15#GL_ELEMENT_ARRAY_BUFFER ELEMENT_ARRAY_BUFFER}</td><td>{@link GL21#GL_PIXEL_PACK_BUFFER PIXEL_PACK_BUFFER}</td><td>{@link GL21#GL_PIXEL_UNPACK_BUFFER PIXEL_UNPACK_BUFFER}</td></tr><tr><td>{@link #GL_TRANSFORM_FEEDBACK_BUFFER TRANSFORM_FEEDBACK_BUFFER}</td><td>{@link GL31#GL_UNIFORM_BUFFER UNIFORM_BUFFER}</td><td>{@link GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link GL31#GL_COPY_READ_BUFFER COPY_READ_BUFFER}</td></tr><tr><td>{@link GL31#GL_COPY_WRITE_BUFFER COPY_WRITE_BUFFER}</td><td>{@link GL40#GL_DRAW_INDIRECT_BUFFER DRAW_INDIRECT_BUFFER}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td><td>{@link GL43#GL_DISPATCH_INDIRECT_BUFFER DISPATCH_INDIRECT_BUFFER}</td></tr><tr><td>{@link GL43#GL_SHADER_STORAGE_BUFFER SHADER_STORAGE_BUFFER}</td><td>{@link ARBIndirectParameters#GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB}</td></tr></table>
     * @param offset the start of the buffer subrange, in basic machine units
     * @param length the length of the buffer subrange, in basic machine units
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFlushMappedBufferRange">Reference Page</a>
     */
    fun flushMappedBufferRange(target: BufferTarget, offset: Int, length: Int) = GL30C.glFlushMappedBufferRange(target.i, offset.L, length.L)

    // --- [ glClampColor ] ---

    /**
     * Controls color clamping.
     *
     * @param target target for color clamping. Must be:<br><table><tr><td>{@link #GL_CLAMP_READ_COLOR CLAMP_READ_COLOR}</td></tr></table>
     * @param clamp  whether to apply color clamping. One of:<br><table><tr><td>{@link GL11#GL_TRUE TRUE}</td><td>{@link GL11#GL_FALSE FALSE}</td><td>{@link #GL_FIXED_ONLY FIXED_ONLY}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClampColor">Reference Page</a>
     */
    fun clampColor(clamp: ClampColor) = GL30C.glClampColor(GL30C.GL_CLAMP_READ_COLOR, clamp.i)

    // --- [ glIsRenderbuffer ] ---

    /**
     * Determines if a name corresponds to a renderbuffer object.
     *
     * @param renderbuffer a value that may be the name of a renderbuffer object
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glIsRenderbuffer">Reference Page</a>
     */
    fun isRenderbuffer(renderbuffer: GlRenderbuffer) = GL30C.glIsRenderbuffer(renderbuffer.name)

    // --- [ glBindRenderbuffer ] ---

    /**
     * Binds a renderbuffer to a renderbuffer target.
     *
     * @param target       the renderbuffer target of the binding operation. Must be:<br><table><tr><td>{@link #GL_RENDERBUFFER RENDERBUFFER}</td></tr></table>
     * @param renderbuffer the name of the renderbuffer object to bind
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBindRenderbuffer">Reference Page</a>
     */
    fun bindRenderbuffer(renderbuffer: GlRenderbuffer) = GL30C.glBindRenderbuffer(GL30C.GL_RENDERBUFFER, renderbuffer.name)

    // --- [ glDeleteRenderbuffers ] ---

    /**
     * Deletes renderbuffer objects.
     *
     * @param renderbuffer an array containing {@code n} renderbuffer objects to be deleted
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteRenderbuffers">Reference Page</a>
     */
    fun deleteRenderbuffers(renderbuffer: GlRenderbuffer) = GL30C.nglDeleteRenderbuffers(1, renderbuffer.name.toOffHeap())

    /**
     * Deletes renderbuffer objects.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteRenderbuffers">Reference Page</a>
     */
    fun deleteRenderbuffers(renderbuffers: GlRenderbuffers) = GL30C.nglDeleteRenderbuffers(renderbuffers.rem, renderbuffers.adr.L)

    // --- [ glGenRenderbuffers ] ---

    /**
     * Generates renderbuffer object names.
     *
     * @param renderbuffers a buffer in which the generated renderbuffer object names are stored
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenRenderbuffers">Reference Page</a>
     */
    fun genRenderbuffers(renderbuffers: GlRenderbuffers) = GL30C.nglGenRenderbuffers(renderbuffers.rem, renderbuffers.adr.L)

    /**
     * Generates renderbuffer object names.
     *
     * @param size the size of renderbuffers to generated
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenRenderbuffers">Reference Page</a>
     */
    fun genRenderbuffers(size: Int): GlRenderbuffers = GlRenderbuffers(size)

    /**
     * Generates renderbuffer object names.
     *
     * @param size the size of renderbuffers to generated
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenRenderbuffers">Reference Page</a>
     */
    fun genRenderbuffers(renderbuffer: KMutableProperty0<GlRenderbuffer>): GlRenderbuffer {
        renderbuffer.set(genRenderbuffers())
        return renderbuffer()
    }

    /**
     * Generates renderbuffer object names.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenRenderbuffers">Reference Page</a>
     */
    fun genRenderbuffers(): GlRenderbuffer = GlRenderbuffer(GL30C.glGenRenderbuffers())

    // --- [ glRenderbufferStorage ] ---

    /**
     * Establishes data storage, format and dimensions of a renderbuffer object's image.
     *
     * @param target         the target of the allocation. Must be:<br><table><tr><td>{@link #GL_RENDERBUFFER RENDERBUFFER}</td></tr></table>
     * @param internalformat the internal format to use for the renderbuffer object's image. Must be a color-renderable, depth-renderable, or stencil-renderable format.
     * @param size           the size of the renderbuffer, in pixels
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glRenderbufferStorage">Reference Page</a>
     */
    fun renderbufferStorage(internalFormat: gl.InternalFormat, size: Vec2i) = GL30C.glRenderbufferStorage(GL30C.GL_RENDERBUFFER, internalFormat.i, size.x, size.y)

    /**
     * Establishes data storage, format and dimensions of a renderbuffer object's image.
     *
     * @param target         the target of the allocation. Must be:<br><table><tr><td>{@link #GL_RENDERBUFFER RENDERBUFFER}</td></tr></table>
     * @param internalformat the internal format to use for the renderbuffer object's image. Must be a color-renderable, depth-renderable, or stencil-renderable format.
     * @param width          the width of the renderbuffer, in pixels
     * @param height         the height of the renderbuffer, in pixels
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glRenderbufferStorage">Reference Page</a>
     */
    fun renderbufferStorage(internalFormat: gl.InternalFormat, width: Int, height: Int) = GL30C.glRenderbufferStorage(GL30C.GL_RENDERBUFFER, internalFormat.i, width, height)

    // --- [ glRenderbufferStorageMultisample ] ---

    /**
     * Establishes data storage, format, dimensions and sample count of a renderbuffer object's image.
     *
     * <p>{@link #glRenderbufferStorage RenderbufferStorage} is equivalent to calling this method with the samples set to zero.</p>
     *
     * @param target         the target of the allocation. Must be:<br><table><tr><td>{@link #GL_RENDERBUFFER RENDERBUFFER}</td></tr></table>
     * @param samples        the number of samples to be used for the renderbuffer object's storage
     * @param internalformat the internal format to use for the renderbuffer object's image. Must be a color-renderable, depth-renderable, or stencil-renderable format.
     * @param width          the width of the renderbuffer, in pixels
     * @param height         the height of the renderbuffer, in pixels
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glRenderbufferStorageMultisample">Reference Page</a>
     */
    fun renderbufferStorageMultisample(samples: Int, internalFormat: gl.InternalFormat, width: Int, height: Int) = GL30C.glRenderbufferStorageMultisample(GL30C.GL_RENDERBUFFER, samples, internalFormat.i, width, height)

    /**
     * Establishes data storage, format, dimensions and sample count of a renderbuffer object's image.
     *
     * <p>{@link #glRenderbufferStorage RenderbufferStorage} is equivalent to calling this method with the samples set to zero.</p>
     *
     * @param target         the target of the allocation. Must be:<br><table><tr><td>{@link #GL_RENDERBUFFER RENDERBUFFER}</td></tr></table>
     * @param samples        the number of samples to be used for the renderbuffer object's storage
     * @param internalformat the internal format to use for the renderbuffer object's image. Must be a color-renderable, depth-renderable, or stencil-renderable format.
     * @param size           the size of the renderbuffer, in pixels
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glRenderbufferStorageMultisample">Reference Page</a>
     */
    fun renderbufferStorageMultisample(samples: Int, internalFormat: gl.InternalFormat, size: Vec2i) = GL30C.glRenderbufferStorageMultisample(GL30C.GL_RENDERBUFFER, samples, internalFormat.i, size.x, size.y)

    // --- [ glGetRenderbufferParameteriv ] ---

    /**
     * Retrieves information about a bound renderbuffer object.
     *
     * @param target the target of the query operation. Must be:<br><table><tr><td>{@link #GL_RENDERBUFFER RENDERBUFFER}</td></tr></table>
     * @param pname  the parameter whose value to retrieve from the renderbuffer bound to {@code target}. One of:<br><table><tr><td>{@link #GL_RENDERBUFFER_WIDTH RENDERBUFFER_WIDTH}</td><td>{@link #GL_RENDERBUFFER_HEIGHT RENDERBUFFER_HEIGHT}</td><td>{@link #GL_RENDERBUFFER_INTERNAL_FORMAT RENDERBUFFER_INTERNAL_FORMAT}</td></tr><tr><td>{@link #GL_RENDERBUFFER_RED_SIZE RENDERBUFFER_RED_SIZE}</td><td>{@link #GL_RENDERBUFFER_GREEN_SIZE RENDERBUFFER_GREEN_SIZE}</td><td>{@link #GL_RENDERBUFFER_BLUE_SIZE RENDERBUFFER_BLUE_SIZE}</td></tr><tr><td>{@link #GL_RENDERBUFFER_ALPHA_SIZE RENDERBUFFER_ALPHA_SIZE}</td><td>{@link #GL_RENDERBUFFER_DEPTH_SIZE RENDERBUFFER_DEPTH_SIZE}</td><td>{@link #GL_RENDERBUFFER_STENCIL_SIZE RENDERBUFFER_STENCIL_SIZE}</td></tr><tr><td>{@link #GL_RENDERBUFFER_SAMPLES RENDERBUFFER_SAMPLES}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetRenderbufferParameter">Reference Page</a>
     */
    fun getRenderbufferParameter(name: GetRenderbuffer): Int = GL30C.glGetRenderbufferParameteri(GL30C.GL_RENDERBUFFER, name.i)

    // --- [ glIsFramebuffer ] ---

    /**
     * Determines if a name corresponds to a framebuffer object.
     *
     * @param framebuffer a value that may be the name of a framebuffer object
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glIsFramebuffer">Reference Page</a>
     */
    fun isFramebuffer(framebuffer: GlFramebuffer) = GL30C.glIsFramebuffer(framebuffer.name)

    // --- [ glBindFramebuffer ] ---

    /**
     * Binds a framebuffer to a framebuffer target.
     *
     * @param target      the framebuffer target of the binding operation. One of:<br><table><tr><td>{@link #GL_FRAMEBUFFER FRAMEBUFFER}</td><td>{@link #GL_READ_FRAMEBUFFER READ_FRAMEBUFFER}</td><td>{@link #GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER}</td></tr></table>
     * @param framebuffer the name of the framebuffer object to bind
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBindFramebuffer">Reference Page</a>
     */
    fun bindFramebuffer(target: FramebufferBindTarget, framebuffer: GlFramebuffer) = GL30C.glBindFramebuffer(target.i, framebuffer.name)

    /**
     * Binds a framebuffer to a framebuffer target.
     *
     * @param framebuffer the name of the framebuffer object to bind
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBindFramebuffer">Reference Page</a>
     */
    fun bindFramebuffer(framebuffer: GlFramebuffer) = GL30C.glBindFramebuffer(FramebufferBindTarget.BOTH.i, framebuffer.name)

    // --- [ glDeleteFramebuffers ] ---

    /**
     * Deletes framebuffer objects.
     *
     * @param framebuffers an array containing {@code n} framebuffer objects to be deleted
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteFramebuffers">Reference Page</a>
     */
    fun deleteFramebuffers(framebuffers: GlFramebuffers) = GL30C.nglDeleteFramebuffers(framebuffers.rem, framebuffers.adr.L)

    /**
     * Deletes framebuffer objects.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteFramebuffers">Reference Page</a>
     */
    fun deleteFramebuffer(framebuffer: GlFramebuffer) = GL30C.glDeleteFramebuffers(framebuffer.name)

    // --- [ glGenFramebuffers ] ---

    /**
     * Generates framebuffer object names.
     *
     * @param framebuffers a buffer in which the generated framebuffer object names are stored
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenFramebuffers">Reference Page</a>
     */
    fun genFramebuffers(framebuffers: GlFramebuffers) = GL30C.nglGenFramebuffers(framebuffers.rem, framebuffers.adr.L)

    /**
     * Generates framebuffer object names.
     *
     * @param size the number of framebuffer object names to generate
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenFramebuffers">Reference Page</a>
     */
    fun genFramebuffers(size: Int): GlFramebuffers = GlFramebuffers(size).also(::genFramebuffers)

    /**
     * Generates framebuffer object names.
     *
     * @param size the number of framebuffer object names to generate
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenFramebuffers">Reference Page</a>
     */
    fun genFramebuffers(framebuffer: KMutableProperty0<GlFramebuffer>): GlFramebuffer {
        framebuffer.set(genFramebuffers())
        return framebuffer()
    }

    /**
     * Generates framebuffer object names.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenFramebuffers">Reference Page</a>
     */
    fun genFramebuffers(): GlFramebuffer = GlFramebuffer(GL30C.glGenFramebuffers())

    // --- [ glCheckFramebufferStatus ] ---

    /**
     * Checks the completeness status of a framebuffer.
     *
     * @param target the target of the framebuffer completeness check. One of:<br><table><tr><td>{@link #GL_FRAMEBUFFER FRAMEBUFFER}</td><td>{@link #GL_READ_FRAMEBUFFER READ_FRAMEBUFFER}</td><td>{@link #GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCheckFramebufferStatus">Reference Page</a>
     */
    fun checkFramebufferStatus(target: FramebufferTarget = FramebufferTarget.DRAW): FramebufferStatus =
        FramebufferStatus(GL30C.glCheckFramebufferStatus(target.i))

    // --- [ glFramebufferTexture1D ] ---

    /**
     * Attaches a level of a 1D texture object as a logical buffer to the currently bound framebuffer object.
     *
     * @param target     the framebuffer target. One of:<br><table><tr><td>{@link #GL_FRAMEBUFFER FRAMEBUFFER}</td><td>{@link #GL_READ_FRAMEBUFFER READ_FRAMEBUFFER}</td><td>{@link #GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER}</td></tr></table>
     * @param attachment the attachment point of the framebuffer. One of:<br><table><tr><td>{@link #GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td><td>{@link #GL_COLOR_ATTACHMENT1 COLOR_ATTACHMENT1}</td><td>{@link #GL_COLOR_ATTACHMENT2 COLOR_ATTACHMENT2}</td><td>{@link #GL_COLOR_ATTACHMENT3 COLOR_ATTACHMENT3}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT4 COLOR_ATTACHMENT4}</td><td>{@link #GL_COLOR_ATTACHMENT5 COLOR_ATTACHMENT5}</td><td>{@link #GL_COLOR_ATTACHMENT6 COLOR_ATTACHMENT6}</td><td>{@link #GL_COLOR_ATTACHMENT7 COLOR_ATTACHMENT7}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT8 COLOR_ATTACHMENT8}</td><td>{@link #GL_COLOR_ATTACHMENT9 COLOR_ATTACHMENT9}</td><td>{@link #GL_COLOR_ATTACHMENT10 COLOR_ATTACHMENT10}</td><td>{@link #GL_COLOR_ATTACHMENT11 COLOR_ATTACHMENT11}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT12 COLOR_ATTACHMENT12}</td><td>{@link #GL_COLOR_ATTACHMENT13 COLOR_ATTACHMENT13}</td><td>{@link #GL_COLOR_ATTACHMENT14 COLOR_ATTACHMENT14}</td><td>{@link #GL_COLOR_ATTACHMENT15 COLOR_ATTACHMENT15}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT16 COLOR_ATTACHMENT16}</td><td>{@link #GL_COLOR_ATTACHMENT17 COLOR_ATTACHMENT17}</td><td>{@link #GL_COLOR_ATTACHMENT18 COLOR_ATTACHMENT18}</td><td>{@link #GL_COLOR_ATTACHMENT19 COLOR_ATTACHMENT19}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT20 COLOR_ATTACHMENT20}</td><td>{@link #GL_COLOR_ATTACHMENT21 COLOR_ATTACHMENT21}</td><td>{@link #GL_COLOR_ATTACHMENT22 COLOR_ATTACHMENT22}</td><td>{@link #GL_COLOR_ATTACHMENT23 COLOR_ATTACHMENT23}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT24 COLOR_ATTACHMENT24}</td><td>{@link #GL_COLOR_ATTACHMENT25 COLOR_ATTACHMENT25}</td><td>{@link #GL_COLOR_ATTACHMENT26 COLOR_ATTACHMENT26}</td><td>{@link #GL_COLOR_ATTACHMENT27 COLOR_ATTACHMENT27}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT28 COLOR_ATTACHMENT28}</td><td>{@link #GL_COLOR_ATTACHMENT29 COLOR_ATTACHMENT29}</td><td>{@link #GL_COLOR_ATTACHMENT30 COLOR_ATTACHMENT30}</td><td>{@link #GL_COLOR_ATTACHMENT31 COLOR_ATTACHMENT31}</td></tr><tr><td>{@link #GL_DEPTH_ATTACHMENT DEPTH_ATTACHMENT}</td><td>{@link #GL_STENCIL_ATTACHMENT STENCIL_ATTACHMENT}</td><td>{@link #GL_DEPTH_STENCIL_ATTACHMENT DEPTH_STENCIL_ATTACHMENT}</td></tr></table>
     * @param texTarget  the type of texture
     * @param texture    the texture object to attach to the framebuffer attachment point named by {@code attachment}
     * @param level      the mipmap level of {@code texture} to attach
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferTexture1D">Reference Page</a>
     */
    fun framebufferTexture1D(target: FramebufferTarget, attachment: Attachment, texTarget: TextureTarget, texture: GlTexture, level: Int = 0) =
        GL30C.glFramebufferTexture1D(target.i, attachment.i, texTarget.i, texture.name, level)

    /**
     * Attaches a level of a 1D texture object as a logical buffer to the currently bound framebuffer object.
     *
     * @param attachment the attachment point of the framebuffer. One of:<br><table><tr><td>{@link #GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td><td>{@link #GL_COLOR_ATTACHMENT1 COLOR_ATTACHMENT1}</td><td>{@link #GL_COLOR_ATTACHMENT2 COLOR_ATTACHMENT2}</td><td>{@link #GL_COLOR_ATTACHMENT3 COLOR_ATTACHMENT3}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT4 COLOR_ATTACHMENT4}</td><td>{@link #GL_COLOR_ATTACHMENT5 COLOR_ATTACHMENT5}</td><td>{@link #GL_COLOR_ATTACHMENT6 COLOR_ATTACHMENT6}</td><td>{@link #GL_COLOR_ATTACHMENT7 COLOR_ATTACHMENT7}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT8 COLOR_ATTACHMENT8}</td><td>{@link #GL_COLOR_ATTACHMENT9 COLOR_ATTACHMENT9}</td><td>{@link #GL_COLOR_ATTACHMENT10 COLOR_ATTACHMENT10}</td><td>{@link #GL_COLOR_ATTACHMENT11 COLOR_ATTACHMENT11}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT12 COLOR_ATTACHMENT12}</td><td>{@link #GL_COLOR_ATTACHMENT13 COLOR_ATTACHMENT13}</td><td>{@link #GL_COLOR_ATTACHMENT14 COLOR_ATTACHMENT14}</td><td>{@link #GL_COLOR_ATTACHMENT15 COLOR_ATTACHMENT15}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT16 COLOR_ATTACHMENT16}</td><td>{@link #GL_COLOR_ATTACHMENT17 COLOR_ATTACHMENT17}</td><td>{@link #GL_COLOR_ATTACHMENT18 COLOR_ATTACHMENT18}</td><td>{@link #GL_COLOR_ATTACHMENT19 COLOR_ATTACHMENT19}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT20 COLOR_ATTACHMENT20}</td><td>{@link #GL_COLOR_ATTACHMENT21 COLOR_ATTACHMENT21}</td><td>{@link #GL_COLOR_ATTACHMENT22 COLOR_ATTACHMENT22}</td><td>{@link #GL_COLOR_ATTACHMENT23 COLOR_ATTACHMENT23}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT24 COLOR_ATTACHMENT24}</td><td>{@link #GL_COLOR_ATTACHMENT25 COLOR_ATTACHMENT25}</td><td>{@link #GL_COLOR_ATTACHMENT26 COLOR_ATTACHMENT26}</td><td>{@link #GL_COLOR_ATTACHMENT27 COLOR_ATTACHMENT27}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT28 COLOR_ATTACHMENT28}</td><td>{@link #GL_COLOR_ATTACHMENT29 COLOR_ATTACHMENT29}</td><td>{@link #GL_COLOR_ATTACHMENT30 COLOR_ATTACHMENT30}</td><td>{@link #GL_COLOR_ATTACHMENT31 COLOR_ATTACHMENT31}</td></tr><tr><td>{@link #GL_DEPTH_ATTACHMENT DEPTH_ATTACHMENT}</td><td>{@link #GL_STENCIL_ATTACHMENT STENCIL_ATTACHMENT}</td><td>{@link #GL_DEPTH_STENCIL_ATTACHMENT DEPTH_STENCIL_ATTACHMENT}</td></tr></table>
     * @param texTarget  the type of texture
     * @param texture    the texture object to attach to the framebuffer attachment point named by {@code attachment}
     * @param level      the mipmap level of {@code texture} to attach
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferTexture1D">Reference Page</a>
     */
    fun framebufferTexture1D(attachment: Attachment, texTarget: TextureTarget, texture: GlTexture, level: Int = 0) =
        GL30C.glFramebufferTexture1D(GL30C.GL_FRAMEBUFFER, attachment.i, texTarget.i, texture.name, level)

    // --- [ glFramebufferTexture2D ] ---

    /**
     * Attaches a level of a 2D texture object as a logical buffer to the currently bound framebuffer object.
     *
     * @param target     the framebuffer target. One of:<br><table><tr><td>{@link #GL_FRAMEBUFFER FRAMEBUFFER}</td><td>{@link #GL_READ_FRAMEBUFFER READ_FRAMEBUFFER}</td><td>{@link #GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER}</td></tr></table>
     * @param attachment the attachment point of the framebuffer. One of:<br><table><tr><td>{@link #GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td><td>{@link #GL_COLOR_ATTACHMENT1 COLOR_ATTACHMENT1}</td><td>{@link #GL_COLOR_ATTACHMENT2 COLOR_ATTACHMENT2}</td><td>{@link #GL_COLOR_ATTACHMENT3 COLOR_ATTACHMENT3}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT4 COLOR_ATTACHMENT4}</td><td>{@link #GL_COLOR_ATTACHMENT5 COLOR_ATTACHMENT5}</td><td>{@link #GL_COLOR_ATTACHMENT6 COLOR_ATTACHMENT6}</td><td>{@link #GL_COLOR_ATTACHMENT7 COLOR_ATTACHMENT7}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT8 COLOR_ATTACHMENT8}</td><td>{@link #GL_COLOR_ATTACHMENT9 COLOR_ATTACHMENT9}</td><td>{@link #GL_COLOR_ATTACHMENT10 COLOR_ATTACHMENT10}</td><td>{@link #GL_COLOR_ATTACHMENT11 COLOR_ATTACHMENT11}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT12 COLOR_ATTACHMENT12}</td><td>{@link #GL_COLOR_ATTACHMENT13 COLOR_ATTACHMENT13}</td><td>{@link #GL_COLOR_ATTACHMENT14 COLOR_ATTACHMENT14}</td><td>{@link #GL_COLOR_ATTACHMENT15 COLOR_ATTACHMENT15}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT16 COLOR_ATTACHMENT16}</td><td>{@link #GL_COLOR_ATTACHMENT17 COLOR_ATTACHMENT17}</td><td>{@link #GL_COLOR_ATTACHMENT18 COLOR_ATTACHMENT18}</td><td>{@link #GL_COLOR_ATTACHMENT19 COLOR_ATTACHMENT19}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT20 COLOR_ATTACHMENT20}</td><td>{@link #GL_COLOR_ATTACHMENT21 COLOR_ATTACHMENT21}</td><td>{@link #GL_COLOR_ATTACHMENT22 COLOR_ATTACHMENT22}</td><td>{@link #GL_COLOR_ATTACHMENT23 COLOR_ATTACHMENT23}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT24 COLOR_ATTACHMENT24}</td><td>{@link #GL_COLOR_ATTACHMENT25 COLOR_ATTACHMENT25}</td><td>{@link #GL_COLOR_ATTACHMENT26 COLOR_ATTACHMENT26}</td><td>{@link #GL_COLOR_ATTACHMENT27 COLOR_ATTACHMENT27}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT28 COLOR_ATTACHMENT28}</td><td>{@link #GL_COLOR_ATTACHMENT29 COLOR_ATTACHMENT29}</td><td>{@link #GL_COLOR_ATTACHMENT30 COLOR_ATTACHMENT30}</td><td>{@link #GL_COLOR_ATTACHMENT31 COLOR_ATTACHMENT31}</td></tr><tr><td>{@link #GL_DEPTH_ATTACHMENT DEPTH_ATTACHMENT}</td><td>{@link #GL_STENCIL_ATTACHMENT STENCIL_ATTACHMENT}</td><td>{@link #GL_DEPTH_STENCIL_ATTACHMENT DEPTH_STENCIL_ATTACHMENT}</td></tr></table>
     * @param texTarget  the type of texture
     * @param texture    the texture object to attach to the framebuffer attachment point named by {@code attachment}
     * @param level      the mipmap level of {@code texture} to attach
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferTexture2D">Reference Page</a>
     */
    fun framebufferTexture2D(target: FramebufferTarget, attachment: Attachment, texTarget: TextureTarget, texture: GlTexture, level: Int = 0) =
        GL30C.glFramebufferTexture2D(target.i, attachment.i, texTarget.i, texture.name, level)

    /**
     * Attaches a level of a 2D texture object as a logical buffer to the currently bound framebuffer object.
     *
     * @param attachment the attachment point of the framebuffer. One of:<br><table><tr><td>{@link #GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td><td>{@link #GL_COLOR_ATTACHMENT1 COLOR_ATTACHMENT1}</td><td>{@link #GL_COLOR_ATTACHMENT2 COLOR_ATTACHMENT2}</td><td>{@link #GL_COLOR_ATTACHMENT3 COLOR_ATTACHMENT3}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT4 COLOR_ATTACHMENT4}</td><td>{@link #GL_COLOR_ATTACHMENT5 COLOR_ATTACHMENT5}</td><td>{@link #GL_COLOR_ATTACHMENT6 COLOR_ATTACHMENT6}</td><td>{@link #GL_COLOR_ATTACHMENT7 COLOR_ATTACHMENT7}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT8 COLOR_ATTACHMENT8}</td><td>{@link #GL_COLOR_ATTACHMENT9 COLOR_ATTACHMENT9}</td><td>{@link #GL_COLOR_ATTACHMENT10 COLOR_ATTACHMENT10}</td><td>{@link #GL_COLOR_ATTACHMENT11 COLOR_ATTACHMENT11}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT12 COLOR_ATTACHMENT12}</td><td>{@link #GL_COLOR_ATTACHMENT13 COLOR_ATTACHMENT13}</td><td>{@link #GL_COLOR_ATTACHMENT14 COLOR_ATTACHMENT14}</td><td>{@link #GL_COLOR_ATTACHMENT15 COLOR_ATTACHMENT15}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT16 COLOR_ATTACHMENT16}</td><td>{@link #GL_COLOR_ATTACHMENT17 COLOR_ATTACHMENT17}</td><td>{@link #GL_COLOR_ATTACHMENT18 COLOR_ATTACHMENT18}</td><td>{@link #GL_COLOR_ATTACHMENT19 COLOR_ATTACHMENT19}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT20 COLOR_ATTACHMENT20}</td><td>{@link #GL_COLOR_ATTACHMENT21 COLOR_ATTACHMENT21}</td><td>{@link #GL_COLOR_ATTACHMENT22 COLOR_ATTACHMENT22}</td><td>{@link #GL_COLOR_ATTACHMENT23 COLOR_ATTACHMENT23}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT24 COLOR_ATTACHMENT24}</td><td>{@link #GL_COLOR_ATTACHMENT25 COLOR_ATTACHMENT25}</td><td>{@link #GL_COLOR_ATTACHMENT26 COLOR_ATTACHMENT26}</td><td>{@link #GL_COLOR_ATTACHMENT27 COLOR_ATTACHMENT27}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT28 COLOR_ATTACHMENT28}</td><td>{@link #GL_COLOR_ATTACHMENT29 COLOR_ATTACHMENT29}</td><td>{@link #GL_COLOR_ATTACHMENT30 COLOR_ATTACHMENT30}</td><td>{@link #GL_COLOR_ATTACHMENT31 COLOR_ATTACHMENT31}</td></tr><tr><td>{@link #GL_DEPTH_ATTACHMENT DEPTH_ATTACHMENT}</td><td>{@link #GL_STENCIL_ATTACHMENT STENCIL_ATTACHMENT}</td><td>{@link #GL_DEPTH_STENCIL_ATTACHMENT DEPTH_STENCIL_ATTACHMENT}</td></tr></table>
     * @param texTarget  the type of texture
     * @param texture    the texture object to attach to the framebuffer attachment point named by {@code attachment}
     * @param level      the mipmap level of {@code texture} to attach
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferTexture2D">Reference Page</a>
     */
    fun framebufferTexture2D(attachment: Attachment, texTarget: TextureTarget, texture: GlTexture, level: Int = 0) =
        GL30C.glFramebufferTexture2D(GL30C.GL_FRAMEBUFFER, attachment.i, texTarget.i, texture.name, level)

    // --- [ glFramebufferTexture3D ] ---

    /**
     * Attaches a layer of a 3D texture object as a logical buffer to the currently bound framebuffer object.
     *
     * @param target     the framebuffer target. One of:<br><table><tr><td>{@link #GL_FRAMEBUFFER FRAMEBUFFER}</td><td>{@link #GL_READ_FRAMEBUFFER READ_FRAMEBUFFER}</td><td>{@link #GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER}</td></tr></table>
     * @param attachment the attachment point of the framebuffer. One of:<br><table><tr><td>{@link #GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td><td>{@link #GL_COLOR_ATTACHMENT1 COLOR_ATTACHMENT1}</td><td>{@link #GL_COLOR_ATTACHMENT2 COLOR_ATTACHMENT2}</td><td>{@link #GL_COLOR_ATTACHMENT3 COLOR_ATTACHMENT3}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT4 COLOR_ATTACHMENT4}</td><td>{@link #GL_COLOR_ATTACHMENT5 COLOR_ATTACHMENT5}</td><td>{@link #GL_COLOR_ATTACHMENT6 COLOR_ATTACHMENT6}</td><td>{@link #GL_COLOR_ATTACHMENT7 COLOR_ATTACHMENT7}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT8 COLOR_ATTACHMENT8}</td><td>{@link #GL_COLOR_ATTACHMENT9 COLOR_ATTACHMENT9}</td><td>{@link #GL_COLOR_ATTACHMENT10 COLOR_ATTACHMENT10}</td><td>{@link #GL_COLOR_ATTACHMENT11 COLOR_ATTACHMENT11}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT12 COLOR_ATTACHMENT12}</td><td>{@link #GL_COLOR_ATTACHMENT13 COLOR_ATTACHMENT13}</td><td>{@link #GL_COLOR_ATTACHMENT14 COLOR_ATTACHMENT14}</td><td>{@link #GL_COLOR_ATTACHMENT15 COLOR_ATTACHMENT15}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT16 COLOR_ATTACHMENT16}</td><td>{@link #GL_COLOR_ATTACHMENT17 COLOR_ATTACHMENT17}</td><td>{@link #GL_COLOR_ATTACHMENT18 COLOR_ATTACHMENT18}</td><td>{@link #GL_COLOR_ATTACHMENT19 COLOR_ATTACHMENT19}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT20 COLOR_ATTACHMENT20}</td><td>{@link #GL_COLOR_ATTACHMENT21 COLOR_ATTACHMENT21}</td><td>{@link #GL_COLOR_ATTACHMENT22 COLOR_ATTACHMENT22}</td><td>{@link #GL_COLOR_ATTACHMENT23 COLOR_ATTACHMENT23}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT24 COLOR_ATTACHMENT24}</td><td>{@link #GL_COLOR_ATTACHMENT25 COLOR_ATTACHMENT25}</td><td>{@link #GL_COLOR_ATTACHMENT26 COLOR_ATTACHMENT26}</td><td>{@link #GL_COLOR_ATTACHMENT27 COLOR_ATTACHMENT27}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT28 COLOR_ATTACHMENT28}</td><td>{@link #GL_COLOR_ATTACHMENT29 COLOR_ATTACHMENT29}</td><td>{@link #GL_COLOR_ATTACHMENT30 COLOR_ATTACHMENT30}</td><td>{@link #GL_COLOR_ATTACHMENT31 COLOR_ATTACHMENT31}</td></tr><tr><td>{@link #GL_DEPTH_ATTACHMENT DEPTH_ATTACHMENT}</td><td>{@link #GL_STENCIL_ATTACHMENT STENCIL_ATTACHMENT}</td><td>{@link #GL_DEPTH_STENCIL_ATTACHMENT DEPTH_STENCIL_ATTACHMENT}</td></tr></table>
     * @param texTarget  the type of texture
     * @param texture    the texture object to attach to the framebuffer attachment point named by {@code attachment}
     * @param level      the mipmap level of {@code texture} to attach
     * @param layer      the layer of a 2-dimensional image within the 3-dimensional texture.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferTexture3D">Reference Page</a>
     */
    fun framebufferTexture3D(target: FramebufferTarget, attachment: Attachment, texTarget: TextureTarget, texture: GlTexture, level: Int, layer: Int) =
        GL30C.glFramebufferTexture3D(target.i, attachment.i, texTarget.i, texture.name, level, layer)

    /**
     * Attaches a layer of a 3D texture object as a logical buffer to the currently bound framebuffer object.
     *
     * @param attachment the attachment point of the framebuffer. One of:<br><table><tr><td>{@link #GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td><td>{@link #GL_COLOR_ATTACHMENT1 COLOR_ATTACHMENT1}</td><td>{@link #GL_COLOR_ATTACHMENT2 COLOR_ATTACHMENT2}</td><td>{@link #GL_COLOR_ATTACHMENT3 COLOR_ATTACHMENT3}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT4 COLOR_ATTACHMENT4}</td><td>{@link #GL_COLOR_ATTACHMENT5 COLOR_ATTACHMENT5}</td><td>{@link #GL_COLOR_ATTACHMENT6 COLOR_ATTACHMENT6}</td><td>{@link #GL_COLOR_ATTACHMENT7 COLOR_ATTACHMENT7}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT8 COLOR_ATTACHMENT8}</td><td>{@link #GL_COLOR_ATTACHMENT9 COLOR_ATTACHMENT9}</td><td>{@link #GL_COLOR_ATTACHMENT10 COLOR_ATTACHMENT10}</td><td>{@link #GL_COLOR_ATTACHMENT11 COLOR_ATTACHMENT11}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT12 COLOR_ATTACHMENT12}</td><td>{@link #GL_COLOR_ATTACHMENT13 COLOR_ATTACHMENT13}</td><td>{@link #GL_COLOR_ATTACHMENT14 COLOR_ATTACHMENT14}</td><td>{@link #GL_COLOR_ATTACHMENT15 COLOR_ATTACHMENT15}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT16 COLOR_ATTACHMENT16}</td><td>{@link #GL_COLOR_ATTACHMENT17 COLOR_ATTACHMENT17}</td><td>{@link #GL_COLOR_ATTACHMENT18 COLOR_ATTACHMENT18}</td><td>{@link #GL_COLOR_ATTACHMENT19 COLOR_ATTACHMENT19}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT20 COLOR_ATTACHMENT20}</td><td>{@link #GL_COLOR_ATTACHMENT21 COLOR_ATTACHMENT21}</td><td>{@link #GL_COLOR_ATTACHMENT22 COLOR_ATTACHMENT22}</td><td>{@link #GL_COLOR_ATTACHMENT23 COLOR_ATTACHMENT23}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT24 COLOR_ATTACHMENT24}</td><td>{@link #GL_COLOR_ATTACHMENT25 COLOR_ATTACHMENT25}</td><td>{@link #GL_COLOR_ATTACHMENT26 COLOR_ATTACHMENT26}</td><td>{@link #GL_COLOR_ATTACHMENT27 COLOR_ATTACHMENT27}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT28 COLOR_ATTACHMENT28}</td><td>{@link #GL_COLOR_ATTACHMENT29 COLOR_ATTACHMENT29}</td><td>{@link #GL_COLOR_ATTACHMENT30 COLOR_ATTACHMENT30}</td><td>{@link #GL_COLOR_ATTACHMENT31 COLOR_ATTACHMENT31}</td></tr><tr><td>{@link #GL_DEPTH_ATTACHMENT DEPTH_ATTACHMENT}</td><td>{@link #GL_STENCIL_ATTACHMENT STENCIL_ATTACHMENT}</td><td>{@link #GL_DEPTH_STENCIL_ATTACHMENT DEPTH_STENCIL_ATTACHMENT}</td></tr></table>
     * @param texTarget  the type of texture
     * @param texture    the texture object to attach to the framebuffer attachment point named by {@code attachment}
     * @param level      the mipmap level of {@code texture} to attach
     * @param layer      the layer of a 2-dimensional image within the 3-dimensional texture.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferTexture3D">Reference Page</a>
     */
    fun framebufferTexture3D(attachment: Attachment, texTarget: TextureTarget, texture: GlTexture, level: Int, layer: Int) =
        GL30C.glFramebufferTexture3D(GL30C.GL_FRAMEBUFFER, attachment.i, texTarget.i, texture.name, level, layer)

    // --- [ glFramebufferTextureLayer ] ---

    /**
     * Attaches a single layer of a texture to a framebuffer
     *
     * @param target     the framebuffer target. One of:<br><table><tr><td>{@link #GL_FRAMEBUFFER FRAMEBUFFER}</td><td>{@link #GL_READ_FRAMEBUFFER READ_FRAMEBUFFER}</td><td>{@link #GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER}</td></tr></table>
     * @param attachment the attachment point of the framebuffer. One of:<br><table><tr><td>{@link #GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td><td>{@link #GL_COLOR_ATTACHMENT1 COLOR_ATTACHMENT1}</td><td>{@link #GL_COLOR_ATTACHMENT2 COLOR_ATTACHMENT2}</td><td>{@link #GL_COLOR_ATTACHMENT3 COLOR_ATTACHMENT3}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT4 COLOR_ATTACHMENT4}</td><td>{@link #GL_COLOR_ATTACHMENT5 COLOR_ATTACHMENT5}</td><td>{@link #GL_COLOR_ATTACHMENT6 COLOR_ATTACHMENT6}</td><td>{@link #GL_COLOR_ATTACHMENT7 COLOR_ATTACHMENT7}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT8 COLOR_ATTACHMENT8}</td><td>{@link #GL_COLOR_ATTACHMENT9 COLOR_ATTACHMENT9}</td><td>{@link #GL_COLOR_ATTACHMENT10 COLOR_ATTACHMENT10}</td><td>{@link #GL_COLOR_ATTACHMENT11 COLOR_ATTACHMENT11}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT12 COLOR_ATTACHMENT12}</td><td>{@link #GL_COLOR_ATTACHMENT13 COLOR_ATTACHMENT13}</td><td>{@link #GL_COLOR_ATTACHMENT14 COLOR_ATTACHMENT14}</td><td>{@link #GL_COLOR_ATTACHMENT15 COLOR_ATTACHMENT15}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT16 COLOR_ATTACHMENT16}</td><td>{@link #GL_COLOR_ATTACHMENT17 COLOR_ATTACHMENT17}</td><td>{@link #GL_COLOR_ATTACHMENT18 COLOR_ATTACHMENT18}</td><td>{@link #GL_COLOR_ATTACHMENT19 COLOR_ATTACHMENT19}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT20 COLOR_ATTACHMENT20}</td><td>{@link #GL_COLOR_ATTACHMENT21 COLOR_ATTACHMENT21}</td><td>{@link #GL_COLOR_ATTACHMENT22 COLOR_ATTACHMENT22}</td><td>{@link #GL_COLOR_ATTACHMENT23 COLOR_ATTACHMENT23}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT24 COLOR_ATTACHMENT24}</td><td>{@link #GL_COLOR_ATTACHMENT25 COLOR_ATTACHMENT25}</td><td>{@link #GL_COLOR_ATTACHMENT26 COLOR_ATTACHMENT26}</td><td>{@link #GL_COLOR_ATTACHMENT27 COLOR_ATTACHMENT27}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT28 COLOR_ATTACHMENT28}</td><td>{@link #GL_COLOR_ATTACHMENT29 COLOR_ATTACHMENT29}</td><td>{@link #GL_COLOR_ATTACHMENT30 COLOR_ATTACHMENT30}</td><td>{@link #GL_COLOR_ATTACHMENT31 COLOR_ATTACHMENT31}</td></tr><tr><td>{@link #GL_DEPTH_ATTACHMENT DEPTH_ATTACHMENT}</td><td>{@link #GL_STENCIL_ATTACHMENT STENCIL_ATTACHMENT}</td><td>{@link #GL_DEPTH_STENCIL_ATTACHMENT DEPTH_STENCIL_ATTACHMENT}</td></tr></table>
     * @param texture    the texture object to attach to the framebuffer attachment point named by {@code attachment}
     * @param level      the mipmap level of {@code texture} to attach
     * @param layer      the layer of {@code texture} to attach.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferTextureLayer">Reference Page</a>
     */
    fun framebufferTextureLayer(target: FramebufferTarget, attachment: Attachment, texture: GlTexture, level: Int, layer: Int) =
        GL30C.glFramebufferTextureLayer(target.i, attachment.i, texture.name, level, layer)

    /**
     * Attaches a single layer of a texture to a framebuffer
     *
     * @param attachment the attachment point of the framebuffer. One of:<br><table><tr><td>{@link #GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td><td>{@link #GL_COLOR_ATTACHMENT1 COLOR_ATTACHMENT1}</td><td>{@link #GL_COLOR_ATTACHMENT2 COLOR_ATTACHMENT2}</td><td>{@link #GL_COLOR_ATTACHMENT3 COLOR_ATTACHMENT3}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT4 COLOR_ATTACHMENT4}</td><td>{@link #GL_COLOR_ATTACHMENT5 COLOR_ATTACHMENT5}</td><td>{@link #GL_COLOR_ATTACHMENT6 COLOR_ATTACHMENT6}</td><td>{@link #GL_COLOR_ATTACHMENT7 COLOR_ATTACHMENT7}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT8 COLOR_ATTACHMENT8}</td><td>{@link #GL_COLOR_ATTACHMENT9 COLOR_ATTACHMENT9}</td><td>{@link #GL_COLOR_ATTACHMENT10 COLOR_ATTACHMENT10}</td><td>{@link #GL_COLOR_ATTACHMENT11 COLOR_ATTACHMENT11}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT12 COLOR_ATTACHMENT12}</td><td>{@link #GL_COLOR_ATTACHMENT13 COLOR_ATTACHMENT13}</td><td>{@link #GL_COLOR_ATTACHMENT14 COLOR_ATTACHMENT14}</td><td>{@link #GL_COLOR_ATTACHMENT15 COLOR_ATTACHMENT15}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT16 COLOR_ATTACHMENT16}</td><td>{@link #GL_COLOR_ATTACHMENT17 COLOR_ATTACHMENT17}</td><td>{@link #GL_COLOR_ATTACHMENT18 COLOR_ATTACHMENT18}</td><td>{@link #GL_COLOR_ATTACHMENT19 COLOR_ATTACHMENT19}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT20 COLOR_ATTACHMENT20}</td><td>{@link #GL_COLOR_ATTACHMENT21 COLOR_ATTACHMENT21}</td><td>{@link #GL_COLOR_ATTACHMENT22 COLOR_ATTACHMENT22}</td><td>{@link #GL_COLOR_ATTACHMENT23 COLOR_ATTACHMENT23}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT24 COLOR_ATTACHMENT24}</td><td>{@link #GL_COLOR_ATTACHMENT25 COLOR_ATTACHMENT25}</td><td>{@link #GL_COLOR_ATTACHMENT26 COLOR_ATTACHMENT26}</td><td>{@link #GL_COLOR_ATTACHMENT27 COLOR_ATTACHMENT27}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT28 COLOR_ATTACHMENT28}</td><td>{@link #GL_COLOR_ATTACHMENT29 COLOR_ATTACHMENT29}</td><td>{@link #GL_COLOR_ATTACHMENT30 COLOR_ATTACHMENT30}</td><td>{@link #GL_COLOR_ATTACHMENT31 COLOR_ATTACHMENT31}</td></tr><tr><td>{@link #GL_DEPTH_ATTACHMENT DEPTH_ATTACHMENT}</td><td>{@link #GL_STENCIL_ATTACHMENT STENCIL_ATTACHMENT}</td><td>{@link #GL_DEPTH_STENCIL_ATTACHMENT DEPTH_STENCIL_ATTACHMENT}</td></tr></table>
     * @param texture    the texture object to attach to the framebuffer attachment point named by {@code attachment}
     * @param level      the mipmap level of {@code texture} to attach
     * @param layer      the layer of {@code texture} to attach.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferTextureLayer">Reference Page</a>
     */
    fun framebufferTextureLayer(attachment: Attachment, texture: GlTexture, level: Int, layer: Int) =
        GL30C.glFramebufferTextureLayer(GL30C.GL_FRAMEBUFFER, attachment.i, texture.name, level, layer)

    // --- [ glFramebufferRenderbuffer ] ---

    /**
     * Attaches a renderbuffer as a logical buffer to the currently bound framebuffer object.
     *
     * @param target             the framebuffer target. One of:<br><table><tr><td>{@link #GL_FRAMEBUFFER FRAMEBUFFER}</td><td>{@link #GL_READ_FRAMEBUFFER READ_FRAMEBUFFER}</td><td>{@link #GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER}</td></tr></table>
     * @param attachment         the attachment point of the framebuffer. One of:<br><table><tr><td>{@link #GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td><td>{@link #GL_COLOR_ATTACHMENT1 COLOR_ATTACHMENT1}</td><td>{@link #GL_COLOR_ATTACHMENT2 COLOR_ATTACHMENT2}</td><td>{@link #GL_COLOR_ATTACHMENT3 COLOR_ATTACHMENT3}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT4 COLOR_ATTACHMENT4}</td><td>{@link #GL_COLOR_ATTACHMENT5 COLOR_ATTACHMENT5}</td><td>{@link #GL_COLOR_ATTACHMENT6 COLOR_ATTACHMENT6}</td><td>{@link #GL_COLOR_ATTACHMENT7 COLOR_ATTACHMENT7}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT8 COLOR_ATTACHMENT8}</td><td>{@link #GL_COLOR_ATTACHMENT9 COLOR_ATTACHMENT9}</td><td>{@link #GL_COLOR_ATTACHMENT10 COLOR_ATTACHMENT10}</td><td>{@link #GL_COLOR_ATTACHMENT11 COLOR_ATTACHMENT11}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT12 COLOR_ATTACHMENT12}</td><td>{@link #GL_COLOR_ATTACHMENT13 COLOR_ATTACHMENT13}</td><td>{@link #GL_COLOR_ATTACHMENT14 COLOR_ATTACHMENT14}</td><td>{@link #GL_COLOR_ATTACHMENT15 COLOR_ATTACHMENT15}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT16 COLOR_ATTACHMENT16}</td><td>{@link #GL_COLOR_ATTACHMENT17 COLOR_ATTACHMENT17}</td><td>{@link #GL_COLOR_ATTACHMENT18 COLOR_ATTACHMENT18}</td><td>{@link #GL_COLOR_ATTACHMENT19 COLOR_ATTACHMENT19}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT20 COLOR_ATTACHMENT20}</td><td>{@link #GL_COLOR_ATTACHMENT21 COLOR_ATTACHMENT21}</td><td>{@link #GL_COLOR_ATTACHMENT22 COLOR_ATTACHMENT22}</td><td>{@link #GL_COLOR_ATTACHMENT23 COLOR_ATTACHMENT23}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT24 COLOR_ATTACHMENT24}</td><td>{@link #GL_COLOR_ATTACHMENT25 COLOR_ATTACHMENT25}</td><td>{@link #GL_COLOR_ATTACHMENT26 COLOR_ATTACHMENT26}</td><td>{@link #GL_COLOR_ATTACHMENT27 COLOR_ATTACHMENT27}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT28 COLOR_ATTACHMENT28}</td><td>{@link #GL_COLOR_ATTACHMENT29 COLOR_ATTACHMENT29}</td><td>{@link #GL_COLOR_ATTACHMENT30 COLOR_ATTACHMENT30}</td><td>{@link #GL_COLOR_ATTACHMENT31 COLOR_ATTACHMENT31}</td></tr><tr><td>{@link #GL_DEPTH_ATTACHMENT DEPTH_ATTACHMENT}</td><td>{@link #GL_STENCIL_ATTACHMENT STENCIL_ATTACHMENT}</td><td>{@link #GL_DEPTH_STENCIL_ATTACHMENT DEPTH_STENCIL_ATTACHMENT}</td></tr></table>
     * @param renderbuffertarget the renderbuffer target. Must be:<br><table><tr><td>{@link #GL_RENDERBUFFER RENDERBUFFER}</td></tr></table>
     * @param renderbuffer       the name of an existing renderbuffer object of type {@code renderbuffertarget} to attach
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferRenderbuffer">Reference Page</a>
     */
    fun framebufferRenderbuffer(target: FramebufferTarget, attachment: Attachment, renderbuffer: GlRenderbuffer) =
        GL30C.glFramebufferRenderbuffer(target.i, attachment.i, GL30C.GL_RENDERBUFFER, renderbuffer.name)

    /**
     * Attaches a renderbuffer as a logical buffer to the currently bound framebuffer object.
     *
     * @param attachment         the attachment point of the framebuffer. One of:<br><table><tr><td>{@link #GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td><td>{@link #GL_COLOR_ATTACHMENT1 COLOR_ATTACHMENT1}</td><td>{@link #GL_COLOR_ATTACHMENT2 COLOR_ATTACHMENT2}</td><td>{@link #GL_COLOR_ATTACHMENT3 COLOR_ATTACHMENT3}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT4 COLOR_ATTACHMENT4}</td><td>{@link #GL_COLOR_ATTACHMENT5 COLOR_ATTACHMENT5}</td><td>{@link #GL_COLOR_ATTACHMENT6 COLOR_ATTACHMENT6}</td><td>{@link #GL_COLOR_ATTACHMENT7 COLOR_ATTACHMENT7}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT8 COLOR_ATTACHMENT8}</td><td>{@link #GL_COLOR_ATTACHMENT9 COLOR_ATTACHMENT9}</td><td>{@link #GL_COLOR_ATTACHMENT10 COLOR_ATTACHMENT10}</td><td>{@link #GL_COLOR_ATTACHMENT11 COLOR_ATTACHMENT11}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT12 COLOR_ATTACHMENT12}</td><td>{@link #GL_COLOR_ATTACHMENT13 COLOR_ATTACHMENT13}</td><td>{@link #GL_COLOR_ATTACHMENT14 COLOR_ATTACHMENT14}</td><td>{@link #GL_COLOR_ATTACHMENT15 COLOR_ATTACHMENT15}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT16 COLOR_ATTACHMENT16}</td><td>{@link #GL_COLOR_ATTACHMENT17 COLOR_ATTACHMENT17}</td><td>{@link #GL_COLOR_ATTACHMENT18 COLOR_ATTACHMENT18}</td><td>{@link #GL_COLOR_ATTACHMENT19 COLOR_ATTACHMENT19}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT20 COLOR_ATTACHMENT20}</td><td>{@link #GL_COLOR_ATTACHMENT21 COLOR_ATTACHMENT21}</td><td>{@link #GL_COLOR_ATTACHMENT22 COLOR_ATTACHMENT22}</td><td>{@link #GL_COLOR_ATTACHMENT23 COLOR_ATTACHMENT23}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT24 COLOR_ATTACHMENT24}</td><td>{@link #GL_COLOR_ATTACHMENT25 COLOR_ATTACHMENT25}</td><td>{@link #GL_COLOR_ATTACHMENT26 COLOR_ATTACHMENT26}</td><td>{@link #GL_COLOR_ATTACHMENT27 COLOR_ATTACHMENT27}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT28 COLOR_ATTACHMENT28}</td><td>{@link #GL_COLOR_ATTACHMENT29 COLOR_ATTACHMENT29}</td><td>{@link #GL_COLOR_ATTACHMENT30 COLOR_ATTACHMENT30}</td><td>{@link #GL_COLOR_ATTACHMENT31 COLOR_ATTACHMENT31}</td></tr><tr><td>{@link #GL_DEPTH_ATTACHMENT DEPTH_ATTACHMENT}</td><td>{@link #GL_STENCIL_ATTACHMENT STENCIL_ATTACHMENT}</td><td>{@link #GL_DEPTH_STENCIL_ATTACHMENT DEPTH_STENCIL_ATTACHMENT}</td></tr></table>
     * @param renderbuffertarget the renderbuffer target. Must be:<br><table><tr><td>{@link #GL_RENDERBUFFER RENDERBUFFER}</td></tr></table>
     * @param renderbuffer       the name of an existing renderbuffer object of type {@code renderbuffertarget} to attach
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferRenderbuffer">Reference Page</a>
     */
    fun framebufferRenderbuffer(attachment: Attachment, renderbuffer: GlRenderbuffer) =
        GL30C.glFramebufferRenderbuffer(GL30C.GL_FRAMEBUFFER, attachment.i, GL30C.GL_RENDERBUFFER, renderbuffer.name)

    // --- [ glGetFramebufferAttachmentParameteriv ] ---

    /**
     * Retrievees information about attachments of a bound framebuffer object.
     *
     * @param target     the target of the query operation. One of:<br><table><tr><td>{@link #GL_FRAMEBUFFER FRAMEBUFFER}</td><td>{@link #GL_READ_FRAMEBUFFER READ_FRAMEBUFFER}</td><td>{@link #GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER}</td></tr></table>
     * @param attachment the attachment within {@code target}. One of:<br><table><tr><td>{@link #GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td><td>{@link #GL_COLOR_ATTACHMENT1 COLOR_ATTACHMENT1}</td><td>{@link #GL_COLOR_ATTACHMENT2 COLOR_ATTACHMENT2}</td><td>{@link #GL_COLOR_ATTACHMENT3 COLOR_ATTACHMENT3}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT4 COLOR_ATTACHMENT4}</td><td>{@link #GL_COLOR_ATTACHMENT5 COLOR_ATTACHMENT5}</td><td>{@link #GL_COLOR_ATTACHMENT6 COLOR_ATTACHMENT6}</td><td>{@link #GL_COLOR_ATTACHMENT7 COLOR_ATTACHMENT7}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT8 COLOR_ATTACHMENT8}</td><td>{@link #GL_COLOR_ATTACHMENT9 COLOR_ATTACHMENT9}</td><td>{@link #GL_COLOR_ATTACHMENT10 COLOR_ATTACHMENT10}</td><td>{@link #GL_COLOR_ATTACHMENT11 COLOR_ATTACHMENT11}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT12 COLOR_ATTACHMENT12}</td><td>{@link #GL_COLOR_ATTACHMENT13 COLOR_ATTACHMENT13}</td><td>{@link #GL_COLOR_ATTACHMENT14 COLOR_ATTACHMENT14}</td><td>{@link #GL_COLOR_ATTACHMENT15 COLOR_ATTACHMENT15}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT16 COLOR_ATTACHMENT16}</td><td>{@link #GL_COLOR_ATTACHMENT17 COLOR_ATTACHMENT17}</td><td>{@link #GL_COLOR_ATTACHMENT18 COLOR_ATTACHMENT18}</td><td>{@link #GL_COLOR_ATTACHMENT19 COLOR_ATTACHMENT19}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT20 COLOR_ATTACHMENT20}</td><td>{@link #GL_COLOR_ATTACHMENT21 COLOR_ATTACHMENT21}</td><td>{@link #GL_COLOR_ATTACHMENT22 COLOR_ATTACHMENT22}</td><td>{@link #GL_COLOR_ATTACHMENT23 COLOR_ATTACHMENT23}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT24 COLOR_ATTACHMENT24}</td><td>{@link #GL_COLOR_ATTACHMENT25 COLOR_ATTACHMENT25}</td><td>{@link #GL_COLOR_ATTACHMENT26 COLOR_ATTACHMENT26}</td><td>{@link #GL_COLOR_ATTACHMENT27 COLOR_ATTACHMENT27}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT28 COLOR_ATTACHMENT28}</td><td>{@link #GL_COLOR_ATTACHMENT29 COLOR_ATTACHMENT29}</td><td>{@link #GL_COLOR_ATTACHMENT30 COLOR_ATTACHMENT30}</td><td>{@link #GL_COLOR_ATTACHMENT31 COLOR_ATTACHMENT31}</td></tr><tr><td>{@link #GL_DEPTH_ATTACHMENT DEPTH_ATTACHMENT}</td><td>{@link #GL_STENCIL_ATTACHMENT STENCIL_ATTACHMENT}</td><td>{@link #GL_DEPTH_STENCIL_ATTACHMENT DEPTH_STENCIL_ATTACHMENT}</td></tr></table>
     * @param name      the parameter of {@code attachment} to query. One of:<br><table><tr><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE}</td><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME FRAMEBUFFER_ATTACHMENT_OBJECT_NAME}</td></tr><tr><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL}</td><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE}</td></tr><tr><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER}</td><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING}</td></tr><tr><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_COMPONENT_TYPE FRAMEBUFFER_ATTACHMENT_COMPONENT_TYPE}</td><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_RED_SIZE FRAMEBUFFER_ATTACHMENT_RED_SIZE}</td></tr><tr><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_GREEN_SIZE FRAMEBUFFER_ATTACHMENT_GREEN_SIZE}</td><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_BLUE_SIZE FRAMEBUFFER_ATTACHMENT_BLUE_SIZE}</td></tr><tr><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_ALPHA_SIZE FRAMEBUFFER_ATTACHMENT_ALPHA_SIZE}</td><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_DEPTH_SIZE FRAMEBUFFER_ATTACHMENT_DEPTH_SIZE}</td></tr><tr><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_STENCIL_SIZE FRAMEBUFFER_ATTACHMENT_STENCIL_SIZE}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetFramebufferAttachmentParameter">Reference Page</a>
     */
    fun getFramebufferAttachmentParameter(target: FramebufferTarget, attachment: Attachment, name: GetFramebufferAttachment): Int =
        GL30C.glGetFramebufferAttachmentParameteri(target.i, attachment.i, name.i)

    /**
     * Retrievees information about attachments of a bound framebuffer object.
     *
     * @param attachment the attachment within {@code target}. One of:<br><table><tr><td>{@link #GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td><td>{@link #GL_COLOR_ATTACHMENT1 COLOR_ATTACHMENT1}</td><td>{@link #GL_COLOR_ATTACHMENT2 COLOR_ATTACHMENT2}</td><td>{@link #GL_COLOR_ATTACHMENT3 COLOR_ATTACHMENT3}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT4 COLOR_ATTACHMENT4}</td><td>{@link #GL_COLOR_ATTACHMENT5 COLOR_ATTACHMENT5}</td><td>{@link #GL_COLOR_ATTACHMENT6 COLOR_ATTACHMENT6}</td><td>{@link #GL_COLOR_ATTACHMENT7 COLOR_ATTACHMENT7}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT8 COLOR_ATTACHMENT8}</td><td>{@link #GL_COLOR_ATTACHMENT9 COLOR_ATTACHMENT9}</td><td>{@link #GL_COLOR_ATTACHMENT10 COLOR_ATTACHMENT10}</td><td>{@link #GL_COLOR_ATTACHMENT11 COLOR_ATTACHMENT11}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT12 COLOR_ATTACHMENT12}</td><td>{@link #GL_COLOR_ATTACHMENT13 COLOR_ATTACHMENT13}</td><td>{@link #GL_COLOR_ATTACHMENT14 COLOR_ATTACHMENT14}</td><td>{@link #GL_COLOR_ATTACHMENT15 COLOR_ATTACHMENT15}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT16 COLOR_ATTACHMENT16}</td><td>{@link #GL_COLOR_ATTACHMENT17 COLOR_ATTACHMENT17}</td><td>{@link #GL_COLOR_ATTACHMENT18 COLOR_ATTACHMENT18}</td><td>{@link #GL_COLOR_ATTACHMENT19 COLOR_ATTACHMENT19}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT20 COLOR_ATTACHMENT20}</td><td>{@link #GL_COLOR_ATTACHMENT21 COLOR_ATTACHMENT21}</td><td>{@link #GL_COLOR_ATTACHMENT22 COLOR_ATTACHMENT22}</td><td>{@link #GL_COLOR_ATTACHMENT23 COLOR_ATTACHMENT23}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT24 COLOR_ATTACHMENT24}</td><td>{@link #GL_COLOR_ATTACHMENT25 COLOR_ATTACHMENT25}</td><td>{@link #GL_COLOR_ATTACHMENT26 COLOR_ATTACHMENT26}</td><td>{@link #GL_COLOR_ATTACHMENT27 COLOR_ATTACHMENT27}</td></tr><tr><td>{@link #GL_COLOR_ATTACHMENT28 COLOR_ATTACHMENT28}</td><td>{@link #GL_COLOR_ATTACHMENT29 COLOR_ATTACHMENT29}</td><td>{@link #GL_COLOR_ATTACHMENT30 COLOR_ATTACHMENT30}</td><td>{@link #GL_COLOR_ATTACHMENT31 COLOR_ATTACHMENT31}</td></tr><tr><td>{@link #GL_DEPTH_ATTACHMENT DEPTH_ATTACHMENT}</td><td>{@link #GL_STENCIL_ATTACHMENT STENCIL_ATTACHMENT}</td><td>{@link #GL_DEPTH_STENCIL_ATTACHMENT DEPTH_STENCIL_ATTACHMENT}</td></tr></table>
     * @param name      the parameter of {@code attachment} to query. One of:<br><table><tr><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE}</td><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME FRAMEBUFFER_ATTACHMENT_OBJECT_NAME}</td></tr><tr><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL}</td><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE}</td></tr><tr><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER}</td><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING}</td></tr><tr><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_COMPONENT_TYPE FRAMEBUFFER_ATTACHMENT_COMPONENT_TYPE}</td><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_RED_SIZE FRAMEBUFFER_ATTACHMENT_RED_SIZE}</td></tr><tr><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_GREEN_SIZE FRAMEBUFFER_ATTACHMENT_GREEN_SIZE}</td><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_BLUE_SIZE FRAMEBUFFER_ATTACHMENT_BLUE_SIZE}</td></tr><tr><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_ALPHA_SIZE FRAMEBUFFER_ATTACHMENT_ALPHA_SIZE}</td><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_DEPTH_SIZE FRAMEBUFFER_ATTACHMENT_DEPTH_SIZE}</td></tr><tr><td>{@link #GL_FRAMEBUFFER_ATTACHMENT_STENCIL_SIZE FRAMEBUFFER_ATTACHMENT_STENCIL_SIZE}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetFramebufferAttachmentParameter">Reference Page</a>
     */
    fun getFramebufferAttachmentParameter(attachment: Attachment, name: GetFramebufferAttachment): Int =
        GL30C.glGetFramebufferAttachmentParameteri(GL30C.GL_FRAMEBUFFER, attachment.i, name.i)

    // --- [ glBlitFramebuffer ] ---

    /**
     * Copies a block of pixels from the read framebuffer to the draw framebuffer.
     *
     * @param srcX0  the lower-left coordinate of the source rectangle within the read buffer
     * @param srcY0  the upper-left coordinate of the source rectangle within the read buffer
     * @param srcX1  the lower-right coordinate of the source rectangle within the read buffer
     * @param srcY1  the upper-right coordinate of the source rectangle within the read buffer
     * @param dstX0  the lower-left coordinate of the destination rectangle within the write buffer
     * @param dstY0  the upper-left coordinate of the destination rectangle within the write buffer
     * @param dstX1  the lower-right coordinate of the destination rectangle within the write buffer
     * @param dstY1  the upper-right coordinate of the destination rectangle within the write buffer
     * @param mask   the bitwise OR of the flags indicating which buffers are to be copied. One of:<br><table><tr><td>{@link GL11#GL_COLOR_BUFFER_BIT COLOR_BUFFER_BIT}</td><td>{@link GL11#GL_DEPTH_BUFFER_BIT DEPTH_BUFFER_BIT}</td><td>{@link GL11#GL_STENCIL_BUFFER_BIT STENCIL_BUFFER_BIT}</td></tr></table>
     * @param filter the interpolation to be applied if the image is stretched. One of:<br><table><tr><td>{@link GL11#GL_NEAREST NEAREST}</td><td>{@link GL11#GL_LINEAR LINEAR}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBlitFramebuffer">Reference Page</a>
     */
    fun blitFramebuffer(srcX0: Int, srcY0: Int, srcX1: Int, srcY1: Int, dstX0: Int, dstY0: Int, dstX1: Int, dstY1: Int, mask: AttribMask, filter: MagFilter) =
        GL30C.glBlitFramebuffer(srcX0, srcY0, srcX1, srcY1, dstX0, dstY0, dstX1, dstY1, mask.i, filter.i)

    /**
     * Copies a block of pixels from the read framebuffer to the draw framebuffer.
     *
     * @param src    the size of the source rectangle within the read buffer
     * @param dst    the size of the destination rectangle within the write buffer
     * @param mask   the bitwise OR of the flags indicating which buffers are to be copied. One of:<br><table><tr><td>{@link GL11#GL_COLOR_BUFFER_BIT COLOR_BUFFER_BIT}</td><td>{@link GL11#GL_DEPTH_BUFFER_BIT DEPTH_BUFFER_BIT}</td><td>{@link GL11#GL_STENCIL_BUFFER_BIT STENCIL_BUFFER_BIT}</td></tr></table>
     * @param filter the interpolation to be applied if the image is stretched. One of:<br><table><tr><td>{@link GL11#GL_NEAREST NEAREST}</td><td>{@link GL11#GL_LINEAR LINEAR}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBlitFramebuffer">Reference Page</a>
     */
    fun blitFramebuffer(src: Vec2i, dst: Vec2i, mask: AttribMask, filter: MagFilter) =
        GL30C.glBlitFramebuffer(0, 0, src.x, src.y, 0, 0, dst.x, dst.y, mask.i, filter.i)

    /**
     * Copies a block of pixels from the read framebuffer to the draw framebuffer.
     *
     * @param size   the size of the rectangle
     * @param mask   the bitwise OR of the flags indicating which buffers are to be copied. One of:<br><table><tr><td>{@link GL11#GL_COLOR_BUFFER_BIT COLOR_BUFFER_BIT}</td><td>{@link GL11#GL_DEPTH_BUFFER_BIT DEPTH_BUFFER_BIT}</td><td>{@link GL11#GL_STENCIL_BUFFER_BIT STENCIL_BUFFER_BIT}</td></tr></table>
     * @param filter the interpolation to be applied if the image is stretched. One of:<br><table><tr><td>{@link GL11#GL_NEAREST NEAREST}</td><td>{@link GL11#GL_LINEAR LINEAR}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBlitFramebuffer">Reference Page</a>
     */
    fun blitFramebuffer(size: Vec2i, mask: AttribMask, filter: MagFilter) =
        GL30C.glBlitFramebuffer(0, 0, size.x, size.y, 0, 0, size.x, size.y, mask.i, filter.i)

    /**
     * Copies a block of pixels from the read framebuffer to the draw framebuffer.
     *
     * @param size   the size of the rectangle
     * @param mask   the bitwise OR of the flags indicating which buffers are to be copied. One of:<br><table><tr><td>{@link GL11#GL_COLOR_BUFFER_BIT COLOR_BUFFER_BIT}</td><td>{@link GL11#GL_DEPTH_BUFFER_BIT DEPTH_BUFFER_BIT}</td><td>{@link GL11#GL_STENCIL_BUFFER_BIT STENCIL_BUFFER_BIT}</td></tr></table>
     * @param filter the interpolation to be applied if the image is stretched. One of:<br><table><tr><td>{@link GL11#GL_NEAREST NEAREST}</td><td>{@link GL11#GL_LINEAR LINEAR}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBlitFramebuffer">Reference Page</a>
     */
    fun blitFramebuffer(size: Vec2i, filter: MagFilter) =
        GL30C.glBlitFramebuffer(0, 0, size.x, size.y, 0, 0, size.x, size.y, GL30C.GL_COLOR_BUFFER_BIT, filter.i)

    // --- [ glGenerateMipmap ] ---

    /**
     * Generate mipmaps for a specified texture target.     *
     * @param target the target to which the texture whose mimaps to generate is bound. One of:<br><table><tr><td>{@link GL11#GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link GL11#GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link #GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link #GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenerateMipmap">Reference Page</a>
     */
    fun generateMipmap(target: TextureTarget) = GL30C.glGenerateMipmap(target.i)

    // --- [ glTexParameterIiv ] ---

//    /**
//     * Sets the integer value of a texture parameter.
//     *
//     * @param target the texture target
//     * @param name  the symbolic name of a single-valued texture parameter
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTexParameter">Reference Page</a>
//     */
//    fun texParameterI(target: TextureTarget, name: Int, param: Int) =
//            Stack.intAdr(param) { GL30C.nglTexParameterIiv(target.i, name, it) }

    /**
     * Sets the integer value of a texture parameter. Only BORDER_COLOR and SWIZZLE_RGBA
     *
     * @param target the texture target
     * @param name  the symbolic name of a single-valued texture parameter
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTexParameter">Reference Page</a>
     */
    fun texParameterI(target: TextureTarget, name: TexParameter, param: Vec4i) = GL30C.nglTexParameterIiv(target.i, name.i, param.toOffHeap())

    // --- [ glTexParameterIuiv ] ---

    /**
     * Sets the unsigned integer value of a texture parameter.
     *
     * @param target the texture target
     * @param pname  the symbolic name of a single-valued texture parameter
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTexParameter">Reference Page</a>
     */
    fun texParameter(target: TextureTarget, name: TexParameter, param: Vec4ui) = GL30C.nglTexParameterIuiv(target.i, name.i, param.toOffHeap())

//    // --- [ glGetTexParameterIiv ] ---
//
//    /** Unsafe version of: {@link #glGetTexParameterIiv GetTexParameterIiv} */
//    public static native void nglGetTexParameterIiv(int target, int pname, long params);
//
//    /**
//     * Returns the integer value of a texture parameter.
//     *
//     * @param target the texture target
//     * @param pname  the symbolic name of a texture parameter
//     * @param params returns the texture parameter value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexParameter">Reference Page</a>
//     */
//    public static void glGetTexParameterIiv(@NativeType("GLenum") int target, @NativeType("GLenum") int pname, @NativeType("GLint *") IntBuffer params) {
//        if (CHECKS) {
//            check(params, 1);
//        }
//        nglGetTexParameterIiv(target, pname, memAddress(params));
//    }
//
//    /**
//     * Returns the integer value of a texture parameter.
//     *
//     * @param target the texture target
//     * @param pname  the symbolic name of a texture parameter
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexParameter">Reference Page</a>
//     */
//    @NativeType("void")
//    public static int glGetTexParameterIi(@NativeType("GLenum") int target, @NativeType("GLenum") int pname) {
//        MemoryStack stack = stackGet(); int stackPointer = stack.getPointer();
//        try {
//            IntBuffer params = stack.callocInt(1);
//            nglGetTexParameterIiv(target, pname, memAddress(params));
//            return params.get(0);
//        } finally {
//            stack.setPointer(stackPointer);
//        }
//    }
//
//    // --- [ glGetTexParameterIuiv ] ---
//
//    /** Unsafe version of: {@link #glGetTexParameterIuiv GetTexParameterIuiv} */
//    public static native void nglGetTexParameterIuiv(int target, int pname, long params);
//
//    /**
//     * Returns the unsigned integer value of a texture parameter.
//     *
//     * @param target the texture target
//     * @param pname  the symbolic name of a texture parameter
//     * @param params returns the texture parameter value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexParameter">Reference Page</a>
//     */
//    public static void glGetTexParameterIuiv(@NativeType("GLenum") int target, @NativeType("GLenum") int pname, @NativeType("GLuint *") IntBuffer params) {
//        if (CHECKS) {
//            check(params, 1);
//        }
//        nglGetTexParameterIuiv(target, pname, memAddress(params));
//    }
//
//    /**
//     * Returns the unsigned integer value of a texture parameter.
//     *
//     * @param target the texture target
//     * @param pname  the symbolic name of a texture parameter
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTexParameter">Reference Page</a>
//     */
//    @NativeType("void")
//    public static int glGetTexParameterIui(@NativeType("GLenum") int target, @NativeType("GLenum") int pname) {
//        MemoryStack stack = stackGet(); int stackPointer = stack.getPointer();
//        try {
//            IntBuffer params = stack.callocInt(1);
//            nglGetTexParameterIuiv(target, pname, memAddress(params));
//            return params.get(0);
//        } finally {
//            stack.setPointer(stackPointer);
//        }
//    }
//
    // --- [ glColorMaski ] ---

    /**
     * Enables and disables writing of frame buffer color components.
     *
     * @param buf the index of the draw buffer whose color mask to set
     * @param r   whether R values are written or not
     * @param g   whether G values are written or not
     * @param b   whether B values are written or not
     * @param a   whether A values are written or not
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glColorMaski">Reference Page</a>
     */
    fun colorMask(buf: Int, r: Boolean, g: Boolean, b: Boolean, a: Boolean) = GL30C.glColorMaski(buf, r, g, b, a)

    /**
     * Enables and disables writing of frame buffer color components.
     *
     * @param buf   the index of the draw buffer whose color mask to set
     * @param rgba  whether RGBA values are written or not
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glColorMaski">Reference Page</a>
     */
    fun colorMask(buf: Int, rgba: Boolean) = GL30C.glColorMaski(buf, rgba, rgba, rgba, rgba)

    /**
     * Enables and disables writing of frame buffer color components.
     *
     * @param buf   the index of the draw buffer whose color mask to set
     * @param rgba  whether RGBA values are written or not
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glColorMaski">Reference Page</a>
     */
    fun colorMask(buf: Int, rgba: Vec4bool) = GL30C.glColorMaski(buf, rgba.x, rgba.y, rgba.z, rgba.w)

    // --- [ glGetBooleani_v ] ---
    // inline reified with a to-do

    // --- [ glEnablei ] ---

    /**
     * Enables an indexed capability.
     *
     * @param cap   the indexed capability to enable
     * @param index the index to enable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glEnablei">Reference Page</a>
     */
    fun enable(cap: IndexedState, index: Int) = GL30C.glEnablei(cap.i, index)

    // --- [ glDisablei ] ---

    /**
     * Disables an indexed capability.
     *
     * @param cap the indexed capability to disable
     * @param index  the index to disable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDisablei">Reference Page</a>
     */
    fun disable(cap: IndexedState, index: Int) = GL30C.glDisablei(cap.i, index)

    // --- [ glIsEnabledi ] ---

    /**
     * Tests whether an indexed capability is enabled.
     *
     * @param cap the indexed capability to query
     * @param index  the index to query
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glIsEnabledi">Reference Page</a>
     */
    fun isEnabled(cap: IndexedState, index: Int) = GL30C.glIsEnabledi(cap.i, index)

    // --- [ glBindBufferRange ] ---

    /**
     * Binds a range within a buffer object to an indexed buffer target.
     *
     * @param target the target of the bind operation. One of:<br><table><tr><td>{@link #GL_TRANSFORM_FEEDBACK_BUFFER TRANSFORM_FEEDBACK_BUFFER}</td><td>{@link GL31#GL_UNIFORM_BUFFER UNIFORM_BUFFER}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td><td>{@link GL43#GL_SHADER_STORAGE_BUFFER SHADER_STORAGE_BUFFER}</td></tr></table>
     * @param index  the index of the binding point within the array specified by {@code target}
     * @param buffer a buffer object to bind to the specified binding point
     * @param offset the starting offset in basic machine units into the buffer object {@code buffer}
     * @param size   the amount of data in machine units that can be read from the buffer object while used as an indexed target
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBindBufferRange">Reference Page</a>
     */
    fun bindBufferRange(target: BufferTarget2, index: Int, buffer: GlBuffer, offset: Int, size: Int) =
        GL30C.glBindBufferRange(target.i, index, buffer.name, offset.L, size.L)

    // --- [ glBindBufferBase ] ---

    /**
     * Binds a buffer object to an indexed buffer target.
     *
     * @param target the target of the bind operation. One of:<br><table><tr><td>{@link #GL_TRANSFORM_FEEDBACK_BUFFER TRANSFORM_FEEDBACK_BUFFER}</td><td>{@link GL31#GL_UNIFORM_BUFFER UNIFORM_BUFFER}</td><td>{@link GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td><td>{@link GL43#GL_SHADER_STORAGE_BUFFER SHADER_STORAGE_BUFFER}</td></tr></table>
     * @param index  the index of the binding point within the array specified by {@code target}
     * @param buffer a buffer object to bind to the specified binding point
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBindBufferBase">Reference Page</a>
     */
    fun bindBufferBase(target: BufferTarget2, index: Int, buffer: GlBuffer) =
        GL30C.glBindBufferBase(target.i, index, buffer.name)

    // --- [ glBeginTransformFeedback ] ---

    /**
     * Starts transform feedback operation.
     *
     * @param primitiveMode the output type of the primitives that will be recorded into the buffer objects that are bound for transform feedback. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBeginTransformFeedback">Reference Page</a>
     */
    fun beginTransformFeedback(primitiveMode: PrimitiveMode) =
        GL30C.glBeginTransformFeedback(primitiveMode.i)

    // --- [ glEndTransformFeedback ] ---

    /**
     * Ends transform feedback operation.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glEndTransformFeedback">Reference Page</a>
     */
    fun endTransformFeedback() = GL30C.glEndTransformFeedback()

    // --- [ glTransformFeedbackVaryings ] ---

    /**
     * Specifies values to record in transform feedback buffers.
     *
     * @param program    the target program object
     * @param varyings   an array of {@code count} zero-terminated strings specifying the names of the varying variables to use for transform feedback
     * @param bufferMode the mode used to capture the varying variables when transform feedback is active. One of:<br><table><tr><td>{@link #GL_INTERLEAVED_ATTRIBS INTERLEAVED_ATTRIBS}</td><td>{@link #GL_SEPARATE_ATTRIBS SEPARATE_ATTRIBS}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTransformFeedbackVaryings">Reference Page</a>
     */
    fun transformFeedbackVaryings(program: GlProgram, varyings: Array<CharSequence>, bufferMode: TransformBufferMode) =
        GL30C.glTransformFeedbackVaryings(program.name, varyings, bufferMode.i)

    // --- [ glGetTransformFeedbackVarying ] ---

    /**
     * Retrieves information about varying variables selected for transform feedback.
     *
     * @param program the target program object
     * @param index   the index of the varying variable whose information to retrieve
     * @param bufSize the maximum number of characters, including the null terminator, that may be written into {@code name}
     * @param size    a variable that will receive the size of the varying
     * @param type    a variable that will receive the type of the varying
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTransformFeedbackVarying">Reference Page</a>
     */
    fun getTransformFeedbackVarying(program: GlProgram, index: Int): Triple<Int, Int, String> =
        stack {
            val maxLength = program.transformFeedbackVaryingMaxLength
            val pSize = it.nmalloc(Int.BYTES, 8)
            val pType = pSize + Int.BYTES
            val name = it.readAscii(maxLength) { ptr, length ->
                GL30C.nglGetTransformFeedbackVarying(program.name, index, maxLength, length.adr.L, pSize, pType, ptr.adr.L)
            }
            Triple(memGetInt(pSize), memGetInt(pType), name)
        }

    // --- [ glBindVertexArray ] ---

    /**
     * Binds a vertex array object
     *
     * @param array the name of the vertex array to bind
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBindVertexArray">Reference Page</a>
     */
    fun bindVertexArray(array: GlVertexArray) = GL30C.glBindVertexArray(array.name)

    // --- [ glDeleteVertexArrays ] ---

    /**
     * Deletes vertex array objects.
     *
     * @param arrays an array containing the n names of the objects to be deleted
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteVertexArrays">Reference Page</a>
     */
    fun deleteVertexArrays(arrays: GlVertexArrays) = GL30C.nglDeleteVertexArrays(arrays.rem, arrays.adr.L)

    /**
     * Deletes vertex array objects.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDeleteVertexArrays">Reference Page</a>
     */
    fun deleteVertexArrays(array: GlVertexArray) = GL30C.nglDeleteVertexArrays(1, array.name.toOffHeap())

    // --- [ glGenVertexArrays ] ---

    /**
     * Generates vertex array object names.
     *
     * @param arrays a buffer in which the generated vertex array object names are stored
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenVertexArrays">Reference Page</a>
     */
    fun genVertexArrays(arrays: GlVertexArrays) = GL30C.nglGenVertexArrays(arrays.rem, arrays.adr.L)

    /**
     * Generates vertex array object names.
     *
     * @param size size of the generated vertex array object names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenVertexArrays">Reference Page</a>
     */
    fun genVertexArrays(size: Int): GlVertexArrays = GlVertexArrays(size).also(::genVertexArrays)

    /**
     * Generates vertex array object names.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenVertexArrays">Reference Page</a>
     */
    fun genVertexArrays(array: KMutableProperty0<GlVertexArray>): GlVertexArray {
        array.set(genVertexArrays())
        return array()
    }

    /**
     * Generates vertex array object names.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenVertexArrays">Reference Page</a>
     */
    fun genVertexArrays() = GlVertexArray(readInt { GL30C.nglGenVertexArrays(1, it) })

    // --- [ glIsVertexArray ] ---

    /**
     * Determines if a name corresponds to a vertex array object.
     *
     * @param array a value that may be the name of a vertex array object
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glIsVertexArray">Reference Page</a>
     */
    fun isVertexArray(array: GlVertexArray): Boolean = GL30C.glIsVertexArray(array.name)

//    /** TODO?
//     * Array version of: {@link #glClearBufferiv ClearBufferiv}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glClearBuffer">Reference Page</a>
//     */
//    public static void glClearBufferiv(@NativeType("GLenum") int buffer, @NativeType("GLint") int drawbuffer, @NativeType("GLint *") int[] value) {
//        long __functionAddress = GL.getICD().glClearBufferiv;
//        if (CHECKS) {
//            check(__functionAddress);
//            check(value, 1);
//        }
//        callPV(__functionAddress, buffer, drawbuffer, value);
//    }
//
//    /**
//     * Array version of: {@link #glClearBufferuiv ClearBufferuiv}
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glClearBuffer">Reference Page</a>
//     */
//    public static void glClearBufferuiv(@NativeType("GLenum") int buffer, @NativeType("GLint") int drawbuffer, @NativeType("GLint *") int[] value) {
//        long __functionAddress = GL.getICD().glClearBufferuiv;
//        if (CHECKS) {
//            check(__functionAddress);
//            check(value, 4);
//        }
//        callPV(__functionAddress, buffer, drawbuffer, value);
//    }
}