package com.tongji.build_logic.depend

import com.tongji.build_logic.depend.utils.libsVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

/*
* 文档地址：
* https://developer.android.com/codelabs/android-hilt#2 注意：别看中文文档，中文文档版本号没有英文文档高
*
* 因为 hilt 需要在目录下的 build.gradle 中使用到 gradle 插件，为了统一版本号，只能这样写，
* 其中版本号统一写在 build-logic 的 settings.gradle.kts 中
* */
@Suppress("RemoveRedundantBackticks")
val Project.`hilt_version`: String
  get() = libsVersion("hilt.gradle.version").requiredVersion

fun Project.dependHilt() {
  apply(plugin = "dagger.hilt.android.plugin")
  dependencies {
    "implementation"("com.google.dagger:hilt-android:${hilt_version}")
    "kapt"("com.google.dagger:hilt-android-compiler:${hilt_version}")
  }
}