package com.thuypham.ptithcm.simplebaseapp.di

import com.thuypham.ptithcm.simplebaseapp.domain.repository.AuthenticationRepository
import com.thuypham.ptithcm.simplebaseapp.domain.repository.MovieRepository
import com.thuypham.ptithcm.simplebaseapp.domain.repositoryimpl.AuthenticationRepositoryImpl
import com.thuypham.ptithcm.simplebaseapp.domain.repositoryimpl.MovieRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.binds
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {


    /* Classical DSL way */
    single<MovieRepository> { MovieRepositoryImpl(get()) }
//    single<MovieRepository>  { TestMovieRepositoryImpl(get()) }

//    /* Constructor DSL way */
//    singleOf(::MovieRepositoryImpl) { bind<MovieRepository>() }


    scope(named(LOGIN_SCOPE)) {
        scoped<AuthenticationRepository> {
            AuthenticationRepositoryImpl(get())
        }
    }

}