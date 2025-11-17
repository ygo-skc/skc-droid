package com.skc.app.droid.ui.card.ygo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.skc.app.droid.model.YGOCard
import com.skc.app.droid.model.YGOCardImageVariant
import com.skc.app.droid.model.YGOImageSize
import com.skc.app.droid.ui.card.ygo.components.Attribute
import com.skc.app.droid.ui.utility.CardColorIndicatorView
import com.skc.app.droid.ui.utility.YGOCardImage

@Composable
fun YGOCardListItem(
    card: YGOCard,
    onClick: () -> Unit = {},
    header: @Composable() () -> Unit = {}
) {
    OutlinedCard {
        Column(
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(all = 10.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            header()

            Row(
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                YGOCardImage(
                    size = 70.dp,
                    cardID = card.cardID,
                    imageSize = YGOImageSize.TINY,
                    variant = YGOCardImageVariant.ROUNDED_CORNER
                )
                Column(
                    modifier = Modifier.align(Alignment.Top)
                ) {
                    Text(
                        text = card.cardName,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = card.monsterType ?: card.attribute.value)
                        Text(
                            text = card.cardID,
                            fontWeight = FontWeight.Light,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                        CardColorIndicatorView(cardColor = card.cardColor)
                        Attribute(attribute = card.attribute)
                    }
                }
            }
        }
    }
}