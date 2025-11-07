package com.skc.app.droid.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val DateRed = Color(0xFFE42263)
val DateGray = Color(0xFFF5F5F5)@Composable
fun dateGray(): Color {
    return if (isSystemInDarkTheme()) Color(0xFF5C5C5C) else Color(0xFFF5F5F5)
}

@Composable
fun dateRed(): Color {
    return if (isSystemInDarkTheme()) Color(0xFFB22A53) else Color(0xFFE42263)
}