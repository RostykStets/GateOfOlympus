package com.wildtraff.gatesofolympus.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.wildtraff.gatesofolympus.domain.gamelogic.GameState
import kotlinx.coroutines.delay

@Composable
fun GameLoop(
    state: GameState,
    onBrightnessUpdate: (Int) -> Unit
) {
    LaunchedEffect(state.isGameOver) {
        while (!state.isGameOver) {
            delay(calculateSpeed(state.score))
            val newLevel = (state.brightnessLevel + 1) % (state.maxBrightness + 1)
            onBrightnessUpdate(newLevel)
        }
    }
}

fun calculateSpeed(score: Int): Long {
    return when {
        score >= 20 -> 100L
        score >= 10 -> 200L
        score >= 5 -> 300L
        else -> 500L
    }
}