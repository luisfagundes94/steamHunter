package com.luisfagundes

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val dispatchers: SteamHunterDispatchers)

enum class SteamHunterDispatchers {
    Default,
    IO,
}
