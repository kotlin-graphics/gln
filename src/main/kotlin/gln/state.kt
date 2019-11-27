package gln

import gln.dsa.dsaARB
import gln.dsa.dsaCore
import gln.dsa.dsaInterface
import gln.identifiers.GlFramebuffer
import gln.identifiers.GlFramebuffers
import org.lwjgl.opengl.GL11C

@FunctionalInterface
private interface StateChangedCallback {
    operator fun invoke(block : () -> String)
}

enum class DSAType {
    ARB,
    CORE,
    NONE
}

object OpenGlState {
    private var _dsa = DSAType.CORE

    var dsa: DSAType
        get() = _dsa
        set(value) {
            valueChanged { "DSA Mode was $_dsa, is now $value" }
            _dsa = value
        }

    internal val dsaClass: dsaInterface
        get() {
            return when(_dsa) {
                DSAType.CORE -> dsaCore
                DSAType.ARB -> dsaARB
                DSAType.NONE -> throw RuntimeException("DSA Was set to off and then called!")
            }
        }

    var cullFaceEnabled: Boolean = false
        internal set(value) {
            valueChanged { "Cull Face Enabled was $field, is now $value" }
            field = value
        }
    var cullFaceMode = CullFaceMode(GL11C.GL_BACK)
        internal set(value) {
            valueChanged {
                if(logicOpEnabled) {
                    "Cull Face Mode was $field, is now $value"
                } else {
                    "Cull Face Mode was $field, is now $value. However, Cull Face is disabled!"
                }
            }
            field = value
        }


    var logicOpEnabled: Boolean = false
        internal set(value) {
            valueChanged { "Logic Op Enabled was $field, is now $value" }
            field = value
        }
    var logicOp = LogicOp(GL11C.GL_FALSE)
        internal set(value) {
            valueChanged {
                if(logicOpEnabled) {
                    "Logic Op was $field, is now $value"
                } else {
                    "Logic Op was $field, is now $value. However, Logic Op is disabled!"
                }
            }
            field = value
        }

    @PublishedApi
    internal var _framebuffer = GlFramebuffer.DEFAULT
        set(value) {
            valueChanged {
                val was = if(field == GlFramebuffer.DEFAULT) "screen" else "${value.name}"
                val new = if(value == GlFramebuffer.DEFAULT) "screen" else "${value.name}"
                "Framebuffer was $was, is now $value"
            }
            field = value
        }

    val framebuffer: GlFramebuffer
        get() = _framebuffer


    @Suppress("OVERRIDE_BY_INLINE")
    private val nullCallback: StateChangedCallback = object : StateChangedCallback {
        override inline fun invoke(block: () -> String) {}
    }

    private val updateCallback: StateChangedCallback = object : StateChangedCallback {
        override fun invoke(block: () -> String) {
            println(block())
        }
    }

    private var valueChanged: StateChangedCallback = nullCallback

    var trackingEnabled = false
        get() = updateCallback != nullCallback
        set(value) {
            field = value
            valueChanged = if(value) updateCallback else nullCallback
        }
}