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

    implementation("com.haibin:calendarview:3.7.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

    // 图片展示框架
    implementation("com.github.SherlockGougou:BigImageViewPager:androidx-7.3.0")

    // 友盟（移动统计、分享）
    implementation("com.umeng.umsdk:common:9.4.7")  // 必选
    implementation("com.umeng.umsdk:asms:1.4.0")  // 必选

    // 分享相关库
    //implementation("com.umeng.umsdk:share-core:7.1.6") // 分享核心库，必选
    //implementation("com.umeng.umsdk:share-board:7.1.6") // 分享面板功能，可选
    //implementation("com.umeng.umsdk:link:1.1.0") // 集成智能超链U-Link，可选，如要统计分享回流次数和分享新增用户指标则必选

    // 第三方平台相关库，可根据需要自行选择
    //implementation("com.umeng.umsdk:share-wx:7.1.6") // 微信完整版
    //implementation("com.tencent.mm.opensdk:wechat-sdk-android-without-mta:6.7.9") // 微信官方依赖库，必选

    //implementation("com.umeng.umsdk:share-qq:7.1.6") // QQ完整版
    //implementation("com.tencent.tauth:qqopensdk:3.53.0") // QQ官方依赖库，必选
}