package com.kyrie.aether.domain.model

import com.kyrie.aether.weatherCore.WeatherCondition
import com.kyrie.aether.weatherCore.WeatherIcon

data class CurrentWeatherDomain(
    val time: String,
    val temperature: Double,
    val temperatureUnit: String,
    val condition: WeatherCondition,
    val icon: WeatherIcon,
    val isDay: Boolean,
    val windSpeed: Double,
    val windSpeedUnit: String,
    val windDirection: Int,
)

data class HourlyDomain(
    val time: String,
    val temperature: Double,
    val temperatureUnit: String,
    val feelsLike: Double,
    val condition: WeatherCondition,
    val icon: WeatherIcon,
    val precipitationChance: Int,
    val precipitationUnit: String,
    val humidity: Int,
    val pressure: Double,
    val uvIndex: Double,
    val windSpeed10m: Double,
    val windSpeed10mUnit: String,
    val gustSpeed: Double,
)

data class DailyDomain(
    val date: String,
    val temperatureUnit: String,
    val condition: WeatherCondition,
    val icon: WeatherIcon,
    val maxTemp: Double,
    val minTemp: Double,
    val uvIndex: Double,
    val sunrise: String,
    val sunset: String,
    val precipitationChance: String,
    val precipitationUnit: String,
)
