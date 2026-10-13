package com.example.ui.screens

import android.Manifest
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.data.UtilityRepository
import com.example.location.LocationPoint
import com.example.location.LocationRepository
import com.example.location.LocationTrackingService
import com.example.location.LocationUtils
import com.example.ui.components.GeoRadarCanvas
import com.example.ui.theme.HubBorder
import com.example.ui.theme.HubBorderGold
import com.example.ui.theme.HubCard
import com.example.ui.theme.HubCardElevated
import com.example.ui.theme.HubEmerald
import com.example.ui.theme.HubGold
import com.example.ui.theme.HubInnerWell
import com.example.ui.theme.HubRose
import com.example.ui.theme.HubSky
import com.example.ui.theme.HubTextDim
import com.example.ui.theme.HubTextPrimary
import com.example.ui.theme.HubTextSecondary

@Composable
fun GeoTrackingScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val currentLocation by LocationRepository.currentLocation.collectAsState()
    val breadcrumbs by LocationRepository.breadcrumbs.collectAsState()
    val isTracking by LocationRepository.isTracking.collectAsState()
    val totalDistanceMeters by LocationRepository.totalDistanceMeters.collectAsState()
    val tripDurationSeconds by LocationRepository.tripDurationSeconds.collectAsState()
    val geofences by LocationRepository.geofences.collectAsState()
    val speedThreshold by LocationRepository.speedAlertThresholdKmh.collectAsState()
    val isSimulatedJourney by LocationRepository.isSimulatedJourneyActive.collectAsState()

    var showAddGeofenceDialog by remember { mutableStateOf(false) }

    // Runtime Permission Request Launcher for Location & Notifications
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val fineGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
        val coarseGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        if (fineGranted || coarseGranted) {
            LocationTrackingService.startService(context)
        } else {
            Toast.makeText(context, "Location permission required for GPS tracking", Toast.LENGTH_SHORT).show()
        }
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
            .testTag("geo_tracking_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Nearest City & Coordinates Header Card
        val lat = currentLocation?.latitude ?: 31.5204
        val lon = currentLocation?.longitude ?: 74.3587
        val speed = currentLocation?.speedKmh ?: 0f
        val distKm = totalDistanceMeters / 1000.0
        val nearestCityPair = remember(lat, lon) { LocationUtils.findNearestPakCity(lat, lon) }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, HubBorderGold, RoundedCornerShape(14.dp)),
            colors = CardDefaults.cardColors(containerColor = HubCard),
            shape = RoundedCornerShape(14.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = HubGold,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "LOCATION TELEMETRY",
                                style = MaterialTheme.typography.labelSmall,
                                color = HubGold,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = "${nearestCityPair.first.name}, ${nearestCityPair.first.province}",
                            style = MaterialTheme.typography.titleMedium,
                            color = HubTextPrimary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Hub: ${nearestCityPair.first.mandiHub} • ${String.format("%.1f", nearestCityPair.second)} km",
                            style = MaterialTheme.typography.bodyMedium,
                            color = HubTextSecondary,
                            fontSize = 11.sp
                        )
                    }

                    IconButton(
                        onClick = {
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val clip = ClipData.newPlainText("Coordinates", "$lat, $lon")
                            clipboard.setPrimaryClip(clip)
                            Toast.makeText(context, "Coordinates copied to clipboard", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.testTag("copy_coords_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = "Copy Coordinates",
                            tint = HubTextSecondary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // GPS Raw Coordinate Pills
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(HubInnerWell)
                            .padding(horizontal = 8.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "LAT: ${String.format("%.5f", lat)}° N",
                            style = MaterialTheme.typography.labelSmall,
                            color = HubSky,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(HubInnerWell)
                            .padding(horizontal = 8.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "LON: ${String.format("%.5f", lon)}° E",
                            style = MaterialTheme.typography.labelSmall,
                            color = HubSky,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }
        }

        // Real-Time Radar & Breadcrumbs Trail Map
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "RADAR TRACKING & BREADCRUMBS",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubTextSecondary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${breadcrumbs.size} PTS LOGGED",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubGold
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            GeoRadarCanvas(
                currentLocation = currentLocation,
                breadcrumbs = breadcrumbs,
                geofences = geofences,
                enableRadarSweep = isTracking
            )
        }

        // Live Speed, Trip Distance, and Duration HUD Grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Speed HUD
            Card(
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, if (speed > speedThreshold) HubRose else HubBorder, RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = HubCard),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Speed,
                            contentDescription = null,
                            tint = if (speed > speedThreshold) HubRose else HubGold,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "SPEED",
                            style = MaterialTheme.typography.labelSmall,
                            color = HubTextSecondary
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${String.format("%.1f", speed)}",
                        style = MaterialTheme.typography.headlineMedium,
                        color = if (speed > speedThreshold) HubRose else HubTextPrimary,
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        text = "km/h • Alert @ ${speedThreshold.toInt()}",
                        style = MaterialTheme.typography.labelSmall,
                        color = HubTextDim,
                        fontSize = 9.sp
                    )
                }
            }

            // Distance HUD
            Card(
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, HubBorder, RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = HubCard),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Navigation,
                            contentDescription = null,
                            tint = HubSky,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "DISTANCE",
                            style = MaterialTheme.typography.labelSmall,
                            color = HubTextSecondary
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${String.format("%.2f", distKm)}",
                        style = MaterialTheme.typography.headlineMedium,
                        color = HubSky,
                        fontWeight = FontWeight.Black
                    )
                    val hours = tripDurationSeconds / 3600
                    val minutes = (tripDurationSeconds % 3600) / 60
                    val seconds = tripDurationSeconds % 60
                    Text(
                        text = String.format("km • %02d:%02d:%02d", hours, minutes, seconds),
                        style = MaterialTheme.typography.labelSmall,
                        color = HubTextDim,
                        fontSize = 9.sp
                    )
                }
            }
        }

        // Prominent Telemetry Result Display Box (Matching clarity in Gold, Fuel, Forex & Tax screens)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF070B13))
                .border(1.5.dp, HubBorderGold, RoundedCornerShape(12.dp))
                .padding(14.dp)
                .testTag("telemetry_result_card")
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(androidx.compose.foundation.shape.CircleShape)
                                .background(if (isTracking) HubEmerald else HubGold)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "LIVE TELEMETRY RESULT",
                            style = MaterialTheme.typography.labelSmall,
                            color = HubGold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            letterSpacing = 1.sp
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Prominent Clear Results Action Button
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(0xFF280B10))
                                .border(1.dp, HubRose.copy(alpha = 0.7f), RoundedCornerShape(6.dp))
                                .clickable {
                                    LocationRepository.resetTrip()
                                    Toast.makeText(context, "Telemetry Results Cleared", Toast.LENGTH_SHORT).show()
                                }
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                                .testTag("clear_telemetry_results_button"),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.DeleteSweep,
                                    contentDescription = "Clear Results",
                                    tint = HubRose,
                                    modifier = Modifier.size(12.dp)
                                )
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = "Clear Results",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = HubRose,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 9.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(6.dp))

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(0xFF132A15))
                                .border(1.dp, HubEmerald, RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = if (isTracking) "CRUISING • ${String.format("%.1f", speed)} KM/H" else "GPS IDLE",
                                style = MaterialTheme.typography.labelSmall,
                                color = HubEmerald,
                                fontWeight = FontWeight.Bold,
                                fontSize = 9.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column {
                        Text(
                            text = "TOTAL LOGGED TRANSIT",
                            style = MaterialTheme.typography.labelSmall,
                            color = HubTextDim,
                            fontSize = 9.sp
                        )
                        Text(
                            text = "${String.format("%.2f", distKm)} Kilometers",
                            style = MaterialTheme.typography.headlineMedium,
                            color = HubEmerald,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Serif,
                            fontSize = 22.sp
                        )
                        Text(
                            text = "Waypoint Chain: ${breadcrumbs.size} Breadcrumb Vectors",
                            style = MaterialTheme.typography.bodySmall,
                            color = HubTextSecondary,
                            fontSize = 11.sp
                        )
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "ACTIVE LOCATION SECTOR",
                            style = MaterialTheme.typography.labelSmall,
                            color = HubTextDim,
                            fontSize = 9.sp
                        )
                        Text(
                            text = "${nearestCityPair.first.name} Core",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "GPS Fix: ±3.5m Precision",
                            style = MaterialTheme.typography.bodySmall,
                            color = HubSky,
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }

        // Primary Tracking Controls Action Bar
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, HubBorder, RoundedCornerShape(14.dp)),
            colors = CardDefaults.cardColors(containerColor = HubCard),
            shape = RoundedCornerShape(14.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "TELEMETRY ENGINE CONTROLS",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubGold,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Start/Stop GPS Tracking
                    Button(
                        onClick = {
                            if (isTracking) {
                                LocationTrackingService.stopService(context)
                            } else {
                                val hasFine = ContextCompat.checkSelfPermission(
                                    context, Manifest.permission.ACCESS_FINE_LOCATION
                                ) == PackageManager.PERMISSION_GRANTED

                                if (hasFine) {
                                    LocationTrackingService.startService(context)
                                } else {
                                    val permissionsToRequest = mutableListOf(
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.ACCESS_COARSE_LOCATION
                                    )
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                        permissionsToRequest.add(Manifest.permission.POST_NOTIFICATIONS)
                                    }
                                    permissionLauncher.launch(permissionsToRequest.toTypedArray())
                                }
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("toggle_tracking_button"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isTracking) HubRose else HubGold,
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(
                            imageVector = if (isTracking) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isTracking) "Pause GPS" else "Start GPS",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }

                    // Simulated Driving Route (for instant previewing without moving)
                    OutlinedButton(
                        onClick = {
                            LocationRepository.toggleSimulation()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("toggle_simulation_button"),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = if (isSimulatedJourney) HubEmerald else HubTextPrimary
                        ),
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp, if (isSimulatedJourney) HubEmerald else HubBorder
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.DirectionsCar,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isSimulatedJourney) "Sim Active" else "Simulate Drive",
                            fontSize = 12.sp
                        )
                    }

                    // Reset Trip
                    IconButton(
                        onClick = {
                            LocationRepository.resetTrip()
                            Toast.makeText(context, "Trip metrics reset", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.testTag("reset_trip_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Reset Trip",
                            tint = HubTextSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Speed Alert Threshold Slider
                Text(
                    text = "Speed Limit Alert Trigger: ${speedThreshold.toInt()} km/h",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubTextSecondary
                )
                Slider(
                    value = speedThreshold,
                    onValueChange = { LocationRepository.setSpeedThreshold(it) },
                    valueRange = 40f..140f,
                    steps = 19,
                    colors = SliderDefaults.colors(
                        thumbColor = HubGold,
                        activeTrackColor = HubGold,
                        inactiveTrackColor = HubInnerWell
                    ),
                    modifier = Modifier.testTag("speed_slider")
                )
            }
        }

        // Real-Time Trip Fuel Burn Estimator
        val fuelRates by UtilityRepository.fuelRates.collectAsState()
        val fuelPricePerLtr = fuelRates.firstOrNull { it.fuelType == "PETROL" }?.pricePerLiter ?: 248.38
        val assumedAvgKmpl = 12.5
        val litersConsumed = distKm / assumedAvgKmpl
        val estimatedCostPkr = litersConsumed * fuelPricePerLtr

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, HubBorder, RoundedCornerShape(14.dp)),
            colors = CardDefaults.cardColors(containerColor = HubCardElevated),
            shape = RoundedCornerShape(14.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocalGasStation,
                        contentDescription = null,
                        tint = HubGold,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "GPS ROUTE FUEL BURN ESTIMATOR",
                        style = MaterialTheme.typography.labelSmall,
                        color = HubGold,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Estimated Fuel Burn",
                            style = MaterialTheme.typography.bodyMedium,
                            color = HubTextSecondary,
                            fontSize = 11.sp
                        )
                        Text(
                            text = "${String.format("%.2f", litersConsumed)} Liters",
                            style = MaterialTheme.typography.titleMedium,
                            color = HubTextPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "Estimated Trip Cost",
                            style = MaterialTheme.typography.bodyMedium,
                            color = HubTextSecondary,
                            fontSize = 11.sp
                        )
                        Text(
                            text = "Rs. ${String.format("%.0f", estimatedCostPkr)}",
                            style = MaterialTheme.typography.titleMedium,
                            color = HubEmerald,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // Active Geofence Zones
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ACTIVE GEOFENCING ZONES (${geofences.size})",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubTextSecondary,
                    fontWeight = FontWeight.Bold
                )

                TextButton(
                    onClick = { showAddGeofenceDialog = true },
                    modifier = Modifier.testTag("add_geofence_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.AddLocation,
                        contentDescription = null,
                        tint = HubGold,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Add Zone",
                        color = HubGold,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            geofences.forEach { zone ->
                val distMeters = LocationUtils.calculateDistanceMeters(
                    lat, lon, zone.latitude, zone.longitude
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .border(
                            1.dp,
                            if (zone.isInside) HubEmerald else HubBorder,
                            RoundedCornerShape(10.dp)
                        ),
                    colors = CardDefaults.cardColors(containerColor = HubCard),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = zone.name,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = HubTextPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                                if (zone.isInside) {
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(HubEmerald)
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = "INSIDE",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = Color.Black,
                                            fontWeight = FontWeight.Black,
                                            fontSize = 9.sp
                                        )
                                    }
                                }
                            }
                            Text(
                                text = zone.description,
                                style = MaterialTheme.typography.bodyMedium,
                                color = HubTextDim,
                                fontSize = 11.sp
                            )
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = if (distMeters < 1000) "${distMeters.toInt()}m" else "${String.format("%.1f", distMeters / 1000.0)}km",
                                style = MaterialTheme.typography.labelLarge,
                                color = if (zone.isInside) HubEmerald else HubGold
                            )
                            Text(
                                text = "Radius: ${zone.radiusMeters.toInt()}m",
                                style = MaterialTheme.typography.labelSmall,
                                color = HubTextDim,
                                fontSize = 9.sp
                            )
                        }
                    }
                }
            }
        }
    }

    // Add Custom Geofence Dialog
    if (showAddGeofenceDialog) {
        var zoneName by remember { mutableStateOf("") }
        var zoneRadius by remember { mutableFloatStateOf(800f) }
        var zoneDesc by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { showAddGeofenceDialog = false },
            title = {
                Text("Add Geofence Boundary", color = HubTextPrimary, fontWeight = FontWeight.Bold)
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        "Set boundary pinned at current GPS location (${String.format("%.4f", currentLocation?.latitude ?: 31.5204)}, ${String.format("%.4f", currentLocation?.longitude ?: 74.3587)}):",
                        style = MaterialTheme.typography.bodyMedium,
                        color = HubTextSecondary
                    )
                    OutlinedTextField(
                        value = zoneName,
                        onValueChange = { zoneName = it },
                        label = { Text("Zone Name (e.g. Shell Terminal)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = zoneDesc,
                        onValueChange = { zoneDesc = it },
                        label = { Text("Trigger Description") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        "Trigger Radius: ${zoneRadius.toInt()} meters",
                        style = MaterialTheme.typography.labelSmall,
                        color = HubGold
                    )
                    Slider(
                        value = zoneRadius,
                        onValueChange = { zoneRadius = it },
                        valueRange = 200f..3000f,
                        steps = 13,
                        colors = SliderDefaults.colors(thumbColor = HubGold, activeTrackColor = HubGold)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (zoneName.isNotBlank()) {
                            val cLat = currentLocation?.latitude ?: 31.5204
                            val cLon = currentLocation?.longitude ?: 74.3587
                            LocationRepository.addGeofence(
                                name = zoneName,
                                lat = cLat,
                                lon = cLon,
                                radius = zoneRadius,
                                description = zoneDesc.ifBlank { "Custom user geofence boundary" }
                            )
                            showAddGeofenceDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = HubGold, contentColor = Color.Black)
                ) {
                    Text("Create Zone")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddGeofenceDialog = false }) {
                    Text("Cancel", color = HubTextSecondary)
                }
            },
            containerColor = HubCard
        )
    }
}
