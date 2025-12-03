package com.kyrie.aether.home.presentation.ui;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u001ar\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0012\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u000f2\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00120\u000fH\u0007\u001a\b\u0010\u0016\u001a\u00020\u0001H\u0003\u001a\u0018\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\rH\u0003\u001a(\u0010\u0019\u001a\u00020\u00012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u001b\u001a\u00020\rH\u0003\u001a\u0010\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0013H\u0003\u001a\u001e\u0010\u001e\u001a\u00020\u00012\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00150\u00122\u0006\u0010\b\u001a\u00020\tH\u0003\u001a\u0010\u0010 \u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0015H\u0003\u00a8\u0006!"}, d2 = {"HomeScreen", "", "modifier", "Landroidx/compose/ui/Modifier;", "shaders", "Lcom/kyrie/aether/utility/shaders/model/ParticleShaders;", "sceneShaders", "Lcom/kyrie/aether/utility/shaders/model/WeatherSceneShaders;", "weatherCondition", "Lcom/kyrie/aether/weatherCore/WeatherCondition;", "iTime", "Landroidx/compose/runtime/MutableFloatState;", "locationName", "", "currentWeatherState", "Lcom/kyrie/aether/home/presentation/state/WeatherUiState;", "Lcom/kyrie/aether/home/presentation/model/CurrentWeatherUiModel;", "hourlyWeatherState", "", "Lcom/kyrie/aether/home/presentation/model/HourlyUiModel;", "dailyWeatherState", "Lcom/kyrie/aether/home/presentation/model/DailyUiModel;", "TopBar", "MainWeatherInfo", "currentWeatherData", "HourlyForecast", "hourlyWeather", "description", "HourlyWeatherItem", "weather", "DailyForecast", "dailyWeather", "DailyWeatherItem", "home_debug"})
public final class HomeScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void HomeScreen(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    com.kyrie.aether.utility.shaders.model.ParticleShaders shaders, @org.jetbrains.annotations.NotNull()
    com.kyrie.aether.utility.shaders.model.WeatherSceneShaders sceneShaders, @org.jetbrains.annotations.NotNull()
    com.kyrie.aether.weatherCore.WeatherCondition weatherCondition, @org.jetbrains.annotations.NotNull()
    androidx.compose.runtime.MutableFloatState iTime, @org.jetbrains.annotations.NotNull()
    java.lang.String locationName, @org.jetbrains.annotations.NotNull()
    com.kyrie.aether.home.presentation.state.WeatherUiState<com.kyrie.aether.home.presentation.model.CurrentWeatherUiModel> currentWeatherState, @org.jetbrains.annotations.NotNull()
    com.kyrie.aether.home.presentation.state.WeatherUiState<? extends java.util.List<com.kyrie.aether.home.presentation.model.HourlyUiModel>> hourlyWeatherState, @org.jetbrains.annotations.NotNull()
    com.kyrie.aether.home.presentation.state.WeatherUiState<? extends java.util.List<com.kyrie.aether.home.presentation.model.DailyUiModel>> dailyWeatherState) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void TopBar() {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void MainWeatherInfo(com.kyrie.aether.home.presentation.model.CurrentWeatherUiModel currentWeatherData, java.lang.String locationName) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void HourlyForecast(java.util.List<com.kyrie.aether.home.presentation.model.HourlyUiModel> hourlyWeather, com.kyrie.aether.weatherCore.WeatherCondition weatherCondition, java.lang.String description) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void HourlyWeatherItem(com.kyrie.aether.home.presentation.model.HourlyUiModel weather) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DailyForecast(java.util.List<com.kyrie.aether.home.presentation.model.DailyUiModel> dailyWeather, com.kyrie.aether.weatherCore.WeatherCondition weatherCondition) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DailyWeatherItem(com.kyrie.aether.home.presentation.model.DailyUiModel weather) {
    }
}