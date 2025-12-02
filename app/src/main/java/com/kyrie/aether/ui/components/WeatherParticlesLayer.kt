package com.kyrie.aether.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.ui.Modifier
import com.kyrie.aether.utility.shaders.model.ParticleShaders
import com.kyrie.aether.weatherCore.WeatherCondition

/**
 * A composable that displays weather scene layers based on the given weather condition.
 * @param modifier The modifier to be applied to the composable.
 * @param weatherCondition The current weather condition.
 * @param shaders The weather particle shaders to be used for rendering.
 * @param iTime A mutable float state representing the time for shader animations.
 *
 * Note that  this composable is for weather particle layers like rain and snow
 * for the specific compose view not entire screen.
 *
 * usage , put it inside a Box that you want to display the weather particles effect over the content.
 * **/
@Composable
fun WeatherParticlesLayer(
    modifier: Modifier = Modifier,
    weatherCondition: WeatherCondition,
    shaders: ParticleShaders,
    iTime: MutableFloatState,
) {
    val layers =
        when (weatherCondition) {
            WeatherCondition.RAINY ->
                listOf<@Composable (Modifier) -> Unit>(
                    { FallingDropletLayer(it, shaders.fallingDroplet!!, iTime) },
                    { FadingDropletLayer(it, shaders.fadingDroplet!!, iTime) },
                )

            WeatherCondition.SNOWY ->
                listOf<@Composable (Modifier) -> Unit>(
                    { FallingDropletLayer(it, shaders.fallingDroplet!!, iTime) },
                    { FadingDropletLayer(it, shaders.fadingDroplet!!, iTime) },
                )

            WeatherCondition.STARRY -> emptyList()
            else -> emptyList() // todo No layers for other weather conditions for now. will implement later
        }

    if (layers.isNotEmpty()) {
        ShaderLayers(modifier = modifier, layers = layers)
    }
}
