@file:Suppress("UnstableApiUsage")

include(":lib_common")


include(":lib_base")


pluginManagement {
    includeBuild("build_logic")
    repositories {
        maven("https://maven.aliyun.com/repository/public")
        maven("https://maven.aliyun.com/repository/google")
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven("$rootDir/build/maven") // 本地模块缓存文件夹
        maven("https://maven.aliyun.com/repository/public")
        maven("https://maven.aliyun.com/repository/google")
        maven("https://jitpack.io")
        // mavenCentral 快照仓库
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
        google()
        mavenCentral()
    }
}
rootProject.name = "YanLuApp"

// 测试使用，排除掉不需要的模块
val excludeList = listOf<String>(

)

//对文件夹进行遍历，深度为2
rootDir.walk()
    .maxDepth(2)
    .asSequence()
    .filter {
        //过滤掉干扰文件夹
        val isDirectory = it.isDirectory
        val isSubModule = file("$it/build.gradle").exists() || file("$it/build.gradle.kts").exists()
        val isIndependentProject = file("$it/settings.gradle").exists() || file("$it/settings.gradle.kts").exists()
        isDirectory && isSubModule && !isIndependentProject
    }
    .filter {
        //对module进行过滤
        "(api_.+)|(module_.+)|(lib_.+)".toRegex().matches(it.name)
                && it.name !in excludeList
                && it.parentFile.name !in excludeList // 如果只是写了忽略掉 module_test，那么里面的 api_test 也得一起忽略掉才能彻底忽略
    }
    .map {
        //将file映射到相对路径
        val parentFile = it.parentFile
        if (parentFile.path == rootDir.path) {
            ":${it.name}"
        } else {
            ":${parentFile.name}:${it.name}"
        }
    }
    .forEach {
        //进行include
        include(it)
    }