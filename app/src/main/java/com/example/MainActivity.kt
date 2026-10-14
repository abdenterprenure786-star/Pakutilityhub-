package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.location.LocationRepository
import com.example.ui.components.ChannelTabs
import com.example.ui.components.HubChannel
import com.example.ui.components.HubHeader
import com.example.ui.components.NavigationPanelDialog
import com.example.ui.screens.AiMatrixScreen
import com.example.ui.screens.ForexScreen
import com.example.ui.screens.FuelScreen
import com.example.ui.screens.SpeedTestScreen
import com.example.ui.screens.GoldScreen
import com.example.ui.screens.MandiScreen
import com.example.ui.screens.NotificationsScreen
import com.example.ui.screens.TaxMaterialsScreen
import com.example.ui.theme.HubBackground
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        LocationRepository.initialize(this)

        setContent {
            MyApplicationTheme {
                MainAppContent()
            }
        }
    }
}

@Composable
fun MainAppContent() {
    var currentChannel by remember { mutableStateOf(HubChannel.FUEL_LOGS) }
    var isNavigationPanelOpen by remember { mutableStateOf(false) }

    val notifications by LocationRepository.notifications.collectAsState()
    val isTracking by LocationRepository.isTracking.collectAsState()

    val unreadCount = remember(notifications) {
        notifications.count { !it.isRead }
    }

    var isFullScreen by remember { mutableStateOf(false) }

    // BackHandler: exits fullscreen first, or returns to FUEL_LOGS if inside another channel
    BackHandler(enabled = isFullScreen || currentChannel != HubChannel.FUEL_LOGS) {
        if (isFullScreen) {
            isFullScreen = false
        } else {
            currentChannel = HubChannel.FUEL_LOGS
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .testTag("main_app_scaffold"),
        contentWindowInsets = if (isFullScreen) WindowInsets(0, 0, 0, 0) else WindowInsets.systemBars,
        containerColor = HubBackground,
        topBar = {
            if (!isFullScreen) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .widthIn(max = 680.dp)
                    ) {
                        Column {
                            HubHeader(
                                unreadNotificationCount = unreadCount,
                                isTrackingActive = isTracking,
                                isFullScreen = false,
                                onToggleFullScreen = { isFullScreen = true },
                                onNotificationClick = {
                                    currentChannel = HubChannel.PUSH_ALERTS
                                },
                                onNavigationClick = {
                                    isNavigationPanelOpen = true
                                }
                            )
                            ChannelTabs(
                                selectedChannel = currentChannel,
                                onChannelSelect = { selected ->
                                    currentChannel = selected
                                }
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(HubBackground)
                .testTag("channel_content_container"),
            contentAlignment = Alignment.TopCenter
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .widthIn(max = 680.dp)
            ) {
                when (currentChannel) {
                    HubChannel.NET_SPEED -> SpeedTestScreen()
                    HubChannel.PUSH_ALERTS -> NotificationsScreen()
                    HubChannel.GOLD_INDEX -> GoldScreen()
                    HubChannel.FUEL_LOGS -> FuelScreen()
                    HubChannel.FOREX_HUB -> ForexScreen()
                    HubChannel.MANDI_BAZAAR -> MandiScreen()
                    HubChannel.TAX_STATEMENT -> TaxMaterialsScreen(showMaterialsInitially = false)
                    HubChannel.RAW_MATERIALS -> TaxMaterialsScreen(showMaterialsInitially = true)
                    HubChannel.AI_THINKING -> AiMatrixScreen()
                }
            }

            // Floating Frosted Glass Full-Screen Control Island (ONLY in Full Screen Mode)
            if (isFullScreen) {
                AnimatedVisibility(
                    visible = true,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(30.dp))
                            .background(Color(0xCC060914))
                            .border(
                                width = 1.dp,
                                brush = Brush.horizontalGradient(
                                    listOf(
                                        com.example.ui.theme.HubCosmicViolet,
                                        com.example.ui.theme.HubSky,
                                        com.example.ui.theme.HubGold
                                    )
                                ),
                                shape = RoundedCornerShape(30.dp)
                            )
                            .padding(horizontal = 14.dp, vertical = 6.dp)
                            .testTag("fullscreen_floating_island")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "⛶ FULL SCREEN",
                                style = MaterialTheme.typography.labelSmall,
                                color = com.example.ui.theme.HubGold,
                                fontWeight = FontWeight.Black,
                                fontSize = 10.sp
                            )

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color(0x3338BDF8))
                                    .clickable { isNavigationPanelOpen = true }
                                    .padding(horizontal = 7.dp, vertical = 3.dp)
                            ) {
                                Text(
                                    text = "${currentChannel.iconPrefix} ${currentChannel.label}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.White,
                                    fontSize = 10.sp
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color(0x33F43F5E))
                                    .clickable { isFullScreen = false }
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                                    .testTag("exit_fullscreen_button")
                            ) {
                                Text(
                                    text = "✕ Exit",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = com.example.ui.theme.HubRose,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (isNavigationPanelOpen) {
        NavigationPanelDialog(
            selectedChannel = currentChannel,
            onSelectChannel = { channel ->
                currentChannel = channel
            },
            onDismissRequest = {
                isNavigationPanelOpen = false
            }
        )
    }
}
