package com.android.otrium.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity (
    @PrimaryKey(autoGenerate = false)
    val login : String,
    val name : String,
    val profileUrl : String,
    val email : String,
    val followers : Int,
    val following : Int,
    val updatedAt : String
)
