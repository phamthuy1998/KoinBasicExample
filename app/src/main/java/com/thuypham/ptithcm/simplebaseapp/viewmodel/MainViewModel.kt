package com.thuypham.ptithcm.simplebaseapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.thuypham.ptithcm.simplebaseapp.base.BaseViewModel
import com.thuypham.ptithcm.simplebaseapp.data.model.ResponseHandler
import com.thuypham.ptithcm.simplebaseapp.data.remote.Movie
import com.thuypham.ptithcm.simplebaseapp.domain.usecase.movie.GetNowPlayingUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getNowPlayingUseCase: GetNowPlayingUseCase
) : BaseViewModel() {

    private val _movieList = MutableLiveData<ArrayList<Movie>?>()
    val movieList = _movieList


    fun getMovieNowPlaying(page: Int) = viewModelScope.launch {
        isLoading.value = true
        val result = getNowPlayingUseCase.invoke(page)
        isLoading.value = false
        when (result) {
            is ResponseHandler.Success -> {
                _movieList.value = result.data.movies
            }
            is ResponseHandler.Failure -> {
                error.value = result
            }
            else -> {

            }
        }
    }

}