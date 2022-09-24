package com.tongji.yanluapp.app.utils

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tongji.yanluapp.R
import com.tongji.yanluapp.ui.fragment.HomeFragment
import com.tongji.yanluapp.ui.fragment.InfoFragment
import com.tongji.yanluapp.ui.fragment.MeFragment
import com.tongji.yanluapp.ui.fragment.SubjectFragment
import me.hgj.jetpackmvvm.base.appContext
import me.hgj.jetpackmvvm.ext.util.toHtml

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/14 16:39
 * @description:
 * @email: tongji0x208@gmail.com
 */

// 初始化主页面 ViewPager，设置 adapter
fun ViewPager2.initMain(activity: AppCompatActivity): ViewPager2 {
    //是否可滑动
    this.isUserInputEnabled = false
    this.offscreenPageLimit = 4
    //设置适配器
    adapter = object : FragmentStateAdapter(activity) {

        override fun getItemCount(): Int = 4

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> HomeFragment()
                1 -> SubjectFragment()
                2 -> InfoFragment()
                3 -> MeFragment()
                else -> HomeFragment()
            }
        }
    }
    return this
}

// 初始化普通的toolbar 只设置标题
fun Toolbar.init(titleStr: String = ""): Toolbar {
    setBackgroundColor(R.color.colorPrimary)
    title = titleStr
    return this
}


// 初始 ToolBar 返回键
fun Toolbar.initClose(
    titleStr: String = "",
    backImg: Int = R.mipmap.ic_back,
    onBack: (toolbar: Toolbar) -> Unit
): Toolbar {
    title = titleStr.toHtml()
    setNavigationIcon(backImg)
    setNavigationOnClickListener { onBack.invoke(this) }
    return this
}

// 初始化底部导航
fun BottomNavigationView.init(navigationItemSelectedAction: (Int) -> Unit) : BottomNavigationView {
    setOnNavigationItemSelectedListener {
        navigationItemSelectedAction.invoke(it.itemId)
        true
    }
    return this
}