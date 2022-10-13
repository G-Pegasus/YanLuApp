package com.tongji.build_logic.depend

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("MemberVisibilityCanBePrivate", "ObjectPropertyName", "SpellCheckingInspection")
object Lottie {
  // https://lottiefiles.com/blog/working-with-lottie/getting-started-with-lottie-animations-in-android-app
  // 最新版本号查看：https://maven-badges.herokuapp.com/maven-central/com.airbnb.android/lottie/badge.svg
  const val lottie = "com.airbnb.android:lottie:5.2.0"
}

fun Project.dependLottie() {
  dependencies {
    "implementation"(Lottie.lottie)
  }
}