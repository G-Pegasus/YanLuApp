@file:Suppress("ObjectPropertyName")

package com.tongji.build_logic.config

import org.gradle.api.Project

object Config {
    const val minSdk = 26
    const val targetSdk = 32
    const val compileSdk = 32

    const val versionCode = 1
    const val versionName = "1.1.0"

    fun getApplicationId(project: Project): String {
        return when (project.name) {
            "module_app" -> "com.tongji.yanluapp"
            else -> "com.tongji.yanluapp.${project.name}"
        }
    }
}
