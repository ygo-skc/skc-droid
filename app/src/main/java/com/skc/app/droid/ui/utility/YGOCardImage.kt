package com.skc.app.droid.ui.utility

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.skc.app.droid.model.YGOCard
import com.skc.app.droid.model.YGOCardImageVariant
import com.skc.app.droid.model.YGOImageSize
import com.skc.app.droid.ui.theme.cardColorUI
import com.skc.app.droid.ui.theme.cardGradientUI

@Composable
fun YGOCardImage(
    size: Dp,
    cardID: String,
    cardColor: String? = null,
    imageSize: YGOImageSize = YGOImageSize.SMALL,
    variant: YGOCardImageVariant = YGOCardImageVariant.ROUND,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .let { m ->
                cardColor?.let {
                    if (YGOCard.isPendulum(cardColor)) m.border(
                        width = 4.dp,
                        brush = cardColor.cardGradientUI(),
                        shape = CircleShape
                    )
                    else m.border(
                        width = 4.dp,
                        color = cardColor.cardColorUI(),
                        shape = CircleShape
                    )
                } ?: m
            }
    ) {
        AsyncImage(
            model = "https://images.thesupremekingscastle.com/cards/${imageSize.value}/$cardID.jpg",
            contentDescription = "Card Image",
            modifier = Modifier
                .size(size = size)
                .clip(
                    shape = if (variant == YGOCardImageVariant.ROUND)
                        RoundedCornerShape(corner = CornerSize(size / 2))
                    else RoundedCornerShape(
                        corner = CornerSize(size / 10)
                    )
                )
        )
    }
}