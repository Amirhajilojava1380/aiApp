@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger)
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.aiapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.aiapp"
        minSdk = 27
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    buildToolsVersion = "36.1.0"
    hilt {
        enableAggregatingTask = false
    }
    ksp {
        arg("dagger.hilt.disableModulesHaveInstallInCheck", "true")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.storage)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.bundles.room)
    ksp(libs.roomcompiler)

    implementation(libs.bundles.datastore)

    implementation(libs.daggerhilt)
    implementation(libs.bundles.hilt)
    ksp(libs.hilt.android.compiler)
    androidTestImplementation(libs.hilt.testing)
    androidTestAnnotationProcessor(libs.hilt.compiler)
    testImplementation(libs.hilt.testing)
    testAnnotationProcessor(libs.hilt.compiler)

    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.lifecycle)

    implementation(libs.lottie)
    implementation(libs.bundles.pager)

    implementation(libs.persiandate)

    implementation(libs.constraintlayout)

    implementation(libs.bundles.org)

    implementation(libs.bundles.moshi)
    ksp(libs.moshikapt)
    implementation(libs.bundles.retrofit)

   implementation(libs.glide)
   implementation(libs.exifinterface)

   implementation(libs.bundles.media3)



}