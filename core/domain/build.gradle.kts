plugins {
    alias(libs.plugins.steamhunter.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
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
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}