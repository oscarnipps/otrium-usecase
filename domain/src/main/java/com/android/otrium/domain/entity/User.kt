package com.android.otrium.domain.entity

data class User(
    val login : String,
    val name : String,
    val profileUrl : String,
    val email : String,
    val followers : Int,
    val following : Int
)
