package com.tongji.build_logic.depend.lib

import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

object LibDepend {
  /*
  * 注意事项：
  * 1、别忘了前面要打引号
  * 2、建议按顺序添加
  * 3、一般情况下只有共用的才会添加，比如像 lib_account 这种，只需要添加它的 api 模块就够了，
  *   没必要添加它的 lib 模块，因为没有其他模块会使用
  * */

  const val base = ":lib_base"
  const val config = ":lib_config"
  const val debug = ":lib_debug"
  const val utils = ":lib_utils"
  const val course = ":module_course:lib_course"
}
