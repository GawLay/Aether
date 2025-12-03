package com.kyrie.aether.utility.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.kyrie.aether.utility.AetherLog
import com.kyrie.aether.utility.model.LocationDataRaw
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await

sealed class LocationResult {
    data class Success(
        val location: LocationDataRaw,
    ) : LocationResult()

    data class Error(
        val message: String,
    ) : LocationResult()

    object PermissionDenied : LocationResult()
}

@Singleton
class LocationHelper
    @Inject
    constructor(
        @param:ApplicationContext private val context: Context,
    ) {
        private val fusedLocationClient: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)

        fun hasLocationPermission(): Boolean =
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION,
            ) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                ) == PackageManager.PERMISSION_GRANTED

        suspend fun getCurrentLocation(): LocationResult {
            if (!hasLocationPermission()) {
                return LocationResult.PermissionDenied
            }

            return try {
                val location = getLastKnownLocation() ?: getCurrentLocationFromProvider()
                AetherLog.e("LocationHelper", "Location fetched: $location")
                if (location != null) {
                    val cityName = getCityNameFromCoordinates(location.latitude, location.longitude)
                    val locationData =
                        LocationDataRaw(
                            latitude = location.latitude,
                            longitude = location.longitude,
                            cityName = cityName,
                            countryName = null,
                            fullAddress = null,
                        )
                    LocationResult.Success(locationData)
                } else {
                    LocationResult.Error("Unable to get location")
                }
            } catch (e: Exception) {
                LocationResult.Error(e.message ?: "Unknown error occurred")
            }
        }

        @SuppressLint("MissingPermission")
        private suspend fun getLastKnownLocation(): Location? {
            if (!hasLocationPermission()) return null

            return try {
                fusedLocationClient.lastLocation.await()
            } catch (e: Exception) {
                null
            }
        }

        @SuppressLint("MissingPermission")
        private suspend fun getCurrentLocationFromProvider(): Location? {
            if (!hasLocationPermission()) return null

            return suspendCancellableCoroutine { continuation ->
                val cancellationTokenSource = CancellationTokenSource()

                fusedLocationClient
                    .getCurrentLocation(
                        Priority.PRIORITY_HIGH_ACCURACY,
                        cancellationTokenSource.token,
                    ).addOnSuccessListener { location ->
                        if (!continuation.isCompleted) continuation.resume(location)
                    }.addOnFailureListener { exception ->
                        AetherLog.e("LocationHelper", "Failed to get location: $exception")
                        if (!continuation.isCompleted) continuation.resume(null)
                    }

                continuation.invokeOnCancellation {
                    cancellationTokenSource.cancel()
                }
            }
        }

        suspend fun getCityNameFromCoordinates(
            latitude: Double,
            longitude: Double,
        ): String? =
            try {
                getCityNameNew(latitude, longitude)
            } catch (e: Exception) {
                null
            }

        private suspend fun getCityNameNew(
            latitude: Double,
            longitude: Double,
        ): String? =
            suspendCancellableCoroutine { continuation ->
                val geocoder = Geocoder(context, Locale.getDefault())
                geocoder.getFromLocation(latitude, longitude, 1) { addresses ->
                    AetherLog.e("LocationHelper", "CityName  $addresses")
                    val cityName =
                        addresses.firstOrNull()?.locality
                            ?: addresses.firstOrNull()?.subAdminArea
                            ?: addresses.firstOrNull()?.adminArea
                    continuation.resume(cityName)
                }
            }

        suspend fun getFullLocationDetails(
            latitude: Double,
            longitude: Double,
        ): LocationDataRaw? =
            try {
                getFullLocationDetailsNew(latitude, longitude)
            } catch (e: Exception) {
                null
            }

        private suspend fun getFullLocationDetailsNew(
            latitude: Double,
            longitude: Double,
        ): LocationDataRaw? =
            suspendCancellableCoroutine { continuation ->
                val geocoder = Geocoder(context, Locale.getDefault())
                geocoder.getFromLocation(latitude, longitude, 1) { addresses ->
                    val address = addresses.firstOrNull()
                    if (address != null) {
                        val locationData =
                            LocationDataRaw(
                                latitude = latitude,
                                longitude = longitude,
                                cityName = address.locality ?: address.subAdminArea,
                                countryName = address.countryName,
                                fullAddress = address.getAddressLine(0),
                            )
                        continuation.resume(locationData)
                    } else {
                        continuation.resume(null)
                    }
                }
            }

        suspend fun getLocationOrFallback(locationData: LocationDataRaw?): LocationDataRaw {
            if (locationData != null) {
                return locationData
            }
            // todo Fallback location,make sure to change it later at least no hardcoding,
            // for now it is Ho Chi Minh City,
            val cityName =
                getCityNameFromCoordinates(10.762622, 106.660172)

            return LocationDataRaw(
                latitude = 10.762622,
                longitude = 106.660172,
                cityName = cityName,
                countryName = null,
                fullAddress = null,
            )
        }
    }
