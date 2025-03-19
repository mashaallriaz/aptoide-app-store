package com.example.aptoidebymashalriaz.data.remote

import javax.inject.Inject

class AptoideRemoteDataSource @Inject constructor(private val service: AptoideService) {
    suspend fun fetchAllApps(): ApiResponse<GetAllAppsResponse> {
        return service.getAllApps()
    }
}