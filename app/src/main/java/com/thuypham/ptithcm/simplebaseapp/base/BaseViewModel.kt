package com.thuypham.ptithcm.simplebaseapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thuypham.ptithcm.simplebaseapp.data.model.ResponseHandler

abstract class BaseViewModel : ViewModel() {
    val error = MutableLiveData<ResponseHandler.Failure>()

    protected val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading
}