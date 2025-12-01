package com.kyrie.aether.weatherCore.configs

data class SnowConfig(
    override val dropletCount: Int,
    override val fallSpeed: Float,
    override val timeSpread: Float,
    override val minRadiusPx: Float,
    override val maxRadiusPx: Float,
    override val minOpacity: Float,
    override val maxOpacity: Float,
    override val dropletSoftness: Float,
    override val baseAlpha: Float
) : WeatherConfig
