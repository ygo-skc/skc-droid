package com.skc.app.droid.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class YGOCard(
    val cardID: String,
    val cardColor: String,
    val cardName: String,
    val cardAttribute: String,
    val cardEffect: String,
    val monsterType: String?,
    val monsterAttack: Int?,
    val monsterDefense: Int?,
) : Parcelable
