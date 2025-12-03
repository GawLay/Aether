package com.kyrie.aether.weatherCore

enum class WeatherCondition {
    DRIZZLE,
    RAINY,
    FREEZING_RAIN,
    SHOWER,
    THUNDERSTORM,
    CLEAR, // SUNNY sky
    MOSTLY_CLEAR,
    CLOUDY,
    PARTLY_CLOUDY,

    FOGGY,
    SNOWY,
    HEAVY_SNOWY,

    STARRY, // clear night sky
    PARTLY_STARRY, // partly cloudy night sky
    UNKNOWN,
}

enum class DayNight {
    DAY,
    NIGHT,
}

/**
* will not implement icons for all weather conditions yet
* */
enum class WeatherIcon {
    CLEAR_DAY,
    CLEAR_NIGHT,

    MOSTLY_CLEAR_DAY,
    MOSTLY_CLEAR_NIGHT,

    PARTLY_CLOUDY_DAY,
    PARTLY_CLOUDY_NIGHT,

    CLOUDY,

    FOG,

    DRIZZLE,
    RAIN,
    FREEZING_RAIN,

    SNOW,
    HEAVY_SNOW,

    SHOWER,
    THUNDERSTORM,

    UNKNOWN,
}
