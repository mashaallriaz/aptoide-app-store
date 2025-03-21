package com.example.aptoidebymashalriaz.domain.interactors

import com.example.aptoidebymashalriaz.domain.models.App
import com.example.aptoidebymashalriaz.domain.repository.AptoideRepository
import javax.inject.Inject

class GetAppById @Inject constructor(private val repository: AptoideRepository) :
    FlowResourceInteractor<Long, App?>() {

    override suspend fun doWork(params: Long): App? {
        return repository.getAppById(params)
    }
}