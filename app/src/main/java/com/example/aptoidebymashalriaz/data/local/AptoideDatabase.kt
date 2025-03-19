package com.example.aptoidebymashalriaz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AppEntity::class], version = 1, exportSchema = false)
abstract class AptoideDatabase : RoomDatabase() {
    abstract fun aptoideDao(): AptoideDao
}