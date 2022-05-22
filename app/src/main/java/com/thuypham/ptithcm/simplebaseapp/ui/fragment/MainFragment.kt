package com.thuypham.ptithcm.simplebaseapp.ui.fragment

import com.thuypham.ptithcm.simplebaseapp.R
import com.thuypham.ptithcm.simplebaseapp.base.BaseFragment
import com.thuypham.ptithcm.simplebaseapp.data.remote.Movie
import com.thuypham.ptithcm.simplebaseapp.databinding.FragmentMainBinding
import com.thuypham.ptithcm.simplebaseapp.extension.logE
import com.thuypham.ptithcm.simplebaseapp.ui.adapter.MovieAdapter
import com.thuypham.ptithcm.simplebaseapp.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val mainViewModel: MainViewModel by viewModel()
    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter { movie -> onMovieItemClick(movie) }
    }

    private fun onMovieItemClick(item: Movie) {

    }

    override fun getData() {
        mainViewModel.getMovieNowPlaying(1)
    }


    override fun setupDataObserver() {
        super.setupDataObserver()
        mainViewModel.movieList.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {

            } else {
                movieAdapter.submitList(it)
            }
        }
        mainViewModel.error.observe(viewLifecycleOwner) {
            showSnackBar(it.extra)
            logE(it.toString(), it.error)
        }
        mainViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            showLoading(isLoading)
        }
    }

    override fun setupToolbar() {
        setToolbarTitle(getString(R.string.app_name))
    }

    override fun setupView() {
        binding.apply {
            rvMovie.adapter = movieAdapter
        }
    }
}