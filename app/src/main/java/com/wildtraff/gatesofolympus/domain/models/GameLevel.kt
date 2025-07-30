package com.wildtraff.gatesofolympus.domain.models

data class GameLevel(
    val id: Int = 0,
    val score: Int = 0,
    val stars: List<Boolean> = listOf(false, false, false),
    val scoreLimit: Int = 30,
    val isPassed: Boolean = false
)
