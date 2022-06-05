package com.thuypham.ptithcm.simplebaseapp.domain.repositoryimpl

import com.thuypham.ptithcm.simplebaseapp.api.MovieApi
import com.thuypham.ptithcm.simplebaseapp.api.wrapApiCall
import com.thuypham.ptithcm.simplebaseapp.data.local.dao.MovieDao
import com.thuypham.ptithcm.simplebaseapp.data.local.entity.MovieEntity
import com.thuypham.ptithcm.simplebaseapp.data.model.ResponseHandler
import com.thuypham.ptithcm.simplebaseapp.data.remote.MovieList
import com.thuypham.ptithcm.simplebaseapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDao: MovieDao,
) : MovieRepository {

    override suspend fun getNowPlaying(page: Int): ResponseHandler<MovieList> {
        return wrapApiCall { movieApi.getNowPlaying(page = page) }
    }


    override suspend fun getTopRated(): ResponseHandler<MovieList> {
        TODO("Not yet implemented")
    }

    override suspend fun getLocalNowPlaying(): ArrayList<MovieEntity> {
        TODO("Not yet implemented")
    }
}