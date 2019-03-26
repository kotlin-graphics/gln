package gln.sampler

import gln.gl
import java.nio.IntBuffer

object GlSamplerDSL {

    var name = 0
}

object GlSamplersDSL {

    lateinit var names: IntBuffer

    // --- [ glBindSamplers ] ---

    fun bind(first: Int = 0) = gl.bindSamplers(first, GlSamplers(names))
}