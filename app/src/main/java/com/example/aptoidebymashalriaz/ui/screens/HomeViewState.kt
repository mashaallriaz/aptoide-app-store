package com.example.aptoidebymashalriaz.ui.screens

import com.example.aptoidebymashalriaz.domain.models.App

data class HomeViewState(
    val loading: Boolean = true,
    val apps: List<App> = emptyList()
)