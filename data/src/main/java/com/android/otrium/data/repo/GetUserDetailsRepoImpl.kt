package com.android.otrium.data.repo

import com.android.otrium.data.di.DispatcherProvider
import com.android.otrium.data.local.LocalDataSource
import com.android.otrium.data.remote.RemoteDataSource
import com.android.otrium.data.remote.entity.UserDetailDto
import com.android.otrium.domain.common.ApiResult
import com.android.otrium.domain.entity.PinnedRepo
import com.android.otrium.domain.entity.StarredRepo
import com.android.otrium.domain.entity.TopRepo
import com.android.otrium.domain.entity.User
import com.android.otrium.domain.repo.GetUserDetailsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GetUserDetailsRepoImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val localEntityMapper: LocalEntityMapper,
    private val dispatchers: DispatcherProvider
) : GetUserDetailsRepo {

    override suspend fun getUserDetailsFromApi(isPullToRefresh: Boolean) {
        withContext(dispatchers.io) {
            if (isPullToRefresh || hasCacheExceeded1Day()) {
                //call network
                val apiResult = getUserDetailsFromAPi()

                //update local
                updateLocalDatabase(apiResult)
            }
        }
    }

    override fun getUser(): Flow<User?> {
        return localDataSource.getUserFromCache()
    }

    override fun getTop(): Flow<List<TopRepo>> {
        return  localDataSource.getTopRepoFromCache().map{  it  }
    }

    override fun getStarred(): Flow<List<StarredRepo>> {
        return  localDataSource.getStarredRepoFromCache().map { it   }
    }

    override fun getPinned(): Flow<List<PinnedRepo>> {
        return  localDataSource.getPinnedRepoFromCache().map { it   }
    }

    private suspend fun getUserDetailsFromAPi(): ApiResult<UserDetailDto> {
        return withContext(dispatchers.io) {
            remoteDataSource.getUserDetailsFromApi()
        }
    }

    private suspend fun updateLocalDatabase(apiResult: ApiResult<UserDetailDto>) {

        apiResult.data?.let {

          localDataSource.addUserToCache(localEntityMapper.mapTpUserEntity(it))

          localDataSource.addPinnedRepoToCache(localEntityMapper.mapToPinnedRepo(it.pinnedRepo))

          localDataSource.addTopRepoToCache(localEntityMapper.mapToTopRepo(it.topRepo))

          localDataSource.addStarredRepoToCache(localEntityMapper.mapToStarredRepo(it.starredRepo))
        }
    }

    private suspend fun hasCacheExceeded1Day(): Boolean {
        //get the user entry and check against the last updated time
        val lastEntryDateTime = localDataSource.getUserLastUpdateTime()

        return localEntityMapper.mapToHasCacheExceeded1Day(lastEntryDateTime)

    }
}