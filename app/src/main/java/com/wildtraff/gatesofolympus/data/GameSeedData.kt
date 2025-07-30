package com.wildtraff.gatesofolympus.data

import com.wildtraff.gatesofolympus.data.entities.GameLevel

object GameSeedData {

    fun generateInitialData(): List<GameLevel> {
        return List(9) {
            GameLevel(
                id = 0,
                score = 0,
                stars = listOf(false, false, false),
                scoreLimit = 30,
                isPassed = false
            )
        }
    }
}