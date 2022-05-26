package com.thuypham.ptithcm.simplebaseapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.thuypham.ptithcm.simplebaseapp.base.BaseViewModel
import com.thuypham.ptithcm.simplebaseapp.data.remote.Movie

class MovieDetailViewModel : BaseViewModel() {

    private val _movieDetail = MutableLiveData<Movie>()
    val movieDetail = _movieDetail

    fun setDetailMovie(movie: Movie) {
        _movieDetail.value = movie
    }
}