package io.androidtafs.exampleproject.ui.feature.lesson

import androidx.lifecycle.ViewModel
import io.androidtafs.exampleproject.domain.model.LessonPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LessonsListViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LessonsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadLessons()
    }

    private fun loadLessons() {
        _uiState.update { it.copy(isLoading = true) }

        val mockLessonPreviews = listOf(
            LessonPreview(1, "Привет, Android!", "Первое приложение и знакомство с IDE", "🚀"),
        )

        _uiState.update { it.copy(lessonPreviews = mockLessonPreviews, isLoading = false) }
    }
}