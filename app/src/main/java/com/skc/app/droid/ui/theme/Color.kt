package com.skc.app.droid.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val PrimaryLight = Color(0xFF673ab7)
val SecondaryLight = Color(0xFF607d8b)
val TertiaryLight = Color(0xFFb388ff)

val PrimaryDark = Color(0xFFB9A3FF)
val SecondaryDark = Color(0xFFFF944D)
val TertiaryDark = Color(0xFFE5B6FF)

@Composable
fun dateGray(): Color {
    return if (isSystemInDarkTheme()) Color(0xFF5C5C5C) else Color(0xFFF5F5F5)
}

@Composable
fun dateRed(): Color {
    return if (isSystemInDarkTheme()) Color(0xFFB22A53) else Color(0xFFE42263)
}