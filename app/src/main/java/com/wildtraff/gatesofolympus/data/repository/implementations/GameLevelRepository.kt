package com.wildtraff.gatesofolympus.data.repository.implementations

import com.wildtraff.gatesofolympus.data.GameSeedData
import com.wildtraff.gatesofolympus.data.dao.GameLevelDao
import com.wildtraff.gatesofolympus.data.dto.GameLevelDto
import com.wildtraff.gatesofolympus.data.mapper.EntityDtoMapper
import com.wildtraff.gatesofolympus.data.repository.interfaces.IGameLevelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GameLevelRepository @Inject constructor(private val gameLevelDao: GameLevelDao) :
    IGameLevelRepository {

    private val mapper: EntityDtoMapper = EntityDtoMapper()
    override suspend fun insertAll() {
        return gameLevelDao.insertAll(GameSeedData.generateInitialData())
    }

    override fun getGameLevelByIdOrNull(id: Int): Flow<GameLevelDto?> {
        return gameLevelDao.getGameLevelByIdOrNull(id)
            .map { entity -> entity?.let { mapper.entityToDto(it) } }
    }

    override fun getFirstLockedGameLevelOrNull(): Flow<GameLevelDto?> {
        return gameLevelDao.getFirstLockedGameLevelOrNull()
            .map { entity -> entity?.let { mapper.entityToDto(it) } }
    }

    override fun getGameLevels(): Flow<List<GameLevelDto>> {
        return gameLevelDao.getGameLevels().map { gameLevels ->
            gameLevels.map { gameLevel ->
                gameLevel.let {
                    mapper.entityToDto(gameLevel)
                }
            }
        }
    }

    override fun upsertGameLevel(gameLevelDto: GameLevelDto) {
        gameLevelDao.upsertGameLevel(mapper.dtoToEntity(gameLevelDto))
    }
}