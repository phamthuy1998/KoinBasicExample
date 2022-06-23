package com.thuypham.ptithcm.simplebaseapp.di

import com.thuypham.ptithcm.simplebaseapp.ui.activity.SplashActivity
import com.thuypham.ptithcm.simplebaseapp.viewmodel.LoginViewModel
import com.thuypham.ptithcm.simplebaseapp.viewmodel.MainViewModel
import com.thuypham.ptithcm.simplebaseapp.viewmodel.MovieDetailViewModel
import com.thuypham.ptithcm.simplebaseapp.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


const val LOGIN_SCOPE = "LOGIN_SCOPE"

val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { MovieDetailViewModel() }

    scope(named(LOGIN_SCOPE)) {
        viewModel { params -> LoginViewModel(params.get(), get(), get(), get()) }
    }

    /**
     * Class Typed Scope
     * --> Id of scope will be SplashActivity::class.java.name
     */
    scope<SplashActivity> {
        viewModel { SplashViewModel(get()) }
    }

}
