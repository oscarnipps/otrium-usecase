package com.android.otrium.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.otrium.database.entity.StarredRepoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StarredRepoDao {
    @Query("SELECT * FROM StarredRepoEntity")
    fun getStarredRepo(): Flow<List<StarredRepoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStarredRepo(starredRepoEntity:  List<StarredRepoEntity>)
}