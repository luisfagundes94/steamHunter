plugins {
    alias(libs.plugins.steamhunter.android.library)
    alias(libs.plugins.steamhunter.android.library.compose)
}

android {
    namespace = "com.luisfagundes.ui"
}

dependencies {
    api(projects.core.designsystem)
    implementation(libs.kotlinx.datetime)
}
