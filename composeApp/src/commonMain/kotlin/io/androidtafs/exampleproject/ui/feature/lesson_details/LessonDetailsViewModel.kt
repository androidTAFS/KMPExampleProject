package io.androidtafs.exampleproject.ui.feature.lesson_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.androidtafs.exampleproject.data.repository.LessonsRepository
import io.androidtafs.exampleproject.domain.model.Slide
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class LessonDetailsViewModel(private val lessonId: Int) : ViewModel() {
    private val lesson = LessonsRepository.lessons.first { it.id == lessonId }

    private val _currentSlideIndex = MutableStateFlow(0)
    val currentSlideIndex = _currentSlideIndex.asStateFlow()

    val uiState = _currentSlideIndex.map { index ->
        val currentSlide = lesson.slides[index]
        LessonDetailsUiState(
            lessonTitle = lesson.title,
            currentSlide = currentSlide,
            progress = (index + 1).toFloat() / lesson.slides.size,
            isLastSlide = index == lesson.slides.lastIndex,
            isFirstSlide = index == 0,
            practiceId = lesson.practiceId
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), LessonDetailsUiState())

    fun nextSlide() {
        if (_currentSlideIndex.value < lesson.slides.lastIndex) {
            _currentSlideIndex.value++
        }
    }

    fun previousSlide() {
        if (_currentSlideIndex.value > 0) {
            _currentSlideIndex.value--
        }
    }
}

data class LessonDetailsUiState(
    val lessonTitle: String = "",
    val currentSlide: Slide? = null,
    val progress: Float = 0f,
    val isLastSlide: Boolean = false,
    val isFirstSlide: Boolean = true,
    val practiceId: Int = 0
)