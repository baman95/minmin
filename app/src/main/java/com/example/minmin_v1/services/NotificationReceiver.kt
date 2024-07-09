package com.example.minmin_v1.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

class NotificationReceiver(private val onNotificationReceived: (String) -> Unit) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.getStringExtra("packageName")?.let { packageName ->
            onNotificationReceived(packageName)
        }
    }

    fun register(context: Context) {
        val filter = IntentFilter().apply {
            addAction("com.example.minmin_v1.NOTIFICATION_POSTED")
        }
        context.registerReceiver(this, filter)
    }

    fun unregister(context: Context) {
        context.unregisterReceiver(this)
    }
}
