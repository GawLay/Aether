package com.kyrie.aether.ui.theme

import androidx.compose.ui.graphics.Color

// Weather-based color schemes
object WeatherColors {
    // Rainy theme colors
    val RainyPrimary = Color(0xFF5A7A95)
    val RainySecondary = Color(0xFF7B9BB8)
    val RainyBackground = Color(0xFF2D3E50)
    val RainyBackgroundVariant = Color(0xFF34495E)
    val RainyOnBackground = Color(0xFFFFFFFF)
    val RainySurface = Color(0x80FFFFFF)
    val RainyOnSurface = Color(0xFFFFFFFF)

    // Sunny theme colors
    val SunnyPrimary = Color(0xFFFF9800)
    val SunnySecondary = Color(0xFFFFC107)
    val SunnyBackground = Color(0xFF87CEEB)
    val SunnyBackgroundVariant = Color(0xFF4FC3F7)
    val SunnyOnBackground = Color(0xFF1A1A1A)
    val SunnySurface = Color(0x80FFFFFF)
    val SunnyOnSurface = Color(0xFF1A1A1A)

    // Snowy theme colors
    val SnowyPrimary = Color(0xFF81BFE8)
    val SnowySecondary = Color(0xFFB3E5FC)
    val SnowyBackground = Color(0xFF607D8B)
    val SnowyBackgroundVariant = Color(0xFF78909C)
    val SnowyOnBackground = Color(0xFFFFFFFF)
    val SnowySurface = Color(0x80FFFFFF)
    val SnowyOnSurface = Color(0xFF1A1A1A)

    // Common colors used across all themes
    val WeatherTextPrimary = Color(0xFFFFFFFF)
    val WeatherTextSecondary = Color(0xCCFFFFFF)
    val WeatherCardBackground = Color(0xE9394046)

    val WeatherCardBackgroundTransparent = Color(0xBCFFFFFF)
    val WeatherAccent = Color(0xFFFF9800)

    val WhiteTransparent = Color(0x80FFFFFF)
    val Transparent = Color(0x00000000)

    // Foggy theme colors
    val FoggyPrimary = Color(0xFF9FA4A8)
    val FoggySecondary = Color(0xFFCACDD1)

    // Slightly grey foggy gradient
    val FoggyBackground = Color(0xFFB0B4B8) // lighter grey, top
    val FoggyBackgroundVariant = Color(0xFF8F9499) // darker grey, bottom
    val FoggyOnBackground = Color(0xFF111315)
    val FoggySurface = Color(0x80FFFFFF) // translucent mist overlay
    val FoggyOnSurface = Color(0xFF111315)

    // Starry theme colors
    val StarryPrimary = Color(0xFF8AB4F8) // soft moonlight blue
    val StarrySecondary = Color(0xFFBBD1FF)
    val StarryBackground = Color(0xFF0B1026) // very dark blue/indigo
    val StarryBackgroundVariant = Color(0xFF162044) // deeper blue for gradient
    val StarryOnBackground = Color(0xFFFFFFFF)
    val StarrySurface = Color(0x66FFFFFF) // subtle glow overlay
    val StarryOnSurface = Color(0xFFFFFFFF)

    val CloudyPrimary = Color(0xFF607D8B) // slate blue-gray
    val CloudySecondary = Color(0xFF90A4AE) // lighter blue-gray
    val CloudyBackground = Color(0xFF37474F) // deep muted gray
    val CloudyBackgroundVariant = Color(0xFF263238) // darker variant for gradients
    val CloudyOnBackground = Color(0xFFFFFFFF) // text/icons on background
    val CloudySurface = Color(0x80FFFFFF) // translucent overlay for cards
    val CloudyOnSurface = Color(0xFF111315) // text/icons on surfaces
}

// Default Material 3 colors (keeping for fallback)
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
