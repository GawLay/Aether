package com.kyrie.aether.utility.shaders.model

import android.graphics.RuntimeShader

data class ParticleShaders(
    val fallingDroplet: RuntimeShader? = null,
    val fadingDroplet: RuntimeShader? = null,
    //  fields for future layers
    val snowLayer: RuntimeShader? = null,
    val thunderLayer: RuntimeShader? = null,
)

data class WeatherSceneShaders(
    val rainy: RuntimeShader? = null,
    val snowy: RuntimeShader? = null,
    val starry: RuntimeShader? = null,
    val sunny: RuntimeShader? = null,
    val cloudy: RuntimeShader? = null,
)
