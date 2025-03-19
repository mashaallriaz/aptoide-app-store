package com.example.aptoidebymashalriaz.domain.models

sealed class Result<out T> {
    data class Loading<out T>(val data: T? = null) : Result<T>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error<out T>(val message: String, val data: T? = null) : Result<T>()
}