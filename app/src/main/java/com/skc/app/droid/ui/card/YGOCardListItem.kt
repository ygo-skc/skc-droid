package com.skc.app.droid.ui.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.skc.app.droid.model.YGOCard
import com.skc.app.droid.model.YGOCardImageVariant
import com.skc.app.droid.model.YGOImageSize
import com.skc.app.droid.ui.utility.YGOCardImage

@Composable
fun YGOCardListItem(card: YGOCard) {
    OutlinedCard {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp), horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            YGOCardImage(
                length = 60.dp,
                cardID = card.cardID,
                imageSize = YGOImageSize.EX_SMALL,
                variant = YGOCardImageVariant.ROUNDED_CORNER
            )
            Column {
                Text(
                    text = card.cardName,
                    fontWeight = FontWeight.Medium,
                )
                Text(text = card.monsterType ?: card.cardAttribute)
            }
        }
    }
}