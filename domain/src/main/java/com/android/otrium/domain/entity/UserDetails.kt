package com.android.otrium.domain.entity

import kotlin.random.Random

data class UserDetails(
    val user : User?,
    val starredRepos : List<StarredRepo>,
    val topRepos : List<TopRepo>,
    val pinnedRepos : List<PinnedRepo>,
){
    //would ideally add this to a wrapper class rather than adding directly here
    //needed to avoid the combineFlow() problem when the same state is emitted more than once
    //the combine flow ignores passing the update / emissions
    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        return Random.nextInt()
    }
}
