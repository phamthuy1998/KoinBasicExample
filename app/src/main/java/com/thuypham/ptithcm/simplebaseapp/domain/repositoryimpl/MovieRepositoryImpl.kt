package com.thuypham.ptithcm.simplebaseapp.domain.repositoryimpl

import com.thuypham.ptithcm.simplebaseapp.api.MovieApi
import com.thuypham.ptithcm.simplebaseapp.api.wrapApiCall
import com.thuypham.ptithcm.simplebaseapp.data.model.ResponseHandler
import com.thuypham.ptithcm.simplebaseapp.data.remote.MovieList
import com.thuypham.ptithcm.simplebaseapp.domain.repository.MovieRepository

class MovieRepositoryImpl(private val movieApi: MovieApi) : MovieRepository {

    override suspend fun getNowPlaying(page: Int): ResponseHandler<MovieList> {
        return wrapApiCall { movieApi.getNowPlaying(page = page) }
    }


    override suspend fun getTopRated(): ResponseHandler<MovieList> {
        TODO("Not yet implemented")
    }
}