package com.kyrie.aether.weatherCore.configs

sealed class StarProfile(
    override val low: StarConfig,
    override val medium: StarConfig,
    override val high: StarConfig
) : WeatherProfile<StarConfig> {

    object Moderate : StarProfile(
        low = StarConfig(
            dropletCount = 50,
            minRadiusPx = 4f,
            maxRadiusPx = 5f,
            upperLimit = 0.5f,
            minOpacity = 0.4f,
            maxOpacity = 0.8f,
            dropletSoftness = 0.3f,
            baseAlpha = 0.9f,
            timeSpread = 20f  // Time variation between stars
        ),
        medium = StarConfig(
            dropletCount = 70,
            minRadiusPx = 5f,
            maxRadiusPx = 6f,
            upperLimit = 0.5f,
            minOpacity = 0.5f,
            maxOpacity = 1.0f,
            dropletSoftness = 0.25f,
            baseAlpha = 1.0f,
            timeSpread = 25f
        ),
        high = StarConfig(
            dropletCount = 100,
            minRadiusPx = 6f,
            maxRadiusPx = 7f,
            upperLimit = 0.5f,
            minOpacity = 0.6f,
            maxOpacity = 1.2f,
            dropletSoftness = 0.2f,
            baseAlpha = 1.0f,
            timeSpread = 30f
        )
    )

    object Heavy: StarProfile(
        low = StarConfig(
            dropletCount = 80,
            minRadiusPx = 5f,
            maxRadiusPx = 7f,
            upperLimit = 0.5f,
            minOpacity = 0.5f,
            maxOpacity = 1.0f,
            dropletSoftness = 0.25f,
            baseAlpha = 1.0f,
            timeSpread = 25f
        ),
        medium = StarConfig(
            dropletCount = 120,
            minRadiusPx = 6f,
            maxRadiusPx = 8f,
            upperLimit = 0.5f,
            minOpacity = 0.6f,
            maxOpacity = 1.2f,
            dropletSoftness = 0.2f,
            baseAlpha = 1.0f,
            timeSpread = 30f
        ),
        high = StarConfig(
            dropletCount = 140,
            minRadiusPx = 7f,
            maxRadiusPx = 9f,
            upperLimit = 0.5f,
            minOpacity = 0.7f,
            maxOpacity = 1.4f,
            dropletSoftness = 0.15f,
            baseAlpha = 1.0f,
            timeSpread = 35f
        )
    )

}