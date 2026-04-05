package io.androidtafs.exampleproject

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.androidtafs.exampleproject.ui.feature.main.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "ExampleProject",
    ) {
        App()
    }
}