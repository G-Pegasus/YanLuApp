package com.tongji.build_logic.project

import com.tongji.build_logic.project.base.BaseLibraryProject
import org.gradle.api.Project

class LibProject(project: Project) : BaseLibraryProject(project) {
  override fun initProject() {
  }
  
  /**
   * 不需要依赖自己模块下的子模块时在这里写上，但只提供测试使用，请测试后取消
   */
  override fun isDependChildModule(): Boolean {
//    if (name == "lib_account") {
//      println(name)
//      return false
//    }
    return super.isDependChildModule()
  }
}