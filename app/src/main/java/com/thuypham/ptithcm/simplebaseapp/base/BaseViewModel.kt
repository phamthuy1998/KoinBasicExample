package com.thuypham.ptithcm.simplebaseapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thuypham.ptithcm.simplebaseapp.data.model.ResponseHandler

abstract class BaseViewModel : ViewModel() {
    protected val _error = MutableLiveData<ResponseHandler.Failure>()
    val error: LiveData<ResponseHandler.Failure> = _error

    protected val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading
}