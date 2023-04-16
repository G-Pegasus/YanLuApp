package com.tongji.yanluapp.ui.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarLayout
import com.haibin.calendarview.CalendarView
import com.haibin.calendarview.CalendarView.OnCalendarSelectListener
import com.tongji.lib_base.ui.BaseFragment1
import com.tongji.yanluapp.R
import com.tongji.yanluapp.databinding.FragmentSubjectBinding
import com.tongji.yanluapp.ui.adapter.TodoAdapter
import com.tongji.yanluapp.ui.fragment.dialog.TodoDialog
import com.tongji.yanluapp.viewmodel.SubjectViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/14 16:28
 * @description:
 * @email: tongji0x208@gmail.com
 */
class SubjectFragment : BaseFragment1<SubjectViewModel, FragmentSubjectBinding>() {

    private var mYear: Int = 0
    private lateinit var mTime: String
    private lateinit var mCalendarLayout: CalendarLayout
    private lateinit var mTextCurrentDay: TextView
    private lateinit var mCalendarView: CalendarView
    private lateinit var mRelativeTool: RelativeLayout
    private lateinit var mTextLunar: TextView
    private lateinit var mTextYear: TextView
    private lateinit var mNoneTv: TextView
    private lateinit var mTextMonthDay: TextView
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAddTodoBtn: ImageButton
    private lateinit var dialog: TodoDialog

    override fun initView(savedInstanceState: Bundle?) {
        dialog = TodoDialog()
        requireView().run {
            mTextMonthDay = findViewById(R.id.tv_month_day)
            mTextYear = findViewById(R.id.tv_year)
            mTextLunar = findViewById(R.id.tv_lunar)
            mNoneTv = findViewById(R.id.tv_none_todo)

            mRelativeTool = findViewById(R.id.rl_tool)
            mCalendarView = findViewById(R.id.calendarView)

            mCalendarLayout = findViewById(R.id.calendarLayout)
            //mCalendarView.setRange(2018, 7, 1, 2019, 4, 28);
            mTextCurrentDay = findViewById(R.id.tv_current_day)

            mRecyclerView = findViewById(R.id.recyclerView)
            mAddTodoBtn = findViewById(R.id.btn_todo_add)
        }
        mTextMonthDay.setOnClickListener {
            if (!mCalendarLayout.isExpand) {
                mCalendarLayout.expand()
                return@setOnClickListener
            }
            mCalendarView.showYearSelectLayout(mYear)
            mTextLunar.visibility = View.GONE
            mTextYear.visibility = View.GONE
            mTextMonthDay.text = mYear.toString()
        }
        mYear = mCalendarView.curYear
        mCalendarView.run {
            setOnYearChangeListener {
                mTextMonthDay.text = it.toString()
            }
            setOnMonthChangeListener { _, _ ->
                val calendar = mCalendarView.selectedCalendar
                mTextLunar.visibility = View.VISIBLE
                mTextYear.visibility = View.VISIBLE
                val msg = calendar.month.toString() + "月" + calendar.day + "日"
                mTextMonthDay.text = msg
                mTextYear.text = calendar.year.toString()
                mTextLunar.text = calendar.lunar
                mYear = calendar.year
            }
            setOnCalendarSelectListener(object : OnCalendarSelectListener {
                override fun onCalendarOutOfRange(calendar: Calendar?) {

                }

                override fun onCalendarSelect(calendar: Calendar, isClick: Boolean) {
                    //Log.e("onDateSelected", "  -- " + calendar.getYear() + "  --  " + calendar.getMonth() + "  -- " + calendar.getDay());
                    mTextLunar.visibility = View.VISIBLE
                    mTextYear.visibility = View.VISIBLE
                    val msg = calendar.month.toString() + "月" + calendar.day + "日"
                    mTime = mYear.toString() + "." + calendar.month.toString() + "." + calendar.day
                    mTextMonthDay.text = msg
                    mTextYear.text = calendar.year.toString()
                    mTextLunar.text = calendar.lunar
                    mYear = calendar.year
                    mViewModel.getTodoByTime("$mYear.${calendar.month}.${calendar.day}")
                }
            })
        }
        mTextYear.text = mCalendarView.curYear.toString()
        val month = mCalendarView.curMonth.toString() + "月" + mCalendarView.curDay + "日"
        mTextMonthDay.text = month
        mTextLunar.text = "今日"
        mTextCurrentDay.text = mCalendarView.curDay.toString()

        mTime = mYear.toString() + "." + mCalendarView.curMonth.toString() + "." + mCalendarView.curDay

        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val todoAdapter = TodoAdapter()
        mRecyclerView.adapter = todoAdapter

        mViewModel.todoLiveData.observe(viewLifecycleOwner) {
            if (it!!.isNotEmpty()) {
                mNoneTv.visibility=View.GONE
            }else mNoneTv.visibility=View.VISIBLE
            todoAdapter.submitList(it)
        }
        mViewModel.errorLiveData.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "操作失败", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "操作成功", Toast.LENGTH_SHORT).show()
                mViewModel.getTodoByTime(mTime)
            }
        }
        todoAdapter.setOnClickedListener {
            dialog.setData(it)
            dialog.show(parentFragmentManager, "")
        }
        todoAdapter.setLongClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("你确定要删除吗？")
                .setPositiveButton(
                    "确定"
                ) { _, _ ->
                    mViewModel.deleteTodo(it.todo_id)
                }
                .setNegativeButton("取消") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
        mAddTodoBtn.setOnClickListener {
            dialog.show(parentFragmentManager, "")
        }
        dialog.setUpdateListener { id, title, detail, time ->
            mViewModel.updateTodo(id, title, detail, time)
        }
        dialog.setAddTodoListener { title, detail, time ->
            mViewModel.addTodo(title, time, detail)
        }
        mViewModel.getTodoByTime("$mYear.${mCalendarView.curMonth}.${mCalendarView.curDay}")

    }

    override fun dismissLoading() {

    }

    override fun showLoading(message: String) {

    }

}