import kx.KxProject.gli
import kx.KxProject.glm
import kx.KxProject.kool
import kx.KxProject.unsigned
import kx.Lwjgl
import kx.Lwjgl.Modules.*
import kx.implementation

//import kx.LwjglModules.*
//import kx.KxProject.*
//import kx.kxImplementation

plugins {
    val build = "0.7.3+41"
    id("kx.kotlin") version build
//    id("kx.dokka") version build
    id("kx.publish") version build
    id("kx.dynamic-align") version build
    id("kx.util") version build
}

dependencies {

    implementation(kotlin("reflect"))

    implementation(unsigned, kool, glm, gli)

    //    compile group: 'org.jetbrains.kotlin.kapt', name: 'org.jetbrains.kotlin.kapt.gradle.plugin', version: '1.3.0-rc-146'

    Lwjgl {
        implementation(glfw, jemalloc, openal, opengl, opengles, stb)
        testImplementation(opengl)
    }
}