package com.android.otrium.network

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY  }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun providesApolloClient(okHttpClient: OkHttpClient) : ApolloClient {
        return ApolloClient
            .Builder()
            .serverUrl(Constants.SERVER_URL)
            .addHttpHeader(Constants.AUTH_HEADER , Constants.HEADER_VALUE)
            .okHttpClient(okHttpClient)
            .build()
    }


}