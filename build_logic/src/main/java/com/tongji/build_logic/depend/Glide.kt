package com.tongji.build_logic.depend

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("MemberVisibilityCanBePrivate", "ObjectPropertyName", "SpellCheckingInspection")
object Glide {
  // https://github.com/bumptech/glide
  const val glide_version = "4.14.0"
  
  const val glide = "com.github.bumptech.glide:glide:$glide_version"
  const val `glide-compiler` = "com.github.bumptech.glide:compiler:$glide_version"
}

fun Project.dependGlide() {
  dependencies {
    "implementation"(Glide.glide)
    "kapt"(Glide.`glide-compiler`)
  }
}