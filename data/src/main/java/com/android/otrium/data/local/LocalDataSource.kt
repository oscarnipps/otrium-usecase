package com.android.otrium.data.local

import com.android.otrium.database.entity.PinnedRepoEntity
import com.android.otrium.database.entity.StarredRepoEntity
import com.android.otrium.database.entity.TopRepoEntity
import com.android.otrium.database.entity.UserEntity
import com.android.otrium.domain.entity.PinnedRepo
import com.android.otrium.domain.entity.StarredRepo
import com.android.otrium.domain.entity.TopRepo
import com.android.otrium.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getUserFromCache(): Flow<User?>

    suspend fun addUserToCache(user: UserEntity)

    suspend fun getUserLastUpdateTime(): String?

    fun getStarredRepoFromCache(): Flow<List<StarredRepo>>

    suspend fun addStarredRepoToCache(starredRepoEntities: List<StarredRepoEntity>)

    fun getTopRepoFromCache(): Flow<List<TopRepo>>

    suspend fun addTopRepoToCache(topRepoEntities: List<TopRepoEntity>)

    fun getPinnedRepoFromCache(): Flow<List<PinnedRepo>>

    suspend fun addPinnedRepoToCache(pinnedRepoEntities: List<PinnedRepoEntity>)
}