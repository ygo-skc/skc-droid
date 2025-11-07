package com.skc.app.droid.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardOfTheDay(
    val date: String,
    val version: Int,
    val card: YGOCard,
) : Parcelable
