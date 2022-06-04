package com.thuypham.ptithcm.simplebaseapp.domain.repository

import com.thuypham.ptithcm.simplebaseapp.data.model.LoginParam
import com.thuypham.ptithcm.simplebaseapp.data.model.ResponseHandler
import com.thuypham.ptithcm.simplebaseapp.data.remote.LoginResponse

interface AuthenticationRepository {
    suspend fun login(loginParam: LoginParam): ResponseHandler<LoginResponse>
    suspend fun getNewToken(): ResponseHandler<LoginResponse>
}