package com.android.otrium.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.otrium.domain.entity.PinnedRepo
import com.android.otrium.ui.databinding.PinnedRepoListItemBinding
import com.bumptech.glide.Glide


class PinnedRepoAdapter : ListAdapter<PinnedRepo, PinnedRepoAdapter.PinnedRepoAdapterViewHolder>(PinnedRepoAdapterItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PinnedRepoAdapterViewHolder {
        val binding: PinnedRepoListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.pinned_repo_list_item,
            parent,
            false
        )

        return PinnedRepoAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PinnedRepoAdapterViewHolder, position: Int) {
        val currentMember = getItem(position)
        holder.bindMember(currentMember)
    }

    private class PinnedRepoAdapterItemDiffCallback : DiffUtil.ItemCallback<PinnedRepo>() {
        override fun areItemsTheSame(oldItem: PinnedRepo, newItem: PinnedRepo): Boolean {
            return oldItem.login == newItem.login
        }

        override fun areContentsTheSame(oldItem: PinnedRepo, newItem: PinnedRepo): Boolean {
            return oldItem == newItem
        }
    }

    inner class PinnedRepoAdapterViewHolder(private val itemBinding: PinnedRepoListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bindMember(repo: PinnedRepo) {
            itemBinding.loginName.text = repo.login

            itemBinding.repoName.text = repo.name

            itemBinding.repoDescription.text = repo.description

            itemBinding.starCount.text = repo.starsCount

            itemBinding.repoLanguage.text = repo.repoLanguage

            itemBinding.repoColor.setImageDrawable(ColorDrawable(itemView.context.getColor(android.R.color.transparent)))
            itemBinding.repoColor.setBackgroundColor(Color.parseColor(repo.repoLanguageHexColor))

            Glide.with(itemView.context)
                .load(repo.profileUrl)
                .into(itemBinding.userImage)
        }


    }
}