package com.kyrie.aether.utility.shaders

enum class ShaderUniforms(val value: String) {
    I_TIME("iTime"),
    I_RESOLUTION("iResolution"),
    COLLISION_BOUNDARY("collisionBoundary"),
    COLLISION_Y("collisionY"),
    COLLISION_ENABLED("collisionEnabled"),
}

enum class ShaderComposeLayers(val value: String) {
    FALLING_DROPLET("fallingDropletShaderComposable"),
    FADING_DROPLET("fadingDropletShaderComposable"),
    WEATHER_SCENE("weatherSceneShaderComposable"),
}