package com.wildtraff.gatesofolympus.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wildtraff.gatesofolympus.data.repository.interfaces.IGameLevelRepository
import com.wildtraff.gatesofolympus.domain.mapper.DtoModelMapper
import com.wildtraff.gatesofolympus.domain.models.GameLevel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameLevelViewModel @Inject constructor(private val gameLevelRepository: IGameLevelRepository) :
    ViewModel() {

    init {
        viewModelScope.launch {
            gameLevelRepository.getGameLevels()
                .collect { list ->
                    if (list.isEmpty())
                        gameLevelRepository.insertAll()
                }
        }
    }

private val _levels = MutableStateFlow<List<GameLevel>>(emptyList())
val levels: StateFlow<List<GameLevel>> = _levels

private val _level = MutableStateFlow<GameLevel?>(null)
val level: StateFlow<GameLevel?> = _level

private val _error = MutableStateFlow<String?>(null)
val error: StateFlow<String?> = _error

private val mapper = DtoModelMapper()

fun getGameLevels() {
    viewModelScope.launch(Dispatchers.IO) {
        gameLevelRepository.getGameLevels().catch { ex -> _error.value = ex.message }
            .collect { list ->
                _levels.value = list.map { gameLevelDto -> mapper.dtoToModel(gameLevelDto) }
            }
    }
}

fun getGameLevelByIdOrNull(id: Int) {
    viewModelScope.launch(Dispatchers.IO) {
        gameLevelRepository.getGameLevelByIdOrNull(id).catch { ex -> _error.value = ex.message }
            .collect { level ->
                _level.value = if (level != null) mapper.dtoToModel(level) else null
            }
    }
}

fun getFirstLockedGameLevelOrNull() {
    viewModelScope.launch(Dispatchers.IO) {
        gameLevelRepository.getFirstLockedGameLevelOrNull()
            .catch { ex -> _error.value = ex.message }
            .collect { level ->
                _level.value = if (level != null) mapper.dtoToModel(level) else null
            }
    }
}

fun upsertGameLevel(gameLevel: GameLevel) {
    viewModelScope.launch(Dispatchers.IO) {
        gameLevelRepository.upsertGameLevel(mapper.modelToDto(gameLevel))
    }
}
}