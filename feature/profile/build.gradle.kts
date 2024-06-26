plugins {
    alias(libs.plugins.steamhunter.android.feature)
    alias(libs.plugins.steamhunter.android.library.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.luisfagundes.profile"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.common)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.accompanist.permissions)
}
