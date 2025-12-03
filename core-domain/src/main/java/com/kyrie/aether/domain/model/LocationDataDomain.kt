package com.kyrie.aether.domain.model

data class LocationDataDomain(
    val latitude: Double,
    val longitude: Double,
    val cityName: String? = null,
    val countryName: String? = null,
    val fullAddress: String? = null,
)
