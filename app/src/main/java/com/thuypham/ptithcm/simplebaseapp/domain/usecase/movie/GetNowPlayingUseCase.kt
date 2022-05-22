package com.thuypham.ptithcm.simplebaseapp.domain.usecase.movie

import com.thuypham.ptithcm.simplebaseapp.data.model.ResponseHandler
import com.thuypham.ptithcm.simplebaseapp.data.remote.MovieList
import com.thuypham.ptithcm.simplebaseapp.domain.repository.MovieRepository
import com.thuypham.ptithcm.simplebaseapp.domain.usecase.BaseUseCase

class GetNowPlayingUseCase(private val movieRepository: MovieRepository) :
    BaseUseCase<Int, ResponseHandler<MovieList>>() {

    override suspend fun invoke(param: Int): ResponseHandler<MovieList> {
        return movieRepository.getNowPlaying(param)
    }
}