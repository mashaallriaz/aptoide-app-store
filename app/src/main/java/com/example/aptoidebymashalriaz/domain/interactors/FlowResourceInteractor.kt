package com.example.aptoidebymashalriaz.domain.interactors

import com.example.aptoidebymashalriaz.domain.models.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * A reusable resource interactor that handles the common pattern of:
 *   • emitting cached data immediately (if any),
 *   • fetching fresh data (example: from network),
 *   • saving that fresh data locally,
 *   • then emitting the up‑to‑date data,
 *   • and finally reporting errors if the fetch fails.
 *
 * This class returns a Flow<Result<T>> with three possible states:
 *   • Loading — emits any cached data while the remote fetch is in progress
 *   • Success — emits the newly fetched (or reloaded cached) data on success
 *   • Error   — emits an error message if the fetch fails (cached data remains available)
 *
 * To use it:
 *   • Subclass and implement doWork(params) to perform the remote/data fetch.
 *   • Override getCachedData(params) if you want to return locally cached data (default = null).
 *   • Override saveData(params, data) to persist the fetched result (default = no‑op).
 */
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
    }.flowOn(Dispatchers.IO)

    protected abstract suspend fun doWork(params: P): T
    protected open suspend fun getCachedData(params: P): T? = null
    protected open suspend fun saveData(params: P, data: T) {}
}