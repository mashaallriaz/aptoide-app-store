package com.example.aptoidebymashalriaz.data.aptoide

import com.example.aptoidebymashalriaz.data.ApiResponse
import javax.inject.Inject

class AptoideRepository @Inject constructor(private val remoteDataSource: AptoideRemoteDataSource) {
    suspend fun getAllApps(): ApiResponse<GetAllAppsResponse> {
        return remoteDataSource.fetchAllApps()
    }
}