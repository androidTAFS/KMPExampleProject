package io.androidtafs.exampleproject.ui.feature.lesson_details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LessonDetailsRoute(
    lessonId: Int,
    onNavigateToPractice: (Int) -> Unit,
    onBack: () -> Unit
) {
    val viewModel = viewModel { LessonDetailsViewModel(lessonId) }
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LessonDetailsScreen(
        state = state,
        onAction = { action ->
            when (action) {
                LessonDetailsAction.OnNext -> viewModel.nextSlide()
                LessonDetailsAction.OnPrevious -> viewModel.previousSlide()
                LessonDetailsAction.OnBack -> onBack()
                is LessonDetailsAction.OnGoToPractice -> onNavigateToPractice(action.id)
            }
        }
    )
}