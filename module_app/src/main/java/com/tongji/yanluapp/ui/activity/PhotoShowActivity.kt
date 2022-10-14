package com.tongji.yanluapp.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.tongji.yanluapp.R
import com.tongji.yanluapp.utils.showToast
import com.tongji.yanluapp.ui.adapter.PhotoShowAdapter

class PhotoShowActivity : AppCompatActivity() {

    private lateinit var photoAdapter: PhotoShowAdapter
    private lateinit var mViewPager: ViewPager2
    private lateinit var mCurrentPosition: TextView
    private lateinit var mAllPhotos: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_show)
        initData()

        val urlList = intent.getStringArrayListExtra("data")

        if (urlList != null) {
            photoAdapter = PhotoShowAdapter(urlList)
            mViewPager.adapter = photoAdapter

            // 监听滑到第几张图片
            mViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                @SuppressLint("SetTextI18n")
                override fun onPageSelected(position: Int) {
                    mViewPager.currentItem = position
                    mCurrentPosition.text = (mViewPager.currentItem + 1).toString()
                }
            })
            photoAdapter.setOnItemClickListener(object : PhotoShowAdapter.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    finish()
                }

                override fun onItemLongClick(view: View, position: Int) {

                }
            })
            mAllPhotos.text = urlList.size.toString()
        } else {
            this.showToast("图片加载失败了")
        }
    }

    private fun initData() {
        mViewPager = findViewById(R.id.show_photo)
        mCurrentPosition = findViewById(R.id.current_item)
        mAllPhotos = findViewById(R.id.all_photo)
    }
}