package com.skc.app.droid.model

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