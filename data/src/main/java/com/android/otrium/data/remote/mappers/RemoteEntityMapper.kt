package com.android.otrium.data.remote.mappers

import com.android.otrium.data.remote.entity.PinnedRepoDto
import com.android.otrium.data.remote.entity.StarredRepoDto
import com.android.otrium.data.remote.entity.TopRepoDto
import com.android.otrium.data.remote.entity.UserDetailDto
import com.otrium.GithubProfileQuery
import javax.inject.Inject

class RemoteEntityMapper @Inject constructor() {
    fun mapToUserDetailDto(data: GithubProfileQuery.Data?): UserDetailDto {
        return UserDetailDto(
            login = data?.user?.login.orEmpty(),
            name = data?.user?.name.orEmpty(),
            profileUrl = data?.user?.avatarUrl as String,
            email = data.user?.email.orEmpty(),
            followers = data.user?.followers?.totalCount ?: 0,
            following = data.user?.following?.totalCount ?: 0,
            starredRepo = getMappedStarredRepos(
                data.user?.starredRepositories?.nodes,
                data.user?.login.orEmpty(),
                data.user?.avatarUrl as String
            ),
            pinnedRepo = getMappedPinnedRepos(
                data.user?.pinnedItems?.nodes,
                data.user?.login.orEmpty(),
                data.user?.avatarUrl as String
            ),
            topRepo = getMappedTopRepos(
                data.user?.repositories?.nodes,
                data.user?.login.orEmpty(),
                data.user?.avatarUrl as String
            ),
        )
    }

    private fun getMappedStarredRepos(
        nodes: List<GithubProfileQuery.Node2?>?,
        login: String,
        profileUrl: String,
    ): List<StarredRepoDto> {
        if (nodes.isNullOrEmpty()) {
            return emptyList()
        }

        return nodes.map { node ->
            StarredRepoDto(
                login = login,
                name = node?.name.orEmpty(),
                description = node?.description.orEmpty(),
                starsCount = node?.stargazerCount?.toString().orEmpty(),
                repoLanguage = node?.primaryLanguage?.name.orEmpty(),
                repoLanguageHexCodeColor = node?.primaryLanguage?.color.orEmpty(),
                profileUrl = profileUrl
            )
        }
    }

    private fun getMappedTopRepos(
        nodes: List<GithubProfileQuery.Node1?>?,
        login: String,
        profileUrl: String,
    ): List<TopRepoDto> {
        if (nodes.isNullOrEmpty()) {
            return emptyList()
        }

        return nodes.map { node ->
            TopRepoDto(
                login = login,
                name = node?.name.orEmpty(),
                description = node?.description.orEmpty(),
                starsCount = node?.stargazerCount?.toString().orEmpty(),
                repoLanguage = node?.primaryLanguage?.name.orEmpty(),
                repoLanguageHexCodeColor = node?.primaryLanguage?.color.orEmpty(),
                profileUrl = profileUrl
            )
        }
    }

    private fun getMappedPinnedRepos(
        nodes: List<GithubProfileQuery.Node?>?,
        login: String,
        profileUrl: String,
    ): List<PinnedRepoDto> {
        if (nodes.isNullOrEmpty()) {
            return emptyList()
        }

        return nodes.map { node ->
            PinnedRepoDto(
                login = login,
                name = node?.onRepository?.name.orEmpty(),
                description = node?.onRepository?.description.orEmpty(),
                starsCount = node?.onRepository?.stargazerCount?.toString().orEmpty(),
                repoLanguage = node?.onRepository?.primaryLanguage?.name.orEmpty(),
                repoLanguageHexCodeColor = node?.onRepository?.primaryLanguage?.color.orEmpty(),
                profileUrl = profileUrl
            )
        }
    }
}