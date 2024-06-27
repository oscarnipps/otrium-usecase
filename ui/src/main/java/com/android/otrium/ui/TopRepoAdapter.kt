package com.android.otrium.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.otrium.domain.entity.TopRepo
import com.android.otrium.ui.databinding.HorizontalRepoListItemBinding
import com.bumptech.glide.Glide

class TopRepoAdapter : ListAdapter<TopRepo, TopRepoAdapter.TopRepoAdapterViewHolder>(TopRepoAdapterItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRepoAdapterViewHolder {
        val binding: HorizontalRepoListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.horizontal_repo_list_item,
            parent,
            false
        )

        return TopRepoAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopRepoAdapterViewHolder, position: Int) {
        val currentMember = getItem(position)
        holder.bindMember(currentMember)
    }

    private class TopRepoAdapterItemDiffCallback : DiffUtil.ItemCallback<TopRepo>() {
        override fun areItemsTheSame(oldItem: TopRepo, newItem: TopRepo): Boolean {
            return oldItem.login == newItem.login
        }

        override fun areContentsTheSame(oldItem: TopRepo, newItem: TopRepo): Boolean {
            return oldItem == newItem
        }
    }

    inner class TopRepoAdapterViewHolder(private val itemBinding: HorizontalRepoListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bindMember(repo: TopRepo) {
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