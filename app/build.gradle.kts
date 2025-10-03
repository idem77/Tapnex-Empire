// TapnexEmpire/app/build.gradle.kts

plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.gms.google-services") // Firebase
}

android {
    namespace = "com.tapnexempire"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tapnexempire"
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        // BOM handles version compatibility, so compiler version auto-adjust
        kotlinCompilerExtensionVersion = "1.5.15"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Compose BOM - auto-compatible versions
    implementation(platform("androidx.compose:compose-bom:2025.09.00"))

    // Jetpack Compose
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.activity:activity-compose")

    // Navigation
    implementation("androidx.navigation:navigation-compose")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.4.0"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")

    // Core Android
    implementation("androidx.core:core-ktx")
    implementation("androidx.appcompat:appcompat")
}
