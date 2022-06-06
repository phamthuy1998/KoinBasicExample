package com.thuypham.ptithcm.simplebaseapp.domain.usecase.movie

import com.thuypham.ptithcm.simplebaseapp.data.remote.Movie
import com.thuypham.ptithcm.simplebaseapp.domain.repository.MovieRepository
import com.thuypham.ptithcm.simplebaseapp.domain.usecase.BaseUseCase
import javax.inject.Inject

class InsertMovieToDBUseCase @Inject constructor(private val movieRepository: MovieRepository) :
    BaseUseCase<List<Movie?>, Boolean>() {

    override suspend fun invoke(param: List<Movie?>): Boolean {
        movieRepository.insertMovie(param)
        return true
    }
}