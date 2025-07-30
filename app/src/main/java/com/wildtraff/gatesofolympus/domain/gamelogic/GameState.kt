package com.wildtraff.gatesofolympus.domain.gamelogic

data class GameState(
    val score: Int = 0,
    val lives: Int = 3,
    val brightnessLevel: Int = 0,
    val maxBrightness: Int = 3,
    val isGameOver: Boolean = false
)