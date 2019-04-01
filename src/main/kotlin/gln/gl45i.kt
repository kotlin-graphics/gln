package gln

import glm_.L
import glm_.vec2.Vec2i
import glm_.vec3.Vec3i
import glm_.vec4.Vec4i
import glm_.vec4.Vec4ui
import gln.framebuffer.GlFramebuffer
import gln.framebuffer.GlFramebuffers
import gln.identifiers.*
import gln.program.GlPipeline
import gln.program.GlPipelines
import gln.renderbuffer.GlRenderbuffer
import gln.renderbuffer.GlRenderbuffers
import gln.sampler.GlSampler
import gln.sampler.GlSamplers
import gln.transformFeedback.GlTransformFeedback
import gln.transformFeedback.GlTransformFeedbacks
import gln.vertexArray.GlVertexArray
import gln.vertexArray.GlVertexArrays
import kool.Ptr
import kool.adr
import kool.rem
import kool.stak
import org.lwjgl.opengl.GL15C
import org.lwjgl.opengl.GL30C
import org.lwjgl.opengl.GL45C
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.system.MemoryUtil.memByteBufferSafe
import java.nio.*

/**
 * The OpenGL functionality up to version 4.5. Includes only Core Profile symbols.
 *
 * <p>OpenGL 4.5 implementations support revision 4.50 of the OpenGL Shading Language.</p>
 *
 * <p>Extensions promoted to core in this release:</p>
 *
 * <ul>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_clip_control.txt">ARB_clip_control</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_cull_distance.txt">ARB_cull_distance</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_ES3_1_compatibility.txt">ARB_ES3_1_compatibility</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_conditional_render_inverted.txt">ARB_conditional_render_inverted</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/KHR/KHR_context_flush_control.txt">KHR_context_flush_control</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_derivative_control.txt">ARB_derivative_control</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_direct_state_access.txt">ARB_direct_state_access</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_get_texture_sub_image.txt">ARB_get_texture_sub_image</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/KHR/KHR_robustness.txt">KHR_robustness</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_shader_texture_image_samples.txt">ARB_shader_texture_image_samples</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_texture_barrier.txt">ARB_texture_barrier</a></li>
 * </ul>
 */
interface gl45i {

    // --- [ glClipControl ] ---

    /**
     * Controls the clipping volume behavior.
     *
     * <p>These parameters update the clip control origin and depth mode respectively. The initial value of the clip control origin is {@link GL20#GL_LOWER_LEFT LOWER_LEFT} and the
     * initial value of the depth mode is {@link #GL_NEGATIVE_ONE_TO_ONE NEGATIVE_ONE_TO_ONE}.</p>
     *
     * <p>The error {@link GL11#GL_INVALID_OPERATION INVALID_OPERATION} is generated if ClipControl is executed between the execution of {@link GL11#glBegin Begin} and the corresponding
     * execution of {@link GL11#glEnd End}.</p>
     *
     * @param origin the clip origin. One of:<br><table><tr><td>{@link GL20#GL_LOWER_LEFT LOWER_LEFT}</td><td>{@link GL20#GL_UPPER_LEFT UPPER_LEFT}</td></tr></table>
     * @param depth  the clip depth mode. One of:<br><table><tr><td>{@link #GL_NEGATIVE_ONE_TO_ONE NEGATIVE_ONE_TO_ONE}</td><td>{@link #GL_ZERO_TO_ONE ZERO_TO_ONE}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClipControl">Reference Page</a>
     */
    fun clipControl(origin: ClipOrigin, depth: ClipDepthMode) = GL45C.glClipControl(origin.i, depth.i)

    // --- [ glCreateTransformFeedbacks ] ---

    /**
     * Returns {@code n} previously unused transform feedback object names in {@code ids}, each representing a new state vector.
     *
     * @param ids the buffer in which to return the names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateTransformFeedbacks">Reference Page</a>
     */
    fun createTransformFeedbacks(ids: GlTransformFeedbacks) = GL45C.nglCreateTransformFeedbacks(ids.rem, ids.adr)

    /**
     * Returns {@code n} previously unused transform feedback object names in {@code ids}, each representing a new state vector.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateTransformFeedbacks">Reference Page</a>
     */
    fun createTransformFeedbacks(): GlTransformFeedback =
            GlTransformFeedback(stak.intAddress { GL45C.nglCreateTransformFeedbacks(1, it) })

    // --- [ glTransformFeedbackBufferBase ] ---

    /**
     * Binds a buffer object to a transform feedback object.
     *
     * @param xfb    zero or the name of an existing transform feedback object
     * @param index  the transform feedback stream index
     * @param buffer the name of an existing buffer object
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTransformFeedbackBufferBase">Reference Page</a>
     */
    fun transformFeedbackBufferBase(xfb: GlTransformFeedback, index: Int, buffer: GlBuffer) =
            GL45C.glTransformFeedbackBufferBase(xfb.name, index, buffer.name)

    // --- [ glTransformFeedbackBufferRange ] ---

    /**
     * Binds a region of a buffer object to a transform feedback object.
     *
     * @param xfb    zero or the name of an existing transform feedback object
     * @param index  the transform feedback stream index
     * @param buffer the name of an existing buffer object
     * @param offset the starting offset in basic machine units into the buffer object
     * @param size   the amount of data in machine units
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTransformFeedbackBufferRange">Reference Page</a>
     */
    fun transformFeedbackBufferRange(xfb: GlTransformFeedback, index: Int, buffer: GlBuffer, offset: Int, size: Int) =
            GL45C.glTransformFeedbackBufferRange(xfb.name, index, buffer.name, offset.L, size.L)

    // --- [ glGetTransformFeedbackiv ] ---
    // inline reified

    // --- [ glGetTransformFeedbacki_v ] ---
    // inline reified

    // --- [ glGetTransformFeedbacki64_v ] ---
    // inline reified

    // --- [ glCreateBuffers ] ---

    /**
     * Returns {@code n} previously unused buffer names in {@code buffers}, each representing a new buffer object initialized as if it had been bound to an
     * unspecified target.
     *
     * @param buffers the buffer in which to return the names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateBuffers">Reference Page</a>
     */
    fun createBuffers(buffers: GlBuffers) = GL45C.nglCreateBuffers(buffers.rem, buffers.adr)

