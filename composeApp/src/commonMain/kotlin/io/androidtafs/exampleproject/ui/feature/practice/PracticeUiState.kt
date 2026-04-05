package io.androidtafs.exampleproject.ui.feature.practice

import io.androidtafs.exampleproject.domain.model.PracticeTask

data class PracticeUiState(
    val tasks: List<PracticeTask> = emptyList(),
    val isLoading: Boolean = false
)

sealed interface PracticeAction {
    data class OnTaskSelect(val id: Int) : PracticeAction
}