# gln

[![Build Status](https://github.com/kotlin-graphics/gln/workflows/build/badge.svg)](https://github.com/kotlin-graphics/gln/actions?workflow=build)
[![license](https://img.shields.io/badge/License-Apache%202.0-orange.svg)](https://github.com/kotlin-graphics/gln/blob/master/LICENSE) 
[![Release](https://jitpack.io/v/kotlin-graphics/gln.svg)](https://jitpack.io/#kotlin-graphics/gln) 
![Size](https://github-size-badge.herokuapp.com/kotlin-graphics/gln.svg)
[![Github All Releases](https://img.shields.io/github/downloads/kotlin-graphics/gln/total.svg)]()

OpenGL Next, functional programming


A wrapper around OpenGL aimed to improve the dev experience by making gl code compact, easier (also allocation-wise) and type-safe (by converting those gl int constants into enums).

To show you a practical example, you can take a look at the different code snippets on the [gli README](https://github.com/kotlin-graphics/gli)

Intensive usage of inline classes and enums.. use them as a class or an enum, compiled as `Int`s

#### Buffer

A buffer in means of OpenGL can be used for vertex attributes, indices, uniform data, atomic counters, texture data, and shader storage data.

This

```kotlin
    val arrayBufferName = glGenBuffers()
    glBindBuffer(GL_ARRAY_BUFFER, arrayBufferName)
    glBufferData(GL_ARRAY_BUFFER, positionData, GL_STATIC_DRAW)
    glBindBuffer(GL_ARRAY_BUFFER, 0)

    val elementBufferName = glGenBuffers()
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, elementBufferName)
    glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementData, GL_STATIC_DRAW)
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0)
```

We can see a lot of redundancy here, starting from the word "Buffer". 

Second, we can enclose in a lambda whatever we want to execute when a buffer gets bound.

Next, we can get rid of the target repetition, it can be specified just once, at the begin of the scoping.

All successive calls will refer to that target.

All of this, turns out as

```kotlin
    val arrayBuffer = glGenBuffers()
    arrayBuffer.bound(ARRAY) {
        data(positionData, STATIC_DRAW)
    }
```

We can push even further and specify the buffer name just once, and leave out the usage (default to `STATIC_DRAW`) and 
have everything as

```kotlin
glGenBuffers(::elementBuffer).bound(ELEMENT_ARRAY) { data(elementData) }
```

Thanks to the inline function, the compiler will take the burden to take our code and automatically transform it as you 
see on top, the verbose traditional version

Object-oriented design offers also to a much comfortable way to get and set parameters.

Let's say we want to retrieve the size of a buffer. The old-school way would require you to write something like:

```kotlin
glBindBuffer(GL_ARRAY_BUFFER, buffer)
val bufferSize /* : Int */ = glGetBufferParameteriv(GL_ARRAY_BUFFER, GL_BUFFER_SIZE)
glBindBuffer(GL_ARRAY_BUFFER, 0)
``` 

Again, syntax redundancy can be avoided, so with gln you can simply type

```kotlin
val bufferSize = buffer.bound(ARRAY) { size }
```

The nice thing is that we do know the type of request, so we can deliver it directly as it should be logically.
For example, since `GL_BUFFER_IMMUTABLE_STORAGE` returns either `GL_FALSE` or `GL_TRUE`

```kotlin
glBindBuffer(GL_ARRAY_BUFFER, buffer)
val bufferImmutability /* : Boolean */ = glGetBufferParameteriv(GL_ARRAY_BUFFER, GL_BUFFER_IMMUTABLE_STORAGE) == GL_TRUE
glBindBuffer(GL_ARRAY_BUFFER, 0)
```

it makes sense to return directly a `Boolean`

```kotlin
val bufferImmutability /* : Boolean */ = buffer.bound(ARRAY) { immutableStorage }
```

This of course applies also for other variable types, such as the inlined enum `BufferAccess` or the typealiased  integer `MapBufferFlags`.


### Manual

[here](https://github.com/kotlin-graphics/gln/blob/master/src/main/kotlin/gln/manual.md)

### Build-logic and platform dependencies

The build logic has been extracted in dedicated [plugins](https://github.com/elect86/build-logic), as well as the versioning in specific platform [plugins](https://github.com/elect86/platforms).

In order to import kool you need then to add the repository where these plugins are getting published for the time being.

In Gradle KTS you can do that by adding the following to your `settings.gradle.kts`:

```kotlin
pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.repsy.io/mvn/elect/kx")
    }
}
```

