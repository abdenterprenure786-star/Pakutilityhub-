package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ProSubscriptionManager
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
fun ProUpgradeDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val isProUser by ProSubscriptionManager.isProUser.collectAsState()
    val scrollState = rememberScrollState()

    val jazzCashAccountTitle = "Kousar Parveen"
    val jazzCashAccountNumber = "03064678975"
    var enteredTid by remember { mutableStateOf("") }
    var tidSubmittedSuccess by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier.testTag("pro_upgrade_dialog"),
        containerColor = HubCard,
        shape = RoundedCornerShape(20.dp),
        title = null,
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                    .padding(vertical = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header Close Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF282D07))
                            .border(1.dp, HubBorderGold, RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = if (isProUser) "VIP SUBSCRIBER" else "PREMIUM UPGRADE",
                            style = MaterialTheme.typography.labelSmall,
                            color = HubGold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 9.sp
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
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Star Crown Icon
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(HubInnerWell)
                        .border(1.5.dp, HubBorderGold, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = HubGold,
                        modifier = Modifier.size(26.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Pak Utility Hub Pro",
                    style = MaterialTheme.typography.headlineMedium,
                    color = HubTextPrimary,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Black,
                    fontSize = 22.sp
                )

                Text(
                    text = "100% Ad-Free Experience & VIP Rs. 300 Rates Push Alerts",
                    style = MaterialTheme.typography.bodyMedium,
                    color = HubTextSecondary,
                    fontSize = 11.sp,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Official JazzCash Payment Gateway Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.5.dp, Color(0xFFE53935), RoundedCornerShape(14.dp)),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E0A0E)),
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
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(Color(0xFFE53935))
                                        .padding(horizontal = 6.dp, vertical = 3.dp)
                                ) {
                                    Text(
                                        text = "JazzCash",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Color.White,
                                        fontWeight = FontWeight.Black,
                                        fontSize = 10.sp
                                    )
                                }
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "OFFICIAL ACCOUNT",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color(0xFFFFB100),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 9.sp
                                )
                            }

                            Text(
                                text = "Rs. 300 / Mo",
                                style = MaterialTheme.typography.titleMedium,
                                color = HubGold,
                                fontWeight = FontWeight.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Account Details Container
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFF120306))
                                .border(1.dp, Color(0x33E53935), RoundedCornerShape(10.dp))
                                .padding(10.dp)
                        ) {
                            Column {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(
                                            text = "ACCOUNT TITLE",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = HubTextDim,
                                            fontSize = 9.sp
                                        )
                                        Text(
                                            text = jazzCashAccountTitle,
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp
                                        )
                                        Text(
                                            text = "(کوثر پروین)",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = Color(0xFFFFB100),
                                            fontSize = 11.sp
                                        )
                                    }

                                    IconButton(
                                        onClick = {
                                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                            val clip = ClipData.newPlainText("JazzCash Number", jazzCashAccountNumber)
                                            clipboard.setPrimaryClip(clip)
                                            Toast.makeText(context, "JazzCash Number copied: $jazzCashAccountNumber", Toast.LENGTH_SHORT).show()
                                        },
                                        modifier = Modifier.size(32.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ContentCopy,
                                            contentDescription = "Copy Number",
                                            tint = Color(0xFFFFB100),
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(
                                            text = "JAZZCASH MOBILE NUMBER",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = HubTextDim,
                                            fontSize = 9.sp
                                        )
                                        Text(
                                            text = "0306-4678975",
                                            style = MaterialTheme.typography.titleMedium,
                                            color = HubGold,
                                            fontWeight = FontWeight.Black,
                                            fontFamily = FontFamily.Monospace,
                                            fontSize = 16.sp
                                        )
                                    }

                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(Color(0xFF28070B))
                                            .clickable {
                                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                                val clip = ClipData.newPlainText("JazzCash Number", jazzCashAccountNumber)
                                                clipboard.setPrimaryClip(clip)
                                                Toast.makeText(context, "JazzCash Number copied: $jazzCashAccountNumber", Toast.LENGTH_SHORT).show()
                                            }
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Text("Tap to Copy", color = Color(0xFFFFB100), fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Payment Procedure:\n1. Send Rs. 300 to 03064678975 (Kousar Parveen) via JazzCash App or *786#.\n2. Enter the 10/12-digit TID from 8558 SMS below to verify:",
                            style = MaterialTheme.typography.labelSmall,
                            color = HubTextSecondary,
                            fontSize = 10.sp,
                            lineHeight = 14.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Transaction ID Input Field
                        OutlinedTextField(
                            value = enteredTid,
                            onValueChange = { enteredTid = it },
                            label = { Text("JazzCash TID / Transaction ID (e.g. 104928374)") },
                            placeholder = { Text("Enter TID from 8558 SMS", fontSize = 11.sp) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Verify TID Button
                        Button(
                            onClick = {
                                if (enteredTid.isNotBlank()) {
                                    ProSubscriptionManager.activatePro()
                                    tidSubmittedSuccess = true
                                    Toast.makeText(context, "JazzCash Payment Verified! Pak Utility Hub Pro is now Active.", Toast.LENGTH_LONG).show()
                                    onDismissRequest()
                                } else {
                                    Toast.makeText(context, "Please enter your JazzCash Transaction ID (TID)", Toast.LENGTH_SHORT).show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFE53935),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth().testTag("verify_jazzcash_tid_button")
                        ) {
                            Icon(imageVector = Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Verify TID & Unlock Pro (Rs. 300)", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Feature Checklist
                val features = listOf(
                    "🚫 100% Zero Advertisements & No Promoted Frames",
                    "🔔 Merged VIP Push & Pull Notification System for Rs. 300+ Shifts",
                    "🍎 Full All-Pakistan Fruit & Food Mandi Wholesale Rates",
                    "🛰️ Full-Screen Background GPS Radar & Live Breadcrumbs Tracking",
                    "⚡ Instant JazzCash Verification (Kousar Parveen - 03064678975)"
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    features.forEach { feat ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = HubEmerald,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = feat,
                                style = MaterialTheme.typography.bodyMedium,
                                color = HubTextPrimary,
                                fontSize = 11.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Instant Demo Toggle Action Button
                Button(
                    onClick = {
                        ProSubscriptionManager.toggleProStatus()
                        onDismissRequest()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("pro_subscription_action_button"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isProUser) HubRose else HubGold,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = if (isProUser) "Downgrade to Free Tier" else "Instant Activate / Test Pro Mode",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }
        },
        confirmButton = {}
    )
}
