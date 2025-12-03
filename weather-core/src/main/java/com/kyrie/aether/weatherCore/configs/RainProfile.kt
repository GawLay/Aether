package com.kyrie.aether.weatherCore.configs

sealed class RainProfile(
    override val low: RainConfig,
    override val medium: RainConfig,
    override val high: RainConfig,
) : WeatherProfile<RainConfig> {
    object Light : RainProfile(
        low = RainConfig(15, 0.15f, 10f, 4f, 8f, 0.3f, 0.7f, 0.8f, 0.4f),
        medium = RainConfig(25, 0.2f, 10f, 4f, 10f, 0.3f, 0.8f, 0.7f, 0.45f),
        high = RainConfig(40, 0.2f, 10f, 5f, 12f, 0.4f, 0.9f, 0.7f, 0.5f),
    )

    object Moderate : RainProfile(
        low = RainConfig(30, 0.25f, 10f, 4f, 10f, 0.4f, 0.8f, 0.7f, 0.45f),
        medium = RainConfig(60, 0.3f, 10f, 5f, 12f, 0.4f, 0.9f, 0.7f, 0.5f),
        high = RainConfig(80, 0.3f, 10f, 5f, 13f, 0.4f, 1f, 0.7f, 0.5f),
    )

    object Heavy : RainProfile(
        low = RainConfig(40, 0.35f, 8f, 5f, 12f, 0.5f, 0.9f, 0.6f, 0.5f),
        medium = RainConfig(90, 0.4f, 8f, 5f, 14f, 0.5f, 1f, 0.6f, 0.55f),
        high = RainConfig(120, 0.5f, 8f, 6f, 15f, 0.5f, 1f, 0.6f, 0.6f),
    )
}
