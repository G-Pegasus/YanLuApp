package com.tongji.yanluapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tongji.yanluapp.R
import com.tongji.yanluapp.app.data.SchoolData.addList
import com.tongji.yanluapp.databinding.FragmentInfoBinding
import com.tongji.yanluapp.ui.activity.SchoolInfoActivity
import com.tongji.yanluapp.ui.activity.SearchResultActivity
import com.tongji.yanluapp.ui.adapter.SchoolAdapter
import com.tongji.yanluapp.viewmodel.InfoViewModel
import me.hgj.jetpackmvvm.base.appContext
import com.tongji.lib_base.ui.BaseFragment1

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/14 16:25
 * @description:
 * @email: tongji0x208@gmail.com
 */
class InfoFragment : BaseFragment1<InfoViewModel, FragmentInfoBinding>() {

    private lateinit var schoolAdapter: SchoolAdapter
    private val viewModel by lazy { ViewModelProvider(this)[InfoViewModel::class.java] }

    override fun initView(savedInstanceState: Bundle?) {

        viewModel.listData.addAll(addList())

        val rvSchool = mViewBind.rvSchool
        rvSchool.layoutManager = LinearLayoutManager(appContext)
        schoolAdapter = SchoolAdapter(requireContext(), addList())
        rvSchool.adapter = schoolAdapter
        schoolAdapter.setOnItemClickListener(object : SchoolAdapter.OnItemClickListener {

            override fun onItemClick(view: View, position: Int) {
                val intent = Intent(context, SchoolInfoActivity::class.java)
                val bundle = Bundle()
                val schoolWeb = addList()[position].schoolInfo
                bundle.putString("data", schoolWeb)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onItemLongClick(view: View, position: Int) {
                val intent = Intent(context, SchoolInfoActivity::class.java)
                val bundle = Bundle()
                val schoolWeb = addList()[position].schoolWeb
                bundle.putString("data", schoolWeb)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        mActivity.title = "????????????"
        val searchView = menu.findItem(R.id.action_search)?.actionView as SearchView
        searchView.run {
            maxWidth = Integer.MAX_VALUE
            queryHint = "????????????"

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                //searchview?????????
                override fun onQueryTextSubmit(query: String?): Boolean {
                    //?????????????????? ???????????????????????????????????????????????????
                    query?.let { queryStr ->
                        val intent = Intent(context, SearchResultActivity::class.java)
                        intent.putExtra("searchKey", queryStr)
                        startActivity(intent)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })

            isSubmitButtonEnabled = true //??????????????????????????????
            val field = javaClass.getDeclaredField("mGoButton")
            field.run {
                isAccessible = true
                val mGoButton = get(searchView) as ImageView
                mGoButton.setImageResource(R.mipmap.ic_search)
            }
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onResume() {
        super.onResume()
        //??????Fragment????????????????????????????????????Menu???????????????WebFragment ActionBar?????????????????????????????????ActionBar????????????bug
        setMenu()
    }

    private fun setMenu() {
        setHasOptionsMenu(true)
        mViewBind.includeToolbar.toolbar.run {
            //??????menu ????????????
            mActivity.setSupportActionBar(this)
            mActivity.title = "????????????"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mActivity.setSupportActionBar(null)
    }

    override fun dismissLoading() {

    }

    override fun showLoading(message: String) {

    }
}