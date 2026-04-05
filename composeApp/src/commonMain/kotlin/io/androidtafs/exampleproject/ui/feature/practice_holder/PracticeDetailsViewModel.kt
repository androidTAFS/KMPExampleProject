package io.androidtafs.exampleproject.ui.feature.practice_holder

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PracticeDetailsViewModel(val taskId: Int) : ViewModel() {
    private val _uiState = MutableStateFlow(PracticeDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadTaskInfo()
    }

    private fun loadTaskInfo() {
        _uiState.update {
            it.copy(title = "Задание №$taskId")
        }
    }
}

data class PracticeDetailsUiState(
    val title: String = ""
)