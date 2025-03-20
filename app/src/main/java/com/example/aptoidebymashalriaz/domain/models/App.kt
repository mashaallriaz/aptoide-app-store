package com.example.aptoidebymashalriaz.domain.models

/**
 * This is the domain model used by the use‑cases (interactors) and ViewModels to drive UI state,
 * keeping presentation and business rules agnostic of network or persistence details.
 */
data class App(
    val id: Long,
    val name: String?,
    val storeName: String?,
    val verName: String?,
    val size: Long?,
    val downloads: Int?,
    val rating: Double?,
    val icon: String?,
    val graphic: String?,
)