    /**
     * Returns {@code n} previously unused buffer names in {@code buffers}, each representing a new buffer object initialized as if it had been bound to an
     * unspecified target.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateBuffers">Reference Page</a>
     */
    fun createBuffers(): GlBuffer = GlBuffer(stak.intAddress { GL45C.nglCreateBuffers(1, it) })
//
//    // --- [ glNamedBufferStorage ] --- TODO
//
//    /**
//     * Unsafe version of: {@link #glNamedBufferStorage NamedBufferStorage}
//     *
//     * @param size the size of the data store in basic machine units
//     */
//    public static native void nglNamedBufferStorage(int buffer, long size, long data, int flags);
//
//    /**
//     * DSA version of {@link GL44C#glBufferStorage BufferStorage}.
//     *
//     * @param buffer the buffer object name
//     * @param size   the size of the data store in basic machine units
//     * @param flags  the bitwise {@code OR} of flags describing the intended usage of the buffer object's data store by the application. Valid flags and their meanings
//     *               are as follows:
//     *
//     *               <ul>
//     *               <li>{@link GL44#GL_DYNAMIC_STORAGE_BIT DYNAMIC_STORAGE_BIT} &ndash; The contents of the data store may be updated after creation through calls to
//     *               {@link GL15C#glBufferSubData BufferSubData}. If this bit is not set, the buffer content may not be directly updated by the client. The {@code data}
//     *               argument may be used to specify the initial content of the buffer's data store regardless of the presence of the {@link GL44#GL_DYNAMIC_STORAGE_BIT DYNAMIC_STORAGE_BIT}.
//     *               Regardless of the presence of this bit, buffers may always be updated with server-side calls such as {@link GL31C#glCopyBufferSubData CopyBufferSubData} and
//     *               {@link GL43C#glClearBufferSubData ClearBufferSubData}.</li>
//     *               <li>{@link GL30#GL_MAP_READ_BIT MAP_READ_BIT} &ndash; The buffer's data store may be mapped by the client for read access and a pointer in the client's address space
//     *               obtained that may be read from.</li>
//     *               <li>{@link GL30#GL_MAP_WRITE_BIT MAP_WRITE_BIT} &ndash; The buffer's data store may be mapped by the client for write access and a pointer in the client's address
//     *               space obtained that may be written to.</li>
//     *               <li>{@link GL44#GL_MAP_PERSISTENT_BIT MAP_PERSISTENT_BIT} &ndash; The client may request that the server read from or write to the buffer while it is mapped. The client's
//     *               pointer to the data store remains valid so long as the data store is mapped, even during execution of drawing or dispatch commands.</li>
//     *               <li>{@link GL44#GL_MAP_COHERENT_BIT MAP_COHERENT_BIT} &ndash; Shared access to buffers that are simultaneously mapped for client access and are used by the server will be
//     *               coherent, so long as that mapping is performed using MapBufferRange. That is, data written to the store by either the client or server will be
//     *               immediately visible to the other with no further action taken by the application. In particular:
//     *
//     *               <ul>
//     *               <li>If {@code MAP_COHERENT_BIT} is not set and the client performs a write followed by a call to the {@link GL42C#glMemoryBarrier MemoryBarrier} command with
//     *               the {@link GL44#GL_CLIENT_MAPPED_BUFFER_BARRIER_BIT CLIENT_MAPPED_BUFFER_BARRIER_BIT} set, then in subsequent commands the server will see the writes.</li>
//     *               <li>If {@code MAP_COHERENT_BIT} is set and the client performs a write, then in subsequent commands the server will see the writes.</li>
//     *               <li>If {@code MAP_COHERENT_BIT} is not set and the server performs a write, the application must call {@link GL42C#glMemoryBarrier MemoryBarrier} with the
//     *               {@link GL44#GL_CLIENT_MAPPED_BUFFER_BARRIER_BIT CLIENT_MAPPED_BUFFER_BARRIER_BIT} set and then call {@link GL32C#glFenceSync FenceSync} with {@link GL32#GL_SYNC_GPU_COMMANDS_COMPLETE SYNC_GPU_COMMANDS_COMPLETE} (or
//     *               {@link GL11C#glFinish Finish}). Then the CPU will see the writes after the sync is complete.</li>
//     *               <li>If {@code MAP_COHERENT_BIT} is set and the server does a write, the app must call {@link GL32C#glFenceSync FenceSync} with
//     *               {@link GL32#GL_SYNC_GPU_COMMANDS_COMPLETE SYNC_GPU_COMMANDS_COMPLETE} (or {@link GL11C#glFinish Finish}). Then the CPU will see the writes after the sync is complete.</li>
//     *               </ul></li>
//     *               <li>{@link GL44#GL_CLIENT_STORAGE_BIT CLIENT_STORAGE_BIT} &ndash; When all other criteria for the buffer storage allocation are met, this bit may be used by an
//     *               implementation to determine whether to use storage that is local to the server or to the client to serve as the backing store for the buffer.</li>
//     *               </ul>
//     *
//     *               <p>If {@code flags} contains {@link GL44#GL_MAP_PERSISTENT_BIT MAP_PERSISTENT_BIT}, it must also contain at least one of {@link GL30#GL_MAP_READ_BIT MAP_READ_BIT} or {@link GL30#GL_MAP_WRITE_BIT MAP_WRITE_BIT}.</p>
//     *
//     *               <p>It is an error to specify {@link GL44#GL_MAP_COHERENT_BIT MAP_COHERENT_BIT} without also specifying {@link GL44#GL_MAP_PERSISTENT_BIT MAP_PERSISTENT_BIT}.</p>
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferStorage">Reference Page</a>
//     */
//    public static void glNamedBufferStorage(@NativeType("GLuint") int buffer, @NativeType("GLsizeiptr") long size, @NativeType("GLbitfield") int flags) {
//        nglNamedBufferStorage(buffer, size, NULL, flags);
//    }
//
//    /**
//     * DSA version of {@link GL44C#glBufferStorage BufferStorage}.
//     *
//     * @param buffer the buffer object name
//     * @param data   the address in client memory of the data that should be used to initialize the buffer's data store. If {@code data} is {@code NULL}, the data store of the
//     *               buffer is created, but contains undefined data. Otherwise, {@code data} should point to an array of at least {@code size} basic machine units.
//     * @param flags  the bitwise {@code OR} of flags describing the intended usage of the buffer object's data store by the application. Valid flags and their meanings
//     *               are as follows:
//     *
//     *               <ul>
//     *               <li>{@link GL44#GL_DYNAMIC_STORAGE_BIT DYNAMIC_STORAGE_BIT} &ndash; The contents of the data store may be updated after creation through calls to
//     *               {@link GL15C#glBufferSubData BufferSubData}. If this bit is not set, the buffer content may not be directly updated by the client. The {@code data}
//     *               argument may be used to specify the initial content of the buffer's data store regardless of the presence of the {@link GL44#GL_DYNAMIC_STORAGE_BIT DYNAMIC_STORAGE_BIT}.
//     *               Regardless of the presence of this bit, buffers may always be updated with server-side calls such as {@link GL31C#glCopyBufferSubData CopyBufferSubData} and
//     *               {@link GL43C#glClearBufferSubData ClearBufferSubData}.</li>
//     *               <li>{@link GL30#GL_MAP_READ_BIT MAP_READ_BIT} &ndash; The buffer's data store may be mapped by the client for read access and a pointer in the client's address space
//     *               obtained that may be read from.</li>
//     *               <li>{@link GL30#GL_MAP_WRITE_BIT MAP_WRITE_BIT} &ndash; The buffer's data store may be mapped by the client for write access and a pointer in the client's address
//     *               space obtained that may be written to.</li>
//     *               <li>{@link GL44#GL_MAP_PERSISTENT_BIT MAP_PERSISTENT_BIT} &ndash; The client may request that the server read from or write to the buffer while it is mapped. The client's
//     *               pointer to the data store remains valid so long as the data store is mapped, even during execution of drawing or dispatch commands.</li>
//     *               <li>{@link GL44#GL_MAP_COHERENT_BIT MAP_COHERENT_BIT} &ndash; Shared access to buffers that are simultaneously mapped for client access and are used by the server will be
//     *               coherent, so long as that mapping is performed using MapBufferRange. That is, data written to the store by either the client or server will be
//     *               immediately visible to the other with no further action taken by the application. In particular:
//     *
//     *               <ul>
//     *               <li>If {@code MAP_COHERENT_BIT} is not set and the client performs a write followed by a call to the {@link GL42C#glMemoryBarrier MemoryBarrier} command with
//     *               the {@link GL44#GL_CLIENT_MAPPED_BUFFER_BARRIER_BIT CLIENT_MAPPED_BUFFER_BARRIER_BIT} set, then in subsequent commands the server will see the writes.</li>
//     *               <li>If {@code MAP_COHERENT_BIT} is set and the client performs a write, then in subsequent commands the server will see the writes.</li>
//     *               <li>If {@code MAP_COHERENT_BIT} is not set and the server performs a write, the application must call {@link GL42C#glMemoryBarrier MemoryBarrier} with the
//     *               {@link GL44#GL_CLIENT_MAPPED_BUFFER_BARRIER_BIT CLIENT_MAPPED_BUFFER_BARRIER_BIT} set and then call {@link GL32C#glFenceSync FenceSync} with {@link GL32#GL_SYNC_GPU_COMMANDS_COMPLETE SYNC_GPU_COMMANDS_COMPLETE} (or
//     *               {@link GL11C#glFinish Finish}). Then the CPU will see the writes after the sync is complete.</li>
//     *               <li>If {@code MAP_COHERENT_BIT} is set and the server does a write, the app must call {@link GL32C#glFenceSync FenceSync} with
//     *               {@link GL32#GL_SYNC_GPU_COMMANDS_COMPLETE SYNC_GPU_COMMANDS_COMPLETE} (or {@link GL11C#glFinish Finish}). Then the CPU will see the writes after the sync is complete.</li>
//     *               </ul></li>
//     *               <li>{@link GL44#GL_CLIENT_STORAGE_BIT CLIENT_STORAGE_BIT} &ndash; When all other criteria for the buffer storage allocation are met, this bit may be used by an
//     *               implementation to determine whether to use storage that is local to the server or to the client to serve as the backing store for the buffer.</li>
//     *               </ul>
//     *
//     *               <p>If {@code flags} contains {@link GL44#GL_MAP_PERSISTENT_BIT MAP_PERSISTENT_BIT}, it must also contain at least one of {@link GL30#GL_MAP_READ_BIT MAP_READ_BIT} or {@link GL30#GL_MAP_WRITE_BIT MAP_WRITE_BIT}.</p>
//     *
//     *               <p>It is an error to specify {@link GL44#GL_MAP_COHERENT_BIT MAP_COHERENT_BIT} without also specifying {@link GL44#GL_MAP_PERSISTENT_BIT MAP_PERSISTENT_BIT}.</p>
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferStorage">Reference Page</a>
//     */
//    public static void glNamedBufferStorage(@NativeType("GLuint") int buffer, @NativeType("void const *") ByteBuffer data, @NativeType("GLbitfield") int flags) {
//        nglNamedBufferStorage(buffer, data.remaining(), memAddress(data), flags);
//    }
//
//    /**
//     * DSA version of {@link GL44C#glBufferStorage BufferStorage}.
//     *
//     * @param buffer the buffer object name
//     * @param data   the address in client memory of the data that should be used to initialize the buffer's data store. If {@code data} is {@code NULL}, the data store of the
//     *               buffer is created, but contains undefined data. Otherwise, {@code data} should point to an array of at least {@code size} basic machine units.
//     * @param flags  the bitwise {@code OR} of flags describing the intended usage of the buffer object's data store by the application. Valid flags and their meanings
//     *               are as follows:
//     *
//     *               <ul>
//     *               <li>{@link GL44#GL_DYNAMIC_STORAGE_BIT DYNAMIC_STORAGE_BIT} &ndash; The contents of the data store may be updated after creation through calls to
//     *               {@link GL15C#glBufferSubData BufferSubData}. If this bit is not set, the buffer content may not be directly updated by the client. The {@code data}
//     *               argument may be used to specify the initial content of the buffer's data store regardless of the presence of the {@link GL44#GL_DYNAMIC_STORAGE_BIT DYNAMIC_STORAGE_BIT}.
//     *               Regardless of the presence of this bit, buffers may always be updated with server-side calls such as {@link GL31C#glCopyBufferSubData CopyBufferSubData} and
//     *               {@link GL43C#glClearBufferSubData ClearBufferSubData}.</li>
//     *               <li>{@link GL30#GL_MAP_READ_BIT MAP_READ_BIT} &ndash; The buffer's data store may be mapped by the client for read access and a pointer in the client's address space
//     *               obtained that may be read from.</li>
//     *               <li>{@link GL30#GL_MAP_WRITE_BIT MAP_WRITE_BIT} &ndash; The buffer's data store may be mapped by the client for write access and a pointer in the client's address
//     *               space obtained that may be written to.</li>
//     *               <li>{@link GL44#GL_MAP_PERSISTENT_BIT MAP_PERSISTENT_BIT} &ndash; The client may request that the server read from or write to the buffer while it is mapped. The client's
//     *               pointer to the data store remains valid so long as the data store is mapped, even during execution of drawing or dispatch commands.</li>
//     *               <li>{@link GL44#GL_MAP_COHERENT_BIT MAP_COHERENT_BIT} &ndash; Shared access to buffers that are simultaneously mapped for client access and are used by the server will be
//     *               coherent, so long as that mapping is performed using MapBufferRange. That is, data written to the store by either the client or server will be
//     *               immediately visible to the other with no further action taken by the application. In particular:
//     *
//     *               <ul>
//     *               <li>If {@code MAP_COHERENT_BIT} is not set and the client performs a write followed by a call to the {@link GL42C#glMemoryBarrier MemoryBarrier} command with
//     *               the {@link GL44#GL_CLIENT_MAPPED_BUFFER_BARRIER_BIT CLIENT_MAPPED_BUFFER_BARRIER_BIT} set, then in subsequent commands the server will see the writes.</li>
//     *               <li>If {@code MAP_COHERENT_BIT} is set and the client performs a write, then in subsequent commands the server will see the writes.</li>
//     *               <li>If {@code MAP_COHERENT_BIT} is not set and the server performs a write, the application must call {@link GL42C#glMemoryBarrier MemoryBarrier} with the
//     *               {@link GL44#GL_CLIENT_MAPPED_BUFFER_BARRIER_BIT CLIENT_MAPPED_BUFFER_BARRIER_BIT} set and then call {@link GL32C#glFenceSync FenceSync} with {@link GL32#GL_SYNC_GPU_COMMANDS_COMPLETE SYNC_GPU_COMMANDS_COMPLETE} (or
//     *               {@link GL11C#glFinish Finish}). Then the CPU will see the writes after the sync is complete.</li>
//     *               <li>If {@code MAP_COHERENT_BIT} is set and the server does a write, the app must call {@link GL32C#glFenceSync FenceSync} with
//     *               {@link GL32#GL_SYNC_GPU_COMMANDS_COMPLETE SYNC_GPU_COMMANDS_COMPLETE} (or {@link GL11C#glFinish Finish}). Then the CPU will see the writes after the sync is complete.</li>
//     *               </ul></li>
//     *               <li>{@link GL44#GL_CLIENT_STORAGE_BIT CLIENT_STORAGE_BIT} &ndash; When all other criteria for the buffer storage allocation are met, this bit may be used by an
//     *               implementation to determine whether to use storage that is local to the server or to the client to serve as the backing store for the buffer.</li>
//     *               </ul>
//     *
//     *               <p>If {@code flags} contains {@link GL44#GL_MAP_PERSISTENT_BIT MAP_PERSISTENT_BIT}, it must also contain at least one of {@link GL30#GL_MAP_READ_BIT MAP_READ_BIT} or {@link GL30#GL_MAP_WRITE_BIT MAP_WRITE_BIT}.</p>
//     *
//     *               <p>It is an error to specify {@link GL44#GL_MAP_COHERENT_BIT MAP_COHERENT_BIT} without also specifying {@link GL44#GL_MAP_PERSISTENT_BIT MAP_PERSISTENT_BIT}.</p>
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferStorage">Reference Page</a>
//     */
//    public static void glNamedBufferStorage(@NativeType("GLuint") int buffer, @NativeType("void const *") ShortBuffer data, @NativeType("GLbitfield") int flags) {
//        nglNamedBufferStorage(buffer, Integer.toUnsignedLong(data.remaining()) << 1, memAddress(data), flags);
//    }
//
//    /**
//     * DSA version of {@link GL44C#glBufferStorage BufferStorage}.
//     *
//     * @param buffer the buffer object name
//     * @param data   the address in client memory of the data that should be used to initialize the buffer's data store. If {@code data} is {@code NULL}, the data store of the
//     *               buffer is created, but contains undefined data. Otherwise, {@code data} should point to an array of at least {@code size} basic machine units.
//     * @param flags  the bitwise {@code OR} of flags describing the intended usage of the buffer object's data store by the application. Valid flags and their meanings
//     *               are as follows:
//     *
//     *               <ul>
//     *               <li>{@link GL44#GL_DYNAMIC_STORAGE_BIT DYNAMIC_STORAGE_BIT} &ndash; The contents of the data store may be updated after creation through calls to
//     *               {@link GL15C#glBufferSubData BufferSubData}. If this bit is not set, the buffer content may not be directly updated by the client. The {@code data}
//     *               argument may be used to specify the initial content of the buffer's data store regardless of the presence of the {@link GL44#GL_DYNAMIC_STORAGE_BIT DYNAMIC_STORAGE_BIT}.
//     *               Regardless of the presence of this bit, buffers may always be updated with server-side calls such as {@link GL31C#glCopyBufferSubData CopyBufferSubData} and
//     *               {@link GL43C#glClearBufferSubData ClearBufferSubData}.</li>
//     *               <li>{@link GL30#GL_MAP_READ_BIT MAP_READ_BIT} &ndash; The buffer's data store may be mapped by the client for read access and a pointer in the client's address space
//     *               obtained that may be read from.</li>
//     *               <li>{@link GL30#GL_MAP_WRITE_BIT MAP_WRITE_BIT} &ndash; The buffer's data store may be mapped by the client for write access and a pointer in the client's address
//     *               space obtained that may be written to.</li>
//     *               <li>{@link GL44#GL_MAP_PERSISTENT_BIT MAP_PERSISTENT_BIT} &ndash; The client may request that the server read from or write to the buffer while it is mapped. The client's
//     *               pointer to the data store remains valid so long as the data store is mapped, even during execution of drawing or dispatch commands.</li>
//     *               <li>{@link GL44#GL_MAP_COHERENT_BIT MAP_COHERENT_BIT} &ndash; Shared access to buffers that are simultaneously mapped for client access and are used by the server will be
//     *               coherent, so long as that mapping is performed using MapBufferRange. That is, data written to the store by either the client or server will be
//     *               immediately visible to the other with no further action taken by the application. In particular:
//     *
//     *               <ul>
//     *               <li>If {@code MAP_COHERENT_BIT} is not set and the client performs a write followed by a call to the {@link GL42C#glMemoryBarrier MemoryBarrier} command with
//     *               the {@link GL44#GL_CLIENT_MAPPED_BUFFER_BARRIER_BIT CLIENT_MAPPED_BUFFER_BARRIER_BIT} set, then in subsequent commands the server will see the writes.</li>
//     *               <li>If {@code MAP_COHERENT_BIT} is set and the client performs a write, then in subsequent commands the server will see the writes.</li>
//     *               <li>If {@code MAP_COHERENT_BIT} is not set and the server performs a write, the application must call {@link GL42C#glMemoryBarrier MemoryBarrier} with the
//     *               {@link GL44#GL_CLIENT_MAPPED_BUFFER_BARRIER_BIT CLIENT_MAPPED_BUFFER_BARRIER_BIT} set and then call {@link GL32C#glFenceSync FenceSync} with {@link GL32#GL_SYNC_GPU_COMMANDS_COMPLETE SYNC_GPU_COMMANDS_COMPLETE} (or
//     *               {@link GL11C#glFinish Finish}). Then the CPU will see the writes after the sync is complete.</li>
//     *               <li>If {@code MAP_COHERENT_BIT} is set and the server does a write, the app must call {@link GL32C#glFenceSync FenceSync} with
//     *               {@link GL32#GL_SYNC_GPU_COMMANDS_COMPLETE SYNC_GPU_COMMANDS_COMPLETE} (or {@link GL11C#glFinish Finish}). Then the CPU will see the writes after the sync is complete.</li>
//     *               </ul></li>
//     *               <li>{@link GL44#GL_CLIENT_STORAGE_BIT CLIENT_STORAGE_BIT} &ndash; When all other criteria for the buffer storage allocation are met, this bit may be used by an
//     *               implementation to determine whether to use storage that is local to the server or to the client to serve as the backing store for the buffer.</li>
//     *               </ul>
//     *
//     *               <p>If {@code flags} contains {@link GL44#GL_MAP_PERSISTENT_BIT MAP_PERSISTENT_BIT}, it must also contain at least one of {@link GL30#GL_MAP_READ_BIT MAP_READ_BIT} or {@link GL30#GL_MAP_WRITE_BIT MAP_WRITE_BIT}.</p>
//     *
//     *               <p>It is an error to specify {@link GL44#GL_MAP_COHERENT_BIT MAP_COHERENT_BIT} without also specifying {@link GL44#GL_MAP_PERSISTENT_BIT MAP_PERSISTENT_BIT}.</p>
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferStorage">Reference Page</a>
//     */
//    public static void glNamedBufferStorage(@NativeType("GLuint") int buffer, @NativeType("void const *") IntBuffer data, @NativeType("GLbitfield") int flags) {
//        nglNamedBufferStorage(buffer, Integer.toUnsignedLong(data.remaining()) << 2, memAddress(data), flags);
//    }
//
//    /**
//     * DSA version of {@link GL44C#glBufferStorage BufferStorage}.
//     *
//     * @param buffer the buffer object name
//     * @param data   the address in client memory of the data that should be used to initialize the buffer's data store. If {@code data} is {@code NULL}, the data store of the
//     *               buffer is created, but contains undefined data. Otherwise, {@code data} should point to an array of at least {@code size} basic machine units.
//     * @param flags  the bitwise {@code OR} of flags describing the intended usage of the buffer object's data store by the application. Valid flags and their meanings
//     *               are as follows:
//     *
//     *               <ul>
//     *               <li>{@link GL44#GL_DYNAMIC_STORAGE_BIT DYNAMIC_STORAGE_BIT} &ndash; The contents of the data store may be updated after creation through calls to
//     *               {@link GL15C#glBufferSubData BufferSubData}. If this bit is not set, the buffer content may not be directly updated by the client. The {@code data}
//     *               argument may be used to specify the initial content of the buffer's data store regardless of the presence of the {@link GL44#GL_DYNAMIC_STORAGE_BIT DYNAMIC_STORAGE_BIT}.
//     *               Regardless of the presence of this bit, buffers may always be updated with server-side calls such as {@link GL31C#glCopyBufferSubData CopyBufferSubData} and
//     *               {@link GL43C#glClearBufferSubData ClearBufferSubData}.</li>
//     *               <li>{@link GL30#GL_MAP_READ_BIT MAP_READ_BIT} &ndash; The buffer's data store may be mapped by the client for read access and a pointer in the client's address space
//     *               obtained that may be read from.</li>
//     *               <li>{@link GL30#GL_MAP_WRITE_BIT MAP_WRITE_BIT} &ndash; The buffer's data store may be mapped by the client for write access and a pointer in the client's address
//     *               space obtained that may be written to.</li>
//     *               <li>{@link GL44#GL_MAP_PERSISTENT_BIT MAP_PERSISTENT_BIT} &ndash; The client may request that the server read from or write to the buffer while it is mapped. The client's
//     *               pointer to the data store remains valid so long as the data store is mapped, even during execution of drawing or dispatch commands.</li>
//     *               <li>{@link GL44#GL_MAP_COHERENT_BIT MAP_COHERENT_BIT} &ndash; Shared access to buffers that are simultaneously mapped for client access and are used by the server will be
//     *               coherent, so long as that mapping is performed using MapBufferRange. That is, data written to the store by either the client or server will be
//     *               immediately visible to the other with no further action taken by the application. In particular:
//     *
//     *               <ul>
//     *               <li>If {@code MAP_COHERENT_BIT} is not set and the client performs a write followed by a call to the {@link GL42C#glMemoryBarrier MemoryBarrier} command with
//     *               the {@link GL44#GL_CLIENT_MAPPED_BUFFER_BARRIER_BIT CLIENT_MAPPED_BUFFER_BARRIER_BIT} set, then in subsequent commands the server will see the writes.</li>
//     *               <li>If {@code MAP_COHERENT_BIT} is set and the client performs a write, then in subsequent commands the server will see the writes.</li>
//     *               <li>If {@code MAP_COHERENT_BIT} is not set and the server performs a write, the application must call {@link GL42C#glMemoryBarrier MemoryBarrier} with the
//     *               {@link GL44#GL_CLIENT_MAPPED_BUFFER_BARRIER_BIT CLIENT_MAPPED_BUFFER_BARRIER_BIT} set and then call {@link GL32C#glFenceSync FenceSync} with {@link GL32#GL_SYNC_GPU_COMMANDS_COMPLETE SYNC_GPU_COMMANDS_COMPLETE} (or
//     *               {@link GL11C#glFinish Finish}). Then the CPU will see the writes after the sync is complete.</li>
//     *               <li>If {@code MAP_COHERENT_BIT} is set and the server does a write, the app must call {@link GL32C#glFenceSync FenceSync} with
//     *               {@link GL32#GL_SYNC_GPU_COMMANDS_COMPLETE SYNC_GPU_COMMANDS_COMPLETE} (or {@link GL11C#glFinish Finish}). Then the CPU will see the writes after the sync is complete.</li>
//     *               </ul></li>
//     *               <li>{@link GL44#GL_CLIENT_STORAGE_BIT CLIENT_STORAGE_BIT} &ndash; When all other criteria for the buffer storage allocation are met, this bit may be used by an
//     *               implementation to determine whether to use storage that is local to the server or to the client to serve as the backing store for the buffer.</li>
//     *               </ul>
//     *
//     *               <p>If {@code flags} contains {@link GL44#GL_MAP_PERSISTENT_BIT MAP_PERSISTENT_BIT}, it must also contain at least one of {@link GL30#GL_MAP_READ_BIT MAP_READ_BIT} or {@link GL30#GL_MAP_WRITE_BIT MAP_WRITE_BIT}.</p>
//     *
//     *               <p>It is an error to specify {@link GL44#GL_MAP_COHERENT_BIT MAP_COHERENT_BIT} without also specifying {@link GL44#GL_MAP_PERSISTENT_BIT MAP_PERSISTENT_BIT}.</p>
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferStorage">Reference Page</a>
//     */
//    public static void glNamedBufferStorage(@NativeType("GLuint") int buffer, @NativeType("void const *") FloatBuffer data, @NativeType("GLbitfield") int flags) {
//        nglNamedBufferStorage(buffer, Integer.toUnsignedLong(data.remaining()) << 2, memAddress(data), flags);
//    }
//
//    /**
//     * DSA version of {@link GL44C#glBufferStorage BufferStorage}.
//     *
//     * @param buffer the buffer object name
//     * @param data   the address in client memory of the data that should be used to initialize the buffer's data store. If {@code data} is {@code NULL}, the data store of the
//     *               buffer is created, but contains undefined data. Otherwise, {@code data} should point to an array of at least {@code size} basic machine units.
//     * @param flags  the bitwise {@code OR} of flags describing the intended usage of the buffer object's data store by the application. Valid flags and their meanings
//     *               are as follows:
//     *
//     *               <ul>
//     *               <li>{@link GL44#GL_DYNAMIC_STORAGE_BIT DYNAMIC_STORAGE_BIT} &ndash; The contents of the data store may be updated after creation through calls to
//     *               {@link GL15C#glBufferSubData BufferSubData}. If this bit is not set, the buffer content may not be directly updated by the client. The {@code data}
//     *               argument may be used to specify the initial content of the buffer's data store regardless of the presence of the {@link GL44#GL_DYNAMIC_STORAGE_BIT DYNAMIC_STORAGE_BIT}.
//     *               Regardless of the presence of this bit, buffers may always be updated with server-side calls such as {@link GL31C#glCopyBufferSubData CopyBufferSubData} and
//     *               {@link GL43C#glClearBufferSubData ClearBufferSubData}.</li>
//     *               <li>{@link GL30#GL_MAP_READ_BIT MAP_READ_BIT} &ndash; The buffer's data store may be mapped by the client for read access and a pointer in the client's address space
//     *               obtained that may be read from.</li>
//     *               <li>{@link GL30#GL_MAP_WRITE_BIT MAP_WRITE_BIT} &ndash; The buffer's data store may be mapped by the client for write access and a pointer in the client's address
//     *               space obtained that may be written to.</li>
//     *               <li>{@link GL44#GL_MAP_PERSISTENT_BIT MAP_PERSISTENT_BIT} &ndash; The client may request that the server read from or write to the buffer while it is mapped. The client's
//     *               pointer to the data store remains valid so long as the data store is mapped, even during execution of drawing or dispatch commands.</li>
//     *               <li>{@link GL44#GL_MAP_COHERENT_BIT MAP_COHERENT_BIT} &ndash; Shared access to buffers that are simultaneously mapped for client access and are used by the server will be
//     *               coherent, so long as that mapping is performed using MapBufferRange. That is, data written to the store by either the client or server will be
//     *               immediately visible to the other with no further action taken by the application. In particular:
//     *
//     *               <ul>
//     *               <li>If {@code MAP_COHERENT_BIT} is not set and the client performs a write followed by a call to the {@link GL42C#glMemoryBarrier MemoryBarrier} command with
//     *               the {@link GL44#GL_CLIENT_MAPPED_BUFFER_BARRIER_BIT CLIENT_MAPPED_BUFFER_BARRIER_BIT} set, then in subsequent commands the server will see the writes.</li>
//     *               <li>If {@code MAP_COHERENT_BIT} is set and the client performs a write, then in subsequent commands the server will see the writes.</li>
//     *               <li>If {@code MAP_COHERENT_BIT} is not set and the server performs a write, the application must call {@link GL42C#glMemoryBarrier MemoryBarrier} with the
//     *               {@link GL44#GL_CLIENT_MAPPED_BUFFER_BARRIER_BIT CLIENT_MAPPED_BUFFER_BARRIER_BIT} set and then call {@link GL32C#glFenceSync FenceSync} with {@link GL32#GL_SYNC_GPU_COMMANDS_COMPLETE SYNC_GPU_COMMANDS_COMPLETE} (or
//     *               {@link GL11C#glFinish Finish}). Then the CPU will see the writes after the sync is complete.</li>
//     *               <li>If {@code MAP_COHERENT_BIT} is set and the server does a write, the app must call {@link GL32C#glFenceSync FenceSync} with
//     *               {@link GL32#GL_SYNC_GPU_COMMANDS_COMPLETE SYNC_GPU_COMMANDS_COMPLETE} (or {@link GL11C#glFinish Finish}). Then the CPU will see the writes after the sync is complete.</li>
//     *               </ul></li>
//     *               <li>{@link GL44#GL_CLIENT_STORAGE_BIT CLIENT_STORAGE_BIT} &ndash; When all other criteria for the buffer storage allocation are met, this bit may be used by an
//     *               implementation to determine whether to use storage that is local to the server or to the client to serve as the backing store for the buffer.</li>
//     *               </ul>
//     *
//     *               <p>If {@code flags} contains {@link GL44#GL_MAP_PERSISTENT_BIT MAP_PERSISTENT_BIT}, it must also contain at least one of {@link GL30#GL_MAP_READ_BIT MAP_READ_BIT} or {@link GL30#GL_MAP_WRITE_BIT MAP_WRITE_BIT}.</p>
//     *
//     *               <p>It is an error to specify {@link GL44#GL_MAP_COHERENT_BIT MAP_COHERENT_BIT} without also specifying {@link GL44#GL_MAP_PERSISTENT_BIT MAP_PERSISTENT_BIT}.</p>
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferStorage">Reference Page</a>
//     */
//    public static void glNamedBufferStorage(@NativeType("GLuint") int buffer, @NativeType("void const *") DoubleBuffer data, @NativeType("GLbitfield") int flags) {
//        nglNamedBufferStorage(buffer, Integer.toUnsignedLong(data.remaining()) << 3, memAddress(data), flags);
//    }

    // --- [ glNamedBufferData ] ---

    /**
     * DSA version of {@link GL15C#glBufferData BufferData}.
     *
     * @param size  the size in bytes of the buffer object's new data store
     * @param usage the expected usage pattern of the data store. One of:<br><table><tr><td>{@link GL15#GL_STREAM_DRAW STREAM_DRAW}</td><td>{@link GL15#GL_STREAM_READ STREAM_READ}</td><td>{@link GL15#GL_STREAM_COPY STREAM_COPY}</td><td>{@link GL15#GL_STATIC_DRAW STATIC_DRAW}</td><td>{@link GL15#GL_STATIC_READ STATIC_READ}</td><td>{@link GL15#GL_STATIC_COPY STATIC_COPY}</td><td>{@link GL15#GL_DYNAMIC_DRAW DYNAMIC_DRAW}</td></tr><tr><td>{@link GL15#GL_DYNAMIC_READ DYNAMIC_READ}</td><td>{@link GL15#GL_DYNAMIC_COPY DYNAMIC_COPY}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferData">Reference Page</a>
     */
    fun bufferData(buffer: GlBuffer, size: Int, usage: Usage) = GL45C.nglNamedBufferData(buffer.name, size.L, NULL, usage.i)

