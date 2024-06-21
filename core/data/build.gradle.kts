plugins {
    alias(libs.plugins.steamhunter.android.library)
    alias(libs.plugins.steamhunter.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.luisfagundes.steamhunter.data"
}

dependencies {
    implementation(projects.core.testing)
    api(projects.core.network)
    api(projects.core.common)
    api(projects.core.datastore)
    api(projects.core.domain)
}