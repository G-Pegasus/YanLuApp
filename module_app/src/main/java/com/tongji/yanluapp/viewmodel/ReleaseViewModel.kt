package com.tongji.yanluapp.viewmodel

import com.luck.picture.lib.entity.LocalMedia
import com.tongji.yanluapp.app.network.apiService1
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.request
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * @author: Kana (Tongji)
 * @date: 2022/10/1 14:46
 * @description:
 * @email: tongji0x208@gmail.com
 */
class ReleaseViewModel : BaseViewModel() {

    var list = ArrayList<MultipartBody.Part>()

    // 上传单张图片
    fun uploadImages(content: String, files: List<LocalMedia>) {
        var requestFile: RequestBody

        for (i in files.indices) {
            val imageFile = File(files[i].realPath)
            val builder = MultipartBody.Builder().setType(MultipartBody.FORM) // 表单类型
            requestFile = RequestBody.create(MediaType.parse("image/*"), imageFile)
            builder.addFormDataPart("file${i}", imageFile.name, requestFile)
            list.add(builder.build().part(0))
        }

        request(
            { apiService1.releasePost(content, list) } // 请求体
            , {},{}, // 请求的返回结果
            false, ""
        )
    }
}