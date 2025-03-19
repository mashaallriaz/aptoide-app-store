package com.example.aptoidebymashalriaz.data.aptoide

import com.example.aptoidebymashalriaz.data.ApiResponse
import retrofit2.http.GET

interface AptoideService {
    @GET("api/6/bulkRequest/api_list/listApps")
    suspend fun getAllApps(): ApiResponse<GetAllAppsResponse>
}