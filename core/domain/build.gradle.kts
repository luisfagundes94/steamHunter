plugins {
    alias(libs.plugins.steamhunter.android.library)
}

android {
    namespace = "com.luisfagundes.steamhunter.core.domain"
}

dependencies {
    implementation(projects.core.common)
    api(projects.core.model)
    implementation(projects.core.testing)
    implementation(libs.kotlinx.coroutines)
    implementation(libs.javax.inject)
}