package com.thuypham.ptithcm.simplebaseapp.domain.usecase.authentication

import com.thuypham.ptithcm.simplebaseapp.data.model.ResponseHandler
import com.thuypham.ptithcm.simplebaseapp.data.remote.LoginResponse
import com.thuypham.ptithcm.simplebaseapp.domain.repository.AuthenticationRepository
import com.thuypham.ptithcm.simplebaseapp.domain.usecase.BaseUseCaseNoParam
import javax.inject.Inject

class GetNewTokenUseCase @Inject constructor(private val authenticationRepository: AuthenticationRepository) :
    BaseUseCaseNoParam<ResponseHandler<LoginResponse>>() {

    override suspend fun invoke(): ResponseHandler<LoginResponse> {
        return authenticationRepository.getNewToken()
    }
}