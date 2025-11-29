package com.kyrie.aether.ui.model

import com.kyrie.aether.utility.extensions.formatToFullDateAndTime
import com.kyrie.aether.utility.weather.toStringIcon
import com.kyrie.aether.weatherCore.WeatherCondition
import com.kyrie.aether.weatherCore.WeatherIcon

object SampleWeatherData {

    val currentWeather = CurrentWeatherUiModel(
        time = "2024-11-29T14:00".formatToFullDateAndTime(),
        temperature = "29°C",
        condition = WeatherCondition.RAINY,
        icon = WeatherIcon.RAIN,
        isDay = true,
        windSpeed = "16 km/h",
        windDirection = 180,
    )

    val hourlyForecast = listOf(
        HourlyUiModel(
            time = "Now",
            temperature = "29°C",
            feelsLike = "32°C",
            condition = WeatherCondition.RAINY,
            iconString = WeatherIcon.RAIN.toStringIcon(),
            precipitationChance = "80%",
            pressure = "1013 hPa",
            uvIndex = "Moderate (5)",
            windSpeed10m = "16 km/h",
        ),
        HourlyUiModel(
            time = "3:00 PM",
            temperature = "28°C",
            feelsLike = "31°C",
            condition = WeatherCondition.RAINY,
            iconString = WeatherIcon.RAIN.toStringIcon(),
            precipitationChance = "70%",
            pressure = "1012 hPa",
            uvIndex = "Moderate (4)",
            windSpeed10m = "14 km/h",
        ),
        HourlyUiModel(
            time = "4:00 PM",
            temperature = "27°C",
            feelsLike = "30°C",
            condition = WeatherCondition.CLOUDY,
            iconString = WeatherIcon.CLOUDY.toStringIcon(),
            precipitationChance = "50%",
            pressure = "1012 hPa",
            uvIndex = "Low (3)",
            windSpeed10m = "12 km/h",
        ),
        HourlyUiModel(
            time = "5:00 PM",
            temperature = "27°C",
            feelsLike = "29°C",
            condition = WeatherCondition.PARTLY_CLOUDY,
            iconString = WeatherIcon.PARTLY_CLOUDY_DAY.toStringIcon(),
            precipitationChance = "40%",
            pressure = "1011 hPa",
            uvIndex = "Low (2)",
            windSpeed10m = "10 km/h",
        ),
        HourlyUiModel(
            time = "6:00 PM",
            temperature = "26°C",
            feelsLike = "28°C",
            condition = WeatherCondition.CLEAR,
            iconString = WeatherIcon.CLEAR_DAY.toStringIcon(),
            precipitationChance = "20%",
            pressure = "1011 hPa",
            uvIndex = "Low (1)",
            windSpeed10m = "8 km/h",
        )
    )

    val dailyForecast = listOf(
        DailyUiModel(
            date = "Today",
            fullDate = "Friday, November 29",
            condition = WeatherCondition.RAINY,
            iconString = WeatherIcon.RAIN.toStringIcon(),
            maxTemp = "29°C",
            minTemp = "24°C",
            tempRange = "24° - 29°",
            uvIndex = "Moderate (5)",
            sunrise = "5:48 AM",
            sunset = "5:37 PM",
            precipitationChance = "30%"
        ),
        DailyUiModel(
            date = "Sat",
            fullDate = "Saturday, November 30",
            condition = WeatherCondition.RAINY,
            iconString = WeatherIcon.RAIN.toStringIcon(),
            maxTemp = "30°C",
            minTemp = "24°C",
            tempRange = "24° - 30°",
            uvIndex = "High (6)",
            sunrise = "5:48 AM",
            sunset = "5:37 PM",
            precipitationChance = "30%"
        ),
        DailyUiModel(
            date = "Sun",
            fullDate = "Sunday, December 1",
            condition = WeatherCondition.THUNDERSTORM,
            iconString = WeatherIcon.THUNDERSTORM.toStringIcon(),
            maxTemp = "30°C",
            minTemp = "25°C",
            tempRange = "25° - 30°",
            uvIndex = "High (7)",
            sunrise = "5:49 AM",
            sunset = "5:38 PM",
            precipitationChance = "30%"
        ),
        DailyUiModel(
            date = "Mon",
            fullDate = "Monday, December 2",
            condition = WeatherCondition.CLOUDY,
            iconString = WeatherIcon.CLOUDY.toStringIcon(),
            maxTemp = "31°C",
            minTemp = "25°C",
            tempRange = "25° - 31°",
            uvIndex = "Moderate (5)",
            sunrise = "5:49 AM",
            sunset = "5:38 PM",
            precipitationChance = "30%"
        ),
        DailyUiModel(
            date = "Tue",
            fullDate = "Tuesday, December 3",
            condition = WeatherCondition.PARTLY_CLOUDY,
            iconString = WeatherIcon.PARTLY_CLOUDY_DAY.toStringIcon(),
            maxTemp = "32°C",
            minTemp = "26°C",
            tempRange = "26° - 32°",
            uvIndex = "High (7)",
            sunrise = "5:49 AM",
            sunset = "5:39 PM",
            precipitationChance = "30%"
        ),
        DailyUiModel(
            date = "Wed",
            fullDate = "Wednesday, December 4",
            condition = WeatherCondition.CLEAR,
            iconString = WeatherIcon.CLEAR_DAY.toStringIcon(),
            maxTemp = "33°C",
            minTemp = "26°C",
            tempRange = "26° - 33°",
            uvIndex = "Very High (8)",
            sunrise = "5:50 AM",
            sunset = "5:39 PM",
            precipitationChance = "30%"
        ),
        DailyUiModel(
            date = "Thu",
            fullDate = "Thursday, December 5",
            condition = WeatherCondition.CLEAR,
            iconString = WeatherIcon.CLEAR_DAY.toStringIcon(),
            maxTemp = "33°C",
            minTemp  = "27°C",
            tempRange = "27° - 33°",
            uvIndex = "Very High (9)",
            sunrise = "5:50 AM",
            sunset = "5:40 PM",
            precipitationChance = "30%"
        )
    )

}