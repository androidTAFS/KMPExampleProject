package io.androidtafs.exampleproject.ui.feature.practice

import androidx.lifecycle.ViewModel
import io.androidtafs.exampleproject.domain.model.PracticeTask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PracticeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PracticeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { it.copy(isLoading = true) }
        val mockTasks = listOf(
            PracticeTask(1, "Привет, Compose!", "Составте экран, данный мной", "📝"),
        )
        _uiState.update { it.copy(tasks = mockTasks, isLoading = false) }
    }
}