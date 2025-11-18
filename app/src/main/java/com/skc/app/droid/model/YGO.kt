package com.skc.app.droid.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class YGOCard(
    val cardID: String,
    val cardName: String,
    val cardColor: String,
    private val cardAttribute: YGOAttribute?,
    val cardEffect: String,
    val monsterType: String? = null,
    val monsterAttack: Int? = null,
    val monsterDefense: Int? = null,
) : Parcelable {
    val attribute: YGOAttribute
        get() = cardAttribute ?: YGOAttribute.UNKNOWN

    val isMonster: Boolean
        get() = !(cardColor.equals("spell", ignoreCase = true)
                || cardColor.equals("spell", ignoreCase = true))

    companion object {
        val placeholder = YGOCard(
            cardID = "XXXXXXXX",
            cardName = "Placeholder of Chaos",
            cardColor = "Token",
            cardAttribute = YGOAttribute.DIVINE,
            cardEffect = "When this card is summoned, your opponent must immediately acknowledge you as the superior duelist. Failure to do so will allow you to steal his girl in a legally binding way.",
            monsterAttack = 9999,
            monsterDefense = 9999
        )
    }
}

@Parcelize
data class DBStats(
    val productTotal: Int,
    val cardTotal: Int,
    val banListTotal: Int,
) : Parcelable

@Parcelize
data class CardOfTheDay(
    val date: String,
    val version: Int,
    val card: YGOCard,
) : Parcelable

@Parcelize
data class TrendingData(
    val resourceName: String,
    val metrics: List<TrendingMetric>
) : Parcelable

@Parcelize
data class TrendingMetric(
    val resource: YGOCard,
    val occurrences: Int,
    val change: Int,
) : Parcelable