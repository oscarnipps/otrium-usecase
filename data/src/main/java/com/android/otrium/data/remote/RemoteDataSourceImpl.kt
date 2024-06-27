package com.android.otrium.data.remote

import com.android.otrium.data.remote.entity.UserDetailDto
import com.android.otrium.data.remote.mappers.RemoteEntityMapper
import com.android.otrium.domain.common.ApiResult
import com.android.otrium.domain.common.AppError
import com.apollographql.apollo3.ApolloClient
import com.otrium.GithubProfileQuery
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val mapper: RemoteEntityMapper
) : RemoteDataSource {

    override suspend fun getUserDetailsFromApi(): ApiResult<UserDetailDto> {
        return try {
            val userData = apolloClient.query(GithubProfileQuery()).execute().data
            ApiResult.Success(mapper.mapToUserDetailDto(userData))
        } catch (exception: Exception) {
            //todo: better error handling and propagation
            ApiResult.Error(AppError.HTTP_ERROR)
        }
    }
}