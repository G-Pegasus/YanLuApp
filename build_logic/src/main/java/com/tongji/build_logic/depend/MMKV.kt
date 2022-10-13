package com.tongji.build_logic.depend

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * @author: Kana (Tongji)
 * @date: 2022/10/13 21:45
 * @description:
 * @email: tongji0x208@gmail.com
 */

@Suppress("MemberVisibilityCanBePrivate", "ObjectPropertyName", "SpellCheckingInspection")
object MMKV {
    // https://github.com/Tencent/MMKV
    const val mmkv_version = "1.2.14"

    const val mmkv = "com.tencent:mmkv:$mmkv_version"
}

fun Project.dependMMKV() {
    dependencies {
        "implementation"(MMKV.mmkv)
    }
}