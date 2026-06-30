package io.androidtafs.exampleproject.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface StudentRoute : NavKey {
    @Serializable
    data object Home : StudentRoute

    data class Task(val id: Int) : StudentRoute
}