    /**
     * DSA version of {@link GL15C#glBufferData BufferData}.
     *
     * @param data  a pointer to data that will be copied into the data store for initialization, or {@code NULL} if no data is to be copied
     * @param usage the expected usage pattern of the data store. One of:<br><table><tr><td>{@link GL15#GL_STREAM_DRAW STREAM_DRAW}</td><td>{@link GL15#GL_STREAM_READ STREAM_READ}</td><td>{@link GL15#GL_STREAM_COPY STREAM_COPY}</td><td>{@link GL15#GL_STATIC_DRAW STATIC_DRAW}</td><td>{@link GL15#GL_STATIC_READ STATIC_READ}</td><td>{@link GL15#GL_STATIC_COPY STATIC_COPY}</td><td>{@link GL15#GL_DYNAMIC_DRAW DYNAMIC_DRAW}</td></tr><tr><td>{@link GL15#GL_DYNAMIC_READ DYNAMIC_READ}</td><td>{@link GL15#GL_DYNAMIC_COPY DYNAMIC_COPY}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferData">Reference Page</a>
     */
    fun bufferData(buffer: GlBuffer, data: Buffer, usage: Usage) = GL45C.nglNamedBufferData(buffer.name, data.rem.L, data.adr, usage.i)

    // --- [ glNamedBufferSubData ] ---

    /**
     * DSA version of {@link GL15C#glBufferSubData BufferSubData}.
     *
     * @param offset the offset into the buffer object's data store where data replacement will begin, measured in bytes
     * @param data   a pointer to the new data that will be copied into the data store
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBufferSubData">Reference Page</a>
     */
    fun bufferSubData(buffer: GlBuffer, offset: Int, data: Buffer) =
            GL45C.nglNamedBufferSubData(buffer.name, offset.L, data.rem.L, data.adr)

    // --- [ glCopyNamedBufferSubData ] ---

    /**
     * DSA version of {@link GL31C#glCopyBufferSubData CopyBufferSubData}.
     *
     * @param readBuffer  the source buffer object name
     * @param writeBuffer the destination buffer object name
     * @param readOffset  the source buffer object offset, in bytes
     * @param writeOffset the destination buffer object offset, in bytes
     * @param size        the number of bytes to copy
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCopyBufferSubData">Reference Page</a>
     */
    fun copyBufferSubData(readBuffer: GlBuffer, writeBuffer: GlBuffer, readOffset: Int, writeOffset: Int, size: Int) =
            GL45C.glCopyNamedBufferSubData(readBuffer.name, writeBuffer.name, readOffset.L, writeOffset.L, size.L)

    // --- [ glClearNamedBufferData ] --- TODO format and type

    /**
     * DSA version of {@link GL43C#glClearBufferData ClearBufferData}.
     *
     * @param buffer         the buffer object name
     * @param internalFormat the internal format with which the data will be stored in the buffer object
     * @param format         the format of the data in memory addressed by {@code data}. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td><td>{@link GL11#GL_LUMINANCE LUMINANCE}</td><td>{@link GL11#GL_LUMINANCE_ALPHA LUMINANCE_ALPHA}</td></tr></table>
     * @param type           the type of the data in memory addressed by {@code data}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr><tr><td>{@link GL11#GL_BITMAP BITMAP}</td></tr></table>
     * @param data           the buffer containing the data to be used as the source of the constant fill value. The elements of data are converted by the GL into the format
     *                       specified by internalFormat, and then used to fill the specified range of the destination buffer. If data is {@code NULL}, then it is ignored and the
     *                       sub-range of the buffer is filled with zeros.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClearBufferData">Reference Page</a>
     */
    fun clearBufferData(buffer: GlBuffer, internalFormat: InternalFormat, format: Int, type: Int, data: Buffer? = null) =
            GL45C.nglClearNamedBufferData(buffer.name, internalFormat.i, format, type, data?.adr ?: NULL)

    // --- [ glClearNamedBufferSubData ] --- TODO format and type

    /**
     * DSA version of {@link GL43C#glClearBufferSubData ClearBufferSubData}.
     *
     * @param buffer         the buffer object name
     * @param internalformat the internal format with which the data will be stored in the buffer object
     * @param offset         the offset, in basic machine units into the buffer object's data store at which to start filling
     * @param size           the size, in basic machine units of the range of the data store to fill
     * @param format         the format of the data in memory addressed by {@code data}. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td><td>{@link GL11#GL_LUMINANCE LUMINANCE}</td><td>{@link GL11#GL_LUMINANCE_ALPHA LUMINANCE_ALPHA}</td></tr></table>
     * @param type           the type of the data in memory addressed by {@code data}. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr><tr><td>{@link GL11#GL_BITMAP BITMAP}</td></tr></table>
     * @param data           the buffer containing the data to be used as the source of the constant fill value. The elements of data are converted by the GL into the format
     *                       specified by internalformat, and then used to fill the specified range of the destination buffer. If data is {@code NULL}, then it is ignored and the
     *                       sub-range of the buffer is filled with zeros.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClearBufferSubData">Reference Page</a>
     */
    fun clearBufferSubData(buffer: GlBuffer, internalformat: Int, offset: Int, size: Int, format: Int, type: Int, data: Buffer? = null) =
            GL45C.nglClearNamedBufferSubData(buffer.name, internalformat, offset.L, size.L, format, type, data?.adr
                    ?: NULL)

    // --- [ glMapNamedBuffer ] ---

    /**
     * DSA version of {@link GL15C#glMapBuffer MapBuffer}.
     *
     * @param buffer the buffer object name
     * @param access the access policy, indicating whether it will be possible to read from, write to, or both read from and write to the buffer object's mapped data store. One of:<br><table><tr><td>{@link GL15#GL_READ_ONLY READ_ONLY}</td><td>{@link GL15#GL_WRITE_ONLY WRITE_ONLY}</td><td>{@link GL15#GL_READ_WRITE READ_WRITE}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glMapBuffer">Reference Page</a>
     */
    fun mapBuffer(buffer: GlBuffer, access: BufferAccess): ByteBuffer? {
        val ptr = GL45C.nglMapNamedBuffer(buffer.name, access.i)
        return if (ptr != NULL) memByteBufferSafe(ptr, gl.getBufferParameter(buffer, BufferParameter.SIZE)) else null
    }

    // --- [ glMapNamedBufferRange ] ---

    /**
     * DSA version of {@link GL30C#glMapBufferRange MapBufferRange}.
     *
     * @param buffer the buffer object name
     * @param offset the starting offset within the buffer of the range to be mapped
     * @param length the length of the range to be mapped
     * @param access a combination of access flags indicating the desired access to the range. One or more of:<br><table><tr><td>{@link GL30#GL_MAP_READ_BIT MAP_READ_BIT}</td><td>{@link GL30#GL_MAP_WRITE_BIT MAP_WRITE_BIT}</td><td>{@link GL30#GL_MAP_INVALIDATE_RANGE_BIT MAP_INVALIDATE_RANGE_BIT}</td><td>{@link GL30#GL_MAP_INVALIDATE_BUFFER_BIT MAP_INVALIDATE_BUFFER_BIT}</td></tr><tr><td>{@link GL30#GL_MAP_FLUSH_EXPLICIT_BIT MAP_FLUSH_EXPLICIT_BIT}</td><td>{@link GL30#GL_MAP_UNSYNCHRONIZED_BIT MAP_UNSYNCHRONIZED_BIT}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glMapBufferRange">Reference Page</a>
     */
    fun mapBufferRange(buffer: GlBuffer, offset: Int, length: Int, access: BufferAccess): ByteBuffer? {
        val ptr = GL45C.nglMapNamedBufferRange(buffer.name, offset.L, length.L, access.i)
        return memByteBufferSafe(ptr, length)
    }

    // --- [ glUnmapNamedBuffer ] ---

    /**
     * DSA version of {@link GL15C#glUnmapBuffer UnmapBuffer}.
     *
     * @param buffer the buffer object name
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUnmapBuffer">Reference Page</a>
     */
    fun unmapBuffer(buffer: GlBuffer): Boolean = GL45C.glUnmapNamedBuffer(buffer.name)

    // --- [ glFlushMappedNamedBufferRange ] ---

    /**
     * DSA version of {@link GL30C#glFlushMappedBufferRange FlushMappedBufferRange}.
     *
     * @param buffer the buffer object name
     * @param offset the start of the buffer subrange, in basic machine units
     * @param length the length of the buffer subrange, in basic machine units
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFlushMappedBufferRange">Reference Page</a>
     */
    fun flushMappedBufferRange(buffer: GlBuffer, offset: Int, length: Int) =
            GL45C.glFlushMappedNamedBufferRange(buffer.name, offset.L, length.L)

    // --- [ glGetNamedBufferParameteriv ] ---
    // inline reified

    // --- [ glGetNamedBufferParameteri64v ] ---
    // inline reified

    // --- [ glGetNamedBufferPointerv ] --- TODO this could be merged into the above function if Ptr gets converted from typealias to inline class

    /**
     * DSA version of {@link GL15C#glGetBufferPointerv GetBufferPointerv}.
     *
     * @param buffer the buffer object name
     * @param name  the pointer to be returned. Must be:<br><table><tr><td>{@link GL15#GL_BUFFER_MAP_POINTER BUFFER_MAP_POINTER}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetBufferPointerv">Reference Page</a>
     */
    infix fun getBufferPointer(buffer: GlBuffer): Ptr =
            stak.pointerAddress { GL45C.nglGetNamedBufferPointerv(buffer.name, GL15C.GL_BUFFER_MAP_POINTER, it) }

    // --- [ glGetNamedBufferSubData ] ---

    /**
     * DSA version of {@link GL15C#glGetBufferSubData GetBufferSubData}.
     *
     * @param buffer the buffer object name
     * @param offset the offset into the buffer object's data store from which data will be returned, measured in bytes
     * @param data   a pointer to the location where buffer object data is returned
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetBufferSubData">Reference Page</a>
     */
    fun getBufferSubData(buffer: GlBuffer, offset: Int, data: Buffer) =
            GL45C.nglGetNamedBufferSubData(buffer.name, offset.L, data.rem.L, data.adr)

    // --- [ glCreateFramebuffers ] ---

    /**
     * Returns {@code n} previously unused framebuffer names in {@code framebuffers}, each representing a new framebuffer object.
     *
     * @param framebuffers the buffer in which to store the framebuffer names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateFramebuffers">Reference Page</a>
     */
    fun createFramebuffers(framebuffers: GlFramebuffers) =
            GL45C.nglCreateFramebuffers(framebuffers.rem, framebuffers.adr)

    /**
     * Returns {@code n} previously unused framebuffer names in {@code framebuffers}, each representing a new framebuffer object.
     *
     * @param size the size of the framebuffer names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateFramebuffers">Reference Page</a>
     */
    fun createFramebuffers(size: Int): GlFramebuffers = GlFramebuffers(size).apply(::createFramebuffers)

    /**
     * Returns {@code n} previously unused framebuffer names in {@code framebuffers}, each representing a new framebuffer object.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateFramebuffers">Reference Page</a>
     */
    fun createFramebuffers(): GlFramebuffer =
            GlFramebuffer(stak.intAddress { GL45C.nglCreateFramebuffers(1, it) })

    // --- [ glNamedFramebufferRenderbuffer ] ---

    /**
     * DSA version of {@link GL30C#glFramebufferRenderbuffer FramebufferRenderbuffer}.
     *
     * @param framebuffer        the framebuffer name
     * @param attachment         the attachment point of the framebuffer. One of:<br><table><tr><td>{@link GL30#GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td><td>{@link GL30#GL_COLOR_ATTACHMENT1 COLOR_ATTACHMENT1}</td><td>{@link GL30#GL_COLOR_ATTACHMENT2 COLOR_ATTACHMENT2}</td><td>{@link GL30#GL_COLOR_ATTACHMENT3 COLOR_ATTACHMENT3}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT4 COLOR_ATTACHMENT4}</td><td>{@link GL30#GL_COLOR_ATTACHMENT5 COLOR_ATTACHMENT5}</td><td>{@link GL30#GL_COLOR_ATTACHMENT6 COLOR_ATTACHMENT6}</td><td>{@link GL30#GL_COLOR_ATTACHMENT7 COLOR_ATTACHMENT7}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT8 COLOR_ATTACHMENT8}</td><td>{@link GL30#GL_COLOR_ATTACHMENT9 COLOR_ATTACHMENT9}</td><td>{@link GL30#GL_COLOR_ATTACHMENT10 COLOR_ATTACHMENT10}</td><td>{@link GL30#GL_COLOR_ATTACHMENT11 COLOR_ATTACHMENT11}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT12 COLOR_ATTACHMENT12}</td><td>{@link GL30#GL_COLOR_ATTACHMENT13 COLOR_ATTACHMENT13}</td><td>{@link GL30#GL_COLOR_ATTACHMENT14 COLOR_ATTACHMENT14}</td><td>{@link GL30#GL_COLOR_ATTACHMENT15 COLOR_ATTACHMENT15}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT16 COLOR_ATTACHMENT16}</td><td>{@link GL30#GL_COLOR_ATTACHMENT17 COLOR_ATTACHMENT17}</td><td>{@link GL30#GL_COLOR_ATTACHMENT18 COLOR_ATTACHMENT18}</td><td>{@link GL30#GL_COLOR_ATTACHMENT19 COLOR_ATTACHMENT19}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT20 COLOR_ATTACHMENT20}</td><td>{@link GL30#GL_COLOR_ATTACHMENT21 COLOR_ATTACHMENT21}</td><td>{@link GL30#GL_COLOR_ATTACHMENT22 COLOR_ATTACHMENT22}</td><td>{@link GL30#GL_COLOR_ATTACHMENT23 COLOR_ATTACHMENT23}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT24 COLOR_ATTACHMENT24}</td><td>{@link GL30#GL_COLOR_ATTACHMENT25 COLOR_ATTACHMENT25}</td><td>{@link GL30#GL_COLOR_ATTACHMENT26 COLOR_ATTACHMENT26}</td><td>{@link GL30#GL_COLOR_ATTACHMENT27 COLOR_ATTACHMENT27}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT28 COLOR_ATTACHMENT28}</td><td>{@link GL30#GL_COLOR_ATTACHMENT29 COLOR_ATTACHMENT29}</td><td>{@link GL30#GL_COLOR_ATTACHMENT30 COLOR_ATTACHMENT30}</td><td>{@link GL30#GL_COLOR_ATTACHMENT31 COLOR_ATTACHMENT31}</td></tr><tr><td>{@link GL30#GL_DEPTH_ATTACHMENT DEPTH_ATTACHMENT}</td><td>{@link GL30#GL_STENCIL_ATTACHMENT STENCIL_ATTACHMENT}</td><td>{@link GL30#GL_DEPTH_STENCIL_ATTACHMENT DEPTH_STENCIL_ATTACHMENT}</td></tr></table>
     * @param renderbuffertarget the renderbuffer target. Must be:<br><table><tr><td>{@link GL30#GL_RENDERBUFFER RENDERBUFFER}</td></tr></table>
     * @param renderbuffer       the name of an existing renderbuffer object of type {@code renderbuffertarget} to attach
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferRenderbuffer">Reference Page</a>
     */
    fun framebufferRenderbuffer(framebuffer: GlFramebuffer, attachment: Int, renderbuffer: GlRenderbuffer) =
            GL45C.glNamedFramebufferRenderbuffer(framebuffer.name, attachment, GL30C.GL_RENDERBUFFER, renderbuffer.name)

    // --- [ glNamedFramebufferParameteri ] ---

    /**
     * DSA version of {@link GL43C#glFramebufferParameteri FramebufferParameteri}.
     *
     * @param framebuffer the framebuffer name
     * @param name       a token indicating the parameter to be modified. One of:<br><table><tr><td>{@link GL43#GL_FRAMEBUFFER_DEFAULT_WIDTH FRAMEBUFFER_DEFAULT_WIDTH}</td><td>{@link GL43#GL_FRAMEBUFFER_DEFAULT_HEIGHT FRAMEBUFFER_DEFAULT_HEIGHT}</td></tr><tr><td>{@link GL43#GL_FRAMEBUFFER_DEFAULT_LAYERS FRAMEBUFFER_DEFAULT_LAYERS}</td><td>{@link GL43#GL_FRAMEBUFFER_DEFAULT_SAMPLES FRAMEBUFFER_DEFAULT_SAMPLES}</td></tr><tr><td>{@link GL43#GL_FRAMEBUFFER_DEFAULT_FIXED_SAMPLE_LOCATIONS FRAMEBUFFER_DEFAULT_FIXED_SAMPLE_LOCATIONS}</td></tr></table>
     * @param param       the new value for the parameter named {@code name}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferParameteri">Reference Page</a>
     */
    fun framebufferParameter(framebuffer: GlFramebuffer, name: FramebufferParameter, param: Int) =
            GL45C.glNamedFramebufferParameteri(framebuffer.name, name.i, param)

    // --- [ glNamedFramebufferTexture ] ---

    /**
     * DSA version of {@link GL32C#glFramebufferTexture FramebufferTexture}.
     *
     * @param framebuffer the framebuffer name
     * @param attachment  the attachment point of the framebuffer
     * @param texture     the texture object to attach to the framebuffer attachment point named by {@code attachment}
     * @param level       the mipmap level of {@code texture} to attach
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferTexture">Reference Page</a>
     */
    fun framebufferTexture(framebuffer: GlFramebuffer, attachment: Int, texture: GlTexture, level: Int) =
            GL45C.glNamedFramebufferTexture(framebuffer.name, attachment, texture.name, level)

    // --- [ glNamedFramebufferTextureLayer ] ---

    /**
     * DSA version of {@link GL30C#glFramebufferTextureLayer FramebufferTextureLayer}.
     *
     * @param framebuffer the framebuffer name
     * @param attachment  the attachment point of the framebuffer. One of:<br><table><tr><td>{@link GL30#GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td><td>{@link GL30#GL_COLOR_ATTACHMENT1 COLOR_ATTACHMENT1}</td><td>{@link GL30#GL_COLOR_ATTACHMENT2 COLOR_ATTACHMENT2}</td><td>{@link GL30#GL_COLOR_ATTACHMENT3 COLOR_ATTACHMENT3}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT4 COLOR_ATTACHMENT4}</td><td>{@link GL30#GL_COLOR_ATTACHMENT5 COLOR_ATTACHMENT5}</td><td>{@link GL30#GL_COLOR_ATTACHMENT6 COLOR_ATTACHMENT6}</td><td>{@link GL30#GL_COLOR_ATTACHMENT7 COLOR_ATTACHMENT7}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT8 COLOR_ATTACHMENT8}</td><td>{@link GL30#GL_COLOR_ATTACHMENT9 COLOR_ATTACHMENT9}</td><td>{@link GL30#GL_COLOR_ATTACHMENT10 COLOR_ATTACHMENT10}</td><td>{@link GL30#GL_COLOR_ATTACHMENT11 COLOR_ATTACHMENT11}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT12 COLOR_ATTACHMENT12}</td><td>{@link GL30#GL_COLOR_ATTACHMENT13 COLOR_ATTACHMENT13}</td><td>{@link GL30#GL_COLOR_ATTACHMENT14 COLOR_ATTACHMENT14}</td><td>{@link GL30#GL_COLOR_ATTACHMENT15 COLOR_ATTACHMENT15}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT16 COLOR_ATTACHMENT16}</td><td>{@link GL30#GL_COLOR_ATTACHMENT17 COLOR_ATTACHMENT17}</td><td>{@link GL30#GL_COLOR_ATTACHMENT18 COLOR_ATTACHMENT18}</td><td>{@link GL30#GL_COLOR_ATTACHMENT19 COLOR_ATTACHMENT19}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT20 COLOR_ATTACHMENT20}</td><td>{@link GL30#GL_COLOR_ATTACHMENT21 COLOR_ATTACHMENT21}</td><td>{@link GL30#GL_COLOR_ATTACHMENT22 COLOR_ATTACHMENT22}</td><td>{@link GL30#GL_COLOR_ATTACHMENT23 COLOR_ATTACHMENT23}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT24 COLOR_ATTACHMENT24}</td><td>{@link GL30#GL_COLOR_ATTACHMENT25 COLOR_ATTACHMENT25}</td><td>{@link GL30#GL_COLOR_ATTACHMENT26 COLOR_ATTACHMENT26}</td><td>{@link GL30#GL_COLOR_ATTACHMENT27 COLOR_ATTACHMENT27}</td></tr><tr><td>{@link GL30#GL_COLOR_ATTACHMENT28 COLOR_ATTACHMENT28}</td><td>{@link GL30#GL_COLOR_ATTACHMENT29 COLOR_ATTACHMENT29}</td><td>{@link GL30#GL_COLOR_ATTACHMENT30 COLOR_ATTACHMENT30}</td><td>{@link GL30#GL_COLOR_ATTACHMENT31 COLOR_ATTACHMENT31}</td></tr><tr><td>{@link GL30#GL_DEPTH_ATTACHMENT DEPTH_ATTACHMENT}</td><td>{@link GL30#GL_STENCIL_ATTACHMENT STENCIL_ATTACHMENT}</td><td>{@link GL30#GL_DEPTH_STENCIL_ATTACHMENT DEPTH_STENCIL_ATTACHMENT}</td></tr></table>
     * @param texture     the texture object to attach to the framebuffer attachment point named by {@code attachment}
     * @param level       the mipmap level of {@code texture} to attach
     * @param layer       the layer of {@code texture} to attach.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferTextureLayer">Reference Page</a>
     */
    fun framebufferTextureLayer(framebuffer: GlFramebuffer, attachment: Int, texture: GlTexture, level: Int, layer: Int) =
            GL45C.glNamedFramebufferTextureLayer(framebuffer.name, attachment, texture.name, level, layer)

