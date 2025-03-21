package com.example.aptoidebymashalriaz.domain.repository

import com.example.aptoidebymashalriaz.domain.models.App

interface AptoideRepository {
    suspend fun fetchAllApps(): List<App>
    suspend fun getAllCachedApps(): List<App>
    suspend fun saveApps(apps: List<App>)
    suspend fun getAppById(id: Long): App?
}