package com.example.aptoidebymashalriaz.domain.interactors

import com.example.aptoidebymashalriaz.domain.models.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class FlowResourceInteractor<in P, T> {
    operator fun invoke(params: P): Flow<Result<T>> = flow {
        val cachedData = getCachedData(params)
        emit(Result.Loading(cachedData))
        try {
            val freshData = doWork(params)
            saveData(params, freshData)
            val updatedData = getCachedData(params) ?: freshData
            emit(Result.Success(updatedData))
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: "Unknown error"))
        }
    }

    protected abstract suspend fun doWork(params: P): T
    protected open suspend fun getCachedData(params: P): T? = null
    protected open suspend fun saveData(params: P, data: T) {}
}