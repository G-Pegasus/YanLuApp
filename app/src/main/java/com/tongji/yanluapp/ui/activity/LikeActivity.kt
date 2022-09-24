package com.tongji.yanluapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tongji.yanluapp.app.App
import com.tongji.yanluapp.bean.School
import com.tongji.yanluapp.databinding.ActivityLikeBinding
import com.tongji.yanluapp.ui.adapter.SchoolAdapter
import com.tongji.yanluapp.viewmodel.LikeSchoolViewModel
import me.hgj.jetpackmvvm.base.appContext
import me.hgj.jetpackmvvm.demo.app.base.BaseActivity1

class LikeActivity : BaseActivity1<LikeSchoolViewModel, ActivityLikeBinding>() {

    private lateinit var schoolAdapter: SchoolAdapter
    private val viewModel by lazy { ViewModelProvider(this)[LikeSchoolViewModel::class.java] }

    override fun initView(savedInstanceState: Bundle?) {
        val rvLike = mViewBind.rvLikeSchool

        viewModel.likeLiveData.observe(this) {
            rvLike.layoutManager = LinearLayoutManager(appContext)
            schoolAdapter = SchoolAdapter(this, it as MutableList<School>)
            rvLike.adapter = schoolAdapter
            schoolAdapter.setOnItemClickListener(object : SchoolAdapter.OnItemClickListener {

                override fun onItemClick(view: View, position: Int) {
                    val intent = Intent(appContext, SchoolInfoActivity::class.java)
                    val bundle = Bundle()
                    val schoolWeb = it[position].schoolInfo
                    bundle.putString("data", schoolWeb)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }

                override fun onItemLongClick(view: View, position: Int) {
                    val intent = Intent(App.context, SchoolInfoActivity::class.java)
                    val bundle = Bundle()
                    val schoolWeb = it[position].schoolWeb
                    bundle.putString("data", schoolWeb)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
            })
        }
    }

    override fun dismissLoading() {

    }

    override fun showLoading(message: String) {

    }
}