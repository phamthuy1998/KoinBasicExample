package com.thuypham.ptithcm.simplebaseapp.ui.fragment

import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thuypham.ptithcm.simplebaseapp.R
import com.thuypham.ptithcm.simplebaseapp.base.BaseFragment
import com.thuypham.ptithcm.simplebaseapp.data.remote.Movie
import com.thuypham.ptithcm.simplebaseapp.databinding.FragmentMainBinding
import com.thuypham.ptithcm.simplebaseapp.extension.logE
import com.thuypham.ptithcm.simplebaseapp.extension.navigateTo
import com.thuypham.ptithcm.simplebaseapp.ui.adapter.MovieAdapter
import com.thuypham.ptithcm.simplebaseapp.viewmodel.MainViewModel
import com.thuypham.ptithcm.simplebaseapp.viewmodel.MovieDetailViewModel
import org.koin.androidx.navigation.koinNavGraphViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val mainViewModel: MainViewModel by viewModel()
    private val detailMovieViewModel: MovieDetailViewModel by koinNavGraphViewModel(R.id.main_nav)

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter { movie -> onMovieItemClick(movie) }
    }

    private var isMovieListLoadMore = false
    private var loadMoreAble = true
    private var movieItemLoadingPosition = 0

    private fun onMovieItemClick(item: Movie) {
        // Pass data to detail ViewModel
        // Just an example about nav graph ViewModel :v
        detailMovieViewModel.setDetailMovie(item)
        navigateTo(R.id.mainToMovieDetail)
    }

    override fun getData() {
        mainViewModel.getMovieNowPlaying()
    }


    override fun setupDataObserver() {
        super.setupDataObserver()

        mainViewModel.movieList.observe(viewLifecycleOwner) {
            isMovieListLoadMore = false
            movieAdapter.submitList(it)
            movieAdapter.notifyItemChanged(movieItemLoadingPosition)
        }

        mainViewModel.error.observe(viewLifecycleOwner) {
            showSnackBar(it.extra)
            logE(it.toString(), it.error)
        }

        mainViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            setLoadingStatus(isLoading)
        }

        mainViewModel.loadMoreAble.observe(viewLifecycleOwner) {
            loadMoreAble = it
        }
    }

    override fun setupToolbar() {
        setToolbarTitle(getString(R.string.app_name))
    }

    override fun setupView() {
        // Setup Recyclerview
        binding.rvMovie.apply {
            adapter = movieAdapter

            val recyclerViewLayoutManager = layoutManager as LinearLayoutManager
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    movieItemLoadingPosition = recyclerViewLayoutManager.findLastCompletelyVisibleItemPosition()
                    if (!isMovieListLoadMore && loadMoreAble
                        && movieItemLoadingPosition == movieAdapter.itemCount - 4
                    ) {
                        isMovieListLoadMore = true
                        mainViewModel.getMovieNowPlaying()
                    }
                }
            })

            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))

        }
    }

}