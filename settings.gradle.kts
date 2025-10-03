// TapnexEmpire/settings.gradle.kts

pluginManagement {
    repositories {
        google()               // For resolving plugins
        gradlePluginPortal()   // Default Gradle plugin portal
        mavenCentral()         // Any extra plugin dependencies
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TapnexEmpire"
include(":app")
