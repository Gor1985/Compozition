package com.android.compozition.Domain.useCase

import com.android.compozition.Domain.entity.GameSettings
import com.android.compozition.Domain.entity.Question
import com.android.compozition.Domain.repository.GameRepository

class GenerationQuestionUseCase(private val repository: GameRepository) {
    operator fun invoke(maxSumValue: Int):Question{
     return repository.generationQestion(maxSumValue, COUNT_OF_OPTIONS)
    }
    private companion object{
        private const val COUNT_OF_OPTIONS=6
    }
}