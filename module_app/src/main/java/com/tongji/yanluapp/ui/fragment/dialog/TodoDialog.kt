package com.tongji.yanluapp.ui.fragment.dialog

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.tongji.lib_common.bean.TodoResponse
import com.tongji.yanluapp.R
import java.util.*

/**
 * ...
 * @author RQ527 (Ran Sixiang)
 * @email 1799796122@qq.com
 * @date 2023/3/14
 * @Description:
 */
@SuppressLint("InflateParams")
class TodoDialog : DialogFragment() {

    private lateinit var titleEt: EditText
    private lateinit var timeEt: TextView
    private lateinit var detailEt: EditText
    private lateinit var cancelBtn: Button
    private lateinit var ensureBtn: Button
    private var data: TodoResponse? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.popuwindow_todo, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.run {
            titleEt = findViewById(R.id.et_todo_title)
            timeEt = findViewById(R.id.et_todo_time)
            detailEt = findViewById(R.id.et_todo_detail)
            cancelBtn = findViewById(R.id.btn_todo_cancel)
            ensureBtn = findViewById(R.id.btn_todo_ensure)
        }
        cancelBtn.setOnClickListener {
            dismiss()
        }
        ensureBtn.setOnClickListener {
            if (data != null) {
                data!!.run {
                    updateTodoListener?.invoke(
                        todo_id,
                        titleEt.text.toString(),
                        detailEt.text.toString(),
                        timeEt.text.toString()
                    )
                }
            } else {
                if (timeEt.text == "点击设置时间") {
                    Toast.makeText(context, "请设置时间！", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                addTodoListener?.invoke(
                    titleEt.text.toString(),
                    detailEt.text.toString(),
                    timeEt.text.toString()
                )
            }
            dismiss()
        }
        timeEt.setOnClickListener {
            openCalender(view.context, timeEt)
        }

    }

    override fun onResume() {
        super.onResume()
        if (data != null) {
            titleEt.setText(data!!.title)
            timeEt.text = data!!.time
            detailEt.setText(data!!.detail)
        } else {
            titleEt.setText("")
            timeEt.text = "点击设置时间"
            detailEt.setText("")
        }
    }

    override fun dismiss() {
        super.dismiss()
        data = null
    }

    fun setData(response: TodoResponse) {
        data = response
    }

    @SuppressLint("SetTextI18n")
    private fun openCalender(context: Context, textView: TextView) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            context,
            null,
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog.show()

        // 确认按钮
        datePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
            // 确认年月日
            val year = datePickerDialog.datePicker.year;
            val monthOfYear = datePickerDialog.datePicker.month + 1;
            val dayOfMonth = datePickerDialog.datePicker.dayOfMonth;
            textView.text = "$year.$monthOfYear.$dayOfMonth"
            // 关闭dialog
            datePickerDialog.dismiss()
        }
    }

    private var addTodoListener: ((title: String, detail: String, time: String) -> Unit)? = null
    private var updateTodoListener: ((id: String, title: String, detail: String, time: String) -> Unit)? =
        null

    fun setAddTodoListener(listener: (title: String, detail: String, time: String) -> Unit) {
        addTodoListener = listener
    }

    fun setUpdateListener(listener: (id: String, title: String, detail: String, time: String) -> Unit) {
        updateTodoListener = listener
    }
}