android {
    namespace = "com.tapnexempire"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tapnexempire"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2023.10.01")
    implementation(composeBom)

    // ✅ Material 3
    implementation("androidx.compose.material3:material3")

    // ✅ Compose UI
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // ✅ Activity Compose
    implementation("androidx.activity:activity-compose:1.8.2")
}
