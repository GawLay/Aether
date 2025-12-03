package com.kyrie.aether.weatherCore.extensions

import com.kyrie.aether.weatherCore.DayNight
import com.kyrie.aether.weatherCore.WeatherCondition
import com.kyrie.aether.weatherCore.WeatherIcon

/***
 * @param dayNightCode : Int? --> 1 for day, 0 for night,
 * pass null when you don't need day night status, because we will show day night status only in current weather
 * don't need it for hourly and daily weather
 */
fun Int.weatherCodeToCondition(dayNightCode: Int?): WeatherCondition =
    when (this) {
        0 -> {
            if (dayNightCode != null) {
                if (dayNightCode.isDay()) {
                    WeatherCondition.CLEAR
                } else {
                    WeatherCondition.STARRY
                }
            } else {
                WeatherCondition.CLEAR
            }
        }

        1 -> {
            if (dayNightCode != null) {
                if (dayNightCode.isDay()) {
                    WeatherCondition.MOSTLY_CLEAR
                } else {
                    WeatherCondition.STARRY
                }
            } else {
                WeatherCondition.MOSTLY_CLEAR
            }
        }

        2 -> {
            if (dayNightCode != null) {
                if (dayNightCode.isDay()) {
                    WeatherCondition.PARTLY_CLOUDY
                } else {
                    WeatherCondition.PARTLY_STARRY
                }
            } else {
                WeatherCondition.PARTLY_CLOUDY
            }
        }

        3 -> WeatherCondition.CLOUDY

        45, 48 -> WeatherCondition.FOGGY

        in 51..57 -> WeatherCondition.DRIZZLE // 51-53 normal drizzle, 54-55, moderate, 56-7 freezing

        in 61..63 -> WeatherCondition.RAINY
        in 64..67 -> WeatherCondition.FREEZING_RAIN

        in 71..73 -> WeatherCondition.SNOWY // 71-2 normal snow, 72-3 moderate.
        in 74..77 -> WeatherCondition.HEAVY_SNOWY // 74-5 heavy snow, 75-77 (grains snow)

        in 80..82 -> WeatherCondition.SHOWER // 80 light heavy rain shower, 81 moderate, 82 violent
        in 95..99 -> WeatherCondition.THUNDERSTORM // 95 --> normal thunderstorm, 96-99 with hail

        else -> WeatherCondition.UNKNOWN
    }

fun Int.isDay(): Boolean = this.codeToDayAndNight() == DayNight.DAY

fun Int.codeToDayAndNight(): DayNight = if (this == 1) DayNight.DAY else DayNight.NIGHT

fun Int.weatherCodeToWeatherIcon(): WeatherIcon =
    when (this) {
        0 -> WeatherIcon.CLEAR_DAY

        1 -> WeatherIcon.MOSTLY_CLEAR_DAY

        2 -> WeatherIcon.PARTLY_CLOUDY_DAY

        3 -> WeatherIcon.CLOUDY

        45, 48 -> WeatherIcon.FOG

        in 51..57 -> WeatherIcon.DRIZZLE

        in 61..63 -> WeatherIcon.RAIN
        in 64..67 -> WeatherIcon.FREEZING_RAIN

        in 71..73 -> WeatherIcon.SNOW
        in 74..77 -> WeatherIcon.HEAVY_SNOW

        in 80..82 -> WeatherIcon.SHOWER

        in 95..99 -> WeatherIcon.THUNDERSTORM

        else -> WeatherIcon.UNKNOWN
    }
