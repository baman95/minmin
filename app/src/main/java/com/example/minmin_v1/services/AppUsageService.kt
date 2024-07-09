package com.example.minmin_v1.data

import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.pm.PackageManager
import com.example.minmin_v1.model.AppUsageInfo

object AppUsageService {

    fun getUsageStats(context: Context): List<AppUsageInfo> {
        val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val endTime = System.currentTimeMillis()
        val startTime = endTime - 24 * 60 * 60 * 1000 // Last 24 hours

        val usageStats: List<UsageStats> = usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY, startTime, endTime
        )

        val appUsageInfoList = mutableListOf<AppUsageInfo>()
        val packageManager = context.packageManager

        usageStats.forEach { usageStat ->
            try {
                val appName = packageManager.getApplicationLabel(packageManager.getApplicationInfo(usageStat.packageName, PackageManager.GET_META_DATA)).toString()
                appUsageInfoList.add(
                    AppUsageInfo(
                        appName = appName,
                        usageTime = usageStat.totalTimeInForeground
                    )
                )
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
        }

        return appUsageInfoList
    }
}
