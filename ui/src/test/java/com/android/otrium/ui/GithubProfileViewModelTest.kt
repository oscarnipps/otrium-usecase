package com.android.otrium.ui

import com.android.otrium.domain.entity.PinnedRepo
import com.android.otrium.domain.entity.StarredRepo
import com.android.otrium.domain.entity.TopRepo
import com.android.otrium.domain.entity.User
import com.android.otrium.domain.repo.GetUserDetailsRepo
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GithubProfileViewModelTest {

    @RelaxedMockK
    private lateinit var repo: GetUserDetailsRepo

    private lateinit var viewModel: GithubProfileViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule.create()

    @Before
    fun setUp() {
        repo = mockk<GetUserDetailsRepo>()
    }

    @Test
    fun `test api `() = runTest (StandardTestDispatcher()){

        coEvery { repo.getUser() } returns flowOf(
            User(
                login = "oscar",
                name = "oscar ekesiobi",
                profileUrl = "url",
                email = "oscar@yahoo.com",
                followers = 10,
                following = 15
            )
        )

        coEvery { repo.getTop() } returns flowOf(
            listOf(
                TopRepo(
                    login = "oscar",
                    name = "oscar ekesiobi",
                    repoLanguage = "kotlin",
                    repoLanguageHexColor = "#ttyr7",
                    description = "super app",
                    starsCount = "10",
                    profileUrl = "url"
                )
            )
        )

        coEvery { repo.getStarred() } returns flowOf(
            listOf(
                StarredRepo(
                    login = "oscar",
                    name = "oscar ekesiobi",
                    repoLanguage = "kotlin",
                    repoLanguageHexColor = "#ttyr7",
                    description = "super app",
                    starsCount = "10",
                    profileUrl = "url"
                )
            )
        )

        coEvery { repo.getPinned() } returns flowOf(
            listOf(
                PinnedRepo(
                    login = "oscar",
                    name = "oscar ekesiobi",
                    repoLanguage = "kotlin",
                    repoLanguageHexColor = "#ttyr7",
                    description = "super app",
                    starsCount = "10",
                    profileUrl = "url"
                )
            )
        )

        coEvery { repo.getUserDetailsFromApi(false) } returns Unit

        viewModel = GithubProfileViewModel(repo)
    }
}