// TapnexEmpire/build.gradle.kts

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
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}
