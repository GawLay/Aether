# Aether – Weather Application

Aether is an Android showcase app demonstrating real-time weather effects rendered with Android
Shading Language (AGSL). Weather conditions are driven by the Open-Meteo
API (https://open-meteo.com/), and visualized with customizable shader scripts.

Status: This project is in active early development. Many features and effects are work-in-progress,
and the application icon is still a placeholder.

## Demo (screen recordings)

Check out the weather shader effects in action:
- Rainy effect:
  ![Rainy effect](https://drive.google.com/file/d/1yeLDQcBnGyrGnjnQsujhqi8PR-qotKEq/view?usp=drive_link)
- Snowy effect:
- ![Snowy effect](https://drive.google.com/file/d/1zDMtJNC2P8AmvAgb7BNSfDhSSXlvmeNh/view?usp=drive_link)
- Starry effect:
- ![Starry effect](https://drive.google.com/file/d/1imRir1T8rQUOE9TX8Gd3mIoea9xJ4poV/view?usp=drive_link)

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

## Architecture

Modular Clean Architecture for scalability and maintainability.

Current (early stage):

- `:app` — Presentation (UI, ViewModels, screens)
- `:data` — Data (repositories, data sources, API)
- `:domain` — Business logic (use cases, domain models)
- `:utility` — Shared Android utilities
- `:weatherCore` — Core weather contracts

### Scalable Design

The architecture is intentionally modular to support future growth. As features expand, the project
will transition into feature-based modules, for example:

- `feature-weather`
- `feature-weather-domain`
- `feature-weather-data`

- `feature-cities`
- `feature-cities-domain`
- `feature-cities-data`
  Each feature module will have its own dedicated domain-* and data-* modules
