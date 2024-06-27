package com.android.otrium.data.remote.entity

data class UserDetailDto(
    val login: String,
    val name: String,
    val profileUrl: String,
    val email: String,
    val followers: Int,
    val following: Int,
    val starredRepo: List<StarredRepoDto>,
    val pinnedRepo: List<PinnedRepoDto>,
    val topRepo: List<TopRepoDto>,
)
