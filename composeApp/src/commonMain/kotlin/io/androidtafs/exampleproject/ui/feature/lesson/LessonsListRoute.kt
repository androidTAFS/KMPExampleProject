package io.androidtafs.exampleproject.ui.feature.lesson

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LessonsListRoute(
    viewModel: LessonsListViewModel = viewModel { LessonsListViewModel() },
    onNavigateToDetail: (Int) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LessonsListScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is LessonsAction.OnLessonSelect -> onNavigateToDetail(action.id)
            }
        }
    )
}