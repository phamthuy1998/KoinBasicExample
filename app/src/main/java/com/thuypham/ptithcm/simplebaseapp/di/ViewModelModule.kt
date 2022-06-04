package com.thuypham.ptithcm.simplebaseapp.di

import androidx.lifecycle.SavedStateHandle
import com.thuypham.ptithcm.simplebaseapp.extension.LOGIN_SCOPE
import com.thuypham.ptithcm.simplebaseapp.extension.getLoginScope
import com.thuypham.ptithcm.simplebaseapp.ui.fragment.LoginFragment
import com.thuypham.ptithcm.simplebaseapp.viewmodel.LoginViewModel
import com.thuypham.ptithcm.simplebaseapp.viewmodel.MainViewModel
import com.thuypham.ptithcm.simplebaseapp.viewmodel.MovieDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { MovieDetailViewModel() }

    scope<LoginFragment>{
        viewModel { (handle: SavedStateHandle) -> LoginViewModel(handle, get(), get(), get()) }
    }
}
