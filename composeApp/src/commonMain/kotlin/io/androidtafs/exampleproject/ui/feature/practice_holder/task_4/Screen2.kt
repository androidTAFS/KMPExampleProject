package io.androidtafs.exampleproject.ui.feature.practice_holder.task_4

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CyberPlayerScreen() {
    val neonPurple = Color(0xFFBD00FF)

    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFF0A0A0A)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .width(280.dp)
                .background(Color(0xFF151515), RoundedCornerShape(24.dp))
                .border(1.dp, neonPurple.copy(0.3f), RoundedCornerShape(24.dp))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .background(neonPurple.copy(0.1f), RoundedCornerShape(16.dp))
                    .border(2.dp, neonPurple, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("🎵", fontSize = 60.sp)
            }

            Spacer(Modifier.height(20.dp))

            Text("NIGHT CITY PHONK", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("ARTIST: SHADOW_DEV", color = neonPurple, fontSize = 12.sp)

            Spacer(Modifier.height(24.dp))

            Box(Modifier.fillMaxWidth().height(4.dp).background(Color.DarkGray, CircleShape)) {
                Box(Modifier.fillMaxWidth(0.6f).fillMaxHeight().background(neonPurple, CircleShape))
            }

            Spacer(Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("⏮", color = Color.White, fontSize = 24.sp)
                Surface(shape = CircleShape, color = neonPurple, modifier = Modifier.size(50.dp)) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("▶", color = Color.Black, fontSize = 20.sp)
                    }
                }
                Text("⏭", color = Color.White, fontSize = 24.sp)
            }
        }
    }
}

@Preview
@Composable
fun CyberPlayerPreview(){
    MaterialTheme {
        CyberPlayerScreen()
    }
}