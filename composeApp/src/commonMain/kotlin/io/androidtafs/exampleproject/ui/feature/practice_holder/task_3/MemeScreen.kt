package io.androidtafs.exampleproject.ui.feature.practice_holder.task_3

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import exampleproject.composeapp.generated.resources.Res
import exampleproject.composeapp.generated.resources.compose_multiplatform
import exampleproject.composeapp.generated.resources.me
import org.jetbrains.compose.resources.painterResource

@Composable
fun MemeScreen(
    state: MemeUiState,
    onAction: (MemeAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            modifier = Modifier
                .padding(top = 16.dp)
                .size(280.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(state.currentImage),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                MemeText(state.currentTopText, Modifier.align(Alignment.TopCenter))
                MemeText(state.currentBottomText, Modifier.align(Alignment.BottomCenter))
            }
        }

        SelectionRow("Выбери верхний текст", state.topTexts, state.selectedTopIndex) {
            onAction(MemeAction.SelectTop(it))
        }

        SelectionRow("Выбери персонажа", state.images.map { "🖼️" }, state.selectedImageIndex) {
            onAction(MemeAction.SelectImage(it))
        }

        SelectionRow("Выбери нижний текст", state.bottomTexts, state.selectedBottomIndex) {
            onAction(MemeAction.SelectBottom(it))
        }

        Button(
            onClick = { onAction(MemeAction.Randomize) },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("🎲 Мне повезёт")
        }
    }
}

@Composable
fun SelectionRow(
    title: String,
    items: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit
) {
    Column {
        Text(title, modifier = Modifier.padding(start = 16.dp), style = MaterialTheme.typography.titleMedium)
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(items) { index, item ->
                FilterChip(
                    selected = index == selectedIndex,
                    onClick = { onSelect(index) },
                    label = { Text(item) }
                )
            }
        }
    }
}

@Composable
fun MemeText(text: String, modifier: Modifier) {
    Text(
        text = text.uppercase(),
        modifier = modifier.padding(8.dp),
        color = Color.White,
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.ExtraBold,
            shadow = Shadow(color = Color.Black, blurRadius = 8f)
        ),
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF0F0F0)
@Composable
fun MemeGeneratorPreview() {
    val mockState = MemeUiState(
        topTexts = listOf("Когда код работает", "Программирование это...", "Мой кот когда"),
        images = listOf(
            Res.drawable.me,
            Res.drawable.compose_multiplatform
        ),
        bottomTexts = listOf("Магия!", "Чистый стресс", "Я голоден"),
        selectedTopIndex = 0,
        selectedImageIndex = 1,
        selectedBottomIndex = 0
    )

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .background(Color.White, RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp))
        ) {
            MemeScreen(
                state = mockState,
                onAction = {}
            )
        }
    }
}