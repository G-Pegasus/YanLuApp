import com.tongji.build_logic.depend.*

plugins {
    id("module-manager")
}

dependRoom()
dependNavigation()
dependGlide()
dependNetwork()
dependSmartRefreshLayout()
dependPhotoView()
dependMMKV()

dependencies {

    // MVVM基本框架
    implementation("com.github.hegaojian:JetpackMvvm:1.2.7")

    // PictureSelector 图片上传
    implementation("io.github.lucksiege:pictureselector:v3.10.6")
    implementation("io.github.lucksiege:ucrop:v3.10.6")
    implementation("org.xutils:xutils:3.9.0")

    // Banner
    implementation("com.github.zhpanvip:bannerviewpager:3.5.7")

}