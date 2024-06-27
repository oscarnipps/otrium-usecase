package com.android.otrium.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.otrium.database.entity.PinnedRepoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PinnedRepoDao {
    @Query("SELECT * FROM PinnedRepoEntity")
    fun getPinnedRepo(): Flow<List<PinnedRepoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPinnedRepo(pinnedRepoEntity: List<PinnedRepoEntity>)
}