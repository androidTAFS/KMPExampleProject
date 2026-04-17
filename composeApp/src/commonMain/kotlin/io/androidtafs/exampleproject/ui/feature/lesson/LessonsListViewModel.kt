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
            LessonPreview(2, "Ленивые элементы", "Знакомство с оптимизацией списков", "\uD83C\uDFEE"),
            LessonPreview(2, "ViewModel, Route, Screen", "Основы архитектуры UI", "\uD83C\uDF1A"),
        )

        _uiState.update { it.copy(lessonPreviews = mockLessonPreviews, isLoading = false) }
    }
}