    // --- [ glNamedFramebufferDrawBuffer ] ---

    /**
     * DSA version of {@link GL11C#glDrawBuffer DrawBuffer}.
     *
     * @param framebuffer the framebuffer name
     * @param buf         the color buffer to draw to. One of:<br><table><tr><td>{@link GL11#GL_NONE NONE}</td><td>{@link GL11#GL_FRONT_LEFT FRONT_LEFT}</td><td>{@link GL11#GL_FRONT_RIGHT FRONT_RIGHT}</td><td>{@link GL11#GL_BACK_LEFT BACK_LEFT}</td><td>{@link GL11#GL_BACK_RIGHT BACK_RIGHT}</td><td>{@link GL11#GL_FRONT FRONT}</td><td>{@link GL11#GL_BACK BACK}</td><td>{@link GL11#GL_LEFT LEFT}</td></tr><tr><td>{@link GL11#GL_RIGHT RIGHT}</td><td>{@link GL11#GL_FRONT_AND_BACK FRONT_AND_BACK}</td><td>{@link GL30#GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td><td>GL30.GL_COLOR_ATTACHMENT[1-15]</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferDrawBuffer">Reference Page</a>
     */
    fun framebufferDrawBuffer(framebuffer: GlFramebuffer, buf: Int) =
            GL45C.glNamedFramebufferDrawBuffer(framebuffer.name, buf)

    // --- [ glNamedFramebufferDrawBuffers ] ---

    /**
     * DSA version of {@link GL20C#glDrawBuffers DrawBuffers}.
     *
     * @param framebuffer the framebuffer name
     * @param bufs        an array of symbolic constants specifying the buffers into which fragment colors or data values will be written. One of:<br><table><tr><td>{@link GL11#GL_NONE NONE}</td><td>{@link GL11#GL_FRONT_LEFT FRONT_LEFT}</td><td>{@link GL11#GL_FRONT_RIGHT FRONT_RIGHT}</td><td>{@link GL11#GL_BACK_LEFT BACK_LEFT}</td><td>{@link GL11#GL_BACK_RIGHT BACK_RIGHT}</td><td>{@link GL30#GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td></tr><tr><td>GL30.GL_COLOR_ATTACHMENT[1-15]</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferDrawBuffers">Reference Page</a>
     */
    fun framebufferDrawBuffers(framebuffer: GlFramebuffer, bufs: IntBuffer) =
            GL45C.nglNamedFramebufferDrawBuffers(framebuffer.name, bufs.rem, bufs.adr)

    /**
     * DSA version of {@link GL20C#glDrawBuffers DrawBuffers}. TODO rename to framebufferDrawBuffer?
     *
     * @param framebuffer the framebuffer name
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferDrawBuffers">Reference Page</a>
     */
    fun framebufferDrawBuffers(framebuffer: GlFramebuffer, buf: Int) =
            stak.intAddress(buf) { GL45C.nglNamedFramebufferDrawBuffers(framebuffer.name, 1, it) }

    // --- [ glNamedFramebufferReadBuffer ] ---

    /**
     * DSA version of {@link GL11C#glReadBuffer ReadBuffer}.
     *
     * @param framebuffer the framebuffer name
     * @param src         the color buffer to read from. One of:<br><table><tr><td>{@link GL11#GL_NONE NONE}</td><td>{@link GL11#GL_FRONT_LEFT FRONT_LEFT}</td><td>{@link GL11#GL_FRONT_RIGHT FRONT_RIGHT}</td><td>{@link GL11#GL_BACK_LEFT BACK_LEFT}</td><td>{@link GL11#GL_BACK_RIGHT BACK_RIGHT}</td><td>{@link GL11#GL_FRONT FRONT}</td><td>{@link GL11#GL_BACK BACK}</td><td>{@link GL11#GL_LEFT LEFT}</td></tr><tr><td>{@link GL11#GL_RIGHT RIGHT}</td><td>{@link GL11#GL_FRONT_AND_BACK FRONT_AND_BACK}</td><td>{@link GL30#GL_COLOR_ATTACHMENT0 COLOR_ATTACHMENT0}</td><td>GL30.GL_COLOR_ATTACHMENT[1-15]</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glFramebufferReadBuffer">Reference Page</a>
     */
    fun framebufferReadBuffer(framebuffer: GlFramebuffer, src: Int) =
            GL45C.glNamedFramebufferReadBuffer(framebuffer.name, src)

    // --- [ glInvalidateNamedFramebufferData ] ---

    /**
     * DSA version of {@link GL43C#glInvalidateFramebuffer InvalidateFramebuffer}.
     *
     * @param framebuffer the framebuffer name
     * @param attachments the address of an array identifying the attachments to be invalidated
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glInvalidateFramebufferData">Reference Page</a>
     */
    fun invalidateFramebufferData(framebuffer: GlFramebuffer, attachments: IntBuffer) =
            GL45C.nglInvalidateNamedFramebufferData(framebuffer.name, attachments.rem, attachments.adr)

    /**
     * DSA version of {@link GL43C#glInvalidateFramebuffer InvalidateFramebuffer}.
     *
     * @param framebuffer the framebuffer name
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glInvalidateFramebufferData">Reference Page</a>
     */
    fun invalidateFramebufferData(framebuffer: GlFramebuffer, attachment: Int) =
            stak.intAddress(attachment) { GL45C.nglInvalidateNamedFramebufferData(framebuffer.name, 1, it) }

    // --- [ glInvalidateNamedFramebufferSubData ] ---

    /**
     * DSA version of {@link GL43C#glInvalidateSubFramebuffer InvalidateSubFramebuffer}.
     *
     * @param framebuffer the framebuffer name
     * @param attachments an array identifying the attachments to be invalidated
     * @param offset      the offset of the region to be invalidated
     * @param size        the size of the region to be invalidated
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glInvalidateFramebufferSubData">Reference Page</a>
     */
    fun invalidateFramebufferSubData(framebuffer: GlFramebuffer, attachments: IntBuffer, offset: Vec2i, size: Vec2i) =
            GL45C.nglInvalidateNamedFramebufferSubData(framebuffer.name, attachments.rem, attachments.adr, offset.x, offset.y, size.x, size.y)

    /**
     * DSA version of {@link GL43C#glInvalidateSubFramebuffer InvalidateSubFramebuffer}.
     *
     * @param framebuffer the framebuffer name
     * @param offset      the offset of the region to be invalidated
     * @param size        the size of the region to be invalidated
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glInvalidateFramebufferSubData">Reference Page</a>
     */
    fun invalidateFramebufferSubData(framebuffer: GlFramebuffer, attachment: Int, offset: Vec2i, size: Vec2i) =
            stak.intAddress(attachment) { GL45C.nglInvalidateNamedFramebufferSubData(framebuffer.name, 1, it, offset.x, offset.y, size.x, size.y) }

    // --- [ glClearNamedFramebufferiv ] ---

    /**
     * DSA version of {@link GL30C#glClearBufferiv ClearBufferiv}.
     *
     * @param framebuffer the framebuffer name
     * @param buffer      the buffer to clear. One of:<br><table><tr><td>{@link GL11#GL_COLOR COLOR}</td><td>{@link GL11#GL_STENCIL STENCIL}</td></tr></table>
     * @param drawBuffer  the draw buffer to clear
     * @param value       for color buffers, a pointer to a four-element vector specifying R, G, B and A values to clear the buffer to. For stencil buffers, a pointer to a
     *                    single stencil value to clear the buffer to.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClearFramebuffer">Reference Page</a>
     */
    fun clearFramebuffer(framebuffer: GlFramebuffer, buffer: BufferType, drawBuffer: Int, value: IntBuffer) =
            GL45C.nglClearNamedFramebufferiv(framebuffer.name, buffer.i, drawBuffer, value.adr)

    // --- [ glClearNamedFramebufferuiv ] ---

    /**
     * DSA version of {@link GL30C#glClearBufferuiv ClearBufferuiv}.
     *
     * @param framebuffer the framebuffer name
     * @param buffer      the buffer to clear. Must be:<br><table><tr><td>{@link GL11#GL_COLOR COLOR}</td></tr></table>
     * @param drawBuffer  the draw buffer to clear
     * @param value       a pointer to a four-element vector specifying R, G, B and A values to clear the buffer to
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClearFramebuffer">Reference Page</a>
     */
    fun clearFramebuffer(framebuffer: GlFramebuffer, buffer: BufferType, drawBuffer: Int, value: UintBuffer) =
            GL45C.nglClearNamedFramebufferuiv(framebuffer.name, buffer.i, drawBuffer, value.adr)

    // --- [ glClearNamedFramebufferfv ] ---

    /**
     * DSA version of {@link GL30C#glClearBufferfv ClearBufferfv}.
     *
     * @param framebuffer the framebuffer name
     * @param buffer      the buffer to clear. One of:<br><table><tr><td>{@link GL11#GL_COLOR COLOR}</td><td>{@link GL11#GL_DEPTH DEPTH}</td></tr></table>
     * @param drawbuffer  the draw buffer to clear
     * @param value       for color buffers, a pointer to a four-element vector specifying R, G, B and A values to clear the buffer to. For depth buffers, a pointer to a
     *                    single depth value to clear the buffer to.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClearFramebuffer">Reference Page</a>
     */
    fun clearFramebuffer(framebuffer: GlFramebuffer, buffer: BufferType, drawbuffer: Int, value: FloatBuffer) =
            GL45C.nglClearNamedFramebufferfv(framebuffer.name, buffer.i, drawbuffer, value.adr)

    // --- [ glClearNamedFramebufferfi ] ---

    /**
     * DSA version of {@link GL30C#glClearBufferfi ClearBufferfi}.
     *
     * @param framebuffer the framebuffer name
     * @param depth       the depth value to clear the buffer to
     * @param stencil     the stencil value to clear the buffer to
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glClearFramebufferfi">Reference Page</a>
     */
    fun clearFramebufferDepthStencil(framebuffer: GlFramebuffer, depth: Float, stencil: Int) =
            GL45C.glClearNamedFramebufferfi(framebuffer.name, GL30C.GL_DEPTH_STENCIL, 0, depth, stencil)

    // --- [ glBlitNamedFramebuffer ] ---

    /**
     * DSA version of {@link GL30C#glBlitFramebuffer BlitFramebuffer}.
     *
     * @param readFramebuffer the source framebuffer name
     * @param drawFramebuffer the destination framebuffer name
     * @param src             the coordinate of the source rectangle within the read buffer [lower-left, upper-left, lower-right, upper-right]
     * @param dst             the coordinate of the destination rectangle within the write buffer [lower-left, upper-left, lower-right, upper-right]
     * @param mask            the bitwise OR of the flags indicating which buffers are to be copied. One of:<br><table><tr><td>{@link GL11#GL_COLOR_BUFFER_BIT COLOR_BUFFER_BIT}</td><td>{@link GL11#GL_DEPTH_BUFFER_BIT DEPTH_BUFFER_BIT}</td><td>{@link GL11#GL_STENCIL_BUFFER_BIT STENCIL_BUFFER_BIT}</td></tr></table>
     * @param filter          the interpolation to be applied if the image is stretched. One of:<br><table><tr><td>{@link GL11#GL_NEAREST NEAREST}</td><td>{@link GL11#GL_LINEAR LINEAR}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBlitFramebuffer">Reference Page</a>
     */
    fun blitFramebuffer(readFramebuffer: GlFramebuffer, drawFramebuffer: GlFramebuffer, src: Vec4i, dst: Vec4i, mask: AttribMask, filter: MagFilter) =
            GL45C.glBlitNamedFramebuffer(readFramebuffer.name, drawFramebuffer.name, src.x, src.y, src.z, src.w, dst.x, dst.y, dst.z, dst.w, mask.i, filter.i)

    // --- [ glCheckNamedFramebufferStatus ] ---

    /**
     * DSA version of {@link GL30C#glCheckFramebufferStatus CheckFramebufferStatus}.
     *
     * @param framebuffer the framebuffer name
     * @param target      the target of the framebuffer completeness check. One of:<br><table><tr><td>{@link GL30#GL_FRAMEBUFFER FRAMEBUFFER}</td><td>{@link GL30#GL_READ_FRAMEBUFFER READ_FRAMEBUFFER}</td><td>{@link GL30#GL_DRAW_FRAMEBUFFER DRAW_FRAMEBUFFER}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCheckFramebufferStatus">Reference Page</a>
     */
    fun checkFramebufferStatus(framebuffer: GlFramebuffer, target: FramebufferTarget): FramebufferStatus =
            FramebufferStatus(GL45C.glCheckNamedFramebufferStatus(framebuffer.name, target.i))

    // --- [ glGetNamedFramebufferParameteriv ] ---
    // inline reified

    // --- [ glGetNamedFramebufferAttachmentParameteriv ] ---
    // inline reified

    // --- [ glCreateRenderbuffers ] ---

    /**
     * Returns {@code n} previously unused renderbuffer names in {@code renderbuffers}, each representing a new renderbuffer object.
     *
     * @param renderbuffers the buffer in which to store the created renderbuffer names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateRenderbuffers">Reference Page</a>
     */
    fun createRenderbuffers(renderbuffers: GlRenderbuffers) =
            GL45C.nglCreateRenderbuffers(renderbuffers.rem, renderbuffers.adr)

    /**
     * Returns {@code n} previously unused renderbuffer names in {@code renderbuffers}, each representing a new renderbuffer object.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateRenderbuffers">Reference Page</a>
     */
    fun createRenderbuffers(): GlRenderbuffer =
            GlRenderbuffer(stak.intAddress { GL45C.nglCreateRenderbuffers(1, it) })

    // --- [ glNamedRenderbufferStorage ] ---

    /**
     * DSA version of {@link GL30C#glRenderbufferStorage RenderbufferStorage}.
     *
     * @param internalFormat the internal format to use for the renderbuffer object's image. Must be a color-renderable, depth-renderable, or stencil-renderable format.
     * @param size           the size of the renderbuffer, in pixels
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glRenderbufferStorage">Reference Page</a>
     */
    fun renderbufferStorage(renderbuffer: GlRenderbuffer, internalFormat: Int, size: Vec2i) =
            GL45C.glNamedRenderbufferStorage(renderbuffer.name, internalFormat, size.x, size.y)

    // --- [ glNamedRenderbufferStorageMultisample ] ---

    /**
     * DSA version of {@link GL30C#glRenderbufferStorageMultisample RenderbufferStorageMultisample}.
     *
     * @param samples        the number of samples to be used for the renderbuffer object's storage
     * @param internalFormat the internal format to use for the renderbuffer object's image. Must be a color-renderable, depth-renderable, or stencil-renderable format.
     * @param width          the width of the renderbuffer, in pixels
     * @param height         the height of the renderbuffer, in pixels
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glRenderbufferStorageMultisample">Reference Page</a>
     */
    fun renderbufferStorageMS(renderbuffer: GlRenderbuffer, samples: Int, internalFormat: Int, size: Vec2i) =
            GL45C.glNamedRenderbufferStorageMultisample(renderbuffer.name, samples, internalFormat, size.x, size.y)

    // --- [ glGetNamedRenderbufferParameteriv ] ---

    /**
     * DSA version of {@link GL30C#glGetRenderbufferParameteriv GetRenderbufferParameteriv}.
     *
     * @param name the parameter whose value to retrieve from the renderbuffer bound to {@code target}. One of:<br><table><tr><td>{@link GL30#GL_RENDERBUFFER_WIDTH RENDERBUFFER_WIDTH}</td><td>{@link GL30#GL_RENDERBUFFER_HEIGHT RENDERBUFFER_HEIGHT}</td><td>{@link GL30#GL_RENDERBUFFER_INTERNAL_FORMAT RENDERBUFFER_INTERNAL_FORMAT}</td></tr><tr><td>{@link GL30#GL_RENDERBUFFER_RED_SIZE RENDERBUFFER_RED_SIZE}</td><td>{@link GL30#GL_RENDERBUFFER_GREEN_SIZE RENDERBUFFER_GREEN_SIZE}</td><td>{@link GL30#GL_RENDERBUFFER_BLUE_SIZE RENDERBUFFER_BLUE_SIZE}</td></tr><tr><td>{@link GL30#GL_RENDERBUFFER_ALPHA_SIZE RENDERBUFFER_ALPHA_SIZE}</td><td>{@link GL30#GL_RENDERBUFFER_DEPTH_SIZE RENDERBUFFER_DEPTH_SIZE}</td><td>{@link GL30#GL_RENDERBUFFER_STENCIL_SIZE RENDERBUFFER_STENCIL_SIZE}</td></tr><tr><td>{@link GL30#GL_RENDERBUFFER_SAMPLES RENDERBUFFER_SAMPLES}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetRenderbufferParameter">Reference Page</a>
     */
    fun getRenderbufferParameter(renderbuffer: GlRenderbuffer, name: GetRenderbuffer): Int =
            stak.intAddress { GL45C.nglGetNamedRenderbufferParameteriv(renderbuffer.name, name.i, it) }

    // --- [ glCreateTextures ] ---

    /**
     * Returns {@code n} previously unused texture names in {@code textures}, each representing a new texture object.
     *
     * @param target   the texture target. One of:<br><table><tr><td>{@link GL11#GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link GL11#GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL40#GL_TEXTURE_CUBE_MAP_ARRAY TEXTURE_CUBE_MAP_ARRAY}</td><td>{@link GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE TEXTURE_2D_MULTISAMPLE}</td></tr><tr><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE_ARRAY TEXTURE_2D_MULTISAMPLE_ARRAY}</td></tr></table>
     * @param textures the buffer in which to store the created texture names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateTextures">Reference Page</a>
     */
    fun createTextures(target: TextureTarget, textures: GlTextures) =
            GL45C.nglCreateTextures(target.i, textures.rem, textures.adr)

