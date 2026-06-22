package io.androidtafs.exampleproject.ui.feature.promo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import exampleproject.composeapp.generated.resources.Res
import exampleproject.composeapp.generated.resources.gta_promo
import exampleproject.composeapp.generated.resources.lucy_promo
import io.androidtafs.exampleproject.ui.component.NavRailItem.Companion.items
import org.jetbrains.compose.resources.painterResource

@Composable
fun PromoProfileScreen() {
    val purpleGlow = Color(0xFF8B5CF6)
    val cyanNeon = Color(0xFF06B6D4)
    val darkCard = Color(0xFF111111)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF050505)) // Pure black background
    ) {
        // --- BACKGROUND DECORATION ---
        // A subtle gradient glow in the top corner to add depth
        Box(
            modifier = Modifier
                .size(300.dp)
                .offset(x = (-100).dp, y = (-100).dp)
                .background(Brush.radialGradient(listOf(purpleGlow.copy(0.15f), Color.Transparent)))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // --- HEADER: TOP NAV ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ПАНЕЛЬ УПРАВЛЕНИЯ",
                    style = TextStyle(
                        color = Color.White.copy(alpha = 0.5f),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                )
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.5f),
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(Modifier.height(24.dp))

            // --- USER IDENTITY SECTION ---
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Avatar with a "Glow ring"
                Box(contentAlignment = Alignment.Center) {
                    // Outer Glow
                    Box(Modifier.size(86.dp).background(purpleGlow.copy(0.2f), CircleShape))
                    // Border Ring
                    Box(Modifier.size(76.dp).border(2.dp, Brush.linearGradient(listOf(purpleGlow, cyanNeon)), CircleShape))

                    Surface(
                        modifier = Modifier.size(64.dp),
                        shape = CircleShape,
                        color = Color.DarkGray
                    ) {
                        // IMAGE IDEA: Cool Anime PFP (e.g. Cyberpunk Edgerunners style)
                        Image(
                            painter = painterResource(Res.drawable.lucy_promo),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Spacer(Modifier.width(20.dp))

                Column {
                    Text(
                        text = "ShadowCoder",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Surface(
                        color = purpleGlow.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "LEVEL 12 • JUNIOR DEV",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                            style = TextStyle(
                                color = purpleGlow,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }

            Spacer(Modifier.height(32.dp))

            // --- XP PROGRESS BAR ---
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Опыт (XP)", color = Color.White, fontSize = 14.sp)
                    Text("750 / 1000", color = cyanNeon, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(0.1f))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                            .fillMaxHeight()
                            .background(Brush.horizontalGradient(listOf(purpleGlow, cyanNeon)))
                    )
                }
            }

            Spacer(Modifier.height(32.dp))

            // --- STATS GRID ---
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                ModernStatCard("Проекты", "14", Icons.Default.Code, Modifier.weight(1f))
                ModernStatCard("Стрик", "5 дней", Icons.Default.Bolt, Modifier.weight(1f))
            }

            Spacer(Modifier.height(32.dp))

            // --- SKILL STACK (The "Loadout") ---
            Text("МОЙ СТЭК", color = Color.White.copy(0.5f), fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                SkillBadge("Kotlin")
                SkillBadge("Compose")
                SkillBadge("Android")
                SkillBadge("Clean Coding")
            }

            AchievementSection()
            ActiveProjectCard()
        }
    }
}

@Composable
fun ModernStatCard(label: String, value: String, icon: ImageVector, modifier: Modifier) {
    Surface(
        modifier = modifier,
        color = Color(0xFF111111),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, Color.White.copy(0.05f))
    ) {
        Column(Modifier.padding(20.dp)) {
            Icon(icon, contentDescription = null, tint = Color(0xFF06B6D4), modifier = Modifier.size(24.dp))
            Spacer(Modifier.height(16.dp))
            Text(label, color = Color.Gray, fontSize = 12.sp)
            Text(value, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Black)
        }
    }
}

