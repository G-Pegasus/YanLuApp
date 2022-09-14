package com.tongji.yanluapp.app.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tongji.yanluapp.ui.fragment.HomeFragment
import com.tongji.yanluapp.ui.fragment.InfoFragment
import com.tongji.yanluapp.ui.fragment.MeFragment
import com.tongji.yanluapp.ui.fragment.SubjectFragment

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

// 初始化底部导航
fun BottomNavigationView.init(navigationItemSelectedAction: (Int) -> Unit) : BottomNavigationView {
    setOnNavigationItemSelectedListener {
        navigationItemSelectedAction.invoke(it.itemId)
        true
    }
    return this
}