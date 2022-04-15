// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Libs.gradle_plugin)
        classpath(Libs.kotlin_gradle_plugin)

        classpath(Google.playServicesGradlePlugin)

        classpath(Google.dagger.hilt.android.gradlePlugin)
        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:_")
        classpath("de.mannodermaus.gradle.plugins:android-junit5:_")


        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.withType<Test> {
    reports {
        junitXml.isEnabled = true
    }
}