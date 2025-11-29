package com.kyrie.aether.utility.shaders

import android.graphics.RuntimeShader
import com.kyrie.aether.utility.shaders.model.ParticleShaders
import com.kyrie.aether.utility.shaders.model.WeatherSceneShaders
import com.kyrie.aether.utility.shaders.scripts.Droplets
import com.kyrie.aether.utility.shaders.scripts.RainScene

object ShaderFactory {
    private fun createShader(shaderCode: String): RuntimeShader {
        return RuntimeShader(shaderCode)
    }

    fun fadingDropletShader(): RuntimeShader {
        return createShader(Droplets.FADING_DROPLET_LAYER)
    }

    fun fallingDropletShader(): RuntimeShader {
        return createShader(Droplets.FALLING_DROPLET_LAYER)
    }

    fun rainSceneShader(): RuntimeShader {
        return createShader(RainScene.RAIN_LAYER)
    }

    fun createParticleShaders(): ParticleShaders {
        return ParticleShaders(
            fallingDroplet = fallingDropletShader(),
            fadingDroplet = fadingDropletShader()
        )
    }

    fun createSceneShaders(): WeatherSceneShaders {
        return WeatherSceneShaders(
            rainy = rainSceneShader()
        )
    }
}