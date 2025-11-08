package com.skc.app.droid.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skc.app.droid.model.CardOfTheDay
import com.skc.app.droid.model.YGOCard
import com.skc.app.droid.ui.theme.SKCTheme
import com.skc.app.droid.ui.utility.InlineDate
import com.skc.app.droid.ui.utility.Section
import com.skc.app.droid.ui.utility.YGOCardImage

@Composable
fun CardOfTheDay(cotd: CardOfTheDay) {
    Section(header = "Card of the day") {
        Row(
            modifier = Modifier.padding(15.dp),
            horizontalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            YGOCardImage(length = 85.dp, cardID = cotd.card.cardID, cardColor = cotd.card.cardColor)
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                InlineDate(if (cotd.date == "") "1991-07-27" else cotd.date)
                Text(
                    text = cotd.card.cardName,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = cotd.card.monsterType ?: cotd.card.cardAttribute,
                    fontWeight = FontWeight.Light,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardOfTheDayPreview() {
    SKCTheme {
        CardOfTheDay(
            CardOfTheDay(
                date = "2025-10-27",
                version = 1,
                card = YGOCard(
                    cardID = "89943723",
                    cardColor = "Normal",
                    cardName = "Elemental HERO Neos",
                    cardAttribute = "Light",
                    cardEffect = "None",
                    monsterType = "Warrior/Normal",
                    monsterAttack = 2500,
                    monsterDefense = 2000
                )
            )
        )
    }
}