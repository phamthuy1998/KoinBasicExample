package com.thuypham.ptithcm.simplebaseapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.thuypham.ptithcm.simplebaseapp.base.BaseViewModel
import com.thuypham.ptithcm.simplebaseapp.data.local.IStorage
import com.thuypham.ptithcm.simplebaseapp.data.local.SharedPreferencesStorage
import com.thuypham.ptithcm.simplebaseapp.data.model.LoadingItem
import com.thuypham.ptithcm.simplebaseapp.data.model.ResponseHandler
import com.thuypham.ptithcm.simplebaseapp.domain.usecase.movie.GetLocalNowPlayingUseCase
import com.thuypham.ptithcm.simplebaseapp.domain.usecase.movie.GetNowPlayingUseCase
import com.thuypham.ptithcm.simplebaseapp.domain.usecase.movie.InsertMovieToDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNowPlayingUseCase: GetNowPlayingUseCase,
    private val getLocalNowPlaying: GetLocalNowPlayingUseCase,
    private val insertMovieToDBUseCase: InsertMovieToDBUseCase,
    private val sharedPrf: IStorage
) : BaseViewModel() {

    private val _movieList = MutableLiveData<ArrayList<Any>>()
    val movieList: LiveData<ArrayList<Any>> = _movieList

    private val movieListItems: ArrayList<Any> = arrayListOf()

    private val _loadMoreAble = MutableLiveData<Boolean>()
    val loadMoreAble: LiveData<Boolean> = _loadMoreAble

    private var currentPage = 0

    fun getMovieNowPlaying(isNetworkOn: Boolean) {
        if (isNetworkOn) {
            getMovieNowPlaying()
        } else {
            getLocalNowPlaying()
        }
    }

    private fun getLocalNowPlaying() {
        viewModelScope.launch {
            movieListItems.addAll(getLocalNowPlaying.invoke())
            _movieList.value = movieListItems
        }
    }

    private fun getMovieNowPlaying() = viewModelScope.launch {
        currentPage++

        // Add Item Loading
        movieListItems.add(LoadingItem())

        if (currentPage == 1) {
            _isLoading.value = true
        }

        when (val result = getNowPlayingUseCase.invoke(currentPage)) {
            is ResponseHandler.Success -> {
                // Remove last Item Loading
                movieListItems.removeLast()

                val listMovies = result.data.movies
                if (listMovies.isNullOrEmpty()) {
                    // End of api will response an empty list --> set load more able = false to stop load data
                    _loadMoreAble.value = false
                } else {
                    _loadMoreAble.value = true
                    movieListItems.addAll(listMovies)
                    _movieList.value = movieListItems

                    // Save movie to local database
                    insertMovieToDBUseCase.invoke(listMovies)
                }
            }
            is ResponseHandler.Failure -> {
                _error.value = result
                movieListItems.removeLast()

            }
            else -> {
                movieListItems.removeLast()
            }
        }

        if (currentPage == 1) {
            _isLoading.value = false
        }
    }


    fun isUserLogin(): Boolean {
        return sharedPrf.getObject(SharedPreferencesStorage.LOGIN_DATA) != null
    }

}