package com.kyrie.aether.data.mapper

import com.kyrie.aether.data.remote.dto.CurrentUnitDto
import com.kyrie.aether.data.remote.dto.CurrentWeatherDto
import com.kyrie.aether.data.remote.dto.DailyDto
import com.kyrie.aether.data.remote.dto.DailyUnitDto
import com.kyrie.aether.data.remote.dto.HourlyDto
import com.kyrie.aether.data.remote.dto.HourlyUnitDto
import com.kyrie.aether.data.remote.dto.WeatherResponseDto
import com.kyrie.aether.domain.model.CurrentWeatherDomain
import com.kyrie.aether.domain.model.DailyDomain
import com.kyrie.aether.domain.model.HourlyDomain
import com.kyrie.aether.weatherCore.WeatherCondition
import com.kyrie.aether.weatherCore.WeatherIcon
import com.kyrie.aether.weatherCore.extensions.weatherCodeToCondition
import com.kyrie.aether.weatherCore.extensions.weatherCodeToWeatherIcon

fun WeatherResponseDto.toCurrentDomain(): CurrentWeatherDomain =
    current_weather.toDomain(current_weather_units)

fun WeatherResponseDto.toHourlyDomain(): List<HourlyDomain>? =
    hourly?.toDomainList(hourly_units) ?: emptyList()

fun WeatherResponseDto.toDailyDomain(): List<DailyDomain>? =
    daily?.toDomainList(daily_units) ?: emptyList()

fun printWeatherData(message: String) {
    println("Data Mapper Current weather code: $message")
}

fun CurrentWeatherDto?.toDomain(currentUnits: CurrentUnitDto?): CurrentWeatherDomain {
    if (this == null) {
        printWeatherData("Data Mapper Current weather data is null")
        return CurrentWeatherDomain(
            time = "2025-11-28T23:00",
            temperature = 0.0,
            temperatureUnit = "?",
            condition = WeatherCondition.UNKNOWN,
            icon = WeatherIcon.UNKNOWN,
            isDay = true,
            windSpeed = 0.0,
            windDirection = 0,
            windSpeedUnit = "?",
        )
    }
    val dayNight = is_day ?: 1
    val weatherCode = weathercode ?: 0
    val icon = weatherCode.weatherCodeToWeatherIcon()
    val condition = weatherCode.weatherCodeToCondition(dayNight)

    return CurrentWeatherDomain(
        time = time ?: "0000-00-00T00:00",
        temperature = temperature ?: 0.0,
        temperatureUnit = currentUnits?.temperature ?: "?",
        condition = condition,
        icon = icon,
        isDay = dayNight == 1,
        windSpeed = windspeed ?: 0.0,
        windSpeedUnit = currentUnits?.windspeed ?: "?",
        windDirection = winddirection ?: 0,
    )
}

fun HourlyDto.toDomainList(hourlyUnits: HourlyUnitDto?): List<HourlyDomain> {
    printWeatherData("Data Mapper Hourly weather data mapping started ${weather_code?.size ?: 0}")

    val timeList = time ?: emptyList()
    val weatherCodeList = weather_code ?: emptyList()
    val temperatureList = temperature_2m ?: emptyList()
    val apparentTempList = apparent_temperature ?: emptyList()
    val precipitationProbList = precipitation_probability ?: emptyList()
    val humidityList = relative_humidity_2m ?: emptyList()
    val pressureList = pressure_msl ?: emptyList()
    val windSpeedList = wind_speed_10m ?: emptyList()
    val gustSpeedList = wind_gusts_10m ?: emptyList()
    val uvIndexList = uv_index ?: emptyList()

    return timeList.mapIndexed { index, t ->
        val code = weatherCodeList.getOrNull(index) ?: 0
        HourlyDomain(
            time = t,
            temperature = temperatureList.getOrNull(index) ?: 0.0,
            temperatureUnit = hourlyUnits?.temperature_2m ?: "?",
            feelsLike = apparentTempList.getOrNull(index) ?: 0.0,
            condition = code.weatherCodeToCondition(null),
            icon = code.weatherCodeToWeatherIcon(),
            precipitationChance = precipitationProbList.getOrNull(index) ?: 0,
            precipitationUnit = hourlyUnits?.precipitation_probability ?: "?",
            humidity = humidityList.getOrNull(index) ?: 0,
            pressure = pressureList.getOrNull(index) ?: 0.0,
            uvIndex = uvIndexList.getOrNull(index) ?: 0.0,
            windSpeed10m = windSpeedList.getOrNull(index) ?: 0.0,
            windSpeed10mUnit = hourlyUnits?.wind_speed_10m ?: "?",
            gustSpeed = gustSpeedList.getOrNull(index) ?: 0.0,
        )
    }
}

fun DailyDto.toDomainList(dailyUnits: DailyUnitDto?): List<DailyDomain> {
    val timeList = time ?: emptyList()
    val weatherCodeList = weather_code ?: emptyList()
    val maxTempList = temperature_2m_max ?: emptyList()
    val minTempList = temperature_2m_min ?: emptyList()
    val uvIndexList = uv_index_max ?: emptyList()
    val sunriseList = sunrise ?: emptyList()
    val sunsetList = sunset ?: emptyList()
    val precipitationProbList = precipitation_probability_max ?: emptyList()

    return timeList.mapIndexed { index, dateValue ->
        val code = weatherCodeList.getOrNull(index) ?: 0
        // we can get precipitation sign as string from API but you know .. :)
        val precipitationChance = precipitationProbList.getOrNull(index) ?: 0

        DailyDomain(
            date = dateValue,
            temperatureUnit = dailyUnits?.temperature_2m_max ?: "?",
            condition = code.weatherCodeToCondition(null),
            icon = code.weatherCodeToWeatherIcon(),
            maxTemp = maxTempList.getOrNull(index) ?: 0.0,
            minTemp = minTempList.getOrNull(index) ?: 0.0,
            uvIndex = uvIndexList.getOrNull(index) ?: 0.0,
            sunrise = sunriseList.getOrNull(index) ?: "",
            sunset = sunsetList.getOrNull(index) ?: "",
            precipitationChance = "$precipitationChance",
            precipitationUnit = dailyUnits?.precipitation_probability_max ?: "?",
        )
    }
}