    /**
     * Returns {@code n} previously unused texture names in {@code textures}, each representing a new texture object.
     *
     * @param target the texture target. One of:<br><table><tr><td>{@link GL11#GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link GL11#GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}</td></tr><tr><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL40#GL_TEXTURE_CUBE_MAP_ARRAY TEXTURE_CUBE_MAP_ARRAY}</td><td>{@link GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE TEXTURE_2D_MULTISAMPLE}</td></tr><tr><td>{@link GL32#GL_TEXTURE_2D_MULTISAMPLE_ARRAY TEXTURE_2D_MULTISAMPLE_ARRAY}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateTextures">Reference Page</a>
     */
    fun createTextures(target: TextureTarget): GlTexture =
            GlTexture(stak.intAddress { GL45C.nglCreateTextures(target.i, 1, it) })

    // --- [ glTextureBuffer ] ---

    /**
     * DSA version of {@link GL31C#glTexBuffer TexBuffer}.
     *
     * @param texture        the texture name
     * @param internalFormat the sized internal format of the data in the store belonging to {@code buffer}
     * @param buffer         the name of the buffer object whose storage to attach to the active buffer texture
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureBuffer">Reference Page</a>
     */
    fun texBuffer(texture: GlTexture, internalFormat: InternalFormat, buffer: GlBuffer) =
            GL45C.glTextureBuffer(texture.name, internalFormat.i, buffer.name)

    // --- [ glTextureBufferRange ] ---

    /**
     * DSA version of {@link GL43C#glTexBufferRange TexBufferRange}.
     *
     * @param texture        the texture name
     * @param internalFormat the internal format of the data in the store belonging to {@code buffer}
     * @param buffer         the name of the buffer object whose storage to attach to the active buffer texture
     * @param offset         the offset of the start of the range of the buffer's data store to attach
     * @param size           the size of the range of the buffer's data store to attach
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureBufferRange">Reference Page</a>
     */
    fun texBufferRange(texture: GlTexture, internalFormat: InternalFormat, buffer: GlBuffer, offset: Int, size: Int) =
            GL45C.glTextureBufferRange(texture.name, internalFormat.i, buffer.name, offset.L, size.L)

    // --- [ glTextureStorage1D ] ---

    /**
     * DSA version of {@link GL42C#glTexStorage1D TexStorage1D}.
     *
     * @param texture        the texture name
     * @param levels         the number of texture levels
     * @param internalFormat the sized internal format to be used to store texture image data
     * @param width          the width of the texture, in texels
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureStorage1D">Reference Page</a>
     */
    fun texStorage1D(texture: GlTexture, levels: Int, internalFormat: gli_.gl.InternalFormat, width: Int) =
            GL45C.glTextureStorage1D(texture.name, levels, internalFormat.i, width)

    // --- [ glTextureStorage2D ] ---

    /**
     * DSA version of {@link GL42C#glTexStorage2D TexStorage2D}.
     *
     * @param texture        the texture name
     * @param levels         the number of texture levels
     * @param internalFormat the sized internal format to be used to store texture image data
     * @param size           the size of the texture, in texels
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureStorage2D">Reference Page</a>
     */
    fun texStorage2D(texture: GlTexture, levels: Int, internalFormat: gli_.gl.InternalFormat, size: Vec2i) =
            GL45C.glTextureStorage2D(texture.name, levels, internalFormat.i, size.x, size.y)

    // --- [ glTextureStorage3D ] ---

    /**
     * DSA version of {@link GL42C#glTexStorage3D TexStorage3D}.
     *
     * @param texture        the texture name
     * @param levels         the number of texture levels
     * @param internalFormat the sized internal format to be used to store texture image data
     * @param size           the size of the texture, in texels
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureStorage3D">Reference Page</a>
     */
    fun texStorage3D(texture: GlTexture, levels: Int, internalFormat: gli_.gl.InternalFormat, size: Vec3i) =
            GL45C.glTextureStorage3D(texture.name, levels, internalFormat.i, size.x, size.y, size.z)

    // --- [ glTextureStorage2DMultisample ] ---

    /**
     * DSA version of {@link GL43C#glTexStorage2DMultisample TexStorage2DMultisample}.
     *
     * @param texture              the texture name
     * @param samples              the number of samples in the texture
     * @param internalFormat       the sized internal format to be used to store texture image data
     * @param width                the width of the texture, in texels
     * @param height               the height of the texture, in texels
     * @param fixedSampleLocations whether the image will use identical sample locations and the same number of samples for all texels in the image, and the sample locations will not
     *                             depend on the internal format or size of the image
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureStorage2DMultisample">Reference Page</a>
     */
    fun texStorage2dMS(texture: GlTexture, samples: Int, internalFormat: gli_.gl.InternalFormat, size: Vec2i, fixedSampleLocations: Boolean) =
            GL45C.glTextureStorage2DMultisample(texture.name, samples, internalFormat.i, size.x, size.y, fixedSampleLocations)

    // --- [ glTextureStorage3DMultisample ] ---

    /**
     * DSA version of {@link GL43C#glTexStorage3DMultisample TexStorage3DMultisample}.
     *
     * @param texture              the texture name
     * @param samples              the number of samples in the texture
     * @param internalFormat       the sized internal format to be used to store texture image data
     * @param width                the width of the texture, in texels
     * @param height               the height of the texture, in texels
     * @param depth                the depth of the texture, in texels
     * @param fixedsamplelocations whether the image will use identical sample locations and the same number of samples for all texels in the image, and the sample locations will not
     *                             depend on the internal format or size of the image
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureStorage3DMultisample">Reference Page</a>
     */
    fun texStorage3dMS(texture: GlTexture, samples: Int, internalFormat: gli_.gl.InternalFormat, size: Vec3i, fixedSampleLocations: Boolean) =
            GL45C.glTextureStorage3DMultisample(texture.name, samples, internalFormat.i, size.x, size.y, size.z, fixedSampleLocations)

    // --- [ glTextureSubImage1D ] --- TODO format and type?

    /**
     * DSA version of {@link GL11C#glTexSubImage1D TexSubImage1D}.
     *
     * @param texture the texture name
     * @param level   the level-of-detail-number
     * @param offset  the left coordinate of the texel subregion
     * @param width   the subregion width
     * @param format  the pixel data format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
     * @param type    the pixel data type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
     * @param pixels  the pixel data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureSubImage1D">Reference Page</a>
     */
    fun texSubImage1D(texture: GlTexture, level: Int, offset: Int, width: Int, format: Int, type: Int, pixels: Buffer) =
            GL45C.nglTextureSubImage1D(texture.name, level, offset, width, format, type, pixels.adr)

    // --- [ glTextureSubImage2D ] ---

    /**
     * DSA version of {@link GL11C#glTexSubImage2D TexSubImage2D}.
     *
     * @param texture the texture name
     * @param level   the level-of-detail-number
     * @param offset  the coordinate of the texel subregion [left, bottom]
     * @param size    the subregion size
     * @param format  the pixel data format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
     * @param type    the pixel data type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
     * @param pixels  the pixel data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureSubImage2D">Reference Page</a>
     */
    fun texSubImage2D(texture: GlTexture, level: Int, offset: Vec2i, size: Vec2i, format: Int, type: Int, pixels: Buffer) =
        GL45C.nglTextureSubImage2D(texture.name, level, offset.x, offset.y, size.x, size.y, format, type, pixels.adr)

    // --- [ glTextureSubImage3D ] ---

    /**
     * DSA version of {@link GL12C#glTexSubImage3D TexSubImage3D}.
     *
     * @param texture the texture name
     * @param level   the level-of-detail-number
     * @param offset  the coordinate of the texel subregion
     * @param size    the subregion size
     * @param format  the pixel data format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
     * @param type    the pixel data type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
     * @param pixels  the pixel data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureSubImage3D">Reference Page</a>
     */
    fun texSubImage3D(texture: GlTexture, level: Int, offset: Vec3i, size: Vec3i, format: Int, type: Int, pixels: Buffer) =
        GL45C.nglTextureSubImage3D(texture.name, level, offset.x, offset.y, offset.z, size.x, size.y, size.z, format, type, pixels.adr)

    // --- [ glCompressedTextureSubImage1D ] ---

    /**
     * DSA version of {@link GL13C#glCompressedTexSubImage1D CompressedTexSubImage1D}.
     *
     * @param texture the texture name
     * @param level   the level-of-detail number. Level 0 is the base image level. Level n is the nth mipmap reduction image.
     * @param offset a texel offset in the x direction within the texture array
     * @param width   the width of the texture subimage
     * @param format  the format of the compressed image data stored at address {@code data}. One of:<br><table><tr><td>{@link GL30#GL_COMPRESSED_RED_RGTC1 COMPRESSED_RED_RGTC1}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RED_RGTC1 COMPRESSED_SIGNED_RED_RGTC1}</td></tr><tr><td>{@link GL30#GL_COMPRESSED_RG_RGTC2 COMPRESSED_RG_RGTC2}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RG_RGTC2 COMPRESSED_SIGNED_RG_RGTC2}</td></tr><tr><td>{@link GL42#GL_COMPRESSED_RGBA_BPTC_UNORM COMPRESSED_RGBA_BPTC_UNORM}</td><td>{@link GL42#GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM COMPRESSED_SRGB_ALPHA_BPTC_UNORM}</td></tr><tr><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT COMPRESSED_RGB_BPTC_SIGNED_FLOAT}</td><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGB8_ETC2 COMPRESSED_RGB8_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ETC2 COMPRESSED_SRGB8_ETC2}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGBA8_ETC2_EAC COMPRESSED_RGBA8_ETC2_EAC}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC COMPRESSED_SRGB8_ALPHA8_ETC2_EAC}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_R11_EAC COMPRESSED_R11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_R11_EAC COMPRESSED_SIGNED_R11_EAC}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RG11_EAC COMPRESSED_RG11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_RG11_EAC COMPRESSED_SIGNED_RG11_EAC}</td></tr><tr><td>see {@link EXTTextureCompressionS3TC}</td><td>see {@link EXTTextureCompressionLATC}</td></tr><tr><td>see {@link ATITextureCompression3DC}</td><td>see {@link KHRTextureCompressionASTCLDR}</td></tr></table>
     * @param data    a pointer to the compressed image data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCompressedTextureSubImage1D">Reference Page</a>
     */
    fun compressedTexSubImage1D(texture: GlTexture, level: Int, offset: Int, width: Int, format: Int, data: ByteBuffer) =
        GL45C.nglCompressedTextureSubImage1D(texture.name, level, offset, width, format, data.rem, data.adr)

    // --- [ glCompressedTextureSubImage2D ] ---

    /**
     * DSA version of {@link GL13C#glCompressedTexSubImage2D CompressedTexSubImage2D}.
     *
     * @param texture the texture name
     * @param level   the level-of-detail number. Level 0 is the base image level. Level n is the nth mipmap reduction image.
     * @param offset  a texel offset in the x, y direction within the texture array
     * @param size    the size of the texture subimage
     * @param height  the height of the texture subimage
     * @param format  the format of the compressed image data stored at address {@code data}. One of:<br><table><tr><td>{@link GL30#GL_COMPRESSED_RED_RGTC1 COMPRESSED_RED_RGTC1}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RED_RGTC1 COMPRESSED_SIGNED_RED_RGTC1}</td></tr><tr><td>{@link GL30#GL_COMPRESSED_RG_RGTC2 COMPRESSED_RG_RGTC2}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RG_RGTC2 COMPRESSED_SIGNED_RG_RGTC2}</td></tr><tr><td>{@link GL42#GL_COMPRESSED_RGBA_BPTC_UNORM COMPRESSED_RGBA_BPTC_UNORM}</td><td>{@link GL42#GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM COMPRESSED_SRGB_ALPHA_BPTC_UNORM}</td></tr><tr><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT COMPRESSED_RGB_BPTC_SIGNED_FLOAT}</td><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGB8_ETC2 COMPRESSED_RGB8_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ETC2 COMPRESSED_SRGB8_ETC2}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGBA8_ETC2_EAC COMPRESSED_RGBA8_ETC2_EAC}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC COMPRESSED_SRGB8_ALPHA8_ETC2_EAC}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_R11_EAC COMPRESSED_R11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_R11_EAC COMPRESSED_SIGNED_R11_EAC}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RG11_EAC COMPRESSED_RG11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_RG11_EAC COMPRESSED_SIGNED_RG11_EAC}</td></tr><tr><td>see {@link EXTTextureCompressionS3TC}</td><td>see {@link EXTTextureCompressionLATC}</td></tr><tr><td>see {@link ATITextureCompression3DC}</td><td>see {@link KHRTextureCompressionASTCLDR}</td></tr></table>
     * @param data    a pointer to the compressed image data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCompressedTextureSubImage2D">Reference Page</a>
     */
    fun compressedTexSubImage2D(texture: GlTexture, level: Int, offset: Vec2i, size: Vec2i, format: Int, data: ByteBuffer) =
        GL45C.nglCompressedTextureSubImage2D(texture.name, level, offset.x, offset.y, size.x, size.y, format, data.rem, data.adr)

    // --- [ glCompressedTextureSubImage3D ] ---

    /**
     * DSA version of {@link GL13C#glCompressedTexSubImage3D CompressedTexSubImage3D}.
     *
     * @param texture the texture name
     * @param level   the level-of-detail number. Level 0 is the base image level. Level n is the nth mipmap reduction image.
     * @param offset  a texel offset in the x, y, z direction within the texture array
     * @param size    the size of the texture subimage
     * @param format  the format of the compressed image data stored at address {@code data}. One of:<br><table><tr><td>{@link GL30#GL_COMPRESSED_RED_RGTC1 COMPRESSED_RED_RGTC1}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RED_RGTC1 COMPRESSED_SIGNED_RED_RGTC1}</td></tr><tr><td>{@link GL30#GL_COMPRESSED_RG_RGTC2 COMPRESSED_RG_RGTC2}</td><td>{@link GL30#GL_COMPRESSED_SIGNED_RG_RGTC2 COMPRESSED_SIGNED_RG_RGTC2}</td></tr><tr><td>{@link GL42#GL_COMPRESSED_RGBA_BPTC_UNORM COMPRESSED_RGBA_BPTC_UNORM}</td><td>{@link GL42#GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM COMPRESSED_SRGB_ALPHA_BPTC_UNORM}</td></tr><tr><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT COMPRESSED_RGB_BPTC_SIGNED_FLOAT}</td><td>{@link GL42#GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGB8_ETC2 COMPRESSED_RGB8_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ETC2 COMPRESSED_SRGB8_ETC2}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RGBA8_ETC2_EAC COMPRESSED_RGBA8_ETC2_EAC}</td><td>{@link GL43#GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC COMPRESSED_SRGB8_ALPHA8_ETC2_EAC}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_R11_EAC COMPRESSED_R11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_R11_EAC COMPRESSED_SIGNED_R11_EAC}</td></tr><tr><td>{@link GL43#GL_COMPRESSED_RG11_EAC COMPRESSED_RG11_EAC}</td><td>{@link GL43#GL_COMPRESSED_SIGNED_RG11_EAC COMPRESSED_SIGNED_RG11_EAC}</td></tr><tr><td>see {@link EXTTextureCompressionS3TC}</td><td>see {@link EXTTextureCompressionLATC}</td></tr><tr><td>see {@link ATITextureCompression3DC}</td><td>see {@link KHRTextureCompressionASTCLDR}</td></tr></table>
     * @param data    a pointer to the compressed image data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCompressedTextureSubImage3D">Reference Page</a>
     */
    fun compressedTexSubImage3D(texture: GlTexture, level: Int, offset: Vec3i, size: Vec3i, format: Int, data: ByteBuffer) =
        GL45C.nglCompressedTextureSubImage3D(texture.name, level, offset.x, offset.y, offset.z, size.x, size.y, size.z, format, data.rem, data.adr)

    // --- [ glCopyTextureSubImage1D ] ---

    /**
     * DSA version of {@link GL11C#glCopyTexSubImage1D CopyTexSubImage1D}.
     *
     * @param texture           the texture name
     * @param level             the level-of-detail number
     * @param xoffset           the left texel coordinate of the texture subregion to update
     * @param leftLowerPixel    the leftLower framebuffer pixel coordinate
     * @param width             the texture subregion width
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCopyTextureSubImage1D">Reference Page</a>
     */
    fun copyTexSubImage1D(texture: GlTexture, level: Int, offset: Int, leftLowerPixel: Vec2i, width: Int) =
            GL45C.glCopyTexSubImage1D(texture.name, level, offset, leftLowerPixel.x, leftLowerPixel.y, width)

    // --- [ glCopyTextureSubImage2D ] ---

    /**
     * DSA version of {@link GL11C#glCopyTexSubImage2D CopyTexSubImage2D}.
     *
     * @param texture           the texture name
     * @param level             the level-of-detail number
     * @param offset            the left-lower texel coordinate of the texture subregion to update
     * @param leftLowerPixel    the left-lower framebuffer pixel coordinate
     * @param size              the texture subregion size
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCopyTextureSubImage2D">Reference Page</a>
     */
    fun copyTexSubImage2D(texture: GlTexture, level: Int, offset: Vec2i, leftLowerPixel: Vec2i, size: Vec2i) =
            GL45C.glCopyTextureSubImage2D(texture.name, level, offset.x, offset.y, leftLowerPixel.x, leftLowerPixel.y, size.x, size.y)

    // --- [ glCopyTextureSubImage3D ] ---

    /**
     * DSA version of {@link GL12C#glCopyTexSubImage3D CopyTexSubImage3D}.
     *
     * @param texture           the texture name
     * @param level             the level-of-detail number
     * @param offset            the x, y, z coordinate of the texture subregion to update
     * @param leftLowerPixel    the left framebuffer pixel coordinate
     * @param size              the texture subregion size
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCopyTextureSubImage3D">Reference Page</a>
     */
    fun copyTexSubImage3D(texture: GlTexture, level: Int, offset: Vec3i, leftLowerPixel: Vec2i, size: Vec2i) =
            GL45C.glCopyTextureSubImage3D(texture.name, level, offset.x, offset.y, offset.z, leftLowerPixel.x, leftLowerPixel.y, size.x, size.y)

    // --- [ glTextureParameterf ] ---

    /**
     * DSA version of {@link GL11C#glTexParameterf TexParameterf}.
     *
     * @param texture the texture name
     * @param name   the parameter to set
     * @param param   the parameter value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureParameterf">Reference Page</a>
     */
    fun texParameter(texture: GlTexture, name: TexParameter, param: Float) =
            GL45C.glTextureParameterf(texture.name, name.i, param)

//    // --- [ glTextureParameterfv ] --- TODO?
//
//    /** Unsafe version of: {@link #glTextureParameterfv TextureParameterfv} */
//    public static native void nglTextureParameterfv(int texture, int pname, long params);
//
//    /**
//     * DSA version of {@link GL11C#glTexParameterfv TexParameterfv}.
//     *
//     * @param texture the texture name
//     * @param pname   the parameter to set
//     * @param params  the parameter value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureParameter">Reference Page</a>
//     */
//    public static void glTextureParameterfv(@NativeType("GLuint") int texture, @NativeType("GLenum") int pname, @NativeType("GLfloat const *") FloatBuffer params) {
//        if (CHECKS) {
//            check(params, 4);
//        }
//        nglTextureParameterfv(texture, pname, memAddress(params));
//    }

    // --- [ glTextureParameteri ] ---

