package com.thuypham.ptithcm.simplebaseapp.di

import com.thuypham.ptithcm.simplebaseapp.domain.repository.AuthenticationRepository
import com.thuypham.ptithcm.simplebaseapp.domain.repository.MovieRepository
import com.thuypham.ptithcm.simplebaseapp.domain.repositoryimpl.AuthenticationRepositoryImpl
import com.thuypham.ptithcm.simplebaseapp.domain.repositoryimpl.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun bindAuthenticationRepo(authRepositoryImpl: AuthenticationRepositoryImpl): AuthenticationRepository = authRepositoryImpl

    @Provides
    @Singleton
    fun bindMovieRepo(authRepositoryImpl: MovieRepositoryImpl): MovieRepository = authRepositoryImpl

}