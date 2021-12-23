package com.android.compozition.Domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable
@Parcelize
data class GameResults(
    val winner:Boolean,
    val countOfRightsAnswers:Int,
    val countOfQuestion: Int,
    val gameSettings: GameSettings
) : Parcelable {
}