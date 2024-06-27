package com.android.otrium.domain.entity

data class TopRepo (
    val login: String,
    val name: String,
    val repoLanguage: String,
    val repoLanguageHexColor: String,
    val description: String,
    val starsCount: String,
    val profileUrl: String
)
