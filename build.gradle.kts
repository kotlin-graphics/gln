import kx.LwjglModules.*
import kx.KxProject.*
import kx.kxImplementation
import kx.lwjglImplementation
import kx.lwjglTestImplementation

plugins {
    val build = "0.7.0+79"
    id("kx.kotlin.11") version build
    id("kx.lwjgl") version build
    id("kx.dokka") version build
    id("kx.publish") version build
    java
}

version = "0.5.2+22"

dependencies {

    implementation(kotlin("reflect"))

    kxImplementation(unsigned, kool, glm, gli)

    //    compile group: 'org.jetbrains.kotlin.kapt', name: 'org.jetbrains.kotlin.kapt.gradle.plugin', version: '1.3.0-rc-146'

    lwjglImplementation(glfw, jemalloc, openal, opengl, opengles, stb)

//    lwjglTestImplementation(opengl)
}