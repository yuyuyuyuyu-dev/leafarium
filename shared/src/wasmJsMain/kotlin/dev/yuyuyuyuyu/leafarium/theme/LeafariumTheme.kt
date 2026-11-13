package dev.yuyuyuyuyu.leafarium.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Composable
fun LeafariumTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = leafariumColorScheme,
        typography = leafariumTypography,
        content = content,
    )
}

object LeafariumColors {
    val Ground = Color(0xFFF3F1E8)
    val Surface = Color(0xFFFBFAF5)
    val Ink = Color(0xFF1C2620)
    val Muted = Color(0xFF5A6558)
    val Subtle = Color(0xFF4A5548)
    val Line = Color(0xFFE2DECF)
    val Outline = Color(0xFFC9C4B2)
    val Primary = Color(0xFF2F7A45)
    val PrimaryDeep = Color(0xFF1F5A31)
    val PrimarySoft = Color(0xFFE2EDDB)
    val Chip = Color(0xFFECE8DA)
    val Code = Color(0xFFEEEBDF)
    val Banner = Color(0xFFDDE8D5)
    val Scrim = Color(0x731C2620)
}

enum class AvatarTone(
    val background: Color,
    val content: Color,
) {
    Moss(Color(0xFFCFE3C8), Color(0xFF1F5A31)),
    Bark(Color(0xFFE8DCC4), Color(0xFF6B4E1F)),
    Sky(Color(0xFFD5E0E8), Color(0xFF2D4E66)),
}

private val leafariumColorScheme =
    lightColorScheme(
        primary = LeafariumColors.Primary,
        onPrimary = Color.White,
        primaryContainer = LeafariumColors.PrimarySoft,
        onPrimaryContainer = LeafariumColors.PrimaryDeep,
        secondaryContainer = LeafariumColors.PrimarySoft,
        onSecondaryContainer = LeafariumColors.PrimaryDeep,
        background = LeafariumColors.Ground,
        onBackground = LeafariumColors.Ink,
        surface = LeafariumColors.Surface,
        onSurface = LeafariumColors.Ink,
        surfaceVariant = LeafariumColors.Chip,
        onSurfaceVariant = LeafariumColors.Muted,
        surfaceContainer = LeafariumColors.Ground,
        surfaceContainerHigh = LeafariumColors.Surface,
        outline = LeafariumColors.Outline,
        outlineVariant = LeafariumColors.Line,
        inverseSurface = LeafariumColors.Ink,
        inverseOnSurface = LeafariumColors.Surface,
    )

private val leafariumTypography =
    Typography(
        headlineSmall = TextStyle(fontSize = 22.sp, lineHeight = 28.sp, fontWeight = FontWeight.Bold, letterSpacing = (-0.01).em),
        titleLarge = TextStyle(fontSize = 20.sp, lineHeight = 26.sp, fontWeight = FontWeight.Bold),
        titleMedium = TextStyle(fontSize = 17.sp, lineHeight = 24.sp, fontWeight = FontWeight.Bold),
        titleSmall = TextStyle(fontSize = 15.sp, lineHeight = 20.sp, fontWeight = FontWeight.Bold),
        bodyLarge = TextStyle(fontSize = 16.sp, lineHeight = 25.sp),
        bodyMedium = TextStyle(fontSize = 15.sp, lineHeight = 22.sp),
        bodySmall = TextStyle(fontSize = 14.sp, lineHeight = 20.sp),
        labelLarge = TextStyle(fontSize = 15.sp, lineHeight = 20.sp, fontWeight = FontWeight.SemiBold),
        labelMedium = TextStyle(fontSize = 14.sp, lineHeight = 18.sp, fontWeight = FontWeight.Medium),
        labelSmall = TextStyle(fontSize = 12.sp, lineHeight = 16.sp, fontWeight = FontWeight.SemiBold),
    )
