package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val HubColorScheme = darkColorScheme(
    primary = HubGold,
    onPrimary = Color(0xFF0F1404),
    primaryContainer = Color(0xFF282D07),
    onPrimaryContainer = HubGoldGlow,
    secondary = HubSky,
    onSecondary = Color(0xFF031625),
    secondaryContainer = Color(0xFF0C243B),
    onSecondaryContainer = Color(0xFFBAE6FD),
    tertiary = HubEmerald,
    onTertiary = Color(0xFF022013),
    background = HubBackground,
    onBackground = HubTextPrimary,
    surface = HubCard,
    onSurface = HubTextPrimary,
    surfaceVariant = HubCardElevated,
    onSurfaceVariant = HubTextSecondary,
    outline = HubBorder,
    error = HubRose,
    onError = Color.White
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = HubColorScheme,
        typography = Typography,
        content = content
    )
}
