package com.example.aptoidebymashalriaz.data.remote

import com.example.aptoidebymashalriaz.data.DataMappers.toAppListFromEntity
import com.example.aptoidebymashalriaz.data.DataMappers.toAppListFromResponse
import com.example.aptoidebymashalriaz.data.DataMappers.toEntityList
import com.example.aptoidebymashalriaz.data.local.AptoideLocalDataSource
import com.example.aptoidebymashalriaz.domain.models.App
import javax.inject.Inject

class AptoideRepository @Inject constructor(
    private val remoteDataSource: AptoideRemoteDataSource,
    private val localDataSource: AptoideLocalDataSource
) {
    suspend fun fetchAllApps(): List<App> {
        return remoteDataSource.fetchAllApps().responses.appsList?.datasets?.all?.data?.list.toAppListFromResponse()
    }

    suspend fun getAllCachedApps(): List<App> {
        return localDataSource.getApps().toAppListFromEntity()
    }

    suspend fun saveApps(appsList: List<App>) {
        localDataSource.saveApps(appsList.toEntityList())
    }
}