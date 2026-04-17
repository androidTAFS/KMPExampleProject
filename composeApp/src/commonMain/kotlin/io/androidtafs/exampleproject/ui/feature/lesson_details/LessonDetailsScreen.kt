package io.androidtafs.exampleproject.ui.feature.lesson_details

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material.icons.rounded.RocketLaunch
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.androidtafs.exampleproject.domain.model.Slide
import io.androidtafs.exampleproject.ui.preview.DevicePreviews
import io.androidtafs.exampleproject.ui.theme.AndroidSchoolTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonDetailsScreen(
    state: LessonDetailsUiState,
    onAction: (LessonDetailsAction) -> Unit
) {
    Scaffold(
        topBar = {
            Surface(shadowElevation = 4.dp) {
                Column {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                state.lessonTitle,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = { onAction(LessonDetailsAction.OnBack) }) {
                                Icon(Icons.AutoMirrored.Rounded.ArrowBack, "Назад")
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                        )
                    )
                    LinearProgressIndicator(
                        progress = { state.progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp),
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.primaryContainer,
                        strokeCap = StrokeCap.Round
                    )
                }
            }
        },
        bottomBar = {
            Surface(
                tonalElevation = 8.dp,
                shadowElevation = 16.dp,
                modifier = Modifier.navigationBarsPadding()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = { onAction(LessonDetailsAction.OnPrevious) },
                        enabled = !state.isFirstSlide,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.AutoMirrored.Rounded.KeyboardArrowLeft, null)
                        Spacer(Modifier.width(4.dp))
                        Text("Назад", style = MaterialTheme.typography.labelLarge)
                    }

                    if (state.isLastSlide) {
                        Button(
                            onClick = { onAction(LessonDetailsAction.OnGoToPractice(state.practiceId)) },
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            ),
                            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
                        ) {
                            Text("К практике", style = MaterialTheme.typography.titleMedium)
                            Spacer(Modifier.width(8.dp))
                            Icon(Icons.Rounded.RocketLaunch, null)
                        }
                    } else {
                        Button(
                            onClick = { onAction(LessonDetailsAction.OnNext) },
                            shape = RoundedCornerShape(16.dp),
                            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
                        ) {
                            Text("Далее", style = MaterialTheme.typography.titleMedium)
                            Spacer(Modifier.width(8.dp))
                            Icon(Icons.AutoMirrored.Rounded.KeyboardArrowRight, null)
                        }
                    }
                }
            }
        }
    ) { padding ->
        state.currentSlide?.let { slide ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = slide.title,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(24.dp))

                if (slide.imageRes != null) {
                    Card(
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                        )
                    ) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Rounded.Image,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                                modifier = Modifier.size(48.dp)
                            )
                        }
                    }
                    Spacer(Modifier.height(24.dp))
                }

                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = slide.description,
                        style = MaterialTheme.typography.bodyLarge.copy(lineHeight = 24.sp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                slide.codeExample?.let { code ->
                    Spacer(Modifier.height(24.dp))
                    CodeBlock(code)
                }

                Spacer(Modifier.height(40.dp))
            }
        }
    }
}

@Composable
private fun CodeBlock(code: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF2B2B2B))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF3C3F41))
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.size(10.dp).background(Color(0xFFFF5F56), CircleShape))
            Spacer(Modifier.width(6.dp))
            Box(Modifier.size(10.dp).background(Color(0xFFFFBD2E), CircleShape))
            Spacer(Modifier.width(6.dp))
            Box(Modifier.size(10.dp).background(Color(0xFF27C93F), CircleShape))

            Spacer(Modifier.weight(1f))

            Text(
                text = "Kotlin",
                style = MaterialTheme.typography.labelSmall,
                color = Color.LightGray.copy(alpha = 0.7f)
            )
        }

        Text(
            text = code,
            modifier = Modifier
                .padding(16.dp)
                .horizontalScroll(rememberScrollState()),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = FontFamily.Monospace,
                color = Color(0xFF76D1FF),
                fontWeight = FontWeight.Medium
            )
        )
    }
}


sealed interface LessonDetailsAction {
    data object OnNext : LessonDetailsAction
    data object OnPrevious : LessonDetailsAction
    data object OnBack : LessonDetailsAction
    data class OnGoToPractice(val id: Int) : LessonDetailsAction
}

@DevicePreviews
@Composable
fun LessonDetailsScreenPreview() {
    val mockSlide = Slide(
        title = "Компоненты: Column и Row",
        description = "Column (Колонка) — ставит элементы друг под друга вертикально.\n" +
                "Row (Строка) — ставит элементы в ряд горизонтально. " +
                "Это основные инструменты для создания любого интерфейса.",
        codeExample = """
Column {
    Text("Верхний элемент")
    Text("Нижний элемент")
}

Row {
    Icon(Icons.Default.Phone, null)
    Text("8-800-555-35-35")
}
        """.trimIndent()
    )

    val mockState = LessonDetailsUiState(
        lessonTitle = "Основы Android и Compose",
        currentSlide = mockSlide,
        progress = 0.66f,
        isLastSlide = false,
        isFirstSlide = false,
        practiceId = 1
    )

    AndroidSchoolTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            LessonDetailsScreen(
                state = mockState,
                onAction = {}
            )
        }
    }
}

@DevicePreviews
@Composable
fun LessonDetailsLastSlidePreview() {
    val mockState = LessonDetailsUiState(
        lessonTitle = "Основы Android и Compose",
        currentSlide = Slide(
            title = "Практическое задание",
            description = "Пришло время закрепить теорию! Создай свою визитку."
        ),
        progress = 1.0f,
        isLastSlide = true,
        isFirstSlide = false,
        practiceId = 1
    )

    AndroidSchoolTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            LessonDetailsScreen(
                state = mockState,
                onAction = {}
            )
        }
    }
}