    /**
     * DSA version of {@link GL11C#glTexParameteri TexParameteri}.
     *
     * @param texture the texture name
     * @param name   the parameter to set. One of:<br><table><tr><td>{@link GL12#GL_TEXTURE_BASE_LEVEL TEXTURE_BASE_LEVEL}</td><td>{@link GL11#GL_TEXTURE_BORDER_COLOR TEXTURE_BORDER_COLOR}</td><td>{@link GL14#GL_TEXTURE_COMPARE_MODE TEXTURE_COMPARE_MODE}</td><td>{@link GL14#GL_TEXTURE_COMPARE_FUNC TEXTURE_COMPARE_FUNC}</td></tr><tr><td>{@link GL14#GL_TEXTURE_LOD_BIAS TEXTURE_LOD_BIAS}</td><td>{@link GL11#GL_TEXTURE_MAG_FILTER TEXTURE_MAG_FILTER}</td><td>{@link GL12#GL_TEXTURE_MAX_LEVEL TEXTURE_MAX_LEVEL}</td><td>{@link GL12#GL_TEXTURE_MAX_LOD TEXTURE_MAX_LOD}</td></tr><tr><td>{@link GL11#GL_TEXTURE_MIN_FILTER TEXTURE_MIN_FILTER}</td><td>{@link GL12#GL_TEXTURE_MIN_LOD TEXTURE_MIN_LOD}</td><td>{@link GL33#GL_TEXTURE_SWIZZLE_R TEXTURE_SWIZZLE_R}</td><td>{@link GL33#GL_TEXTURE_SWIZZLE_G TEXTURE_SWIZZLE_G}</td></tr><tr><td>{@link GL33#GL_TEXTURE_SWIZZLE_B TEXTURE_SWIZZLE_B}</td><td>{@link GL33#GL_TEXTURE_SWIZZLE_A TEXTURE_SWIZZLE_A}</td><td>{@link GL33#GL_TEXTURE_SWIZZLE_RGBA TEXTURE_SWIZZLE_RGBA}</td><td>{@link GL11#GL_TEXTURE_WRAP_S TEXTURE_WRAP_S}</td></tr><tr><td>{@link GL11#GL_TEXTURE_WRAP_T TEXTURE_WRAP_T}</td><td>{@link GL12#GL_TEXTURE_WRAP_R TEXTURE_WRAP_R}</td><td>{@link GL14#GL_DEPTH_TEXTURE_MODE DEPTH_TEXTURE_MODE}</td><td>{@link GL14#GL_GENERATE_MIPMAP GENERATE_MIPMAP}</td></tr></table>
     * @param param   the parameter value
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureParameteri">Reference Page</a>
     */
    fun texParameter(texture: GlTexture, name: TexParameter, param: Int) =
            GL45C.glTextureParameteri(texture.name, name.i, param)

    // --- [ glTextureParameterIiv ] ---

    /**
     * DSA version of {@link GL30C#glTexParameterIiv TexParameterIiv}.
     *
     * @param texture the texture name
     * @param name   the symbolic name of a single-valued texture parameter
     * @param param  the value of {@code name}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureParameter">Reference Page</a>
     */
    fun texParameterI(texture: GlTexture, name: TexParameter, param: Vec4i) =
            stak.vec4iAddress(param) { GL45C.nglTextureParameterIiv(texture.name, name.i, it) }

    // --- [ glTextureParameterIuiv ] ---

    /**
     * DSA version of {@link GL30C#glTexParameterIuiv TexParameterIuiv}.
     *
     * @param texture the texture name
     * @param name   the symbolic name of a single-valued texture parameter
     * @param param  the value of {@code name}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureParameter">Reference Page</a>
     */
    fun texParameterI(texture: GlTexture, name: TexParameter, param: Vec4ui) =
            stak.vec4uiAddress(param) { GL45C.nglTextureParameterIuiv(texture.name, name.i, it) }

//    // --- [ glTextureParameteriv ] ---
//
//    /** Unsafe version of: {@link #glTextureParameteriv TextureParameteriv} */
//    public static native void nglTextureParameteriv(int texture, int pname, long params);
//
//    /**
//     * DSA version of {@link GL11C#glTexParameteriv TexParameteriv}.
//     *
//     * @param texture the texture name
//     * @param pname   the parameter to set
//     * @param params  the parameter value
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureParameter">Reference Page</a>
//     */
//    public static void glTextureParameteriv(@NativeType("GLuint") int texture, @NativeType("GLenum") int pname, @NativeType("GLint const *") IntBuffer params) {
//        if (CHECKS) {
//            check(params, 4);
//        }
//        nglTextureParameteriv(texture, pname, memAddress(params));
//    }

    // --- [ glGenerateTextureMipmap ] ---

    /**
     * DSA version of {@link GL30C#glGenerateMipmap GenerateMipmap}.
     *
     * @param texture the texture name
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGenerateTextureMipmap">Reference Page</a>
     */
    fun generateMipmap(texture: GlTexture) = GL45C.glGenerateTextureMipmap(texture.name)

    // --- [ glBindTextureUnit ] ---

    /**
     * Binds an existing texture object to the texture unit numbered {@code unit}.
     *
     * <p>{@code texture} must be zero or the name of an existing texture object. When {@code texture} is the name of an existing texture object, that object is
     * bound to the target, in the corresponding texture unit, that was specified when the object was created. When {@code texture} is zero, each of the targets
     * enumerated at the beginning of this section is reset to its default texture for the corresponding texture image unit.</p>
     *
     * @param unit    the texture unit number
     * @param texture the texture name
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glBindTextureUnit">Reference Page</a>
     */
    fun bindTextureUnit(unit: TexUnit, texture: GlTexture) = GL45C.glBindTextureUnit(unit, texture.name)

    // --- [ glGetTextureImage ] ---

    /**
     * DSA version of {@link GL11C#glGetTexImage GetTexImage}.
     *
     * @param texture the texture name
     * @param level   the level-of-detail number
     * @param format  the pixel format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
     * @param type    the pixel type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
     * @param pixels  the buffer in which to place the returned data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTextureImage">Reference Page</a>
     */
    fun getTexImage(texture: GlTexture, level: Int, format: TextureFormat2, type: TextureType2, pixels: ByteBuffer) =
            GL45C.nglGetTextureImage(texture.name, level, format.i, type.i, pixels.rem, pixels.adr)

//    /** TODO?
//     * DSA version of {@link GL11C#glGetTexImage GetTexImage}.
//     *
//     * @param texture the texture name
//     * @param level   the level-of-detail number
//     * @param format  the pixel format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type    the pixel type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels  the buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTextureImage">Reference Page</a>
//     */
//    public static void glGetTextureImage(@NativeType("GLuint") int texture, @NativeType("GLint") int level, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") ShortBuffer pixels) {
//        nglGetTextureImage(texture, level, format, type, pixels.remaining() << 1, memAddress(pixels));
//    }
//
//    /**
//     * DSA version of {@link GL11C#glGetTexImage GetTexImage}.
//     *
//     * @param texture the texture name
//     * @param level   the level-of-detail number
//     * @param format  the pixel format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type    the pixel type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels  the buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTextureImage">Reference Page</a>
//     */
//    public static void glGetTextureImage(@NativeType("GLuint") int texture, @NativeType("GLint") int level, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") IntBuffer pixels) {
//        nglGetTextureImage(texture, level, format, type, pixels.remaining() << 2, memAddress(pixels));
//    }
//
//    /**
//     * DSA version of {@link GL11C#glGetTexImage GetTexImage}.
//     *
//     * @param texture the texture name
//     * @param level   the level-of-detail number
//     * @param format  the pixel format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type    the pixel type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels  the buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTextureImage">Reference Page</a>
//     */
//    public static void glGetTextureImage(@NativeType("GLuint") int texture, @NativeType("GLint") int level, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") FloatBuffer pixels) {
//        nglGetTextureImage(texture, level, format, type, pixels.remaining() << 2, memAddress(pixels));
//    }
//
//    /**
//     * DSA version of {@link GL11C#glGetTexImage GetTexImage}.
//     *
//     * @param texture the texture name
//     * @param level   the level-of-detail number
//     * @param format  the pixel format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type    the pixel type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param pixels  the buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTextureImage">Reference Page</a>
//     */
//    public static void glGetTextureImage(@NativeType("GLuint") int texture, @NativeType("GLint") int level, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") DoubleBuffer pixels) {
//        nglGetTextureImage(texture, level, format, type, pixels.remaining() << 3, memAddress(pixels));
//    }

    // --- [ glGetCompressedTextureImage ] ---

    /**
     * DSA version of {@link GL13C#glGetCompressedTexImage GetCompressedTexImage}.
     *
     * @param texture the texture name
     * @param level   the level-of-detail number. Level 0 is the base image level. Level n is the nth mipmap reduction image.
     * @param pixels  a buffer in which to return the compressed texture image
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetCompressedTextureImage">Reference Page</a>
     */
    fun getCompressedTexImage(texture: GlTexture, level: Int, pixels: ByteBuffer) =
            GL45C.nglGetCompressedTextureImage(texture.name, level, pixels.rem, pixels.adr)

    // --- [ glGetTextureLevelParameterfv ] ---
    // inline reified

    // --- [ glGetTextureLevelParameteriv ] ---
    // inline reified

    // --- [ glGetTextureParameterfv ] ---
    // inline reified

    // --- [ glGetTextureParameterIiv ] ---
    // inline reified

    // --- [ glGetTextureParameterIuiv ] ---
    // inline reified

    // --- [ glGetTextureParameteriv ] ---
    // inline reified

    // --- [ glCreateVertexArrays ] ---

    /**
     * Returns {@code n} previously unused vertex array object names in {@code arrays}.
     *
     * @param arrays the buffer in which to return the created vertex array object names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateVertexArrays">Reference Page</a>
     */
    infix fun createVertexArrays(arrays: GlVertexArrays) =
            GL45C.nglCreateVertexArrays(arrays.rem, arrays.adr)

    /**
     * Returns {@code n} previously unused vertex array object names in {@code arrays}.
     *
     * @param size the size of the created vertex array object names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateVertexArrays">Reference Page</a>
     */
    infix fun createVertexArrays(size: Int): GlVertexArrays = GlVertexArrays(size).apply(::createVertexArrays)

    /**
     * Returns {@code n} previously unused vertex array object names in {@code arrays}.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateVertexArrays">Reference Page</a>
     */
    fun createVertexArrays(): GlVertexArray =
            GlVertexArray(stak.intAddress { GL45C.nglCreateVertexArrays(1, it) })

    // TODO vertexArray* -> vertex*?

    // --- [ glDisableVertexArrayAttrib ] ---

    /**
     * DSA version of {@link GL20C#glDisableVertexAttribArray DisableVertexAttribArray}.
     *
     * @param vaobj the vertex array object name
     * @param index the index of the generic vertex attribute to be disabled
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glDisableVertexArrayAttrib">Reference Page</a>
     */
    fun disableVertexArrayAttrib(vaobj: GlVertexArray, index: VertexAttrIndex) =
            GL45C.glDisableVertexArrayAttrib(vaobj.name, index)

    // --- [ glEnableVertexArrayAttrib ] ---

    /**
     * DSA version of {@link GL20C#glEnableVertexAttribArray EnableVertexAttribArray}.
     *
     * @param vaobj the vertex array object name
     * @param index the index of the generic vertex attribute to be enabled
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glEnableVertexArrayAttrib">Reference Page</a>
     */
    fun enableVertexArrayAttrib(vaobj: GlVertexArray, index: VertexAttrIndex) =
            GL45C.glEnableVertexArrayAttrib(vaobj.name, index)

    // --- [ glVertexArrayElementBuffer ] ---

    /**
     * Binds a buffer object to the element array buffer bind point of a vertex array object.
     *
     * @param vaobj  the vertex array object name
     * @param buffer the buffer object name. If {@code buffer} is zero, any existing element array buffer binding to {@code vaobj} is removed.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexArrayElementBuffer">Reference Page</a>
     */
    fun vertexArrayElementBuffer(vaobj: GlVertexArray, buffer: GlBuffer) =
            GL45C.glVertexArrayElementBuffer(vaobj.name, buffer.name)

    // --- [ glVertexArrayVertexBuffer ] ---

    /**
     * DSA version of {@link GL43C#glBindVertexBuffer BindVertexBuffer}.
     *
     * @param vaobj        the vertex array object name
     * @param bindingIndex the index of the vertex buffer binding point to which to bind the buffer
     * @param buffer       the name of an existing buffer to bind to the vertex buffer binding point
     * @param offset       the offset of the first element of the buffer
     * @param stride       the distance between elements within the buffer
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexArrayVertexBuffer">Reference Page</a>
     */
    fun vertexArrayVertexBuffer(vaobj: GlVertexArray, bindingIndex: Int, buffer: GlBuffer, offset: Int, stride: Int) =
            GL45C.glVertexArrayVertexBuffer(vaobj.name, bindingIndex, buffer.name, offset.L, stride)

    // --- [ glVertexArrayVertexBuffers ] ---

    /**
     * DSA version of {@link GL44C#glBindVertexBuffers BindVertexBuffers}.
     *
     * @param vaobj   the vertex array object name
     * @param first   the first vertex buffer binding point
     * @param buffers an array of zeros or names of existing buffers objects
     * @param offsets an array of offses
     * @param strides an array of stride values
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexArrayVertexBuffers">Reference Page</a>
     */
    fun vertexArrayVertexBuffers(vaobj: GlVertexArray, first: Int, buffers: GlBuffers? = null, offsets: LongBuffer? = null, strides: IntBuffer? = null) =
            GL45C.nglVertexArrayVertexBuffers(vaobj.name, first, buffers?.rem ?: 0, buffers?.adr ?: NULL, offsets?.adr
                    ?: NULL, strides?.adr ?: NULL)

    // --- [ glVertexArrayAttribFormat ] ---

    /**
     * DSA version of {@link GL43C#glVertexAttribFormat VertexAttribFormat}.
     *
     * @param vaobj          the vertex array object name
     * @param attribIndex    the generic vertex attribute array being described
     * @param size           the number of values per vertex that are stored in the array. One of:<br><table><tr><td>1</td><td>2</td><td>3</td><td>4</td><td>{@link GL12#GL_BGRA BGRA}</td></tr></table>
     * @param type           the type of the data stored in the array
     * @param normalized     if true then integer data is normalized to the range [-1, 1] or [0, 1] if it is signed or unsigned, respectively. If false then integer data is
     *                       directly converted to floating point.
     * @param relativeOffset the offset, measured in basic machine units of the first element relative to the start of the vertex buffer binding this attribute fetches from
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexArrayAttribFormat">Reference Page</a>
     */
    fun vertexArrayAttribFormat(vaobj: GlVertexArray, attribIndex: VertexAttrIndex, size: Int, type: VertexAttrType, normalized: Boolean, relativeOffset: Int) =
            GL45C.glVertexArrayAttribFormat(vaobj.name, attribIndex, size, type.i, normalized, relativeOffset)

    // --- [ glVertexArrayAttribIFormat ] ---

    /**
     * DSA version of {@link GL43C#glVertexAttribIFormat VertexAttribIFormat}.
     *
     * @param vaobj          the vertex array object name
     * @param attribIndex    the generic vertex attribute array being described
     * @param size           the number of values per vertex that are stored in the array. One of:<br><table><tr><td>1</td><td>2</td><td>3</td><td>4</td><td>{@link GL12#GL_BGRA BGRA}</td></tr></table>
     * @param type           the type of the data stored in the array
     * @param relativeOffset the offset, measured in basic machine units of the first element relative to the start of the vertex buffer binding this attribute fetches from
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexArrayAttribIFormat">Reference Page</a>
     */
    fun vertexArrayAttribIFormat(vaobj: GlVertexArray, attribIndex: VertexAttrIndex, size: VertexAttrSize, type: VertexAttrType, relativeOffset: Int) =
            GL45C.glVertexArrayAttribIFormat(vaobj.name, attribIndex, size, type.i, relativeOffset)

    // --- [ glVertexArrayAttribLFormat ] ---

    /**
     * DSA version of {@link GL43C#glVertexAttribLFormat VertexAttribLFormat}.
     *
     * @param vaobj          the vertex array object name
     * @param attribIndex    the generic vertex attribute array being described
     * @param size           the number of values per vertex that are stored in the array. One of:<br><table><tr><td>1</td><td>2</td><td>3</td><td>4</td><td>{@link GL12#GL_BGRA BGRA}</td></tr></table>
     * @param type           the type of the data stored in the array
     * @param relativeOffset the offset, measured in basic machine units of the first element relative to the start of the vertex buffer binding this attribute fetches from
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexArrayAttribLFormat">Reference Page</a>
     */
    fun vertexArrayAttribLFormat(vaobj: GlVertexArray, attribIndex: VertexAttrIndex, size: VertexAttrSize, type: VertexAttrType, relativeOffset: Int) =
            GL45C.glVertexArrayAttribLFormat(vaobj.name, attribIndex, size, type.i, relativeOffset)

    // --- [ glVertexArrayAttribBinding ] ---

    /**
     * DSA version of {@link GL43C#glVertexAttribBinding VertexAttribBinding}.
     *
     * @param vaobj        the vertex array object name
     * @param attribIndex  the index of the attribute to associate with a vertex buffer binding
     * @param bindingIndex the index of the vertex buffer binding with which to associate the generic vertex attribute
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexArrayAttribBinding">Reference Page</a>
     */
    fun vertexArrayAttribBinding(vaobj: GlVertexArray, attribIndex: VertexAttrIndex, bindingIndex: Int) =
            GL45C.glVertexArrayAttribBinding(vaobj.name, attribIndex, bindingIndex)

    // --- [ glVertexArrayBindingDivisor ] ---

    /**
     * DSA version of {@link GL43C#glVertexBindingDivisor VertexBindingDivisor}.
     *
     * @param vaobj        the vertex array object name
     * @param bindingIndex the index of the generic vertex attribute
     * @param divisor      the number of instances that will pass between updates of the generic attribute at slot {@code index}
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glVertexArrayBindingDivisor">Reference Page</a>
     */
    fun vertexArrayBindingDivisor(vaobj: GlVertexArray, bindingIndex: Int, divisor: Int) =
            GL45C.glVertexArrayBindingDivisor(vaobj.name, bindingIndex, divisor)

    // --- [ glGetVertexArrayiv ] ---

    /**
     * Queries parameters of a vertex array object.
     *
     * @param vaobj the vertex array object name
     * @param pname the parameter to query. Must be:<br><table><tr><td>{@link GL15#GL_ELEMENT_ARRAY_BUFFER_BINDING ELEMENT_ARRAY_BUFFER_BINDING}</td></tr></table>
     * @param param the buffer in which to return the parameter values
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetVertexArray">Reference Page</a>
     */
    fun getVertexArrayElementBuffer(vaobj: GlVertexArray): GlBuffer =
            GlBuffer(stak.intAddress { GL45C.nglGetVertexArrayiv(vaobj.name, GL15C.GL_ELEMENT_ARRAY_BUFFER_BINDING, it) })

    // --- [ glGetVertexArrayIndexediv ] ---
    // inline reified

    // --- [ glGetVertexArrayIndexed64iv ] ---
    // inline reified

    // --- [ glCreateSamplers ] ---

    /**
     * Returns {@code n} previously unused sampler names in {@code samplers}, each representing a new sampler object.
     *
     * @param samplers the buffer in which to return the created sampler object names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateSamplers">Reference Page</a>
     */
    fun createSamplers(samplers: GlSamplers) = GL45C.nglCreateSamplers(samplers.rem, samplers.adr)

    /**
     * Returns {@code n} previously unused sampler names in {@code samplers}, each representing a new sampler object.
     *
     * @param size the size of the created sampler object names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateSamplers">Reference Page</a>
     */
    fun createSamplers(size: Int) = GlSamplers(size).apply(::createSamplers)

    /**
     * Returns {@code n} previously unused sampler names in {@code samplers}, each representing a new sampler object.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateSamplers">Reference Page</a>
     */
    fun createSamplers(): GlSampler =
            GlSampler(stak.intAddress { GL45C.nglCreateSamplers(1, it) })

    // --- [ glCreateProgramPipelines ] ---

    /**
     * Returns {@code n} previously unused program pipeline names in {@code pipelines}, each representing a new program pipeline object.
     *
     * @param pipelines the buffer in which to return the created program pipeline names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateProgramPipelines">Reference Page</a>
     */
    fun createProgramPipelines(pipelines: GlPipelines) =
            GL45C.nglCreateProgramPipelines(pipelines.rem, pipelines.adr)

    /**
     * Returns {@code n} previously unused program pipeline names in {@code pipelines}, each representing a new program pipeline object.
     *
     * @param size the size of the created program pipeline names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateProgramPipelines">Reference Page</a>
     */
    fun createProgramPipelines(size: Int) = GlPipelines(size).apply(::createProgramPipelines)

