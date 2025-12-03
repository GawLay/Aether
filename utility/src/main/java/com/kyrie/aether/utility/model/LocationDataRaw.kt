package com.kyrie.aether.utility.model

data class LocationDataRaw(
    val latitude: Double,
    val longitude: Double,
    val cityName: String? = null,
    val countryName: String? = null,
    val fullAddress: String? = null,
)
