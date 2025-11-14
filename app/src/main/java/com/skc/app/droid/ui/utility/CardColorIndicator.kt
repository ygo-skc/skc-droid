package com.skc.app.droid.ui.utility

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import com.skc.app.droid.model.IconSize
import com.skc.app.droid.ui.theme.cardColorUI


@Composable
fun CardColorIndicatorView(cardColor: String, iconSize: IconSize = IconSize.REGULAR) {
    val color = cardColor.cardColorUI()
    Canvas(
        modifier = iconSize.modifier,
        onDraw = {
            drawCircle(color = color)
        })
}