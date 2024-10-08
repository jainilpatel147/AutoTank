plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.autotank"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.autotank"
        minSdk = 29
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("androidx.core:core:1.10.0")
    // Retrofit for API calls
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    // Converter for JSON to Java objects (using Gson)
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp for logging network requests (optional but useful)
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.1")
}