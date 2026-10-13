package com.example.ui.screens

import android.Manifest
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ClearAll
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.PakUtilityApp
import com.example.data.ProSubscriptionManager
import com.example.data.UtilityRepository
import com.example.location.LocationRepository
import com.example.notifications.AlertCategory
import com.example.notifications.AlertSeverity
import com.example.notifications.NotificationHelper
import com.example.notifications.NotificationItem
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun NotificationsScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val notifications by LocationRepository.notifications.collectAsState()

    var selectedFilter by remember { mutableStateOf<AlertCategory?>(null) }
    var customAlertTitle by remember { mutableStateOf("") }
    var customAlertMessage by remember { mutableStateOf("") }
    var isPushComposerVisible by remember { mutableStateOf(false) }

    val isProUser by ProSubscriptionManager.isProUser.collectAsState()
    val isAutoPullActive by ProSubscriptionManager.isAutoPullActive.collectAsState()
    val pullIntervalMinutes by ProSubscriptionManager.pullIntervalMinutes.collectAsState()
    val lastPullTimestamp by ProSubscriptionManager.lastPullTimestampMillis.collectAsState()

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(context, "Notification permission granted!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Notifications permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    val isPermissionGranted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        ContextCompat.checkSelfPermission(
            context, Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    } else {
        true
    }

    val filteredList = remember(notifications, selectedFilter) {
        if (selectedFilter == null) notifications
        else notifications.filter { it.category == selectedFilter }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .testTag("notifications_screen"),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Push Status & Permission Banner
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, if (isPermissionGranted) HubBorderGold else HubRose, RoundedCornerShape(14.dp)),
            colors = CardDefaults.cardColors(containerColor = HubCard),
            shape = RoundedCornerShape(14.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(if (isPermissionGranted) HubEmerald.copy(alpha = 0.15f) else HubRose.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.NotificationsActive,
                            contentDescription = null,
                            tint = if (isPermissionGranted) HubEmerald else HubRose,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = if (isPermissionGranted) "Push Engine Connected" else "Push Permission Required",
                            style = MaterialTheme.typography.titleMedium,
                            color = HubTextPrimary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (isPermissionGranted) "High-priority heads-up delivery active" else "Allow notifications to receive geo & rate alerts",
                            style = MaterialTheme.typography.bodyMedium,
                            color = HubTextSecondary,
                            fontSize = 11.sp
                        )
                    }
                }

                if (!isPermissionGranted && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    Button(
                        onClick = {
                            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = HubGold, contentColor = Color.Black),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.testTag("grant_permission_button")
                    ) {
                        Text("Enable", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Pro Merged Push & Pull Notification Hub Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, if (isProUser) HubEmerald else HubBorderGold, RoundedCornerShape(14.dp)),
            colors = CardDefaults.cardColors(containerColor = if (isProUser) Color(0xFF07140B) else HubCardElevated),
            shape = RoundedCornerShape(14.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Sync,
                            contentDescription = null,
                            tint = if (isProUser) HubEmerald else HubGold,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "MERGED PUSH & PULL NOTIFICATION ENGINE",
                            style = MaterialTheme.typography.labelSmall,
                            color = if (isProUser) HubEmerald else HubGold,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (isProUser) Color(0xFF132A15) else Color(0xFF282D07))
                            .border(1.dp, if (isProUser) HubEmerald else HubBorderGold, RoundedCornerShape(6.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = if (isProUser) "PRO VIP (NO ADS)" else "FREE TIER",
                            style = MaterialTheme.typography.labelSmall,
                            color = if (isProUser) HubEmerald else HubGold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 8.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "In Pro tier, master feeds (Gold, OGRA Fuel, Forex, Mandi Produce) are automatically pulled and converted into instant push alerts whenever rates move by Rs. 300+.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = HubTextSecondary,
                    fontSize = 11.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                // On-demand Pull Action Button
                Button(
                    onClick = {
                        UtilityRepository.syncPakUtilityHubMasterFeeds()
                        ProSubscriptionManager.recordPullExecuted()
                        val pullNotice = NotificationItem(
                            title = "🔄 Live Feeds Pulled & Synchronized",
                            message = "Latest market parameters pulled from open gateways. Zero rate deviations above Rs. 300 threshold.",
                            category = AlertCategory.SYSTEM,
                            severity = AlertSeverity.SUCCESS
                        )
                        LocationRepository.addNotification(pullNotice)
                        NotificationHelper.sendSystemNotification(
                            context,
                            notificationId = (1000..9999).random(),
                            title = "🔄 PakUtility Feeds Pulled",
                            message = "Gold 24K: Rs. 435K | Petrol: Rs. 398.30 | Mandi: 24 commodities synced",
                            channelId = PakUtilityApp.CHANNEL_MARKET
                        )
                        Toast.makeText(context, "Feeds pulled! Push notifications synchronized to drawer.", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isProUser) HubEmerald else HubGold,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth().testTag("pull_master_feeds_button")
                ) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Pull Live Feeds & Sync Push Alerts", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Auto-Pull Frequency Selection
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Auto-Pull Interval:",
                        style = MaterialTheme.typography.labelSmall,
                        color = HubTextDim,
                        fontSize = 10.sp
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        listOf(1, 5, 15).forEach { interval ->
                            val isSelected = pullIntervalMinutes == interval
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (isSelected) (if (isProUser) HubEmerald else HubGold) else HubInnerWell)
                                    .clickable { ProSubscriptionManager.setPullInterval(interval) }
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Text(
                                    text = "$interval Min",
                                    color = if (isSelected) Color.Black else HubTextSecondary,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }

        // Test Push Triggers Grid
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, HubBorder, RoundedCornerShape(14.dp)),
            colors = CardDefaults.cardColors(containerColor = HubCardElevated),
            shape = RoundedCornerShape(14.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "DISPATCH TEST PUSH NOTIFICATIONS",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubGold,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Fire instant heads-up notification payloads to the Android system drawer:",
                    style = MaterialTheme.typography.bodyMedium,
                    color = HubTextDim,
                    fontSize = 11.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            UtilityRepository.dispatchSimulatedPushAlert(AlertCategory.GOLD_BULLION)
                            NotificationHelper.sendSystemNotification(
                                context,
                                notificationId = (1000..9999).random(),
                                title = "✨ Gold 24K Surge Notice",
                                message = "All-Pakistan Sarafa: 24K Tola surged by +Rs. 1,800. Current: Rs. 272,400.",
                                channelId = PakUtilityApp.CHANNEL_MARKET
                            )
                        },
                        modifier = Modifier.weight(1f).testTag("trigger_gold_push_button"),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("✨ Gold Surge", fontSize = 11.sp, color = HubGold)
                    }

                    OutlinedButton(
                        onClick = {
                            UtilityRepository.dispatchSimulatedPushAlert(AlertCategory.FUEL_LOG)
                            NotificationHelper.sendSystemNotification(
                                context,
                                notificationId = (1000..9999).random(),
                                title = "⛽ OGRA Fuel Revision",
                                message = "OGRA petroleum update: Petrol @ Rs. 279.75, HSD @ Rs. 284.20/L.",
                                channelId = PakUtilityApp.CHANNEL_MARKET
                            )
                        },
                        modifier = Modifier.weight(1f).testTag("trigger_fuel_push_button"),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("⛽ Fuel Alert", fontSize = 11.sp, color = HubSky)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            UtilityRepository.dispatchSimulatedPushAlert(AlertCategory.MANDI_PRICE)
                            NotificationHelper.sendSystemNotification(
                                context,
                                notificationId = (1000..9999).random(),
                                title = "🥬 Mandi Auction Drop",
                                message = "Badami Bagh mandi: Tomato & Potato rates dropped -10% on fresh arrivals.",
                                channelId = PakUtilityApp.CHANNEL_MARKET
                            )
                        },
                        modifier = Modifier.weight(1f).testTag("trigger_mandi_push_button"),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("🥬 Mandi Drop", fontSize = 11.sp, color = HubEmerald)
                    }

                    OutlinedButton(
                        onClick = {
                            isPushComposerVisible = !isPushComposerVisible
                        },
                        modifier = Modifier.weight(1f).testTag("custom_composer_button"),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(if (isPushComposerVisible) "Close Composer" else "Custom Push ✍️", fontSize = 11.sp, color = HubTextPrimary)
                    }
                }

                // Custom Push Composer
                if (isPushComposerVisible) {
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = customAlertTitle,
                        onValueChange = { customAlertTitle = it },
                        label = { Text("Push Title") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = customAlertMessage,
                        onValueChange = { customAlertMessage = it },
                        label = { Text("Message Payload") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            if (customAlertTitle.isNotBlank()) {
                                val item = NotificationItem(
                                    title = customAlertTitle,
                                    message = customAlertMessage.ifBlank { "Custom push alert notification received." },
                                    category = AlertCategory.PUSH_EVENT,
                                    severity = AlertSeverity.INFO
                                )
                                LocationRepository.addNotification(item)
                                NotificationHelper.sendSystemNotification(
                                    context,
                                    notificationId = (1000..9999).random(),
                                    title = item.title,
                                    message = item.message,
                                    channelId = PakUtilityApp.CHANNEL_ALERTS
                                )
                                customAlertTitle = ""
                                customAlertMessage = ""
                                isPushComposerVisible = false
                                Toast.makeText(context, "Push notification sent to drawer!", Toast.LENGTH_SHORT).show()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = HubGold, contentColor = Color.Black),
                        modifier = Modifier.fillMaxWidth().testTag("send_custom_push_button")
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.Send, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Dispatch to Device")
                    }
                }
            }
        }

        // Filter Chips and Bulk Actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "NOTIFICATION INBOX (${filteredList.size})",
                style = MaterialTheme.typography.labelSmall,
                color = HubTextSecondary,
                fontWeight = FontWeight.Bold
            )

            Row {
                TextButton(
                    onClick = { LocationRepository.markAllNotificationsRead() },
                    modifier = Modifier.testTag("mark_read_button")
                ) {
                    Icon(imageVector = Icons.Default.DoneAll, contentDescription = null, tint = HubSky, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Read All", color = HubSky, fontSize = 11.sp)
                }
                TextButton(
                    onClick = { LocationRepository.clearNotifications() },
                    modifier = Modifier.testTag("clear_all_button")
                ) {
                    Icon(imageVector = Icons.Default.ClearAll, contentDescription = null, tint = HubTextDim, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Clear", color = HubTextDim, fontSize = 11.sp)
                }
            }
        }

        // Category Filter Chips
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            FilterChip(
                selected = selectedFilter == null,
                onClick = { selectedFilter = null },
                label = { Text("All (${notifications.size})", fontSize = 10.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = HubGold,
                    selectedLabelColor = Color.Black
                )
            )
            FilterChip(
                selected = selectedFilter == AlertCategory.GEOLOCATION,
                onClick = { selectedFilter = AlertCategory.GEOLOCATION },
                label = { Text("Geo", fontSize = 10.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = HubGold,
                    selectedLabelColor = Color.Black
                )
            )
            FilterChip(
                selected = selectedFilter == AlertCategory.GOLD_BULLION,
                onClick = { selectedFilter = AlertCategory.GOLD_BULLION },
                label = { Text("Gold", fontSize = 10.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = HubGold,
                    selectedLabelColor = Color.Black
                )
            )
            FilterChip(
                selected = selectedFilter == AlertCategory.FUEL_LOG,
                onClick = { selectedFilter = AlertCategory.FUEL_LOG },
                label = { Text("Fuel", fontSize = 10.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = HubGold,
                    selectedLabelColor = Color.Black
                )
            )
        }

        // Notification List
        if (filteredList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = HubTextDim,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("No alerts in this category", color = HubTextDim, fontSize = 12.sp)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredList, key = { it.id }) { item ->
                    NotificationCard(item = item)
                }
            }
        }
    }
}

