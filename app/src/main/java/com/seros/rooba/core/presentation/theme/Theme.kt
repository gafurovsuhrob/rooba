package com.seros.rooba.core.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColorScheme = lightColorScheme(
    primary = PrimaryPink,
    onPrimary = Color.White,
    secondary = PinkSecondary,
    onSecondary = Color.White,
    background = SoftPink,
    surface = CardWhite,
    onBackground = TextDark,
    outlineVariant = TextLightGray,
    onSurface = TextDark,
    outline = BorderGray,
    onTertiary = Lilac,
    onSecondaryContainer = PinkSecondary
)

@Composable
fun RoobaTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
