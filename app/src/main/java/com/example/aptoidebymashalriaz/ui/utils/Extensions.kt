package com.example.aptoidebymashalriaz.ui.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import java.util.Locale

/**
 * Converts an integer count into a human‑friendly abbreviated string:
 * • Under 1,000 → returns raw number (e.g. “532”)
 * • ≥1,000 → appends “K”, “M”, “B”, “T” (e.g. “1.2K”, “10M”). Drops “.0” when exact (e.g. “2K”).
 */
fun Int?.getFormattedDownloads(): String {
    if (this == null) return ""
    if (this < 1_000) return toString()
    val suffixes = arrayOf("K", "M", "B", "T")
    var value = toDouble()
    var index = -1
    while (value >= 1_000 && index < suffixes.lastIndex) {
        value /= 1_000
        index++
    }
    return if (value % 1.0 == 0.0) {
        "${value.toInt()}${suffixes[index]}"
    } else {
        String.format(Locale.getDefault(), "%.1f%s", value, suffixes[index])
    }
}

/**
 * Formats a nullable rating (Double) as a string:
 * >0 → numeric string (e.g. “4.5”)
 * ≤0 or null → placeholder “--”
 */
fun Double?.getFormattedRating(): String =
    if ((this ?: 0.0) > 0.0) this.toString() else "--"

/**
 * Converts a byte count into a human‑readable file size string:
 * Under 1,024 bytes → “X B”
 * ≥1,024 → “KB”, “MB”, “GB”, “TB” (e.g. “1.5 MB”, “2 GB”).
 * Drops decimals when exact.
 */
fun Long?.getFormattedSize(): String {
    if (this == null) return ""
    if (this < 1024) return "$this B"
    val units = arrayOf("KB", "MB", "GB", "TB")
    var value = toDouble()
    var index = -1
    while (value >= 1024 && index < units.lastIndex) {
        value /= 1024
        index++
    }
    return if (value % 1.0 == 0.0)
        "${value.toInt()} ${units[index]}"
    else
        String.format(Locale.getDefault(), "%.1f %s", value, units[index])
}

fun Context.hasNotificationsPermission(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        ContextCompat.checkSelfPermission(
            this, Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    } else {
        true
    }
}