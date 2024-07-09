package com.example.minmin_v1.services


import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class CustomAccessibilityService : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        sbn?.let {
            Log.d("NotificationListener", "Notification posted from: ${it.packageName}")
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
        sbn?.let {
            Log.d("NotificationListener", "Notification removed from: ${it.packageName}")
        }
    }
}

