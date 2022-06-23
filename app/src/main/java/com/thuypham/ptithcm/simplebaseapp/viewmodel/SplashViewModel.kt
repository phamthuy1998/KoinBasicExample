package com.thuypham.ptithcm.simplebaseapp.viewmodel

import com.thuypham.ptithcm.simplebaseapp.base.BaseViewModel
import com.thuypham.ptithcm.simplebaseapp.data.IStorage
import com.thuypham.ptithcm.simplebaseapp.data.SharedPreferencesStorage

class SplashViewModel(private val sharedPref: IStorage) : BaseViewModel() {

    // Testing
    fun isUserLogin(): Boolean {
        return sharedPref.getBoolean(SharedPreferencesStorage.IS_USER_LOGIN, false)
    }
}