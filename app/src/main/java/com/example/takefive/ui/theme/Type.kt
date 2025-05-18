package com.example.takefive.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.takefive.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fontName = GoogleFont("Poppins")

val poppinsFontFamily = FontFamily(
    Font(googleFont = fontName, fontProvider = provider)
)

// TODO: Remove this font family and associated files
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

val poppinsTypography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = poppinsFontFamily),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = poppinsFontFamily),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = poppinsFontFamily),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = poppinsFontFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = poppinsFontFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = poppinsFontFamily),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = poppinsFontFamily),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = poppinsFontFamily),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = poppinsFontFamily),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = poppinsFontFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = poppinsFontFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = poppinsFontFamily),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = poppinsFontFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = poppinsFontFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = poppinsFontFamily)
)

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