package com.thuypham.ptithcm.simplebaseapp.domain.usecase.authentication

import com.thuypham.ptithcm.simplebaseapp.data.model.LoginParam
import com.thuypham.ptithcm.simplebaseapp.data.model.ResponseHandler
import com.thuypham.ptithcm.simplebaseapp.data.remote.LoginResponse
import com.thuypham.ptithcm.simplebaseapp.domain.repository.AuthenticationRepository
import com.thuypham.ptithcm.simplebaseapp.domain.usecase.BaseUseCase
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authenticationRepository: AuthenticationRepository) :
    BaseUseCase<LoginParam, ResponseHandler<LoginResponse>>() {

    override suspend fun invoke(param: LoginParam): ResponseHandler<LoginResponse> {
        return authenticationRepository.login(param)
    }
}