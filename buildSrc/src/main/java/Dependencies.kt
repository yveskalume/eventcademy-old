package com.yveskalume.buildsrc

object App {
    const val compileSdkVersion = 31
    const val buildToolsVersion = "_"
    const val minSdkVersion = 21
    const val targetSdkVersion = 31
    const val jvmTarget = "_"
}

object Plugin {
    const val gradle = "com.android.tools.build:gradle:_"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
    const val navigationSafeArgs = "android.arch.navigation:navigation-safe-args-gradle-plugin:_"
}

object Version {
    const val kotlin = "_"
    const val hilt = "_"
}

object Module {
    const val data = ":data"
    const val util = ":util"
    const val domain = ":domain"
}

object Deps {

    const val viewBinding = "com.github.yogacp:android-viewbinding:_"
    const val roundedImageview = "com.makeramen:roundedimageview:_"
    const val dotIndicator = "com.tbuonomo.andrui:viewpagerdotsindicator:_"
    const val material = "com.google.android.material:material:_'"
    const val pinview = "com.github.mukeshsolanki:android-otpview-pinview:_"
    const val timber = "com.jakewharton.timber:timber:_"
    const val formValidator = "com.github.ShabanKamell.FormValidator:core:_"
    const val glide = "com.github.bumptech.glide:glide:_"
    const val blurry = "jp.wasabeef:blurry:_"
    const val blurBehind = "no.danielzeller.blurbehindlib:blurbehindlib:_"
    const val imageSlider = "com.github.smarteist:autoimageslider:_"
    const val lottie = "com.airbnb.android:lottie:_"
    const val circleImageView = "de.hdodenhof:circleimageview:_"
    const val materialDialog = "com.afollestad.material-dialogs:core:_"

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}"
    }

    object Hilt {
        const val classPath = "com.google.dagger:hilt-android-gradle-plugin:${Version.hilt}"
        const val hilt = "com.google.dagger:hilt-android:${Version.hilt}"
        const val processor = "com.google.dagger:hilt-compiler:${Version.hilt}"

    }

    object GooglePlayService {
        private const val version = "_"
        const val core = "com.google.android.play:core-ktx:_"
    }

    object Groupie {
        private const val version = "_"
        const val core = "com.xwray:groupie:_"
        const val viewBinding = "com.xwray:groupie-viewbinding:_"
    }


    object Mavericks {
        const val mvrx = "com.airbnb.android:mvrx:_"
    }

    object Retrofit {
        private const val retrofitVersion = "_"
        const val coroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:_"
        const val retrofit = "com.squareup.retrofit2:retrofit:_"
        const val gson = "com.squareup.retrofit2:converter-gson:_"
    }

    object Koin {
        private const val koin_version = "_"
        const val android = "org.koin:koin-android:_"
        const val androidxScope = "org.koin:koin-androidx-scope:_"
        const val viewmodel = "org.koin:koin-androidx-viewmodel:_"
    }

    object Coroutines {
        private const val coroutinesVersion = "_"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:_"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:_"
        const val playService = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:_"
    }

    object Epoxy {
        private const val epoxyVersion = "_"
        val epoxy = "com.airbnb.android:epoxy:_"
        val dataBinding = "com.airbnb.android:epoxy-databinding:_"
        val processor = "com.airbnb.android:epoxy-processor:_"
    }

    object Firebase {
        const val bom = "com.google.firebase:firebase-bom:_"
        const val firestore = "com.google.firebase:firebase-firestore-ktx"
        const val storage = "com.google.firebase:firebase-storage"
        const val auth = "com.google.firebase:firebase-auth-ktx"
        const val ui = "com.firebaseui:firebase-ui-auth:_"
        const val fcm = "com.google.firebase:firebase-messaging-ktx"
        const val googlePlayServiceAuth = "com.google.android.gms:play-services-auth:_"
    }

    object WorkManager {
        private const val work_version = "_"

        // Kotlin + coroutines
        const val kotlin = "androidx.work:work-runtime-ktx:_"
    }

    object PdfTron {
        private const val version = "_"
        const val pdftron = "com.pdftron:pdftron:_"
        const val tools = "com.pdftron:tools:_"
    }

    object AndroidX {
        private const val nav_version = "_"

        const val appcompat = "androidx.appcompat:appcompat:_"
        const val core = "androidx.core:core-ktx:_"
        const val legacy = "androidx.legacy:legacy-support-v4:_"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:_"
        const val viewpager = "androidx.viewpager2:viewpager2:_"
        const val startup = "androidx.startup:startup-runtime:_"

        object Navigation {
            private const val version = "_"

            const val fragment = "androidx.navigation:navigation-fragment-ktx:_"
            const val ui = "androidx.navigation:navigation-ui-ktx:_"
            const val test = "androidx.navigation:navigation-testing:_"
        }

        object Test {
            const val junit = "junit:junit:_"
            const val ext = "androidx.test.ext:junit:_"
            object Espresso {
                private const val version = "_"
                const val core = "androidx.test.espresso:espresso-core:_"
            }
        }
    }
}
