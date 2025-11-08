package com.skc.app.droid.x

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.pm.PackageInfoCompat

fun Context.getAppVersion(): String {
    val packageInfo = packageManager.getPackageInfo(
        packageName,
        PackageManager.PackageInfoFlags.of(0)
    )

    val versionName = packageInfo.versionName ?: "?"
    val versionCode = PackageInfoCompat.getLongVersionCode(packageInfo)
    return "v$versionName ($versionCode)"
}