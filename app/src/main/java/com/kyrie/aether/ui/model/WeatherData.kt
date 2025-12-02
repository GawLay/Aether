package com.kyrie.aether.ui.model

import com.kyrie.aether.weatherCore.WeatherCondition
import com.kyrie.aether.weatherCore.WeatherIcon

// data class WeatherData(
//    val current: CurrentWeatherUiModel,
//    val hourly: List<HourlyUiModel>,
//    val daily: List<DailyUiModel>
// )

data class CurrentWeatherUiModel(
    val time: String,
    val temperature: String, // "25°C"
    val condition: WeatherCondition,
    val icon: WeatherIcon,
    val isDay: Boolean,
    val windSpeed: String, // "15 km/h"
    val windDirection: Int,
)

data class HourlyUiModel(
    val time: String, // "3:00 PM" or "15:00"
    val temperature: String, // "24°C"
    val feelsLike: String, // "27°C"
    val condition: WeatherCondition,
    val iconString: String, // emoji string representation of the icon
    val precipitationChance: String, // "60%"
    val pressure: String, // "1012 hPa"
    val uvIndex: String, // "High (8)"
    val windSpeed10m: String, // "12 km/h"
)

data class DailyUiModel(
    val date: String, // "Monday" or "Jan 15"
    val fullDate: String, // "Monday, January 15"
    val condition: WeatherCondition,
    val iconString: String,
    val maxTemp: String, // "28°C"
    val minTemp: String, // "18°C"
    val tempRange: String, // "18° - 28°"
    val uvIndex: String, // "Moderate (5)"
    val sunrise: String, // "6:30 AM"
    val sunset: String, // "6:45 PM"
    val precipitationChance: String, // "70%",
)

// Sample data for demonstration
// object SampleWeatherData {
//    val current = WeatherData(
//        location = "Ho Chi Minh City",
//        currentTemp = 29,
//        condition = "Rain",
//        highTemp = 29,
//        lowTemp = 24,
//        description = "Rainy conditions will continue for the rest of the day. Wind gusts are up to 16 km/h.",
//        hourlyForecast = listOf(
//            HourlyWeather("Now", 29, "Rain", 80, isNow = true),
//            HourlyWeather("3PM", 28, "Rain", 45),
//            HourlyWeather("4PM", 27, "Rain", 50),
//            HourlyWeather("5PM", 27, "Rain", 45),
//            HourlyWeather("5:37PM", 0, "Sunset", isSunset = true)
//        ),
//        dailyForecast = listOf(
//            DailyWeather("Today", "Rain", 29, 24, 80),
//            DailyWeather("Sun", "Rain", 30, 24, 85),
//            DailyWeather("Mon", "Rain", 30, 25, 80)
//        )
//    )
// }
