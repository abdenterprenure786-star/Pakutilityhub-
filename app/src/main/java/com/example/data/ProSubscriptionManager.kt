package com.example.data

import android.content.Context
import com.example.location.LocationRepository
import com.example.notifications.AlertCategory
import com.example.notifications.AlertSeverity
import com.example.notifications.NotificationItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object ProSubscriptionManager {

    private val _isProUser = MutableStateFlow(false)
    val isProUser: StateFlow<Boolean> = _isProUser.asStateFlow()

    private val _vipAlertsThresholdPkr = MutableStateFlow(300.0)
    val vipAlertsThresholdPkr: StateFlow<Double> = _vipAlertsThresholdPkr.asStateFlow()

    private val _isAutoPullActive = MutableStateFlow(true)
    val isAutoPullActive: StateFlow<Boolean> = _isAutoPullActive.asStateFlow()

    private val _pullIntervalMinutes = MutableStateFlow(5)
    val pullIntervalMinutes: StateFlow<Int> = _pullIntervalMinutes.asStateFlow()

    private val _lastPullTimestampMillis = MutableStateFlow(System.currentTimeMillis())
    val lastPullTimestampMillis: StateFlow<Long> = _lastPullTimestampMillis.asStateFlow()

    private val _isPushNotificationsActive = MutableStateFlow(true)
    val isPushNotificationsActive: StateFlow<Boolean> = _isPushNotificationsActive.asStateFlow()

    fun toggleProStatus() {
        if (_isProUser.value) {
            _isProUser.value = false
            LocationRepository.addNotification(
                NotificationItem(
                    title = "Pro Plan Reverted",
                    message = "Switched to standard ad-supported tier.",
                    category = AlertCategory.SYSTEM,
                    severity = AlertSeverity.INFO
                )
            )
        } else {
            activatePro()
        }
    }

    fun activatePro() {
        _isProUser.value = true
        _isAutoPullActive.value = true
        _isPushNotificationsActive.value = true
        LocationRepository.addNotification(
            NotificationItem(
                title = "⭐ PakUtility Pro VIP Activated",
                message = "100% Ad-Free active. Merged Push & Pull VIP rate alert system unlocked with Rs. 300 thresholds for all feeds.",
                category = AlertCategory.SYSTEM,
                severity = AlertSeverity.SUCCESS
            )
        )
    }

    fun setAlertThreshold(threshold: Double) {
        _vipAlertsThresholdPkr.value = threshold
    }

    fun setPullInterval(minutes: Int) {
        _pullIntervalMinutes.value = minutes
    }

    fun toggleAutoPull() {
        _isAutoPullActive.value = !_isAutoPullActive.value
    }

    fun togglePushNotifications() {
        _isPushNotificationsActive.value = !_isPushNotificationsActive.value
    }

    fun recordPullExecuted() {
        _lastPullTimestampMillis.value = System.currentTimeMillis()
    }
}
