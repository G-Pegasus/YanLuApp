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
dependMVVM()

dependencies {

    // PictureSelector 图片上传
    implementation("io.github.lucksiege:pictureselector:v3.10.6")
    implementation("io.github.lucksiege:ucrop:v3.10.6")
    implementation("org.xutils:xutils:3.9.0")

    // Banner
    implementation("com.github.zhpanvip:bannerviewpager:3.5.7")

}