@Composable
fun SkillBadge(name: String) {
    Surface(
        color = Color.White.copy(0.05f),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.White.copy(0.1f))
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun AchievementSection() {
    val cyanNeon = Color(0xFF06B6D4)
    val purpleGlow = Color(0xFF8B5CF6)

    Column(modifier = Modifier.padding(top = 32.dp)) {
        Text(
            "ДОСТИЖЕНИЯ",
            color = Color.White.copy(0.5f),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AchievementIcon("🚀", "First Launch", cyanNeon)
            AchievementIcon("🎨", "UI Master", purpleGlow)
            AchievementIcon("⚡", "Fast Coder", Color(0xFFFACC15)) // Gold
        }
    }
}

@Composable
fun ActiveProjectCard() {
    val cyanNeon = Color(0xFF06B6D4)
    Column(modifier = Modifier.padding(top = 32.dp, bottom = 40.dp)) {
        Text(
            "В РАЗРАБОТКЕ",
            color = Color.White.copy(0.5f),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )
        Spacer(Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            shape = RoundedCornerShape(24.dp),
            border = BorderStroke(1.dp, Color.White.copy(0.1f))
        ) {
            Box {
                // IMAGE IDEA: A screenshot of a funny meme or a code snippet
                Image(
                    painter = painterResource(Res.drawable.gta_promo),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Dark Overlay with Gradient
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(0.9f)))))

                // Content on top of the image
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(20.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(8.dp).background(cyanNeon, CircleShape))
                        Spacer(Modifier.width(8.dp))
                        Text("Готово на 85%", color = cyanNeon, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun AchievementIcon(emoji: String, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(56.dp)
                .background(color.copy(0.1f), CircleShape)
                .border(1.dp, color.copy(0.5f), CircleShape)
        ) {
            Text(emoji, fontSize = 24.sp)
            // Subtle Glow behind the emoji
            Box(Modifier.size(20.dp).background(color.copy(0.3f), CircleShape))
        }
        Text(
            text = label,
            color = Color.White.copy(0.7f),
            fontSize = 10.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun PromoRoadmapScreen() {
    val cyan = Color(0xFF06B6D4)
    val purple = Color(0xFF8B5CF6)
    val lockedGray = Color(0xFF222222)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF050505))
    ) {
        // Decorative background glow
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(400.dp)
                .background(Brush.radialGradient(listOf(cyan.copy(0.1f), Color.Transparent)))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Text(
                "ПЛАН ЗАХВАТА МИРА (ROADMAP)",
                style = TextStyle(
                    color = purple,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
            )
            Text(
                "Project: Android Alpha",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Black
            )

            Spacer(Modifier.height(32.dp))

            // --- THE TIMELINE ---

            // Completed Quests
            RoadmapNode(
                title = "Основы UI",
                subtitle = "Стек, Box, Column. Первая визитка.",
                date = "30.03 - 05.04",
                status = RoadmapStatus.COMPLETED,
                color = cyan
            )

            RoadmapNode(
                title = "Списки и Дизайн",
                subtitle = "LazyColumn и верстка карточек.",
                date = "06.04 - 12.04",
                status = RoadmapStatus.COMPLETED,
                color = cyan
            )

            RoadmapNode(
                title = "State: Живой экран",
                subtitle = "Интерактив, mutableStateOf, Лайки.",
                date = "13.04 - 19.04",
                status = RoadmapStatus.COMPLETED,
                color = cyan
            )

            // ACTIVE QUEST (Right Now!)
            RoadmapNode(
                title = "Навигация",
                subtitle = "Создание 'Мультивселенной' в приложении.",
                date = "20.04 - 26.04",
                status = RoadmapStatus.ACTIVE,
                color = purple
            )

            // Future Quests
            RoadmapNode(
                title = "Анимации",
                subtitle = "VFX: Оживляем интерфейс.",
                date = "27.04 - 03.05",
                status = RoadmapStatus.LOCKED,
                color = lockedGray
            )

            RoadmapNode(
                title = "Работа с Сетью",
                subtitle = "Библиотека Coil: Фото из интернета.",
                date = "04.05 - 10.05",
                status = RoadmapStatus.LOCKED,
                color = lockedGray
            )

            RoadmapNode(
                title = "Финальный Босс: APK",
                subtitle = "Иконка, Релиз и экспорт на телефон.",
                date = "25.05 - 31.05",
                status = RoadmapStatus.LOCKED,
                color = lockedGray
            )

            Spacer(Modifier.height(40.dp))
        }
    }
}

enum class RoadmapStatus { COMPLETED, ACTIVE, LOCKED }

@Composable
fun RoadmapNode(
    title: String,
    subtitle: String,
    date: String,
    status: RoadmapStatus,
    color: Color
) {
    Row(modifier = Modifier.height(IntrinsicSize.Min)) {
        // Timeline Column
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(if (status == RoadmapStatus.LOCKED) Color.Transparent else color, CircleShape)
                    .border(2.dp, color, CircleShape)
            )
            // Vertical Line
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(2.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(color, color.copy(alpha = 0.1f))
                        )
                    )
            )
        }

        Spacer(Modifier.width(20.dp))

        // Content Card
        Column(modifier = Modifier.padding(bottom = 32.dp)) {

            val cardAlpha = if (status == RoadmapStatus.LOCKED) 0.3f else 1f
            Surface(
                color = if (status == RoadmapStatus.ACTIVE) color.copy(0.1f) else Color(0xFF111111).copy(cardAlpha),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, if (status == RoadmapStatus.ACTIVE) color else Color.White.copy(0.05f))
            ) {
                Column(Modifier.padding(16.dp).fillMaxWidth()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = title,
                            color = if (status == RoadmapStatus.LOCKED) Color.Gray else Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.weight(1f)
                        )
                            Box(Modifier.size(8.dp).background(color, CircleShape)) // Pulsing dot

                    }
                    Text(
                        text = subtitle,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun PromoFeedScreen() {
    val purple = Color(0xFF8B5CF6)
    val cyan = Color(0xFF06B6D4)
    val pink = Color(0xFFD946EF)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF050505))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(40.dp))

            // Header
            Text(
                "АРСЕНАЛ ПРОЕКТОВ",
                style = TextStyle(
                    color = cyan,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
            )
            Text(
                "Выбери свою цель",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Black
            )

            Spacer(Modifier.height(24.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(bottom = 40.dp)
            ) {
                item {
                    ProjectFeaturedCard(
                        title = "Генератор Мемов",
                        tags = listOf("State", "Images"),
                        complexity = "Easy",
                        color = purple
                    )
                }

                items(projectList) { project ->
                    ProjectSmallCard(project)
                }
            }
        }
    }
}

