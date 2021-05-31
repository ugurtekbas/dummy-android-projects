package com.example.newgithubuser.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newgithubuser.presentation.ui.utils.ImageLoader
import com.example.newgithubuser.databinding.ListItemBinding
import com.example.newgithubuser.domain.RepoItem

class MainAdapter constructor(
    private val onClick: (RepoItem) -> Unit,
    private val imageService: ImageLoader
): ListAdapter<RepoItem, MainAdapter.MainViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(
            binding,
            imageService
        )
    }

    override fun onBindViewHolder(holderMain: MainViewHolder, position: Int) {
        holderMain.bind(getItem(position), onClick)
    }

    class MainViewHolder(
        private val binding: ListItemBinding,
        private val imageService: ImageLoader
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RepoItem, onClick: (RepoItem) -> Unit) {
            with(binding) {
                repoName.text = item.name
                repoOwnerName.text = item.description
                repoLayout.setOnLongClickListener {
                    onClick.invoke(item)
                    true
                }
                repoLayout.setBackgroundResource(item.background)
                imageService.loadAndFitImage(
                    item.profilePicture, binding.profilePic, android.R.drawable.btn_minus
                )
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<RepoItem>() {

    override fun areContentsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean {
        return oldItem == newItem
    }
}
