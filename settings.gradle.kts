plugins {
    id("de.fayard.refreshVersions") version "0.40.1"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

refreshVersions {
    rejectVersionIf {
        candidate.stabilityLevel.isLessStableThan(current.stabilityLevel)
    }
    enableBuildSrcLibs()
}

rootProject.name = "EventCademy"
include(":app")
include(":util")
include(":data")
include(":domain")
include(":test-shared")
