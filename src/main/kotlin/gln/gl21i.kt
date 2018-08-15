/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package gln


/**
 * The OpenGL functionality of a forward compatible context, up to version 2.1.
 *
 * <p>OpenGL 2.1 implementations must support at least revision 1.20 of the OpenGL Shading Language.</p>
 *
 * <p>Extensions promoted to core in this release:</p>
 *
 * <ul>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/ARB/ARB_pixel_buffer_object.txt">ARB_pixel_buffer_object</a></li>
 * <li><a target="_blank" href="https://www.khronos.org/registry/OpenGL/extensions/EXT/EXT_texture_sRGB.txt">EXT_texture_sRGB</a></li>
 * </ul>
 */
interface gl21i {

    // --- [ glUniformMatrix2x3fv ] ---

    /**
     * Unsafe version of: {@link #glUniformMatrix2x3fv UniformMatrix2x3fv}
     *
     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
     */
    public static native void nglUniformMatrix2x3fv(int location, int count, boolean transpose, long value);

    /**
     * Specifies the value of a single mat2x3 uniform variable or a mat2x3 uniform variable array for the current program object.
     *
     * @param location  the location of the uniform variable to be modified
     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
     * @param value     a pointer to an array of {@code count} values that will be used to update the specified uniform variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    public static void glUniformMatrix2x3fv(@NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLfloat const *") FloatBuffer value) {
        nglUniformMatrix2x3fv(location, value.remaining() / 6, transpose, memAddress(value));
    }

    // --- [ glUniformMatrix3x2fv ] ---

    /**
     * Unsafe version of: {@link #glUniformMatrix3x2fv UniformMatrix3x2fv}
     *
     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
     */
    public static native void nglUniformMatrix3x2fv(int location, int count, boolean transpose, long value);

    /**
     * Specifies the value of a single mat3x2 uniform variable or a mat3x2 uniform variable array for the current program object.
     *
     * @param location  the location of the uniform variable to be modified
     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
     * @param value     a pointer to an array of {@code count} values that will be used to update the specified uniform variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    public static void glUniformMatrix3x2fv(@NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLfloat const *") FloatBuffer value) {
        nglUniformMatrix3x2fv(location, value.remaining() / 6, transpose, memAddress(value));
    }

    // --- [ glUniformMatrix2x4fv ] ---

    /**
     * Unsafe version of: {@link #glUniformMatrix2x4fv UniformMatrix2x4fv}
     *
     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
     */
    public static native void nglUniformMatrix2x4fv(int location, int count, boolean transpose, long value);

    /**
     * Specifies the value of a single mat2x4 uniform variable or a mat2x4 uniform variable array for the current program object.
     *
     * @param location  the location of the uniform variable to be modified
     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
     * @param value     a pointer to an array of {@code count} values that will be used to update the specified uniform variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    public static void glUniformMatrix2x4fv(@NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLfloat const *") FloatBuffer value) {
        nglUniformMatrix2x4fv(location, value.remaining() >> 3, transpose, memAddress(value));
    }

    // --- [ glUniformMatrix4x2fv ] ---

    /**
     * Unsafe version of: {@link #glUniformMatrix4x2fv UniformMatrix4x2fv}
     *
     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
     */
    public static native void nglUniformMatrix4x2fv(int location, int count, boolean transpose, long value);

    /**
     * Specifies the value of a single mat4x2 uniform variable or a mat4x2 uniform variable array for the current program object.
     *
     * @param location  the location of the uniform variable to be modified
     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
     * @param value     a pointer to an array of {@code count} values that will be used to update the specified uniform variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    public static void glUniformMatrix4x2fv(@NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLfloat const *") FloatBuffer value) {
        nglUniformMatrix4x2fv(location, value.remaining() >> 3, transpose, memAddress(value));
    }

    // --- [ glUniformMatrix3x4fv ] ---

    /**
     * Unsafe version of: {@link #glUniformMatrix3x4fv UniformMatrix3x4fv}
     *
     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
     */
    public static native void nglUniformMatrix3x4fv(int location, int count, boolean transpose, long value);

    /**
     * Specifies the value of a single mat3x4 uniform variable or a mat3x4 uniform variable array for the current program object.
     *
     * @param location  the location of the uniform variable to be modified
     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
     * @param value     a pointer to an array of {@code count} values that will be used to update the specified uniform variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    public static void glUniformMatrix3x4fv(@NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLfloat const *") FloatBuffer value) {
        nglUniformMatrix3x4fv(location, value.remaining() / 12, transpose, memAddress(value));
    }

    // --- [ glUniformMatrix4x3fv ] ---

    /**
     * Unsafe version of: {@link #glUniformMatrix4x3fv UniformMatrix4x3fv}
     *
     * @param count the number of matrices that are to be modified. This should be 1 if the targeted uniform variable is not an array of matrices, and 1 or more if it is an array of matrices.
     */
    public static native void nglUniformMatrix4x3fv(int location, int count, boolean transpose, long value);

    /**
     * Specifies the value of a single mat4x3 uniform variable or a mat4x3 uniform variable array for the current program object.
     *
     * @param location  the location of the uniform variable to be modified
     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
     * @param value     a pointer to an array of {@code count} values that will be used to update the specified uniform variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    public static void glUniformMatrix4x3fv(@NativeType("GLint") int location, @NativeType("GLboolean") boolean transpose, @NativeType("GLfloat const *") FloatBuffer value) {
        nglUniformMatrix4x3fv(location, value.remaining() / 12, transpose, memAddress(value));
    }
}