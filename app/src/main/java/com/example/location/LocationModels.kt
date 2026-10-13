package com.example.location

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

data class LocationPoint(
    val latitude: Double,
    val longitude: Double,
    val altitude: Double = 0.0,
    val speedKmh: Float = 0f,
    val bearing: Float = 0f,
    val accuracyMeters: Float = 5f,
    val timestamp: Long = System.currentTimeMillis()
)

data class GeofenceZone(
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val radiusMeters: Float,
    val description: String,
    val isInside: Boolean = false,
    val alertOnEntry: Boolean = true,
    val alertOnExit: Boolean = true
)

data class PakCity(
    val name: String,
    val province: String,
    val latitude: Double,
    val longitude: Double,
    val mandiHub: String
)

object LocationUtils {

    val PAK_CITIES = listOf(
        PakCity("Lahore", "Punjab", 31.5204, 74.3587, "Badami Bagh & Ravi Mandi"),
        PakCity("Karachi", "Sindh", 24.8607, 67.0011, "Super Highway Sabzi Mandi"),
        PakCity("Islamabad", "ICT", 33.6844, 73.0479, "I-11 Wholesale Fruit & Veg Mandi"),
        PakCity("Rawalpindi", "Punjab", 33.5651, 73.0169, "Pirwadhai Grain & Produce Market"),
        PakCity("Faisalabad", "Punjab", 31.4504, 73.1350, "Ghulam Muhammad Abad Mandi"),
        PakCity("Multan", "Punjab", 30.1575, 71.5249, "Vehari Road Grain Terminal"),
        PakCity("Peshawar", "KPK", 34.0151, 71.5249, "Inqilab Road Mandi Peshawar"),
        PakCity("Quetta", "Balochistan", 30.1798, 66.9750, "Hazar Ganji Central Mandi"),
        PakCity("Gujranwala", "Punjab", 32.1877, 74.1945, "Pasrur Road Wholesale Mandi"),
        PakCity("Hyderabad", "Sindh", 25.3960, 68.3578, "Hala Naka Commodity Exchange")
    )

    fun calculateDistanceMeters(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val r = 6371000.0 // Earth radius in meters
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return r * c
    }

    fun findNearestPakCity(lat: Double, lon: Double): Pair<PakCity, Double> {
        var closestCity = PAK_CITIES[0]
        var minDistanceMeters = calculateDistanceMeters(lat, lon, closestCity.latitude, closestCity.longitude)

        for (city in PAK_CITIES) {
            val dist = calculateDistanceMeters(lat, lon, city.latitude, city.longitude)
            if (dist < minDistanceMeters) {
                minDistanceMeters = dist
                closestCity = city
            }
        }
        return Pair(closestCity, minDistanceMeters / 1000.0) // returns city and km
    }
}
