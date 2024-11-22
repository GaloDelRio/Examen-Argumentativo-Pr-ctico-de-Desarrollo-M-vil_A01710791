plugins {
    // Plugin para una aplicación Android
    alias(libs.plugins.android.application)

    // Plugin para el soporte de Kotlin en Android
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.kotlin.examen"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.kotlin.examen"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        // Configuración para pruebas instrumentadas
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            // Configuración de ProGuard para optimización y eliminación de código no utilizado
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        // Configuración de compatibilidad con Java 8
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        // Configuración del target JVM para Kotlin
        jvmTarget = "1.8"
    }

    buildFeatures {
        // Activación de ViewBinding para facilitar la vinculación de vistas
        viewBinding = true
    }
}

dependencies {
    // Dependencias básicas para Android

    // Librería para trabajar con las funcionalidades básicas de Android
    implementation(libs.androidx.core.ktx)

    // Librería para compatibilidad con versiones anteriores de la interfaz de usuario
    implementation(libs.androidx.appcompat)

    // Librería de Material Design para widgets y componentes de interfaz
    implementation(libs.material)

    // Dependencias para pruebas unitarias y de integración

    // Librería para pruebas unitarias
    testImplementation(libs.junit)

    // Librería para pruebas instrumentadas en Android
    androidTestImplementation(libs.androidx.junit)

    // Librería para pruebas de interfaz de usuario (UI) con Espresso
    androidTestImplementation(libs.androidx.espresso.core)

    // Dependencias para la interfaz de usuario y el ciclo de vida

    // RecyclerView para listas de elementos
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // Fragmentos con soporte para Kotlin
    implementation("androidx.fragment:fragment-ktx:1.5.0")

    // Actividad con soporte para Kotlin
    implementation("androidx.activity:activity-ktx:1.5.0")

    // DataBinding para vincular datos a las vistas de manera más eficiente
    implementation("androidx.databinding:databinding-runtime:7.1.2")

    // ViewModel con soporte para LiveData y Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")

    // LiveData con soporte para Kotlin
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")

    // Librería para carga de imágenes (Glide)
    implementation ("com.github.bumptech.glide:glide:4.12.0")

    // Dependencia para Parse SDK (para trabajar con Parse Server)
    implementation("com.github.parse-community.Parse-SDK-Android:parse:4.3.0")

    // Retrofit para trabajar con APIs REST y conversión JSON a objetos Kotlin (usando Gson)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Librería para usar CardView en la interfaz
    implementation ("androidx.cardview:cardview:1.0.0")
}
