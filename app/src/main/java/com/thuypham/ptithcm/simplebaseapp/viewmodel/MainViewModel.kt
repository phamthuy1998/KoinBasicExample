package com.thuypham.ptithcm.simplebaseapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.thuypham.ptithcm.simplebaseapp.base.BaseViewModel
import com.thuypham.ptithcm.simplebaseapp.data.IStorage
import com.thuypham.ptithcm.simplebaseapp.data.SharedPreferencesStorage
import com.thuypham.ptithcm.simplebaseapp.data.model.ResponseHandler
import com.thuypham.ptithcm.simplebaseapp.data.remote.Movie
import com.thuypham.ptithcm.simplebaseapp.domain.usecase.movie.GetNowPlayingUseCase
import kotlinx.coroutines.launch
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication

class MainViewModel(
    private val getNowPlayingUseCase: GetNowPlayingUseCase,
    private val sharedPrf: IStorage
) : BaseViewModel() {

    private val _movieList = MutableLiveData<ArrayList<Movie?>?>()
    val movieList: LiveData<ArrayList<Movie?>?> = _movieList

    private val movieListItems: ArrayList<Movie?> = arrayListOf()

    private val _loadMoreAble = MutableLiveData<Boolean>()
    val loadMoreAble: LiveData<Boolean> = _loadMoreAble


    private val _isLogout = MutableLiveData<Boolean>()
    val isLogout: LiveData<Boolean> = _isLogout

    private var currentPage = 0

    fun getMovieNowPlaying() = viewModelScope.launch {
        currentPage++

        // Add Item null for loading item
        movieListItems.add(null)

        if (currentPage == 1) {
            _isLoading.value = true
        }

        when (val result = getNowPlayingUseCase.invoke(currentPage)) {
            is ResponseHandler.Success -> {
                // Remove last null item (Item for loading)
                movieListItems.removeLast()

                val listItems = result.data.movies
                if (listItems.isNullOrEmpty()) {
                    // End of api will response an empty list --> set load more able = false to stop load data
                    _loadMoreAble.value = false
                } else {
                    _loadMoreAble.value = true
                    movieListItems.addAll(listItems)
                    _movieList.value = movieListItems
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


    fun logOut() {
        sharedPrf.clearData(SharedPreferencesStorage.LOGIN_RESPONSE)
        sharedPrf.clearData(SharedPreferencesStorage.LOGIN_DATA)
        sharedPrf.setBoolean(SharedPreferencesStorage.IS_USER_LOGIN, false)
        _isLogout
    }

}