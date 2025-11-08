package com.skc.app.droid.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.skc.app.droid.model.CardOfTheDay
import com.skc.app.droid.model.YGOCard
import com.skc.app.droid.ui.theme.SKCTheme
import com.skc.app.droid.ui.utility.InlineDate
import com.skc.app.droid.ui.utility.Section

@Composable
fun CardOfTheDay(cotd: CardOfTheDay) {
    Section(header = "Card of the day") {
        Row(
            modifier = Modifier.padding(15.dp),
            horizontalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            AsyncImage(
                model = "https://images.thesupremekingscastle.com/cards/md/${cotd.card.cardID}.jpg",
                contentDescription = "Card Image",
                modifier = Modifier
                    .width(85.dp)
                    .clip(
                        RoundedCornerShape(corner = CornerSize(45.dp))
                    )
            )
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