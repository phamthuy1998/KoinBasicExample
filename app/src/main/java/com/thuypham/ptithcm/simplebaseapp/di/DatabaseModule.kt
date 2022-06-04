package com.thuypham.ptithcm.simplebaseapp.di

import com.google.gson.Gson
import com.thuypham.ptithcm.simplebaseapp.data.local.AppDatabase
import com.thuypham.ptithcm.simplebaseapp.data.local.IStorage
import com.thuypham.ptithcm.simplebaseapp.data.local.SharedPreferencesStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { AppDatabase.getInstance(get()) }

//    single<IStorage> { SharedPreferencesStorage(androidContext()) }
    single { Gson() }
    single<IStorage> { SharedPreferencesStorage(get(), get()) }
}
