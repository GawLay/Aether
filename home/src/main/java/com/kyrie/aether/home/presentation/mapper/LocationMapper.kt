package com.kyrie.aether.home.presentation.mapper

import com.kyrie.aether.home.presentation.model.LocationDataUi
import com.kyrie.aether.utility.model.LocationDataRaw

fun LocationDataRaw.toLocationUi() = LocationDataUi(
    latitude = latitude,
    longitude = longitude,
    cityName = cityName,
    countryName = countryName,
    fullAddress = fullAddress,
)
