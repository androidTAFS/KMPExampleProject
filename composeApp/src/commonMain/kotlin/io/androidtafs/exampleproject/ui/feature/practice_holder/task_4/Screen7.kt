package io.androidtafs.exampleproject.ui.feature.practice_holder.task_4

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

// ==========================================
// 1. Модель данных для улучшений
// ==========================================
data class Meteorite(
    val xOffset: Int,
    val yOffset: Int,
    val size: Int,
    val reward: Int
)

// ==========================================
// 2. Индикатор прочности щита
// ==========================================
@Composable
fun ShieldBar(hp: Int) {
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text("ПРОЧНОСТЬ ЭНЕРГОЩИТА: $hp%", color = Color.White, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { hp / 100f },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = if (hp < 30) Color.Red else Color(0xFF00FFCC)
        )
    }
}

// ==========================================
// 3. Главный экран игры
// ==========================================
@Composable
fun MeteoriteDefenseScreen() {
    // 4. Переменные состояния
    var score by remember { mutableStateOf(0) }
    var shieldHp by remember { mutableStateOf(100) }
    var activeMeteorite by remember { mutableStateOf<Meteorite?>(null) }

    var timeLeft by remember { mutableStateOf(0L) }
    val maxTimeForReaction = 1500L

    // 5. LaunchedEffect 1: Генератор метеоритов
    LaunchedEffect(shieldHp) {
        if (shieldHp > 0) {
            while (shieldHp > 0) {
                delay(kotlin.random.Random.nextLong(1000, 2500))

                if (activeMeteorite == null) {
                    activeMeteorite = Meteorite(
                        xOffset = kotlin.random.Random.nextInt(-120, 120),
                        yOffset = kotlin.random.Random.nextInt(-200, 100),
                        size = kotlin.random.Random.nextInt(60, 90),
                        reward = 100
                    )
                    timeLeft = maxTimeForReaction
                }
            }
        }
    }

    // 6. LaunchedEffect 2: Счётчик времени метеорита
    LaunchedEffect(activeMeteorite) {
        if (activeMeteorite != null) {
            while (timeLeft > 0) {
                delay(50)
                timeLeft -= 50
            }
            if (activeMeteorite != null) {
                shieldHp = (shieldHp - 20).coerceAtLeast(0)
                activeMeteorite = null
            }
        }
    }

    // 7. Основной контейнер Box
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0B0E14)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "СБИТО МЕТЕОРИТОВ: $score",
                color = Color(0xFFFFD54F),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            // 8. Панель управления и статус
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ShieldBar(hp = shieldHp)

                Spacer(modifier = Modifier.height(12.dp))

                if (shieldHp <= 0) {
                    Button(
                        onClick = {
                            shieldHp = 100
                            score = 0
                            activeMeteorite = null
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("НАЧАТЬ ЗАНОВО", color = Color.White)
                    }
                }
            }
        }

        // 9. Отрисовка активного метеорита
        activeMeteorite?.let { met ->
            Box(
                modifier = Modifier
                    .offset(x = met.xOffset.dp, y = met.yOffset.dp)
                    .size(met.size.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE57373))
                    .clickable {
                        score += 1
                        activeMeteorite = null
                    },
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = { timeLeft.toFloat() / maxTimeForReaction },
                    color = Color.White,
                    strokeWidth = 4.dp,
                    modifier = Modifier.fillMaxSize().padding(2.dp)
                )
                Text("☄️", fontSize = (met.size / 2.5).sp)
            }
        }
    }
}

// ==========================================
// 10. Предпросмотр (Preview)
// ==========================================
@Preview
@Composable
fun MeteoriteDefensePreview() {
    MaterialTheme {
        MeteoriteDefenseScreen()
    }
}