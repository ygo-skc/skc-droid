package com.skc.app.droid.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,

    secondary = SecondaryDark,
    secondaryContainer = Color(0xFFff8f00),
    onSecondaryContainer = Color(0xFFeceff1),

    tertiary = TertiaryDark,

    background = Color(0xFF263238),
    surface = Color(0xFF29333A),
    onBackground = Color(0xFFeceff1),

    surfaceContainer = Color(0xFF2D3840),
    surfaceContainerLowest = Color(0xFF323D46),
    surfaceContainerLow = Color(0xFF36424C),
    surfaceContainerHigh = Color(0xF3B4752),
    surfaceContainerHighest = Color(0xFF3F4C58),
    onSurface = Color(0xFFeceff1),
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    primaryContainer = Color(0xFFd1c4e9),

    secondary = SecondaryLight,
    secondaryContainer = Color(0xFFfff3e0),
    onSecondaryContainer = Color.Black,

    tertiary = TertiaryLight,
    tertiaryContainer = Color(0xFFdacaff),

    background = Color(0xFFF0F3F5),
    surface = Color(0xFFE6E9EB),
    onBackground = Color(0xFF263238),

    surfaceContainer = Color(0xFFE0E3E5),
    surfaceContainerLowest = Color(0xFFE0E3E5),
    surfaceContainerLow = Color(0xFFDDDFE1),
    surfaceContainerHigh = Color(0xFFDADDE0),
    surfaceContainerHighest = Color(0xFFB6B9BF),
    onSurface = Color(0xFF263238),
)

@Composable
fun SKCTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}