package com.kyrie.aether.utility.shaders

import android.graphics.RuntimeShader
import com.kyrie.aether.utility.shaders.model.ParticleShaders
import com.kyrie.aether.utility.shaders.model.WeatherSceneShaders
import com.kyrie.aether.utility.shaders.scripts.Droplets
import com.kyrie.aether.utility.shaders.scripts.RainScene
import com.kyrie.aether.utility.shaders.scripts.SnowScene
import com.kyrie.aether.utility.shaders.scripts.StarScene
import com.kyrie.aether.weatherCore.WeatherCondition

object ShaderUtil {
    fun createShader(shaderCode: String): RuntimeShader = RuntimeShader(shaderCode)

    fun fadingDropletShader(): RuntimeShader = createShader(Droplets.FADING_DROPLET_LAYER)

    fun fallingDropletShader(): RuntimeShader = createShader(Droplets.FALLING_DROPLET_LAYER)

    fun createParticleShaders(): ParticleShaders =
        ParticleShaders(
            fallingDroplet = fallingDropletShader(),
            fadingDroplet = fadingDropletShader(),
        )

    fun createSceneShaders(weatherCondition: WeatherCondition): WeatherSceneShaders =
        WeatherSceneShaders(
            rainy = rainSceneShaderScript(weatherCondition),
            snowy = snowSceneShaderScript(weatherCondition),
            starry = starSceneShaderScript(weatherCondition),
            sunny = sunnySceneShaderScript(weatherCondition), // todo implement later
            cloudy = cloudySceneShaderScript(weatherCondition), // todo implement later
        )

    private fun rainSceneShaderScript(weatherCondition: WeatherCondition): RuntimeShader? =
        RainScene.createRainShaderForCondition(weatherCondition)

    private fun snowSceneShaderScript(weatherCondition: WeatherCondition): RuntimeShader? =
        SnowScene.createSnowShaderForCondition(weatherCondition)

    private fun starSceneShaderScript(weatherCondition: WeatherCondition): RuntimeShader? =
        StarScene.createStarShaderForCondition(weatherCondition)

    private fun sunnySceneShaderScript(weatherCondition: WeatherCondition): RuntimeShader? = null

    private fun cloudySceneShaderScript(weatherCondition: WeatherCondition): RuntimeShader? = null
}
