with open('app/src/main/java/com/example/ui/components/ProUpgradeDialog.kt', 'r', encoding='utf-8') as f:
    text = f.read()

enum_def = """enum class ProTier(val title: String, val price: String, val perks: String, val priceInt: Int) {
    ALL_ACCESS_PRO("👑 Master VIP PRO", "Rs. 300", "All Rates Push Alerts + 100% Ad-Free everywhere", 300),
    GOLD_PASS("🟡 Gold Pass", "Rs. 50", "Gold rate alerts + No Ads on Gold", 50),
    CURRENCY_PASS("💱 Currency Pass", "Rs. 50", "Forex rise/drop alerts + No Ads on Forex", 50),
    FUEL_PASS("⛽ Fuel Pass", "Rs. 50", "OGRA rate alerts + No Ads on Fuel", 50)
}

@Composable
fun ProUpgradeDialog("""

target = """@Composable



fun ProUpgradeDialog("""

if target in text:
    text = text.replace(target, enum_def)
else:
    # Just insert before @Composable
    idx = text.find('@Composable')
    text = text[:idx] + enum_def + text[idx+len('@Composable\n\n\n\nfun ProUpgradeDialog('):]

with open('app/src/main/java/com/example/ui/components/ProUpgradeDialog.kt', 'w', encoding='utf-8') as f:
    f.write(text)

print("ProUpgradeDialog.kt enum class inserted.")
