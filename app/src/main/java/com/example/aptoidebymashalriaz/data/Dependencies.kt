package com.example.aptoidebymashalriaz.data

import android.content.Context
import androidx.room.Room
import com.example.aptoidebymashalriaz.BuildConfig
import com.example.aptoidebymashalriaz.data.remote.AptoideRemoteDataSource
import com.example.aptoidebymashalriaz.data.local.AptoideDao
import com.example.aptoidebymashalriaz.data.local.AptoideDatabase
import com.example.aptoidebymashalriaz.data.local.AptoideLocalDataSource
import com.example.aptoidebymashalriaz.data.remote.AptoideRepositoryImpl
import com.example.aptoidebymashalriaz.data.remote.AptoideService
import com.example.aptoidebymashalriaz.domain.repositories.AptoideRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Dependencies {
    @Provides
    fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) httpClient.addInterceptor(httpLoggingInterceptor)
        return httpClient.build()
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AptoideDatabase {
        return Room.databaseBuilder(appContext, AptoideDatabase::class.java, "aptoide_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideAppDao(database: AptoideDatabase): AptoideDao = database.aptoideDao()

    @Provides
    fun provideAptoideService(retrofit: Retrofit): AptoideService {
        return retrofit.create(AptoideService::class.java)
    }

    @Provides
    fun provideAptoideRepository(
        remoteDataSource: AptoideRemoteDataSource,
        localDataSource: AptoideLocalDataSource
    ): AptoideRepository {
        return AptoideRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Provides
    fun provideAptoideRemoteDataSource(service: AptoideService): AptoideRemoteDataSource {
        return AptoideRemoteDataSource(service)
    }
}