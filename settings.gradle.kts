plugins {
    id("de.fayard.refreshVersions") version "0.30.2"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

rootProject.name = "EventCademy"
include(":app")
include(":util")
include(":data")
include(":domain")
include(":test-shared")
