plugins {
    alias(libs.plugins.steamhunter.android.library)
    alias(libs.plugins.steamhunter.android.hilt)
}

android {
    namespace = "com.luisfagundes.steamhunter.core.common"
}

dependencies {
    api(libs.kotlinx.datetime)
    api(libs.timber)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}
