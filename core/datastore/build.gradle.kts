plugins {
    alias(libs.plugins.steamhunter.android.library)
    alias(libs.plugins.steamhunter.android.hilt)
}

android {
    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }
    namespace = "com.luisfagundes.steamhunter.core.datastore"
    testOptions {
        unitTests {
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    api(libs.androidx.dataStore.core)
    api(projects.core.protodata)
    api(projects.core.model)

    implementation(projects.core.common)
}