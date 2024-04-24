plugins {
    alias(libs.plugins.steamhunter.android.feature)
    alias(libs.plugins.steamhunter.android.library.compose)
}

android {
    namespace = "com.luisfagundes.search"
}

dependencies {
    implementation(projects.core.data)
}