package com.example.minmin_v1.data

object NotificationStats {
    private val notificationCounts = mutableMapOf<String, Int>()

    fun incrementNotificationCount(packageName: String) {
        val count = notificationCounts.getOrDefault(packageName, 0)
        notificationCounts[packageName] = count + 1
    }

    fun getNotificationCount(packageName: String): Int {
        return notificationCounts.getOrDefault(packageName, 0)
    }

    fun getAllNotificationCounts(): Map<String, Int> {
        return notificationCounts
    }
}
