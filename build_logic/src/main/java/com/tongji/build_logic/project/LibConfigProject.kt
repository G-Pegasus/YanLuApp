package com.tongji.build_logic.project

import com.tongji.build_logic.project.base.BaseLibraryProject
import org.gradle.api.Project

class LibConfigProject(project: Project) : BaseLibraryProject(project) {
  override fun initProject() {
    // 这里面只依赖带有 internal 修饰的
  }
}