package com.wildtraff.gatesofolympus.data.mapper

import com.wildtraff.gatesofolympus.data.dto.GameLevelDto
import com.wildtraff.gatesofolympus.data.entities.GameLevel

class EntityDtoMapper {
    fun dtoToEntity(gameLevelDto: GameLevelDto): GameLevel {
        val gameLevel = GameLevel(
            gameLevelDto.id,
            gameLevelDto.score,
            gameLevelDto.stars,
            gameLevelDto.scoreLimit,
            gameLevelDto.isPassed
        )
        return gameLevel
    }

    fun entityToDto(gameLevel: GameLevel): GameLevelDto {
        val gameLevelDto = GameLevelDto(
            gameLevel.id,
            gameLevel.score,
            gameLevel.stars,
            gameLevel.scoreLimit,
            gameLevel.isPassed
        )
        return gameLevelDto
    }
}