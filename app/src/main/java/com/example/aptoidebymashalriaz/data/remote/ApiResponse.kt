package com.example.aptoidebymashalriaz.data.remote

data class ApiResponse<T>(
    val status: String,
    val responses: T
)