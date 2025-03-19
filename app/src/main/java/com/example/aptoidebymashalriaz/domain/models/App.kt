package com.example.aptoidebymashalriaz.domain.models

data class App(
    val id: Long,
    val name: String?,
    val storeName: String?,
    val verCode: Int?,
    val size: Long?,
    val downloads: Int?,
    val rating: Double?,
    val icon: String?,
    val graphic: String?,
)