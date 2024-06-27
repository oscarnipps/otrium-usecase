package com.android.otrium.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.otrium.domain.entity.StarredRepo
import com.android.otrium.ui.databinding.HorizontalRepoListItemBinding
import com.android.otrium.ui.databinding.PinnedRepoListItemBinding
import com.bumptech.glide.Glide

class StarredRepoAdapter : ListAdapter<StarredRepo, StarredRepoAdapter.StarredRepoAdapterViewHolder>(StarredRepoAdapterItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarredRepoAdapterViewHolder {
        val binding: HorizontalRepoListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.horizontal_repo_list_item,
            parent,
            false
        )

        return StarredRepoAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StarredRepoAdapterViewHolder, position: Int) {
        val currentMember = getItem(position)
        holder.bindMember(currentMember)
    }

    private class StarredRepoAdapterItemDiffCallback : DiffUtil.ItemCallback<StarredRepo>() {
        override fun areItemsTheSame(oldItem: StarredRepo, newItem: StarredRepo): Boolean {
            return oldItem.login == newItem.login
        }

        override fun areContentsTheSame(oldItem: StarredRepo, newItem: StarredRepo): Boolean {
            return oldItem == newItem
        }
    }

    inner class StarredRepoAdapterViewHolder(private val itemBinding: HorizontalRepoListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bindMember(repo: StarredRepo) {
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