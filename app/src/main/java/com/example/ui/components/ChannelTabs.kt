package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.HubBackground
import com.example.ui.theme.HubGold
import com.example.ui.theme.HubTextDim
import com.example.ui.theme.HubTextSecondary

enum class HubChannel(val label: String, val iconPrefix: String) {
    NET_SPEED("NET SPEED", "🚀"),
    PUSH_ALERTS("PUSH ALERTS", "🔔"),
    GOLD_INDEX("GOLD INDEX", "✨"),
    FUEL_LOGS("FUEL LOGS", "⛽"),
    FOREX_HUB("FOREX HUB", "💱"),
    MANDI_BAZAAR("MANDI BAZAAR", "🥬"),
    TAX_STATEMENT("TAX STATEMENT", "🧾"),
    RAW_MATERIALS("RAW MATERIALS", "🏗️"),
    AI_THINKING("AI THINKING", "🧠")
}

@Composable
fun ChannelTabs(
    selectedChannel: HubChannel,
    onChannelSelect: (HubChannel) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(HubBackground)
            .horizontalScroll(scrollState)
            .padding(horizontal = 12.dp)
            .testTag("channel_tabs_row")
    ) {
        HubChannel.values().forEach { channel ->
            val isSelected = channel == selectedChannel

            Column(
                modifier = Modifier
                    .clickable { onChannelSelect(channel) }
                    .padding(horizontal = 10.dp, vertical = 6.dp)
                    .testTag("tab_${channel.name.lowercase()}"),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${channel.iconPrefix} ${channel.label}",
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 11.sp,
                    color = if (isSelected) HubGold else HubTextDim,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                )

                Box(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .height(2.dp)
                        .fillMaxWidth()
                        .background(if (isSelected) HubGold else Color.Transparent)
                )
            }
        }
    }
}
