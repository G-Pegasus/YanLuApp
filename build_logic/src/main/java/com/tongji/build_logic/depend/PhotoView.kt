package com.tongji.build_logic.depend

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("MemberVisibilityCanBePrivate", "ObjectPropertyName", "SpellCheckingInspection")
object PhotoView {
  // https://github.com/Baseflow/PhotoView
  const val photoView = "com.github.chrisbanes:PhotoView:2.3.0"
}

fun Project.dependPhotoView() {
  dependencies {
    "implementation"(PhotoView.photoView)
  }
}