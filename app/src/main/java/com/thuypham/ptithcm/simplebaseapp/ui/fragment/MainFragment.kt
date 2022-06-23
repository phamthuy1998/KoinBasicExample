package com.thuypham.ptithcm.simplebaseapp.ui.fragment

import android.content.Intent
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thuypham.ptithcm.simplebaseapp.R
import com.thuypham.ptithcm.simplebaseapp.base.BaseFragment
import com.thuypham.ptithcm.simplebaseapp.data.model.AppException
import com.thuypham.ptithcm.simplebaseapp.data.model.ResponseHandler
import com.thuypham.ptithcm.simplebaseapp.data.remote.Movie
import com.thuypham.ptithcm.simplebaseapp.databinding.FragmentMainBinding
import com.thuypham.ptithcm.simplebaseapp.extension.logD
import com.thuypham.ptithcm.simplebaseapp.extension.logE
import com.thuypham.ptithcm.simplebaseapp.extension.navigateTo
import com.thuypham.ptithcm.simplebaseapp.ui.activity.LoginActivity
import com.thuypham.ptithcm.simplebaseapp.ui.activity.SplashActivity
import com.thuypham.ptithcm.simplebaseapp.ui.adapter.MovieAdapter
import com.thuypham.ptithcm.simplebaseapp.ui.dialog.ConfirmDialog
import com.thuypham.ptithcm.simplebaseapp.viewmodel.MainViewModel
import com.thuypham.ptithcm.simplebaseapp.viewmodel.MovieDetailViewModel
import com.thuypham.ptithcm.simplebaseapp.viewmodel.SplashViewModel
import org.koin.android.ext.android.getKoin
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.navigation.koinNavGraphViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope
import kotlin.math.log


class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val mainViewModel: MainViewModel by viewModel()
    private val detailMovieViewModel: MovieDetailViewModel by koinNavGraphViewModel(R.id.main_graph)

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
        showLoading()
        mainViewModel.getMovieNowPlaying()
    }


    override fun setupDataObserver() {
        super.setupDataObserver()

        mainViewModel.movieList.observe(viewLifecycleOwner) {
            logD("setupDataObserver - submitList movie")
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
            msg = error?.extra ?: getString(R.string.dialog_error_msg_time_out)
        ).show(childFragmentManager, ConfirmDialog.TAG)
    }


    override fun setupToolbar() {
        setToolbarTitle(getString(R.string.app_name))
        setRightBtn(R.drawable.ic_log_out) {
            mainViewModel.logOut()
            startLoginActivity()
        }
    }

    private fun startLoginActivity() {
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
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
                        && movieItemLoadingPosition == movieAdapter.itemCount - 7
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