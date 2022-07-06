package com.thuypham.ptithcm.simplebaseapp.di

import com.thuypham.ptithcm.simplebaseapp.ui.activity.SplashActivity
import com.thuypham.ptithcm.simplebaseapp.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


const val LOGIN_SCOPE = "LOGIN_SCOPE"
const val LOGIN_SCOPE2 = "LOGIN_SCOPE2"

val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { MovieDetailViewModel() }

    scope(named(LOGIN_SCOPE)) {
        viewModel { params -> LoginViewModel(params.get(), get(), get(), get()) }
        viewModel { ForgotPasswordViewModel() }
    }

    // For test multi scope in one fragment
    scope(named(LOGIN_SCOPE2)) {
        viewModel { MultiScopeViewModel() }
    }

    /**
     * Class Typed Scope
     * --> Id of scope will be SplashActivity::class.java.name
     */
    scope<SplashActivity> {
        viewModel { SplashViewModel(get()) }
    }

}
