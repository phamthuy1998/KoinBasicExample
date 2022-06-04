package com.thuypham.ptithcm.simplebaseapp.api

import com.thuypham.ptithcm.simplebaseapp.data.model.LoginParam
import com.thuypham.ptithcm.simplebaseapp.data.remote.MovieList
import com.thuypham.ptithcm.simplebaseapp.data.remote.LoginResponse
import com.thuypham.ptithcm.simplebaseapp.util.ApiConstant.API_KEY
import com.thuypham.ptithcm.simplebaseapp.util.StaticObject
import retrofit2.Response
import retrofit2.http.*

interface MovieApi {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = StaticObject.getCurrentLanguage(),
    ): Response<MovieList>

    @GET("authentication/token/new")
    suspend fun getNewToken(
        @Query("api_key") apiKey: String = API_KEY,
    ): Response<LoginResponse>

    @POST("authentication/token/validate_with_login")
    suspend fun login(
        @Body loginParam: LoginParam,
        @Query("api_key") apiKey: String = API_KEY,
    ): Response<LoginResponse>
}