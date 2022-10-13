package com.tongji.build_logic.depend.api

import org.gradle.api.Project

import com.tongji.build_logic.depend.api.utils.ApiDependUtils

object ApiDepend {
  
  /*
  * 这里写 api 模块以及该 api 模块的实现模块
  * 写法如下：
  * val test = ":module_test:api_test" by parent and ":module_xxx"
  *                                    ↑    ↑     ↑        ↑
  * by:     通过 by 来连接实现模块的 path
  * parent: 如果父模块是实现模块，则使用该方式可直接添加
  * and:    用于连接多个实现模块，比如后面写的 module_xxx，就是 api_test 的另一个实现模块
  * */
  
  val account = ":lib_account:api_account" by parent
  val main = ":module_main:api_main" by parent
  val course = ":module_course:api_course" by parent
  
  private infix fun String.by(implPath: String): ApiDependUtils.IApiDependUtils = by { implPath }
  private infix fun String.by(implPath: String.() -> String): ApiDependUtils.IApiDependUtils {
    return ApiDependUtils(this)
      .by(implPath.invoke(this))
  }
  
  private val parent: String.() -> String
    get() = { substringBeforeLast(":") }
}

fun Project.dependApiAccount() {
  ApiDepend.account.dependApiOnly(this)
}

fun Project.dependApiMain() {
  ApiDepend.main.dependApiOnly(this)
}

fun Project.dependApiCourse(){
  ApiDepend.course.dependApiOnly(this)
}