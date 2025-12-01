package com.kyrie.aether.weatherCore.configs

import com.kyrie.aether.weatherCore.DeviceUtil

sealed interface WeatherProfile<T : WeatherConfig> {
    val low: T
    val medium: T
    val high: T
    
    fun forTier(): T = when (DeviceUtil.deviceTier) {
        DeviceUtil.DeviceTier.LOW -> low
        DeviceUtil.DeviceTier.MEDIUM -> medium
        DeviceUtil.DeviceTier.HIGH -> high
    }
}