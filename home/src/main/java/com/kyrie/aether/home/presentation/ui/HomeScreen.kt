package com.kyrie.aether.home.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.kyrie.aether.home.presentation.model.CurrentWeatherUiModel
import com.kyrie.aether.home.presentation.model.DailyUiModel
import com.kyrie.aether.home.presentation.model.HourlyUiModel
import com.kyrie.aether.home.presentation.state.WeatherUiState
import com.kyrie.aether.ui.components.ErrorScreen
import com.kyrie.aether.ui.components.LoadingScreen
import com.kyrie.aether.ui.components.WeatherCard
import com.kyrie.aether.ui.components.WeatherParticlesLayer
import com.kyrie.aether.ui.components.WeatherScenesLayer
import com.kyrie.aether.ui.theme.LocalDimensions
import com.kyrie.aether.ui.theme.WeatherColors
import com.kyrie.aether.utility.AetherLog
import com.kyrie.aether.utility.shaders.model.ParticleShaders
import com.kyrie.aether.utility.shaders.model.WeatherSceneShaders
import com.kyrie.aether.utility.weather.weatherConditionToString
import com.kyrie.aether.weatherCore.WeatherCondition

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    shaders: ParticleShaders,
    sceneShaders: WeatherSceneShaders,
    weatherCondition: WeatherCondition = WeatherCondition.RAINY,
    iTime: MutableFloatState,
    locationName: String,
    currentWeatherState: WeatherUiState<CurrentWeatherUiModel>,
    hourlyWeatherState: WeatherUiState<List<HourlyUiModel>>,
    dailyWeatherState: WeatherUiState<List<DailyUiModel>>,
) {
    val dimensions = LocalDimensions.current

    AetherLog.d("WeatherCondition", "Current value: $weatherCondition")

    WeatherScenesLayer(
        modifier = modifier.fillMaxSize(),
        weatherCondition = weatherCondition,
        shaders = sceneShaders,
        iTime = iTime,
    )

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(color = WeatherColors.Transparent),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .verticalScroll(rememberScrollState()),
        ) {
            // Top bar
            TopBar()

            Spacer(modifier = Modifier.height(dimensions.spaceExtraLarge))

            // Main weather info
            when (currentWeatherState) {
                is WeatherUiState.Loading -> LoadingScreen()
                is WeatherUiState.Success -> {
                    MainWeatherInfo(
                        currentWeatherState.weather,
                        locationName,
                    )
                    Spacer(modifier = Modifier.height(dimensions.spaceExtraLarge))
                }

                is WeatherUiState.Error ->
                    ErrorScreen(
                        message = currentWeatherState.message,
                        onRetry = { /* TODO: Implement retry logic */ },
                    )
            }

            AnimatedVisibility(
                visible = hourlyWeatherState is WeatherUiState.Success,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(dimensions.spaceSmall),
                ) {
                    if (hourlyWeatherState is WeatherUiState.Success) {
                        HourlyForecast(
                            hourlyWeather = hourlyWeatherState.weather,
                            weatherCondition = weatherCondition,
                            description = "Weather forecast for the day will be implemented later ",
                        )
                    } else {
                        AetherLog.e("HOMESCREEN", "Hourly weather state is not Success")
                    }

                    WeatherParticlesLayer(
                        modifier =
                            Modifier
                                .matchParentSize()
                                .padding(horizontal = dimensions.spaceLarge),
                        weatherCondition = weatherCondition,
                        shaders = shaders,
                        iTime = iTime,
                    )
                }
            }

            Spacer(modifier = Modifier.height(dimensions.spaceLarge))

            // daily day - range forecast
            AnimatedVisibility(
                visible = dailyWeatherState is WeatherUiState.Success,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(dimensions.spaceSmall),
                ) {
                    if (dailyWeatherState is WeatherUiState.Success) {
                        DailyForecast(
                            dailyWeather = dailyWeatherState.weather,
                            weatherCondition = weatherCondition,
                        )
                    }

                    WeatherParticlesLayer(
                        modifier =
                            Modifier
                                .matchParentSize()
                                .padding(horizontal = dimensions.spaceLarge),
                        weatherCondition = weatherCondition,
                        shaders = shaders,
                        iTime = iTime,
                    )
                }
            }

            Spacer(modifier = Modifier.height(dimensions.spaceLarge))
        }
    }
}

@Composable
private fun TopBar() {
    val dimensions = LocalDimensions.current

    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensions.spaceLarge)
                .padding(top = dimensions.spaceSmall),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "Map",
            tint = WeatherColors.WeatherTextPrimary,
            modifier = Modifier.size(dimensions.iconMedium),
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensions.spaceExtraSmall),
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                tint = WeatherColors.WeatherTextPrimary,
                modifier = Modifier.size(dimensions.iconSmall),
            )
            Text(
                text = "HOME",
                style = MaterialTheme.typography.labelMedium,
                color = WeatherColors.WeatherTextPrimary,
                fontWeight = FontWeight.Medium,
            )
        }

        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Menu",
            tint = WeatherColors.WeatherTextPrimary,
            modifier = Modifier.size(dimensions.iconMedium),
        )
    }
}

