package com.kyrie.aether.home.presentation.ui;

import com.kyrie.aether.domain.usecases.GetCurrentWeatherUseCase;
import com.kyrie.aether.utility.location.LocationHelper;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<GetCurrentWeatherUseCase> _weatherUseCaseProvider;

  private final Provider<LocationHelper> _locationHelperProvider;

  private HomeViewModel_Factory(Provider<GetCurrentWeatherUseCase> _weatherUseCaseProvider,
      Provider<LocationHelper> _locationHelperProvider) {
    this._weatherUseCaseProvider = _weatherUseCaseProvider;
    this._locationHelperProvider = _locationHelperProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(_weatherUseCaseProvider.get(), _locationHelperProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<GetCurrentWeatherUseCase> _weatherUseCaseProvider,
      Provider<LocationHelper> _locationHelperProvider) {
    return new HomeViewModel_Factory(_weatherUseCaseProvider, _locationHelperProvider);
  }

  public static HomeViewModel newInstance(GetCurrentWeatherUseCase _weatherUseCase,
      LocationHelper _locationHelper) {
    return new HomeViewModel(_weatherUseCase, _locationHelper);
  }
}
