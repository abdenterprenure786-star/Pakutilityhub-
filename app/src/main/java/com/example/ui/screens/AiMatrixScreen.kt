package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ai.GeminiAiService
import com.example.ai.GeminiResponse
import com.example.data.UtilityRepository
import com.example.location.LocationRepository
import com.example.location.LocationUtils
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
import kotlinx.coroutines.launch

import com.example.ui.components.AiDesignHeroCard

@Composable
fun AiMatrixScreen(
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val currentLocation by LocationRepository.currentLocation.collectAsState()
    val totalDistanceMeters by LocationRepository.totalDistanceMeters.collectAsState()
    val goldRates by UtilityRepository.goldRates.collectAsState()
    val fuelRates by UtilityRepository.fuelRates.collectAsState()
    val forexRates by UtilityRepository.forexRates.collectAsState()

    var userPromptInput by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var aiResponse by remember { mutableStateOf<GeminiResponse?>(null) }

    val lat = currentLocation?.latitude ?: 31.5204
    val lon = currentLocation?.longitude ?: 74.3587
    val nearestCity = remember(lat, lon) { LocationUtils.findNearestPakCity(lat, lon) }

    val currentContextString = remember(lat, lon, totalDistanceMeters, goldRates, fuelRates, forexRates) {
        """
        Telemetry: Lat=${String.format("%.4f", lat)}, Lon=${String.format("%.4f", lon)}
        Nearest City: ${nearestCity.first.name} (${String.format("%.1f", nearestCity.second)} km)
        Trip Distance Logged: ${String.format("%.2f", totalDistanceMeters / 1000.0)} km
        24K Gold Tola: Rs. ${goldRates.first().ratePerTola}
        Super Petrol Rate: Rs. ${fuelRates.first().pricePerLiter}/L
        USD/PKR Interbank: Rs. ${forexRates.first().interbankSell}
        """.trimIndent()
    }

    val presetQueries = listOf(
        "🛰️ Analyze my GPS location, speed & trip fuel cost economy",
        "🪙 24K Gold hedging strategy: Buy dip or wait for correction?",
        "🧾 FBR Tax optimization advisory for salaried earners in FY2026",
        "🌾 Agricultural Mandi pricing trend & arbitrage between cities"
    )

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
            .testTag("ai_matrix_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Flagship AI Design System & Writing Style Card (Matches User Photo Reference)
        AiDesignHeroCard(
            onChipClick = { chip ->
                val prompt = when (chip) {
                    "Website Design" -> "Generate modern web design systems with ethereal cosmic aura, frosted glass, and editorial typography."
                    "AI Prompts" -> "Prompt engineering master framework for Pakistani financial matrix & spatial telemetry."
                    "AI Workflows" -> "Real-time push/pull workflow for Gold, Fuel, and Mandi market rates in Pakistan."
                    "Creative Direction" -> "Architectural creative direction for high-fidelity Android mobile interfaces with zero generic slop."
                    else -> "Provide AI design systems analysis."
                }
                userPromptInput = prompt
                isLoading = true
                coroutineScope.launch {
                    val res = GeminiAiService.analyzeWithHighThinking(
                        userPrompt = prompt,
                        contextData = currentContextString
                    )
                    aiResponse = res
                    isLoading = false
                }
            }
        )

        // High Thinking Model Banner
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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(HubInnerWell)
                                .border(1.dp, HubBorderGold, RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Psychology,
                                contentDescription = null,
                                tint = HubGold,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "GEMINI HIGH THINKING MATRIX",
                                style = MaterialTheme.typography.titleMedium,
                                color = HubTextPrimary,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Model: gemini-3.1-pro-preview • ThinkingLevel.HIGH",
                                style = MaterialTheme.typography.labelSmall,
                                color = HubGold,
                                fontSize = 9.sp
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFF282D07))
                            .border(1.dp, HubBorderGold, RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "DEEP REASONING",
                            style = MaterialTheme.typography.labelSmall,
                            color = HubGold,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Live telemetry & commodity prices are automatically injected into reasoning context:",
                    style = MaterialTheme.typography.bodyMedium,
                    color = HubTextSecondary,
                    fontSize = 11.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(HubInnerWell)
                        .padding(8.dp)
                ) {
                    Text(
                        text = "GPS: ${nearestCity.first.name} • Gold: Rs. ${goldRates.first().ratePerTola} • Petrol: Rs. ${fuelRates.first().pricePerLiter} • USD: Rs. ${forexRates.first().interbankSell}",
                        style = MaterialTheme.typography.labelSmall,
                        color = HubSky,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 10.sp
                    )
                }
            }
        }

        // Quick Preset Prompts
        Text(
            text = "SELECT COMPLEX REASONING TOPIC",
            style = MaterialTheme.typography.labelSmall,
            color = HubTextSecondary,
            fontWeight = FontWeight.Bold
        )

        presetQueries.forEach { preset ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        userPromptInput = preset
                    }
                    .border(1.dp, HubBorder, RoundedCornerShape(10.dp)),
                colors = CardDefaults.cardColors(containerColor = HubCardElevated),
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = preset,
                        style = MaterialTheme.typography.bodyMedium,
                        color = HubTextPrimary,
                        fontSize = 12.sp
                    )
                }
            }
        }

        // Custom Query Input Field
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, HubBorder, RoundedCornerShape(14.dp)),
            colors = CardDefaults.cardColors(containerColor = HubCard),
            shape = RoundedCornerShape(14.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                OutlinedTextField(
                    value = userPromptInput,
                    onValueChange = { userPromptInput = it },
                    label = { Text("Ask Gemini High Thinking Matrix...") },
                    placeholder = { Text("e.g. Plan fuel budget from Lahore to Islamabad with mandi stops") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        val promptToRun = userPromptInput.ifBlank { presetQueries.first() }
                        isLoading = true
                        aiResponse = null
                        coroutineScope.launch {
                            val res = GeminiAiService.analyzeWithHighThinking(
                                userPrompt = promptToRun,
                                contextData = currentContextString
                            )
                            aiResponse = res
                            isLoading = false
                        }
                    },
                    enabled = !isLoading,
                    colors = ButtonDefaults.buttonColors(containerColor = HubGold, contentColor = Color.Black),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("execute_ai_thinking_button")
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            color = Color.Black,
                            modifier = Modifier.size(16.dp),
                            strokeWidth = 2.dp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Thinking Deeply (gemini-3.1-pro-preview)...", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    } else {
                        Icon(imageVector = Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Execute High Thinking Analysis", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // AI Response Output Card
        if (isLoading) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, HubBorderGold, RoundedCornerShape(14.dp)),
                colors = CardDefaults.cardColors(containerColor = HubCardElevated),
                shape = RoundedCornerShape(14.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(color = HubGold, modifier = Modifier.size(32.dp))
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Synthesizing spatial telemetry & financial indicators...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = HubGold
                    )
                    Text(
                        text = "Evaluating multiple scenario paths with thinkingLevel = HIGH",
                        style = MaterialTheme.typography.labelSmall,
                        color = HubTextDim,
                        fontSize = 10.sp
                    )
                }
            }
        }

        aiResponse?.let { resp ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, HubBorderGold, RoundedCornerShape(14.dp)),
                colors = CardDefaults.cardColors(containerColor = HubCard),
                shape = RoundedCornerShape(14.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = HubGold,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "SYNTHESIS INTELLIGENCE REPORT",
                                style = MaterialTheme.typography.labelSmall,
                                color = HubGold,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(HubInnerWell)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = resp.modelUsed,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = HubSky,
                                    fontSize = 9.sp
                                )
                            }

                            Spacer(modifier = Modifier.width(6.dp))

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(Color(0xFF280B10))
                                    .border(1.dp, Color(0xFFF43F5E).copy(alpha = 0.5f), RoundedCornerShape(4.dp))
                                    .clickable { aiResponse = null }
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                                    .testTag("clear_ai_result_button")
                            ) {
                                Text(
                                    text = "✕ Clear Result",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color(0xFFF43F5E),
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = resp.text,
                        style = MaterialTheme.typography.bodyMedium,
                        color = HubTextPrimary,
                        lineHeight = 22.sp
                    )
                }
            }
        }
    }
}
