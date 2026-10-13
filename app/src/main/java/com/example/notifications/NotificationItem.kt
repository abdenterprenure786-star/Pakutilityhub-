package com.example.notifications

enum class AlertCategory {
    GEOLOCATION,
    PUSH_EVENT,
    GOLD_BULLION,
    FUEL_LOG,
    FOREX_EXCHANGE,
    MANDI_PRICE,
    SYSTEM
}

enum class AlertSeverity {
    INFO,
    SUCCESS,
    WARNING,
    CRITICAL
}

data class NotificationItem(
    val id: String = java.util.UUID.randomUUID().toString(),
    val title: String,
    val message: String,
    val category: AlertCategory,
    val severity: AlertSeverity = AlertSeverity.INFO,
    val timestampMillis: Long = System.currentTimeMillis(),
    val isRead: Boolean = false,
    val actionUrl: String? = null
)
