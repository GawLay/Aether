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
    fun createShader(shaderCode: String): RuntimeShader {
        return RuntimeShader(shaderCode)
    }

    fun fadingDropletShader(): RuntimeShader {
        return createShader(Droplets.FADING_DROPLET_LAYER)
    }

    fun fallingDropletShader(): RuntimeShader {
        return createShader(Droplets.FALLING_DROPLET_LAYER)
    }

    fun createParticleShaders(): ParticleShaders {
        return ParticleShaders(
            fallingDroplet = fallingDropletShader(),
            fadingDroplet = fadingDropletShader()
        )
    }

    fun createSceneShaders(
        weatherCondition: WeatherCondition
    ): WeatherSceneShaders {
        return WeatherSceneShaders(
            rainy = rainSceneShaderScript(weatherCondition),
            snowy = snowSceneShaderScript(weatherCondition),
            starry = starSceneShaderScript(weatherCondition),
            sunny = null,
            cloudy = null
        )
    }


    private fun rainSceneShaderScript(weatherCondition: WeatherCondition): RuntimeShader? {
        return RainScene.createRainShaderForCondition(weatherCondition)
    }

    private fun snowSceneShaderScript(weatherCondition: WeatherCondition): RuntimeShader? {
        return SnowScene.createSnowShaderForCondition(weatherCondition)
    }

    private fun starSceneShaderScript(weatherCondition: WeatherCondition): RuntimeShader? {
        return StarScene.createStarShaderForCondition(weatherCondition)
    }

}