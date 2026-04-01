plugins {
    alias(libs.plugins.android.application)
    id("com.google.devtools.ksp")
}
android {
    namespace = "com.example.myapplicationhome"
    compileSdk {
        version = release(35)
    }

    defaultConfig {
        applicationId = "com.example.myapplicationhome"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.3.0")

    //room
    val room_version = "2.6.1"
    // Room runtime
    implementation("androidx.room:room-runtime:$room_version")
    // Kotlin extensions & Coroutines support
    implementation("androidx.room:room-ktx:$room_version")
    // Annotation Processor (KSP - recommended)
    ksp("androidx.room:room-compiler:$room_version")

// ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")

// LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.4")

// Activity KTX
    implementation("androidx.activity:activity-ktx:1.9.0")

// Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")

}