@Composable
fun NotificationCard(item: NotificationItem) {
    val severityColor = when (item.severity) {
        AlertSeverity.CRITICAL -> HubRose
        AlertSeverity.WARNING -> HubGold
        AlertSeverity.SUCCESS -> HubEmerald
        AlertSeverity.INFO -> HubSky
    }

    val icon = when (item.category) {
        AlertCategory.GEOLOCATION -> Icons.Default.LocationOn
        AlertCategory.GOLD_BULLION -> Icons.Default.Bolt
        AlertCategory.FUEL_LOG -> Icons.Default.LocalGasStation
        AlertCategory.PUSH_EVENT -> Icons.Default.NotificationsActive
        else -> Icons.Default.Info
    }

    val timeString = remember(item.timestampMillis) {
        SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date(item.timestampMillis))
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, if (!item.isRead) severityColor.copy(alpha = 0.5f) else HubBorder, RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(containerColor = HubCard),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(severityColor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = severityColor,
                    modifier = Modifier.size(16.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = HubTextPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = timeString,
                        style = MaterialTheme.typography.labelSmall,
                        color = HubTextDim,
                        fontSize = 9.sp
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = item.message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = HubTextSecondary,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(HubInnerWell)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = item.category.name,
                            style = MaterialTheme.typography.labelSmall,
                            color = severityColor,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
