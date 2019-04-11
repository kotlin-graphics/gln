package gln

import gln.dsa.dsaARB
import gln.dsa.dsaCore
import gln.dsa.dsaInterface

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

    var DSA = false

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

    var logicOpEnabled: Boolean = false


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