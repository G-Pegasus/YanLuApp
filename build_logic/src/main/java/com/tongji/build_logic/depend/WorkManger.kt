package com.tongji.build_logic.depend

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("MemberVisibilityCanBePrivate", "ObjectPropertyName", "SpellCheckingInspection")
object WorkManger {
  // https://developer.android.google.cn/kotlin/ktx?hl=zh_cn#workmanager
  const val `work-runtime-ktx` = "androidx.work:work-runtime-ktx:2.7.1"
}

fun Project.dependWorkManger() {
  dependencies {
    "implementation"(WorkManger.`work-runtime-ktx`)
  }
}

