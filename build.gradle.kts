import kx.LwjglModules.*
import kx.KxProject.*
import kx.kxImplementation
import kx.lwjglImplementation

plugins {
    val build = "0.6.2"
    id("kx.kotlin.11") version build
    id("kx.lwjgl") version build
    id("kx.dokka") version build
    java
    `maven-publish`
}

group = "com.github.kotlin.graphics"
version = "0.5.3"

repositories {
    maven("https://repo.repsy.io/mvn/elect/kx")
    maven("https://jitpack.io")
}

dependencies {

    implementation(kotlin("reflect"))

    kxImplementation(unsigned, kool, glm, gli)

    //    compile group: 'org.jetbrains.kotlin.kapt', name: 'org.jetbrains.kotlin.kapt.gradle.plugin', version: '1.3.0-rc-146'

    lwjglImplementation(glfw, jemalloc, openal, opengl, opengles, stb)
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}