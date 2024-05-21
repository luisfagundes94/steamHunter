package com.luisfagundes.di

import com.luisfagundes.datasource.NetworkDataSource
import com.luisfagundes.network.retrofit.RetrofitNetworkProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun bindNetworkDataSource(
        retrofit: RetrofitNetworkProvider
    ): NetworkDataSource
}