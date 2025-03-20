package com.example.aptoidebymashalriaz.domain.models

import java.util.Locale

/**
 * This is the domain model used by the use‑cases (interactors) and ViewModels to drive UI state,
 * keeping presentation and business rules agnostic of network or persistence details.
 */
data class App(
    val id: Long,
    val name: String?,
    val storeName: String?,
    val verCode: Int?,
    val size: Long?,
    val downloads: Int?,
    val rating: Double?,
    val icon: String?,
    val graphic: String?,
) {
    val formattedDownloads: String
        get() {
            val count = downloads ?: 0
            if (count < 1_000) return count.toString()

            val suffixes = arrayOf("K", "M", "B", "T")
            var value = count.toDouble()
            var index = 0

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

    val formattedRating: String
        get() {
            return if ((rating ?: 0.0) > 0.0) rating.toString() else "--"
        }

    /** Assuming that the size is in bytes. */
    val formattedSize: String
        get() {
            val bytes = size ?: return ""
            if (bytes < 1024) return "$bytes B"

            val units = arrayOf("KB", "MB", "GB", "TB")
            var value = bytes.toDouble()
            var index = 0

            while (value >= 1024 && index < units.lastIndex) {
                value /= 1024
                index++
            }

            return if (value % 1.0 == 0.0)
                "${value.toInt()} ${units[index]}"
            else
                String.format(Locale.US, "%.1f %s", value, units[index])
        }
}