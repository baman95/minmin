package com.example.minmin_v1.services

import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class NotificationListener : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)
        Log.d("NotificationListener", "Notification posted: ${sbn?.packageName}")
        val intent = Intent("com.example.minmin_v1.NOTIFICATION_LISTENER")
        intent.putExtra("package_name", sbn.packageName)
        intent.putExtra("notification_id", sbn.id)
        intent.putExtra("notification_text", sbn.notification.tickerText?.toString() ?: "")
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        super.onNotificationRemoved(sbn)
        Log.d("NotificationListener", "Notification removed: ${sbn?.packageName}")
        // Handle the notification removal
        val intent = Intent("com.example.minmin_v1.NOTIFICATION_LISTENER")
        intent.putExtra("package_name", sbn.packageName)
        intent.putExtra("notification_id", sbn.id)
        intent.putExtra("notification_removed", true)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }
}
