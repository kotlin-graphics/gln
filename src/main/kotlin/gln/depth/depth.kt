package gln.depth

import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL32
import org.lwjgl.opengl.GL41

inline fun depth(block: ObjectDepth.() -> Unit) = ObjectDepth.block()

object ObjectDepth {

    val never = Func.never
    val less = Func.less
    val equal = Func.equal
    val lEqual = Func.lEqual
    val greater = Func.greater
    val notEqual = Func.notEqual
    val gEqual = Func.gEqual
    val always = Func.always

    var test = false
        set(value) {
            if (value)
                GL11.glEnable(GL11.GL_DEPTH_TEST)
            else
                GL11.glDisable(GL11.GL_DEPTH_TEST)
        }
    var mask = true
        set(value) {
            GL11.glDepthMask(value)
            field = value
        }
    var func = Func.less
        set(value) {
            GL11.glDepthFunc(func.i)
            field = value
        }
    var range = 0.0 .. 1.0
        set(value) {
            GL11.glDepthRange(value.start, value.endInclusive)
            field = value
        }
    var rangef = 0f .. 1f
        set(value) {
            GL41.glDepthRangef(value.start, value.endInclusive)
            field = value
        }
    var clamp = false
        set(value) {
            if (value)
                GL11.glEnable(GL32.GL_DEPTH_CLAMP)
            else
                GL11.glDisable(GL32.GL_DEPTH_CLAMP)
        }

    enum class Func(val i: Int) { never(GL11.GL_NEVER), less(GL11.GL_LESS), equal(GL11.GL_EQUAL), lEqual(GL11.GL_LEQUAL),
        greater(GL11.GL_GREATER), notEqual(GL11.GL_NOTEQUAL), gEqual(GL11.GL_GEQUAL), always(GL11.GL_ALWAYS)
    }
}