package com.example.ui.components

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.FullscreenExit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ProSubscriptionManager
import com.example.data.UtilityRepository
import com.example.ui.theme.HubBackground
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
fun HubHeader(
    unreadNotificationCount: Int,
    isTrackingActive: Boolean,
    onNotificationClick: () -> Unit,
    onNavigationClick: () -> Unit,
    isFullScreen: Boolean = false,
    onToggleFullScreen: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val syncMetadata by UtilityRepository.syncMetadata.collectAsState()
    val isProUser by ProSubscriptionManager.isProUser.collectAsState()
    var showProDialog by remember { mutableStateOf(false) }

    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val alphaAnim by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alphaAnim"
    )

    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(HubBackground)
            .padding(horizontal = 14.dp, vertical = 6.dp)
            .testTag("hub_header")
    ) {
        // Row 1: Compact Full-Screen Top Bar (Navigation, Brand Title, Pro Badge, Fullscreen, Expand Toggle, Bell)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Navigation Panel Outline Button
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, HubBorder, RoundedCornerShape(8.dp))
                    .clickable { onNavigationClick() }
                    .padding(horizontal = 8.dp, vertical = 5.dp)
                    .testTag("navigation_panel_button"),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                        tint = HubGold,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Navigation Panel",
                        style = MaterialTheme.typography.labelSmall,
                        color = HubTextPrimary,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp
                    )
                }
            }

            // Center Brand Title (Tap to toggle full overview) with All-Rates Logo
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { isExpanded = !isExpanded }
                    .padding(horizontal = 6.dp, vertical = 3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(RoundedCornerShape(7.dp))
                        .border(1.2.dp, HubBorderGold, RoundedCornerShape(7.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    androidx.compose.foundation.Image(
                        painter = androidx.compose.ui.res.painterResource(id = com.example.R.drawable.pak_rates_logo),
                        contentDescription = "PakUtility Rates Logo",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "PakUtility",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Black,
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(HubGold)
                                .padding(horizontal = 4.dp, vertical = 1.dp)
                        ) {
                            Text(
                                text = "HUB",
                                color = Color.Black,
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Black
                            )
                        }
                    }
                    Text(
                        text = "تمام چیزوں کے لائیو ریٹس",
                        style = MaterialTheme.typography.labelSmall,
                        color = HubGold,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                // Pro Badge / Action Button
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isProUser) Color(0xFF132A15) else Color(0xFF282D07))
                        .border(1.dp, if (isProUser) HubEmerald else HubBorderGold, RoundedCornerShape(8.dp))
                        .clickable { showProDialog = true }
                        .padding(horizontal = 8.dp, vertical = 5.dp)
                        .testTag("pro_badge_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = if (isProUser) HubEmerald else HubGold,
                            modifier = Modifier.size(11.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = if (isProUser) "PRO VIP" else "PRO (Rs.300)",
                            style = MaterialTheme.typography.labelSmall,
                            color = if (isProUser) HubEmerald else HubGold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 9.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.width(3.dp))

                // Full Screen Toggle Button (Allows keeping review in 100% full screen)
                IconButton(
                    onClick = onToggleFullScreen,
                    modifier = Modifier.size(30.dp).testTag("toggle_fullscreen_button")
                ) {
                    Icon(
                        imageVector = if (isFullScreen) Icons.Default.FullscreenExit else Icons.Default.Fullscreen,
                        contentDescription = if (isFullScreen) "Exit Full Screen" else "Full Screen Mode",
                        tint = HubSky,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Spacer(modifier = Modifier.width(2.dp))

                // Toggle Full Overview Expand/Collapse
                IconButton(
                    onClick = { isExpanded = !isExpanded },
                    modifier = Modifier.size(30.dp).testTag("toggle_overview_button")
                ) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = if (isExpanded) "Collapse Overview" else "Expand Overview",
                        tint = HubGold,
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Notification Bell with Badge
                IconButton(
                    onClick = onNotificationClick,
                    modifier = Modifier.size(30.dp).testTag("notification_bell_button")
                ) {
                    BadgedBox(
                        badge = {
                            if (unreadNotificationCount > 0) {
                                Badge(
                                    containerColor = HubRose,
                                    contentColor = Color.White
                                ) {
                                    Text(
                                        text = if (unreadNotificationCount > 9) "9+" else "$unreadNotificationCount",
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Alerts",
                            tint = if (unreadNotificationCount > 0) HubGold else HubTextSecondary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }

        // Expandable Overview Section (Provides full-screen real estate when collapsed)
        AnimatedVisibility(visible = isExpanded) {
            Column(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {

        // Row 2: Live Alert Ticker (Matching the "THREAT FEED" ticker from photo)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, HubBorder, RoundedCornerShape(10.dp)),
            colors = CardDefaults.cardColors(containerColor = HubCard),
            shape = RoundedCornerShape(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF3B1219))
                        .border(1.dp, Color(0xFF6B212E), RoundedCornerShape(6.dp))
                        .padding(horizontal = 6.dp, vertical = 3.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Bolt,
                            contentDescription = null,
                            tint = HubRose,
                            modifier = Modifier.size(10.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "LIVE FEED",
                            style = MaterialTheme.typography.labelSmall,
                            color = HubRose,
                            fontWeight = FontWeight.Black,
                            fontSize = 8.sp,
                            letterSpacing = 0.5.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = HubGold,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Alert: 24K Gold Rs. 432.5K/tola • Petrol Super Rs. 392.66/L • Diesel Rs. 396.82/L",
                        style = MaterialTheme.typography.bodyMedium,
                        color = HubTextSecondary,
                        fontSize = 11.sp,
                        maxLines = 1
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Row 3: Distinctive Status Pill (Matching "SYSTEM STATUS: SECURE | 1,476 nodes online" from photo)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(HubInnerWell)
                .border(1.dp, Color(0x33334155), RoundedCornerShape(20.dp))
                .padding(horizontal = 14.dp, vertical = 7.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(7.dp)
                        .clip(CircleShape)
                        .background(HubEmerald)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "SYSTEM STATUS:",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubTextDim,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 10.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "SECURE",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubGold,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 10.sp
                )

                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "|",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "1,476 nodes online",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubTextSecondary,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 10.sp
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    imageVector = Icons.Default.Shield,
                    contentDescription = null,
                    tint = HubEmerald,
                    modifier = Modifier.size(12.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Row 4: Golden Sub-Heading, Serif Brand Heading, and Narrative (Matching the photo typography)
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "PAKISTAN'S PREMIUM VERIFICATION & UTILITY DASHBOARD",
                style = MaterialTheme.typography.labelSmall,
                color = HubGold,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                fontSize = 9.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "PakUtility ",
                    style = MaterialTheme.typography.displayLarge,
                    color = Color.White,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Black,
                    fontSize = 38.sp
                )
                Text(
                    text = "Hub",
                    style = MaterialTheme.typography.displayLarge,
                    color = HubGold,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Black,
                    fontSize = 38.sp
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Track real-time GPS telemetry, monitor live bullion, fuel, forex, and comprehensive mandi fruits & food rates with instant push alerts — all from one elegant, secure platform.",
                style = MaterialTheme.typography.bodyMedium,
                color = HubTextSecondary,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                lineHeight = 17.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Row 5: 3-Column Key Metrics Counters (Matching the 48K+, 2,340, 0.3S counters in the photo)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "48K+",
                    style = MaterialTheme.typography.titleLarge,
                    color = HubGold,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Black,
                    fontSize = 24.sp
                )
                Text(
                    text = "Rates Verified",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubTextDim,
                    fontSize = 9.sp
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "2,340",
                    style = MaterialTheme.typography.titleLarge,
                    color = HubGold,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Black,
                    fontSize = 24.sp
                )
                Text(
                    text = "Mandi Nodes",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubTextDim,
                    fontSize = 9.sp
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "0.3s",
                    style = MaterialTheme.typography.titleLarge,
                    color = HubGold,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Black,
                    fontSize = 24.sp
                )
                Text(
                    text = "Avg Telemetry",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubTextDim,
                    fontSize = 9.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Row 6: Sponsored Google Ad Banner (ONLY visible if NOT Pro User!)
        if (!isProUser) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF090D16))
                    .border(1.dp, Color(0x33475569), RoundedCornerShape(10.dp))
                    .clickable { showProDialog = true }
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .testTag("sponsored_ad_container"),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color(0xFF1E293B))
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "AD",
                                style = MaterialTheme.typography.labelSmall,
                                color = HubTextDim,
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Google AdSense Sponsored Partner",
                                style = MaterialTheme.typography.bodyMedium,
                                color = HubTextPrimary,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1
                            )
                            Text(
                                text = "Tap here to Upgrade to Pro (Rs. 300) to remove all ads",
                                style = MaterialTheme.typography.labelSmall,
                                color = HubGold,
                                fontSize = 9.sp,
                                maxLines = 1
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = "Remove ✕",
                        style = MaterialTheme.typography.labelSmall,
                        color = HubTextDim,
                        fontSize = 10.sp,
                        maxLines = 1,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        } else {
            // PRO User Active Banner (100% Ad-Free confirmed)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF0C2413))
                    .border(1.dp, Color(0xFF165328), RoundedCornerShape(10.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
                    .testTag("pro_active_banner"),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Shield,
                        contentDescription = null,
                        tint = HubEmerald,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "⭐ PRO VIP ACTIVE — 100% AD-FREE & RS. 300 VIP RATE ALERTS ENABLED",
                        style = MaterialTheme.typography.labelSmall,
                        color = HubEmerald,
                        fontWeight = FontWeight.Bold,
                        fontSize = 9.sp
                    )
                }
            }
        }
            }
        }
    }

    if (showProDialog) {
        ProUpgradeDialog(
            onDismissRequest = { showProDialog = false }
        )
    }
}
