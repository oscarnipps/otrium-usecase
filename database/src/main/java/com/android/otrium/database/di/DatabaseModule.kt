package com.android.otrium.database.di

import android.content.Context
import androidx.room.Room
import com.android.otrium.database.AppDatabase
import com.android.otrium.database.dao.PinnedRepoDao
import com.android.otrium.database.dao.StarredRepoDao
import com.android.otrium.database.dao.TopRepoDao
import com.android.otrium.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "AppLocalDatabase.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesUserRepoDao (appDatabase: AppDatabase) : UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun providesPinnedRepoDao (appDatabase: AppDatabase) : PinnedRepoDao {
        return appDatabase.pinnedRepoDao()
    }


    @Provides
    @Singleton
    fun providesStarredRepoDao (appDatabase: AppDatabase) : StarredRepoDao {
        return appDatabase.starredRepoDao()
    }


    @Provides
    @Singleton
    fun providesTopRepoDao (appDatabase: AppDatabase) : TopRepoDao {
        return appDatabase.topRepoDao()
    }
}