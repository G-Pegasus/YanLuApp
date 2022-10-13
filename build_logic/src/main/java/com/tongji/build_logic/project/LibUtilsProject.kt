package com.tongji.build_logic.project

import com.tongji.build_logic.depend.dependAndroidKtx
import com.tongji.build_logic.depend.dependAndroidView
import com.tongji.build_logic.depend.dependLifecycleKtx
import com.tongji.build_logic.depend.dependNetworkInternal
import com.tongji.build_logic.project.base.BaseLibraryProject
import org.gradle.api.Project

class LibUtilsProject(project: Project) : BaseLibraryProject(project) {
  override fun initProject() {
    // 这里面只依赖带有 internal 修饰的
    dependAndroidView()
    dependAndroidKtx()
    dependLifecycleKtx()
    dependNetworkInternal()
  }
}