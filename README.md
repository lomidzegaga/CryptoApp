# Crypto Currency Android

Welcome to Crypto Currency App - an app created to keep you informed about the constantly evolving
cryptocurrency landscape.
Manage your portfolio, keep an eye on market movements, and keep an eye on prices.

The application is a totally written by [Kotlin](https://kotlinlang.org/docs/home.html), the UI
layer is using
[Jetpack Compose](https://developer.android.com/jetpack/compose), and the navigation is managed
using
[Compose Navigation](https://developer.android.com/develop/ui/compose/navigation).

<br>

# Table of Content

* [Screenshots](#screenshots)
* [Used Technologies with dependencies](#used-technologies-with-dependencies)
* [Build Instruction](#build-instruction)

  <br>

## screenshots

Here are some screenshots of the application:

|<img src="https://github.com/user-attachments/assets/7da4e387-8a90-4df2-8e83-42b5104987d4" width="200" />|<img src="https://github.com/user-attachments/assets/d3c8b2c1-eb05-451e-a33b-0859b028bbcf" width="200" />|<img src="https://github.com/user-attachments/assets/db86b855-5b0e-4cca-b9ee-6c685e228b71" width="200" />|
|-|-|-|
|<img src="https://github.com/user-attachments/assets/990ca0bc-79b9-4ca5-968c-13828acabb2b" width="200" />|<img src="https://github.com/user-attachments/assets/28262dd6-8e10-49f3-91f6-c08709d302cd" width="200" />|<img src="https://github.com/user-attachments/assets/b7417400-02b2-4641-bef5-bbfe4f72c0f1" width="200" />|

<br>

## used technologies with dependencies

* Core AndroidX Libraries

```kotlin
dependency("androidx.core:core-ktx:1.15.0")
dependency("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
dependency("androidx.activity:activity-compose:1.9.3")
dependency("androidx.compose:compose-bom:2024.10.01")
dependency("androidx.compose.material3:material3")
```

* Compose UI

```kotlin
dependency("androidx.compose.ui:ui")
dependency("androidx.compose.ui:ui-graphics")
dependency("androidx.compose.ui:ui-tooling")
dependency("androidx.compose.ui:ui-tooling-preview")
```

* Navigation

```kotlin
dependency("androidx.navigation:navigation-compose:2.8.3")
```

* Charts and Animations

```kotlin
dependency("com.patrykandpatrick.vico:compose-m3:2.0.0-alpha.28")
dependency("com.airbnb.android:lottie-compose:6.5.0")
```

* Networking

```kotlin
dependency("com.squareup.retrofit2:retrofit:2.11.0")
dependency("com.squareup.okhttp3:okhttp:4.12.0")
dependency("com.squareup.okhttp3:logging-interceptor:4.12.0")
```

* Json And Serialization

```kotlin
dependency("com.squareup.moshi:moshi:1.15.1")
dependency("com.squareup.moshi:moshi-kotlin:1.15.1")
dependency("com.squareup.retrofit2:converter-moshi:2.9.0")
dependency("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
```

* Dependency Injection

```kotlin
dependency("com.google.dagger:hilt-android:2.52")
dependency("com.google.dagger:hilt-android-compiler:2.52")
dependency("androidx.hilt:hilt-navigation-compose:1.2.0")
```

* Image Loading

```kotlin
dependency("io.coil-kt:coil-compose:2.7.0")
```

<br>

## Build Instruction
Just clone the project and open it in Android Studio.
Makes sure to select the `app` configuration when building.
