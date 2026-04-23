package io.androidtafs.exampleproject.ui.feature.practice_holder.task_3

import exampleproject.composeapp.generated.resources.Res
import exampleproject.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.DrawableResource

data class MemeUiState(
    val topTexts: List<String> = emptyList(),
    val images: List<DrawableResource> = emptyList(),
    val bottomTexts: List<String> = emptyList(),

    val selectedTopIndex: Int = 0,
    val selectedImageIndex: Int = 0,
    val selectedBottomIndex: Int = 0
) {
    val currentTopText get() = topTexts.getOrNull(selectedTopIndex) ?: ""
    val currentImage get() = images.getOrNull(selectedImageIndex) ?: Res.drawable.compose_multiplatform
    val currentBottomText get() = bottomTexts.getOrNull(selectedBottomIndex) ?: ""
}

sealed interface MemeAction {
    data class SelectTop(val index: Int) : MemeAction
    data class SelectImage(val index: Int) : MemeAction
    data class SelectBottom(val index: Int) : MemeAction
    object Randomize : MemeAction
}