plugins {
    alias(libs.plugins.steamhunter.android.feature)
    alias(libs.plugins.steamhunter.android.library.compose)
}

android {
    namespace = "com.luisfagundes.games"
}

dependencies {
    implementation(projects.core.data)

    implementation(libs.accompanist.permissions)
}