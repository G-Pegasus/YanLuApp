package com.tongji.build_logic.project

import com.tongji.build_logic.depend.dependAndroidKtx
import com.tongji.build_logic.depend.dependAndroidView
import com.tongji.build_logic.depend.dependLifecycleKtx
import com.tongji.build_logic.project.base.BaseLibraryProject
import org.gradle.api.Project

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email guo985892345@foxmail.com
 * @date 2022/8/1 13:07
 */
class LibBaseProject(project: Project) : BaseLibraryProject(project) {
  override fun initProject() {
    // 这里面只依赖带有 internal 修饰的
    dependAndroidView()
    dependAndroidKtx()
    dependLifecycleKtx()
  }
}