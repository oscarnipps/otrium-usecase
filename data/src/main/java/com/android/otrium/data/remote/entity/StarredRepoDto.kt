package com.android.otrium.data.remote.entity

data class StarredRepoDto(
    val login: String,
    val name: String,
    val description: String,
    val starsCount: String,
    val repoLanguage: String,
    val repoLanguageHexCodeColor: String,
    val profileUrl: String
)
