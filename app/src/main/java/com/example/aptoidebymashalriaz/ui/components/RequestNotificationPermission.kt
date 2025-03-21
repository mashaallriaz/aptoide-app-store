package com.example.aptoidebymashalriaz.ui.components

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.aptoidebymashalriaz.ui.utils.hasNotificationsPermission

@Composable
fun RequestNotificationPermission(dismissPrompt: () -> Unit) {
    val context = LocalContext.current
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { dismissPrompt() }

    LaunchedEffect(Unit) {
        if (!context.hasNotificationsPermission() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        } // For API < 33, hasNotificationPermission() always returns true so no additional branch is needed.
    }
}