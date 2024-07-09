package com.android.otrium.ui

import app.cash.turbine.test
import com.android.otrium.domain.entity.PinnedRepo
import com.android.otrium.domain.entity.StarredRepo
import com.android.otrium.domain.entity.TopRepo
import com.android.otrium.domain.entity.User
import com.android.otrium.domain.entity.UserDetails
import com.android.otrium.domain.repo.GetUserDetailsRepo
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
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
    fun `test api `() = runTest(StandardTestDispatcher()) {
        val user = User(
            login = "oscar",
            name = "oscar ekesiobi",
            profileUrl = "url",
            email = "oscar@yahoo.com",
            followers = 10,
            following = 15
        )
        coEvery { repo.getUser() } returns flowOf(user)

        val topRepos = listOf(
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
        coEvery { repo.getTop() } returns flowOf(topRepos)

        val starredRepos = listOf(
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
        coEvery { repo.getStarred() } returns flowOf(
            starredRepos
        )

        val pinnedRepos = listOf(
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
        coEvery { repo.getPinned() } returns flowOf(
            pinnedRepos
        )

        coEvery { repo.getUserDetailsFromApi(false) } returns Unit

        viewModel = GithubProfileViewModel(repo)

        assertTrue(viewModel.combinedResult.value == GithubProfileScreenState.Loading)

        //you could use the backgroundScope to collect the flow (basically start the flow pipeline)
        /*backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.combinedResult.collect()
        }*/

        val expectedUserDetails = UserDetails(
            user = user,
            pinnedRepos = pinnedRepos,
            topRepos = topRepos,
            starredRepos = starredRepos,
        )

        /*
        *  using turbine you could :
        *  - verify the duration of the emission i.e via the timeOut variable
        *  - verify that there was no emission or event i.e via the expectNoEvents()
        *  - verify that there was an emission or event i.e via the awaitItem()
        *  - verify that an error occurred during the emission i.e via the awaitError()
        *  - verify all the emissions have finished i.e via the awaitComplete()
        * */
        viewModel.combinedResult.test {
            val items = awaitItem() as GithubProfileScreenState.Success

            assertTrue(expectedUserDetails.user == items.userDetails?.user)

            assertTrue(expectedUserDetails.topRepos == items.userDetails?.topRepos)

            assertTrue(expectedUserDetails.starredRepos == items.userDetails?.starredRepos)

            assertTrue(expectedUserDetails.pinnedRepos == items.userDetails?.pinnedRepos)
        }

    }
}