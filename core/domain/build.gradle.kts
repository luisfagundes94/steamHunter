plugins {
    alias(libs.plugins.steamhunter.android.library)
}

android {
    namespace = "com.luisfagundes.steamhunter.core.domain"
}

dependencies {
    implementation(libs.kotlinx.coroutines)
    implementation(libs.javax.inject)
}