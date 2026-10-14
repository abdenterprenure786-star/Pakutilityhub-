package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.HubBorder
import com.example.ui.theme.HubBorderGold
import com.example.ui.theme.HubCard
import com.example.ui.theme.HubCardElevated
import com.example.ui.theme.HubGold
import com.example.ui.theme.HubTextDim
import com.example.ui.theme.HubTextPrimary
import com.example.ui.theme.HubTextSecondary

@Composable
fun NavigationPanelDialog(
    selectedChannel: HubChannel,
    onSelectChannel: (HubChannel) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val channelDescriptions = mapOf(
        HubChannel.NET_SPEED to "Live internet speed test (Mbps/MB/s), ping latency & active network throughput meter",
        HubChannel.PUSH_ALERTS to "Push notification manager, Rs. 300 alert rules & live dispatch drawer",
        HubChannel.GOLD_INDEX to "24K/22K Sarafa bullion rates, volumetric calculator & Islamic Zakat matrix",
        HubChannel.FUEL_LOGS to "Official OGRA ex-depot bulletin fuel rates & real GPS trip burn calculator",
        HubChannel.FOREX_HUB to "State Bank (SBP) & open market parity exchange rates with live Frankfurter gateway",
        HubChannel.MANDI_BAZAAR to "All Pakistani agricultural mandi fruits, vegetables, food staples & poultry rates",
        HubChannel.TAX_STATEMENT to "FBR progressive salary income tax calculator for FY 2024–2026",
        HubChannel.RAW_MATERIALS to "Construction mill prices for Steel Grade 60, Cement 50kg, Bricks, Sand & Crush",
        HubChannel.AI_THINKING to "Gemini 3.1 Pro High-Thinking deep reasoning matrix with telemetry context"
    )

    AlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier.testTag("navigation_panel_dialog"),
        containerColor = HubCard,
        shape = RoundedCornerShape(18.dp),
        title = null,
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null,
                            tint = HubGold,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "NAVIGATION MATRIX",
                            style = MaterialTheme.typography.titleMedium,
                            color = HubTextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 13.sp
                        )
                    }

                    IconButton(
                        onClick = onDismissRequest,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = HubTextSecondary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // List of all channels
                HubChannel.values().forEach { channel ->
                    val isSelected = channel == selectedChannel
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .border(1.dp, if (isSelected) HubBorderGold else HubBorder, RoundedCornerShape(10.dp))
                            .clickable {
                                onSelectChannel(channel)
                                onDismissRequest()
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) HubCardElevated else Color(0xFF090D15)
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 9.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = channel.iconPrefix, fontSize = 18.sp)
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = channel.label,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = if (isSelected) HubGold else HubTextPrimary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                                Text(
                                    text = channelDescriptions[channel] ?: "",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = HubTextDim,
                                    fontSize = 9.sp,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {}
    )
}
