package com.kyrie.aether.domain.repositories

import com.kyrie.aether.domain.model.CurrentWeatherDomain
import com.kyrie.aether.domain.model.DailyDomain
import com.kyrie.aether.domain.model.HourlyDomain

interface WeatherRepository {
    suspend fun getCurrentWeather(lat: Double, lon: Double): CurrentWeatherDomain
    suspend fun getHourlyWeather(lat: Double, lon: Double): List<HourlyDomain>?
    suspend fun getDailyWeather(lat: Double, lon: Double): List<DailyDomain>?
}