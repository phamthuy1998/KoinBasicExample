package com.thuypham.ptithcm.simplebaseapp.domain.repositoryimpl

import com.thuypham.ptithcm.simplebaseapp.api.MovieApi
import com.thuypham.ptithcm.simplebaseapp.api.wrapApiCall
import com.thuypham.ptithcm.simplebaseapp.data.model.LoginParam
import com.thuypham.ptithcm.simplebaseapp.data.model.ResponseHandler
import com.thuypham.ptithcm.simplebaseapp.data.remote.LoginResponse
import com.thuypham.ptithcm.simplebaseapp.domain.repository.AuthenticationRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationRepositoryImpl @Inject constructor(private val api: MovieApi) : AuthenticationRepository {
    override suspend fun login(loginParam: LoginParam): ResponseHandler<LoginResponse> {
        return wrapApiCall { api.login(loginParam) }
    }

    override suspend fun getNewToken(): ResponseHandler<LoginResponse> {
        return wrapApiCall { api.getNewToken() }
    }
}