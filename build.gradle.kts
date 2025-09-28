// TapnexEmpire/build.gradle.kts
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.3.0")
        classpath("com.google.gms:google-services:4.4.0") // For Firebase
    }
}

plugins {
    // Optional: Kotlin plugin
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
