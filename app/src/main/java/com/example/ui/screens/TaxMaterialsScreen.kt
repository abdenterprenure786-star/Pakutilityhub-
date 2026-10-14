package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Description
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
import com.example.ui.theme.HubInnerWell
import com.example.ui.theme.HubRose
import com.example.ui.theme.HubSky
import com.example.ui.theme.HubTextDim
import com.example.ui.theme.HubTextPrimary
import com.example.ui.theme.HubTextSecondary

@Composable
fun TaxMaterialsScreen(
    showMaterialsInitially: Boolean = false,
    modifier: Modifier = Modifier
) {
    val constructionMaterials by UtilityRepository.constructionMaterials.collectAsState()

    var monthlySalaryInput by remember { mutableStateOf("250000") }
    val monthlySalary = monthlySalaryInput.toDoubleOrNull() ?: 250000.0
    val annualSalary = monthlySalary * 12.0
    val taxBreakdown = remember(annualSalary) {
        UtilityRepository.calculateIncomeTax(annualSalary)
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
            .testTag("tax_materials_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // FBR Income Tax Calculator
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
                        imageVector = Icons.Default.Description,
                        contentDescription = null,
                        tint = HubGold,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "FBR SALARY INCOME TAX CALCULATOR (FY 2024-2026)",
                        style = MaterialTheme.typography.labelSmall,
                        color = HubGold,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "Official progressive taxation slabs for salaried individuals",
                    style = MaterialTheme.typography.bodyMedium,
                    color = HubTextSecondary,
                    fontSize = 11.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = monthlySalaryInput,
                    onValueChange = { monthlySalaryInput = it },
                    label = { Text("Gross Monthly Salary (PKR)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Prominent Salary Take-Home & Tax Result Box
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
                                    text = "NET MONTHLY TAKE-HOME PAY",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = HubEmerald,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                )
                                Text(
                                    text = "ٹیکس کٹوتی کے بعد ماہانہ خالص تنخواہ",
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
                                        .clickable { monthlySalaryInput = "0" }
                                        .padding(horizontal = 6.dp, vertical = 3.dp)
                                ) {
                                    Text("Clear", color = Color(0xFFF43F5E), fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                }
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Rs. ${String.format("%,.0f", taxBreakdown.netMonthlySalary)}",
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
                                Text("Monthly FBR Tax", style = MaterialTheme.typography.labelSmall, color = HubTextDim, fontSize = 9.sp)
                                Text(
                                    "Rs. ${String.format("%,.0f", taxBreakdown.monthlyTax)}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = if (taxBreakdown.monthlyTax > 0) HubRose else HubEmerald,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Column {
                                Text("Effective Tax Rate", style = MaterialTheme.typography.labelSmall, color = HubTextDim, fontSize = 9.sp)
                                Text(
                                    "${String.format("%.1f", taxBreakdown.effectiveRatePercent)}%",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = HubTextPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Column(horizontalAlignment = Alignment.End) {
                                Text("Annual Tax Deduction", style = MaterialTheme.typography.labelSmall, color = HubTextDim, fontSize = 9.sp)
                                Text(
                                    "Rs. ${String.format("%,.0f", taxBreakdown.annualTax)}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = HubSky,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }

        // Filer vs Non-Filer Informative Display Box (Premium Muted Dark Design)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, HubBorder, RoundedCornerShape(14.dp)),
            colors = CardDefaults.cardColors(containerColor = HubCard),
            shape = RoundedCornerShape(14.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "FILER VS NON-FILER TAX MATRIX (FBR)",
                            style = MaterialTheme.typography.labelSmall,
                            color = HubGold,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Active Taxpayer List (ATL) Withholding Disparity",
                            style = MaterialTheme.typography.bodyMedium,
                            color = HubTextSecondary,
                            fontSize = 11.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF282D07))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "ATL STATUS",
                            style = MaterialTheme.typography.labelSmall,
                            color = HubGold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 8.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Table Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(HubInnerWell)
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("TRANSACTION TYPE", style = MaterialTheme.typography.labelSmall, color = HubTextDim, fontSize = 9.sp, modifier = Modifier.weight(1.3f))
                    Text("FILER (ATL)", style = MaterialTheme.typography.labelSmall, color = HubEmerald, fontSize = 9.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                    Text("NON-FILER", style = MaterialTheme.typography.labelSmall, color = HubRose, fontSize = 9.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = androidx.compose.ui.text.style.TextAlign.End)
                }

                Spacer(modifier = Modifier.height(6.dp))

                val comparisons = listOf(
                    Triple("Cash Withdrawal (>50K/day)", "0% (Exempt)", "0.6% Tax"),
                    Triple("Property Purchase / Transfer", "3.0% Advance", "10.5% - 12.0%"),
                    Triple("New Vehicle Registration", "Standard Slabs", "200% - 300% Tax"),
                    Triple("Bank Profit / Prize Bonds", "15.0% Tax", "30.0% Double Tax"),
                    Triple("International Card Spends", "2.0% Advance", "10.0% Advance")
                )

                comparisons.forEach { (trx, filer, nonFiler) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp, horizontal = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = trx,
                            style = MaterialTheme.typography.bodyMedium,
                            color = HubTextPrimary,
                            fontSize = 11.sp,
                            modifier = Modifier.weight(1.3f)
                        )
                        Text(
                            text = filer,
                            style = MaterialTheme.typography.bodyMedium,
                            color = HubEmerald,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            modifier = Modifier.weight(1f),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                        Text(
                            text = nonFiler,
                            style = MaterialTheme.typography.bodyMedium,
                            color = HubRose,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            modifier = Modifier.weight(1f),
                            textAlign = androidx.compose.ui.text.style.TextAlign.End
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF0C2413))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "💡 Active Taxpayers save an average of Rs. 140,000+ annually on banking, auto registrations, and property documentation.",
                        style = MaterialTheme.typography.labelSmall,
                        color = HubEmerald,
                        fontSize = 9.sp,
                        lineHeight = 13.sp
                    )
                }
            }
        }

        // Construction Materials Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Construction,
                    contentDescription = null,
                    tint = HubSky,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "CONSTRUCTION RAW MATERIALS INDEX",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubSky,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "MILL RATES",
                style = MaterialTheme.typography.labelSmall,
                color = HubTextDim
            )
        }

        constructionMaterials.forEach { mat ->
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
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = mat.name,
                                style = MaterialTheme.typography.bodyLarge,
                                color = HubTextPrimary,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(
                                        if (mat.status == "HIGH") HubRose.copy(alpha = 0.2f) else HubEmerald.copy(alpha = 0.2f)
                                    )
                                    .padding(horizontal = 5.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = mat.status,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (mat.status == "HIGH") HubRose else HubEmerald,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 8.sp
                                )
                            }
                        }
                        Text(
                            text = "${mat.gradeOrBrand} • per ${mat.unit}",
                            style = MaterialTheme.typography.labelSmall,
                            color = HubTextDim,
                            fontSize = 10.sp
                        )
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "Rs. ${String.format("%,d", mat.pricePkr)}",
                            style = MaterialTheme.typography.titleMedium,
                            color = HubTextPrimary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (mat.changePkr != 0L) "${if (mat.isUp) "+" else "-"}Rs. ${mat.changePkr}" else "Stable",
                            style = MaterialTheme.typography.labelSmall,
                            color = if (mat.changePkr > 0) HubRose else if (mat.changePkr < 0) HubEmerald else HubTextDim,
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }

        // ==========================================
        // HOUSE CONSTRUCTION COST CALCULATOR SECTION
        // ==========================================
        var selectedMarla by remember { mutableStateOf(5.0) }
        var selectedStories by remember { mutableStateOf(2) } // 1, 2, or 3
        var isFullFinishing by remember { mutableStateOf(false) }

        val houseEstimate = remember(selectedMarla, selectedStories, isFullFinishing) {
            UtilityRepository.calculateHouseConstructionCost(selectedMarla, selectedStories, isFullFinishing)
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.5.dp, HubBorderGold, RoundedCornerShape(16.dp))
                .testTag("house_construction_card"),
            colors = CardDefaults.cardColors(containerColor = HubCardElevated),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Construction,
                                contentDescription = null,
                                tint = HubGold,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "GHAR BANANE KA KHARCHA (گھر کی لاگت)",
                                style = MaterialTheme.typography.labelSmall,
                                color = HubGold,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.5.sp
                            )
                        }
                        Text(
                            text = "House Construction Cost Estimator (3 Marla to 1 Kanal)",
                            style = MaterialTheme.typography.bodyMedium,
                            color = HubTextSecondary,
                            fontSize = 11.sp
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFF282D07))
                            .border(1.dp, HubGold, RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text("2026 RATES", color = HubGold, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Plot Size Chips (3 Marla to 20 Marla / 1 Kanal)
                Text(
                    text = "SELECT PLOT SIZE (پلاٹ سائز): ${if (selectedMarla == 20.0) "1 Kanal (20 Marla)" else "${selectedMarla.toInt()} Marla (${(selectedMarla * 225).toInt()} sq ft)"}",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubTextSecondary,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    listOf(
                        3.0 to "3 Marla",
                        5.0 to "5 Marla",
                        7.0 to "7 Marla",
                        10.0 to "10 Marla",
                        20.0 to "1 Kanal"
                    ).forEach { (marlaVal, label) ->
                        val isSel = selectedMarla == marlaVal
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isSel) HubGold else HubInnerWell)
                                .border(1.dp, if (isSel) HubGold else HubBorder, RoundedCornerShape(8.dp))
                                .clickable { selectedMarla = marlaVal }
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = label,
                                style = MaterialTheme.typography.labelSmall,
                                color = if (isSel) Color.Black else HubTextPrimary,
                                fontWeight = if (isSel) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 10.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Number of Stories (Single, Double, Triple)
                Text(
                    text = "SELECT FLOORS (منزلیں):",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubTextSecondary,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf(
                        1 to "Single Story (ایک منزلہ)",
                        2 to "Double Story (دو منزلہ)",
                        3 to "Triple Story (تین منزلہ)"
                    ).forEach { (storyNum, label) ->
                        val isSel = selectedStories == storyNum
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isSel) Color(0xFF132A15) else HubInnerWell)
                                .border(1.dp, if (isSel) HubEmerald else HubBorder, RoundedCornerShape(8.dp))
                                .clickable { selectedStories = storyNum }
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = label,
                                style = MaterialTheme.typography.labelSmall,
                                color = if (isSel) HubEmerald else HubTextPrimary,
                                fontWeight = if (isSel) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 10.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Construction Stage: Grey Structure vs Full A-Grade Finishing
                Text(
                    text = "CONSTRUCTION STAGE (کنسٹرکشن قسم):",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubTextSecondary,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (!isFullFinishing) Color(0xFF282D07) else HubInnerWell)
                            .border(1.dp, if (!isFullFinishing) HubGold else HubBorder, RoundedCornerShape(8.dp))
                            .clickable { isFullFinishing = false }
                            .padding(vertical = 10.dp, horizontal = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Grey Structure (گرے)",
                                style = MaterialTheme.typography.bodyMedium,
                                color = if (!isFullFinishing) HubGold else HubTextPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp
                            )
                            Text(
                                text = "Rs. 3,100 / sq ft",
                                style = MaterialTheme.typography.labelSmall,
                                color = HubTextDim,
                                fontSize = 9.sp
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isFullFinishing) Color(0xFF132A15) else HubInnerWell)
                            .border(1.dp, if (isFullFinishing) HubEmerald else HubBorder, RoundedCornerShape(8.dp))
                            .clickable { isFullFinishing = true }
                            .padding(vertical = 10.dp, horizontal = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Full A-Grade Finishing",
                                style = MaterialTheme.typography.bodyMedium,
                                color = if (isFullFinishing) HubEmerald else HubTextPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp
                            )
                            Text(
                                text = "Rs. 5,800 / sq ft",
                                style = MaterialTheme.typography.labelSmall,
                                color = HubTextDim,
                                fontSize = 9.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Total Estimated Cost Highlight Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF070B13))
                        .border(1.5.dp, if (isFullFinishing) HubEmerald else HubBorderGold, RoundedCornerShape(12.dp))
                        .padding(14.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "TOTAL ESTIMATED COST (کل لاگت)",
                                style = MaterialTheme.typography.labelSmall,
                                color = HubGold,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp
                            )
                            Text(
                                text = "${String.format("%,.0f", houseEstimate.totalCoveredAreaSqFt)} Sq Ft Covered Area",
                                style = MaterialTheme.typography.labelSmall,
                                color = HubSky,
                                fontSize = 10.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Rs. ${String.format("%,.0f", houseEstimate.totalCostPkr)}",
                            style = MaterialTheme.typography.headlineMedium,
                            color = if (isFullFinishing) HubEmerald else HubGold,
                            fontWeight = FontWeight.Black,
                            fontSize = 26.sp
                        )

                        Text(
                            text = "Approx. ${(houseEstimate.totalCostPkr / 100000.0).let { String.format("%.2f Lacs (%.2f Crore)", it, it / 100.0) }}",
                            style = MaterialTheme.typography.bodySmall,
                            color = HubTextSecondary,
                            fontSize = 11.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Material Quantity & Cost Breakdown
                Text(
                    text = "REQUIRED MATERIAL BREAKDOWN (سامان کی مقدار و خرچ):",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubTextDim,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    // Cement
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Cement (${houseEstimate.cementBagsCount} Bags @ Rs. 1,520)",
                            style = MaterialTheme.typography.bodySmall,
                            color = HubTextPrimary,
                            fontSize = 11.sp
                        )
                        Text(
                            text = "Rs. ${String.format("%,.0f", houseEstimate.cementCostPkr)}",
                            style = MaterialTheme.typography.bodySmall,
                            color = HubTextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }

                    // Steel
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Steel Grade-60 (${String.format("%.2f", houseEstimate.steelTons)} Tons @ Rs. 275k)",
                            style = MaterialTheme.typography.bodySmall,
                            color = HubTextPrimary,
                            fontSize = 11.sp
                        )
                        Text(
                            text = "Rs. ${String.format("%,.0f", houseEstimate.steelCostPkr)}",
                            style = MaterialTheme.typography.bodySmall,
                            color = HubTextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }

                    // Bricks
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Bricks Awal (${String.format("%,d", houseEstimate.bricksCount)} Pcs @ Rs. 18.5k/k)",
                            style = MaterialTheme.typography.bodySmall,
                            color = HubTextPrimary,
                            fontSize = 11.sp
                        )
                        Text(
                            text = "Rs. ${String.format("%,.0f", houseEstimate.bricksCostPkr)}",
                            style = MaterialTheme.typography.bodySmall,
                            color = HubTextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }

                    // Sand & Crush
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Sand & Crush (ریت و بجری)",
                            style = MaterialTheme.typography.bodySmall,
                            color = HubTextPrimary,
                            fontSize = 11.sp
                        )
                        Text(
                            text = "Rs. ${String.format("%,.0f", houseEstimate.sandCrushCostPkr)}",
                            style = MaterialTheme.typography.bodySmall,
                            color = HubTextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }

                    // Labor Cost
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Mason & Labor Cost (مزدوری ٹھیکیدار)",
                            style = MaterialTheme.typography.bodySmall,
                            color = HubTextPrimary,
                            fontSize = 11.sp
                        )
                        Text(
                            text = "Rs. ${String.format("%,.0f", houseEstimate.laborCostPkr)}",
                            style = MaterialTheme.typography.bodySmall,
                            color = HubSky,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }

                    if (isFullFinishing) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Tiles, Paint, Woodwork, Sanitary & Electricals",
                                style = MaterialTheme.typography.bodySmall,
                                color = HubEmerald,
                                fontSize = 11.sp
                            )
                            Text(
                                text = "Rs. ${String.format("%,.0f", houseEstimate.finishingCostPkr)}",
                                style = MaterialTheme.typography.bodySmall,
                                color = HubEmerald,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
