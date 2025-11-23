package com.skc.app.droid.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class YGOCard(
    val cardID: String,
    val cardName: String,
    val cardColor: String,
    private val cardAttribute: YGOAttribute?,
    val cardEffect: String,
    val monsterType: String? = null,
    val monsterAssociation: MonsterAssociation? = null,
    private val monsterAttack: Int? = null,
    private val monsterDefense: Int? = null,
    private val restrictedIn: AdvancedFormatRestrictions? = null,
    private val foundIn: List<ProductItem>? = null,
) : Parcelable {
    val attribute: YGOAttribute
        get() = cardAttribute ?: YGOAttribute.UNKNOWN

    val isMonster: Boolean
        get() = !(cardColor.equals("spell", ignoreCase = true)
                || cardColor.equals("trap", ignoreCase = true))

    val isPendulum: Boolean
        get() = isPendulum(cardColor)

    val atk: String
        get() = monsterAttack?.toString() ?: run { "?" }

    val def: String
        get() = if (cardColor.equals(
                "link",
                ignoreCase = true
            )
        ) "—" else monsterDefense?.toString() ?: run { "?" }

    val advancedFormatRestriction: AdvancedFormatRestrictions
        get() = restrictedIn ?: AdvancedFormatRestrictions(tcg = emptyList(), md = emptyList())

    val productItems: List<ProductItem>
        get() = foundIn ?: emptyList()

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

        fun isPendulum(cardColor: String) = cardColor.contains("pendulum", ignoreCase = true)
    }

    @Parcelize
    data class MonsterAssociation(
        val level: Int? = null,
        val rank: Int? = null,
        val scaleRating: Int? = null,
        val linkRating: Int? = null,
        val linkArrows: List<String>? = null,
    ) : Parcelable
}

@Parcelize
data class AdvancedFormatRestrictions(
    @param:Json(name = "TCG") val tcg: List<AdvancedFormatRestriction> = emptyList(),
    @param:Json(name = "MD") val md: List<AdvancedFormatRestriction> = emptyList()
) : Parcelable

@Parcelize
data class AdvancedFormatRestriction(
    val banListDate: String,
    val banStatus: String,
) : Parcelable

@Parcelize
data class ProductItem(
    val productId: String,
    val productLocale: String,
    val productName: String,
    val productType: String,
    val productSubType: String,
    val productReleaseDate: String,
    @param:Json(name = "productContent") val cardSlot: List<CardSlot>,
) : Parcelable

@Parcelize
data class CardSlot(
    val productPosition: String,
    val rarities: List<String>
) : Parcelable

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