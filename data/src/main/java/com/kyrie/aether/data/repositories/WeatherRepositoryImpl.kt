package com.kyrie.aether.data.repositories

import com.kyrie.aether.data.mapper.toCurrentDomain
import com.kyrie.aether.data.mapper.toDailyDomain
import com.kyrie.aether.data.mapper.toHourlyDomain
import com.kyrie.aether.data.remote.api.WeatherApi
import com.kyrie.aether.domain.model.CurrentWeatherDomain
import com.kyrie.aether.domain.model.DailyDomain
import com.kyrie.aether.domain.model.HourlyDomain
import com.kyrie.aether.domain.repositories.WeatherRepository

class WeatherRepositoryImpl(
    private val api: WeatherApi,
) : WeatherRepository {
    override suspend fun getCurrentWeather(
        lat: Double,
        lon: Double,
    ): CurrentWeatherDomain {
        val response = api.getCurrentWeather(lat, lon)
        return response.toCurrentDomain()
    }

    override suspend fun getHourlyWeather(
        lat: Double,
        lon: Double,
    ): List<HourlyDomain>? {
        val response = api.getHourlyWeather(lat, lon)
        return response.toHourlyDomain()
    }

    override suspend fun getDailyWeather(
        lat: Double,
        lon: Double,
    ): List<DailyDomain>? {
        val response = api.getDailyWeather(lat, lon)
        return response.toDailyDomain()
    }
}
