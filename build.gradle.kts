plugins {
    id("com.android.application") version "7.2.1" apply false
    id("com.android.library") version "7.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.7.0" apply false
    id("org.jetbrains.kotlin.jvm") version "1.7.10" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}

tasks.register("cacheToLocalMaven") {
    group = "publishing"
    subprojects
        .map { it.tasks.named("cacheToLocalMaven") }
        .let { dependsOn(it) }
}

buildscript {
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        mavenCentral()
        google()
        jcenter()
        maven { url = uri("https://repo1.maven.org/maven2/") }
    }
    dependencies {
        /*
        * 可能你会好奇这里与 build-logic 中有什么区别，
        * 如果你在 build-logic 中要使用插件，需要 implementation() 才行，
        * 而如果你只是在某个模块里面使用，那直接在这里写即可，但请写好注释和对应链接！！！
        * */
        classpath(libs.android.gradlePlugin)
        classpath(libs.kotlin.gradlePlugin)
        classpath("com.github.whataa:pandora-plugin:1.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
        classpath("com.android.tools.build:gradle:3.4.0")
    }
}