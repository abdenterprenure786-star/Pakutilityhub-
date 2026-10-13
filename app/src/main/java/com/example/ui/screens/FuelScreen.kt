package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.ui.draw.clip
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
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.UtilityRepository
import com.example.location.LocationRepository
import com.example.ui.theme.HubBorder
import com.example.ui.theme.HubBorderGold
import com.example.ui.theme.HubCard
import com.example.ui.theme.HubCardElevated
import com.example.ui.theme.HubEmerald
import com.example.ui.theme.HubGold
import com.example.ui.theme.HubInnerWell
import com.example.ui.theme.HubSky
import com.example.ui.theme.HubTextDim
import com.example.ui.theme.HubTextPrimary
import com.example.ui.theme.HubTextSecondary

@Composable
fun FuelScreen(
    modifier: Modifier = Modifier
) {
    val fuelRates by UtilityRepository.fuelRates.collectAsState()
    val totalDistanceMeters by LocationRepository.totalDistanceMeters.collectAsState()
    val currentGpsKm = remember(totalDistanceMeters) { totalDistanceMeters / 1000.0 }

    var tripDistanceKmInput by remember { mutableStateOf("") }
    var vehicleAverageKmplInput by remember { mutableStateOf("12.5") }
    var selectedFuelType by remember { mutableStateOf("PETROL") }

    val effectiveDistance = if (tripDistanceKmInput.isNotBlank()) {
        tripDistanceKmInput.toDoubleOrNull() ?: 0.0
    } else {
        currentGpsKm.coerceAtLeast(1.0)
    }

    val avgKmpl = vehicleAverageKmplInput.toDoubleOrNull() ?: 12.5
    val currentSelectedFuelPrice = fuelRates.find { it.fuelType == selectedFuelType }?.pricePerLiter ?: 279.75
    val litersNeeded = if (avgKmpl > 0) effectiveDistance / avgKmpl else 0.0
    val totalExpensePkr = litersNeeded * currentSelectedFuelPrice

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
            .testTag("fuel_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Fuel Title
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "OGRA PETROLEUM PRICE MATRIX",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubGold,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Official National Retail Fuel Prices",
                    style = MaterialTheme.typography.bodyMedium,
                    color = HubTextSecondary,
                    fontSize = 11.sp
                )
            }
            Box(
                modifier = Modifier
                    .border(1.dp, HubBorderGold, RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "LATEST GAZETTE",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubGold,
                    fontSize = 9.sp
                )
            }
        }

        // Fuel Rates List
        fuelRates.forEach { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, if (item.fuelType == selectedFuelType) HubBorderGold else HubBorder, RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = HubCard),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.LocalGasStation,
                                contentDescription = null,
                                tint = HubSky,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.bodyLarge,
                                color = HubTextPrimary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = "${item.statusBadge} • ${item.trendAlert}",
                            style = MaterialTheme.typography.labelSmall,
                            color = HubTextDim,
                            fontSize = 10.sp
                        )
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "Rs. ${String.format("%.2f", item.pricePerLiter)}",
                            style = MaterialTheme.typography.titleLarge,
                            color = HubGold,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            text = "per ${item.unit}",
                            style = MaterialTheme.typography.labelSmall,
                            color = HubTextDim,
                            fontSize = 9.sp
                        )
                    }
                }
            }
        }

        // Live Trip Fuel Burn & Cost Calculator
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, HubBorderGold, RoundedCornerShape(14.dp)),
            colors = CardDefaults.cardColors(containerColor = HubCardElevated),
            shape = RoundedCornerShape(14.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.DirectionsCar,
                        contentDescription = null,
                        tint = HubGold,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "LIVE GPS ROUTE FUEL BURN CALCULATOR",
                        style = MaterialTheme.typography.labelSmall,
                        color = HubGold,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = tripDistanceKmInput,
                        onValueChange = { tripDistanceKmInput = it },
                        placeholder = { Text("GPS: ${String.format("%.2f", currentGpsKm)} km") },
                        label = { Text("Distance (km)") },
                        modifier = Modifier.weight(1f),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = vehicleAverageKmplInput,
                        onValueChange = { vehicleAverageKmplInput = it },
                        label = { Text("Avg (km/L)") },
                        modifier = Modifier.weight(1f),
                        singleLine = true
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Vehicle Presets (Bike / Car / SUV / Rickshaw)
                Text(
                    text = "VEHICLE PRESETS (TAP TO SELECT)",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubTextDim,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val presets = listOf(
                        Triple("🏍️ Bike", "45.0", "70-125cc"),
                        Triple("🚗 Small Car", "20.0", "Alto/WagonR"),
                        Triple("🚘 Sedan", "13.0", "Corolla/Civic"),
                        Triple("🚙 SUV 4x4", "9.0", "Fortuner/Sportage")
                    )

                    presets.forEach { (name, avgVal, subtitle) ->
                        val isSelected = vehicleAverageKmplInput == avgVal
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isSelected) Color(0xFF282D07) else HubInnerWell)
                                .border(1.dp, if (isSelected) HubGold else HubBorder, RoundedCornerShape(8.dp))
                                .clickable { vehicleAverageKmplInput = avgVal }
                                .padding(vertical = 6.dp, horizontal = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = name,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (isSelected) HubGold else HubTextPrimary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp,
                                    maxLines = 1
                                )
                                Text(
                                    text = "${avgVal.toDouble().toInt()} km/L",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (isSelected) HubEmerald else HubTextDim,
                                    fontSize = 8.sp
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Quick button to sync with GPS telemetry
                Button(
                    onClick = {
                        tripDistanceKmInput = String.format("%.2f", currentGpsKm)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = HubInnerWell, contentColor = HubSky),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(imageVector = Icons.Default.NearMe, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Auto-Fill from Live GPS Trip (${String.format("%.2f", currentGpsKm)} km)", fontSize = 11.sp)
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Prominent Trip Calculation Result Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF07120B))
                        .border(1.5.dp, Color(0xFF10B981).copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                        .padding(12.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "PROJECTED TRIP FUEL EXPENSE",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = HubEmerald,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                )
                                Text(
                                    text = "سفر کا کل پیٹرول/ڈیزل خرچہ",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = HubTextDim,
                                    fontSize = 9.sp
                                )
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(Color(0xFF280B10))
                                        .border(1.dp, Color(0xFFF43F5E).copy(alpha = 0.5f), RoundedCornerShape(6.dp))
                                        .clickable { tripDistanceKmInput = "0" }
                                        .padding(horizontal = 6.dp, vertical = 3.dp)
                                ) {
                                    Text("Clear", color = Color(0xFFF43F5E), fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                }
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Rs. ${String.format("%,.0f", totalExpensePkr)}",
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = HubGold,
                                    fontWeight = FontWeight.Black,
                                    fontSize = 24.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text("Fuel Volume Needed", style = MaterialTheme.typography.labelSmall, color = HubTextDim, fontSize = 9.sp)
                                Text(
                                    "${String.format("%.2f", litersNeeded)} Litres",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = HubTextPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Column {
                                Text("Trip Distance", style = MaterialTheme.typography.labelSmall, color = HubTextDim, fontSize = 9.sp)
                                Text(
                                    "${String.format("%.1f", effectiveDistance)} km",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = HubTextPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Column(horizontalAlignment = Alignment.End) {
                                Text("Cost Per Km", style = MaterialTheme.typography.labelSmall, color = HubTextDim, fontSize = 9.sp)
                                val perKm = if (effectiveDistance > 0) totalExpensePkr / effectiveDistance else 0.0
                                Text(
                                    "Rs. ${String.format("%.1f", perKm)}/km",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = HubEmerald,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
