package com.example.aptoidebymashalriaz.data.repository

import com.example.aptoidebymashalriaz.data.mappers.DataMappers.toApp
import com.example.aptoidebymashalriaz.data.mappers.DataMappers.toAppListFromEntity
import com.example.aptoidebymashalriaz.data.mappers.DataMappers.toAppListFromResponse
import com.example.aptoidebymashalriaz.data.mappers.DataMappers.toEntityList
import com.example.aptoidebymashalriaz.data.local.AptoideLocalDataSource
import com.example.aptoidebymashalriaz.data.remote.AptoideRemoteDataSource
import com.example.aptoidebymashalriaz.domain.models.App
import com.example.aptoidebymashalriaz.domain.repository.AptoideRepository
import javax.inject.Inject

class AptoideRepositoryImpl @Inject constructor(
    private val remoteDataSource: AptoideRemoteDataSource,
    private val localDataSource: AptoideLocalDataSource
) : AptoideRepository {

    override suspend fun fetchAllApps(): List<App> {
        return remoteDataSource.fetchAllApps().responses.appsList?.datasets?.all?.data?.list.toAppListFromResponse()
    }

    override suspend fun getAllCachedApps(): List<App> {
        return localDataSource.getApps().toAppListFromEntity()
    }

    override suspend fun saveApps(apps: List<App>) {
        localDataSource.saveApps(apps.toEntityList())
    }

    override suspend fun getAppById(id: Long): App? {
        return localDataSource.getAppById(id)?.toApp()
    }
}