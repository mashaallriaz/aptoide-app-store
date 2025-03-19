package com.example.aptoidebymashalriaz.data.aptoide

import com.example.aptoidebymashalriaz.data.ApiResponse
import javax.inject.Inject

class AptoideRemoteDataSource @Inject constructor(private val service: AptoideService) {
    suspend fun fetchAllApps(): ApiResponse<GetAllAppsResponse> {
        return service.getAllApps()
    }
}