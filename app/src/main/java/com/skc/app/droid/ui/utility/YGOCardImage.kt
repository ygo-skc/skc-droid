package com.skc.app.droid.ui.utility

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.skc.app.droid.model.YGOCardImageVariant
import com.skc.app.droid.model.YGOImageSize
import com.skc.app.droid.ui.theme.cardColorUI

@Composable
fun YGOCardImage(
    length: Dp,
    cardID: String,
    cardColor: String? = null,
    imageSize: YGOImageSize = YGOImageSize.SMALL,
    variant: YGOCardImageVariant = YGOCardImageVariant.ROUND
) {
    Box(
        modifier = Modifier
            .size(length)
            .then(
                cardColor?.let {
                    Modifier
                        .border(
                            width = 4.dp,
                            color = cardColor.cardColorUI(),
                            shape = CircleShape
                        )
                } ?: run {
                    Modifier
                }
            )
    ) {
        AsyncImage(
            model = "https://images.thesupremekingscastle.com/cards/${imageSize.value}/$cardID.jpg",
            contentDescription = "Card Image",
            modifier = Modifier
                .width(length)
                .height(length)
                .clip(
                    if (variant == YGOCardImageVariant.ROUND)
                        RoundedCornerShape(corner = CornerSize(length / 2)) else RoundedCornerShape(
                        corner = CornerSize(
                            length / 10
                        )
                    )
                )
        )
    }
}