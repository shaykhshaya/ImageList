package com.shaya.fliptree.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shaya.fliptree.databinding.ListItemBinding
import com.shaya.fliptree.response.ImageItem

class ImageListAdapter(private val onItemClick: (ImageItem) -> Unit) : ListAdapter<ImageItem,
        ImageListAdapter.ImageListViewHolder>(DiffCallback) {


    class ImageListViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageItem: ImageItem) {
            binding.item = imageItem
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListViewHolder {
        val adapterLayout =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageListViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ImageListViewHolder, position: Int) {
        val imageItem = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClick(imageItem)
        }

        holder.bind(imageItem)

    }

    companion object DiffCallback : DiffUtil.ItemCallback<ImageItem>() {
        override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
            return oldItem.image == newItem.image
        }

    }


}