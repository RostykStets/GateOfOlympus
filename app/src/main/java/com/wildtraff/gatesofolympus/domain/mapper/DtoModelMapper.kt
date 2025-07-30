package com.wildtraff.gatesofolympus.domain.mapper

import com.wildtraff.gatesofolympus.data.dto.GameLevelDto
import com.wildtraff.gatesofolympus.domain.models.GameLevel

class DtoModelMapper {
    fun dtoToModel(gameLevelDto: GameLevelDto): GameLevel {
        val gameLevel = GameLevel(
            gameLevelDto.id,
            gameLevelDto.score,
            gameLevelDto.stars,
            gameLevelDto.scoreLimit,
            gameLevelDto.isPassed
        )
        return gameLevel
    }

    fun modelToDto(gameLevel: GameLevel): GameLevelDto {
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