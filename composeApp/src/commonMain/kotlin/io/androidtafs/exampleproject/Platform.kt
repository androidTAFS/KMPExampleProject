package io.androidtafs.exampleproject

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform