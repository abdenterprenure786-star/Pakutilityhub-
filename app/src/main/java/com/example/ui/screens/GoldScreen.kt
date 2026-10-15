package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.ui.draw.clip
import com.example.ui.theme.HubInnerWell
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
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
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
fun GoldScreen(
    modifier: Modifier = Modifier
) {
    val goldRates by UtilityRepository.goldRates.collectAsState()

    var massQtyInput by remember { mutableStateOf("1.0") }
    var massUnit by remember { mutableStateOf("TOLA") }
    var selectedCarat by remember { mutableStateOf("24K") }
    var isUnitDropdownExpanded by remember { mutableStateOf(false) }

    // Zakat inputs
    var zakatGoldGramsInput by remember { mutableStateOf("0.0") }
    var zakatSilverGramsInput by remember { mutableStateOf("0.0") }
    var zakatCashInput by remember { mutableStateOf("500000") }

    // Gold Buy / Sell Profit & Commission Simulator State
    var tradeMode by remember { mutableStateOf("SIMULATE") } // SIMULATE, BUY, SELL
    var tradeKarat by remember { mutableStateOf("24K") }
    var tradeQtyInput by remember { mutableStateOf("1.0") }
    var tradeBuyRateInput by remember { mutableStateOf("434200") }
    var tradeSellRateInput by remember { mutableStateOf("437836") }
    var tradeCommissionPercent by remember { mutableDoubleStateOf(1.0) } // 1.0% Sarafa dealer standard

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
            .testTag("gold_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Prominent Gold Trend Banner (Price Increasing / Decreasing)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF132A15))
                .border(1.5.dp, HubEmerald, RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(HubEmerald.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowUpward,
                        contentDescription = "Price Up",
                        tint = HubEmerald,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "📈 GOLD TREND: PRICES ARE INCREASING (قیمت میں اضافہ ہو رہا ہے)",
                        style = MaterialTheme.typography.labelSmall,
                        color = HubEmerald,
                        fontWeight = FontWeight.Black,
                        fontSize = 11.sp
                    )
                    Text(
                        text = "24K Gold is UP +Rs. 1,500/tola today in Sarafa Market. Bullion benchmark: Rs. 432,500/tola.",
                        style = MaterialTheme.typography.bodySmall,
                        color = HubTextSecondary,
                        fontSize = 10.sp
                    )
                }
            }
        }

        // Bullion Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "BULLION RATES (PER TOLA PK)",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubGold,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = goldRates.firstOrNull()?.source ?: "All-Pakistan Gems & Jewellers Association",
                    style = MaterialTheme.typography.bodyMedium,
                    color = HubTextSecondary,
                    fontSize = 11.sp
                )
            }
            Box(
                modifier = Modifier
                    .border(1.dp, HubEmerald, RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "BULLISH SURGE (+Rs. 1,500)",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubEmerald,
                    fontWeight = FontWeight.Bold,
                    fontSize = 9.sp
                )
            }
        }

        // 4 Carat Rates 2x2 Grid
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                goldRates.take(2).forEach { item ->
                    GoldRateCard(item = item, modifier = Modifier.weight(1f))
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                goldRates.drop(2).take(2).forEach { item ->
                    GoldRateCard(item = item, modifier = Modifier.weight(1f))
                }
            }
        }

        // Weight Volumetric Calculator
        val parsedQty = massQtyInput.toDoubleOrNull() ?: 1.0
        val conversion = remember(parsedQty, massUnit, goldRates) {
            UtilityRepository.convertGoldMass(parsedQty, massUnit)
        }

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
                        imageVector = Icons.Default.Calculate,
                        contentDescription = null,
                        tint = HubGold,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "WEIGHT VOLUMETRIC CALCULATOR",
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
                        value = massQtyInput,
                        onValueChange = { massQtyInput = it },
                        label = { Text("Mass Quantity") },
                        modifier = Modifier.weight(1f),
                        singleLine = true
                    )

                    ExposedDropdownMenuBox(
                        expanded = isUnitDropdownExpanded,
                        onExpandedChange = { isUnitDropdownExpanded = !isUnitDropdownExpanded },
                        modifier = Modifier.weight(1f)
                    ) {
                        OutlinedTextField(
                            value = massUnit,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Unit") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isUnitDropdownExpanded) },
                            modifier = Modifier.menuAnchor()
                        )
                        ExposedDropdownMenu(
                            expanded = isUnitDropdownExpanded,
                            onDismissRequest = { isUnitDropdownExpanded = false }
                        ) {
                            listOf("TOLA", "GRAM", "MASHA", "RATTI").forEach { unit ->
                                DropdownMenuItem(
                                    text = { Text(unit) },
                                    onClick = {
                                        massUnit = unit
                                        isUnitDropdownExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Carat Selector (18K, 20K, 22K, 24K)
                Text(
                    text = "SELECT GOLD CARAT (قیراط منتخب کریں):",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubTextSecondary,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    listOf("24K", "22K", "20K", "18K").forEach { carat ->
                        val isSelected = selectedCarat == carat
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isSelected) HubGold else HubInnerWell)
                                .border(1.dp, if (isSelected) HubGold else HubBorder, RoundedCornerShape(8.dp))
                                .clickable { selectedCarat = carat }
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = carat,
                                style = MaterialTheme.typography.labelSmall,
                                color = if (isSelected) Color.Black else HubTextPrimary,
                                fontWeight = FontWeight.Black,
                                fontSize = 11.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                val selectedCaratPrice = when (selectedCarat) {
                    "24K" -> (conversion.tola * 432500).toLong()
                    "22K" -> (conversion.tola * 396458).toLong()
                    "20K" -> (conversion.tola * 360417).toLong()
                    else -> (conversion.tola * 324375).toLong()
                }

                // Prominent Calculation Result Display Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF07120B))
                        .border(1.dp, Color(0xFF10B981).copy(alpha = 0.4f), RoundedCornerShape(10.dp))
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
                                    text = "ESTIMATED $selectedCarat VALUE (${selectedCarat} سونا کی قیمت)",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = HubEmerald,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                )
                                Text(
                                    text = "${String.format("%.3f", conversion.tola)} Tola @ $selectedCarat rate",
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
                                        .clickable { massQtyInput = "" }
                                        .padding(horizontal = 6.dp, vertical = 3.dp)
                                ) {
                                    Text("Clear", color = Color(0xFFF43F5E), fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                }
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Rs. ${String.format("%,d", selectedCaratPrice)}",
                                    style = MaterialTheme.typography.headlineSmall,
                                    color = HubGold,
                                    fontWeight = FontWeight.Black,
                                    fontSize = 22.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text("Equivalent Tolas", style = MaterialTheme.typography.labelSmall, color = HubTextDim, fontSize = 9.sp)
                                Text("${String.format("%.3f", conversion.tola)} Tola", style = MaterialTheme.typography.bodyLarge, color = HubTextPrimary, fontWeight = FontWeight.Bold)
                            }
                            Column {
                                Text("Weight in Grams", style = MaterialTheme.typography.labelSmall, color = HubTextDim, fontSize = 9.sp)
                                Text("${String.format("%.3f", conversion.grams)} g", style = MaterialTheme.typography.bodyLarge, color = HubTextPrimary, fontWeight = FontWeight.Bold)
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text("22K Est. Value", style = MaterialTheme.typography.labelSmall, color = HubTextDim, fontSize = 9.sp)
                                val val22k = (conversion.value24kPkr * (22.0 / 24.0)).toLong()
                                Text("Rs. ${String.format("%,d", val22k)}", style = MaterialTheme.typography.bodyLarge, color = HubEmerald, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        // -------------------------------------------------------------
        // GOLD BUY / SELL PROFIT & COMMISSION SIMULATOR CARD
        // -------------------------------------------------------------
        val parsedTradeQty = tradeQtyInput.toDoubleOrNull() ?: 1.0
        val parsedTradeBuy = tradeBuyRateInput.toDoubleOrNull() ?: 434200.0
        val parsedTradeSell = tradeSellRateInput.toDoubleOrNull() ?: 437836.0
        val totalTradeBuy = parsedTradeQty * parsedTradeBuy
        val totalTradeSell = parsedTradeQty * parsedTradeSell
        val tradeGrossSpread = totalTradeSell - totalTradeBuy
        val tradeBase = if (tradeMode == "BUY") totalTradeBuy else totalTradeSell
        val tradeCommissionPkr = (tradeCommissionPercent / 100.0) * tradeBase
        val tradeNetProfit = tradeGrossSpread - tradeCommissionPkr
        val tradeRoi = if (totalTradeBuy > 0) (tradeNetProfit / totalTradeBuy) * 100 else 0.0
        val tradeBreakEven = parsedTradeBuy + (tradeCommissionPkr / maxOf(0.001, parsedTradeQty))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.5.dp, HubBorderGold, RoundedCornerShape(14.dp)),
            colors = CardDefaults.cardColors(containerColor = HubCardElevated),
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
                            imageVector = Icons.Default.MonetizationOn,
                            contentDescription = null,
                            tint = HubGold,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "GOLD BUY / SELL PROFIT & COMMISSION",
                            style = MaterialTheme.typography.labelSmall,
                            color = HubGold,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF282D07))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "صرافہ ٹریڈ و کمیشن",
                            style = MaterialTheme.typography.labelSmall,
                            color = HubGold,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Buy / Sell Mode Toggle Pills
                Text("TRANSACTION MODE (خرید یا فروخت منتخب کریں):", style = MaterialTheme.typography.labelSmall, color = HubTextSecondary, fontSize = 10.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    listOf("SIMULATE" to "💰 Full Profit", "BUY" to "🟢 Buy Order", "SELL" to "🔴 Sell Order").forEach { (mode, label) ->
                        val isSelected = tradeMode == mode
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isSelected) (if (mode == "BUY") HubSky else HubGold) else HubInnerWell)
                                .border(1.dp, if (isSelected) (if (mode == "BUY") HubSky else HubGold) else HubBorder, RoundedCornerShape(8.dp))
                                .clickable { tradeMode = mode }
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = label,
                                color = if (isSelected) Color.Black else HubTextPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Karat Chips Row
                Text("SELECT PURITY (قیراط منتخب کریں):", style = MaterialTheme.typography.labelSmall, color = HubTextDim, fontSize = 9.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    listOf("24K" to 434200.0 to 437836.0, "22K" to 393000.0 to 396458.0, "21K" to 374500.0 to 378000.0, "18K" to 321000.0 to 324000.0).forEach { item ->
                        val karat = item.first.first
                        val buyR = item.first.second
                        val sellR = item.second
                        val isSelected = tradeKarat == karat
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isSelected) HubGold else HubInnerWell)
                                .border(1.dp, if (isSelected) HubGold else HubBorder, RoundedCornerShape(6.dp))
                                .clickable {
                                    tradeKarat = karat
                                    tradeBuyRateInput = buyR.toLong().toString()
                                    tradeSellRateInput = sellR.toLong().toString()
                                }
                                .padding(vertical = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = karat,
                                color = if (isSelected) Color.Black else HubTextPrimary,
                                fontWeight = FontWeight.Black,
                                fontSize = 11.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Quantity Input
                OutlinedTextField(
                    value = tradeQtyInput,
                    onValueChange = { tradeQtyInput = it },
                    label = { Text("Weight in Tolas (مقدار بمع تولہ)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Rates Row (Buy & Sell)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (tradeMode != "SELL") {
                        OutlinedTextField(
                            value = tradeBuyRateInput,
                            onValueChange = { tradeBuyRateInput = it },
                            label = { Text("Buy Rate/Tola") },
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )
                    }
                    if (tradeMode != "BUY") {
                        OutlinedTextField(
                            value = tradeSellRateInput,
                            onValueChange = { tradeSellRateInput = it },
                            label = { Text("Sell Rate/Tola") },
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Commission Selection Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(HubInnerWell)
                        .padding(10.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("MY BROKER COMMISSION (آپ کا کمیشن):", style = MaterialTheme.typography.labelSmall, color = HubGold, fontWeight = FontWeight.Bold, fontSize = 10.sp)
                            Text("${tradeCommissionPercent}% (Rs. ${String.format("%,.0f", tradeCommissionPkr / maxOf(0.001, parsedTradeQty))}/Tola)", style = MaterialTheme.typography.labelSmall, color = HubGold, fontWeight = FontWeight.Black, fontSize = 10.sp)
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            listOf(0.5 to "0.5%", 1.0 to "1.0% (Std)", 1.5 to "1.5%", 2.0 to "2.0%").forEach { (rate, lbl) ->
                                val isSelected = tradeCommissionPercent == rate
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(if (isSelected) HubGold else Color(0xFF131B2E))
                                        .border(1.dp, if (isSelected) HubGold else HubBorder, RoundedCornerShape(4.dp))
                                        .clickable { tradeCommissionPercent = rate }
                                        .padding(vertical = 4.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = lbl,
                                        color = if (isSelected) Color.Black else HubTextSecondary,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Breakdown Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(HubInnerWell)
                        .padding(10.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Total Purchase Cost:", style = MaterialTheme.typography.labelSmall, color = HubTextSecondary, fontSize = 10.sp)
                            Text("Rs. ${String.format("%,.0f", totalTradeBuy)}", style = MaterialTheme.typography.labelSmall, color = HubTextPrimary, fontWeight = FontWeight.Bold)
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Total Gross Revenue:", style = MaterialTheme.typography.labelSmall, color = HubTextSecondary, fontSize = 10.sp)
                            Text("Rs. ${String.format("%,.0f", totalTradeSell)}", style = MaterialTheme.typography.labelSmall, color = HubTextPrimary, fontWeight = FontWeight.Bold)
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Trading Spread:", style = MaterialTheme.typography.labelSmall, color = HubTextSecondary, fontSize = 10.sp)
                            Text("${if (tradeGrossSpread >= 0) "+" else ""}Rs. ${String.format("%,.0f", tradeGrossSpread)}", style = MaterialTheme.typography.labelSmall, color = if (tradeGrossSpread >= 0) HubEmerald else HubRose, fontWeight = FontWeight.Bold)
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("My Commission Earned:", style = MaterialTheme.typography.labelSmall, color = HubGold, fontWeight = FontWeight.Bold, fontSize = 10.sp)
                            Text("Rs. ${String.format("%,.0f", tradeCommissionPkr)}", style = MaterialTheme.typography.labelSmall, color = HubGold, fontWeight = FontWeight.Black)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Result Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (tradeNetProfit >= 0) Color(0xFF0C2413) else Color(0xFF280B10))
                        .border(1.dp, if (tradeNetProfit >= 0) HubEmerald else HubRose, RoundedCornerShape(10.dp))
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
                                    text = if (tradeMode == "BUY") "TOTAL BUY ACQUISITION" else if (tradeMode == "SELL") "NET CASH-OUT PAYOUT" else "NET INVESTOR / TRADER PROFIT",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (tradeNetProfit >= 0) HubEmerald else HubRose,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                )
                                Text(
                                    text = if (tradeMode == "BUY") "کل لاگت بمع کمیشن" else if (tradeMode == "SELL") "بعد از کمیشن وصولی رقم" else "خالص منافع بعد از کمیشن",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = HubTextDim,
                                    fontSize = 9.sp
                                )
                            }
                            Text(
                                text = if (tradeMode == "BUY") "Rs. ${String.format("%,.0f", totalTradeBuy + tradeCommissionPkr)}"
                                       else if (tradeMode == "SELL") "Rs. ${String.format("%,.0f", totalTradeSell - tradeCommissionPkr)}"
                                       else "${if (tradeNetProfit >= 0) "+" else ""}Rs. ${String.format("%,.0f", tradeNetProfit)}",
                                style = MaterialTheme.typography.headlineSmall,
                                color = if (tradeNetProfit >= 0) HubEmerald else HubRose,
                                fontWeight = FontWeight.Black,
                                fontSize = 22.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = if (tradeMode == "SIMULATE") "ROI: ${String.format("%.2f", tradeRoi)}% • Break-Even Sell: Rs. ${String.format("%,.0f", tradeBreakEven)}"
                                   else "Commission: Rs. ${String.format("%,.0f", tradeCommissionPkr)} (${tradeCommissionPercent}%)",
                            style = MaterialTheme.typography.labelSmall,
                            color = HubTextSecondary,
                            fontSize = 9.sp
                        )
                    }
                }
            }
        }

        // Islamic Zakat Matrix Evaluator
        val goldG = zakatGoldGramsInput.toDoubleOrNull() ?: 0.0
        val silverG = zakatSilverGramsInput.toDoubleOrNull() ?: 0.0
        val cash = zakatCashInput.toDoubleOrNull() ?: 0.0
        val isSilverInTolas = remember { mutableStateOf(false) }
        val silverHolding = zakatSilverGramsInput.toDoubleOrNull() ?: 0.0
        val effectiveSilverGrams = if (isSilverInTolas.value) silverHolding * 11.664 else silverHolding

        val isGoldInTolas = remember { mutableStateOf(false) }
        val goldHolding = zakatGoldGramsInput.toDoubleOrNull() ?: 0.0
        val effectiveGoldGrams = if (isGoldInTolas.value) goldHolding * 11.664 else goldHolding

        val zakatResult = remember(effectiveGoldGrams, effectiveSilverGrams, cash, goldRates) {
            UtilityRepository.calculateZakat(effectiveGoldGrams, effectiveSilverGrams, cash)
        }

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
                        Icon(
                            imageVector = Icons.Default.Shield,
                            contentDescription = null,
                            tint = HubEmerald,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "ISLAMIC ZAKAT MATRIX (GOLD & CHANDI)",
                            style = MaterialTheme.typography.labelSmall,
                            color = HubEmerald,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF0C2413))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "NISAB 52.5 TOLA SILVER",
                            style = MaterialTheme.typography.labelSmall,
                            color = HubEmerald,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Silver / Chandi Benchmark Rate Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(HubInnerWell)
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Chandi (Silver) Rate: Rs. 3,450/Tola (Rs. 295.8/g)",
                            style = MaterialTheme.typography.labelSmall,
                            color = HubSky,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 10.sp
                        )
                        Text(
                            text = "Nisab: 612.36g",
                            style = MaterialTheme.typography.labelSmall,
                            color = HubGold,
                            fontSize = 9.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Gold Input Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = zakatGoldGramsInput,
                        onValueChange = { zakatGoldGramsInput = it },
                        label = { Text(if (isGoldInTolas.value) "Gold (Sona in Tolas)" else "Gold (Sona in Grams)") },
                        modifier = Modifier.weight(1f),
                        singleLine = true
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isGoldInTolas.value) Color(0xFF282D07) else HubInnerWell)
                            .border(1.dp, if (isGoldInTolas.value) HubGold else HubBorder, RoundedCornerShape(8.dp))
                            .clickable { isGoldInTolas.value = !isGoldInTolas.value }
                            .padding(horizontal = 10.dp, vertical = 12.dp)
                    ) {
                        Text(
                            text = if (isGoldInTolas.value) "Tolas" else "Grams",
                            style = MaterialTheme.typography.labelSmall,
                            color = if (isGoldInTolas.value) HubGold else HubTextSecondary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Silver Input Row (Chandi)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = zakatSilverGramsInput,
                        onValueChange = { zakatSilverGramsInput = it },
                        label = { Text(if (isSilverInTolas.value) "Silver (Chandi in Tolas)" else "Silver (Chandi in Grams)") },
                        modifier = Modifier.weight(1f),
                        singleLine = true
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isSilverInTolas.value) Color(0xFF0C243B) else HubInnerWell)
                            .border(1.dp, if (isSilverInTolas.value) HubSky else HubBorder, RoundedCornerShape(8.dp))
                            .clickable { isSilverInTolas.value = !isSilverInTolas.value }
                            .padding(horizontal = 10.dp, vertical = 12.dp)
                    ) {
                        Text(
                            text = if (isSilverInTolas.value) "Tolas" else "Grams",
                            style = MaterialTheme.typography.labelSmall,
                            color = if (isSilverInTolas.value) HubSky else HubTextSecondary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = zakatCashInput,
                    onValueChange = { zakatCashInput = it },
                    label = { Text("Cash, Bank Savings & Tradable Assets (PKR)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Prominent Zakat Payable Result Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (zakatResult.isNisabReached) Color(0xFF1B2305) else Color(0xFF1E0A0E))
                        .border(1.5.dp, if (zakatResult.isNisabReached) HubGold else HubRose.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
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
                                    text = "NET ZAKAT PAYABLE (2.5%)",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (zakatResult.isNisabReached) HubGold else HubTextDim,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                )
                                Text(
                                    text = if (zakatResult.isNisabReached) "زکوٰۃ ادا کرنا واجب ہے (2.5 فیصد)" else "نصاب مکمل نہیں (زکوٰۃ لاگو نہیں)",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (zakatResult.isNisabReached) HubEmerald else HubRose,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Text(
                                text = "Rs. ${String.format("%,.0f", zakatResult.zakatPayablePkr)}",
                                style = MaterialTheme.typography.headlineMedium,
                                color = if (zakatResult.isNisabReached) HubGold else HubTextDim,
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
                                Text("Total Zakatable Wealth", style = MaterialTheme.typography.labelSmall, color = HubTextDim, fontSize = 9.sp)
                                Text(
                                    "Rs. ${String.format("%,.0f", zakatResult.totalWealthPkr)}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = HubTextPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text("Silver Nisab Benchmark (52.5 Tola)", style = MaterialTheme.typography.labelSmall, color = HubTextDim, fontSize = 9.sp)
                                Text(
                                    "Rs. ${String.format("%,.0f", zakatResult.silverNisabThreshold)}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = HubSky,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GoldRateCard(
    item: com.example.data.GoldCaratRate,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.border(1.dp, HubBorder, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = HubCard),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.labelSmall,
                    color = HubTextSecondary
                )
                Icon(
                    imageVector = if (item.isPositive) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward,
                    contentDescription = null,
                    tint = if (item.isPositive) HubEmerald else HubRose,
                    modifier = Modifier.size(12.dp)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Rs. ${String.format("%,d", item.ratePerTola)}",
                style = MaterialTheme.typography.titleMedium,
                color = HubTextPrimary,
                fontWeight = FontWeight.Black
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "10g: Rs. ${String.format("%,d", item.ratePer10Grams)}",
                style = MaterialTheme.typography.labelSmall,
                color = HubTextDim,
                fontSize = 10.sp
            )
        }
    }
}
