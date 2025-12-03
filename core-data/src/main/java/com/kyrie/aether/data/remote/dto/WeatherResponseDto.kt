package com.kyrie.aether.data.remote.dto

data class WeatherResponseDto(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val current_weather_units: CurrentUnitDto? = null, // current weather
    val current_weather: CurrentWeatherDto? = null, // current weather
    val hourly_units: HourlyUnitDto? = null, // hourly weather
    val daily_units: DailyUnitDto? = null, // daily weather
    val hourly: HourlyDto? = null, // hourly weather
    val daily: DailyDto? = null, // daily weather
)

data class CurrentUnitDto(
    val time: String?,
    val interval: String?,
    val temperature: String?,
    val is_day: String?,
    val weathercode: String?,
    val windspeed: String?,
    val winddirection: String?,
)

data class HourlyUnitDto(
    val time: String?,
    val interval: String?,
    val temperature_2m: String?,
    val is_day: String?,
    val weather_code: String?,
    val wind_speed_10m: String?,
    val wind_direction_10m: String?,
    val wind_gusts_10m: String?,
    val apparent_temperature: String?,
    val relative_humidity_2m: String?,
    val precipitation: String?,
    val precipitation_probability: String?, // "%" (for hourly_units)
    val rain: String?,
    val showers: String?,
    val snowfall: String?,
    val cloud_cover: String?,
    val pressure_msl: String?,
    val surface_pressure: String?,
)

data class DailyUnitDto(
    val time: String?,
    val weather_code: String?,
    val temperature_2m_max: String?, // "°C" (for daily_units)
    val temperature_2m_min: String?,
    val apparent_temperature_max: String?,
    val apparent_temperature_min: String?,
    val sunrise: String?,
    val sunset: String?,
    val sunshine_duration: String?,
    val daylight_duration: String?,
    val uv_index_max: String?,
    val uv_index_clear_sky_max: String?,
    val showers_sum: String?,
    val rain_sum: String?,
    val snowfall_sum: String?,
    val precipitation_sum: String?,
    val precipitation_probability_max: String?, // "%"
    val precipitation_hours: String?,
    val wind_speed_10m_max: String?,
    val wind_gusts_10m_max: String?,
    val wind_direction_10m_dominant: String?,
    val shortwave_radiation_sum: String?,
    val et0_fao_evapotranspiration: String?,
)

data class CurrentWeatherDto(
    val time: String?,
    val interval: Int?,
    val temperature: Double?,
    val is_day: Int?,
    val weathercode: Int?,
    val windspeed: Double?,
    val winddirection: Int?,
)

data class HourlyDto(
    val time: List<String>?,
    val temperature_2m: List<Double>?,
    val apparent_temperature: List<Double>?,
    val weather_code: List<Int>?,
    val precipitation_probability: List<Int>?,
    val pressure_msl: List<Double>?,
    val surface_pressure: List<Double>?,
    val relative_humidity_2m: List<Int>?,
    val wind_speed_10m: List<Double>?,
    val wind_gusts_10m: List<Double>?,
    val wind_direction_10m: List<Int>?,
    val visibility: List<Double>?,
    val cloud_cover: List<Int>?,
    val dew_point_2m: List<Double>?,
    val uv_index: List<Double>? = null,
    val rain: List<Double>? = null,
    val showers: List<Double>? = null,
    val snowfall: List<Double>? = null,
    val snow_depth: List<Double>? = null,
    val precipitation: List<Double>? = null,
)

data class DailyDto(
    val time: List<String>?,
    val weather_code: List<Int>?,
    val temperature_2m_max: List<Double>?,
    val temperature_2m_min: List<Double>?,
    val sunrise: List<String>?,
    val sunset: List<String>?,
    val uv_index_max: List<Double>?,
    val precipitation_probability_max: List<Int>?,
)
