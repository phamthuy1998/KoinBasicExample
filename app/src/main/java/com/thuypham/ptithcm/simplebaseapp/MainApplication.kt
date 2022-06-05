package com.thuypham.ptithcm.simplebaseapp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.thuypham.ptithcm.simplebaseapp.data.local.IStorage
import com.thuypham.ptithcm.simplebaseapp.data.local.SharedPreferencesStorage
import com.thuypham.ptithcm.simplebaseapp.util.ApiConstant
import com.thuypham.ptithcm.simplebaseapp.util.StaticObject
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class MainApplication : Application() {

    @Inject
    lateinit var pref: IStorage

    companion object {
        lateinit var instance: MainApplication
        fun applicationContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
        getCurrentLanguage()
    }

    private fun getCurrentLanguage() {
        val currentLanguage = pref.getString(SharedPreferencesStorage.PREF_CURRENT_LANGUAGE, ApiConstant.DEFAULT_LANGUAGE)
        StaticObject.setCurrentLanguage(currentLanguage ?: ApiConstant.DEFAULT_LANGUAGE)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        // Prevent app throw exception too much method (over 64k method)
        MultiDex.install(this)
    }
}