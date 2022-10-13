package com.tongji.yanluapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.tongji.yanluapp.R
import com.tongji.yanluapp.app.App.Companion.context
import com.tongji.yanluapp.app.data.SchoolData.addList
import com.tongji.yanluapp.app.utils.initClose
import com.tongji.yanluapp.bean.School
import com.tongji.yanluapp.databinding.ActivitySearchResultBinding
import com.tongji.yanluapp.ui.adapter.SchoolAdapter
import com.tongji.yanluapp.viewmodel.SearchResultViewModel
import me.hgj.jetpackmvvm.base.appContext
import com.tongji.yanluapp.app.base.BaseActivity1

class SearchResultActivity : BaseActivity1<SearchResultViewModel, ActivitySearchResultBinding>() {

    private lateinit var schoolAdapter: SchoolAdapter

    override fun initView(savedInstanceState: Bundle?) {
        val searchKey = intent.getStringExtra("searchKey")

        if (searchKey != null) {
            mViewBind.toolbar.initClose(searchKey) {
                it.setBackgroundColor(R.color.colorPrimary)
                finish()
            }
        }

        val schoolSearchList = mutableListOf<School>()
        if (searchKey != null) {
            addList().forEach {
                if (it.name.contains(searchKey) || it.type.contains(searchKey)) {
                    schoolSearchList.add(it)
                }
            }
        }

        val rvSchool = mViewBind.rvSearchSchool
        rvSchool.layoutManager = LinearLayoutManager(appContext)
        schoolAdapter = SchoolAdapter(this, schoolSearchList)
        rvSchool.adapter = schoolAdapter
        schoolAdapter.setOnItemClickListener(object : SchoolAdapter.OnItemClickListener {

            override fun onItemClick(view: View, position: Int) {
                val intent = Intent(appContext, SchoolInfoActivity::class.java)
                val bundle = Bundle()
                val schoolWeb = schoolSearchList[position].schoolInfo
                bundle.putString("data", schoolWeb)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onItemLongClick(view: View, position: Int) {
                val intent = Intent(context, SchoolInfoActivity::class.java)
                val bundle = Bundle()
                val schoolWeb = schoolSearchList[position].schoolWeb
                bundle.putString("data", schoolWeb)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })
    }

    override fun dismissLoading() {

    }

    override fun showLoading(message: String) {

    }
}