package com.thuypham.ptithcm.simplebaseapp.di

import com.thuypham.ptithcm.simplebaseapp.domain.repository.AuthenticationRepository
import com.thuypham.ptithcm.simplebaseapp.domain.repository.MovieRepository
import com.thuypham.ptithcm.simplebaseapp.domain.repositoryimpl.AuthenticationRepositoryImpl
import com.thuypham.ptithcm.simplebaseapp.domain.repositoryimpl.MovieRepositoryImpl
import com.thuypham.ptithcm.simplebaseapp.extension.LOGIN_SCOPE
import com.thuypham.ptithcm.simplebaseapp.ui.fragment.LoginFragment
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {


    /* Classical DSL way */

    // Will match type ServiceImp only
    // single { MovieRepositoryImpl(get()) }
    // Will match type MovieRepository only
    // single<MovieRepository> { MovieRepositoryImpl(get()) }

//    /* Constructor DSL way */
    singleOf(::MovieRepositoryImpl) { bind<MovieRepository>() }

//    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get()) }

    scope<LoginFragment>{
        scoped<AuthenticationRepository> {
            AuthenticationRepositoryImpl(get())
        }
    }

}