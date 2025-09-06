package com.tapnexempire.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val LightColorScheme = lightColorScheme(
    primary = RoyalTeal,
    onPrimary = White,
    secondary = VibrantCoral,
    onSecondary = White,
    background = SoftCream,
    onBackground = DarkCharcoal,
    surface = White,
    onSurface = DarkCharcoal,
)

private val DarkColorScheme = darkColorScheme(
    primary = RoyalTeal,
    onPrimary = White,
    secondary = VibrantCoral,
    onSecondary = White,
    background = DarkCharcoal,
    onBackground = White,
    surface = Color(0xFF121212),
    onSurface = White,
)

@Composable
fun TapnexEmpireTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography(), // use default typography or replace with your custom
        shapes = Shapes(
            extraSmall = androidx.compose.foundation.shape.RoundedCornerShape(6.dp),
            small = androidx.compose.foundation.shape.RoundedCornerShape(10.dp),
            medium = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
            large = androidx.compose.foundation.shape.RoundedCornerShape(24.dp)
        ),
        content = content
    )
}
