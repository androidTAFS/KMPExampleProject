package io.androidtafs.exampleproject.ui.feature.practice_holder

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PracticeDetailsRoute(
    taskId: Int,
    viewModel: PracticeDetailsViewModel = viewModel { PracticeDetailsViewModel(taskId) },
    onBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    PracticeDetailsScreen(
        title = state.title,
        taskId = taskId,
        onBack = onBack
    )
}