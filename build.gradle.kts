import org.gradle.internal.os.OperatingSystem.*
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    java
    kotlin("jvm") version "1.3.72"
    maven
    //    id "org.jetbrains.kotlin.kapt" version "1.3.10"
    id("org.jetbrains.dokka") version "0.10.1"
    id("com.github.johnrengelman.shadow").version("5.2.0")
}

val group = "com.github.kotlin_graphics"
val moduleName = "$group.gln"
val kotestVersion = "4.0.5"

val kx = "com.github.kotlin-graphics"
val unsignedVersion = "87630c4d"
val koolVersion = "3be0cc2f"
val glmVersion = "3cb433d5"
val gliVersion = "b0ecb4cd"
val lwjglVersion = "3.2.3"
val lwjglNatives = when (current()) {
    WINDOWS -> "windows"
    LINUX -> "linux"
    else -> "macos"
}

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
    jcenter()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("$kx:kotlin-unsigned:$unsignedVersion")
    implementation("$kx:kool:$koolVersion")
    implementation("$kx:glm:$glmVersion")
    implementation("$kx:gli:$gliVersion")

    listOf("", "-glfw", "-jemalloc", "-openal", "-opengl", "-opengles", "-stb").forEach {
        implementation("org.lwjgl:lwjgl$it:$lwjglVersion")
        implementation("org.lwjgl:lwjgl$it:$lwjglVersion:natives-$lwjglNatives")
    }

    //    compile group: 'org.jetbrains.kotlin.kapt', name: 'org.jetbrains.kotlin.kapt.gradle.plugin', version: '1.3.0-rc-146'

    listOf("-glfw").forEach {
        testImplementation("org.lwjgl:lwjgl$it:$lwjglVersion")
        testRuntimeOnly("org.lwjgl:lwjgl$it:$lwjglVersion:natives-$lwjglNatives")
    }

    listOf("runner-junit5", "assertions-core", "runner-console"/*, "property"*/).forEach {
        testImplementation("io.kotest:kotest-$it-jvm:$kotestVersion")
    }
}

tasks {
    val dokka by getting(DokkaTask::class) {
        outputFormat = "html"
        outputDirectory = "$buildDir/dokka"
    }

    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-XXLanguage:+InlineClasses", "-Xjvm-default=enable")
        }
        sourceCompatibility = "1.8"
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
        sourceCompatibility = "1.8"
    }

    withType<Test> { useJUnitPlatform() }
}

val dokkaJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles Kotlin docs with Dokka"
    archiveClassifier.set("javadoc")
    from(tasks.dokka)
}

val sourceJar = task("sourceJar", Jar::class) {
    dependsOn(tasks["classes"])
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

artifacts {
    archives(sourceJar)
    archives(dokkaJar)
}

// == Add access to the 'modular' variant of kotlin("stdlib"): Put this into a buildSrc plugin and reuse it in all your subprojects
configurations.all {
    attributes.attribute(TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, 8)
}
