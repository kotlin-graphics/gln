package gln.`object`

import gln.QueryTarget
import kool.Adr
import kool.adr
import kool.rem
import org.lwjgl.opengl.GL15C
import org.lwjgl.opengl.GL20C
import java.nio.IntBuffer

inline class GLquery(val i: Int) {

    // --- [ glBeginQuery ] ---

    infix fun begin(target: QueryTarget) = GL15C.glBeginQuery(target.i, i)

    // --- [ glEndQuery ] ---

    infix fun end(target: QueryTarget) = GL15C.glEndQuery(target.i)


    fun <R> use(target: QueryTarget, block: (GLquery) -> R): R {
        GL15C.glBeginQuery(target.i, i)
        return block(this).also { GL15C.glEndQuery(target.i) }
    }

    // --- [ glIsQuery ] ---

    val valid: Boolean
        get() = GL20C.glIsQuery(i)
}

inline class GLqueries(val i: IntBuffer) {

    inline val rem: Int
        get() = i.rem

    inline val adr: Adr
        get() = i.adr
}