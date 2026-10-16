# -*- coding: utf-8 -*-
with open('app/src/main/java/com/example/ui/components/ProUpgradeDialog.kt', 'r', encoding='utf-8') as f:
    content = f.read()

# 1. Define ProTier enum
tier_enum = '''
enum class ProTier(val title: String, val price: String, val perks: String, val priceInt: Int) {
    ALL_ACCESS_PRO("👑 Master VIP PRO", "Rs. 300", "All Rates Push Alerts + 100% Ad-Free everywhere", 300),
    GOLD_PASS("🟡 Gold Pass", "Rs. 50", "Gold rate alerts + No Ads on Gold", 50),
    CURRENCY_PASS("💱 Currency Pass", "Rs. 50", "Forex rise/drop alerts + No Ads on Forex", 50),
    FUEL_PASS("⛽ Fuel Pass", "Rs. 50", "OGRA rate alerts + No Ads on Fuel", 50)
}
'''
if 'enum class ProTier' not in content:
    content = content.replace('fun ProUpgradeDialog(', tier_enum + '\nfun ProUpgradeDialog(')

# 2. Add selectedTier state
if 'var selectedTier' not in content:
    content = content.replace(
        'val jazzCashAccountNumber = "03064678975"',
        'val jazzCashAccountNumber = "03064678975"\n    var selectedTier by remember { mutableStateOf(ProTier.ALL_ACCESS_PRO) }'
    )

# 3. Add Tier Selector Chips right after subtitle text
tier_selector_ui = '''                Text(
                    text = "100% Ad-Free Experience & VIP Rates Push Alerts",
                    style = MaterialTheme.typography.bodyMedium,
                    color = HubTextSecondary,
                    fontSize = 11.sp,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Select Package / Tier Chips
                Text(
                    text = "SELECT PRO PACKAGE (پیکج منتخب کریں):",
                    style = MaterialTheme.typography.labelSmall,
                    color = HubGold,
                    fontWeight = FontWeight.Bold,
                    fontSize = 9.sp,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    ProTier.values().forEach { tier ->
                        val isSelected = selectedTier == tier
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isSelected) (if (tier == ProTier.ALL_ACCESS_PRO) Color(0xFF282D07) else Color(0xFF132A15)) else HubInnerWell)
                                .border(1.dp, if (isSelected) (if (tier == ProTier.ALL_ACCESS_PRO) HubGold else HubEmerald) else HubBorderDim, RoundedCornerShape(8.dp))
                                .clickable { selectedTier = tier }
                                .padding(vertical = 6.dp, horizontal = 2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = tier.title,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (isSelected) Color.White else HubTextDim,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 8.sp,
                                    maxLines = 1
                                )
                                Text(
                                    text = tier.price,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (isSelected) HubGold else HubTextDim,
                                    fontWeight = FontWeight.Black,
                                    fontSize = 9.sp
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF0F1A12))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Included: " + selectedTier.perks,
                        style = MaterialTheme.typography.labelSmall,
                        color = HubEmerald,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }'''

target_sub = '''                Text(
                    text = "100% Ad-Free Experience & VIP Rs. 300 Rates Push Alerts",
                    style = MaterialTheme.typography.bodyMedium,
                    color = HubTextSecondary,
                    fontSize = 11.sp,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )'''

if target_sub in content:
    content = content.replace(target_sub, tier_selector_ui)

# 4. Replace hardcoded "Rs. 300 / Mo" with selectedTier.price
content = content.replace('text = "Rs. 300 / Mo",', 'text = "${selectedTier.price} / Mo",')

# 5. Replace payment procedure text
content = content.replace('1. Send Rs. 300 to 03064678975', '1. Send ${selectedTier.price} to 03064678975')

with open('app/src/main/java/com/example/ui/components/ProUpgradeDialog.kt', 'w', encoding='utf-8') as f:
    f.write(content)

print('Updated ProUpgradeDialog.kt successfully.')
