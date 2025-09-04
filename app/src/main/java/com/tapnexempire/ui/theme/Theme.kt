package com.tapnexempire.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = RoyalBlue,
    secondary = Gold,
    tertiary = NeonGreen,
    background = MidnightBlack,
    surface = MidnightBlack,
    onPrimary = PureWhite,
    onSecondary = PureWhite,
    onBackground = PureWhite,
    onSurface = PureWhite
)

private val LightColorScheme = lightColorScheme(
    primary = RoyalBlue,
    secondary = Gold,
    tertiary = NeonGreen,
    background = PureWhite,
    surface = PureWhite,
    onPrimary = PureWhite,
    onSecondary = MidnightBlack,
    onBackground = MidnightBlack,
    onSurface = MidnightBlack
)

@Composable
fun TapnexEmpireTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography(),
        content = content
    )
}
