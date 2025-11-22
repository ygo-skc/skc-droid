package com.skc.app.droid.ui.card.ygo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skc.app.droid.model.YGOAttribute
import com.skc.app.droid.model.YGOCard
import com.skc.app.droid.ui.theme.SKCTheme

@Composable
fun Stats(card: YGOCard) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(size = 6.dp))
            .background(Color.White.copy(alpha = 0.7f))
            .padding(6.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = card.cardColor,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
        )
        Text(
            text = card.cardEffect,
            modifier = Modifier.padding(bottom = 5.dp),
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = card.cardID,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium,
            )
            if (card.isMonster) {
                MonsterStrength(
                    monsterAttack = card.atk,
                    monsterDefense = card.def,
                )
            }
        }
    }
}

@Preview
@Composable
fun StatsPreview() {
    SKCTheme {
        Column(modifier = Modifier.padding(top = 50.dp)) {
            Stats(
                card = YGOCard(
                    cardID = "89943723",
                    cardName = "Elemental HERO Neos",
                    cardColor = "Normal",
                    cardAttribute = YGOAttribute.LIGHT,
                    cardEffect = "A new Elemental HERO has arrived from Neo-Space! When he initiates a Contact Fusion with a Neo-Spacian his unknown powers are unleashed.",
                    monsterAttack = 2500,
                    monsterDefense = 2000
                )
            )
        }
    }
}