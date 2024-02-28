package com.foodviewer.di

import com.foodviewer.data.FoodRepositoryImpl
import com.foodviewer.data.SearchCache
import com.foodviewer.data.SearchCacheImpl
import com.foodviewer.data.api.ApiHelper
import com.foodviewer.data.api.ApiHelperImpl
import com.foodviewer.data.api.FoodApi
import com.foodviewer.domain.model.FoodRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val url = "https://uih0b7slze.execute-api.us-east-1.amazonaws.com/"

@InstallIn(SingletonComponent::class)
@Module(includes = [RepoModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): FoodApi {
        return retrofit.create(FoodApi::class.java)
    }
}

@InstallIn(SingletonComponent::class)
@Module
interface RepoModule {
    @Binds
    @Singleton
    fun bindApiHelper(apiHelper: ApiHelperImpl): ApiHelper

    @Binds
    @Singleton
    fun bindCache(cache: SearchCacheImpl): SearchCache

    @Binds
    @Singleton
    fun bindRepo(repo: FoodRepositoryImpl): FoodRepository
}