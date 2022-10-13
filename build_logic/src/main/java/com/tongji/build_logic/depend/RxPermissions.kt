package com.tongji.build_logic.depend

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("MemberVisibilityCanBePrivate", "ObjectPropertyName", "SpellCheckingInspection")
object RxPermissions {
  // https://github.com/tbruyelle/RxPermissions
  const val rxpermissions = "com.github.tbruyelle:rxpermissions:0.12"
}

fun Project.dependRxPermissions() {
  dependencies {
    "implementation"(RxPermissions.rxpermissions)
  }
}