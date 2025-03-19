package com.example.aptoidebymashalriaz.domain

import com.example.aptoidebymashalriaz.data.ApiResponse
import com.example.aptoidebymashalriaz.data.aptoide.AptoideRepository
import com.example.aptoidebymashalriaz.data.aptoide.GetAllAppsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllApps @Inject constructor(private val repository: AptoideRepository) {
    fun getAllApps(): Flow<ApiResponse<GetAllAppsResponse>> = flow {
        emit(repository.getAllApps())
    }
}