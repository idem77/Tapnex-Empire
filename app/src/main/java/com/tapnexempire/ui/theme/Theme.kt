package com.tapnexempire.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = RoyalPurple,
    onPrimary = OnPrimary,
    secondary = PremiumGold,
    surface = SurfaceVariant,
    onSurface = OnSurface,
    background = MidnightBlack
)

@Composable
fun TapnexEmpireTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = TapnexTypography,
        shapes = Shapes(),
        content = content
    )
}
