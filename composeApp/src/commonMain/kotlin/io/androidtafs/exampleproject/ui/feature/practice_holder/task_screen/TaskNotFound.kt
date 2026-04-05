package io.androidtafs.exampleproject.ui.feature.practice_holder.task_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.androidtafs.exampleproject.ui.preview.DevicePreviews
import io.androidtafs.exampleproject.ui.theme.AndroidSchoolTheme

@Composable
fun TaskNotFound() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Задание не найдено",
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@DevicePreviews
@Composable
private fun TaskNotFoundPreview() {
    AndroidSchoolTheme {
        Scaffold { TaskNotFound() }
    }
}