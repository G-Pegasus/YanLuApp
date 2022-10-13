package com.tongji.build_logic.depend.api.utils

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class ApiDependUtils(val apiProjectPath: String) {
  
  fun by(path: String): IApiDependUtils {
    list.add(path)
    return apiImplDependUtils
  }
  
  private val list = mutableListOf<String>()
  private val apiImplDependUtils = ApiImplDependUtils()
  
  init {
    _ApiWithImplMap[apiProjectPath] = apiImplDependUtils
  }
  
  private inner class ApiImplDependUtils: IApiDependUtils {
    
    val apiProjectPath: String
      get() = this@ApiDependUtils.apiProjectPath
    
    infix fun and(path: String): IApiDependUtils {
      list.add(path)
      return this
    }
  
    override fun add(path: String): IApiDependUtils {
      list.add(path)
      return this
    }
  
    override fun add(path: (String) -> String): IApiDependUtils {
      list.add(path.invoke(apiProjectPath))
      return this
    }
  
    override fun getImplPaths(): List<String> {
      return list
    }
  
    override fun dependApiOnly(project: Project) {
      if (list.isEmpty()) {
        throw RuntimeException("api 模块 $apiProjectPath 没得实现模块，你正确书写了吗？")
      }
      project.run {
        dependencies {
          "implementation"(project(apiProjectPath))
        }
      }
    }
  
    override fun dependApiImplOnly(project: Project, filter: (String) -> Boolean): List<String> {
      val dependList = mutableListOf<String>()
      project.run {
        dependencies {
          getImplPaths().forEach {
            if (it.isNotBlank() && filter.invoke(it)) {
              "runtimeOnly"(project(it)) // 使用 runtimeOnly 保证编译时不依赖
              dependList.add(it)
            }
          }
        }
      }
      return dependList
    }
  }
  
  companion object {
    private val _ApiWithImplMap = HashMap<String, IApiDependUtils>()
    
    /**
     * api 模块的 path 与 实现模块的路径
     */
    val sApiWithImplMap: Map<String, IApiDependUtils>
      get() = _ApiWithImplMap
  }
  
  interface IApiDependUtils {
    infix fun add(path: String): IApiDependUtils
    infix fun add(path: (String) -> String): IApiDependUtils
    fun getImplPaths(): List<String>
    fun dependApiOnly(project: Project)
    fun dependApiImplOnly(project: Project, filter: (String) -> Boolean = { true }): List<String>
  }
}