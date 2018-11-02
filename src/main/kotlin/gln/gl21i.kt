/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package gln

import glm_.mat2x3.Mat2x3
import glm_.mat2x4.Mat2x4
import glm_.mat3x2.Mat3x2
import glm_.mat3x4.Mat3x4
import glm_.mat4x2.Mat4x2
import glm_.mat4x3.Mat4x3


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
     * Specifies the value of a single mat2x3 uniform variable or a mat2x3 uniform variable array for the current program object.
     *
     * @param location  the location of the uniform variable to be modified
     * @param value     a pointer to an array of {@code count} values that will be used to update the specified uniform variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: UniformLocation, value: Mat2x3): Nothing = TODO()

    // --- [ glUniformMatrix3x2fv ] ---

    /**
     * Specifies the value of a single mat3x2 uniform variable or a mat3x2 uniform variable array for the current program object.
     *
     * @param location  the location of the uniform variable to be modified
     * @param value     a pointer to an array of {@code count} values that will be used to update the specified uniform variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: UniformLocation, value: Mat3x2): Nothing = TODO()

    // --- [ glUniformMatrix2x4fv ] ---

    /**
     * Specifies the value of a single mat2x4 uniform variable or a mat2x4 uniform variable array for the current program object.
     *
     * @param location  the location of the uniform variable to be modified
     * @param value     a pointer to an array of {@code count} values that will be used to update the specified uniform variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: UniformLocation, value: Mat2x4): Nothing = TODO()

    // --- [ glUniformMatrix4x2fv ] ---

    /**
     * Specifies the value of a single mat4x2 uniform variable or a mat4x2 uniform variable array for the current program object.
     *
     * @param location  the location of the uniform variable to be modified
     * @param value     a pointer to an array of {@code count} values that will be used to update the specified uniform variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: UniformLocation, value: Mat4x2): Nothing = TODO()

    // --- [ glUniformMatrix3x4fv ] ---

    /**
     * Specifies the value of a single mat3x4 uniform variable or a mat3x4 uniform variable array for the current program object.
     *
     * @param location  the location of the uniform variable to be modified
     * @param value     a pointer to an array of {@code count} values that will be used to update the specified uniform variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: UniformLocation, value: Mat3x4): Nothing = TODO()

    // --- [ glUniformMatrix4x3fv ] ---

    /**
     * Specifies the value of a single mat4x3 uniform variable or a mat4x3 uniform variable array for the current program object.
     *
     * @param location  the location of the uniform variable to be modified
     * @param transpose whether to transpose the matrix as the values are loaded into the uniform variable
     * @param value     a pointer to an array of {@code count} values that will be used to update the specified uniform variable
     *
     * @see <a target="_blank" href="http://docs.gl/gl4/glUniform">Reference Page</a>
     */
    fun uniform(location: UniformLocation, value: Mat4x3): Nothing = TODO()
}