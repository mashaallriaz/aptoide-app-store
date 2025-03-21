package com.example.aptoidebymashalriaz.ui.work

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.compose.ui.graphics.toArgb
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.aptoidebymashalriaz.R
import com.example.aptoidebymashalriaz.ui.MainActivity
import com.example.aptoidebymashalriaz.ui.theme.AptoideColor
import com.example.aptoidebymashalriaz.ui.utils.hasNotificationsPermission
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

/**
 * Background worker to fire a simple “New apps available” notification on a fixed interval.
 * Responsibilities:
 *  Creates its NotificationChannel if it doesn’t already exist (API26+).
 *  Builds and displays a notification pointing back to MainActivity.
 *  Checks runtime notification permission via a custom extension before posting.
 */
@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        showNotification()
        return Result.success()
    }

    private fun showNotification() {
        val intent = Intent(context, MainActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_app_logo)
            .setContentTitle(context.getString(R.string.notification_title))
            .setContentText(context.getString(R.string.notification_text))
            .setColor(AptoideColor.AptoidePrimary.toArgb())
            .setColorized(true)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        createNotificationChannel(context)

        /** Suppressing the lint warning because we explicitly verify notification permission
         *  using the hasNotificationsPermission() extension. */
        @SuppressLint("MissingPermission")
        if (context.hasNotificationsPermission()) {
            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)
        }
    }

    private fun createNotificationChannel(context: Context) {
        val name = context.getString(R.string.notification_channel_name_new_apps)
        val descriptionText = context.getString(R.string.notification_channel_description_new_apps)
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        const val CHANNEL_ID = "notification_channel"
        private const val NOTIFICATION_ID = 1001
        const val NOTIFICATION_WORK_NAME = "static_notification_work"
    }
}