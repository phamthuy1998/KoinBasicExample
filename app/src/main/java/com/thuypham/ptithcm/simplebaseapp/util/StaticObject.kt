package com.thuypham.ptithcm.simplebaseapp.util

import com.thuypham.ptithcm.simplebaseapp.util.ApiConstant.DEFAULT_LANGUAGE

object StaticObject {
    private var currentLanguage: String? = null

    fun setCurrentLanguage(language: String) {
        currentLanguage = language
    }

    fun getCurrentLanguage(): String {
        return if (currentLanguage.isNullOrBlank()) DEFAULT_LANGUAGE else currentLanguage!!
    }
}