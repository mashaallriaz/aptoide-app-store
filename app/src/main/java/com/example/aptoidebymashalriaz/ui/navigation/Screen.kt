package com.example.aptoidebymashalriaz.ui.navigation

import com.example.aptoidebymashalriaz.ui.navigation.RouteArgs.APP_ID

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Detail : Screen("detail/{$APP_ID}") {
        fun create(appId: Long) = "detail/$appId"
    }
}