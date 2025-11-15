package com.skc.app.droid.ui.utility

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import com.skc.app.droid.model.IconModifier
import com.skc.app.droid.ui.theme.cardColorUI

@Composable
fun CardColorIndicatorView(cardColor: String, iconModifier: IconModifier = IconModifier.REGULAR) {
    val color = cardColor.cardColorUI()
    Canvas(
        modifier = iconModifier.modifier,
        onDraw = {
            drawCircle(color = color)
        })
}