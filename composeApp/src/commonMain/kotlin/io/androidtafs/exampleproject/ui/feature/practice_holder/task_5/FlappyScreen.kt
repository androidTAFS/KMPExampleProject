package io.androidtafs.exampleproject.ui.feature.practice_holder.task_5

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FlappyBirdScreen(
    birdPainter: Painter? = null,
    pipePainter: Painter? = null,
    viewModel: FlappyBirdViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF70C5CF)) // Sky Blue
            .pointerInput(Unit) {
                detectTapGestures {
                    viewModel.onTap()
                }
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val scaleX = size.width / FlappyBirdViewModel.BOARD_WIDTH
            val scaleY = size.height / FlappyBirdViewModel.BOARD_HEIGHT

            withTransform({
                scale(scaleX, scaleY, pivot = Offset(0f, 0f))
            }) {

                // 1. Draw Pipes (Using images if available)
                state.pipes.forEach { pipe ->
                    val gapTop = pipe.gapY - pipe.gapHeight / 2
                    val gapBottom = pipe.gapY + pipe.gapHeight / 2

                    if (pipePainter != null) {
                        // Top Pipe (Rotated 180 degrees so the pipe opening faces down)
                        val topPipeSize = Size(pipe.width, gapTop)
                        withTransform({
                            translate(left = pipe.x, top = 0f)
                            rotate(degrees = 180f, pivot = Offset(pipe.width / 2, gapTop / 2))
                        }) {
                            with(pipePainter) {
                                draw(size = topPipeSize)
                            }
                        }

                        // Bottom Pipe
                        val bottomPipeSize = Size(pipe.width, FlappyBirdViewModel.GROUND_Y - gapBottom)
                        translate(left = pipe.x, top = gapBottom) {
                            with(pipePainter) {
                                draw(size = bottomPipeSize)
                            }
                        }
                    } else {
                        // Fallback vector drawing if no image is supplied
                        drawRect(
                            color = Color(0xFF73C73E),
                            topLeft = Offset(pipe.x, 0f),
                            size = Size(pipe.width, gapTop)
                        )
                        drawRect(
                            color = Color(0xFF539E1B),
                            topLeft = Offset(pipe.x, 0f),
                            size = Size(pipe.width, gapTop),
                            style = Stroke(width = 4f)
                        )

                        drawRect(
                            color = Color(0xFF73C73E),
                            topLeft = Offset(pipe.x, gapBottom),
                            size = Size(pipe.width, FlappyBirdViewModel.GROUND_Y - gapBottom)
                        )
                        drawRect(
                            color = Color(0xFF539E1B),
                            topLeft = Offset(pipe.x, gapBottom),
                            size = Size(pipe.width, FlappyBirdViewModel.GROUND_Y - gapBottom),
                            style = Stroke(width = 4f)
                        )
                    }
                }

                // 2. Draw Ground Base
                drawRect(
                    color = Color(0xFFD2B48C),
                    topLeft = Offset(0f, FlappyBirdViewModel.GROUND_Y),
                    size = Size(
                        FlappyBirdViewModel.BOARD_WIDTH,
                        FlappyBirdViewModel.BOARD_HEIGHT - FlappyBirdViewModel.GROUND_Y
                    )
                )
                drawRect(
                    color = Color(0xFF55A630),
                    topLeft = Offset(0f, FlappyBirdViewModel.GROUND_Y),
                    size = Size(FlappyBirdViewModel.BOARD_WIDTH, 40f)
                )

                // 3. Draw Bird (Using image if available)
                val birdY = state.birdY
                val birdX = FlappyBirdViewModel.BIRD_X
                val birdRadius = FlappyBirdViewModel.BIRD_RADIUS

                if (birdPainter != null) {
                    val birdSize = Size(birdRadius * 2, birdRadius * 2)
                    // Translate top-left of the bounding box relative to center birdX/birdY
                    translate(left = birdX - birdRadius, top = birdY - birdRadius) {
                        with(birdPainter) {
                            draw(size = birdSize)
                        }
                    }
                } else {
                    // Fallback vector drawing if no image is supplied
                    val beakPath = Path().apply {
                        moveTo(birdX + birdRadius - 5f, birdY - 10f)
                        lineTo(birdX + birdRadius + 18f, birdY)
                        lineTo(birdX + birdRadius - 5f, birdY + 10f)
                        close()
                    }
                    drawPath(path = beakPath, color = Color(0xFFF77F00))

                    drawCircle(
                        color = Color(0xFFF9C80E),
                        radius = birdRadius,
                        center = Offset(birdX, birdY)
                    )
                    drawCircle(
                        color = Color(0xFFD6A000),
                        radius = birdRadius,
                        center = Offset(birdX, birdY),
                        style = Stroke(width = 3f)
                    )
                    drawCircle(
                        color = Color.White,
                        radius = 8f,
                        center = Offset(birdX + 10f, birdY - 10f)
                    )
                    drawCircle(
                        color = Color.Black,
                        radius = 4f,
                        center = Offset(birdX + 12f, birdY - 10f)
                    )
                }
            }
        }

        // 4. Score & Overlay UI Layer
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Score: ${state.score}",
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                when (state.gameState) {
                    GameState.NOT_STARTED -> {
                        Text(
                            text = "TAP TO START",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    GameState.GAME_OVER -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "GAME OVER",
                                fontSize = 44.sp,
                                fontWeight = FontWeight.Black,
                                color = Color(0xFFD62828)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "High Score: ${state.highScore}",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "TAP TO RESTART",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        }
                    }
                    GameState.PLAYING -> {}
                }
            }
        }
    }
}