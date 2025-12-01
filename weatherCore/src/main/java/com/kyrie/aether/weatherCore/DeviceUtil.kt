package com.kyrie.aether.weatherCore

/**
 * Just a device racist utility to detect device performance tier XD
 */
object DeviceUtil {
    /**
     * Detect device performance tier
     */
    val deviceTier: DeviceTier by lazy {
        when {
            Runtime.getRuntime().availableProcessors() >= 8 -> DeviceTier.HIGH
            Runtime.getRuntime().availableProcessors() <= 4 -> DeviceTier.LOW
            else -> DeviceTier.MEDIUM
        }
    }

    enum class DeviceTier {
        LOW,    // Budget phones, older devices
        MEDIUM, // Mid-range phones
        HIGH    // Flagship phones
    }
}