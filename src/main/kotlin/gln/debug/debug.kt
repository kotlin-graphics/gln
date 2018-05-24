package gln.debug


import org.lwjgl.opengl.GLDebugMessageCallback
import org.lwjgl.opengl.GLDebugMessageCallbackI
import org.lwjgl.opengl.KHRDebug
import org.lwjgl.system.MemoryUtil
import org.lwjgl.system.MemoryUtil.NULL


enum class GlDebugSource(val i: Int) {
    API(KHRDebug.GL_DEBUG_SOURCE_API),
    APPLICATION(KHRDebug.GL_DEBUG_SOURCE_APPLICATION),
    OTHER(KHRDebug.GL_DEBUG_SOURCE_OTHER),
    SHADER_COMPILER(KHRDebug.GL_DEBUG_SOURCE_SHADER_COMPILER),
    THIRD_PARTY(KHRDebug.GL_DEBUG_SOURCE_THIRD_PARTY),
    WINDOW_SYSTEM(KHRDebug.GL_DEBUG_SOURCE_WINDOW_SYSTEM);

    companion object {
        infix fun of(i: Int) = values().first { it.i == i }
    }
}

enum class GlDebugType(val i: Int) {
    DEPRECATED_BEHAVIOR(KHRDebug.GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR),
    ERROR(KHRDebug.GL_DEBUG_TYPE_ERROR),
    MARKER(KHRDebug.GL_DEBUG_TYPE_MARKER),
    OTHER(KHRDebug.GL_DEBUG_TYPE_OTHER),
    PERFORMANCE(KHRDebug.GL_DEBUG_TYPE_PERFORMANCE),
    POP_GROUP(KHRDebug.GL_DEBUG_TYPE_POP_GROUP),
    PORTABILITY(KHRDebug.GL_DEBUG_TYPE_PORTABILITY),
    PUSH_GROUP(KHRDebug.GL_DEBUG_TYPE_PUSH_GROUP),
    UNDEFINED_BEHAVIOR(KHRDebug.GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR);

    companion object {
        infix fun of(i: Int) = values().first { it.i == i }
    }
}

enum class GlDebugSeverity(val i: Int) {
    HIGH(KHRDebug.GL_DEBUG_SEVERITY_HIGH),
    LOW(KHRDebug.GL_DEBUG_SEVERITY_LOW),
    MEDIUM(KHRDebug.GL_DEBUG_SEVERITY_MEDIUM),
    NOTIFICATION(KHRDebug.GL_DEBUG_SEVERITY_NOTIFICATION);

    companion object {
        infix fun of(i: Int) = values().first { it.i == i }
    }
}

var glDebugCallback: GLDebugMessageCallback? = null

fun glDebugMessageCallback(callback: (source: GlDebugSource, type: GlDebugType, id: Int, severity: GlDebugSeverity, message: String) -> Unit) {
    glDebugCallback?.free()
    glDebugCallback = GLDebugMessageCallback.create { source, type, id, severity, _, message, _ ->
        callback(GlDebugSource.of(source), GlDebugType.of(type), id, GlDebugSeverity.of(severity), MemoryUtil.memUTF8(message))
    }
    KHRDebug.glDebugMessageCallback(glDebugCallback, NULL)
}