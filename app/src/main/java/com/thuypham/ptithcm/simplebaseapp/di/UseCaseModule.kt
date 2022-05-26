package com.thuypham.ptithcm.simplebaseapp.di

import com.thuypham.ptithcm.simplebaseapp.domain.usecase.movie.GetNowPlayingUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetNowPlayingUseCase(get()) }
}