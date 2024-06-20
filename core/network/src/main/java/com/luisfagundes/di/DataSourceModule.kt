package com.luisfagundes.di

import com.luisfagundes.datasource.SteamDataSource
import com.luisfagundes.network.retrofit.RetrofitSteamProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun bindNetworkDataSource(
        dataSource: RetrofitSteamProvider
    ): SteamDataSource
}