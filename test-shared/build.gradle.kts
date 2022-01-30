plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 22
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(KotlinX.coroutines.android)
    implementation(KotlinX.coroutines.core)
    implementation(KotlinX.coroutines.test)

    implementation(Testing.junit4)

    implementation(Testing.junit.jupiter.api)
    implementation(Testing.junit.jupiter.engine)

    implementation(AndroidX.test.runner)
    implementation(Testing.junit.jupiter.api)

    implementation("de.mannodermaus.junit5:android-test-core:_")
    implementation("de.mannodermaus.junit5:android-test-runner:_")
}