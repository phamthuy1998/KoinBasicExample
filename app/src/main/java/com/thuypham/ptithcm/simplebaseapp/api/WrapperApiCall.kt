package com.thuypham.ptithcm.simplebaseapp.api

import com.thuypham.ptithcm.simplebaseapp.data.model.AppException
import com.thuypham.ptithcm.simplebaseapp.data.model.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException


suspend inline fun <reified T> wrapApiCall(
    crossinline api: suspend () -> Response<T>
): ResponseHandler<T> {
    return try {
        val response = withContext(Dispatchers.IO) {
            api()
        }
        when (response.code()) {
            200 -> {
                ResponseHandler.Success(response.body()!!)
            }
            else -> {
                ResponseHandler.Failure(AppException.Unknown)
            }
        }
    } catch (exp: Exception) {
        return when (exp) {
            is HttpException -> {
                ResponseHandler.Failure(AppException.NoNetwork)
            }
            is UnknownHostException -> {
                ResponseHandler.Failure(AppException.UnsolvedHost)
            }
            is ConnectException -> {
                ResponseHandler.Failure(AppException.ConnectException)
            }
            else -> {
                ResponseHandler.Failure(exp)
            }
        }
    }
}


fun <T> isEmptyData(data: T): Boolean {
    return data == null
}