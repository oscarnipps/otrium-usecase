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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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
    ): LocalDataSource {
        return LocalDataSourceImpl(
            userDao,
            topRepoDao,
            pinnedRepoDao,
            starredRepoDao,
        )
    }

    @Provides
    @Singleton
    fun providesRemoteDataSource(
        apolloClient: ApolloClient,
        mapper: RemoteEntityMapper
    ): RemoteDataSource {
        return RemoteDataSourceImpl(apolloClient, mapper)
    }

    @Provides
    @Singleton
    fun providesUserDetailRepo(
        getUserDetailsRepoImpl: GetUserDetailsRepoImpl
    ): GetUserDetailsRepo {
        return getUserDetailsRepoImpl
    }


    //another way to inject dispatchers would have been to create a dispatchers module
    //where you have separate provides method for the types (io , main , default and unconfined)
    //and you also create annotation classes (with the required retention and qualifier annotations)
    //to differentiate between the dispatchers () this way is preferred her because you only inject
    //the DispatcherProvider and access what you need rather than specifying more than when needed
    @Provides
    @Singleton
    fun providesDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default

    }

}