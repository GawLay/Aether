package com.kyrie.aether.data.remote.api

import com.kyrie.aether.data.remote.dto.CurrentWeatherDto
import com.kyrie.aether.data.remote.dto.DailyDto
import com.kyrie.aether.data.remote.dto.HourlyDto
import com.kyrie.aether.data.remote.dto.WeatherResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("current_weather") current: Boolean = true,
        @Query("hourly") hourly: String = "temperature_2m,weathercode",
        @Query("daily") daily: String = "temperature_2m_max,temperature_2m_min,weathercode",
        @Query("timezone") timezone: String = "auto"
    ): WeatherResponseDto



    @GET("v1/forecast")
    suspend fun getHourlyWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourly: String = "temperature_2m,weather_code,rain,showers,relative_humidity_2m," +
                "apparent_temperature,precipitation,precipitation_probability,snow_depth,snowfall,pressure_msl," +
                "surface_pressure,cloud_cover,cloud_cover_low,cloud_cover_mid,cloud_cover_high,visibility," +
                "evapotranspiration,wind_speed_10m,wind_speed_80m,wind_speed_180m,wind_direction_120m," +
                "wind_direction_80m,wind_direction_10m,wind_speed_120m,wind_gusts_10m,temperature_80m," +
                "temperature_120m,wind_direction_180m,temperature_180m,dew_point_2m",
        @Query("timezone") timezone: String = "auto"
    ): WeatherResponseDto

    @GET("v1/forecast")
    suspend fun getDailyWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("daily") daily: String = "weather_code,temperature_2m_max,temperature_2m_min," +
                "apparent_temperature_max,apparent_temperature_min,sunrise,sunset,sunshine_duration,daylight_duration," +
                "uv_index_max,uv_index_clear_sky_max,showers_sum,rain_sum,snowfall_sum,precipitation_sum," +
                "precipitation_probability_max,precipitation_hours,wind_speed_10m_max,wind_gusts_10m_max," +
                "wind_direction_10m_dominant,shortwave_radiation_sum,et0_fao_evapotranspiration",
        @Query("timezone") timezone: String = "auto"
    ): WeatherResponseDto

    @GET("v1/forecast")
    suspend fun getCurrentWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current_weather") current: Boolean = true,
        @Query("current") currentParams: String = "temperature_2m,is_day,weather_code,wind_speed_10m," +
                "wind_direction_10m,wind_gusts_10m,apparent_temperature,relative_humidity_2m,precipitation," +
                "rain,showers,snowfall,cloud_cover,pressure_msl,surface_pressure",
        @Query("timezone") timezone: String = "auto"
    ): WeatherResponseDto
}