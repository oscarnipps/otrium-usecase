package com.android.otrium.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.otrium.domain.common.AppError
import com.android.otrium.domain.entity.UserDetails
import com.android.otrium.domain.repo.GetUserDetailsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubProfileViewModel @Inject constructor(
    private val getUserDetailsRepo: GetUserDetailsRepo
) : ViewModel() {

    init {
        getUserFromApi(isForceRefresh = false)
    }

    fun getUserFromApi(isForceRefresh: Boolean) {
        viewModelScope.launch {
            getUserDetailsRepo.getUserDetailsFromApi(isForceRefresh)
        }
    }

    val combinedResult = combine(
        getUserDetailsRepo.getUser(),
        getUserDetailsRepo.getTop(),
        getUserDetailsRepo.getStarred(),
        getUserDetailsRepo.getPinned(),
    ) { user, topRepos, starredRepos, pinnedRepos ->
        GithubProfileScreenState.Success(
            UserDetails(
                user = user,
                pinnedRepos = pinnedRepos,
                topRepos = topRepos,
                starredRepos = starredRepos,
            )
        )
    }.distinctUntilChanged().catch {
        //todo: handle error properly
        GithubProfileScreenState.Error(AppError.UNKNOWN_ERROR)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = GithubProfileScreenState.Loading
    )


}