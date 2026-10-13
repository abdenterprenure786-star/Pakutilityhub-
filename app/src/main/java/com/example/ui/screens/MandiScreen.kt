package com.example.ui.screens

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.MandiCategory
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
fun MandiScreen(
    modifier: Modifier = Modifier
) {
    val commodities by UtilityRepository.mandiCommodities.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(MandiCategory.ALL) }
    var selectedCityFilter by remember { mutableStateOf<String?>(null) }

    val cities = remember(commodities) {
        listOf("All Cities") + commodities.map { it.mandiCity }.distinct()
    }

    val filteredList = remember(commodities, selectedCategory, selectedCityFilter, searchQuery) {
        commodities.filter { item ->
            val matchesCategory = selectedCategory == MandiCategory.ALL || item.category == selectedCategory
            val matchesCity = selectedCityFilter == null || selectedCityFilter == "All Cities" || item.mandiCity == selectedCityFilter
            val matchesQuery = searchQuery.isBlank() ||
                    item.name.contains(searchQuery, ignoreCase = true) ||
                    item.urduName.contains(searchQuery, ignoreCase = true) ||
                    item.mandiCity.contains(searchQuery, ignoreCase = true)
            matchesCategory && matchesCity && matchesQuery
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .testTag("mandi_screen"),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Agriculture,
                        contentDescription = null,
                        tint = HubEmerald,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "MANDI BAZAAR ALL FOODS & FRUITS",
                        style = MaterialTheme.typography.labelSmall,
                        color = HubEmerald,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "Wholesale Mandi (40 Kg & Kg), Retail Apps & DC Notified Rates",
                    style = MaterialTheme.typography.bodyMedium,
                    color = HubTextSecondary,
                    fontSize = 11.sp
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF282D07))
                    .border(1.dp, HubBorderGold, RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "${filteredList.size} ITEMS",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubGold,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search fruits, foods, vegetables (e.g. Apple, Atta, Aloo)...", fontSize = 12.sp) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = HubGold,
                    modifier = Modifier.size(18.dp)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Category Filter Tabs: All, Fruits, Vegetables, Grains, Poultry & Meat
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(MandiCategory.values()) { category ->
                val isSelected = category == selectedCategory
                FilterChip(
                    selected = isSelected,
                    onClick = { selectedCategory = category },
                    label = {
                        Text(
                            text = "${category.icon} ${category.displayName}",
                            fontSize = 11.sp
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = HubGold,
                        selectedLabelColor = Color.Black
                    )
                )
            }
        }

        // City Filter Chips Row
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(cities) { city ->
                val isSelected = (selectedCityFilter == null && city == "All Cities") || (selectedCityFilter == city)
                FilterChip(
                    selected = isSelected,
                    onClick = {
                        selectedCityFilter = if (city == "All Cities") null else city
                    },
                    label = { Text(city, fontSize = 10.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = HubSky,
                        selectedLabelColor = Color.Black
                    )
                )
            }
        }

        // Commodity Cards List
        if (filteredList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text("No items found matching \"$searchQuery\"", color = HubTextDim, fontSize = 12.sp)
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredList, key = { it.id }) { item ->
                    MandiItemCard(item = item)
                }
            }
        }
    }
}

@Composable
fun MandiItemCard(item: com.example.data.MandiCommodity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, HubBorder, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = HubCard),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.bodyLarge,
                            color = HubTextPrimary,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = item.urduName,
                            style = MaterialTheme.typography.bodyMedium,
                            color = HubGold,
                            fontSize = 11.sp
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(
                                    when (item.stateBadge) {
                                        "HIGH" -> HubRose.copy(alpha = 0.2f)
                                        "LOW" -> HubEmerald.copy(alpha = 0.2f)
                                        else -> HubGold.copy(alpha = 0.2f)
                                    }
                                )
                                .padding(horizontal = 5.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = item.stateBadge,
                                style = MaterialTheme.typography.labelSmall,
                                color = when (item.stateBadge) {
                                    "HIGH" -> HubRose
                                    "LOW" -> HubEmerald
                                    else -> HubGold
                                },
                                fontWeight = FontWeight.Bold,
                                fontSize = 8.sp
                            )
                        }
                    }
                    Text(
                        text = "Mandi: ${item.mandiCity} • Category: ${item.category.displayName}",
                        style = MaterialTheme.typography.labelSmall,
                        color = HubTextDim,
                        fontSize = 10.sp
                    )
                }

                // Daily change percentage
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val isUp = item.changePercent >= 0
                    Icon(
                        imageVector = if (isUp) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward,
                        contentDescription = null,
                        tint = if (isUp) HubRose else HubEmerald,
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = "${if (isUp) "+" else ""}${String.format("%.1f", item.changePercent)}%",
                        style = MaterialTheme.typography.labelSmall,
                        color = if (isUp) HubRose else HubEmerald,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 3-Column Price Comparison Matrix
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(HubInnerWell)
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Wholesale 40KG & KG
                Column {
                    Text("Wholesale 40KG", style = MaterialTheme.typography.labelSmall, color = HubTextDim, fontSize = 9.sp)
                    Text("Rs. ${String.format("%,d", item.minPrice40Kg)}", style = MaterialTheme.typography.bodyMedium, color = HubTextPrimary, fontWeight = FontWeight.Bold)
                    Text("Rs. ${String.format("%.0f", item.avgPriceKg)}/${item.unit}", style = MaterialTheme.typography.labelSmall, color = HubSky, fontSize = 9.sp)
                }

                // Retail Market Apps
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Retail Apps", style = MaterialTheme.typography.labelSmall, color = HubTextDim, fontSize = 9.sp)
                    Text("Rs. ${String.format("%.0f", item.retailPriceKg)}/${item.unit}", style = MaterialTheme.typography.bodyMedium, color = HubGold, fontWeight = FontWeight.Bold)
                    Text("Market Est.", style = MaterialTheme.typography.labelSmall, color = HubTextDim, fontSize = 9.sp)
                }

                // Govt DC Notified Rate
                Column(horizontalAlignment = Alignment.End) {
                    Text("Govt DC Rate", style = MaterialTheme.typography.labelSmall, color = HubTextDim, fontSize = 9.sp)
                    Text("Rs. ${String.format("%.0f", item.govtDcRateKg)}/${item.unit}", style = MaterialTheme.typography.bodyMedium, color = HubEmerald, fontWeight = FontWeight.Bold)
                    Text("Official Max", style = MaterialTheme.typography.labelSmall, color = HubTextDim, fontSize = 9.sp)
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Quick trigger Rs. 300 alert button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .clickable {
                            UtilityRepository.triggerRs300RateShiftAlert("${item.name} (${item.mandiCity})", 300.0)
                        }
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.NotificationsActive,
                        contentDescription = null,
                        tint = HubGold,
                        modifier = Modifier.size(11.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Set Rs. 300 Alert",
                        style = MaterialTheme.typography.labelSmall,
                        color = HubGold,
                        fontSize = 9.sp
                    )
                }
            }
        }
    }
}
