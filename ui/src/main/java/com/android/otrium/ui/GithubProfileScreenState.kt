package com.android.otrium.ui

import com.android.otrium.domain.common.AppError
import com.android.otrium.domain.entity.UserDetails

sealed class GithubProfileScreenState {

    data object Initial : GithubProfileScreenState()

    data object Loading : GithubProfileScreenState()

    data class Error (val appError: AppError): GithubProfileScreenState()

    data class Success(val userDetails: UserDetails?) : GithubProfileScreenState()
}