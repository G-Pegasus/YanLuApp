package com.tongji.build_logic.project

import com.tongji.build_logic.depend.dependAndroidKtx
import com.tongji.build_logic.depend.dependAndroidView
import com.tongji.build_logic.depend.dependLifecycleKtx
import com.tongji.build_logic.project.base.BaseLibraryProject
import org.gradle.api.Project

class ModuleProject(project: Project) : BaseLibraryProject(project) {
  
  override fun initProject() {
    checkIsInDebug()
    dependAndroidView()
    dependAndroidKtx()
    dependLifecycleKtx()
  }
  
  /**
   * 检查该模块是否处于 debug 状态
   */
  private fun checkIsInDebug() {
    if (plugins.hasPlugin("com.android.application")) {
      throw RuntimeException("取消单模块调试才能使用多模块插件！")
    }
  }
}