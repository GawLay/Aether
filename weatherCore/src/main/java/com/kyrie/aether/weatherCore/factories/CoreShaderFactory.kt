package com.kyrie.aether.weatherCore.factories

import com.kyrie.aether.weatherCore.WeatherCondition
import com.kyrie.aether.weatherCore.configs.RainConfig
import com.kyrie.aether.weatherCore.configs.RainProfile
import com.kyrie.aether.weatherCore.configs.SnowConfig
import com.kyrie.aether.weatherCore.configs.SnowProfile
import com.kyrie.aether.weatherCore.configs.StarConfig
import com.kyrie.aether.weatherCore.configs.StarProfile

object CoreShaderFactory {
    fun getRainConfig(condition: WeatherCondition): RainConfig? =
        when (condition) {
            WeatherCondition.DRIZZLE -> RainProfile.Light.forTier()
            WeatherCondition.FREEZING_RAIN, WeatherCondition.RAINY -> RainProfile.Moderate.forTier()

            WeatherCondition.SHOWER, WeatherCondition.THUNDERSTORM -> RainProfile.Heavy.forTier()

            else -> null
        }

    fun getSnowConfig(condition: WeatherCondition): SnowConfig? =
        when (condition) {
            WeatherCondition.SNOWY -> SnowProfile.Moderate.forTier()
            WeatherCondition.HEAVY_SNOWY -> SnowProfile.Heavy.forTier()
            else -> null
        }

    fun getStarConfig(condition: WeatherCondition): StarConfig? =
        when (condition) {
            WeatherCondition.STARRY -> StarProfile.Heavy.forTier()
            WeatherCondition.PARTLY_STARRY -> StarProfile.Moderate.forTier()
            else -> null
        }
}