data class ProjectItem(val title: String, val desc: String, val icon: String, val color: Color)

val projectList = listOf(
    ProjectItem("Рандом для Настолок", "Кубики и жеребьевка", "🎲", Color(0xFF03DAC5)),
    ProjectItem("Саунд-Пак", "Звуки и цитаты", "🔊", Color(0xFFFF7597)),
    ProjectItem("Магический Шар", "Принимает решения за тебя", "🔮", Color(0xFFFACC15)),
    ProjectItem("Мультивселенная", "Твои герои в одном месте", "🦸", Color(0xFF8B5CF6))
)

@Composable
fun ProjectFeaturedCard(title: String, tags: List<String>, complexity: String, color: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, color.copy(0.5f))
    ) {
        Box(modifier = Modifier.background(Color(0xFF111111))) {
            // Background Glow Effect
            Box(Modifier.fillMaxSize().background(Brush.radialGradient(listOf(color.copy(0.15f), Color.Transparent))))

            Column(Modifier.padding(20.dp).fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(color = color, shape = CircleShape) {
                            Text("NEW", modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp), fontSize = 8.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        }
                        Spacer(Modifier.width(8.dp))
                        Text(complexity, color = color, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                    Text(title, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Black)
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    tags.forEach { tag ->
                        Surface(color = Color.White.copy(0.05f), shape = RoundedCornerShape(4.dp)) {
                            Text(tag, color = Color.Gray, modifier = Modifier.padding(4.dp), fontSize = 10.sp)
                        }
                    }
                }
            }

            Text("🖼️", fontSize = 60.sp, modifier = Modifier.align(Alignment.BottomEnd).offset(x = 10.dp, y = 10.dp).alpha(0.3f))
        }
    }
}

@Composable
fun ProjectSmallCard(project: ProjectItem) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color(0xFF111111),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.White.copy(0.05f))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(48.dp).background(project.color.copy(0.1f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(project.icon, fontSize = 24.sp)
            }
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Text(project.title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(project.desc, color = Color.Gray, fontSize = 12.sp)
            }
            Icon(Icons.Default.ArrowForwardIos, contentDescription = null, tint = Color.DarkGray, modifier = Modifier.size(12.dp))
        }
    }
}

