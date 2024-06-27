package com.android.otrium.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.otrium.database.dao.PinnedRepoDao
import com.android.otrium.database.dao.StarredRepoDao
import com.android.otrium.database.dao.TopRepoDao
import com.android.otrium.database.dao.UserDao
import com.android.otrium.database.entity.PinnedRepoEntity
import com.android.otrium.database.entity.StarredRepoEntity
import com.android.otrium.database.entity.TopRepoEntity
import com.android.otrium.database.entity.UserEntity


@Database(
    entities = [
        UserEntity::class,
        TopRepoEntity::class,
        StarredRepoEntity::class,
        PinnedRepoEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun topRepoDao(): TopRepoDao

    abstract fun starredRepoDao(): StarredRepoDao

    abstract fun pinnedRepoDao(): PinnedRepoDao
}