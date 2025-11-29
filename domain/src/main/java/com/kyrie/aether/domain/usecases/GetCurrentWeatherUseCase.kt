package com.kyrie.aether.domain.usecases

import com.kyrie.aether.domain.model.CurrentWeatherDomain
import com.kyrie.aether.domain.model.DailyDomain
import com.kyrie.aether.domain.model.HourlyDomain
import com.kyrie.aether.domain.repositories.WeatherRepository

class GetCurrentWeatherUseCase(
    private val repository: WeatherRepository
) {
    suspend fun getCurrentWeather(lat: Double, lon: Double): CurrentWeatherDomain {
        return repository.getCurrentWeather(lat, lon)
    }

    suspend fun getHourlyWeather(lat: Double, lon: Double): List<HourlyDomain> {
        return repository.getHourlyWeather(lat, lon) ?: emptyList()
    }

    suspend fun getDailyWeather(lat: Double, lon: Double): List<DailyDomain> {
        return repository.getDailyWeather(lat, lon) ?: emptyList()
    }

}