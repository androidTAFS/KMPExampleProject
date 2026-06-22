package io.androidtafs.exampleproject.ui.feature.practice_holder.task_5

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.random.Random

class FlappyBirdViewModel : ViewModel() {

    companion object {
        // Virtual coordinate boundaries
        const val BOARD_WIDTH = 1000f
        const val BOARD_HEIGHT = 1600f
        const val GROUND_Y = 1450f

        // Physics and Dimensions
        const val BIRD_X = 250f
        const val BIRD_RADIUS = 35f

        private const val FRAME_RATE_MS = 16L // ~60 FPS
        private const val GRAVITY = 0.7f
        private const val JUMP_VELOCITY = -15f
        private const val TERMINAL_VELOCITY = 18f
        private const val PIPE_SPEED = 7.5f
        private const val MIN_GAP_Y = 350f
        private const val MAX_GAP_Y = 1100f
    }

    private val _state = MutableStateFlow(FlappyState())
    val state: StateFlow<FlappyState> = _state.asStateFlow()

    private var gameJob: Job? = null
    private var birdVelocity = 0f

    fun onTap() {
        when (_state.value.gameState) {
            GameState.NOT_STARTED -> startGame()
            GameState.PLAYING -> jump()
            GameState.GAME_OVER -> resetGame()
        }
    }

    private fun startGame() {
        _state.value = FlappyState(
            gameState = GameState.PLAYING,
            highScore = _state.value.highScore
        )
        birdVelocity = JUMP_VELOCITY
        startGameLoop()
    }

    private fun jump() {
        birdVelocity = JUMP_VELOCITY
    }

    private fun resetGame() {
        startGame()
    }

    private fun startGameLoop() {
        gameJob?.cancel()
        gameJob = viewModelScope.launch {
            while (isActive && _state.value.gameState == GameState.PLAYING) {
                updateGame()
                delay(FRAME_RATE_MS)
            }
        }
    }

    private fun updateGame() {
        val currentState = _state.value

        // 1. Update Physics (Gravity & Velocity)
        birdVelocity = (birdVelocity + GRAVITY).coerceAtMost(TERMINAL_VELOCITY)
        val newBirdY = currentState.birdY + birdVelocity

        // 2. Move existing pipes and filter off-screen ones
        val updatedPipes = currentState.pipes
            .map { it.copy(x = it.x - PIPE_SPEED) }
            .filter { it.x + it.width > 0 }
            .toMutableList()

        // Spawn a new pipe if the last one has traveled far enough
        val lastPipe = updatedPipes.lastOrNull()
        if (lastPipe == null || lastPipe.x < BOARD_WIDTH - 450f) {
            val randomGapY = Random.nextFloat() * (MAX_GAP_Y - MIN_GAP_Y) + MIN_GAP_Y
            updatedPipes.add(Pipe(x = BOARD_WIDTH, gapY = randomGapY))
        }

        // 3. Collision and Score Detection
        var collision = false
        var newScore = currentState.score

        val birdLeft = BIRD_X - BIRD_RADIUS
        val birdRight = BIRD_X + BIRD_RADIUS
        val birdTop = newBirdY - BIRD_RADIUS
        val birdBottom = newBirdY + BIRD_RADIUS

        // Boundary/Ground Collision
        if (birdBottom >= GROUND_Y || birdTop <= 0) {
            collision = true
        }

        // Pipe Collision & Passing Check
        for (i in updatedPipes.indices) {
            val pipe = updatedPipes[i]
            val pipeLeft = pipe.x
            val pipeRight = pipe.x + pipe.width
            val gapTop = pipe.gapY - pipe.gapHeight / 2
            val gapBottom = pipe.gapY + pipe.gapHeight / 2

            // Check if bird occupies horizontal position of the pipe
            if (birdRight > pipeLeft && birdLeft < pipeRight) {
                if (birdTop < gapTop || birdBottom > gapBottom) {
                    collision = true
                    break
                }
            }

            // Check if the bird successfully crossed the current pipe midpoint
            if (!pipe.passed && pipeLeft + pipe.width / 2 < BIRD_X) {
                updatedPipes[i] = pipe.copy(passed = true)
                newScore++
            }
        }

        if (collision) {
            endGame()
        } else {
            _state.value = currentState.copy(
                birdY = newBirdY,
                pipes = updatedPipes,
                score = newScore
            )
        }
    }

    private fun endGame() {
        gameJob?.cancel()
        val finalScore = _state.value.score
        val currentHighScore = _state.value.highScore
        _state.value = _state.value.copy(
            gameState = GameState.GAME_OVER,
            highScore = maxOf(finalScore, currentHighScore)
        )
    }

    override fun onCleared() {
        super.onCleared()
        gameJob?.cancel()
    }
}
enum class GameState {
    NOT_STARTED, PLAYING, GAME_OVER
}

data class Pipe(
    val x: Float,
    val gapY: Float,
    val width: Float = 160f,
    val gapHeight: Float = 330f,
    val passed: Boolean = false
)

data class FlappyState(
    val gameState: GameState = GameState.NOT_STARTED,
    val birdY: Float = 800f,
    val pipes: List<Pipe> = emptyList(),
    val score: Int = 0,
    val highScore: Int = 0
)