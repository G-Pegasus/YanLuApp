@file:Suppress("UnstableApiUsage")

package com.tongji.build_logic.project.base

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.kotlin.dsl.apply
import com.tongji.build_logic.project.base.base.BaseAndroidProject
import com.tongji.build_logic.config.Config
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

abstract class BaseApplicationProject(project: Project) : BaseAndroidProject(project) {
  
  override fun initProjectInternal() {
    initApplication()
    super.initProjectInternal()
  }
  
  protected open fun initApplication() {
    apply(plugin = "com.android.application")
    apply(plugin = "kotlin-android")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "kotlin-parcelize")
    
    extensions.configure<BaseAppModuleExtension> {
      initAndroid(this)
    }
  }
  
  // 配置 android 闭包
  protected open fun initAndroid(extension: BaseAppModuleExtension) {
    extension.run {
      uniformConfigAndroid()
      defaultConfig {
        applicationId = Config.getApplicationId(project)
        versionCode = Config.versionCode
        versionName = Config.versionName
        targetSdk = Config.targetSdk
      }
    
      buildFeatures {
        dataBinding = true
        viewBinding = true
      }
    
      buildTypes {
        release {
          isShrinkResources = false
        }
      }
    }
  }
}