import com.luisfagundes.SteamHunterBuildType

plugins {
    alias(libs.plugins.steamhunter.android.application)
    alias(libs.plugins.steamhunter.android.application.compose)
    alias(libs.plugins.steamhunter.android.hilt)
    alias(libs.plugins.ksp)
}

kotlin {
    sourceSets.all {
        languageSettings {
            enableLanguageFeature("ExplicitBackingFields")
        }
    }
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

    buildTypes {
        debug {
            applicationIdSuffix = SteamHunterBuildType.DEBUG.applicationIdSuffix
        }
        release {
            isMinifyEnabled = true
            applicationIdSuffix = SteamHunterBuildType.RELEASE.applicationIdSuffix
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.named("debug").get()
        }
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    namespace = "com.luisfagundes.steamhunter"
}

dependencies {
    implementation(projects.sync.work)

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

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
}