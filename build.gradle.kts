import com.android.build.gradle.internal.packaging.defaultExcludes
import org.jetbrains.kotlin.resolve.scopes.DescriptorKindExclude.Extensions.excludes

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

detekt {
    autoCorrect = true
    parallel = true
    config.setFrom(files(projectDir))
    source.setFrom("src/main/java")
    source.setFrom("src/main/kotlin")
//    excludes(
//        "**/resources/**",
//        "**/META-INF/**",
//        "**/build/**",
//        "**/generated/**",
//    )
    config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
}

dependencies {
    detektPlugins(libs.detekt.formatting)
}
