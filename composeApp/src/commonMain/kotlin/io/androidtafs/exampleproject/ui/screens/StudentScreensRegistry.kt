package io.androidtafs.exampleproject.ui.screens

import androidx.compose.runtime.Composable

object StudentScreensRegistry {
    private val screens = mutableMapOf<Int, @Composable () -> Unit>()

    fun register(taskId: Int, screen: @Composable () -> Unit) {
        screens[taskId] = screen
    }

    @Composable
    fun getScreen(taskId: Int): @Composable () -> Unit {
        return screens[taskId] ?: { PlaceholderScreen(taskId) }
    }
}
