plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("dagger.hilt.android.plugin")
    id("de.mannodermaus.android-junit5")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.yvkalume.eventcademy"
        minSdk = 22
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArgument("runnerBuilder", "de.mannodermaus.junit5.AndroidJUnit5Builder")

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.5"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":data"))
    implementation(AndroidX.core.ktx)
    implementation(AndroidX.appCompat)
    implementation(Google.android.material)
    implementation(AndroidX.compose.ui)
    implementation(AndroidX.compose.material)
    implementation(AndroidX.compose.ui.toolingPreview)
    implementation(AndroidX.lifecycle.runtimeKtx)
    implementation(AndroidX.activity.compose)
    testImplementation(Testing.junit4)
    implementation(AndroidX.constraintLayout.compose)

    implementation(AndroidX.navigation.compose)
    implementation(AndroidX.hilt.navigationCompose)

    implementation(platform(Firebase.bom))
//    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation(Google.android.playServices.auth)

    implementation(Google.dagger.hilt.android)
    kapt(Google.dagger.hilt.compiler)

    implementation("com.airbnb.android:mavericks:_")

    androidTestImplementation(AndroidX.test.ext.junit)
    androidTestImplementation(AndroidX.test.espresso.core)
    androidTestImplementation(AndroidX.compose.ui.testJunit4)
    debugImplementation(AndroidX.compose.ui.tooling)

    testImplementation(Testing.junit.jupiter.api)
    testRuntimeOnly(Testing.junit.jupiter.engine)

    androidTestImplementation(AndroidX.test.runner)
    androidTestImplementation(Testing.junit.jupiter.api)

    testImplementation(Testing.mockK)
    testImplementation("io.mockk:mockk-agent-jvm:_")


    androidTestImplementation("de.mannodermaus.junit5:android-test-core:_")
    androidTestRuntimeOnly("de.mannodermaus.junit5:android-test-runner:_")
}