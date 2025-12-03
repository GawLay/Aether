package com.kyrie.aether.coreAndroid.di

import com.kyrie.aether.data.remote.api.WeatherApi
import com.kyrie.aether.data.repositories.WeatherRepositoryImpl
import com.kyrie.aether.domain.repositories.WeatherRepository
import com.kyrie.aether.domain.usecases.GetCurrentWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideWeatherRepository(api: WeatherApi): WeatherRepository =
        WeatherRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideGetCurrentWeatherUseCase(repo: WeatherRepository) =
        GetCurrentWeatherUseCase(repo)
}
