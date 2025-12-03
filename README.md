# Aether – Weather Application

Aether is an Android showcase app demonstrating real-time weather effects rendered with Android
Shading Language (AGSL). Weather conditions are driven by the Open-Meteo
API (https://open-meteo.com/), and visualized with customizable shader scripts.

Status: This project is in active early development. Many features and effects are work-in-progress,
and the application icon is still a placeholder.

## Demo (screen recordings)

Check out the weather shader effects in action:
- **Rainy effect:**
  <img src="https://drive.google.com/uc?export=view&id=1yeLDQcBnGyrGnjnQsujhqi8PR-qotKEq" width="300"/>

- **Snowy effect:**
  <img src="https://drive.google.com/uc?export=view&id=1zDMtJNC2P8AmvAgb7BNSfDhSSXlvmeNh" width="300"/>

- **Starry effect:**
  <img src="https://drive.google.com/uc?export=view&id=1imRir1T8rQUOE9TX8Gd3mIoea9xJ4poV" width="300"/>

*(More recordings will be added as new effects are completed.)*

## Shader customization

Shaders are designed to be dynamic and configurable at runtime:

- Rainy: droplet count, size, speed
- Snowy: flake count, drift amount, falling speed
- Starry: twinkling speed, density, time progression

## Weather effects (current and planned)

Implemented:

- Rainy
- Snowy
- Starry

Planned / WIP:

- Thunder (WIP)
- Cloudy (WIP)
- Sunny (WIP)

## Architecture

Aether follows a **modular, feature-based Clean Architecture** approach with a clear separation of concerns across multiple independent modules.

### Module Structure

```
app/                 # Application wiring, navigation, startup DI
├── home/           # Home weather feature (self-contained)
│   ├── data/       # (Empty for now as it doesn't have feature-specific business logic yet)
│   ├── domain/     # (Empty for now as it doesn't have feature-specific business logic yet)
│   ├── presentation/ # UI, ViewModels, states
│   └── di/         # Feature-specific DI
├── core-android/   # Shared Android DI modules (Hilt network, Retrofit, repository providers)
├── core-data/      # Shared data layer (repositories, API clients, DTO mappings, weather API)
├── core-domain/    # Shared domain layer (use cases, domain models)
├── ui/             # Shared UI components, widgets, themes
├── utility/        # Shared utilities, helpers, shader scripts, animations, extensions
└── weather-core/    # Core weather functionality, configs, shader effect factory
```

### Module Descriptions

**app**
- Minimal application module responsible for wiring everything together
- Handles navigation, app-level DI setup, and startup configuration
- Depends on feature modules and coordinates the overall app structure

**home**
- Self-contained feature module for the home weather screen
- Currently relies on shared `core_data` and `core_domain` for weather functionality
- Data and domain layers are empty for now as it doesn't have feature-specific business logic yet
- Contains presentation layer with UI and ViewModels
- Demonstrates the feature-based architecture pattern for future feature modules

**core-android**
- Shared Android-specific dependency injection modules
- Provides Hilt network module, Retrofit configuration, and repository providers
- Bridges core Kotlin modules with Android framework dependencies
- Depends on `core_data` to wire up repositories

**core-data** (Pure Kotlin)
- Shared data layer implementation
- Provides repositories, API clients, and DTO-to-domain mappings
- Handles weather API integration (current/daily/hourly forecasts)
- No Android dependencies for improved testability

**core-domain** (Pure Kotlin)
- Shared domain layer defining business logic contracts
- Contains shared use cases and domain models
- Platform-agnostic for maximum reusability and testability
- No Android dependencies

**ui**
- Shared UI components and widgets used across features
- Provides consistent theming and design system
- Reusable Compose components

**utility**
- Shared utility classes and helper functions
- Contains shader scripts for weather effects
- Provides animations and Kotlin extensions
- General-purpose utilities

**weatherCore**
- Core weather-related functionality and configuration
- Factory for shader effects based on weather conditions
- Weather condition mappings and icon providers

### Tech Stack
- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: Clean Architecture + MVVM
- **DI**: Hilt
- **Networking**: Retrofit + OkHttp
- **Async**: Coroutines + Flow
- **Location**: Google Play Services Location
- **Shaders**: Android Graphics Shading Language (AGSL)

## Feature roadmap / TODO

- Implement the remaining shader scripts: Thunder, Cloudy, Sunny
- City search by location
- Saved city list (favorites)
- Selected city detail screen with shader-driven weather
- Weather map integration
- Additional UI elements for weather data in the Home screen

## Build and run

- Requirements: Android Studio Giraffe or newer, JDK 17
- Android modules require minSdk 33 (Android 13) for AGSL
- Sync Gradle, then run the `app` module on a device or emulator

## Contributing

Contributions are welcome! Feel free to open issues with ideas, bugs, or feature requests, or submit
pull requests.

Before contributing, please ensure you follow the project architecture as described in the Architecture section above.

Before committing, please run the code style checks and formatter.

## Code style (ktlint)

This repo enforces Kotlin style via the Gradle ktlint plugin.

Common tasks:

- Format: `./gradlew ktlintFormat`
- Check: `./gradlew ktlintCheck`
- CI combined (format then check): `./gradlew ktLintCheckFormat`

Optional local hooks:

- Install pre-commit and pre-push hooks to run ktlint automatically:
  ```sh
  scripts/install-git-hooks.sh
  ```
- Pre-commit auto-formats staged Kotlin files and re-adds them; pre-push runs the CI combined task.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

