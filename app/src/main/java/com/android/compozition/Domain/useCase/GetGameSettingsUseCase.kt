package com.android.compozition.Domain.useCase

import com.android.compozition.Domain.entity.GameSettings
import com.android.compozition.Domain.entity.Level
import com.android.compozition.Domain.entity.Question
import com.android.compozition.Domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {
    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }

    }
