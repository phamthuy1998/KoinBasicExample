package com.thuypham.ptithcm.simplebaseapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.thuypham.ptithcm.simplebaseapp.R
import com.thuypham.ptithcm.simplebaseapp.data.remote.Movie
import com.thuypham.ptithcm.simplebaseapp.databinding.ItemLoadingBinding
import com.thuypham.ptithcm.simplebaseapp.databinding.ItemMovieBinding
import com.thuypham.ptithcm.simplebaseapp.extension.setOnSingleClickListener
import com.thuypham.ptithcm.simplebaseapp.ui.adapter.viewholder.ItemLoadingViewHolder

class MovieAdapter(
    private val onItemClick: ((item: Movie) -> Unit)? = null,
) : ListAdapter<Any, RecyclerView.ViewHolder>(DiffCallback()) {

    companion object {
        const val VIEW_TYPE_ITEM = 0
        const val VIEW_TYPE_LOADING = 1
    }

    private class ItemMovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            binding.apply {
                if (item.posterPath.isNullOrBlank()) {
                    Glide.with(root.context)
                        .load(R.drawable.ic_image_placeholder)
                        .into(ivMovie)
                } else {
                    Glide.with(root.context)
                        .load(item.getImagePath())
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .placeholder(R.drawable.ic_image_placeholder)
                        .into(ivMovie)
                }

                tvTitle.text = item.title
                tvDescription.text = item.overview
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM -> {
                val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemMovieViewHolder(binding).apply {
                    binding.root.setOnSingleClickListener {
                        onItemClick?.invoke(currentList[absoluteAdapterPosition] as Movie)
                    }
                }
            }
            else -> {
                val binding = ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemLoadingViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) is Movie) VIEW_TYPE_ITEM else VIEW_TYPE_LOADING
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is ItemMovieViewHolder) {
            viewHolder.bind(getItem(position) as Movie)
        } else {
            (viewHolder as ItemLoadingViewHolder).bind()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any) =
            oldItem.toString() == newItem.toString()

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Any, newItem: Any) =
            oldItem == newItem
    }
}