package com.thuypham.ptithcm.simplebaseapp.di

import android.content.Context
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.thuypham.ptithcm.simplebaseapp.BuildConfig
import com.thuypham.ptithcm.simplebaseapp.api.MovieApi
import com.thuypham.ptithcm.simplebaseapp.util.ApiConstant
import com.thuypham.ptithcm.simplebaseapp.util.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideGson() }
    single { provideClient() }
    single { createService(get(), MovieApi::class.java) }
    single { provideRetrofit(get()) }

    // Another way
//    factoryOf(::provideRetrofit)

//    factory { createService(get(named(Constant.MAIN_API)), MovieApi::class.java) }
//    scope<MovieApi> {
//        factory { provideRetrofit(get()) }
//    }
}


inline fun <reified T> createService(retrofit: Retrofit, apiService: Class<T>): T {
    return retrofit.create(apiService)
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(ApiConstant.BASE_MOVIE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(client)
        .build()
}

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

fun provideGson(): Gson {
    return Gson()
}
