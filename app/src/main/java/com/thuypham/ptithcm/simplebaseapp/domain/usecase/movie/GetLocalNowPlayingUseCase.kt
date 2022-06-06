package com.thuypham.ptithcm.simplebaseapp.domain.usecase.movie

import com.thuypham.ptithcm.simplebaseapp.data.local.entity.movieEntitiesToMovies
import com.thuypham.ptithcm.simplebaseapp.data.remote.Movie
import com.thuypham.ptithcm.simplebaseapp.domain.repository.MovieRepository
import com.thuypham.ptithcm.simplebaseapp.domain.usecase.BaseUseCaseNoParam
import javax.inject.Inject

class GetLocalNowPlayingUseCase @Inject constructor(private val movieRepository: MovieRepository) :
    BaseUseCaseNoParam<ArrayList<Movie>>() {

    override suspend fun invoke(): ArrayList<Movie> {
        return movieRepository.getLocalNowPlaying().movieEntitiesToMovies()
    }
}