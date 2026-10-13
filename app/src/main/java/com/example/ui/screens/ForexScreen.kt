package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.UtilityRepository
import com.example.ui.theme.HubBorder
import com.example.ui.theme.HubBorderGold
import com.example.ui.theme.HubCard
import com.example.ui.theme.HubCardElevated
import com.example.ui.theme.HubEmerald
import com.example.ui.theme.HubGold
import com.example.ui.theme.HubRose
import com.example.ui.theme.HubSky
import com.example.ui.theme.HubTextDim
import com.example.ui.theme.HubTextPrimary
import com.example.ui.theme.HubTextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForexScreen(
    modifier: Modifier = Modifier
) {
    val forexRates by UtilityRepository.forexRates.collectAsState()

    var exchangeAmountInput by remember { mutableStateOf("100") }
    var selectedCurrencyCode by remember { mutableStateOf("USD") }
    var isCurrencyDropdownOpen by remember { mutableStateOf(false) }

    val selectedRate = forexRates.find { it.code == selectedCurrencyCode } ?: forexRates.first()
    val parsedAmount = exchangeAmountInput.toDoubleOrNull() ?: 100.0
    val interbankTotal = parsedAmount * selectedRate.interbankSell
    val openMarketTotal = parsedAmount * selectedRate.openMarketSell

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
            .testTag("forex_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Forex Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "FOREX CURRENCY EXCHANGE HUB",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubGold,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "State Bank of Pakistan (SBP) & Open Market Rates",
                    style = MaterialTheme.typography.bodyMedium,
                    color = HubTextSecondary,
                    fontSize = 11.sp
                )
            }
            Text(
                text = "INTERBANK LIVE",
                style = MaterialTheme.typography.labelSmall,
                color = HubSky,
                fontWeight = FontWeight.Bold
            )
        }

        // Currency Exchange Converter Card
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
                        imageVector = Icons.Default.CurrencyExchange,
                        contentDescription = null,
                        tint = HubGold,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "INSTANT FOREX CONVERTER",
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
                        value = exchangeAmountInput,
                        onValueChange = { exchangeAmountInput = it },
                        label = { Text("Amount") },
                        modifier = Modifier.weight(1f),
                        singleLine = true
                    )

                    ExposedDropdownMenuBox(
                        expanded = isCurrencyDropdownOpen,
                        onExpandedChange = { isCurrencyDropdownOpen = !isCurrencyDropdownOpen },
                        modifier = Modifier.weight(1f)
                    ) {
                        OutlinedTextField(
                            value = "${selectedRate.flag} ${selectedRate.code}",
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Currency") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isCurrencyDropdownOpen) },
                            modifier = Modifier.menuAnchor()
                        )
                        ExposedDropdownMenu(
                            expanded = isCurrencyDropdownOpen,
                            onDismissRequest = { isCurrencyDropdownOpen = false }
                        ) {
                            forexRates.forEach { rate ->
                                DropdownMenuItem(
                                    text = { Text("${rate.flag} ${rate.code} (${rate.name})") },
                                    onClick = {
                                        selectedCurrencyCode = rate.code
                                        isCurrencyDropdownOpen = false
                                    }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Prominent Forex Conversion Result Box
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
                                    text = "OPEN MARKET VALUE (PKR)",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = HubGold,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                )
                                Text(
                                    text = "اوپن مارکیٹ تبادلہ رقم (روپے)",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = HubTextDim,
                                    fontSize = 9.sp
                                )
                            }

                            Text(
                                text = "Rs. ${String.format("%,.0f", openMarketTotal)}",
                                style = MaterialTheme.typography.headlineMedium,
                                color = HubGold,
                                fontWeight = FontWeight.Black,
                                fontSize = 24.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text("Interbank Value (SBP)", style = MaterialTheme.typography.labelSmall, color = HubTextDim, fontSize = 9.sp)
                                Text(
                                    "Rs. ${String.format("%,.2f", interbankTotal)}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = HubSky,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Column(horizontalAlignment = Alignment.End) {
                                Text("Market Spread Difference", style = MaterialTheme.typography.labelSmall, color = HubTextDim, fontSize = 9.sp)
                                val spread = (openMarketTotal - interbankTotal).coerceAtLeast(0.0)
                                Text(
                                    "+Rs. ${String.format("%,.2f", spread)}",
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

        // Currency Exchange Rates Table
        Text(
            text = "PARITY EXCHANGE RATES (TO PKR)",
            style = MaterialTheme.typography.labelSmall,
            color = HubTextSecondary,
            fontWeight = FontWeight.Bold
        )

        forexRates.forEach { rate ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, HubBorder, RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = HubCard),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = rate.flag, fontSize = 24.sp)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "${rate.code} / PKR",
                                style = MaterialTheme.typography.bodyLarge,
                                color = HubTextPrimary,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = rate.name,
                                style = MaterialTheme.typography.labelSmall,
                                color = HubTextDim,
                                fontSize = 10.sp
                            )
                        }
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        Column(horizontalAlignment = Alignment.End) {
                            Text("Interbank", style = MaterialTheme.typography.labelSmall, color = HubTextDim, fontSize = 9.sp)
                            Text(
                                text = String.format("%.2f", rate.interbankSell),
                                style = MaterialTheme.typography.bodyLarge,
                                color = HubSky,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("Open Mkt", style = MaterialTheme.typography.labelSmall, color = HubTextDim, fontSize = 9.sp)
                            Text(
                                text = String.format("%.2f", rate.openMarketSell),
                                style = MaterialTheme.typography.bodyLarge,
                                color = HubGold,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
