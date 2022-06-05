package com.thuypham.ptithcm.simplebaseapp.di

import android.content.Context
import com.thuypham.ptithcm.simplebaseapp.data.local.AppDatabase
import com.thuypham.ptithcm.simplebaseapp.data.local.IStorage
import com.thuypham.ptithcm.simplebaseapp.data.local.SharedPreferencesStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {
    @Provides
    @Singleton
    fun provideSharedPrf(sharedPrf: SharedPreferencesStorage): IStorage = sharedPrf

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideMovieDao(database: AppDatabase) = database.getMovieDao()

}