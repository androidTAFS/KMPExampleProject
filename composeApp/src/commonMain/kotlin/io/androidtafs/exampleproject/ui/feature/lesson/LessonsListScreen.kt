package io.androidtafs.exampleproject.ui.feature.lesson

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.androidtafs.exampleproject.domain.model.LessonPreview
import io.androidtafs.exampleproject.ui.preview.DevicePreviews
import io.androidtafs.exampleproject.ui.theme.AndroidSchoolTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonsListScreen(
    state: LessonsUiState,
    onAction: (LessonsAction) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Курс Android разработки",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        if (state.isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 340.dp),
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(state.lessonPreviews) { lesson ->
                    LessonCard(
                        lessonPreview = lesson,
                        onClick = { onAction(LessonsAction.OnLessonSelect(lesson.id)) }
                    )
                }
            }
        }
    }
}

@Composable
private fun LessonCard(
    lessonPreview: LessonPreview,
    onClick: () -> Unit
) {
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primaryContainer,
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                            )
                        ),
                        shape = RoundedCornerShape(20.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = lessonPreview.icon,
                    style = MaterialTheme.typography.displaySmall
                )
            }

            Spacer(Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = lessonPreview.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF1A1C1E)
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = lessonPreview.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 18.sp
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Начать →",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@DevicePreviews
@Composable
private fun LessonsScreenPreview() {
    val mockState = LessonsUiState(
        lessonPreviews = listOf(
            LessonPreview(
                id = 1,
                title = "1. Первая программа",
                description = "Учимся выводить текст в консоль и запускать код.",
                icon = "👋"
            ),
            LessonPreview(
                id = 2,
                title = "2. Переменные и Типы",
                description = "Как компьютер запоминает данные (числа, строки).",
                icon = "📦"
            ),
            LessonPreview(
                id = 3,
                title = "3. Создаем интерфейс",
                description = "Знакомство с кнопками (Button) и текстом (Text).",
                icon = "📱"
            ),
            LessonPreview(
                id = 4,
                title = "4. Цвета и отступы",
                description = "Делаем наше приложение красивым с помощью Modifier.",
                icon = "🎨"
            ),
            LessonPreview(
                id = 5,
                title = "5. Логика программы",
                description = "Используем if/else, чтобы приложение принимало решения.",
                icon = "🤖"
            ),
            LessonPreview(
                id = 6,
                title = "6. Списки данных",
                description = "Как отобразить много элементов сразу с LazyColumn.",
                icon = "📜"
            )
        ),
        isLoading = false
    )

    AndroidSchoolTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            LessonsListScreen(
                state = mockState,
                onAction = {}
            )
        }
    }
}