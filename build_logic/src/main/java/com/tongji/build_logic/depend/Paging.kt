package com.tongji.build_logic.depend

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("MemberVisibilityCanBePrivate", "ObjectPropertyName", "SpellCheckingInspection")
object Paging {
  // https://developer.android.com/jetpack/androidx/releases/paging?hl=zh_cn
  const val paging_version = "3.1.1"
  
  const val `paging-runtime` = "androidx.paging:paging-runtime:$paging_version"
  const val `paging-rxjava3` = "androidx.paging:paging-rxjava3:$paging_version"
  
  // alternatively - without Android dependencies for tests
  const val `paging-testing` = "androidx.paging:paging-common:$paging_version"
}

fun Project.dependPaging() {
  dependencies {
    "implementation"(Paging.`paging-runtime`)
    "implementation"(Paging.`paging-rxjava3`)
  }
}

