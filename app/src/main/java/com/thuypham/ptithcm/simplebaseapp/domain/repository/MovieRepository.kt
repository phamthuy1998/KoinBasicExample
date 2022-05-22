package com.thuypham.ptithcm.simplebaseapp.domain.repository

import com.thuypham.ptithcm.simplebaseapp.data.model.ResponseHandler
import com.thuypham.ptithcm.simplebaseapp.data.remote.MovieList

interface MovieRepository {

    suspend fun getNowPlaying(page: Int): ResponseHandler<MovieList>

    suspend fun getTopRated(): ResponseHandler<MovieList>
}