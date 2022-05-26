package com.thuypham.ptithcm.simplebaseapp.ui.fragment

import com.bumptech.glide.Glide
import com.thuypham.ptithcm.simplebaseapp.R
import com.thuypham.ptithcm.simplebaseapp.base.BaseFragment
import com.thuypham.ptithcm.simplebaseapp.data.remote.Movie
import com.thuypham.ptithcm.simplebaseapp.databinding.FragmentMovieDetailBinding
import com.thuypham.ptithcm.simplebaseapp.extension.goBack
import com.thuypham.ptithcm.simplebaseapp.viewmodel.MovieDetailViewModel
import org.koin.androidx.navigation.koinNavGraphViewModel

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>(R.layout.fragment_movie_detail) {

    private val detailMovieViewModel: MovieDetailViewModel by koinNavGraphViewModel(R.id.main_nav)

    override fun setupToolbar() {
        setLeftBtn(R.drawable.ic_back) {
            goBack()
        }
    }

    private fun setupView(movie: Movie) {
        setToolbarTitle(movie.title ?: "")

        binding.apply {

            Glide.with(requireActivity())
                .load(movie.getImagePath())
                .placeholder(R.drawable.ic_image_placeholder)
                .into(ivMovie)

            tvDescription.text = movie.overview
            tvTitleMovie.text = movie.title
        }
    }

    override fun setupDataObserver() {
        super.setupDataObserver()
        detailMovieViewModel.movieDetail.observe(viewLifecycleOwner) { movie ->
            setupView(movie)
        }
    }

}