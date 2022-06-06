package com.thuypham.ptithcm.simplebaseapp.domain.repositoryimpl

import com.thuypham.ptithcm.simplebaseapp.api.MovieApi
import com.thuypham.ptithcm.simplebaseapp.api.wrapApiCall
import com.thuypham.ptithcm.simplebaseapp.data.local.dao.MovieDao
import com.thuypham.ptithcm.simplebaseapp.data.local.entity.MovieEntity
import com.thuypham.ptithcm.simplebaseapp.data.local.entity.movieToMovieEntity
import com.thuypham.ptithcm.simplebaseapp.data.model.ResponseHandler
import com.thuypham.ptithcm.simplebaseapp.data.remote.Movie
import com.thuypham.ptithcm.simplebaseapp.data.remote.MovieList
import com.thuypham.ptithcm.simplebaseapp.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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

    override suspend fun getLocalNowPlaying(): List<MovieEntity>? {
        return withContext(Dispatchers.IO) {
            movieDao.getNowPlaying()
        }
    }

    override suspend fun insertMovie(movies: List<Movie?>) {
        withContext(Dispatchers.IO) {
            movies.forEach {
                val movieEntity = it?.movieToMovieEntity()
                movieEntity?.let { movieDao.insertMovie(movieEntity) }
            }
        }
    }
}