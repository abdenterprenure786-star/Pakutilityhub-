package com.example.data

data class SyncMetadata(
    val timestamp: String = "LIVE",
    val syncStatus: String = "ACTIVE_PRODUCTION_NODES",
    val isSyncing: Boolean = false,
    val lastSyncMessage: String = "100% Live Sync Handshake Completed"
)

data class GoldCaratRate(
    val carat: String,
    val name: String,
    val purityPercent: Float,
    val ratePerTola: Long,
    val ratePer10Grams: Long,
    val changePkr: Long,
    val isPositive: Boolean,
    val trendBadge: String = "LOW MARGIN (Bearish Correction Drop)",
    val source: String = "All-Pakistan Gems & Jewellers Association"
)

data class FuelItem(
    val fuelType: String,
    val name: String,
    val pricePerLiter: Double,
    val previousPrice: Double,
    val unit: String = "Ltr",
    val statusBadge: String = "Govt Fixed",
    val trendAlert: String = "OGRA Notified Ex-Depot Bulletins"
)

data class ForexRate(
    val code: String,
    val name: String,
    val symbol: String,
    val flag: String,
    val interbankBuy: Double,
    val interbankSell: Double,
    val openMarketBuy: Double,
    val openMarketSell: Double,
    val changePercent: Double
)

enum class MandiCategory(val displayName: String, val icon: String) {
    ALL("All Mandi Items", "🌐"),
    FRUITS("Fruits (Phal)", "🍎"),
    VEGETABLES("Vegetables (Sabzi)", "🥦"),
    GRAINS_STAPLES("Grains & Staples (Galla)", "🌾"),
    POULTRY_MEAT("Poultry & Meat (Gosht)", "🍗")
}

data class MandiCommodity(
    val id: String,
    val name: String,
    val urduName: String,
    val category: MandiCategory,
    val minPrice40Kg: Long, // wholesale per 40kg (Maund/Man)
    val maxPrice40Kg: Long,
    val avgPriceKg: Double, // wholesale avg per kg
    val retailPriceKg: Double = avgPriceKg * 1.25, // retail market app price
    val govtDcRateKg: Double = avgPriceKg * 1.15, // official DC notified price
    val mandiCity: String,
    val unit: String = "Kg",
    val changePercent: Double = 0.0,
    val stateBadge: String = "STABLE" // "STABLE", "HIGH", "LOW"
)

data class ConstructionMaterial(
    val name: String,
    val gradeOrBrand: String,
    val unit: String,
    val pricePkr: Long,
    val changePkr: Long,
    val isUp: Boolean,
    val status: String = "STABLE"
)

data class ZakatResult(
    val totalWealthPkr: Double,
    val silverNisabThreshold: Double,
    val goldNisabThreshold: Double,
    val isNisabReached: Boolean,
    val zakatPayablePkr: Double
)

data class GoldMassConversion(
    val tola: Double,
    val grams: Double,
    val masha: Double,
    val ratti: Double,
    val value24kPkr: Long,
    val value22kPkr: Long,
    val value18kPkr: Long
)

data class TaxBreakdown(
    val annualGross: Double,
    val monthlyGross: Double,
    val annualTax: Double,
    val monthlyTax: Double,
    val effectiveRatePercent: Double,
    val netMonthlySalary: Double
)
