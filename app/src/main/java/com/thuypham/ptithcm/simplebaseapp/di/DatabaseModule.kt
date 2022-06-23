package com.thuypham.ptithcm.simplebaseapp.di

import com.google.gson.Gson
import com.thuypham.ptithcm.simplebaseapp.data.IStorage
import com.thuypham.ptithcm.simplebaseapp.data.SharedPreferencesStorage
import com.thuypham.ptithcm.simplebaseapp.data.local.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { AppDatabase.getInstance(get()) }
    single { Gson() }
    single<IStorage> { SharedPreferencesStorage(get(), get()) }
}
