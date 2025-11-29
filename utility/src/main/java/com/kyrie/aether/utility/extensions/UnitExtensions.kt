package com.kyrie.aether.utility.extensions

import kotlin.math.roundToInt

fun Double.formatUvIndex(): String {
    val level = when {
        this < 3 -> "Low"
        this < 6 -> "Moderate"
        this < 8 -> "High"
        this < 11 -> "Very High"
        else -> "Extreme"
    }
    return "$level (${this.roundToInt()})"
}
