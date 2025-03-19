package com.example.aptoidebymashalriaz.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apps")
data class AppEntity(
    @PrimaryKey val id: Long,
    val name: String?,
    val storeName: String?,
    val verCode: Int?,
    val size: Long?,
    val downloads: Int?,
    val rating: Double?,
    val icon: String?,
    val graphic: String?,
)