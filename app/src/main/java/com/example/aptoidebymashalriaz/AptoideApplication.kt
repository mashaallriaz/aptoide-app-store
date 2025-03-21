package com.example.aptoidebymashalriaz

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.aptoidebymashalriaz.ui.work.NotificationWorker
import com.example.aptoidebymashalriaz.ui.work.NotificationWorker.Companion.NOTIFICATION_WORK_NAME
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class AptoideApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            NOTIFICATION_WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            PeriodicWorkRequestBuilder<NotificationWorker>(30, TimeUnit.MINUTES)
                // .setInitialDelay(20, TimeUnit.SECONDS) // Uncomment this to quickly test the notification worker
                .build()
        )
    }
}