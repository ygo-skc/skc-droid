package com.skc.app.droid.model

import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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

enum class IconSize(val modifier: Modifier) {
    SMALL(modifier = Modifier.size(16.dp)),
    REGULAR(modifier = Modifier.size(23.dp)),
    LARGE(modifier = Modifier.size(30.dp)),
}