    /**
     * Returns {@code n} previously unused program pipeline names in {@code pipelines}, each representing a new program pipeline object.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateProgramPipelines">Reference Page</a>
     */
    fun createProgramPipelines(): GlPipeline =
            GlPipeline(stak.intAddress { GL45C.nglCreateProgramPipelines(1, it) })

    // --- [ glCreateQueries ] ---

    /**
     * Returns {@code n} previously unused query object names in {@code ids}, each representing a new query object with the specified {@code target}.
     *
     * @param target the query target. One of:<br><table><tr><td>{@link GL15#GL_SAMPLES_PASSED SAMPLES_PASSED}</td><td>{@link GL30#GL_PRIMITIVES_GENERATED PRIMITIVES_GENERATED}</td><td>{@link GL30#GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN}</td><td>{@link GL33#GL_TIME_ELAPSED TIME_ELAPSED}</td></tr><tr><td>{@link GL33#GL_TIMESTAMP TIMESTAMP}</td><td>{@link GL33#GL_ANY_SAMPLES_PASSED ANY_SAMPLES_PASSED}</td><td>{@link GL43#GL_ANY_SAMPLES_PASSED_CONSERVATIVE ANY_SAMPLES_PASSED_CONSERVATIVE}</td></tr></table>
     * @param ids    the buffer in which to return the created query object names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateQueries">Reference Page</a>
     */
    fun createQueries(target: QueryTarget, ids: GlQueries) =
            GL45C.nglCreateQueries(target.i, ids.rem, ids.adr)

    /**
     * Returns {@code n} previously unused query object names in {@code ids}, each representing a new query object with the specified {@code target}.
     *
     * @param target the query target. One of:<br><table><tr><td>{@link GL15#GL_SAMPLES_PASSED SAMPLES_PASSED}</td><td>{@link GL30#GL_PRIMITIVES_GENERATED PRIMITIVES_GENERATED}</td><td>{@link GL30#GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN}</td><td>{@link GL33#GL_TIME_ELAPSED TIME_ELAPSED}</td></tr><tr><td>{@link GL33#GL_TIMESTAMP TIMESTAMP}</td><td>{@link GL33#GL_ANY_SAMPLES_PASSED ANY_SAMPLES_PASSED}</td><td>{@link GL43#GL_ANY_SAMPLES_PASSED_CONSERVATIVE ANY_SAMPLES_PASSED_CONSERVATIVE}</td></tr></table>
     * @param size   the size of the created query object names
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateQueries">Reference Page</a>
     */
    fun createQueries(target: QueryTarget, size: Int) = GlQueries(size).also { createQueries(target, it) }

    /**
     * Returns {@code n} previously unused query object names in {@code ids}, each representing a new query object with the specified {@code target}.
     *
     * @param target the query target. One of:<br><table><tr><td>{@link GL15#GL_SAMPLES_PASSED SAMPLES_PASSED}</td><td>{@link GL30#GL_PRIMITIVES_GENERATED PRIMITIVES_GENERATED}</td><td>{@link GL30#GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN}</td><td>{@link GL33#GL_TIME_ELAPSED TIME_ELAPSED}</td></tr><tr><td>{@link GL33#GL_TIMESTAMP TIMESTAMP}</td><td>{@link GL33#GL_ANY_SAMPLES_PASSED ANY_SAMPLES_PASSED}</td><td>{@link GL43#GL_ANY_SAMPLES_PASSED_CONSERVATIVE ANY_SAMPLES_PASSED_CONSERVATIVE}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glCreateQueries">Reference Page</a>
     */
    infix fun createQueries(target: QueryTarget): GlQuery =
            GlQuery(stak.intAddress { GL45C.nglCreateQueries(target.i, 1, it) })

//    // --- [ glGetQueryBufferObjectiv ] --- TODO
//
//    /**
//     * Queries the state of a query object.
//     *
//     * @param id     the name of a query object
//     * @param buffer the name of a buffer object
//     * @param pname  the state to query
//     * @param offset the offset into {@code buffer} at which the queried value is written
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetQueryBufferObject">Reference Page</a>
//     */
//    public static native void glGetQueryBufferObjectiv(@NativeType("GLuint") int id, @NativeType("GLuint") int buffer, @NativeType("GLenum") int pname, @NativeType("GLintptr") long offset);
//
//    // --- [ glGetQueryBufferObjectuiv ] ---
//
//    /**
//     * Unsigned version of {@link #glGetQueryBufferObjectiv GetQueryBufferObjectiv}.
//     *
//     * @param id     the name of a query object
//     * @param buffer the name of a buffer object
//     * @param pname  the state to query
//     * @param offset the offset into {@code buffer} at which the queried value is written
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetQueryBufferObject">Reference Page</a>
//     */
//    public static native void glGetQueryBufferObjectuiv(@NativeType("GLuint") int id, @NativeType("GLuint") int buffer, @NativeType("GLenum") int pname, @NativeType("GLintptr") long offset);
//
//    // --- [ glGetQueryBufferObjecti64v ] ---
//
//    /**
//     * 64bit version of {@link #glGetQueryBufferObjectiv GetQueryBufferObjectiv}.
//     *
//     * @param id     the name of a query object
//     * @param buffer the name of a buffer object
//     * @param pname  the state to query
//     * @param offset the offset into {@code buffer} at which the queried value is written
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetQueryBufferObject">Reference Page</a>
//     */
//    public static native void glGetQueryBufferObjecti64v(@NativeType("GLuint") int id, @NativeType("GLuint") int buffer, @NativeType("GLenum") int pname, @NativeType("GLintptr") long offset);
//
//    // --- [ glGetQueryBufferObjectui64v ] ---
//
//    /**
//     * 64bit version of {@link #glGetQueryBufferObjectuiv GetQueryBufferObjectuiv}.
//     *
//     * @param id     the name of a query object
//     * @param buffer the name of a buffer object
//     * @param pname  the state to query
//     * @param offset the offset into {@code buffer} at which the queried value is written
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetQueryBufferObject">Reference Page</a>
//     */
//    public static native void glGetQueryBufferObjectui64v(@NativeType("GLuint") int id, @NativeType("GLuint") int buffer, @NativeType("GLenum") int pname, @NativeType("GLintptr") long offset);

    // --- [ glMemoryBarrierByRegion ] ---

    /**
     * Behaves like {@link GL42C#glMemoryBarrier MemoryBarrier}, with two differences:
     *
     * <p>First, it narrows the region under consideration so that only reads/writes of prior fragment shaders that are invoked for a smaller region of the
     * framebuffer will be completed/reflected prior to subsequent reads/write of following fragment shaders. The size of the region is implementation
     * dependent and may be as small as one framebuffer pixel.</p>
     *
     * <p>Second, it only applies to memory transactions that may be read by or written by a fragment shader.</p>
     *
     * <p>When barriers is {@link GL42#GL_ALL_BARRIER_BITS ALL_BARRIER_BITS}, shader memory accesses will be synchronized relative to all these barrier bits, but not to other
     * barrier bits specific to {@link GL42C#glMemoryBarrier MemoryBarrier}. This implies that reads/writes for scatter/gather-like algorithms may or may not be
     * completed/reflected after a MemoryBarrierByRegion command. However, for uses such as deferred shading, where a linked list of visible
     * surfaces with the head at a framebuffer address may be constructed, and the entirety of the list is only dependent on previous executions at that
     * framebuffer address, MemoryBarrierByRegion may be significantly more efficient than {@link GL42C#glMemoryBarrier MemoryBarrier}.</p>
     *
     * @param barriers the barriers to insert. One or more of:<br><table><tr><td>{@link GL42#GL_ATOMIC_COUNTER_BARRIER_BIT ATOMIC_COUNTER_BARRIER_BIT}</td><td>{@link GL42#GL_FRAMEBUFFER_BARRIER_BIT FRAMEBUFFER_BARRIER_BIT}</td><td>{@link GL42#GL_SHADER_IMAGE_ACCESS_BARRIER_BIT SHADER_IMAGE_ACCESS_BARRIER_BIT}</td></tr><tr><td>{@link GL43#GL_SHADER_STORAGE_BARRIER_BIT SHADER_STORAGE_BARRIER_BIT}</td><td>{@link GL42#GL_TEXTURE_FETCH_BARRIER_BIT TEXTURE_FETCH_BARRIER_BIT}</td><td>{@link GL42#GL_UNIFORM_BARRIER_BIT UNIFORM_BARRIER_BIT}</td></tr></table>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glMemoryBarrierByRegion">Reference Page</a>
     */
    fun memoryBarrierByRegion(barriers: Int) = GL45C.glMemoryBarrierByRegion(barriers) // TODO enum/inline class?

    // --- [ glGetTextureSubImage ] ---

    /**
     * Obtains sub-regions of a texture image from a texture object.
     *
     * @param texture the source texture object name
     * @param level   the level-of-detail number
     * @param offset  the position of the subregion
     * @param size    the subregion size
     * @param format  the pixel format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td><td>{@link GL11#GL_LUMINANCE LUMINANCE}</td><td>{@link GL11#GL_LUMINANCE_ALPHA LUMINANCE_ALPHA}</td></tr></table>
     * @param type    the pixel type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr><tr><td>{@link GL11#GL_BITMAP BITMAP}</td></tr></table>
     * @param pixels  the buffer in which to place the returned data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTextureSubImage">Reference Page</a>
     */
    fun getTextureSubImage(texture: GlTexture, level: Int, offset: Vec3i, size: Vec3i, format: TextureFormat3, type: TextureType2, pixels: ByteBuffer) =
            GL45C.nglGetTextureSubImage(texture.name, level, offset.x, offset.y, offset.z, size.x, size.y, size.z, format.i, type.i, pixels.rem, pixels.adr)

//    /** TODO?
//     * Obtains sub-regions of a texture image from a texture object.
//     *
//     * @param texture the source texture object name
//     * @param level   the level-of-detail number
//     * @param xoffset the x-position of the subregion
//     * @param yoffset the y-position of the subregion
//     * @param zoffset the z-position of the subregion
//     * @param width   the subregion width
//     * @param height  the subregion height
//     * @param depth   the subregion depth
//     * @param format  the pixel format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td><td>{@link GL11#GL_LUMINANCE LUMINANCE}</td><td>{@link GL11#GL_LUMINANCE_ALPHA LUMINANCE_ALPHA}</td></tr></table>
//     * @param type    the pixel type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr><tr><td>{@link GL11#GL_BITMAP BITMAP}</td></tr></table>
//     * @param pixels  the buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTextureSubImage">Reference Page</a>
//     */
//    public static void glGetTextureSubImage(@NativeType("GLuint") int texture, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLint") int yoffset, @NativeType("GLint") int zoffset, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLsizei") int depth, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") ShortBuffer pixels) {
//        nglGetTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels.remaining() << 1, memAddress(pixels));
//    }
//
//    /**
//     * Obtains sub-regions of a texture image from a texture object.
//     *
//     * @param texture the source texture object name
//     * @param level   the level-of-detail number
//     * @param xoffset the x-position of the subregion
//     * @param yoffset the y-position of the subregion
//     * @param zoffset the z-position of the subregion
//     * @param width   the subregion width
//     * @param height  the subregion height
//     * @param depth   the subregion depth
//     * @param format  the pixel format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td><td>{@link GL11#GL_LUMINANCE LUMINANCE}</td><td>{@link GL11#GL_LUMINANCE_ALPHA LUMINANCE_ALPHA}</td></tr></table>
//     * @param type    the pixel type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr><tr><td>{@link GL11#GL_BITMAP BITMAP}</td></tr></table>
//     * @param pixels  the buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTextureSubImage">Reference Page</a>
//     */
//    public static void glGetTextureSubImage(@NativeType("GLuint") int texture, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLint") int yoffset, @NativeType("GLint") int zoffset, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLsizei") int depth, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") IntBuffer pixels) {
//        nglGetTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels.remaining() << 2, memAddress(pixels));
//    }
//
//    /**
//     * Obtains sub-regions of a texture image from a texture object.
//     *
//     * @param texture the source texture object name
//     * @param level   the level-of-detail number
//     * @param xoffset the x-position of the subregion
//     * @param yoffset the y-position of the subregion
//     * @param zoffset the z-position of the subregion
//     * @param width   the subregion width
//     * @param height  the subregion height
//     * @param depth   the subregion depth
//     * @param format  the pixel format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td><td>{@link GL11#GL_LUMINANCE LUMINANCE}</td><td>{@link GL11#GL_LUMINANCE_ALPHA LUMINANCE_ALPHA}</td></tr></table>
//     * @param type    the pixel type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr><tr><td>{@link GL11#GL_BITMAP BITMAP}</td></tr></table>
//     * @param pixels  the buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTextureSubImage">Reference Page</a>
//     */
//    public static void glGetTextureSubImage(@NativeType("GLuint") int texture, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLint") int yoffset, @NativeType("GLint") int zoffset, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLsizei") int depth, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") FloatBuffer pixels) {
//        nglGetTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels.remaining() << 2, memAddress(pixels));
//    }
//
//    /**
//     * Obtains sub-regions of a texture image from a texture object.
//     *
//     * @param texture the source texture object name
//     * @param level   the level-of-detail number
//     * @param xoffset the x-position of the subregion
//     * @param yoffset the y-position of the subregion
//     * @param zoffset the z-position of the subregion
//     * @param width   the subregion width
//     * @param height  the subregion height
//     * @param depth   the subregion depth
//     * @param format  the pixel format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td><td>{@link GL11#GL_LUMINANCE LUMINANCE}</td><td>{@link GL11#GL_LUMINANCE_ALPHA LUMINANCE_ALPHA}</td></tr></table>
//     * @param type    the pixel type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr><tr><td>{@link GL11#GL_BITMAP BITMAP}</td></tr></table>
//     * @param pixels  the buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetTextureSubImage">Reference Page</a>
//     */
//    public static void glGetTextureSubImage(@NativeType("GLuint") int texture, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLint") int yoffset, @NativeType("GLint") int zoffset, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLsizei") int depth, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") DoubleBuffer pixels) {
//        nglGetTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels.remaining() << 3, memAddress(pixels));
//    }

    // --- [ glGetCompressedTextureSubImage ] ---

    /**
     * Obtains a sub-region of a compressed texture image.
     *
     * @param texture the source texture object name
     * @param level   the level-of-detail number
     * @param offset  the position of the subregion
     * @param size    the subregion size
     * @param pixels  the buffer in which to place the returned data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetCompressedTextureSubImage">Reference Page</a>
     */
    fun getCompressedTextureSubImage(texture: GlTexture, level: Int, offset: Vec3i, size: Vec3i, pixels: ByteBuffer) =
            GL45C.nglGetCompressedTextureSubImage(texture.name, level, offset.x, offset.y, offset.z, size.x, size.y, size.z, pixels.rem, pixels.adr)

//    /** TODO?
//     * Obtains a sub-region of a compressed texture image.
//     *
//     * @param texture the source texture object name
//     * @param level   the level-of-detail number
//     * @param xoffset the x-position of the subregion
//     * @param yoffset the y-position of the subregion
//     * @param zoffset the z-position of the subregion
//     * @param width   the subregion width
//     * @param height  the subregion height
//     * @param depth   the subregion depth
//     * @param pixels  the buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetCompressedTextureSubImage">Reference Page</a>
//     */
//    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int texture, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLint") int yoffset, @NativeType("GLint") int zoffset, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLsizei") int depth, @NativeType("void *") ShortBuffer pixels) {
//        nglGetCompressedTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, pixels.remaining() << 1, memAddress(pixels));
//    }
//
//    /**
//     * Obtains a sub-region of a compressed texture image.
//     *
//     * @param texture the source texture object name
//     * @param level   the level-of-detail number
//     * @param xoffset the x-position of the subregion
//     * @param yoffset the y-position of the subregion
//     * @param zoffset the z-position of the subregion
//     * @param width   the subregion width
//     * @param height  the subregion height
//     * @param depth   the subregion depth
//     * @param pixels  the buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetCompressedTextureSubImage">Reference Page</a>
//     */
//    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int texture, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLint") int yoffset, @NativeType("GLint") int zoffset, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLsizei") int depth, @NativeType("void *") IntBuffer pixels) {
//        nglGetCompressedTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, pixels.remaining() << 2, memAddress(pixels));
//    }
//
//    /**
//     * Obtains a sub-region of a compressed texture image.
//     *
//     * @param texture the source texture object name
//     * @param level   the level-of-detail number
//     * @param xoffset the x-position of the subregion
//     * @param yoffset the y-position of the subregion
//     * @param zoffset the z-position of the subregion
//     * @param width   the subregion width
//     * @param height  the subregion height
//     * @param depth   the subregion depth
//     * @param pixels  the buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetCompressedTextureSubImage">Reference Page</a>
//     */
//    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int texture, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLint") int yoffset, @NativeType("GLint") int zoffset, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLsizei") int depth, @NativeType("void *") FloatBuffer pixels) {
//        nglGetCompressedTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, pixels.remaining() << 2, memAddress(pixels));
//    }
//
//    /**
//     * Obtains a sub-region of a compressed texture image.
//     *
//     * @param texture the source texture object name
//     * @param level   the level-of-detail number
//     * @param xoffset the x-position of the subregion
//     * @param yoffset the y-position of the subregion
//     * @param zoffset the z-position of the subregion
//     * @param width   the subregion width
//     * @param height  the subregion height
//     * @param depth   the subregion depth
//     * @param pixels  the buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetCompressedTextureSubImage">Reference Page</a>
//     */
//    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int texture, @NativeType("GLint") int level, @NativeType("GLint") int xoffset, @NativeType("GLint") int yoffset, @NativeType("GLint") int zoffset, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLsizei") int depth, @NativeType("void *") DoubleBuffer pixels) {
//        nglGetCompressedTextureSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth, pixels.remaining() << 3, memAddress(pixels));
//    }

    // --- [ glTextureBarrier ] ---

    /**
     * Guarantees that writes have completed and caches have been invalidated before subsequent Draws are executed.
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glTextureBarrier">Reference Page</a>
     */
    fun textureBarrier() = GL45C.glTextureBarrier()

    // --- [ glGetGraphicsResetStatus ] ---

    /**
     * Indicates if the GL context has been in a reset state at any point since the last call to GetGraphicsResetStatus:
     *
     * <ul>
     * <li>{@link GL11#GL_NO_ERROR NO_ERROR} indicates that the GL context has not been in a reset state since the last call.</li>
     * <li>{@link #GL_GUILTY_CONTEXT_RESET GUILTY_CONTEXT_RESET} indicates that a reset has been detected that is attributable to the current GL context.</li>
     * <li>{@link #GL_INNOCENT_CONTEXT_RESET INNOCENT_CONTEXT_RESET} indicates a reset has been detected that is not attributable to the current GL context.</li>
     * <li>{@link #GL_UNKNOWN_CONTEXT_RESET UNKNOWN_CONTEXT_RESET} indicates a detected graphics reset whose cause is unknown.</li>
     * </ul>
     *
     * <p>If a reset status other than NO_ERROR is returned and subsequent calls return NO_ERROR, the context reset was encountered and completed. If a reset
     * status is repeatedly returned, the context may be in the process of resetting.</p>
     *
     * <p>Reset notification behavior is determined at context creation time, and may be queried by calling GetIntegerv with the symbolic constant
     * {@link #GL_RESET_NOTIFICATION_STRATEGY RESET_NOTIFICATION_STRATEGY}.</p>
     *
     * <p>If the reset notification behavior is {@link #GL_NO_RESET_NOTIFICATION NO_RESET_NOTIFICATION}, then the implementation will never deliver notification of reset events, and
     * GetGraphicsResetStatus will always return NO_ERROR.</p>
     *
     * <p>If the behavior is {@link #GL_LOSE_CONTEXT_ON_RESET LOSE_CONTEXT_ON_RESET}, a graphics reset will result in a lost context and require creating a new context as described
     * above. In this case GetGraphicsResetStatus will return an appropriate value from those described above.</p>
     *
     * <p>If a graphics reset notification occurs in a context, a notification must also occur in all other contexts which share objects with that context.</p>
     *
     * <p>After a graphics reset has occurred on a context, subsequent GL commands on that context (or any context which shares with that context) will generate a
     * {@link #GL_CONTEXT_LOST CONTEXT_LOST} error. Such commands will not have side effects (in particular, they will not modify memory passed by pointer for query results,
     * and may not block indefinitely or cause termination of the application. Exceptions to this behavior include:</p>
     *
     * <ul>
     * <li>{@link GL11C#glGetError GetError} and GetGraphicsResetStatus behave normally following a graphics reset, so that the application can determine a reset has
     * occurred, and when it is safe to destroy and recreate the context.</li>
     * <li>Any commands which might cause a polling application to block indefinitely will generate a CONTEXT_LOST error, but will also return a value
     * indicating completion to the application.</li>
     * </ul>
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetGraphicsResetStatus">Reference Page</a>
     */
    val graphicsResetStatus: GraphicsResetStatus
        get() = GraphicsResetStatus(GL45C.glGetGraphicsResetStatus())

