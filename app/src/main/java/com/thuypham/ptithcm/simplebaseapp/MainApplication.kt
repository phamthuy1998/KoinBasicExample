package com.thuypham.ptithcm.simplebaseapp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.thuypham.ptithcm.simplebaseapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class MainApplication : Application() {

    companion object {
        lateinit var instance: MainApplication
        fun applicationContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        instance = this
        initKoin()
        super.onCreate()
    }

    private fun initKoin() {
        /**
         * Other way to start koin
         */
        /*
         // declare koinApplication
         val koinApplication = koinApplication {
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
         startKoin(koinApplication)
     */

        /**
         * Start a Koin Application as StandAlone
         */
        // Start Koin
        startKoin {
            // describe log level for Koin
            logger(AndroidLogger(Level.DEBUG))

            // Or use default android log: androidLogger to set log with log Level.INFO
            // androidLogger()

            createEagerInstances()

            // load HashMap properties into Koin container
            // properties(mapOf...)

            // load properties from given file into Koin container
            // fileProperties()

            allowOverride(true)
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