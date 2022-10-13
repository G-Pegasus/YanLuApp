package com.tongji.build_logic.depend

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("MemberVisibilityCanBePrivate", "ObjectPropertyName", "SpellCheckingInspection")
object Navigation {
  // navigation 因为有些坑，所以暂不使用，大部分情况可以使用 vp2 代替
  // https://developer.android.com/guide/navigation/navigation-getting-started#Set-up
  // https://developer.android.google.cn/kotlin/ktx?hl=zh_cn#navigation
  const val navigation_version = "2.4.2"
  const val `navigation-runtime-ktx` = "androidx.navigation:navigation-runtime-ktx:$navigation_version"
  const val `navigation-fragment-ktx` = "androidx.navigation:navigation-fragment-ktx:$navigation_version"
  const val `navigation-ui-ktx` = "androidx.navigation:navigation-test.ui-ktx:$navigation_version"
  
  // Testing Navigation
  const val `navigation-testing` = "androidx.navigation:navigation-testing:$navigation_version"
}

fun Project.dependNavigation() {
  dependencies {
    "implementation"(Navigation.`navigation-runtime-ktx`)
    "implementation"(Navigation.`navigation-fragment-ktx`)
    "implementation"(Navigation.`navigation-ui-ktx`)
  }
}