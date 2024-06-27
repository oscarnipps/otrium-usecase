package com.android.otrium.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PinnedRepoEntity(

    val login: String,
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val repoLanguage: String,
    val repoLanguageHexColor: String,
    val description: String,
    val starsCount: String,
    val profileUrl: String,
    val updatedAt: String
)
