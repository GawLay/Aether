package com.kyrie.aether.home.presentation.model;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001a\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\nH\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\rH\u00c6\u0003JO\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\rH\u00c6\u0001J\u0013\u0010#\u001a\u00020\n2\b\u0010$\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010%\u001a\u00020\rH\u00d6\u0001J\t\u0010&\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006\'"}, d2 = {"Lcom/kyrie/aether/home/presentation/model/CurrentWeatherUiModel;", "", "time", "", "temperature", "condition", "Lcom/kyrie/aether/weatherCore/WeatherCondition;", "icon", "Lcom/kyrie/aether/weatherCore/WeatherIcon;", "isDay", "", "windSpeed", "windDirection", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/kyrie/aether/weatherCore/WeatherCondition;Lcom/kyrie/aether/weatherCore/WeatherIcon;ZLjava/lang/String;I)V", "getTime", "()Ljava/lang/String;", "getTemperature", "getCondition", "()Lcom/kyrie/aether/weatherCore/WeatherCondition;", "getIcon", "()Lcom/kyrie/aether/weatherCore/WeatherIcon;", "()Z", "getWindSpeed", "getWindDirection", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "home_debug"})
public final class CurrentWeatherUiModel {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String time = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String temperature = null;
    @org.jetbrains.annotations.NotNull()
    private final com.kyrie.aether.weatherCore.WeatherCondition condition = null;
    @org.jetbrains.annotations.NotNull()
    private final com.kyrie.aether.weatherCore.WeatherIcon icon = null;
    private final boolean isDay = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String windSpeed = null;
    private final int windDirection = 0;
    
    public CurrentWeatherUiModel(@org.jetbrains.annotations.NotNull()
    java.lang.String time, @org.jetbrains.annotations.NotNull()
    java.lang.String temperature, @org.jetbrains.annotations.NotNull()
    com.kyrie.aether.weatherCore.WeatherCondition condition, @org.jetbrains.annotations.NotNull()
    com.kyrie.aether.weatherCore.WeatherIcon icon, boolean isDay, @org.jetbrains.annotations.NotNull()
    java.lang.String windSpeed, int windDirection) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTime() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTemperature() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.kyrie.aether.weatherCore.WeatherCondition getCondition() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.kyrie.aether.weatherCore.WeatherIcon getIcon() {
        return null;
    }
    
    public final boolean isDay() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getWindSpeed() {
        return null;
    }
    
    public final int getWindDirection() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.kyrie.aether.weatherCore.WeatherCondition component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.kyrie.aether.weatherCore.WeatherIcon component4() {
        return null;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    public final int component7() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.kyrie.aether.home.presentation.model.CurrentWeatherUiModel copy(@org.jetbrains.annotations.NotNull()
    java.lang.String time, @org.jetbrains.annotations.NotNull()
    java.lang.String temperature, @org.jetbrains.annotations.NotNull()
    com.kyrie.aether.weatherCore.WeatherCondition condition, @org.jetbrains.annotations.NotNull()
    com.kyrie.aether.weatherCore.WeatherIcon icon, boolean isDay, @org.jetbrains.annotations.NotNull()
    java.lang.String windSpeed, int windDirection) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}