package com.kyrie.aether.weatherCore.configs

sealed class SnowProfile(
    override val low: SnowConfig,
    override val medium: SnowConfig,
    override val high: SnowConfig
) : WeatherProfile<SnowConfig> {
    //for this  profile, I won't use Light snow fall, i will use normal and heavy only
    object Moderate : SnowProfile(
        low = SnowConfig(40, 0.06f, 10f, 6f, 12f, 0.6f, 1f, 0.9f, 0.6f),
        medium = SnowConfig(70, 0.08f, 10f, 7f, 14f, 0.6f, 1f, 0.9f, 0.7f),
        high = SnowConfig(110, 0.1f, 10f, 8f, 16f, 0.6f, 1f, 0.9f, 0.75f)
    )

    object Heavy : SnowProfile(
        low = SnowConfig(70, 0.1f, 10f, 7f, 14f, 0.7f, 1f, 0.9f, 0.7f),
        medium = SnowConfig(120, 0.12f, 10f, 8f, 16f, 0.7f, 1f, 0.9f, 0.75f),
        high = SnowConfig(180, 0.15f, 10f, 9f, 18f, 0.7f, 1f, 0.9f, 0.8f)
    )
}