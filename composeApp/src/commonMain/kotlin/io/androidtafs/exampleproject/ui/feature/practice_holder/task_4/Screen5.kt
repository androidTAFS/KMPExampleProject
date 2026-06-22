package io.androidtafs.exampleproject.ui.feature.practice_holder.task_4

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun CyberBountyHunterScreen() {
    // --- ПАМЯТЬ ИГРЫ ---
    var credits by remember { mutableStateOf(0L) }
    var clickPower by remember { mutableStateOf(1L) }
    var drones by remember { mutableStateOf(0L) }

    // Автоматическая логика (Ранг)
    val rank = when {
        credits < 100 -> "НОВИЧОК"
        credits < 1000 -> "НАЕМНИК"
        credits < 5000 -> "МАСТЕР"
        else -> "ЛЕГЕНДА ПУСТОШИ"
    }

    // --- ПАССИВНЫЙ ДОХОД (РАБОТАЕТ РАЗ В СЕКУНДУ) ---
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            credits += (drones * 5)
        }
    }

    val neonCyan = Color(0xFF00F3FF)
    val neonPurple = Color(0xFFBD00FF)

    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFF050505)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // ИНФОРМАЦИЯ
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "РАНГ: $rank",
                    color = neonPurple,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text(
                    text = "$credits CR",
                    color = neonCyan,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Black
                )
                Row {
                    Text("УРОН: $clickPower", color = Color.Gray, fontSize = 12.sp)
                    Spacer(Modifier.width(16.dp))
                    Text("ДРОНЫ: $drones", color = Color.Gray, fontSize = 12.sp)
                }
            }

            // ГЛАВНАЯ КНОПКА
            Button(
                onClick = { credits += clickPower },
                modifier = Modifier.size(200.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = neonPurple)
            ) {
                Text("ОХОТА", fontSize = 28.sp, fontWeight = FontWeight.Black, color = Color.White)
            }

            // МАГАЗИН
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                val weaponCost = clickPower * 15
                val droneCost = (drones + 1) * 50

                // Улучшение клика
                Button(
                    onClick = {
                        if (credits >= weaponCost) {
                            credits -= weaponCost
                            clickPower += 2
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF111111))
                ) {
                    Text("УЛУЧШИТЬ ПУШКУ (ЦЕНА: $weaponCost)", color = neonCyan, fontSize = 12.sp)
                }

                // Покупка авто-дрона
                Button(
                    onClick = {
                        if (credits >= droneCost) {
                            credits -= droneCost
                            drones += 1
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF111111))
                ) {
                    Text("КУПИТЬ ДРОНА (ЦЕНА: $droneCost)", color = neonPurple, fontSize = 12.sp)
                }
            }
        }
    }
}

@Preview
@Composable
fun BountyHunterPreview() {
    MaterialTheme {
        CyberBountyHunterScreen()
    }
}