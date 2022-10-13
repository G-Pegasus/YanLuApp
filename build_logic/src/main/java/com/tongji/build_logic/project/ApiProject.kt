package com.tongji.build_logic.project

import com.tongji.build_logic.project.base.BaseLibraryProject
import org.gradle.api.Project

class ApiProject(project: Project) : BaseLibraryProject(project) {
  override fun initProject() {
    // api 模块不主动依赖 lib_common，应尽量做到只有接口和简单逻辑
  }
}