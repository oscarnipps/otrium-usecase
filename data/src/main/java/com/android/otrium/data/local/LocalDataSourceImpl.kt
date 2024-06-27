package com.android.otrium.data.local

import com.android.otrium.database.dao.PinnedRepoDao
import com.android.otrium.database.dao.StarredRepoDao
import com.android.otrium.database.dao.TopRepoDao
import com.android.otrium.database.dao.UserDao
import com.android.otrium.database.entity.PinnedRepoEntity
import com.android.otrium.database.entity.StarredRepoEntity
import com.android.otrium.database.entity.TopRepoEntity
import com.android.otrium.database.entity.UserEntity
import com.android.otrium.domain.entity.PinnedRepo
import com.android.otrium.domain.entity.StarredRepo
import com.android.otrium.domain.entity.TopRepo
import com.android.otrium.domain.entity.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class LocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao,
    private val topRepoDao: TopRepoDao,
    private val pinnedRepoDao: PinnedRepoDao,
    private val starredRepoDao: StarredRepoDao,
) : LocalDataSource {

    override fun getUserFromCache(): Flow<User?> {

        return userDao.getUser().map {userEntity ->
            userEntity?.let {
                User(
                    login = it.login,
                    name = it.name,
                    profileUrl = it.profileUrl,
                    email = it.email,
                    followers = it.followers,
                    following = it.following,

                    )
            }
        }
    }

    override suspend fun addUserToCache(user: UserEntity) {
        userDao.insertUser(user)
    }

    override suspend fun getUserLastUpdateTime() : String?{
        return userDao.getUserLastUpdateTime()
    }

    override  fun getStarredRepoFromCache(): Flow<List<StarredRepo>> {
        return starredRepoDao.getStarredRepo().map {
            it.map {
                StarredRepo(
                    login = it.login ,
                    name = it.name,
                    repoLanguage = it.repoLanguage,
                    repoLanguageHexColor= it.repoLanguageHexColor,
                    description = it.description,
                    starsCount = it.starsCount,
                    profileUrl= it.profileUrl
                )
            }
        }
    }

    override suspend fun addStarredRepoToCache(starredRepoEntities: List<StarredRepoEntity>) {
        starredRepoDao.insertStarredRepo(starredRepoEntities)
    }

    override  fun getTopRepoFromCache(): Flow<List<TopRepo>> {
        return topRepoDao.getTopRepo().map {
            it.map {
                TopRepo(
                    login = it.login ,
                    name = it.name,
                    repoLanguage = it.repoLanguage,
                    repoLanguageHexColor= it.repoLanguageHexColor,
                    description = it.description,
                    starsCount = it.starsCount,
                    profileUrl= it.profileUrl
                )
            }
        }
    }

    override suspend fun addTopRepoToCache(topRepoEntities: List<TopRepoEntity>) {
        topRepoDao.insertTopRepo(topRepoEntities)
    }

    override  fun getPinnedRepoFromCache(): Flow<List<PinnedRepo>> {
        return pinnedRepoDao.getPinnedRepo().map {
            it.map {
                PinnedRepo(
                    login = it.login ,
                    name = it.name,
                    repoLanguage = it.repoLanguage,
                    repoLanguageHexColor= it.repoLanguageHexColor,
                    description = it.description,
                    starsCount = it.starsCount,
                    profileUrl= it.profileUrl
                )
            }
        }
    }

    override suspend fun addPinnedRepoToCache(pinnedRepoEntities: List<PinnedRepoEntity>) {
        pinnedRepoDao.insertPinnedRepo(pinnedRepoEntities)
    }
}