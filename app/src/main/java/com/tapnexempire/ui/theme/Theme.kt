package com.tapnexempire.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Light Theme Colors
private val LightColors = lightColorScheme(
    primary = PinkPeachLight,
    onPrimary = CharcoalBlack,
    secondary = SoftSkyBlue, // ✅ updated
    onSecondary = CharcoalBlack,
    background = LightCream,
    onBackground = CharcoalBlack,
    surface = CardBackground,
    onSurface = CharcoalBlack,
    error = Color.Red,
    onError = Color.White
)

// Dark Theme Colors
private val DarkColors = darkColorScheme(
    primary = PinkPeachDark,
    onPrimary = Color.White,
    secondary = SoftSkyBlue, // ✅ updated
    onSecondary = CharcoalBlack,
    background = Color.Black,
    onBackground = Color.White,
    surface = CardBackground,
    onSurface = Color.White,
    error = Color.Red,
    onError = Color.Black
)

@Composable
fun TapnexEmpireTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
