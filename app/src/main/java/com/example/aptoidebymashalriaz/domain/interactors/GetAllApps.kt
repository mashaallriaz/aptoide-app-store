package com.example.aptoidebymashalriaz.domain.interactors

import com.example.aptoidebymashalriaz.data.remote.AptoideRepository
import com.example.aptoidebymashalriaz.domain.models.App
import javax.inject.Inject

class GetAllApps @Inject constructor(private val repository: AptoideRepository) :
    FlowResourceInteractor<Unit, List<App>>() {

    override suspend fun doWork(params: Unit): List<App> {
        return repository.fetchAllApps()
    }

    override suspend fun getCachedData(params: Unit): List<App> {
        return repository.getAllCachedApps()
    }

    override suspend fun saveData(params: Unit, data: List<App>) {
        repository.saveApps(data)
    }
}