package com.skc.app.droid.model

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.google.gson.annotations.SerializedName
import com.skc.app.droid.R

enum class YGOCardImageVariant() {
    ROUND,
    ROUNDED_CORNER,
}

enum class YGOImageSize(val value: String) {
    TINY("tn"),
    EX_SMALL("x-sm"),
    SMALL("sm"),
    MEDIUM("md"),
    LARGE("lg"),
    ORIGINAL("original")
}

enum class TaskStatus() {
    PENDING,
    DONE,
    UNINITIATED,
}

enum class IconModifier(val modifier: Modifier) {
    SMALL(modifier = Modifier.size(16.dp)),
    REGULAR(modifier = Modifier.size(23.dp)),
    REGULAR(
        modifier = Modifier
            .size(23.dp)
            .clip(CircleShape)
            .aspectRatio(1f),
    ),
    LARGE(modifier = Modifier.size(30.dp)),
}