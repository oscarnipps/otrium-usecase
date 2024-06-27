package com.android.otrium.data.repo

import com.android.otrium.data.DateUtils
import com.android.otrium.data.remote.entity.PinnedRepoDto
import com.android.otrium.data.remote.entity.StarredRepoDto
import com.android.otrium.data.remote.entity.TopRepoDto
import com.android.otrium.data.remote.entity.UserDetailDto
import com.android.otrium.database.entity.PinnedRepoEntity
import com.android.otrium.database.entity.StarredRepoEntity
import com.android.otrium.database.entity.TopRepoEntity
import com.android.otrium.database.entity.UserEntity
import javax.inject.Inject

class LocalEntityMapper @Inject constructor(
    private val dateUtils: DateUtils
) {

    fun mapTpUserEntity(user: UserDetailDto?): UserEntity {
        return UserEntity(
            login = user?.login.orEmpty(),
            name = user?.name.orEmpty(),
            profileUrl = user?.profileUrl.orEmpty(),
            email = user?.email.orEmpty(),
            followers = user?.followers ?: 0,
            following = user?.following ?: 0,
            updatedAt = dateUtils.getCurrentLocalTime()
        )
    }

    fun mapToPinnedRepo(pinnedRepo: List<PinnedRepoDto>): List<PinnedRepoEntity> {
        return pinnedRepo.map {
            PinnedRepoEntity(
                login = it.login,
                name = it.name,
                repoLanguage = it.repoLanguage,
                repoLanguageHexColor = it.repoLanguageHexCodeColor,
                description = it.description,
                starsCount = it.starsCount,
                profileUrl = it.profileUrl,
                updatedAt = dateUtils.getCurrentLocalTime()
            )
        }
    }

    fun mapToStarredRepo(starredRepo: List<StarredRepoDto>): List<StarredRepoEntity> {
        return starredRepo.map {
            StarredRepoEntity(
                login = it.login,
                name = it.name,
                repoLanguage = it.repoLanguage,
                repoLanguageHexColor = it.repoLanguageHexCodeColor,
                description = it.description,
                starsCount = it.starsCount,
                profileUrl = it.profileUrl,
                updatedAt = dateUtils.getCurrentLocalTime()
            )
        }
    }

    fun mapToTopRepo(topRepo: List<TopRepoDto>): List<TopRepoEntity> {
        return topRepo.map {
            TopRepoEntity(
                login = it.login,
                name = it.name,
                repoLanguage = it.repoLanguage,
                repoLanguageHexColor = it.repoLanguageHexCodeColor,
                description = it.description,
                starsCount = it.starsCount,
                profileUrl = it.profileUrl,
                updatedAt = dateUtils.getCurrentLocalTime()
            )
        }
    }

    fun mapToHasCacheExceeded1Day(dateTimeString: String?) : Boolean{
        //when value is null it means no entry in the db yet
        return dateTimeString?.let { dateUtils.hasExceeded24Hours(dateTimeString) } ?: true
    }
}