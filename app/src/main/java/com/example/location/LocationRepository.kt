package com.example.location

import android.content.Context
import com.example.notifications.AlertCategory
import com.example.notifications.AlertSeverity
import com.example.notifications.NotificationHelper
import com.example.notifications.NotificationItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin

object LocationRepository {

    private val scope = CoroutineScope(Dispatchers.Default)

    private val _currentLocation = MutableStateFlow<LocationPoint?>(
        // Initial location: Lahore Mall Road coordinates with active cruising speed
        LocationPoint(
            latitude = 31.5204,
            longitude = 74.3587,
            altitude = 217.0,
            speedKmh = 42.5f,
            bearing = 45f,
            accuracyMeters = 3.5f
        )
    )
    val currentLocation: StateFlow<LocationPoint?> = _currentLocation.asStateFlow()

    private val initialBreadcrumbsList = listOf(
        LocationPoint(31.5120, 74.3480, 215.0, 36f, 40f, 3f),
        LocationPoint(31.5135, 74.3502, 215.0, 38f, 42f, 3f),
        LocationPoint(31.5150, 74.3521, 216.0, 41f, 44f, 3f),
        LocationPoint(31.5168, 74.3545, 216.0, 44f, 45f, 3f),
        LocationPoint(31.5185, 74.3565, 217.0, 43f, 45f, 3f),
        LocationPoint(31.5204, 74.3587, 217.0, 42.5f, 45f, 3.5f)
    )

    private val _breadcrumbs = MutableStateFlow<List<LocationPoint>>(initialBreadcrumbsList)
    val breadcrumbs: StateFlow<List<LocationPoint>> = _breadcrumbs.asStateFlow()

    private val _isTracking = MutableStateFlow(true)
    val isTracking: StateFlow<Boolean> = _isTracking.asStateFlow()

    private val _totalDistanceMeters = MutableStateFlow(4820.0)
    val totalDistanceMeters: StateFlow<Double> = _totalDistanceMeters.asStateFlow()

    private val _tripDurationSeconds = MutableStateFlow(765L)
    val tripDurationSeconds: StateFlow<Long> = _tripDurationSeconds.asStateFlow()

    private val _speedAlertThresholdKmh = MutableStateFlow(75f)
    val speedAlertThresholdKmh: StateFlow<Float> = _speedAlertThresholdKmh.asStateFlow()

    private val _isSimulatedJourneyActive = MutableStateFlow(false)
    val isSimulatedJourneyActive: StateFlow<Boolean> = _isSimulatedJourneyActive.asStateFlow()

    private val _geofences = MutableStateFlow<List<GeofenceZone>>(
        listOf(
            GeofenceZone(
                id = "geo-1",
                name = "PSO Central Terminal & Fuel Depot",
                latitude = 31.5240,
                longitude = 74.3620,
                radiusMeters = 600f,
                description = "Automated fuel price update & discount alert zone"
            ),
            GeofenceZone(
                id = "geo-2",
                name = "Lahore Badami Bagh Mandi Gate",
                latitude = 31.5930,
                longitude = 74.3160,
                radiusMeters = 1200f,
                description = "Live morning produce auction pricing trigger"
            ),
            GeofenceZone(
                id = "geo-3",
                name = "State Bank / Bullion Safe Zone",
                latitude = 31.5600,
                longitude = 74.3350,
                radiusMeters = 500f,
                description = "Forex settlement & Gold 24K secure clearance zone"
            ),
            GeofenceZone(
                id = "geo-4",
                name = "Motorway M-2 Toll Plaza Border",
                latitude = 31.6200,
                longitude = 74.2400,
                radiusMeters = 1500f,
                description = "High-speed transit highway checkpoint"
            )
        )
    )
    val geofences: StateFlow<List<GeofenceZone>> = _geofences.asStateFlow()

