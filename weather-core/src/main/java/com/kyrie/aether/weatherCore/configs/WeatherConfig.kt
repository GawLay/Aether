package com.kyrie.aether.weatherCore.configs

sealed interface WeatherConfig {
    val dropletCount: Int
    val fallSpeed: Float
    val timeSpread: Float
    val minRadiusPx: Float
    val maxRadiusPx: Float
    val minOpacity: Float
    val maxOpacity: Float
    val dropletSoftness: Float
    val baseAlpha: Float
}
