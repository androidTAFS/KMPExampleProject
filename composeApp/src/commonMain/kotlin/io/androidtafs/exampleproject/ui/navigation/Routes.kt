package io.androidtafs.exampleproject.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {
    @Serializable
    data object Lessons : Route

    @Serializable
    data object Practice : Route
    data class LessonDetails(val id: Int) : Route
    data class PracticeDetails(val id: Int) : Route
}