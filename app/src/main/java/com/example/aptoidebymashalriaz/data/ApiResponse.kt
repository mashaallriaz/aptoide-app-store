package com.example.aptoidebymashalriaz.data

data class ApiResponse<T>(
    val status: String,
    val responses: T
)