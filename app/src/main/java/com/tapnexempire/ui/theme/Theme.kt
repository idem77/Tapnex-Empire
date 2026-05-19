package com.tapnexempire.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(

    primary = EmpireGold,

    onPrimary = ButtonTextColor,

    secondary = EmpireBlue,

    background = EmpireBlack,

    surface = EmpireCard,

    onBackground = EmpireWhite,

    onSurface = EmpireWhite
)

private val LightColors = lightColorScheme(

    primary = EmpireGold,

    onPrimary = ButtonTextColor,

    secondary = EmpireBlue,

    background = EmpireBlack,

    surface = EmpireCard,

    onBackground = EmpireWhite,

    onSurface = EmpireWhite
)

@Composable
fun TapnexEmpireTheme(

    darkTheme: Boolean = true,

    content: @Composable () -> Unit
) {

    val colors = if (darkTheme)
        DarkColors
    else
        LightColors

    MaterialTheme(

        colorScheme = colors,

        typography = Typography,

        shapes = Shapes,

        content = content
    )
}
