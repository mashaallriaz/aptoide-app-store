package com.example.aptoidebymashalriaz.domain.interactors

import com.example.aptoidebymashalriaz.data.aptoide.App
import com.example.aptoidebymashalriaz.data.aptoide.AptoideRepository
import javax.inject.Inject

class GetAllApps @Inject constructor(private val repository: AptoideRepository) :
    FlowResourceInteractor<Unit, List<App?>?>() {
    override suspend fun doWork(params: Unit): List<App?>? {
        return repository.getAllApps().responses.appsList?.datasets?.all?.data?.list
    }
}