package com.wildtraff.gatesofolympus.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameLevel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val score: Int = 0,
    val stars: List<Boolean> = listOf(false, false, false),
    val scoreLimit: Int = 30,
    val isPassed: Boolean = false
)