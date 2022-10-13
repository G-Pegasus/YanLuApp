plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

/*
* 注意：
* 1、classpath 在 build-logic 中要这样才能导入
* 2、如果出现了与 hilt 一样的情况，需要统一版本号，建议写在 gradle/libs.versions.toml 中，然后在 kt 文件中使用
*    Project.libsVersion() 这个高阶扩展来导入（可以参考 depend/Hilt.kt 中的写法）
* */
dependencies {
    implementation("com.android.tools.build:gradle:7.2.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
}

gradlePlugin {
    plugins {
        create("module-debug") {
            id = "module-debug"
            implementationClass = "ModuleDebugPlugin"
        }

        create("module-manager") {
            id = "module-manager"
            implementationClass = "ModuleManagerPlugin"
        }
    }
}