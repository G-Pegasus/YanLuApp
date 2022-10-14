package com.tongji.build_logic.depend

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * @author: Kana (Tongji)
 * @date: 2022/10/14 16:27
 * @description:
 * @email: tongji0x208@gmail.com
 */

@Suppress("MemberVisibilityCanBePrivate", "ObjectPropertyName", "SpellCheckingInspection")
object BaseMVVM {
    // https://github.com/hegaojian/JetpackMvvm
    // 这个基础框架用的确实不错，后面有时间自己仿写一个
    const val mvvm_version = "1.2.7"

    const val mvvm = "com.github.hegaojian:JetpackMvvm:$mvvm_version"
}

fun Project.dependMVVM() {
    dependencies {
        "implementation"(BaseMVVM.mvvm)
    }
}