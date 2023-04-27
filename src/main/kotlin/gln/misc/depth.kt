package gln.misc

import gln.CompareFunction
import gln.L
import gln.offHeapPtr
import kool.get
import org.lwjgl.opengl.GL11C
import org.lwjgl.opengl.GL32
import org.lwjgl.opengl.GL32C
import org.lwjgl.opengl.GL41C

inline fun depth(block: ObjectDepth.() -> Unit) = ObjectDepth.block()

object ObjectDepth {

    var test: Boolean
        get() = GL11C.glIsEnabled(GL11C.GL_DEPTH_TEST)
        set(value) = when {
            value -> GL11C.glEnable(GL11C.GL_DEPTH_TEST)
            else -> GL11C.glDisable(GL11C.GL_DEPTH_TEST)
        }

    var mask: Boolean
        get() = GL11C.glGetBoolean(GL11C.GL_DEPTH_WRITEMASK)
        set(value) = GL11C.glDepthMask(value)

    var func: CompareFunction
        get() = CompareFunction(GL11C.glGetInteger(GL11C.GL_DEPTH_FUNC))
        set(value) = GL11C.glDepthFunc(value.i)

    var range: ClosedFloatingPointRange<Double>
        get() {
            GL11C.nglGetDoublev(GL11C.GL_DEPTH_RANGE, offHeapAdr)
            val p = offHeapPtr.toPtr<Double>()
            return p[0]..p[1]
        }
        set(value) = GL11C.glDepthRange(value.start, value.endInclusive)

    var rangef: ClosedFloatingPointRange<Float>
        get() {
            GL11C.nglGetFloatv(GL11C.GL_DEPTH_RANGE, offHeapAdr)
            val p = offHeapPtr.toPtr<Float>()
            return p[0]..p[1]
        }
        set(value) = GL41C.glDepthRangef(value.start, value.endInclusive)

    var clamp: Boolean
        get() = GL11C.glIsEnabled(GL32C.GL_DEPTH_CLAMP)
        set(value) = when {
            value -> GL11C.glEnable(GL32.GL_DEPTH_CLAMP)
            else -> GL11C.glDisable(GL32.GL_DEPTH_CLAMP)
        }
}