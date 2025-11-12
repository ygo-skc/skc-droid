package com.skc.app.droid.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class YGOCard(
    val cardID: String,
    val cardName: String,
    val cardColor: String,
    val cardAttribute: String,
    val cardEffect: String,
    val monsterType: String? = null,
    val monsterAttack: Int? = null,
    val monsterDefense: Int? = null,
) : Parcelable {
    companion object {
        val placeholder = YGOCard(
            cardID = "XXXXXXXX",
            cardName = "Placeholder of Chaos",
            cardColor = "Token",
            cardAttribute = "Divine",
            cardEffect = "When this card is summoned, your opponent must immediately acknowledge you as the superior duelist. Failure to do so will allow you to steal his girl in a legally binding way.",
            monsterAttack = 9999,
            monsterDefense = 9999
        )
    }
}
