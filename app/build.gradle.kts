plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.capital_taxi"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.capital_taxi"
        minSdk = 24
        targetSdk = 34
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation (libs.accompanist.pager.indicators)
    implementation("io.coil-kt:coil-compose:2.3.0")
    implementation ("io.ktor:ktor-client-core:2.3.2")
    implementation ("io.ktor:ktor-client-okhttp:2.3.2")
    implementation ("io.ktor:ktor-client-android:2.0.0")
    implementation ("io.ktor:ktor-client-serialization:2.0.0")
    implementation ("io.ktor:ktor-client-json:2.0.0")

    implementation ("io.ktor:ktor-client-content-negotiation:2.3.2")
    implementation ("io.ktor:ktor-serialization-gson:2.3.2")
    implementation ("com.googlecode.libphonenumber:libphonenumber:8.13.52")
    implementation ("com.hbb20:ccp:2.7.0")
    implementation ("com.google.android.gms:play-services-auth:")
    implementation ("com.airbnb.android:lottie-compose:6.6.0")
    implementation ("androidx.compose.animation:animation:")
    implementation ("androidx.camera:camera-core:1.3.1")
    implementation ("androidx.camera:camera-camera2:1.3.1")
    implementation ("androidx.camera:camera-view:1.3.1")
    implementation ("androidx.camera:camera-lifecycle:1.3.1")
    implementation("com.google.maps.android:maps-compose:4.3.0")
    implementation("com.google.maps.android:maps-compose-utils:4.3.0")
    implementation("com.google.maps.android:maps-compose-widgets:4.3.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation(platform("com.google.firebase:firebase-bom:33.6.0"))
    implementation ("androidx.compose.material3:material3:")
    implementation("com.google.firebase:firebase-auth")
    implementation ("androidx.compose.material3:material3:")
    implementation ("androidx.compose.foundation:foundation:")
    implementation ("androidx.navigation:navigation-compose:")
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.firebase.auth)
    implementation(libs.transportation.consumer)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}