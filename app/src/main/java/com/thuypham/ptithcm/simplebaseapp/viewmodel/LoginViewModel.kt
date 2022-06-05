package com.thuypham.ptithcm.simplebaseapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.thuypham.ptithcm.simplebaseapp.MainApplication
import com.thuypham.ptithcm.simplebaseapp.R
import com.thuypham.ptithcm.simplebaseapp.base.BaseViewModel
import com.thuypham.ptithcm.simplebaseapp.data.local.IStorage
import com.thuypham.ptithcm.simplebaseapp.data.local.SharedPreferencesStorage
import com.thuypham.ptithcm.simplebaseapp.data.model.LoginParam
import com.thuypham.ptithcm.simplebaseapp.data.model.ResponseHandler
import com.thuypham.ptithcm.simplebaseapp.data.remote.LoginResponse
import com.thuypham.ptithcm.simplebaseapp.domain.usecase.authentication.GetNewTokenUseCase
import com.thuypham.ptithcm.simplebaseapp.domain.usecase.authentication.LoginUseCase
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val loginUseCase: LoginUseCase,
    private val getNewTokenUseCase: GetNewTokenUseCase,
    private val sharedPrf: IStorage,
) : BaseViewModel() {

    private val _userName: MutableLiveData<String> =
        savedStateHandle.getLiveData("username", "")
    val userName: LiveData<String> = _userName

    private val _password: MutableLiveData<String> =
        savedStateHandle.getLiveData("password", "")
    val password: LiveData<String> = _password

    // Set data when login success
    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    fun setUsername(userName: String) {
        _userName.value = userName
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun login() {
        val passwordStr = _password.value
        val usernameStr = _userName.value
        if (passwordStr.isNullOrBlank() || usernameStr.isNullOrBlank()) {
            _error.value = ResponseHandler.Failure(extra = MainApplication.applicationContext().getString(R.string.error_empty_username_pw))
            return
        }
        _isLoading.value = true

        viewModelScope.launch {
            when (val newToken = getNewTokenUseCase.invoke()) {
                is ResponseHandler.Success -> {
                    if (newToken.data.success == true) {
                        val loginParam = LoginParam().apply {
                            this.password = passwordStr
                            this.username = usernameStr
                            requestToken = newToken.data.requestToken
                        }
                        login(loginParam)
                    } else {
                        // Request new token fail
                        _isLoading.value = false
                        _error.value = ResponseHandler.Failure()
                    }
                }
                is ResponseHandler.Failure -> {
                    _isLoading.value = false
                    _error.value = newToken
                }
                else -> {}
            }
        }
    }

    private suspend fun login(loginParam: LoginParam) {
        _isLoading.value = true
        when (val loginResponse = loginUseCase.invoke(loginParam)) {
            is ResponseHandler.Success -> {
                _isLoading.value = false

                val loginData = loginResponse.data
                if (loginData.success == true) {
                    sharedPrf.setObject(SharedPreferencesStorage.LOGIN_DATA, loginParam) // user & pw
                    sharedPrf.setObject(SharedPreferencesStorage.LOGIN_RESPONSE, loginData)
                    _loginResponse.value = loginData
                } else {
                    // Login fail
                    _error.value = ResponseHandler.Failure()
                }
            }
            is ResponseHandler.Failure -> {

                _isLoading.value = false
                _error.value = loginResponse
            }
            else -> {}
        }
    }

}