package com.kyrie.aether.common


sealed class WeatherUiState<out T> {
    object Loading : WeatherUiState<Nothing>()
    data class Success<out T>(val weather: T) : WeatherUiState<T>()
    data class Error(val message: String) : WeatherUiState<Nothing>()
}