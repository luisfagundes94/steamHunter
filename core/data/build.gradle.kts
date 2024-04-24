plugins {
    alias(libs.plugins.steamhunter.android.library)
    alias(libs.plugins.steamhunter.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.luisfagundes.data"
}

dependencies {
    api(projects.core.network)
    api(projects.core.common)
    api(projects.core.model)
}