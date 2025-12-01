package com.kyrie.aether.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.ui.Modifier
import com.kyrie.aether.ui.theme.WeatherColors
import com.kyrie.aether.utility.extensions.applyShaderRuntimeEffect
import com.kyrie.aether.utility.shaders.ShaderComposeLayers
import com.kyrie.aether.utility.shaders.ShaderUtil
import com.kyrie.aether.utility.shaders.model.WeatherSceneShaders
import com.kyrie.aether.utility.shaders.scripts.RainScene
import com.kyrie.aether.weatherCore.WeatherCondition

/**
 * A composable that displays weather scene layers based on the given weather condition.
 * @param modifier The modifier to be applied to the composable.
 * @param weatherCondition The current weather condition.
 * @param shaders The weather scene shaders to be used for rendering.
 * @param iTime A mutable float state representing the time for shader animations.
 *
 * Note that this composable is for full-screen weather scenes backgrounds.
 * usage , put it inside a entire screen that you want to display the weather scene effect as background.
 * **/
@Composable
fun WeatherScenesLayer(
    modifier: Modifier = Modifier,
    weatherCondition: WeatherCondition,
    shaders: WeatherSceneShaders,
    iTime: MutableFloatState
) {
    Log.d("WeatherCondition", "Current value in ScenesLayer: $weatherCondition")
    val backgroundColors = when (weatherCondition) {
        WeatherCondition.RAINY, WeatherCondition.DRIZZLE, WeatherCondition.FREEZING_RAIN -> listOf(
            WeatherColors.WeatherCardBackgroundTransparent,
            WeatherColors.RainyBackgroundVariant
        )

        WeatherCondition.SHOWER, WeatherCondition.THUNDERSTORM -> listOf(
            WeatherColors.WeatherCardBackgroundTransparent,
            WeatherColors.RainyBackgroundVariant
        )

        WeatherCondition.CLEAR, WeatherCondition.MOSTLY_CLEAR -> listOf(
            WeatherColors.SunnyBackground,
            WeatherColors.SunnyBackgroundVariant
        )

        WeatherCondition.CLOUDY, WeatherCondition.PARTLY_CLOUDY -> listOf(
            WeatherColors.SunnyBackground,
            WeatherColors.SunnyBackgroundVariant
        )

        WeatherCondition.FOGGY -> listOf(
            WeatherColors.FoggyBackground,
            WeatherColors.FoggyBackgroundVariant
        )

        WeatherCondition.SNOWY, WeatherCondition.HEAVY_SNOWY -> listOf(
            WeatherColors.SnowyBackground,
            WeatherColors.SnowyBackgroundVariant
        )

        WeatherCondition.STARRY, WeatherCondition.PARTLY_STARRY -> listOf(
            WeatherColors.StarryBackground,
            WeatherColors.StarryBackgroundVariant
        )

        WeatherCondition.UNKNOWN -> listOf(
            WeatherColors.SunnyBackground,
            WeatherColors.SunnyBackgroundVariant
        )
    }

    val scene = when (weatherCondition) {
        WeatherCondition.RAINY, WeatherCondition.DRIZZLE,
        WeatherCondition.SHOWER, WeatherCondition.THUNDERSTORM,
        WeatherCondition.FREEZING_RAIN -> shaders.rainy

        WeatherCondition.CLEAR, WeatherCondition.MOSTLY_CLEAR -> null //shaders.sunny

        WeatherCondition.SNOWY, WeatherCondition.HEAVY_SNOWY -> shaders.snowy

        WeatherCondition.CLOUDY, WeatherCondition.PARTLY_CLOUDY, WeatherCondition.FOGGY -> null // shaders.cloudy

        WeatherCondition.STARRY, WeatherCondition.PARTLY_STARRY -> shaders.starry //shaders.starry
        WeatherCondition.UNKNOWN -> null // shaders.sunny
    }
    Log.d("WeatherCondition", "SCENE Current value in ScenesLayer: $scene")
    val finalModifier = if (scene != null) {
        modifier.applyShaderRuntimeEffect(
            shader = scene,
            iTime = { iTime.floatValue },
            uniformName = ShaderComposeLayers.WEATHER_SCENE.value
        )
    } else {
        modifier
            .fillMaxSize()
    }
    Box(
        modifier = finalModifier
            .background(
                brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                    colors = backgroundColors
                )
            )
    )
}