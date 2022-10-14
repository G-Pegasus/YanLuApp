package com.tongji.build_logic.depend.lib

import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

object LibDepend {
  const val base = ":lib_base"
}

fun Project.dependLibBase() {
  dependencies {
    "implementation"(project(LibDepend.base))
  }
}