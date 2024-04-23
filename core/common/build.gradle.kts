plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.luisfagundes.steamhunter.core.common"
}

dependencies {
    api(libs.kotlinx.datetime)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}