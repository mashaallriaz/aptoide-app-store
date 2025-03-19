package com.example.aptoidebymashalriaz.data.local

import javax.inject.Inject

class AptoideLocalDataSource @Inject constructor(private val aptoideDao: AptoideDao) {
    suspend fun saveApps(apps: List<AppEntity>) {
        aptoideDao.insertAll(apps)
    }

    suspend fun getApps(): List<AppEntity> {
        return aptoideDao.getAllApps()
    }
}