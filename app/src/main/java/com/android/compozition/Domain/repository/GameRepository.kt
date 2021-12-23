package com.android.compozition.Domain.repository

import com.android.compozition.Domain.entity.GameSettings
import com.android.compozition.Domain.entity.Level
import com.android.compozition.Domain.entity.Question

interface GameRepository {
    fun generationQestion(
        maxSumValue:Int,
        countOfOptions:Int
    ):Question
fun getGameSettings(level: Level):GameSettings

}
