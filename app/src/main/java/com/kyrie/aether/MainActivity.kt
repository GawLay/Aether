package com.kyrie.aether

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.kyrie.aether.common.WeatherUiState
import com.kyrie.aether.ui.home.HomeScreen
import com.kyrie.aether.ui.home.HomeViewModel
import com.kyrie.aether.ui.model.SampleWeatherData
import com.kyrie.aether.ui.theme.AetherTheme
import com.kyrie.aether.utility.AetherLog
import com.kyrie.aether.utility.shaders.ShaderUtil
import com.kyrie.aether.utility.shaders.model.ParticleShaders
import com.kyrie.aether.utility.shaders.model.WeatherSceneShaders
import com.kyrie.aether.utils.permissions.RequestLocationPermission
import com.kyrie.aether.weatherCore.WeatherCondition
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val iTime = rememberAnimationTime()
            val currentLocation by viewModel.currentLocation.collectAsState()

            val hasPermission = remember { mutableStateOf(viewModel.hasLocationPermission()) }

            RequestLocationPermissionIfNeeded(
                hasPermission = hasPermission.value,
                onPermissionGranted = {
                    hasPermission.value = true
                    viewModel.loadWeatherForCurrentLocation()
                },
                onPermissionDenied = {
                    hasPermission.value = false
                },
            )
            val currentWeatherState by viewModel.currentWeatherState.collectAsState()
            val hourlyWeatherState by viewModel.hourlyWeatherState.collectAsState()
            val dailyWeatherState by viewModel.dailyWeatherState.collectAsState()

            val weatherCondition =
                when (currentWeatherState) {
                    is WeatherUiState.Success ->
                        (currentWeatherState as WeatherUiState.Success)
                            .weather.condition
                    is WeatherUiState.Loading -> WeatherCondition.CLEAR
                    is WeatherUiState.Error -> WeatherCondition.CLEAR
                }
            val particleShaders: ParticleShaders = ShaderUtil.createParticleShaders()
            val sceneShaders: WeatherSceneShaders =
                ShaderUtil.createSceneShaders(weatherCondition)
            AetherLog.d("WeatherCondition", "MainActivity value: $weatherCondition")
            AetherTheme(weatherCondition = WeatherCondition.RAINY) {
                HomeScreen(
                    shaders = particleShaders,
                    sceneShaders = sceneShaders,
                    weatherCondition = weatherCondition,
                    iTime = iTime,
                    currentWeatherState = currentWeatherState,
                    hourlyWeatherState = hourlyWeatherState,
                    dailyWeatherState = dailyWeatherState,
                    locationName = currentLocation?.cityName ?: "Unknown Location",
                )
            }
        }
    }
}

@Composable
private fun RequestLocationPermissionIfNeeded(
    hasPermission: Boolean,
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit,
) {
    if (!hasPermission) {
        RequestLocationPermission(
            onPermissionGranted = onPermissionGranted,
            onPermissionDenied = onPermissionDenied,
        )
    } else {
        LaunchedEffect(Unit) { onPermissionGranted() }
    }
}

@Composable
private fun rememberAnimationTime() =
    remember {
        mutableFloatStateOf(0f)
    }.also { state ->
        LaunchedEffect(Unit) {
            val start = System.nanoTime()
            while (true) {
                val now = System.nanoTime()
                state.floatValue = (now - start) / 1_000_000_000f
                delay(16)
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun WeatherHomePreview() {
    val iTime = remember { mutableFloatStateOf(0f) }
    AetherTheme(weatherCondition = WeatherCondition.RAINY) {
        HomeScreen(
            shaders = getDefaultShaderSet(),
            sceneShaders = getDefaultSceneShaderSet(),
            weatherCondition = WeatherCondition.RAINY,
            iTime = iTime,
            locationName = "City Preview Name",
            currentWeatherState = WeatherUiState.Success(SampleWeatherData.currentWeather),
            hourlyWeatherState = WeatherUiState.Success(SampleWeatherData.hourlyForecast),
            dailyWeatherState = WeatherUiState.Success(SampleWeatherData.dailyForecast),
        )
    }
}

@Preview(showBackground = true, name = "Sunny Theme")
@Composable
fun SunnyWeatherPreview() {
    val iTime = remember { mutableFloatStateOf(0f) }
    AetherTheme(weatherCondition = WeatherCondition.CLEAR) {
        HomeScreen(
            shaders = getDefaultShaderSet(),
            sceneShaders = getDefaultSceneShaderSet(),
            weatherCondition = WeatherCondition.CLEAR,
            iTime = iTime,
            locationName = "City Preview Name",
            currentWeatherState = WeatherUiState.Success(SampleWeatherData.currentWeather),
            hourlyWeatherState = WeatherUiState.Success(SampleWeatherData.hourlyForecast),
            dailyWeatherState = WeatherUiState.Success(SampleWeatherData.dailyForecast),
        )
    }
}

private fun getDefaultShaderSet(): ParticleShaders = ShaderUtil.createParticleShaders()

private fun getDefaultSceneShaderSet(): WeatherSceneShaders =
    ShaderUtil.createSceneShaders(
        WeatherCondition.RAINY,
    )

@Preview(showBackground = true, name = "Loading State")
@Composable
fun LoadingWeatherPreview() {
    val iTime = remember { mutableFloatStateOf(0f) }
    AetherTheme(weatherCondition = WeatherCondition.CLEAR) {
        HomeScreen(
            shaders = getDefaultShaderSet(),
            sceneShaders = getDefaultSceneShaderSet(),
            weatherCondition = WeatherCondition.CLEAR,
            iTime = iTime,
            locationName = "City Preview Name",
            currentWeatherState = WeatherUiState.Success(SampleWeatherData.currentWeather),
            hourlyWeatherState = WeatherUiState.Success(SampleWeatherData.hourlyForecast),
            dailyWeatherState = WeatherUiState.Success(SampleWeatherData.dailyForecast),
        )
    }
}

@Preview(showBackground = true, name = "Error State")
@Composable
fun ErrorWeatherPreview() {
    val iTime = remember { mutableFloatStateOf(0f) }
    AetherTheme(weatherCondition = WeatherCondition.CLEAR) {
        HomeScreen(
            shaders = getDefaultShaderSet(),
            sceneShaders = getDefaultSceneShaderSet(),
            weatherCondition = WeatherCondition.CLEAR,
            iTime = iTime,
            locationName = "City Preview Name",
            currentWeatherState = WeatherUiState.Error("Failed to load weather data"),
            hourlyWeatherState = WeatherUiState.Error("Failed to load hourly data"),
            dailyWeatherState = WeatherUiState.Error("Failed to load daily data"),
        )
    }
}

@Preview(showBackground = true, name = "Snowy Theme")
@Composable
fun SnowyWeatherPreview() {
    val iTime = remember { mutableFloatStateOf(0f) }
    AetherTheme(weatherCondition = WeatherCondition.SNOWY) {
        HomeScreen(
            shaders = getDefaultShaderSet(),
            sceneShaders = getDefaultSceneShaderSet(),
            weatherCondition = WeatherCondition.SNOWY,
            iTime = iTime,
            locationName = "City Preview Name",
            currentWeatherState = WeatherUiState.Success(SampleWeatherData.currentWeather),
            hourlyWeatherState = WeatherUiState.Success(SampleWeatherData.hourlyForecast),
            dailyWeatherState = WeatherUiState.Success(SampleWeatherData.dailyForecast),
        )
    }
}