@Composable
fun FinalPromoBanner() {
    val cyan = Color(0xFF00F3FF)
    val purple = Color(0xFFBD00FF)
    val darkBack = Color(0xFF050505)

    // The Canvas
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBack)
    ) {
        // 1. BACKGROUND DECORATIONS
        // Large purple glow in the bottom left
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .size(600.dp)
                .background(Brush.radialGradient(listOf(purple.copy(0.15f), Color.Transparent)))
        )
        // Large cyan glow in the top right
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(600.dp)
                .background(Brush.radialGradient(listOf(cyan.copy(0.15f), Color.Transparent)))
        )

        // 2. THE CONTENT LAYER
        Column(
            modifier = Modifier.fillMaxSize().padding(40.dp),
            verticalArrangement = Arrangement.Center
        ) {
            // TITLE SECTION
            Text(
                text = "Разработка мобильных приложений на Android",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 4.sp,
                    shadow = Shadow(color = cyan, blurRadius = 20f)
                )
            )
            Text(
                text = "КУРС ПО JETPACK COMPOSE 2026",
                color = cyan,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                fontSize = 18.sp
            )

            Spacer(Modifier.height(60.dp))

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy((-60).dp), // Overlapping
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // We use a helper to wrap your screens in a "Phone" shell
                    PromoPhoneFrame(rotationY = 25f, scale = 0.8f) { PromoProfileScreen() }
                    PromoPhoneFrame(
                        rotationY = 20f,
                        scale = 0.9f,
                        isElevated = true
                    ) { PromoFeedScreen() }
                    PromoPhoneFrame(rotationY = 15f, scale = 0.8f) { PromoRoadmapScreen() }
                }
            }
        }

        // 4. FLOATING DECORATIONS (Floating Code Elements)
        Text(
            "fun main() { }",
            modifier = Modifier.align(Alignment.TopStart).padding(100.dp).alpha(0.1f),
            color = cyan,
            fontFamily = FontFamily.Monospace
        )
        Text(
            "Modifier.graphicsLayer { }",
            modifier = Modifier.align(Alignment.BottomEnd).padding(100.dp).alpha(0.1f),
            color = purple,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Composable
fun PromoPhoneFrame(
    rotationY: Float,
    scale: Float,
    isElevated: Boolean = false,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(width = 400.dp, height = 800.dp)
            .graphicsLayer {
                this.rotationY = -rotationY // Tilt effect
                this.rotationZ = 5f         // Slight lean
                this.cameraDistance = 12f * density
                this.scaleX = scale
                this.scaleY = scale
                if (isElevated) {
                    this.translationY = -40f // Lift the middle one
                }
            }
            .shadow(
                elevation = 40.dp,
                shape = RoundedCornerShape(32.dp),
                ambientColor = Color.Cyan,
                spotColor = Color.Cyan
            ),
        shape = RoundedCornerShape(32.dp),
        color = Color(0xFF111111),
        border = BorderStroke(2.dp, Color.White.copy(alpha = 0.1f))
    ) {
        // This clips the screen inside the phone frame
        Box(modifier = Modifier.fillMaxSize().padding(8.dp).clip(RoundedCornerShape(24.dp))) {
            content()
        }
    }
}

// --- FINAL PROMO PREVIEW ---
@Preview(
    name = "Final Landscape Promo",
    device = "spec:width=1450dp,height=1080dp,orientation=landscape",
    showBackground = true
)
@Composable
fun PreviewFinalPromo() {
    MaterialTheme {
        FinalPromoBanner()
    }
}

// --- PREVIEW: DEVELOPER PROFILE ---
@Preview(name = "Profile Screen")
@Composable
fun PreviewPromoProfile() {
    MaterialTheme {
        PromoProfileScreen()
    }
}

// --- PREVIEW: PROJECT FEED ---
@Preview(name = "Feed Screen")
@Composable
fun PreviewPromoFeed() {
    MaterialTheme {
        PromoFeedScreen()
    }
}

// --- PREVIEW: ROADMAP ---
@Preview(name = "Roadmap Screen")
@Composable
fun PreviewPromoRoadmap() {
    MaterialTheme {
        PromoRoadmapScreen()
    }
}