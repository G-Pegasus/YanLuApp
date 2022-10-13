package com.tongji.build_logic.depend

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("MemberVisibilityCanBePrivate", "ObjectPropertyName", "SpellCheckingInspection")
object EventBus {
  // https://github.com/greenrobot/EventBus
  const val eventBus = "org.greenrobot:eventbus:3.3.1"
}

fun Project.dependEventBus() {
  dependencies {
    "implementation"(EventBus.eventBus)
  }
}