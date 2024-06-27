package com.android.otrium.data.remote

import com.android.otrium.data.remote.entity.UserDetailDto
import com.android.otrium.domain.common.ApiResult

interface RemoteDataSource {
    suspend fun getUserDetailsFromApi() : ApiResult<UserDetailDto>
}