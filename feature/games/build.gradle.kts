plugins {
    alias(libs.plugins.steamhunter.android.feature)
    alias(libs.plugins.steamhunter.android.library.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.luisfagundes.games"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.common)
    implementation(projects.core.testing)
    implementation(libs.accompanist.permissions)
    implementation(libs.kotlinx.serialization.json)
}
