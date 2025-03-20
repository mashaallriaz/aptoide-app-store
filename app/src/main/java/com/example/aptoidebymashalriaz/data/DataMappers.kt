package com.example.aptoidebymashalriaz.data

import com.example.aptoidebymashalriaz.data.local.AppEntity
import com.example.aptoidebymashalriaz.data.remote.AppResponse
import com.example.aptoidebymashalriaz.domain.models.App

object DataMappers {
    fun AppEntity.toApp(): App {
        return App(
            id = this.id,
            name = this.name,
            storeName = this.storeName,
            verCode = this.verCode,
            size = this.size,
            downloads = this.downloads,
            rating = this.rating,
            icon = this.icon,
            graphic = this.graphic
        )
    }

    private fun App.toEntity(): AppEntity {
        return AppEntity(
            id = this.id,
            name = this.name,
            storeName = this.storeName,
            verCode = this.verCode,
            size = this.size,
            downloads = this.downloads,
            rating = this.rating,
            icon = this.icon,
            graphic = this.graphic
        )
    }

    private fun AppResponse.toApp(): App {
        return App(
            id = this.id,
            name = this.name,
            storeName = this.storeName,
            verCode = this.verCode,
            size = this.size,
            downloads = this.downloads,
            rating = this.rating,
            icon = this.icon,
            graphic = this.graphic
        )
    }

    fun List<AppResponse?>?.toAppListFromResponse(): List<App> {
        return this?.mapNotNull { it?.toApp() } ?: emptyList()
    }

    fun List<AppEntity>.toAppListFromEntity(): List<App> {
        return this.map { it.toApp() }
    }

    fun List<App>.toEntityList(): List<AppEntity> {
        return this.map { it.toEntity() }
    }
}