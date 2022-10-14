@file:Suppress("UnstableApiUsage")

package com.tongji.build_logic.project.base

import com.android.build.gradle.LibraryExtension
import com.tongji.build_logic.config.Config
import org.gradle.kotlin.dsl.apply
import com.tongji.build_logic.project.base.base.BaseAndroidProject
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

abstract class BaseLibraryProject(project: Project) : BaseAndroidProject(project) {
  
  override fun initProjectInternal() {
    initLibrary()
    super.initProjectInternal()
  }
  
  protected open fun initLibrary() {
    apply(plugin = "com.android.library")
    apply(plugin = "kotlin-android")
    apply(plugin = "kotlin-kapt")
    
    extensions.configure<LibraryExtension> {
      initAndroid(this)
    }
  }
  
  // 配置 android 闭包
  protected open fun initAndroid(extension: LibraryExtension) {
    extension.run {
      uniformConfigAndroid()
      defaultConfig {
        targetSdk = Config.targetSdk
      
        // 自己模块的混淆文件
        consumerProguardFiles.add(projectDir.resolve("consumer-rules.pro"))
      }
    
      buildFeatures {
        dataBinding = true
        viewBinding = true
      }
    }
  }
}