    private val _notifications = MutableStateFlow<List<NotificationItem>>(
        listOf(
            NotificationItem(
                title = "Live Hub Handshake Complete",
                message = "GPS positioning engine initialized. Geofence monitors are active.",
                category = AlertCategory.SYSTEM,
                severity = AlertSeverity.SUCCESS
            ),
            NotificationItem(
                title = "Gold Bullion Alert",
                message = "24K Gold jumped +Rs. 1,200 to Rs. 272,400/tola today.",
                category = AlertCategory.GOLD_BULLION,
                severity = AlertSeverity.INFO
            )
        )
    )
    val notifications: StateFlow<List<NotificationItem>> = _notifications.asStateFlow()

    private val _notificationEvents = MutableSharedFlow<NotificationItem>()
    val notificationEvents: SharedFlow<NotificationItem> = _notificationEvents.asSharedFlow()

    private var simulationJob: Job? = null
    private var timerJob: Job? = null
    private var appContext: Context? = null

    fun initialize(context: Context) {
        appContext = context.applicationContext
    }

    fun setSpeedThreshold(threshold: Float) {
        _speedAlertThresholdKmh.value = threshold
    }

    fun startTracking() {
        if (_isTracking.value) return
        _isTracking.value = true

        startTimer()

        // Push notification on tracking start
        appContext?.let { ctx ->
            NotificationHelper.sendSystemNotification(
                ctx,
                notificationId = 1001,
                title = "📡 Live Geo Tracking Active",
                message = "Continuous background telemetry & geofencing radar is active."
            )
        }
        addNotification(
            NotificationItem(
                title = "Geo Tracking Started",
                message = "High-precision GPS telemetry recording started.",
                category = AlertCategory.GEOLOCATION,
                severity = AlertSeverity.INFO
            )
        )
    }

    fun stopTracking() {
        _isTracking.value = false
        stopSimulation()
        timerJob?.cancel()
        timerJob = null

        appContext?.let { ctx ->
            NotificationHelper.sendSystemNotification(
                ctx,
                notificationId = 1002,
                title = "⏹️ Tracking Paused",
                message = "Session concluded. Total logged distance: ${String.format("%.2f", _totalDistanceMeters.value / 1000.0)} km"
            )
        }
    }

    fun resetTrip() {
        _breadcrumbs.value = emptyList()
        _totalDistanceMeters.value = 0.0
        _tripDurationSeconds.value = 0L
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = scope.launch {
            while (_isTracking.value) {
                delay(1000)
                _tripDurationSeconds.value += 1
            }
        }
    }

    fun onNewLocationFix(newPoint: LocationPoint) {
        val lastPoint = _currentLocation.value
        _currentLocation.value = newPoint

        // Add to breadcrumb trail
        val currentList = _breadcrumbs.value.toMutableList()
        currentList.add(newPoint)
        if (currentList.size > 200) {
            currentList.removeAt(0)
        }
        _breadcrumbs.value = currentList

        // Calculate distance increment
        if (lastPoint != null) {
            val dist = LocationUtils.calculateDistanceMeters(
                lastPoint.latitude, lastPoint.longitude,
                newPoint.latitude, newPoint.longitude
            )
            if (dist > 1.0 && dist < 500.0) { // filter out GPS jitter & impossible jumps
                _totalDistanceMeters.value += dist
            }
        }

        // Check speed alert
        if (newPoint.speedKmh > _speedAlertThresholdKmh.value) {
            val alert = NotificationItem(
                title = "⚠️ Speed Limit Alert",
                message = "Current velocity ${String.format("%.1f", newPoint.speedKmh)} km/h exceeds threshold (${_speedAlertThresholdKmh.value} km/h)!",
                category = AlertCategory.GEOLOCATION,
                severity = AlertSeverity.WARNING
            )
            addNotification(alert)
            appContext?.let { ctx ->
                NotificationHelper.sendSystemNotification(
                    ctx,
                    notificationId = 2001,
                    title = alert.title,
                    message = alert.message
                )
            }
        }

        // Check Geofences
        checkGeofences(newPoint)
    }

