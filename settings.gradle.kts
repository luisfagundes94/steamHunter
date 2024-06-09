pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "SteamHunter"
include(":app")
include(":core:data")
include(":core:common")
include(":sync:work")
include(":core:designsystem")
include(":feature:games")
include(":core:ui")
include(":convention")
include(":feature:profile")
include(":feature:about")
include(":feature:search")
include(":core:protodata")
include(":core:datastore")
include(":core:network")
include(":core:domain")
include(":core:testing")
