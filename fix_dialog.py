with open('app/src/main/java/com/example/ui/components/ProUpgradeDialog.kt', 'r', encoding='utf-8') as f:
    text = f.read()

# Remove any misplaced enum class before fixing
text = text.replace("""enum class ProTier(val title: String, val price: String, val perks: String, val priceInt: Int) {
    ALL_ACCESS_PRO("👑 Master VIP PRO", "Rs. 300", "All Rates Push Alerts + 100% Ad-Free everywhere", 300),
    GOLD_PASS("🟡 Gold Pass", "Rs. 50", "Gold rate alerts + No Ads on Gold", 50),
    CURRENCY_PASS("💱 Currency Pass", "Rs. 50", "Forex rise/drop alerts + No Ads on Forex", 50),
    FUEL_PASS("⛽ Fuel Pass", "Rs. 50", "OGRA rate alerts + No Ads on Fuel", 50)
}""", "")

# Replace HubBorderDim with HubBorder
text = text.replace("HubBorderDim", "HubBorder")

# Make sure enum class is placed cleanly before @Composable
enum_def = """enum class ProTier(val title: String, val price: String, val perks: String, val priceInt: Int) {
    ALL_ACCESS_PRO("👑 Master VIP PRO", "Rs. 300", "All Rates Push Alerts + 100% Ad-Free everywhere", 300),
    GOLD_PASS("🟡 Gold Pass", "Rs. 50", "Gold rate alerts + No Ads on Gold", 50),
    CURRENCY_PASS("💱 Currency Pass", "Rs. 50", "Forex rise/drop alerts + No Ads on Forex", 50),
    FUEL_PASS("⛽ Fuel Pass", "Rs. 50", "OGRA rate alerts + No Ads on Fuel", 50)
}

@Composable
fun ProUpgradeDialog("""

if "@Composable\nfun ProUpgradeDialog(" in text:
    text = text.replace("@Composable\nfun ProUpgradeDialog(", enum_def)
elif "@Composable fun ProUpgradeDialog(" in text:
    text = text.replace("@Composable fun ProUpgradeDialog(", enum_def)

with open('app/src/main/java/com/example/ui/components/ProUpgradeDialog.kt', 'w', encoding='utf-8') as f:
    f.write(text)

print("ProUpgradeDialog.kt fixed.")
