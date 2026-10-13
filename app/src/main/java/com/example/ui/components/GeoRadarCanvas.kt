package com.example.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.location.GeofenceZone
import com.example.location.LocationPoint
import com.example.ui.theme.HubBackground
import com.example.ui.theme.HubBorderGold
import com.example.ui.theme.HubEmerald
import com.example.ui.theme.HubGold
import com.example.ui.theme.HubRose
import com.example.ui.theme.HubSky
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun GeoRadarCanvas(
    currentLocation: LocationPoint?,
    breadcrumbs: List<LocationPoint>,
    geofences: List<GeofenceZone>,
    modifier: Modifier = Modifier,
    enableRadarSweep: Boolean = true
) {
    var zoomScale by remember { mutableFloatStateOf(1f) }
    var panOffsetX by remember { mutableFloatStateOf(0f) }
    var panOffsetY by remember { mutableFloatStateOf(0f) }

    val infiniteTransition = rememberInfiniteTransition(label = "radarSweep")
    val sweepAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "sweepAngle"
    )

    val pulseRadius by infiniteTransition.animateFloat(
        initialValue = 6f,
        targetValue = 18f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseRadius"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(280.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(HubBackground)
            .border(1.dp, HubBorderGold, RoundedCornerShape(16.dp))
            .testTag("geo_radar_canvas_container"),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        zoomScale = (zoomScale * zoom).coerceIn(0.5f, 3.5f)
                        panOffsetX += pan.x
                        panOffsetY += pan.y
                    }
                }
                .testTag("geo_radar_canvas")
        ) {
            val center = Offset(size.width / 2 + panOffsetX, size.height / 2 + panOffsetY)
            val maxRadius = (size.minDimension / 2) * 0.9f

            // 1. Draw Radar Range Grid Rings
            val ringCount = 4
            for (i in 1..ringCount) {
                val radius = (maxRadius / ringCount) * i * zoomScale
                drawCircle(
                    color = Color(0x22DBE637),
                    radius = radius,
                    center = center,
                    style = Stroke(width = 1.2f)
                )
            }

            // Crosshair Axes
            drawLine(
                color = Color(0x33DBE637),
                start = Offset(center.x - maxRadius * zoomScale, center.y),
                end = Offset(center.x + maxRadius * zoomScale, center.y),
                strokeWidth = 1f
            )
            drawLine(
                color = Color(0x33DBE637),
                start = Offset(center.x, center.y - maxRadius * zoomScale),
                end = Offset(center.x, center.y + maxRadius * zoomScale),
                strokeWidth = 1f
            )

            // 2. Radar Sweep Beam
            if (enableRadarSweep) {
                val sweepRad = Math.toRadians(sweepAngle.toDouble())
                val beamEnd = Offset(
                    (center.x + maxRadius * zoomScale * cos(sweepRad)).toFloat(),
                    (center.y + maxRadius * zoomScale * sin(sweepRad)).toFloat()
                )
                drawLine(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0x88DBE637), Color(0x00DBE637)),
                        start = center,
                        end = beamEnd
                    ),
                    start = center,
                    end = beamEnd,
                    strokeWidth = 2.5f
                )
            }

            val currentLat = currentLocation?.latitude ?: 31.5204
            val currentLon = currentLocation?.longitude ?: 74.3587
            val metersPerPixel = (2000.0 / (maxRadius * zoomScale))

            // 3. Draw Geofence Zones
            geofences.forEach { zone ->
                val dLat = (zone.latitude - currentLat) * 111320.0
                val dLon = (zone.longitude - currentLon) * (111320.0 * cos(Math.toRadians(currentLat)))
                val zoneX = (center.x + (dLon / metersPerPixel)).toFloat()
                val zoneY = (center.y - (dLat / metersPerPixel)).toFloat()
                val zoneRadiusPx = (zone.radiusMeters / metersPerPixel).toFloat()

                val zoneColor = if (zone.isInside) HubEmerald else HubSky
                // Fill zone
                drawCircle(
                    color = zoneColor.copy(alpha = 0.12f),
                    radius = zoneRadiusPx,
                    center = Offset(zoneX, zoneY)
                )
                // Zone Border
                drawCircle(
                    color = zoneColor.copy(alpha = 0.65f),
                    radius = zoneRadiusPx,
                    center = Offset(zoneX, zoneY),
                    style = Stroke(width = 1.5f)
                )
                // Center pinpoint
                drawCircle(
                    color = zoneColor,
                    radius = 3.5f,
                    center = Offset(zoneX, zoneY)
                )
            }

            // 4. Draw Breadcrumb Journey Trail
            if (breadcrumbs.size > 1) {
                val path = Path()
                var first = true
                breadcrumbs.forEach { pt ->
                    val dLat = (pt.latitude - currentLat) * 111320.0
                    val dLon = (pt.longitude - currentLon) * (111320.0 * cos(Math.toRadians(currentLat)))
                    val x = (center.x + (dLon / metersPerPixel)).toFloat()
                    val y = (center.y - (dLat / metersPerPixel)).toFloat()

                    if (first) {
                        path.moveTo(x, y)
                        first = false
                    } else {
                        path.lineTo(x, y)
                    }

                    // Breadcrumb dot
                    drawCircle(
                        color = Color(0x99DBE637),
                        radius = 2.5f,
                        center = Offset(x, y)
                    )
                }
                drawPath(
                    path = path,
                    color = HubGold.copy(alpha = 0.75f),
                    style = Stroke(width = 2.5f)
                )
            }

            // 5. Draw User / Current Vehicle Beacon
            // Pulse outer glow
            drawCircle(
                color = HubGold.copy(alpha = 0.35f),
                radius = pulseRadius,
                center = center
            )
            // Solid center marker
            drawCircle(
                color = HubGold,
                radius = 7f,
                center = center
            )
            drawCircle(
                color = Color.Black,
                radius = 2.5f,
                center = center
            )

            // Heading pointer direction cone
            val bearingRad = Math.toRadians((currentLocation?.bearing?.toDouble() ?: 0.0) - 90.0)
            val pointerLength = 22f
            val pointerTip = Offset(
                (center.x + pointerLength * cos(bearingRad)).toFloat(),
                (center.y + pointerLength * sin(bearingRad)).toFloat()
            )
            drawLine(
                color = HubGold,
                start = center,
                end = pointerTip,
                strokeWidth = 3f
            )
        }
    }
}
