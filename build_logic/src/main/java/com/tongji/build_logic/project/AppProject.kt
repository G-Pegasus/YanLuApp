@file:Suppress("UnstableApiUsage")

package com.tongji.build_logic.project

import com.tongji.build_logic.depend.dependAndroidKtx
import com.tongji.build_logic.depend.dependAndroidView
import com.tongji.build_logic.depend.dependLifecycleKtx
import com.tongji.build_logic.project.base.BaseApplicationProject
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*

class AppProject(project: Project) : BaseApplicationProject(project) {
  override fun initProject() {
    dependAllProject()
    // 这里面只依赖带有 internal 修饰的
  }
  
  /**
   * 引入第一层目录下所有的 module 和 lib 模块
   */
  private fun dependAllProject() {
    
    // 测试使用，设置 module_app 暂时不依赖的模块
    val excludeList = mutableListOf<String>(
    )
    
    // 根 gradle 中包含的所有子模块
    val includeProjects = rootProject.allprojects.map { it.name }

    dependAndroidView()
    dependAndroidKtx()
    dependLifecycleKtx()
    dependencies {
      //引入所有的module和lib模块
      rootDir.listFiles()!!.filter {
        // 1.是文件夹
        // 2.不是module_app
        // 3.以lib_或者module_开头
        // 4.去掉暂时排除的模块
        // 5.根 gradle 导入了的模块
        it.isDirectory
          && it.name != "module_app"
          && "(lib_.+)|(module_.+)".toRegex().matches(it.name)
          && it.name !in excludeList
          && includeProjects.contains(it.name)
      }.forEach {
        "implementation"(project(":${it.name}"))
      }
    }
  }
}