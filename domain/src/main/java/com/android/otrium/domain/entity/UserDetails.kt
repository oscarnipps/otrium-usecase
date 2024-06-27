package com.android.otrium.domain.entity

data class UserDetails(
    val user : User?,
    val starredRepos : List<StarredRepo>,
    val topRepos : List<TopRepo>,
    val pinnedRepos : List<PinnedRepo>,
)
