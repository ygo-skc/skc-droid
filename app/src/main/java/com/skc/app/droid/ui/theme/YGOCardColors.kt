package com.skc.app.droid.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
private fun normal(): Color {
    return if (isSystemInDarkTheme()) Color(0xFFfbc02d) else Color(0xFFffd600)
}

@Composable
private fun effect(): Color {
    return if (isSystemInDarkTheme()) Color(0xFFe65100) else Color(0xFFfb8c00)
}

@Composable
private fun ritual(): Color {
    return if (isSystemInDarkTheme()) Color(0xFF039be5) else Color(0xFF4fc3f7)
}

@Composable
private fun fusion(): Color {
    return if (isSystemInDarkTheme()) Color(0xFF512da8) else Color(0xFF7c4dff)
}

@Composable
private fun synchro(): Color {
    return if (isSystemInDarkTheme()) Color(0xFF757575) else Color(0xFFbdbdbd)
}

@Composable
private fun xyz(): Color {
    return if (isSystemInDarkTheme()) Color(0xFF212121) else Color(0xFF424242)
}

@Composable
private fun link(): Color {
    return if (isSystemInDarkTheme()) Color(0xFF0d47a1) else Color(0xFF1e88e5)
}

@Composable
private fun spell(): Color {
    return if (isSystemInDarkTheme()) Color(0xFF00bfa5) else Color(0xFF009688)
}

@Composable
private fun trap(): Color {
    return if (isSystemInDarkTheme()) Color(0xFFad1457) else Color(0xFFf50057)
}

@Composable
fun String.cardColorUI(): Color {
    return when (this) {
        "Normal" -> normal()
        "Effect" -> effect()
        "Ritual" -> ritual()
        "Fusion" -> fusion()
        "Synchro" -> synchro()
        "Xyz" -> xyz()
        "Link" -> link()
        "Spell" -> spell()
        "Trap" -> trap()
        else -> Color.White
    }
}

@Composable
fun String.cardGradientUI(): Brush = when (this) {
    "Pendulum-Normal" -> Brush.verticalGradient(
        colorStops = pendulumColorStops(monsterColor = normal()),
        startY = 0.0f,
        endY = Float.POSITIVE_INFINITY
    )

    "Pendulum-Effect" -> Brush.verticalGradient(
        colorStops = pendulumColorStops(monsterColor = effect()),
        startY = 0.0f,
        endY = Float.POSITIVE_INFINITY
    )

    "Pendulum-Ritual" -> Brush.verticalGradient(
        colorStops = pendulumColorStops(monsterColor = ritual()),
        startY = 0.0f,
        endY = Float.POSITIVE_INFINITY
    )

    "Pendulum-Fusion" -> Brush.verticalGradient(
        colorStops = pendulumColorStops(monsterColor = fusion()),
        startY = 0.0f,
        endY = Float.POSITIVE_INFINITY
    )

    "Pendulum-Xyz" -> Brush.verticalGradient(
        colorStops = pendulumColorStops(monsterColor = xyz()),
        startY = 0.0f,
        endY = Float.POSITIVE_INFINITY
    )

    else -> Brush.verticalGradient(
        colors = listOf(Color.White),
        startY = 0.0f,
        endY = 100.0f
    )
}

@Composable
private fun pendulumColorStops(monsterColor: Color) = arrayOf(
    0.0f to monsterColor,
    0.45f to monsterColor,
    0.55f to spell(),
    1.0f to spell()
)

@Composable
fun onYGOCard(): Color {
    return if (isSystemInDarkTheme()) Color(0xFFeceff1) else Color.White
}