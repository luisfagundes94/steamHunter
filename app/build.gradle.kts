import com.luisfagundes.convention.SteamHunterBuildType

plugins {
    alias(libs.plugins.steamhunter.android.application)
    alias(libs.plugins.steamhunter.android.application.compose)
    alias(libs.plugins.steamhunter.android.hilt)
    alias(libs.plugins.ksp)
}

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "com.luisfagundes.steamhunter"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            applicationIdSuffix = SteamHunterBuildType.DEBUG.applicationIdSuffix
        }
        release {
            isMinifyEnabled = true
            applicationIdSuffix = SteamHunterBuildType.RELEASE.applicationIdSuffix
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.named("debug").get()
        }
    }

    packaging {
        resources.excludes.addAll(
            listOf(
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md",
            )
        )
    }

    namespace = "com.luisfagundes.steamhunter"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.datastore)
    implementation(projects.core.protodata)
    implementation(projects.core.common)
    implementation(projects.core.network)
    implementation(projects.core.designsystem)
    implementation(projects.core.ui)

    implementation(projects.feature.games)
    implementation(projects.feature.profile)
    implementation(projects.feature.about)
    implementation(projects.feature.search)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3.adaptive)
    implementation(libs.androidx.compose.material3.adaptive.layout)
    implementation(libs.androidx.compose.material3.adaptive.navigation)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil.kt)

    ksp(libs.hilt.compiler)

    debugImplementation(libs.leakCanary)
}
