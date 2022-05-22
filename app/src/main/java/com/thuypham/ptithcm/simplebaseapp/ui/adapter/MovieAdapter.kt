package com.thuypham.ptithcm.simplebaseapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thuypham.ptithcm.simplebaseapp.R
import com.thuypham.ptithcm.simplebaseapp.data.remote.Movie
import com.thuypham.ptithcm.simplebaseapp.databinding.ItemMovieBinding
import com.thuypham.ptithcm.simplebaseapp.extension.setOnSingleClickListener

class MovieAdapter(
    private val onItemClick: ((item: Movie) -> Unit)? = null,
) : ListAdapter<Movie, RecyclerView.ViewHolder>(DiffCallback()) {

    class ImageVideoItemViewHolder(
        private val binding: ItemMovieBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            binding.apply {
                Glide.with(root.context)
                    .load(item.getImagePath())
                    .placeholder(R.drawable.ic_image_placeholder)
                    .into(ivMovie)

                tvTitle.text = item.title
                tvDescription.text = item.overview
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ImageVideoItemViewHolder {
        val binding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageVideoItemViewHolder(binding)
            .apply {
                binding.root.setOnSingleClickListener {
                    onItemClick?.invoke(currentList[absoluteAdapterPosition])
                }

            }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ImageVideoItemViewHolder).bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem == newItem
    }
}