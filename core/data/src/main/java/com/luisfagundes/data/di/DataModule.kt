package com.luisfagundes.data.di

import com.luisfagundes.data.utils.ConnectivityManagerNetworkMonitor
import com.luisfagundes.data.utils.NetworkMonitor
import com.luisfagundes.data.utils.TimeZoneBroadcastMonitor
import com.luisfagundes.data.utils.TimeZoneMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor

    @Binds
    internal abstract fun binds(impl: TimeZoneBroadcastMonitor): TimeZoneMonitor
}