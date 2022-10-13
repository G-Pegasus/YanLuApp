package com.tongji.build_logic.depend

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

object AutoService {
  // 谷歌官方的一种动态加载库 https://github.com/google/auto/tree/master/service
  const val autoService = "com.google.auto.service:auto-service:1.0.1"
}

fun Project.dependAutoService() {
  dependencies {
    // 谷歌官方的一种动态加载库 https://github.com/google/auto/tree/master/service
    "kapt"(AutoService.autoService)
    "compileOnly"(AutoService.autoService)
  }
}