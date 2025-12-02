package com.kyrie.aether.weatherCore.configs

data class StarConfig(
    // aka star amount
    override val dropletCount: Int,
    // size of stars
    override val minRadiusPx: Float,
    override val maxRadiusPx: Float,
    val upperLimit: Float = 0.5f, // 0.5 = top 50%, 0.3 = top 30%, 1.0 = full screen
    // brightness of stars
    override val minOpacity: Float, // Used as minBrightness
    override val maxOpacity: Float, // Used as maxBrightness
    override val dropletSoftness: Float,
    override val baseAlpha: Float,
    // Twinkling properties
    override val timeSpread: Float, // Used for time variation between stars
    val twinkleSpeed: Float = 1.0f, // Overall twinkling speed
    val twinkleAmount: Float = 0.9f, // How much they fade (0 = no fade, 1 = full fade)
    val minTwinkleSpeed: Float = 0.5f, // Minimum  individual star speed multiplier
    val maxTwinkleSpeed: Float = 1.5f, // Maximum  individual star speed multiplier
    val minVisibleRatio: Float = 0.1f, // Minimum visibility, when faded (0.1 = 10% of min brightness)
    override val fallSpeed: Float = 0f, // Not used for stars
) : WeatherConfig
