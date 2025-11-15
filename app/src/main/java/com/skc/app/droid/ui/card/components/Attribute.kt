package com.skc.app.droid.ui.card.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.skc.app.droid.model.IconModifier
import com.skc.app.droid.model.YGOAttribute

@Composable
fun Attribute(attribute: YGOAttribute) {
    Image(
        painterResource(id = attribute.drawable),
        contentDescription = "",
        contentScale = ContentScale.Fit,
        modifier = IconModifier.REGULAR.modifier,
    )
}