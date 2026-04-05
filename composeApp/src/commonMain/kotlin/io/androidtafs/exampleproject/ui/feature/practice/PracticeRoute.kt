package io.androidtafs.exampleproject.ui.feature.practice

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PracticeRoute(
    viewModel: PracticeViewModel = viewModel { PracticeViewModel() },
    onNavigateToTask: (Int) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    PracticeScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is PracticeAction.OnTaskSelect -> onNavigateToTask(action.id)
            }
        }
    )
}