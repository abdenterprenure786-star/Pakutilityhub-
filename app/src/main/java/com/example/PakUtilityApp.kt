package com.example

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class PakUtilityApp : Application() {

    companion object {
        const val CHANNEL_LOCATION = "geo_tracking_channel"
        const val CHANNEL_ALERTS = "push_alerts_channel"
        const val CHANNEL_MARKET = "market_updates_channel"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val locationChannel = NotificationChannel(
                CHANNEL_LOCATION,
                "Real-Time Geo Tracking",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Foreground notifications for continuous GPS geolocation tracking and distance telemetry"
            }

            val alertsChannel = NotificationChannel(
                CHANNEL_ALERTS,
                "Push & Geofence Alerts",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Heads-up push notifications for geofence breaches, speed alerts, and critical utility events"
                enableVibration(true)
            }

            val marketChannel = NotificationChannel(
                CHANNEL_MARKET,
                "Utility & Commodity Price Alerts",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Updates on Gold bullion, Fuel revisions, Forex fluctuations, and Mandi auction rates"
            }

            notificationManager.createNotificationChannels(listOf(locationChannel, alertsChannel, marketChannel))
        }
    }
}
