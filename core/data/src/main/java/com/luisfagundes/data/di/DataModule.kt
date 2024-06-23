package com.luisfagundes.data.di

import com.luisfagundes.data.repository.OfflineFirstUserDataRepository
import com.luisfagundes.data.repository.SteamRepositoryImpl
import com.luisfagundes.data.utils.ConnectivityManagerNetworkMonitor
import com.luisfagundes.data.utils.NetworkMonitor
import com.luisfagundes.domain.repository.SteamRepository
import com.luisfagundes.domain.repository.UserDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindSteamRepository(
        repository: SteamRepositoryImpl
    ): SteamRepository

    @Binds
    internal abstract fun bindNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor

    @Binds
    internal abstract fun bindUserDataRepository(
        userDataRepository: OfflineFirstUserDataRepository,
    ): UserDataRepository
}
