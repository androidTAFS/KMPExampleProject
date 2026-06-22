package io.androidtafs.exampleproject.ui.feature.practice_holder.task_4


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import kotlin.math.pow

// ==========================================
// ШАГ 1: Модель данных для магазина
// ==========================================
data class UpgradeItem(
    val id: String,
    val name: String,
    val description: String,
    val baseCost: Long,
    val costMultiplier: Double,
    val level: Int,
    val icon: String
) {
    // Формула автоматического расчета цены:
    val currentCost: Long
        get() = (baseCost * costMultiplier.pow(level)).toLong()
}

// ==========================================
// ШАГ 2: Строка Улучшения (UpgradeRow)
// ==========================================
@Composable
fun UpgradeRow(upgrade: UpgradeItem, canAfford: Boolean, onBuyClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF131A2B))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(upgrade.icon + " " + upgrade.name, color = Color.White, fontWeight = FontWeight.Bold)
                Text(upgrade.description, color = Color.Gray, fontSize = 11.sp)
                Text("Ур. " + upgrade.level, color = Color(0xFF00FFCC), fontSize = 11.sp)
            }
            Button(onClick = onBuyClick, enabled = canAfford) {
                Text(upgrade.currentCost.toString() + " E")
            }
        }
    }
}

// ==========================================
// ШАГ 3: Объявляем главный экран (Голова)
// ==========================================
@Composable
fun SolarHarvesterScreen() {

    // ==========================================
    // ШАГ 4: Переменные состояния (Память)
    // ==========================================
    var energy by remember { mutableStateOf(0L) } // Сколько энергии накопили
    var isRunning by remember { mutableStateOf(false) } // Включен ли сборщик
    var heatLevel by remember { mutableStateOf(0f) } // Перегрев (0.0f - холод, 1.0f - взрыв)

    // Уровни наших улучшений
    var solarLevel by remember { mutableStateOf(1) }
    var coolingLevel by remember { mutableStateOf(1) }
    var batteryLevel by remember { mutableStateOf(1) }

    // ==========================================
    // ШАГ 5: Динамическая математика
    // ==========================================
    val energyPerTick = solarLevel * 8L // Сколько энергии дает один тик
    val heatPerTick = 0.04f / (1f + (coolingLevel * 0.15f)) // Нагрев ядра
    val maxCapacity = batteryLevel * 400L // Лимит батареи

    // ==========================================
    // ШАГ 6: Автоматический реактор (LaunchedEffect)
    // ==========================================
    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (isRunning) {
                delay(300) // Ждем 0.3 секунды
                heatLevel += heatPerTick

                // Проверка на взрыв реактора
                if (heatLevel >= 1.0f) {
                    isRunning = false
                    energy /= 2 // Наказание: теряем половину энергии
                    heatLevel = 0f
                }

                // Начисляем энергию не выше лимита батареи
                if (energy < maxCapacity) {
                    energy = (energy + energyPerTick).coerceAtMost(maxCapacity)
                }
            }
        }
    }

    // ==========================================
    // ШАГ 7: Основной контейнер экрана (Box)
    // ==========================================
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF070B12)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            // ==========================================
            // ШАГ 8: Панель мониторов (Dashboard)
            // ==========================================
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("КВАНТОВЫЙ РЕАКТОР", color = Color.Gray, fontSize = 12.sp)
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "$energy / $maxCapacity E",
                    color = Color(0xFF00FFCC),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(16.dp))
                Text("Температура ядра реактора:", color = Color.White, fontSize = 12.sp)
                Spacer(Modifier.height(4.dp))

                // Шкала перегрева ядра (краснеет при опасности)
                LinearProgressIndicator(
                    progress = { heatLevel },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp)
                        .clip(RoundedCornerShape(6.dp)),
                    color = if (heatLevel > 0.75f) Color.Red else Color(0xFF00FFCC)
                )
            }

            // ==========================================
            // ШАГ 9: Панель ручного контроля
            // ==========================================
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { isRunning = !isRunning },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isRunning) Color(0xFFD32F2F) else Color(0xFF388E3C)
                    )
                ) {
                    Text(if (isRunning) "СТОП" else "СТАРТ", fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = {
                        if (heatLevel > 0f) {
                            heatLevel = (heatLevel - 0.15f).coerceAtLeast(0f)
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
                    enabled = heatLevel > 0f
                ) {
                    Text("ОХЛАДИТЬ", fontWeight = FontWeight.Bold)
                }
            }

            // ==========================================
            // ШАГ 10: Магазин Улучшений (LazyColumn)
            // ==========================================
            val upgrades = listOf(
                UpgradeItem("solar", "Солнечные Панели", "+$energyPerTick E/тик", 100L, 1.4, solarLevel, "☀️"),
                UpgradeItem("heatsink", "Охлаждение радиатора", "-15% нагрева", 150L, 1.3, coolingLevel, "❄️"),
                UpgradeItem("battery", "Емкость Батареи", "+400 макс. энергии", 200L, 1.5, batteryLevel, "🔋")
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(upgrades) { item ->
                    UpgradeRow(
                        upgrade = item,
                        canAfford = energy >= item.currentCost,
                        onBuyClick = {
                            energy -= item.currentCost
                            when (item.id) {
                                "solar" -> solarLevel += 1
                                "heatsink" -> coolingLevel += 1
                                "battery" -> batteryLevel += 1
                            }
                        }
                    )
                }
            }

        } // Конец Column
    } // Конец Box
} // Конец функции SolarHarvesterScreen

// ==========================================
// ШАГ 11: Предпросмотр Экрана (Preview)
// ==========================================
@Preview
@Composable
fun SolarHarvesterPreview() {
    MaterialTheme {
        SolarHarvesterScreen()
    }
}