package com.thuypham.ptithcm.simplebaseapp.api

import com.thuypham.ptithcm.simplebaseapp.data.remote.MovieList
import com.thuypham.ptithcm.simplebaseapp.util.ApiConstant.TOKEN_API
import com.thuypham.ptithcm.simplebaseapp.util.StaticObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("page") page: Int = 1,
        @Query("api_key") token: String = TOKEN_API,
        @Query("language") language: String = StaticObject.getCurrentLanguage(),
    ): Response<MovieList>

}