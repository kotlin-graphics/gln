package gln.debug


import org.lwjgl.opengl.*
import org.lwjgl.system.MemoryUtil
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.opengl.GL43.glDebugMessageControl as prova


enum class GlDebugSource(val i: Int) {
    API(KHRDebug.GL_DEBUG_SOURCE_API),
    APPLICATION(KHRDebug.GL_DEBUG_SOURCE_APPLICATION),
    DONT_CARE(GL11.GL_DONT_CARE),
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
    DONT_CARE(GL11.GL_DONT_CARE),
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
    DONT_CARE(GL11.GL_DONT_CARE),
    HIGH(KHRDebug.GL_DEBUG_SEVERITY_HIGH),
    LOW(KHRDebug.GL_DEBUG_SEVERITY_LOW),
    MEDIUM(KHRDebug.GL_DEBUG_SEVERITY_MEDIUM),
    NOTIFICATION(KHRDebug.GL_DEBUG_SEVERITY_NOTIFICATION);

    companion object {
        infix fun of(i: Int) = values().first { it.i == i }
    }
}

var glDebugCallback: GLDebugMessageCallback? = null

fun glDebugMessageCallback(callback: (source: GlDebugSource, type: GlDebugType, severity: GlDebugSeverity, id: Int, message: String) -> Unit) {
    glDebugCallback?.free()
    glDebugCallback = GLDebugMessageCallback.create { source, type, severity, id, _, message, _ ->
        callback(GlDebugSource of source, GlDebugType of type, GlDebugSeverity of severity, id, MemoryUtil.memUTF8(message))
    }
    KHRDebug.glDebugMessageCallback(glDebugCallback, NULL)
}
// TODO bug https://youtrack.jetbrains.com/issue/KT-25452?query=KotlinFrontEndException
//// TODO glDebugMessageControl with id
//fun glDebugMessageControl(source: GlDebugSource, type: GlDebugType, severity: GlDebugSeverity, enabled: Boolean) {
//    _glDebugMessageControl(source.i, type.i, severity.i, 0, NULL, enabled)
//}
//
//val _glDebugMessageControl: (source: Int, type: Int, severity: Int, count: Int, adr: Long, Boolean) -> Unit = GL.getCapabilities().run {
//    when {
//        OpenGL43 -> GL43::nglDebugMessageControl
//        GL_KHR_debug -> KHRDebug::nglDebugMessageControl
//        GL_ARB_debug_output -> ARBDebugOutput::nglDebugMessageControlARB
//        else -> throw Error()
//    //            if (caps.GL_AMD_debug_output)
//    //                AMDDebugOutput.glDebugMessageEnableAMD(GL11.GL_DONT_CARE, GL11.GL_DONT_CARE,  GL43.GL_DEBUG_SEVERITY_NOTIFICATION, 0, false)
//    }
//}