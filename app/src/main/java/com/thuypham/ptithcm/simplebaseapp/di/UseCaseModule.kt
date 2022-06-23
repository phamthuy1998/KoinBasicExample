package com.thuypham.ptithcm.simplebaseapp.di

import com.thuypham.ptithcm.simplebaseapp.domain.usecase.authentication.GetNewTokenUseCase
import com.thuypham.ptithcm.simplebaseapp.domain.usecase.authentication.LoginUseCase
import com.thuypham.ptithcm.simplebaseapp.domain.usecase.movie.GetNowPlayingUseCase
import com.thuypham.ptithcm.simplebaseapp.extension.getLoginScope
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetNowPlayingUseCase(get()) }


    // Test scope koin
    scope(named(LOGIN_SCOPE)) {
        scoped {
            LoginUseCase(getLoginScope().get())
//            // or
//            LoginUseCase(get())
        }
        scoped {
            GetNewTokenUseCase(getLoginScope().get())
        }
    }

//    scope(named(LOGIN_SCOPE)) {
//        scoped {
//            LoginUseCase(getLoginScope().get())
//        }
//        scoped {
//            GetNewTokenUseCase(getLoginScope().get())
//        }
//    }


//    factory { LoginUseCase(get()) }
//    factory { GetNewTokenUseCase(get()) }
}