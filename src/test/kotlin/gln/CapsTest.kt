package gln

import gln.cap.Caps
import io.kotest.core.spec.style.StringSpec
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.system.MemoryUtil.NULL
import java.io.File

val isNotCI: Boolean
    get() = System.getenv("GITHUB_ACTIONS") != "true" && System.getenv("TRAVIS") != "true"

class CapsTest : StringSpec() {

    init {
        "caps" {

            if (isNotCI) {

                // Initialize GLFW. Most GLFW functions will not work before doing this.
                if (!glfwInit())
                    throw IllegalStateException("Unable to initialize GLFW")

                // Configure GLFW
                glfwDefaultWindowHints() // optional, the current window hints are already the default
                glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE) // the window will stay hidden after creation
                glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE) // the window will be resizable

                // Create the window
                val window = glfwCreateWindow(300, 300, "Hello World!", NULL, NULL)
                // Make the OpenGL context current
                glfwMakeContextCurrent(window)

                val caps = Caps()
                caps.writeTo("opengl.caps")

                val capsFile = File("opengl.caps")
                assert(capsFile.exists() && capsFile.canRead())
                assert(capsFile.delete())
            }
        }
    }
}