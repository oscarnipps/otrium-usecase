package com.android.otrium.domain.repo

import com.android.otrium.domain.common.ApiResult
import com.android.otrium.domain.entity.PinnedRepo
import com.android.otrium.domain.entity.StarredRepo
import com.android.otrium.domain.entity.TopRepo
import com.android.otrium.domain.entity.User
import com.android.otrium.domain.entity.UserDetails
import kotlinx.coroutines.flow.Flow

interface GetUserDetailsRepo {
    suspend fun getUserDetailsFromApi(isPullToRefresh : Boolean)
     fun getUser() : Flow<User?>
     fun getTop() : Flow<List<TopRepo>>
     fun getStarred() : Flow<List<StarredRepo>>
     fun getPinned() : Flow<List<PinnedRepo>>
}