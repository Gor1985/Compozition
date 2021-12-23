package com.android.compozition.Domain.entity

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSettings(
   val maxSumValue:Int,
   val minCountOfRightAnswers:Int,
   val minProcentOfRightAnswers:Int,
   val gameTameInSeconds:Int
):Parcelable {

}