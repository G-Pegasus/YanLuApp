package com.tongji.build_logic.project

import com.tongji.build_logic.project.base.BaseLibraryProject
import com.tongji.build_logic.depend.*
import org.gradle.api.Project

class LibCommonProject(project: Project) : BaseLibraryProject(project) {
  override fun initProject() {
    // lib_common 默认情况下是导入所有依赖
    dependAndroidView()
    dependAndroidKtx()
    dependCoroutines()
    dependCoroutinesRx3()
    dependEventBus()
    dependGlide()
    dependLifecycleKtx()
    dependLottie()
    dependNetwork()
    dependNetworkInternal()
    dependPaging()
    dependRoom()
    dependRoomRxjava()
    dependRoomPaging()
    dependRxjava()
    dependRxPermissions()
  }
}