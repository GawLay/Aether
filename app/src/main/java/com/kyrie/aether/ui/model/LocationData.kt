package com.kyrie.aether.ui.model

data class LocationData(
    val latitude: Double,
    val longitude: Double,
    val cityName: String? = null,
    val countryName: String? = null,
    val fullAddress: String? = null
)