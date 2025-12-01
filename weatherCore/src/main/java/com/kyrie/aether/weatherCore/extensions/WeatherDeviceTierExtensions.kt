package com.kyrie.aether.weatherCore.extensions

import com.kyrie.aether.weatherCore.DeviceUtil
import com.kyrie.aether.weatherCore.configs.RainConfig
import com.kyrie.aether.weatherCore.configs.RainProfile

fun RainProfile.forTier(tier: DeviceUtil.DeviceTier): RainConfig {
    return when (tier) {
        DeviceUtil.DeviceTier.LOW -> low
        DeviceUtil.DeviceTier.MEDIUM -> medium
        DeviceUtil.DeviceTier.HIGH -> high
    }
}