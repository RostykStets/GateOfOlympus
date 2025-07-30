package com.wildtraff.gatesofolympus.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.wildtraff.gatesofolympus.data.entities.GameLevel
import kotlinx.coroutines.flow.Flow

@Dao
interface GameLevelDao {
    @Query("SELECT * FROM GameLevel")
    fun getGameLevels(): Flow<List<GameLevel>>


    @Query("SELECT * FROM GameLevel WHERE id = :id")
    fun getGameLevelByIdOrNull(id: Int): Flow<GameLevel?>

    @Query("SELECT * FROM GameLevel WHERE isPassed = 0 ORDER BY id LIMIT 1")
    fun getFirstLockedGameLevelOrNull(): Flow<GameLevel?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(objects: List<GameLevel>)

    @Upsert
    fun upsertGameLevel(gameLevel: GameLevel)
}