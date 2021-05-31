package com.example.newgithubuser.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newgithubuser.databinding.ListItemBinding
import com.example.newgithubuser.domain.RepoItem
import com.example.newgithubuser.presentation.ui.utils.ImageLoader

class SampleAdapter (
    private val imageService: ImageLoader,
    private val onClick: (RepoItem) -> Unit
) : RecyclerView.Adapter<SampleAdapter.SampleViewHolder>() {

    private val repoList: MutableList<RepoItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SampleViewHolder(binding, imageService)
    }

    override fun getItemCount() = repoList.size

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.bind(repoList[position], onClick)
    }

    class SampleViewHolder (
        private val binding: ListItemBinding,
        private val imageService: ImageLoader
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(repoItem: RepoItem, onClick: (RepoItem) -> Unit) {
            with(binding) {
                this.repoLayout.setOnClickListener {
                    onClick.invoke(repoItem)
                    true
                }
            }
        }
    }
}

private class SampleDiffCallback(val oldItems: List<RepoItem>, val newItems: List<RepoItem>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].id == newItems[newItemPosition].id
    }

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}
