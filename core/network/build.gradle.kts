plugins {
    alias(libs.plugins.steamhunter.android.library)
    alias(libs.plugins.steamhunter.android.hilt)
    id("kotlinx-serialization")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.luisfagundes.network"
    buildFeatures {
        buildConfig = true
    }
}
secrets {
    defaultPropertiesFileName = "api.properties"
}


dependencies {
    api(libs.kotlinx.datetime)
    api(projects.core.common)
    api(projects.core.model)

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
}