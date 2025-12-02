package com.kyrie.aether.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Responsive dimension system for Aether.
 * Usage: `LocalDimensions.current.spaceMedium`.
 */
@Immutable
sealed class Dimensions(
    // Spacing
    val spaceExtraSmall: Dp,
    val spaceSmall: Dp,
    val spaceMedium: Dp,
    val spaceLarge: Dp,
    val spaceExtraLarge: Dp,
    // Sizes
    val iconSmall: Dp,
    val iconMedium: Dp,
    val iconLarge: Dp,
    val buttonHeight: Dp,
    val cardWidth: Dp,
    // Generic thickness / strokes (e.g. temperature range bar)
    val strokeThin: Dp,
    // Generic item widths
    val minItemWidth: Dp,
    // Radii
    val radiusSmall: Dp,
    val radiusMedium: Dp,
    val radiusLarge: Dp,
    // Elevation
    val elevationLow: Dp,
    val elevationMedium: Dp,
    val elevationHigh: Dp,
) {
    /** Compact screens (phones in portrait, small screens). */
    data object Compact : Dimensions(
        spaceExtraSmall = 2.dp,
        spaceSmall = 4.dp,
        spaceMedium = 8.dp,
        spaceLarge = 16.dp,
        spaceExtraLarge = 24.dp,
        iconSmall = 16.dp,
        iconMedium = 20.dp,
        iconLarge = 24.dp,
        buttonHeight = 40.dp,
        cardWidth = 0.dp, // use Modifier.fillMaxWidth() typically
        strokeThin = 4.dp,
        minItemWidth = 60.dp,
        radiusSmall = 6.dp,
        radiusMedium = 12.dp,
        radiusLarge = 18.dp,
        elevationLow = 1.dp,
        elevationMedium = 3.dp,
        elevationHigh = 6.dp,
    )

    /** Medium screens (large phones / small tablets). */
    data object Medium : Dimensions(
        spaceExtraSmall = 3.dp,
        spaceSmall = 6.dp,
        spaceMedium = 12.dp,
        spaceLarge = 20.dp,
        spaceExtraLarge = 28.dp,
        iconSmall = 18.dp,
        iconMedium = 24.dp,
        iconLarge = 32.dp,
        buttonHeight = 44.dp,
        cardWidth = 0.dp,
        strokeThin = 5.dp,
        minItemWidth = 72.dp,
        radiusSmall = 8.dp,
        radiusMedium = 16.dp,
        radiusLarge = 24.dp,
        elevationLow = 2.dp,
        elevationMedium = 4.dp,
        elevationHigh = 8.dp,
    )

    /** Expanded screens (tablets, foldables, desktop). */
    data object Expanded : Dimensions(
        spaceExtraSmall = 4.dp,
        spaceSmall = 8.dp,
        spaceMedium = 16.dp,
        spaceLarge = 24.dp,
        spaceExtraLarge = 32.dp,
        iconSmall = 20.dp,
        iconMedium = 28.dp,
        iconLarge = 36.dp,
        buttonHeight = 48.dp,
        cardWidth = 0.dp,
        strokeThin = 6.dp,
        minItemWidth = 80.dp,
        radiusSmall = 10.dp,
        radiusMedium = 20.dp,
        radiusLarge = 28.dp,
        elevationLow = 2.dp,
        elevationMedium = 6.dp,
        elevationHigh = 10.dp,
    )
}

/** CompositionLocal to access current dimensions. */
val LocalDimensions = staticCompositionLocalOf<Dimensions> { Dimensions.Compact }

/**
 * Pick a [Dimensions] instance based on the current screen width.
 */
@Composable
fun responsiveDimensions(): Dimensions {
    val configuration = LocalConfiguration.current
    val widthDp = configuration.screenWidthDp

    return when {
        widthDp < 600 -> Dimensions.Compact
        widthDp < 840 -> Dimensions.Medium
        else -> Dimensions.Expanded
    }
}
