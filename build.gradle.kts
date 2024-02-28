// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.50" apply false
}
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.50")
    }
}