package io.androidtafs.exampleproject.ui.feature.practice_holder.task_3

import androidx.lifecycle.ViewModel
import exampleproject.composeapp.generated.resources.Res
import exampleproject.composeapp.generated.resources.me
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MemeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MemeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                topTexts = listOf("Когда ты кодишь...", "Моё лицо когда", "Друг говорит"),
                images = listOf(
                    Res.drawable.me
                ),
                bottomTexts = listOf("Это и правда работает!", "Стоп, что?", "Я гений!", "Пора перекусить!")
            )
        }
    }

    fun onAction(action: MemeAction) {
        when (action) {
            is MemeAction.SelectTop -> _uiState.update { it.copy(selectedTopIndex = action.index) }
            is MemeAction.SelectImage -> _uiState.update { it.copy(selectedImageIndex = action.index) }
            is MemeAction.SelectBottom -> _uiState.update { it.copy(selectedBottomIndex = action.index) }
            MemeAction.Randomize -> {
                _uiState.update { state ->
                    state.copy(
                        selectedTopIndex = state.topTexts.indices.random(),
                        selectedImageIndex = state.images.indices.random(),
                        selectedBottomIndex = state.bottomTexts.indices.random()
                    )
                }
            }
        }
    }
}