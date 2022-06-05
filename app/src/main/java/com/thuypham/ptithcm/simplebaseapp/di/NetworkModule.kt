package com.thuypham.ptithcm.simplebaseapp.di

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.thuypham.ptithcm.simplebaseapp.BuildConfig
import com.thuypham.ptithcm.simplebaseapp.api.MovieApi
import com.thuypham.ptithcm.simplebaseapp.util.ApiConstant
import com.thuypham.ptithcm.simplebaseapp.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstant.BASE_MOVIE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient {
        val builder =
            OkHttpClient.Builder()
                .callTimeout(Constant.CONNECTION_TIME_OUT_SECOND, TimeUnit.SECONDS)
                .readTimeout(Constant.CONNECTION_TIME_OUT_SECOND, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            builder.also {
                val logger = HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
                it.addInterceptor(logger)
            }
        }
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideGithubService(): MovieApi {
        return provideRetrofit(provideClient())
            .create(MovieApi::class.java)
    }

}