@Composable
private fun MainWeatherInfo(
    currentWeatherData: CurrentWeatherUiModel,
    locationName: String,
) {
    val dimensions = LocalDimensions.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Location
        Text(
            text = locationName,
            style = MaterialTheme.typography.headlineMedium,
            color = WeatherColors.WeatherTextPrimary,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light,
        )

        Spacer(modifier = Modifier.height(dimensions.spaceExtraLarge))

        // Current temperature
        Text(
            text = currentWeatherData.temperature,
            style =
                MaterialTheme.typography.displayLarge.copy(
                    fontSize = 96.sp,
                    fontWeight = FontWeight.Thin,
                ),
            color = WeatherColors.WeatherTextPrimary,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(dimensions.spaceSmall))

        // Weather condition
        Text(
            text = currentWeatherData.condition.weatherConditionToString(),
            style = MaterialTheme.typography.headlineSmall,
            color = WeatherColors.WeatherTextPrimary,
            fontWeight = FontWeight.Light,
        )

        Spacer(modifier = Modifier.height(dimensions.spaceSmall))
        // todo adjust time live update, or show last updated time
        Text(
            text = currentWeatherData.time,
            style = MaterialTheme.typography.headlineSmall,
            color = WeatherColors.WeatherTextPrimary,
            fontWeight = FontWeight.Light,
        )

        Spacer(modifier = Modifier.height(dimensions.spaceSmall))
    }
}

@Composable
private fun HourlyForecast(
    hourlyWeather: List<HourlyUiModel>,
    weatherCondition: WeatherCondition,
    description: String = "Weather forecast for the day will be implemented later ",
) {
    val dimensions = LocalDimensions.current

    WeatherCard(weatherCondition) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = WeatherColors.WeatherTextPrimary,
                modifier = Modifier.padding(dimensions.spaceLarge),
                lineHeight = 20.sp,
            )

            Spacer(modifier = Modifier.height(dimensions.spaceExtraLarge))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(dimensions.spaceMedium),
            ) {
                items(hourlyWeather) { weather ->
                    HourlyWeatherItem(weather)
                }
            }

            Spacer(modifier = Modifier.height(dimensions.spaceExtraLarge))
        }
    }
}

@Composable
private fun HourlyWeatherItem(weather: HourlyUiModel) {
    val dimensions = LocalDimensions.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(dimensions.minItemWidth),
    ) {
        Text(
            text = weather.time,
            style = MaterialTheme.typography.bodySmall,
            color = WeatherColors.WeatherTextSecondary,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(dimensions.spaceSmall))

        Box(
            modifier =
                Modifier
                    .size(dimensions.iconLarge)
                    .background(
                        color = WeatherColors.WeatherCardBackground,
                        shape = RoundedCornerShape(dimensions.radiusSmall),
                    ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = weather.iconString,
                fontSize = 16.sp,
            )
        }

        Spacer(modifier = Modifier.height(dimensions.spaceExtraSmall))

        Text(
            text = weather.precipitationChance,
            style = MaterialTheme.typography.bodySmall,
            color = WeatherColors.WeatherTextSecondary,
            fontSize = 10.sp,
        )

        Spacer(modifier = Modifier.height(dimensions.spaceExtraSmall))

        Text(
            text = weather.temperature,
            style = MaterialTheme.typography.bodyMedium,
            color = WeatherColors.WeatherTextPrimary,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
        )
    }
}

@Composable
private fun DailyForecast(
    dailyWeather: List<DailyUiModel>,
    weatherCondition: WeatherCondition,
) {
    val dimensions = LocalDimensions.current

    WeatherCard(weatherCondition) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensions.spaceLarge),
        ) {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(dimensions.spaceMedium),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(dimensions.spaceSmall),
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Forecast",
                    tint = WeatherColors.WeatherTextSecondary,
                    modifier = Modifier.size(dimensions.iconSmall),
                )
                Text(
                    text = "7-DAY FORECAST",
                    style = MaterialTheme.typography.labelMedium,
                    color = WeatherColors.WeatherTextSecondary,
                    fontWeight = FontWeight.Medium,
                )
            }

            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensions.spaceSmall),
            ) {
                dailyWeather.forEachIndexed { index, weather ->
                    DailyWeatherItem(weather)
                    if (index < dailyWeather.size - 1) {
                        Spacer(modifier = Modifier.height(dimensions.spaceMedium))
                    }
                }
            }
        }
    }
}

@Composable
private fun DailyWeatherItem(weather: DailyUiModel) {
    val dimensions = LocalDimensions.current

    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = dimensions.spaceExtraSmall),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = weather.date,
            style = MaterialTheme.typography.bodyMedium,
            color = WeatherColors.WeatherTextPrimary,
            modifier = Modifier.weight(1f),
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensions.spaceExtraSmall),
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = weather.iconString,
                fontSize = 16.sp,
            )
            Text(
                text = weather.precipitationChance,
                style = MaterialTheme.typography.bodySmall,
                color = WeatherColors.WeatherTextSecondary,
                fontSize = 12.sp,
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensions.spaceExtraSmall),
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = weather.minTemp,
                style = MaterialTheme.typography.bodyMedium,
                color = WeatherColors.WeatherTextSecondary,
            )

            Box(
                modifier =
                    Modifier
                        .height(dimensions.strokeThin)
                        .weight(1f)
                        .background(
                            color = WeatherColors.WeatherAccent,
                            shape = RoundedCornerShape(dimensions.radiusSmall),
                        ),
            )

            Text(
                text = weather.maxTemp,
                style = MaterialTheme.typography.bodyMedium,
                color = WeatherColors.WeatherTextPrimary,
            )
        }
    }
}
