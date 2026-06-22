package io.androidtafs.exampleproject.ui.feature.practice_holder.task_4

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
fun HackerAccessScreen() {
    val neonCyan = Color(0xFF00F3FF)
    val darkBackground = Color(0xFF050505)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .border(1.dp, neonCyan.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Terminal,
                    contentDescription = null,
                    tint = neonCyan,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.width(12.dp))
                Text(
                    text = "СИСТЕМА ВЗЛОМА v1.0",
                    color = neonCyan,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
            }

            Spacer(Modifier.height(32.dp))

            Text(
                text = "ВНИМАНИЕ: ОБНАРУЖЕНО ВТОРЖЕНИЕ",
                color = Color.Red,
                fontSize = 12.sp,
                fontWeight = FontWeight.Black
            )

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = neonCyan),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "ПОДКЛЮЧИТЬСЯ",
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            Spacer(Modifier.height(12.dp))

            Text(
                text = "IP: 192.168.1.104 | ПИНГ: 24мс",
                color = neonCyan.copy(alpha = 0.4f),
                fontSize = 10.sp
            )
        }
    }
}

@Preview
@Composable
fun HackerAccessScreenPreview(){
    MaterialTheme{
        HackerAccessScreen()
    }
}