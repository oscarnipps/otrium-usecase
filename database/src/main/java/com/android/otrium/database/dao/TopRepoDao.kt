package com.android.otrium.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.otrium.database.entity.TopRepoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopRepoDao {
    @Query("SELECT * FROM TopRepoEntity")
    fun getTopRepo(): Flow<List<TopRepoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopRepo(topRepoEntity:  List<TopRepoEntity>)
}