package com.example.aptoidebymashalriaz.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.aptoidebymashalriaz.ui.navigation.NavArgs.APP_ID
import com.example.aptoidebymashalriaz.ui.navigation.Screen
import com.example.aptoidebymashalriaz.ui.screens.detail.AppDetailScreen
import com.example.aptoidebymashalriaz.ui.screens.home.HomeScreen
import com.example.aptoidebymashalriaz.ui.theme.AptoideTheme
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AptoideTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route
                    ) {
                        composable(route = Screen.Home.route) {
                            HomeScreen(onAppClick = { app ->
                                navController.navigate(Screen.Detail.create(app.id))
                            })
                        }
                        composable(
                            route = Screen.Detail.route,
                            arguments = listOf(navArgument(APP_ID) {
                                type = NavType.LongType
                            })
                        ) {
                            AppDetailScreen(onBackClick = {
                                navController.navigateUp()
                            })
                        }
                    }
                }
            }
        }
    }
}