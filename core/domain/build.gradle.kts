plugins {
    alias(libs.plugins.steamhunter.android.library)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.luisfagundes.steamhunter.core.domain"
}

dependencies {
    api(projects.core.data)
    api(projects.core.model)

    implementation(libs.javax.inject)
}