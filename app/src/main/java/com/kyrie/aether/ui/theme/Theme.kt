package com.kyrie.aether.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.platform.LocalContext
import com.kyrie.aether.weatherCore.WeatherCondition

private val RainyColorScheme = darkColorScheme(
    primary = WeatherColors.RainyPrimary,
    secondary = WeatherColors.RainySecondary,
    background = WeatherColors.RainyBackground,
    surface = WeatherColors.RainySurface,
    onBackground = WeatherColors.RainyOnBackground,
    onSurface = WeatherColors.RainyOnSurface
)

private val SunnyColorScheme = lightColorScheme(
    primary = WeatherColors.SunnyPrimary,
    secondary = WeatherColors.SunnySecondary,
    background = WeatherColors.SunnyBackground,
    surface = WeatherColors.SunnySurface,
    onBackground = WeatherColors.SunnyOnBackground,
    onSurface = WeatherColors.SunnyOnSurface
)

private val SnowyColorScheme = lightColorScheme(
    primary = WeatherColors.SnowyPrimary,
    secondary = WeatherColors.SnowySecondary,
    background = WeatherColors.SnowyBackground,
    surface = WeatherColors.SnowySurface,
    onBackground = WeatherColors.SnowyOnBackground,
    onSurface = WeatherColors.SnowyOnSurface
)

private val FoggyColorScheme = darkColorScheme(
    primary = WeatherColors.FoggyPrimary,
    secondary = WeatherColors.FoggySecondary,
    background = WeatherColors.FoggyBackground,
    surface = WeatherColors.FoggySurface,
    onBackground = WeatherColors.FoggyOnBackground,
    onSurface = WeatherColors.FoggyOnSurface
)

private val StarryColorScheme = darkColorScheme(
    primary = WeatherColors.StarryPrimary,
    secondary = WeatherColors.StarrySecondary,
    background = WeatherColors.StarryBackground,
    surface = WeatherColors.StarrySurface,
    onBackground = WeatherColors.StarryOnBackground,
    onSurface = WeatherColors.StarryOnSurface
)

private val CloudyColorScheme = lightColorScheme(
    primary = WeatherColors.CloudyPrimary,
    secondary = WeatherColors.CloudySecondary,
    background = WeatherColors.CloudyBackground,
    surface = WeatherColors.CloudySurface,
    onBackground = WeatherColors.CloudyOnBackground,
    onSurface = WeatherColors.CloudyOnSurface
)

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

@Composable
fun AetherTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Disabled to use weather-based themes
    // simple value-based API for backward compatibility
    weatherCondition: WeatherCondition = WeatherCondition.RAINY,
    // optional reactive state so callers can pass LiveData/StateFlow/MutableState from API updates
    weatherConditionState: State<WeatherCondition>? = null,
    content: @Composable () -> Unit
) {
    val currentCondition = weatherConditionState?.value ?: weatherCondition

    val colorScheme = when {
        dynamicColor -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        currentCondition in listOf(
            WeatherCondition.CLEAR,
            WeatherCondition.MOSTLY_CLEAR
        ) -> RainyColorScheme

        currentCondition in listOf(
            WeatherCondition.CLOUDY,
            WeatherCondition.PARTLY_CLOUDY,
        ) -> CloudyColorScheme

        currentCondition in listOf(
            WeatherCondition.RAINY,
            WeatherCondition.THUNDERSTORM,
            WeatherCondition.DRIZZLE
        ) -> RainyColorScheme

        currentCondition in listOf(
            WeatherCondition.SNOWY,
            WeatherCondition.HEAVY_SNOWY,
        ) -> SnowyColorScheme

        currentCondition in listOf(
            WeatherCondition.STARRY,
            WeatherCondition.PARTLY_STARRY,
        ) -> StarryColorScheme

        currentCondition == WeatherCondition.FOGGY -> FoggyColorScheme
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}