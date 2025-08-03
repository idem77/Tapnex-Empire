plugins {
        id 'com.android.application'
            id 'org.jetbrains.kotlin.android'
            }

            android {
                namespace 'com.yourpackage.tapnexempire'
                    compileSdk 34

                        defaultConfig {
                                applicationId "com.yourpackage.tapnexempire"
                                        minSdk 24
                                                targetSdk 34
                                                        versionCode 1
                                                                versionName "1.0"
                                                                    }

                                                                        buildFeatures {
                                                                                compose true
                                                                                    }

                                                                                        composeOptions {
                                                                                                kotlinCompilerExtensionVersion = "1.6.0"
                                                                                                    }

                                                                                                        kotlinOptions {
                                                                                                                jvmTarget = '1.8'
                                                                                                                    }
                                                                                                                    }

                                                                                                                    dependencies {
                                                                                                                        implementation "androidx.core:core-ktx:1.13.0"
                                                                                                                            implementation "androidx.activity:activity-compose:1.8.2"
                                                                                                                                implementation "androidx.compose.ui:ui:$compose_version"
                                                                                                                                    implementation "androidx.compose.material:material:1.6.0"
                                                                                                                                        implementation "androidx.compose.ui:ui-tooling-preview:$compose_version"
                                                                                                                                            implementation "androidx.navigation:navigation-compose:2.7.7"

                                                                                                                                                debugImplementation "androidx.compose.ui:ui-tooling:$compose_version"
                                                                                                                                                }
}
