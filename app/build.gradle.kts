plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.tananaev.switcher"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.tananaev.google.fi.switcher"
        minSdk = 23
        targetSdk = 36
        versionCode = 8
        versionName = "2.4"
    }

    flavorDimensions += "default"
    productFlavors {
        create("regular") {
            isDefault = true
            extra["enableCrashlytics"] = false
        }
        create("google")
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
    "googleImplementation"(platform(libs.firebase.bom))
    "googleImplementation"(libs.firebase.analytics.ktx)
    "googleImplementation"(libs.firebase.crashlytics)
    "googleImplementation"(libs.play.services.ads)
    "googleImplementation"(libs.play.review.ktx)
}
