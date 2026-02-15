plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.myapplicatiocalculyator"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.myapplicatiocalculyator"
        minSdk = 21 // Android 5.0+
        targetSdk = 36
        versionCode = 1
        versionName = "Beta 1.0"

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
}