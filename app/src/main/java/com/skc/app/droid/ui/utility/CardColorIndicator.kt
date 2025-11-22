package com.skc.app.droid.ui.utility

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import com.skc.app.droid.model.IconModifier
import com.skc.app.droid.model.YGOCard
import com.skc.app.droid.ui.theme.cardColorUI
import com.skc.app.droid.ui.theme.cardGradientUI

@Composable
fun CardColorIndicatorView(cardColor: String, iconModifier: IconModifier = IconModifier.REGULAR) {
    val isPendulum = YGOCard.isPendulum(cardColor)

    val color = if (!isPendulum) cardColor.cardColorUI() else null
    val brush = if (isPendulum) cardColor.cardGradientUI() else null

    Canvas(
        modifier = iconModifier.modifier,
        onDraw = {
            if (isPendulum) {
                drawCircle(brush!!)
            } else {
                drawCircle(color!!)
            }
        })
}