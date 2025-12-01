package com.kyrie.aether.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kyrie.aether.ui.theme.LocalDimensions
import com.kyrie.aether.ui.theme.WeatherColors
import com.kyrie.aether.weatherCore.WeatherCondition

/***
 * Just common weather card composable with dynamic background color for the application.
 * use this composable for the weather related cards in the app.
 * */
@Composable
fun WeatherCard(
    weatherCondition: WeatherCondition,
    content: @Composable () -> Unit
) {
    val backgroundColors = when (weatherCondition) {
        WeatherCondition.RAINY, WeatherCondition.THUNDERSTORM, WeatherCondition.DRIZZLE,
        WeatherCondition.SHOWER, WeatherCondition.FREEZING_RAIN ->
            WeatherColors.WeatherCardBackground

        WeatherCondition.CLEAR, WeatherCondition.MOSTLY_CLEAR ->
            WeatherColors.SunnyBackground

        WeatherCondition.CLOUDY, WeatherCondition.PARTLY_CLOUDY ->
            WeatherColors.CloudyBackground

        WeatherCondition.SNOWY, WeatherCondition.HEAVY_SNOWY ->
            WeatherColors.SnowyBackground

        WeatherCondition.STARRY, WeatherCondition.PARTLY_STARRY ->
            WeatherColors.StarryBackground

        WeatherCondition.FOGGY -> WeatherColors.FoggyBackground
        WeatherCondition.UNKNOWN -> WeatherColors.FoggyBackground
    }

    val dimensions = LocalDimensions.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensions.spaceLarge),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColors
        ),
        shape = RoundedCornerShape(dimensions.radiusLarge),
    ) {
        content()
    }
}