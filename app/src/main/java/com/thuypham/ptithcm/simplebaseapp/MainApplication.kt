package com.thuypham.ptithcm.simplebaseapp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.thuypham.ptithcm.simplebaseapp.data.local.IStorage
import com.thuypham.ptithcm.simplebaseapp.data.local.SharedPreferencesStorage
import com.thuypham.ptithcm.simplebaseapp.di.*
import com.thuypham.ptithcm.simplebaseapp.util.ApiConstant
import com.thuypham.ptithcm.simplebaseapp.util.StaticObject
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.logger.AndroidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.unloadKoinModules
import org.koin.core.logger.Level
import org.koin.fileProperties


class MainApplication : Application() {
    private val pref: IStorage by inject()

    companion object {
        lateinit var instance: MainApplication
        fun applicationContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        instance = this
        initKoin()
        super.onCreate()
        getCurrentLanguage()
    }

    private fun getCurrentLanguage() {
        val currentLanguage = pref.getString(SharedPreferencesStorage.PREF_CURRENT_LANGUAGE, ApiConstant.DEFAULT_LANGUAGE)
        StaticObject.setCurrentLanguage(currentLanguage ?: ApiConstant.DEFAULT_LANGUAGE)
    }

    private fun initKoin() {
        // Start Koin
        startKoin {
            // describe log level for Koin
            logger(AndroidLogger(Level.DEBUG))

            // Or use androidLogger to set log with log Level.INFO
            // androidLogger()

            createEagerInstances()

            // load HashMap properties into Koin container
            // properties(mapOf...)

            // load properties from given file into Koin container
            // fileProperties()

            androidContext(applicationContext)
            fragmentFactory()
            modules(
                databaseModule,
                repositoryModule,
                viewModelModule,
                useCaseModule,
                utilityModule,
                networkModule
            )
        }

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        // Prevent app throw exception too much method (over 64k method)
        MultiDex.install(this)
    }
}