    // --- [ glGetnTexImage ] ---

    /**
     * Robust version of {@link GL11C#glGetTexImage GetTexImage}
     *
     * @param tex    the texture (or texture face) to be obtained. One of:<br><table><tr><td>{@link GL11#GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link GL11#GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td></tr><tr><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_X TEXTURE_CUBE_MAP_POSITIVE_X}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_X TEXTURE_CUBE_MAP_NEGATIVE_X}</td></tr><tr><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Y TEXTURE_CUBE_MAP_POSITIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Y TEXTURE_CUBE_MAP_NEGATIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Z TEXTURE_CUBE_MAP_POSITIVE_Z}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Z TEXTURE_CUBE_MAP_NEGATIVE_Z}</td></tr></table>
     * @param level  the level-of-detail number
     * @param format the pixel format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
     * @param type   the pixel type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
     * @param img    a buffer in which to place the returned data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetnTexImage">Reference Page</a>
     */
    fun getnTexImage(tex: GlTexture, level: Int, format: TextureFormat2, type: TextureType2, img: ByteBuffer) =
        GL45C.nglGetnTexImage(tex.name, level, format.i, type.i, img.rem, img.adr)

//    /** TODO?
//     * Robust version of {@link GL11C#glGetTexImage GetTexImage}
//     *
//     * @param tex    the texture (or texture face) to be obtained. One of:<br><table><tr><td>{@link GL11#GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link GL11#GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td></tr><tr><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_X TEXTURE_CUBE_MAP_POSITIVE_X}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_X TEXTURE_CUBE_MAP_NEGATIVE_X}</td></tr><tr><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Y TEXTURE_CUBE_MAP_POSITIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Y TEXTURE_CUBE_MAP_NEGATIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Z TEXTURE_CUBE_MAP_POSITIVE_Z}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Z TEXTURE_CUBE_MAP_NEGATIVE_Z}</td></tr></table>
//     * @param level  the level-of-detail number
//     * @param format the pixel format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type   the pixel type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param img    a buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetnTexImage">Reference Page</a>
//     */
//    public static void glGetnTexImage(@NativeType("GLenum") int tex, @NativeType("GLint") int level, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") ShortBuffer img) {
//        nglGetnTexImage(tex, level, format, type, img.remaining() << 1, memAddress(img));
//    }
//
//    /**
//     * Robust version of {@link GL11C#glGetTexImage GetTexImage}
//     *
//     * @param tex    the texture (or texture face) to be obtained. One of:<br><table><tr><td>{@link GL11#GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link GL11#GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td></tr><tr><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_X TEXTURE_CUBE_MAP_POSITIVE_X}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_X TEXTURE_CUBE_MAP_NEGATIVE_X}</td></tr><tr><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Y TEXTURE_CUBE_MAP_POSITIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Y TEXTURE_CUBE_MAP_NEGATIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Z TEXTURE_CUBE_MAP_POSITIVE_Z}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Z TEXTURE_CUBE_MAP_NEGATIVE_Z}</td></tr></table>
//     * @param level  the level-of-detail number
//     * @param format the pixel format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type   the pixel type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param img    a buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetnTexImage">Reference Page</a>
//     */
//    public static void glGetnTexImage(@NativeType("GLenum") int tex, @NativeType("GLint") int level, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") IntBuffer img) {
//        nglGetnTexImage(tex, level, format, type, img.remaining() << 2, memAddress(img));
//    }
//
//    /**
//     * Robust version of {@link GL11C#glGetTexImage GetTexImage}
//     *
//     * @param tex    the texture (or texture face) to be obtained. One of:<br><table><tr><td>{@link GL11#GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link GL11#GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td></tr><tr><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_X TEXTURE_CUBE_MAP_POSITIVE_X}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_X TEXTURE_CUBE_MAP_NEGATIVE_X}</td></tr><tr><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Y TEXTURE_CUBE_MAP_POSITIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Y TEXTURE_CUBE_MAP_NEGATIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Z TEXTURE_CUBE_MAP_POSITIVE_Z}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Z TEXTURE_CUBE_MAP_NEGATIVE_Z}</td></tr></table>
//     * @param level  the level-of-detail number
//     * @param format the pixel format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type   the pixel type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param img    a buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetnTexImage">Reference Page</a>
//     */
//    public static void glGetnTexImage(@NativeType("GLenum") int tex, @NativeType("GLint") int level, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") FloatBuffer img) {
//        nglGetnTexImage(tex, level, format, type, img.remaining() << 2, memAddress(img));
//    }
//
//    /**
//     * Robust version of {@link GL11C#glGetTexImage GetTexImage}
//     *
//     * @param tex    the texture (or texture face) to be obtained. One of:<br><table><tr><td>{@link GL11#GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link GL11#GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td></tr><tr><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_X TEXTURE_CUBE_MAP_POSITIVE_X}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_X TEXTURE_CUBE_MAP_NEGATIVE_X}</td></tr><tr><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Y TEXTURE_CUBE_MAP_POSITIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Y TEXTURE_CUBE_MAP_NEGATIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Z TEXTURE_CUBE_MAP_POSITIVE_Z}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Z TEXTURE_CUBE_MAP_NEGATIVE_Z}</td></tr></table>
//     * @param level  the level-of-detail number
//     * @param format the pixel format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td></tr></table>
//     * @param type   the pixel type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr></table>
//     * @param img    a buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetnTexImage">Reference Page</a>
//     */
//    public static void glGetnTexImage(@NativeType("GLenum") int tex, @NativeType("GLint") int level, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") DoubleBuffer img) {
//        nglGetnTexImage(tex, level, format, type, img.remaining() << 3, memAddress(img));
//    }

    // --- [ glReadnPixels ] ---

    /**
     * Behaves identically to {@link GL11C#glReadPixels ReadPixels} except that it does not write more than {@code bufSize} bytes into {@code data}
     *
     * @param pixel  the pixel coordinate
     * @param size   the number of pixels to read in the xy-dimension
     * @param format the pixel format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td><td>{@link GL11#GL_LUMINANCE LUMINANCE}</td><td>{@link GL11#GL_LUMINANCE_ALPHA LUMINANCE_ALPHA}</td></tr></table>
     * @param type   the pixel type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr><tr><td>{@link GL11#GL_BITMAP BITMAP}</td></tr></table>
     * @param pixels a buffer in which to place the returned pixel data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glReadnPixels">Reference Page</a>
     */
    fun readnPixels(pixel: Vec2i, size: Vec2i, format: TextureFormat3, type: TextureType2, pixels: ByteBuffer) =
        GL45C.nglReadnPixels(pixel.x, pixel.y, size.x, size.y, format.i, type.i, pixels.rem, pixels.adr)

//    /** TODO?
//     * Behaves identically to {@link GL11C#glReadPixels ReadPixels} except that it does not write more than {@code bufSize} bytes into {@code data}
//     *
//     * @param x      the left pixel coordinate
//     * @param y      the lower pixel coordinate
//     * @param width  the number of pixels to read in the x-dimension
//     * @param height the number of pixels to read in the y-dimension
//     * @param format the pixel format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td><td>{@link GL11#GL_LUMINANCE LUMINANCE}</td><td>{@link GL11#GL_LUMINANCE_ALPHA LUMINANCE_ALPHA}</td></tr></table>
//     * @param type   the pixel type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr><tr><td>{@link GL11#GL_BITMAP BITMAP}</td></tr></table>
//     * @param pixels a buffer in which to place the returned pixel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glReadnPixels">Reference Page</a>
//     */
//    public static void glReadnPixels(@NativeType("GLint") int x, @NativeType("GLint") int y, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") ShortBuffer pixels) {
//        nglReadnPixels(x, y, width, height, format, type, pixels.remaining() << 1, memAddress(pixels));
//    }
//
//    /**
//     * Behaves identically to {@link GL11C#glReadPixels ReadPixels} except that it does not write more than {@code bufSize} bytes into {@code data}
//     *
//     * @param x      the left pixel coordinate
//     * @param y      the lower pixel coordinate
//     * @param width  the number of pixels to read in the x-dimension
//     * @param height the number of pixels to read in the y-dimension
//     * @param format the pixel format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td><td>{@link GL11#GL_LUMINANCE LUMINANCE}</td><td>{@link GL11#GL_LUMINANCE_ALPHA LUMINANCE_ALPHA}</td></tr></table>
//     * @param type   the pixel type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr><tr><td>{@link GL11#GL_BITMAP BITMAP}</td></tr></table>
//     * @param pixels a buffer in which to place the returned pixel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glReadnPixels">Reference Page</a>
//     */
//    public static void glReadnPixels(@NativeType("GLint") int x, @NativeType("GLint") int y, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") IntBuffer pixels) {
//        nglReadnPixels(x, y, width, height, format, type, pixels.remaining() << 2, memAddress(pixels));
//    }
//
//    /**
//     * Behaves identically to {@link GL11C#glReadPixels ReadPixels} except that it does not write more than {@code bufSize} bytes into {@code data}
//     *
//     * @param x      the left pixel coordinate
//     * @param y      the lower pixel coordinate
//     * @param width  the number of pixels to read in the x-dimension
//     * @param height the number of pixels to read in the y-dimension
//     * @param format the pixel format. One of:<br><table><tr><td>{@link GL11#GL_RED RED}</td><td>{@link GL11#GL_GREEN GREEN}</td><td>{@link GL11#GL_BLUE BLUE}</td><td>{@link GL11#GL_ALPHA ALPHA}</td><td>{@link GL30#GL_RG RG}</td><td>{@link GL11#GL_RGB RGB}</td><td>{@link GL11C#GL_RGBA RGBA}</td><td>{@link GL12#GL_BGR BGR}</td></tr><tr><td>{@link GL12#GL_BGRA BGRA}</td><td>{@link GL30#GL_RED_INTEGER RED_INTEGER}</td><td>{@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}</td><td>{@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}</td><td>{@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}</td><td>{@link GL30#GL_RG_INTEGER RG_INTEGER}</td><td>{@link GL30#GL_RGB_INTEGER RGB_INTEGER}</td><td>{@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}</td></tr><tr><td>{@link GL30#GL_BGR_INTEGER BGR_INTEGER}</td><td>{@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}</td><td>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}</td><td>{@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}</td><td>{@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}</td><td>{@link GL11#GL_LUMINANCE LUMINANCE}</td><td>{@link GL11#GL_LUMINANCE_ALPHA LUMINANCE_ALPHA}</td></tr></table>
//     * @param type   the pixel type. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_BYTE BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_SHORT SHORT}</td></tr><tr><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td><td>{@link GL11#GL_INT INT}</td><td>{@link GL30#GL_HALF_FLOAT HALF_FLOAT}</td><td>{@link GL11#GL_FLOAT FLOAT}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}</td><td>{@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}</td><td>{@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}</td><td>{@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}</td><td>{@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}</td></tr><tr><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}</td><td>{@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}</td><td>{@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}</td><td>{@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}</td></tr><tr><td>{@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}</td><td>{@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}</td><td>{@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}</td><td>{@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}</td></tr><tr><td>{@link GL11#GL_BITMAP BITMAP}</td></tr></table>
//     * @param pixels a buffer in which to place the returned pixel data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glReadnPixels">Reference Page</a>
//     */
//    public static void glReadnPixels(@NativeType("GLint") int x, @NativeType("GLint") int y, @NativeType("GLsizei") int width, @NativeType("GLsizei") int height, @NativeType("GLenum") int format, @NativeType("GLenum") int type, @NativeType("void *") FloatBuffer pixels) {
//        nglReadnPixels(x, y, width, height, format, type, pixels.remaining() << 2, memAddress(pixels));
//    }
//
    // --- [ glGetnCompressedTexImage ] ---

    /**
     * Robust version of {@link GL13C#glGetCompressedTexImage GetCompressedTexImage}
     *
     * @param target the target texture. One of:<br><table><tr><td>{@link GL11#GL_TEXTURE_1D TEXTURE_1D}</td><td>{@link GL11#GL_TEXTURE_2D TEXTURE_2D}</td><td>{@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}</td></tr><tr><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_X TEXTURE_CUBE_MAP_POSITIVE_X}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_X TEXTURE_CUBE_MAP_NEGATIVE_X}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Y TEXTURE_CUBE_MAP_POSITIVE_Y}</td></tr><tr><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Y TEXTURE_CUBE_MAP_NEGATIVE_Y}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_POSITIVE_Z TEXTURE_CUBE_MAP_POSITIVE_Z}</td><td>{@link GL13#GL_TEXTURE_CUBE_MAP_NEGATIVE_Z TEXTURE_CUBE_MAP_NEGATIVE_Z}</td></tr><tr><td>{@link GL12#GL_TEXTURE_3D TEXTURE_3D}</td><td>{@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}</td><td>{@link GL40#GL_TEXTURE_CUBE_MAP_ARRAY TEXTURE_CUBE_MAP_ARRAY}</td></tr></table>
     * @param level  the level-of-detail number. Level 0 is the base image level. Level n is the nth mipmap reduction image.
     * @param img    a buffer in which to place the returned data
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glGetnCompressedTexImage">Reference Page</a>
     */
    fun getnCompressedTexImage(target: TextureTarget, level: Int, img: ByteBuffer) =
        GL45C.nglGetnCompressedTexImage(target.i, level, img.rem, img.adr)

//    // --- [ glGetnUniformfv ] --- TODO?
//
//    /**
//     * Unsafe version of: {@link #glGetnUniformfv GetnUniformfv}
//     *
//     * @param bufSize the maximum number of bytes to write to {@code params}
//     */
//    public static native void nglGetnUniformfv(int program, int location, int bufSize, long params);
//
//    /**
//     * Returns the value or values of a uniform of the default uniform block.
//     *
//     * @param program  the program object
//     * @param location the uniform location
//     * @param params   the buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetUniform">Reference Page</a>
//     */
//    public static void glGetnUniformfv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLfloat *") FloatBuffer params) {
//        nglGetnUniformfv(program, location, params.remaining(), memAddress(params));
//    }
//
//    /**
//     * Returns the value or values of a uniform of the default uniform block.
//     *
//     * @param program  the program object
//     * @param location the uniform location
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetUniform">Reference Page</a>
//     */
//    @NativeType("void")
//    public static float glGetnUniformf(@NativeType("GLuint") int program, @NativeType("GLint") int location) {
//        MemoryStack stack = stackGet(); int stackPointer = stack.getPointer();
//        try {
//            FloatBuffer params = stack.callocFloat(1);
//            nglGetnUniformfv(program, location, 1, memAddress(params));
//            return params.get(0);
//        } finally {
//            stack.setPointer(stackPointer);
//        }
//    }
//
//    // --- [ glGetnUniformdv ] ---
//
//    /**
//     * Unsafe version of: {@link #glGetnUniformdv GetnUniformdv}
//     *
//     * @param bufSize the maximum number of bytes to write to {@code params}
//     */
//    public static native void nglGetnUniformdv(int program, int location, int bufSize, long params);
//
//    /**
//     * Double version of {@link #glGetnUniformfv GetnUniformfv}.
//     *
//     * @param program  the program object
//     * @param location the uniform location
//     * @param params   the buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetUniform">Reference Page</a>
//     */
//    public static void glGetnUniformdv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLdouble *") DoubleBuffer params) {
//        nglGetnUniformdv(program, location, params.remaining(), memAddress(params));
//    }
//
//    /**
//     * Double version of {@link #glGetnUniformfv GetnUniformfv}.
//     *
//     * @param program  the program object
//     * @param location the uniform location
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetUniform">Reference Page</a>
//     */
//    @NativeType("void")
//    public static double glGetnUniformd(@NativeType("GLuint") int program, @NativeType("GLint") int location) {
//        MemoryStack stack = stackGet(); int stackPointer = stack.getPointer();
//        try {
//            DoubleBuffer params = stack.callocDouble(1);
//            nglGetnUniformdv(program, location, 1, memAddress(params));
//            return params.get(0);
//        } finally {
//            stack.setPointer(stackPointer);
//        }
//    }
//
//    // --- [ glGetnUniformiv ] ---
//
//    /**
//     * Unsafe version of: {@link #glGetnUniformiv GetnUniformiv}
//     *
//     * @param bufSize the maximum number of bytes to write to {@code params}
//     */
//    public static native void nglGetnUniformiv(int program, int location, int bufSize, long params);
//
//    /**
//     * Integer version of {@link #glGetnUniformfv GetnUniformfv}.
//     *
//     * @param program  the program object
//     * @param location the uniform location
//     * @param params   the buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetUniform">Reference Page</a>
//     */
//    public static void glGetnUniformiv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLfloat *") FloatBuffer params) {
//        nglGetnUniformiv(program, location, params.remaining(), memAddress(params));
//    }
//
//    /**
//     * Integer version of {@link #glGetnUniformfv GetnUniformfv}.
//     *
//     * @param program  the program object
//     * @param location the uniform location
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetUniform">Reference Page</a>
//     */
//    @NativeType("void")
//    public static float glGetnUniformi(@NativeType("GLuint") int program, @NativeType("GLint") int location) {
//        MemoryStack stack = stackGet(); int stackPointer = stack.getPointer();
//        try {
//            FloatBuffer params = stack.callocFloat(1);
//            nglGetnUniformiv(program, location, 1, memAddress(params));
//            return params.get(0);
//        } finally {
//            stack.setPointer(stackPointer);
//        }
//    }
//
//    // --- [ glGetnUniformuiv ] ---
//
//    /**
//     * Unsafe version of: {@link #glGetnUniformuiv GetnUniformuiv}
//     *
//     * @param bufSize the maximum number of bytes to write to {@code params}
//     */
//    public static native void nglGetnUniformuiv(int program, int location, int bufSize, long params);
//
//    /**
//     * Unsigned version of {@link #glGetnUniformiv GetnUniformiv}.
//     *
//     * @param program  the program object
//     * @param location the uniform location
//     * @param params   the buffer in which to place the returned data
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetUniform">Reference Page</a>
//     */
//    public static void glGetnUniformuiv(@NativeType("GLuint") int program, @NativeType("GLint") int location, @NativeType("GLfloat *") FloatBuffer params) {
//        nglGetnUniformuiv(program, location, params.remaining(), memAddress(params));
//    }
//
//    /**
//     * Unsigned version of {@link #glGetnUniformiv GetnUniformiv}.
//     *
//     * @param program  the program object
//     * @param location the uniform location
//     *
//     * @see <a target="_blank" href="http://docs.gl/gl4/glGetUniform">Reference Page</a>
//     */
//    @NativeType("void")
//    public static float glGetnUniformui(@NativeType("GLuint") int program, @NativeType("GLint") int location) {
//        MemoryStack stack = stackGet(); int stackPointer = stack.getPointer();
//        try {
//            FloatBuffer params = stack.callocFloat(1);
//            nglGetnUniformuiv(program, location, 1, memAddress(params));
//            return params.get(0);
//        } finally {
//            stack.setPointer(stackPointer);
//        }
//    }
}