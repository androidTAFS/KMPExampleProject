package io.androidtafs.exampleproject.ui.feature.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class StudentTaskEntry(
    val id: Int,
    val title: String,
    val icon: String,
    val gradientColors: List<Color>
)

@Composable
fun StudentNav(
    onTaskSelected: (Int) -> Unit
) {
    val tasks = listOf(
        StudentTaskEntry(1, "Хакерский терминал", "💻", listOf(Color(0xFF0F2027), Color(0xFF203A43))),
        StudentTaskEntry(3, "Генератор мемов", "😂", listOf(Color(0xFF533483), Color(0xFFE94560))),
        StudentTaskEntry(4, "Кибер-охотник", "🎯", listOf(Color(0xFF1A1A2E), Color(0xFFBD00FF))),
        StudentTaskEntry(5, "Квантовый реактор", "☀️", listOf(Color(0xFF070B12), Color(0xFF00FFCC))),
        StudentTaskEntry(6, "Подводная бездна", "🤿", listOf(Color(0xFF050E1E), Color(0xFF4FC3F7))),
        StudentTaskEntry(7, "Flappy Bird", "🐦", listOf(Color(0xFF70C5CF), Color(0xFF55A630))),
        StudentTaskEntry(8, "Визуальный роман", "🎭", listOf(Color(0xFF1A1A2E), Color(0xFFD62828)))
    )

    Surface(color = Color(0xFF0F0F1A)) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Меню заданий",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                color = Color.White,
                fontWeight = FontWeight.Black,
                fontSize = 28.sp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(tasks, key = { it.id }) { task ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .clickable { onTaskSelected(task.id) },
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Black)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = task.icon,
                                    fontSize = 48.sp,
                                    modifier = Modifier.padding(bottom = 12.dp)
                                )
                                Text(
                                    text = task.title,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
