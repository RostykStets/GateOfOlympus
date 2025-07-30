package com.wildtraff.gatesofolympus.data.repository.interfaces

import com.wildtraff.gatesofolympus.data.dto.GameLevelDto
import kotlinx.coroutines.flow.Flow

interface IGameLevelRepository {
    suspend fun insertAll()

    fun getGameLevelByIdOrNull(id: Int): Flow<GameLevelDto?>

    fun getFirstLockedGameLevelOrNull(): Flow<GameLevelDto?>

    fun getGameLevels(): Flow<List<GameLevelDto>>

    fun upsertGameLevel(gameObjectDto: GameLevelDto)
}