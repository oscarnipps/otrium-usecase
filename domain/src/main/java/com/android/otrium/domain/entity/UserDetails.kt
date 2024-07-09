package com.android.otrium.domain.entity

import kotlin.random.Random

data class UserDetails(
    val user : User?,
    val starredRepos : List<StarredRepo>,
    val topRepos : List<TopRepo>,
    val pinnedRepos : List<PinnedRepo>,
){

    //NOTE:
    //It is not possible to collect the same value twice in a row with state flows, because they're
    // designed to specifically to react to a change of state.
    //
    //StateFlows won't emit the same values by design
    //The easiest way is to create your own class / object and override equals and hashCode so that they are always different
    //would ideally add this to a wrapper class rather than adding directly here
    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        return Random.nextInt()
    }
}
