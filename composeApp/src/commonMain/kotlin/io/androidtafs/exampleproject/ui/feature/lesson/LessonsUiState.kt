package io.androidtafs.exampleproject.ui.feature.lesson

import io.androidtafs.exampleproject.domain.model.LessonPreview

data class LessonsUiState(
    val lessonPreviews: List<LessonPreview> = emptyList(),
    val isLoading: Boolean = false
)

sealed interface LessonsAction {
    data class OnLessonSelect(val id: Int) : LessonsAction
}