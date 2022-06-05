package com.thuypham.ptithcm.simplebaseapp.ui.fragment

import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thuypham.ptithcm.simplebaseapp.R
import com.thuypham.ptithcm.simplebaseapp.base.BaseFragment
import com.thuypham.ptithcm.simplebaseapp.data.model.AppException
import com.thuypham.ptithcm.simplebaseapp.data.model.ResponseHandler
import com.thuypham.ptithcm.simplebaseapp.data.remote.Movie
import com.thuypham.ptithcm.simplebaseapp.databinding.FragmentMainBinding
import com.thuypham.ptithcm.simplebaseapp.extension.logE
import com.thuypham.ptithcm.simplebaseapp.extension.navigateTo
import com.thuypham.ptithcm.simplebaseapp.ui.adapter.MovieAdapter
import com.thuypham.ptithcm.simplebaseapp.ui.dialog.ConfirmDialog
import com.thuypham.ptithcm.simplebaseapp.viewmodel.MainViewModel
import com.thuypham.ptithcm.simplebaseapp.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val mainViewModel: MainViewModel by viewModels()
    private val detailMovieViewModel: MovieDetailViewModel by hiltNavGraphViewModels(R.id.main_graph)

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

        mainViewModel.error.observe(viewLifecycleOwner) { error ->
            showSnackBar(error.extra)
            error.error?.let { it1 -> logE(error.toString(), it1) }
            when (error.error) {
                AppException.TimeOut -> {
                    showErrorDialog(error)
                }
            }
        }

        mainViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            setLoadingStatus(isLoading)
        }

        mainViewModel.loadMoreAble.observe(viewLifecycleOwner) {
            loadMoreAble = it
        }

    }

    private fun showErrorDialog(error: ResponseHandler.Failure?) {
        ConfirmDialog(
            title = getString(R.string.dialog_error_title),
            msg = getString(R.string.dialog_error_msg_time_out)
        ).show(childFragmentManager, ConfirmDialog.TAG)
    }


    override fun setupToolbar() {
        toolbarHelper.setToolbarTitle(getString(R.string.app_name))
        val isLogin = mainViewModel.isUserLogin()
        if (!isLogin) {
            toolbarHelper.setRightBtn(R.drawable.ic_login_account) {
                navigateTo(R.id.authentication_graph)
            }
        } else {
            toolbarHelper.setRightBtn(R.drawable.ic_account_setting) {
                // Todo: Navigate to account setting fragment
                navigateTo(R.id.authentication_graph)
            }
        }
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