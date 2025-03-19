package com.example.aptoidebymashalriaz.data

import com.example.aptoidebymashalriaz.BuildConfig
import com.example.aptoidebymashalriaz.data.aptoide.AptoideRemoteDataSource
import com.example.aptoidebymashalriaz.data.aptoide.AptoideRepository
import com.example.aptoidebymashalriaz.data.aptoide.AptoideService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
    fun provideAptoideService(retrofit: Retrofit): AptoideService {
        return retrofit.create(AptoideService::class.java)
    }

    @Provides
    fun provideAptoideRepository(remoteDataSource: AptoideRemoteDataSource): AptoideRepository {
        return AptoideRepository(remoteDataSource)
    }

    @Provides
    fun provideAptoideRemoteDataSource(service: AptoideService): AptoideRemoteDataSource {
        return AptoideRemoteDataSource(service)
    }
}