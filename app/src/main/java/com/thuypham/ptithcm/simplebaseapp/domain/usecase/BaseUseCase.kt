package com.thuypham.ptithcm.simplebaseapp.domain.usecase

import com.thuypham.ptithcm.simplebaseapp.data.remote.Movie

abstract class BaseUseCase<Param, Result> {
    abstract suspend operator fun invoke(param: Param): Result

}

abstract class BaseUseCaseSuspendNoParam<Result> {
    abstract suspend operator fun invoke(): Result
}

abstract class BaseUseCaseNoParam<Result> {
    abstract suspend operator fun invoke(): Result
}
