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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LootCardScreen() {
    val neonYellow = Color(0xFFF3FF00)

    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFF050505)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(width = 220.dp, height = 300.dp)
                .background(Color(0xFF111111), RoundedCornerShape(12.dp))
                .border(2.dp, neonYellow, RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
        ) {
            Box(Modifier.fillMaxWidth().height(100.dp).background(neonYellow.copy(0.1f)))

            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("⚔️", fontSize = 80.sp, modifier = Modifier.padding(top = 20.dp))

                Spacer(Modifier.weight(1f))

                Text(
                    text = "КЛИНOK КОДА",
                    color = neonYellow,
                    fontWeight = FontWeight.Black,
                    fontSize = 20.sp
                )

                Row(modifier = Modifier.padding(vertical = 8.dp)) {
                    Text("УРОН: ", color = Color.Gray, fontSize = 12.sp)
                    Text("999", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = neonYellow),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text("КУПИТЬ", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            }

            Surface(
                color = neonYellow,
                modifier = Modifier.align(Alignment.TopEnd).padding(8.dp),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text("LEGENDARY", modifier = Modifier.padding(4.dp), fontSize = 8.sp, fontWeight = FontWeight.Black)
            }
        }
    }
}

@Preview
@Composable
fun LootCardPreview(){
    MaterialTheme {
        LootCardScreen()
    }
}