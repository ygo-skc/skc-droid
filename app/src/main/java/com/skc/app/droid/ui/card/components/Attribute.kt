package com.skc.app.droid.ui.card.components

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.skc.app.droid.model.IconModifier
import com.skc.app.droid.model.YGOAttribute

@Composable
fun Attribute(attribute: YGOAttribute) {
    if (attribute == YGOAttribute.UNKNOWN) {
        Icon(Icons.Default.QuestionMark, "Unknown")
    } else {
        Image(
            painterResource(id = attribute.drawable),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = IconModifier.REGULAR.modifier,
        )
    }
}