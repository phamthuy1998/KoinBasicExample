package com.thuypham.ptithcm.simplebaseapp.data.model

import com.thuypham.ptithcm.simplebaseapp.data.remote.ErrorResponse

sealed class ResponseHandler<out T> {
    /* Success response with data */
    data class Success<out T>(val data: T) : ResponseHandler<T>()

    data class Error<out E>(val errorData: E) : ResponseHandler<E>()

    /* Failed Response with an exception and message */
    data class Failure(val error: Throwable? = AppException.Unknown, val extra: String? = "", val errorResponse: ErrorResponse? = null) :
        ResponseHandler<Nothing>()

    /** Function had already called before and the previous one is executing */
    object Loading : ResponseHandler<Nothing>()
    object DoNothing : ResponseHandler<Nothing>()
}