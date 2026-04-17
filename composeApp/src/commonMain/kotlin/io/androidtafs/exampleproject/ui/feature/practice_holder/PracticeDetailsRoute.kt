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
    PracticeDetailsScreen(
        title = taskId.toString(),
        taskId = taskId,
        onBack = onBack
    )
}