package com.example.thejournal.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

private val defaultTypography = Typography()
private val serifTypography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = FontFamily.Serif),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = FontFamily.Serif),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = FontFamily.Serif),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = FontFamily.Serif),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = FontFamily.Serif),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = FontFamily.Serif),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = FontFamily.Serif),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = FontFamily.Serif),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = FontFamily.Serif),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = FontFamily.Serif),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = FontFamily.Serif),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = FontFamily.Serif),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = FontFamily.Serif),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = FontFamily.Serif),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = FontFamily.Serif)
)

@Composable
fun TheJournalTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = serifTypography,
        content = content
    )
}