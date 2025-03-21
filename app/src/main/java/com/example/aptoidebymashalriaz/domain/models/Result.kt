package com.example.aptoidebymashalriaz.domain.models

/**
 * A generic wrapper for representing asynchronous data-loading states in UI:
 *  Loading — indicates work is in progress (optionally exposing cached)
 *  Success — indicates the operation finished successfully with non-null data
 *  Error   — indicates the operation failed, carrying an error message and optional fallback data
 */
sealed class Result<out T> {
    data class Loading<out T>(val data: T? = null) : Result<T>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error<out T>(val message: String, val data: T? = null) : Result<T>()
}