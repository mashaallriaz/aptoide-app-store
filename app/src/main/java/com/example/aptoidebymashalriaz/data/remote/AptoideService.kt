package com.example.aptoidebymashalriaz.data.remote

import retrofit2.http.GET

interface AptoideService {
    @GET("api/6/bulkRequest/api_list/listApps")
    suspend fun getAllApps(): ApiResponse<GetAllAppsResponse>
}