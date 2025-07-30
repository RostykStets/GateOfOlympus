package com.wildtraff.gatesofolympus.data.dto

data class GameLevelDto(
    val id: Int = 0,
    val score: Int = 0,
    val stars: List<Boolean> = listOf(false, false, false),
    val scoreLimit: Int = 30,
    val isPassed: Boolean = false
)
