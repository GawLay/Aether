package com.kyrie.aether.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyrie.aether.common.WeatherUiState
import com.kyrie.aether.domain.usecases.GetCurrentWeatherUseCase
import com.kyrie.aether.mapper.toUiDailyDomain
import com.kyrie.aether.mapper.toUiHourlyDomain
import com.kyrie.aether.mapper.toUiModel
import com.kyrie.aether.ui.model.CurrentWeatherUiModel
import com.kyrie.aether.ui.model.DailyUiModel
import com.kyrie.aether.ui.model.HourlyUiModel
import com.kyrie.aether.ui.model.LocationData
import com.kyrie.aether.utility.AetherLog
import com.kyrie.aether.utils.LocationHelper
import com.kyrie.aether.utils.LocationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val _weatherUseCase: GetCurrentWeatherUseCase,
        private val _locationHelper: LocationHelper,
    ) : ViewModel() {
        fun hasLocationPermission(): Boolean = _locationHelper.hasLocationPermission()

        private val _currentWeatherState =
            MutableStateFlow<WeatherUiState<CurrentWeatherUiModel>>(WeatherUiState.Loading)
        val currentWeatherState: StateFlow<WeatherUiState<CurrentWeatherUiModel>> =
            _currentWeatherState.asStateFlow()
        private val _hourlyWeatherState =
            MutableStateFlow<WeatherUiState<List<HourlyUiModel>>>(WeatherUiState.Loading)
        val hourlyWeatherState: StateFlow<WeatherUiState<List<HourlyUiModel>>> =
            _hourlyWeatherState.asStateFlow()
        private val _dailyWeatherState =
            MutableStateFlow<WeatherUiState<List<DailyUiModel>>>(WeatherUiState.Loading)
        val dailyWeatherState: StateFlow<WeatherUiState<List<DailyUiModel>>> =
            _dailyWeatherState.asStateFlow()

        private val _currentLocation = MutableStateFlow<LocationData?>(null)
        val currentLocation: StateFlow<LocationData?> = _currentLocation.asStateFlow()

        fun loadWeatherForCurrentLocation() {
            viewModelScope.launch {
                val locationResult = _locationHelper.getCurrentLocation()
                when (locationResult) {
                    is LocationResult.Success -> {
                        val locationData = locationResult.location
                        _currentLocation.value = locationData
                        loadCurrentWeather(locationData.latitude, locationData.longitude)
                        loadHourlyWeather(locationData.latitude, locationData.longitude)
                        loadDailyWeather(locationData.latitude, locationData.longitude)
                    }

                    is LocationResult.Error -> {
                        AetherLog.e("HomeViewModel", "Loading weather for current location Error")
                    }

                    is LocationResult.PermissionDenied -> {
                        AetherLog.e(
                            "HomeViewModel",
                            "Loading weather for current location PermissionDenied",
                        )
                    }
                }
            }
        }

        fun loadCurrentWeather(
            latitude: Double,
            longitude: Double,
        ) {
            viewModelScope.launch {
                _currentWeatherState.value = WeatherUiState.Loading
                try {
                    val currentWeatherDomain =
                        _weatherUseCase.getCurrentWeather(
                            latitude,
                            longitude,
                        )
                    val weatherData = currentWeatherDomain.toUiModel()

                    _currentWeatherState.value = WeatherUiState.Success(weatherData)
                } catch (e: Exception) {
                    _currentWeatherState.value =
                        WeatherUiState.Error(
                            e.message ?: "Unknown error occurred",
                        )
                }
            }
        }

        fun loadHourlyWeather(
            latitude: Double,
            longitude: Double,
        ) {
            viewModelScope.launch {
                _hourlyWeatherState.value = WeatherUiState.Loading
                try {
                    val hourlyWeatherList = _weatherUseCase.getHourlyWeather(latitude, longitude)
                    val weatherData = hourlyWeatherList.toUiHourlyDomain()

                    _hourlyWeatherState.value = WeatherUiState.Success(weatherData)
                } catch (e: Exception) {
                    _hourlyWeatherState.value =
                        WeatherUiState.Error(
                            e.message ?: "Unknown error occurred",
                        )
                }
            }
        }

        fun loadDailyWeather(
            latitude: Double,
            longitude: Double,
        ) {
            viewModelScope.launch {
                _dailyWeatherState.value = WeatherUiState.Loading
                try {
                    val dailyWeatherList = _weatherUseCase.getDailyWeather(latitude, longitude)
                    val weatherData = dailyWeatherList.toUiDailyDomain()

                    _dailyWeatherState.value = WeatherUiState.Success(weatherData)
                } catch (e: Exception) {
                    _dailyWeatherState.value =
                        WeatherUiState.Error(
                            e.message ?: "Unknown error occurred",
                        )
                }
            }
        }

        fun retryCurrent(
            latitude: Double,
            longitude: Double,
        ) {
            loadCurrentWeather(latitude, longitude)
        }

        fun retryDaily(
            latitude: Double,
            longitude: Double,
        ) {
            loadDailyWeather(latitude, longitude)
        }

        fun retryHourly(
            latitude: Double,
            longitude: Double,
        ) {
            loadHourlyWeather(latitude, longitude)
        }
    }
