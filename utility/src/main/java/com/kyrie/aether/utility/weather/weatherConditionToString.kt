package com.kyrie.aether.utility.weather

import com.kyrie.aether.weatherCore.WeatherCondition
import com.kyrie.aether.weatherCore.WeatherIcon

/**
 * to display in UI with proper string
 * **/
fun WeatherCondition.weatherConditionToString(): String {
    when (this) {
        WeatherCondition.RAINY -> return "Rainy"
        WeatherCondition.DRIZZLE -> return "Drizzle"
        WeatherCondition.FREEZING_RAIN -> return "Freezing Rain"
        WeatherCondition.SHOWER -> return "Shower"
        WeatherCondition.THUNDERSTORM -> return "Thunderstorm"

        WeatherCondition.CLEAR -> return "Clear"
        WeatherCondition.MOSTLY_CLEAR -> return "Haze"

        WeatherCondition.CLOUDY -> return "Cloudy"
        WeatherCondition.PARTLY_CLOUDY -> return "Partly Cloudy"
        WeatherCondition.FOGGY -> return "Foggy"

        WeatherCondition.SNOWY -> return "Snowy"
        WeatherCondition.HEAVY_SNOWY -> return "Heavy Snowy"

        WeatherCondition.STARRY -> return "Starry"
        WeatherCondition.PARTLY_STARRY -> return "Partly Starry"
        WeatherCondition.UNKNOWN -> return "Unknown"
    }
}

/**
 * to display in UI with proper ICON
 * **/
fun WeatherIcon.toStringIcon(): String {
    return when (this) {
        WeatherIcon.RAIN -> "🌧️"
        WeatherIcon.DRIZZLE -> "🌧️"
        WeatherIcon.SHOWER -> "🌦️"
        WeatherIcon.THUNDERSTORM -> "⛈️"

        WeatherIcon.CLEAR_DAY -> "☀️"
        WeatherIcon.CLEAR_NIGHT -> "🌕"
        WeatherIcon.MOSTLY_CLEAR_DAY -> "🌤️"
        WeatherIcon.MOSTLY_CLEAR_NIGHT -> "🌙"

        WeatherIcon.CLOUDY -> "☁️"
        WeatherIcon.PARTLY_CLOUDY_DAY -> "⛅"
        WeatherIcon.PARTLY_CLOUDY_NIGHT -> "🌑"
        WeatherIcon.FOG -> "🌫️"

        WeatherIcon.SNOW, WeatherIcon.HEAVY_SNOW -> "❄️"

        WeatherIcon.UNKNOWN -> "❓"
        WeatherIcon.FREEZING_RAIN -> "🧊🌧️"
    }
}