    private fun checkGeofences(point: LocationPoint) {
        val updated = _geofences.value.map { geofence ->
            val dist = LocationUtils.calculateDistanceMeters(
                point.latitude, point.longitude,
                geofence.latitude, geofence.longitude
            )
            val isNowInside = dist <= geofence.radiusMeters

            if (isNowInside != geofence.isInside) {
                if (isNowInside && geofence.alertOnEntry) {
                    val alert = NotificationItem(
                        title = "📍 Geofence Entered",
                        message = "You arrived within ${geofence.name} (${String.format("%.0f", dist)}m from center).",
                        category = AlertCategory.GEOLOCATION,
                        severity = AlertSeverity.SUCCESS
                    )
                    addNotification(alert)
                    appContext?.let { ctx ->
                        NotificationHelper.sendSystemNotification(
                            ctx,
                            notificationId = geofence.id.hashCode(),
                            title = alert.title,
                            message = alert.message
                        )
                    }
                } else if (!isNowInside && geofence.alertOnExit) {
                    val alert = NotificationItem(
                        title = "🚪 Geofence Exited",
                        message = "Departed ${geofence.name}. Distance now: ${String.format("%.0f", dist)}m.",
                        category = AlertCategory.GEOLOCATION,
                        severity = AlertSeverity.INFO
                    )
                    addNotification(alert)
                    appContext?.let { ctx ->
                        NotificationHelper.sendSystemNotification(
                            ctx,
                            notificationId = geofence.id.hashCode() + 1,
                            title = alert.title,
                            message = alert.message
                        )
                    }
                }
            }
            geofence.copy(isInside = isNowInside)
        }
        _geofences.value = updated
    }

    fun addGeofence(name: String, lat: Double, lon: Double, radius: Float, description: String) {
        val newZone = GeofenceZone(
            name = name,
            latitude = lat,
            longitude = lon,
            radiusMeters = radius,
            description = description
        )
        _geofences.value = _geofences.value + newZone
        addNotification(
            NotificationItem(
                title = "Geofence Created",
                message = "New tracking boundary \"$name\" active with ${radius.toInt()}m radius.",
                category = AlertCategory.GEOLOCATION,
                severity = AlertSeverity.INFO
            )
        )
    }

    fun removeGeofence(id: String) {
        _geofences.value = _geofences.value.filter { it.id != id }
    }

    fun addNotification(item: NotificationItem) {
        _notifications.value = listOf(item) + _notifications.value
        scope.launch {
            _notificationEvents.emit(item)
        }
    }

    fun markAllNotificationsRead() {
        _notifications.value = _notifications.value.map { it.copy(isRead = true) }
    }

    fun clearNotifications() {
        _notifications.value = emptyList()
    }

    // Interactive Demo / Simulation mode allowing user or tester to preview real-time vehicle movement
    fun toggleSimulation() {
        if (_isSimulatedJourneyActive.value) {
            stopSimulation()
        } else {
            startSimulation()
        }
    }

    private fun startSimulation() {
        _isSimulatedJourneyActive.value = true
        startTracking()

        simulationJob?.cancel()
        simulationJob = scope.launch {
            var step = 0
            val baseLat = 31.5204
            val baseLon = 74.3587
            var currentLat = baseLat
            var currentLon = baseLon

            while (_isSimulatedJourneyActive.value) {
                step++
                // Simulate vehicle driving along a route towards PSO depot and Mandi
                val angle = (step * 0.08)
                val speed = (38f + (sin(step.toDouble()) * 18f).toFloat()).coerceAtLeast(15f)
                val bearing = (step * 12f) % 360f

                currentLat += 0.00035 * sin(angle)
                currentLon += 0.00035 * cos(angle)

                val point = LocationPoint(
                    latitude = currentLat,
                    longitude = currentLon,
                    altitude = 217.0 + (sin(step * 0.1) * 8.0),
                    speedKmh = speed,
                    bearing = bearing,
                    accuracyMeters = 3.0f,
                    timestamp = System.currentTimeMillis()
                )
                onNewLocationFix(point)
                delay(2000)
            }
        }
    }

    private fun stopSimulation() {
        _isSimulatedJourneyActive.value = false
        simulationJob?.cancel()
        simulationJob = null
    }
}
