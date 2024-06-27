package com.android.otrium.data.di

import com.android.otrium.data.local.LocalDataSource
import com.android.otrium.data.local.LocalDataSourceImpl
import com.android.otrium.data.remote.RemoteDataSource
import com.android.otrium.data.remote.RemoteDataSourceImpl
import com.android.otrium.data.remote.mappers.RemoteEntityMapper
import com.android.otrium.data.repo.GetUserDetailsRepoImpl
import com.android.otrium.database.dao.PinnedRepoDao
import com.android.otrium.database.dao.StarredRepoDao
import com.android.otrium.database.dao.TopRepoDao
import com.android.otrium.database.dao.UserDao
import com.android.otrium.domain.repo.GetUserDetailsRepo
import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providesLocalDataSource(
        userDao: UserDao,
        topRepoDao: TopRepoDao,
        pinnedRepoDao: PinnedRepoDao,
        starredRepoDao: StarredRepoDao,
    ) : LocalDataSource {
        return LocalDataSourceImpl(
            userDao,
            topRepoDao,
            pinnedRepoDao,
            starredRepoDao,
        )
    }

    @Provides
    @Singleton
    fun providesRemoteDataSource(apolloClient: ApolloClient , mapper: RemoteEntityMapper) : RemoteDataSource {
        return RemoteDataSourceImpl(apolloClient,mapper)
    }

    @Provides
    @Singleton
    fun providesUserDetailRepo(
        getUserDetailsRepoImpl: GetUserDetailsRepoImpl
    ): GetUserDetailsRepo {
        return getUserDetailsRepoImpl
    }

}