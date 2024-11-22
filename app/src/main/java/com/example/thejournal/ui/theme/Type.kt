package com.example.thejournal.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.thejournal.R

val darkerGrotesqueFontFamily = FontFamily(
    androidx.compose.ui.text.font.Font(resId = R.font.darker_grotesque_light, FontWeight.Light),
    androidx.compose.ui.text.font.Font(resId = R.font.darker_grotesque_regular, FontWeight.Normal),
    androidx.compose.ui.text.font.Font(resId = R.font.darker_grotesque_medium, FontWeight.Medium),
    androidx.compose.ui.text.font.Font(resId = R.font.darker_grotesque_semi_bold, FontWeight.SemiBold),
    androidx.compose.ui.text.font.Font(resId = R.font.darker_grotesque_bold, FontWeight.Bold),
    androidx.compose.ui.text.font.Font(resId = R.font.darker_grotesque_extra_bold, FontWeight.ExtraBold),
    androidx.compose.ui.text.font.Font(resId = R.font.darker_grotesque_black, FontWeight.Black)
)

private val defaultTypography = Typography()
val darkerGrotesqueTypography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = darkerGrotesqueFontFamily),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = darkerGrotesqueFontFamily),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = darkerGrotesqueFontFamily),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = darkerGrotesqueFontFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = darkerGrotesqueFontFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = darkerGrotesqueFontFamily),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = darkerGrotesqueFontFamily),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = darkerGrotesqueFontFamily),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = darkerGrotesqueFontFamily),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = darkerGrotesqueFontFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = darkerGrotesqueFontFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = darkerGrotesqueFontFamily),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = darkerGrotesqueFontFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = darkerGrotesqueFontFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = darkerGrotesqueFontFamily)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)