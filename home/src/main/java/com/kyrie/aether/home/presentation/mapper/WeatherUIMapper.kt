package com.kyrie.aether.home.presentation.mapper

import com.kyrie.aether.domain.model.CurrentWeatherDomain
import com.kyrie.aether.domain.model.DailyDomain
import com.kyrie.aether.domain.model.HourlyDomain
import com.kyrie.aether.home.presentation.model.CurrentWeatherUiModel
import com.kyrie.aether.home.presentation.model.DailyUiModel
import com.kyrie.aether.home.presentation.model.HourlyUiModel
import com.kyrie.aether.utility.extensions.formatToDayName
import com.kyrie.aether.utility.extensions.formatToFullDate
import com.kyrie.aether.utility.extensions.formatToFullDateAndTime
import com.kyrie.aether.utility.extensions.formatToHourly
import com.kyrie.aether.utility.extensions.formatToTime
import com.kyrie.aether.utility.extensions.formatUvIndex
import com.kyrie.aether.utility.weather.toStringIcon
import kotlin.math.roundToInt

fun List<HourlyDomain>.toUiHourlyDomain(): List<HourlyUiModel> = this.map { it.toUiModel() }

fun List<DailyDomain>.toUiDailyDomain(): List<DailyUiModel> = this.map { it.toUiModel() }

fun CurrentWeatherDomain.toUiModel(): CurrentWeatherUiModel =
    CurrentWeatherUiModel(
        time = time.formatToFullDateAndTime(),
        temperature = "${temperature.roundToInt()} $temperatureUnit",
        condition = condition,
        icon = icon,
        isDay = isDay,
        windSpeed = "${windSpeed.roundToInt()} $windSpeedUnit",
        windDirection = windDirection,
    )

fun HourlyDomain.toUiModel(): HourlyUiModel =
    HourlyUiModel(
        time = time.formatToHourly(),
        temperature = "${temperature.roundToInt()}$temperatureUnit",
        feelsLike = "${feelsLike.roundToInt()}$temperatureUnit",
        condition = condition,
        iconString = icon.toStringIcon(),
        precipitationChance = "$precipitationChance$precipitationUnit",
        pressure = "${pressure.roundToInt()} hPa",
        uvIndex = uvIndex.formatUvIndex(),
        windSpeed10m = "${windSpeed10m.roundToInt()} $windSpeed10mUnit",
    )

fun DailyDomain.toUiModel(): DailyUiModel =
    DailyUiModel(
        date = date.formatToDayName(),
        fullDate = date.formatToFullDate(),
        condition = condition,
        iconString = icon.toStringIcon(),
        maxTemp = "${maxTemp.roundToInt()}$temperatureUnit",
        minTemp = "${minTemp.roundToInt()}$temperatureUnit",
        tempRange = "${minTemp.roundToInt()}° - ${maxTemp.roundToInt()}°",
        uvIndex = uvIndex.formatUvIndex(),
        sunrise = sunrise.formatToTime(),
        sunset = sunset.formatToTime(),
        precipitationChance = "$precipitationChance$precipitationUnit",
    )
