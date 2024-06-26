buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.android.gradlePlugin)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.secrets) apply false
    alias(libs.plugins.room) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
}

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
}

detekt {
    autoCorrect = true
    parallel = true
    source.setFrom(
        files(
            "src/main/java",
            "src/test/java",
            "src/main/kotlin",
            "src/test/kotlin"
        )
    )
}

dependencies {
    detektPlugins(libs.detekt.formatting)
}
