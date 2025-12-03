package com.kyrie.aether.home.presentation.model

data class LocationDataUi(
    val latitude: Double,
    val longitude: Double,
    val cityName: String? = null,
    val countryName: String? = null,
    val fullAddress: String? = null,
)
